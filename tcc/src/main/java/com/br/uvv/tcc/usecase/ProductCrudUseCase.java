package com.br.uvv.tcc.usecase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.uvv.tcc.entities.Product;
import com.br.uvv.tcc.entities.enums.Role;
import com.br.uvv.tcc.gateway.database.ProductDatabase;

@Service
public class ProductCrudUseCase {

	@Autowired
	private ProductDatabase productDatabase;

	@Autowired
	private VerifyCredentialsUseCase verifyCredentialsUseCase;

	public void create(final Product product, final Role role) {
		this.verifyCredentialsUseCase.verifyManager(role);
		this.productDatabase.create(product);
	}
	
	public void sell(final Long id, final Integer quantitySold) {
		this.productDatabase.sell(id, quantitySold);
	}
	
	public List<Product> getAll() {
		return this.productDatabase.getAll();
	}
	
	public void delete(final Long id, final Role role) {
		this.verifyCredentialsUseCase.verifyManager(role);
		this.productDatabase.delete(id);
	}
	
	public void update(final Product product, final Role role) {
		this.verifyCredentialsUseCase.verifyManager(role);
		this.productDatabase.update(product);
	}
	
}
