package paul.sydney.service.weiwai;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import paul.sydney.model.HongXunPurchaseNum;
import paul.sydney.model.HongXunWeiwaiItem;

public interface ServiceWeiwaiItemInf {

	List<Map<String, Object>> saveMoney(int iD, BigDecimal value);
	List<Map<String, Object>> loadData();
	List<Map<String, Object>> saveRow(HongXunWeiwaiItem hongXunDataFive);
	List<Map<String, Object>> deleteRow(int iD);
	List<Map<String, Object>> updateRow(HongXunWeiwaiItem hongXunDataFive);
/*	List<HongXunDataFive> quary(HongXunDataFive hongXunDataFive);
	HongXunDataOne quarywithpara(String string);

	List<Map<String, Object>> stockinLoadData();
	List<Map<String, Object>> outstore(String materialNum, int quantity, int uncleID);*/
	List<Map<String, Object>> getWeiwaiChildrens(int uncleID);
	List<Map<String, Object>> searchNum(String weiwaiItem);
	List<Map<String, Object>> autotimp(String q);

}
