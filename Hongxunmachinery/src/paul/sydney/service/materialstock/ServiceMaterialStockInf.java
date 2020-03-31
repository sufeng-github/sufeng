package paul.sydney.service.materialstock;

import java.util.List;
import java.util.Map;

import paul.sydney.commen.result.BaseResult;
import paul.sydney.commen.result.PageResults;
import paul.sydney.model.HongXunMaterialItemOutStock;
import paul.sydney.model.HongXunMaterialStock;
import paul.sydney.model.HongXunPurchaseItemInStock;

public interface ServiceMaterialStockInf {

	//List<Map<String, Object>> loadData();

	List<Map<String, Object>> autotimp(String q);
	List<Map<String, Object>> saveRow(HongXunMaterialStock hongXunMaterialStock);
	List<Map<String, Object>> updateRow(HongXunMaterialStock hongXunMaterialStock);
	List<Map<String, Object>> getEntity();
	List<Map<String, Object>> delAllRows();
	BaseResult deleteRow(HongXunMaterialStock item);
	PageResults<HongXunMaterialStock> getData(HongXunMaterialStock hongXunMaterialStock, Integer pageNo, Integer pageSize);
	//BaseResult deleteRow(int id);

}
