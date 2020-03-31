package paul.sydney.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import paul.sydney.commen.dao.IHibernateBaseUtil;
import paul.sydney.commen.dao.impl.HibernateBaseUtil;
import paul.sydney.dao.IWeldItemOutStockDao;
import paul.sydney.model.HongXunDeliveryWeldItem;
import paul.sydney.model.HongXunDeliveryWeldNum;
import paul.sydney.model.HongXunMaterialStock;
import paul.sydney.model.HongXunProductionWeldStock;
import paul.sydney.model.HongXunWeldCompilation;
import paul.sydney.model.HongXunWeldItemInStock;
import paul.sydney.model.HongXunWeldItemOutStock;
import paul.sydney.model.HongXunWeldNoLimitItemInStock;
import paul.sydney.model.HongXunWeldNoLimitItemOutStock;
@Repository("WeldItemOutStockDao")
public class WeldItemOutStockDao extends HibernateBaseUtil<HongXunWeldItemOutStock, Integer> implements IWeldItemOutStockDao{
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

}
