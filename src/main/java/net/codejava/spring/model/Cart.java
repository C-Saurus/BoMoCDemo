package net.codejava.spring.model;

public class Cart {
	private int cart_id;
	private String product_id;
	private String product_name;
	private float price;
	private int quantity;
	public String customer_id;
	
	public Cart() {}

	public Cart(int cart_id, String product_id, float price, int quantity, String customer_id, String product_name) {
		super();
		this.cart_id = cart_id;
		this.product_id = product_id;
		this.price = price;
		this.quantity = quantity;
		this.customer_id = customer_id;
		this.product_name = product_name;
	}

	public int getCart_id() {
		return cart_id;
	}

	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
}
