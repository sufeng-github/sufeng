package paul.sydney.service.materialstock;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import paul.sydney.commen.result.PageResults;
import paul.sydney.model.HongXunMaterialCompilation;
import paul.sydney.model.HongXunMaterialItemOutStock;
import paul.sydney.model.HongXunMaterialStock;
import paul.sydney.model.HongXunPurchaseItemInStock;

public interface ServiceMaterialCompilationInf {

	void saveRow(List<HongXunMaterialCompilation> hongXunMaterialCompilationList);
	List<Map<String, Object>> getEntity();
	void synchronization();
	PageResults<HongXunMaterialCompilation> getData(HongXunMaterialCompilation hongXunMaterialCompilation,
			Integer pageNo, Integer pageSize);

}
