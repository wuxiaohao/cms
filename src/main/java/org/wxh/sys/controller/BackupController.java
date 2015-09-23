package org.wxh.sys.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.wxh.basic.model.SystemContext;
import org.wxh.user.auth.AuthClass;
import org.wxh.util.BackupFileUtil;

/**
 * 网站数据备份
 * @author wxh
 *
 */

@AuthClass
@Controller
@RequestMapping("/admin")
public class BackupController {
	
	/*@Autowired
	private IIndexService indexService;
	
	public IIndexService getIndexService() {
		return indexService;
	}
	public void setIndexService(IIndexService indexService) {
		this.indexService = indexService;
	}*/

	/**
	 * 返回备份的页面
	 * @return
	 */
	@RequestMapping(value="/backup/add",method=RequestMethod.GET)
	public String backup() {
		return "backup/add";
	}
	/**
	 * 备份文件
	 * @param backupFilename
	 * @return
	 */
	@RequestMapping(value="/backup/add",method=RequestMethod.POST)
	public String backup(String backupFilename) {
		BackupFileUtil bfu = BackupFileUtil.getInstance(SystemContext.getRealPath());
		bfu.backup(backupFilename);
		return "redirect:/admin/backups";
	}
	/**
	 * 显示所有的备份文件
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/backups")
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
	@RequestMapping("delete/{name}")
	public String delete(@PathVariable String name,String type) {
		BackupFileUtil bfu = BackupFileUtil.getInstance(SystemContext.getRealPath());
		bfu.delete(name+"."+type);
		return "redirect:/admin/backups";
	}
	/**
	 * 根据备份文件恢复数据
	 * @param name
	 * @param type
	 * @return
	 */
	@RequestMapping("resume/{name}")
	public String resume(@PathVariable String name,String type) {
		BackupFileUtil bfu = BackupFileUtil.getInstance(SystemContext.getRealPath());
		bfu.resume(name+"."+type);
		//indexService.generateTop();
		//indexService.generateBody();
		//indexService.generateBottom();
		return "redirect:/admin/backups";
	}
	
}
