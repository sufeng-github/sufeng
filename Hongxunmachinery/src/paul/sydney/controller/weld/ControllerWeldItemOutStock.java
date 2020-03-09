package paul.sydney.controller.weld;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import paul.sydney.service.weld.ServiceWeldItemOutStockInf;


@Controller 				 
@RequestMapping("/weldItemOutStock.htm")
public class ControllerWeldItemOutStock {
    private final ServiceWeldItemOutStockInf service;
    @Autowired 
    public ControllerWeldItemOutStock(ServiceWeldItemOutStockInf service){
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
    	System.out.println("purchaseItemOutStock.htm/loadData");
    	return service.loadData();
    }	   


    @ResponseBody
   	@RequestMapping("/saveRow")
    private List<Map<String,Object>> saveRow(@RequestBody List<HongXunPurchaseItemOutStock> hongXunPurchaseItemOutStockList) {    	
    	// TODO Auto-generated catch block
    	System.out.println("HongXunPurchaseItemOutStock: ");
    	//if(hongXunPurchaseItemOutStockList.size()>0){
	    	return service.saveRow(hongXunPurchaseItemOutStockList.get(0));
 
   	}*/

    @ResponseBody
   	@RequestMapping("/getWeldItemOutStock")
    private List<Map<String,Object>> getWeldItemOutStock(@RequestParam(value = "orderNumID", required = true) int orderNumID){
    	//int purchaseNumID = (hongXunPurchaseItemList.get(0).getIdc());
    	//System.out.println("purchaseNumID :" + purchaseNumID);
    	return service.getWeldItemsOutStore(orderNumID);
    }
    

    @ResponseBody
   	@RequestMapping("/autotimp")
   	public List<Map<String,Object>> autotimp(HttpServletRequest request) {
    	String q=request.getParameter("q");
    	int orderNumID= Integer.valueOf(request.getParameter("orderNumID"));
    	//System.out.println("purchaseNumID:" + purchaseNumID); 
   		return service.autotimp(q, orderNumID);  
   	}  
    
    @ResponseBody
   	@RequestMapping("/outstoreProductionWeld")
    private List<Map<String,Object>> outstoreProductionWeld(@RequestParam(value = "name", required = true) String materialNum,
    													@RequestParam(value = "id", required = true) int orderNumID,
    													@RequestParam(value = "quantity", required = true) int quantity){
    	
    	//System.out.println(materialNum);
    	List<Map<String,Object>> list = service.outstoreProductionWeld(materialNum, orderNumID,  quantity);
		return list;
    	
    }
    
}