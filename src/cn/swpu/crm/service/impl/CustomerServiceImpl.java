package cn.swpu.crm.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.swpu.crm.dao.CustomerDao;
import cn.swpu.crm.domain.Customer;
import cn.swpu.crm.domain.PageBean;
import cn.swpu.crm.service.CustomerService;

@Component
public class CustomerServiceImpl implements CustomerService{
	@Autowired
	private CustomerDao customerDao;

	public CustomerDao getCustomerDao() {
		return customerDao;
	}

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	public void save(Customer customer) {
		// TODO Auto-generated method stub
		customerDao.save(customer);
	}

	@Override
	public PageBean<Customer> findByPage(DetachedCriteria detachedCriteria, Integer currentPage, Integer pageSize) {
		PageBean<Customer> pageBean = new PageBean<Customer>();
		pageBean.setCurrentPage(currentPage);
		pageBean.setPageSize(pageSize);
		Integer totalCount = customerDao.getTotalCount(detachedCriteria);
		pageBean.setTotal(totalCount);
		Double tc = totalCount.doubleValue();
		Double num = Math.ceil(tc/pageSize);
		pageBean.setPageSize(num.intValue());
		Integer begin = (currentPage-1)*pageSize;
		List<Customer> list = customerDao.findByPage(detachedCriteria,begin,pageSize);
		pageBean.setList(list);
		//customerDao.
		return pageBean;
	}
	
}
