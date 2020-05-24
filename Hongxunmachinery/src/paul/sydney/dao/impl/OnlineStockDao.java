package paul.sydney.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import paul.sydney.commen.dao.IHibernateBaseUtil;
import paul.sydney.commen.dao.impl.HibernateBaseUtil;
import paul.sydney.dao.IOnlineStockDao;
import paul.sydney.model.HongXunMaterialStock;
import paul.sydney.model.HongXunOnlineStock;
import paul.sydney.model.HongXunSpecialMaterialNum;
import paul.sydney.model.HongXunTablesStatus;
import paul.sydney.model.HongXunWorkItem;
import paul.sydney.model.HongXunWorkNum;
@Repository("OnlineStockDao")
public class OnlineStockDao extends HibernateBaseUtil<HongXunOnlineStock, Integer> implements IOnlineStockDao{
	

	@Override
	public void save(HongXunOnlineStock hongXunOnlineStock) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(hongXunOnlineStock);
	}


	@Override
	public List<HongXunOnlineStock> quary(HongXunOnlineStock hongXunOnlineStock) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().findByExample(hongXunOnlineStock);
	}


	@Override
	public void deleteOnlineItemList(List<HongXunOnlineStock> hongXunOnlineStocks) {
		// TODO Auto-generated method stub
		getHibernateTemplate().deleteAll(hongXunOnlineStocks);
	}


	@Override
	public void update(HongXunOnlineStock hongXunOnlineStock) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(hongXunOnlineStock);
	}

}
