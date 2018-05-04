package com.fudian.mid.system.service;

import com.fudian.mid.common.service.IService;
import com.fudian.mid.system.domain.UserRole;

public interface UserRoleService extends IService<UserRole> {

	void deleteUserRolesByRoleId(String roleIds);

	void deleteUserRolesByUserId(String userIds);
}
