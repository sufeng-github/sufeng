package paul.sydney.service.purchase.impl;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import paul.sydney.dao.IMaterialStockDao;
import paul.sydney.dao.IPurchaseDao;
import paul.sydney.dao.IPurchaseDeItemDao;
import paul.sydney.dao.IPurchaseItemDao;
import paul.sydney.dao.IPurchaseItemInStockDao;
import paul.sydney.dao.StockDao;
import paul.sydney.model.HongXunPurchaseNum;
import paul.sydney.service.purchase.ServicePurchaseItemInf;
import paul.sydney.model.HongXunPurchaseItem;
import paul.sydney.model.HongXunBomTree;
import paul.sydney.model.HongXunMaterialStock;
import paul.sydney.model.HongXunPurchaseDeItem;
import paul.sydney.model.HongXunPurchaseItemInStock;

@Transactional
@Service("serviceNumTwo")
public class ServicePurchaseItemImpl implements ServicePurchaseItemInf{
	
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
	IPurchaseItemInStockDao iPurchaseItemInStockDao;
	public void setStockDao(IPurchaseItemInStockDao iPurchaseItemInStockDao) {
		this.iPurchaseItemInStockDao = iPurchaseItemInStockDao;
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
	@Override
	public List<Map<String, Object>> loadData() {
		// TODO Auto-generated method stub
		//System.out.println("serviceBranchoneload");
		HongXunPurchaseItem hongXunPurchaseItem = new HongXunPurchaseItem();
		List<HongXunPurchaseItem> hongXunPurchaseItems = iPurchaseItemDao.quary(hongXunPurchaseItem);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		for(HongXunPurchaseItem item : hongXunPurchaseItems){
			Map<String,Object> map = new HashMap<String,Object>();
			//System.out.println(itemSun.getIdc());
			mapHongXunPurchaseItem(map, item);
			list.add(map);
		}	
		return list;		
	}
	

	@Override
	public List<Map<String, Object>> getAllPurchaseItems() {
		// TODO Auto-generated method stub
		List<HongXunPurchaseNum> hongXunPurchaseNums = iPurchaseDao.quary(new HongXunPurchaseNum());
   		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();  	
   		for(int i=hongXunPurchaseNums.size()-1; i>=0; i--){
   			HongXunPurchaseItem hongXunPurchaseItem = new HongXunPurchaseItem();
   			hongXunPurchaseItem.setPurchaseNumID(hongXunPurchaseNums.get(i).getIdc());
   			List<HongXunPurchaseItem> hongXunPurchaseItems = iPurchaseItemDao.quary(hongXunPurchaseItem);
   			for(HongXunPurchaseItem item: hongXunPurchaseItems){
   				Map<String,Object> map = new HashMap<String,Object>();
   				mapHongXunPurchaseItem(map, item);
   				list.add(map);	 	   			
   			} 	   
   		}
   		return list;
	}
	
	@Override
	public List<Map<String, Object>> saveMoney(int id) {
		// TODO Auto-generated method stub
		//HongXunPurchaseNum hongXunPurchaseNum = iPurchaseDao.purchaseNumFindById(id);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    	@SuppressWarnings("unchecked")
		List<HongXunPurchaseItem> hongXunPurchaseItems = (List<HongXunPurchaseItem>) stockDao.quarywithpara("HongXunPurchaseItem", "purchaseNumID", id); 
    	BigDecimal sum = null; //String state="";
    	for(int i=0; i<hongXunPurchaseItems.size(); i++){
    		if(hongXunPurchaseItems.get(i).getMaterialMoney() != null){
    			if(sum==null){
    				sum = hongXunPurchaseItems.get(i).getMaterialMoney();
    			}else{
    				sum = sum.add(hongXunPurchaseItems.get(i).getMaterialMoney());
    			}
    		}
    		/*if(hongXunPurchaseItems.get(i).getMaterialRealQuantity()!=null){
				if(state.equals("空")){
					state = "部分入库";
					break;
				}else{ 
					if(hongXunPurchaseItems.get(i).getMaterialRealQuantity()<hongXunPurchaseItems.get(i).getMaterialPurchaseQuantity()){										
						state = "部分入库";
						break;
					}else{
						state = "部分入库";
						if(i==hongXunPurchaseItems.size()-1){
							if(!state.equals("空")){
								state = "入库完成";							
							}
						}
					}
				}
			}else{
				if(state.indexOf("入库")>-1){
					state = "部分入库";
					break;
				}else{
					state="空";
				}
			}*/
    	}
    	
		if(sum != null){
			HongXunPurchaseNum hongXunDataOne = iPurchaseDao.purchaseNumFindById(id);
			hongXunDataOne.setPurchaseAmount(sum);
			iPurchaseDao.update(hongXunDataOne);	
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("purchaseOrderAmount", hongXunDataOne.getPurchaseAmount());
			list.add(map);	
		}else{
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("purchaseOrderAmount", "");
			list.add(map);	
		}
	/*	if(state.equals("空")){
			state="";
		}else{
			hongXunPurchaseNum.setPurchaseStatus(state);
			iPurchaseDao.update(hongXunPurchaseNum);
		}
		
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("state", state);
		list.add(map);	*/				
		return list;
	}
	
	@Override
	public List<Map<String, Object>> updateSotockState(int id) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    	@SuppressWarnings("unchecked")
		List<HongXunPurchaseItem> hongXunPurchaseItems = (List<HongXunPurchaseItem>) stockDao.quarywithpara("HongXunPurchaseItem", "purchaseNumID", id); 
    	int flag = -1; //int flag1 = 0;
    	for(HongXunPurchaseItem item : hongXunPurchaseItems){
    		if(item.getMaterialRealQuantity() != null){
    			if(item.getMaterialPurchaseQuantity() > item.getMaterialRealQuantity()){
    				flag = 0;
    				break;
    			}else{
    				flag = 1;
    			}
    		}/*else{
    			flag1 = -1;
    		}*/
    	}
    	if(flag != -1){
			HongXunPurchaseNum hongXunPurchaseNum = iPurchaseDao.purchaseNumFindById(id);
			if(flag == 0){
				hongXunPurchaseNum.setPurchaseStatus("部分入库");
			}else if(flag == 1){
				hongXunPurchaseNum.setPurchaseStatus("入库完成");
			}	
			iPurchaseDao.update(hongXunPurchaseNum);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("purchaseStatus", hongXunPurchaseNum.getPurchaseStatus());
			list.add(map);		
    	}			
		return list;
	}
	
