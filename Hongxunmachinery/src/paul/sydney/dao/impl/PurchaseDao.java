package paul.sydney.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;

import paul.sydney.commen.dao.CustomHibernateDaoSupport;
import paul.sydney.commen.dao.impl.HibernateBaseUtil;
import paul.sydney.dao.IPurchaseDao;
import paul.sydney.model.HongXunMaterialStock;
import paul.sydney.model.HongXunPurchaseDeItem;
import paul.sydney.model.HongXunPurchaseDeNum;
import paul.sydney.model.HongXunPurchaseItem;
import paul.sydney.model.HongXunPurchaseItemInStock;
import paul.sydney.model.HongXunPurchaseNum;

@Repository("PurchaseDao")

public class PurchaseDao extends HibernateBaseUtil<HongXunPurchaseNum, Integer> implements IPurchaseDao{
	@Override
	public void update(HongXunPurchaseNum hongXunPurchaseNum) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(hongXunPurchaseNum);
	}
	@Override
	public void save(HongXunPurchaseNum hongXunPurchaseNum) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(hongXunPurchaseNum);
	}
	@Override
	public HongXunPurchaseNum purchaseNumFindById(int ID) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(HongXunPurchaseNum.class, ID);
	}
	@Override
	public List<HongXunPurchaseNum> quary(HongXunPurchaseNum hongXunPurchaseNum) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().findByExample(hongXunPurchaseNum);
	}
	@Override
	public HongXunPurchaseNum oneDataFindById(int id) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(HongXunPurchaseNum.class, id);
	}
	@Override
	public void deletRow(HongXunPurchaseNum hongXunPurchaseNum) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(hongXunPurchaseNum);
	}
	
	
}
