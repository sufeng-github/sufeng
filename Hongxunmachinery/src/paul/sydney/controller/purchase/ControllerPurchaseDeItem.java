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
import paul.sydney.commen.result.PageResults;
import paul.sydney.dao.IPurchaseDeItemDao;
import paul.sydney.model.HongXunMaterialStock;
import paul.sydney.model.HongXunPurchaseDeItem;
import paul.sydney.model.HongXunPurchaseDeNum;
import paul.sydney.service.purchase.ServicePurchaseDeItemInf;



@Controller 
@RequestMapping("/purchaseDeItem.htm") 
public class ControllerPurchaseDeItem {
    private final ServicePurchaseDeItemInf service;
    @Autowired 
    public ControllerPurchaseDeItem(ServicePurchaseDeItemInf service){
        this.service = service; 
    }
	@Autowired
	IPurchaseDeItemDao iPurchaseDeItemDao;
	public void setStockDao(IPurchaseDeItemDao iPurchaseDeItemDao) {
		this.iPurchaseDeItemDao = iPurchaseDeItemDao;
	}
    
    @ResponseBody
   	@RequestMapping("/getEntity")
   	public List<Map<String,Object>> getEntity() {
    	return service.getEntity();
   	} 
    
    
    @ResponseBody
   	@RequestMapping("/getData")
    private PageResults<HongXunPurchaseDeItem> getData(/* @RequestBody HongXunMaterialStock hongXunMaterialStock,*/
											    		@RequestParam(value="purchaseDeNumId",required=false) Integer purchaseDeNumId,
											    		@RequestParam(value="materialNum",required=false) String materialNum,
											    		@RequestParam(value="specification",required=false) String specification,
											    		@RequestParam(value="supplier",required=false) String supplier,	
														@RequestParam(value="page", defaultValue="1") Integer pageNo,
														@RequestParam(value="rows", defaultValue="10") Integer pageSize){    	
		// TODO Auto-generated catch block
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	//System.out.println("purchaseDeNumId:" + purchaseDeNumId);
		HongXunPurchaseDeItem hongXunPurchaseDeItem =new HongXunPurchaseDeItem();
		hongXunPurchaseDeItem.setHongXunPurchaseDeNum(new HongXunPurchaseDeNum());
		hongXunPurchaseDeItem.setHongXunMaterialStock(new HongXunMaterialStock());
		if (purchaseDeNumId!=null && purchaseDeNumId!=0) {		
			hongXunPurchaseDeItem.getHongXunPurchaseDeNum().setIdc(purchaseDeNumId);	
		}
		if(materialNum != null && !materialNum.equals("")){
    		hongXunPurchaseDeItem.getHongXunMaterialStock().setMaterialNum(materialNum);
    	}   	
    	if(specification != null && !specification.equals("")){
    		hongXunPurchaseDeItem.getHongXunMaterialStock().setSpecification(specification);
    	}
    	if(supplier != null && !supplier.equals("")){
    		hongXunPurchaseDeItem.setSupplier(supplier);
    	}
		return service.getData(hongXunPurchaseDeItem, pageNo, pageSize);
	}
    
    @ResponseBody
   	@RequestMapping("/deleteRow")
    private Boolean deleteRow(@RequestBody List<HongXunPurchaseDeItem> hongXunPurchaseDeItemList){
   		//int ID = hongXunPurchaseNumList.get(0).getIdc();
    	for(HongXunPurchaseDeItem item: hongXunPurchaseDeItemList){
    		
    		service.deleteRow(item);    		
    	}
   		return true;
   	} 
    
    @ResponseBody
   	@RequestMapping("/updateRow")
    private List<Map<String,Object>> updateRow(@RequestBody List<HongXunPurchaseDeItem> hongXunPurchaseDeItemList){
    	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    	for(HongXunPurchaseDeItem item: hongXunPurchaseDeItemList){
    		service.updateRow(item);
    	}
    	return list;
    }

    
    @ResponseBody
   	@RequestMapping("/autotimp")
   	public List<Map<String,Object>> autotimp(HttpServletRequest request) {
    	String q=request.getParameter("q");
    	String numId = request.getParameter("numId");
    	//System.out.println("autotimp:" + q); 
    	Integer forId = null;
    	if(numId!=null){
    		forId = Integer.valueOf(numId);
    	}
   		return service.autotimp(q, forId);  
   	} 
    

}