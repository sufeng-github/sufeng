package paul.sydney.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import paul.sydney.commen.dao.IHibernateBaseUtil;
import paul.sydney.commen.dao.impl.HibernateBaseUtil;
import paul.sydney.dao.IWeldNoLimitItemOutStockDao;
import paul.sydney.model.HongXunWeldNoLimitItemOutStock;
@Repository("WeldNoLimitItemOutStockDao")
public class WeldNoLimitItemOutStockDao extends HibernateBaseUtil<HongXunWeldNoLimitItemOutStock, Integer> implements IWeldNoLimitItemOutStockDao{
	
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
}
