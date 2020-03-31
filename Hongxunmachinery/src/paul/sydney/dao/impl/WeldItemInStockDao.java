package paul.sydney.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import paul.sydney.commen.dao.IHibernateBaseUtil;
import paul.sydney.commen.dao.impl.HibernateBaseUtil;
import paul.sydney.dao.IWeldItemInStockDao;
import paul.sydney.model.HongXunDeliveryWeldItem;
import paul.sydney.model.HongXunDeliveryWeldNum;
import paul.sydney.model.HongXunMaterialStock;
import paul.sydney.model.HongXunProductionWeldStock;
import paul.sydney.model.HongXunWeldCompilation;
import paul.sydney.model.HongXunWeldItemInStock;
import paul.sydney.model.HongXunWeldItemOutStock;
import paul.sydney.model.HongXunWeldNoLimitItemInStock;
import paul.sydney.model.HongXunWeldNoLimitItemOutStock;
@Repository("WeldItemInStockDao")
public class WeldItemInStockDao extends HibernateBaseUtil<HongXunWeldItemInStock, Integer> implements IWeldItemInStockDao{
		
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

	

}
