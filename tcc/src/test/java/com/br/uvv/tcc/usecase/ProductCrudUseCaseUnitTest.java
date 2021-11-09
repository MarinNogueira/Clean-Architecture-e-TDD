package com.br.uvv.tcc.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

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
import com.br.uvv.tcc.gateway.database.ProductDatabase;

@ExtendWith(MockitoExtension.class)
class ProductCrudUseCaseUnitTest {
	
	@InjectMocks
	private ProductCrudUseCase productCrud;
	
	@Mock
	private ProductDatabase productDatabase;
	
	@Mock
	private VerifyCredentialsUseCase verifyCredentialsUseCase;
	
	@Test
	void createWithSuccess() {
		final Product product = ProductDatabuilder.createProduct();
		final Role role = Role.MANAGER;
	
		final ArgumentCaptor<Product> productAC = ArgumentCaptor.forClass(Product.class);
		
		this.productCrud.create(product, role);
		
		verify(verifyCredentialsUseCase).verifyManager(role);
		verify(productDatabase).create(productAC.capture());
		final Product productCap = productAC.getValue();
		
		assertProduct(product, productCap);
		
	}
	
	@Test
	void sellWithSuccess() {
		final Long id = 1L;
		final Integer quantitySold = 2;
		
		this.productCrud.sell(id, quantitySold);
		
		verify(productDatabase).sell(id, quantitySold);
		
	}
	
	@Test
	void getAllWithSuccess() {
		final List<Product> productList = Arrays.asList(ProductDatabuilder.createProduct(), ProductDatabuilder.createProduct());

		doReturn(productList).when(this.productDatabase).getAll();
		
		final List<Product> productListReturned = this.productCrud.getAll();
		
		assertProduct(productList.get(0), productListReturned.get(0));
		assertProduct(productList.get(1), productListReturned.get(1));
		
	}
	
	@Test
	void deleteWithSuccess() {
		final Long id = 1L;
		final Role role = Role.MANAGER;
		
		this.productCrud.delete(id, role);
		
		verify(verifyCredentialsUseCase).verifyManager(role);
		verify(productDatabase).delete(id);
	}
	
	@Test
	void updateWithSuccess() {
		final Product product = ProductDatabuilder.createProduct();
		final Role role = Role.MANAGER;
	
		final ArgumentCaptor<Product> productAC = ArgumentCaptor.forClass(Product.class);
		
		this.productCrud.update(product, role);
		
		verify(verifyCredentialsUseCase).verifyManager(role);
		verify(productDatabase).update(productAC.capture());
		final Product productCap = productAC.getValue();
		
		assertProduct(product, productCap);
		
	}
	
	@Test
	void getByIdWithSuccess() {
		
		final Long id = 1L;
		
		final Product product = ProductDatabuilder.createProduct();
		
		doReturn(product).when(this.productDatabase).get(id);
		
		final Product productReturned = this.productCrud.get(id);
		
		assertProduct(product, productReturned);
		
	}
	
	@Test
	void getByNameWithSuccess() {
		
		final String name = "anyName";
		
		final Product product = ProductDatabuilder.createProduct();
		
		doReturn(product).when(this.productDatabase).get(name);
		
		final Product productReturned = this.productCrud.get(name);
		
		assertProduct(product, productReturned);
		
	}
	
	private void assertProduct(final Product expected, final Product actual) {
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getDescription(), actual.getDescription());
		assertEquals(expected.getName(), actual.getName());
		assertEquals(expected.getQuantity(), actual.getQuantity());
	}
	
}
