package paul.sydney.controller.order;


import java.text.ParseException;
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
import paul.sydney.model.HongXunPoN;
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
    @RequestMapping(value="/exlImport",method=RequestMethod.POST)
    public List<Map<String,Object>> exlImport(HttpServletRequest request) throws Exception {
    	System.out.println("exlImport");
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(request.getSession().getServletContext());
        //检查form中是否有enctype="multipart/form-data"
        if(multipartResolver.isMultipart(request)){
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
           //获取multiRequest 中所有的文件名
            Iterator<String> iter=multiRequest.getFileNames();        
            while(iter.hasNext()){
                MultipartFile file=multiRequest.getFile(iter.next());
                if(file!=null){
                	InputStream is = file.getInputStream();           	
                	list = service.orderNumImport(is);
                }                
            }
            return list;
        }else{
        	return list;
        } 
    }*/
    
    @ResponseBody
   	@RequestMapping("/loadData")
   	private List<Map<String,Object>> loadData() {
    	List<Map<String,Object>> list = service.loadData();
    	//System.out.println("tenloadData");
   		return list;
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
  /*  @ResponseBody
   	@RequestMapping("/saveRows")
   	private List<Map<String,Object>> saveRows(@RequestBody List<HongXunPoN> hongXunPoNList) throws ParseException {
    	System.out.println("saveRows: ");
    	List<Map<String, Object>> list = null;
    	for(HongXunPoN item : hongXunPoNList){ 		
			
				if(list != null){
					list.containsAll(service.saveRow(item));
				}else{
					list = service.saveRow(item);
				}
    		
    	}
    	return list;
   	}*/
/*    
    @ResponseBody
   	@RequestMapping("/getOrderItems")
   	private List<Map<String,Object>> getOrderItems(@RequestBody List<HongXunOrderNum> hongXunOrderNumList) {	
   		System.out.println("getOrderItems");
   		return service.getOrderItems(hongXunOrderNumList.get(0).getIdc());
   	} */
    
    @ResponseBody
   	@RequestMapping("/deleteRow")
   	//private List<Map<String,Object>> deleteRow(@RequestBody List<HongXunPoN> hongXunPoNList) throws ParseException {
    private List<Map<String,Object>> deleteRow(@RequestParam(value = "idc", required = true) int id){
   		//System.out.println("deleteRow");
   		//List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
   		//for(HongXunPoN item: hongXunPoNList){
   			//if(item.getIdc()!=0){
   				//list = 
   			//}
   		//}
   		return service.deleteRow(id);
   	} 
    
    @ResponseBody
   	@RequestMapping("/decrease")
   	public List<Map<String,Object>> decrease(@RequestParam(value = "orderNumID", required = true) int id){
    		//System.out.println("changeStatus");
    	return service.decrease(id);
   	} 
    
    @ResponseBody
   	@RequestMapping("/betweenDateFind")
   	private List<Map<String,Object>> betweenDateFind(	@RequestParam(value = "beginDate", required = true) String date1,
													@RequestParam(value = "endDate", required = true) String date2) throws ParseException{
    	//System.out.println("beginDate:" + date1.toString() + " endDate:" + date2.toString());	
    	List<Map<String,Object>> list = service.betweenDateFind(date1, date2);
   		return list;
   	} 
    
    
    @ResponseBody
   	@RequestMapping("/searchMaterialNum")
    private List<Map<String,Object>> searchNum(@RequestParam(value = "name", required = true) String materialNum,
    											@RequestParam(value = "orderNumID", required = true) String orderNumID){
    	List<Map<String,Object>> list = service.searchNum(Integer.valueOf(orderNumID), materialNum);
		return list; 	
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
    	
    	//System.out.println("autotimp:" + orderNum); 	
   		return service.autotimp(Integer.valueOf(orderNumID), q);  
   	} 
    
/*    private boolean isDate(String date)
    {
      *//**
       * �ж����ڸ�ʽ�ͷ�Χ
       *//*
      String rexp = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
      Pattern pat = Pattern.compile(rexp);
      Matcher mat = pat.matcher(date);
      boolean dateType = mat.matches();
      return dateType;
    }*/

    
}