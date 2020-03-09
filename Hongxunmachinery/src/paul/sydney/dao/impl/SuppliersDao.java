package paul.sydney.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import paul.sydney.commen.dao.CustomHibernateDaoSupport;
import paul.sydney.dao.ISuppliersDao;
import paul.sydney.model.HongXunSupplier;
import paul.sydney.model.HongXunSuppliersPrice;


@Repository("SuppliersDao")
public class SuppliersDao extends CustomHibernateDaoSupport implements ISuppliersDao{
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
	
	@Override
	public void save(HongXunSupplier hongXunSupplier) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(hongXunSupplier);
	}
	@Override
	public List<HongXunSupplier> quary(HongXunSupplier hongXunSupplier) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().findByExample(hongXunSupplier);
	}
	@Override
	public void update(HongXunSupplier hongXunSupplier) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(hongXunSupplier);
	}
	@Override
	public HongXunSupplier hongXunSupplierFindById(int id) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(HongXunSupplier.class, id);
	}
	@Override
	public void deletRow(HongXunSupplier hongXunSupplier) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(hongXunSupplier);
	}
}
