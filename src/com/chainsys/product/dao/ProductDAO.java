package com.chainsys.product.dao;

import java.util.Date;
import java.util.List;
import java.time.LocalDate;
import java.util.Set;

import com.chainsys.product.model.Product;

public interface ProductDAO {
	Set<Product> findAll();
	List<String> findAllName();
	List<Integer> findAllId();
	List<Date> findAllDate();

	
	Product findById(int id);
	Product findByName(String name);

	void save(Product product);

	void update(Product product);
	void update_expire(Product updateProduct1);
	
	void delete(int id);
	

	void delete_date(LocalDate expiryDate);

	Product findByDate(LocalDate date);

	void delete_name(String name);
	
	
	

	
	
}
