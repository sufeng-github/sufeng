package paul.sydney.service.work;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import paul.sydney.model.HongXunBomTree;
import paul.sydney.model.HongXunPoN;
import paul.sydney.model.HongXunPurchaseNum;
import paul.sydney.model.HongXunWorkNum;

public interface ServiceWorkNumInf {

	List<Map<String, Object>> loadData();

	List<Map<String, Object>> saveRow(HongXunWorkNum hongXunDataThree);
	//List<Map<String, Object>> deleteRow(HongXunWorkNum hongXunDataThree);

	List<Map<String, Object>> updateRow(HongXunWorkNum hongXunDataThree);

	List<HongXunWorkNum> quary(HongXunWorkNum hongXunDataThree);

	//void mapHongXunDataThree(Map<String, Object> map, HongXunWorkNum item);

	List<Map<String, Object>> betweenDateFind(String date1, String date2) throws ParseException;

	List<Map<String, Object>> searchNum(String database, String filed, String num);

	Boolean deleteRow(int iD);

	List<Map<String, Object>> getNumthreeFathers(HongXunWorkNum hongXunDataThree);

	void saveWorkItem(List<HongXunBomTree> hongXunBomTreeList, HongXunWorkNum hongXunWorkNum);

	List<Map<String, Object>> searchWorkNum(String workNum);

	List<Map<String, Object>> autotimp(String q);

}
