package com.br.uvv.tcc.gateway.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.br.uvv.tcc.entities.Product;
import com.br.uvv.tcc.entities.enums.Role;
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
	public void create(final Product product, final Role role) {
		this.productCrudUseCase.create(product, role);
	}

	@Validated
	@ResponseStatus(HttpStatus.OK)
	@PutMapping
	public void sell(final Long id, final Integer quantitySold) {
		this.productCrudUseCase.sell(id, quantitySold);
	}
	
	@Validated
	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public List<Product> getAll() {
		return this.productCrudUseCase.getAll();
	}
	
	@Validated
	@ResponseStatus(HttpStatus.OK)
	@PostMapping
	public void delete(final Long id, final Role role) {
		this.productCrudUseCase.delete(id, role);
	}

	@Validated
	@ResponseStatus(HttpStatus.OK)
	@PutMapping
	public void update(final Product product, final Role role) {
		this.productCrudUseCase.update(product, role);
	}
}
