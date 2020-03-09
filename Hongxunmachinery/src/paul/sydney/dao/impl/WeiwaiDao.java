package paul.sydney.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import paul.sydney.commen.dao.CustomHibernateDaoSupport;
import paul.sydney.dao.IWeiwaiDao;
import paul.sydney.model.HongXunWeiwaiItem;
import paul.sydney.model.HongXunWeiwaiNum;


@Repository("WeiwaiDao")
public class WeiwaiDao extends CustomHibernateDaoSupport implements IWeiwaiDao{
	@Override
	public void save(HongXunWeiwaiNum hongXunDataFour) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(hongXunDataFour);
	}
	@Override
	public void update(HongXunWeiwaiNum hongXunDataFour) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(hongXunDataFour);
	}
	@Override
	public List<HongXunWeiwaiNum> quary(HongXunWeiwaiNum hongXunDataFour) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().findByExample(hongXunDataFour);
	}
	@Override
	public HongXunWeiwaiNum weiwaiNumFindById(int ID) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(HongXunWeiwaiNum.class, ID);
	}
	@Override
	public void delete(HongXunWeiwaiNum hongXunWeiwaiNum) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(hongXunWeiwaiNum);
	}
	@Override
	public void deleteWeiwaiNumList(List<HongXunWeiwaiNum> hongXunWeiwaiNumList) {
		// TODO Auto-generated method stub
		getHibernateTemplate().deleteAll(hongXunWeiwaiNumList);
	}

	@Override
	public List<HongXunWeiwaiItem> quary(HongXunWeiwaiItem hongXunDataFive) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().findByExample(hongXunDataFive);
	}
	@Override
	public void save(HongXunWeiwaiItem hongXunDataFive) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(hongXunDataFive);
	}
	@Override
	public HongXunWeiwaiItem dataFiveFindById(int ID) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(HongXunWeiwaiItem.class, ID);
	}
	@Override
	public void deletRow(HongXunWeiwaiItem hongXunDataFive) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(hongXunDataFive);
	}
	@Override
	public void update(HongXunWeiwaiItem hongXunDataFive) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(hongXunDataFive);
	}
	@Override
	public void deleteWeiwaiItemList(List<HongXunWeiwaiItem> hongXunWeiwaiItems) {
		// TODO Auto-generated method stub
		getHibernateTemplate().deleteAll(hongXunWeiwaiItems);
	}


}
