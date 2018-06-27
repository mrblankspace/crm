package cn.swpu.crm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.swpu.crm.dao.UserDao;
import cn.swpu.crm.domain.User;
import cn.swpu.crm.service.UserService;
import cn.swpu.crm.utils.MD5Utils;

@Component
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	//实现注册方法
	public void regist(User user){	
		user.setUser_password(MD5Utils.md5(user.getUser_password()));
		user.setUser_state("1");
		userDao.save(user);
	}
	
	//用户登陆实现方法
	public User login(User user){
		return userDao.login(user);
	}
}
