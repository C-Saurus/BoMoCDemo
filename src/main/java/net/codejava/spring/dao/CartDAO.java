package net.codejava.spring.dao;

import java.util.List;

import net.codejava.spring.model.Cart;

public interface CartDAO {
	public String addToCart(String product_id, int quantity, float price, String customer_id);
	public String updateQuantity(String product_id, int quantity, String customer_id);
	public String remove(String product_id, String customer_id);
	public List<Cart> getCartInfo(String customer_id);
}
