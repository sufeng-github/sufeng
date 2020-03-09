package paul.sydney.dao.impl;

import java.util.List;

import paul.sydney.commen.dao.IHibernateBaseUtil;
import paul.sydney.commen.dao.impl.HibernateBaseUtil;
import paul.sydney.dao.IProductionNoLimitOutStockDao;
import paul.sydney.model.HongXunAssembleCompilation;
import paul.sydney.model.HongXunDeliveryItem;
import paul.sydney.model.HongXunDeliveryNum;
import paul.sydney.model.HongXunProductionItemInStock;
import paul.sydney.model.HongXunProductionItemOutStock;
import paul.sydney.model.HongXunProductionNoLimitItemInStock;
import paul.sydney.model.HongXunProductionNoLimitItemOutStock;
import paul.sydney.model.HongXunProductionStock;


public class ProductionNoLimitOutStockDao extends HibernateBaseUtil<HongXunProductionNoLimitItemOutStock, Integer> implements IProductionNoLimitOutStockDao{

	@Override
	public void save(HongXunProductionNoLimitItemOutStock hongXunProductionNoLimitItemOutStock) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(hongXunProductionNoLimitItemOutStock);
	}
	@Override
	public List<HongXunProductionNoLimitItemOutStock> quary(HongXunProductionNoLimitItemOutStock hongXunProductionNoLimitItemOutStock) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().findByExample(hongXunProductionNoLimitItemOutStock);
	}
	@Override
	public HongXunProductionNoLimitItemOutStock productionNoLimitItemOutStockFindById(int idc) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(HongXunProductionNoLimitItemOutStock.class, idc);
	}
	@Override
	public void update(HongXunProductionNoLimitItemOutStock hongXunProductionNoLimitItemOutStock) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(hongXunProductionNoLimitItemOutStock);
	}
	
}
