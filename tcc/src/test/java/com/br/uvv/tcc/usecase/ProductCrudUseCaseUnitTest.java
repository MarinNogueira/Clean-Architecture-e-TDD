package com.br.uvv.tcc.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.br.uvv.tcc.databuilder.ProductDatabuilder;
import com.br.uvv.tcc.entities.Product;
import com.br.uvv.tcc.gateway.database.ProductDatabase;
import com.br.uvv.tcc.usecase.ProductCrudUseCase;

@ExtendWith(MockitoExtension.class)
class ProductCrudUseCaseUnitTest {
	
	@InjectMocks
	private ProductCrudUseCase productCrud;
	
	@Mock
	private ProductDatabase productDatabase;
	
	@Test
	void createWithSuccess() {
		final Product product = ProductDatabuilder.createProduct();
	
		final ArgumentCaptor<Product> productAC = ArgumentCaptor.forClass(Product.class);
		
		this.productCrud.create(product);
		
		verify(productDatabase).create(productAC.capture());
		final Product productCap = productAC.getValue();
		
		assertEquals(product.getId(), productCap.getId());
		assertEquals(product.getDescription(), productCap.getDescription());
		assertEquals(product.getName(), productCap.getName());
		assertEquals(product.getQuantity(), productCap.getQuantity());
		
	}
}
