package paul.sydney.service.work;

import java.util.List;
import java.util.Map;

import paul.sydney.model.HongXunWorkItem;
import paul.sydney.model.HongXunWorkNum;

public interface ServiceWorkItemInf {

	List<Map<String, Object>> saveRow(HongXunWorkItem item);
	List<Map<String, Object>> deleteRow(HongXunWorkItem item);
	List<Map<String, Object>> updateRow(HongXunWorkItem item);
	List<HongXunWorkNum> quary(HongXunWorkNum hongXunDataThree);
	List<Map<String, Object>> getWorkNumItems(int i);

}
