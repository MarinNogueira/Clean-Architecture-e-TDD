package com.br.uvv.tcc.gateway.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.br.uvv.tcc.entities.Product;

public class ProductJdbcDatabase implements ProductDatabase {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void create(Product product) {

		jdbcTemplate.execute("INSERT INTO product (id, name, description, quantity) VALUES (" 
				+ product.getId() + ", " + product.getName() + ", " + product.getDescription() + ", " + product.getQuantity() + ");");
		
	}

}
