package paul.sydney.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import paul.sydney.commen.dao.impl.HibernateBaseUtil;
import paul.sydney.dao.IPurchaseDeItemDao;
import paul.sydney.model.HongXunPurchaseDeItem;
import paul.sydney.model.HongXunPurchaseItem;


@Repository("PurchaseDeItemDao")

public class PurchaseDeItemDao extends HibernateBaseUtil<HongXunPurchaseDeItem, Integer> implements IPurchaseDeItemDao{
		
	
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
	@Override
	public void save(HongXunPurchaseDeItem hongXunPurchaseDeItem) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(hongXunPurchaseDeItem);
	}
	@Override
	public List<HongXunPurchaseDeItem> quary(HongXunPurchaseDeItem hongXunPurchaseDeItem) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().findByExample(hongXunPurchaseDeItem);
	}
	@Override
	public void deletePurchaseDeItemList(List<HongXunPurchaseDeItem> hongXunPurchaseDeItems) {
		// TODO Auto-generated method stub
		getHibernateTemplate().deleteAll(hongXunPurchaseDeItems);
	}
	@Override
	public HongXunPurchaseDeItem purchaseDeItemFindById(int id) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(HongXunPurchaseDeItem.class, id);
	}
	@Override
	public void delete(HongXunPurchaseDeItem hongXunPurchaseDeItem) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(hongXunPurchaseDeItem);
	}
	@Override
	public void update(HongXunPurchaseDeItem item) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(item);
	}
	
	
}
