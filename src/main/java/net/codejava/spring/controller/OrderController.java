package net.codejava.spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.codejava.spring.dao.OrderDAO;
import net.codejava.spring.model.Order;

@Controller
public class OrderController {
	@Autowired
	private OrderDAO orderDAO;
	
	@RequestMapping(value="/listorder")
	public String getCartInfo(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("user_id");
		int role = (int)session.getAttribute("role");
		System.out.println("Role " + role);
		if (user_id == null) {
			return "redirect:/login?error=1";
		}
		List<Order> listOrder;
		if (role == 0) {
			listOrder = orderDAO.getListOrder(user_id);
		}
		else {
			listOrder = orderDAO.getAllOrder();
		}
		model.addAttribute("listOrder", listOrder);
		model.addAttribute("role", role);
		return "listorder";	
	}
	
	@RequestMapping(value="/updateStatus/{id}", method = RequestMethod.POST)
	public String updateStatus(@PathVariable int id, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("user_id");
		if (user_id == null) {
			return "redirect:/login";
		}
		String res = orderDAO.updateOrderStatus(id);
		if (res == null) {
			return "error";
		}
		return "orderlist";
	}
}
