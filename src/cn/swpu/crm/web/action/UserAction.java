package cn.swpu.crm.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.swpu.crm.domain.User;
import cn.swpu.crm.service.UserService;

/**
 * 用户管理的action类
 * @author zhangbo
 *
 */
@Component
public class UserAction extends ActionSupport implements ModelDriven<User> {
	private User user = new User();
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
		
	}
	
	//spring注入
	@Autowired
	private UserService userService;
	
	//public UserService getUserService() {
	//	return userService;
	//}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 用户注册方法      //调用的具体流程是什么  
	 */
	public String regist(){
		userService.regist(user);
		return "login";
	}
	
	/**
	 * 用户登陆方法
	 */
	
	public String login(){
		User exist = userService.login(user);
		if(exist==null){
			this.addActionError("用户名或者密码错误");
			return "login";
		}else{
			ActionContext.getContext().getSession().put("existUser", exist);
			return "success";
		}
	}

}
