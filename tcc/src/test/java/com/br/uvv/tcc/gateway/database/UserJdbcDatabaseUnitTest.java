package com.br.uvv.tcc.gateway.database;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import com.br.uvv.tcc.databuilder.UserDatabuilder;
import com.br.uvv.tcc.entities.User;
import com.br.uvv.tcc.gateway.exceptions.ErrorToAccessDatabase;

@ExtendWith(MockitoExtension.class)
class UserJdbcDatabaseUnitTest {

	@InjectMocks
	private UserJdbcDatabase userJdbcDatabase;
	
	@Mock
	private JdbcTemplate jdbcTemplate;
	
	@Test
	void createSalesmanWithSuccess() {
		
		final User user = UserDatabuilder.createUser();
		
		final int roleCode = 0;

		this.userJdbcDatabase.create(user);
		
		verify(jdbcTemplate).update("INSERT INTO user (id, role, name) VALUES (?, ?, ?)", user.getId(), roleCode, user.getName());
		
	}
	
	@Test
	void createManagerWithSuccess() {
		
		final User user = UserDatabuilder.createManagerUser();
		
		final int roleCode = 1;
		
		this.userJdbcDatabase.create(user);
		
		verify(jdbcTemplate).update("INSERT INTO user (id, role, name) VALUES (?, ?, ?)", user.getId(), roleCode, user.getName());
		
	}
	
	@Test
	void createWithErrorToAccessDatabase() {
		
		final User user = null;

		assertThrows(ErrorToAccessDatabase.class, () -> {
			this.userJdbcDatabase.create(user);
		});
			
	}
	
}
