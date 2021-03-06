package paul.sydney.controller.weld;

import java.text.ParseException;
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
import paul.sydney.model.HongXunProductionStock;
import paul.sydney.model.HongXunProductionWeldStock;
import paul.sydney.service.weld.ServiceProductionWeldStockInf;


@Controller 
@RequestMapping("/productionWeldStock.htm") 
public class ControllerProductionWeldStock {
    private final ServiceProductionWeldStockInf service;
    @Autowired 
    public ControllerProductionWeldStock(ServiceProductionWeldStockInf service){
        this.service = service; 
    }
    
  
    @ResponseBody
   	@RequestMapping("/getData")
    private PageResults<HongXunProductionWeldStock> getData(/* @RequestBody HongXunMaterialStock hongXunMaterialStock,*/
    											@RequestParam(value="page", defaultValue="1") Integer pageNo,
			  									@RequestParam(value="rows", defaultValue="10") Integer pageSize){    	
    	// TODO Auto-generated catch block
    	//System.out.println("pageNo :" + pageNo + " pageSize:" + pageSize);
    	return service.getData(/*hongXunMaterialStock*/null,pageNo,pageSize);
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
    private List<Map<String,Object>> searchPurchaseItem(@RequestParam(value = "name", required = true) String materialNum){
    	//System.out.println(materialNum);
    	List<Map<String,Object>> list = service.searchMaterialNum(materialNum);
		return list;
    	
    }
    
    @ResponseBody
   	@RequestMapping("/getEntity")
   	public List<Map<String,Object>> getEntity() {
    	return service.getEntity();
   	} 
    
    @ResponseBody
   	@RequestMapping("/delAllRows")
    public List<Map<String,Object>> delAllRows(){
    	return service.delAllRows();
    
    }
    
    @ResponseBody
   	@RequestMapping("/deleteRow")
   	private List<Map<String,Object>> deleteRow(@RequestBody List<HongXunProductionWeldStock> entitys){	
   		//System.out.println("deleteRow");
   		List<Map<String, Object>> list = null;
   		for(HongXunProductionWeldStock item: entitys){ 			
   			list = service.deleteRow(item);
   		}
   		return list;
   	} 
    
    @ResponseBody
   	@RequestMapping("/saveRow")
    private List<Map<String,Object>> saveRow(@RequestBody List<HongXunProductionWeldStock> hongXunProductionStockList){
    	
    	//System.out.println("HongXunProduction saveRow");
	    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	for(HongXunProductionWeldStock item : hongXunProductionStockList){ 		
			if(item.getIdc()==0){
				list.addAll(service.saveRow(item));	
    		}else{   			
    			list.addAll(service.updateRow(item));
    		}	
    	}
    	return list;
   	}
}