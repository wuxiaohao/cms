package org.wxh.user.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;
import net.coobird.thumbnailator.resizers.configurations.ScalingMode;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import javax.jws.WebParam.Mode;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.wxh.basic.common.GlobalResult;
import org.wxh.basic.exception.MyException;
import org.wxh.sys.model.BaseInfo;
import org.wxh.topic.model.ChannelTree;
import org.wxh.topic.model.dto.AjaxObj;
import org.wxh.topic.service.IChannelService;
import org.wxh.user.auth.AuthClass;
import org.wxh.user.auth.AuthMethod;
import org.wxh.user.model.Role;
import org.wxh.user.model.RoleType;
import org.wxh.user.model.User;
import org.wxh.user.model.dto.IconDto;
import org.wxh.user.model.dto.UserDto;
import org.wxh.user.service.IGroupService;
import org.wxh.user.service.IRoleService;
import org.wxh.user.service.IUserService;
import org.wxh.util.JsonUtil;

/**
 * 用户管理的控制层
 * @author wxh
 *
 */

@Controller
@RequestMapping("/admin/user")
@AuthClass("login")
public class UserController {
	
	private static final Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	private IUserService userService;
	@Autowired
	private IGroupService groupService;
	@Autowired
	private IRoleService roleService;
	@Autowired
	private IChannelService channelService;
	
