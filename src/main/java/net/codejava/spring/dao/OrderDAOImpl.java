package net.codejava.spring.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;

import net.codejava.spring.model.Order;
import net.codejava.spring.model.Product;

public class OrderDAOImpl implements OrderDAO{
	
	private JdbcTemplate jdbcTemplate;

	public OrderDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public String addOrder(Order order) {
		String sql = "INSERT INTO order (cart_id, customer_id, order_date, order_status, discount_id, total_price) VALUES (?, ?, ?, ?, ?, ?)";
		try {
			jdbcTemplate.update(sql, order.getCart_id(), order.getCustomer_id(), order.getOrder_date(), order.getOrder_status(), order.getDiscount_id(), order.getTotal_price());
			return "Insert success";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String updateOrder(Order order) {
		String sql = "UPDATE order SET cart_id=?, order_date=?, order_status=?, discount_id=?, total_price=? WHERE order_id=?";
		try {
			jdbcTemplate.update(sql, order.getCart_id(), order.getOrder_date(), order.getOrder_status(), order.getDiscount_id(), order.getTotal_price(), order.getOrder_id());
			return "Update success";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String cancelOrder(String customer_id, int order_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> getListOrder(String customer_id) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public Order getOrderInfo(final int order_id) {
		String sql = "SELECT * FROM order where order_id = ?";
		return jdbcTemplate.query(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, order_id);
			}
		}, new ResultSetExtractor<Order>() {
			@Override
			public Order extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Order order = new Order();
					order.setCart_id(rs.getInt("cart_id"));
					order.setAddress(rs.getString("address"));
					order.setCustomer_id(rs.getString("user_id"));
					order.setOrder_date(rs.getString("order_date"));
					order.setTotal_price(rs.getFloat("total_price"));
					order.setOrder_status(rs.getString("order_status"));
					return order;
				}
				return null;
			}
		});
	}

}
