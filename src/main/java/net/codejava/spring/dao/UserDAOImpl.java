package net.codejava.spring.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import net.codejava.spring.model.User;
import net.codejava.spring.model.UserRole;

public class UserDAOImpl implements UserDAO{
	private JdbcTemplate jdbcTemplate;

	public UserDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public String updateProfile(User user) {
		String sql = "UPDATE user SET username=?, dob=?, phone_num=?, email=?, address=? " + "WHERE user_id=?";
		try {
			jdbcTemplate.update(sql, user.getUsername(),  user.getDob(), 
					user.getPhone_num(), user.getEmail(), user.getAddress(), user.getUser_id());
			return "Update success";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public User getUserInfo(final String user_id) {
		String sql = "SELECT * FROM user WHERE user_id=?";
		return jdbcTemplate.query(sql, new PreparedStatementSetter() {
		    @Override
		    public void setValues(PreparedStatement ps) throws SQLException {
		        ps.setString(1, user_id);
		    }
		}, new ResultSetExtractor<User>() {
		    @Override
		    public User extractData(ResultSet rs) throws SQLException, DataAccessException {
		        if (rs.next()) {
		            User user = new User();
		            user.setUsername(rs.getString("username"));
		            user.setAddress(rs.getString("address"));
		            user.setDob(rs.getString("dob"));
		            user.setEmail(rs.getString("email"));
		            user.setPhone_num(rs.getString("phone_num"));
		            user.setPassword(rs.getString("password"));
		            return user;
		        }
		        return null;
		    }
		});
	}

	@Override
	public String register(User user) {
		String sql = "INSERT INTO user (user_id, username, dob, phone_num, email, address, password, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			jdbcTemplate.update(sql, user.getUser_id(), user.getUsername(),  user.getDob(), 
					user.getPhone_num(), user.getEmail(), user.getAddress(), user.getPassword(), UserRole.CUSTOMER.ordinal());
			return "Insert success";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public User login(final String email, final String password) {
		String sql = "SELECT username, role, user_id FROM user WHERE email=? and password=?";
		return jdbcTemplate.query(sql, new PreparedStatementSetter() {
		    @Override
		    public void setValues(PreparedStatement ps) throws SQLException {
		        ps.setString(1, email);
		        ps.setString(2, password);
		    }
		}, new ResultSetExtractor<User>() {
		    @Override
		    public User extractData(ResultSet rs) throws SQLException, DataAccessException {
		        if (rs.next()) {
		            User user = new User();
		            user.setUsername(rs.getString("username"));
		            user.role = rs.getInt("role");
		            user.setUser_id(rs.getString("user_id"));
		            return user;
		        }
		        return null;
		    }
		});
	}

	
}
