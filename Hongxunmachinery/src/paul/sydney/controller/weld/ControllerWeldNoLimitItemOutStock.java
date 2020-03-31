package paul.sydney.controller.weld;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import paul.sydney.model.HongXunWeldNoLimitItemOutStock;
import paul.sydney.service.weld.ServiceWeldNoLimitItemOutStockInf;

@Controller 			 
@RequestMapping("/weldNoLimitItemOutStock.htm")
public class ControllerWeldNoLimitItemOutStock {
    private final ServiceWeldNoLimitItemOutStockInf service;
    @Autowired 
    public ControllerWeldNoLimitItemOutStock(ServiceWeldNoLimitItemOutStockInf service){
        this.service = service; 
    }
    


    @ResponseBody
   	@RequestMapping("/loadData")
    private List<Map<String,Object>> loadData(){
    	//int purchaseNumID = (hongXunPurchaseItemList.get(0).getIdc());
    	//System.out.println("purchaseNumID :" + purchaseNumID);
    	return service.loadData();
    }
    


    @ResponseBody
   	@RequestMapping("/autotimp")
   	public List<Map<String,Object>> autotimp(HttpServletRequest request) {
    	String q=request.getParameter("q");
    	//int orderNumID= Integer.valueOf(request.getParameter("orderNumID"));
    	//System.out.println("purchaseNumID:" + purchaseNumID); 
   		return service.autotimp(q);  
   	} 
    
    
    @ResponseBody
   	@RequestMapping("/outstoreProduction")
    private List<Map<String,Object>> outstoreProduction(@RequestParam(value = "name", required = true) String materialNum,
    													//@RequestParam(value = "id", required = true) int orderNumID,
    													@RequestParam(value = "quantity", required = true) int quantity){
    	
    	//System.out.println(materialNum +  " : " + orderNumID + " : " + quantity);
    	List<Map<String,Object>> list = service.outstoreProduction(materialNum,  quantity);
		return list;
    	
    }
    
    @ResponseBody
   	@RequestMapping("/getEntity")
   	public List<Map<String,Object>> getEntity() {
    	return service.getEntity();
   	}
    
    @ResponseBody
   	@RequestMapping("/updateRow")
    private List<Map<String,Object>> saveRow(@RequestBody List<HongXunWeldNoLimitItemOutStock> hongXunWeldNoLimitItemOutStockList) {    	
    	// TODO Auto-generated catch block
    	//System.out.println("HongXunPurchaseItemOutStock: ");
    	//if(hongXunPurchaseItemInStockList.size()>0){
	    //return service.updateRow(hongXunPurchaseItemOutStockList.get(0));
	    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	for(HongXunWeldNoLimitItemOutStock item : hongXunWeldNoLimitItemOutStockList){ 	
    		if(item.getIdc()==0){
				list.addAll(service.saveRow(item));	
    		}else{   			
    			list.addAll(service.updateRow(item));
    		}	
    	}
    	return list;
 
   	}
}