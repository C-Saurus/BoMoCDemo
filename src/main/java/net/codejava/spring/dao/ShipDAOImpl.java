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

import net.codejava.spring.model.Shipment;

public class ShipDAOImpl implements ShipDAO{
	
	private JdbcTemplate jdbcTemplate;

	public ShipDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public int addShip(final Shipment ship) {
		if (ship.getShip_id() > 0) {
			String sql = "UPDATE shipping SET customer_id = ?, ship_method_id = ?, ship_status = ?, address = ?, order_id = ? WHERE ship_id=?";
			try {
				jdbcTemplate.update(sql, ship.getCustomer_id(), ship.getShip_method_id(), ship.getShip_status(),  ship.getAddress(), ship.getOrder_id(), ship.getShip_id());
				return (ship.getShip_id());
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return -1;
			}	
		}
		else {
			final String sql = "INSERT INTO shipping (customer_id, ship_method_id, ship_status, address, order_id) VALUES (?, ?, ?, ?, ?)";
			KeyHolder keyHolder = new GeneratedKeyHolder();
			try {
				jdbcTemplate.update(new PreparedStatementCreator() {
		            @Override
		            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
		                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		                ps.setString(1, ship.getCustomer_id());
		                ps.setInt(2, ship.getShip_method_id());
		                ps.setString(3, ship.getShip_status());
		                ps.setString(4, ship.getAddress());
		                ps.setInt(5, ship.getOrder_id());
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
	public String updateStatus(String status, int ship_id) {
		String sql = "UPDATE shipping SET ship_status = ? WHERE ship_id=?";
		try {
			jdbcTemplate.update(sql, status, ship_id);
			return "Update success";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String addOrderId(int order_id, int ship_id) {
		String sql = "UPDATE shipping SET order_id = ? WHERE ship_id=?";
		try {
			jdbcTemplate.update(sql, order_id, ship_id);
			return "Update success";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Shipment getShipmentInfo(final int shipment_id) {
		String sql = "SELECT * FROM shipping WHERE ship_id=?";
		return jdbcTemplate.query(sql, new PreparedStatementSetter() {
		    @Override
		    public void setValues(PreparedStatement ps) throws SQLException {
		        ps.setInt(1, shipment_id);
		    }
		}, new ResultSetExtractor<Shipment>() {
		    @Override
		    public Shipment extractData(ResultSet rs) throws SQLException, DataAccessException {
		        if (rs.next()) {
		        	Shipment ship = new Shipment();
		        	ship.setShip_id(rs.getInt("ship_id"));
		            ship.setOrder_id(rs.getInt("order_id"));
		            ship.setShip_method_id(rs.getInt("ship_method_id"));
		            ship.setShip_status(rs.getString("ship_status"));
		            ship.setAddress(rs.getString("address"));
		            return ship;
		        }
		        return null;
		    }
		});
	}

	@Override
	public int getShipMethodId(final int ship_id) {
		String sql = "SELECT ship_method_id FROM shipping WHERE ship_id = ?";
		return jdbcTemplate.query(sql, new PreparedStatementSetter() {
		    @Override
		    public void setValues(PreparedStatement ps) throws SQLException {
		        ps.setInt(1, ship_id);
		    }
		}, new ResultSetExtractor<Integer>() {
		    @Override
		    public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
		        if (rs.next()) {
		            return rs.getInt("ship_method_id");
		        }
		        return -1;
		    }
		});
	}

}
