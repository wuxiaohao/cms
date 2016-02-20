package org.wxh.service;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wxh.basic.model.Pager;
import org.wxh.basic.model.SystemContext;
import org.wxh.topic.model.dto.PictureDto;
import org.wxh.topic.service.IPictureTopicService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class Test {
	
	@Autowired
	private IPictureTopicService pictureTopicService;
	
	@org.junit.Test
	public void test() {
		SystemContext.setPageSize( 3 );
		Pager<PictureDto> p = pictureTopicService.findPicTopByCid(59);
		System.out.println(p.getTotal());
	}
	

}
