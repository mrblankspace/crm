package cn.swpu.crm.web.action;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.swpu.crm.domain.Customer;

import cn.swpu.crm.domain.LinkMan;
import cn.swpu.crm.domain.PageBean;
import cn.swpu.crm.service.CustomerService;
import cn.swpu.crm.service.LinkManService;

/**
 * 联系人action
 */
@Component
public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan>{
	private LinkMan linkMan = new LinkMan();
	@Override
	public LinkMan getModel() {
		// TODO Auto-generated method stub
		return linkMan;
	}
	@Autowired
	private LinkManService linkManService;
	@Autowired
	private CustomerService customerService;
	
	private Integer currPage=1;
	private Integer pageSize=3;
	public void setCurrPage(Integer currPage) {
		if(currPage == null){
			currPage = 1;
		}
		this.currPage = currPage;
	}
	public void setPageSize(Integer pageSize) {
		if(pageSize== null){
			pageSize = 3;
		}
		this.pageSize = pageSize;
	}
	
	/**
	 * 展示列表
	 */
	public String findAll(){
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(LinkMan.class);
		PageBean<LinkMan> pageBean = linkManService.findAll(detachedCriteria,currPage, pageSize);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}
	
	/**
	 * 
	 * @return
	 */
	public String saveUI(){
		List<Customer> list = customerService.findAll();
		ActionContext.getContext().getValueStack().set("list", list);
		return "saveUI";
	}
	
	/**
	 * 保存联系人
	 * @return
	 */
	public String save(){
		linkManService.save(linkMan);
		return  "successSave";
	}
	
	/**
	 * 删除联系人
	 */
	public String delete(){
		linkMan = linkManService.findById(linkMan.getLkmId());
		linkManService.delete(linkMan);
		return "successDelete";
	}
	
	public String edit(){
		List<Customer> list = customerService.findAll();
		LinkMan man = linkManService.findById(linkMan.getLkmId());
		ActionContext.getContext().getValueStack().set("list", list);
		ActionContext.getContext().getValueStack().push(man);
		return "successEdit";
	}
	
	public String update(){
		linkManService.update(linkMan);
		return "successUpdate";
	}
}
