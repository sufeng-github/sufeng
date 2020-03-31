package paul.sydney.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import paul.sydney.commen.dao.IHibernateBaseUtil;
import paul.sydney.commen.dao.impl.HibernateBaseUtil;
import paul.sydney.dao.IWeldNoLimitItemInStockDao;
import paul.sydney.model.HongXunDeliveryWeldItem;
import paul.sydney.model.HongXunDeliveryWeldNum;
import paul.sydney.model.HongXunMaterialStock;
import paul.sydney.model.HongXunProductionWeldStock;
import paul.sydney.model.HongXunWeldCompilation;
import paul.sydney.model.HongXunWeldItemInStock;
import paul.sydney.model.HongXunWeldItemOutStock;
import paul.sydney.model.HongXunWeldNoLimitItemInStock;
import paul.sydney.model.HongXunWeldNoLimitItemOutStock;
@Repository("WeldNoLimitItemInStockDao")
public class WeldNoLimitItemInStockDao extends HibernateBaseUtil<HongXunWeldNoLimitItemInStock, Integer> implements IWeldNoLimitItemInStockDao{

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

}
