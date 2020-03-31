package paul.sydney.service.purchase;

import java.util.List;
import java.util.Map;
import paul.sydney.commen.result.PageResults;
import paul.sydney.model.HongXunMaterialStock;
import paul.sydney.model.HongXunPurchaseDeItem;

public interface ServicePurchaseDeItemInf {


	List<Map<String, Object>> autotimp(String q, Integer integer);
	List<Map<String, Object>> getEntity();
	PageResults<HongXunPurchaseDeItem> getData(HongXunPurchaseDeItem hongXunPurchaseDeItem, Integer pageNo,
			Integer pageSize);
	Boolean deleteRow(HongXunPurchaseDeItem hongXunPurchaseDeItem);
	void updateRow(HongXunPurchaseDeItem item);

}
