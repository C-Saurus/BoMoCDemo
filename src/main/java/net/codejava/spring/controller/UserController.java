package net.codejava.spring.controller;

/*
 * @author Vu Huu Dat
 */

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import net.codejava.spring.dao.UserDAO;
import net.codejava.spring.model.Login;
import net.codejava.spring.model.User;

@Controller
public class UserController {
	@Autowired
	private UserDAO userDAO;
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerRequest(Model model) {
		User newUser = new User();
		model.addAttribute("user", newUser);
		return "register";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginRequest(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("user_id");
		System.out.println("user " + user_id);
		if (user_id != null) {
			return "redirect:/";
		}
		model.addAttribute("data", new Login());
		return "login";
	}
	
	@RequestMapping(value = "/saveuser", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
		user.setUser_id("CU" + String.valueOf(System.currentTimeMillis()));
		if (bindingResult.hasErrors()) {
	        return "register";
	    }
		String response = userDAO.register(user);	
		if (response == null) {
			return "error";
		}
		return "redirect:/login";
	}
	
	@RequestMapping(value = "/updateuser", method = RequestMethod.POST)
	public String updateUser(HttpServletRequest request, @ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
	        return "profile";
	    }
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("user_id");
		user.setUser_id(user_id);
		String response = userDAO.updateProfile(user);	
		if (response == null) {
			return "error";
		}
		return "redirect:/profile";
	}
	
	@RequestMapping(value = "/authentication", method = RequestMethod.POST)
	public String authentication(@ModelAttribute("data") @Valid Login data, BindingResult bindingResult, HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
	        return "login";
	    }
		User user = userDAO.login(data.email, data.password);
		if (user == null) {
			bindingResult.rejectValue("password", "authen", "Email or Password is wrong");
			return "login";
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("user_id", user.getUser_id());
		session.setAttribute("username", user.getUsername());
		session.setAttribute("role", user.role);
		return "redirect:/";
	}
	
	
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String editUser(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("user_id");
		if (user_id == null) {
			return "redirect:/login?error=1";
		}
		User user = userDAO.getUserInfo(user_id);
		model.addAttribute("user", user);
		return "profile";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("user_id");
		session.removeAttribute("username");
		session.removeAttribute("role");
		return "redirect:/login";
	}
}
