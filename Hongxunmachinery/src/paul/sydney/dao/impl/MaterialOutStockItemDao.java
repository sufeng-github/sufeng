package paul.sydney.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import paul.sydney.commen.dao.impl.HibernateBaseUtil;
import paul.sydney.dao.IMaterialOutStockItemDao;
import paul.sydney.model.HongXunMaterialOutStoreItem;


@Repository("MaterialOutStockItemDao")
public class MaterialOutStockItemDao extends HibernateBaseUtil<HongXunMaterialOutStoreItem, Integer> implements IMaterialOutStockItemDao {

	

/*	@Override
	public void save(HongXunMaterialOutStoreItem hongXunMaterialOutStoreItem) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(hongXunMaterialOutStoreItem);
	}*/
	@Override
	public List<HongXunMaterialOutStoreItem> quary(HongXunMaterialOutStoreItem hongXunMaterialOutStoreItem) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().findByExample(hongXunMaterialOutStoreItem);
	}
/*	@Override
	public void update(HongXunMaterialOutStoreItem hongXunMaterialOutStoreItem) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(hongXunMaterialOutStoreItem);
	}*/
	@Override
	public void delete(HongXunMaterialOutStoreItem hongXunMaterialOutStoreItem) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(hongXunMaterialOutStoreItem);
	}
/*	@Override
	public HongXunMaterialOutStoreItem materialOutStoreFindById(int id) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(HongXunMaterialOutStoreItem.class, id);
	}
	@Override
	public void deleteOutStoreItemList(List<HongXunMaterialOutStoreItem> hongXunMaterialOutStoreItems) {
		// TODO Auto-generated method stub
		getHibernateTemplate().deleteAll(hongXunMaterialOutStoreItems);
	}*/
	

}
