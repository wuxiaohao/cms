package org.wxh.index.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
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
import org.wxh.topic.model.Topic;
import org.wxh.topic.service.IAttachmentService;
import org.wxh.topic.service.IChannelService;
import org.wxh.topic.service.IKeywordService;
import org.wxh.topic.service.ITopicService;
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
		Channel c = channelService.load(cid);
		Channel pc = null;
		//如果是导航栏目
		if(c.getType()==ChannelType.NAV_CHANNEL) {  
			pc = c; //自己就是父栏目
			//如果是导航栏目，需要获取该栏目中的第一个子栏目
			c = channelService.loadFirstChannelByNav(c.getId());
		} else {
			pc = c.getParent();  //获取父栏目
		}
		model.addAttribute("pc", pc); 
		model.addAttribute("channel", c); 
		if(c.getType()==ChannelType.TOPIC_CONTENT) {  //如果是文章内容栏目,直接跳转显示文章内容
			resp.sendRedirect(req.getContextPath()+"/topic/"+topicService.loadLastedTopicByColumn(cid).getId());
		} else if(c.getType()==ChannelType.TOPIC_IMG){ //如果是图片列表栏目，
			SystemContext.setPageSize(16);
			SystemContext.setSort("a.topic.publishDate");
			SystemContext.setOrder("desc");
			Pager<Attachment> atts = attachmentService.findChannelPic(cid);
			model.addAttribute("datas", atts);
		} else if(c.getType()==ChannelType.TOPIC_LIST) { //如果是文章列表栏目
			SystemContext.setSort("t.publishDate");
			SystemContext.setOrder("desc");
			//System.out.println(c.getType());
			model.addAttribute("datas", topicService.find(c.getId(),null,1));
		}
		SystemContext.removeSort();
		SystemContext.removeOrder();
		model.addAttribute("cs", channelService.listUseChannelByParent(pc.getId()));
		
		if(c.getType()==ChannelType.TOPIC_LIST) {
			return "index/channel";
		} else {
			return "index/channel_pic";
		}
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
		return "index/topic";
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
		model.addAttribute("kws", keywordService.getMaxTimesKeyword(9));
		SystemContext.setOrder("desc");
		SystemContext.setSort("t.publishDate");
		Pager<Topic> topics = topicService.searchTopicByKeyword(con);
		emp(topics,con);
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
