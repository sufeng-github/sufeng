package paul.sydney.service.work.impl;

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

import paul.sydney.dao.IWorkDao;
import paul.sydney.dao.StockDao;
import paul.sydney.model.HongXunBomTree;
import paul.sydney.model.HongXunPoN;
import paul.sydney.model.HongXunPurchaseNum;
import paul.sydney.model.HongXunWorkNum;
import paul.sydney.service.work.ServiceWorkNumInf;
import paul.sydney.model.HongXunWorkItem;


@Transactional
@Service("serviceNumThree")
public class ServiceWorkNumImpl implements ServiceWorkNumInf{
	
	@Autowired
	StockDao stockDao;
	public void setStockDao(StockDao stockDao) {
		this.stockDao = stockDao;
	}
	@Autowired
	IWorkDao iWorkDao;
	public void setStockDao(IWorkDao iWorkDao) {
		this.iWorkDao = iWorkDao;
	}
	@Override
	public List<Map<String, Object>> loadData() {
		// TODO Auto-generated method stub
		//System.out.println("serviceoneload");
		List<HongXunWorkNum> hongXunDataThrees = iWorkDao.quary(new HongXunWorkNum());	
		System.out.println("size: " + hongXunDataThrees.size());
   		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
   		for(HongXunWorkNum item : hongXunDataThrees){   	   		
   			Map<String,Object> map = new HashMap<String,Object>();
   	   		mapHongXunWorkNum(map, item);
	   	   	list.add(map);	   				
   		}
   		return list;
	}
	
	@Override
	public List<Map<String, Object>> saveRow(HongXunWorkNum hongXunWorkNum) {
		// TODO Auto-generated method stub
		//System.out.println("serviceoneloadSaveRow");
		iWorkDao.save(hongXunWorkNum);
		Map<String,Object> map = new HashMap<String,Object>();
		//map.put("idc", hongXunWorkNum.getIdc());
		mapHongXunWorkNum(map, hongXunWorkNum);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.add(map);
		return list;
	}
	

