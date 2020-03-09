package paul.sydney.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import paul.sydney.commen.dao.CustomHibernateDaoSupport;
import paul.sydney.commen.dao.impl.HibernateBaseUtil;
import paul.sydney.dao.IMaterialStockDao;
import paul.sydney.dao.IProductionDao;
import paul.sydney.model.HongXunAssembleCompilation;
import paul.sydney.model.HongXunDeliveryItem;
import paul.sydney.model.HongXunDeliveryNum;
import paul.sydney.model.HongXunMaterialStock;
import paul.sydney.model.HongXunProductionItemInStock;
import paul.sydney.model.HongXunProductionItemOutStock;
import paul.sydney.model.HongXunProductionNoLimitItemInStock;
import paul.sydney.model.HongXunProductionNoLimitItemOutStock;
import paul.sydney.model.HongXunProductionStock;

@Repository("ProductionDao")
public class ProductionDao extends HibernateBaseUtil<HongXunProductionStock, Integer> implements IProductionDao{

	@Override
	public void save(HongXunProductionStock hongXunProductionStock) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(hongXunProductionStock);
	}
	@Override
	public void update(HongXunProductionStock hongXunProductionStock) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(hongXunProductionStock);
	}
	@Override
	public HongXunProductionStock productionFindById(Integer idc) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(HongXunProductionStock.class, idc);
	}
	@Override
	public void deleteProductionItemList(List<HongXunProductionStock> hongXunProductionStocks) {
		// TODO Auto-generated method stub
		getHibernateTemplate().deleteAll(hongXunProductionStocks);
	}
	@Override
	public List<HongXunProductionStock> quary(HongXunProductionStock hongXunProductionStock) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().findByExample(hongXunProductionStock);
	}
	
}
