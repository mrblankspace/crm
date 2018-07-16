package cn.swpu.crm.web.action;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.swpu.crm.domain.Customer;
import cn.swpu.crm.domain.PageBean;
import cn.swpu.crm.service.CustomerService;
import cn.swpu.crm.utils.UploadUtils;

/**
 * 客户管理的action类
 * @author zhangbo
 *
 */
@Component
public class CustomerAction extends ActionSupport implements ModelDriven<Customer>{
	//文件上传属性
	private String uploadFileName;
	private File upload;
	private String uploadContentType;
	
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

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
	private Integer currentPage=1;

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
	private Integer pageSize=5;
	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	public String save() throws IOException{
		//上传图片
		if(upload!=null){
			String path="F:/upload";
			String uuidFileName = UploadUtils.getUuidFileName(uploadFileName);
			String dir = UploadUtils.getPath(uuidFileName);
			String url = path+dir;
			File file = new File(url);
			if(!file.exists())
			{
				file.mkdirs();
			}
			File dictFile = new File(url+"/"+uuidFileName);
			FileUtils.copyFile(upload, dictFile);
			customer.setCust_image(url+"/"+uuidFileName);
		}
		
		customerService.save(customer);
		return "saveSuccess";
	}
	
	//查询currentPage页面    
	public String findAll(){
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class); 
		PageBean<Customer> pageBean = customerService.findByPage(detachedCriteria,currentPage,pageSize);
		ActionContext.getContext().getValueStack().push(pageBean);//值栈？？？
		return "findAll";	
	}
	
	//修改customer
	public void modify(){
		customerService.modify(customer);
	}

	//删除customer
	public String delete(){
		customerService.delete(customer);
		findAll();
		return "findAll";
	}
	
	
	
	
}
