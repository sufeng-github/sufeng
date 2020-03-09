package paul.sydney.service.purchase;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import paul.sydney.model.HongXunMaterialItemOutStock;
import paul.sydney.model.HongXunPurchaseItemInStock;

public interface ServicePurchaseItemInStockInf {

	
	//List<Map<String, Object>> saveRow(HongXunPurchaseItemInStock hongXunDataTwoSun);

	List<Map<String, Object>> loadData();

	//List<Map<String, Object>> outstore(HongXunDataTwoChildren hongXunDataTwoChildren);

	//List<Map<String, Object>> preOutstore(String materialNum, int outStoreQuantity, int uncleID);

	//List<Map<String, Object>> autotimp(String q, int purchaseNumID);

	List<Map<String, Object>> instoreMaterialNum(String materialNum, int purchaseNumID, int quantity);

	List<Map<String, Object>> getPurchaseItemsInStore(int purchaseNumID);

	List<Map<String, Object>> autotimp(String q, int purchaseNumID);

	Collection<? extends Map<String, Object>> saveRow(HongXunPurchaseItemInStock item);

	Collection<? extends Map<String, Object>> updateRow(HongXunPurchaseItemInStock item);

	List<Map<String, Object>> getEntity();

/*	List<Map<String, Object>> searchPurchaseItem(int outStoreNumID, String materialNum);

	List<Map<String, Object>> instorePurchaseItem(int id, int quantity);*/
	
}
