package paul.sydney.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import paul.sydney.commen.dao.CustomHibernateDaoSupport;
import paul.sydney.commen.dao.impl.HibernateBaseUtil;
import paul.sydney.dao.ISuppliersDao;
import paul.sydney.dao.ISuppliersPriceDao;
import paul.sydney.model.HongXunSupplier;
import paul.sydney.model.HongXunSuppliersPrice;


@Repository("SuppliersPriceDao")
public class SuppliersPriceDao extends HibernateBaseUtil<HongXunSuppliersPrice, Integer> implements ISuppliersPriceDao{
	@Override
	public void save(HongXunSuppliersPrice hongXunSuppliersPrice) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(hongXunSuppliersPrice);
	}
	@Override
	public void update(HongXunSuppliersPrice hongXunSuppliersPrice) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(hongXunSuppliersPrice);
	}
	@Override
	public List<HongXunSuppliersPrice> quary(HongXunSuppliersPrice hongXunSuppliersPrice) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().findByExample(hongXunSuppliersPrice);
	}
	@Override
	public void deletePriceList(List<HongXunSuppliersPrice> hongXunSuppliersPriceList) {
		// TODO Auto-generated method stub
		getHibernateTemplate().deleteAll(hongXunSuppliersPriceList);
	}
	
	
}
