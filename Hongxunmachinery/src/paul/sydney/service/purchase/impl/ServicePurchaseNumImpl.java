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

import paul.sydney.dao.IMaterialOutStockItemDao;
import paul.sydney.dao.IMaterialOutStockNumDao;
import paul.sydney.dao.IMaterialStockDao;
import paul.sydney.dao.IPurchaseDao;
import paul.sydney.dao.IPurchaseDeItemDao;
import paul.sydney.dao.IPurchaseItemDao;
import paul.sydney.dao.ISuppliersDao;
import paul.sydney.dao.StockDao;
import paul.sydney.model.HongXunPurchaseNum;
import paul.sydney.model.HongXunSuppliersPrice;
import paul.sydney.service.purchase.ServicePurchaseNumInf;
import paul.sydney.model.HongXunBomTree;
import paul.sydney.model.HongXunMaterialOutStoreItem;
import paul.sydney.model.HongXunMaterialOutStoreNum;
import paul.sydney.model.HongXunMaterialStock;
import paul.sydney.model.HongXunPurchaseDeItem;
import paul.sydney.model.HongXunPurchaseItem;
import org.springframework.transaction.annotation.Transactional;
@Transactional
@Service("servicePurchaseNum")
public class ServicePurchaseNumImpl implements ServicePurchaseNumInf{
	
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
	IPurchaseItemDao iPurchaseItemDao;
	public void setStockDao(IPurchaseItemDao iPurchaseItemDao) {
		this.iPurchaseItemDao = iPurchaseItemDao;
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
	@Autowired
	ISuppliersDao iSuppliersDao;
	public void setStockDao(ISuppliersDao iSuppliersDao) {
		this.iSuppliersDao = iSuppliersDao;
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
		List<HongXunPurchaseNum> hongXunDataOnes = iPurchaseDao.quary(new HongXunPurchaseNum());
   		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
   		List<Map<String,Object>> listCaptial = new ArrayList<Map<String,Object>>();
   		for(int i=hongXunDataOnes.size()-1; i>=0; i--){
/*   			Iterator<DataOutstore> iterator = item.getDataOutstore().iterator();
   				while(iterator.hasNext()){
   					DataOutstore dataOutstore = iterator.next();
   					Map<String,Object> mapOutstore = new HashMap<String,Object>();
   					mapScrewOut(mapOutstore, dataOutstore);				
//   				System.out.println(dataOutstore.getHospital());
   				}
//
*/   	  	Map<String,Object> map = new HashMap<String,Object>();
			mapHongXunPurchaseNum(map, hongXunDataOnes.get(i));
   			if(hongXunDataOnes.get(i).getPurchaseStatus() == null || hongXunDataOnes.get(i).getPurchaseStatus().indexOf("部分")>-1){
   				listCaptial.add(map);
   			}else{
		   	   	list.add(map);	
   			}
   		}
   		listCaptial.addAll(list);
   		return listCaptial;
	}
	
	
	@Override
	public Boolean deleteRow(int ID) {
		// TODO Auto-generated method stub
		HongXunPurchaseNum hongXunPurchaseNum = iPurchaseDao.purchaseNumFindById(ID);
		HongXunPurchaseItem hongXunPurchaseItem = new HongXunPurchaseItem();
		hongXunPurchaseItem.setPurchaseNumID(hongXunPurchaseNum.getIdc());
		List<HongXunPurchaseItem> hongXunPurchaseItems = iPurchaseItemDao.quary(hongXunPurchaseItem);
		for(HongXunPurchaseItem item : hongXunPurchaseItems){			
			// TODO Auto-generated method stub
			if(item.getPurchaseDeItemID() != null){
				
				HongXunPurchaseDeItem hongXunPurchaseDeItem = iPurchaseDeItemDao.purchaseDeItemFindById(item.getPurchaseDeItemID());
				if(hongXunPurchaseDeItem != null){
					HongXunMaterialStock hongXunMaterialStock = hongXunPurchaseDeItem.getHongXunMaterialStock();
					if(hongXunMaterialStock.getInRoadQuantity()==null){
						hongXunMaterialStock.setInRoadQuantity(0);
					}
					hongXunMaterialStock.setInRoadQuantity(hongXunMaterialStock.getInRoadQuantity()-item.getMaterialPurchaseQuantity());			
					hongXunMaterialStock.setAlarm("报警");	
					//hongXunMaterialStock.setPurchaseDeId(null);
					hongXunMaterialStock.setPurchaseId(null);
		    		iMaterialStockDao.update(hongXunMaterialStock);
		    		//hongXunPurchaseDeItem.setDeNumId(null);
		    		hongXunPurchaseDeItem.setStatus("已提单");
		    		iPurchaseDeItemDao.update(hongXunPurchaseDeItem);
				}
			}else{
				System.out.println("无库信息了");
			}
			iPurchaseDeItemDao.deletRow(hongXunPurchaseItem);
		}
		iPurchaseDao.deletRow(hongXunPurchaseNum);
		return true;		
	}


	private void mapHongXunPurchaseNum(Map<String, Object> map, HongXunPurchaseNum item) {
		// TODO Auto-generated method stub			
		map.put("idc", item.getIdc());
		map.put("purchaseNum", item.getPurchaseNum());
		map.put("productionOrderNum", item.getProductionItemNum());
		
		map.put("productionDes", item.getProductionDes());
		if(item.getPurchaseDate() != null){
			map.put("purchaseDate", new SimpleDateFormat("yyyy-MM-dd").format(item.getPurchaseDate()));
		}
		if(item.getPurchaseDeliveryDate() != null){
			map.put("purchaseDeliveryDate", new SimpleDateFormat("yyyy-MM-dd").format(item.getPurchaseDeliveryDate()));
		}
		map.put("purchasingAgent", item.getPurchasingAgent());
		map.put("purchaseSupplier", item.getPurchaseSupplier());
		if(item.getPurchaseAmount() != null){
			map.put("purchaseOrderAmount",item.getPurchaseAmount().doubleValue());
		}
		map.put("purchaseStatus", item.getPurchaseStatus());
		map.put("purchaseRemark", item.getPurchaseRemark());	
		map.put("purchaseProcedure", item.getPurchaseProcedure());	
		map.put("purchaseQuantity", item.getPurchaseQuantity());	
		
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
    	List<HongXunPurchaseNum> hongXunPurchaseNums = iPurchaseDao.quary(new HongXunPurchaseNum());
    	for(HongXunPurchaseNum item: hongXunPurchaseNums){
    		//System.out.println("date: " + item.getPoCreateDate());
			if(beginDate.getTime()<= item.getPurchaseDate().getTime() && endDate.getTime() >= item.getPurchaseDate().getTime()){
				//System.out.println("date: ");
       			Map<String,Object> map = new HashMap<String,Object>();
       			mapHongXunPurchaseNum(map, item);
       	   		list.add(map);	
			}
    	}
    	return list;
	}

/*	@Override
	public List<Map<String, Object>> searchNum(HongXunPurchaseNum hongXunPurchaseNum) {
		// TODO Auto-generated method stub
		List<HongXunPurchaseNum> hongXunDataOnes = stockDao.quarywithparaOne("purchaseOrderNum", hongXunPurchaseNum.getPurchaseNum());
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
	@Override
	public List<Map<String, Object>> refresh(List<HongXunPurchaseNum> hongXunDataOneList) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    	for(int i=0; i<hongXunDataOneList.size(); i++){
    		int ID = Integer.valueOf(hongXunDataOneList.get(i).getPurchaseNum());
    		Map<String,Object> map = new HashMap<String,Object>();
    		mapHongXunPurchaseNum(map, iPurchaseDao.purchaseNumFindById(ID));
    		list.add(map);	
    	}
		return list;
	}

	@Override
	public List<Map<String, Object>> autotimp(String str) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		List<String> listName = new ArrayList<String>();
		@SuppressWarnings("unchecked")
		List<HongXunPurchaseNum> hongXunPurchaseNums = (List<HongXunPurchaseNum>) stockDao.quaryFuzzyWithpara("HongXunPurchaseNum", "purchaseNum", str); 
		
		for(HongXunPurchaseNum item : hongXunPurchaseNums){
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
		List<HongXunPurchaseNum> hongXunPurchaseNums = (List<HongXunPurchaseNum>) stockDao.quarywithpara("HongXunPurchaseNum", "purchaseNum", purchaseNum);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		//if (hongXunPurchaseNums != null) {
			for (HongXunPurchaseNum item : hongXunPurchaseNums) {
				Map<String, Object> map = new HashMap<String, Object>();
				mapHongXunPurchaseNum(map, item);
				list.add(map);
			}
		//}
		return list;
	}

	@Override
	public List<Map<String, Object>> saveRow(List<HongXunPurchaseNum> hongXunPurchaseNumList) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		for(HongXunPurchaseNum item: hongXunPurchaseNumList){
			if(item.getIdc() != 0){
				iPurchaseDao.update(item);
			}else{
				if(item.getPurchaseNum() == null && item.getPurchaseDate() == null){
					item.setPurchaseDate(new Date());
					Date now = new Date(); 
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
					String purchaseNum = dateFormat.format( now ); 
					purchaseNum = purchaseNum.replace("-", "");
					purchaseNum = purchaseNum.replace(":", "");
					purchaseNum = purchaseNum.replace(" ", "");
			    	item.setPurchaseNum(purchaseNum);
			    	item.setPurchaseStatus("新增外购");
				}
				iPurchaseDao.save(item);
			}
			Map<String, Object> map = new HashMap<String, Object>();
			mapHongXunPurchaseNum(map, item);
			list.add(map);
		}
		return list;
	}
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

	@Override
	public void update(HongXunMaterialOutStoreItem hongXunMaterialOutStoreItem) {
		// TODO Auto-generated method stub
		iMaterialOutStockItemDao.update(hongXunMaterialOutStoreItem);	
	}
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

	@Override
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

/*    	for(HongXunBomTree item: hongXunBomTreeList){
    		materialNumList.add(item.getBomMaterialNum());
    	}
    	System.out.println(materialNumList.size());
    	materialNumList = materialNumList.stream().distinct().collect(Collectors.toList());
    	System.out.println(materialNumList.size());
    	for(String str: materialNumList){
    		for(HongXunBomTree item: hongXunBomTreeList){
    			
    		}
    	}*/
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
    			/*if(hongXunMaterialOutStoreItemList.get(index).getStoreOutDate().after(item.getBomDeliveryDate())){ //合并选择最早日期
    				hongXunMaterialOutStoreItemList.get(index).setStoreOutDate(item.getBomDeliveryDate());
    			}*/

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
    		List<HongXunMaterialStock> hongXunMaterialStocks = iMaterialStockDao.quary(hongXunMaterialStockEntity);//(List<HongXunMaterialStock>) stockDao.quarywithpara("HongXunMaterialStock", "materialNum", item.getMaterialNum()); 
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
    	iMaterialOutStockNumDao.save(hongXunMaterialOutStoreNum);//保存领料单
    	for(HongXunMaterialOutStoreItem item: hongXunMaterialOutStoreItemList){
    		item.setOutStoreNumID(hongXunMaterialOutStoreNum.getIdc());
    		iMaterialOutStockItemDao.save(item);
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
    			iMaterialStockDao.update(item);
    		}else{
    			iMaterialStockDao.save(item);
    		}
    	}
   		return list;
	}

	@Override
	public List<Map<String, Object>> createNewPurchaseNum(List<HongXunPurchaseDeItem> hongXunPurchaseDeItemList) {
		// TODO Auto-generated method stub
		HongXunPurchaseNum hongXunPurchaseNum = new HongXunPurchaseNum();
    	hongXunPurchaseNum.setPurchaseDate(new Date());
    	//hongXunPurchaseNum.setPurchaseDeliveryDate(new Date());
    	//hongXunPurchaseNum.setPurchaseQuantity(String.valueOf(hongXunBomTreeList.size()-1));
    	//hongXunPurchaseNum.setPurchaseProcedure(hongXunBomTreeList.get(0).getRemark());
    	Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
		String purchaseNum = dateFormat.format( now ); 
		purchaseNum = purchaseNum.replace("-", "");
		purchaseNum = purchaseNum.replace(":", "");
		purchaseNum = purchaseNum.replace(" ", "");
    	hongXunPurchaseNum.setPurchaseNum("PX-001-003");
    	hongXunPurchaseNum.setPurchaseStatus("新增外购");
    	iPurchaseDao.save(hongXunPurchaseNum);
    	//Session session = null;
    	//int cnt=0;
    	List<HongXunPurchaseItem> hongXunPurchaseItemList = new ArrayList<HongXunPurchaseItem>();
    	List<HongXunMaterialStock> hongXunMaterialStockList = new ArrayList<HongXunMaterialStock>();
    	HongXunSuppliersPrice hongXunSupplierPrice = new HongXunSuppliersPrice();
    	for(HongXunPurchaseDeItem item: hongXunPurchaseDeItemList){
    		HongXunPurchaseItem hongXunPurchaseItem = new HongXunPurchaseItem();
    		HongXunMaterialStock hongXunMaterialStock = item.getHongXunMaterialStock();
    		hongXunPurchaseItem.setMaterialNum(hongXunMaterialStock.getMaterialNum());
    		hongXunPurchaseItem.setSpecification(hongXunMaterialStock.getSpecification());    		
    		hongXunPurchaseItem.setMaterialPurchaseQuantity(item.getQuantity()); 			
       		hongXunPurchaseItem.setDeliveryDate(hongXunMaterialStock.getDeliveryDate());
       		hongXunSupplierPrice.setMaterialNum(hongXunMaterialStock.getMaterialNum());
       		List<HongXunSuppliersPrice> hongXunSupplierPrices = iSuppliersDao.quary(hongXunSupplierPrice);
       		if(hongXunSupplierPrices.size()==1){
       			hongXunPurchaseItem.setPrice(hongXunSupplierPrices.get(0).getPrice());
       			double materialMoney = hongXunPurchaseItem.getPrice().doubleValue() * hongXunPurchaseItem.getMaterialPurchaseQuantity();
       			hongXunPurchaseItem.setMaterialMoney(new BigDecimal(Double.toString(materialMoney)));
       			hongXunPurchaseItem.setSupplier(hongXunSupplierPrices.get(0).getSupplier());
       		}
       		hongXunPurchaseItem.setPurchaseDeItemID(item.getIdc());
    		hongXunPurchaseItemList.add(hongXunPurchaseItem);
    		
			if (hongXunMaterialStock.getInRoadQuantity() == null) {
				hongXunMaterialStock.setInRoadQuantity(0);	
			} 
		
			if (hongXunMaterialStock.getPurQuantity() == null) {
				hongXunMaterialStock.setInRoadQuantity(0);
			} 
			hongXunMaterialStock.setInRoadQuantity(hongXunMaterialStock.getInRoadQuantity() + item.getQuantity());
    		hongXunMaterialStock.setPurQuantity(null);
    		hongXunMaterialStock.setAlarm(null);
    		hongXunMaterialStockList.add(hongXunMaterialStock);
    		item.setStatus("已下单");
    		item.setPurchaseId(hongXunPurchaseNum.getIdc());
    		iPurchaseDeItemDao.update(item);
		}
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	
    	for(HongXunPurchaseItem item: hongXunPurchaseItemList){
    		item.setPurchaseNumID(hongXunPurchaseNum.getIdc());   		
    		iPurchaseDeItemDao.save(item);
    	}
    	for(HongXunMaterialStock item: hongXunMaterialStockList){
    		item.setPurchaseId(hongXunPurchaseNum.getIdc());
    		iMaterialStockDao.update(item);
    	}
    	return list;
	}

	@Override
	public List<Map<String, Object>> getEntity() {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String,Object>();
		HongXunPurchaseNum e = new HongXunPurchaseNum();
		Class<? extends HongXunPurchaseNum> cls = e.getClass();  
        Field[] fields = cls.getDeclaredFields();  
        for(int i=0; i<fields.length; i++){  
            Field f = fields[i];  
            f.setAccessible(true); 
            map.put(f.getName(), "");
            //System.out.println("属性名:" + f.getName()/* + " 属性值:" + f.get(e)*/);  
        } 
        list.add(map);
        return list;
	}

	@Override
	public List<Map<String, Object>> updateSotockState(int purchaseNumID) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		HongXunPurchaseItem hongXunPurchaseItem = new HongXunPurchaseItem();
		hongXunPurchaseItem.setPurchaseNumID(purchaseNumID);
		List<HongXunPurchaseItem> hongXunPurchaseItems = iPurchaseDeItemDao.quary(hongXunPurchaseItem);
    	//@SuppressWarnings("unchecked")
		//List<HongXunPurchaseItem> hongXunPurchaseItems = (List<HongXunPurchaseItem>) stockDao.quarywithpara("HongXunPurchaseItem", "purchaseNumID", purchaseNumID); 
		//boolean flag = false; boolean flag1 = false; String status="";
		int purchaseQuantity = 0;  int realQuantity = 0;
    	for(HongXunPurchaseItem item : hongXunPurchaseItems){
    		if(item.getMaterialPurchaseQuantity()!=null){
    			purchaseQuantity = purchaseQuantity + item.getMaterialPurchaseQuantity();
    		}
    		if(item.getMaterialRealQuantity()!=null){
    			realQuantity = realQuantity + item.getMaterialRealQuantity();
    		}

    	}
    	System.out.println("purchaseQuantity："+purchaseQuantity + " realQuantity:" + realQuantity);
    	HongXunPurchaseNum hongXunPurchaseNum = iPurchaseDao.purchaseNumFindById(purchaseNumID);
		hongXunPurchaseNum.setPurchaseStatus("入库" + print(realQuantity, purchaseQuantity));
		iPurchaseDao.update(hongXunPurchaseNum);
    	Map<String,Object> map = new HashMap<String,Object>();
    	map.put("purchaseStatus", hongXunPurchaseNum.getPurchaseStatus());
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

}
