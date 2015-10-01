package org.wxh.index.service.impl;

import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wxh.basic.model.SystemContext;
import org.wxh.index.service.IIndexService;
import org.wxh.topic.model.Channel;
import org.wxh.topic.service.IChannelService;
import org.wxh.util.BaseInfoUtil;
import org.wxh.util.FreemarkerUtil;

/**
 * 根据ftl模板代码生成静态页面的控制层
 * @author wxh
 *
 */

@Service("indexService")
public class IndexService implements IIndexService {
	
	private static final Logger logger = Logger.getLogger(IndexService.class);
	
	private String ftlPath;
	private String outPath;
	private FreemarkerUtil util;
	
	/**
	 * 创建有参构造函数，基于名称注入ftlPath，outPath两个参数
	 * @param ftlPath  
	 * @param outPath
	 */
	@Autowired(required=true)
	public IndexService(String ftlPath, String outPath) {
		super();
		if(util == null){
			this.ftlPath = ftlPath;
			this.outPath = outPath;
			util = FreemarkerUtil.getInstance(ftlPath);
		}
	}

	@Autowired
	private IChannelService channelService;

	public IChannelService getChannelService() {
		return channelService;
	}
	public void setChannelService(IChannelService channelService) {
		this.channelService = channelService;
	}

	@Override
	public void generateTop() {
		List<Channel> list = channelService.listTopNavChannel();
		Map<String,Object> root = new HashMap<String,Object>();
		root.put("navs", list);
		root.put("baseInfo", BaseInfoUtil.getInstacne().read());
		String outfile = SystemContext.getRealPath()+outPath+"/top.jsp";
		util.fprint(root, "/top.ftl", outfile);
		logger.info("=============重新生成了顶部信息====================");
	}

	@Override
	public void generateBottom() {
		
		logger.info("=============重新生成了底部信息====================");
	}

	@Override
	public void generateBody() {
		// TODO Auto-generated method stub

	}

}
