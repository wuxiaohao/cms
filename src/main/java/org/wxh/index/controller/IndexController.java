package org.wxh.index.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.hamcrest.core.Is;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wxh.basic.model.Pager;
import org.wxh.basic.model.SystemContext;
import org.wxh.index.service.IIndexService;
import org.wxh.index.service.impl.IndexService;
import org.wxh.topic.model.Attachment;
import org.wxh.topic.model.Channel;
import org.wxh.topic.model.ChannelType;
import org.wxh.topic.model.Keyword;
import org.wxh.topic.model.Picture;
import org.wxh.topic.model.PictureTopic;
import org.wxh.topic.model.Topic;
import org.wxh.topic.model.Video;
import org.wxh.topic.model.dto.PictureDto;
import org.wxh.topic.service.IAttachmentService;
import org.wxh.topic.service.IChannelService;
import org.wxh.topic.service.IKeywordService;
import org.wxh.topic.service.IPictureService;
import org.wxh.topic.service.IPictureTopicService;
import org.wxh.topic.service.ITopicService;
import org.wxh.topic.service.IVideoService;
import org.wxh.util.BaseInfoUtil;

/**
 * 网站的Controller
 * @author wxh
 *
 */
@Controller
public class IndexController {
	
	private static final Logger logger = Logger.getLogger(IndexService.class);
	
	@Autowired
	private IChannelService channelService;
	@Autowired
	private ITopicService topicService;
	@Autowired
	private IAttachmentService attachmentService;
	@Autowired
	private IKeywordService keywordService;
	@Autowired
	private IIndexService indexService;
	@Autowired
	private IPictureTopicService pictureTopicService;
	@Autowired
	private IPictureService pictureService;
	@Autowired
	private IVideoService videoService;
	
	/**
	 * 访问网站首页的方法
	 * @param model
	 * @return
	 */
	@RequestMapping({"/","/index"})
	public String index(Model model) {
		model.addAttribute("baseInfo", BaseInfoUtil.getInstacne().read());
		return "index/index";
	}
	/**
	 * 显示栏目信息
	 * @param cid
	 * @param model
	 * @param resp
	 * @param req
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/channel/{cid}")
	public String showChannel(@PathVariable int cid,Model model,HttpServletResponse resp,HttpServletRequest req) throws IOException {
		//1、获取当前栏目及父栏目
		String result = "";
		Channel c = channelService.load( cid );
		List<Channel> navs = getNavChannel( c );
		model.addAttribute("navs",navs); //传递：导航栏目
		Channel pc = null;
		//如果是导航栏目
		if( c.getType() == ChannelType.NAV_CHANNEL ) {  
			pc = c; //自己就是父栏目
			//如果是导航栏目，需要获取该栏目中的第一个子栏目
			c = channelService.loadFirstChannelByNav( c.getId() );
		} else {
			pc = c.getParent();  //获取父栏目
		} 
		//2、获取当前栏目下的父栏目列表
		List<Channel> cs = null;
		String cname = "";
		if( pc == null ) {
			cs = channelService.listUseChannelByParent( null );
			cname = "网站栏目";
		} else {
			cs = channelService.listUseChannelByParent( pc.getId() );
			cname = pc.getName();
		}
		model.addAttribute( "cs", cs );
		model.addAttribute( "cname", cname ); //传递：父栏目的名称
		model.addAttribute( "channel", c ); //传递：当前栏目
		//3、获取关键字
		List<Keyword> kws = keywordService.getMaxTimesKeyword( 18 );
		model.addAttribute( "kws", kws );
		//4、获取新闻列表
		if ( c.getType() == ChannelType.TOPIC_CONTENT ) {  //如果是文章内容栏目,直接跳转显示文章内容
			Topic topic = topicService.loadLastedTopicByColumn( cid );
			resp.sendRedirect( req.getContextPath() + "/topic/" + topic.getId() );
		} else if ( c.getType() == ChannelType.IMG_NEW ) { //如果是组图新闻列表
			SystemContext.setPageSize( 8 );
			Pager<PictureDto> pics = pictureTopicService.findPicTopByCid(cid);//获取组图新闻的封面列表
			model.addAttribute("datas", pics);
			result = "index/article_pic";
		} else if ( c.getType() == ChannelType.VIDEO_NEW ) { //如果是视频新闻列表
			SystemContext.setPageSize( 8 );
			SystemContext.setSort( "v.publishDate" );
			SystemContext.setOrder( "desc" );
			Pager<Video> vids = videoService.findVideoByCid( cid );
			model.addAttribute("datas", vids);
			result = "index/article_video";
		} else if ( c.getType() == ChannelType.TOPIC_LIST ) { //如果是文章列表栏目
			SystemContext.setPageSize( 10 );
			SystemContext.setSort( "t.publishDate" );
			SystemContext.setOrder( "desc" );
			model.addAttribute( "datas" , topicService.find( c.getId(),null,1 ) );
			result = "index/article_list";
		}
		SystemContext.removeSort();
		SystemContext.removeOrder();
		SystemContext.removePageSize();
		
		return result;
		
	}
	/**
	 * 显示文章内容
	 * @param tid
	 * @param model
	 * @return
	 */
	@RequestMapping("/topic/{tid}")
	public String showTopic(@PathVariable int tid,Model model) {
		Topic t = topicService.load(tid);
		t.setViewCount(t.getViewCount() + 1);
		topicService.update(t);
		String keywords = t.getKeyword();
		model.addAttribute("topic", t);
		//获取导航栏目
		List<Channel> navs = getNavChannel( t.getChannel() );
		model.addAttribute("navs", navs);
		if(keywords==null||"".equals(keywords.trim())||"\\|".equals(keywords.trim())) {
			model.addAttribute("hasKey", false);
		} else {
			String[] kws = keywords.split("\\|");
			model.addAttribute("hasKey", true);
			model.addAttribute("kws",kws);
		}
		List<Attachment> atts = attachmentService.listAttachByTopic(tid);
		if(atts.size()>0) {
			model.addAttribute("hasAtts", true);
			model.addAttribute("atts", atts);
		} else {
			model.addAttribute("hasAtts",false);
		}
		//获取关键字
		model.addAttribute( "kws", keywordService.getMaxTimesKeyword( 18 ) );
		//热门文章
		List<Topic> tops = topicService.listTopic();
		return "index/topic";
	}
	/**
	 * 显示组图新闻
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/imgsNews/{id}")
	public String showImgsNews(@PathVariable int id,Model model) {
		PictureTopic top = pictureTopicService.load( id );
		model.addAttribute("top", top);
		//获取导航栏目
		List<Channel> navs = getNavChannel( top.getChannel() );
		model.addAttribute("navs", navs);
		//获取组图
		List<Picture> imgs = pictureService.listByPicTopic( id );
		model.addAttribute("imgs", imgs);
		return "index/imgs_news";
	}
	/**
	 * 显示视频新闻
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/videoNews/{id}")
	public String showVideo(@PathVariable int id,Model model) {
		//获取视频信息
		Video video = videoService.loadCash( id );
		//获取导航栏目
		List<Channel> navs = getNavChannel( video.getChannel() );
		model.addAttribute("navs", navs);
		//获取视频列表
		List<Video> vids = videoService.listVideoByNum(video.getChannel().getId(), 8);
		//获取总播放量
		//获取总视频数量
		return "index/video_news";
	}
	/**
	 * 首页全文搜索
	 * @param con
	 * @param model
	 * @return
	 */
	@RequestMapping("/search/{con}")
	public String search(@PathVariable String con,Model model) {
		SystemContext.setOrder("asc");
		SystemContext.setSort("c.orders");
		model.addAttribute("cs", channelService.listChannelByType(ChannelType.NAV_CHANNEL));
		SystemContext.setOrder("desc");
		SystemContext.setSort("t.publishDate");
		Pager<Topic> topics = topicService.searchTopic(con);
		emp(topics,con);
		model.addAttribute("datas", topics);
		model.addAttribute("con", con);
		return "index/search";
	}
	/**
	 * 关键字检索
	 * @param con
	 * @param model
	 * @return
	 */
	@RequestMapping("/keyword/{con}")
	public String keyword(@PathVariable String con,Model model) {
		model.addAttribute("kws", keywordService.getMaxTimesKeyword( 18 ));
		SystemContext.setOrder("desc");
		SystemContext.setSort("t.publishDate");
		Pager<Topic> topics = topicService.searchTopicByKeyword( con );
		emp( topics,con );
		model.addAttribute("datas", topics);
		model.addAttribute("con", con);
		return "index/keyword";
	}

