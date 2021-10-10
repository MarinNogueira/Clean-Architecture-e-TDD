package com.br.uvv.tcc.entities;

import com.br.uvv.tcc.entities.enums.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

	private Long id;
	private Role role;
	private String name;
	
}
