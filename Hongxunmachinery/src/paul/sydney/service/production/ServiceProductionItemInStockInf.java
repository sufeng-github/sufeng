package paul.sydney.service.production;

import java.util.List;
import java.util.Map;

import paul.sydney.commen.result.PageResults;
import paul.sydney.model.HongXunMaterialItemOutStock;
import paul.sydney.model.HongXunProductionItemInStock;
import paul.sydney.model.HongXunPurchaseItemInStock;

public interface ServiceProductionItemInStockInf {

	List<Map<String, Object>> autotimp(String q);	

	PageResults<HongXunProductionItemInStock> getData(HongXunProductionItemInStock hongXunProductionItemInStock,
			Integer pageNo, Integer pageSize);

	
}
