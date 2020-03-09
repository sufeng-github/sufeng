package paul.sydney.service.purchase.impl;
import org.springframework.transaction.annotation.Transactional;

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

import paul.sydney.dao.IMaterialStockDao;
import paul.sydney.dao.IPurchaseDao;
import paul.sydney.dao.IPurchaseDeItemDao;
import paul.sydney.dao.IPurchaseItemDao;
import paul.sydney.dao.IPurchaseItemInStockDao;
import paul.sydney.dao.StockDao;
import paul.sydney.model.HongXunMaterialStock;
import paul.sydney.model.HongXunPurchaseDeItem;
import paul.sydney.model.HongXunPurchaseItem;
import paul.sydney.model.HongXunPurchaseItemInStock;
import paul.sydney.service.purchase.ServicePurchaseItemInStockInf;
@Transactional
@Service("servicePurchaseItemInStock")
public class ServicePurchaseItemInStockImpl implements ServicePurchaseItemInStockInf{
	
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
	IMaterialStockDao iMaterialStockDao;
	public void setStockDao(IMaterialStockDao iMaterialStockDao) {
		this.iMaterialStockDao = iMaterialStockDao;
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
	IPurchaseItemInStockDao iPurchaseItemInStockDao;
	public void setStockDao(IPurchaseItemInStockDao iPurchaseItemInStockDao) {
		this.iPurchaseItemInStockDao = iPurchaseItemInStockDao;
	}
	@Override
	public List<Map<String, Object>> loadData() {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		List<HongXunPurchaseItemInStock>  hongXunPurchaseItemInStocks = iPurchaseItemInStockDao.quary(new HongXunPurchaseItemInStock());
		for(HongXunPurchaseItemInStock item : hongXunPurchaseItemInStocks){
			Map<String,Object> map = new HashMap<String,Object>();
		
			//mapHongXunPurchaseItem(map,item.getHongXunPurchaseItem());
			mapHongXunPurchaseItemInStock(map,item);
			list.add(map);
		}
		
		return list;
	}
	
	@Override
	public List<Map<String, Object>> getPurchaseItemsInStore(int purchaseNumID) {
		// TODO Auto-generated method stub
    	@SuppressWarnings("unchecked")
		List<HongXunPurchaseItem> hongXunPurchaseItems = (List<HongXunPurchaseItem>) stockDao.quarywithpara("HongXunPurchaseItem", "purchaseNumID", purchaseNumID); 
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		//System.out.println("list.size:" + list.size());
    	for(HongXunPurchaseItem item : hongXunPurchaseItems){
    		@SuppressWarnings("unchecked")
    		List<HongXunPurchaseItemInStock> hongXunPurchaseItemInStocks = (List<HongXunPurchaseItemInStock>) stockDao.quarywithpara("HongXunPurchaseItemInStock", "purchaseItemID", item.getIdc()); 
    		for(HongXunPurchaseItemInStock item1: hongXunPurchaseItemInStocks){
    			//hongXunPurchaseItemInStock = iterator.next();
    			Map<String,Object> map = new HashMap<String,Object>();
    			//mapHongXunPurchaseItem(map, item);
    			mapHongXunPurchaseItemInStock(map, item1);
    			list.add(map);
    		}
    	}
		return list;
	}


/*	@Override
	public List<Map<String, Object>> saveRow(HongXunPurchaseItemInStock hongXunPurchaseItemInStock) {
		// TODO Auto-generated method stub
		//System.out.println("serviceoneloadSaveRow");	
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		HongXunPurchaseItem hongXunPurchaseItem = stockDao.purchaseItemFindById(hongXunPurchaseItemInStock.getIdc());
		//hongXunPurchaseItemInStock.setHongXunPurchaseItem(hongXunPurchaseItem);
		hongXunPurchaseItemInStock.setInstoreDate(new Date());
		stockDao.update(hongXunPurchaseItem);
		stockDao.save(hongXunPurchaseItemInStock);
		
    	@SuppressWarnings("unchecked")
		List<HongXunMaterialStock> hongXunMaterialStocks = (List<HongXunMaterialStock>) stockDao.quarywithpara("HongXunMaterialStock", "materialNum", hongXunPurchaseItem.getMaterialNum()); 
    	if(hongXunMaterialStocks.size()==1){
    		if(hongXunMaterialStocks.get(0).getQuantity() != null){
    			//hongXunMaterialStocks.get(0).setQuantity(hongXunMaterialStocks.get(0).getQuantity() + hongXunPurchaseItemInStock.getInstoreQuantity());
    		}else{
    			//hongXunMaterialStocks.get(0).setQuantity(hongXunPurchaseItemInStock.getInstoreQuantity());
    		}
    		stockDao.update(hongXunMaterialStocks.get(0));
    	}else if(hongXunMaterialStocks.size()>1){
    		System.out.println("HongXunMaterialStock 有重复料号出错");
    	}else{
    		HongXunMaterialStock hongXunMaterialStock = new HongXunMaterialStock();
    		hongXunMaterialStock.setMaterialNum(hongXunPurchaseItem.getMaterialNum());
    		//hongXunMaterialStock.setQuantity(hongXunPurchaseItemInStock.getInstoreQuantity());
    		hongXunMaterialStock.setSpecification(hongXunPurchaseItem.getSpecification());
    		hongXunMaterialStock.setUnit(hongXunPurchaseItem.getUnit());
    		stockDao.save(hongXunMaterialStock);
    	}
		Map<String,Object> map = new HashMap<String,Object>();
		//mapHongXunPurchaseItem(map,hongXunPurchaseItem);
		mapHongXunPurchaseItemInStock(map,hongXunPurchaseItemInStock);

		list.add(map);
		return list;
	}*/
	
	
/*	private void mapHongXunPurchaseItem(Map<String, Object> map, HongXunPurchaseItem item) {
		// TODO Auto-generated method stub	
		//System.out.println("test: " + item.getHongXunDataOne().getProductionOrderNum());
		map.put("idc", item.getIdc());
		map.put("materialNum", item.getMaterialNum());
		map.put("materialName", item.getMaterialName());
		map.put("materialSpecification", item.getSpecification());
		//map.put("materialModel", item.getMaterialModel());
		map.put("materialLotNum", item.getMaterialLotNum());

		
		if(item.getMaterialRealQuantity()!=null){
			map.put("materialRealQuantity", item.getMaterialRealQuantity());
		}

		map.put("materialUnit", item.getUnit());

	  	if(item.getPrice() != null){
	  		map.put("materialPrice", item.getPrice().doubleValue());
	  	}
	  	if(item.getMaterialMoney() != null){
	  		map.put("materialMoney", item.getMaterialMoney().doubleValue());
	  	}
		map.put("materialsupplier", item.getSupplier());	  	
		map.put("materialRemark", item.getMaterialRemark());
		map.put("uncleID", item.getPurchaseNumID());
	}*/
	
	private void mapHongXunPurchaseItemInStock(Map<String, Object> map, HongXunPurchaseItemInStock item) {
		// TODO Auto-generated method stub	
		//map.put("mysidc", item.getIdc());
		if(item.getInstoreDate() != null){
			map.put("instoreDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(item.getInstoreDate()));
		}	
		//map.put("instoreQuantity", item.getInstoreQuantity());
		map.put("realQuantity", item.getRealQuantity());
		map.put("lotNum", item.getLotNum());
		map.put("remark", item.getRemark());
		map.put("idc", item.getIdc());
		map.put("purchaseItemID", item.getPurchaseItemID());
		HongXunPurchaseItem hongXunPurchaseItem = iPurchaseItemDao.purchaseItemFindById(item.getPurchaseItemID());
		if(hongXunPurchaseItem != null){
			map.put("materialNum", hongXunPurchaseItem.getMaterialNum());
			map.put("materialSpecification", hongXunPurchaseItem.getSpecification());
			map.put("materialUnit", hongXunPurchaseItem.getUnit());
			map.put("materialUnit", hongXunPurchaseItem.getUnit());
		}
	}

	@Override
	public List<Map<String, Object>> instoreMaterialNum(String materialNum, int purchaseNumID, int quantity) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		HongXunPurchaseItemInStock hongXunPurchaseItemInStock = new HongXunPurchaseItemInStock();
		HongXunPurchaseItem hongXunPurchaseItem = new HongXunPurchaseItem();
		hongXunPurchaseItem.setPurchaseNumID(purchaseNumID);		
		List<HongXunPurchaseItem> hongXunPurchaseItems = iPurchaseItemDao.quary(hongXunPurchaseItem);
		for(HongXunPurchaseItem item: hongXunPurchaseItems){	
			if(item.getMaterialNum().equals(materialNum)){
				if(item.getMaterialPurchaseQuantity() != null){
					if(item.getMaterialRealQuantity() == null){
						if(quantity > item.getMaterialPurchaseQuantity()){
							//System.out.println("入库数量大于采购数量");
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("error", "物料号:"+ materialNum +"->入库数量大于采购数量");
							list.add(map);
							return list;
						}
					}else{
						if(quantity > item.getMaterialPurchaseQuantity()-item.getMaterialRealQuantity()){
							//System.out.println("入库数量大于采购数量...");
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("error", "物料号:"+ materialNum +"->入库数量大于采购数量...");
							list.add(map);
							return list;
						}
					}
				}
				hongXunPurchaseItemInStock.setPurchaseItemID(item.getIdc()); //填充入库信息
				hongXunPurchaseItemInStock.setInstoreDate(new Date());
				hongXunPurchaseItemInStock.setRealQuantity(quantity);
				iPurchaseItemInStockDao.save(hongXunPurchaseItemInStock);
				if(item.getMaterialRealQuantity() == null){		//修改采购入库数量
					item.setMaterialRealQuantity(hongXunPurchaseItemInStock.getRealQuantity());
				}else{
					item.setMaterialRealQuantity(item.getMaterialRealQuantity() + hongXunPurchaseItemInStock.getRealQuantity());
				}
				iPurchaseItemDao.update(item);
				if(item.getPurchaseDeItemID() != null){
				//	System.out.println(item.getPurchaseDeItemID());
					HongXunPurchaseDeItem hongXunPurchaseDeItem = iPurchaseDeItemDao.purchaseDeItemFindById(item.getPurchaseDeItemID());
					if(hongXunPurchaseDeItem != null){
						HongXunMaterialStock hongXunMaterialStock = hongXunPurchaseDeItem.getHongXunMaterialStock(); 
			    		if(hongXunMaterialStock.getQuantity() != null){
			    			hongXunMaterialStock.setQuantity(hongXunMaterialStock.getQuantity() + hongXunPurchaseItemInStock.getRealQuantity());		    			
			    		}else{
			    			hongXunMaterialStock.setQuantity(hongXunPurchaseItemInStock.getRealQuantity());
			    		}
			    		if(hongXunMaterialStock.getInQuantity() != null){
			    			hongXunMaterialStock.setInQuantity(hongXunMaterialStock.getInQuantity() + hongXunPurchaseItemInStock.getRealQuantity());		    			
			    		}else{
			    			hongXunMaterialStock.setInQuantity(hongXunPurchaseItemInStock.getRealQuantity());
			    		}
			    		
			    		if(hongXunMaterialStock.getInRoadQuantity() != null){
			    			hongXunMaterialStock.setInRoadQuantity(hongXunMaterialStock.getInRoadQuantity()-hongXunPurchaseItemInStock.getRealQuantity());
			    		}
			    		
			    		iMaterialStockDao.update(hongXunMaterialStock);
					}else{
						HongXunMaterialStock hongXunMaterialStock = new HongXunMaterialStock(); 
			    		hongXunMaterialStock.setMaterialNum(item.getMaterialNum());
			    		hongXunMaterialStock.setQuantity(hongXunPurchaseItemInStock.getRealQuantity());
			    		hongXunMaterialStock.setInQuantity(hongXunPurchaseItemInStock.getRealQuantity());
			    		hongXunMaterialStock.setSpecification(item.getSpecification());
			    		hongXunMaterialStock.setUnit(item.getUnit());
			    		iMaterialStockDao.save(hongXunMaterialStock);
					}
					break;
				}	
				/*if(item.getMaterialID()==null){
					HongXunMaterialStock hongXunMaterialStock = new HongXunMaterialStock(); 
		    		hongXunMaterialStock.setMaterialNum(item.getMaterialNum());
		    		hongXunMaterialStock.setQuantity(hongXunPurchaseItemInStock.getRealQuantity());
		    		hongXunMaterialStock.setInQuantity(hongXunPurchaseItemInStock.getRealQuantity());
		    		hongXunMaterialStock.setSpecification(item.getSpecification());
		    		hongXunMaterialStock.setUnit(item.getUnit());
		    		stockDao.save(hongXunMaterialStock);
		    	}else {
		    		HongXunMaterialStock hongXunMaterialStock = stockDao.hongXunMaterialFindById(item.getMaterialID()); 
		    		if(hongXunMaterialStock.getQuantity() != null){
		    			hongXunMaterialStock.setQuantity(hongXunMaterialStock.getQuantity() + hongXunPurchaseItemInStock.getRealQuantity());		    			
		    		}else{
		    			hongXunMaterialStock.setQuantity(hongXunPurchaseItemInStock.getRealQuantity());
		    		}
		    		if(hongXunMaterialStock.getInQuantity() != null){
		    			hongXunMaterialStock.setInQuantity(hongXunMaterialStock.getInQuantity() + hongXunPurchaseItemInStock.getRealQuantity());		    			
		    		}else{
		    			hongXunMaterialStock.setInQuantity(hongXunPurchaseItemInStock.getRealQuantity());
		    		}
		    		
		    		if(hongXunMaterialStock.getInRoadQuantity() != null){
		    			hongXunMaterialStock.setInRoadQuantity(hongXunMaterialStock.getInRoadQuantity()-hongXunPurchaseItemInStock.getRealQuantity());
		    		}
		    		
		    		stockDao.update(hongXunMaterialStock);
		    	}			*/	
				
			}		
		}
		Map<String, Object> map = new HashMap<String, Object>();
		mapHongXunPurchaseItemInStock(map,hongXunPurchaseItemInStock);		
		list.add(map);
		return list;
	}

	@Override
	public List<Map<String, Object>> autotimp(String q, int purchaseNumID) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    	@SuppressWarnings("unchecked")
		List<HongXunPurchaseItem> hongXunPurchaseItems = (List<HongXunPurchaseItem>) stockDao.quarywithpara("HongXunPurchaseItem", "purchaseNumID", purchaseNumID); 
    	
		//List<HongXunPurchaseItemInStock> hongXunPurchaseItemInStocks = stockDao.quary(new HongXunPurchaseItemInStock());	
		for(HongXunPurchaseItem item: hongXunPurchaseItems){	
			if(item.getMaterialNum().indexOf(q)>-1){
				Map<String,Object> map = new HashMap<String,Object>();
				//map.put("materialName", item.getMaterialName());	
				map.put("name", item.getMaterialNum());
				map.put("id", item.getIdc());  
				list.add(map);
			}
		}
		return list;
	}

