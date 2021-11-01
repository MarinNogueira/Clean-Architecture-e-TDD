package com.br.uvv.tcc.databuilder;

import com.br.uvv.tcc.entities.User;
import com.br.uvv.tcc.entities.enums.Role;

public class UserDatabuilder {

	public static User createUser() {
		final User user = new User();
		
		user.setId(1L);
		user.setName("anyName");
		user.setRole(Role.SALESMAN);
		
		return user;
	}
	
	public static User createManagerUser() {
		final User user = new User();
		
		user.setId(1L);
		user.setName("anyName");
		user.setRole(Role.MANAGER);
		
		return user;
	}
	
}
