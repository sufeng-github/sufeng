package paul.sydney.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import paul.sydney.commen.dao.CustomHibernateDaoSupport;
import paul.sydney.commen.dao.impl.HibernateBaseUtil;
import paul.sydney.dao.IMaterialStockDao;
import paul.sydney.dao.IProductionDao;
import paul.sydney.dao.IWeldDao;
import paul.sydney.model.HongXunDeliveryWeldItem;
import paul.sydney.model.HongXunDeliveryWeldNum;
import paul.sydney.model.HongXunProductionItemInStock;
import paul.sydney.model.HongXunProductionItemOutStock;
import paul.sydney.model.HongXunProductionNoLimitItemInStock;
import paul.sydney.model.HongXunProductionNoLimitItemOutStock;
import paul.sydney.model.HongXunProductionStock;
import paul.sydney.model.HongXunProductionWeldStock;
import paul.sydney.model.HongXunWeldCompilation;
import paul.sydney.model.HongXunWeldItemInStock;
import paul.sydney.model.HongXunWeldItemOutStock;
import paul.sydney.model.HongXunWeldNoLimitItemInStock;
import paul.sydney.model.HongXunWeldNoLimitItemOutStock;

@Repository("WeldDao")
public class WeldDao extends HibernateBaseUtil<HongXunProductionWeldStock, Integer> implements IWeldDao{
	@Override
	public List<HongXunProductionWeldStock> quary(HongXunProductionWeldStock hongXunProductionWeldStock) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().findByExample(hongXunProductionWeldStock);
	}
	@Override
	public void save(HongXunProductionWeldStock hongXunProductionWeldStock) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(hongXunProductionWeldStock);
	}
	@Override
	public void update(HongXunProductionWeldStock hongXunProductionWeldStock) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(hongXunProductionWeldStock);
	}
	@Override
	public HongXunProductionWeldStock productionWeldFindById(int idc) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(HongXunProductionWeldStock.class, idc);
	}
	@Override
	public void deleteWeldItemList(List<HongXunProductionWeldStock> hongXunProductionWeldStocks) {
		// TODO Auto-generated method stub
		getHibernateTemplate().deleteAll(hongXunProductionWeldStocks);
	}
	

}
