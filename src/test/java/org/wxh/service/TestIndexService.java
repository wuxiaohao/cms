package org.wxh.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wxh.basic.model.SystemContext;
import org.wxh.index.service.IIndexService;
import org.wxh.topic.model.Channel;
import org.wxh.topic.model.ChannelType;
import org.wxh.topic.service.IChannelService;
import org.wxh.topic.service.ITopicService;
import org.wxh.topic.service.impl.ChannelService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans.xml")
public class TestIndexService {
	@Autowired
	private IIndexService indexService;
	@Autowired
	private IChannelService channelService;
	@Autowired
	private ITopicService topicService;
	private String rp = "G:\\javawebCode\\springMVC\\cms\\src\\main\\webapp";
	
	@Test
	public void testGenerateTop() {
		SystemContext.setRealPath(rp);
		indexService.generateTop();
	}
	@Test
	public void testIndexChannel(){
		List<Channel> cs = channelService.listAllIndexChannel(ChannelType.TOPIC_LIST);
		for(Channel c : cs){
			System.out.println(c.getId() +","+c.getName());
		}
	}
	
	@Test
	public void testGenerateBody() {
		SystemContext.setRealPath(rp);
		indexService.generateBody();
	}
	@Test
	public void testLoadLastedTopicByColumn(){
		System.out.println(topicService.loadLastedTopicByColumn(38).getSummary());
	}
}
