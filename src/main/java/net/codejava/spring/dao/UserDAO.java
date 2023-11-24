package net.codejava.spring.dao;

import net.codejava.spring.model.User;

public interface UserDAO {
	public String updateProfile(User user);
	
	public User getUserInfo(String user_id);
	
	public String register(User user);
	
	public User login(String email, String password);
}
