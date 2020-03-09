package paul.sydney.controller.purchase;

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

import paul.sydney.model.HongXunMaterialNoLimitInStock;
import paul.sydney.model.HongXunPurchaseItemInStock;
import paul.sydney.service.purchase.ServicePurchaseItemInStockInf;




@Controller 
@RequestMapping("/purchaseItemInStock.htm")
public class ControllerPurchaseItemInStock {
    private final ServicePurchaseItemInStockInf service;

    @Autowired 
    public ControllerPurchaseItemInStock(ServicePurchaseItemInStockInf service){
        this.service = service; 

    }
    
/*  @InitBinder
    public void initBinder(WebDataBinder binder) {
    	System.out.println("InitBinder");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }*/
  
    @ResponseBody
   	@RequestMapping("/loadData")
    private List<Map<String,Object>> loadData(){    	
    	// TODO Auto-generated catch block
    	System.out.println("purchaseItemInStock.htm/loadData");
    	return service.loadData();
    }	   

    @ResponseBody
   	@RequestMapping("/getEntity")
   	public List<Map<String,Object>> getEntity() {
    	return service.getEntity();
   	}

    @ResponseBody
   	@RequestMapping("/updateRow")
    private List<Map<String,Object>> saveRow(@RequestBody List<HongXunPurchaseItemInStock> hongXunPurchaseItemInStockList) {    	
    	// TODO Auto-generated catch block
	  	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	  	for(HongXunPurchaseItemInStock item : hongXunPurchaseItemInStockList){ 	
	  		if(item.getIdc()==0){
					list.addAll(service.saveRow(item));	
	  		}else{   			
	  			list.addAll(service.updateRow(item));
	  		}	
	  	}
	  	return list;
   	}
    
    
    @ResponseBody
   	@RequestMapping("/getPurchaseItemsInStock")
    private List<Map<String,Object>> getPurchaseItemsInStore(@RequestParam(value = "purchaseNumID", required = true) int purchaseNumID){
    	//int purchaseNumID = (hongXunPurchaseItemList.get(0).getIdc());
    	//System.out.println("purchaseNumID :" + purchaseNumID);
    	return service.getPurchaseItemsInStore(purchaseNumID);
    }
    
/*    @ResponseBody
   	@RequestMapping("/preOutstore")
    private List<Map<String,Object>> outstore(@RequestBody List<HongXunPurchaseItem> hongXunDataTwoList){ 

    	System.out.println("preOutstore");
    	String materialNum = hongXunDataTwoList.get(0).getMaterialNum();
    	int outStoreQuantity = hongXunDataTwoList.get(0).getMaterialPlanQuantity();
    	int uncleID = hongXunDataTwoList.get(0).getIdc();
    	return service.preOutstore(materialNum, outStoreQuantity, uncleID);
   	} */

/*    @ResponseBody
   	@RequestMapping("/autotimp")
   	public List<Map<String,Object>> autotimp(HttpServletRequest request) {
    	String q=request.getParameter("q");
    	int purchaseNumID= Integer.valueOf(request.getParameter("purchaseNumID"));
    	//System.out.println("purchaseNumID:" + purchaseNumID); 
    	
   		return service.autotimp(q, purchaseNumID);  
   	}*/ 
    /*
    @ResponseBody
   	@RequestMapping("/autotimp")
   	public List<Map<String,Object>> autotimp(HttpServletRequest request) {
    	String q=request.getParameter("q");
    	String purchaseNumID = request.getParameter("purchaseNumID");
    	//System.out.println("autotimp:" + purchaseNumID); 	
   		return service.autotimp(q, Integer.valueOf(purchaseNumID));  
   	} 
    
    @ResponseBody
   	@RequestMapping("/searchMaterialNum")
    private List<Map<String,Object>> searchPurchaseItem(@RequestParam(value = "name", required = true) String materialNum,
    													@RequestParam(value = "purchaseNumID", required = true) int purchaseNumID
    													//@RequestParam(value = "quantity", required = true) int quantity
    													){

    	List<Map<String,Object>> list = service.searchPurchaseItem(purchaseNumID,materialNum);
    	//List<Map<String,Object>> list = service.outstoreMaterialNum(materialNum, outStoreNumID,  quantity);
		return list;
    	
    }
    
    @ResponseBody
   	@RequestMapping("/materialInStore")
    private List<Map<String,Object>> materialInStore(
    													@RequestParam(value = "id", required = true) int id,
    													@RequestParam(value = "quantity", required = true) int quantity
    													){    	
    	List<Map<String,Object>> list = service.instorePurchaseItem(id,  quantity);
		return list;
    	
    }
    */
    
    @ResponseBody
   	@RequestMapping("/autotimp")
   	public List<Map<String,Object>> autotimp(HttpServletRequest request) {
    	String q=request.getParameter("q");
    	String purchaseNumID = request.getParameter("purchaseNumID");
    	//System.out.println("autotimp:" + purchaseNumID); 	
   		return service.autotimp(q, Integer.valueOf(purchaseNumID));  
   	} 
    
    @ResponseBody
   	@RequestMapping("/instoreMaterialNum")
    private List<Map<String,Object>> instoreMaterialNum(@RequestParam(value = "name", required = true) String materialNum,
    													@RequestParam(value = "id", required = true) int purchaseNumID,
    													@RequestParam(value = "quantity", required = true) int quantity){
    	
    	//System.out.println(materialNum);
    	List<Map<String,Object>> list = service.instoreMaterialNum(materialNum, purchaseNumID,  quantity);
		return list;
    	
    }
    
}