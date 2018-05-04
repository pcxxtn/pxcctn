package com.fudian.mid.system.service;

import java.util.List;

import com.fudian.mid.common.domain.Tree;
import com.fudian.mid.common.service.IService;
import com.fudian.mid.system.domain.Dept;

public interface DeptService extends IService<Dept> {

	Tree<Dept> getDeptTree();

	List<Dept> findAllDepts(Dept dept);

	Dept findByName(String deptName);

	Dept findById(Long deptId);
	
	void addDept(Dept dept);
	
	void updateDept(Dept dept);

	void deleteDepts(String deptIds);
}
