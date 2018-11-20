package com.spring.service;

import java.util.List;

import com.spring.entity.User;

public interface UserService {
	
	public List<User> getUsers();
	
	public void deleteUser(User user);

}
