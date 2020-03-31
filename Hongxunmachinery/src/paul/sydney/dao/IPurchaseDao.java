package paul.sydney.dao;

import java.util.List;

import paul.sydney.commen.dao.IHibernateBaseUtil;
import paul.sydney.model.HongXunPurchaseItemInStock;
import paul.sydney.model.HongXunPurchaseNum;

public interface IPurchaseDao extends IHibernateBaseUtil<HongXunPurchaseNum, Integer> {
	void update(HongXunPurchaseNum hongXunDataOne);
	void save(HongXunPurchaseNum hongXunDataOne);

	List<HongXunPurchaseNum> quary(HongXunPurchaseNum hongXunDataOne);	
	void deletRow(HongXunPurchaseNum hongXunDataOne);
	HongXunPurchaseNum oneDataFindById(int id);
	HongXunPurchaseNum purchaseNumFindById(int id);
	

}
