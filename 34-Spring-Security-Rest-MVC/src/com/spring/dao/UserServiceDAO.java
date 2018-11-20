package com.spring.dao;

import java.util.List;

import com.spring.entity.User;

public interface UserServiceDAO {

	public List<User> getUsers();

	public void deleteUser(User user);

}
