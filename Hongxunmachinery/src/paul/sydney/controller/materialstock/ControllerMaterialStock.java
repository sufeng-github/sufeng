package paul.sydney.controller.materialstock;

import java.text.ParseException;
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
import paul.sydney.model.HongXunMaterialStock;
import paul.sydney.service.materialstock.ServiceMaterialStockInf;

@Controller 
@RequestMapping("/materialStock.htm") 
public class ControllerMaterialStock {
    private final ServiceMaterialStockInf service;
    @Autowired 
    public ControllerMaterialStock(ServiceMaterialStockInf service){
        this.service = service; 
    }
    

    @ResponseBody
   	@RequestMapping("/getData")
    private PageResults<HongXunMaterialStock> getData(/* @RequestBody HongXunMaterialStock hongXunMaterialStock,*/
									    		@RequestParam(value="materialNum",required=false) String materialNum,
									    		@RequestParam(value="specification",required=false) String specification,
									    		@RequestParam(value="alarm",required=false) String alarm,
    											@RequestParam(value="page", defaultValue="1") Integer pageNo,
			  									@RequestParam(value="rows", defaultValue="10") Integer pageSize){    	
    	// TODO Auto-generated catch block
    	HongXunMaterialStock hongXunMaterialStock = new HongXunMaterialStock();
    	
    	if(materialNum != null && !materialNum.equals("")){
    		hongXunMaterialStock.setMaterialNum(materialNum);
    	}   	
    	if(specification != null && !specification.equals("")){
    		hongXunMaterialStock.setSpecification(specification);
    	}
    	if(alarm != null && !alarm.equals("")){
    		hongXunMaterialStock.setAlarm(alarm);
    	}
    	return service.getData(hongXunMaterialStock,pageNo,pageSize);
    }	
    	
/*    @ResponseBody
   	@RequestMapping("/getMaterialDelivery")
    private List<Map<String,Object>> getMaterialDelivery(@RequestParam(value = "purchaseDeId", required = true) int purchaseDeId){    	
    	// TODO Auto-generated catch block
    	return service.getMaterialDelivery(purchaseDeId);
    }*/
/*    
    @ResponseBody
   	@RequestMapping("/getMaterialAllDelivery")
    private List<Map<String,Object>> getMaterialAllDelivery(){    	
    	// TODO Auto-generated catch block
    	return service.getMaterialAllDelivery();
    }*/

    
    @ResponseBody
   	@RequestMapping("/autotimp")
   	public List<Map<String,Object>> autotimp(HttpServletRequest request) {
    	String q=request.getParameter("q");
    	//System.out.println("autotimp:" + q); 	
   		return service.autotimp(q);  
   	} 
    
    
    @ResponseBody
   	@RequestMapping("/delAllRows")
    public List<Map<String,Object>> delAllRows(){
    	
    	return service.delAllRows();
    
    }
    
    @ResponseBody
   	@RequestMapping("/deleteRow")
   	private List<Map<String,Object>> deleteRow(@RequestBody List<HongXunMaterialStock> hongXunMaterialStockList) throws ParseException {	
   		//System.out.println("deleteRow");
   		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
   		for(HongXunMaterialStock item: hongXunMaterialStockList){
   			if(item.getIdc()!=0){
   				list = service.deleteRow(item);
   			}
   		}
   		return list;
   	} 
     
    
/*    @ResponseBody
   	@RequestMapping("/moveRow")
   	private List<Map<String,Object>> moveRow(@RequestBody List<HongXunMaterialStock> hongXunMaterialStockList) {	
   		//System.out.println("deleteRow");
   		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
   		for(HongXunMaterialStock item: hongXunMaterialStockList){
   			if(item.getIdc()!=0){
   				list = service.moveRow(item);
   			}
   		}
   		return list;
   	}*/
    
    
    
    @ResponseBody
   	@RequestMapping("/getEntity")
   	public List<Map<String,Object>> getEntity() {
    	return service.getEntity();
   	} 
    
    @ResponseBody
   	@RequestMapping("/saveRow")
    private List<Map<String,Object>> saveRow(@RequestBody List<HongXunMaterialStock> hongXunMaterialStockList){
    	
/*	    if(hongXunMaterialStockList.get(0).getIdc()==0){  
	    	return service.saveRow(hongXunMaterialStockList.get(0));
	    }else{	
	    	return service.updateRow(hongXunMaterialStockList.get(0));
	    }*/
    	System.out.println("HongXunMaterialStock saveRow ：" + hongXunMaterialStockList.size());
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	for(HongXunMaterialStock item : hongXunMaterialStockList){ 	
    		if(item.getMaterialNum()==null){
    			break;
    		}
    		if(item.getSafeQuantity() == null){
    			item.setSafeQuantity(0);
    		}
    		if(item.getIdc()==0){
				list.addAll(service.saveRow(item));	
    		}else{   			
    			list.addAll(service.updateRow(item));
    		}	
    	}
    	return list;
   	}
}