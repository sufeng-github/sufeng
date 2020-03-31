package paul.sydney.service.materialstock;

import java.io.InputStream;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import paul.sydney.model.HongXunMaterialStock;
import paul.sydney.model.HongXunPurchaseItem;
import paul.sydney.model.HongXunMaterialOutStoreNum;

public interface ServiceStoreOutNumInf {

	List<Map<String, Object>> loadData();

	List<Map<String, Object>> saveRow(HongXunMaterialOutStoreNum hongXunDataOne);
	//Boolean deleteRow(HongXunDataOne hongXunDataOne);

	List<Map<String, Object>> updateRow(HongXunMaterialOutStoreNum hongXunDataOne);
	Boolean deleteRow(int ID);
	//List<HongXunDataOne> quary(HongXunDataOne hongXunDataOne);

	List<Map<String, Object>> updateStatus(int outStoreNumID);

	//void mapHongXunDataOne(Map<String, Object> map, HongXunDataOne item);
/*
	List<Map<String, Object>> betweenDateFind(String date1, String date2) throws ParseException;

	List<Map<String, Object>> searchNum(HongXunMaterialOutStoreNum hongXunDataOne);

	List<Map<String, Object>> refresh(List<HongXunMaterialOutStoreNum> hongXunDataOneList);



	void save(HongXunPurchaseItem hongXunDataTwo);

	List<Map<String, Object>> autotimp(String q);

	List<Map<String, Object>> searchNum(String purchaseNum);

	void update(HongXunPurchaseItem hongXunPurchaseItem);
*/

	//List<Map<String, Object>> getPurchaseItems(List<HongXunMaterialOutStoreNum> hongXunDataOneList);



}
