package paul.sydney.service.materialstock.impl;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import paul.sydney.dao.IMaterialOutStockItemDao;
import paul.sydney.dao.IMaterialOutStockNumDao;
import paul.sydney.dao.IMaterialStockDao;
import paul.sydney.dao.StockDao;
import paul.sydney.model.HongXunMaterialOutStoreItem;
import paul.sydney.model.HongXunMaterialOutStoreNum;
import paul.sydney.service.materialstock.ServiceStoreOutNumInf;

@Transactional
@Service("serviceStoreOutNum")
public class ServiceStoreOutNumImpl implements ServiceStoreOutNumInf{
	
	@Autowired
	StockDao stockDao;
	public void setStockDao(StockDao stockDao) {
		this.stockDao = stockDao;
	}
	@Autowired
	IMaterialStockDao iMaterialStockDao;
	public void setStockDao(IMaterialStockDao iMaterialStockDao) {
		this.iMaterialStockDao = iMaterialStockDao;
	}
	@Autowired
	IMaterialOutStockNumDao iMaterialOutStockNumDao;
	public void setStockDao(IMaterialOutStockNumDao iMaterialOutStockNumDao) {
		this.iMaterialOutStockNumDao = iMaterialOutStockNumDao;
	}
	@Autowired
	IMaterialOutStockItemDao iMaterialOutStockItemDao;
	public void setStockDao(IMaterialOutStockItemDao iMaterialOutStockItemDao) {
		this.iMaterialOutStockItemDao = iMaterialOutStockItemDao;
	}
	@Override
	public List<Map<String, Object>> loadData() {
		// TODO Auto-generated method stub
		//System.out.println("serviceoneload");
		List<HongXunMaterialOutStoreNum> hongXunMaterialOutStoreNums = iMaterialOutStockNumDao.quary(new HongXunMaterialOutStoreNum());
   		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
   		List<Map<String,Object>> listCapital = new ArrayList<Map<String,Object>>();
   		for(int i=hongXunMaterialOutStoreNums.size()-1; i>=0; i--){ 	   		
   			Map<String,Object> map = new HashMap<String,Object>();
   	   		mapHongXunOutStoreNum(map, hongXunMaterialOutStoreNums.get(i));
   	   		if(hongXunMaterialOutStoreNums.get(i).getState().indexOf("领料")>0){
   	   			listCapital.add(map);
   	   		}else{
   	   			list.add(map);	   
   	   		}
	   	   					
   		}
   		listCapital.addAll(list);
   		return listCapital;
	}
	
	@Override
	public List<Map<String, Object>> saveRow(HongXunMaterialOutStoreNum hongXunMaterialOutStoreNum) {
		// TODO Auto-generated method stub
		iMaterialOutStockNumDao.save(hongXunMaterialOutStoreNum);
		Map<String,Object> map = new HashMap<String,Object>();
		mapHongXunOutStoreNum(map, hongXunMaterialOutStoreNum);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.add(map);
		return list;
	}
	
	@Override
	public List<Map<String, Object>> updateRow(HongXunMaterialOutStoreNum hongXunMaterialOutStoreNum) {
		// TODO Auto-generated method stub
		iMaterialOutStockNumDao.update(hongXunMaterialOutStoreNum);
		Map<String,Object> map = new HashMap<String,Object>();
		mapHongXunOutStoreNum(map, hongXunMaterialOutStoreNum);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.add(map);
		return list;
	}
	
	@Override
	public List<Map<String, Object>> updateStatus(int outStoreNumID) {
		// TODO Auto-generated method stub

		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		HongXunMaterialOutStoreItem hongXunMaterialOutStoreItem = new HongXunMaterialOutStoreItem();
		hongXunMaterialOutStoreItem.setOutStoreNumID(outStoreNumID);
		//@SuppressWarnings("unchecked")
		List<HongXunMaterialOutStoreItem> hongXunMaterialOutStoreItems = iMaterialOutStockItemDao.quary(hongXunMaterialOutStoreItem);//(List<HongXunMaterialOutStoreItem>) stockDao.quarywithpara("HongXunMaterialOutStoreItem", "outStoreNumID", outStoreNumID); 
		
		//boolean flag = false; boolean flag1 = false; String status="";
		int outPlanQuantity = 0; int outRealQuantity = 0;
		for(HongXunMaterialOutStoreItem item: hongXunMaterialOutStoreItems){
			if(item.getStoreOutPlanQuantity() != null){											
				outPlanQuantity = outPlanQuantity + item.getStoreOutPlanQuantity();	    		   		
	    	}
			if(item.getStoreOutRealQuantity() != null){
				outRealQuantity = outRealQuantity + item.getStoreOutRealQuantity();
		    }  
		}
		HongXunMaterialOutStoreNum hongXunMaterialOutStoreNum = iMaterialOutStockNumDao.outStoreNumFindById(outStoreNumID);

		
		hongXunMaterialOutStoreNum.setState("领料" + print(outRealQuantity, outPlanQuantity));
		iMaterialOutStockNumDao.update(hongXunMaterialOutStoreNum);
		Map<String,Object> map = new HashMap<String,Object>();	
		map.put("state", hongXunMaterialOutStoreNum.getState());
		list.add(map);	
		return list;
	}
	
