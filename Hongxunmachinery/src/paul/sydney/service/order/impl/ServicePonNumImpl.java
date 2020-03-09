package paul.sydney.service.order.impl;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import paul.sydney.dao.IOrderNumDao;
import paul.sydney.dao.IPonNumDao;
import paul.sydney.dao.StockDao;
import paul.sydney.model.HongXunMaterialOutStoreItem;
import paul.sydney.model.HongXunMaterialOutStoreNum;
import paul.sydney.model.HongXunMaterialStock;
import paul.sydney.model.HongXunPonNum;
import paul.sydney.model.HongXunPoN;
import paul.sydney.model.HongXunProductionStock;
import paul.sydney.model.HongXunProductionWeldStock;
import paul.sydney.model.HongXunWeiwaiItem;
import paul.sydney.model.HongXunWeiwaiNum;
import paul.sydney.service.order.ServicePonNumInf;
@Transactional
@Service("ponNum")
public class ServicePonNumImpl implements ServicePonNumInf {

	@Autowired
	StockDao stockDao;

	public void setStockDao(StockDao stockDao) {
		this.stockDao = stockDao;
	}
/*	@Autowired
	IOrderNumDao iOrderNumDao;
	public void setStockDao(IOrderNumDao iOrderNumDao) {
		this.iOrderNumDao = iOrderNumDao;
	}*/
	@Autowired
	IPonNumDao iPonNumDao;
	public void setStockDao(IPonNumDao iPonNumDao) {
		this.iPonNumDao = iPonNumDao;
	}
	
	@Override
	public List<Map<String, Object>> loadData() {
		// TODO Auto-generated method stub
		//System.out.println("serviceTenload");
		List<HongXunPonNum> hongXunPonNums = iPonNumDao.quary(new HongXunPonNum());
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (HongXunPonNum item : hongXunPonNums) {
			Map<String, Object> map = new HashMap<String, Object>();
			mapHongXunPonNum(map, item);	
			list.add( 0, map);
		}
		return list;
	}
	
