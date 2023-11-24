package net.codejava.spring.model;

import java.util.Date;

public class Mobile {
	private String mobile_id;
	private int category_id;
	private String mobile_name;
	private String thumbnail;
	private int quantity_in_stock;
	private String description;
	private float price;
	private int vote;
	private int feedback_id;
	private float memory_size;
	private float screen_size;
	private Date warranty_date;
	
	public Mobile() {}

	public Mobile(String mobile_id, int category_id, String mobile_name, String thumbnail, int quantity_in_stock,
			String description, float price, int vote, int feedback_id, float memory_size, float screen_size,
			Date warranty_date) {
		super();
		this.mobile_id = mobile_id;
		this.category_id = category_id;
		this.mobile_name = mobile_name;
		this.thumbnail = thumbnail;
		this.quantity_in_stock = quantity_in_stock;
		this.description = description;
		this.price = price;
		this.vote = vote;
		this.feedback_id = feedback_id;
		this.memory_size = memory_size;
		this.screen_size = screen_size;
		this.warranty_date = warranty_date;
	}

	public String getMobile_id() {
		return mobile_id;
	}

	public void setMobile_id(String mobile_id) {
		this.mobile_id = mobile_id;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public String getMobile_name() {
		return mobile_name;
	}

	public void setMobile_name(String mobile_name) {
		this.mobile_name = mobile_name;
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

	public Date getWarranty_date() {
		return warranty_date;
	}

	public void setWarranty_date(Date warranty_date) {
		this.warranty_date = warranty_date;
	}
}
