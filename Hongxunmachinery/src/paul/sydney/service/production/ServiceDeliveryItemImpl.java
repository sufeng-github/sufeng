package paul.sydney.service.production;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import paul.sydney.model.HongXunDeliveryItem;
import paul.sydney.model.HongXunDeliveryNum;
import paul.sydney.model.HongXunProductionStock;
import paul.sydney.dao.IProductionDao;
import paul.sydney.dao.StockDao;
@Transactional
@Service("serviceDeliveryItem")
public class ServiceDeliveryItemImpl implements ServiceDeliveryItemInf{
	
	@Autowired
	StockDao stockDao;
	public void setStockDao(StockDao stockDao) {
		this.stockDao = stockDao;
	}
	@Autowired
	IProductionDao iProductionDao;
	public void setStockDao(IProductionDao iProductionDao) {
		this.iProductionDao = iProductionDao;
	}
	@Override
	public List<Map<String, Object>> loadData(Integer deliveryNumID) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		HongXunDeliveryItem hongXunDeliveryItem = new HongXunDeliveryItem();
		hongXunDeliveryItem.setDeliveryNumID(deliveryNumID);
		List<HongXunDeliveryItem>  hongXunDeliveryItems = iProductionDao.quary(hongXunDeliveryItem);
		for(HongXunDeliveryItem item : hongXunDeliveryItems){
			Map<String,Object> map = new HashMap<String,Object>();
			mapHongXunDeliveryItem(map,item);
			list.add(map);
		}
		
