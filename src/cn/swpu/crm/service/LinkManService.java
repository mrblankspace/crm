package cn.swpu.crm.service;

import org.hibernate.criterion.DetachedCriteria;

import cn.swpu.crm.domain.LinkMan;
import cn.swpu.crm.domain.PageBean;

public interface LinkManService {

	PageBean<LinkMan> findAll(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);

	void save(LinkMan linkMan);

	void delete(LinkMan linkMan);


	LinkMan findById(Long lkmId);

	void update(LinkMan linkMan);
}
