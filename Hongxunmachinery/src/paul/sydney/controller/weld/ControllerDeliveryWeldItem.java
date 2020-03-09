package paul.sydney.controller.weld;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import paul.sydney.model.HongXunDeliveryWeldItem;
import paul.sydney.service.weld.ServiceDeliveryWeldItemInf;

@Controller 
@RequestMapping("/deliveryWeldItem.htm") 
public class ControllerDeliveryWeldItem {
    private final ServiceDeliveryWeldItemInf service;
    @Autowired 
    public ControllerDeliveryWeldItem(ServiceDeliveryWeldItemInf service){
        this.service = service; 
    }
    
  
    @ResponseBody
   	@RequestMapping("/loadData")
    private List<Map<String,Object>> loadData(@RequestParam(value = "deliveryNumID", required = true) Integer deliveryNumID){    	
    	// TODO Auto-generated catch block
    	return service.loadData(deliveryNumID);
    }	   

    @ResponseBody
   	@RequestMapping("/autotimp")
   	public List<Map<String,Object>> autotimp(HttpServletRequest request) {
    	String q=request.getParameter("q");
    	String deliveryNumID = request.getParameter("deliveryNumID");	
   		return service.autotimp(q, Integer.valueOf(deliveryNumID));  	 
   	} 
    
    @ResponseBody
   	@RequestMapping("/searchMaterialNum")
    private List<Map<String,Object>> searchPurchaseItem(@RequestParam(value = "name", required = true) String materialNum,
    													@RequestParam(value = "deliveryNumID", required = true) int deliveryNumID){
    	//System.out.println(materialNum);
    	List<Map<String,Object>> list = service.searchMaterialNum(materialNum, deliveryNumID);
		return list;
    	
    }
    @ResponseBody
   	@RequestMapping("/outstock")
	List<Map<String, Object>> outstock(@RequestParam(value = "name", required = true) String materialNum,
										@RequestParam(value = "deliveryItemID", required = true) int deliveryItemID){
		//System.out.println("outstock");
    	return service.outstock(materialNum, deliveryItemID);
    }
    
    @ResponseBody
   	@RequestMapping("/autotimpAll")
   	public List<Map<String,Object>> autotimpAll(HttpServletRequest request) {
    	String q=request.getParameter("q");
   		return service.autotimp(q);  	 
   	} 
    
    @ResponseBody
   	@RequestMapping("/searchMaterialNumAll")
    private List<Map<String,Object>> searchPurchaseItemAll(@RequestParam(value = "name", required = true) String materialNum){
    	//System.out.println(materialNum);
    	List<Map<String,Object>> list = service.searchMaterialNum(materialNum);
		return list;
    	
    }
    
    @ResponseBody
   	@RequestMapping("/getEntity")
   	public List<Map<String,Object>> getEntity() {
    	return service.getEntity();
   	} 
    
/*    
    @ResponseBody
   	@RequestMapping("/delAllRows")
    public List<Map<String,Object>> delAllRows(){
    	return service.delAllRows();
    
    }*/
    
/*    @ResponseBody
   	@RequestMapping("/saveRow")
    private List<Map<String,Object>> saveRow(@RequestBody List<HongXunDeliveryWeldItem> hongXunDeliveryWeldItemList){
    	
    	//System.out.println("HongXunProduction saveRow");
	    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	for(HongXunDeliveryWeldItem item : hongXunDeliveryWeldItemList){ 		
			if(item.getIdc()==0){
				list.addAll(service.saveRow(item));	
    		}else{   			
    			list.addAll(service.updateRow(item));
    		}	
    	}
    	return list;
	
   	}*/
    @ResponseBody
   	@RequestMapping("/importData")
    public List<Map<String,Object>> importData(@RequestBody List<HongXunDeliveryWeldItem> hongXunDeliveryWeldItemList){
    	return service.importData(hongXunDeliveryWeldItemList);
    }
    	
}