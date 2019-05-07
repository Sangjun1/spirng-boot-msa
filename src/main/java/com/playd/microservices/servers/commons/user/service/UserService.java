package com.playd.microservices.servers.commons.user.service;

import com.playd.microservices.servers.commons.user.mapper.UserMapper;
import com.playd.microservices.servers.commons.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;

	@Transactional
	public int insertUser(User user) throws Exception {
		return userMapper.insertUser(user);
	}

}
