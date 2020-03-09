package paul.sydney.controller.weld;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import paul.sydney.model.HongXunWeldCompilation;
import paul.sydney.service.weld.ServiceWeldCompilationInf;



@Controller 
@RequestMapping("/weldCompilation.htm") 
public class ControllerWeldCompilation {
    private final ServiceWeldCompilationInf service;
    @Autowired 
    public ControllerWeldCompilation(ServiceWeldCompilationInf service){
        this.service = service; 
    }
    
  
    @ResponseBody
   	@RequestMapping("/loadData")
    private List<Map<String,Object>> loadData(){    	
    	// TODO Auto-generated catch block
    	return service.loadData();
    }	   
    @ResponseBody
   	@RequestMapping("/getMaterialAlarms")
    private List<Map<String,Object>> getMaterialAlarms(@RequestParam(value = "alarm", required = true) String alarm){    	
    	// TODO Auto-generated catch block
    	return service.getMaterialAlarms(alarm);
    }	
    
/*    @ResponseBody
   	@RequestMapping("/autotimp")
   	public List<Map<String,Object>> autotimp(HttpServletRequest request) {
    	String q=request.getParameter("q");
    	//System.out.println("autotimp:" + q); 	
   		return service.autotimp(q);  
   	} */
    
    @ResponseBody
   	@RequestMapping("/searchMaterialNum")
    private List<Map<String,Object>> searchPurchaseItem(@RequestParam(value = "name", required = true) String materialNum){
    	//System.out.println(materialNum);
    	List<Map<String,Object>> list = service.searchMaterialNum(materialNum);
		return list;
    	
    }
    
    @ResponseBody
   	@RequestMapping("/getEntity")
   	public List<Map<String,Object>> getEntity() {
    	return service.getEntity();
   	} 
    
    @ResponseBody
   	@RequestMapping("/weldCompilation")
    private List<Map<String,Object>> scanOutMaterialNum(@RequestParam(value = "name", required = true) String materialNum,
    									
    													@RequestParam(value = "quantity", required = true) int quantity){
    	
    	//System.out.println(materialNum);
    	List<Map<String,Object>> list = service.scanMaterialCompilation(materialNum,  quantity);
		return list;	
    }
    
    @ResponseBody
   	@RequestMapping("/synchronization")
    private List<Map<String,Object>> synchronization(){
    	
    	//System.out.println("synchronization");
    	List<Map<String,Object>> list = service.synchronization();
		return list;	
    }
    
    @ResponseBody
   	@RequestMapping("/saveRow")
    private List<Map<String,Object>> saveRow(@RequestBody List<HongXunWeldCompilation> HongXunWeldCompilationList){
    	
/*	    if(HongXunMaterialCompilationList.get(0).getIdc()==0){  
	    	return service.saveRow(HongXunMaterialCompilationList.get(0));
	    }else{	
	    	return service.updateRow(HongXunMaterialCompilationList.get(0));
	    }*/
    	//System.out.println("HongXunMaterialCompilation saveRow");
    	 List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	for(HongXunWeldCompilation item : HongXunWeldCompilationList){ 		
    		if(item.getIdc()==0){
				list.addAll(service.saveRow(item));	
    		}else{   			
    			list.addAll(service.updateRow(item));
    		}	
    	}
    	return list;
   	}
    
    @ResponseBody
   	@RequestMapping("/xlf")
    private List<Map<String,Object>> xlf(@RequestBody List<HongXunWeldCompilation> hongXunWeldCompilationList,
    		@RequestParam(value = "date", required = true) String date){  
    	return service.xlf(hongXunWeldCompilationList,date);
   	}
    
   /* @ResponseBody
    @RequestMapping(value="/exlImport",method=RequestMethod.POST)
    public List<Map<String,Object>> exlImport(HttpServletRequest request) {
    	//System.out.println("exlImport");
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
						list = service.exlImport(workbook);
						
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
                	
                }                
            }
            return list;
        }else{
        	return list;
        } 
    }*/
}