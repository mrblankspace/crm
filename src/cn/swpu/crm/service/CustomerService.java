package cn.swpu.crm.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.swpu.crm.domain.Customer;
import cn.swpu.crm.domain.LinkMan;
import cn.swpu.crm.domain.PageBean;


public interface CustomerService {
	public void save(Customer customer);

	public PageBean<Customer> findByPage(DetachedCriteria detachedCriteria, Integer currentPage, Integer pageSize);

	public void modify(Customer customer);

	public void delete(Customer customer);

	public Customer findById(Long cust_id);

	public List<Customer> findAll();


	//public void update(Customer customer);
}
