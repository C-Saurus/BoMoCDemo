package net.codejava.spring.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class User {
	private String user_id;
	
	@NotBlank(message = "User is required")
	private String username;
	@NotBlank(message = "Password is required")
	@Size(min = 6, message = "Password is more than 6 characters")
	private String password;
	private String dob;
	@NotBlank(message = "Phone number is required")
	private String phone_num;
	@NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
	private String email;
	@NotBlank(message = "Address is required")
	private String address;
	public int role;
	
	public User() {}

	public User(String user_id, String username, String password, String dob, String phone_num, String email,
			String address, int role) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.password = password;
		this.dob = dob;
		this.phone_num = phone_num;
		this.email = email;
		this.address = address;
		this.role = role;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getPhone_num() {
		return phone_num;
	}

	public void setPhone_num(String phone_num) {
		this.phone_num = phone_num;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
