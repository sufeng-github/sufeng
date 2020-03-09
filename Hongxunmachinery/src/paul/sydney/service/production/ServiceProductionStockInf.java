package paul.sydney.service.production;

import java.util.List;
import java.util.Map;

import paul.sydney.commen.result.PageResults;
import paul.sydney.model.HongXunDeliveryItem;
import paul.sydney.model.HongXunMaterialItemOutStock;
import paul.sydney.model.HongXunMaterialStock;
import paul.sydney.model.HongXunProductionStock;
import paul.sydney.model.HongXunPurchaseItemInStock;

public interface ServiceProductionStockInf {

	//List<Map<String, Object>> loadData();

	List<Map<String, Object>> autotimp(String q);

	List<Map<String, Object>> searchMaterialNum(String materialNum);

	List<Map<String, Object>> saveRow(HongXunProductionStock hongXunMaterialStock);

	List<Map<String, Object>> updateRow(HongXunProductionStock hongXunMaterialStock);

	List<Map<String, Object>> getEntity();

	List<Map<String, Object>> delAllRows();

	PageResults<HongXunProductionStock> getData(HongXunProductionStock hongXunProductionStock, Integer pageNo, Integer pageSize);

	List<Map<String, Object>> deleteRow(HongXunProductionStock item);


}
