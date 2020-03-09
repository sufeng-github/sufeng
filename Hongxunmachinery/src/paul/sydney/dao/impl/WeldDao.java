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
	
	@Override
	public void save(HongXunWeldCompilation hongXunWeldCompilation) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(hongXunWeldCompilation);
	}
	@Override
	public void update(HongXunWeldCompilation hongXunWeldCompilation) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(hongXunWeldCompilation);
	}
	@Override
	public List<HongXunWeldCompilation> quary(HongXunWeldCompilation hongXunWeldCompilation) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().findByExample(hongXunWeldCompilation);
	}
	
	@Override
	public void save(HongXunWeldItemInStock hongXunWeldItemInStock) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(hongXunWeldItemInStock);
	}
	@Override
	public List<HongXunWeldItemInStock> quary(HongXunWeldItemInStock hongXunWeldItemInStock) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().findByExample(hongXunWeldItemInStock);
	}

	@Override
	public void save(HongXunWeldItemOutStock hongXunWeldItemOutStock) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(hongXunWeldItemOutStock);
	}
	@Override
	public List<HongXunWeldItemOutStock> quary(HongXunWeldItemOutStock hongXunWeldItemOutStock) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().findByExample(hongXunWeldItemOutStock);
	}


	@Override
	public void save(HongXunWeldNoLimitItemInStock hongXunWeldNoLimitItemInStock) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(hongXunWeldNoLimitItemInStock);
	}
	@Override
	public List<HongXunWeldNoLimitItemInStock> quary(HongXunWeldNoLimitItemInStock hongXunWeldNoLimitItemInStock) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().findByExample(hongXunWeldNoLimitItemInStock);
	}
	@Override
	public HongXunWeldNoLimitItemInStock weldNoLimitItemInStockFindById(int idc) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(HongXunWeldNoLimitItemInStock.class, idc);
	}
	@Override
	public void update(HongXunWeldNoLimitItemInStock hongXunWeldNoLimitItemInStock) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(hongXunWeldNoLimitItemInStock);
	}

	@Override
	public List<HongXunWeldNoLimitItemOutStock> quary(HongXunWeldNoLimitItemOutStock hongXunWeldNoLimitItemOutStock) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().findByExample(hongXunWeldNoLimitItemOutStock);
	}
	@Override
	public void save(HongXunWeldNoLimitItemOutStock hongXunWeldNoLimitItemOutStock) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(hongXunWeldNoLimitItemOutStock);
	}
	@Override
	public HongXunWeldNoLimitItemOutStock weldNoLimitItemOutStockFindById(int idc) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(HongXunWeldNoLimitItemOutStock.class, idc);
	}
	@Override
	public void update(HongXunWeldNoLimitItemOutStock hongXunWeldNoLimitItemOutStock) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(hongXunWeldNoLimitItemOutStock);
	}

	@Override
	public void update(HongXunDeliveryWeldItem hongXunDeliveryWeldItem) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(hongXunDeliveryWeldItem);
	}
	@Override
	public HongXunDeliveryWeldItem deliveryWeldItemFindById(int idc) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(HongXunDeliveryWeldItem.class, idc);
	}
	@Override
	public void delete(HongXunDeliveryWeldItem hongXunDeliveryWeldItem) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(hongXunDeliveryWeldItem);
	}
	@Override
	public List<HongXunDeliveryWeldItem> quary(HongXunDeliveryWeldItem hongXunDeliveryWeldItem) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().findByExample(hongXunDeliveryWeldItem);
	}
	@Override
	public void save(HongXunDeliveryWeldItem hongXunDeliveryWeldItem) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(hongXunDeliveryWeldItem);
	}
	@Override
	public List<HongXunDeliveryWeldNum> quary(HongXunDeliveryWeldNum hongXunDeliveryWeldNum) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().findByExample(hongXunDeliveryWeldNum);
	}
	@Override
	public void save(HongXunDeliveryWeldNum hongXunDeliveryWeldNum) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(hongXunDeliveryWeldNum);
	}
	@Override
	public void delete(HongXunDeliveryWeldNum hongXunDeliveryWeldNum) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(hongXunDeliveryWeldNum);
	}
	@Override
	public HongXunDeliveryWeldNum deliveryWeldNumFindById(int id) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(HongXunDeliveryWeldNum.class, id);
	}
	@Override
	public void update(HongXunDeliveryWeldNum hongXunDeliveryWeldNum) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(hongXunDeliveryWeldNum);
	}



}
