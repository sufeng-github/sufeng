package paul.sydney.service.materialstock;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import paul.sydney.model.HongXunMaterialItemOutStock;
import paul.sydney.model.HongXunMaterialOutStoreItem;
import paul.sydney.model.HongXunMaterialOutStoreNum;
import paul.sydney.model.HongXunPurchaseNum;


public interface ServiceStoreOutItemInf {
	List<Map<String, Object>> loadData();
	List<Map<String, Object>> saveRow(HongXunMaterialOutStoreItem hongXunDataTwo);
	List<Map<String, Object>> deleteRow(int iD);
	List<Map<String, Object>> updateRow(HongXunMaterialOutStoreItem hongXunDataTwo);
	//List<Map<String, Object>> updateRow1(HongXunDataOneBranchOne hongXunDataTwo);
	List<HongXunMaterialOutStoreItem> quary(HongXunMaterialOutStoreItem hongXunDataTwo);
	//void mapHongXunDataOneBranchOne(Map<String, Object> map, HongXunDataTwo item);
	/*HongXunPurchaseNum quarywithpara(String string);
	List<Map<String, Object>> saveMoney(int iD);
	
	List<Map<String, Object>> getStockinChildrens(int mainID);
	List<Map<String, Object>> stockinLoadData();
	List<Map<String, Object>> outstore(String materialNum, int quantity, int uncleID);
	List<Map<String, Object>> getPurchaseItems(int uncleID);
	List<Map<String, Object>> autotimp(String q);
	//List<Map<String, Object>> searchNum(String purchaseItem);
	List<Map<String, Object>> updateSotockState(int mainID);*/
	//List<Map<String, Object>> getOutStoreSheet(List<HongXunMaterialOutStoreNum> hongXunDataOneList);
	List<Map<String, Object>> getOutStoreItems(int uncleID);
	List<Map<String, Object>> getEntity();
	//List<Map<String, Object>> autotimp(String q, Integer outStoreNumID);
	List<Map<String, Object>> outstoreMaterialNum(int id, int quantity);
	//List<Map<String, Object>> searchMaterialNum(int outStoreNumID, String materialNum);


}
