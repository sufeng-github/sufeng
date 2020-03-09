package paul.sydney.service.materialstock.impl;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import paul.sydney.dao.IMaterialItemOutStockDao;
import paul.sydney.dao.IMaterialOutStockItemDao;
import paul.sydney.dao.IMaterialStockDao;
import paul.sydney.dao.StockDao;
import paul.sydney.model.HongXunMaterialItemOutStock;
import paul.sydney.model.HongXunMaterialOutStoreItem;
import paul.sydney.model.HongXunMaterialStock;
import paul.sydney.model.HongXunPurchaseItem;
import paul.sydney.model.HongXunPurchaseItemInStock;
import paul.sydney.service.materialstock.ServiceStoreOutItemInf;

@Transactional
@Service("serviceStoreOutItem")
public class ServiceStoreOutItemImpl implements ServiceStoreOutItemInf{
	
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
	IMaterialOutStockItemDao iMaterialOutStockItemDao;
	public void setStockDao(IMaterialOutStockItemDao iMaterialOutStockItemDao) {
		this.iMaterialOutStockItemDao = iMaterialOutStockItemDao;
	}
	@Autowired
	IMaterialItemOutStockDao iMaterialItemOutStockDao;
	public void setStockDao(IMaterialItemOutStockDao iMaterialItemOutStockDao) {
		this.iMaterialItemOutStockDao = iMaterialItemOutStockDao;
	}
	@Override
	public List<Map<String, Object>> loadData() {
		// TODO Auto-generated method stub
		//System.out.println("serviceBranchoneload");
		HongXunMaterialOutStoreItem hongXunMaterialOutStoreItem = new HongXunMaterialOutStoreItem();
		List<HongXunMaterialOutStoreItem> hongXunMaterialOutStoreItems = iMaterialOutStockItemDao.quary(hongXunMaterialOutStoreItem);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		for(HongXunMaterialOutStoreItem item : hongXunMaterialOutStoreItems){
			Map<String,Object> map = new HashMap<String,Object>();
			//System.out.println(itemSun.getIdc());
			mapHongXunOutStoreItem(map, item);
			list.add(map);
		}		
		return list;		
	}
	
	
	@Override
	public List<Map<String, Object>> saveRow(HongXunMaterialOutStoreItem hongXunMaterialOutStoreItem) {
		// TODO Auto-generated method stub
		iMaterialOutStockItemDao.save(hongXunMaterialOutStoreItem);
		Map<String,Object> map = new HashMap<String,Object>();

		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.add(map);
		return list;
	}
	
	@Override
	public List<Map<String, Object>> deleteRow(int ID) {
		// TODO Auto-generated method stub
		HongXunMaterialOutStoreItem hongXunMaterialOutStoreItem = iMaterialOutStockItemDao.materialOutStoreFindById(ID);
		iMaterialOutStockItemDao.delete(hongXunMaterialOutStoreItem);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		return list;
	}
	
	@Override
	public List<Map<String, Object>> updateRow(HongXunMaterialOutStoreItem hongXunMaterialOutStoreItem) {
		iMaterialOutStockItemDao.update(hongXunMaterialOutStoreItem);
		Map<String,Object> map = new HashMap<String,Object>();
		//map.put("materialMoney", hongXunMaterialOutStoreItem.getMaterialMoney());	
		//map.put("instoreDate", hongXunMaterialOutStoreItem.getInstoreDate());
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.add(map);
		return list;
	}

	@Override
	public List<HongXunMaterialOutStoreItem> quary(HongXunMaterialOutStoreItem hongXunMaterialOutStoreItem) {
		// TODO Auto-generated method stub
		List<HongXunMaterialOutStoreItem> list = iMaterialOutStockItemDao.quary(hongXunMaterialOutStoreItem);
		return list;
	}

/*	@Override
	public List<Map<String, Object>> searchMaterialNum(int outStoreNumID, String materialNum) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		HongXunMaterialOutStoreItem hongXunMaterialOutStoreItemEntity = new HongXunMaterialOutStoreItem();
		hongXunMaterialOutStoreItemEntity.setOutStoreNumID(outStoreNumID);
		List<HongXunMaterialOutStoreItem> hongXunMaterialOutStoreItems = stockDao.quary(hongXunMaterialOutStoreItemEntity);//(List<HongXunMaterialOutStoreItem>) stockDao.quarywithpara("HongXunMaterialOutStoreItem", "outStoreNumID", outStoreNumID); 
    	for(HongXunMaterialOutStoreItem item : hongXunMaterialOutStoreItems){ //查看工单具体项
			if(item.getMaterialNum().equals(materialNum)){			
				Map<String, Object> map = new HashMap<String, Object>();
				mapHongXunOutStoreItem(map, item);
				list.add(map);
			}
		}
		return list;
	}*/
	
