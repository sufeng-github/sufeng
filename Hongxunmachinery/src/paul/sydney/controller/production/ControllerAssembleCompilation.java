package paul.sydney.controller.production;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import paul.sydney.model.HongXunAssembleCompilation;
import paul.sydney.service.materialstock.ServiceMaterialCompilationInf;
import paul.sydney.service.materialstock.ServiceMaterialStockInf;
import paul.sydney.service.production.ServiceAssembleCompilationInf;


@Controller 
@RequestMapping("/assembleCompilation.htm") 
public class ControllerAssembleCompilation {
    private final ServiceAssembleCompilationInf service;
    @Autowired 
    public ControllerAssembleCompilation(ServiceAssembleCompilationInf service){
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
    	System.out.println(materialNum);
    	List<Map<String,Object>> list = service.searchMaterialNum(materialNum);
		return list;
    	
    }
    
    @ResponseBody
   	@RequestMapping("/getEntity")
   	public List<Map<String,Object>> getEntity() {
    	return service.getEntity();
   	} 
    
    @ResponseBody
   	@RequestMapping("/scanAssembleCompilation")
    private List<Map<String,Object>> scanOutMaterialNum(@RequestParam(value = "name", required = true) String materialNum,
    									
    													@RequestParam(value = "quantity", required = true) int quantity){
    	
    	//System.out.println(materialNum);
    	List<Map<String,Object>> list = service.scanMaterialCompilation(materialNum,  quantity);
		return list;	
    }
    
    @ResponseBody
   	@RequestMapping("/synchronization")
    private List<Map<String,Object>> synchronization(){
    	
    	System.out.println("synchronization");
    	List<Map<String,Object>> list = service.synchronization();
		return list;	
    }
    
    @ResponseBody
   	@RequestMapping("/saveRow")
    private List<Map<String,Object>> saveRow(@RequestBody List<HongXunAssembleCompilation> HongXunAssembleCompilationList){
    	
/*	    if(HongXunMaterialCompilationList.get(0).getIdc()==0){  
	    	return service.saveRow(HongXunMaterialCompilationList.get(0));
	    }else{	
	    	return service.updateRow(HongXunMaterialCompilationList.get(0));
	    }*/
    	System.out.println("HongXunMaterialCompilation saveRow");
    	 List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	for(HongXunAssembleCompilation item : HongXunAssembleCompilationList){ 		
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
    private List<Map<String,Object>> xlf(@RequestBody List<HongXunAssembleCompilation> hongXunAssembleCompilationList,
    		@RequestParam(value = "date", required = true) String date ){   	   	
    	return service.xlf(hongXunAssembleCompilationList, date);
   	}

}