package com.wang.service;

import java.util.List;

import com.wang.bean.Dept;

public interface DeptService {
	List<Dept> queryAll();
	void dbOperateWithShiwu();
	void insert();
	void update();
}
