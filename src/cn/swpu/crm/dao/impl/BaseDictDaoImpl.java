package cn.swpu.crm.dao.impl;

import java.awt.event.HierarchyEvent;
import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import cn.swpu.crm.dao.BaseDictDao;
import cn.swpu.crm.domain.BaseDict;


@Transactional
public class BaseDictDaoImpl extends HibernateDaoSupport implements BaseDictDao{
	@Override
	public List<BaseDict> findByTypecode(String dict_type_code) {
		return (List<BaseDict>) getHibernateTemplate().find(""
				+ "from BaseDict where dict_type_code=?", dict_type_code);
	}
}
