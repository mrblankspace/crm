package cn.swpu.crm.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.swpu.crm.domain.LinkMan;

public interface LinkManDao {

	Integer findCount(DetachedCriteria detachedCriteria);

	List<LinkMan> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize);

}
