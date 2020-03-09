package paul.sydney.dao;

import java.util.List;
import paul.sydney.commen.dao.IHibernateBaseUtil;
import paul.sydney.model.HongXunPurchaseItemInStock;

public interface IPurchaseItemInStockDao extends IHibernateBaseUtil<HongXunPurchaseItemInStock, Integer> {
	
	
	void save(HongXunPurchaseItemInStock hongXunPurchaseItemInStock);
	List<HongXunPurchaseItemInStock> quary(HongXunPurchaseItemInStock hongXunPurchaseItemInStock);
	HongXunPurchaseItemInStock purchaseItemInStockFindById(int fatherID);
	void update(HongXunPurchaseItemInStock hongXunPurchaseItemInStock);
	



}
