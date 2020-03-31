package paul.sydney.service.weld;

import java.util.List;
import java.util.Map;

import paul.sydney.model.HongXunDeliveryItem;
import paul.sydney.model.HongXunDeliveryWeldItem;
import paul.sydney.model.HongXunMaterialItemOutStock;
import paul.sydney.model.HongXunProductionStock;
import paul.sydney.model.HongXunPurchaseItemInStock;

public interface ServiceDeliveryWeldItemInf {

	List<Map<String, Object>> autotimp(String q, Integer integer);

	List<Map<String, Object>> searchMaterialNum(String materialNum, int deliveryNumID);

	//List<Map<String, Object>> saveRow(HongXunDeliveryWeldItem hongXunDeliveryWeldItem);

	//List<Map<String, Object>> updateRow(HongXunDeliveryWeldItem hongXunDeliveryWeldItem);

	List<Map<String, Object>> getEntity();

	//List<Map<String, Object>> delAllRows();

	List<Map<String, Object>> importData(List<HongXunDeliveryWeldItem> hongXunDeliveryWeldItemList);

	List<Map<String, Object>> loadData(Integer deliveryNumID);

	List<Map<String, Object>> autotimp(String q);

	List<Map<String, Object>> searchMaterialNum(String materialNum);

	List<Map<String, Object>> outstock(String materialNum, int deliveryItemID);

}
