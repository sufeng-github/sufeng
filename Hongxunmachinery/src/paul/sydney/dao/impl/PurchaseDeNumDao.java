package paul.sydney.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import paul.sydney.commen.dao.impl.HibernateBaseUtil;
import paul.sydney.dao.IPurchaseDeNumDao;
import paul.sydney.model.HongXunPurchaseDeNum;


@Repository("PurchaseDeNumDao")

public class PurchaseDeNumDao extends HibernateBaseUtil<HongXunPurchaseDeNum, Integer> implements IPurchaseDeNumDao{
		
	@Override
	public void save(HongXunPurchaseDeNum hongXunPurchaseDeNum) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(hongXunPurchaseDeNum);
	}
	@Override
	public List<HongXunPurchaseDeNum> quary(HongXunPurchaseDeNum hongXunPurchaseDeNum) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().findByExample(hongXunPurchaseDeNum);
	}
	@Override
	public void update(HongXunPurchaseDeNum item) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(item);
	}
	@Override
	public HongXunPurchaseDeNum purchaseDeNumFindById(int id) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(HongXunPurchaseDeNum.class, id);
	}

	
}
