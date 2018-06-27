package cn.swpu.crm.dao;

import cn.swpu.crm.domain.User;

/**
 * 用户管理接口
 * @author zhangbo
 *
 */
public interface UserDao {
	
	//存
	public void save(User user);
	
	//登陆
	public User login(User user);

}
