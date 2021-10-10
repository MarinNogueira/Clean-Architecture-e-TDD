package com.br.uvv.tcc.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
	private Long id;
	private String name;
	private String description;
	private Integer quantity;
}
