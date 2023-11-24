package net.codejava.spring.model;

import java.util.Date;

public class Book {
	private String book_id;
	private int category_id;
	private String title;
	private String thumbnail;
	private int quantity_in_stock;
	private String description;
	private float price;
	private int vote;
	private int feedback_id;
	private Date release_date;
	private String author_name; 
	private int pages;

	public Book() {
	}

	public Book(String book_id, int category_id, String title, String thumbnail, int quantity_in_stock,
			String description, float price, int vote, int feedback_id, Date release_date, String author_name,
			int pages) {
		super();
		this.book_id = book_id;
		this.category_id = category_id;
		this.title = title;
		this.thumbnail = thumbnail;
		this.quantity_in_stock = quantity_in_stock;
		this.description = description;
		this.price = price;
		this.vote = vote;
		this.feedback_id = feedback_id;
		this.release_date = release_date;
		this.author_name = author_name;
		this.pages = pages;
	}

	public String getBook_id() {
		return book_id;
	}

	public void setBook_id(String book_id) {
		this.book_id = book_id;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public Date getRelease_date() {
		return release_date;
	}

	public void setRelease_date(Date release_date) {
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

}
