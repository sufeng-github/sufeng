package paul.sydney.service.purchase.impl;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.NumberFormat;
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

import paul.sydney.commen.result.PageResults;
import paul.sydney.dao.IMaterialStockDao;
import paul.sydney.dao.IPurchaseDao;
import paul.sydney.dao.IPurchaseDeItemDao;
import paul.sydney.dao.IPurchaseDeNumDao;
import paul.sydney.dao.StockDao;
import paul.sydney.model.HongXunPurchaseNum;
import paul.sydney.model.HongXunSuppliersPrice;
import paul.sydney.service.purchase.ServicePurchaseDeItemInf;
import paul.sydney.model.HongXunBomTree;
import paul.sydney.model.HongXunMaterialOutStoreItem;
import paul.sydney.model.HongXunMaterialOutStoreNum;
import paul.sydney.model.HongXunMaterialStock;
import paul.sydney.model.HongXunPurchaseDeItem;
import paul.sydney.model.HongXunPurchaseDeNum;
import paul.sydney.model.HongXunPurchaseItem;
import org.springframework.transaction.annotation.Transactional;
@Transactional
@Service("servicePurchaseDeItem")
public class ServicePurchaseDeItemImpl implements ServicePurchaseDeItemInf{
	
	@Autowired
	StockDao stockDao;
	public void setStockDao(StockDao stockDao) {
		this.stockDao = stockDao;
	}
	@Autowired
	IPurchaseDao iPurchaseDao;
	public void setStockDao(IPurchaseDao iPurchaseDao) {
		this.iPurchaseDao = iPurchaseDao;
	}
	@Autowired
	IPurchaseDeNumDao iPurchaseDeNumDao;
	public void setStockDao(IPurchaseDeNumDao iPurchaseDeNumDao) {
		this.iPurchaseDeNumDao = iPurchaseDeNumDao;
	}
	@Autowired
	IPurchaseDeItemDao iPurchaseDeItemDao;
	public void setStockDao(IPurchaseDeItemDao iPurchaseDeItemDao) {
		this.iPurchaseDeItemDao = iPurchaseDeItemDao;
	}
	@Autowired
	IMaterialStockDao iMaterialStockDao;
	public void setStockDao(IMaterialStockDao iMaterialStockDao) {
		this.iMaterialStockDao = iMaterialStockDao;
	}

	
/*	@Override
	public List<Map<String, Object>> loadData() {
		// TODO Auto-generated method stub
		//System.out.println("serviceoneload");
		List<HongXunPurchaseDeNum> hongXunPurchaseDeNums = stockDao.quary(new HongXunPurchaseDeNum());
   		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();

   		for(int i=hongXunPurchaseDeNums.size()-1; i>=0; i--){
  	  	
   			Map<String,Object> map = new HashMap<String,Object>();
			mapHongXunPurchaseDeNum(map, hongXunPurchaseDeNums.get(i));

		   	list.add(map);	
   			
   		}
   
   		return list;
	}*/
	
	
	@Override
	public Boolean deleteRow(int ID) {
		// TODO Auto-generated method stub
		HongXunPurchaseDeItem hongXunPurchaseDeItem = iPurchaseDeItemDao.purchaseDeItemFindById(ID);
	

		iPurchaseDeItemDao.delete(hongXunPurchaseDeItem);
		return true;
		
	}
/*

	private void mapHongXunPurchaseDeNum(Map<String, Object> map, HongXunPurchaseDeNum hongXunPurchaseDeNum) {
		// TODO Auto-generated method stub			
		map.put("idc", hongXunPurchaseDeNum.getIdc());
		map.put("purchaseDeNum", hongXunPurchaseDeNum.getPurchaseDeNum());
		if(hongXunPurchaseDeNum.getPurchaseDeDate() != null){
			map.put("purchaseDeDate", new SimpleDateFormat("yyyy-MM-dd").format(hongXunPurchaseDeNum.getPurchaseDeDate()));
		}
		
		map.put("purchaseDeRemark", hongXunPurchaseDeNum.getPurchaseDeRemark());	
		HongXunMaterialStock hongXunMaterialStock = new HongXunMaterialStock();
		hongXunMaterialStock.setPurchaseDeId(hongXunPurchaseDeNum.getIdc());		
		int total = iPurchaseDao.quary(hongXunMaterialStock).size();
		hongXunMaterialStock.setAlarm("已提单");
		int decnt = iPurchaseDao.quary(hongXunMaterialStock).size();
		if(total>0){
			//float u = ((float)total - (float)decnt)/(float)total;
			map.put("purchaseDeStatus", print(decnt, total) + "已下单" );
			
		}
	}

	 private String print(double num1, double num2)
	  {		
		 double ratio = num1 / num2;
		 // 创建一个数值格式化对象  
		 NumberFormat format = NumberFormat.getPercentInstance();
		 //设置保留几位小数
		 format.setMaximumFractionDigits(2);
		 String result=format.format(ratio);
		 System.out.println("百分比为1："+result);
		 double ratioBB=1-ratio;
		 String resultBB=format.format(ratioBB);
		 System.out.println("百分比为："+resultBB);
		 return resultBB;
		 
      }


	
	@Override
	public List<Map<String, Object>> betweenDateFind(String date1, String date2) throws ParseException {
		// TODO Auto-generated method stub
		Date beginDate;
    	Date endDate;
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    	if(df.parse(date1).before(df.parse(date2))){
    		System.out.println("0");
        	beginDate= df.parse(date1);
        	endDate = df.parse(date2);
    	}else{
    		System.out.println("1");
        	beginDate= df.parse(date2); 
        	endDate = df.parse(date1);
    	}
    	 Calendar c = Calendar.getInstance();
         c.setTime(endDate);
         c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
         endDate = c.getTime();
    	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    	List<HongXunPurchaseDeNum> hongXunPurchaseDeNums = iPurchaseDao.quary(new HongXunPurchaseDeNum());
    	for(HongXunPurchaseDeNum item: hongXunPurchaseDeNums){
    		//System.out.println("date: " + item.getPoCreateDate());
			if(beginDate.getTime()<= item.getPurchaseDeDate().getTime() && endDate.getTime() >= item.getPurchaseDeDate().getTime()){
				//System.out.println("date: ");
       			Map<String,Object> map = new HashMap<String,Object>();
       			mapHongXunPurchaseDeNum(map, item);
       	   		list.add(map);	
			}
    	}
    	return list;
	}
*/
/*	@Override
	public List<Map<String, Object>> searchNum(HongXunPurchaseNum hongXunPurchaseNum) {
		// TODO Auto-generated method stub
		List<HongXunPurchaseNum> hongXunDataOnes = iPurchaseDao.quarywithparaOne("purchaseOrderNum", hongXunPurchaseNum.getPurchaseNum());
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		if(hongXunDataOnes != null){
			for(HongXunPurchaseNum item: hongXunDataOnes){
				Map<String,Object> map = new HashMap<String,Object>();
       			mapHongXunPurchaseNum(map, item);
       			list.add(map);
			}
		}
		return list;
	}
*/
/*	@Override
	public List<Map<String, Object>> refresh(List<HongXunPurchaseNum> hongXunDataOneList) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    	for(int i=0; i<hongXunDataOneList.size(); i++){
    		int ID = Integer.valueOf(hongXunDataOneList.get(i).getPurchaseNum());
    		Map<String,Object> map = new HashMap<String,Object>();
    		//mapHongXunPurchaseNum(map, iPurchaseDao.purchaseNumFindById(ID));
    		list.add(map);	
    	}
		return list;
	}*/

