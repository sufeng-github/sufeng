package paul.sydney.controller.production;


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
import paul.sydney.commen.result.PageResults;
import paul.sydney.model.HongXunDeliveryNum;
import paul.sydney.service.production.ServiceDeliveryNumInf;

@Controller 
@RequestMapping("/deliveryNum.htm")
public class ControllerDeliveryNum {
    private final ServiceDeliveryNumInf service;
    @Autowired 
    public ControllerDeliveryNum(ServiceDeliveryNumInf service){
        this.service = service; 
    }
    
/*  
    @ResponseBody
   	@RequestMapping("/getData")
    private List<Map<String,Object>> loadData(){    	
    	// TODO Auto-generated catch block
    	return service.loadData();
    }*/	   
    @ResponseBody
   	@RequestMapping("/getData")
    private PageResults<HongXunDeliveryNum> getData(/* @RequestBody HongXunMaterialStock hongXunMaterialStock,*/
									    		@RequestParam(value="deliveryNum",required=false) String deliveryNum,									    		
									    		@RequestParam(value="name",required=false) String name,
									    		@RequestParam(value="startTime",required=false) String startTime,
									    		@RequestParam(value="endTime",required=false) String endTime,
    											@RequestParam(value="page", defaultValue="1") Integer pageNo,
			  									@RequestParam(value="rows", defaultValue="10") Integer pageSize){    	
    	// TODO Auto-generated catch block  	
    	HongXunDeliveryNum hongXunDeliveryNum = new HongXunDeliveryNum();  
    	SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
    	if(deliveryNum != null && !deliveryNum.equals("")){
    		hongXunDeliveryNum.setDeliveryNum(deliveryNum);
    	}   	
    	if(startTime != null && !startTime.equals("")){    		
    		try {
    			hongXunDeliveryNum.setStartTime(sdf.parse(startTime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
    	}
    	if(endTime != null && !endTime.equals("")){
    		try {
    			hongXunDeliveryNum.setEndTime(sdf.parse(endTime));
			} catch (ParseException e) {			
				e.printStackTrace();
			}
    	}
    	if(name !=null && !name.equals("")){
    		hongXunDeliveryNum.setName(name);
    	}
    	return service.getData(hongXunDeliveryNum ,pageNo,pageSize);
    }	
    
    
    @ResponseBody
   	@RequestMapping("/autotimp")
   	public List<Map<String,Object>> autotimp(HttpServletRequest request) {
    	String q=request.getParameter("q");
    	//System.out.println("autotimp:" + q); 	
   		return service.autotimp(q);  
   	} 
    
    @ResponseBody
   	@RequestMapping("/getEntity")
   	public List<Map<String,Object>> getEntity() {
    	return service.getEntity();
   	} 
    
    
    @ResponseBody
   	@RequestMapping("/deleteRow")
    public List<Map<String,Object>> deleteRow(@RequestBody List<HongXunDeliveryNum> hongXunDeliveryNumList){	
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
     	for(HongXunDeliveryNum item : hongXunDeliveryNumList){ 		
 			if(item.getIdc()!=0){
 				service.deleteRow(item.getIdc());
     		}
     	}
     	return list;
    }
    
    @ResponseBody
   	@RequestMapping("/saveRow")
    private List<Map<String,Object>> saveRow(@RequestBody List<HongXunDeliveryNum> hongXunDeliveryNumList){   	
    	//System.out.println("HongXunProduction saveRow");
	    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	for(HongXunDeliveryNum item : hongXunDeliveryNumList){ 		
			if(item.getIdc()==0){
				service.saveRow(item);	
    		}else{   			
    			service.updateRow(item);
    		}	
    	}
    	return list;
   	}
    
}