	@Override
	public List<Map<String, Object>> updateRow(HongXunWorkNum hongXunWorkNum) {
		// TODO Auto-generated method stub
		iWorkDao.update(hongXunWorkNum);
		Map<String,Object> map = new HashMap<String,Object>();
		mapHongXunWorkNum(map, hongXunWorkNum);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.add(map);
		return list;
	}
	
/*	@Override
	public List<Map<String, Object>> deleteRow(HongXunWorkNum hongXunWorkNum) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<HongXunWorkItem> hongXunWorkItems = (List<HongXunWorkItem>)stockDao.quarywithpara("HongXunWorkItem", "workNumID", hongXunWorkNum.getIdc());		
		for(HongXunWorkItem item: hongXunWorkItems){
			stockDao.deletRow(item);
		}
		stockDao.deletRow(hongXunWorkNum);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		return list;
	}
*/	
	@Override
	public Boolean deleteRow(int iD) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<HongXunWorkItem> hongXunWorkItems = (List<HongXunWorkItem>)stockDao.quarywithpara("HongXunWorkItem", "workNumID", iD);		
/*		for(HongXunWorkItem item: hongXunWorkItems){
			stockDao.deletRow(item);
		}*/
		iWorkDao.deleteWorkItemList(hongXunWorkItems);
		HongXunWorkNum hongXunWorkNum = iWorkDao.workNumFindById(iD);
		iWorkDao.deletRow(hongXunWorkNum);
		return true;
	}

	@Override
	public List<HongXunWorkNum> quary(HongXunWorkNum hongXunWorkNum) {
		// TODO Auto-generated method stub
		List<HongXunWorkNum> list = iWorkDao.quary(hongXunWorkNum);
		return list;
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
    	List<HongXunWorkNum> hongXunDataThrees = iWorkDao.quary(new HongXunWorkNum());
    	for(HongXunWorkNum item: hongXunDataThrees){
			if(beginDate.before(item.getWorkStartDate()) && endDate.after(item.getWorkStartDate())){
				//System.out.println("ID: " + item.getID());
       			Map<String,Object> map = new HashMap<String,Object>();
       			mapHongXunWorkNum(map, item);
       	   		list.add(map);	
			}
    	}
    	return list;
	}

	@Override
	public List<Map<String, Object>> searchNum(String database, String filed, String num) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<HongXunWorkNum> hongXunDataThrees = (List<HongXunWorkNum>)stockDao.quarywithpara(database, filed, num);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		if(hongXunDataThrees != null){
			for(HongXunWorkNum item: hongXunDataThrees){
				Map<String,Object> map = new HashMap<String,Object>();
       			mapHongXunWorkNum(map, item);
       			list.add(map);	
			}
		}
		return list;
	}
	
	@Override
	public List<Map<String, Object>> searchWorkNum(String workNum) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<HongXunWorkNum> hongXunWorkNums = (List<HongXunWorkNum>) stockDao.quarywithpara("HongXunWorkNum", "workNum", workNum);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (hongXunWorkNums != null) {
			for (HongXunWorkNum item : hongXunWorkNums) {
				Map<String, Object> map = new HashMap<String, Object>();
				mapHongXunWorkNum(map, item);
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
		List<HongXunWorkNum> hongXunWorkNums = (List<HongXunWorkNum>) stockDao.quaryFuzzyWithpara("HongXunWorkNum", "workNum", str); 		
		for(HongXunWorkNum item : hongXunWorkNums){
			if(!listName.contains(item.getWorkNum())){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("id", item.getIdc());
				map.put("name", item.getWorkNum());
				listName.add(item.getWorkNum());
				list.add(map);
			}
		};
		return list;
	}
	
	
	@Override
	public List<Map<String, Object>> getNumthreeFathers(HongXunWorkNum hongXunWorkNum) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
	/*	HongXunWorkItem hongXunDataThreeFather = new HongXunWorkItem();
		hongXunDataThreeFather.setGrandfID(hongXunWorkNum.getIdc());
		List<HongXunWorkItem> hongXunDataThreeFathers = stockDao.quary(hongXunDataThreeFather);
   		for(HongXunWorkItem item : hongXunDataThreeFathers){   	   		
   			Map<String,Object> map = new HashMap<String,Object>();
   	   		mapHongXunWorkNumFather(map, item);
	   	   	list.add(map);	   				
   		}*/
		return list;
	}

	@Override
	public void saveWorkItem(List<HongXunBomTree> hongXunBomTreeList, HongXunWorkNum hongXunWorkNum) {
		// TODO Auto-generated method stub
		for(HongXunBomTree item : hongXunBomTreeList){   	
			HongXunWorkItem hongXunWorkItem = new HongXunWorkItem();
			hongXunWorkItem.setDrawNum(item.getBomDrawingNum());		
			hongXunWorkItem.setLineNum(item.getBomLine());
			hongXunWorkItem.setGroupNum(item.getBomGroup());
			//hongXunWorkItem.setSupplier(item);
			hongXunWorkItem.setMaterialNum(item.getBomMaterialNum());
			hongXunWorkItem.setOrderNum(item.getBomOrderNum());
			hongXunWorkItem.setRemark(item.getRemark());
			hongXunWorkItem.setWorkDeliveryDate(item.getBomDeliveryDate());
			hongXunWorkItem.setQuantity(item.getBomItemQuantity());
			hongXunWorkItem.setSpecification(item.getBomSpacification());
			hongXunWorkItem.setWorkDeliveryDate(item.getBomDeliveryDate());
			
			hongXunWorkItem.setOrderNumID(item.getIdc());
			hongXunWorkItem.setAttribute(item.getAttribute());
			hongXunWorkItem.setWorkNumID(hongXunWorkNum.getIdc());
			hongXunWorkItem.setBomMaterialCuting(item.getBomMaterialCuting());
			hongXunWorkItem.setBomPainting(item.getBomPainting());
			hongXunWorkItem.setBomMaterialWeld(item.getBomMaterialWeld());
			hongXunWorkItem.setBomPainting(item.getBomPainting());
			hongXunWorkItem.setBomAssemble(item.getBomAssemble());
			hongXunWorkItem.setBomWeiwai(item.getBomWeiwai());
			iWorkDao.save(hongXunWorkItem);
		}
	}
	

	private void mapHongXunWorkNum(Map<String, Object> map, HongXunWorkNum item) {
		// TODO Auto-generated method stub	
		map.put("orderNum", item.getOrderNum());
		map.put("componentNum", item.getComponentNum());
		map.put("componentName", item.getComponentName());
		if(item.getWorkStartDate() != null){
			map.put("workStartDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(item.getWorkStartDate()));
		}
		if(item.getWorkEndDate() != null){
			map.put("workEndDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(item.getWorkEndDate()));
		}
		map.put("idc", item.getIdc());
//		map.put("stockoutNum", item.getStockoutNum());
		map.put("workNum", item.getWorkNum());
		map.put("workProcedure", item.getWorkProcedure());
		map.put("workQuantity", item.getWorkQuantity());
/*		//System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(item.getStockoutDate()));
		if(item.getStockoutDate() != null){
			map.put("stockoutDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(item.getStockoutDate()));
		}*/
/*		if(item.getStockoutDate() != null){
			map.put("getStockoutDate", new SimpleDateFormat("yyyy-MM-dd").format(item.getStockoutDate()));
		}*/
/*		map.put("getMaterialStaff", item.getGetMaterialStaff());
		map.put("stockMenager", item.getStockMenager());
		
		Double value = (item.getProductQuantity().doubleValue());*/
		//System.out.println(orderAmount);
/*		if(value == 0){
			map.put("productQuantity","");
		}else{
			map.put("productQuantity", value);
		}*/
/*		if(item.getPurchaseOrderAmount() != null){
			map.put("purchaseOrderAmount", item.getPurchaseOrderAmount().toString());
		}*/
		map.put("state", item.getState());
		map.put("remark", item.getRemark());	
	}
/*	
	private void mapHongXunDataThreeFather (Map<String, Object> map, HongXunWorkItem item) {
		map.put("idc", item.getIdc());
		map.put("grandfID", item.getHongXunWorkNum().getIdc());
		map.put("prodInDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(item.getProdInDate()));
		map.put("prodInQuantity", item.getProdInQuantity());
		map.put("prodRemark", item.getProdRemark());
		map.put("prodStaff", item.getProdStaff());
	}*/

}
