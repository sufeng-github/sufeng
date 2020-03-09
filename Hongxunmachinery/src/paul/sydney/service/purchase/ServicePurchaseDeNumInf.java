package paul.sydney.service.purchase;

import java.io.InputStream;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import paul.sydney.commen.result.PageResults;
import paul.sydney.model.HongXunBomTree;
import paul.sydney.model.HongXunMaterialOutStoreItem;
import paul.sydney.model.HongXunMaterialOutStoreNum;
import paul.sydney.model.HongXunMaterialStock;
import paul.sydney.model.HongXunPurchaseDeNum;
import paul.sydney.model.HongXunPurchaseItem;
import paul.sydney.model.HongXunPurchaseNum;

public interface ServicePurchaseDeNumInf {

	//List<Map<String, Object>> loadData();

	//List<Map<String, Object>> saveRow(HongXunPurchaseNum hongXunDataOne);
	//Boolean deleteRow(HongXunDataOne hongXunDataOne);

	//List<Map<String, Object>> updateRow(HongXunPurchaseNum hongXunDataOne);

	//List<HongXunDataOne> quary(HongXunDataOne hongXunDataOne);

	//void mapHongXunDataOne(Map<String, Object> map, HongXunDataOne item);

	//List<Map<String, Object>> betweenDateFind(String date1, String date2) throws ParseException;

	//List<Map<String, Object>> searchNum(HongXunPurchaseNum hongXunDataOne);

	//List<Map<String, Object>> refresh(List<HongXunPurchaseNum> hongXunDataOneList);

	Boolean deleteRow(HongXunPurchaseDeNum item);

	//void save(HongXunPurchaseItem hongXunDataTwo);

	List<Map<String, Object>> autotimp(String q);

	//List<Map<String, Object>> searchNum(String purchaseNum);

	//void update(HongXunPurchaseItem hongXunPurchaseItem);

	//void saveRow(HongXunMaterialOutStoreNum hongXunMaterialOutStoreNum);

	//void save(HongXunMaterialOutStoreItem hongXunMaterialOutStoreItem);

	//int calPurchaseQuantity(String materialNum, Integer materialPlanQuantity);

	//void calPurchaseQuantity(int idc);

	void update(HongXunMaterialOutStoreItem itemNer);

	List<Map<String, Object>> createOutStoreSheet(List<HongXunBomTree> hongXunBomTreeList);

	List<Map<String, Object>> createNewPurchaseDeNum(List<HongXunMaterialStock> hongXunMaterialStockList);

	List<Map<String, Object>> saveRow(List<HongXunPurchaseDeNum> hongXunPurchaseDeNumList);

	List<Map<String, Object>> getEntity();

	List<Map<String, Object>> updateSotockState(int purchaseNumID);

	PageResults<HongXunPurchaseDeNum> getData(HongXunPurchaseDeNum hongXunPurchaseDeNum, Integer pageNo,
			Integer pageSize);



}
