package paul.sydney.service.production;

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
import paul.sydney.model.HongXunProductionStock;
import paul.sydney.dao.IProductionDao;
import paul.sydney.dao.StockDao;
@Transactional
@Service("serviceDeliveryNum")
public class ServiceDeliveryNumImpl implements ServiceDeliveryNumInf{
	
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
	public List<Map<String, Object>> loadData() {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		List<HongXunDeliveryNum>  hongXunDeliveryNums = iProductionDao.quary(new HongXunDeliveryNum());
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
		HongXunDeliveryNum e = new HongXunDeliveryNum();
		Class<? extends HongXunDeliveryNum> cls = e.getClass();  
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
	
	private void mapHongXunDeliveryNum(Map<String, Object> map, HongXunDeliveryNum item) {
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
		HongXunDeliveryNum hongXunDeliveryNum = new HongXunDeliveryNum();
		List<HongXunDeliveryNum> hongXunDeliveryNums = iProductionDao.quary(hongXunDeliveryNum);
		for(HongXunDeliveryNum item: hongXunDeliveryNums){	
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
		HongXunDeliveryNum hongXunDeliveryNum = new HongXunDeliveryNum();
		hongXunDeliveryNum.setDeliveryNum(materialNum);
		List<HongXunDeliveryNum> hongXunDeliveryNums = iProductionDao.quary(hongXunDeliveryNum);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (HongXunDeliveryNum item : hongXunDeliveryNums) {			
			Map<String, Object> map = new HashMap<String, Object>();
			mapHongXunDeliveryNum(map,item);
			list.add(map);
		}	
		return list;
	}

	@Override
	public List<Map<String, Object>> saveRow(HongXunDeliveryNum hongXunDeliveryNum) {
		// TODO Auto-generated method stub
		iProductionDao.save(hongXunDeliveryNum);
		Map<String,Object> map = new HashMap<String,Object>();
		mapHongXunDeliveryNum(map, hongXunDeliveryNum);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.add(map);
		return list;
	}

	@Override
	public List<Map<String, Object>> updateRow(HongXunDeliveryNum hongXunDeliveryNum) {
		// TODO Auto-generated method stub
		iProductionDao.update(hongXunDeliveryNum);
		Map<String,Object> map = new HashMap<String,Object>();
		//mapHongXunProductionStock(map, hongXunMaterialStock);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.add(map);
		return list;
	}
	
	@Override
	public List<Map<String, Object>> changeStatus(int deliveryNumID) {
		HongXunDeliveryItem hongXunDeliveryItem = new HongXunDeliveryItem();
		hongXunDeliveryItem.setDeliveryNumID(deliveryNumID);
		List<HongXunDeliveryItem> hongXunDeliveryItems = iProductionDao.quary(hongXunDeliveryItem);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String,Object>();
		String status = "完成";
		if(hongXunDeliveryItems.size()==0){
			map.put("status", "空单");		
		}else{		
			for(HongXunDeliveryItem item: hongXunDeliveryItems){
				if(item.getRemark().equals("库存无记录")){
					status = "未完成";
					break;
				}
			}
			map.put("status", status);	
		}
		HongXunDeliveryNum hongXunDeliveryNum = iProductionDao.deliveryNumFindById(deliveryNumID); 
		hongXunDeliveryNum.setStatus(status);
		iProductionDao.update(hongXunDeliveryNum);
		list.add(map);
		return list;
	}
	
/*	@Override
	public List<Map<String, Object>> lotOutStock(List<HongXunProductionStock> hongXunProductionStockList) {
		// TODO Auto-generated method stub
		List<HongXunProductionStock>  hongXunProductionStocks = iProductionDao.quary(new HongXunProductionStock());
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
		HongXunDeliveryNum hongXunDeliveryNum = iProductionDao.deliveryNumFindById(id);
		HongXunDeliveryItem hongXunDeliveryItem = new HongXunDeliveryItem();
		hongXunDeliveryItem.setDeliveryNumID(id);
		List<HongXunDeliveryItem> hongXunDeliveryItems = iProductionDao.quary(hongXunDeliveryItem);
		List<HongXunProductionStock>  hongXunProductionStocks = iProductionDao.quary(new HongXunProductionStock());
		List<String> materialNumList = new ArrayList<String>();
		for(HongXunProductionStock item : hongXunProductionStocks){
			materialNumList.add(item.getMaterialNum());
		}
		for(HongXunDeliveryItem item: hongXunDeliveryItems){
			int index = materialNumList.indexOf(item.getMaterialNo());
			if(index >-1){
				hongXunProductionStocks.get(index).setQuantity(hongXunProductionStocks.get(index).getQuantity() + item.getSendQuantity());
				hongXunProductionStocks.get(index).setOutQuantity(hongXunProductionStocks.get(index).getOutQuantity() - item.getSendQuantity());	
				iProductionDao.update(hongXunProductionStocks.get(index));
			}
			iProductionDao.delete(item);
		}
		iProductionDao.delete(hongXunDeliveryNum);
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
