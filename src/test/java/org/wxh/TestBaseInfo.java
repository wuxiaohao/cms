package org.wxh;

import org.junit.Test;
import org.wxh.util.BaseInfoUtil;
import org.wxh.sys.model.BaseInfo;

public class TestBaseInfo {

	@Test
	public void testRead(){
		BaseInfo bi = BaseInfoUtil.getInstacne().read();
		System.out.println(bi);
	
	}
	
	@Test
	public void testWrite(){
		BaseInfo bi = BaseInfoUtil.getInstacne().read();
		bi.setIndexPicWidth(600);
		bi.setIndexPicHeight(1000);
	}
	
}
