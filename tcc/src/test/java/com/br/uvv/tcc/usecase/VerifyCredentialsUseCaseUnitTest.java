package com.br.uvv.tcc.usecase;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.br.uvv.tcc.entities.enums.Role;
import com.br.uvv.tcc.usecase.exceptions.IncorrectCredentialsBusinessException;

@ExtendWith(MockitoExtension.class)
class VerifyCredentialsUseCaseUnitTest {

	@InjectMocks
	private VerifyCredentialsUseCase verifyCredentialsUseCase;
	
	@Test
	void verifyManagerCredentialsWithSuccess() {
		
		final Role role = Role.MANAGER;
		
		this.verifyCredentialsUseCase.verifyManager(role);
		
	}
	
	@Test
	void verifyManagerCredentialsWithIncorrectCredentials() {
		final Role role = Role.SALESMAN;
		
		assertThrows(IncorrectCredentialsBusinessException.class, () -> {
			this.verifyCredentialsUseCase.verifyManager(role);
		});
	}
	
}
