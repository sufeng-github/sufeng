package paul.sydney.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import paul.sydney.commen.dao.impl.HibernateBaseUtil;
import paul.sydney.dao.IWeiwaiItemDao;
import paul.sydney.model.HongXunWeiwaiItem;


@Repository("WeiwaiItemDao")
public class WeiwaiItemDao extends HibernateBaseUtil<HongXunWeiwaiItem, Integer> implements IWeiwaiItemDao{

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