	@Override
	public List<Map<String, Object>> autotimp(String str) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		List<String> listName = new ArrayList<String>();
		@SuppressWarnings("unchecked")
		List<HongXunPurchaseDeNum> hongXunPurchaseDeNums = (List<HongXunPurchaseDeNum>) stockDao.quaryFuzzyWithpara("HongXunPurchaseDeNum", "purchaseDeNum", str); 
		
		for(HongXunPurchaseDeNum item : hongXunPurchaseDeNums){
			if(!listName.contains(item.getPurchaseDeNum())){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("id", item.getIdc());
				map.put("name", item.getPurchaseDeNum());
				listName.add(item.getPurchaseDeNum());
				list.add(map);
			}
		};
		return list;
	}

/*	@Override
	public List<Map<String, Object>> searchNum(String purchaseDeNum) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<HongXunPurchaseDeNum> hongXunPurchaseDeNums = (List<HongXunPurchaseDeNum>) stockDao.quarywithpara("HongXunPurchaseDeNum", "purchaseDeNum", purchaseDeNum);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		//if (hongXunPurchaseNums != null) {
			for (HongXunPurchaseDeNum item : hongXunPurchaseDeNums) {
				Map<String, Object> map = new HashMap<String, Object>();
				mapHongXunPurchaseDeNum(map, item);
				list.add(map);
			}
		//}
		return list;
	}

	@Override
	public List<Map<String, Object>> saveRow(List<HongXunPurchaseDeNum> hongXunPurchaseDeNumList) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		for(HongXunPurchaseDeNum item: hongXunPurchaseDeNumList){
			if(item.getIdc() != 0){
				stockDao.update(item);
			}else{
				if(item.getPurchaseDeNum() == null && item.getPurchaseDeDate() == null){
					item.setPurchaseDeDate(new Date());
					Date now = new Date(); 
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
					String purchaseNum = dateFormat.format( now ); 
					purchaseNum = purchaseNum.replace("-", "");
					purchaseNum = purchaseNum.replace(":", "");
					purchaseNum = purchaseNum.replace(" ", "");
			    	item.setPurchaseDeNum(purchaseNum);
			    	item.setPurchaseDeStatus("已提单");
				}
				stockDao.save(item);
			}
			Map<String, Object> map = new HashMap<String, Object>();
			//mapHongXunPurchaseNum(map, item);
			list.add(map);
		}
		return list;
	}*/
