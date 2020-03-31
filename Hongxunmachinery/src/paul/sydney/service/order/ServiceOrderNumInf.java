package paul.sydney.service.order;

import java.util.List;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import paul.sydney.commen.result.PageResults;
import paul.sydney.model.HongXunOrderNum;

public interface ServiceOrderNumInf {

	List<Map<String, Object>> saveRow(HongXunOrderNum hongXunOrderNum);
	List<Map<String, Object>> deleteRow(HongXunOrderNum hongXunOrderNum);
	List<Map<String, Object>> updateRow(HongXunOrderNum hongXunOrderNum);
	List<Map<String, Object>> getEntity();
	List<Map<String, Object>> autotimp(String q);
	List<Map<String, Object>> orderNumImport(HSSFWorkbook workbook);
	PageResults<HongXunOrderNum> getData(HongXunOrderNum hongXunOrderNum, Integer pageNo, Integer pageSize);
}
