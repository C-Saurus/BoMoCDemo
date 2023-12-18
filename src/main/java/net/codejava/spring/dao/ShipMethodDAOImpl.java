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
import org.springframework.jdbc.core.RowMapper;
import net.codejava.spring.model.ShipMethod;


public class ShipMethodDAOImpl implements ShipMethodDAO{
	
	private JdbcTemplate jdbcTemplate;

	public ShipMethodDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<ShipMethod> getShipMethod() {
		String sql = "SELECT * from shipmethod";
		List<ShipMethod> listMethod = jdbcTemplate.query(sql, new RowMapper<ShipMethod>() {

			@Override
			public ShipMethod mapRow(ResultSet rs, int rowNum) throws SQLException {
				ShipMethod shipMethod = new ShipMethod();
				shipMethod.setShip_method_id(rs.getInt("ship_method_id"));
				shipMethod.setShip_method_name(rs.getString("ship_method_name"));
				shipMethod.setPrice(rs.getFloat("price"));
				return shipMethod;
			}

		});

		return listMethod;	
	}

	@Override
	public float getPriceById(final int id) {
		String sql = "SELECT price FROM shipmethod WHERE ship_method_id = ?";
		return jdbcTemplate.query(sql, new PreparedStatementSetter() {
		    @Override
		    public void setValues(PreparedStatement ps) throws SQLException {
		        ps.setInt(1, id);
		    }
		}, new ResultSetExtractor<Float>() {
		    @Override
		    public Float extractData(ResultSet rs) throws SQLException, DataAccessException {
		        if (rs.next()) {
		            return rs.getFloat("price");
		        }
		        return -1F;
		    }
		});
	}

}
