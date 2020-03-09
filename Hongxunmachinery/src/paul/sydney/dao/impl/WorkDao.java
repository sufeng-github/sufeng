package paul.sydney.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;

import paul.sydney.commen.dao.CustomHibernateDaoSupport;
import paul.sydney.commen.dao.IHibernateBaseUtil;
import paul.sydney.commen.dao.impl.HibernateBaseUtil;
import paul.sydney.dao.IWorkDao;
import paul.sydney.model.HongXunWorkItem;
import paul.sydney.model.HongXunWorkNum;

@Repository("WorkDao")
public class WorkDao extends HibernateBaseUtil<HongXunWorkNum, Integer> implements IWorkDao{
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
	@Override
	public List<HongXunWorkNum> quary(HongXunWorkNum hongXunWorkNum) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().findByExample(hongXunWorkNum);
	}

	@Override
	public void save(HongXunWorkNum hongXunWorkNum) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(hongXunWorkNum);
	}

	@Override
	public void deletRow(HongXunWorkNum hongXunWorkNum) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(hongXunWorkNum);
	}

	@Override
	public void update(HongXunWorkNum hongXunWorkNum) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(hongXunWorkNum);
	}
	@Override
	public HongXunWorkNum workNumFindById(int ID) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(HongXunWorkNum.class, ID);
	}
}
