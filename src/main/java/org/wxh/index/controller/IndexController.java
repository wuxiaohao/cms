package org.wxh.index.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.wxh.basic.common.Constant;
import org.wxh.basic.common.IndexConstant;
import org.wxh.basic.model.Pager;
import org.wxh.basic.model.SystemContext;
import org.wxh.index.service.IIndexService;
import org.wxh.topic.model.Attachment;
import org.wxh.topic.model.Channel;
import org.wxh.topic.model.ChannelType;
import org.wxh.topic.model.Keyword;
import org.wxh.topic.model.Picture;
import org.wxh.topic.model.PictureTopic;
import org.wxh.topic.model.Topic;
import org.wxh.topic.model.Video;
import org.wxh.topic.model.dto.PictureDto;
import org.wxh.topic.model.dto.TopicDto;
import org.wxh.topic.service.IAttachmentService;
import org.wxh.topic.service.IChannelService;
import org.wxh.topic.service.IKeywordService;
import org.wxh.topic.service.IPictureService;
import org.wxh.topic.service.IPictureTopicService;
import org.wxh.topic.service.ITopicService;
import org.wxh.topic.service.IVideoService;
import org.wxh.util.BaseInfoUtil;

/**
 * 前端新闻展示界面的Controller
 * @author wxh
 *
 */
