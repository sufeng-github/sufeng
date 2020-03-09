package paul.sydney.controller.work;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import paul.sydney.model.HongXunWorkNum;
import paul.sydney.service.work.ServiceWorkNumInf;
import paul.sydney.model.HongXunBomTree;


@Controller 
@RequestMapping("/workNum.htm") 
public class ControllerWorkNum {
    private final ServiceWorkNumInf service;
	//private BeansInf beans;
    @Autowired 
    public ControllerWorkNum(ServiceWorkNumInf service){
        this.service = service; 
       // this.beans = beans; 
    }
	
    @ResponseBody
   	@RequestMapping("/loadData")
   	private List<Map<String,Object>> loadData() {
    	List<Map<String,Object>> list = service.loadData();
    	//System.out.println("loadDataThree");
   		return list;
   	}  
    

    
    @ResponseBody
   	@RequestMapping("/saveRow")
    private List<Map<String,Object>> saveRow(@RequestBody List<HongXunWorkNum> hongXunWorkNumList) throws ParseException{
    	//System.out.println("saveRowNumThree");
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date workStartDate = simpleDateFormat.parse(hongXunWorkNumList.get(1).getWorkNum());
    	Date workEndDate = simpleDateFormat.parse(hongXunWorkNumList.get(1).getOrderNum());
    	hongXunWorkNumList.get(0).setWorkStartDate(workStartDate);
    	hongXunWorkNumList.get(0).setWorkEndDate(workEndDate);
    	if(hongXunWorkNumList.get(0).getIdc()==0){
        	Date date = new Date();
			long workNum = date .getTime();
			hongXunWorkNumList.get(0).setWorkNum(String.valueOf(workNum));
    		return service.saveRow(hongXunWorkNumList.get(0));
    	}else{	
    		return service.updateRow(hongXunWorkNumList.get(0));
    	}
   	}

    

	@ResponseBody
   	@RequestMapping("/deleteRow")
   	private Boolean deleteRow(		@RequestParam(value = "idc", required = true) int ID){
   		return service.deleteRow(ID);
   	}  
    
    @ResponseBody
   	@RequestMapping("/betweenDateFind")
   	private List<Map<String,Object>> betweenDateFind(	@RequestParam(value = "beginDate", required = true) String date1,
													@RequestParam(value = "endDate", required = true) String date2) throws ParseException{
    	List<Map<String,Object>> list = service.betweenDateFind(date1, date2);
   		return list;
   	} 
    
    @ResponseBody
   	@RequestMapping("/searchWorkNum")
    private List<Map<String,Object>> searchNum(@RequestParam(value = "name", required = true) String workNum){
    	List<Map<String,Object>> list = service.searchWorkNum(workNum);
		return list;
    }
    
    @ResponseBody
   	@RequestMapping("/autotimp")
   	public List<Map<String,Object>> autotimp(HttpServletRequest request) {
    	String q=request.getParameter("q");
    	//System.out.println("autotimp:" + q); 	
   		return service.autotimp(q);  
   	} 
    
/*    @ResponseBody
   	@RequestMapping("/searchNum")
    private List<Map<String,Object>> searchNum(@RequestParam(value = "num", required = true) String num){
    	//HongXunDataSeven hongXunDataSeven = new HongXunDataSeven();
    	//hongXunDataSeven.setOutsourcingNum(num);
    	List<Map<String,Object>> list = service.searchNum("HongXunDataThree", "stockoutNum", num);
		return list;
    	
    }*/
    
    @ResponseBody
   	@RequestMapping("/getNumthreeFathers")
    private List<Map<String,Object>> getNumthreeFathers(@RequestBody List<HongXunWorkNum> hongXunWorkNumList){
    	//HongXunDataSeven hongXunDataSeven = new HongXunDataSeven();
    	//hongXunDataSeven.setOutsourcingNum(num);
    	//System.out.println("getNumthreeFathers");
		return service.getNumthreeFathers(hongXunWorkNumList.get(0));	
    }    
    
    
    @ResponseBody
   	@RequestMapping("/newWorkNum")
    
   	private List<Map<String,Object>> newWorkNum(@RequestBody List<HongXunBomTree> hongXunBomTreeList) throws Exception{
    	//System.out.println("newWorkNum: ");
    	//SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	HongXunWorkNum hongXunWorkNum = new HongXunWorkNum();
    	hongXunWorkNum.setWorkStartDate(new Date());
    	hongXunWorkNum.setWorkEndDate(new Date());
    	//long workNum = new Date().getTime();
    	//hongXunWorkNum.setWorkNum(String.valueOf(workNum));
    	hongXunWorkNum.setWorkNum(hongXunBomTreeList.get(0).getStatus());
    	hongXunWorkNum.setWorkQuantity(hongXunBomTreeList.size()-1);
    	hongXunWorkNum.setState("新增工单");
    	hongXunWorkNum.setWorkProcedure(hongXunBomTreeList.get(0).getRemark());	
   		List<Map<String, Object>> list = service.saveRow(hongXunWorkNum);
   		//HongXunBomTree A = hongXunBomTreeList.remove(0);
   		service.saveWorkItem(hongXunBomTreeList.subList(1, hongXunBomTreeList.size()), hongXunWorkNum);
   		return list;		
   	}   
   
    private boolean isDate(String date)
    {
      /**
       * �ж����ڸ�ʽ�ͷ�Χ
       */
      String rexp = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
      Pattern pat = Pattern.compile(rexp);
      Matcher mat = pat.matcher(date);
      boolean dateType = mat.matches();
      return dateType;
    }
    
/*
    private HongXunDataThree hongXunDataThreeFillItem(String orderNum,String componentNum,String componentName,String workStartDate,String workEndDate,
    												String stockoutNum,  String workNum,  String lineNum,  String productQuantity,  String stockoutDate,  
    												String getMaterialStaff,  String stockMenager,  String stockoutState, String stockoutRemark) throws ParseException{
    	System.out.println("hongXunDataThreeFillItem:");
    	HongXunDataThree hongXunWorkNum = new HongXunDataThree();
    	hongXunWorkNum.setOrderNum(orderNum);
    	hongXunWorkNum.setComponentNum(componentNum);
    	hongXunWorkNum.setComponentName(componentName);
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date date = simpleDateFormat.parse(workStartDate);
    	hongXunWorkNum.setWorkStartDate(date);
    	date = simpleDateFormat.parse(workEndDate);
    	hongXunWorkNum.setWorkEndDate(date);
    	//hongXunWorkNum.setStockoutNum(stockoutNum);
    	hongXunWorkNum.setWorkNum(workNum);

    	return hongXunWorkNum;
    }*/
}