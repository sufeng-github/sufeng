package paul.sydney.service.weiwai.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import paul.sydney.dao.IWeiwaiDao;
import paul.sydney.dao.StockDao;
import paul.sydney.model.HongXunWeiwaiItem;
import paul.sydney.model.HongXunWeiwaiNum;
import paul.sydney.model.HongXunWorkItem;
import paul.sydney.service.weiwai.ServiceWeiwaiNumInf;

@Transactional
@Service("serviceNumFour")
public class ServiceWeiwaiNumImpl implements ServiceWeiwaiNumInf{
	
	@Autowired
	StockDao stockDao;
	public void setStockDao(StockDao stockDao) {
		this.stockDao = stockDao;
	}
	@Autowired
	IWeiwaiDao iWeiwaiDao;
	public void setStockDao(IWeiwaiDao iWeiwaiDao) {
		this.iWeiwaiDao = iWeiwaiDao;
	}
	
	@Override
	public List<Map<String, Object>> loadData() {
		// TODO Auto-generated method stub
		//System.out.println("serviceoneload");
		List<HongXunWeiwaiNum> hongXunWeiwaiNums = iWeiwaiDao.quary(new HongXunWeiwaiNum());
   		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
   		for(HongXunWeiwaiNum item : hongXunWeiwaiNums){
  	   		
   			Map<String,Object> map = new HashMap<String,Object>();
   	   		mapHongXunWeiwaiNum(map, item);
	   	   	list.add(map);	   				
   		}
   		return list;
	}
	
	@Override
	public List<Map<String, Object>> saveRow(HongXunWeiwaiNum hongXunWeiwaiNum) {
		// TODO Auto-generated method stub
		System.out.println("SaveRow");
		iWeiwaiDao.save(hongXunWeiwaiNum);
		Map<String,Object> map = new HashMap<String,Object>();
		mapHongXunWeiwaiNum(map, hongXunWeiwaiNum);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.add(map);
		return list;
		
	}
	
	@Override
	public List<Map<String, Object>> updateRow(HongXunWeiwaiNum hongXunWeiwaiNum) {
		// TODO Auto-generated method stub
		iWeiwaiDao.update(hongXunWeiwaiNum);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		return list;
	}
	
	@Override
	public Boolean deleteRow(int ID) {
		// TODO Auto-generated method stub
		HongXunWeiwaiNum hongXunWeiwaiNum = iWeiwaiDao.weiwaiNumFindById(ID);
		@SuppressWarnings("unchecked")
		List<HongXunWeiwaiItem> hongXunWeiwaiItems = (List<HongXunWeiwaiItem>) stockDao.quarywithpara("HongXunWeiwaiItem", "weiwaiNumID", hongXunWeiwaiNum.getIdc()); 
/*		for(HongXunWeiwaiItem item : hongXunWeiwaiItems){
			stockDao.deletRow(item);
		}*/
		iWeiwaiDao.deleteWeiwaiItemList(hongXunWeiwaiItems);
		iWeiwaiDao.delete(hongXunWeiwaiNum);
		return true;		
	}


