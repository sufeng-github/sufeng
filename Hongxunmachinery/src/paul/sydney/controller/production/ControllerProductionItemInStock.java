package paul.sydney.controller.production;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import paul.sydney.commen.result.PageResults;
import paul.sydney.model.HongXunProductionItemInStock;
import paul.sydney.model.HongXunProductionStock;
import paul.sydney.model.HongXunPurchaseItemInStock;
import paul.sydney.model.HongXunPurchaseNum;
import paul.sydney.service.production.ServiceProductionItemInStockInf;


@Controller 				 
@RequestMapping("/productionItemInStock.htm")
public class ControllerProductionItemInStock {
    private final ServiceProductionItemInStockInf service;
    @Autowired 
    public ControllerProductionItemInStock(ServiceProductionItemInStockInf service){
        this.service = service; 
    }
 


    @ResponseBody
   	@RequestMapping("/autotimp")
   	public List<Map<String,Object>> autotimp(HttpServletRequest request) {
    	String q=request.getParameter("q");
    	//String name=request.getParameter("name");
   		return service.autotimp(q);  
   	} 
    
    @ResponseBody
   	@RequestMapping("/getData")
    private PageResults<HongXunProductionItemInStock> getData(  													
											    		@RequestParam(value="materialNum",required=false) String materialNum,
											    		@RequestParam(value="specification",required=false) String specification,
											    		@RequestParam(value="name",required=false) String name,
											    		@RequestParam(value="startTime",required=false) String startTime,
											    		@RequestParam(value="endTime",required=false) String endTime,
											    		@RequestParam(value="page", defaultValue="1") Integer pageNo,
														@RequestParam(value="rows", defaultValue="10") Integer pageSize){ 

    	SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" ); 
    	HongXunProductionItemInStock hongXunProductionItemInStock = new HongXunProductionItemInStock();
    	hongXunProductionItemInStock.setHongXunProductionStock(new HongXunProductionStock());
    	if(materialNum != null && !materialNum.equals("")){
    		hongXunProductionItemInStock.getHongXunProductionStock().setMaterialNum(materialNum);
    	} 
    	if(specification != null && !specification.equals("")){
    		hongXunProductionItemInStock.getHongXunProductionStock().setSpecification(specification);
    	}  
    	if(startTime != null && !startTime.equals("")){    		
    		try {
    			hongXunProductionItemInStock.setStartTime(sdf.parse(startTime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
    	}
    	if(endTime != null && !endTime.equals("")){
    		try {
    			hongXunProductionItemInStock.setEndTime(sdf.parse(endTime));
			} catch (ParseException e) {			
				e.printStackTrace();
			}
    	}
    	if(name !=null && !name.equals("")){
    		hongXunProductionItemInStock.getHongXunProductionStock().setName(name);
    	}
    	PageResults<HongXunProductionItemInStock> result = service.getData(hongXunProductionItemInStock,pageNo,pageSize);
    	return result;
    }
}