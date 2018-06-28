package cn.swpu.crm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.swpu.crm.dao.CustomerDao;
import cn.swpu.crm.domain.Customer;
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
	
}