	@Override
	public List<Map<String, Object>> saveRow(HongXunPurchaseItem hongXunPurchaseItem) {
		// TODO Auto-generated method stub
		iPurchaseItemDao.save(hongXunPurchaseItem);
		/*Map<String,Object> map = new HashMap<String,Object>();
		map.put("idc", hongXunPurchaseItem.getIdc());
		if(hongXunPurchaseItem.getMaterialMoney() != null){
			map.put("materialMoney", hongXunPurchaseItem.getMaterialMoney());
		}else{
			map.put("materialMoney", "");
		}*/
		Map<String,Object> map = new HashMap<String,Object>();
		//System.out.println(itemSun.getIdc());
		mapHongXunPurchaseItem(map, hongXunPurchaseItem);
		
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.add(map);
		return list;
	}
	
	@Override
	public List<Map<String, Object>> deleteRow(int ID) {
		// TODO Auto-generated method stub
		HongXunPurchaseItem hongXunPurchaseItem = iPurchaseItemDao.purchaseItemFindById(ID);
		iPurchaseItemDao.deletRow(hongXunPurchaseItem);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		return list;
	}
	
	@Override
	public List<Map<String, Object>> updateRow(HongXunPurchaseItem hongXunPurchaseItem) {
		iPurchaseItemDao.update(hongXunPurchaseItem);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("materialMoney", hongXunPurchaseItem.getMaterialMoney());	
		//map.put("instoreDate", hongXunPurchaseItem.getInstoreDate());
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.add(map);
		return list;
	}

	@Override
	public List<HongXunPurchaseItem> quary(HongXunPurchaseItem hongXunPurchaseItem) {
		// TODO Auto-generated method stub
		List<HongXunPurchaseItem> list = iPurchaseItemDao.quary(hongXunPurchaseItem);
		return list;
	}

/*	@Override
	public HongXunPurchaseNum quarywithpara(String para) {
		// TODO Auto-generated method stub
		return iPurchaseDao.quarywithpara(para);
	}*/

