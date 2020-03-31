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

	void saveRow(HongXunProductionStock hongXunMaterialStock);

	void updateRow(HongXunProductionStock hongXunMaterialStock);

	List<Map<String, Object>> getEntity();

	List<Map<String, Object>> delAllRows();

	PageResults<HongXunProductionStock> getData(HongXunProductionStock hongXunProductionStock, Integer pageNo, Integer pageSize);

	List<Map<String, Object>> deleteRow(HongXunProductionStock item);

	PageResults<HongXunProductionStock> scanInStock(HongXunProductionStock hongXunProductionStock, PageResults<HongXunProductionStock> result);

	List<Map<String, Object>> autotimp(String str);

/*	PageResults<HongXunProductionStock> outStock(HongXunProductionStock hongXunProductionStock,
			PageResults<HongXunProductionStock> result);*/

	PageResults<HongXunProductionStock> outStock(int proIdc, int itemOutIdc, int sendQuantity,
			PageResults<HongXunProductionStock> result);

}
