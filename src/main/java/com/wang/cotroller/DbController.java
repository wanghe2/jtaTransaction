package com.wang.cotroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wang.bean.Dept;
import com.wang.bean.User;
import com.wang.service.DeptService;
import com.wang.service.ManyService;
import com.wang.service.UserService;

@RestController
public class DbController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	DeptService deptService;
	
	@Autowired
	ManyService manyService;
	
	@RequestMapping("getUserList")
	public String getAllUser() {
		List<User> users=userService.queryAll();
		StringBuilder userstr=new StringBuilder();
		for(User user: users) {
			userstr.append(user.toString());
			userstr.append("......");
		}
		return userstr.toString();
	}
	
	@RequestMapping("getDeptList")
	public String getDeptList() {
		List<Dept>seckills=deptService.queryAll();
		StringBuilder deptStr=new StringBuilder();
		for(Dept dept: seckills) {
			deptStr.append(dept.toString());
			deptStr.append("......");
		}
		return deptStr.toString();
	}
	
	@RequestMapping("operateDeptDb")
	public String operateMysqlDb() {
		deptService.dbOperateWithShiwu();
		return "完成";
	}
	
	@RequestMapping("operateUserDb")
	public String operateOracleDb() {
		userService.dbOperateWithShiwu();
		return "完成";
	}
	
	@RequestMapping("operateManyDb")
	public String operateManyDb() {
		manyService.operateJtaShiwu();
		return "完成";
	}
}
