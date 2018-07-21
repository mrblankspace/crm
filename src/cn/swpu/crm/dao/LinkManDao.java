package cn.swpu.crm.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.swpu.crm.domain.LinkMan;

public interface LinkManDao {
	//查询总记录数
	Integer findCount(DetachedCriteria detachedCriteria);
	//分页查找
	List<LinkMan> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize);
	//保存联系人接口
	void save(LinkMan linkMan);
	
	void delete(LinkMan linkMan);
	LinkMan findById(Long lkmId);
	void update(LinkMan linkMan);   
}
