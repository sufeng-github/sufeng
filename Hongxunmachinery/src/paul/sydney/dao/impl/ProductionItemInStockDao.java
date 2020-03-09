package paul.sydney.dao.impl;

import java.util.List;

import paul.sydney.commen.dao.IHibernateBaseUtil;
import paul.sydney.commen.dao.impl.HibernateBaseUtil;
import paul.sydney.dao.IProductionItemInStockDao;
import paul.sydney.model.HongXunAssembleCompilation;
import paul.sydney.model.HongXunDeliveryItem;
import paul.sydney.model.HongXunDeliveryNum;
import paul.sydney.model.HongXunProductionItemInStock;
import paul.sydney.model.HongXunProductionItemOutStock;
import paul.sydney.model.HongXunProductionNoLimitItemInStock;
import paul.sydney.model.HongXunProductionNoLimitItemOutStock;
import paul.sydney.model.HongXunProductionStock;


public class ProductionItemInStockDao extends HibernateBaseUtil<HongXunProductionItemInStock, Integer> implements IProductionItemInStockDao {

	@Override
	public void save(HongXunProductionItemInStock hongXunProductionItemInStock) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(hongXunProductionItemInStock);
	}

	@Override
	public List<HongXunProductionItemInStock> quary(HongXunProductionItemInStock hongXunProductionItemInStock) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().findByExample(hongXunProductionItemInStock);
	}

}
