package com.wang.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wang.service.DeptService;
import com.wang.service.ManyService;
import com.wang.service.UserService;
@Service("manyService")
public class ManyServiceImpl implements ManyService{

	@Autowired
	private UserService userService;

	@Autowired
	private DeptService deptService;
	
	@Override
	@Transactional
	public void operateJtaShiwu() {
		userService.update();//这个是正常执行
		deptService.dbOperateWithShiwu();//这里会有异常，观察user的操作是否会回滚
	}

}
