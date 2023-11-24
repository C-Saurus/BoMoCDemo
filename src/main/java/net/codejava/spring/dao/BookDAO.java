package net.codejava.spring.dao;

import java.util.List;

import net.codejava.spring.model.Book;

public interface BookDAO {
	
	public void saveOrUpdate(Book contact);
	
	public void delete(int contactId);
	
	public Book get(int contactId);
	
	public List<Book> list();
}
