package paul.sydney.controller.order;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
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
import paul.sydney.model.HongXunOrderNum;
import paul.sydney.model.HongXunPoN;
import paul.sydney.model.HongXunPurchaseItem;
import paul.sydney.service.order.ServicePoNInf;

@Transactional
@Controller 
@RequestMapping("/poNum.htm") 
public class ControllerPoN {
    private final ServicePoNInf service;
    @Autowired 
    public ControllerPoN(ServicePoNInf service){
        this.service = service; 
    } 
    
/*    @ResponseBody
   	@RequestMapping("/loadData")
   	private List<Map<String,Object>> loadData() {
    	List<Map<String,Object>> list = service.loadData();
    	//System.out.println("tenloadData");
   		return list;
   	} */ 
    
    @ResponseBody
   	@RequestMapping("/getData")
    private PageResults<HongXunPoN> getData(			
    											@RequestParam(value="materialNum",required=false) String materialNum,
    											@RequestParam(value="specification",required=false) String specification,
									    		@RequestParam(value="pon",required=false) String pon,
									    		@RequestParam(value="line",required=false) String line,
    											@RequestParam(value="orderNumId",required=false) Integer orderNumId,
    											@RequestParam(value="page", defaultValue="1") Integer pageNo,
			  									@RequestParam(value="rows", defaultValue="10") Integer pageSize){ 
    	HongXunPoN hongXunPoN = new HongXunPoN();
		if (orderNumId!=null && orderNumId!=0) {		
			hongXunPoN.setOrderNumID(orderNumId);
		}
    	if(materialNum != null && !materialNum.equals("")){
    		hongXunPoN.setMaterialNo(materialNum);
    	}
    	if(specification != null && !specification.equals("")){
    		hongXunPoN.setMaterialDesc(specification);
    	}
    	if(pon != null && !pon.equals("")){
    		hongXunPoN.setPon(pon);
    	}
    	if(line != null && !line.equals("")){
    		hongXunPoN.setLine(line);
    	}
    	PageResults<HongXunPoN> result = service.getData(hongXunPoN,pageNo,pageSize);
    	return result;	
    }
    
    @ResponseBody
   	@RequestMapping("/saveRow")
   	private List<Map<String,Object>> saveRow(@RequestBody List<HongXunPoN> hongXunPoNList) throws ParseException {
    	//System.out.println("saveRow: ");
    	List<Map<String, Object>> list = null;
    	for(HongXunPoN item : hongXunPoNList){ 		
			if(item.getIdc()==0){
				if(list != null){
					list.containsAll(service.saveRow(item));
				}else{
					list = service.saveRow(item);
				}
    		}else{
    			if(list != null){
    				list.containsAll(service.updateRow(item));
    			}else{
    				list = service.updateRow(item);
    			}
    		}	
    	}
    	return list;
   	}
    
    @ResponseBody
   	@RequestMapping("/deleteRow")
    private List<Map<String,Object>> deleteRow(@RequestParam(value = "idc", required = true) int id){
   		return service.deleteRow(id);
   	} 
    
    @ResponseBody
   	@RequestMapping("/decrease")
   	public List<Map<String,Object>> decrease(@RequestParam(value = "orderNumID", required = true) int id){
    		//System.out.println("changeStatus");
    	return service.decrease(id);
   	} 
    
    @ResponseBody
   	@RequestMapping("/orderNumGetList")
    private List<Map<String,Object>> orderNumGetList(@RequestParam(value = "orderNumID", required = true) int orderNumID){
    	List<Map<String,Object>> list = service.orderNumGetList(orderNumID);
		return list; 	
    }
    
    @ResponseBody
   	@RequestMapping("/ponNumList")
   	private List<Map<String,Object>> ponNumList(@RequestParam(value = "pon", required = true) String pon) {
    	List<Map<String,Object>> list = service.ponNumList(pon);
    	//System.out.println("orderNum");
   		return list;
   	} 
    
    @ResponseBody
   	@RequestMapping("/updateState")
    private List<Map<String,Object>> updateState(@RequestParam(value = "orderNumID", required = true) int orderNumID,
    											@RequestParam(value = "state", required = true) String state){
    	//System.out.println("updateState");
    	List<Map<String,Object>> list = service.updateState(orderNumID,state);
		return list; 	
    }
    
    
    @ResponseBody
   	@RequestMapping("/autotimp")
   	public List<Map<String,Object>> autotimp(HttpServletRequest request) {
    	String q=request.getParameter("q");
    	String orderNumID = request.getParameter("orderNumID");	
   		return service.autotimp(Integer.valueOf(orderNumID), q);  
   	} 
    
    
}