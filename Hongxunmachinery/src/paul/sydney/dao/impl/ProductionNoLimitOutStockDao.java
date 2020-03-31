package paul.sydney.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import paul.sydney.commen.dao.impl.HibernateBaseUtil;
import paul.sydney.dao.IProductionNoLimitOutStockDao;
import paul.sydney.model.HongXunProductionNoLimitItemOutStock;


@Repository("ProductionNoLimitOutStockDao")
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
