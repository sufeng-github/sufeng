package paul.sydney.service.purchase;

import java.util.List;
import java.util.Map;
import paul.sydney.commen.result.PageResults;
import paul.sydney.model.HongXunBomTree;
import paul.sydney.model.HongXunMaterialOutStoreItem;
import paul.sydney.model.HongXunPurchaseDeItem;
import paul.sydney.model.HongXunPurchaseNum;

public interface ServicePurchaseNumInf {

	//List<Map<String, Object>> betweenDateFind(String date1, String date2) throws ParseException;
	//List<Map<String, Object>> refresh(List<HongXunPurchaseNum> hongXunDataOneList);
	Boolean deleteRow(HongXunPurchaseNum item);
	List<Map<String, Object>> autotimp(String q);
	//List<Map<String, Object>> searchNum(String purchaseNum);
	void update(HongXunMaterialOutStoreItem itemNer);
	List<Map<String, Object>> createOutStoreSheet(List<HongXunBomTree> hongXunBomTreeList);
	List<Map<String, Object>> createNewPurchaseNum(List<HongXunPurchaseDeItem> hongXunPurchaseDeItemList);
	List<Map<String, Object>> saveRow(List<HongXunPurchaseNum> hongXunPurchaseNumList);
	List<Map<String, Object>> getEntity();
	//List<Map<String, Object>> updateSotockState(int purchaseNumID);
	PageResults<HongXunPurchaseNum> getData(HongXunPurchaseNum hongXunPurchaseNum, Integer pageNo, Integer pageSize);

}
