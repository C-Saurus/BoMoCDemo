package net.codejava.spring.model;

import java.util.Date;

public class Clothes {
	private String clothes_id;
	private int category_id;
	private String clothes_name;
	private String thumbnail;
	private int quantity_in_stock;
	private String description;
	private float price;
	private int vote;
	private int feedback_id;
	private String size ;
	private int suitable_age;
	private Date warranty_date;
	private String brand ;
	
	public Clothes() {}

	public Clothes(String clothes_id, int category_id, String clothes_name, String thumbnail, int quantity_in_stock,
			String description, float price, int vote, int feedback_id, String size, int suitable_age,
			Date warranty_date, String brand) {
		super();
		this.clothes_id = clothes_id;
		this.category_id = category_id;
		this.clothes_name = clothes_name;
		this.thumbnail = thumbnail;
		this.quantity_in_stock = quantity_in_stock;
		this.description = description;
		this.price = price;
		this.vote = vote;
		this.feedback_id = feedback_id;
		this.size = size;
		this.suitable_age = suitable_age;
		this.warranty_date = warranty_date;
		this.brand = brand;
	}

	public String getClothes_id() {
		return clothes_id;
	}

	public void setClothes_id(String clothes_id) {
		this.clothes_id = clothes_id;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public String getClothes_name() {
		return clothes_name;
	}

	public void setClothes_name(String clothes_name) {
		this.clothes_name = clothes_name;
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

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public int getSuitable_age() {
		return suitable_age;
	}

	public void setSuitable_age(int suitable_age) {
		this.suitable_age = suitable_age;
	}

	public Date getWarranty_date() {
		return warranty_date;
	}

	public void setWarranty_date(Date warranty_date) {
		this.warranty_date = warranty_date;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
}
