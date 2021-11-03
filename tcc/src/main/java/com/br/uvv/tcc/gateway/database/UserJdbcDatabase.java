package com.br.uvv.tcc.gateway.database;

import org.springframework.jdbc.core.JdbcTemplate;

import com.br.uvv.tcc.entities.User;

public class UserJdbcDatabase implements UserDatabase {

	private JdbcTemplate jdbcTemplate;

	@Override
	public void create(User user) {
		
		jdbcTemplate.execute("INSERT INTO user (id, role, name) VALUES (" 
				+ user.getId() + ", " + user.getRole() + ", " + user.getName() + ")");
		
	}

}
