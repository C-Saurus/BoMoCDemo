package net.codejava.spring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import net.codejava.spring.model.Cart;
import net.codejava.spring.model.Order;
import net.codejava.spring.model.Product;

public class OrderDAOImpl implements OrderDAO {

	private JdbcTemplate jdbcTemplate;

	public OrderDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	@Transactional
	public String addOrder(Order order) {
		String sql = "INSERT INTO order (customer_id, ship_od, pay_id, total_price) VALUES (?, ?, ?, ?, ?, ?)";
		String sqlUpdate = "UPDATE cart SET is_ordered=? WHERE is_ordered=? AND customer_id = ?";
		try {
			jdbcTemplate.update(sqlUpdate, 1, 0, order.getCustomer_id());
			jdbcTemplate.update(sql, order.getCustomer_id(), order.getPay_id(), order.getShip_id(),
					order.getTotal_price());
			return "Insert success";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@Transactional
	public String updateOrder(Order order) {
		String sql = "UPDATE order_table SET ship_id = ?, pay_id = ?, total_price=? WHERE order_id=?";
		String sqlUpdate = "UPDATE cart SET is_ordered=? WHERE is_ordered=? AND customer_id = ?";
		try {
			jdbcTemplate.update(sqlUpdate, 1, 0, order.getCustomer_id());
			jdbcTemplate.update(sql, order.getShip_id(), order.getPay_id(), order.getTotal_price(),
					order.getOrder_id());
			return "Update success";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String cancelOrder(int order_id) {
		String sqlDeleteOrder = "DELETE FROM order_table WHERE order_id=?";
		String sqlDeleteShip = "DELETE FROM shipping WHERE order_id=?";
		String sqlDeletePay = "DELETE FROM payment WHERE order_id=?";
		try {
			jdbcTemplate.update(sqlDeleteOrder);
			jdbcTemplate.update(sqlDeleteShip);
			jdbcTemplate.update(sqlDeletePay);
			return "Delete success";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Order> getListOrder(final String customer_id) {
		String sql = "SELECT * FROM order_table WHERE customer_id = ?";
		List<Order> listOrder = jdbcTemplate.query(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, customer_id);
			}
		}, new RowMapper<Order>() {

			@Override
			public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
				Order order = new Order();
				order.setOrder_id(rs.getInt("order_id"));
				order.setOrder_date(rs.getString("order_date"));
				order.setOrder_status(rs.getString("order_status"));
				order.setTotal_price(rs.getFloat("total_price"));
				if (order.getOrder_status() == null) {
					order.setOrder_status("Đang giao");
				}

				return order;
			}

		});

		return listOrder;
	}

	@Override
	public Order getOrderInfo(final int order_id) {
		String sql = "SELECT * FROM order_table where order_id = ?";
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

	@Override
	public Long initOrder(final String customer_id) {
		final String sql = "INSERT INTO order_table (customer_id, order_date) VALUES (?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		try {
			LocalDate currentDate = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			final String formattedDate = currentDate.format(formatter);
			jdbcTemplate.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, customer_id);
					ps.setString(2, formattedDate);
					return ps;
				}
			}, keyHolder);

			return keyHolder.getKey().longValue();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return -1L;
		}
	}

	@Override
	public List<Order> getAllOrder() {
		String sql = "SELECT * FROM order_table";
		List<Order> listOrder = jdbcTemplate.query(sql, new RowMapper<Order>() {

			@Override
			public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
				Order order = new Order();
				order.setOrder_id(rs.getInt("order_id"));
				order.setOrder_date(rs.getString("order_date"));
				order.setOrder_status(rs.getString("order_status"));
				order.setTotal_price(rs.getFloat("total_price"));
				if (order.getOrder_status() == null) {
					order.setOrder_status("Đang giao");
				}

				return order;
			}

		});

		return listOrder;
	}

	@Transactional
	@Override
	public String updateOrderStatus(int order_id) {
		String sql = "UPDATE order_table SET order_status = ? WHERE order_id=?";
		String sqlShip = "UPDATE shipping SET ship_status = ? WHERE order_id=?";
		String sqlPay = "UPDATE payment SET pay_status = ? WHERE order_id=?";
		
		try {
			jdbcTemplate.update(sqlShip, "Đã giao hàng", order_id);
			jdbcTemplate.update(sql, "Đã nhận hàng", order_id);
			jdbcTemplate.update(sqlShip, "Đã thanh toán", order_id);
			return "Update success";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
}
