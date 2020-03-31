package paul.sydney.service.production;

import java.util.List;
import java.util.Map;

import paul.sydney.commen.result.PageResults;
import paul.sydney.model.HongXunDeliveryNum;

public interface ServiceDeliveryNumInf {


	List<Map<String, Object>> autotimp(String q);
	List<Map<String, Object>> getEntity();
	void saveRow(HongXunDeliveryNum item);
	void updateRow(HongXunDeliveryNum item);
	void deleteRow(int id);
	PageResults<HongXunDeliveryNum> getData(HongXunDeliveryNum hongXunDeliveryNum, Integer pageNo, Integer pageSize);

}
