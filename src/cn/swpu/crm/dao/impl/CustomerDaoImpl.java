package cn.swpu.crm.dao.impl;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import cn.swpu.crm.dao.CustomerDao;
import cn.swpu.crm.domain.Customer;

@Transactional
public class CustomerDaoImpl extends HibernateDaoSupport implements CustomerDao{

	@Override
	public void save(Customer customer) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(customer);
	}

}
