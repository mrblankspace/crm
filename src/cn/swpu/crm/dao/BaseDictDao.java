package cn.swpu.crm.dao;

import java.util.List;

import cn.swpu.crm.domain.BaseDict;

public interface BaseDictDao {
	public List<BaseDict> findByTypecode(String dict_type_code);
}
