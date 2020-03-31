package paul.sydney.controller.purchase;


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
import paul.sydney.service.purchase.ServicePurchaseItemInf;
import paul.sydney.commen.result.PageResults;
import paul.sydney.dao.IPurchaseItemDao;
import paul.sydney.model.HongXunMaterialStock;
import paul.sydney.model.HongXunPurchaseDeItem;
import paul.sydney.model.HongXunPurchaseItem;
import paul.sydney.model.HongXunPurchaseNum;


@Controller 
@RequestMapping("/purchaseItem.htm") 
				 
public class ControllerPurchaseItem {
    private final ServicePurchaseItemInf service;
	
    @Autowired 
    public ControllerPurchaseItem(ServicePurchaseItemInf service){
        this.service = service; 
    	
    }

	
    @ResponseBody
   	@RequestMapping("/getData")
    private PageResults<HongXunPurchaseItem> getData(/* @RequestBody HongXunMaterialStock hongXunMaterialStock,*/
											    		@RequestParam(value="purchaseNumId",required=false) Integer purchaseNumID,
											    		@RequestParam(value="materialNum",required=false) String materialNum,
											    		@RequestParam(value="specification",required=false) String specification,
											    		@RequestParam(value="supplier",required=false) String supplier,										    		
											    		@RequestParam(value="page", defaultValue="1") Integer pageNo,
														@RequestParam(value="rows", defaultValue="10") Integer pageSize){    	

		HongXunPurchaseItem hongXunPurchaseItem =new HongXunPurchaseItem();
		hongXunPurchaseItem.setHongXunPurchaseNum(new HongXunPurchaseNum());
    	hongXunPurchaseItem.setHongXunPurchaseDeItem(new HongXunPurchaseDeItem());
    	hongXunPurchaseItem.getHongXunPurchaseDeItem().setHongXunMaterialStock(new HongXunMaterialStock());
		if (purchaseNumID!=null && purchaseNumID!=0) {	
			hongXunPurchaseItem.getHongXunPurchaseNum().setIdc(purchaseNumID);	
		}
    	if(materialNum != null && !materialNum.equals("")){
    		hongXunPurchaseItem.getHongXunPurchaseDeItem().getHongXunMaterialStock().setMaterialNum(materialNum);
    	}   	
    	if(specification != null && !specification.equals("")){
    		hongXunPurchaseItem.getHongXunPurchaseDeItem().getHongXunMaterialStock().setSpecification(specification);
    	}
    	if(supplier != null && !supplier.equals("")){
    		hongXunPurchaseItem.setSupplier(supplier);
    	}
		return service.getData(hongXunPurchaseItem, pageNo, pageSize);
	}

       
    @ResponseBody
   	@RequestMapping("/deleteRow")
    private List<Map<String,Object>> deleteRow(@RequestBody List<HongXunPurchaseItem> hongXunPurchaseItemList){ 
    	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    	for(HongXunPurchaseItem item: hongXunPurchaseItemList){
    		//HongXunPurchaseDeItem hongXunPurchaseDeItem = iPurchaseItemDao.get(item.getIdc()).getHongXunPurchaseDeItem();
    		service.deleteRow(item);
    	}   	
   		return list;
   	}    

    @ResponseBody
   	@RequestMapping("/updateRow")
    private List<Map<String,Object>> updateRow(@RequestBody List<HongXunPurchaseItem> hongXunPurchaseItemList){ 
    	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    	for(HongXunPurchaseItem item: hongXunPurchaseItemList){
    		service.updateRow(item);
    	}
   		return list;
   	}  
    @ResponseBody
   	@RequestMapping("/autotimp")
   	public List<Map<String,Object>> autotimp(HttpServletRequest request) {
    	String q=request.getParameter("q");
    	int purchaseNumID= Integer.valueOf(request.getParameter("purchaseNumID"));
    	//System.out.println("autotimp:" + q); 	
   		return service.autotimp(q, purchaseNumID);  
   	} 
    
    
    @ResponseBody
   	@RequestMapping("/instock")
    private List<Map<String,Object>> instock(@RequestBody List<HongXunPurchaseItem> hongXunPurchaseItemList){ 
   	 	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	   	for(HongXunPurchaseItem item : hongXunPurchaseItemList){ 
	   		service.instock(item);	   			
	   	}
	   	return list;
   	} 
    
    @ResponseBody
   	@RequestMapping("/getEntity")
   	public List<Map<String,Object>> getEntity() {
    	return service.getEntity();
   	} 

}