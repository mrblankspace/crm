package cn.swpu.crm.web.action;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.swpu.crm.domain.BaseDict;
import cn.swpu.crm.service.BaseDictService;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.JSONUtils;
@Component
public class BaseDictAction extends ActionSupport implements ModelDriven{
	//模型驱动使用的对象
	private BaseDict baseDict = new BaseDict();
	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		return baseDict;
	}
	
	//注入service
	@Autowired
	private BaseDictService baseDictService;
	
	public String findByTypeCode(){
		List<BaseDict> list = baseDictService.findByTypecode(baseDict.getDict_type_code());
		//list转成json jsonlib fastjson
		/**
		 * JsonArray 数组和list
		 * Jsonobject 对象和map
		 * JSONConfig 转json的配置对象
		 */
		JsonConfig jsonConfig = new JsonConfig();
		//去除不必掉的参数
		jsonConfig.setExcludes(new String[]{"dict_sort","dict_enable","dict_memo"});
		JSONArray jsonArray = JSONArray.fromObject(list,jsonConfig);
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
