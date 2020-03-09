package paul.sydney.service.weld;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import paul.sydney.model.HongXunMaterialItemOutStock;
import paul.sydney.model.HongXunMaterialNoLimitInStock;
import paul.sydney.model.HongXunProductionWeldStock;
import paul.sydney.model.HongXunPurchaseItemInStock;
import paul.sydney.model.HongXunWeldNoLimitItemInStock;

public interface ServiceWeldNoLimitItemInStockInf {

	
	//List<Map<String, Object>> saveRow(HongXunPurchaseItemInStock hongXunDataTwoSun);

	//List<Map<String, Object>> loadData();

	//List<Map<String, Object>> outstore(HongXunDataTwoChildren hongXunDataTwoChildren);

	//List<Map<String, Object>> preOutstore(String materialNum, int outStoreQuantity, int uncleID);


	List<Map<String, Object>> instoreProduction(String materialNum,  int quantity);

	List<Map<String, Object>> loadData();

	List<Map<String, Object>> autotimp(String str);

	List<Map<String, Object>> getEntity();

	Collection<? extends Map<String, Object>> saveRow(HongXunWeldNoLimitItemInStock item);

	Collection<? extends Map<String, Object>> updateRow(HongXunWeldNoLimitItemInStock item);


	
}
