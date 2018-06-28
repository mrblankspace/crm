package cn.swpu.crm.service;

import java.util.List;

import cn.swpu.crm.domain.BaseDict;

public interface BaseDictService {
	public List<BaseDict> findByTypecode(String dict_type_code);
}
