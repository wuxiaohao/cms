package org.wxh.user.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;
import net.coobird.thumbnailator.resizers.configurations.ScalingMode;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.wxh.basic.common.Constant;
import org.wxh.basic.exception.MyException;
import org.wxh.basic.model.AjaxObj;
import org.wxh.basic.util.StringUtils;
import org.wxh.topic.model.ChannelTree;
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
import org.wxh.util.SecurityUtil;

/**
 * 用户管理的控制层
 * @author wxh
 *
 */

@Controller
@RequestMapping("/admin/user")
@AuthClass("login")
public class UserController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IUserService userService;
	@Autowired
	private IGroupService groupService;
	@Autowired
	private IRoleService roleService;
	@Autowired
	private IChannelService channelService;

	/**
	 * 用户列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/users",method=RequestMethod.POST)
	@AuthMethod(role=Constant.AuthConstant.ROLE_COMMADMIN)
	public ModelAndView listUser(Model model){
		model.addAttribute("datas", userService.findUser());
		return new ModelAndView("user/list");
	}
	
	private void initAddUser(Model model){
		model.addAttribute("groups",groupService.listGroup());
		model.addAttribute("roles", roleService.listRole());
	}
	
	/**
	 * 返回添加用户的界面
	 */
	@RequestMapping(value = "/addUI", method = RequestMethod.POST)
	@AuthMethod(role=Constant.AuthConstant.ROLE_COMMADMIN)
	public String addUser(Model model){
		model.addAttribute("userDto",new UserDto()); //user,user
		initAddUser(model);
		return "user/add";
	}
	
	/**
	 * 添加用户
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@AuthMethod(role=Constant.AuthConstant.ROLE_COMMADMIN)
	public ModelAndView addUser(@Valid UserDto userDto, BindingResult br, Model model){
		if(br.hasErrors()){
			initAddUser(model);
			return new ModelAndView("user/add");
		}
		userService.add(userDto.getUser(), userDto.getRoleIds(), userDto.getGroupIds());
		model.addAttribute(Constant.BaseCode.SUCCESS, "添加用户成功!");
		return listUser(model);
	}
	
	/**
	 * 校验用户名是否已存在
	 */
	@RequestMapping(value = "/checkUserName", method = RequestMethod.POST)
	@AuthMethod(role=Constant.AuthConstant.ROLE_COMMADMIN)
	public void checkUserName(HttpServletResponse response,String username) {
		try {
			if(userService.checkUserName(username)){
				response.getWriter().write(Constant.FALSE);
			} else {
				response.getWriter().write(Constant.TRUE);
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
	public ModelAndView deleteUser(Model model,@PathVariable int id){
		userService.delete(id);
		model.addAttribute(Constant.BaseCode.SUCCESS, "删除用户成功!");
		return listUser(model);
	}
	
	/**
	 * 修改用户状态
	 */
	@RequestMapping(value="/updateStatus/{id}",method=RequestMethod.POST)
	public ModelAndView updateStatus(Model model,@PathVariable int id) {
		userService.updateStatus(id);
		model.addAttribute(Constant.BaseCode.SUCCESS, "用户状态已更改!");
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
	public ModelAndView update(@PathVariable int id,@Valid UserDto userDto,BindingResult br,Model model) {
		if(br.hasErrors()) {
			logger.info("修改用户信息失败，失败信息：[{}]", br.hasErrors());
			initAddUser(model);
			return new ModelAndView("user/update");
		}
		User ou = userService.load(id);
		ou.setNickname(userDto.getNickname());
		ou.setPhone(userDto.getPhone());
		ou.setEmail(userDto.getEmail());
		ou.setStatus(userDto.getStatus());
		userService.update(ou, userDto.getRoleIds(), userDto.getGroupIds());
		model.addAttribute(Constant.BaseCode.SUCCESS, "用户信息已更新!");
		return listUser(model);
	}
	
	/**
	 * 修改指定用户的密码界面
	 */
	@RequestMapping(value="/updatePwdAllUI",method = RequestMethod.POST)
	public ModelAndView updatePwdAllUI(int id,String username,Model model) {
		model.addAttribute("userid", id);
		model.addAttribute("username", username);
		return new ModelAndView("user/updatePwdAll");
	}
	/**
	 * 修改指定用户的密码
	 * @param password
	 * @param confirmPwd
	 * @return
	 */
	@RequestMapping(value="/updatePwdAll",method = RequestMethod.POST)
	public ModelAndView updatePwdAll(int id,String password,String confirmPwd,Model model) {
		if (password == null || confirmPwd == null) {
			logger.error("修改指定用户的密码失败,请求参数:[id:{}，password:{}，confirmPwd:{}]",new Object[]{id,password,confirmPwd});
			return new ModelAndView("user/updatePwdAll");
		}
		if ( !password.equals(confirmPwd) ) {
			logger.error("两次输入的密码不一致，请求参数：[id:{}，password:{}，confirmPwd:{}]",new Object[]{id,password,confirmPwd});
			return new ModelAndView("user/updatePwdAll");
		}
		
		userService.updatePwdByNewPwd(id, password);
		model.addAttribute(Constant.BaseCode.SUCCESS, "密码修改成功！");
		return listUser(model);
	}
	
	/**
	 * 修改个人密码的界面
	 */
	@RequestMapping(value="/updatePwdUI",method=RequestMethod.POST)
	@AuthMethod
	public ModelAndView updatePwdUI(Model model,HttpSession session) {
		User u = (User)session.getAttribute(Constant.BaseCode.LOGIN_USER);
		model.addAttribute(u);
		return new ModelAndView("user/updatePwd");
	}
	
	/**
	 * 修改个人密码
	 */
	@RequestMapping(value="/updatePwd",method=RequestMethod.POST)
	@AuthMethod
	public ModelAndView updatePwd(Model model,int id,String oldPwd,String password,HttpSession session) {
		try {
			userService.updatePwd(id, oldPwd, password);
			model.addAttribute(Constant.BaseCode.SUCCESS, "密码修改成功!");
			return showMySelf(model, session);
		} catch (MyException e) {
			logger.error("修改密码失败，请求参数：[id:{},oldPwd:{},password:{}]",new Object[]{id,oldPwd,password});
			model.addAttribute(Constant.BaseCode.ERROR, e.getMessage());
			return updatePwdUI(model, session);
		}
	}
	
	/**
	 * 显示某个用户的详细信息
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.POST)
	@AuthMethod(role=Constant.AuthConstant.ROLE_COMMADMIN)
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
	@AuthMethod(role=Constant.AuthConstant.ROLE_COMMADMIN)
	public String showSelf(Model model,HttpSession session) {
		User user = (User)session.getAttribute(Constant.BaseCode.LOGIN_USER);
		model.addAttribute(user);
		model.addAttribute("gs",userService.listUserGroups(user.getId()));
		model.addAttribute("rs",userService.listUserRoles(user.getId()));
		return "user/show";
	}
	
	/**
	 * 用户查询个人信息
	 */
	@RequestMapping(value="/showMySelf",method=RequestMethod.POST)
	@AuthMethod
	public ModelAndView showMySelf(Model model,HttpSession session) {
		User user = (User)session.getAttribute(Constant.BaseCode.LOGIN_USER);
		model.addAttribute(user);
		model.addAttribute("gs",userService.listUserGroups(user.getId()));
		model.addAttribute("rs",userService.listUserRoles(user.getId()));
		return new ModelAndView("user/showSelf");
	}
	
	/**
	 * 修改个人信息的界面
	 */
	@RequestMapping(value="/updateSelfUI",method=RequestMethod.POST)
	@AuthMethod
	public String updateSelf(Model model,HttpSession session) {
		User u = (User)session.getAttribute(Constant.BaseCode.LOGIN_USER);
		model.addAttribute(new UserDto(u));
		return "user/updateSelf";
	}
	
	/**
	 * 修改个人信息
	 */
	@RequestMapping(value="/updateSelf",method=RequestMethod.POST)
	@AuthMethod
	public ModelAndView updateSelf(@Valid UserDto userDto,BindingResult br,Model model,HttpSession session) {
		if(br.hasErrors()) {
			return new ModelAndView("user/updateSelf");
		}
		//TODO 1.更新个人信息
		User ou = userService.load( userDto.getId() );
		ou.setNickname( userDto.getNickname() );
		ou.setPhone( userDto.getPhone() );
		ou.setEmail( userDto.getEmail() );
		ou.setIcon( userDto.getIcon() );
		userService.update( ou );
		
		String newIcon = userDto.getIcon();
		String oldIcon = userDto.getOldIcon();
		if ( !StringUtils.isEmpty( newIcon ) && !StringUtils.isEmpty( oldIcon ) 
				&& !newIcon.equals(oldIcon) ) {
			//TODO 2.删除旧的头像图片
			userService.deleteIcon( oldIcon );
		}
		
		session.setAttribute(Constant.BaseCode.LOGIN_USER, ou);
		model.addAttribute(Constant.BaseCode.SUCCESS, "个人信息修改成功!");
		
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
				model.addAttribute("uAdmin",Constant.YES);
			}
		}
		return "/user/listChannel";
	}*/
	
	/**
	 * 返回tree的json格式数据
	 */
	@RequestMapping("/userTree/{uid}")
	@AuthMethod(role=Constant.AuthConstant.ROLE_COMMADMIN)
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
	@AuthMethod
	public void uploadIcon(HttpSession session,HttpServletResponse resp,MultipartFile ico) {
		resp.setContentType( Constant.CONTENT_TYPE );
		AjaxObj ao = new AjaxObj();
		PrintWriter out = null;
		try {
			out = resp.getWriter();
			String oldName = ico.getOriginalFilename();//图片的原始名称
			String newName = new Date().getTime() + "." + FilenameUtils.getExtension( oldName );//图片的新名称
			String realPath = session.getServletContext().getRealPath("");
			//创建临时文件存放的位置
			String path = realPath + Constant.UrlConstant.ICON_PATH_TEMP;
			File f = new File( path ); 
			if( !f.exists() ) {
				f.mkdir();
			}
			IconDto con = new IconDto();
			con.setNewName( newName ); 
			con.setOldName( oldName );
			con.setIconHeight( Constant.ICON_HEIGHT );
			con.setIconWidth( Constant.ICON_WIDTH );
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
			ao.setResult( Constant.YES );
 		} catch (IOException e) {
			e.printStackTrace();
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
	@RequestMapping(value="/confirmPic",method=RequestMethod.POST)
	@AuthMethod
	public @ResponseBody AjaxObj confirmPic(HttpSession session,int x,int y,int w,int h,String newName) {
		AjaxObj ao = new AjaxObj();
		try {
			String path = session.getServletContext().getRealPath("");
			String tpath = path + Constant.UrlConstant.ICON_PATH_TEMP + newName; //临时存放的路径
			File tf = new File(tpath);
			BufferedImage bi = ImageIO.read(tf);
			String npath = path + Constant.UrlConstant.ICON_PATH + newName; //新的存放路径
			String ttpath = path + Constant.UrlConstant.ICON_PATH_THUM + newName;//缩略图的路径
			Builder<BufferedImage> b = Thumbnails.of(bi);
			//根据坐标切割图片
			BufferedImage bi2 = b.sourceRegion(x, y, w, h).forceSize(Constant.ICON_WIDTH, Constant.ICON_HEIGHT).asBufferedImage();
			//写原图
			b.toFile(npath);
			//写缩略图
			Thumbnails.of(bi2).forceSize( Constant.ICON_WIDTH_THUMBNAIL,Constant.ICON_HEIGHT_THUMBNAIL ).scalingMode( ScalingMode.BILINEAR ).toFile( ttpath );
			//删除临时图片
			tf.delete(); 
			ao.setResult(Constant.YES);
		} catch (IOException e) {
			e.printStackTrace();
			ao.setResult(Constant.NO);
			ao.setMsg(e.getMessage());
		}
		return ao;
		
	}
	
}
