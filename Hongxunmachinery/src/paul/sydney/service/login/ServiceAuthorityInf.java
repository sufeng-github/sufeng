package paul.sydney.service.login;


import java.util.List;
import java.util.Map;

import paul.sydney.model.HongXunAuthority;
import paul.sydney.model.HongXunPoN;

public interface ServiceAuthorityInf {
	boolean login(String userName, String password);
	List<Map<String, Object>> loadData();
	List<Map<String, Object>> save(HongXunAuthority hongXunAuthority);
	List<Map<String, Object>> deleteRow(int id);
	List<Map<String, Object>> update(HongXunAuthority hongXunAuthority);
	List<Map<String, Object>> getMessage(int id);

}
