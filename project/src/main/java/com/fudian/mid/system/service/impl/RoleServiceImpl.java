package com.fudian.mid.system.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fudian.mid.common.service.impl.BaseService;
import com.fudian.mid.common.util.StringUtils;
import com.fudian.mid.system.dao.RoleMapper;
import com.fudian.mid.system.dao.RoleMenuMapper;
import com.fudian.mid.system.domain.Role;
import com.fudian.mid.system.domain.RoleMenu;
import com.fudian.mid.system.domain.RoleWithMenu;
import com.fudian.mid.system.service.RoleMenuServie;
import com.fudian.mid.system.service.RoleService;
import com.fudian.mid.system.service.UserRoleService;
import tk.mybatis.mapper.entity.Example;

@Service("roleService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class RoleServiceImpl extends BaseService<Role> implements RoleService {

	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private RoleMenuMapper roleMenuMapper;

	@Autowired
	private UserRoleService userRoleService;

	@Autowired
	private RoleMenuServie roleMenuService;

	@Override
	public List<Role> findUserRole(String userName) {
		return this.roleMapper.findUserRole(userName);
	}

	@Override
	public List<Role> findAllRole(Role role) {
		Example example = new Example(Role.class);
		if (StringUtils.hasValue(role.getRoleName())) {
			example.createCriteria().andCondition("role_name=", role.getRoleName());
		}
		example.setOrderByClause("create_time");
		return this.selectByExample(example);
	}

	@Override
	public Role findByName(String roleName) {
		Example example = new Example(Role.class);
		example.createCriteria().andCondition("lower(role_name)=", roleName.toLowerCase());
		List<Role> list = this.roleMapper.selectByExample(example);
		if (list.size() == 0) {
			return null;
		} else {
			return list.get(0);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addRole(Role role, Long[] menuIds) {
		role.setRoleId(this.getSequence(Role.SEQ));
		role.setCreateTime(new Date());
		this.save(role);
		for (Long menuId : menuIds) {
			RoleMenu rm = new RoleMenu();
			rm.setMenuId(menuId);
			rm.setRoleId(role.getRoleId());
			this.roleMenuMapper.insert(rm);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteRoles(String roleIds) {
		List<String> list = Arrays.asList(roleIds.split(","));
		this.batchDelete(list, "roleId", Role.class);

		this.roleMenuService.deleteRoleMenusByRoleId(roleIds);
		this.userRoleService.deleteUserRolesByRoleId(roleIds);

	}

	@Override
	public RoleWithMenu findRoleWithMenus(Long roleId) {
		List<RoleWithMenu> list = this.roleMapper.findById(roleId);
		List<Long> menuList = new ArrayList<Long>();
		for (RoleWithMenu rwm : list) {
			menuList.add(rwm.getMenuId());
		}
		if (list.size() == 0) {
			return null;
		}
		RoleWithMenu roleWithMenu = list.get(0);
		roleWithMenu.setMenuIds(menuList);
		return roleWithMenu;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void updateRole(Role role, Long[] menuIds) {
		role.setModifyTime(new Date());
		this.roleMapper.updateByPrimaryKeySelective(role);
		Example example = new Example(RoleMenu.class);
		example.createCriteria().andCondition("role_id=", role.getRoleId());
		this.roleMenuMapper.deleteByExample(example);
		for (Long menuId : menuIds) {
			RoleMenu rm = new RoleMenu();
			rm.setMenuId(menuId);
			rm.setRoleId(role.getRoleId());
			this.roleMenuMapper.insert(rm);
		}
	}

}