@Controller
public class IndexController implements IndexConstant{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
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
		model.addAttribute(Constant.BaseCode.BASE_INFO, BaseInfoUtil.getInstacne().read());
		return INDEX;
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
	public ModelAndView showChannel(@PathVariable int cid,HttpServletResponse resp,HttpServletRequest req) throws IOException {
		ModelAndView mv = new ModelAndView();
		//1、获取当前栏目及父栏目
		Channel c = channelService.load( cid );
		List<Channel> navs = getNavChannel( c );
		mv.addObject(NAVS,navs); //传递：导航栏目
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
		mv.addObject( CS, cs );
		mv.addObject( CNAME, cname ); //传递：父栏目的名称
		mv.addObject( CHANNEL, c ); //传递：当前栏目
		//3、获取关键字
		List<Keyword> kws = keywordService.getMaxTimesKeyword( 18 );
		mv.addObject( KWS, kws );
		//4、获取新闻列表
		if ( c.getType() == ChannelType.TOPIC_CONTENT ) {  //如果是文章内容栏目,直接跳转显示文章内容
			Topic topic = topicService.loadLastedTopicByColumn( cid );
			String url = "/topic/" + topic.getId();
			return new ModelAndView("redirect:"+url);  
		} else if ( c.getType() == ChannelType.IMG_NEW ) { //如果是组图新闻列表
			SystemContext.setPageSize( 8 );
			Pager<PictureDto> pics = pictureTopicService.findPicTopByCid(cid);//获取组图新闻的封面列表
			mv.addObject(DATAS, pics);
			mv.setViewName( ARTICLE_PIC );
		} else if ( c.getType() == ChannelType.VIDEO_NEW ) { //如果是视频新闻列表
			SystemContext.setPageSize( 8 );
			SystemContext.setSort( VPUBLISHDATE );
			SystemContext.setOrder( DESC );
			Pager<Video> vids = videoService.findVideoByCid( cid );
			mv.addObject(DATAS, vids);
			mv.setViewName( ARTICLE_VIDEO );
		} else if ( c.getType() == ChannelType.TOPIC_LIST ) { //如果是文章列表栏目
			SystemContext.setSort( TPUBLISHDATE );
			SystemContext.setOrder( DESC );
			Pager<Topic> page = topicService.find( c.getId(),null,1 );
			mv.addObject( DATAS , topicService.find( c.getId(),null,1 ) );
			mv.setViewName( ARTICLE_LIST );
		}	
		return mv;
		
	}
	/**
	 * 显示文章内容
	 * @param tid
	 * @param model
	 * @return
	 */
	@RequestMapping("/topic/{tid}")
	public String showTopic(@PathVariable int tid,Model model) {
		//获取文章并修改浏览次数
		Topic t = topicService.load(tid);
		t.setViewCount(t.getViewCount() + 1);
		topicService.update(t);
		TopicDto dto = new TopicDto( t, t.getChannel().getId() );
		//获取上/下篇文章的id
		topicService.getPreAndNextTopic( dto );
		model.addAttribute(TOPIC, dto);
		//获取导航栏目
		List<Channel> navs = getNavChannel( t.getChannel() );
		model.addAttribute(NAVS, navs);
		//获取文章关键字
		String keywords = t.getKeyword();
		if(keywords==null||"".equals(keywords.trim())||"\\|".equals(keywords.trim())) {
			model.addAttribute(HASKEY, false);
		} else {
			String[] kwst = keywords.split("\\|");
			model.addAttribute(HASKEY, true);
			model.addAttribute(KWST,kwst);
		}
		List<Attachment> atts = attachmentService.listAttachByTopic(tid);
		if(atts.size()>0) {
			model.addAttribute(HASATTS, true);
			model.addAttribute(ATTS, atts);
		} else {
			model.addAttribute(HASATTS,false);
		}
		//获取标签云关键字
		List<Keyword> kws = keywordService.getMaxTimesKeyword( 18 );
		model.addAttribute( KWS, kws );
		//热门文章
		List<Topic> hotTop = topicService.listTopic();
		model.addAttribute( HOTTOP, hotTop );
		return ARTICLE_SHOW;
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
		top.setViewCount( top.getViewCount() +1 );
		pictureTopicService.update( top );
		model.addAttribute(TOP, top);
		//获取导航栏目
		List<Channel> navs = getNavChannel( top.getChannel() );
		model.addAttribute(NAVS, navs);
		//获取组图
		List<Picture> imgs = pictureService.listByPicTopic( id );
		model.addAttribute(IMGS, imgs);
		return ARTICLE_PIC_SHOW;
	}
	/**
	 * 显示视频新闻内容
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/videoNews/{id}")
	public String showVideo(@PathVariable int id,Model model) {
		//获取视频信息并修改浏览次数
		Video video = videoService.loadCash( id );
		video.setViewCount( video.getViewCount() + 1 );
		model.addAttribute(VIDEO, video);
		videoService.update(video);
		//获取导航栏目
		List<Channel> navs = getNavChannel( video.getChannel() );
		model.addAttribute(NAVS, navs);
		//获取视频列表
		/*List<Video> vids = videoService.listVideoByNum(video.getChannel().getId(), 8);
		model.addAttribute("vids", vids);*/
		return ARTICLE_VIDEO_SHOW;
	}
	/**
	 * 首页全文搜索
	 * @param con
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/search/{con}", method = {RequestMethod.GET,RequestMethod.POST})
	public String search(@PathVariable String con, Model model) throws Exception {
		SystemContext.setOrder(ASC);
		SystemContext.setSort(CORDERS);
		model.addAttribute(CS, channelService.listChannelByType(ChannelType.NAV_CHANNEL));
		SystemContext.setOrder(DESC);
		SystemContext.setSort(TPUBLISHDATE);
		Pager<Topic> topics = topicService.searchTopic(con);
		emp(topics,con);
		model.addAttribute(DATAS, topics);
		model.addAttribute(CON, con);
		//获取标签云关键字
		List<Keyword> kws = keywordService.getMaxTimesKeyword( 18 );
		model.addAttribute( KWS, kws );
		return ARTICLE_SEARCH;
	}
	/**
	 * 关键字检索
	 * @param con
	 * @param model
	 * @return
	 */
	@RequestMapping("/keyword/{con}")
	public String keyword(@PathVariable String con,Model model) throws Exception {
		//获取标签云关键字
		List<Keyword> kws = keywordService.getMaxTimesKeyword( 18 );
		model.addAttribute( KWS, kws );
		//获取栏目
		model.addAttribute(CS, channelService.listChannelByType(ChannelType.NAV_CHANNEL));
		SystemContext.setOrder(DESC);
		SystemContext.setSort(TPUBLISHDATE);
		Pager<Topic> topics = topicService.searchTopicByKeyword( con );
		emp( topics,con );
		model.addAttribute(DATAS, topics);
		model.addAttribute(CON, con);
		return ARTICLE_KEYWORD;
	}

	private void emp(Pager<Topic> topics, String con) {
		for(Topic t:topics.getDatas()) {
			if(t.getTitle().contains(con)) {
				String tt = t.getTitle().replaceAll(con, "<span style='color: red'>"+con+"</span>");
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
