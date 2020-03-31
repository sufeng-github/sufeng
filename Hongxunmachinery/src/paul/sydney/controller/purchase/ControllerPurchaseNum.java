package paul.sydney.controller.purchase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import paul.sydney.model.HongXunPurchaseNum;
import paul.sydney.service.purchase.ServicePurchaseNumInf;
import paul.sydney.commen.result.PageResults;
import paul.sydney.model.HongXunBomTree;
import paul.sydney.model.HongXunPurchaseDeItem;


@Controller 
@RequestMapping("/purchaseNum.htm")
public class ControllerPurchaseNum {
    private final ServicePurchaseNumInf service;
    @Autowired 
    public ControllerPurchaseNum(ServicePurchaseNumInf service){
        this.service = service; 
    }  
    
    @ResponseBody
   	@RequestMapping("/getData")
    private PageResults<HongXunPurchaseNum> getData(/* @RequestBody HongXunMaterialStock hongXunMaterialStock,*/
									    		@RequestParam(value="purchaseNum",required=false) String purchaseNum,
									    		@RequestParam(value="startTime",required=false) String startTime,
									    		@RequestParam(value="endTime",required=false) String endTime,
									    		@RequestParam(value="purchaseStatus",required=false) String purchaseStatus,
    											@RequestParam(value="page", defaultValue="1") Integer pageNo,
			  									@RequestParam(value="rows", defaultValue="10") Integer pageSize){    	
    	// TODO Auto-generated catch block
    	SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" ); 
    	HongXunPurchaseNum hongXunPurchaseNum = new HongXunPurchaseNum();
    	if(purchaseNum != null && !purchaseNum.equals("")){
    		hongXunPurchaseNum.setPurchaseNum(purchaseNum);
    	}   	
    	if(startTime != null && !startTime.equals("")){    		
    		try {
				hongXunPurchaseNum.setStartTime(sdf.parse(startTime));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	if(endTime != null && !endTime.equals("")){
    		try {
				hongXunPurchaseNum.setEndTime(sdf.parse(endTime));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	if(purchaseStatus != null && !purchaseStatus.equals("")){
    		hongXunPurchaseNum.setPurchaseStatus(purchaseStatus);
    	}
    	PageResults<HongXunPurchaseNum> result = service.getData(hongXunPurchaseNum,pageNo,pageSize);
    	return result;
    }		
    
    
    
    @ResponseBody
   	@RequestMapping("/getEntity")
   	public List<Map<String,Object>> getEntity() {
    	return service.getEntity();
   	} 
    
    @ResponseBody
   	@RequestMapping("/saveRow")
    private List<Map<String,Object>> saveRow(@RequestBody List<HongXunPurchaseNum> hongXunPurchaseNumList) throws ParseException{
		//System.out.println("refresh");
	return service.saveRow(hongXunPurchaseNumList);
}

    
    @ResponseBody
   	@RequestMapping("/deleteRow")
    private List<Map<String, Object>> deleteRow(@RequestBody List<HongXunPurchaseNum> hongXunPurchaseNumList){
   		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
   		for(HongXunPurchaseNum item: hongXunPurchaseNumList){   			
   			service.deleteRow(item);  			
   		}
   		return list;
   	}
    
    @ResponseBody
   	@RequestMapping("/autotimp")
   	public List<Map<String,Object>> autotimp(HttpServletRequest request) {
    	String q=request.getParameter("q");
    	//System.out.println("autotimp:" + q); 	
   		return service.autotimp(q);  
   	} 
    
    @ResponseBody
   	@RequestMapping("/newMaterialOutStoreNum")
   	private List<Map<String,Object>> newMaterialOutStoreNum(@RequestBody List<HongXunBomTree> hongXunBomTreeList){
    	//System.out.println("newMaterialOutStoreNum");
    	return service.createOutStoreSheet(hongXunBomTreeList);
   	}
    
    @ResponseBody
   	@RequestMapping("/newPurchaseNum")
   	private List<Map<String,Object>> newPurchaseNum(@RequestBody List<HongXunPurchaseDeItem> hongXunPurchaseDeItemList){
    	//System.out.println("newPurchaseNum");
    	return service.createNewPurchaseNum(hongXunPurchaseDeItemList);
   	}
        
}