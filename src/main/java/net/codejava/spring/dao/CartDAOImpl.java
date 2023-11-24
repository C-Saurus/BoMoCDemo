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

import net.codejava.spring.model.Cart;

public class CartDAOImpl implements CartDAO{
	
	private JdbcTemplate jdbcTemplate;

	public CartDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public Integer checkExistProduct(final String product_id, final String customer_id) {
		String sql = "Select quantity from cart WHERE product_id =? AND customer_id =  ?";
		return jdbcTemplate.query(sql, new PreparedStatementSetter() {
		    @Override
		    public void setValues(PreparedStatement ps) throws SQLException {
		        ps.setString(1, product_id);
		        ps.setString(2, customer_id);
		    }
		}, new ResultSetExtractor<Integer>() {
		    @Override
		    public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
		        if (rs.next()) {
		            return rs.getInt("quantity");
		        }
		        return -1;
		    }
		});
	}

	@Override
	public String addToCart(String product_id, int quantity, float price, String customer_id) {
		int res = checkExistProduct(product_id, customer_id);
		if ( res > -1) {
			String sql = "UPDATE cart SET quantity=? WHERE product_id=? AND customer_id = ?";
			try {
				jdbcTemplate.update(sql, quantity + res, product_id, customer_id);
				return "Update success";
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return null;
			}
		}
		else {
			String sql = "INSERT INTO cart (product_id, quantity, price, customer_id) VALUES (?, ?, ?, ?)";
			try {
				jdbcTemplate.update(sql, product_id, quantity, price, customer_id);
				return "Insert success";
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return null;
			}
		}
	}

	@Override
	public String updateQuantity(String product_id, int quantity, String customer_id) {
		String sql = "UPDATE cart SET quantity=? WHERE product_id=? AND customer_id = ?";
		try {
			jdbcTemplate.update(sql, quantity, product_id, customer_id);
			return "Update success";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String remove(String product_id, String customer_id) {
		String sql = "DELETE FROM cart WHERE product_id=? AND customer_id=?";
		try {
			jdbcTemplate.update(sql, product_id, customer_id);
			return "Delete success";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Cart> getCartInfo(final String customer_id) {
		String sql = "SELECT c1.cart_id, p1.product_id, p1.product_name, c1.quantity, c1.price FROM moboc.cart c1 left join moboc.product p1 on c1.product_id = p1.product_id where c1.customer_id = ?";
		List<Cart> listProduct = jdbcTemplate.query(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, customer_id);
			}
		}, new RowMapper<Cart>() {

			@Override
			public Cart mapRow(ResultSet rs, int rowNum) throws SQLException {
				Cart cart = new Cart();
				cart.setCart_id(rs.getInt("cart_id"));
				cart.setProduct_id(rs.getString("product_id"));
				cart.setProduct_name(rs.getString("product_name"));
				cart.setQuantity(rs.getInt("quantity"));
				cart.setPrice(rs.getFloat("price") * cart.getQuantity());
				return cart;
			}

		});

		return listProduct;
	}

}
