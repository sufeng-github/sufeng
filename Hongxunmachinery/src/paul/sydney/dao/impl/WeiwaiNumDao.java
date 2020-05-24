package paul.sydney.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import paul.sydney.commen.dao.CustomHibernateDaoSupport;
import paul.sydney.commen.dao.impl.HibernateBaseUtil;
import paul.sydney.dao.IWeiwaiNumDao;
import paul.sydney.model.HongXunWeiwaiItem;
import paul.sydney.model.HongXunWeiwaiNum;


@Repository("WeiwaiNumDao")
public class WeiwaiNumDao extends HibernateBaseUtil<HongXunWeiwaiNum, Integer> implements IWeiwaiNumDao{
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

}
