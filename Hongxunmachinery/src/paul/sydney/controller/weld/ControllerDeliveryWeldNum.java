package paul.sydney.controller.weld;


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


import paul.sydney.model.HongXunDeliveryWeldNum;
import paul.sydney.model.HongXunMaterialStock;
import paul.sydney.model.HongXunProductionStock;
import paul.sydney.service.materialstock.ServiceMaterialStockInf;
import paul.sydney.service.production.ServiceDeliveryNumInf;
import paul.sydney.service.production.ServiceProductionStockInf;
import paul.sydney.service.weld.ServiceDeliveryWeldNumInf;

@Controller 
@RequestMapping("/deliveryWeldNum.htm")
public class ControllerDeliveryWeldNum {
    private final ServiceDeliveryWeldNumInf service;
    @Autowired 
    public ControllerDeliveryWeldNum(ServiceDeliveryWeldNumInf service){
        this.service = service; 
    }
    
  
    @ResponseBody
   	@RequestMapping("/loadData")
    private List<Map<String,Object>> loadData(){    	
    	// TODO Auto-generated catch block
    	return service.loadData();
    }	   

    @ResponseBody
   	@RequestMapping("/autotimp")
   	public List<Map<String,Object>> autotimp(HttpServletRequest request) {
    	String q=request.getParameter("q");
    	//System.out.println("autotimp:" + q); 	
   		return service.autotimp(q);  
   	} 
    
    
    @ResponseBody
   	@RequestMapping("/searchDeliveryNum")
    private List<Map<String,Object>> searchPurchaseItem(@RequestParam(value = "name", required = true) String deliveryNum){
    	//System.out.println(materialNum);
    	List<Map<String,Object>> list = service.searchMaterialNum(deliveryNum);
		return list;
    	
    }
    
    @ResponseBody
   	@RequestMapping("/getEntity")
   	public List<Map<String,Object>> getEntity() {
    	return service.getEntity();
   	} 
    
    
    @ResponseBody
   	@RequestMapping("/deleteRow")
    public List<Map<String,Object>> deleteRow(@RequestParam(value = "idc", required = true) int id){
    	return service.deleteRow(id);
    
    }
    
    @ResponseBody
   	@RequestMapping("/saveRow")
    private List<Map<String,Object>> saveRow(@RequestBody List<HongXunDeliveryWeldNum> hongXunDeliveryNumList){
    	
    	//System.out.println("HongXunProduction saveRow");
	    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	for(HongXunDeliveryWeldNum item : hongXunDeliveryNumList){ 		
			if(item.getIdc()==0){
				list.addAll(service.saveRow(item));	
    		}else{   			
    			list.addAll(service.updateRow(item));
    		}	
    	}
    	return list;
	
   	}
    
    @ResponseBody
   	@RequestMapping("/changeStatus")
    private List<Map<String,Object>> changeStatus(@RequestParam(value = "deliveryNumID", required = true) int deliveryNumID){
    	return service.changeStatus(deliveryNumID);
    }
    	
    
}