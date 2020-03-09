package paul.sydney.service.others.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import paul.sydney.dao.StockDao;
import paul.sydney.model.HongXunOnlineStock;
import paul.sydney.service.others.ServiceOnlineStockInf;
@Transactional
@Service("serviceOnlineStock")
public class ServiceOnlineStockImpl implements ServiceOnlineStockInf{
	
	@Autowired
	StockDao stockDao;
	public void setStockDao(StockDao stockDao) {
		this.stockDao = stockDao;
	}

	@Override
	public List<Map<String, Object>> loadData() {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		List<HongXunOnlineStock>  hongXunOnlineStocks = stockDao.quary(new HongXunOnlineStock());
		for(HongXunOnlineStock item : hongXunOnlineStocks){
			Map<String,Object> map = new HashMap<String,Object>();
			mapHongXunOnlineStock(map,item);
			list.add(map);
		}
		
		return list;
	}
	
	@Override
	public List<Map<String, Object>> getEntity() {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String,Object>();
		HongXunOnlineStock e = new HongXunOnlineStock();
		Class<? extends HongXunOnlineStock> cls = e.getClass();  
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
	
	private void mapHongXunOnlineStock(Map<String, Object> map, HongXunOnlineStock item) {
		// TODO Auto-generated method stub	
		//System.out.println("test: " + item.getHongXunDataOne().getProductionOrderNum());
		map.put("idc", item.getIdc());
		map.put("materialNum", item.getMaterialNum());
		map.put("specification", item.getSpecification());
		map.put("quantity", item.getQuantity());
/*		map.put("unit", item.getUnit());
		map.put("safeQuantity", item.getSafeQuantity());
		map.put("inRoadQuantity", item.getInRoadQuantity());
		map.put("itemQuantity", item.getItemQuantity());
		map.put("localCode", item.getLocalCode());*/
		map.put("remark", item.getRemark());
	}

	@Override
	public List<Map<String, Object>> autotimp(String str) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		//List<String> listName = new ArrayList<String>();
		@SuppressWarnings("unchecked")
		List<HongXunOnlineStock> hongXunOnlineStocks = (List<HongXunOnlineStock>) stockDao.quaryFuzzyWithpara("HongXunOnlineStock", "materialNum", str); 	
		for(HongXunOnlineStock item: hongXunOnlineStocks){	
			Map<String,Object> map = new HashMap<String,Object>();
			//map.put("materialName", item.getMaterialName());	
			map.put("name", item.getMaterialNum());
			map.put("id", item.getIdc());  
			list.add(map);
		}
		return list;
	}


	@Override
	public List<Map<String, Object>> searchMaterialNum(String materialNum) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<HongXunOnlineStock> hongXunOnlineStocks = (List<HongXunOnlineStock>) stockDao.quaryFuzzyWithpara("HongXunOnlineStock", "materialNum", materialNum);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (hongXunOnlineStocks != null) {
			for (HongXunOnlineStock item : hongXunOnlineStocks) {
				Map<String, Object> map = new HashMap<String, Object>();
				mapHongXunOnlineStock(map,item);
				list.add(map);
			}
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> saveRow(HongXunOnlineStock hongXunOnlineStock) {
		// TODO Auto-generated method stub
		stockDao.save(hongXunOnlineStock);
		Map<String,Object> map = new HashMap<String,Object>();
		mapHongXunOnlineStock(map, hongXunOnlineStock);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.add(map);
		return list;
	}

	@Override
	public List<Map<String, Object>> updateRow(HongXunOnlineStock hongXunOnlineStock) {
		// TODO Auto-generated method stub
		stockDao.update(hongXunOnlineStock);
		Map<String,Object> map = new HashMap<String,Object>();
		mapHongXunOnlineStock(map, hongXunOnlineStock);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.add(map);
		return list;
	}

	@Override
	public List<Map<String, Object>> delAllRows() {
		// TODO Auto-generated method stub
		List<HongXunOnlineStock>  hongXunOnlineStocks = stockDao.quary(new HongXunOnlineStock());
		stockDao.deleteOnlineItemList(hongXunOnlineStocks);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		return list;
	}

}
