package paul.sydney.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;

import paul.sydney.commen.dao.CustomHibernateDaoSupport;
import paul.sydney.commen.dao.IHibernateBaseUtil;
import paul.sydney.commen.dao.impl.HibernateBaseUtil;
import paul.sydney.dao.IWorkNumDao;
import paul.sydney.model.HongXunWorkItem;
import paul.sydney.model.HongXunWorkNum;

@Repository("WorkNumDao")
public class WorkNumDao extends HibernateBaseUtil<HongXunWorkNum, Integer> implements IWorkNumDao{
	
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