	 private String print(double num1, double num2)
	  {		
		 double ratio = num1 / num2;
		 // 创建一个数值格式化对象  
		 NumberFormat format = NumberFormat.getPercentInstance();
		 //设置保留几位小数
		 format.setMaximumFractionDigits(2);
		 String result=format.format(ratio);
		 //System.out.println("百分比为1："+result);
		 //double ratioBB=1-ratio;
		 //String resultBB=format.format(ratioBB);
		 //System.out.println("百分比为："+resultBB);
		 return result;
		 
     }
	
	@Override
	public Boolean deleteRow(int ID) {
		// TODO Auto-generated method stub
		//HongXunMaterialOutStoreNum hongXunMaterialOutStoreNum = stockDao.outStoreNumFindById(ID);
		
		return null;
	}


	private void mapHongXunOutStoreNum(Map<String, Object> map, HongXunMaterialOutStoreNum item) {
		// TODO Auto-generated method stub	
		map.put("idc", item.getIdc());
		map.put("outStoreNum", item.getOutStoreNum());
		map.put("workProcedure", item.getWorkProcedure());
		map.put("quantity", item.getQuantity());
		map.put("state", item.getState());
		map.put("remark", item.getRemark());	
	}
/*
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
    	List<HongXunMaterialOutStoreNum> hongXunPurchaseNums = stockDao.quary(new HongXunMaterialOutStoreNum());
    	for(HongXunMaterialOutStoreNum item: hongXunPurchaseNums){
    		//System.out.println("date: " + item.getPoCreateDate());
			if(beginDate.getTime()<= item.getPurchaseDate().getTime() && endDate.getTime() >= item.getPurchaseDate().getTime()){
				//System.out.println("date: ");
       			Map<String,Object> map = new HashMap<String,Object>();
       			mapHongXunOutStoreNum(map, item);
       	   		list.add(map);	
			}
    	}
    	return list;
	}

	@Override
	public List<Map<String, Object>> searchNum(HongXunMaterialOutStoreNum hongXunMaterialOutStoreNum) {
		// TODO Auto-generated method stub
		List<HongXunMaterialOutStoreNum> hongXunDataOnes = stockDao.quarywithparaOne("purchaseOrderNum", hongXunMaterialOutStoreNum.getPurchaseNum());
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		if(hongXunDataOnes != null){
			for(HongXunMaterialOutStoreNum item: hongXunDataOnes){
				Map<String,Object> map = new HashMap<String,Object>();
       			mapHongXunOutStoreNum(map, item);
       			list.add(map);
       			
			}
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> refresh(List<HongXunMaterialOutStoreNum> hongXunDataOneList) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    	for(int i=0; i<hongXunDataOneList.size(); i++){
    		int ID = Integer.valueOf(hongXunDataOneList.get(i).getPurchaseNum());
    		Map<String,Object> map = new HashMap<String,Object>();
    		mapHongXunOutStoreNum(map, stockDao.purchaseNumFindById(ID));
    		list.add(map);	
    	}
		return list;
	}

	@Override
	public void save(HongXunPurchaseItem hongXunDataTwo) {
		// TODO Auto-generated method stub
		stockDao.save(hongXunDataTwo);
	}
	
	@Override
	public void update(HongXunPurchaseItem hongXunPurchaseItem) {
		// TODO Auto-generated method stub
		stockDao.update(hongXunPurchaseItem);
	}
	@Override
	public List<Map<String, Object>> autotimp(String str) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		List<String> listName = new ArrayList<String>();
		@SuppressWarnings("unchecked")
		List<HongXunMaterialOutStoreNum> hongXunPurchaseNums = (List<HongXunMaterialOutStoreNum>) stockDao.quaryFuzzyWithpara("HongXunMaterialOutStoreNum", "purchaseOrderNum", str); 
		
		for(HongXunMaterialOutStoreNum item : hongXunPurchaseNums){
			if(!listName.contains(item.getPurchaseNum())){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("id", item.getIdc());
				map.put("name", item.getPurchaseNum());
				listName.add(item.getPurchaseNum());
				list.add(map);
			}
		};
		return list;
	}

	@Override
	public List<Map<String, Object>> searchNum(String purchaseNum) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<HongXunMaterialOutStoreNum> hongXunPurchaseNums = (List<HongXunMaterialOutStoreNum>) stockDao.quarywithpara("HongXunMaterialOutStoreNum", "purchaseOrderNum", purchaseNum);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (hongXunPurchaseNums != null) {
			for (HongXunMaterialOutStoreNum item : hongXunPurchaseNums) {
				Map<String, Object> map = new HashMap<String, Object>();
				mapHongXunOutStoreNum(map, item);
				list.add(map);
			}
		}
		return list;
	}
*/

}
