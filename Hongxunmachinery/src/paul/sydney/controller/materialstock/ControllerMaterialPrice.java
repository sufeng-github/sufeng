package paul.sydney.controller.materialstock;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import paul.sydney.model.HongXunSuppliersPrice;
import paul.sydney.service.materialstock.ServiceMaterialPriceInf;


@Controller 
@RequestMapping("/materialPrice.htm") 
public class ControllerMaterialPrice {
    private final ServiceMaterialPriceInf service;
    @Autowired 
    public ControllerMaterialPrice(ServiceMaterialPriceInf service){
        this.service = service; 
    }
    
  
    @ResponseBody
   	@RequestMapping("/loadData")
    private List<Map<String,Object>> loadData(){    	
    	// TODO Auto-generated catch block
    	return service.loadData();
    }	   
   /* @ResponseBody
   	@RequestMapping("/getMaterialAlarms")
    private List<Map<String,Object>> getMaterialAlarms(@RequestParam(value = "alarm", required = true) String alarm){    	
    	// TODO Auto-generated catch block
    	return service.getMaterialAlarms(alarm);
    }	
    */
    @ResponseBody
   	@RequestMapping("/autotimp")
   	public List<Map<String,Object>> autotimp(HttpServletRequest request) {
    	String q=request.getParameter("q");
    	//System.out.println("autotimp:" + q); 	
   		return service.autotimp(q);  
   	} 
    /*
    @ResponseBody
   	@RequestMapping("/searchMaterialNum")
    private List<Map<String,Object>> searchPurchaseItem(@RequestParam(value = "name", required = true) String materialNum){
    	System.out.println(materialNum);
    	List<Map<String,Object>> list = service.searchMaterialNum(materialNum);
		return list;
    	
    }
    */
    @ResponseBody
   	@RequestMapping("/getEntity")
   	public List<Map<String,Object>> getEntity() {
    	return service.getEntity();
   	} 
    
    @ResponseBody
   	@RequestMapping("/saveRow")
    private List<Map<String,Object>> saveRow(@RequestBody List<HongXunSuppliersPrice> hongXunSuppliersPriceList){
    	
    	//System.out.println("HongXunMaterialStock saveRow");
    	//List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	/*for(HongXunSuppliersPrice item : hongXunSuppliersPriceList){ 		
    		if(item.getIdc()==0){
				list.addAll(service.saveRow(item));	
    		}else{   			
    			list.addAll(service.updateRow(item));
    		}	
    	}*/
    	
    	return service.saveOrUpdateRow(hongXunSuppliersPriceList);
   	}
    
    @ResponseBody
   	@RequestMapping("/deleteRow")
    private List<Map<String,Object>> deleteRow(@RequestBody List<HongXunSuppliersPrice> hongXunSuppliersPriceList){
    	return service.deleteRow(hongXunSuppliersPriceList);   	
   	}
    
}