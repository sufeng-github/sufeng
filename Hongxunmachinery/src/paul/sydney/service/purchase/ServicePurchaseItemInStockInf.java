package paul.sydney.service.purchase;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import paul.sydney.commen.result.PageResults;
import paul.sydney.model.HongXunMaterialItemOutStock;
import paul.sydney.model.HongXunPurchaseItemInStock;

public interface ServicePurchaseItemInStockInf {

	List<Map<String, Object>> instoreMaterialNum(String materialNum, int purchaseNumID, int quantity);
	List<Map<String, Object>> autotimp(String q, int purchaseNumID);
	Collection<? extends Map<String, Object>> saveRow(HongXunPurchaseItemInStock item);
	void updateRow(HongXunPurchaseItemInStock item);
	List<Map<String, Object>> getEntity();
	PageResults<HongXunPurchaseItemInStock> getData(HongXunPurchaseItemInStock hongXunPurchaseItemInStock,
			Integer pageNo, Integer pageSize);
	void deleteRow(HongXunPurchaseItemInStock item);
	
}
