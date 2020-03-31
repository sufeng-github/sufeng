package paul.sydney.service.others;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import paul.sydney.model.HongXunPurchaseNum;
import paul.sydney.model.HongXunSpecialMaterialNum;
import paul.sydney.model.HongXunSupplier;

public interface ServiceSpecialMaterialNumInf {

	
	List<Map<String, Object>> saveRow(HongXunSpecialMaterialNum hongXunSpecialMaterialNum);
	List<Map<String, Object>> deleteRow(int iD);
	List<Map<String, Object>> updateRow(HongXunSpecialMaterialNum hongXunSpecialMaterialNum);
	//HongXunPurchaseNum quarywithpara(String str);
	List<Map<String, Object>> loadData();
	boolean excelImport(InputStream is);
	List<Map<String, Object>> getEntity();
	
}
