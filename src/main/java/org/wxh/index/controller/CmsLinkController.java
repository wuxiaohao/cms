package org.wxh.index.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.wxh.basic.common.CmsLinkConstant;
import org.wxh.basic.common.Constant;
import org.wxh.basic.model.AjaxObj;
import org.wxh.basic.model.SystemContext;
import org.wxh.basic.util.JsonUtils;
import org.wxh.index.model.CmsLink;
import org.wxh.index.model.dto.CmsLinkDto;
import org.wxh.index.service.ICmsLinkService;
import org.wxh.index.service.IIndexService;
import org.wxh.user.auth.AuthClass;
import org.wxh.user.auth.AuthMethod;
import org.wxh.util.JsonUtil;

/**
 * 友情链接管理
 * @author wxh
 *
 */

@Controller
@RequestMapping("/admin/cmsLink")
@AuthClass("login")
public class CmsLinkController implements CmsLinkConstant{

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ICmsLinkService cmsLinkService;
	@Autowired
	private IIndexService indexService;
	
	/**
	 * 返回所有超链接的列表页面
	 * @param model
	 * @param type
	 * @return
	 */
	@RequestMapping("/links")
	@AuthMethod(role=Constant.AuthConstant.ROLE_COMMADMIN)
	public String list(Model model,String type,HttpSession session) {
		model.addAttribute(DATAS, cmsLinkService.findByType(type));
		model.addAttribute(TYPES,cmsLinkService.listAllType());
		Map<String,Integer> m = cmsLinkService.getMinAndMaxPos();
		model.addAttribute(Constant.BaseCode.MIN, m.get(Constant.BaseCode.MIN));
		model.addAttribute(Constant.BaseCode.MAX,m.get(Constant.BaseCode.MAX));
		model.addAttribute(TYPE,type);
		return GOTO_LIST;
	}
	/**
	 * 返回添加超链接的界面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/addUI",method=RequestMethod.POST)
	@AuthMethod(role=Constant.AuthConstant.ROLE_COMMADMIN)
	public String addUI(Model model) {
		model.addAttribute(new CmsLink());
		model.addAttribute(TYPES,cmsLinkService.listAllType());
		return GOTO_ADD;
	}
	/**
	 * 添加超链接
	 * @param cmsLink
	 * @param br
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@AuthMethod(role=Constant.AuthConstant.ROLE_COMMADMIN)
	public String add(@Validated CmsLink cmsLink,BindingResult br,Model model,HttpSession session) {
		if(br.hasFieldErrors()) {
			return GOTO_ADD;
		}
		cmsLinkService.add(cmsLink);
		model.addAttribute(Constant.BaseCode.SUCCESS, "超链接添加成功!");
		indexService.generateLink();//重新生成超链接页面
		return list(model,null,session);
	}
	/**
	 * 删除超链接
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete/{id}",method=RequestMethod.POST)
	public String delete(@PathVariable int id ,@RequestParam(required=false) String type,Model model,HttpSession session) {
		cmsLinkService.delete(id);
		model.addAttribute(Constant.BaseCode.SUCCESS, "超链接删除成功!");
		indexService.generateLink();//重新生成超链接页面
		return list(model,type,session);
	}
	/**
	 * 返回修改超链接的界面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/updateUI/{id}",method=RequestMethod.POST)
	@AuthMethod(role=Constant.AuthConstant.ROLE_COMMADMIN)
	public String updateUI(@PathVariable int id,Model model) {
		model.addAttribute(cmsLinkService.load(id));
		model.addAttribute(TYPES,cmsLinkService.listAllType());
		return GOTO_UPDATE;
	}
	/**
	 * 修改超修链接
	 * @param id
	 * @param cmsLink
	 * @param br
	 * @return
	 */
	@RequestMapping(value="/update/{id}",method=RequestMethod.POST)
	@AuthMethod(role=Constant.AuthConstant.ROLE_COMMADMIN)
	public String update(@PathVariable int id,@Validated CmsLink cmsLink,String oldPic,BindingResult br,Model model,HttpSession session) {
		if(br.hasFieldErrors()) {
			logger.error("修改超修链接失败，请求参数：[id:{},cmsLink:{},oldPic:{}]",new Object[]{id,JsonUtils.object2String(cmsLink),oldPic});
			return GOTO_UPDATE;
		}
		if( !oldPic.equals(cmsLink.getPicName()) ) {
			//删除原来旧的图片
			String realPath = SystemContext.getRealPath();
			String path = realPath + Constant.UrlConstant.LINK_PATH;//新闻图片存放的位置
			new File( path + oldPic ).delete();
		}
		CmsLink tcl = cmsLinkService.load(id);
		tcl.setNewWin(cmsLink.getNewWin());
		tcl.setTitle(cmsLink.getTitle());
		tcl.setType(cmsLink.getType());
		tcl.setUrl(cmsLink.getUrl());
		tcl.setUrlClass(cmsLink.getUrlClass());
		tcl.setUrlId(cmsLink.getUrlId());
		tcl.setPicName(cmsLink.getPicName());
		cmsLinkService.update(tcl);
		model.addAttribute(Constant.BaseCode.SUCCESS, "修改成功!");
		indexService.generateLink();//重新生成超链接页面
		return list(model,null,session);
	}
	/**
	 * 显示某个超链接详细信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.POST)
	@AuthMethod(role=Constant.AuthConstant.ROLE_COMMADMIN)
	public String show(@PathVariable int id,Model model) {
		model.addAttribute(cmsLinkService.load(id));
		return GOTO_SHOW;
	}
	/**
	 * 上传超链接的图片
	 * @param session
	 * @param resp
	 * @param pic
	 */
	@RequestMapping(value="/uploadPicLink",method=RequestMethod.POST)
	@AuthMethod(role=Constant.AuthConstant.ROLE_COMMADMIN)
	public void uploadPicLink(HttpSession session,HttpServletResponse resp,MultipartFile pic) {
		resp.setContentType(Constant.CONTENT_TYPE);
		AjaxObj ao = new AjaxObj();
		PrintWriter out = null;
		try {
			out = resp.getWriter();
			String oldName = pic.getOriginalFilename(); //图片的原始名称
			String newName = new Date().getTime() + "." + FilenameUtils.getExtension(oldName);//图片的新名称
			String realPath = session.getServletContext().getRealPath("");
			//创建临时文件存放的位置
			String path = realPath + Constant.UrlConstant.LINK_PATH_TEMP;
			File f = new File( path );
			if( !f.exists() ) {
				f.mkdir();
			}
			CmsLinkDto dto = new CmsLinkDto();
			dto.setNewName( newName );
			dto.setOldName( oldName );
			//超链接图片设定宽度
			dto.setLinkPicWidth( Constant.LINKPIC_WIDTH );
			dto.setLinkPicHeight( Constant.LINKPIC_HEIGHT );
			//获取上传图片的宽，高
			BufferedImage bi = ImageIO.read( pic.getInputStream() );
			Builder<BufferedImage> b = Thumbnails.of( bi );
			BufferedImage bi2 = null;
			if( bi.getWidth() < 800 && bi.getHeight() <200 ) { //超链接图片设定的宽度
				bi2 = b.scale( 1.0f ).asBufferedImage();
			} else { //如果图片超出大小，则按等比例压缩
				bi2 = b.size(600, 150).keepAspectRatio(true).asBufferedImage();
			}
			//保存图片
			b.toFile( path + newName );
			dto.setImgHeight( bi2.getHeight() );
			dto.setImgWidth( bi2.getWidth() );
			ao.setObj( dto );
			ao.setResult( Constant.YES );
		} catch (Exception e) {
			logger.error("上传超链接的图片异常，异常信息：[{}]", e.getMessage() );
			ao.setResult( Constant.NO );
			ao.setMsg( e.getMessage() );
		} finally {
			out.println( JsonUtil.getInstance().obj2json( ao ) );
			out.flush();
		}
		
	}
	/**
	 * 处理被剪切后的图片的坐标，并上传
	 * @param session
	 * @param x 
	 * @param y
	 * @param w
	 * @param h
	 * @param newName
	 * @return
	 */
	@RequestMapping( value = "/confirmPic", method = RequestMethod.POST )
	@AuthMethod(role=Constant.AuthConstant.ROLE_COMMADMIN)
	public @ResponseBody AjaxObj confirmPic(HttpSession session,int x,int y,int w,int h,String newName) {
		AjaxObj ao = new AjaxObj();
		try {
			String path = session.getServletContext().getRealPath("");
			String tpath = path + Constant.UrlConstant.LINK_PATH_TEMP  + newName;//临时存放的路径
			File tf = new File( tpath );
			BufferedImage bi = ImageIO.read( tf );
			String npath = path + Constant.UrlConstant.LINK_PATH + newName; //新的存放路径
			Builder<BufferedImage> b = Thumbnails.of( bi );
			//根据坐标切割图片
			b.sourceRegion(x, y, w, h).forceSize( Constant.LINKPIC_WIDTH, Constant.LINKPIC_HEIGHT ).asBufferedImage();
			//保存图片
			b.toFile( npath );
			//删除临时图片
			tf.delete(); 
			ao.setResult( Constant.YES );
		} catch (Exception e) {
			e.printStackTrace();
			ao.setResult( Constant.NO );
			ao.setMsg(e.getMessage());
			logger.error("处理被剪切后的图片,失败信息：[{}]",e.getMessage());
		}
		return ao;
	}
}
