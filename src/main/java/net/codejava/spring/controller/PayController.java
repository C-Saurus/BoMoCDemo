package net.codejava.spring.controller;

import java.util.List;

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

import net.codejava.spring.dao.CartDAO;
import net.codejava.spring.dao.OrderDAO;
import net.codejava.spring.dao.PayDAO;
import net.codejava.spring.dao.PayMethodDAO;
import net.codejava.spring.dao.ShipDAO;
import net.codejava.spring.dao.ShipMethodDAO;
import net.codejava.spring.model.Cart;
import net.codejava.spring.model.Order;
import net.codejava.spring.model.PayMethod;
import net.codejava.spring.model.Payment;
import net.codejava.spring.model.ShipMethod;
import net.codejava.spring.model.Shipment;

@Controller
public class PayController {
	@Autowired
	private PayMethodDAO payMethodDAO;
	@Autowired
	private ShipDAO shipDAO;
	@Autowired
	private PayDAO payDAO;
	@Autowired
	private ShipMethodDAO smDAO;
	@Autowired
	private CartDAO cartDAO;
	@Autowired
	private OrderDAO orderDAO;
	
	@RequestMapping(value = "/addPay", method = RequestMethod.POST)
	public String addShip(@ModelAttribute("pay") @Valid Payment pay, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			List<PayMethod> listPayMethod = payMethodDAO.getPayMethod();
			model.addAttribute("listPayMethod", listPayMethod);
	        return "pay";
	    }
		int pay_id = payDAO.addPayment(pay);
		if (pay_id == -1) {
			return "error";
		}
		int shipMethodId = shipDAO.getShipMethodId(pay.getShip_id());
		float shipPrice = smDAO.getPriceById(shipMethodId);
		float offerPercent = payMethodDAO.getOfferById(pay.getPay_method_id());
		List<Cart> listCart = cartDAO.getCartInfo(pay.getCustomer_id());
		float totalPrice = 0; 
		for (Cart i: listCart) {
			totalPrice += i.getPrice();
		}
		totalPrice += shipPrice;
		totalPrice = totalPrice - (totalPrice * offerPercent)/100;
		Order order = new Order();
		order.setShip_id(pay.getShip_id());
		order.setPay_id(pay_id);
		order.setOrder_id(pay.getOrder_id());
		order.setTotal_price(totalPrice);
		String res = orderDAO.updateOrder(order);
		if (res == null) {
			return "error";
		}
		model.addAttribute("shipPrice", shipPrice);
		model.addAttribute("offerPercent", offerPercent);
		model.addAttribute("listCart", listCart);
		model.addAttribute("order", order);
		return "order";
	}
	
	@RequestMapping(value="/pay/{id}")
	public String getShipInfo(Model model, @PathVariable int id,HttpServletRequest request) {
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("user_id");
		if (user_id == null) {
			return "redirect:/login?error=1";
		}
		List<PayMethod> listPayMethod = payMethodDAO.getPayMethod();
		model.addAttribute("listPayMethod", listPayMethod);
		Payment pay = payDAO.getPaymentInfo(id);
		model.addAttribute("pay", pay);
		return "pay";	
	}
}
