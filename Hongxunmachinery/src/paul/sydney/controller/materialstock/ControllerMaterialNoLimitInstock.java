package paul.sydney.controller.materialstock;

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
import paul.sydney.service.materialstock.ServiceMaterialNoLimitInStockInf;
import paul.sydney.model.HongXunMaterialNoLimitInStock;

@Controller 
@RequestMapping("/materialNoLimitInStock.htm")
public class ControllerMaterialNoLimitInstock {
    private final ServiceMaterialNoLimitInStockInf service;
    @Autowired 
    public ControllerMaterialNoLimitInstock(ServiceMaterialNoLimitInStockInf service){
        this.service = service; 
    }
    
    @ResponseBody
   	@RequestMapping("/loadData")
    private List<Map<String,Object>> loadData(){    	
    	// TODO Auto-generated catch block
 
    	return service.loadData();
    }	
    
    @ResponseBody
   	@RequestMapping("/getEntity")
   	public List<Map<String,Object>> getEntity() {
    	return service.getEntity();
   	}
    
    @ResponseBody
   	@RequestMapping("/updateRow")
    private List<Map<String,Object>> saveRow(@RequestBody List<HongXunMaterialNoLimitInStock> hongXunMaterialNoLimitInStockList) {    	
    	// TODO Auto-generated catch block
    	//System.out.println("HongXunPurchaseItemOutStock: ");
    	//if(hongXunPurchaseItemInStockList.size()>0){
	    //return service.updateRow(hongXunPurchaseItemOutStockList.get(0));
	    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	for(HongXunMaterialNoLimitInStock item : hongXunMaterialNoLimitInStockList){ 	
    		if(item.getIdc()==0){
				list.addAll(service.saveRow(item));	
    		}else{   			
    			list.addAll(service.updateRow(item));
    		}	
    	}
    	return list;
 
   	}
    
/*    @ResponseBody
   	@RequestMapping("/outstoreMaterialNum")
    private List<Map<String,Object>> outstoreMaterialNum(@RequestParam(value = "name", required = true) String materialNum,
    													@RequestParam(value = "id", required = true) int outStoreNumID,
    													@RequestParam(value = "quantity", required = true) int quantity){
    	
    	System.out.println(materialNum);
    	List<Map<String,Object>> list = service.outstoreMaterialNum(materialNum, outStoreNumID,  quantity);
		return list;	
    }*/
    
    @ResponseBody
   	@RequestMapping("/scanInMaterialNum")
    private List<Map<String,Object>> scanInMaterialNum(@RequestParam(value = "name", required = true) String materialNum,
    									
    													@RequestParam(value = "quantity", required = true) int quantity){
    	
    	//System.out.println(materialNum);
    	List<Map<String,Object>> list = service.scanInMaterialNum(materialNum,  quantity);
		return list;	
    }
    
    
/*    @ResponseBody
   	@RequestMapping("/getMaterialItemOutStock")
    private List<Map<String,Object>> getMaterialItemOutStock(@RequestParam(value = "outStoreNumID", required = true) int outStoreNumID){
    	
    	//int childUncleID = (hongXunDataTwo.get(0).getIdc());
    	System.out.println("outStoreNumID123 :" + outStoreNumID);
    	
    	return service.getMaterialItemOutStock(outStoreNumID);
    	//return null;
    }*/
    
    @ResponseBody
   	@RequestMapping("/getWeiwaiItemOutStocks")
    private List<Map<String,Object>> getWeiwaiItemOutStocks(@RequestParam(value = "weiwaiNum", required = true) String weiwaiNum){
    	
    	//int childUncleID = (hongXunDataTwo.get(0).getIdc());
    	//System.out.println("weiwaiNum :" + weiwaiNum);
    	return service.getWeiwaiItemStorckOuts(weiwaiNum);
    	//return null;
    }
    
    @ResponseBody
   	@RequestMapping("/autotimp")
   	public List<Map<String,Object>> autotimp(HttpServletRequest request) {
    	String q=request.getParameter("q");
    	//System.out.println("autotimp:" + q); 	
   		return service.autotimp(q);  
   	} 
    
    @ResponseBody
   	@RequestMapping("/searchMaterialNum")
    private List<Map<String,Object>> searchPurchaseItem(@RequestParam(value = "name", required = true) String materialNum,
    													@RequestParam(value = "quantity", required = true) int quantity){
    	//System.out.println(materialNum);
    	List<Map<String,Object>> list = service.searchMaterialNum(materialNum, quantity);
		return list;
    	
    }
}