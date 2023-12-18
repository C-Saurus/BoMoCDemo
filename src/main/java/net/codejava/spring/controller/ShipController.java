package net.codejava.spring.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.codejava.spring.dao.OrderDAO;
import net.codejava.spring.dao.PayMethodDAO;
import net.codejava.spring.dao.ShipDAO;
import net.codejava.spring.dao.ShipMethodDAO;
import net.codejava.spring.dao.UserDAO;
import net.codejava.spring.model.PayMethod;
import net.codejava.spring.model.Payment;
import net.codejava.spring.model.ShipMethod;
import net.codejava.spring.model.Shipment;

@Controller
public class ShipController {
	@Autowired
	private ShipDAO shipDAO;
	@Autowired
	private ShipMethodDAO smDAO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private OrderDAO orderDAO;
	@Autowired
	private PayMethodDAO payMethodDAO;
	
	@RequestMapping(value="/ship/create")
	public String addShipForm(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("user_id");
		if (user_id == null) {
			return "redirect:/login?error=1";
		}
		String address = userDAO.getAddressById(user_id);
		int order_id = (orderDAO.initOrder(user_id)).intValue();
		if (address == null || order_id == -1) {
			return "error";
		}
		Shipment ship = new Shipment();
		ship.setOrder_id(order_id);
		ship.setAddress(address);
		ship.setShip_method_id(0);
		ship.setCustomer_id(user_id);
		List<ShipMethod> listShipMethod = smDAO.getShipMethod();
		model.addAttribute("listShipMethod", listShipMethod);
		model.addAttribute("ship", ship);
		return "ship";
	}
	
	
	@RequestMapping(value="/ship/{id}")
	public String getShipInfo(Model model, @PathVariable int id,HttpServletRequest request) {
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("user_id");
		if (user_id == null) {
			return "redirect:/login?error=1";
		}
		List<ShipMethod> listShipMethod = smDAO.getShipMethod();
		model.addAttribute("listShipMethod", listShipMethod);
		Shipment ship = shipDAO.getShipmentInfo(id);
		model.addAttribute("ship", ship);
		return "ship";	
	}
	
	@RequestMapping(value = "/addShip", method = RequestMethod.POST)
	public String addShip(@ModelAttribute("ship") @Valid Shipment ship, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			List<ShipMethod> listShipMethod = smDAO.getShipMethod();
			model.addAttribute("listShipMethod", listShipMethod);
	        return "ship";
	    }
		System.out.println(ship.toString());
		int ship_id = shipDAO.addShip(ship);
		if (ship_id == -1) {
			return "error";
		}
		List<PayMethod> listPayMethod = payMethodDAO.getPayMethod();
		
		Payment pay = new Payment();
		pay.setCustomer_id(ship.getCustomer_id());
		pay.setShip_id(ship_id);
		pay.setOrder_id(ship.getOrder_id());
		pay.setPay_method_id(0);
		model.addAttribute("listPayMethod", listPayMethod);
		model.addAttribute("pay", pay);
		return "pay";
	}
	
	@RequestMapping(value = "/cancelOrder/{id}", method = RequestMethod.POST)
	public String cancelOrder(@PathVariable int id) {
		String res = orderDAO.cancelOrder(id);
		if (res == null) {
			return "error";
		}
		return "cart";
	}
}
