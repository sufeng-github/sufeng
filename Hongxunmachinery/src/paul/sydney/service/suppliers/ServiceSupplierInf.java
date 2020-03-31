package paul.sydney.service.suppliers;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import paul.sydney.model.HongXunPurchaseNum;
import paul.sydney.model.HongXunSupplier;

public interface ServiceSupplierInf {

	
	List<Map<String, Object>> saveRow(HongXunSupplier hongXunSupplier);
	List<Map<String, Object>> deleteRow(int iD);
	List<Map<String, Object>> updateRow(HongXunSupplier hongXunSupplier);
	//HongXunPurchaseNum quarywithpara(String str);
	List<Map<String, Object>> loadData();
	boolean excelImport(InputStream is);
	
}
