package paul.sydney.service.purchase;

import java.util.List;
import java.util.Map;
import paul.sydney.commen.result.PageResults;
import paul.sydney.model.HongXunPurchaseDeItem;

public interface ServicePurchaseDeItemInf {


	Boolean deleteRow(int ID);
	List<Map<String, Object>> autotimp(String q);
	List<Map<String, Object>> getEntity();
	//List<Map<String, Object>> updateSotockState(int purchaseNumID);
	List<Map<String, Object>> getNumList(Integer id);
	PageResults<HongXunPurchaseDeItem> getData(HongXunPurchaseDeItem hongXunPurchaseDeItem, Integer pageNo,
			Integer pageSize);

}
