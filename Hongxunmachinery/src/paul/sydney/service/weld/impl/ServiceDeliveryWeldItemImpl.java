package paul.sydney.service.weld.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import paul.sydney.model.HongXunDeliveryWeldItem;
import paul.sydney.model.HongXunDeliveryWeldNum;
import paul.sydney.model.HongXunProductionWeldStock;
import paul.sydney.service.weld.ServiceDeliveryWeldItemInf;
import paul.sydney.dao.IWeldDao;
import paul.sydney.dao.StockDao;
@Transactional
@Service("serviceDeliveryWeldItem")
public class ServiceDeliveryWeldItemImpl implements ServiceDeliveryWeldItemInf{
	
	@Autowired
	StockDao stockDao;
	public void setStockDao(StockDao stockDao) {
		this.stockDao = stockDao;
	}
	@Autowired
	IWeldDao iWeldDao;
	public void setStockDao(IWeldDao iWeldDao) {
		this.iWeldDao = iWeldDao;
	}
	@Override
	public List<Map<String, Object>> loadData(Integer deliveryNumID) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		HongXunDeliveryWeldItem hongXunDeliveryItem = new HongXunDeliveryWeldItem();
		hongXunDeliveryItem.setDeliveryNumID(deliveryNumID);
		List<HongXunDeliveryWeldItem>  hongXunDeliveryWeldItems = iWeldDao.quary(hongXunDeliveryItem);
		for(HongXunDeliveryWeldItem item : hongXunDeliveryWeldItems){
			Map<String,Object> map = new HashMap<String,Object>();
			mapHongXunDeliveryWeldItem(map,item);
			list.add(map);
		}
		
