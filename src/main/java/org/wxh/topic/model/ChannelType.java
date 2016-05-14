package org.wxh.topic.model;

/**
 * 栏目类型的枚举
 * @author wxh
 *
 */

public enum ChannelType {
	
	//TOPIC_IMG("图片文章栏目")已弃用
	NAV_CHANNEL("导航栏目"),TOPIC_LIST("新闻列表栏目"),
	TOPIC_CONTENT("新闻内容栏目"),TOPIC_IMG("图片文章栏目"),IMG_NEW("组图新闻栏目"),VIDEO_NEW("视频新闻栏目");
	
	private String name;
	
	private ChannelType(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