		return list;
	}
	
	@Override
	public List<Map<String, Object>> getEntity() {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String,Object>();
		HongXunProductionStock e = new HongXunProductionStock();
		Class<? extends HongXunProductionStock> cls = e.getClass();  
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
	
	private void mapHongXunDeliveryItem(Map<String, Object> map, HongXunDeliveryItem item) {
		// TODO Auto-generated method stub	
		//System.out.println("test: " + item.getHongXunDataOne().getProductionOrderNum());
		map.put("idc", item.getIdc());
		map.put("pon", item.getPon());
		map.put("line", item.getLine());
		map.put("materialNo", item.getMaterialNo());
		map.put("materialDesc", item.getMaterialDesc());
		map.put("exemption", item.getExemption());
		map.put("code", item.getCode());
		map.put("unit", item.getUnit());
		map.put("projectNum", item.getProjectNum());
		map.put("status", item.getStatus());
		map.put("sendQuantity", item.getSendQuantity());
		map.put("deliveryNumID", item.getDeliveryNumID());	
		map.put("remark", item.getRemark());
		HongXunDeliveryNum deliveryNum = iProductionDao.deliveryNumFindById(item.getDeliveryNumID());
		map.put("deliveryNum", deliveryNum.getDeliveryNum());
	}

	@Override
	public List<Map<String, Object>> autotimp(String str, int deliveryNumID) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		HongXunDeliveryItem hongXunDeliveryItem = new HongXunDeliveryItem();
		hongXunDeliveryItem.setDeliveryNumID(deliveryNumID);
		List<HongXunDeliveryItem> hongXunDeliveryItems = iProductionDao.quary(hongXunDeliveryItem);
		for(HongXunDeliveryItem item: hongXunDeliveryItems){	
			if(item.getMaterialNo().indexOf(str)>-1){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("name", item.getMaterialNo());
				map.put("id", item.getIdc());  
				list.add(map);
			}
		}
		return list;
	}


	@Override
	public List<Map<String, Object>> searchMaterialNum(String materialNum, int deliveryNumID) {
		// TODO Auto-generated method stub
		HongXunDeliveryItem hongXunDeliveryItem = new HongXunDeliveryItem();
		hongXunDeliveryItem.setMaterialNo(materialNum);	
		hongXunDeliveryItem.setDeliveryNumID(deliveryNumID);
		List<HongXunDeliveryItem> hongXunDeliveryItems = iProductionDao.quary(hongXunDeliveryItem);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (hongXunDeliveryItems != null) {
			for (HongXunDeliveryItem item : hongXunDeliveryItems) {
				Map<String, Object> map = new HashMap<String, Object>();
				mapHongXunDeliveryItem(map,item);
				list.add(map);
			}
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> autotimp(String str) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		HongXunDeliveryItem hongXunDeliveryItem = new HongXunDeliveryItem();
		List<HongXunDeliveryItem> hongXunDeliveryItems = iProductionDao.quary(hongXunDeliveryItem);
		for(HongXunDeliveryItem item: hongXunDeliveryItems){	
			if(item.getMaterialNo().indexOf(str)>-1){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("name", item.getMaterialNo());
				map.put("id", item.getIdc());  
				list.add(map);
			}
		}
		return list;
	}


	@Override
	public List<Map<String, Object>> searchMaterialNum(String materialNum) {
		// TODO Auto-generated method stub
		HongXunDeliveryItem hongXunDeliveryItem = new HongXunDeliveryItem();
		hongXunDeliveryItem.setMaterialNo(materialNum);	
		List<HongXunDeliveryItem> hongXunDeliveryItems = iProductionDao.quary(hongXunDeliveryItem);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (hongXunDeliveryItems != null) {
			for (HongXunDeliveryItem item : hongXunDeliveryItems) {
				Map<String, Object> map = new HashMap<String, Object>();
				mapHongXunDeliveryItem(map,item);
				list.add(map);
			}
		}
		return list;
	}
	
	@Override
	public List<Map<String, Object>> outstock(String materialNum, int deliveryItemID) {
		// TODO Auto-generated method stub
		String specification = null;
		if(materialNum.indexOf("->") > 0){
			specification = materialNum.split("->")[1];
			materialNum = materialNum.split("->")[0];
		}
/*		HongXunDeliveryItem hongXunDeliveryItem = new HongXunDeliveryItem();
		hongXunDeliveryItem.setMaterialNo(materialNum);	
		hongXunDeliveryItem.setDeliveryNumID(deliveryNumID);
		hongXunDeliveryItem.setRemark("库存无记录");
		List<HongXunDeliveryItem> hongXunDeliveryItems = iProductionDao.quary(hongXunDeliveryItem);*/
		HongXunDeliveryItem hongXunDeliveryItem = iProductionDao.deliveryItemFindById(deliveryItemID);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		//if (hongXunDeliveryItems.size()>0) {
		if (hongXunDeliveryItem != null && hongXunDeliveryItem.getRemark().equals("库存无记录") && 
				hongXunDeliveryItem.getMaterialNo().equals(materialNum)) {
			HongXunProductionStock hongXunProductionStock = new HongXunProductionStock();
			hongXunProductionStock.setMaterialNum(materialNum);
			if(specification !=null){
				hongXunProductionStock.setSpecification(specification);
			}
			List<HongXunProductionStock> hongXunProductionStocks = iProductionDao.quary(hongXunProductionStock);
			if(hongXunProductionStocks.size()==0){				
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("error", "物料号:"+ materialNum +"->焊接仓库无记录");
				list.add(map);
				return list;	
		    }else if(hongXunProductionStocks.size()==1){
		    	int result = hongXunProductionStocks.get(0).getQuantity() - hongXunDeliveryItem.getSendQuantity();
		    	if(result<0){
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("error", "物料号:"+ materialNum +"->出库数量大于库存数量：" + hongXunProductionStocks.get(0).getQuantity());
					list.add(map);
					return list;
		    	}else{
		    		hongXunProductionStocks.get(0).setQuantity(result);
		    		if(hongXunProductionStocks.get(0).getOutQuantity() != null){
		    			hongXunProductionStocks.get(0).setOutQuantity(hongXunProductionStocks.get(0).getOutQuantity() + hongXunDeliveryItem.getSendQuantity());
		    		}else{
		    			hongXunProductionStocks.get(0).setOutQuantity(hongXunDeliveryItem.getSendQuantity());
		    		}
		    		iProductionDao.update(hongXunProductionStocks.get(0));
		    		hongXunDeliveryItem.setRemark("");
		    		iProductionDao.update(hongXunDeliveryItem);
		    		Map<String, Object> map = new HashMap<String, Object>();
		    		mapHongXunDeliveryItem(map, hongXunDeliveryItem);
					list.add(map);
		    		return list;
		    	}		    	
	    	}else{
	    		//System.out.println("HongXunMaterialStock 有重复料号出错");
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("error", "物料号:"+ materialNum +"->有重复料号,请联系工程师");
				list.add(map);
				return list;
	    	}
		}else{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", "送货单无:"+ materialNum +"料号或已出库");
			list.add(map);
			return list;
		}
		
	}
	
	@Override
	public List<Map<String, Object>> saveRow(HongXunProductionStock hongXunMaterialStock) {
		// TODO Auto-generated method stub
		iProductionDao.save(hongXunMaterialStock);
		Map<String,Object> map = new HashMap<String,Object>();
		//mapHongXunProductionStock(map, hongXunMaterialStock);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.add(map);
		return list;
	}

	@Override
	public List<Map<String, Object>> updateRow(HongXunProductionStock hongXunMaterialStock) {
		// TODO Auto-generated method stub
		iProductionDao.update(hongXunMaterialStock);
		Map<String,Object> map = new HashMap<String,Object>();
		//mapHongXunProductionStock(map, hongXunMaterialStock);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.add(map);
		return list;
	}

	@Override
	public List<Map<String, Object>> delAllRows() {
		// TODO Auto-generated method stub
		List<HongXunProductionStock>  hongXunProductionStocks = iProductionDao.quary(new HongXunProductionStock());
		iProductionDao.deleteProductionItemList(hongXunProductionStocks);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		return list;
	}


	@Override
	public List<Map<String, Object>> importData(List<HongXunDeliveryItem> hongXunDeliveryItemList) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
	/*	List<HongXunProductionStock>  hongXunProductionStocks = iProductionDao.quary(new HongXunProductionStock());
		List<String> materialNumList = new ArrayList<String>();
		for(HongXunProductionStock item : hongXunProductionStocks){
			materialNumList.add(item.getMaterialNum());
		}*/
		HongXunProductionStock  hongXunProductionStock = new HongXunProductionStock();
		for(HongXunDeliveryItem item: hongXunDeliveryItemList){
			String desc = null;
			int indexs = item.getMaterialDesc().indexOf("长度L=:");
			if(indexs > -1){
				String str = item.getMaterialDesc().substring(indexs+5);
				desc = str.substring(0, str.indexOf("."));				
			}
			hongXunProductionStock.setMaterialNum(item.getMaterialNo());
			List<HongXunProductionStock>  hongXunProductionStocks = iProductionDao.quary(hongXunProductionStock);
			if(hongXunProductionStocks.size()==0){
				item.setRemark("库存无记录");
			}else{
				HongXunProductionStock hongXunProductionStock1 = null;
				if(hongXunProductionStocks.size()==1){
					hongXunProductionStock1 = hongXunProductionStocks.get(0);
				}else{
					for(HongXunProductionStock item1 : hongXunProductionStocks){
						if(desc != null){
							if(item1.getSpecification().indexOf(desc)>-1){
								hongXunProductionStock1 = item1;
								break;
							}
						}
					}
				}
				if(hongXunProductionStock1 == null){
					item.setRemark("库存无记录");
				}else{
					hongXunProductionStock1.setQuantity(hongXunProductionStock1.getQuantity() - item.getSendQuantity());
					if(hongXunProductionStock1.getOutQuantity() != null){
						hongXunProductionStock1.setOutQuantity(hongXunProductionStock1.getOutQuantity() + item.getSendQuantity());
					}else{
						hongXunProductionStock1.setOutQuantity(item.getSendQuantity());
					}
					iProductionDao.update(hongXunProductionStock1);
				}
			}
			
/*			int index = materialNumList.indexOf(item.getMaterialNo());
			if(index >-1){
				hongXunProductionStocks.get(index).setQuantity(hongXunProductionStocks.get(index).getQuantity() - item.getSendQuantity());
				if(hongXunProductionStocks.get(index).getOutQuantity() != null){
					hongXunProductionStocks.get(index).setOutQuantity(hongXunProductionStocks.get(index).getOutQuantity() + item.getSendQuantity());
				}else{
					hongXunProductionStocks.get(index).setOutQuantity(item.getSendQuantity());
				}
				iProductionDao.update(hongXunProductionStocks.get(index));
			}else{
				item.setRemark("库存无记录");
			}*/
			iProductionDao.save(item);
			Map<String,Object> map = new HashMap<String,Object>();
			mapHongXunDeliveryItem(map, item);			
			list.add(map);
		}
		return list;
	}
}
