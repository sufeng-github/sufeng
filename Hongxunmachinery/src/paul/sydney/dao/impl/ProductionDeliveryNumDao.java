package paul.sydney.dao.impl;

import java.util.List;

import paul.sydney.commen.dao.IHibernateBaseUtil;
import paul.sydney.commen.dao.impl.HibernateBaseUtil;
import paul.sydney.dao.IProductionDeliveryNumDao;
import paul.sydney.model.HongXunAssembleCompilation;
import paul.sydney.model.HongXunDeliveryItem;
import paul.sydney.model.HongXunDeliveryNum;
import paul.sydney.model.HongXunProductionItemInStock;
import paul.sydney.model.HongXunProductionItemOutStock;
import paul.sydney.model.HongXunProductionNoLimitItemInStock;
import paul.sydney.model.HongXunProductionNoLimitItemOutStock;
import paul.sydney.model.HongXunProductionStock;


public class ProductionDeliveryNumDao extends HibernateBaseUtil<HongXunDeliveryNum, Integer> implements IProductionDeliveryNumDao{


	@Override
	public List<HongXunDeliveryNum> quary(HongXunDeliveryNum hongXunDeliveryNum) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().findByExample(hongXunDeliveryNum);
	}
	@Override
	public void save(HongXunDeliveryNum hongXunDeliveryNum) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(hongXunDeliveryNum);
	}
	@Override
	public void update(HongXunDeliveryNum hongXunDeliveryNum) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(hongXunDeliveryNum);
	}
	@Override
	public HongXunDeliveryNum deliveryNumFindById(int id) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(HongXunDeliveryNum.class, id);
	}
	@Override
	public void delete(HongXunDeliveryNum hongXunDeliveryNum) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(hongXunDeliveryNum);
	}



}
