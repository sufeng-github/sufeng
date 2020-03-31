package paul.sydney.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import paul.sydney.commen.dao.impl.HibernateBaseUtil;
import paul.sydney.dao.IProductionDeliveryItemDao;
import paul.sydney.model.HongXunDeliveryItem;


@Repository("ProductionDeliveryItemDao")
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