/*	@Override
	public void saveRow(HongXunMaterialOutStoreNum hongXunMaterialOutStoreNum) {
		// TODO Auto-generated method stub
		stockDao.save(hongXunMaterialOutStoreNum);
	}

	@Override
	public void save(HongXunMaterialOutStoreItem hongXunMaterialOutStoreItem) {
		// TODO Auto-generated method stub
		stockDao.save(hongXunMaterialOutStoreItem);
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
	public List<Map<String, Object>> saveRow(HongXunPurchaseNum hongXunPurchaseNum) {
		// TODO Auto-generated method stub
		stockDao.save(hongXunPurchaseNum);
		Map<String,Object> map = new HashMap<String,Object>();
		mapHongXunPurchaseNum(map, hongXunPurchaseNum);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.add(map);
		return list;
	}
	
	@Override
	public List<Map<String, Object>> updateRow(HongXunPurchaseNum hongXunPurchaseNum) {
		// TODO Auto-generated method stub
		stockDao.update(hongXunPurchaseNum);
		Map<String,Object> map = new HashMap<String,Object>();
		mapHongXunPurchaseNum(map, hongXunPurchaseNum);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.add(map);
		return list;
	}
	*/

/*	@Override
	public void update(HongXunMaterialOutStoreItem hongXunMaterialOutStoreItem) {
		// TODO Auto-generated method stub
		stockDao.update(hongXunMaterialOutStoreItem);	
	}*/
