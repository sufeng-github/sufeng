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

import paul.sydney.commen.result.PageResults;
import paul.sydney.model.HongXunMaterialCompilation;
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
   	@RequestMapping("/getEntity")
   	public List<Map<String,Object>> getEntity() {
    	return service.getEntity();
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
    private PageResults<HongXunWeldCompilation> saveRow(@RequestBody List<HongXunWeldCompilation> hongXunWeldCompilationList){
    	PageResults<HongXunWeldCompilation> result = new PageResults<HongXunWeldCompilation>();
    	service.saveRow(hongXunWeldCompilationList);
    	return result;
   	}
    
}