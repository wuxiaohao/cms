package org.wxh.topic.model.dto;

import java.util.Date;

import org.apache.commons.io.FilenameUtils;
import org.wxh.topic.model.Attachment;

public class AttachmentDto {
	private int id;
	/**
	 * 附件上传之后的名称
	 */
	private String newName;
	/**
	 * 附件的原始名称
	 */
	private String oldName;
	/**
	 * 附件的类型，这个类型和contentType类型一致
	 */
	private String type;
	/**
	 * 附件的后缀名
	 */
	private String suffix;
	/**
	 * 附件的大小
	 */
	private long size;
	/**
	 * 该附件是否是首页滚动新闻图片
	 */
	private int isIndexPic;
	/**
	 * 该附件是否是图片类型,0表示不是，1表示是
	 */
	private int isImg;
	/**
	 * 是否是附件信息，0表示不是，1表示是，如果是附件信息就在文章的附件栏进行显示
	 */
	private int isAttach;
	/**
	 * 图片宽度
	 */
	private int width;
	/**
	 * 图片高度
	 */
	private int height;
	
	public AttachmentDto() {}
	
	public AttachmentDto(Attachment att,int width,int height) {
		this.id = att.getId();
		this.isAttach = att.getIsAttach();
		this.isIndexPic = att.getIsIndexPic();
		this.newName = att.getNewName();
		this.oldName = att.getOldName();
		this.suffix = att.getSuffix();
		this.type = att.getType();
		this.size = att.getSize();
		this.isImg = att.getIsImg();
		this.width = width;
		this.height = height;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNewName() {
		return newName;
	}
	public void setNewName(String newName) {
		this.newName = newName;
	}
	public String getOldName() {
		return oldName;
	}
	public void setOldName(String oldName) {
		this.oldName = oldName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public int getIsIndexPic() {
		return isIndexPic;
	}
	public void setIsIndexPic(int isIndexPic) {
		this.isIndexPic = isIndexPic;
	}
	public int getIsImg() {
		return isImg;
	}
	public void setIsImg(int isImg) {
		this.isImg = isImg;
	}
	public int getIsAttach() {
		return isAttach;
	}
	public void setIsAttach(int isAttach) {
		this.isAttach = isAttach;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	@Override
	public String toString() {
		return "AttachmentDto [id=" + id + ", newName=" + newName
				+ ", oldName=" + oldName + ", type=" + type + ", suffix="
				+ suffix + ", size=" + size + ", isIndexPic=" + isIndexPic
				+ ", isImg=" + isImg + ", isAttach=" + isAttach + ", width="
				+ width + ", height=" + height + "]";
	}
	
	
	
}
