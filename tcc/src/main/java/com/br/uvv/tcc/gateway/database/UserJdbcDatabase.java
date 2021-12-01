package com.br.uvv.tcc.gateway.database;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.br.uvv.tcc.entities.User;
import com.br.uvv.tcc.entities.enums.Role;
import com.br.uvv.tcc.gateway.exceptions.ErrorToAccessDatabase;

@Component
public class UserJdbcDatabase implements UserDatabase {

	private JdbcTemplate jdbcTemplate;

	@Override
	public void create(User user) {
		
		try {
			
			final Integer roleCode = user.getRole() == Role.SALESMAN ? 0 : 1;
			
			jdbcTemplate.update("INSERT INTO user (id, role, name) VALUES (?, ?, ?)", user.getId(), roleCode, user.getName());
			
		} catch (Exception e) {
			
			throw new ErrorToAccessDatabase();
			
		}
		
	}

}
