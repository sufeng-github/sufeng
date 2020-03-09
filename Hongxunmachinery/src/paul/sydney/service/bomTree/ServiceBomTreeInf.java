package paul.sydney.service.bomTree;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import paul.sydney.model.HongXunBomTree;
import paul.sydney.model.HongXunPoN;

public interface ServiceBomTreeInf {

	//List<Map<String, Object>> loadData();

	List<HongXunBomTree> quary(HongXunBomTree hongXunBomTree);

	List<Map<String, Object>> saveRow(HongXunBomTree hongXunBomTree);

	List<Map<String, Object>> updateRow(HongXunBomTree hongXunBomTree);

	

	//List<Map<String, Object>> getList(int iD);

	List<Map<String, Object>> getList(String sql, String i);

	//List<Map<String, Object>> getDataMsg(HongXunBomTree hongXunBomTree);

	List<Map<String, Object>> excelImport(InputStream is, String nodeID);
	List<Map<String, Object>> excelImport(HSSFWorkbook workbook, String nodeID);
	
	void createRootBom(List<Map<String, Object>> list);

	List<Map<String, Object>> searchBomNum(String num);

	//List<Map<String, Object>> searchBomAttribute(String attribute);

	List<Map<String, Object>> autotimp(String q);

	List<Map<String, Object>> onselect(String materialNum);

	List<Map<String, Object>> searchPurchaseItem(String purchaseItem);

	List<Map<String, Object>> analysis(List<HongXunPoN> hongXunPoNList);

	List<Map<String, Object>> findParent(String num);
	void deleteRow(HongXunBomTree hongXunBomTree);

	List<Map<String, Object>> qrcodeImport(InputStream is);

	void oneKeyExportExcel(HttpServletRequest request, HttpServletResponse response);

	boolean getTablesStatus(String string);

	void setTablesStatus(String string, boolean flag);

	List<Map<String, Object>> getAnalysisItem(String listItem);

	int getNodeID(String id);

	List<Map<String, Object>> barcodeImport(InputStream is, String fileName);

	void download(HttpServletRequest request, HttpServletResponse response);

	List<Map<String, Object>> sendbarcodeImport(InputStream is, String fileName);

	

	//List<Map<String, Object>> getParentID(int i);

	//List<Map<String, Object>> pickTables(List<HongXunPoN> hongXunPoNList);

	//List<Map<String, Object>> loadData();




}
