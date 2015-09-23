package org.wxh.service;



import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.wxh.user.model.Role;
import org.wxh.user.model.RoleType;
import org.wxh.user.service.IRoleService;
import org.wxh.user.service.IUserService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class TestUserService {

	@Autowired
	private IRoleService roleService;
	
	public IRoleService getRoleService() {
		return roleService;
	}
	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}

	@Test
	public void getUser() {
		roleService.add(new Role(1,"12",RoleType.ROLE_ADMIN));	
	}

}
