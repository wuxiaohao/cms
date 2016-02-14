package org.wxh.index.service.impl;

import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wxh.basic.model.SystemContext;
import org.wxh.index.model.IndexTopic;
import org.wxh.index.service.IIndexPicService;
import org.wxh.index.service.IIndexService;
import org.wxh.sys.model.BaseInfo;
import org.wxh.topic.model.Channel;
import org.wxh.topic.model.ChannelType;
import org.wxh.topic.model.Topic;
import org.wxh.topic.model.Video;
import org.wxh.topic.service.IAttachmentService;
import org.wxh.topic.service.IChannelService;
import org.wxh.topic.service.IKeywordService;
import org.wxh.topic.service.ITopicService;
import org.wxh.topic.service.IVideoService;
import org.wxh.topic.service.impl.AttachmentService;
import org.wxh.util.BaseInfoUtil;
import org.wxh.util.FreemarkerUtil;
import org.wxh.util.PropertiesUtil;

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
	@Autowired
	private ITopicService topicService;
	@Autowired
	private IIndexPicService indexPicService;
	@Autowired
	private IKeywordService keyworkService;
	@Autowired
	private IAttachmentService attachmentService;
	@Autowired
	private IVideoService videoService;

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
		Map<String,Object> root = new HashMap<String,Object>();
		root.put("baseInfo", BaseInfoUtil.getInstacne().read());
		String outfile = SystemContext.getRealPath()+outPath+"/bottom.jsp";
		util.fprint(root, "/bottom.ftl", outfile);
		logger.info("=============重新生成了底部信息====================");
	}

	@Override
	public void generateBody() {
		Map<String,Object> root = new HashMap<String,Object>();
		//1、获取所有的首页栏目(置顶新闻未设置)
		List<Channel> cs = channelService.listAllIndexChannel(ChannelType.TOPIC_LIST);
		//2、根据首页栏目创建相应的IndexTopic对象
		//加载indexChannel.properties文件
		Properties prop = PropertiesUtil.getInstance().load("indexChannel");
		Map<String,IndexTopic> topics = new HashMap<String, IndexTopic>();
		for(Channel c:cs) {
			int cid = c.getId();
			String[] xs = prop.getProperty(cid+"").split("_");
			String order = xs[0];//文章排序
			int num = Integer.parseInt(xs[1]);//文章数量
			IndexTopic it = new IndexTopic();
			it.setCid(cid);
			it.setCname(c.getName());
			List<Topic> tops = topicService.listTopicByChannelAndNumber(cid, num);//硬编码，首页栏目应该可以配置排序，数量等
//			System.out.println(cid+"--"+tops);
			it.setTopics(tops);
			topics.put(order, it);
		}
		root.put("ts", topics);
		
		//3、更新宣传图片
		BaseInfo bi = BaseInfoUtil.getInstacne().read();
		int picnum = bi.getIndexPicNumber(); 
		root.put("pics", indexPicService.listIndexPicByNum(picnum));  //硬编码。数量应该可配置
		//4、更新新闻滚动图片(没排序)
		root.put("newsPic", attachmentService.listAttachmentByIndexPic(5));//硬编码。数量应该可配置
		//5、更新视频新闻栏目(没排序)
		List<Video> videos = videoService.listVideoByNum(60,6);//硬编码。栏目id和数量应该可配置
		//root.put("keywords", keyworkService.getMaxTimesKeyword(12));  //获取关键字，硬编码。
		//root.put("xxgk", topicService.loadLastedTopicByColumn(43)); //栏目id43是校园概况，属于文章内容栏目，目前是硬编码
		String outfile = SystemContext.getRealPath()+outPath+"/body.jsp";
		util.fprint(root, "/body.ftl", outfile);
		logger.info("=============重新生成了body信息====================");
	}

	/**
	 * 更新友情链接
	 */
	@Override
	public void generateLink() {
		
	}

}
