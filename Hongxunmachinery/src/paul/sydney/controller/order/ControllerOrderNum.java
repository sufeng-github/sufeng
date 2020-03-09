package paul.sydney.controller.order;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import paul.sydney.model.HongXunOrderNum;
import paul.sydney.service.order.ServiceOrderNumInf;

@Transactional
@Controller 
@RequestMapping("/orderNum.htm") 
public class ControllerOrderNum {
    private final ServiceOrderNumInf service;
    @Autowired 
    public ControllerOrderNum(ServiceOrderNumInf service){
        this.service = service; 
    }
    
    @ResponseBody
    @RequestMapping(value="/exlImport",method=RequestMethod.POST)
    public List<Map<String,Object>> exlImport(HttpServletRequest request) {
    	System.out.println("exlImport");
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(request.getSession().getServletContext());
        //检查form中是否有enctype="multipart/form-data"
        if(multipartResolver.isMultipart(request)){
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
           //获取multiRequest 中所有的文件名
            Iterator<String> iter=multiRequest.getFileNames();        
            while(iter.hasNext()){
                MultipartFile file=multiRequest.getFile(iter.next());
                if(file!=null){
                	//InputStream is = null;
                	POIFSFileSystem fs = null;
                	HSSFWorkbook workbook = null;
					try {
						//is = file.getInputStream();
						fs = new POIFSFileSystem(file.getInputStream());
						workbook = new HSSFWorkbook(fs);
						list = service.orderNumImport(workbook);
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally{
						try {
							workbook.close();
							fs.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}						
					}
                	/*try {
						list = service.orderNumImport(is);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
                }                
            }
            return list;
        }else{
        	return list;
        } 
    }
    
    @ResponseBody
   	@RequestMapping("/loadData")
   	private List<Map<String,Object>> loadData() {
    	List<Map<String,Object>> list = service.loadData();
    	//System.out.println("orderNum");
   		return list;
   	}  
    
    @ResponseBody
   	@RequestMapping("/getEntity")
   	public List<Map<String,Object>> getEntity() {
    	return service.getEntity();
   	} 
    
    @ResponseBody
   	@RequestMapping("/changeStatus")
   	public List<Map<String,Object>> changeStatus(@RequestParam(value = "orderNumID", required = true) int id){
    		//System.out.println("changeStatus");
    	return service.changeStatus(id);
   	} 
    
    
    @ResponseBody
   	@RequestMapping("/saveRow")
   	private List<Map<String,Object>> saveRow(@RequestBody List<HongXunOrderNum> HongXunOrderNumList) throws ParseException {
    	//System.out.println("saveRow: ");
    	List<Map<String, Object>> list = null;
    	for(HongXunOrderNum item : HongXunOrderNumList){ 		
			if(item.getIdc()==0){
				if(list != null){
					list.containsAll(service.saveRow(item));
				}else{
					list = service.saveRow(item);
				}
    		}else{
    			if(list != null){
    				list.containsAll(service.updateRow(item));
    			}else{
    				list = service.updateRow(item);
    			}
    		}	
    	}
    	return list;
   	}

    
    
    @ResponseBody
   	@RequestMapping("/deleteRow")
   	private List<Map<String,Object>> deleteRow(@RequestBody List<HongXunOrderNum> HongXunOrderNumList) throws ParseException {	
   		//System.out.println("deleteRow");
   		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
   		for(HongXunOrderNum item: HongXunOrderNumList){
   			if(item.getIdc()!=0){
   				list = service.deleteRow(item);
   			}
   		}
   		return list;
   	} 
    
    @ResponseBody
   	@RequestMapping("/searchOrderNum")
    private List<Map<String,Object>> searchNum(@RequestParam(value = "name", required = true) String orderNum){
    	//System.out.println(orderNum);
    	List<Map<String,Object>> list = service.searchNum(orderNum);
		return list;
    	
    }
    
    @ResponseBody
   	@RequestMapping("/autotimp")
   	public List<Map<String,Object>> autotimp(HttpServletRequest request) {
    	String q=request.getParameter("q");
    	//System.out.println("autotimp:" + q); 	
   		return service.autotimp(q);  
   	}
    @ResponseBody
   	@RequestMapping("/betweenDateFind")
    private List<Map<String,Object>> betweenDateFind(@RequestParam(value = "beginDate", required = true) String date1,@RequestParam(value = "endDate", required = true) String date2) throws ParseException{

    	List<Map<String,Object>> list = service.betweenDateFind(date1, date2);
   		return list;
   	} 
}