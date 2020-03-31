package paul.sydney.controller.production;

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
import paul.sydney.model.HongXunProductionStock;
import paul.sydney.service.production.ServiceProductionStockInf;


@Controller 
@RequestMapping("/productionStock.htm") 
public class ControllerProductionStock {
    private final ServiceProductionStockInf service;
    @Autowired 
    public ControllerProductionStock(ServiceProductionStockInf service){
        this.service = service; 
    }
    

    @ResponseBody
   	@RequestMapping("/getData")
    private PageResults<HongXunProductionStock> getData(/* @RequestBody HongXunMaterialStock hongXunMaterialStock,*/
									    		@RequestParam(value="materialNum",required=false) String materialNum,
									    		@RequestParam(value="specification",required=false) String specification,
									    		@RequestParam(value="name",required=false) String name,
    											@RequestParam(value="page", defaultValue="1") Integer pageNo,
			  									@RequestParam(value="rows", defaultValue="10") Integer pageSize){    	
    	// TODO Auto-generated catch block
    	HongXunProductionStock hongXunProductionStock = new HongXunProductionStock();   	
    	if(materialNum != null && !materialNum.equals("")){
    		hongXunProductionStock.setMaterialNum(materialNum);
    	}   	
    	if(specification != null && !specification.equals("")){
    		hongXunProductionStock.setSpecification(specification);
    	}
    	if(name !=null && !name.equals("")){
    		hongXunProductionStock.setName(name);
    	}
    	return service.getData(hongXunProductionStock,pageNo,pageSize);
    }	
    

    @ResponseBody
   	@RequestMapping("/autotimp")
   	public List<Map<String,Object>> autotimp(HttpServletRequest request) {
    	String q=request.getParameter("q");
    	String name=request.getParameter("name");
    	//System.out.println("autotimp:" + q); 	  	
   		return service.autotimp(q, name);  
   	} 
    
/*    @ResponseBody
   	@RequestMapping("/searchMaterialNum")
    private List<Map<String,Object>> searchPurchaseItem(@RequestParam(value = "name", required = true) String materialNum){
    	//System.out.println(materialNum);
    	List<Map<String,Object>> list = service.searchMaterialNum(materialNum);
		return list;
    	
    }*/
    
    @ResponseBody
   	@RequestMapping("/getEntity")
   	public List<Map<String,Object>> getEntity() {
    	return service.getEntity();
   	} 
    
    @ResponseBody
   	@RequestMapping("/deleteRow")
   	private List<Map<String,Object>> deleteRow(@RequestBody List<HongXunProductionStock> entitys){	
   		//System.out.println("deleteRow");
   		List<Map<String, Object>> list = null;
   		for(HongXunProductionStock item: entitys){ 			
   			list = service.deleteRow(item);
   		}
   		return list;
   	} 
    
    @ResponseBody
   	@RequestMapping("/delAllRows")
    public List<Map<String,Object>> delAllRows(){
    	return service.delAllRows();
    
    }
    
    @ResponseBody
   	@RequestMapping("/saveRow")
    private List<Map<String,Object>> saveRow(@RequestBody List<HongXunProductionStock> hongXunProductionStockList){	
	    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	for(HongXunProductionStock item : hongXunProductionStockList){ 
    		if(item.getThisInQuantity()!=null){
    			item.setLastInQuantity(item.getThisInQuantity());
    			if(item.getQuantity()!=null){
    				item.setQuantity(item.getQuantity() + item.getThisInQuantity());
    			}else{
    				item.setQuantity(item.getThisInQuantity());
    			}
    		}
			if(item.getIdc()==0){
				service.saveRow(item);	
    		}else{   			
    			service.updateRow(item);
    		}	
    	}
    	return list;
	
   	}

    @ResponseBody
   	@RequestMapping("/inStock")
    private PageResults<HongXunProductionStock> inStock(@RequestBody List<HongXunProductionStock> hongXunProductionStockList){    	
    	PageResults<HongXunProductionStock> result = new PageResults<HongXunProductionStock>();
    	if (hongXunProductionStockList.size()==1){
    		result =  service.scanInStock(hongXunProductionStockList.get(0),result);  
    	}
    	return result;
   	}
        
    @ResponseBody
   	@RequestMapping("/outStock")
    private PageResults<HongXunProductionStock> outStock(
    		@RequestParam(value="proIdc",required=false) int proIdc,
    		@RequestParam(value="itemOutIdc",required=false) int itemOutIdc,
    		@RequestParam(value="sendQuantity",required=false) int sendQuantity,
    		@RequestParam(value="quantity",required=false) int quantity
    		){    	
    	PageResults<HongXunProductionStock> result = new PageResults<HongXunProductionStock>();
 		
    		result = service.outStock(proIdc,itemOutIdc,sendQuantity,result);  
    	
    	return result;
   	}
}