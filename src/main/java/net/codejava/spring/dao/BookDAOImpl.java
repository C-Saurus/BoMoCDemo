package net.codejava.spring.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import net.codejava.spring.model.Book;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

/**
 * An implementation of the ContactDAO interface.
 * 
 * @author www.codejava.net
 *
 */
public class BookDAOImpl implements BookDAO {

	private JdbcTemplate jdbcTemplate;

	public BookDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void saveOrUpdate(Book book) {
		if (book.getBook_id() != null) {
			// update
			String sql = "UPDATE book SET title=?, price=?, description=?, " + "WHERE bookId=?";
			jdbcTemplate.update(sql, book.getTitle(), book.getPrice(), book.getDescription(), book.getBook_id());
		} else {
			// insert
			String sql = "INSERT INTO contract (name, email, address, telephone)" + " VALUES (?, ?, ?, ?)";
			jdbcTemplate.update(sql, book.getTitle(), book.getPrice(), book.getDescription());
		}

	}

	@Override
	public void delete(int bookId) {
		String sql = "DELETE FROM book WHERE bookId=?";
		jdbcTemplate.update(sql, bookId);
	}

	@Override
	public List<Book> list() {
		String sql = "SELECT * FROM book";
		List<Book> listContact = jdbcTemplate.query(sql, new RowMapper<Book>() {

			@Override
			public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
				Book book = new Book();

				book.setBook_id(rs.getString("book_id"));
				book.setTitle(rs.getString("title"));
				book.setPrice(rs.getInt("price"));
				book.setDescription(rs.getString("description"));

				return book;
			}

		});

		return listContact;
	}

	@Override
	public Book get(final int bookId) {
		String sql = "SELECT * FROM contract WHERE bookId=?";
		return jdbcTemplate.query(sql, new PreparedStatementSetter() {
		    @Override
		    public void setValues(PreparedStatement ps) throws SQLException {
		        ps.setInt(1, bookId);
		    }
		}, new ResultSetExtractor<Book>() {
		    @Override
		    public Book extractData(ResultSet rs) throws SQLException, DataAccessException {
		        if (rs.next()) {
		            Book book = new Book();
		            book.setBook_id(rs.getString("book_id"));
		            book.setTitle(rs.getString("title"));
		            book.setPrice(rs.getInt("price"));
		            book.setDescription(rs.getString("description"));
		            return book;
		        }
		        return null;
		    }
		});
	}

}
