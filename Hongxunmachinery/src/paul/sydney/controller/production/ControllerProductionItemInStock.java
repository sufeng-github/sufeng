package paul.sydney.controller.production;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import paul.sydney.service.production.ServiceProductionItemInStockInf;


@Controller 				 
@RequestMapping("/productionItemInStock.htm")
public class ControllerProductionItemInStock {
    private final ServiceProductionItemInStockInf service;
    @Autowired 
    public ControllerProductionItemInStock(ServiceProductionItemInStockInf service){
        this.service = service; 
    }
    
/*  @InitBinder
    public void initBinder(WebDataBinder binder) {
    	System.out.println("InitBinder");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }*/
  
/*    @ResponseBody
   	@RequestMapping("/loadData")
    private List<Map<String,Object>> loadData(){    	
    	// TODO Auto-generated catch block
    	System.out.println("purchaseItemInStock.htm/loadData");
    	return service.loadData();
    }	   


    @ResponseBody
   	@RequestMapping("/saveRow")
    private List<Map<String,Object>> saveRow(@RequestBody List<HongXunPurchaseItemInStock> hongXunPurchaseItemInStockList) {    	
    	// TODO Auto-generated catch block
    	System.out.println("HongXunPurchaseItemInStock: ");
    	//if(hongXunPurchaseItemInStockList.size()>0){
	    	return service.saveRow(hongXunPurchaseItemInStockList.get(0));
 
   	}*/

    @ResponseBody
   	@RequestMapping("/getproductionItemInStock")
    private List<Map<String,Object>> getproductionItemInStock(@RequestParam(value = "orderNumID", required = true) int orderNumID){
    	//int purchaseNumID = (hongXunPurchaseItemList.get(0).getIdc());
    	//System.out.println("purchaseNumID :" + purchaseNumID);
    	return service.getProductionItemsInStore(orderNumID);
    }
    


    @ResponseBody
   	@RequestMapping("/autotimp")
   	public List<Map<String,Object>> autotimp(HttpServletRequest request) {
    	String q=request.getParameter("q");
    	int orderNumID= Integer.valueOf(request.getParameter("orderNumID"));
    	//System.out.println("purchaseNumID:" + purchaseNumID); 
   		return service.autotimp(q, orderNumID);  
   	} 
    
   /* @ResponseBody
   	@RequestMapping("/searchMaterialNum")
    private List<Map<String,Object>> searchPurchaseItem(@RequestParam(value = "name", required = true) String materialNum,
    													@RequestParam(value = "orderNumID", required = true) int orderNumID,
    													@RequestParam(value = "quantity", required = true) int quantity
    													){

    	List<Map<String,Object>> list = service.instoreProduction(materialNum, orderNumID,  quantity);
		return list;
    	
    }*/
    
    @ResponseBody
   	@RequestMapping("/instoreProduction")
    private List<Map<String,Object>> instoreProduction(@RequestParam(value = "name", required = true) String materialNum,
    													@RequestParam(value = "id", required = true) int orderItemID,
    													@RequestParam(value = "orderNumID", required = true) int orderNumID,
    													@RequestParam(value = "quantity", required = true) int quantity){
    	
    //	System.out.println(materialNum +  " : " + orderItemID + " : " + quantity);
    	List<Map<String,Object>> list = service.instoreProduction(materialNum, orderItemID, orderNumID,  quantity);
		return list;
    	
    }
    
}