	@Override
	public List<Map<String, Object>> searchWeiwaikNum(String weiwaiNum) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<HongXunWeiwaiNum> hongXunWeiwaiNums = (List<HongXunWeiwaiNum>) stockDao.quarywithpara("HongXunWeiwaiNum", "weiwaiNum", weiwaiNum);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (hongXunWeiwaiNums != null) {
			for (HongXunWeiwaiNum item : hongXunWeiwaiNums) {
				Map<String, Object> map = new HashMap<String, Object>();
				mapHongXunWeiwaiNum(map, item);
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
		List<HongXunWeiwaiNum> hongXunWeiwaiNums = (List<HongXunWeiwaiNum>) stockDao.quaryFuzzyWithpara("HongXunWeiwaiNum", "weiwaiNum", str); 		
		for(HongXunWeiwaiNum item : hongXunWeiwaiNums){
			if(!listName.contains(item.getWeiwaiNum())){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("id", item.getIdc());
				map.put("name", item.getWeiwaiNum());
				listName.add(item.getWeiwaiNum());
				list.add(map);
			}
		};
		return list;
	}
	/*	
	
	@Override
	public List<Map<String, Object>> betweenDateFind(Date date1, Date date2) throws ParseException {
		// TODO Auto-generated method stub
		Date beginDate;
    	Date endDate;
    	//DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    	if(date1.before(date2)){
    		//System.out.println("0");
        	beginDate= date1;
        	endDate = date2;
    	}else{
    		//System.out.println("1");
        	beginDate= date2; 
        	endDate = date1;
    	}
    	 Calendar c = Calendar.getInstance();
         c.setTime(endDate);
         c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
         endDate = c.getTime();
    	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    	List<HongXunDataFour> hongXunWeiwaiNums = stockDao.quary(new HongXunDataFour());
    	for(HongXunDataFour item: hongXunWeiwaiNums){
			if(beginDate.before(item.getPurchaseDate()) && endDate.after(item.getPurchaseDate())){
				System.out.println("ID: " + item.getIdc());
       			Map<String,Object> map = new HashMap<String,Object>();
       			mapHongXunWeiwaiNum(map, item);
       	   		list.add(map);	
			}
    	}
    	return list;
	}

	@Override
	public List<Map<String, Object>> searchNum(HongXunDataFour hongXunWeiwaiNum) {
		// TODO Auto-generated method stub
		List<HongXunDataFour> hongXunWeiwaiNums = stockDao.quarywithparaOne("purchaseOrderNum", hongXunWeiwaiNum.getPurchaseOrderNum());
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		if(hongXunWeiwaiNums != null){
			for(HongXunDataFour item: hongXunWeiwaiNums){
				Map<String,Object> map = new HashMap<String,Object>();
       			mapHongXunWeiwaiNum(map, item);
       			list.add(map);	
			}
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> refresh(List<HongXunDataFour> hongXunWeiwaiNumList) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    	for(int i=0; i<hongXunWeiwaiNumList.size(); i++){
    		int ID = Integer.valueOf(hongXunWeiwaiNumList.get(i).getPurchaseOrderNum());
    		Map<String,Object> map = new HashMap<String,Object>();
    		mapHongXunWeiwaiNum(map, stockDao.dataOneFindById(ID));
    		list.add(map);	
    	}
		return list;
	}
*/
	private void mapHongXunWeiwaiNum(Map<String, Object> map, HongXunWeiwaiNum item) {
		// TODO Auto-generated method stub			
		map.put("idc", item.getIdc());
		map.put("weiwaiNum", item.getWeiwaiNum());
		map.put("workNum", item.getWorkNum());
		map.put("weiwaiProcedure", item.getWeiwaiProcedure());
		if(item.getWeiwaiDate() != null){
			map.put("weiwaiDate", new SimpleDateFormat("yyyy-MM-dd").format(item.getWeiwaiDate()));
		}
		//map.put("weiwaiDeliveryDate", item.getWeiwaiDeliveryDate());
		if(item.getWeiwaiDeliveryDate() != null){
			map.put("weiwaiDeliveryDate", new SimpleDateFormat("yyyy-MM-dd").format(item.getWeiwaiDeliveryDate()));
		}
		map.put("supplier",item.getSupplier());
		map.put("state", item.getState());
		map.put("weiwaiStaff", item.getWeiwaiStaff());	
		map.put("weiwaiMoney", item.getWeiwaiMoney());
		map.put("remark", item.getRemark());	
		map.put("weiwaiQuantity", item.getWeiwaiQuantity());
		map.put("remark", item.getRemark());
		map.put("weiwaiStaff", item.getWeiwaiStaff());
	}

	@Override
	public void save(HongXunWeiwaiItem hongXunDataFive) {
		// TODO Auto-generated method stub
		iWeiwaiDao.save(hongXunDataFive);
	}

	@Override
	public List<Map<String, Object>> betweenDateFind(String date1, String date2) throws ParseException {
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
    	List<HongXunWeiwaiNum> hongXunWeiwaiNums = iWeiwaiDao.quary(new HongXunWeiwaiNum());
    	for(HongXunWeiwaiNum item: hongXunWeiwaiNums){
    		//System.out.println("date: " + item.getPoCreateDate());
			if(beginDate.getTime()<= item.getWeiwaiDate().getTime() && endDate.getTime() >= item.getWeiwaiDate().getTime()){
				//System.out.println("date: ");
       			Map<String,Object> map = new HashMap<String,Object>();
       			mapHongXunWeiwaiNum(map, item);
       	   		list.add(map);	
			}
    	}
    	return list;
	}


}
