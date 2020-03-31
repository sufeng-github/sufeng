package paul.sydney.dao;

import java.util.List;

import paul.sydney.commen.dao.IHibernateBaseUtil;
import paul.sydney.model.HongXunSuppliersPrice;

public interface ISuppliersPriceDao extends IHibernateBaseUtil<HongXunSuppliersPrice, Integer>{
	void save(HongXunSuppliersPrice hongXunSuppliersPrice);
	void update(HongXunSuppliersPrice hongXunSuppliersPrice);
	List<HongXunSuppliersPrice> quary(HongXunSuppliersPrice hongXunSuppliersPrice);
	void deletePriceList(List<HongXunSuppliersPrice> hongXunSuppliersPriceList);
}
