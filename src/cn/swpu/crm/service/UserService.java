package cn.swpu.crm.service;

import cn.swpu.crm.domain.User;

public interface UserService {
	/**
	 * 注册方法
	 * @param user
	 */
	public void regist(User user);
	
	/*
	 * 登陆方法
	 */
	
	public User login(User user);
}
