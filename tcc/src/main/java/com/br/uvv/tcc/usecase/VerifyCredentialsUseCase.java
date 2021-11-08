package com.br.uvv.tcc.usecase;

import org.springframework.stereotype.Service;

import com.br.uvv.tcc.entities.enums.Role;
import com.br.uvv.tcc.usecase.exceptions.IncorrectCredentialsBusinessException;

@Service
public class VerifyCredentialsUseCase {

	public void verifyManager(final Role role) {
		
		if(!role.equals(Role.MANAGER))
			throw new IncorrectCredentialsBusinessException();
	}
	
}
