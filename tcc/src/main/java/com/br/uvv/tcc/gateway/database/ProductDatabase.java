package com.br.uvv.tcc.gateway.database;

import java.util.List;

import com.br.uvv.tcc.entities.Product;

public interface ProductDatabase {
	void create(Product product);
	void sell(Long id, Integer quantitySold);
	List<Product> getAll();
	void delete(Long id);
	Product get(Long id);
	Product get(String name);
	void update(Product product);
}
