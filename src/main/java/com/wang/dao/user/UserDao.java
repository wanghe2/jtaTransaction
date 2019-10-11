package com.wang.dao.user;

import java.util.List;

import com.wang.bean.User;

public interface UserDao {
	public List<User> queryAll();
	
	void insertUser(User user);
	
	User queryById(long id);
	
	void updateUser(User user);
}
