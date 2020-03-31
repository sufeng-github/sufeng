package paul.sydney.service.weld;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import paul.sydney.model.HongXunDeliveryNum;
import paul.sydney.model.HongXunDeliveryWeldNum;
import paul.sydney.model.HongXunProductionStock;

public interface ServiceDeliveryWeldNumInf {

	List<Map<String, Object>> loadData();
	List<Map<String, Object>> autotimp(String q);
	List<Map<String, Object>> searchMaterialNum(String materialNum);
	List<Map<String, Object>> getEntity();

	//List<Map<String, Object>> lotOutStock(List<HongXunProductionStock> hongXunProductionStockList);

	Collection<? extends Map<String, Object>> saveRow(HongXunDeliveryWeldNum item);

	Collection<? extends Map<String, Object>> updateRow(HongXunDeliveryWeldNum item);

	List<Map<String, Object>> deleteRow(int id);
	List<Map<String, Object>> changeStatus(int deliveryNumID);


}
