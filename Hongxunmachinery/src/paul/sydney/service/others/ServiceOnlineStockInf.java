package paul.sydney.service.others;

import java.util.List;
import java.util.Map;
import paul.sydney.model.HongXunOnlineStock;

public interface ServiceOnlineStockInf {

	List<Map<String, Object>> loadData();

	List<Map<String, Object>> autotimp(String q);

	List<Map<String, Object>> searchMaterialNum(String materialNum);

	List<Map<String, Object>> saveRow(HongXunOnlineStock hongXunOnlineStock);

	List<Map<String, Object>> updateRow(HongXunOnlineStock hongXunOnlineStock);

	List<Map<String, Object>> getEntity();

	List<Map<String, Object>> delAllRows();


}
