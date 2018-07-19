package cn.swpu.crm.web.action;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 联系人action
 */
import cn.swpu.crm.domain.LinkMan;
import cn.swpu.crm.domain.PageBean;
import cn.swpu.crm.service.LinkManService;
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
}
