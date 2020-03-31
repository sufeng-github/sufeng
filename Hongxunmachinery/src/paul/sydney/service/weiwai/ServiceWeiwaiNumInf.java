package paul.sydney.service.weiwai;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import paul.sydney.model.HongXunWeiwaiItem;
import paul.sydney.model.HongXunWeiwaiNum;

public interface ServiceWeiwaiNumInf {

	List<Map<String, Object>> loadData();
	List<Map<String, Object>> saveRow(HongXunWeiwaiNum hongXunDataFour);
	List<Map<String, Object>> updateRow(HongXunWeiwaiNum hongXunDataFour);
	//Boolean deleteRow(HongXunDataFour hongXunDataFour);

/*	

	//List<HongXunDataFour> quary(HongXunDataFour hongXunDataFour);

	//void mapHongXunDataFour(Map<String, Object> map, HongXunDataFour item);

	List<Map<String, Object>> betweenDateFind(Date date, Date date2) throws ParseException;

	List<Map<String, Object>> searchNum(HongXunDataFour hongXunDataFour);

	List<Map<String, Object>> refresh(List<HongXunDataFour> hongXunDataFourList);
*/
	Boolean deleteRow(int ID);
	void save(HongXunWeiwaiItem hongXunDataFive);
	List<Map<String, Object>> betweenDateFind(String date1, String date2) throws ParseException;
	List<Map<String, Object>> searchWeiwaikNum(String workNum);
	List<Map<String, Object>> autotimp(String q);

}