	private void mapHongXunPonNum(Map<String, Object> map, HongXunPonNum item) {
		// TODO Auto-generated method stub
		map.put("idc", item.getIdc());
		map.put("totalAmount", item.getTotalAmount());
		if(item.getPoCreateDate() != null){
			map.put("poCreateDate", new SimpleDateFormat("yyyy-MM-dd").format(item.getPoCreateDate()));
		}
		map.put("pon", item.getPon());
		map.put("quantity", item.getQuantity());
		map.put("status", item.getStatus());
		map.put("supplier", item.getSupplier());		
	}
/*

	@Override
	public List<Map<String, Object>> changeStatus(int id) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		@SuppressWarnings("unchecked")
		List<HongXunPoN>  hongXunPoNs =  (List<HongXunPoN>) stockDao.quarywithpara("HongXunPoN","orderNumID",id);
		boolean flag = false; boolean flag1 = false; boolean flag2 = false;String status="";
		boolean flag_ = false; boolean flag1_ = false; boolean flag2_ = false; String status_="";BigDecimal sum = null;
		//Map<String,Object> map = new HashMap<String,Object>();
		for(HongXunPoN item: hongXunPoNs){
			if(flag2==false){
				if(item.getStockInQuantity()!=null){
					//System.out.println(item.getStoreOutPlanQuantity()+"::"+item.getStoreOutRealQuantity());
					if(item.getQuantity()>item.getStockInQuantity()){												    		
			    		status = "部分入库";
			    		flag2=true;   		
			    	}else if(item.getQuantity() == item.getStockInQuantity()){
			    		//System.out.println("asf8888");
			    		if(flag1 == true){		    			
			    			status = "部分入库";
			    			flag2=true;   
			    		}else{
			    			//System.out.println("部分入库2");
			    			flag = true;	    			
			    		}
			    	}
		   		}else{
		   			//System.out.println("部分入库3");
			    	if(flag == true){		    		
			    		status = "部分入库";
			    		flag2=true;   
			    	}else{
			    		flag1 = true;
			    	}
			    } 
			}
			if(flag2_==false){
				if(item.getStockOutQuantity()!=null){
					//System.out.println(item.getStoreOutPlanQuantity()+"::"+item.getStoreOutRealQuantity());
					if(item.getQuantity()>item.getStockOutQuantity()){												    		
			    		status_ = "部分出库";
			    		flag2_=true;   		
			    	}else if(item.getQuantity() == item.getStockOutQuantity()){
			    		//System.out.println("asf8888");
			    		if(flag1_ == true){		    			
			    			status_ = "部分出库";
			    			flag2_=true;   
			    		}else{
			    			//System.out.println("部分入库2");
			    			flag_ = true;	    			
			    		}
			    	}
		   		}else{
		   			//System.out.println("部分入库3");
			    	if(flag_ == true){		    		
			    		status_ = "部分出库";
			    		flag2_ = true;   
			    	}else{
			    		flag1_ = true;
			    	}
			    } 
			}
			if(item.getTotalAmount() != null){
    			if(sum==null){
    				sum = item.getTotalAmount();
    			}else{
    				sum = sum.add(item.getTotalAmount());
    				//System.out.println(sum.toString());
    			}
    		}
		}

		HongXunPonNum hongXunOrderNum = stockDao.orderNumFindById(id);
		Map<String,Object> map = new HashMap<String,Object>();
		if(status.equals("")){	
			if(flag==false){					
				status = hongXunOrderNum.getStatus();			
			}else{				
				status = "入库完成";						
			}			
		}
		if(status_.equals("")){	
			if(flag_==false){					
				status_ = hongXunOrderNum.getStatus();			
			}else{				
				status_ = "出库完成";						
			}			
		}
		if((status.indexOf("入库")>-1) && (status_.indexOf("出库")>-1)){
			status = status+","+status_;
		//}else if(status.indexOf("入库")>-1) {	
		}else if(status_.indexOf("出库")>-1) {
			status = status_;
		}
		
		map.put("status", status);
		hongXunOrderNum.setStatus(status);
		
		if(sum != null){	
			hongXunOrderNum.setOrderAmount(sum);	
			map.put("orderAmount", sum);
		}else{
			map.put("orderAmount", "");
		}
		list.add(map);
		
		stockDao.update(hongXunOrderNum);
		
		return list;
	}
			
	
	
	@Override
	public List<Map<String, Object>> saveRow(HongXunPonNum hongXunOrderNum) {
		// TODO Auto-generated method stub
		// System.out.println("serviceoneloadSaveRow");
		stockDao.save(hongXunOrderNum);
		Map<String, Object> map = new HashMap<String, Object>();
		//map.put("idc", hongXunOrderNum.getIdc());
		mapHongXunPonNum(map, hongXunOrderNum);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list.add(map);
		return list;
	}

	@Override
	public List<Map<String, Object>> updateRow(HongXunPonNum hongXunOrderNum) {
		// TODO Auto-generated method stub
		System.out.println("HongXunPonNum updateRow");
		stockDao.update(hongXunOrderNum);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		mapHongXunPonNum(map, hongXunOrderNum);
		list.add(map);
		return list;
	}


	@Override
	public List<Map<String, Object>> getEntity() {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String,Object>();
		HongXunPonNum e = new HongXunPonNum();
		Class<? extends HongXunPonNum> cls = e.getClass();  
        Field[] fields = cls.getDeclaredFields();  
        for(int i=0; i<fields.length; i++){  
            Field f = fields[i];  
            f.setAccessible(true); 
            map.put(f.getName(), "");
           
        } 
        list.add(map);
        return list;
	}

	*/
/*	@Override
	public List<Map<String, Object>> betweenDateFind(String date1, String date2) throws ParseException{
		// TODO Auto-generated method stub
		Date beginDate;
    	Date endDate;
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    	if(df.parse(date1).before(df.parse(date2))){
    		//System.out.println("0");
        	beginDate= df.parse(date1);
        	endDate = df.parse(date2);
    	}else{
    		//System.out.println("1");
        	beginDate= df.parse(date2); 
        	endDate = df.parse(date1);
    	}
    	 Calendar c = Calendar.getInstance();
         c.setTime(endDate);
         c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
         endDate = c.getTime();
    	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    	List<HongXunPonNum> hongXunOrderNums = stockDao.quary(new HongXunPonNum());
    	for(HongXunPonNum item: hongXunOrderNums){
    		//System.out.println("date: " + item.getPoCreateDate());
			if(beginDate.getTime()<= item.getPoCreateDate().getTime() && endDate.getTime() >= item.getPoCreateDate().getTime()){
				//System.out.println("date: ");
       			Map<String,Object> map = new HashMap<String,Object>();
       			mapHongXunOrderNum(map, item);
       	   		list.add(map);	
			}
    	}
    	return list;
	}

	@Override
	public List<Map<String, Object>> autotimp(String str) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		List<String> listName = new ArrayList<String>();
		@SuppressWarnings("unchecked")
		List<HongXunPonNum> hongXunOrderNums = (List<HongXunPonNum>) stockDao.quaryFuzzyWithpara("HongXunPonNum", "orderNum", str); 	
		for(HongXunPonNum item : hongXunOrderNums){
			if(!listName.contains(item.getOrderNum())){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("id", item.getIdc());
				map.put("name", item.getOrderNum());
				listName.add(item.getOrderNum());
				list.add(map);
			}
		};
		return list;
	}

	@Override
	public List<Map<String, Object>> searchNum(String orderNum) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<HongXunPonNum> hongXunOrderNums = (List<HongXunPonNum>) stockDao.quarywithpara("HongXunPonNum", "orderNum", orderNum);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			for (HongXunPonNum item : hongXunOrderNums) {
				Map<String, Object> map = new HashMap<String, Object>();
				mapHongXunOrderNum(map, item);
				list.add(map);
			}
		return list;
	}*/



}


	

