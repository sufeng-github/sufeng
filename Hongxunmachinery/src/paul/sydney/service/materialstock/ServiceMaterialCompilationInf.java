package paul.sydney.service.materialstock;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import paul.sydney.model.HongXunMaterialCompilation;
import paul.sydney.model.HongXunMaterialItemOutStock;
import paul.sydney.model.HongXunMaterialStock;
import paul.sydney.model.HongXunPurchaseItemInStock;

public interface ServiceMaterialCompilationInf {

	List<Map<String, Object>> loadData();

	//List<Map<String, Object>> autotimp(String q);

	List<Map<String, Object>> searchMaterialNum(String materialNum);

	List<Map<String, Object>> saveRow(HongXunMaterialCompilation hongXunMaterialCompilation);

	List<Map<String, Object>> updateRow(HongXunMaterialCompilation hongXunMaterialCompilation);

	List<Map<String, Object>> getEntity();

	List<Map<String, Object>> getMaterialAlarms(String alarm);

	List<Map<String, Object>> scanMaterialCompilation(String materialNum, int quantity);

	List<Map<String, Object>> synchronization();

	//List<Map<String, Object>> compilationImport(HSSFWorkbook workbook);

	List<Map<String, Object>> xlf(List<HongXunMaterialCompilation> hongXunMaterialCompilationList, String date);

}
