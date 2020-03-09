package paul.sydney.dao.impl;

import java.util.List;

import paul.sydney.commen.dao.IHibernateBaseUtil;
import paul.sydney.commen.dao.impl.HibernateBaseUtil;
import paul.sydney.dao.IProductionItemOutStockDao;
import paul.sydney.model.HongXunAssembleCompilation;
import paul.sydney.model.HongXunDeliveryItem;
import paul.sydney.model.HongXunDeliveryNum;
import paul.sydney.model.HongXunProductionItemInStock;
import paul.sydney.model.HongXunProductionItemOutStock;
import paul.sydney.model.HongXunProductionNoLimitItemInStock;
import paul.sydney.model.HongXunProductionNoLimitItemOutStock;
import paul.sydney.model.HongXunProductionStock;


public class ProductionItemOutStockDao extends HibernateBaseUtil<HongXunProductionItemOutStock, Integer> implements IProductionItemOutStockDao{

	@Override
	public void save(HongXunProductionItemOutStock hongXunProductionItemOutStock) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(hongXunProductionItemOutStock);
	}
	@Override
	public List<HongXunProductionItemOutStock> quary(HongXunProductionItemOutStock hongXunProductionItemOutStock) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().findByExample(hongXunProductionItemOutStock);
	}
	
}
