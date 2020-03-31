package paul.sydney.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import paul.sydney.commen.dao.impl.HibernateBaseUtil;
import paul.sydney.dao.IPurchaseDeItemDao;
import paul.sydney.dao.IPurchaseItemDao;
import paul.sydney.model.HongXunPurchaseDeItem;
import paul.sydney.model.HongXunPurchaseItem;


@Repository("PurchaseItemDao")

public class PurchaseItemDao extends HibernateBaseUtil<HongXunPurchaseItem, Integer> implements IPurchaseItemDao{
		
	
	@Override
	public List<HongXunPurchaseItem> quary(HongXunPurchaseItem hongXunDataTwo) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().findByExample(hongXunDataTwo);
	}
	@Override
	public void save(HongXunPurchaseItem hongXunDataTwo) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(hongXunDataTwo);
	}
	@Override
	public void deletRow(HongXunPurchaseItem hongXunDataTwo) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(hongXunDataTwo);
	}
	@Override
	public void update(HongXunPurchaseItem hongXunDataTwo) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(hongXunDataTwo);
	}
	@Override
	public HongXunPurchaseItem purchaseItemFindById(int ID) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(HongXunPurchaseItem.class, ID);
	}	
	
}
