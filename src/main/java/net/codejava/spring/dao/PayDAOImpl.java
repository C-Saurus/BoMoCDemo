package net.codejava.spring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import net.codejava.spring.model.Payment;
import net.codejava.spring.model.Shipment;

public class PayDAOImpl implements PayDAO{

	private JdbcTemplate jdbcTemplate;

	public PayDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public int addPayment(final Payment payment) {
		if (payment.getPay_id() > 0) {
			String sql = "UPDATE payment SET customer_id = ?, pay_method_id = ?, pay_status = ?, ship_id = ?, order_id = ? WHERE pay_id=?";
			try {
				jdbcTemplate.update(sql, payment.getCustomer_id(), payment.getPay_method_id(), payment.getPay_status(),  payment.getShip_id(), payment.getOrder_id(), payment.getPay_id());
				return (payment.getPay_id());
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return -1;
			}	
		}
		else {
			final String sql = "INSERT INTO payment (customer_id, pay_method_id, pay_status, ship_id, order_id) VALUES (?, ?, ?, ?, ?)";
			KeyHolder keyHolder = new GeneratedKeyHolder();
			try {
				jdbcTemplate.update(new PreparedStatementCreator() {
		            @Override
		            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
		                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		                ps.setString(1, payment.getCustomer_id());
		                ps.setInt(2, payment.getPay_method_id());
		                ps.setString(3, payment.getPay_status());
		                ps.setInt(4, payment.getShip_id());
		                ps.setInt(5, payment.getOrder_id());
		                return ps;
		            }
		        }, keyHolder);

		        return keyHolder.getKey().intValue();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return -1;
			}
		}
	}

	@Override
	public Payment getPaymentInfo(final int payment_id) {
		String sql = "SELECT * FROM payment WHERE pay_id=?";
		return jdbcTemplate.query(sql, new PreparedStatementSetter() {
		    @Override
		    public void setValues(PreparedStatement ps) throws SQLException {
		        ps.setInt(1, payment_id);
		    }
		}, new ResultSetExtractor<Payment>() {
		    @Override
		    public Payment extractData(ResultSet rs) throws SQLException, DataAccessException {
		        if (rs.next()) {
		        	Payment pay = new Payment();
		        	pay.setShip_id(rs.getInt("ship_id"));
		            pay.setOrder_id(rs.getInt("order_id"));
		            pay.setPay_method_id(rs.getInt("ship_method_id"));
		            pay.setPay_status(rs.getString("ship_status"));
		            pay.setPay_id(rs.getInt("pay_id"));
		            pay.setPay_date(rs.getString("pay_date"));
		            return pay;
		        }
		        return null;
		    }
		});
	}

	@Override
	public String updatePaymentStatus(final int pay_id, final String status) {
		String sql = "UPDATE payment SET pay_status = ?, pay_date = ? WHERE pay_id=?";
		try {
			LocalDate currentDate = LocalDate.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	        final String formattedDate = currentDate.format(formatter);
			jdbcTemplate.update(sql, status, formattedDate, pay_id);
			return "Update Success";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}	
	}

}
