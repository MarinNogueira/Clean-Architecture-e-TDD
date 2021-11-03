package com.br.uvv.tcc.gateway.database;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import com.br.uvv.tcc.databuilder.UserDatabuilder;
import com.br.uvv.tcc.entities.User;

@ExtendWith(MockitoExtension.class)
class UserJdbcDatabaseUnitTest {

	@InjectMocks
	private UserJdbcDatabase userJdbcDatabase;
	
	@Mock
	private JdbcTemplate jdbcTemplate;
	
	@Test
	void createWithSuccess() {
		
		final User user = UserDatabuilder.createUser();
		
		this.userJdbcDatabase.create(user);
		
		verify(jdbcTemplate).execute("INSERT INTO user (id, role, name) VALUES (" 
		+ user.getId() + ", " + user.getRole() + ", " + user.getName() + ")");
		
	}
	
}
