package paul.sydney.controller.weiwai;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
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

import paul.sydney.model.HongXunBomTree;
import paul.sydney.model.HongXunWeiwaiItem;
import paul.sydney.model.HongXunWeiwaiNum;
import paul.sydney.service.weiwai.ServiceWeiwaiNumInf;


@Transactional
@Controller 
@RequestMapping("/weiwaiNum.htm") 
public class ControllerWeiwaiNum {
    private final ServiceWeiwaiNumInf service;

    @Autowired 
    public ControllerWeiwaiNum(ServiceWeiwaiNumInf service){
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
/*      
    @ResponseBody
   	@RequestMapping("/refresh")
    private List<Map<String,Object>> refresh(@RequestBody List<hongXunWeiwaiNum> hongXunWeiwaiNumList) throws ParseException{
    		System.out.println("refresh");
    	return service.refresh(hongXunWeiwaiNumList);
    }
    */
    
    @ResponseBody
   	@RequestMapping("/saveRow")
    private List<Map<String,Object>> saveRow(@RequestBody List<HongXunWeiwaiNum> hongXunWeiwaiNumList) throws ParseException{
    	//System.out.println("saveRowFourNum");
    	Date date = new Date();
    	hongXunWeiwaiNumList.get(0).setWeiwaiDate(date);
    	//hongXunWeiwaiNum hongXunWeiwaiNum = new hongXunWeiwaiNum();
    	//beans.mergeObject(hongXunWeiwaiNumList.get(0), hongXunWeiwaiNum);
    	if(hongXunWeiwaiNumList.get(0).getIdc()==0){
    		long weiwaiNum = date.getTime();
    		hongXunWeiwaiNumList.get(0).setWeiwaiNum(String.valueOf(weiwaiNum));
    		return service.saveRow(hongXunWeiwaiNumList.get(0));
    	}else{	
    		return service.updateRow(hongXunWeiwaiNumList.get(0));
    	}
   
   	}
    
    @ResponseBody
   	@RequestMapping("/deleteRow")
    private Boolean deleteRow(@RequestBody List<HongXunWeiwaiNum> hongXunWeiwaiNumList) throws ParseException{
   		int ID = hongXunWeiwaiNumList.get(0).getIdc();
   		return service.deleteRow(ID);
   	} 
      
    @ResponseBody
   	@RequestMapping("/newWeiwaiNum")
   	private List<Map<String,Object>> newWorkNum(@RequestBody List<HongXunBomTree> hongXunBomTreeList){
    	//System.out.println("newWeiwaiNum");
    	//SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	HongXunWeiwaiNum hongXunWeiwaiNum = new HongXunWeiwaiNum();
    	hongXunWeiwaiNum.setWeiwaiDate(new Date());
    	hongXunWeiwaiNum.setWeiwaiDeliveryDate(new Date());
    	hongXunWeiwaiNum.setWeiwaiNum(hongXunBomTreeList.get(0).getStatus());
    	hongXunWeiwaiNum.setWeiwaiQuantity(hongXunBomTreeList.size()-1);
    	hongXunWeiwaiNum.setWeiwaiProcedure(hongXunBomTreeList.get(0).getRemark());
    	hongXunWeiwaiNum.setState("新增外协");
    	
    	hongXunBomTreeList = hongXunBomTreeList.subList(1, hongXunBomTreeList.size());
    	List<HongXunWeiwaiItem> hongXunWeiwaiItemList = new ArrayList<HongXunWeiwaiItem>();
    	for(HongXunBomTree item: hongXunBomTreeList){
    		HongXunWeiwaiItem hongXunWeiwaiItem = new HongXunWeiwaiItem();
    		hongXunWeiwaiItem.setMaterialNum(item.getBomMaterialNum());
    		hongXunWeiwaiItem.setSpecification(item.getBomSpacification());		
    		//hongXunWeiwaiItem.setPrice(item.getPrice());
    		//hongXunWeiwaiItem.setWeiwaiMoney(item.getTotalAmount());
    		hongXunWeiwaiItem.setPlanQuantity(item.getBomItemQuantity());
    		//hongXunWeiwaiItem.setUnit(item.getUnit());
    		//hongXunWeiwaiItem.setOrderNumID(item.getIdc());
    		//hongXunWeiwaiItem.setWeiwaiNumID(hongXunWeiwaiNum.getIdc());
    		hongXunWeiwaiItemList.add(hongXunWeiwaiItem);
    		//service.save(hongXunWeiwaiItem);
    	}
    	List<Map<String, Object>> list = service.saveRow(hongXunWeiwaiNum);
    	for(HongXunWeiwaiItem item: hongXunWeiwaiItemList){
    		item.setWeiwaiNumID(hongXunWeiwaiNum.getIdc());
    		service.save(item);
    	}
   		return list;
   	}
    
    @ResponseBody
   	@RequestMapping("/betweenDateFind")
    private List<Map<String,Object>> betweenDateFind(@RequestParam(value = "beginDate", required = true) String date1,@RequestParam(value = "endDate", required = true) String date2) throws ParseException{

    	List<Map<String,Object>> list = service.betweenDateFind(date1, date2);
   		return list;
   	} 
    
    @ResponseBody
   	@RequestMapping("/searchWorkNum")
    private List<Map<String,Object>> searchNum(@RequestParam(value = "name", required = true) String workNum){
    	List<Map<String,Object>> list = service.searchWeiwaikNum(workNum);
		return list;
    }
    
    @ResponseBody
   	@RequestMapping("/autotimp")
   	public List<Map<String,Object>> autotimp(HttpServletRequest request) {
    	String q=request.getParameter("q");
    	//System.out.println("autotimp:" + q); 	
   		return service.autotimp(q);  
   	} 
    
/*   
    @ResponseBody
   	@RequestMapping("/searchNum")
    private List<Map<String,Object>> searchNum(@RequestParam(value = "num", required = true) String num){
    	hongXunWeiwaiNum hongXunWeiwaiNum = new hongXunWeiwaiNum();
    	hongXunWeiwaiNum.setPurchaseOrderNum(num);
    	List<Map<String,Object>> list = service.searchNum(hongXunWeiwaiNum);
		return list;
    	
    }
*/

}