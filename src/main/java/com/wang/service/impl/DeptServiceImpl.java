package com.wang.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wang.bean.Dept;
import com.wang.dao.dept.DeptDao;
import com.wang.service.DeptService;

@Service("deptService")
public class DeptServiceImpl implements DeptService{
	
	@Autowired
	private DeptDao deptDao;

	@Override
	public List<Dept> queryAll() {
		List<Dept>depts= deptDao.queryAll();
		return depts;
	}

	@Override
	@Transactional
	public void dbOperateWithShiwu() {
		update();
		insert();
	}
	@Override
	public void insert() {
		Dept dept=new Dept();
		dept.setId(1);
		dept.setDeptName("管理一号");
		deptDao.insertDept(dept);
	}
	@Override
	public void update() {
		Dept dept=deptDao.queryById(1);
		dept.setDeptName(dept.getDeptName()+"-修改");
		deptDao.updateDept(dept);
	}
	
}