/*	
	@Override
	public void calPurchaseQuantity(int purchaseNumID) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<HongXunPurchaseItem> hongXunPurchaseItems = (List<HongXunPurchaseItem>) stockDao.quarywithpara("HongXunPurchaseItem", "purchaseNumID", purchaseNumID);
		for(HongXunPurchaseItem item: hongXunPurchaseItems){		
			@SuppressWarnings("unchecked")
			List<HongXunMaterialStock> hongXunMaterialStocks = (List<HongXunMaterialStock>) stockDao.quarywithpara("HongXunMaterialStock", "materialNum", item.getMaterialNum());
			if(hongXunMaterialStocks.size()==1){
				Integer safeQuantity = hongXunMaterialStocks.get(0).getSafeQuantity();
				Integer calQuantity = hongXunMaterialStocks.get(0).getCalQuantity();
				Integer realQuantity = hongXunMaterialStocks.get(0).getQuantity();
				int purchaseQuantity;
				if(calQuantity== null){
					calQuantity = realQuantity;
				}
					calQuantity = calQuantity - item.getMaterialPlanQuantity();
					if(calQuantity>=0){
						if(safeQuantity == null){
							purchaseQuantity = 0;							
						}else{		
							if(calQuantity>=safeQuantity){
								purchaseQuantity = 0;
							}else{
								purchaseQuantity = safeQuantity;
								calQuantity = calQuantity + safeQuantity;
							}
						}
					}else{
						if(safeQuantity == null){
							purchaseQuantity = 0- calQuantity;
							calQuantity = 0;
						}else{
							purchaseQuantity=(0 - calQuantity) +  safeQuantity;
							calQuantity =  safeQuantity;
						}
					}				
				
				hongXunMaterialStocks.get(0).setCalQuantity(calQuantity);
				stockDao.update(hongXunMaterialStocks.get(0));	
				item.setMaterialPurchaseQuantity(purchaseQuantity);
				stockDao.update(item);				
			}else if(hongXunMaterialStocks.size()==0){
				item.setMaterialPurchaseQuantity(item.getMaterialPlanQuantity());
				stockDao.update(item);	
			}else{
				System.out.println("有重复料号，请联系工程师");
			}
		}
	}*/

	/*@Override
	public List<Map<String, Object>> createOutStoreSheet(List<HongXunBomTree> hongXunBomTreeList) {
		// TODO Auto-generated method stub
		//SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	HongXunMaterialOutStoreNum hongXunMaterialOutStoreNum = new HongXunMaterialOutStoreNum();
    	hongXunMaterialOutStoreNum.setOutStoreNum(hongXunBomTreeList.get(0).getStatus());   	
    	hongXunMaterialOutStoreNum.setWorkProcedure(hongXunBomTreeList.get(0).getRemark());
    	hongXunMaterialOutStoreNum.setState("新增领料单");
    	//service.saveRow(hongXunMaterialOutStoreNum);
    	
    	hongXunBomTreeList = hongXunBomTreeList.subList(1, hongXunBomTreeList.size());
    	List<HongXunMaterialOutStoreItem> hongXunMaterialOutStoreItemList = new ArrayList<HongXunMaterialOutStoreItem>();
    	List<HongXunMaterialOutStoreItem> hongXunMaterialOutStoreItemUnite = new ArrayList<HongXunMaterialOutStoreItem>();
    	List<String> materialNumList = new ArrayList<String>();
    	int cnt = 0;

    	for(HongXunBomTree item: hongXunBomTreeList){ //生成出库单具体项
    		HongXunMaterialOutStoreItem hongXunMaterialOutStoreItem = new HongXunMaterialOutStoreItem();
    		hongXunMaterialOutStoreItem.setMaterialNum(item.getBomMaterialNum());
    		hongXunMaterialOutStoreItem.setSpecification(item.getBomSpacification());    		
    		hongXunMaterialOutStoreItem.setStoreOutPlanQuantity(item.getBomItemQuantity());
    		if(item.getBomDeliveryDate() ==null){
    			System.out.println(item.getBomMaterialNum() + "交货日期为空");
    		}else{
    			//System.out.println("交货日期：" + item.getBomDeliveryDate());
    			hongXunMaterialOutStoreItem.setStoreOutDate(item.getBomDeliveryDate()); //借用传交期参数
    		}
    		//service.save(hongXunMaterialOutStoreItem);
    			
    		//boolean flag = false;
    		
    		int index = materialNumList.indexOf(item.getBomMaterialNum());
    		if(index>-1){
    			hongXunMaterialOutStoreItemUnite.get(index).setStoreOutPlanQuantity(hongXunMaterialOutStoreItemUnite.get(index).getStoreOutPlanQuantity() + hongXunMaterialOutStoreItem.getStoreOutPlanQuantity());

    			//stockDao.update(hongXunMaterialOutStoreItemList.get(index));	
    		}else{
    			//stockDao.save(hongXunMaterialOutStoreItem);
    			hongXunMaterialOutStoreItemUnite.add(hongXunMaterialOutStoreItem);
	    		materialNumList.add(hongXunMaterialOutStoreItem.getMaterialNum());
    		}
    		hongXunMaterialOutStoreItemList.add(hongXunMaterialOutStoreItem);
    	}
    	
    	//System.out.println("我等到花了也谢了" + hongXunMaterialOutStoreItemUnite.size());
    	List<HongXunMaterialStock> hongXunMaterialStockList = new ArrayList<HongXunMaterialStock>();
    	for(HongXunMaterialOutStoreItem item: hongXunMaterialOutStoreItemUnite){ //设置项目数，比较安全库存是否报警
    		//stockDao.save(item);
    		HongXunMaterialStock hongXunMaterialStockEntity = new HongXunMaterialStock();
    		hongXunMaterialStockEntity.setMaterialNum(item.getMaterialNum());
    		//@SuppressWarnings("unchecked")
    		List<HongXunMaterialStock> hongXunMaterialStocks = stockDao.quary(hongXunMaterialStockEntity);//(List<HongXunMaterialStock>) stockDao.quarywithpara("HongXunMaterialStock", "materialNum", item.getMaterialNum()); 
    		if(hongXunMaterialStocks.size()==1){
    			//System.out.println("终于进来了");
    			if(hongXunMaterialStocks.get(0).getItemQuantity() !=null){  
    				//hongXunMaterialStocks.get(0).setSafeQuantity(hongXunMaterialStocks.get(0).getItemQuantity());
    				hongXunMaterialStocks.get(0).setItemQuantity(hongXunMaterialStocks.get(0).getItemQuantity() + item.getStoreOutPlanQuantity());				   			
    				//hongXunMaterialStocks.get(0).setInRoadQuantity(item.getStoreOutPlanQuantity());
    			}else{
    				//System.out.println("2");
    				//hongXunMaterialStocks.get(0).setSafeQuantity(0);
    				hongXunMaterialStocks.get(0).setItemQuantity(item.getStoreOutPlanQuantity());
    				//hongXunMaterialStocks.get(0).setInRoadQuantity(item.getStoreOutPlanQuantity());
    			}
    			if(hongXunMaterialStocks.get(0).getSafeQuantity() == null){
    				hongXunMaterialStocks.get(0).setSafeQuantity(0);
    			}
    			if(hongXunMaterialStocks.get(0).getInRoadQuantity()==null){  				
	    			hongXunMaterialStocks.get(0).setInRoadQuantity(0);
	    		}
	    		if(hongXunMaterialStocks.get(0).getQuantity()-hongXunMaterialStocks.get(0).getItemQuantity() + hongXunMaterialStocks.get(0).getInRoadQuantity() < hongXunMaterialStocks.get(0).getSafeQuantity()){
	    			hongXunMaterialStocks.get(0).setAlarm("报警");
	    			hongXunMaterialStocks.get(0).setDeliveryDate(item.getStoreOutDate());
	    		}
	    		hongXunMaterialStockList.add(hongXunMaterialStocks.get(0));
    			//stockDao.update(hongXunMaterialStocks.get(0));
    		}else if(hongXunMaterialStocks.size()>1){
    			System.out.println(" 实时库存有重复料号:" + item.getMaterialNum());
    		}else{
    			System.out.println("新物料：" + item.getMaterialNum());
    			HongXunMaterialStock hongXunMaterialStock = new HongXunMaterialStock();
    			hongXunMaterialStock.setMaterialNum(item.getMaterialNum());
    			hongXunMaterialStock.setSpecification(item.getSpecification());
    			hongXunMaterialStock.setDeliveryDate(item.getStoreOutDate());
    			hongXunMaterialStock.setQuantity(0);
    			hongXunMaterialStock.setSafeQuantity(0);
    			hongXunMaterialStock.setInRoadQuantity(0);
    			hongXunMaterialStock.setItemQuantity(item.getStoreOutPlanQuantity());
    			hongXunMaterialStock.setAlarm("报警");
    			hongXunMaterialStock.setRemark("新物料");
    			hongXunMaterialStockList.add(hongXunMaterialStock);
    		}	
    	}
    	
    	hongXunMaterialOutStoreNum.setQuantity(cnt);
    	stockDao.save(hongXunMaterialOutStoreNum);//保存领料单
    	for(HongXunMaterialOutStoreItem item: hongXunMaterialOutStoreItemList){
    		item.setOutStoreNumID(hongXunMaterialOutStoreNum.getIdc());
    		stockDao.save(item);
    	}
		List<String> listStr = new ArrayList<String>();
		List<HongXunMaterialStock> listhongXunMaterialStock = new ArrayList<HongXunMaterialStock>();
		int index=0;
		for(HongXunMaterialStock item: hongXunMaterialStockList){
			String str = item.getMaterialNum();
			index = listStr.indexOf(str);
			if(index > -1){
				listhongXunMaterialStock.get(index).setItemQuantity(listhongXunMaterialStock.get(index).getItemQuantity() + item.getItemQuantity());
			}else{
				listStr.add(str);
				listhongXunMaterialStock.add(item);	
			}
		}
    	
    	for(HongXunMaterialStock item: listhongXunMaterialStock){
    		if(item.getIdc()!=0){
    			stockDao.update(item);
    		}else{
    			stockDao.save(item);
    		}
    	}
   		return list;
	}
*/
/*	@Override
	public List<Map<String, Object>> createNewPurchaseDeNum(List<HongXunMaterialStock> hongXunMaterialStockList) {
		// TODO Auto-generated method stub
		HongXunPurchaseDeNum hongXunPurchaseDeNum = new HongXunPurchaseDeNum();
		List<HongXunPurchaseDeNum> hongXunPurchaseDeNums = stockDao.quary(hongXunPurchaseDeNum);
		
    	hongXunPurchaseDeNum.setPurchaseDeDate(new Date());
    	Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//可以方便地修改日期格式
		String purchaseNum = dateFormat.format( now ); 
		purchaseNum = purchaseNum.replace("-", "");
		if(hongXunPurchaseDeNums.size()==0){
			hongXunPurchaseDeNum.setPurchaseDeNum("HX-" + purchaseNum + "-1");
		}else{
			String index = hongXunPurchaseDeNums.get(hongXunPurchaseDeNums.size()-1).getPurchaseDeNum().split("-")[2];
			int sn = Integer.valueOf(index)+1;
			hongXunPurchaseDeNum.setPurchaseDeNum("HX-" + purchaseNum  + "-" + sn);
		}
    	stockDao.save(hongXunPurchaseDeNum);
    	for(HongXunMaterialStock item: hongXunMaterialStockList){

    		//
    		HongXunPurchaseDeItem hongXunPurchaseDeItem = new HongXunPurchaseDeItem();
    		hongXunPurchaseDeItem.setStatus("已提单");
    		if(item.getPurQuantity()!=null){
    			hongXunPurchaseDeItem.setQuantity(item.getPurQuantity());
    		}else{
    			hongXunPurchaseDeItem.setQuantity(item.getSafeQuantity());
    		}
    		hongXunPurchaseDeItem.setMaterialId(item.getIdc());
    		hongXunPurchaseDeItem.setDeNumId(hongXunPurchaseDeNum.getIdc());
    		stockDao.save(hongXunPurchaseDeItem);
    		//item.setPurchaseDeId(hongXunPurchaseDeNum.getIdc());
    		//stockDao.update(item);
		}
    	
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	return list;
	}*/

	@Override
	public List<Map<String, Object>> getEntity() {
		// TODO Auto-generated method stub
		return iPurchaseDeItemDao.getEntity();

	}

