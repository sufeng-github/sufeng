package paul.sydney.service.purchase;

import java.util.List;
import java.util.Map;

import net.sf.ehcache.search.impl.BaseResult;
import paul.sydney.model.HongXunPurchaseNum;
import paul.sydney.commen.result.PageResults;
import paul.sydney.model.HongXunPurchaseDeItem;
import paul.sydney.model.HongXunPurchaseItem;

public interface ServicePurchaseItemInf {
	
	List<Map<String, Object>> deleteRow(HongXunPurchaseItem hongXunPurchaseItem);
	void updateRow(HongXunPurchaseItem hongXunPurchaseItem);
	List<HongXunPurchaseItem> quary(HongXunPurchaseItem hongXunDataTwo);
	//List<Map<String, Object>> saveMoney(int iD);
	List<Map<String, Object>> outstore(String materialNum, int quantity, int uncleID);
	List<Map<String, Object>> updateSotockState(int mainID);
	List<Map<String, Object>> getEntity();
	List<Map<String, Object>> autotimp(String q, int purchaseNumID);
	PageResults<HongXunPurchaseItem> getData(HongXunPurchaseItem hongXunPurchaseItem, Integer pageNo, Integer pageSize);
	PageResults<HongXunPurchaseItem> instock(HongXunPurchaseItem item);


}
