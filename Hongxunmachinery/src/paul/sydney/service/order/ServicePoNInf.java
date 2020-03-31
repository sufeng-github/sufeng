package paul.sydney.service.order;

import java.util.List;
import java.util.Map;
import paul.sydney.commen.result.PageResults;
import paul.sydney.model.HongXunPoN;

public interface ServicePoNInf {

	//List<Map<String, Object>> loadData();
	List<Map<String, Object>> saveRow(HongXunPoN hongXunPoN);
	List<Map<String, Object>> deleteRow(int id);
	List<Map<String, Object>> updateRow(HongXunPoN hongXunPoN);
	List<Map<String, Object>> autotimp(Integer integer, String q);
	List<Map<String, Object>> orderNumGetList(int orderNumID);
	List<Map<String, Object>> updateState(int orderNumID, String state);
	List<Map<String, Object>> decrease(int id);
	List<Map<String, Object>> ponNumList(String pon);
	PageResults<HongXunPoN> getData(HongXunPoN hongXunPoN, Integer pageNo, Integer pageSize);

}
