package paul.sydney.controller.materialstock;


import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import paul.sydney.service.materialstock.ServiceStoreOutNumInf;



@Controller 
@RequestMapping("/storeOutNum.htm") 
public class ControllerMaterialOutNum {
    private final ServiceStoreOutNumInf service;
    @Autowired 
    public ControllerMaterialOutNum(ServiceStoreOutNumInf service){
        this.service = service; 
    }
    
/*    @InitBinder
    public void initBinder(WebDataBinder binder) {
    	System.out.println("InitBinder");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }*/  
    
    
    @ResponseBody
   	@RequestMapping("/loadData")
   	private List<Map<String,Object>> loadData() {
    	List<Map<String,Object>> list = service.loadData();
    	//System.out.println("loadData");
   		return list;
   	}  
   
    @ResponseBody
   	@RequestMapping("/updateStatus")
    private List<Map<String,Object>> updateStatus(@RequestParam(value = "outStoreNumID", required = true) int outStoreNumID){
    		//System.out.println("updateStatus");
    	return service.updateStatus(outStoreNumID);
    }
    /*  
    @ResponseBody
   	@RequestMapping("/deleteRow")
    private Boolean deleteRow(@RequestBody List<HongXunPurchaseNum> hongXunDataOneList) throws ParseException{
   		int ID = hongXunDataOneList.get(0).getIdc();
   		return service.deleteRow(ID);
   	} 
    
    @ResponseBody
   	@RequestMapping("/betweenDateFind")
    private List<Map<String,Object>> betweenDateFind(@RequestParam(value = "beginDate", required = true) String date1,@RequestParam(value = "endDate", required = true) String date2) throws ParseException{
    	System.out.println("betweenDateFind");
    	List<Map<String,Object>> list = service.betweenDateFind(date1, date2);
   		return list;
   	} 

    @ResponseBody
   	@RequestMapping("/searchPurchaseNum")
    private List<Map<String,Object>> searchNum(@RequestParam(value = "name", required = true) String purchaseNum){

    	List<Map<String,Object>> list = service.searchNum(purchaseNum);
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
   	@RequestMapping("/newPurchaseNum")
   	private List<Map<String,Object>> newPurchaseNum(@RequestBody List<HongXunBomTree> hongXunBomTreeList){
    	System.out.println("newPurchaseNum");
    	//SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	HongXunPurchaseNum hongXunPurchase = new HongXunPurchaseNum();
    	hongXunPurchase.setPurchaseDate(new Date());
    	hongXunPurchase.setPurchaseDeliveryDate(new Date());
    	hongXunPurchase.setPurchaseQuantity(String.valueOf(hongXunBomTreeList.size()-1));
    	hongXunPurchase.setPurchaseProcedure(hongXunBomTreeList.get(0).getRemark());
    	hongXunPurchase.setPurchaseNum(hongXunBomTreeList.get(0).getStatus());
    	//hongXunPurchase.set(hongXunPoNList.size());
    	hongXunPurchase.setPurchaseStatus("新增外购");
    	List<Map<String, Object>> list = service.saveRow(hongXunPurchase);
    	hongXunBomTreeList = hongXunBomTreeList.subList(1, hongXunBomTreeList.size());
    	List<HongXunPurchaseItem> hongXunPurchaseItemList = new ArrayList<HongXunPurchaseItem>();
    	for(HongXunBomTree item: hongXunBomTreeList){
    		HongXunPurchaseItem hongXunPurchaseItem = new HongXunPurchaseItem();
    		hongXunPurchaseItem.setMaterialNum(item.getBomMaterialNum());
    		hongXunPurchaseItem.setSpecification(item.getBomSpacification());		
    		//hongXunPurchaseItem.setPrice(item.getPrice());
    		//hongXunPurchaseItem.setMaterialMoney(item.getTotalAmount());
    		hongXunPurchaseItem.setMaterialPlanQuantity(item.getBomQuantity());
    		//hongXunPurchaseItem.setUnit(item.getUnit());
       		hongXunPurchaseItem.setOrderNumID(item.getIdc());
    		hongXunPurchaseItem.setPurchaseNumID(hongXunPurchase.getIdc());
    		boolean flag = false;
    		for(HongXunPurchaseItem itemNer: hongXunPurchaseItemList){
    			if(itemNer.getSpecification().equals(hongXunPurchaseItem.getSpecification())){
    				flag = true;
    				itemNer.setMaterialPlanQuantity(itemNer.getMaterialPlanQuantity()+hongXunPurchaseItem.getMaterialPlanQuantity());
    				service.update(itemNer);
    				break;
    			}
    		}
    		if(flag == false){
	    		service.save(hongXunPurchaseItem);
	    		hongXunPurchaseItemList.add(hongXunPurchaseItem);
    		}
    	}
    	//itemsID = itemsID.substring(0, itemsID.length()-1);
    	//hongXunPurchase.setComponentName(itemsID);	
    	//hongXunPurchase.setWorkProcedure(attribute);	
   		return list;
   	}*/
    
}