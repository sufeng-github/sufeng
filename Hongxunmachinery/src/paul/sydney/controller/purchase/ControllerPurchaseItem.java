package paul.sydney.controller.purchase;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import paul.sydney.model.HongXunPurchaseNum;
import paul.sydney.service.purchase.ServicePurchaseItemInf;
import paul.sydney.model.HongXunMaterialStock;
import paul.sydney.model.HongXunPurchaseItem;


@Controller 
@RequestMapping("/purchaseItem.htm") 
				 
public class ControllerPurchaseItem {
    private final ServicePurchaseItemInf service;

	//private MyMqttServer mqttServer;
	//private int MsgSeqId;
	
    @Autowired 
    public ControllerPurchaseItem(ServicePurchaseItemInf service){
        this.service = service; 
    	
    }
 
    
    @ResponseBody
   	@RequestMapping("/stockinLoadData")
   	private List<Map<String,Object>> stockinLoadDatainLoadData() {
    	//System.out.println("stockinLoadData");	
    	List<Map<String,Object>> list = service.stockinLoadData();
    	//System.out.println("branchOneloadData");
   		return list;
   	} 
    
    
    @ResponseBody
   	@RequestMapping("/loadData")
   	private List<Map<String,Object>> loadData() {
    	//System.out.println("para: " + para);
    	
    	List<Map<String,Object>> list = service.loadData();
    	//System.out.println("branchOneloadData");
   		return list;
   	} 
    
    @ResponseBody
   	@RequestMapping("/getAllPurchaseItems")
   	private List<Map<String,Object>> getAllPurchaseItems() {
    	//System.out.println("para: " + para);
    	List<Map<String,Object>> list = service.getAllPurchaseItems();
   		return list;
   	} 
    
    
    @ResponseBody
   	@RequestMapping("/saveMoney")
    private List<Map<String,Object>> saveMoney(@RequestBody List<HongXunPurchaseItem> hongXunPurchaseItemList){ 
    	//System.out.println("saveMoney");
    	int mainID = hongXunPurchaseItemList.get(0).getPurchaseNumID();
    	//BigDecimal value = new BigDecimal(hongXunPurchaseItemList.get(0).getMaterialName());
    	return service.saveMoney(mainID);
    }
    
    
/*    
    @ResponseBody
   	@RequestMapping("/getStockinChildrens")
    private List<Map<String,Object>> getStockinChildrens(@RequestBody List<HongXunPurchaseNum> hongXunDataOneList){
    	int MainID = (hongXunDataOneList.get(0).getIdc());
    	System.out.println("MainID :" + MainID);
    	return service.getStockinChildrens(MainID);
    }
*/
    @ResponseBody
   	@RequestMapping("/getPurchaseItems")
    private List<Map<String,Object>> getPurchaseItemChildrens(@RequestParam(value = "purchaseNumID", required = true) int purchaseNumID){
    	//int purchaseNumID = (hongXunPurchaseItemList.get(0).getIdc());
    	//System.out.println("purchaseNumID :" + purchaseNumID);
    	return service.getPurchaseItems(purchaseNumID);
    }
    
    @ResponseBody
   	@RequestMapping("/movePurchaseItem")
    private List<Map<String,Object>> movePurchaseItem(@RequestParam(value = "idc", required = true) int id){
    	//int purchaseNumID = (hongXunPurchaseItemList.get(0).getIdc());
    	//System.out.println("purchaseNumID :" + purchaseNumID);
    	return service.movePurchaseItem(id);
    }
    
    
    @ResponseBody
   	@RequestMapping("/saveRow")
    private List<Map<String,Object>> saveRow(@RequestBody List<HongXunPurchaseItem> hongXunPurchaseItemList) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{    	
    	// TODO Auto-generated catch block
    	//System.out.println("saveRow: ");
    	
   	 	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	   	for(HongXunPurchaseItem item : hongXunPurchaseItemList){ 
	    	if(item.getMaterialPurchaseQuantity()!=null && item.getPrice()!=null){
	        	float value =item.getPrice().floatValue() * item.getMaterialPurchaseQuantity();
	        	item.setMaterialMoney(new BigDecimal(value));
	    	}
	   		if(item.getIdc()==0){
				list.addAll(service.saveRow(item));	
	   		}else{   			
	   			list.addAll(service.updateRow(item));
	   		}	
	   	}
	   	return list;

/*    	if(hongXunPurchaseItemList.get(0).getMaterialPurchaseQuantity()!=null && hongXunPurchaseItemList.get(0).getPrice()!=null){
        	float value =hongXunPurchaseItemList.get(0).getPrice().floatValue() * hongXunPurchaseItemList.get(0).getMaterialPurchaseQuantity();
        	hongXunPurchaseItemList.get(0).setMaterialMoney(new BigDecimal(value));
    	}
    	HongXunPurchaseItem hongXunPurchaseItem = new HongXunPurchaseItem();
    	beans.mergeObject(hongXunPurchaseItemList.get(0), hongXunPurchaseItem);
    	if(hongXunPurchaseItem.getIdc()==0){
    		return service.saveRow(hongXunPurchaseItem);
    	}else{	
    		return service.updateRow(hongXunPurchaseItem);
    	}*/
   	}
       
    @ResponseBody
   	@RequestMapping("/deleteRow")
    private List<Map<String,Object>> deleteRow(@RequestBody List<HongXunPurchaseItem> hongXunPurchaseItemList){ 
    	int ID = hongXunPurchaseItemList.get(0).getIdc();
    	service.deleteRow(ID);
    	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
   		return list;
   	} 
    
    @ResponseBody
   	@RequestMapping("/getPurchaseSheet")
    private List<Map<String,Object>> getPurchaseSheet(@RequestBody List<HongXunPurchaseNum> hongXunDataOneList){
    	//System.out.println("getPurchaseSheet");
    	return 	service.getPurchaseSheet(hongXunDataOneList);
    }

    
    @ResponseBody
   	@RequestMapping("/autotimp")
   	public List<Map<String,Object>> autotimp(HttpServletRequest request) {
    	String q=request.getParameter("q");
    	int purchaseNumID= Integer.valueOf(request.getParameter("purchaseNumID"));
    	//System.out.println("autotimp:" + q); 	
   		return service.autotimp(q, purchaseNumID);  
   	} 

    
    @ResponseBody
   	@RequestMapping("/search")
    private List<Map<String,Object>> searchPurchaseItem(@RequestParam(value = "name", required = true) String materialNum,
    													@RequestParam(value = "purchaseNumID", required = true) int purchaseNumID
    													//@RequestParam(value = "quantity", required = true) int quantity
    													){

    	List<Map<String,Object>> list = service.searchPurchaseItem(purchaseNumID,materialNum);
		return list;
    	
    }
    
    @ResponseBody
   	@RequestMapping("/materialInStore")
    private List<Map<String,Object>> materialInStore(
    													@RequestParam(value = "id", required = true) int id,
    													@RequestParam(value = "quantity", required = true) int quantity
    													){    	
    	List<Map<String,Object>> list = service.instorePurchaseItem(id,  quantity);
		return list;
    	
    }
    
    @ResponseBody
   	@RequestMapping("/getEntity")
   	public List<Map<String,Object>> getEntity() {
    	return service.getEntity();
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