	@Override
	public List<Map<String, Object>> autotimp(String q, int purchaseNumID) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		HongXunPurchaseItem hongXunPurchaseItem = new HongXunPurchaseItem();
		hongXunPurchaseItem.setPurchaseNumID(purchaseNumID);
		List<HongXunPurchaseItem> hongXunPurchaseItems = iPurchaseItemDao.quary(hongXunPurchaseItem);
    	for(HongXunPurchaseItem item : hongXunPurchaseItems){ //查看工单具体项
			if(item.getSupplier().indexOf(q)>-1){			
				Map<String,Object> map = new HashMap<String,Object>();	
				map.put("name", item.getSupplier());
				map.put("id", item.getIdc());  
				list.add(map);
			}
		}
		return list;
	}

	
	@Override
	public List<Map<String, Object>> searchPurchaseItem(int purchaseNumID, String supplier) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		HongXunPurchaseItem hongXunPurchaseItem = new HongXunPurchaseItem();
		hongXunPurchaseItem.setPurchaseNumID(purchaseNumID);
		hongXunPurchaseItem.setSupplier(supplier);
		List<HongXunPurchaseItem> hongXunPurchaseItems = iPurchaseItemDao.quary(hongXunPurchaseItem);//(List<HongXunMaterialOutStoreItem>) iPurchaseDao.quarywithpara("HongXunMaterialOutStoreItem", "outStoreNumID", outStoreNumID); 
    	for(HongXunPurchaseItem item : hongXunPurchaseItems){ //查看工单具体项
				
				Map<String, Object> map = new HashMap<String, Object>();
				mapHongXunPurchaseItem(map, item);
				list.add(map);
			
		}
		return list;
	}
	
	@Override
	public List<Map<String, Object>> instorePurchaseItem(int id, int quantity) {
		// TODO Auto-generated method stub
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				HongXunPurchaseItemInStock hongXunPurchaseItemInStock = new HongXunPurchaseItemInStock();
				HongXunMaterialStock hongXunMaterialStock = new HongXunMaterialStock();
				HongXunPurchaseItem hongXunPurchaseItem = iPurchaseItemDao.purchaseItemFindById(id);
				
				//HongXunPurchaseItem hongXunPurchaseItemEntity = new HongXunPurchaseItem();
				//hongXunPurchaseItemEntity.setPurchaseNumID(purchaseNumID);		
		    	//@SuppressWarnings("unchecked")
				//List<HongXunPurchaseItem> hongXunPurchaseItems = iPurchaseDao.quary(hongXunPurchaseItemEntity);//(List<HongXunPurchaseItem>) iPurchaseDao.quarywithpara("HongXunPurchaseItem", "purchaseNumID", purchaseNumID); 
				//for(HongXunPurchaseItem item: hongXunPurchaseItems){	
				if(hongXunPurchaseItem != null){
						if(hongXunPurchaseItem.getMaterialPurchaseQuantity() != null){
							if(hongXunPurchaseItem.getMaterialRealQuantity() == null){
								if(quantity > hongXunPurchaseItem.getMaterialPurchaseQuantity()){
									//System.out.println("入库数量大于采购数量");
									Map<String, Object> map = new HashMap<String, Object>();
									map.put("error", "物料号:"+ hongXunPurchaseItem.getMaterialNum() +"->入库数量大于采购数量");
									list.add(map);
									return list;
								}
							}else{
								if(quantity > hongXunPurchaseItem.getMaterialPurchaseQuantity()-hongXunPurchaseItem.getMaterialRealQuantity()){
									//System.out.println("入库数量大于采购数量...");
									Map<String, Object> map = new HashMap<String, Object>();
									map.put("error", "物料号:"+ hongXunPurchaseItem.getMaterialNum() +"->入库数量大于采购数量...");
									list.add(map);
									return list;
								}
							}
						}
						//HongXunPurchaseItemInStock hongXunPurchaseItemInStock = new HongXunPurchaseItemInStock();
						//hongXunPurchaseItemInStock.setHongXunPurchaseItem(item);
						//System.out.println("item.getIdc():" + item.getIdc());
						hongXunPurchaseItemInStock.setPurchaseItemID(hongXunPurchaseItem.getIdc()); //填充入库信息
						hongXunPurchaseItemInStock.setInstoreDate(new Date());
						hongXunPurchaseItemInStock.setRealQuantity(quantity);
						//stockDao.save(hongXunPurchaseItemInStock);
						if(hongXunPurchaseItem.getMaterialRealQuantity() == null){		//修改采购入库数量
							hongXunPurchaseItem.setMaterialRealQuantity(hongXunPurchaseItemInStock.getRealQuantity());
						}else{
							hongXunPurchaseItem.setMaterialRealQuantity(hongXunPurchaseItem.getMaterialRealQuantity() + hongXunPurchaseItemInStock.getRealQuantity());
						}
						//hongXunPurchaseItem = item;
						//stockDao.update(item);
						HongXunMaterialStock hongXunMaterialStockEntity = new HongXunMaterialStock();
						hongXunMaterialStockEntity.setMaterialNum(hongXunPurchaseItem.getMaterialNum());
						//@SuppressWarnings("unchecked")
						List<HongXunMaterialStock> hongXunMaterialStocks = iMaterialStockDao.quary(hongXunMaterialStockEntity);//(List<HongXunMaterialStock>) stockDao.quarywithpara("HongXunMaterialStock", "materialNum", materialNum); 
				    	if(hongXunMaterialStocks.size()==0){		//修改实时库存
				    		//HongXunMaterialStock hongXunMaterialStock = new HongXunMaterialStock();
				    		hongXunMaterialStock.setMaterialNum(hongXunPurchaseItem.getMaterialNum());
				    		hongXunMaterialStock.setQuantity(hongXunPurchaseItemInStock.getRealQuantity());
				    		hongXunMaterialStock.setSpecification(hongXunPurchaseItem.getSpecification());
				    		hongXunMaterialStock.setUnit(hongXunPurchaseItem.getUnit());
				    		//stockDao.save(hongXunMaterialStock);
				    	}else if(hongXunMaterialStocks.size()==1){
				    		if(hongXunMaterialStocks.get(0).getQuantity() != null){
				    			hongXunMaterialStocks.get(0).setQuantity(hongXunMaterialStocks.get(0).getQuantity() + hongXunPurchaseItemInStock.getRealQuantity());		    			
				    		}else{
				    			hongXunMaterialStocks.get(0).setQuantity(hongXunPurchaseItemInStock.getRealQuantity());
				    		}
				    		if(hongXunMaterialStocks.get(0).getInRoadQuantity() != null){
				    			hongXunMaterialStocks.get(0).setInRoadQuantity(hongXunMaterialStocks.get(0).getInRoadQuantity()-hongXunPurchaseItemInStock.getRealQuantity());
				    		}
				    		hongXunMaterialStock = hongXunMaterialStocks.get(0);
				    		//stockDao.update(hongXunMaterialStocks.get(0));
				    	}else{
				    		//System.out.println("HongXunMaterialStock 有重复料号出错");
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("error", "物料号:"+ hongXunPurchaseItem.getMaterialNum() +"->有重复料号,请联系工程师。");
							list.add(map);
							return list;
				    	}
						
				//		break;
				//	}		
				}else{
				
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("error", "采购入库存单中无信息");
					list.add(map);
					return list;
				}
				
				iPurchaseItemInStockDao.save(hongXunPurchaseItemInStock);
				iPurchaseItemDao.update(hongXunPurchaseItem);
				if(hongXunMaterialStock.getIdc()==0){
					iMaterialStockDao.save(hongXunMaterialStock);
				}else{
					iMaterialStockDao.update(hongXunMaterialStock);
				}
				Map<String, Object> map = new HashMap<String, Object>();
				mapHongXunPurchaseItem(map,hongXunPurchaseItem);
				//mapHongXunPurchaseItem(map,hongXunPurchaseItem);
				list.add(map);
				return list;
			}
	

	@Override
	public List<Map<String, Object>> getPurchaseItems(int purchaseNumID) {
		// TODO Auto-generated method stub
    	@SuppressWarnings("unchecked")
		List<HongXunPurchaseItem> hongXunPurchaseItems = (List<HongXunPurchaseItem>) stockDao.quarywithpara("HongXunPurchaseItem", "purchaseNumID", purchaseNumID); 
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		//System.out.println("list.size:" + list.size());
    	for(HongXunPurchaseItem item : hongXunPurchaseItems){
			Map<String,Object> map = new HashMap<String,Object>();
			mapHongXunPurchaseItem(map, item);
			list.add(map);
    	}
		return list;
	}
	
	
