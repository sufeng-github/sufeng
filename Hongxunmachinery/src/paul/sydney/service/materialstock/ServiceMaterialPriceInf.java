package paul.sydney.service.materialstock;

import java.util.List;
import java.util.Map;
import paul.sydney.model.HongXunSuppliersPrice;

public interface ServiceMaterialPriceInf {

	List<Map<String, Object>> loadData();

	List<Map<String, Object>> autotimp(String q);

	//List<Map<String, Object>> searchMaterialNum(String materialNum);

	List<Map<String, Object>> saveRow(HongXunSuppliersPrice hongXunSuppliersPrice);

	List<Map<String, Object>> updateRow(HongXunSuppliersPrice hongXunSuppliersPrice);

	List<Map<String, Object>> getEntity();

	//List<Map<String, Object>> getMaterialAlarms(String alarm);

	List<Map<String, Object>> deleteRow(List<HongXunSuppliersPrice> hongXunSuppliersPriceList);

	List<Map<String, Object>> saveOrUpdateRow(List<HongXunSuppliersPrice> hongXunSuppliersPriceList);


}
