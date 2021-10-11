package com.br.uvv.tcc.gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.br.uvv.tcc.entities.Product;
import com.br.uvv.tcc.usecase.ProductCrudUseCase;

@CrossOrigin(origins = "*")//NOSONAR
@RestController
@RequestMapping("${baseurl.v1}/products")
public class ProductController {
	
	@Autowired
	private ProductCrudUseCase productCrudUseCase;
	
	@Validated
	@ResponseStatus(HttpStatus.OK)
	@PostMapping
	public void create(final Product product) {
		this.productCrudUseCase.create(product);
	}

}
