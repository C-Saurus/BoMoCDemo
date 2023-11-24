package net.codejava.spring.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Login {
	@NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
	public String email;
	
	@NotBlank(message = "Password is required")
	@Size(min = 6, message = "Password is more than 6 characters")
	public String password;
	
	public Login(){}

	public Login(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
