package net.codejava.spring.model;

import java.util.Date;

public class Product {
	private String product_id;
	private String category;
	private String product_name;
	private String thumbnail;
	private int quantity_in_stock;
	private String description;
	private float price;
	private int vote;
	private int feedback_id;
	private int material_id;
	private String warranty_date;
	private String brand ;
	private String size ;
	private String suitable_age;
	private float memory_size;
	private float screen_size;
	private String release_date;
	private String author_name; 
	private int pages;
	
	public Product() {}

	public Product(String product_id, String category, String product_name, String thumbnail, int quantity_in_stock,
			String description, float price, int vote, int feedback_id, int material_id, String warranty_date,
			String brand, String size, String suitable_age, float memory_size, float screen_size, String release_date,
			String author_name, int pages) {
		super();
		this.product_id = product_id;
		this.category = category;
		this.product_name = product_name;
		this.thumbnail = thumbnail;
		this.quantity_in_stock = quantity_in_stock;
		this.description = description;
		this.price = price;
		this.vote = vote;
		this.feedback_id = feedback_id;
		this.material_id = material_id;
		this.warranty_date = warranty_date;
		this.brand = brand;
		this.size = size;
		this.suitable_age = suitable_age;
		this.memory_size = memory_size;
		this.screen_size = screen_size;
		this.release_date = release_date;
		this.author_name = author_name;
		this.pages = pages;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public int getQuantity_in_stock() {
		return quantity_in_stock;
	}

	public void setQuantity_in_stock(int quantity_in_stock) {
		this.quantity_in_stock = quantity_in_stock;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getVote() {
		return vote;
	}

	public void setVote(int vote) {
		this.vote = vote;
	}

	public int getFeedback_id() {
		return feedback_id;
	}

	public void setFeedback_id(int feedback_id) {
		this.feedback_id = feedback_id;
	}

	public int getMaterial_id() {
		return material_id;
	}

	public void setMaterial_id(int material_id) {
		this.material_id = material_id;
	}

	public String getWarranty_date() {
		return warranty_date;
	}

	public void setWarranty_date(String warranty_date) {
		this.warranty_date = warranty_date;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getSuitable_age() {
		return suitable_age;
	}

	public void setSuitable_age(String suitable_age) {
		this.suitable_age = suitable_age;
	}

	public float getMemory_size() {
		return memory_size;
	}

	public void setMemory_size(float memory_size) {
		this.memory_size = memory_size;
	}

	public float getScreen_size() {
		return screen_size;
	}

	public void setScreen_size(float screen_size) {
		this.screen_size = screen_size;
	}

	public String getRelease_date() {
		return release_date;
	}

	public void setRelease_date(String release_date) {
		this.release_date = release_date;
	}

	public String getAuthor_name() {
		return author_name;
	}

	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	
	
}
