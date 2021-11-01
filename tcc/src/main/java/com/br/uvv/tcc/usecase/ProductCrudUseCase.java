package com.br.uvv.tcc.usecase;

import org.springframework.beans.factory.annotation.Autowired;

import com.br.uvv.tcc.entities.Product;
import com.br.uvv.tcc.gateway.database.ProductDatabase;

public class ProductCrudUseCase {

	@Autowired
	private ProductDatabase productDatabase;
	
	public void create(final Product product) {
		this.productDatabase.create(product);
	}
	
}
