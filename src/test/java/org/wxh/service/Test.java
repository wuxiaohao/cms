package org.wxh.service;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

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
		SystemContext.setPageSize(3);
		Pager<PictureDto> p = pictureTopicService.findPicTopByCid(59);
		System.out.println(p.getTotal());
	}

	@org.junit.Test
	public void test02() {
		List list = new LinkedList();
		for (int i = 0; i < 9; i++) {
			list.add("a" + i);
		}
		Collections.reverse(list);// 倒序排列
		for (int i = 0; i < 9; i++) {
			System.out.println(list.get(i));
		}
	}

}
