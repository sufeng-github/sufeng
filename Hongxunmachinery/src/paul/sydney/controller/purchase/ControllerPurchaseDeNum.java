package paul.sydney.controller.purchase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import paul.sydney.service.purchase.ServicePurchaseDeNumInf;
import paul.sydney.commen.result.PageResults;
import paul.sydney.model.HongXunBomTree;
import paul.sydney.model.HongXunMaterialStock;
import paul.sydney.model.HongXunPurchaseDeNum;



@Controller 
@RequestMapping("/purchaseDeNum.htm")
public class ControllerPurchaseDeNum {
    private final ServicePurchaseDeNumInf service;
    @Autowired 
    public ControllerPurchaseDeNum(ServicePurchaseDeNumInf service){
        this.service = service; 
    }
    
	
    @ResponseBody
   	@RequestMapping("/getData")
    private PageResults<HongXunPurchaseDeNum> getData(/* @RequestBody HongXunMaterialStock hongXunMaterialStock,*/
									    		@RequestParam(value="purchaseDeNum",required=false) String purchaseDeNum,
									    		@RequestParam(value="startTime",required=false) String startTime,
									    		@RequestParam(value="endTime",required=false) String endTime,
									    		@RequestParam(value="purchaseDeStatus",required=false) String purchaseDeStatus,
    											@RequestParam(value="page", defaultValue="1") Integer pageNo,
			  									@RequestParam(value="rows", defaultValue="10") Integer pageSize){    	
    	// TODO Auto-generated catch block
    	SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" ); 
    	HongXunPurchaseDeNum hongXunPurchaseDeNum = new HongXunPurchaseDeNum();
    	if(purchaseDeNum != null && !purchaseDeNum.equals("")){
    		hongXunPurchaseDeNum.setPurchaseDeNum(purchaseDeNum);
    	}   	
    	if(startTime != null && !startTime.equals("")){    		
    		try {
				hongXunPurchaseDeNum.setStartTime(sdf.parse(startTime));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	if(endTime != null && !endTime.equals("")){
    		try {
				hongXunPurchaseDeNum.setEndTime(sdf.parse(endTime));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	if(purchaseDeStatus != null && !purchaseDeStatus.equals("")){
    		hongXunPurchaseDeNum.setPurchaseDeStatus(purchaseDeStatus);
    	}
    	PageResults<HongXunPurchaseDeNum> result = service.getData(hongXunPurchaseDeNum,pageNo,pageSize);
    	return result;
    }	
    
    @ResponseBody
   	@RequestMapping("/getEntity")
   	public List<Map<String,Object>> getEntity() {
    	return service.getEntity();
   	} 
    
    @ResponseBody
   	@RequestMapping("/saveRow")
    private List<Map<String,Object>> saveRow(@RequestBody List<HongXunPurchaseDeNum> hongXunPurchaseDeNumList) throws ParseException{
		//System.out.println("refresh");
	return service.saveRow(hongXunPurchaseDeNumList);
}

    
    @ResponseBody
   	@RequestMapping("/deleteRow")
    private List<Map<String, Object>> deleteRow(@RequestBody List<HongXunPurchaseDeNum> hongXunPurchaseDeNumList){
   		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
   		for(HongXunPurchaseDeNum item: hongXunPurchaseDeNumList){
   			if(item.getIdc()!=0){
   				service.deleteRow(item);
   			}
   		}
   		return list;
   	} 
    

    @ResponseBody
   	@RequestMapping("/updateSotockState")
    private List<Map<String,Object>> updateSotockState(@RequestParam(value = "purchaseNumID", required = true) int purchaseNumID){ 
    	//System.out.println("saveMoney");
    	//int mainID = hongXunPurchaseItemList.get(0).getPurchaseNumID();
    	//BigDecimal value = new BigDecimal(hongXunPurchaseItemList.get(0).getMaterialName());
    	return service.updateSotockState(purchaseNumID);
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
   	@RequestMapping("/newPurchaseDeNum")
   	private List<Map<String,Object>> newPurchaseDeNum(@RequestBody List<HongXunMaterialStock> hongXunMaterialStockList){
    	//System.out.println("newPurchaseNum");
    	return service.createNewPurchaseDeNum(hongXunMaterialStockList);
   	}
}