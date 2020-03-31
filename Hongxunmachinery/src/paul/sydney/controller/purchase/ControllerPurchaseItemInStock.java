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

import paul.sydney.commen.result.PageResults;
import paul.sydney.model.HongXunMaterialNoLimitInStock;
import paul.sydney.model.HongXunMaterialStock;
import paul.sydney.model.HongXunPurchaseDeItem;
import paul.sydney.model.HongXunPurchaseItem;
import paul.sydney.model.HongXunPurchaseItemInStock;
import paul.sydney.model.HongXunPurchaseNum;
import paul.sydney.service.purchase.ServicePurchaseItemInStockInf;




@Controller 
@RequestMapping("/purchaseItemInStock.htm")
public class ControllerPurchaseItemInStock {
    private final ServicePurchaseItemInStockInf service;

    @Autowired 
    public ControllerPurchaseItemInStock(ServicePurchaseItemInStockInf service){
        this.service = service; 

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
	  		service.updateRow(item);		
	  	}
	  	return list;
   	}
    
    @ResponseBody
   	@RequestMapping("/deleteRow")
    private List<Map<String,Object>> deleteRow(@RequestBody List<HongXunPurchaseItemInStock> hongXunPurchaseItemInStockList){ 
    	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
	  	for(HongXunPurchaseItemInStock item : hongXunPurchaseItemInStockList){ 	  			
	  		service.deleteRow(item);		
	  	}
   		return list;
   	} 
    
/*    @ResponseBody
   	@RequestMapping("/getPurchaseItemsInStock")
    private List<Map<String,Object>> getPurchaseItemsInStore(@RequestParam(value = "purchaseNumID", required = true) int purchaseNumID){
    	//int purchaseNumID = (hongXunPurchaseItemList.get(0).getIdc());
    	//System.out.println("purchaseNumID :" + purchaseNumID);
    	return service.getPurchaseItemsInStore(purchaseNumID);
    }*/
    
    @ResponseBody
   	@RequestMapping("/getData")
    private PageResults<HongXunPurchaseItemInStock> getData(
    													@RequestParam(value="purchaseNumID", required = true) Integer purchaseNumID,
											    		@RequestParam(value="materialNum",required=false) String materialNum,
											    		@RequestParam(value="specification",required=false) String specification,
											    		@RequestParam(value="quantity", required = false) Integer quantity,
											    		@RequestParam(value="page", defaultValue="1") Integer pageNo,
														@RequestParam(value="rows", defaultValue="10") Integer pageSize){    	

    	HongXunPurchaseItemInStock hongXunPurchaseItemInStock =new HongXunPurchaseItemInStock();
    	hongXunPurchaseItemInStock.setHongXunPurchaseItem(new HongXunPurchaseItem());
    	hongXunPurchaseItemInStock.getHongXunPurchaseItem().setHongXunPurchaseNum(new HongXunPurchaseNum());
    	hongXunPurchaseItemInStock.getHongXunPurchaseItem().setHongXunPurchaseDeItem(new HongXunPurchaseDeItem());
    	hongXunPurchaseItemInStock.getHongXunPurchaseItem().getHongXunPurchaseDeItem().setHongXunMaterialStock(new HongXunMaterialStock());
		if (purchaseNumID!=null && purchaseNumID!=0) {	
			hongXunPurchaseItemInStock.getHongXunPurchaseItem().getHongXunPurchaseNum().setIdc(purchaseNumID);
		}
    	if(materialNum != null && !materialNum.equals("")){
    		hongXunPurchaseItemInStock.getHongXunPurchaseItem().getHongXunPurchaseDeItem().getHongXunMaterialStock().setMaterialNum(materialNum);
    	}   	
    	if(specification != null && !specification.equals("")){
    		hongXunPurchaseItemInStock.getHongXunPurchaseItem().getHongXunPurchaseDeItem().getHongXunMaterialStock().setSpecification(specification);
    	}
    	if(quantity != null && !quantity.equals("")){
    		hongXunPurchaseItemInStock.setRealQuantity(quantity);
    	}
		return service.getData(hongXunPurchaseItemInStock, pageNo, pageSize);
	}
    
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