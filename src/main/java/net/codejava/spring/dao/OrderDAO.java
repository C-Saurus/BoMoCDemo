package net.codejava.spring.dao;

import java.util.List;

import net.codejava.spring.model.Order;

public interface OrderDAO {
	public String addOrder(Order order);
	public String updateOrder(Order order);
	public String cancelOrder(String customer_id, int order_id);
	public List<Order> getListOrder(String customer_id);
	public Order getOrderInfo(int order_id);
}
