package paul.sydney.service.materialstock;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import paul.sydney.model.HongXunWorkNum;
import paul.sydney.model.HongXunPurchaseItem;
import paul.sydney.model.HongXunPurchaseItemInStock;
import paul.sydney.model.HongXunMaterialItemOutStock;
import paul.sydney.model.HongXunWorkItem;



public interface ServiceMaterialOutStockInf {

	//List<Map<String, Object>> outstore(HongXunPurchaseItemOutStock hongXunDataTwoChildren);

	List<Map<String, Object>> getWorkItemStorckOuts(String workNum);

	List<Map<String, Object>> getWeiwaiItemStorckOuts(String weiwaiNum);

	//List<Map<String, Object>> saveRow(HongXunMaterialItemOutStock hongXunPurchaseItemOutStock);

	List<Map<String, Object>> loadData();

	List<Map<String, Object>> autotimp(String q, int outStoreNumID);

	List<Map<String, Object>> searchMaterialNum(String materialNum);

	List<Map<String, Object>> getEntity();

	List<Map<String, Object>> getMaterialItemOutStock(int outStoreNumID);

	List<Map<String, Object>> outstoreMaterialNum(String materialNum, int outStoreNumID, int quantity);

	Collection<? extends Map<String, Object>> saveRow(HongXunMaterialItemOutStock item);

	Collection<? extends Map<String, Object>> updateRow(HongXunMaterialItemOutStock item);

	//List<Map<String, Object>> scanOutMaterialNum(String materialNum, int quantity);
	

}
