package paul.sydney.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;

import paul.sydney.commen.dao.CustomHibernateDaoSupport;
import paul.sydney.commen.dao.IHibernateBaseUtil;
import paul.sydney.commen.dao.impl.HibernateBaseUtil;
import paul.sydney.dao.IWorkItemDao;
import paul.sydney.dao.IWorkNumDao;
import paul.sydney.model.HongXunWorkItem;
import paul.sydney.model.HongXunWorkNum;

@Repository("WorkItemDao")
public class WorkItemDao extends HibernateBaseUtil<HongXunWorkItem, Integer> implements IWorkItemDao{
	@Override
	public List<HongXunWorkItem> quary(HongXunWorkItem hongXunWorkItem) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().findByExample(hongXunWorkItem);
	}

	@Override
	public void save(HongXunWorkItem hongXunWorkItem) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(hongXunWorkItem);
	}

	@Override
	public void update(HongXunWorkItem hongXunWorkItem) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(hongXunWorkItem);
	}

	@Override
	public HongXunWorkItem dataThreeFatherFindById(int ID) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(HongXunWorkItem.class, ID);
	}

	@Override
	public void deletRow(HongXunWorkItem hongXunWorkItem) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(hongXunWorkItem);
	}
	@Override
	public void deleteWorkItemList(List<HongXunWorkItem> hongXunWorkItems) {
		// TODO Auto-generated method stub
		getHibernateTemplate().deleteAll(hongXunWorkItems);
	}

}
