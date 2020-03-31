package paul.sydney.service.production;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import paul.sydney.model.HongXunMaterialItemOutStock;
import paul.sydney.model.HongXunProductionNoLimitItemInStock;
import paul.sydney.model.HongXunPurchaseItemInStock;

public interface ServiceProductionNoLimitItemInStockInf {

	
	//List<Map<String, Object>> saveRow(HongXunPurchaseItemInStock hongXunDataTwoSun);

	//List<Map<String, Object>> loadData();

	//List<Map<String, Object>> outstore(HongXunDataTwoChildren hongXunDataTwoChildren);

	//List<Map<String, Object>> preOutstore(String materialNum, int outStoreQuantity, int uncleID);


	List<Map<String, Object>> instoreProduction(String materialNum,  int quantity);

	List<Map<String, Object>> loadData();

	List<Map<String, Object>> autotimp(String str);

	List<Map<String, Object>> getEntity();

	Collection<? extends Map<String, Object>> saveRow(HongXunProductionNoLimitItemInStock item);

	Collection<? extends Map<String, Object>> updateRow(HongXunProductionNoLimitItemInStock item);


	
}
