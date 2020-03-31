package paul.sydney.service.weld;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import paul.sydney.model.HongXunWeldCompilation;


public interface ServiceWeldCompilationInf {

//	List<Map<String, Object>> loadData();

/*	List<Map<String, Object>> autotimp(String q);*/

//	List<Map<String, Object>> searchMaterialNum(String materialNum);

//	List<Map<String, Object>> saveRow(HongXunWeldCompilation item);

//	List<Map<String, Object>> updateRow(HongXunWeldCompilation hongXunWeldCompilation);

	List<Map<String, Object>> getEntity();

//	List<Map<String, Object>> getMaterialAlarms(String alarm);

//	List<Map<String, Object>> scanMaterialCompilation(String materialNum, int quantity);

	List<Map<String, Object>> synchronization();

//	List<Map<String, Object>> xlf(List<HongXunWeldCompilation> hongXunWeldCompilationList, String date);

	void saveRow(List<HongXunWeldCompilation> hongXunWeldCompilationList);

	//List<Map<String, Object>> exlImport(HSSFWorkbook workbook);

}
