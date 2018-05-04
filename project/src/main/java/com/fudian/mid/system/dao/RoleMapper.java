package com.fudian.mid.system.dao;

import java.util.List;

import com.fudian.mid.common.config.MyMapper;
import com.fudian.mid.system.domain.Role;
import com.fudian.mid.system.domain.RoleWithMenu;

public interface RoleMapper extends MyMapper<Role> {
	
	List<Role> findUserRole(String userName);
	
	List<RoleWithMenu> findById(Long roleId);
}