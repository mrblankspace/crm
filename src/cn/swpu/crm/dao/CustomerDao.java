package cn.swpu.crm.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.swpu.crm.domain.Customer;


public interface CustomerDao {
	public void save(Customer customer);

	public int getTotalCount(DetachedCriteria detachedCriteria);

	public List<Customer> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize);

	public void modify(Customer customer);

	public void delete(Customer customer);

	public Customer findById(Long cust_id);
} 
