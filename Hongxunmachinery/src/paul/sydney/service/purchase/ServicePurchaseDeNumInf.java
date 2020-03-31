package paul.sydney.service.purchase;

import java.util.List;
import java.util.Map;
import paul.sydney.commen.result.PageResults;
import paul.sydney.model.HongXunBomTree;
import paul.sydney.model.HongXunMaterialOutStoreItem;
import paul.sydney.model.HongXunMaterialStock;
import paul.sydney.model.HongXunPurchaseDeNum;

public interface ServicePurchaseDeNumInf {

	Boolean deleteRow(HongXunPurchaseDeNum item);
	List<Map<String, Object>> autotimp(String q);
	void update(HongXunMaterialOutStoreItem itemNer);
	List<Map<String, Object>> createOutStoreSheet(List<HongXunBomTree> hongXunBomTreeList);
	List<Map<String, Object>> createNewPurchaseDeNum(List<HongXunMaterialStock> hongXunMaterialStockList);
	List<Map<String, Object>> saveRow(List<HongXunPurchaseDeNum> hongXunPurchaseDeNumList);
	List<Map<String, Object>> getEntity();
	List<Map<String, Object>> updateSotockState(int purchaseNumID);
	PageResults<HongXunPurchaseDeNum> getData(HongXunPurchaseDeNum hongXunPurchaseDeNum, Integer pageNo,
			Integer pageSize);
}