	@Override
	public List<Map<String, Object>> outstoreMaterialNum(int id, int quantity) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		HongXunMaterialStock hongXunMaterialStock = null;
		HongXunMaterialItemOutStock hongXunMaterialItemOutStock = null;
		//HongXunMaterialOutStoreItem hongXunMaterialOutStoreItem = null;
		//HongXunMaterialOutStoreItem hongXunMaterialOutStoreItemEntity = new HongXunMaterialOutStoreItem();
		//hongXunMaterialOutStoreItemEntity.setOutStoreNumID(outStoreNumID);
		HongXunMaterialOutStoreItem hongXunMaterialOutStoreItem = iMaterialOutStockItemDao.materialOutStoreFindById(id);
    	//@SuppressWarnings("unchecked")
    	//List<HongXunMaterialOutStoreItem> hongXunMaterialOutStoreItems = stockDao.quary(hongXunMaterialOutStoreItemEntity);//(List<HongXunMaterialOutStoreItem>) stockDao.quarywithpara("HongXunMaterialOutStoreItem", "outStoreNumID", outStoreNumID); 
    	//for(HongXunMaterialOutStoreItem item : hongXunMaterialOutStoreItems){ //查看工单具体项
			//if(item.getMaterialNum().equals(materialNum)){	
				if(hongXunMaterialOutStoreItem.getStoreOutRealQuantity()==null){
					hongXunMaterialOutStoreItem.setStoreOutRealQuantity(0);
				}
/*				if(item.getStoreOutPlanQuantity()-item.getStoreOutRealQuantity() < quantity){
		    		Map<String, Object> map = new HashMap<String, Object>();
					map.put("error", "出库单最多可出库数量:"+(item.getStoreOutPlanQuantity()-item.getStoreOutRealQuantity()));
					list.add(map);
				}else{*/
					HongXunMaterialStock hongXunMaterialStockEntity = new HongXunMaterialStock();
					hongXunMaterialStockEntity.setMaterialNum(hongXunMaterialOutStoreItem.getMaterialNum());
					//@SuppressWarnings("unchecked")
					List<HongXunMaterialStock> hongXunMaterialStocks = iMaterialStockDao.quary(hongXunMaterialStockEntity);//(List<HongXunMaterialStock>) stockDao.quarywithpara("HongXunMaterialStock", "materialNum", materialNum); 
			    	if(hongXunMaterialStocks.size()==1){
			    		if(hongXunMaterialStocks.get(0).getQuantity() != null){
			    			int storeQuantity = hongXunMaterialStocks.get(0).getQuantity() - quantity;
			    			if(storeQuantity>-1){
			    				hongXunMaterialStocks.get(0).setQuantity(storeQuantity);
			    				if(hongXunMaterialStocks.get(0).getItemQuantity()==null){
			    					hongXunMaterialStocks.get(0).setItemQuantity(0);
			    					/*System.out.println("报错：扫描出库，项目数为空");
			    					Map<String, Object> map = new HashMap<String, Object>();
			    					map.put("error", "物料号:"+ materialNum +"->项目数为空");
			    					list.add(map);
			    					return list;*/
			    				}/*else{	
			    					if(quantity <= (item.getStoreOutPlanQuantity() - item.getStoreOutRealQuantity())){
			    						hongXunMaterialStocks.get(0).setItemQuantity(hongXunMaterialStocks.get(0).getItemQuantity()-quantity);
			    					}else{
			    						hongXunMaterialStocks.get(0).setItemQuantity(hongXunMaterialStocks.get(0).getItemQuantity()-(item.getStoreOutPlanQuantity() - item.getStoreOutRealQuantity()));
			    					}
			    				}*/
			    				hongXunMaterialStocks.get(0).setItemQuantity(hongXunMaterialStocks.get(0).getItemQuantity()-quantity);
			    				//stockDao.update(hongXunMaterialStocks.get(0));  	
			    				hongXunMaterialStock=hongXunMaterialStocks.get(0);
			    				hongXunMaterialItemOutStock = new HongXunMaterialItemOutStock();
/*			    	    		boolean flag = false;
			    				@SuppressWarnings("unchecked")
								List<HongXunMaterialItemOutStock> hongXunMaterialItemOutStocks = (List<HongXunMaterialItemOutStock>) stockDao.quarywithpara("HongXunMaterialItemOutStock", "outStoreItemID", item.getIdc());
			    	    		if(hongXunMaterialItemOutStocks.size()>0){	
			    	    			hongXunMaterialItemOutStock = hongXunMaterialItemOutStocks.get(hongXunMaterialItemOutStocks.size() - 1);
			    	    			if(compareTwoTime(new Date(), hongXunMaterialItemOutStock.getDate(),180000)){//180000 三分钟
			    	    				hongXunMaterialItemOutStock.setQuantity(hongXunMaterialItemOutStock.getQuantity()+quantity);
			    	    				stockDao.update(hongXunMaterialItemOutStock);
			    	    				flag = true;
			    	    				Map<String, Object> map = new HashMap<String, Object>();
					    				mapHongXunMaterialItemOutStock(map,hongXunMaterialItemOutStock);
					        			map.put("updateQuantity", hongXunMaterialItemOutStock.getQuantity());					        			
					    				list.add(map);
			    	    			}
			    	    		}
			    	    		if(flag == false){*/
				    	    		hongXunMaterialItemOutStock.setDate(new Date());
				    				//if(hongXunMaterialItemOutStock.getQuantity() == null){
				    					hongXunMaterialItemOutStock.setQuantity(quantity);
				    				//}else{
				    				//	hongXunMaterialItemOutStock.setQuantity(hongXunMaterialItemOutStock.getQuantity() + quantity);
				    				//}	    				
				    				hongXunMaterialItemOutStock.setOutStoreItemID(hongXunMaterialOutStoreItem.getIdc());
				    				hongXunMaterialItemOutStock.setMaterialStockID(hongXunMaterialStock.getIdc());
				    				//stockDao.save(hongXunMaterialItemOutStock);
				    				
				    			/*	Map<String, Object> map = new HashMap<String, Object>();
				    				mapHongXunMaterialItemOutStock(map,hongXunMaterialItemOutStock);
				        			map.put("materialNum", item.getMaterialNum());
				        			map.put("specification", item.getSpecification());
				    				list.add(map);*/
			    	    		//}
				    			hongXunMaterialOutStoreItem.setStoreOutDate(new Date());			    	
				    			hongXunMaterialOutStoreItem.setStoreOutRealQuantity(hongXunMaterialOutStoreItem.getStoreOutRealQuantity()+quantity);
			    				//stockDao.update(item);
			    				//hongXunMaterialOutStoreItem = item;
			    				//break;
			    			}else{
			    				Map<String, Object> map = new HashMap<String, Object>();
								map.put("error", "物料号:"+ hongXunMaterialOutStoreItem.getMaterialNum() +"->库存不足,库存数量：" + hongXunMaterialStocks.get(0).getQuantity());
								list.add(map);
			    				System.out.println("库存不足");
			    				return list;
			    			}
			    		}
			    	}else if(hongXunMaterialStocks.size()==0){
			    		Map<String, Object> map = new HashMap<String, Object>();
						map.put("error", "物料号:"+ hongXunMaterialOutStoreItem.getMaterialNum() +"->无库存记录");
						list.add(map);
						return list;
			    	}else{
			    		//System.out.println("HongXunMaterialStock 有重复料号出错");
			    		Map<String, Object> map = new HashMap<String, Object>();
						map.put("error", "物料号:"+ hongXunMaterialOutStoreItem.getMaterialNum() +"->有重复料号，请联系工程师");
						list.add(map);
						return list;
			    	}
				//}	
			//}
		//}
    	if(hongXunMaterialStock != null && hongXunMaterialItemOutStock != null && hongXunMaterialOutStoreItem != null ){
    		iMaterialStockDao.update(hongXunMaterialStock); 
    		iMaterialItemOutStockDao.save(hongXunMaterialItemOutStock);
    		iMaterialOutStockItemDao.update(hongXunMaterialOutStoreItem);
			Map<String, Object> map = new HashMap<String, Object>();
			//mapHongXunMaterialItemOutStock(map,hongXunMaterialItemOutStock);
			mapHongXunOutStoreItem(map, hongXunMaterialOutStoreItem);
			list.add(map);
    	}else{
    		Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", "物料号:"+ hongXunMaterialOutStoreItem.getMaterialNum() +"->出库单中无记录");
			list.add(map);		
    	}
		return list;
	}

	@Override
	public List<Map<String, Object>> getEntity() {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String,Object>();
		HongXunMaterialOutStoreItem e = new HongXunMaterialOutStoreItem();
		Class<? extends HongXunMaterialOutStoreItem> cls = e.getClass();  
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
	
	private void mapHongXunOutStoreItem(Map<String, Object> map, HongXunMaterialOutStoreItem item) {
		// TODO Auto-generated method stub	
		//System.out.println("mapHongXunPurchaseItem: ");
		map.put("idc", item.getIdc());
		map.put("materialNum", item.getMaterialNum());
		map.put("specification", item.getSpecification());
		map.put("storeOutPlanQuantity", item.getStoreOutPlanQuantity());
		map.put("storeOutRealQuantity", item.getStoreOutRealQuantity());
		map.put("staff", item.getStaff());
		map.put("state", item.getState());
		if(item.getStoreOutDate() != null){
			map.put("storeOutDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(item.getStoreOutDate()));
		}
		map.put("unit", item.getUnit());
		map.put("materialLotNum", item.getMaterialLotNum());
		map.put("attribute", item.getAttribute());
		map.put("outStoreNumID", item.getOutStoreNumID());
		map.put("remark", item.getRemark());
		@SuppressWarnings("unchecked")
		List<HongXunMaterialStock> hongXunMaterialStocks = (List<HongXunMaterialStock>) stockDao.quarywithpara("HongXunMaterialStock", "materialNum", item.getMaterialNum()); 
    	if(hongXunMaterialStocks.size()==1){
    		map.put("storeQuantity", hongXunMaterialStocks.get(0).getQuantity());
    		map.put("safeQuantity", hongXunMaterialStocks.get(0).getSafeQuantity());   		
    	}else if(hongXunMaterialStocks.size()>1){
    		System.out.println("HongXunMaterialStock 有重复料号出错");
    	}
		
	}


	@Override
	public List<Map<String, Object>> getOutStoreItems(int outStoreNumID) {
		// TODO Auto-generated method stub
    	@SuppressWarnings("unchecked")
		List<HongXunMaterialOutStoreItem> hongXunMaterialOutStoreItems = (List<HongXunMaterialOutStoreItem>) stockDao.quarywithpara("HongXunMaterialOutStoreItem", "outStoreNumID", outStoreNumID); 
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		//System.out.println("list.size:" + list.size());
    	for(HongXunMaterialOutStoreItem item : hongXunMaterialOutStoreItems){
			Map<String,Object> map = new HashMap<String,Object>();
			mapHongXunOutStoreItem(map, item);
			list.add(map);
    	}
		return list;
	}
/*
	@Override
	public List<Map<String, Object>> autotimp(String q, Integer outStoreNumID) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		//List<String> listName = new ArrayList<String>();
		HongXunMaterialOutStoreItem hongXunMaterialOutStoreItem = new HongXunMaterialOutStoreItem();
		hongXunMaterialOutStoreItem.setOutStoreNumID(outStoreNumID);
		List<HongXunMaterialOutStoreItem> hongXunMaterialOutStoreItems = stockDao.quary(hongXunMaterialOutStoreItem); 	
		for(HongXunMaterialOutStoreItem item: hongXunMaterialOutStoreItems){
			if(item.getMaterialNum().indexOf(q)>-1){
				Map<String,Object> map = new HashMap<String,Object>();
				//map.put("materialName", item.getMaterialName());	
				map.put("name", item.getMaterialNum());
				map.put("id", item.getIdc());  
				list.add(map);
			}
		}
		return list;
	}*/




}
