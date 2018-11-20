package com.spring.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.entity.Authority;
import com.spring.entity.User;

@Repository
public class UserServiceDAOImpl implements UserServiceDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<User> getUsers() {
		Session session = sessionFactory.getCurrentSession();
		List<User> list = session.createQuery("from User",User.class).getResultList();
		if(list.isEmpty()){
			User user = new User("zack","zack","true");
			Authority au = new Authority("ADMIN");
			session.save(user);
			user.addAuthority(au);
			session.save(au);
		}
		return list;
	}

	@Override
	public void deleteUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(user);
	}

}
