package paul.sydney.service.materialstock.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import paul.sydney.dao.ISuppliersDao;
import paul.sydney.dao.ISuppliersPriceDao;
import paul.sydney.dao.StockDao;
import paul.sydney.model.HongXunSuppliersPrice;
import paul.sydney.service.materialstock.ServiceMaterialPriceInf;
@Transactional
@Service("serviceSuppliersPrice")
public class ServiceMaterialPriceImpl implements ServiceMaterialPriceInf{
	
	@Autowired
	StockDao stockDao;
	public void setStockDao(StockDao stockDao) {
		this.stockDao = stockDao;
	}
	@Autowired
	ISuppliersDao iSuppliersDao;
	public void setStockDao(ISuppliersDao iSuppliersDao) {
		this.iSuppliersDao = iSuppliersDao;
	}
	@Autowired
	ISuppliersPriceDao iSuppliersPriceDao;
	public void setStockDao(ISuppliersPriceDao iSuppliersPriceDao) {
		this.iSuppliersPriceDao = iSuppliersPriceDao;
	}	
	@Override
	public List<Map<String, Object>> loadData() {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		List<HongXunSuppliersPrice>  hongXunSuppliersPrices = iSuppliersPriceDao.quary(new HongXunSuppliersPrice());
		for(HongXunSuppliersPrice item : hongXunSuppliersPrices){
			Map<String,Object> map = new HashMap<String,Object>();
			mapHongXunSuppliersPrice(map,item);
			list.add(map);
		}
		
		return list;
	}
	
	@Override
	public List<Map<String, Object>> getEntity() {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String,Object>();
		HongXunSuppliersPrice e = new HongXunSuppliersPrice();
		Class<? extends HongXunSuppliersPrice> cls = e.getClass();  
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
	public List<Map<String, Object>> autotimp(String str) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		//List<String> listName = new ArrayList<String>();
		@SuppressWarnings("unchecked")
		List<HongXunSuppliersPrice> hongXunSuppliersPrices = (List<HongXunSuppliersPrice>) stockDao.quaryFuzzyWithpara("HongXunSuppliersPrice", "materialNum", str); 	
		for(HongXunSuppliersPrice item: hongXunSuppliersPrices){	
			Map<String,Object> map = new HashMap<String,Object>();
			//map.put("materialName", item.getMaterialName());	
			map.put("name", item.getMaterialNum());
			map.put("id", item.getIdc());  
			list.add(map);
		}
		return list;
	}

/*
	@Override
	public List<Map<String, Object>> searchMaterialNum(String materialNum) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<HongXunSuppliersPrice> hongXunSuppliersPrices = (List<HongXunSuppliersPrice>) stockDao.quaryFuzzyWithpara("HongXunSuppliersPrice", "materialNum", materialNum);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (hongXunSuppliersPrices != null) {
			for (HongXunSuppliersPrice item : hongXunSuppliersPrices) {
				Map<String, Object> map = new HashMap<String, Object>();
				//mapHongXunSuppliersPrice(map,item);
				list.add(map);
			}
		}
		return list;
	}*/
	//@Transactional(readOnly=false)
	@Override
	public List<Map<String, Object>> saveOrUpdateRow(List<HongXunSuppliersPrice> hongXunSuppliersPriceList) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	for(HongXunSuppliersPrice item : hongXunSuppliersPriceList){ 
    		//System.out.println("1");
    		if(item.getMaterialNum()==null || item.getSpecification()==null){
    			break;
    		}
    		if(item.getIdc()==0){
				iSuppliersPriceDao.save(item);	
    		}else{   			
    			iSuppliersPriceDao.update(item);
    		}
    		Map<String,Object> map = new HashMap<String,Object>();
    		mapHongXunSuppliersPrice(map, item);
    		list.add(map);
    	}
    	return list;
	}
	
	@Override
	public List<Map<String, Object>> saveRow(HongXunSuppliersPrice hongXunSuppliersPrice) {
		// TODO Auto-generated method stub
		//System.out.println(hongXunSuppliersPrice.getPrice().doubleValue());
		iSuppliersPriceDao.save(hongXunSuppliersPrice);
		Map<String,Object> map = new HashMap<String,Object>();
		mapHongXunSuppliersPrice(map, hongXunSuppliersPrice);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.add(map);
		return list;
	}

	@Override
	public List<Map<String, Object>> updateRow(HongXunSuppliersPrice hongXunSuppliersPrice) {
		// TODO Auto-generated method stub
		iSuppliersPriceDao.update(hongXunSuppliersPrice);
		Map<String,Object> map = new HashMap<String,Object>();
		mapHongXunSuppliersPrice(map, hongXunSuppliersPrice);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.add(map);
		return list;
	}

/*	@Override
	public List<Map<String, Object>> getMaterialAlarms(String alarm) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		@SuppressWarnings("unchecked")
		List<HongXunSuppliersPrice> hongXunSuppliersPrices = (List<HongXunSuppliersPrice>) stockDao.quaryFuzzyWithpara("HongXunSuppliersPrice", "alarm", alarm);
		for(HongXunSuppliersPrice item : hongXunSuppliersPrices){
			Map<String,Object> map = new HashMap<String,Object>();
			//mapHongXunSuppliersPrice(map,item);
			list.add(map);
		}
		
		return list;
	}
*/
	@Override
	public List<Map<String, Object>> deleteRow(List<HongXunSuppliersPrice> hongXunSuppliersPriceList) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		iSuppliersPriceDao.deletePriceList(hongXunSuppliersPriceList);
		return list;
	}

	private void mapHongXunSuppliersPrice(Map<String, Object> map, HongXunSuppliersPrice item) {
		// TODO Auto-generated method stub	
		//System.out.println("test: " + item.getHongXunDataOne().getProductionOrderNum());
		map.put("idc", item.getIdc());
		map.put("materialNum", item.getMaterialNum());
		map.put("specification", item.getSpecification());
		map.put("supplier", item.getSupplier());
		if(item.getPrice() != null){	
	  		map.put("price", item.getPrice().doubleValue());
	  	}		
		map.put("remark", item.getRemark());
	}

}
