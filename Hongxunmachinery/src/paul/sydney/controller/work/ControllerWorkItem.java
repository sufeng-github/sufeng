package paul.sydney.controller.work;


import java.text.ParseException;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import paul.sydney.model.HongXunWorkItem;
import paul.sydney.service.work.ServiceWorkItemInf;


@Controller 
@RequestMapping("/workItem.htm") 
public class ControllerWorkItem {
    private final ServiceWorkItemInf service;
	//private BeansInf beans;
    @Autowired 
    public ControllerWorkItem(ServiceWorkItemInf service){
        this.service = service; 
       // this.beans = beans; 
    }
	
    
    @ResponseBody
   	@RequestMapping("/saveRow")
    private List<Map<String,Object>> saveRow(@RequestBody List<HongXunWorkItem> hongXunWorkItemList) throws ParseException{
    	//System.out.println("saveRow HongXunWorkItem");
    	for(HongXunWorkItem item: hongXunWorkItemList){
	    	if(item.getIdc()==0){
	    		return service.saveRow(item);
	    	}else{	
	    		//System.out.println("saveRow :" + item.getRemark());
	    		return service.updateRow(item);
	    	}
    	}
    	//System.out.println("saveRow error");
		return null;
   	}
 

	@ResponseBody
   	@RequestMapping("/deleteRow")
   	private Boolean deleteRow(@RequestBody List<HongXunWorkItem> hongXunWorkItemList){
		boolean flag = true;
		for(HongXunWorkItem item : hongXunWorkItemList){
			service.deleteRow(item);
			flag = false;
		}
   		return flag;
   	}  
       
    
    @ResponseBody
   	@RequestMapping("/getWorkNumItems")
	private List<Map<String,Object>> getWorkNumItems(@RequestBody List<HongXunWorkItem> hongXunWorkNumList){
    	//System.out.println("getWorkNumItems");
    	if(hongXunWorkNumList.size() == 1){
    		return service.getWorkNumItems(hongXunWorkNumList.get(0).getIdc());
    	}else{
    		return null;
    	}
   	}  

}