	private void emp(Pager<Topic> topics, String con) {
		for(Topic t:topics.getDatas()) {
			if(t.getTitle().contains(con)) {
				String tt = t.getTitle().replaceAll(con, "<span class='emp'>"+con+"</span>");
				t.setTitle(tt);
			}
		}
	}
	
	/**
	 * 获取导航栏目
	 * @param cid
	 * @return
	 */
	private List<Channel> getNavChannel( Channel channel ) {
		List<Channel> list = new ArrayList<Channel>();
		list.add( channel );
		Channel c = channel.getParent();
		while( c != null ) {
			list.add( c );
			c = c.getParent();
		}
		Collections.reverse( list );
		return list;
	}
	
	//-----------------------------------------------------首页生成----------------------------------------------------------
	/**
	 * 首页生成
	 * @return
	 */
	@RequestMapping("/generateAll")
	public String generateAll() {
		indexService.generateTop();
		indexService.generateBottom();
		indexService.generateBody();
		indexService.generateLink();
		logger.info("-------------------------------首页静态页面已生成----------------------------------------");
		return null;
	}
	/**
	 * 首页顶部
	 * @return
	 */
	@RequestMapping("/generateTop")
	public String generateTop() {
		indexService.generateTop();
		logger.info("-------------------------------首页顶部已生成----------------------------------------");
		return null;
	}
	/**
	 * 首页底部
	 * @return
	 */
	@RequestMapping("/generateBottom")
	public String generateBottom() {
		indexService.generateBottom();
		logger.info("-------------------------------首页底部已生成----------------------------------------");
		return null;
	}
	/**
	 * 首页body
	 * @return
	 */
	@RequestMapping("/generateBody")
	public String generateBody() {
		indexService.generateBody();
		logger.info("-------------------------------首页body已生成----------------------------------------");
		return null;
	}
	/**
	 * 首页友情链接
	 * @return
	 */
	@RequestMapping("/generateLink")
	public String generateLink() {
		indexService.generateLink();
		logger.info("-------------------------------首页友情链接已生成----------------------------------------");
		return null;
	}
	
}
