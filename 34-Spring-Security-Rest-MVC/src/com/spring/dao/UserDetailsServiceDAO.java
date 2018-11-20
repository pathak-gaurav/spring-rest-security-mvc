package com.spring.dao;

import com.spring.entity.User;

public interface UserDetailsServiceDAO {
	
	public User getUser(String username);
}
