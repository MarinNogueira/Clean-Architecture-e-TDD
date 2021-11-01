package com.br.uvv.tcc.gateway.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.br.uvv.tcc.databuilder.UserDatabuilder;
import com.br.uvv.tcc.entities.User;
import com.br.uvv.tcc.usecase.UserCrudUseCase;

@ExtendWith(MockitoExtension.class)
class UserControllerUnitTest {
	
	@InjectMocks
	private UserController userController;
	
	@Mock
	private UserCrudUseCase userCrudUseCase;
	
	@Test
	void createWithSuccess() {
		final User user = UserDatabuilder.createUser();
		
		final ArgumentCaptor<User> userAC = ArgumentCaptor.forClass(User.class);
		
		this.userController.create(user);
		
		verify(userCrudUseCase).create(userAC.capture());
		final User userCap = userAC.getValue();
		
		assertEquals(user.getId(), userCap.getId());
		assertEquals(user.getName(), userCap.getName());
		assertEquals(user.getRole(), userCap.getRole());
	}
	
}
