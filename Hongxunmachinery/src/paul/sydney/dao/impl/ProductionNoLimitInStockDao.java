package paul.sydney.dao.impl;

import java.util.List;

import paul.sydney.commen.dao.IHibernateBaseUtil;
import paul.sydney.commen.dao.impl.HibernateBaseUtil;
import paul.sydney.dao.IProductionNoLimitInStockDao;
import paul.sydney.model.HongXunAssembleCompilation;
import paul.sydney.model.HongXunDeliveryItem;
import paul.sydney.model.HongXunDeliveryNum;
import paul.sydney.model.HongXunProductionItemInStock;
import paul.sydney.model.HongXunProductionItemOutStock;
import paul.sydney.model.HongXunProductionNoLimitItemInStock;
import paul.sydney.model.HongXunProductionNoLimitItemOutStock;
import paul.sydney.model.HongXunProductionStock;


public class ProductionNoLimitInStockDao extends HibernateBaseUtil<HongXunProductionNoLimitItemInStock, Integer> implements IProductionNoLimitInStockDao {

	@Override
	public List<HongXunProductionNoLimitItemInStock> quary(
			HongXunProductionNoLimitItemInStock hongXunProductionNoLimitItemInStock) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().findByExample(hongXunProductionNoLimitItemInStock);
	}
	@Override
	public void save(HongXunProductionNoLimitItemInStock hongXunProductionNoLimitItemInStock) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(hongXunProductionNoLimitItemInStock);
	}
	
	@Override
	public HongXunProductionNoLimitItemInStock productionNoLimitItemInStockFindById(int idc) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(HongXunProductionNoLimitItemInStock.class, idc);
	}
	@Override
	public void update(HongXunProductionNoLimitItemInStock hongXunProductionNoLimitItemInStock) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(hongXunProductionNoLimitItemInStock);
	}

}
