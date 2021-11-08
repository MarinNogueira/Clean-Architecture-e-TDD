package com.br.uvv.tcc.gateway.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.br.uvv.tcc.databuilder.ProductDatabuilder;
import com.br.uvv.tcc.entities.Product;
import com.br.uvv.tcc.entities.enums.Role;
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
		final Role role = Role.MANAGER;
	
		final ArgumentCaptor<Product> productAC = ArgumentCaptor.forClass(Product.class);
		
		this.productController.create(product, role);
		
		verify(productCrudUseCase).create(productAC.capture(), eq(role));
		final Product productCap = productAC.getValue();
		
		assertProduct(product, productCap);
		
	}

	@Test
	void sellWithSuccess() {
		final Long id = 1L;
		final Integer quantitySold = 2;
		
		this.productController.sell(id, quantitySold);
		
		verify(productCrudUseCase).sell(id, quantitySold);
		
	}
	
	@Test
	void getAllWithSuccess() {
	
		final List<Product> productList = Arrays.asList(ProductDatabuilder.createProduct(), ProductDatabuilder.createProduct());

		doReturn(productList).when(this.productCrudUseCase).getAll();
		
		final List<Product> productListReturned = this.productController.getAll();
		
		assertProduct(productList.get(0), productListReturned.get(0));
		assertProduct(productList.get(1), productListReturned.get(1));
		
	}
	
	@Test
	void deleteWithSuccess() {
		
		final Long id = 1L;
		final Role role = Role.MANAGER;
		
		this.productController.delete(id, role);
		
		verify(productCrudUseCase).delete(id, role);
		
	}
	
	@Test
	void updateWithSuccess() {
		
		final Product product = ProductDatabuilder.createProduct();
		final Role role = Role.MANAGER;
		
		this.productController.update(product, role);
		
		final ArgumentCaptor<Product> productAC = ArgumentCaptor.forClass(Product.class);
		
		verify(productCrudUseCase).update(productAC.capture(), eq(role));
		final Product productCap = productAC.getValue();
		
		assertProduct(product, productCap);
		
	}
	
	private void assertProduct(final Product expected, final Product actual) {
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getDescription(), actual.getDescription());
		assertEquals(expected.getName(), actual.getName());
		assertEquals(expected.getQuantity(), actual.getQuantity());
	}
}
