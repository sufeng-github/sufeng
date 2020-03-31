package paul.sydney.controller.materialstock;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import paul.sydney.commen.result.PageResults;
import paul.sydney.model.HongXunMaterialCompilation;
import paul.sydney.service.materialstock.ServiceMaterialCompilationInf;

@Controller 
@RequestMapping("/materialCompilation.htm") 
public class ControllerMaterialCompilation {
    private final ServiceMaterialCompilationInf service;
    @Autowired 
    public ControllerMaterialCompilation(ServiceMaterialCompilationInf service){
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
    	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    	service.synchronization();
		return list;	
    }
    
    @ResponseBody
   	@RequestMapping("/saveRow")
    private PageResults<HongXunMaterialCompilation> saveRow(@RequestBody List<HongXunMaterialCompilation> HongXunMaterialCompilationList){
    	PageResults<HongXunMaterialCompilation> result = new PageResults<HongXunMaterialCompilation>();
    	service.saveRow(HongXunMaterialCompilationList);
    	return result;
   	}
    
   
}