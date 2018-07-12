package cn.swpu.crm.web.action;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.swpu.crm.domain.Customer;
import cn.swpu.crm.domain.PageBean;
import cn.swpu.crm.service.CustomerService;

/**
 * 客户管理的action类
 * @author zhangbo
 *
 */
@Component
public class CustomerAction extends ActionSupport implements ModelDriven<Customer>{
	private Customer customer = new Customer();
	@Override
	public Customer getModel() {
		// TODO Auto-generated method stub
		return customer;
	}
	
	//注入service
	@Autowired
	private CustomerService customerService;
	
	public CustomerService getCustomerService() {
		return customerService;
	}
	
	//当前页
	private Integer currentPage = 1;

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public void setCurrentPage(Integer currentPage) {
		if(currentPage==null){
			currentPage=1;
		}
		this.currentPage = currentPage;
	}
	

	//页面记录数
	private Integer pageSize = 5;
	

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	public void save(){
		customerService.save(customer);
	}
	
	//查询currentPage页面    
	public String findAll(){
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class); 
		PageBean<Customer> pageBean = customerService.findByPage(detachedCriteria,currentPage,pageSize);
		ActionContext.getContext().getValueStack().push(pageBean);//值栈？？？
		return "findAll";	
	}
	
	
}
