package paul.sydney.service.materialstock;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import paul.sydney.model.HongXunWorkNum;
import paul.sydney.model.HongXunPurchaseItem;
import paul.sydney.model.HongXunPurchaseItemInStock;
import paul.sydney.model.HongXunMaterialItemOutStock;
import paul.sydney.model.HongXunMaterialNoLimitInStock;
import paul.sydney.model.HongXunWorkItem;



public interface ServiceMaterialNoLimitInStockInf {

	//List<Map<String, Object>> outstore(HongXunPurchaseItemOutStock hongXunDataTwoChildren);

	List<Map<String, Object>> getWorkItemStorckOuts(String workNum);

	List<Map<String, Object>> getWeiwaiItemStorckOuts(String weiwaiNum);

	//List<Map<String, Object>> saveRow(HongXunMaterialItemOutStock hongXunPurchaseItemOutStock);

	List<Map<String, Object>> loadData();

	List<Map<String, Object>> autotimp(String q);

	List<Map<String, Object>> searchMaterialNum(String materialNum, int quantity);

	List<Map<String, Object>> getEntity();

	//List<Map<String, Object>> getMaterialItemOutStock(int outStoreNumID);

	//List<Map<String, Object>> outstoreMaterialNum(String materialNum, int outStoreNumID, int quantity);

	List<Map<String, Object>> scanInMaterialNum(String materialNum, int quantity);

	Collection<? extends Map<String, Object>> saveRow(HongXunMaterialNoLimitInStock item);

	Collection<? extends Map<String, Object>> updateRow(HongXunMaterialNoLimitInStock item);


	

}
