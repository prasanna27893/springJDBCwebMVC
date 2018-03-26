package com.springmvc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.springmvc.dao.UserDao;
import com.springmvc.model.User;

public class UserDaoImpl implements UserDao {

	private JdbcTemplate jdbcTemplate;

	public UserDaoImpl(DataSource dataSource) {

		setJdbcTemplate(new JdbcTemplate(dataSource));
	}

	@Override
	public List<User> allUsers() {

		String sql = "SELECT * FROM userdetail";

		List<User> listUser = jdbcTemplate.query(sql, new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();

				user.setEmail(rs.getString("email"));
				user.setFirstName(rs.getString("firstname"));
				user.setLastName(rs.getString("lastname"));
				user.setId(rs.getInt("id"));

				return user;
			}

		});

		return listUser;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void addUserorUpdate(User user) {

		System.out.println(user.getId());
		if (user.getId() > 0) {
			System.out.println("Entered if loopppppp");
			String sql = "Update user SET firstname = ?, lastname = ?, email=? where id = ?";
			jdbcTemplate.update(sql, user.getFirstName(), user.getLastName(), user.getEmail(), user.getId());

		} else {

			System.out.println("Entered the else block");
			String sql = "Insert Into userdetail(firstname,lastname,email) VALUES (?,?,?)";

			jdbcTemplate.update(sql, user.getFirstName(), user.getLastName(), user.getEmail());
		}
	}

	@Override
	public User getUser(int id) {
		String sql = "Select * from userdetail where id =" + id;
		User specificUser = new User();
		return jdbcTemplate.query(sql, new ResultSetExtractor<User>() {

			@Override
			public User extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {

					specificUser.setFirstName(rs.getString("firstname"));
					specificUser.setLastName(rs.getString("lastname"));
					specificUser.setEmail(rs.getString("email"));
					return specificUser;
				}
				return null;
			}
		});

	}

	@Override
	public void deleteUser(int id) {
		String sql = "Delete from userdetail where id = " + id;
		
		jdbcTemplate.update(sql);
		
	}

}
