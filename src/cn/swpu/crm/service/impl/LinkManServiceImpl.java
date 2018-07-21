package cn.swpu.crm.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.swpu.crm.dao.LinkManDao;
import cn.swpu.crm.domain.LinkMan;
import cn.swpu.crm.domain.PageBean;
import cn.swpu.crm.service.LinkManService;

/**
 * 业务层linkmanservice实现类
 * @author zhangbo
 *
 */
@Component
public class LinkManServiceImpl implements LinkManService{
	@Autowired
	private LinkManDao linkManDao;

	@Override
	public PageBean<LinkMan> findAll(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize) {
		// TODO Auto-generated method stub
		PageBean<LinkMan> pageBean = new PageBean<LinkMan>();
		pageBean.setCurrentPage(currPage);
		pageBean.setPageSize(pageSize);
		Integer totalCount = linkManDao.findCount(detachedCriteria);
		pageBean.setTotal(totalCount);
		
		pageBean.setTotalPage( (int)Math.ceil((double)totalCount/pageSize));
		Integer begin=(currPage-1)*pageSize;
		List<LinkMan> list = linkManDao.findByPage(detachedCriteria,begin, pageSize);
		LinkMan linkMan = list.get(0);
		pageBean.setList(list);
		return pageBean;
	}
}
