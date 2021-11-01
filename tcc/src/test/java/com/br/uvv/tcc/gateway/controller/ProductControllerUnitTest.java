package com.br.uvv.tcc.gateway.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.br.uvv.tcc.databuilder.ProductDatabuilder;
import com.br.uvv.tcc.entities.Product;
import com.br.uvv.tcc.usecase.ProductCrudUseCase;

@ExtendWith(MockitoExtension.class)
class ProductControllerUnitTest {

	@InjectMocks
	private ProductController productController;
	
	@Mock
	private ProductCrudUseCase productCrudUseCase;
	
	@Test
	void createWithSuccess() {
		final Product product = ProductDatabuilder.createProduct();
	
		final ArgumentCaptor<Product> productAC = ArgumentCaptor.forClass(Product.class);
		
		this.productController.create(product);
		
		verify(productCrudUseCase).create(productAC.capture());
		final Product productCap = productAC.getValue();
		
		assertEquals(product.getId(), productCap.getId());
		assertEquals(product.getDescription(), productCap.getDescription());
		assertEquals(product.getName(), productCap.getName());
		assertEquals(product.getQuantity(), productCap.getQuantity());
		
	}
	
}
