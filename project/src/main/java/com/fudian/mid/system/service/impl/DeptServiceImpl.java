package com.fudian.mid.system.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fudian.mid.common.domain.Tree;
import com.fudian.mid.common.service.impl.BaseService;
import com.fudian.mid.common.util.StringUtils;
import com.fudian.mid.common.util.TreeUtils;
import com.fudian.mid.system.dao.DeptMapper;
import com.fudian.mid.system.domain.Dept;
import com.fudian.mid.system.service.DeptService;
import tk.mybatis.mapper.entity.Example;

@Service("deptService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class DeptServiceImpl extends BaseService<Dept> implements DeptService {

	@Autowired
	private DeptMapper deptMapper;

	@Override
	public Tree<Dept> getDeptTree() {
		List<Tree<Dept>> trees = new ArrayList<Tree<Dept>>();
		List<Dept> depts = this.findAllDepts(new Dept());
		for (Dept dept : depts) {
			Tree<Dept> tree = new Tree<Dept>();
			tree.setId(dept.getDeptId().toString());
			tree.setParentId(dept.getParentId().toString());
			tree.setText(dept.getDeptName());
			trees.add(tree);
		}
		Tree<Dept> t = TreeUtils.build(trees);
		return t;
	}

	@Override
	public List<Dept> findAllDepts(Dept dept) {
		Example example = new Example(Dept.class);
		if(StringUtils.hasValue(dept.getDeptName())){
			example.createCriteria().andCondition("dept_name=", dept.getDeptName());
		}
		example.setOrderByClause("dept_id");
		return this.deptMapper.selectByExample(example);
	}

	@Override
	public Dept findByName(String deptName) {
		Example example = new Example(Dept.class);
		example.createCriteria().andCondition("lower(dept_name) =", deptName.toLowerCase());
		List<Dept> list = this.deptMapper.selectByExample(example);
		if (list.size() == 0) {
			return null;
		} else {
			return list.get(0);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addDept(Dept dept) {
		Long parentId = dept.getParentId();
		if (parentId == null)
			dept.setParentId(0l);
		dept.setDeptId(this.getSequence(Dept.SEQ));
		dept.setCreateTime(new Date());
		this.deptMapper.insert(dept);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteDepts(String deptIds) {
		List<String> list = Arrays.asList(deptIds.split(","));
		this.batchDelete(list, "deptId", Dept.class);
		this.deptMapper.changeToTop(list);
	}

	@Override
	public Dept findById(Long deptId) {
		return this.deptMapper.selectByPrimaryKey(deptId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void updateDept(Dept dept) {
		this.deptMapper.updateByPrimaryKeySelective(dept);
	}

}
