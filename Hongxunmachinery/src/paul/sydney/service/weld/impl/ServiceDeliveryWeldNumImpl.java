package paul.sydney.service.weld.impl;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import paul.sydney.model.HongXunDeliveryItem;
import paul.sydney.model.HongXunDeliveryNum;
import paul.sydney.model.HongXunDeliveryWeldItem;
import paul.sydney.model.HongXunDeliveryWeldNum;
import paul.sydney.model.HongXunProductionStock;
import paul.sydney.model.HongXunProductionWeldStock;
import paul.sydney.service.weld.ServiceDeliveryWeldNumInf;
import paul.sydney.dao.IWeldDao;
import paul.sydney.dao.StockDao;
@Transactional
@Service("serviceDeliveryWeldNum")
public class ServiceDeliveryWeldNumImpl implements ServiceDeliveryWeldNumInf{
	
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
	public List<Map<String, Object>> loadData() {
		// TODO Auto-generated method stub
		System.out.println();
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		List<HongXunDeliveryWeldNum>  hongXunDeliveryNums = iWeldDao.quary(new HongXunDeliveryWeldNum());
		for(int i= hongXunDeliveryNums.size()-1; i>=0; i--){
			Map<String,Object> map = new HashMap<String,Object>();
			mapHongXunDeliveryNum(map,hongXunDeliveryNums.get(i));
			list.add(map);
		}
		
		return list;
	}
	
	@Override
	public List<Map<String, Object>> getEntity() {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String,Object>();
		HongXunDeliveryWeldNum e = new HongXunDeliveryWeldNum();
		Class<? extends HongXunDeliveryWeldNum> cls = e.getClass();  
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
	
	private void mapHongXunDeliveryNum(Map<String, Object> map, HongXunDeliveryWeldNum item) {
		// TODO Auto-generated method stub	
		//System.out.println("test: " + item.getHongXunDataOne().getProductionOrderNum());
		map.put("idc", item.getIdc());
		map.put("customer", item.getCustomer());
		map.put("deliveryNum", item.getDeliveryNum());
		map.put("supplierCode", item.getSupplierCode());		
		if(item.getDate() != null){
			map.put("date", new SimpleDateFormat("yyyy-MM-dd").format(item.getDate()));
		}
		map.put("status", item.getStatus());
		map.put("remark", item.getRemark());
	}

	@Override
	public List<Map<String, Object>> autotimp(String str) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		HongXunDeliveryWeldNum hongXunDeliveryWeldNum = new HongXunDeliveryWeldNum();
		List<HongXunDeliveryWeldNum> hongXunDeliveryNums = iWeldDao.quary(hongXunDeliveryWeldNum);
		for(HongXunDeliveryWeldNum item: hongXunDeliveryNums){	
			if(item.getDeliveryNum().indexOf(str)>-1){
				Map<String,Object> map = new HashMap<String,Object>();	
				map.put("name", item.getDeliveryNum());
				map.put("id", item.getIdc());  
				list.add(map);
			}
		}
		return list;
	}


	@Override
	public List<Map<String, Object>> searchMaterialNum(String materialNum) {
		// TODO Auto-generated method stub
		HongXunDeliveryWeldNum hongXunDeliveryWeldNum = new HongXunDeliveryWeldNum();
		hongXunDeliveryWeldNum.setDeliveryNum(materialNum);
		List<HongXunDeliveryWeldNum> hongXunDeliveryNums = iWeldDao.quary(hongXunDeliveryWeldNum);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (HongXunDeliveryWeldNum item : hongXunDeliveryNums) {			
			Map<String, Object> map = new HashMap<String, Object>();
			mapHongXunDeliveryNum(map,item);
			list.add(map);
		}	
		return list;
	}

	@Override
	public List<Map<String, Object>> saveRow(HongXunDeliveryWeldNum hongXunDeliveryWeldNum) {
		// TODO Auto-generated method stub
		iWeldDao.save(hongXunDeliveryWeldNum);
		Map<String,Object> map = new HashMap<String,Object>();
		mapHongXunDeliveryNum(map, hongXunDeliveryWeldNum);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.add(map);
		return list;
	}

