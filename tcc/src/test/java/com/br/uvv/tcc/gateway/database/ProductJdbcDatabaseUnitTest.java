package com.br.uvv.tcc.gateway.database;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.br.uvv.tcc.databuilder.ProductDatabuilder;
import com.br.uvv.tcc.entities.Product;
import com.br.uvv.tcc.gateway.exceptions.ErrorToAccessDatabase;

@ExtendWith(MockitoExtension.class)
class ProductJdbcDatabaseUnitTest {

	@InjectMocks
	private ProductJdbcDatabase productJdbcDatabase;
	
	@Mock
	private JdbcTemplate jdbcTemplate;
	
	@Mock
	private ResultSet resultSet;
	
	@Test
	void createWithSuccess() {
		
		final Product product = ProductDatabuilder.createProduct();
		
		this.productJdbcDatabase.create(product);

		verify(jdbcTemplate).update("INSERT INTO `tcc_db`.`product` (id, name, description, quantity) VALUES (?, ?, ?, ?);",
					product.getId(), product.getName(), product.getDescription(), product.getQuantity());
		
	}
	
	@Test
	void createWithErrorToAccessDatabase() {
		
		final Product product = null;
		
		assertThrows(ErrorToAccessDatabase.class, () -> {
			this.productJdbcDatabase.create(product);
		});
		
	}
	
	@Test
	void sellWithSuccess() {
		final Long id = 1L;
		final Integer quantitySold = 2;
		
		this.productJdbcDatabase.sell(id, quantitySold);
		
		verify(jdbcTemplate).update("UPDATE `tcc_db`.`product` SET Quantity = Quantity - ? WHERE id = ?", quantitySold, id);
		
	}
	
	@Test
	void sellWithErrorToAccessDatabase() {
		
		final Long id = 1L;
		final Integer quantitySold = 2;
		
		doThrow(RuntimeException.class).when(jdbcTemplate).update("UPDATE `tcc_db`.`product` SET Quantity = Quantity - ? WHERE id = ?", quantitySold, id);
		
		assertThrows(ErrorToAccessDatabase.class, () -> {
			this.productJdbcDatabase.sell(id, quantitySold);
		});
		
	}
	
	@Test
	@SuppressWarnings("unchecked")
	void getAllWithSuccess() {
		
		final List<Product> productList = Arrays.asList(ProductDatabuilder.createProduct(), ProductDatabuilder.createProduct());
				
		when(jdbcTemplate.query(eq("SELECT * FROM `tcc_db`.`product`;"), any(RowMapper.class))).thenReturn(productList);
				
		final List<Product> productListReturned = this.productJdbcDatabase.getAll();

		assertProduct(productList.get(0), productListReturned.get(0));
		assertProduct(productList.get(1), productListReturned.get(1));
		
		
	}
	
	@Test	
	@SuppressWarnings("unchecked")
	void getAllWithErrorToAccessDatabase() {
		
		doThrow(RuntimeException.class).when(this.jdbcTemplate).query(eq("SELECT * FROM `tcc_db`.`product`;"), any(RowMapper.class));
		
		assertThrows(ErrorToAccessDatabase.class, () -> {
			this.productJdbcDatabase.getAll();
		});
		
	}
	
	@Test
	void deleteWithSuccess() {
		
		final Long id = 1L;
		
		this.productJdbcDatabase.delete(id);
		
		verify(jdbcTemplate).update("DELETE FROM `tcc_db`.`product` WHERE id = ?", id);
		
	}
	
	@Test
	void deleteWithErrorToAccessDatabase() {

		final Long id = 1L;

		doThrow(RuntimeException.class).when(this.jdbcTemplate).update("DELETE FROM `tcc_db`.`product` WHERE id = ?", id);
		
		assertThrows(ErrorToAccessDatabase.class, () -> {
			this.productJdbcDatabase.delete(id);
		});
	}
	
	@Test
	void updateWithSuccess() {
		
		final Product product = ProductDatabuilder.createProduct();
		
		this.productJdbcDatabase.update(product);
		
		verify(jdbcTemplate).update("UPDATE `tcc_db`.`product` SET name = ?, description = ?, quantity = ? WHERE id = ?",
									product.getName(), product.getDescription(), product.getQuantity(), product.getId());
		
	}
	
	@Test
	void updateWithErrorToAccessDatabase() {
		
		final Product product = ProductDatabuilder.createProduct();
		
		doThrow(RuntimeException.class).when(this.jdbcTemplate).update("UPDATE `tcc_db`.`product` SET name = ?, description = ?, quantity = ? WHERE id = ?",
				product.getName(), product.getDescription(), product.getQuantity(), product.getId());

		assertThrows(ErrorToAccessDatabase.class, () -> {
			this.productJdbcDatabase.update(product);
		});
		
	}
	
	@Test
	@SuppressWarnings("unchecked")
	void getByNameWithSuccess() {
		
		final String name = "anyName";
		
		final List<Product> productList = Arrays.asList(ProductDatabuilder.createProduct());

		when(jdbcTemplate.query(eq("SELECT * FROM `tcc_db`.`product` WHERE name = " + name + ";"), any(RowMapper.class))).thenReturn(productList);

		final Product productReturned = this.productJdbcDatabase.get(name);

		assertProduct(productList.get(0), productReturned);
		
	}
	
	@Test	
	@SuppressWarnings("unchecked")
	void getByNameWithErrorToAccessDatabase() {
		
		final String name = "anyName";

		doThrow(RuntimeException.class).when(this.jdbcTemplate).query(eq("SELECT * FROM `tcc_db`.`product` WHERE name = " + name + ";"), any(RowMapper.class));
		
		assertThrows(ErrorToAccessDatabase.class, () -> {
			this.productJdbcDatabase.get(name);
		});
		
	}
	
	@Test
	@SuppressWarnings("unchecked")
	void getByIdWithSuccess() {
		
		final Long id = 1L;
		
		final List<Product> productList = Arrays.asList(ProductDatabuilder.createProduct());

		when(jdbcTemplate.query(eq("SELECT * FROM `tcc_db`.`product` WHERE id = " + id + ";"), any(RowMapper.class))).thenReturn(productList);

		final Product productReturned = this.productJdbcDatabase.get(id);

		assertProduct(productList.get(0), productReturned);
		
	}
	
	@Test
	@SuppressWarnings("unchecked")
	void getByIdWithSuccessWithEmptyList() {
		
		final Long id = 1L;
		
		final List<Product> productList = Arrays.asList();

		when(jdbcTemplate.query(eq("SELECT * FROM `tcc_db`.`product` WHERE id = " + id + ";"), any(RowMapper.class))).thenReturn(productList);

		final Product productReturned = this.productJdbcDatabase.get(id);

		assertNull(productReturned);
		
	}
	
	@Test	
	@SuppressWarnings("unchecked")
	void getByIdWithErrorToAccessDatabase() {
		
		final Long id = 1L;

		doThrow(RuntimeException.class).when(this.jdbcTemplate).query(eq("SELECT * FROM `tcc_db`.`product` WHERE id = " + id + ";"), any(RowMapper.class));
		
		assertThrows(ErrorToAccessDatabase.class, () -> {
			this.productJdbcDatabase.get(id);
		});
		
	}
	
	private void assertProduct(final Product expected, final Product actual) {
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getDescription(), actual.getDescription());
		assertEquals(expected.getName(), actual.getName());
		assertEquals(expected.getQuantity(), actual.getQuantity());
	}
	
}
