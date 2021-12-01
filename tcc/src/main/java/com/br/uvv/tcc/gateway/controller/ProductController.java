package com.br.uvv.tcc.gateway.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.br.uvv.tcc.entities.Product;
import com.br.uvv.tcc.entities.enums.Role;
import com.br.uvv.tcc.usecase.ProductCrudUseCase;

@CrossOrigin(origins = "*")//NOSONAR
@RestController
@RequestMapping("/tcc/api/v1/service/products")
public class ProductController {
	
	@Autowired
	private ProductCrudUseCase productCrudUseCase;
	
	@Validated
	@ResponseStatus(HttpStatus.OK)
	@PostMapping
	public void create(
			@RequestBody(required = true) final Product product,
			@RequestParam(required = true) final Role role) {
		this.productCrudUseCase.create(product, role);
	}

	@Validated
	@ResponseStatus(HttpStatus.OK)
	@PutMapping("{id}")
	public void sell(
			@PathVariable(required = true) final Long id, 
			@RequestParam(required = true) final Integer quantitySold) {
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
	@DeleteMapping("{id}")
	public void delete(
			@PathVariable(required = true) final Long id, 
			@RequestParam(required = true) final Role role) {
		this.productCrudUseCase.delete(id, role);
	}

	@Validated
	@ResponseStatus(HttpStatus.OK)
	@PutMapping
	public void update(
			@RequestBody(required = true) final Product product, 
			@RequestParam(required = true) final Role role) {
		this.productCrudUseCase.update(product, role);
	}
	
	@Validated
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("{id}")
	public Product get(
			@PathVariable(required = true) final Long id) {
		return this.productCrudUseCase.get(id);
	}
	
	@Validated
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/by-name")
	public Product get(@RequestParam(required = true) final String name) {
		return this.productCrudUseCase.get(name);
	}
}
