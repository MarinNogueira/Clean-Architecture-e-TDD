package com.br.uvv.tcc.gateway.database;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import com.br.uvv.tcc.databuilder.ProductDatabuilder;
import com.br.uvv.tcc.entities.Product;

@ExtendWith(MockitoExtension.class)
class ProductJdbcDatabaseUnitTest {

	@InjectMocks
	private ProductJdbcDatabase productJdbcDatabase;
	
	@Mock
	private JdbcTemplate jdbcTemplate;
	
	@Test
	void createWithSuccess() {
		
		final Product product = ProductDatabuilder.createProduct();
		
		this.productJdbcDatabase.create(product);

		verify(jdbcTemplate).execute("INSERT INTO product (id, name, description, quantity) VALUES (" 
		+ product.getId() + ", " + product.getName() + ", " + product.getDescription() + ", " + product.getQuantity() + ");");
		
	}
	
}
