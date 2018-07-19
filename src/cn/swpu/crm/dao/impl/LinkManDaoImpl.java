package cn.swpu.crm.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import cn.swpu.crm.dao.LinkManDao;
import cn.swpu.crm.domain.LinkMan;

/**
 * 联系人dao实现类
 * @author zhangbo
 *
 */
@Transactional
public class LinkManDaoImpl  extends HibernateDaoSupport implements LinkManDao {

	@Override
	public Integer findCount(DetachedCriteria detachedCriteria) {
		// TODO Auto-generated method stub
		detachedCriteria.setProjection(Projections.rowCount());
		List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
		if(list.size()>0){
			return list.get(0).intValue();
		}
		return null;
	}
	
	
	/**
	 * 分页查询
	 */
	@Override
	public List<LinkMan> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize) {
		// TODO Auto-generated method stub
		detachedCriteria.setProjection(null);
		List<LinkMan> list = (List<LinkMan>) this.getHibernateTemplate().findByCriteria(detachedCriteria,begin,pageSize);
		return list;
	}
	
}
