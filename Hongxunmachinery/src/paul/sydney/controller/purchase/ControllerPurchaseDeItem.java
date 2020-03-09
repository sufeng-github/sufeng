package paul.sydney.controller.purchase;

import java.text.SimpleDateFormat;
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
import paul.sydney.model.HongXunPurchaseDeItem;
import paul.sydney.service.purchase.ServicePurchaseDeItemInf;



@Controller 
@RequestMapping("/purchaseDeItem.htm") 
public class ControllerPurchaseDeItem {
    private final ServicePurchaseDeItemInf service;
    @Autowired 
    public ControllerPurchaseDeItem(ServicePurchaseDeItemInf service){
        this.service = service; 
    }
    
/*  @InitBinder
    public void initBinder(WebDataBinder binder) {
    	System.out.println("InitBinder");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }*/  
    
/*    
    @ResponseBody
   	@RequestMapping("/loadData")
   	private List<Map<String,Object>> loadData() {
    	List<Map<String,Object>> list = service.loadData();
    	//System.out.println("loadData");
   		return list;
   	}  */
    
    @ResponseBody
   	@RequestMapping("/getEntity")
   	public List<Map<String,Object>> getEntity() {
    	return service.getEntity();
   	} 
    
    @ResponseBody
   	@RequestMapping("/getNumList")
   	public List<Map<String,Object>> getNumList(@RequestParam(value = "purchaseDeNumId", required = true) int id) {
    	return service.getNumList(id);
   	} 
    
    @ResponseBody
   	@RequestMapping("/getData")
    private PageResults<HongXunPurchaseDeItem> getData(/* @RequestBody HongXunMaterialStock hongXunMaterialStock,*/
											    		@RequestParam(value="purchaseDeNumId",required=true) int purchaseDeNumId,
														@RequestParam(value="page", defaultValue="1") Integer pageNo,
														@RequestParam(value="rows", defaultValue="10") Integer pageSize){    	
		// TODO Auto-generated catch block
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		HongXunPurchaseDeItem hongXunPurchaseDeItem = new HongXunPurchaseDeItem();
		if (purchaseDeNumId!=0) {
			hongXunPurchaseDeItem.setDeNumId(purchaseDeNumId);
		}
		return service.getData(hongXunPurchaseDeItem, pageNo, pageSize);
	}
    
    @ResponseBody
   	@RequestMapping("/deleteRow")
    private Boolean deleteRow(@RequestBody List<HongXunPurchaseDeItem> hongXunPurchaseDeItemList){
   		//int ID = hongXunPurchaseNumList.get(0).getIdc();
    	for(HongXunPurchaseDeItem item: hongXunPurchaseDeItemList){
    		service.deleteRow(item.getIdc());
    	}
   		return true;
   	} 
    
   /* @ResponseBody
   	@RequestMapping("/saveRow")
    private List<Map<String,Object>> saveRow(@RequestBody List<HongXunPurchaseDeNum> hongXunPurchaseDeNumList) throws ParseException{
		//System.out.println("refresh");
	return service.saveRow(hongXunPurchaseDeNumList);
}

    @ResponseBody
   	@RequestMapping("/refresh")
    private List<Map<String,Object>> refresh(@RequestBody List<HongXunPurchaseNum> hongXunPurchaseNumList) throws ParseException{
    		//System.out.println("refresh");
    	return service.refresh(hongXunPurchaseNumList);
    }

    
    @ResponseBody
   	@RequestMapping("/betweenDateFind")
    private List<Map<String,Object>> betweenDateFind(@RequestParam(value = "beginDate", required = true) String date1,@RequestParam(value = "endDate", required = true) String date2) throws ParseException{
    	//System.out.println("betweenDateFind");
    	List<Map<String,Object>> list = service.betweenDateFind(date1, date2);
   		return list;
   	} */
       
    /*
    @ResponseBody
   	@RequestMapping("/searchNum")
    private List<Map<String,Object>> searchNum(@RequestParam(value = "num", required = true) String num){
    	HongXunPurchaseNum hongXunDataOne = new HongXunPurchaseNum();
    	hongXunDataOne.setPurchaseOrderNum(num);
    	List<Map<String,Object>> list = service.searchNum(hongXunDataOne);
		return list;
    	
    }
    */
  /*  @ResponseBody
   	@RequestMapping("/updateSotockState")
    private List<Map<String,Object>> updateSotockState(@RequestParam(value = "purchaseNumID", required = true) int purchaseNumID){ 
    	//System.out.println("saveMoney");
    	//int mainID = hongXunPurchaseItemList.get(0).getPurchaseNumID();
    	//BigDecimal value = new BigDecimal(hongXunPurchaseItemList.get(0).getMaterialName());
    	return service.updateSotockState(purchaseNumID);
    }
    
    @ResponseBody
   	@RequestMapping("/searchPurchaseDeNum")
    private List<Map<String,Object>> searchNum(@RequestParam(value = "name", required = true) String purchaseDeNum){
    	List<Map<String,Object>> list = service.searchNum(purchaseDeNum);
		return list;   	
    }*/
    
    @ResponseBody
   	@RequestMapping("/autotimp")
   	public List<Map<String,Object>> autotimp(HttpServletRequest request) {
    	String q=request.getParameter("q");
    	//System.out.println("autotimp:" + q); 	
   		return service.autotimp(q);  
   	} 
    
  /*  @ResponseBody
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
   	}*/
}