package com.springmvc.dao;

import java.util.List;

import com.springmvc.model.User;

public interface UserDao {

	public List<User> allUsers();
	
	public void addUserorUpdate(User user);
	
	
	public User getUser(int id);
	
	public void deleteUser(int id);
	
}
