package paul.sydney.service.production;

import java.util.List;
import java.util.Map;

import paul.sydney.model.HongXunMaterialItemOutStock;
import paul.sydney.model.HongXunPurchaseItemInStock;

public interface ServiceProductionItemOutStockInf {

	
	//List<Map<String, Object>> saveRow(HongXunPurchaseItemInStock hongXunDataTwoSun);

	//List<Map<String, Object>> loadData();

	//List<Map<String, Object>> outstore(HongXunDataTwoChildren hongXunDataTwoChildren);

	//List<Map<String, Object>> preOutstore(String materialNum, int outStoreQuantity, int uncleID);

	List<Map<String, Object>> autotimp(String q, int purchaseNumID);

	List<Map<String, Object>> outstoreProduction(String materialNum, int purchaseNumID, int quantity);

	List<Map<String, Object>> getProductionItemsOutStore(int purchaseNumID);
	
}
