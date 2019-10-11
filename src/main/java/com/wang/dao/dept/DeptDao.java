package com.wang.dao.dept;

import java.util.List;

import com.wang.bean.Dept;

public interface DeptDao {
	public List<Dept> queryAll();
	
	void insertDept(Dept dept);
	
	Dept queryById(long id);
	
	void updateDept(Dept dept);
}
