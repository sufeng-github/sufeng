package paul.sydney.service.production;

import java.util.List;
import java.util.Map;

import paul.sydney.commen.result.PageResults;
import paul.sydney.model.HongXunDeliveryItem;
import paul.sydney.model.HongXunMaterialItemOutStock;
import paul.sydney.model.HongXunProductionStock;
import paul.sydney.model.HongXunPurchaseItemInStock;

public interface ServiceDeliveryItemInf {

	List<Map<String, Object>> autotimp(String q, int deliveryNumID);

	//List<Map<String, Object>> searchMaterialNum(String materialNum, int deliveryNumID);

	void saveRow(HongXunDeliveryItem item);

	void updateRow(HongXunDeliveryItem item);

	List<Map<String, Object>> getEntity();

	//List<Map<String, Object>> delAllRows();

	List<Map<String, Object>> importData(List<HongXunDeliveryItem> hongXunDeliveryItemList);

	//List<Map<String, Object>> loadData(Integer deliveryNumID);

	//List<Map<String, Object>> searchMaterialNum(String materialNum);

	//List<Map<String, Object>> autotimp(String q);

	List<Map<String, Object>> outstock(String materialNum, int deliveryItemID);

	PageResults<HongXunDeliveryItem> getData(HongXunDeliveryItem hongXunDeliveryItem, Integer pageNo, Integer pageSize);

	void deleteRow(HongXunDeliveryItem item);

}
