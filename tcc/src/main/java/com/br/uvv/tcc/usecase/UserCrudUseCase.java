package com.br.uvv.tcc.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.uvv.tcc.entities.User;
import com.br.uvv.tcc.gateway.database.UserDatabase;

@Service
public class UserCrudUseCase {

	@Autowired
	private UserDatabase userDatabase;
	
	public void create(final User user) {
		this.userDatabase.create(user);
	}
	
}
