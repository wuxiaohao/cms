package org.wxh.topic.model;

/**
 * 栏目类型的枚举
 * @author wxh
 *
 */

public enum ChannelType {
	NAV_CHANNEL("导航栏目"),TOPIC_LIST("文章列表栏目"),
	TOPIC_CONTENT("文章内容栏目"),TOPIC_IMG("图片列表栏目"),IMG_NEW("组图新闻"),VIDEO_NEW("视频新闻");
	
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
