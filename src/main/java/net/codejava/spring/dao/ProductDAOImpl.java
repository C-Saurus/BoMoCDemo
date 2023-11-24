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
import net.codejava.spring.model.Product;

public class ProductDAOImpl implements ProductDAO {

	private JdbcTemplate jdbcTemplate;

	public ProductDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Product getDetail(final String product_id) {
		String sql = "SELECT * FROM product left join product_category on product.category_id = product_category.category_id WHERE product_id = ?";
		return jdbcTemplate.query(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, product_id);
			}
		}, new ResultSetExtractor<Product>() {
			@Override
			public Product extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Product product = new Product();
					product.setProduct_id(rs.getString("product_id"));
					product.setCategory(rs.getString("category_name"));
					product.setDescription(rs.getString("description"));
					product.setFeedback_id(rs.getInt("feedback_id"));
					product.setPrice(rs.getFloat("price"));
					product.setProduct_name(rs.getString("product_name"));
					product.setQuantity_in_stock(rs.getInt("quantity_in_stock"));
					product.setThumbnail(rs.getString("thumbnail"));
					product.setAuthor_name(rs.getString("author_name"));
					product.setBrand(rs.getString("brand"));
					product.setMaterial_id(rs.getInt("material_id"));
					product.setMemory_size(rs.getFloat("memory_size"));
					product.setPages(rs.getInt("pages"));
					product.setScreen_size(rs.getFloat("screen_size"));
					product.setSize(rs.getString("size"));
					product.setSuitable_age(rs.getString("suitable_age"));
					product.setVote(rs.getInt("vote"));
					return product;
				}
				return null;
			}
		});
	}

	@Override
	public List<Product> getList() {
		String sql = "SELECT thumbnail, quantity_in_stock, product_name, price, feedback_id, description, category_name, product_id  FROM product left join product_category on product.category_id = product_category.category_id";
		List<Product> listProduct = jdbcTemplate.query(sql, new RowMapper<Product>() {

			@Override
			public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
				Product product = new Product();
				product.setProduct_id(rs.getString("product_id"));
				product.setCategory(rs.getString("category_name"));
				product.setDescription(rs.getString("description"));
				product.setFeedback_id(rs.getInt("feedback_id"));
				product.setPrice(rs.getFloat("price"));
				product.setProduct_name(rs.getString("product_name"));
				product.setQuantity_in_stock(rs.getInt("quantity_in_stock"));
				product.setThumbnail(rs.getString("thumbnail"));

				return product;
			}

		});

		return listProduct;
	}

	@Override
	public List<Product> searchProduct(final String search) {
		String sql = "SELECT thumbnail, quantity_in_stock, product_name, price, feedback_id, description, category_name, product_id FROM product join product_category on product.category_id = product_category.category_id where product_name like ?";
		List<Product> listProduct = jdbcTemplate.query(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, "%" + search + "%");
			}
		}, new RowMapper<Product>() {

			@Override
			public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
				Product product = new Product();
				product.setProduct_id(rs.getString("product_id"));
				product.setCategory(rs.getString("category_name"));
				product.setDescription(rs.getString("description"));
				product.setFeedback_id(rs.getInt("feedback_id"));
				product.setPrice(rs.getFloat("price"));
				product.setProduct_name(rs.getString("product_name"));
				product.setQuantity_in_stock(rs.getInt("quantity_in_stock"));
				product.setThumbnail(rs.getString("thumbnail"));

				return product;
			}

		});

		return listProduct;
	}

}
