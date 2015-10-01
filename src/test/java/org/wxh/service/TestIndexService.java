package org.wxh.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wxh.basic.model.SystemContext;
import org.wxh.index.service.IIndexService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans.xml")
public class TestIndexService {
	@Autowired
	private IIndexService indexService;
	private String rp = "G:\\javawebCode\\springMVC\\cms\\src\\main\\webapp";
	
	@Test
	public void testGenerateTop() {
		SystemContext.setRealPath(rp);
		indexService.generateTop();
	}
	
/*	@Test
	public void testGenerateBody() {
		SystemContext.setRealPath(rp);
		indexService.generateBody();
	}*/
}
