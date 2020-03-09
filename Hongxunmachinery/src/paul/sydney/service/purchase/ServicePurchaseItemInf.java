package paul.sydney.service.purchase;

import java.util.List;
import java.util.Map;
import paul.sydney.model.HongXunPurchaseNum;
import paul.sydney.model.HongXunPurchaseItem;

public interface ServicePurchaseItemInf {
	
	List<Map<String, Object>> saveRow(HongXunPurchaseItem hongXunDataTwo);
	List<Map<String, Object>> deleteRow(int iD);
	List<Map<String, Object>> updateRow(HongXunPurchaseItem hongXunDataTwo);
	//List<Map<String, Object>> updateRow1(HongXunDataOneBranchOne hongXunDataTwo);
	List<HongXunPurchaseItem> quary(HongXunPurchaseItem hongXunDataTwo);
	//void mapHongXunDataOneBranchOne(Map<String, Object> map, HongXunDataTwo item);
	//HongXunPurchaseNum quarywithpara(String string);
	List<Map<String, Object>> saveMoney(int iD);
	List<Map<String, Object>> loadData();
	//List<Map<String, Object>> getStockinChildrens(int mainID);
	List<Map<String, Object>> stockinLoadData();
	List<Map<String, Object>> outstore(String materialNum, int quantity, int uncleID);
	List<Map<String, Object>> getPurchaseItems(int purchaseNumID);
	//List<Map<String, Object>> autotimp(String q);
	//List<Map<String, Object>> searchNum(String purchaseItem);
	List<Map<String, Object>> updateSotockState(int mainID);
	List<Map<String, Object>> getPurchaseSheet(List<HongXunPurchaseNum> hongXunDataOneList);
	List<Map<String, Object>> getEntity();
	List<Map<String, Object>> movePurchaseItem(int id);

	List<Map<String, Object>> searchPurchaseItem(int purchaseNumID, String materialNum);
	List<Map<String, Object>> instorePurchaseItem(int id, int quantity);
	List<Map<String, Object>> autotimp(String q, int purchaseNumID);
	List<Map<String, Object>> getAllPurchaseItems();


}
