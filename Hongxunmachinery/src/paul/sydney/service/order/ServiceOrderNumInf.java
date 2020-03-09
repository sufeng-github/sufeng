package paul.sydney.service.order;

import java.io.InputStream;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import paul.sydney.model.HongXunOrderNum;

public interface ServiceOrderNumInf {

	List<Map<String, Object>> loadData();

	List<Map<String, Object>> saveRow(HongXunOrderNum hongXunOrderNum);
	List<Map<String, Object>> deleteRow(HongXunOrderNum hongXunOrderNum);
	List<Map<String, Object>> updateRow(HongXunOrderNum hongXunOrderNum);
	//List<Map<String, Object>> orderNumImport(InputStream is) throws Exception;
	List<Map<String, Object>> getEntity();
	List<Map<String, Object>> changeStatus(int id);
	List<Map<String, Object>> betweenDateFind(String date1, String date2) throws ParseException;

	List<Map<String, Object>> searchNum(String orderNum);

	List<Map<String, Object>> autotimp(String q);

	List<Map<String, Object>> orderNumImport(HSSFWorkbook workbook);



}
