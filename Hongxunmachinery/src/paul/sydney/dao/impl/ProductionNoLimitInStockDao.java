package paul.sydney.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import paul.sydney.commen.dao.impl.HibernateBaseUtil;
import paul.sydney.dao.IProductionNoLimitInStockDao;
import paul.sydney.model.HongXunProductionNoLimitItemInStock;


@Repository("ProductionNoLimitInStockDao")
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