		return list;
	}
	
	@Override
	public List<Map<String, Object>> getEntity() {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String,Object>();
		HongXunDeliveryWeldItem e = new HongXunDeliveryWeldItem();
		Class<? extends HongXunDeliveryWeldItem> cls = e.getClass();  
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
	
	private void mapHongXunDeliveryWeldItem(Map<String, Object> map, HongXunDeliveryWeldItem item) {
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
		System.out.println("item.getDeliveryNumID() :" + item.getDeliveryNumID());
		HongXunDeliveryWeldNum deliveryNum = iWeldDao.deliveryWeldNumFindById(item.getDeliveryNumID());
		map.put("deliveryNum", deliveryNum.getDeliveryNum());
	}

	@Override
	public List<Map<String, Object>> autotimp(String str, Integer deliveryNumID) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		HongXunDeliveryWeldItem hongXunDeliveryWeldItem = new HongXunDeliveryWeldItem();
		hongXunDeliveryWeldItem.setDeliveryNumID(deliveryNumID);
		List<HongXunDeliveryWeldItem> hongXunDeliveryWeldItems = iWeldDao.quary(hongXunDeliveryWeldItem);
		for(HongXunDeliveryWeldItem item: hongXunDeliveryWeldItems){	
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
		HongXunDeliveryWeldItem hongXunDeliveryWeldItem = new HongXunDeliveryWeldItem();
		hongXunDeliveryWeldItem.setMaterialNo(materialNum);	
		hongXunDeliveryWeldItem.setDeliveryNumID(deliveryNumID);
		List<HongXunDeliveryWeldItem> hongXunDeliveryWeldItems = iWeldDao.quary(hongXunDeliveryWeldItem);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (hongXunDeliveryWeldItems != null) {
			for (HongXunDeliveryWeldItem item : hongXunDeliveryWeldItems) {
				Map<String, Object> map = new HashMap<String, Object>();
				mapHongXunDeliveryWeldItem(map,item);
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
		HongXunDeliveryWeldItem hongXunDeliveryWeldItem = iWeldDao.deliveryWeldItemFindById(deliveryItemID);
/*		HongXunDeliveryWeldItem hongXunDeliveryWeldItem = new HongXunDeliveryWeldItem();
		hongXunDeliveryWeldItem.setMaterialNo(materialNum);	
		hongXunDeliveryWeldItem.setDeliveryNumID(deliveryNumID);
		hongXunDeliveryWeldItem.setRemark("库存无记录");
		List<HongXunDeliveryWeldItem> hongXunDeliveryWeldItems = iWeldDao.quary(hongXunDeliveryWeldItem);*/
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		//if (hongXunDeliveryWeldItems.size()>0) {
		if (hongXunDeliveryWeldItem != null && hongXunDeliveryWeldItem.getRemark().equals("库存无记录") && 
				hongXunDeliveryWeldItem.getMaterialNo().equals(materialNum)) {
			HongXunProductionWeldStock hongXunProductionWeldStock = new HongXunProductionWeldStock();
			hongXunProductionWeldStock.setMaterialNum(materialNum);
			if(specification !=null){
				hongXunProductionWeldStock.setSpecification(specification);
			}
			List<HongXunProductionWeldStock> hongXunProductionWeldStocks = iWeldDao.quary(hongXunProductionWeldStock);
			if(hongXunProductionWeldStocks.size()==0){				
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("error", "物料号:"+ materialNum +"->焊接仓库无记录");
				list.add(map);
				return list;	
		    }else if(hongXunProductionWeldStocks.size()==1){
		    	int result = hongXunProductionWeldStocks.get(0).getQuantity() - hongXunDeliveryWeldItem.getSendQuantity();
		    	if(result<0){
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("error", "物料号:"+ materialNum +"->出库数量大于库存数量：" + hongXunProductionWeldStocks.get(0).getQuantity());
					list.add(map);
					return list;
		    	}else{
		    		hongXunProductionWeldStocks.get(0).setQuantity(result);
		    		if(hongXunProductionWeldStocks.get(0).getOutQuantity() != null){
		    			hongXunProductionWeldStocks.get(0).setOutQuantity(hongXunProductionWeldStocks.get(0).getOutQuantity() + hongXunDeliveryWeldItem.getSendQuantity());
		    		}else{
		    			hongXunProductionWeldStocks.get(0).setOutQuantity(hongXunDeliveryWeldItem.getSendQuantity());
		    		}
		    		iWeldDao.update(hongXunProductionWeldStocks.get(0));
		    		hongXunDeliveryWeldItem.setRemark("");
		    		iWeldDao.update(hongXunDeliveryWeldItem);
		    		Map<String, Object> map = new HashMap<String, Object>();
		    		mapHongXunDeliveryWeldItem(map, hongXunDeliveryWeldItem);
					list.add(map);
		    		return list;
		    	}		    	
	    	}else{
	    		System.out.println("HongXunMaterialStock 有重复料号出错");
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
	public List<Map<String, Object>> autotimp(String str) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		HongXunDeliveryWeldItem hongXunDeliveryWeldItem = new HongXunDeliveryWeldItem();
		List<HongXunDeliveryWeldItem> hongXunDeliveryWeldItems = iWeldDao.quary(hongXunDeliveryWeldItem);
		for(HongXunDeliveryWeldItem item: hongXunDeliveryWeldItems){	
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
		HongXunDeliveryWeldItem hongXunDeliveryWeldItem = new HongXunDeliveryWeldItem();
		hongXunDeliveryWeldItem.setMaterialNo(materialNum);	
		List<HongXunDeliveryWeldItem> hongXunDeliveryWeldItems = iWeldDao.quary(hongXunDeliveryWeldItem);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (hongXunDeliveryWeldItems != null) {
			for (HongXunDeliveryWeldItem item : hongXunDeliveryWeldItems) {
				Map<String, Object> map = new HashMap<String, Object>();
				mapHongXunDeliveryWeldItem(map,item);
				list.add(map);
			}
		}
		return list;
	}
/*	@Override
	public List<Map<String, Object>> saveRow(HongXunDeliveryWeldItem hongXunDeliveryWeldItem) {
		// TODO Auto-generated method stub
		iWeldDao.save(hongXunDeliveryWeldItem);
		Map<String,Object> map = new HashMap<String,Object>();
		//mapHongXunProductionStock(map, hongXunDeliveryWeldItem);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.add(map);
		return list;
	}

	@Override
	public List<Map<String, Object>> updateRow(HongXunDeliveryWeldItem hongXunDeliveryWeldItem) {
		// TODO Auto-generated method stub
		iWeldDao.update(hongXunDeliveryWeldItem);
		Map<String,Object> map = new HashMap<String,Object>();
		//mapHongXunProductionStock(map, hongXunDeliveryWeldItem);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.add(map);
		return list;
	}*/

/*	@Override
	public List<Map<String, Object>> delAllRows() {
		// TODO Auto-generated method stub
		List<HongXunDeliveryWeldItem>  hongXunProductionStocks = iWeldDao.quary(new HongXunDeliveryWeldItem());
		iWeldDao.deleteProductionItemList(hongXunProductionStocks);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		return list;
	}*/


/*	@Override
	public List<Map<String, Object>> importData(List<HongXunDeliveryWeldItem> hongXunDeliveryItemList) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		List<HongXunProductionWeldStock>  hongXunProductionWeldStocks = iWeldDao.quary(new HongXunProductionWeldStock());
		List<String> materialNumList = new ArrayList<String>();
		for(HongXunProductionWeldStock item : hongXunProductionWeldStocks){
			materialNumList.add(item.getMaterialNum());
		}
		for(HongXunDeliveryWeldItem item: hongXunDeliveryItemList){
			int index = materialNumList.indexOf(item.getMaterialNo());
			if(index >-1){
				hongXunProductionWeldStocks.get(index).setQuantity(hongXunProductionWeldStocks.get(index).getQuantity() - item.getSendQuantity());	
				if(hongXunProductionWeldStocks.get(index).getOutQuantity() != null){
					hongXunProductionWeldStocks.get(index).setOutQuantity(hongXunProductionWeldStocks.get(index).getOutQuantity() + item.getSendQuantity());	
				}else{
					hongXunProductionWeldStocks.get(index).setOutQuantity(item.getSendQuantity());
				}
				iWeldDao.update(hongXunProductionWeldStocks.get(index));
			}else{
				item.setRemark("库存无记录");
			}
			iWeldDao.save(item);
			Map<String,Object> map = new HashMap<String,Object>();
			mapHongXunDeliveryWeldItem(map, item);			
			list.add(map);
		}
		return list;
	}*/
	
	@Override
	public List<Map<String, Object>> importData(List<HongXunDeliveryWeldItem> hongXunDeliveryWeldItemList) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		HongXunProductionWeldStock  hongXunProductionWeldStock = new HongXunProductionWeldStock();
		for(HongXunDeliveryWeldItem item: hongXunDeliveryWeldItemList){
			String desc = null;
			int indexs = item.getMaterialDesc().indexOf("长度L=:");
			if(indexs > -1){
				String str = item.getMaterialDesc().substring(indexs+5);
				desc = str.substring(0, str.indexOf("."));				
			}
			hongXunProductionWeldStock.setMaterialNum(item.getMaterialNo());
			List<HongXunProductionWeldStock>  hongXunProductionWeldStocks = iWeldDao.quary(hongXunProductionWeldStock);
			if(hongXunProductionWeldStocks.size()==0){
				item.setRemark("库存无记录");
			}else{
				HongXunProductionWeldStock hongXunProductionWeldStock1 = null;
				if(hongXunProductionWeldStocks.size()==1){
					hongXunProductionWeldStock1 = hongXunProductionWeldStocks.get(0);
				}else{
					for(HongXunProductionWeldStock item1 : hongXunProductionWeldStocks){
						if(desc != null){
							if(item1.getSpecification().indexOf(desc)>-1){
								hongXunProductionWeldStock1 = item1;
								break;
							}
						}
					}
				}
				if(hongXunProductionWeldStock1 == null){
					item.setRemark("库存无记录");
				}else{
					hongXunProductionWeldStock1.setQuantity(hongXunProductionWeldStock1.getQuantity() - item.getSendQuantity());
					if(hongXunProductionWeldStock1.getOutQuantity() != null){
						hongXunProductionWeldStock1.setOutQuantity(hongXunProductionWeldStock1.getOutQuantity() + item.getSendQuantity());
					}else{
						hongXunProductionWeldStock1.setOutQuantity(item.getSendQuantity());
					}
					iWeldDao.update(hongXunProductionWeldStock1);
				}
			}
			iWeldDao.save(item);
			Map<String,Object> map = new HashMap<String,Object>();
			mapHongXunDeliveryWeldItem(map, item);			
			list.add(map);
		}
		return list;
	}
}
