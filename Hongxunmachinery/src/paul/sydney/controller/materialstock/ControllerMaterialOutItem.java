package paul.sydney.controller.materialstock;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import paul.sydney.model.HongXunPurchaseItem;
import paul.sydney.service.materialstock.ServiceStoreOutItemInf;




@Controller 
@RequestMapping("/outStoreItem.htm") 
				 
public class ControllerMaterialOutItem {
    private final ServiceStoreOutItemInf service;
	//private BeansInf beans;
	//private MyMqttServer mqttServer;
	//private int MsgSeqId;
	
    @Autowired 
    public ControllerMaterialOutItem(ServiceStoreOutItemInf service){
        this.service = service; 
    	//this.beans = beans;
    }
 
    
    
    @ResponseBody
   	@RequestMapping("/loadData")
   	private List<Map<String,Object>> loadData() {
    	//System.out.println("para: " + para);
    	
    	List<Map<String,Object>> list = service.loadData();
    	//System.out.println("branchOneloadData");
   		return list;
   	} 


    
    

/*    
    @ResponseBody
   	@RequestMapping("/getStockinChildrens")
    private List<Map<String,Object>> getStockinChildrens(@RequestBody List<HongXunMaterialOutStoreNum> hongXunDataOneList){
    	int MainID = (hongXunDataOneList.get(0).getIdc());
    	System.out.println("MainID :" + MainID);
    	return service.getStockinChildrens(MainID);
    }
*/

    /*
    @ResponseBody
   	@RequestMapping("/saveRow")
    private List<Map<String,Object>> saveRow(@RequestBody List<HongXunPurchaseItem> hongXunDataTwoList) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{    	
    	// TODO Auto-generated catch block
    	System.out.println("saveRow: ");
    	if(hongXunDataTwoList.get(0).getMaterialPlanQuantity()!=null && hongXunDataTwoList.get(0).getPrice()!=null){
        	float value =hongXunDataTwoList.get(0).getPrice().floatValue() * hongXunDataTwoList.get(0).getMaterialPlanQuantity();
        	hongXunDataTwoList.get(0).setMaterialMoney(new BigDecimal(value));
    	}
    	HongXunPurchaseItem hongXunDataTwo = new HongXunPurchaseItem();
    	beans.mergeObject(hongXunDataTwoList.get(0), hongXunDataTwo);
    	if(hongXunDataTwo.getIdc()==0){
    		return service.saveRow(hongXunDataTwo);
    	}else{	
    		return service.updateRow(hongXunDataTwo);
    	}
   	}
    */   
    @ResponseBody
   	@RequestMapping("/deleteRow")
    private List<Map<String,Object>> deleteRow(@RequestBody List<HongXunPurchaseItem> hongXunDataTwoList){ 
    	int ID = hongXunDataTwoList.get(0).getIdc();
    	service.deleteRow(ID);
    	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
   		return list;
   	} 
    

    @ResponseBody
   	@RequestMapping("/getOutStoreItems")

    private List<Map<String,Object>> getOutStoreChildrens(@RequestParam(value = "outStoreNumID", required = true) int outStoreNumID){   	
    	//int OutStoreItemID = (hongXunMaterialOutStoreNumList.get(0).getIdc());
    	//System.out.println("OutStoreItemID :" + outStoreNumID);
    	return service.getOutStoreItems(outStoreNumID);
    }
/*    
    @ResponseBody
   	@RequestMapping("/autotimp")
   	public List<Map<String,Object>> autotimp(HttpServletRequest request) {
    	String q=request.getParameter("q");
    	//System.out.println("autotimp:" + q); 	
   		return service.autotimp(q);  
   	} */
    
    
    @ResponseBody
   	@RequestMapping("/getEntity")
   	public List<Map<String,Object>> getEntity() {
    	return service.getEntity();
   	} 
    

    
/*    @ResponseBody
   	@RequestMapping("/autotimp")
   	public List<Map<String,Object>> autotimp(HttpServletRequest request) {
    	String q=request.getParameter("q");
    	String outStoreNumID = request.getParameter("outStoreNumID");
    	//System.out.println("autotimp:" + outStoreNumID); 	
   		return service.autotimp(q, Integer.valueOf(outStoreNumID));  
   	} */
    
/*    @ResponseBody
   	@RequestMapping("/searchMaterialNum")
    private List<Map<String,Object>> searchPurchaseItem(@RequestParam(value = "name", required = true) String materialNum,
    													@RequestParam(value = "outStoreNumID", required = true) int outStoreNumID
    													//@RequestParam(value = "quantity", required = true) int quantity
    													){

    	List<Map<String,Object>> list = service.searchMaterialNum(outStoreNumID,materialNum);
    	//List<Map<String,Object>> list = service.outstoreMaterialNum(materialNum, outStoreNumID,  quantity);
		return list;
    	
    }*/
    
    @ResponseBody
   	@RequestMapping("/materialOutStore")
    private List<Map<String,Object>> materialOutStore(
    													@RequestParam(value = "id", required = true) int id,
    													@RequestParam(value = "quantity", required = true) int quantity
    													){    	
    	List<Map<String,Object>> list = service.outstoreMaterialNum(id,  quantity);
		return list;
    	
    }
/*    private boolean isDate(String date)
    {
  
      String rexp = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
      Pattern pat = Pattern.compile(rexp);
      Matcher mat = pat.matcher(date);
      boolean dateType = mat.matches();
      return dateType;
    }*/
}