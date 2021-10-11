package com.br.uvv.tcc.gateway.databuilder;

import com.br.uvv.tcc.entities.Product;

public class ProductDatabuilder {
	public static Product createProduct() {
		final Product product = new Product();
		
		product.setId(1L);
		product.setDescription("anyDescription");
		product.setName("anyName");
		product.setQuantity(2);
		
		return product;
	}
}
