package paul.sydney.service.weld;

import java.util.List;
import java.util.Map;
import paul.sydney.commen.result.PageResults;
import paul.sydney.model.HongXunProductionWeldStock;

public interface ServiceProductionWeldStockInf {


	List<Map<String, Object>> autotimp(String q);

	List<Map<String, Object>> searchMaterialNum(String materialNum);

	List<Map<String, Object>> saveRow(HongXunProductionWeldStock HongXunProductionWeldStock);

	List<Map<String, Object>> updateRow(HongXunProductionWeldStock HongXunProductionWeldStock);

	List<Map<String, Object>> getEntity();

	List<Map<String, Object>> delAllRows();

	PageResults<HongXunProductionWeldStock> getData(HongXunProductionWeldStock hongXunProductionWekdStock, Integer pageNo, Integer pageSize);

	List<Map<String, Object>> deleteRow(HongXunProductionWeldStock item);

}
