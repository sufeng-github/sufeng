package paul.sydney.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import paul.sydney.commen.dao.IHibernateBaseUtil;
import paul.sydney.commen.dao.impl.HibernateBaseUtil;
import paul.sydney.dao.IWeldDeliveryItemDao;
import paul.sydney.model.HongXunDeliveryWeldItem;
import paul.sydney.model.HongXunDeliveryWeldNum;
import paul.sydney.model.HongXunMaterialStock;
import paul.sydney.model.HongXunProductionWeldStock;
import paul.sydney.model.HongXunWeldCompilation;
import paul.sydney.model.HongXunWeldItemInStock;
import paul.sydney.model.HongXunWeldItemOutStock;
import paul.sydney.model.HongXunWeldNoLimitItemInStock;
import paul.sydney.model.HongXunWeldNoLimitItemOutStock;
@Repository("WeldDeliveryItemDao")
public class WeldDeliveryItemDao extends HibernateBaseUtil<HongXunDeliveryWeldItem, Integer> implements IWeldDeliveryItemDao{
	
	@Override
	public void update(HongXunDeliveryWeldItem hongXunDeliveryWeldItem) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(hongXunDeliveryWeldItem);
	}
	@Override
	public HongXunDeliveryWeldItem deliveryWeldItemFindById(int idc) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(HongXunDeliveryWeldItem.class, idc);
	}
	@Override
	public void delete(HongXunDeliveryWeldItem hongXunDeliveryWeldItem) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(hongXunDeliveryWeldItem);
	}
	@Override
	public List<HongXunDeliveryWeldItem> quary(HongXunDeliveryWeldItem hongXunDeliveryWeldItem) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().findByExample(hongXunDeliveryWeldItem);
	}
	@Override
	public void save(HongXunDeliveryWeldItem hongXunDeliveryWeldItem) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(hongXunDeliveryWeldItem);
	}
	
}