	public IChannelService getChannelService() {
		return channelService;
	}
	public void setChannelService(IChannelService channelService) {
		this.channelService = channelService;
	}
	public IUserService getUserService() {
		return userService;
	}
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	public IGroupService getGroupService() {
		return groupService;
	}
	public void setGroupService(IGroupService groupService) {
		this.groupService = groupService;
	}
	public IRoleService getRoleService() {
		return roleService;
	}
	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}

	/**
	 * 用户列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/users",method=RequestMethod.POST)
	public String listUser(Model model){
		model.addAttribute("datas", userService.findUser());
		return "user/list";
	}
	
	private void initAddUser(Model model){
		model.addAttribute("groups",groupService.listGroup());
		model.addAttribute("roles", roleService.listRole());
	}
	
	/**
	 * 返回添加用户的界面
	 */
	@RequestMapping(value = "/addUI", method = RequestMethod.POST)
	public String addUser(Model model){
		model.addAttribute("userDto",new UserDto()); //user,user
		initAddUser(model);
		return "user/add";
	}
	
	/**
	 * 添加用户
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addUser(@Valid UserDto userDto, BindingResult br, Model model){
		if(br.hasErrors()){
			initAddUser(model);
			return "user/add";
		}
		userService.add(userDto.getUser(), userDto.getRoleIds(), userDto.getGroupIds());
		model.addAttribute("success", "添加用户成功!");
		return listUser(model);
	}
	
	/**
	 * 校验用户名是否已存在
	 */
	@RequestMapping(value = "/checkUserName", method = RequestMethod.POST)
	public void checkUserName(HttpServletResponse response,String username) {
		try {
			if(userService.checkUserName(username)){
				response.getWriter().write("false");
			} else {
				response.getWriter().write("true");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除用户
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String deleteUser(Model model,@PathVariable int id){
		userService.delete(id);
		model.addAttribute("success", "删除用户成功!");
		return listUser(model);
	}
	
	/**
	 * 修改用户状态
	 */
	@RequestMapping(value="/updateStatus/{id}",method=RequestMethod.POST)
	public String updateStatus(Model model,@PathVariable int id) {
		userService.updateStatus(id);
		model.addAttribute("success", "用户状态已更改!");
		return listUser(model);
	}
	
	/**
	 * 修改用户信息的界面
	 */
	@RequestMapping(value="/updateUI/{id}",method=RequestMethod.POST)
	public String update(@PathVariable int id,Model model) {
		User u = userService.load(id);
		model.addAttribute("userDto",new UserDto(u,
				userService.listUserRoleIds(id),
				userService.listUserGroupIds(id)));
		initAddUser(model);
		return "user/update";
	}
	
	/**
	 * 修改用户信息
	 */
	@RequestMapping(value="/update/{id}",method=RequestMethod.POST)
	public String update(@PathVariable int id,@Valid UserDto userDto,BindingResult br,Model model) {
		if(br.hasErrors()) {
			logger.info(br.hasErrors());
			initAddUser(model);
			return "user/update";
		}
		User ou = userService.load(id);
		ou.setNickname(userDto.getNickname());
		ou.setPhone(userDto.getPhone());
		ou.setEmail(userDto.getEmail());
		ou.setStatus(userDto.getStatus());
		userService.update(ou, userDto.getRoleIds(), userDto.getGroupIds());
		model.addAttribute("success", "用户信息已更新!");
		return listUser(model);
	}
	
	/**
	 * 修改个人密码的界面
	 */
	@RequestMapping(value="/updatePwdUI",method=RequestMethod.POST)
	@AuthMethod
	public String updatePwdUI(Model model,HttpSession session) {
		User u = (User)session.getAttribute("loginUser");
		model.addAttribute(u);
		return "user/updatePwd";
	}
	
	/**
	 * 修改个人密码
	 */
	@RequestMapping(value="/updatePwd",method=RequestMethod.POST)
	@AuthMethod
	public String updatePwd(Model model,int id,String oldPwd,String password,HttpSession session) {
		try {
			userService.updatePwd(id, oldPwd, password);
			model.addAttribute("success", "密码修改成功!");
			return showMySelf(model, session);
		} catch (MyException e) {
			model.addAttribute("error", e.getMessage());
			return updatePwdUI(model, session);
		}
	}
	
	/**
	 * 显示某个用户的详细信息
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.POST)
	public String show(@PathVariable int id,Model model) {
		model.addAttribute(userService.load(id));
		model.addAttribute("gs",userService.listUserGroups(id));
		model.addAttribute("rs",userService.listUserRoles(id));
		return "user/show";
	}
	
	/**
	 * 管理员查询用户信息
	 */
	@RequestMapping(value="/showSelf",method=RequestMethod.POST)
	public String showSelf(Model model,HttpSession session) {
		User user = (User)session.getAttribute("loginUser");
		model.addAttribute(user);
		model.addAttribute("gs",userService.listUserGroups(user.getId()));
		model.addAttribute("rs",userService.listUserRoles(user.getId()));
		return "user/show";
	}
	
	/**
	 * 用户查询个人信息
	 */
	@RequestMapping(value="/showMySelf",method=RequestMethod.POST)
	public String showMySelf(Model model,HttpSession session) {
		User user = (User)session.getAttribute("loginUser");
		model.addAttribute(user);
		model.addAttribute("gs",userService.listUserGroups(user.getId()));
		model.addAttribute("rs",userService.listUserRoles(user.getId()));
		return "user/showSelf";
	}
	
	/**
	 * 修改个人信息的界面
	 */
	@RequestMapping(value="/updateSelfUI",method=RequestMethod.POST)
	@AuthMethod
	public String updateSelf(Model model,HttpSession session) {
		User u = (User)session.getAttribute("loginUser");
		model.addAttribute(new UserDto(u));
		return "user/updateSelf";
	}
	
	/**
	 * 修改个人信息
	 */
	@RequestMapping(value="/updateSelf",method=RequestMethod.POST)
	@AuthMethod
	public String updateSelf(@Valid UserDto userDto,BindingResult br,Model model,HttpSession session) {
		if(br.hasErrors()) {
			return "user/updateSelf";
		}
		User ou = userService.load( userDto.getId() );
		ou.setNickname( userDto.getNickname() );
		ou.setPhone( userDto.getPhone() );
		ou.setEmail( userDto.getEmail() );
		ou.setIcon( userDto.getIcon() );
		userService.update( ou );
		session.setAttribute("loginUser", ou);
		model.addAttribute("success", "个人信息修改成功!");
		return showMySelf(model,session);
	}
	
	
	/**
	 * 查询管理栏目的界面
	 */
	/*@RequestMapping(value="/listChannels/{uid}",method=RequestMethod.POST)
	public String listChannels(@PathVariable int uid,Model model) {
		model.addAttribute(userService.load(uid));
		List<Role> rs = userService.listUserRoles(uid);
		for(Role r:rs) {
			if(r.getRoleType()==RoleType.ROLE_ADMIN) {
				model.addAttribute("uAdmin",1);
			}
		}
		return "/user/listChannel";
	}*/
	
	/**
	 * 返回tree的json格式数据
	 */
	@RequestMapping("/userTree/{uid}")
	public @ResponseBody List<ChannelTree> groupTree(@PathVariable int uid) {
		List<Role> rs = userService.listUserRoles(uid);
		for(Role r:rs) {
			if(r.getRoleType()==RoleType.ROLE_ADMIN) {   //如果是管理员
				return channelService.generateTreeAll();
			}
		}
		return groupService.generateUserChannelTreeAll(uid);
	}
	/**
	 * 上传头像
	 * @param session
	 * @param resp
	 * @param ico
	 */
	@RequestMapping(value="/uploadIcon",method=RequestMethod.POST)
	public void uploadIcon(HttpSession session,HttpServletResponse resp,MultipartFile ico) {
		resp.setContentType( "text/plain;charset=utf-8" );
		AjaxObj ao = new AjaxObj();
		PrintWriter out = null;
		try {
			out = resp.getWriter();
			String oldName = ico.getOriginalFilename();//图片的原始名称
			String newName = new Date().getTime() + "." + FilenameUtils.getExtension( oldName );//图片的新名称
			String realPath = session.getServletContext().getRealPath("");
			//创建临时文件存放的位置
			String path = realPath + GlobalResult.ICON_PATH + "/temp/";
			File f = new File( path ); 
			if( !f.exists() ) {
				f.mkdir();
			}
			IconDto con = new IconDto();
			con.setNewName( newName ); 
			con.setOldName( oldName );
			con.setIconHeight( GlobalResult.ICON_HEIGHT );
			con.setIconWidth( GlobalResult.ICON_WIDTH );
			//获取上传图片的宽度，高度
			BufferedImage bi = ImageIO.read(ico.getInputStream());
			Builder<BufferedImage> b = Thumbnails.of( bi );
			BufferedImage bi2 = null;
			if( bi.getWidth() < 300 && bi.getHeight() < 200 ) { //图片的大小符合要求
				bi2 = b.scale( 1.0f ).asBufferedImage();
			} else {  //如果图片超出大小，则按等比例压缩
				bi2 = b.size(300, 200).keepAspectRatio(true).asBufferedImage();
			}
			//保存图片
			b.toFile( path + newName);
			con.setImgHeight( bi2.getHeight() );
			con.setImgWidth( bi2.getWidth() );
			ao.setObj( con );
			ao.setResult( 1 );
 		} catch (IOException e) {
			e.printStackTrace();
			ao.setResult( 0 );
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
	@RequestMapping(value="/confirmPic",method=RequestMethod.POST)
	public @ResponseBody AjaxObj confirmPic(HttpSession session,int x,int y,int w,int h,String newName) {
		AjaxObj ao = new AjaxObj();
		try {
			String path = session.getServletContext().getRealPath("");
			String tpath = path+GlobalResult.ICON_PATH+"/temp/"+newName; //临时存放的路径
			File tf = new File(tpath);
			BufferedImage bi = ImageIO.read(tf);
			String npath = path+GlobalResult.ICON_PATH+"/"+newName; //新的存放路径
			String ttpath = path+GlobalResult.ICON_PATH+"/thumbnail/"+newName;//缩略图的路径
			Builder<BufferedImage> b = Thumbnails.of(bi);
			//根据坐标切割图片
			BufferedImage bi2 = b.sourceRegion(x, y, w, h).forceSize(GlobalResult.ICON_WIDTH, GlobalResult.ICON_HEIGHT).asBufferedImage();
			//写原图
			b.toFile(npath);
			//写缩略图
			Thumbnails.of(bi2).forceSize( GlobalResult.ICON_WIDTH_THUMBNAIL,GlobalResult.ICON_HEIGHT_THUMBNAIL ).scalingMode( ScalingMode.BILINEAR ).toFile( ttpath );
			//删除临时图片
			tf.delete(); 
			ao.setResult(1);
		} catch (IOException e) {
			e.printStackTrace();
			ao.setResult(0);
			ao.setMsg(e.getMessage());
		}
		return ao;
		
	}
	
}
