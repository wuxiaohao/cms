package org.wxh;

public class TestEnum {

	public static void main(String[] args) {
		System.out.println(RoleType.ROLE_ADMIN);
	}
	
}

enum RoleType{
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

