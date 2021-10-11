package com.br.uvv.tcc.gateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.br.uvv.tcc.entities.User;
import com.br.uvv.tcc.usecase.UserCrudUseCase;

@CrossOrigin(origins = "*")//NOSONAR
@RestController
@RequestMapping("${baseurl.v1}/users")
public class UserController {

	private UserCrudUseCase userCrudUseCase;
	
	@Validated
	@ResponseStatus(HttpStatus.OK)
	@PostMapping
	public void create(final User user) {
		this.userCrudUseCase.create(user);
	}
}
