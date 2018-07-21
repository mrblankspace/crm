package cn.swpu.crm.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
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

	@Override
	public int getTotalCount(DetachedCriteria detachedCriteria) {
		// TODO Auto-generated method stub
		detachedCriteria.setProjection(Projections.rowCount());
		List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
		if(list.size()>0)
		{
			return list.get(0).intValue();
		}
		return 0;
	}

	@Override
	public List<Customer> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize) {
		// TODO Auto-generated method stub
		detachedCriteria.setProjection(null);
		return (List<Customer>)this.getHibernateTemplate().findByCriteria(detachedCriteria, begin, pageSize);
		
	}
	
	public void modify(Customer customer) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(customer);
	}

	public void delete(Customer customer) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(customer);
	}
	
	
	//更具id查詢客戶
	public Customer findById(Long cust_id) {
		Customer customer = this.getHibernateTemplate().get(Customer.class, cust_id);
		// TODO Auto-generated method stub
		return customer;
	}

	
	public List<Customer> findAll() {
		// TODO Auto-generated method stub
		
		return (List<Customer>) this.getHibernateTemplate().find("from Customer");
	}

}
