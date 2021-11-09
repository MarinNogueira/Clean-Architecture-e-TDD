package com.br.uvv.tcc.gateway.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.br.uvv.tcc.entities.Product;
import com.br.uvv.tcc.gateway.exceptions.ErrorToAccessDatabase;

@Component
public class ProductJdbcDatabase implements ProductDatabase {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void create(final Product product) {

		try {
			jdbcTemplate.update("INSERT INTO product (id, name, description, quantity) VALUES (?, ?, ?, ?);",
					product.getId(), product.getName(), product.getDescription(), product.getQuantity());
		} catch (Exception e) {
			
			throw new ErrorToAccessDatabase();
			
		}
	}

	@Override
	public void sell(final Long id, final Integer quantitySold) {
		
		try {
			
			jdbcTemplate.update("UPDATE product SET Quantity = Quantity - ? WHERE id = ?", quantitySold, id);
			
		} catch (Exception e) {

			throw new ErrorToAccessDatabase();
			
		}
		
	}

	@Override
	public List<Product> getAll() {
		
		try {	

			final List<Product> productList = jdbcTemplate.query("SELECT * FROM product;", 
					(rs, i) -> resultSetMap(rs));
			
			return productList;
			
		} catch (Exception e) {
			throw new ErrorToAccessDatabase();
		}

	}

	@Override
	public void delete(Long id) {

		try {

			jdbcTemplate.update("DELETE FROM product WHERE id = ?", id);
			
		} catch (Exception e) {
			
			throw new ErrorToAccessDatabase();
		
		}
		
	}

	@Override
	public Product get(Long id) {
		
		try {
			final List<Product> productList = jdbcTemplate.query("SELECT * FROM product WHERE id = " + id + ";",
					(rs, i) -> resultSetMap(rs));
			
			return productList.get(0);
		} catch (Exception e) {
			
			throw new ErrorToAccessDatabase();
		
		}
		
	}

	@Override
	public Product get(String name) {

		try {
			final List<Product> productList = jdbcTemplate.query("SELECT * FROM product WHERE name = " + name + ";", 
					(rs, i) -> resultSetMap(rs));
			
			return productList.get(0);
		} catch (Exception e) {
			
			throw new ErrorToAccessDatabase();
		
		}
	}

	@Override
	public void update(Product product) {
		
		try {

			this.jdbcTemplate.update("UPDATE product SET name = ?, description = ?, quantity = ? WHERE id = ?",
					product.getName(), product.getDescription(), product.getQuantity(), product.getId());
			
		} catch (Exception e) {
			
			throw new ErrorToAccessDatabase();
		
		}

		
	}
	
	private Product resultSetMap(ResultSet rs) throws SQLException {
		final Product product = new Product();
		
		product.setId(rs.getLong("id"));
		product.setName(rs.getString("name"));
		product.setDescription(rs.getString("description"));
		product.setQuantity(rs.getInt("quantity"));
		return product;
	}

}
