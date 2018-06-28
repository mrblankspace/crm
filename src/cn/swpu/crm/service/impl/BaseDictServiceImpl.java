package cn.swpu.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.swpu.crm.dao.BaseDictDao;
import cn.swpu.crm.domain.BaseDict;
import cn.swpu.crm.service.BaseDictService;
/**
 * 字典业务层实现类
 * @author zhangbo
 *
 */
@Component
public class BaseDictServiceImpl implements BaseDictService{
	@Autowired
	private BaseDictDao baseDictDao;
	@Override
	public List<BaseDict> findByTypecode(String dict_type_code) {
		// TODO Auto-generated method stub
		return (List<BaseDict>) baseDictDao.findByTypecode(dict_type_code);
	}
}