/*	@Override
	public List<Map<String, Object>> updateSotockState(int purchaseNumID) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    	@SuppressWarnings("unchecked")
		List<HongXunPurchaseItem> hongXunPurchaseItems = (List<HongXunPurchaseItem>) stockDao.quarywithpara("HongXunPurchaseItem", "purchaseNumID", purchaseNumID); 
		boolean flag = false; boolean flag1 = false; String status="";
    	for(HongXunPurchaseItem item : hongXunPurchaseItems){
	   		if(item.getMaterialRealQuantity() != null){
	    		if(item.getMaterialPurchaseQuantity() > item.getMaterialRealQuantity()){		
		    		//System.out.println("部分入库1");
		    		status = "部分入库";
		    		break;	    		
		    	}else if(item.getMaterialPurchaseQuantity() == item.getMaterialRealQuantity()){
		    		if(flag1 == true){
		    			//System.out.println("部分入库2");
		    			status = "部分入库";
		    			break;
		    		}else{
		    			flag = true;	    			
		    		}
		    	}
	   		}else{
		    	if(flag == true){
		    		//System.out.println("部分入库3");
		    		status = "部分入库";
		    		break;
		    	}else{
		    		flag1 = true;
		    	}
		    }   		
    	}

    	Map<String,Object> map = new HashMap<String,Object>();
    	HongXunPurchaseNum hongXunPurchaseNum = stockDao.purchaseNumFindById(purchaseNumID);
		if(status.equals("")){	
			if(flag==false){
				status = hongXunPurchaseNum.getPurchaseStatus();				
			}else{
				status = "入库完成";								
			}
			
			//System.out.println("status1:" + hongXunOrderNum.getStatus());
		}
		map.put("purchaseStatus", status);
	
		hongXunPurchaseNum.setPurchaseStatus(status);
		stockDao.update(hongXunPurchaseNum);
		list.add(map);	
		return list;
	}*/

	@Override
	public List<Map<String, Object>> getNumList(Integer id) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		HongXunPurchaseDeItem hongXunPurchaseDeItem = new HongXunPurchaseDeItem();
		hongXunPurchaseDeItem.setDeNumId(id);
		List<HongXunPurchaseDeItem> hongXunPurchaseDeItems = iPurchaseDeItemDao.quary(hongXunPurchaseDeItem);
		for(HongXunPurchaseDeItem item: hongXunPurchaseDeItems){
			HongXunMaterialStock hongXunMaterialStock = item.getHongXunMaterialStock();
			Map<String,Object> map = new HashMap<String,Object>();
			mapHongXunMaterialStock(map, hongXunMaterialStock);
			map.put("purQuantity", item.getQuantity());
			map.put("status", item.getStatus());
			map.put("idc", item.getIdc());
			if(item.getPurchaseId()!=null){
				HongXunPurchaseNum hongXunPurchaseNum = iPurchaseDao.purchaseNumFindById(item.getPurchaseId());
				if(hongXunPurchaseNum != null && hongXunPurchaseNum.getPurchaseDate() != null){
					String date = new SimpleDateFormat("yyyy-MM-dd").format(hongXunPurchaseNum.getPurchaseDate());
					map.put("date", date);
				}
			}
			list.add(map);
		}
		return list;
	}
	
	private void mapHongXunMaterialStock(Map<String, Object> map, HongXunMaterialStock item) {
		// TODO Auto-generated method stub	

		map.put("materialNum", item.getMaterialNum());
		map.put("specification", item.getSpecification());
		map.put("quantity", item.getQuantity());
		map.put("unit", item.getUnit());
		map.put("safeQuantity", item.getSafeQuantity());

		if(item.getDeliveryDate()!=null){
			map.put("deliveryDate", new SimpleDateFormat("yyyy-MM-dd").format(item.getDeliveryDate()));
		}
		map.put("itemQuantity", item.getItemQuantity());
		map.put("inRoadQuantity", item.getInRoadQuantity());
		map.put("materialId", item.getIdc());		
	}


	@Override
	public PageResults<HongXunPurchaseDeItem> getData(HongXunPurchaseDeItem hongXunPurchaseDeItem, Integer pageNo,
			Integer pageSize) {
		// TODO Auto-generated method stub
		List<Object> list = new ArrayList<Object>();
		StringBuilder hql=new StringBuilder("from HongXunPurchaseDeItem where 1=1");
		//String类型属性
		if (hongXunPurchaseDeItem.getDeNumId() != 0) {
			hql.append(" and deNumId = ?");
			list.add(hongXunPurchaseDeItem.getDeNumId());			
		}
		return iPurchaseDeItemDao.findPageByFetchedHql(hql.toString(), "select count(*) " + hql.toString(), pageNo, pageSize,list.toArray());	
	}

	/*@Override
	public List<Map<String, Object>> getItemList() {
		// TODO Auto-generated method stub
		List<HongXunPurchaseDeNum> hongXunPurchaseDeNums = iPurchaseDeNumDao.quary(new HongXunPurchaseDeNum());
   		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
   		for(int i=hongXunPurchaseDeNums.size()-1; i>=0; i--){
   			HongXunPurchaseDeItem hongXunPurchaseDeItem = new HongXunPurchaseDeItem();
   			hongXunPurchaseDeItem.setDeNumId(hongXunPurchaseDeNums.get(i).getIdc());
   			hongXunPurchaseDeItem.setStatus("已提单");
   			String date = new SimpleDateFormat("yyyy-MM-dd").format(hongXunPurchaseDeNums.get(i).getPurchaseDeDate());
   			List<HongXunPurchaseDeItem> hongXunPurchaseDeItems = iPurchaseDeItemDao.quary(hongXunPurchaseDeItem);
   			for(HongXunPurchaseDeItem item : hongXunPurchaseDeItems){
   				HongXunMaterialStock hongXunMaterialStock = iMaterialStockDao.hongXunMaterialFindById(item.getMaterialId());
   				Map<String,Object> map = new HashMap<String,Object>();
   				mapHongXunMaterialStock(map, hongXunMaterialStock);
   	   			map.put("date", date);
   	   			map.put("purQuantity", item.getQuantity());
   	   			map.put("status", "待采购");
   	   			map.put("idc", item.getIdc());
   	   			map.put("deNumId", item.getDeNumId());  	   		
   	   			list.add(map);  				   				
   			}
   		}		
		return list;
	}*/

}
