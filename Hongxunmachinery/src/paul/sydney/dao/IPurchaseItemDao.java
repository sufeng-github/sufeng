package paul.sydney.dao;

import java.util.List;

import paul.sydney.commen.dao.IHibernateBaseUtil;
import paul.sydney.model.HongXunMaterialStock;
import paul.sydney.model.HongXunPurchaseDeItem;
import paul.sydney.model.HongXunPurchaseDeNum;
import paul.sydney.model.HongXunPurchaseItem;
import paul.sydney.model.HongXunPurchaseItemInStock;
import paul.sydney.model.HongXunPurchaseNum;

public interface IPurchaseItemDao extends IHibernateBaseUtil<HongXunPurchaseItem, Integer> {

	List<HongXunPurchaseItem> quary(HongXunPurchaseItem hongXunDataOneBranchOne);
	void save(HongXunPurchaseItem hongXunDataOneBranchOne);
	void deletRow(HongXunPurchaseItem hongXunDataOneBranchOne);
	void update(HongXunPurchaseItem hongXunDataOneBranchOne);
	HongXunPurchaseItem purchaseItemFindById(int id);

}
