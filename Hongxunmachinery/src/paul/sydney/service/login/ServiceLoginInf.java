package paul.sydney.service.login;

import java.util.List;

public interface ServiceLoginInf {
	//List<Map<String, Object>> login(Login login);
	boolean login(String userName, String password);
	List<Integer> getAuthority(String userName, String password);
}
