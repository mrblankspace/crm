package cn.swpu.crm.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.swpu.crm.dao.UserDao;
import cn.swpu.crm.domain.User;
import cn.swpu.crm.utils.MD5Utils;
/**
 * 用户管理实现类
 * @author zhangbo
 *
 */
@Transactional
public class UserDaoImpl extends HibernateDaoSupport implements UserDao{

	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(user);
	} 
	
	public User login(User user){
		List<User> list = (List<User>) this.getHibernateTemplate().find("from User where user_code=? and user_password"
				+ "=?", user.getUser_code(),MD5Utils.md5(user.getUser_password()));
			if(list.size()>0){
				return list.get(0);
			}
		return null;
	}
}
