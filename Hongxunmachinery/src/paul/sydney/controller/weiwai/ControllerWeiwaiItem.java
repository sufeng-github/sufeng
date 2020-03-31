package paul.sydney.controller.weiwai;

import java.io.IOException;
import java.io.InvalidObjectException;
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
import paul.sydney.model.HongXunWeiwaiItem;
import paul.sydney.model.HongXunWeiwaiNum;
import paul.sydney.service.weiwai.ServiceWeiwaiItemInf;




@Transactional
@Controller 
@RequestMapping("/weiwaiItem.htm") 
public class ControllerWeiwaiItem {
    private final ServiceWeiwaiItemInf service;
	

    @Autowired 
    public ControllerWeiwaiItem(ServiceWeiwaiItemInf service){
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
    	//System.out.println("para: " + para);
    	List<Map<String,Object>> list = service.loadData();
    	//System.out.println("branchOneloadData");
   		return list;
   	} 
/*/
    
    @ResponseBody
   	@RequestMapping("/saveMoney")
    private List<Map<String,Object>> saveMoney(@RequestBody List<HongXunWeiwaiItem> hongXunWeiwaiItemList){ 
    	//System.out.println("asdfasf");
    	int mainID = hongXunWeiwaiItemList.get(0).getWeiwaiNumID();
    	BigDecimal value = hongXunWeiwaiItemList.get(0).getWeiwaiMoney();
    	return service.saveMoney(mainID, value);
    }
    


    @ResponseBody
   	@RequestMapping("/getWeiwaiChildrens")
    private List<Map<String,Object>> getPurchaseChildrens(@RequestBody List<HongXunWeiwaiNum> hongXunDataFourList){
    	
    	int weiwaiNumID = (hongXunDataFourList.get(0).getIdc());
    	//System.out.println("weiwaiNumID :" + weiwaiNumID);
    	return service.getWeiwaiChildrens(weiwaiNumID);
    }
    
    @ResponseBody
   	@RequestMapping("/saveRow")
    private List<Map<String,Object>> saveRow(@RequestBody List<HongXunWeiwaiItem> hongXunWeiwaiItemList) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{    	
    	// TODO Auto-generated catch block
    	if(hongXunWeiwaiItemList.get(0).getPlanQuantity()!=null && hongXunWeiwaiItemList.get(0).getPrice() != null){
        	float value =hongXunWeiwaiItemList.get(0).getPrice().floatValue() * hongXunWeiwaiItemList.get(0).getPlanQuantity();
        	hongXunWeiwaiItemList.get(0).setWeiwaiMoney(new BigDecimal(value));
    	}
    	if(hongXunWeiwaiItemList.get(0).getIdc()==0){
    		return service.saveRow(hongXunWeiwaiItemList.get(0));
    	}else{	
    		return service.updateRow(hongXunWeiwaiItemList.get(0));
    	}
   	}
    
      
    @ResponseBody
   	@RequestMapping("/deleteRow")
    private List<Map<String,Object>> deleteRow(@RequestBody List<HongXunWeiwaiItem> hongXunWeiwaiItemList){ 
    	int ID = hongXunWeiwaiItemList.get(0).getIdc();
    	service.deleteRow(ID);
    	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
   		return list;
   	} 

    @ResponseBody
   	@RequestMapping("/searchWeiwaiItem")
    private List<Map<String,Object>> searchNum(@RequestParam(value = "name", required = true) String weiwaiItem){
    	//System.out.println(weiwaiItem);
    	List<Map<String,Object>> list = service.searchNum(weiwaiItem);
		return list;
    	
    }
    
    @ResponseBody
   	@RequestMapping("/autotimp")
   	public List<Map<String,Object>> autotimp(HttpServletRequest request) {
    	String q=request.getParameter("q");
    	//System.out.println("autotimp:" + q); 	
   		return service.autotimp(q);  
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