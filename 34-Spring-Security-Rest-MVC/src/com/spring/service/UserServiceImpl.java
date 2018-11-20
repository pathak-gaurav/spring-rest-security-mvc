package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.dao.UserServiceDAO;
import com.spring.entity.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserServiceDAO userServiceDAO;

	@Override
	public List<User> getUsers() {
		return userServiceDAO.getUsers();
	}

	@Override
	public void deleteUser(User user) {
		userServiceDAO.deleteUser(user);
	}

}
