package paul.sydney.service.others;

import java.util.List;
import java.util.Map;

public interface ServiceCabinInf {
	List<Map<String, Object>> leftTopOne();
	List<Map<String, Object>> leftTopTwo();
	List<Map<String, Object>> leftTopThree();
	List<Map<String, Object>> leftTopFour();
	List<Map<String, Object>> rightPartOne();
	List<Map<String, Object>> rightPartTwo();
	List<Map<String, Object>> rightPartFour();
	List<Map<String, Object>> leftBottomOne();
	List<Map<String, Object>> leftBottomTwo();
	List<Map<String, Object>> leftBottomThree();
	List<Map<String, Object>> rightPartThree();
	List<Map<String, Object>> rightPartFive();
}
