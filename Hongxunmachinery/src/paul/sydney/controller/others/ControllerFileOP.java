package paul.sydney.controller.others;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import paul.sydney.model.HongXunFile;
import paul.sydney.service.others.ServiceFileopInf;
@Transactional
@Controller 
@RequestMapping("/fileop.htm") 
public class ControllerFileOP {
    private final ServiceFileopInf service;
    @Autowired 
    public ControllerFileOP(ServiceFileopInf service){
        this.service = service; 
    }
    
    //private String filename;
    private int fileId;
    
    @ResponseBody
   	@RequestMapping("/loadData")
   	public List<Map<String,Object>> loadData() throws SQLException, IOException {
    	//System.out.println("loadData");
    	//String id=request.getParameter("id");
    	//System.out.println("loadData id:" + id);
    	List<Map<String,Object>> list = service.loadData();
    	
   		return list;
    	
   	} 
 
    @ResponseBody
   	@RequestMapping("/add")
   	public List<Map<String,Object>> add(@RequestParam(value = "parentId", required = true) int parentId) {
    	System.out.println("add");
   		return service.add(parentId);
    	
   	}     
    
    @ResponseBody
   	@RequestMapping("/update")
   	public Boolean update(@RequestParam(value = "nodeId", required = true) int id,@RequestParam(value = "text", required = true) String name) {
    	System.out.println("update");
    	Boolean result = service.update(id,name);
    	
   		return result;
    	
   	} 
    
    @ResponseBody
   	@RequestMapping("/delete")
   	public Boolean delete(@RequestParam(value = "nodeId", required = true) int id){
    	System.out.println("delete");
    	Boolean result = service.delete(id);
    	
   		return result;
    	
   	} 
    @ResponseBody
    @RequestMapping(value = "/writeFile", produces = "application/json;charset=UTF-8")
    public  List<Map<String,Object>> writeFile(@RequestParam("file_upload") MultipartFile [] files, @RequestParam("parentId") int parentId) throws IOException {
			//System.out.println("success" + id);
    		return service.wirteFile(files, parentId);
    }
    
    @ResponseBody
   	@RequestMapping("/autotimp")
   	public List<Map<String,Object>> autotimp(HttpServletRequest request) {
    	String q=request.getParameter("q");
    	//System.out.println("autotimp:" + q); 	
   		return service.autotimp(q);  
   	} 
    
    /*  
    @RequestMapping(value = "/readFileName")
    @ResponseBody
    public void readFileName(String filePath, HttpServletRequest request, HttpServletResponse response) throws SQLException {
    	HongXunFile hongXunFile = service.readFile(filename);
    	if(hongXunFile != null){
	    	Blob blob = hongXunFile.getMyfile();
	    	byte[] data = blob.getBytes(1, (int)blob.length());
	    	//System.out.println(data.length);
	    	//filePath = "d://abc.pdf";
	        //File file = new File(filePath);
	        //byte[] data = null;
	        try {
	            //FileInputStream input = new FileInputStream(file);
	            //data = new byte[input.available()];
	            //input.read(data);
	            response.getOutputStream().write(data);
	            //input.close();
	        } catch (Exception e) {
	           
	        }
    	}
    }*/

    @ResponseBody
   	@RequestMapping("/fileId")
    public Boolean fileIndex(@RequestParam(value = "id", required = true) int idc)  {
    	//System.out.println(idc);
    	fileId = idc;	
		return true;	
    }
    
    @ResponseBody
   	@RequestMapping("/fileName")
    public Boolean fileIndex(@RequestParam(value = "name", required = true) String name)  {
    	System.out.println(name);
    	fileId = service.getFileId(name);	
		return true;	
    }
    
    @RequestMapping(value = "/readFile")
    @ResponseBody
    public void readFile(String filePath, HttpServletRequest request, HttpServletResponse response) throws SQLException {
    	String id=request.getParameter("data");
    	System.out.println("id: " + id);
    	//System.out.println("userName:" + userName);
    	HongXunFile hongXunFile = service.readFile(fileId);
    	if(hongXunFile != null){
	    	Blob blob = hongXunFile.getMyfile();
	    	byte[] data = blob.getBytes(1, (int)blob.length());
	        try {
	            response.getOutputStream().write(data);
	        } catch (Exception e) {
	           
	        }
    	}
    }
}

/*    @RequestMapping({ "/showPdf.do" })
public String showPdf(HttpServletRequest request, HttpServletResponse response) throws Exception {
	try {
		System.out.println("asdfasf");
		InputStream inputStream = service.readFile().getMyfile().getBinaryStream();
		response.setHeader("Content-Disposition", "attachment;fileName=结业.pdf");
	    response.setContentType("multipart/form-data");
	    OutputStream outputStream = response.getOutputStream();
	    IOUtils.write(IOUtils.toByteArray(inputStream), outputStream);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}*/