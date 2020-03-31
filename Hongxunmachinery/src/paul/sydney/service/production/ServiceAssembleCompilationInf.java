package paul.sydney.service.production;

import java.util.List;
import java.util.Map;

import paul.sydney.commen.result.PageResults;
import paul.sydney.model.HongXunAssembleCompilation;


public interface ServiceAssembleCompilationInf {

	//List<Map<String, Object>> loadData();

	//List<Map<String, Object>> autotimp(String q);

	//List<Map<String, Object>> searchMaterialNum(String materialNum);

	//List<Map<String, Object>> saveRow(HongXunAssembleCompilation item);

	//List<Map<String, Object>> updateRow(HongXunAssembleCompilation hongXunAssembleCompilation);

	List<Map<String, Object>> getEntity();

	//List<Map<String, Object>> getMaterialAlarms(String alarm);

	//List<Map<String, Object>> scanMaterialCompilation(String materialNum, int quantity);

	List<Map<String, Object>> synchronization();

	//List<Map<String, Object>> xlf(List<HongXunAssembleCompilation> hongXunAssembleCompilationList,String date);

/*	PageResults<HongXunAssembleCompilation> getData(HongXunAssembleCompilation hongXunAssembleCompilation,
			Integer pageNo, Integer pageSize);*/

	void saveRow(List<HongXunAssembleCompilation> hongXunAssembleCompilationList);

	//List<Map<String, Object>> xlf(List<HongXunMaterialCompilation> hongXunMaterialCompilationList);

}
