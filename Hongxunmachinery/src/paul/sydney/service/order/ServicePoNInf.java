package paul.sydney.service.order;

import java.io.InputStream;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import paul.sydney.model.HongXunAuthority;
import paul.sydney.model.HongXunPoN;

public interface ServicePoNInf {

	List<Map<String, Object>> loadData();

	List<Map<String, Object>> saveRow(HongXunPoN hongXunPoN);
	List<Map<String, Object>> deleteRow(int id);

	List<Map<String, Object>> updateRow(HongXunPoN hongXunPoN);

	//List<HongXunPoN> quary(HongXunPoN hongXunPoN);

	//void mapHongXunPoN(Map<String, Object> map, HongXunPoN item);

	//List<Map<String, Object>> betweenDateFind(String date1, String date2) throws ParseException;

	List<Map<String, Object>> searchNum(Integer integer, String poNum);

	//List<Map<String, Object>> orderNumImport(InputStream is);

	List<Map<String, Object>> betweenDateFind(String date1, String date2) throws ParseException;

	List<Map<String, Object>> autotimp(Integer integer, String q);

	List<Map<String, Object>> orderNumGetList(int orderNumID);

	List<Map<String, Object>> updateState(int orderNumID, String state);
	List<Map<String, Object>> decrease(int id);

	List<Map<String, Object>> ponNumList(String pon);
}
