package org.wxh.sys.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.wxh.basic.common.Constant;
import org.wxh.basic.model.SystemContext;
import org.wxh.index.service.IIndexService;
import org.wxh.user.auth.AuthClass;
import org.wxh.user.auth.AuthMethod;
import org.wxh.util.BackupFileUtil;

/**
 * 网站数据备份
 * @author wxh
 *
 */

@AuthClass("login")
@Controller
@RequestMapping("/admin")
public class BackupController {
	
	@Autowired
	private IIndexService indexService;

	/**
	 * 返回备份的页面
	 * @return
	 */
	@RequestMapping(value="/backup/addUI",method=RequestMethod.POST)
	@AuthMethod(role=Constant.AuthConstant.ROLE_COMMADMIN)
	public String backup() {
		return "backup/add";
	}
	/**
	 * 备份文件
	 * @param backupFilename
	 * @return
	 */
	@RequestMapping(value="/backup/add",method=RequestMethod.POST)
	@AuthMethod(role=Constant.AuthConstant.ROLE_COMMADMIN)
	public String backup(String backupFilename ,Model model) {
		BackupFileUtil bfu = BackupFileUtil.getInstance(SystemContext.getRealPath());
		bfu.backup(backupFilename);
		model.addAttribute(Constant.BaseCode.SUCCESS, "备份成功!");
		return list(model);
	}
	/**
	 * 显示所有的备份文件
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/backups")
	@AuthMethod(role=Constant.AuthConstant.ROLE_COMMADMIN)
	public String list(Model model) {
		BackupFileUtil bfu = BackupFileUtil.getInstance(SystemContext.getRealPath());
		model.addAttribute("backups",bfu.listBackups());
		return "backup/list";
	}
	/**
	 * 删除备份文件
	 * @param name
	 * @param type
	 * @return
	 */
	@RequestMapping(value= "delete/{name}" ,method=RequestMethod.POST)
	public String delete(@PathVariable String name,String type,Model model) {
		BackupFileUtil bfu = BackupFileUtil.getInstance(SystemContext.getRealPath());
		bfu.delete(name+"."+type);
		model.addAttribute(Constant.BaseCode.SUCCESS, "删除成功!");
		return list(model);
	}
	/**
	 * 根据备份文件恢复数据
	 * @param name
	 * @param type
	 * @return
	 */
	@RequestMapping("resume/{name}")
	public String resume(@PathVariable String name,String type,Model model) {
		
		//TODO 数据还原
		BackupFileUtil bfu = BackupFileUtil.getInstance( SystemContext.getRealPath( ) );
		bfu.resume( name + "." + type );
		
		//TODO 重新生成网站首页
		indexService.generateTop( );
		indexService.generateBody( );
		indexService.generateBottom( );
		
		model.addAttribute( Constant.BaseCode.SUCCESS, "系统数据已恢复!" );
		return list( model );
	}
	
}
