package paul.sydney.dao;

import java.util.List;

import paul.sydney.commen.dao.IHibernateBaseUtil;
import paul.sydney.model.HongXunMaterialStock;
import paul.sydney.model.HongXunPurchaseDeItem;
import paul.sydney.model.HongXunPurchaseDeNum;
import paul.sydney.model.HongXunPurchaseItem;
import paul.sydney.model.HongXunPurchaseItemInStock;
import paul.sydney.model.HongXunPurchaseNum;

public interface IPurchaseDeNumDao extends IHibernateBaseUtil<HongXunPurchaseDeNum, Integer> {

	
	void save(HongXunPurchaseDeNum hongXunPurchaseDeNum);
	List<HongXunPurchaseDeNum> quary(HongXunPurchaseDeNum hongXunPurchaseDeNum);
	void update(HongXunPurchaseDeNum item);
	HongXunPurchaseDeNum purchaseDeNumFindById(int iD);



}
