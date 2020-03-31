package paul.sydney.controller.production;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import paul.sydney.commen.result.PageResults;
import paul.sydney.model.HongXunAssembleCompilation;
import paul.sydney.model.HongXunMaterialCompilation;
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
   	@RequestMapping("/getEntity")
   	public List<Map<String,Object>> getEntity() {
    	return service.getEntity();
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
    private PageResults<HongXunMaterialCompilation> saveRow(@RequestBody List<HongXunAssembleCompilation> hongXunAssembleCompilationList){   	
    	PageResults<HongXunMaterialCompilation> result = new PageResults<HongXunMaterialCompilation>();
    	service.saveRow(hongXunAssembleCompilationList);
    	return result;
   	}
    
    

}