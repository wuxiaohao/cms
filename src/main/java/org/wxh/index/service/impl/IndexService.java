package org.wxh.index.service.impl;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wxh.basic.common.Constant;
import org.wxh.basic.model.SystemContext;
import org.wxh.index.model.CmsLink;
import org.wxh.index.model.IndexPic;
import org.wxh.index.model.IndexTopic;
import org.wxh.index.model.IndexVideo;
import org.wxh.index.service.ICmsLinkService;
import org.wxh.index.service.IIndexPicService;
import org.wxh.index.service.IIndexService;
import org.wxh.sys.model.BaseInfo;
import org.wxh.topic.model.Attachment;
import org.wxh.topic.model.Channel;
import org.wxh.topic.model.Topic;
import org.wxh.topic.model.Video;
import org.wxh.topic.service.IAttachmentService;
import org.wxh.topic.service.IChannelService;
import org.wxh.topic.service.IKeywordService;
import org.wxh.topic.service.ITopicService;
import org.wxh.topic.service.IVideoService;
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
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
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
	@Autowired
	private ICmsLinkService cmsLinkService;

	@Override
	public void generateTop() {
		List<Channel> list = channelService.listTopNavChannel( 9 );
		Map<String,Object> root = new HashMap<String,Object>();
		root.put("navs", list);
		root.put(Constant.BaseCode.BASE_INFO, BaseInfoUtil.getInstacne().read());
		String outfile = SystemContext.getRealPath()+outPath+"/top.jsp";
		util.fprint(root, "/top.ftl", outfile);
		logger.info("=============重新生成了顶部信息====================");
	}

	@Override
	public void generateBottom() {
		Map<String,Object> root = new HashMap<String,Object>();
		root.put(Constant.BaseCode.BASE_INFO, BaseInfoUtil.getInstacne().read());
		String outfile = SystemContext.getRealPath()+outPath+"/bottom.jsp";
		util.fprint(root, "/bottom.ftl", outfile);
		logger.info("=============重新生成了底部信息====================");
	}

	@Override
	public void generateBody() {
		Map<String,Object> root = new HashMap<String,Object>();
		Map<String,IndexTopic> topics = new HashMap<String, IndexTopic>();
		
		//TODO 1、获取所有的首页栏目
		//加载indexChannel.properties文件
		Properties prop = PropertiesUtil.getInstance().load("indexChannel");
		Enumeration en = prop.propertyNames();
		while( en.hasMoreElements() ) {
			String key = en.nextElement().toString(); //key
			String[] value = prop.getProperty( key + "" ).split( "_" ); //value
			int cid = Integer.parseInt( value[ 0 ] ); //栏目id
			int num = Integer.parseInt( value[ 1 ] ); //文章数量
			Channel channel = channelService.load( cid );
			IndexTopic it = new IndexTopic();
			it.setCid( cid ); //栏目id
			it.setCname( channel.getName() ); //栏目名称
			List<Topic> tops = topicService.listTopicByChannelAndNumber(cid, num);
			it.setTopics( tops );
			topics.put( key ,it );
		}
		root.put("ts", topics);
		
		//TODO 2、更新宣传图片(没排序)
		BaseInfo bi = BaseInfoUtil.getInstacne().read();
		int picnum = bi.getIndexPicNumber(); 
		List<IndexPic> pics = indexPicService.listIndexPicByNum( picnum );
		root.put("pics", pics);  //硬编码。数量应该可配置
		
		//TODO 3、更新新闻滚动图片(没排序)
		List<Attachment> newsPics = attachmentService.listAttachmentByIndexPic( 5 );
		root.put( "newsPics" ,newsPics );//硬编码。数量应该可配置
		
		//TODO 4、更新视频新闻栏目
		List<Video> v = videoService.listVideoByNum( 60 ,6 );//硬编码。栏目id和数量应该可配置
		Channel channel = channelService.load( 60 );
		IndexVideo vids = new IndexVideo();
		vids.setCid( channel.getId() );
		vids.setCname( channel.getName() );
		vids.setVideos( v );
		root.put( "vids" ,vids );
		
		//TODO 5、生成静态body页面
		String outfile = SystemContext.getRealPath() + outPath + "/body.jsp";
		util.fprint( root ,"/body.ftl" ,outfile );
		logger.info( "=============重新生成了body信息====================" );
		
	}

	/**
	 * 更新友情链接
	 */
	@Override
	public void generateLink() {
		List<CmsLink> list = cmsLinkService.listAllLink();
		Map<String,Object> root = new HashMap<String,Object>();
		root.put("pics", list);
		String outfile = SystemContext.getRealPath()+outPath+"/link.jsp";
		util.fprint(root, "/link.ftl", outfile);
		logger.info("=============重新生成了友情链接信息====================");
	}

}
