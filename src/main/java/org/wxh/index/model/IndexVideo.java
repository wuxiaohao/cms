package org.wxh.index.model;

import java.util.List;

import org.wxh.topic.model.Topic;
import org.wxh.topic.model.Video;


public class IndexVideo {
	private int cid;
	private String cname;
	private List<Video> videos;
	
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public List<Video> getVideos() {
		return videos;
	}
	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}
	
	@Override
	public String toString() {
		return "IndexVideo [cid=" + cid + ", cname=" + cname + ", videos="
				+ videos + "]";
	}
	
	
}