/*
	@Override
	public List<Map<String, Object>> getStockinChildrens(int purchaseNumID) {
		// TODO Auto-generated method stub
    	@SuppressWarnings("unchecked")
		List<HongXunPurchaseItem> hongXunDataTwos = (List<HongXunPurchaseItem>) stockDao.quarywithpara("HongXunPurchaseItem", "purchaseNumID", purchaseNumID); 
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
	
    	for(HongXunPurchaseItem item : hongXunDataTwos){
			//item.getParentID();
			System.out.println("getStockinChildrens");
			//@SuppressWarnings("unchecked")
			//List<HongXunPurchaseItemInStock> hongXunDataTwoSuns = (List<HongXunPurchaseItemInStock>) stockDao.quarywithpara("HongXunPurchaseItemInStock", "grandfID", item.getIdc()); 
			//System.out.println("size : " + hongXunDataTwoSuns.size());
			Map<String,Object> map = new HashMap<String,Object>();
			//System.out.println(item.getID());
			mapHongXunPurchaseItem(map, item);
			list.add(map);
    	}
    	
		return list;
	}
*/
	@Override
	public List<Map<String, Object>> stockinLoadData() {
		// TODO Auto-generated method stub
		
		@SuppressWarnings("unchecked")
		List<HongXunPurchaseItem> hongXunDataTwos = (List<HongXunPurchaseItem>) stockDao.quary("from HongXunPurchaseItem");
		//System.out.println(hongXunDataTwos.size());
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		for(HongXunPurchaseItem item : hongXunDataTwos){
			Map<String,Object> map = new HashMap<String,Object>();
			mapHongXunPurchaseItem(map, item);
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> outstore(String materialNum, int quantity, int purchaseNumID) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		//int tmpQuantity = quantity;
		@SuppressWarnings("unchecked")
		List<HongXunPurchaseItem> hongXunDataTwos = (List<HongXunPurchaseItem>) stockDao.quarywithpara("HongXunPurchaseItem", "materialNum", materialNum); 
		/*for(HongXunPurchaseItem item : hongXunDataTwos){
			if(item.getInstoreQuantity()>0){
				if(item.getInstoreQuantity()>=quantity){
					item.setInstoreQuantity(item.getInstoreQuantity() - quantity);
					stockDao.update(item);
					outstoreBranch(purchaseNumID, quantity, item, list);
					//quantity = 0;
					break;
				}else{
					item.setInstoreQuantity(0);				
					stockDao.update(item);
					outstoreBranch(purchaseNumID, item.getInstoreQuantity(), item, list);
					quantity = quantity - item.getInstoreQuantity();
					System.out.println("quantity:" + quantity);
					if(quantity==0){
						break;
					}
				}
			}
		}*/
		return list;
		/*if(tmpQuantity == quantity){
			HongXunDataOneBranchLeaf hongXunDataOneBranchLeaf = new HongXunDataOneBranchLeaf();
			hongXunDataOneBranchLeaf.setUncleID(purchaseNumID);
			hongXunDataOneBranchLeaf.setStoreOutDate(new Date());
			if(quantity == 0){
				hongXunDataOneBranchLeaf.setStoreOutQuantity(tmpQuantity);
				//hongXunDataTwos.get(0).setInstoreQuantity(tmpQuantity);
			}else{
				hongXunDataOneBranchLeaf.setStoreOutQuantity(quantity);
				//hongXunDataTwos.get(0).setInstoreQuantity(quantity);
			}
			stockDao.save(hongXunDataOneBranchLeaf);
			return list;
		}else{
			return null;
		}*/
	}
	
/*	private void outstoreBranch(int purchaseNumID, int quantity, HongXunPurchaseItem item, List<Map<String,Object>> list){
		HongXunDataOneBranchLeaf hongXunDataOneBranchLeaf = new HongXunDataOneBranchLeaf();
		hongXunDataOneBranchLeaf.setUncleID(purchaseNumID);
		hongXunDataOneBranchLeaf.setStoreOutDate(new Date());
		hongXunDataOneBranchLeaf.setStoreOutQuantity(quantity);
		hongXunDataOneBranchLeaf.setHongXunDataTwo(item);
		stockDao.save(hongXunDataOneBranchLeaf);	
		Map<String,Object> map = new HashMap<String,Object>();
		//Set<HongXunDataOneBranchLeaf> setHongXunDataOneBranchLeaf = new HashSet<HongXunDataOneBranchLeaf>();
		//setHongXunDataOneBranchLeaf.add(hongXunDataOneBranchLeaf);
		//item.setHongXunDataOneBranchLeaf(setHongXunDataOneBranchLeaf);
		mapHongXunPurchaseItem(map, item);	
		mapHongXunDataOneBranchLeaf(map,hongXunDataOneBranchLeaf);
		list.add(map);
	}*/
	@Override
	public List<Map<String, Object>> getEntity() {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String,Object>();
		HongXunPurchaseItem e = new HongXunPurchaseItem();
		Class<? extends HongXunPurchaseItem> cls = e.getClass();  
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
	
	private void mapHongXunPurchaseItem(Map<String, Object> map, HongXunPurchaseItem item) {
		// TODO Auto-generated method stub	
		//System.out.println("mapHongXunPurchaseItem: ");
		map.put("idc", item.getIdc());
		map.put("materialNum", item.getMaterialNum());
		map.put("materialName", item.getMaterialName());
		map.put("specification", item.getSpecification());
		map.put("materialLotNum", item.getMaterialLotNum());
		map.put("materialRemark", item.getMaterialRemark());

		/*int value = item.getMaterialPlanQuantity();
		if(value == 0){
			map.put("materialPlanQuantity", "");
		}else{
			map.put("materialPlanQuantity", item.getMaterialPlanQuantity());
		}*/
		if(item.getDeliveryDate() != null){		
			map.put("deliveryDate", new SimpleDateFormat("yyyy-MM-dd").format(item.getDeliveryDate()));
		}
		
		if(item.getMaterialRealQuantity()!=null){
			map.put("materialRealQuantity", item.getMaterialRealQuantity());
		}
		if(item.getMaterialPurchaseQuantity()!=null){
			map.put("materialPurchaseQuantity", item.getMaterialPurchaseQuantity());
		}
		
		map.put("unit", item.getUnit());
		if(item.getPrice() != null){	
	  		map.put("price", item.getPrice().doubleValue());
	  	}
	  	if(item.getMaterialMoney() != null){
	  		map.put("materialMoney", item.getMaterialMoney().doubleValue());
	  	}
		map.put("supplier", item.getSupplier());	  	
		//map.put("materialRemark", item.getMaterialRemark());

		map.put("purchaseNumID", item.getPurchaseNumID());
		
		@SuppressWarnings("unchecked")
		List<HongXunMaterialStock> hongXunMaterialStocks = (List<HongXunMaterialStock>) stockDao.quarywithpara("HongXunMaterialStock", "materialNum", item.getMaterialNum()); 
    	if(hongXunMaterialStocks.size()==1){
    		map.put("storeQuantity", hongXunMaterialStocks.get(0).getQuantity());
    		map.put("inRoadQuantity", hongXunMaterialStocks.get(0).getInRoadQuantity());
    		map.put("itemQuantity", hongXunMaterialStocks.get(0).getItemQuantity());
    	}else if(hongXunMaterialStocks.size()>1){
    		System.out.println("HongXunMaterialStock 有重复料号出错");
    	}
    	/*
    	int materialInroadQuantity = 0;
		@SuppressWarnings("unchecked")
		List<HongXunPurchaseItem> hongXunPurchaseItems = (List<HongXunPurchaseItem>) stockDao.quarywithpara("HongXunPurchaseItem", "materialNum", item.getMaterialNum()); 
		System.out.println("HongXunPurchaseItem:" + hongXunPurchaseItems.size());
		for(HongXunPurchaseItem hongXunPurchaseItem: hongXunPurchaseItems){
			if(hongXunPurchaseItem.getIdc()!=item.getIdc()){
				if(hongXunPurchaseItem.getMaterialPurchaseQuantity()!=null && hongXunPurchaseItem.getMaterialRealQuantity() != null){			
					System.out.println("MaterialPurchaseQuantity:" + hongXunPurchaseItem.getMaterialPurchaseQuantity());
					System.out.println("MaterialRealQuantity:" + hongXunPurchaseItem.getMaterialRealQuantity());
					if(hongXunPurchaseItem.getMaterialPurchaseQuantity()>hongXunPurchaseItem.getMaterialRealQuantity()){
						materialInroadQuantity = materialInroadQuantity + (hongXunPurchaseItem.getMaterialPurchaseQuantity()-hongXunPurchaseItem.getMaterialRealQuantity());
					}
				}	
			}
		}  
		System.out.println("inroadQuantity:" + materialInroadQuantity);
		if(materialInroadQuantity>0){
			map.put("inroadQuantity", materialInroadQuantity); 
		}*/
		
    	/*List<HongXunPurchaseNum> hongXunPurchaseNumList = new ArrayList<HongXunPurchaseNum>();
    	HongXunPurchaseNum hongXunPurchaseNum = stockDao.purchaseNumFindById(item.getPurchaseNumID());
		@SuppressWarnings("unchecked")
		List<HongXunPurchaseNum> hongXunPurchaseNums = (List<HongXunPurchaseNum>) stockDao.quarywithpara("HongXunPurchaseNum", "purchaseStatus", "新增外购"); 
		for(HongXunPurchaseNum purchaseNum: hongXunPurchaseNums){
			if(!purchaseNum.getPurchaseNum().equals(hongXunPurchaseNum.getPurchaseNum())){
				hongXunPurchaseNumList.add(purchaseNum);
			}
		}
		@SuppressWarnings("unchecked")
		List<HongXunPurchaseNum> hongXunPurchaseNum1s = (List<HongXunPurchaseNum>) stockDao.quarywithpara("HongXunPurchaseNum", "purchaseStatus", "部分入库"); 
		for(HongXunPurchaseNum purchaseNum: hongXunPurchaseNum1s){
			if(!purchaseNum.getPurchaseNum().equals(hongXunPurchaseNum.getPurchaseNum())){
				hongXunPurchaseNumList.add(purchaseNum);
			}
		}
		System.out.println("hongXunPurchaseNumList:" + hongXunPurchaseNumList.size());
		int materialInroadQuantity = 0;
		for(HongXunPurchaseNum purchaseNumItem: hongXunPurchaseNumList){
			@SuppressWarnings("unchecked")
			List<HongXunPurchaseItem> hongXunPurchaseItems = (List<HongXunPurchaseItem>) stockDao.quarywithpara("HongXunPurchaseItem", "purchaseNumID", purchaseNumItem.getIdc()); 
			for(HongXunPurchaseItem hongXunPurchaseItem: hongXunPurchaseItems){
				if(hongXunPurchaseItem.getMaterialNum().equals(item.getMaterialNum()) && hongXunPurchaseItem.getMaterialPurchaseQuantity()!=null && hongXunPurchaseItem.getMaterialRealQuantity() != null){
					System.out.println("MaterialPurchaseQuantity:" + hongXunPurchaseItem.getMaterialPurchaseQuantity());
					System.out.println("MaterialRealQuantity:" + hongXunPurchaseItem.getMaterialRealQuantity());
					if(hongXunPurchaseItem.getMaterialPurchaseQuantity()>hongXunPurchaseItem.getMaterialRealQuantity()){
						materialInroadQuantity = materialInroadQuantity + (hongXunPurchaseItem.getMaterialPurchaseQuantity()-hongXunPurchaseItem.getMaterialRealQuantity());
					}
				}
			}
		}
		if(materialInroadQuantity>0){
			map.put("inroadQuantity", materialInroadQuantity); 
		}*/
		@SuppressWarnings("unchecked")
		List<HongXunPurchaseItemInStock> hongXunPurchaseItemInStocks = (List<HongXunPurchaseItemInStock>) stockDao.quarywithpara("HongXunPurchaseItemInStock", "purchaseItemID", item.getIdc()); 
		//Iterator<HongXunPurchaseItemInStock> iterator = item.getHongXunPurchaseItemInStock().iterator();
		HongXunPurchaseItemInStock hongXunPurchaseItemInStock = null;
		HongXunPurchaseItemInStock tmpHongXunPurchaseItemInStock = null;
		for(HongXunPurchaseItemInStock item1: hongXunPurchaseItemInStocks){
			tmpHongXunPurchaseItemInStock = item1;	
			if(hongXunPurchaseItemInStock == null){
				if(tmpHongXunPurchaseItemInStock.getInstoreDate() != null){
					hongXunPurchaseItemInStock = tmpHongXunPurchaseItemInStock;
				}
			}else{				
				if(hongXunPurchaseItemInStock.getInstoreDate().before(tmpHongXunPurchaseItemInStock.getInstoreDate())){
					hongXunPurchaseItemInStock = tmpHongXunPurchaseItemInStock;
				}
			}
		}
		/*while(iterator.hasNext()){
			tmpHongXunPurchaseItemInStock = iterator.next();		
			if(hongXunPurchaseItemInStock == null){
				if(tmpHongXunPurchaseItemInStock.getInstoreDate() != null){
					hongXunPurchaseItemInStock = tmpHongXunPurchaseItemInStock;
				}
			}else{				
				if(hongXunPurchaseItemInStock.getInstoreDate().before(tmpHongXunPurchaseItemInStock.getInstoreDate())){
					hongXunPurchaseItemInStock = tmpHongXunPurchaseItemInStock;
				}
			}
		}*/
		if(hongXunPurchaseItemInStock != null){
			map.put("instoreDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(hongXunPurchaseItemInStock.getInstoreDate()));
			map.put("lotNum", hongXunPurchaseItemInStock.getLotNum());
			map.put("remark", hongXunPurchaseItemInStock.getRemark());
		}
		
/*		Iterator<HongXunMaterialItemOutStock> iterator1 = item.getHongXunPurchaseItemOutStock().iterator();
		HongXunMaterialItemOutStock hongXunPurchaseItemOutStock = null;
		HongXunMaterialItemOutStock tmpHongXunPurchaseItemOutStock = null;
		while(iterator1.hasNext()){
			tmpHongXunPurchaseItemOutStock = iterator1.next();		
			if(hongXunPurchaseItemOutStock == null){
				if(tmpHongXunPurchaseItemOutStock.getOutStoreDate() != null){
					hongXunPurchaseItemOutStock = tmpHongXunPurchaseItemOutStock;
				}
			}else{				
				if(hongXunPurchaseItemOutStock.getOutStoreDate().before(tmpHongXunPurchaseItemOutStock.getOutStoreDate())){
					hongXunPurchaseItemOutStock = tmpHongXunPurchaseItemOutStock;
				}
			}
		}
		if(hongXunPurchaseItemOutStock != null){
			map.put("outStoreDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(hongXunPurchaseItemOutStock.getOutStoreDate()));
			map.put("staff", hongXunPurchaseItemOutStock.getStaff());
			map.put("unit", hongXunPurchaseItemOutStock.getUnit());
			map.put("quantity", hongXunPurchaseItemOutStock.getQuantity());
			map.put("lotNum", hongXunPurchaseItemOutStock.getLotNum());
			map.put("remark", hongXunPurchaseItemOutStock.getRemark());
		}*/
	}
/*	
	private void mapHongXunDataTwoFather(Map<String, Object> map, HongXunPurchaseItemInStock item) {
		// TODO Auto-generated method stub	
		map.put("mysidc", item.getIdc());
		if(item.getInstoreDate() != null){
			map.put("instoreDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(item.getInstoreDate()));
		}
		if(item.getInstoreQuantity() != null){
			map.put("instoreQuantity", item.getInstoreQuantity());
		}
		map.put("lotNum", item.getLotNum());
		map.put("remark", item.getRemark());
	}
*/
	@Override
	public List<Map<String, Object>> getPurchaseSheet(List<HongXunPurchaseNum> hongXunDataOneList) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		@SuppressWarnings("unchecked")
		List<HongXunPurchaseNum> hongXunPurchaseNums = (List<HongXunPurchaseNum>) stockDao.quarywithpara("HongXunPurchaseNum", "purchaseNum", hongXunDataOneList.get(0).getPurchaseNum());

		for (HongXunPurchaseNum hongXunPurchaseNum : hongXunPurchaseNums) {
			if(hongXunPurchaseNum.getPurchaseProcedure()!=null){
				String str = hongXunPurchaseNum.getPurchaseProcedure().replace("外购", "");
				if(hongXunDataOneList.get(0).getPurchaseProcedure().indexOf(str)>-1){
			    	@SuppressWarnings("unchecked")
					List<HongXunPurchaseItem> hongXunPurchaseItems = (List<HongXunPurchaseItem>) stockDao.quarywithpara("HongXunPurchaseItem", "purchaseNumID", hongXunPurchaseNum.getIdc()); 					
					//System.out.println("list.size:" + list.size());
			    	for(HongXunPurchaseItem item : hongXunPurchaseItems){
						Map<String,Object> map = new HashMap<String,Object>();
						mapHongXunPurchaseItem(map, item);
						list.add(map);
			    	}
					break;
				}
			}
		}
		
		return list;
	}

	@Override
	public List<Map<String, Object>> movePurchaseItem(int id) {
		// TODO Auto-generated method stub
		//List<HongXunMaterialStock> hongXunMaterialStockList = new ArrayList<HongXunMaterialStock>();
		//List<HongXunPurchaseItem> hongXunPurchaseItemList = new ArrayList<HongXunPurchaseItem>();
		HongXunPurchaseItem hongXunPurchaseItem = iPurchaseItemDao.purchaseItemFindById(id);
		if(hongXunPurchaseItem.getPurchaseDeItemID() != null){
			HongXunPurchaseDeItem hongXunPurchaseDeItem = iPurchaseDeItemDao.purchaseDeItemFindById(hongXunPurchaseItem.getPurchaseDeItemID());
			if(hongXunPurchaseDeItem != null){
				hongXunPurchaseDeItem.setStatus("已提单");
				hongXunPurchaseDeItem.setPurchaseId(null);
				HongXunMaterialStock hongXunMaterialStock = hongXunPurchaseDeItem.getHongXunMaterialStock();
				//System.out.println("终于进来了");
				if(hongXunMaterialStock.getInRoadQuantity()==null){
					hongXunMaterialStock.setInRoadQuantity(0);
				}
				hongXunMaterialStock.setInRoadQuantity(hongXunMaterialStock.getInRoadQuantity()-hongXunPurchaseItem.getMaterialPurchaseQuantity());			
	    		if(hongXunMaterialStock.getQuantity()-hongXunMaterialStock.getItemQuantity() + hongXunMaterialStock.getInRoadQuantity() < hongXunMaterialStock.getSafeQuantity()){
	    			hongXunMaterialStock.setAlarm("报警");
	    		}
	    		iPurchaseDeItemDao.update(hongXunPurchaseDeItem);
	    		iMaterialStockDao.update(hongXunMaterialStock);
	    		iPurchaseDeItemDao.deletRow(hongXunPurchaseItem);
	    		//hongXunMaterialStockList.add(hongXunMaterialStock);
	    		//hongXunPurchaseItemList.add(hongXunPurchaseItem);
			}
		}else{
			System.out.println("无库信息了");
		}
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
/*		for(HongXunMaterialStock item: hongXunMaterialStockList){
			stockDao.update(item);
		}
		for(HongXunPurchaseItem item: hongXunPurchaseItemList){
			stockDao.deletRow(item);
		}*/
		return list;
	}




	
/*	public void mapHongXunDataOneBranchLeaf(Map<String, Object> map, HongXunDataOneBranchLeaf item) {

		
			map.put("storeOutDate", item.getStoreOutDate());
			map.put("storeOutQuantity", item.getStoreOutQuantity());
		
	}*/


}
