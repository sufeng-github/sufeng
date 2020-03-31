package paul.sydney.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;

import paul.sydney.commen.dao.CustomHibernateDaoSupport;
import paul.sydney.commen.dao.impl.HibernateBaseUtil;
import paul.sydney.dao.IPurchaseDao;
import paul.sydney.dao.IPurchaseItemInStockDao;
import paul.sydney.model.HongXunMaterialStock;
import paul.sydney.model.HongXunPurchaseDeItem;
import paul.sydney.model.HongXunPurchaseDeNum;
import paul.sydney.model.HongXunPurchaseItem;
import paul.sydney.model.HongXunPurchaseItemInStock;
import paul.sydney.model.HongXunPurchaseNum;

@Repository("PurchaseItemInStockDao")

public class PurchaseItemInStockDao extends HibernateBaseUtil<HongXunPurchaseItemInStock, Integer> implements IPurchaseItemInStockDao{
		
	@Override
	public void save(HongXunPurchaseItemInStock hongXunDataTwoFather) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(hongXunDataTwoFather);
	}
	@Override
	public List<HongXunPurchaseItemInStock> quary(HongXunPurchaseItemInStock hongXunDataTwoFather) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().findByExample(hongXunDataTwoFather);
	}
	@Override
	public HongXunPurchaseItemInStock purchaseItemInStockFindById(int ID) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(HongXunPurchaseItemInStock.class, ID);
	}
	@Override
	public void update(HongXunPurchaseItemInStock hongXunDataTwoFather) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(hongXunDataTwoFather);
	}

	
}
