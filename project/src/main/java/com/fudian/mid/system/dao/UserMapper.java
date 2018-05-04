package com.fudian.mid.system.dao;

import java.util.List;

import com.fudian.mid.common.config.MyMapper;
import com.fudian.mid.system.domain.User;
import com.fudian.mid.system.domain.UserWithRole;

public interface UserMapper extends MyMapper<User> {

	List<User> findUserWithDept(User user);
	
	List<UserWithRole> findUserWithRole(Long userId);
	
	User findUserProfile(User user);
}