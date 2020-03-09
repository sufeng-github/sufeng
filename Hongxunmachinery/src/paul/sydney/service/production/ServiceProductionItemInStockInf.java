package paul.sydney.service.production;

import java.util.List;
import java.util.Map;

import paul.sydney.model.HongXunMaterialItemOutStock;
import paul.sydney.model.HongXunPurchaseItemInStock;

public interface ServiceProductionItemInStockInf {

	
	//List<Map<String, Object>> saveRow(HongXunPurchaseItemInStock hongXunDataTwoSun);

	//List<Map<String, Object>> loadData();

	//List<Map<String, Object>> outstore(HongXunDataTwoChildren hongXunDataTwoChildren);

	//List<Map<String, Object>> preOutstore(String materialNum, int outStoreQuantity, int uncleID);

	List<Map<String, Object>> autotimp(String q, int purchaseNumID);	

	List<Map<String, Object>> getProductionItemsInStore(int purchaseNumID);

	List<Map<String, Object>> instoreProduction(String materialNum, int orderItemID, int orderNumID, int quantity);


	
}
