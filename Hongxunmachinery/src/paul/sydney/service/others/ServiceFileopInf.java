package paul.sydney.service.others;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import paul.sydney.model.HongXunFile;

public interface ServiceFileopInf {
	//HongXunFile readFile();
	//List<Map<String, Object>> readFile() throws SQLException, IOException;
	//List<Map<String, Object>> saveFile(String filePath) throws IOException;
	Map<String, Object> saveFile(String name, byte[] bs, int parentId);
	//HongXunFile readFile(String name);
	HongXunFile readFile(int idc);
	List<Map<String, Object>> loadData();
	Boolean update(int id, String name);
	Boolean delete(int id);
	List<Map<String, Object>> add(int parentId);
	List<Map<String, Object>> wirteFile(MultipartFile[] files, int parentId) throws IOException;
	List<Map<String, Object>> autotimp(String q);
	int getFileId(String name);
}
