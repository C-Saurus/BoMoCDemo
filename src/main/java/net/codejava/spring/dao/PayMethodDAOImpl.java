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

import net.codejava.spring.model.PayMethod;

public class PayMethodDAOImpl implements PayMethodDAO{
	private JdbcTemplate jdbcTemplate;

	public PayMethodDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<PayMethod> getPayMethod() {
		String sql = "SELECT * from paymethod";
		List<PayMethod> listMethod = jdbcTemplate.query(sql, new RowMapper<PayMethod>() {

			@Override
			public PayMethod mapRow(ResultSet rs, int rowNum) throws SQLException {
				PayMethod payMethod = new PayMethod();
				payMethod.setPay_method_id(rs.getInt("pay_method_id"));
				payMethod.setPay_method_name(rs.getString("pay_method_name"));
				payMethod.setOffer_percent(rs.getFloat("offer_percent"));
				return payMethod;
			}

		});

		return listMethod;	
	}

	@Override
	public float getOfferById(final int id) {
		String sql = "SELECT offer_percent FROM paymethod WHERE pay_method_id = ?";
		return jdbcTemplate.query(sql, new PreparedStatementSetter() {
		    @Override
		    public void setValues(PreparedStatement ps) throws SQLException {
		        ps.setInt(1, id);
		    }
		}, new ResultSetExtractor<Float>() {
		    @Override
		    public Float extractData(ResultSet rs) throws SQLException, DataAccessException {
		        if (rs.next()) {
		            return rs.getFloat("offer_percent");
		        }
		        return -1F;
		    }
		});
	}
}
