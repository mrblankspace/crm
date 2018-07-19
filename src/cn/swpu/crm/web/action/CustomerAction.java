package cn.swpu.crm.web.action;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.swpu.crm.domain.BaseDict;
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
		//和查询逻辑一块写这里了
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class); 
		if(customer.getCust_name()!=null&&!"".equals(customer.getCust_name())){
			detachedCriteria.add(Restrictions.like("cust_name", "%"+customer.getCust_name()+"%"));
		}
		if(customer.getBaseDictIndustry()!=null&&!"".equals(customer.getBaseDictIndustry().getDict_id())){
			  BaseDict baseDictIndustry = customer.getBaseDictIndustry();
			detachedCriteria.add(Restrictions.eq("baseDictIndustry.dict_id",baseDictIndustry.getDict_id()));
		}
		if(customer.getBaseDictLevel()!=null&&!"".equals(customer.getBaseDictLevel().getDict_id())){
			detachedCriteria.add(Restrictions.eq("baseDictLevel.dict_id",customer.getBaseDictLevel().getDict_id()));
		}
		if(customer.getBaseDictSource()!=null&&!"".equals(customer.getBaseDictSource().getDict_id())){
			detachedCriteria.add(Restrictions.eq("baseDictSource.dict_id",customer.getBaseDictSource().getDict_id()));
		}
		PageBean<Customer> pageBean = customerService.findByPage(detachedCriteria,currentPage,pageSize);
		ActionContext.getContext().getValueStack().push(pageBean);//值栈？？？
		return "findAll";	
	}
	

	//删除customer
	public String delete(){
		//先查詢，再刪除
		customer = customerService.findById(customer.getCust_id());
		if(customer.getCust_image()!=null){
			File file = new File(customer.getCust_image());
			if(file.exists()){
				file.delete();
			}
		}
		customerService.delete(customer);
		
		return "deleteSuccess";
	}
	//修改方法
	public String edit(){
		//先查询
		customer = customerService.findById(customer.getCust_id());  //customer已经在值栈中了\
		ActionContext.getContext().getValueStack().push(customer);
		return "editSuccess";
	}
	
	/**
	 * 修改客户   
	 * 更改图片  原来有则替换
	 * @return
	 * @throws IOException 
	 */
	public String update() throws IOException{
		if(upload!=null){
			String origin_url = customer.getCust_image();
			if(origin_url!=null){
				File delete =  new File(origin_url);
				delete.delete();
			}
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
		customerService.modify(customer);
		return "updateSuccess";
	}
	
	
}