	@Override
	public List<Map<String, Object>> updateRow(HongXunDeliveryWeldNum hongXunDeliveryWeldNum) {
		// TODO Auto-generated method stub
		iWeldDao.update(hongXunDeliveryWeldNum);
		Map<String,Object> map = new HashMap<String,Object>();
		//mapHongXunProductionStock(map, hongXunMaterialStock);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.add(map);
		return list;
	}
	
	@Override
	public List<Map<String, Object>> changeStatus(int deliveryNumID) {
		HongXunDeliveryWeldItem hongXunDeliveryWeldItem = new HongXunDeliveryWeldItem();
		hongXunDeliveryWeldItem.setDeliveryNumID(deliveryNumID);
		List<HongXunDeliveryWeldItem> hongXunDeliveryWeldItems = iWeldDao.quary(hongXunDeliveryWeldItem);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String,Object>();
		String status = "完成";
		if(hongXunDeliveryWeldItems.size()==0){
			map.put("status", "空单");	
			status = "空单";
		}else{
			for(HongXunDeliveryWeldItem item: hongXunDeliveryWeldItems){			
				if(item.getRemark().equals("库存无记录")){
					status = "未完成";
					break;
				}
			}
			map.put("status", status);	
		}
		HongXunDeliveryWeldNum hongXunDeliveryWeldNum = iWeldDao.deliveryWeldNumFindById(deliveryNumID); 
		hongXunDeliveryWeldNum.setStatus(status);
		iWeldDao.update(hongXunDeliveryWeldNum);
		list.add(map);
		return list;
	}

/*	@Override
	public List<Map<String, Object>> lotOutStock(List<HongXunProductionStock> hongXunProductionStockList) {
		// TODO Auto-generated method stub
		List<HongXunProductionStock>  hongXunProductionStocks = iWeldDao.quary(new HongXunProductionStock());
		List<String> materialNumList = new ArrayList<String>();
		for(HongXunProductionStock item : hongXunProductionStocks){
			materialNumList.add(item.getMaterialNum());
		}
		for(HongXunProductionStock item: hongXunProductionStockList){
			int index = materialNumList.indexOf(item.getMaterialNum());
			if(index >-1){
				hongXunProductionStocks.get(index).setQuantity(hongXunProductionStocks.get(index).getQuantity() - item.getQuantity());
				
			}else{
				
			}
		}
		return null;
	}*/

	@Override
	public List<Map<String, Object>> deleteRow(int id) {
		// TODO Auto-generated method stub
		HongXunDeliveryWeldNum hongXunDeliveryWeldNum = iWeldDao.deliveryWeldNumFindById(id);
		HongXunDeliveryWeldItem hongXunDeliveryWeldItem = new HongXunDeliveryWeldItem();
		hongXunDeliveryWeldItem.setDeliveryNumID(id);
		List<HongXunDeliveryWeldItem> hongXunDeliveryWeldItems = iWeldDao.quary(hongXunDeliveryWeldItem);
		List<HongXunProductionWeldStock>  hongXunProductionWeldStocks = iWeldDao.quary(new HongXunProductionWeldStock());
		List<String> materialNumList = new ArrayList<String>();
		for(HongXunProductionWeldStock item : hongXunProductionWeldStocks){
			materialNumList.add(item.getMaterialNum());
		}
		for(HongXunDeliveryWeldItem item: hongXunDeliveryWeldItems){
			int index = materialNumList.indexOf(item.getMaterialNo());
			if(index >-1){
				hongXunProductionWeldStocks.get(index).setQuantity(hongXunProductionWeldStocks.get(index).getQuantity() + item.getSendQuantity());	
				hongXunProductionWeldStocks.get(index).setOutQuantity(hongXunProductionWeldStocks.get(index).getOutQuantity() - item.getSendQuantity());	
				iWeldDao.update(hongXunProductionWeldStocks.get(index));
			}
			iWeldDao.delete(item);
		}
		iWeldDao.delete(hongXunDeliveryWeldNum);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		return list;
	}
/*	@Override
	public List<Map<String, Object>> delAllRows() {
		// TODO Auto-generated method stub
		List<HongXunProductionStock>  hongXunProductionStocks = stockDao.quary(new HongXunProductionStock());
		stockDao.deleteProductionItemList(hongXunProductionStocks);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		return list;
	}*/
}
