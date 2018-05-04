package com.fudian.mid.system.service;

import com.fudian.mid.common.service.IService;
import com.fudian.mid.system.domain.RoleMenu;

public interface RoleMenuServie extends IService<RoleMenu> {

	void deleteRoleMenusByRoleId(String roleIds);

	void deleteRoleMenusByMenuId(String menuIds);
}
