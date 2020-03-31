package paul.sydney.controller.production;


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
import paul.sydney.model.HongXunDeliveryItem;
import paul.sydney.model.HongXunDeliveryNum;
import paul.sydney.model.HongXunMaterialStock;
import paul.sydney.model.HongXunProductionStock;
import paul.sydney.service.materialstock.ServiceMaterialStockInf;
import paul.sydney.service.production.ServiceDeliveryItemInf;
import paul.sydney.service.production.ServiceProductionStockInf;


@Controller 
@RequestMapping("/deliveryItem.htm") 
public class ControllerDeliveryItem {
    private final ServiceDeliveryItemInf service;
    @Autowired 
    public ControllerDeliveryItem(ServiceDeliveryItemInf service){
        this.service = service; 
    }
    
/*  
    @ResponseBody
   	@RequestMapping("/loadData")
    private List<Map<String,Object>> loadData(@RequestParam(value = "deliveryNumID", required = true) Integer deliveryNumID){    	
    	// TODO Auto-generated catch block
    	return service.loadData(deliveryNumID);
    }*/
    @ResponseBody
   	@RequestMapping("/getData")
    private PageResults<HongXunDeliveryItem> getData(/* @RequestBody HongXunMaterialStock hongXunMaterialStock,*/
									    		@RequestParam(value="materialNum",required=false) String materialNum,
									    		@RequestParam(value="specification",required=false) String specification,
									    		@RequestParam(value="deliveryNumID",required=false) Integer deliveryNumID,
    											@RequestParam(value="page", defaultValue="1") Integer pageNo,
			  									@RequestParam(value="rows", defaultValue="10") Integer pageSize){    	
    	// TODO Auto-generated catch block
    	HongXunDeliveryItem hongXunDeliveryItem = new HongXunDeliveryItem(); 
    	hongXunDeliveryItem.setHongXunDeliveryNum(new HongXunDeliveryNum());
    	if(materialNum != null && !materialNum.equals("")){
    		hongXunDeliveryItem.setMaterialNo(materialNum);
    	}   	
    	if(specification != null && !specification.equals("")){
    		hongXunDeliveryItem.setMaterialDesc(specification);
    	}
    	if(deliveryNumID !=null){
    		hongXunDeliveryItem.getHongXunDeliveryNum().setIdc(deliveryNumID);
    	}
    	return service.getData(hongXunDeliveryItem,pageNo,pageSize);
    }	
    

    @ResponseBody
   	@RequestMapping("/autotimp")
   	public List<Map<String,Object>> autotimp(HttpServletRequest request) {
    	String q = request.getParameter("q");
    	String deliveryNumID = request.getParameter("deliveryNumID");	
   		return service.autotimp(q, Integer.valueOf(deliveryNumID));  
   	} 

    
    @ResponseBody
   	@RequestMapping("/getEntity")
   	public List<Map<String,Object>> getEntity() {
    	return service.getEntity();
   	} 
    
    
    @ResponseBody
   	@RequestMapping("/deleteRow")
    public List<Map<String,Object>> deleteRow(@RequestBody List<HongXunDeliveryItem> hongXunDeliveryItemList){
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	for(HongXunDeliveryItem item : hongXunDeliveryItemList){ 	
    		service.deleteRow(item);
    	}
    	return list;
    }
    
    @ResponseBody
   	@RequestMapping("/saveRow")
    private List<Map<String,Object>> saveRow(@RequestBody List<HongXunDeliveryItem> hongXunDeliveryItemList){	
    	//System.out.println("HongXunProduction saveRow");
	    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	for(HongXunDeliveryItem item : hongXunDeliveryItemList){ 		
			if(item.getIdc()==0){
				service.saveRow(item);	
    		}else{   			
    			service.updateRow(item);
    		}	
    	}
    	return list;
	
   	}
    @ResponseBody
   	@RequestMapping("/importData")
    public List<Map<String,Object>> importData(@RequestBody List<HongXunDeliveryItem> hongXunDeliveryItemList){ 	
    	return service.importData(hongXunDeliveryItemList);
    }
    	
}