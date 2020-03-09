package paul.sydney.dao.impl;

import java.util.List;

import paul.sydney.commen.dao.IHibernateBaseUtil;
import paul.sydney.commen.dao.impl.HibernateBaseUtil;
import paul.sydney.dao.IProductionDao;
import paul.sydney.dao.IProductionDeliveryItemDao;
import paul.sydney.model.HongXunAssembleCompilation;
import paul.sydney.model.HongXunDeliveryItem;
import paul.sydney.model.HongXunDeliveryNum;
import paul.sydney.model.HongXunProductionItemInStock;
import paul.sydney.model.HongXunProductionItemOutStock;
import paul.sydney.model.HongXunProductionNoLimitItemInStock;
import paul.sydney.model.HongXunProductionNoLimitItemOutStock;
import paul.sydney.model.HongXunProductionStock;


public class ProductionDeliveryItemDao extends HibernateBaseUtil<HongXunDeliveryItem, Integer> implements IProductionDeliveryItemDao{

	@Override
	public void delete(HongXunDeliveryItem hongXunDeliveryItem) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(hongXunDeliveryItem);
	}

	@Override
	public void save(HongXunDeliveryItem item) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(item);
	}
	@Override
	public List<HongXunDeliveryItem> quary(HongXunDeliveryItem hongXunDeliveryItem) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().findByExample(hongXunDeliveryItem);
	}
	@Override
	public void update(HongXunDeliveryItem hongXunDeliveryItem) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(hongXunDeliveryItem);
	}
	@Override
	public HongXunDeliveryItem deliveryItemFindById(int idc) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(HongXunDeliveryItem.class, idc);
	}
	


}
