package org.wxh.user.model;

/**
 * 角色类型
 * @author wxh
 *
 */
public enum RoleType{
	ROLE_ADMIN("管理员"),ROLE_PUBLISH("文章发布员"),ROLE_AUDIT("文章审核员");
	
	private String name;

	private RoleType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
