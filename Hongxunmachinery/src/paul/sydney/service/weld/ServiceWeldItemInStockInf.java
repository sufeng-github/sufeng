package paul.sydney.service.weld;

import java.util.List;
import java.util.Map;

import paul.sydney.model.HongXunMaterialItemOutStock;
import paul.sydney.model.HongXunPurchaseItemInStock;

public interface ServiceWeldItemInStockInf {

	
	//List<Map<String, Object>> saveRow(HongXunPurchaseItemInStock hongXunDataTwoSun);

	//List<Map<String, Object>> loadData();

	//List<Map<String, Object>> outstore(HongXunDataTwoChildren hongXunDataTwoChildren);

	//List<Map<String, Object>> preOutstore(String materialNum, int outStoreQuantity, int uncleID);

	List<Map<String, Object>> autotimp(String q, int purchaseNumID);

	List<Map<String, Object>> instoreProductionWeld(String materialNum, int purchaseNumID, int quantity);
							
	List<Map<String, Object>> getWeldItemsInStore(int purchaseNumID);
	
}
