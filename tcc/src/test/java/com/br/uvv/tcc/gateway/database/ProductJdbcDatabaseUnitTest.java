package com.br.uvv.tcc.gateway.database;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import com.br.uvv.tcc.databuilder.ProductDatabuilder;
import com.br.uvv.tcc.entities.Product;
import com.br.uvv.tcc.test.config.DBJdbcConfig;

@Disabled
@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {DBJdbcConfig.class, ProductJdbcDatabase.class})
class ProductJdbcDatabaseUnitTest {

	@MockBean
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private ProductJdbcDatabase productJdbcDatabase;
	
	private final String schemaName = "tcc_db";
	
	@AfterEach
	@BeforeEach
	void cleanData() {
		this.cleanDataBase(schemaName);
	}

	@Test
	void createWithSuccess() {
		final Product product = ProductDatabuilder.createProduct();
		
		this.productJdbcDatabase.create(product);
		
		final List<Product> productList = this.getProducts();

		assertEquals(product.getId(), productList.get(0).getId());
		assertEquals(product.getName(), productList.get(0).getName());
		assertEquals(product.getDescription(), productList.get(0).getDescription());
		assertEquals(product.getQuantity(), productList.get(0).getQuantity());
		
	}
	
	private void cleanDataBase(final String schemaName) {
		this.jdbcTemplate.execute("DELETE FROM `"+ schemaName +"`.`product`;");
	}
	
	private List<Product> getProducts() {
		
		return jdbcTemplate.query("SELECT * FROM `"+ schemaName +"`.`product`", 
				(rs, i) -> {
					final Product product = new Product();
					
					product.setId(rs.getLong("id"));
					product.setName(rs.getString("name"));
					product.setDescription(rs.getString("description"));
					product.setQuantity(rs.getInt("quantity"));
					
					return product;
				});
		
	}

	
}
