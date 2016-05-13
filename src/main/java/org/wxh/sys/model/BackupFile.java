package org.wxh.sys.model;


import java.util.Date;

/**
 * 备份文件的实体对象
 * @author wxh
 *
 */
public class BackupFile implements Comparable<BackupFile>{
	/**
	 * 备份的文件名称
	 */
	private String name;
	/**
	 * 备份的文件时间
	 */
	private Date time;
	/**
	 * 备份的文件的大小
	 */
	private String size;
	/**
	 * 备份的文件类型
	 */
	private String filetype;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getFiletype() {
		return filetype;
	}
	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}
	
	//返回时间的倒序
	public int compareTo(BackupFile o) {
		return o.getTime().compareTo(this.getTime());
	}
	@Override
	public String toString() {
		return "BackupFile [name=" + name + ", time=" + time + ", size=" + size
				+ ", filetype=" + filetype + "]";
	}
}