	@Override
	public Collection<? extends Map<String, Object>> saveRow(HongXunPurchaseItemInStock item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<? extends Map<String, Object>> updateRow(HongXunPurchaseItemInStock item) {
		// TODO Auto-generated method stub
		HongXunPurchaseItem hongXunPurchaseItem = iPurchaseItemDao.purchaseItemFindById(item.getPurchaseItemID());		
		HongXunMaterialStock hongXunMaterialStock = new HongXunMaterialStock();
		hongXunMaterialStock.setMaterialNum(hongXunPurchaseItem.getMaterialNum());
		List<HongXunMaterialStock> hongXunMaterialStocks = iMaterialStockDao.quary(hongXunMaterialStock);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		if(hongXunMaterialStocks.size()==1){
			HongXunPurchaseItemInStock hongXunPurchaseItemInStock = iPurchaseItemInStockDao.purchaseItemInStockFindById(item.getIdc());	
			long diff = new Date().getTime() - hongXunPurchaseItemInStock.getInstoreDate().getTime();
			// 计算差多少天
			long nd = 1000 * 24 * 60 * 60;
			long nh = 1000 * 60 * 60;
			long nm = 1000 * 60;
			//long day = diff / nd;
			// 计算差多少小时
			//long hour = diff % nd / nh;
			// 计算差多少分钟
			long min = diff % nd % nh / nm;
			
			
			//System.out.println("min:" + min);
			if(min<30){	
				hongXunMaterialStocks.get(0).setQuantity(hongXunMaterialStocks.get(0).getQuantity() - (hongXunPurchaseItemInStock.getRealQuantity()-item.getRealQuantity()));
				hongXunMaterialStocks.get(0).setInQuantity(hongXunMaterialStocks.get(0).getInQuantity() - (hongXunPurchaseItemInStock.getRealQuantity()-item.getRealQuantity()));
				if(hongXunMaterialStocks.get(0).getInRoadQuantity() != null){
					hongXunMaterialStocks.get(0).setInRoadQuantity(hongXunMaterialStocks.get(0).getInRoadQuantity() + (hongXunPurchaseItemInStock.getRealQuantity()-item.getRealQuantity()));
				}
				hongXunPurchaseItem.setMaterialRealQuantity(hongXunPurchaseItem.getMaterialRealQuantity() - (hongXunPurchaseItemInStock.getRealQuantity()-item.getRealQuantity()));	
				iMaterialStockDao.update(hongXunMaterialStocks.get(0));
				iPurchaseItemDao.update(hongXunPurchaseItem);
				hongXunPurchaseItemInStock.setRealQuantity(item.getRealQuantity());
				hongXunPurchaseItemInStock.setLotNum(item.getLotNum());
				hongXunPurchaseItemInStock.setRemark(item.getRemark());				
				iPurchaseItemInStockDao.update(hongXunPurchaseItemInStock);
				mapHongXunPurchaseItemInStock(map, item);
				list.add(map);
				return list;
			}else{
				map.put("error","入库时间" + min +"分已超过半小时，禁止修改");
				list.add(map);
				return list;
			}
		}else{
			map.put("error","料号：" + hongXunPurchaseItem.getMaterialNum() +" 库存有" + hongXunMaterialStocks.size() + "个记录");
			list.add(map);
			return list;
			
		}
		
		
	}

	
	@Override
	public List<Map<String, Object>> getEntity() {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String,Object>();
		HongXunPurchaseItemInStock e = new HongXunPurchaseItemInStock();
		Class<? extends HongXunPurchaseItemInStock> cls = e.getClass();  
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


}
