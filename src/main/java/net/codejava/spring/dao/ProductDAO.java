package net.codejava.spring.dao;

import java.util.List;
import net.codejava.spring.model.Product;

public interface ProductDAO {
	public Product getDetail(String product_id);
	public List<Product> getList();
	public List<Product> searchProduct(String search);
	public String addProduct(Product product);
}
