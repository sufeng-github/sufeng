package paul.sydney.service.production.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import paul.sydney.commen.result.PageResults;
import paul.sydney.dao.IProductionDao;
import paul.sydney.dao.StockDao;
import paul.sydney.model.HongXunAssembleCompilation;
import paul.sydney.model.HongXunDeliveryItem;
import paul.sydney.model.HongXunDeliveryNum;
import paul.sydney.model.HongXunPoN;
import paul.sydney.model.HongXunProductionItemInStock;
import paul.sydney.model.HongXunProductionItemOutStock;
import paul.sydney.model.HongXunProductionNoLimitItemInStock;
import paul.sydney.model.HongXunProductionNoLimitItemOutStock;
import paul.sydney.model.HongXunProductionStock;
import paul.sydney.service.production.ServiceProductionStockInf;
@Transactional
@Service("serviceProductionStock")
public class ServiceProductionStockImpl implements ServiceProductionStockInf{
	
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
	public List<Map<String, Object>> getEntity() {
        return iProductionDao.getEntity();
	}
	
	private void mapHongXunProductionStock(Map<String, Object> map, HongXunProductionStock item) {
		// TODO Auto-generated method stub	
		//System.out.println("test: " + item.getHongXunDataOne().getProductionOrderNum());
		map.put("idc", item.getIdc());
		map.put("materialNum", item.getMaterialNum());
		map.put("specification", item.getSpecification());
		map.put("quantity", item.getQuantity());
		map.put("unit", item.getUnit());
		map.put("safeQuantity", item.getSafeQuantity());
		map.put("inRoadQuantity", item.getInRoadQuantity());
		map.put("itemQuantity", item.getItemQuantity());
		map.put("localCode", item.getLocalCode());
		map.put("remark", item.getRemark());
		map.put("inQuantity", item.getInQuantity());
		map.put("outQuantity", item.getOutQuantity());
/*		HongXunAssembleCompilation hongXunAssembleCompilation = new HongXunAssembleCompilation();
		fillmsg(0, hongXunAssembleCompilation, item.getMaterialNum());
		map.put("inQuantity", hongXunAssembleCompilation.getInQuantity1());
		map.put("outQuantity", hongXunAssembleCompilation.getOutQuantity1());*/
	}

	@Override
	public List<Map<String, Object>> autotimp(String str) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		//List<String> listName = new ArrayList<String>();
		@SuppressWarnings("unchecked")
		List<HongXunProductionStock> hongXunProductionStocks = (List<HongXunProductionStock>) stockDao.quaryFuzzyWithpara("HongXunProductionStock", "materialNum", str); 	
		for(HongXunProductionStock item: hongXunProductionStocks){	
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
		List<HongXunProductionStock> hongXunProductionStocks = (List<HongXunProductionStock>) stockDao.quaryFuzzyWithpara("HongXunProductionStock", "materialNum", materialNum);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (hongXunProductionStocks != null) {
			for (HongXunProductionStock item : hongXunProductionStocks) {
				Map<String, Object> map = new HashMap<String, Object>();
				mapHongXunProductionStock(map,item);
				list.add(map);
			}
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> saveRow(HongXunProductionStock hongXunMaterialStock) {
		// TODO Auto-generated method stub
		iProductionDao.save(hongXunMaterialStock);
		Map<String,Object> map = new HashMap<String,Object>();
		mapHongXunProductionStock(map, hongXunMaterialStock);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.add(map);
		return list;
	}

	@Override
	public List<Map<String, Object>> updateRow(HongXunProductionStock hongXunMaterialStock) {
		// TODO Auto-generated method stub
		iProductionDao.update(hongXunMaterialStock);
		Map<String,Object> map = new HashMap<String,Object>();
		mapHongXunProductionStock(map, hongXunMaterialStock);
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
	public PageResults<HongXunProductionStock> getData(HongXunProductionStock hongXunProductionStock, Integer pageNo,
			Integer pageSize) {
		// TODO Auto-generated method stub
		return iProductionDao.findPageByFetchedHql("from HongXunProductionStock order by id asc", "select count(*) from HongXunProductionStock", pageNo, pageSize);
	}

	@Override
	public List<Map<String, Object>> deleteRow(HongXunProductionStock item) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();	
		iProductionDao.delete(item);	
		return list;
	}
	
	/*private void fillmsg(int month, HongXunAssembleCompilation hongXunAssembleCompilation, String materialNum){		
		//一对多时用SQL查找会出问题 select instoreDate 
		String sql = "from HongXunProductionItemInStock where date_format(date,'%Y-%m')=date_format(now(),'%Y-%m')";
		@SuppressWarnings("unchecked")
		List<HongXunProductionItemInStock> hongXunProductionItemInStocks = (List<HongXunProductionItemInStock>) stockDao.quarywithpara(sql);
		for(HongXunProductionItemInStock item: hongXunProductionItemInStocks){ 
			HongXunPoN hongXunPoN = stockDao.hongXunPoNFindById(item.getPoNID());
			//System.out.println(hongXunPurchaseItem.getMaterialNum());
			if(hongXunPoN.getMaterialNo().equals(materialNum)){
				fillInQuantity(month, hongXunAssembleCompilation, item.getQuantity());
			}
		}
		
		//一对多时用SQL查找会出问题 select instoreDate 
		sql = "from HongXunProductionNoLimitItemInStock where date_format(date,'%Y-%m')=date_format(now(),'%Y-%m')";
		@SuppressWarnings("unchecked")
		List<HongXunProductionNoLimitItemInStock> hongXunProductionNoLimitItemInStocks = (List<HongXunProductionNoLimitItemInStock>) stockDao.quarywithpara(sql);
		for(HongXunProductionNoLimitItemInStock item: hongXunProductionNoLimitItemInStocks){ 	
			//System.out.println(hongXunPurchaseItem.getMaterialNum());
			if(item.getMaterialNum().equals(materialNum)){
				fillInQuantity(month, hongXunAssembleCompilation, item.getQuantity());
			}
		}
		
		sql = "from HongXunProductionItemOutStock where date_format(date,'%Y-%m')=date_format(now(),'%Y-%m')";
		@SuppressWarnings("unchecked")
		List<HongXunProductionItemOutStock> hongXunMaterialItemOutStocks = (List<HongXunProductionItemOutStock>) stockDao.quarywithpara(sql);
		//System.out.println("hongXunMaterialItemOutStocks.size(): " + hongXunMaterialItemOutStocks.size());
		for(HongXunProductionItemOutStock item: hongXunMaterialItemOutStocks){ 		
			HongXunPoN hongXunPoN = stockDao.hongXunPoNFindById(item.getPoNID());
			//System.out.println("hongXunProductionStock.getMaterialNum(): " + hongXunProductionStock.getMaterialNum());
			if(hongXunPoN.getMaterialNo().equals(materialNum)){
				//System.out.println("materialNum: " + materialNum);
				fillOutQuantity(month, hongXunAssembleCompilation, item.getQuantity());
			}	
		}
		
		sql = "from HongXunProductionNoLimitItemOutStock where date_format(date,'%Y-%m')=date_format(now(),'%Y-%m')";
		@SuppressWarnings("unchecked")
		List<HongXunProductionNoLimitItemOutStock> hongXunProductionNoLimitItemOutStocks = (List<HongXunProductionNoLimitItemOutStock>) stockDao.quarywithpara(sql);
		for(HongXunProductionNoLimitItemOutStock item: hongXunProductionNoLimitItemOutStocks){ 	
			//System.out.println(hongXunPurchaseItem.getMaterialNum());
			if(item.getMaterialNum().equals(materialNum)){
				fillOutQuantity(month, hongXunAssembleCompilation, item.getQuantity());
			}
		}
	
		sql = "from HongXunDeliveryNum where date_format(date,'%Y-%m')=date_format(now(),'%Y-%m')";
		@SuppressWarnings("unchecked")
		List<HongXunDeliveryNum> hongXunDeliveryNums = (List<HongXunDeliveryNum>) stockDao.quarywithpara(sql);
		for(HongXunDeliveryNum item: hongXunDeliveryNums){ 	
			//System.out.println(hongXunPurchaseItem.getMaterialNum());
			HongXunDeliveryItem hongXunDeliveryItem = new HongXunDeliveryItem();
			hongXunDeliveryItem.setDeliveryNumID(item.getIdc());
			List<HongXunDeliveryItem> hongXunDeliveryItems = stockDao.quary(hongXunDeliveryItem);
			for(HongXunDeliveryItem item1: hongXunDeliveryItems){ 	
				if(item1.getMaterialNo().equals(materialNum)){			
					fillOutQuantity(month, hongXunAssembleCompilation, item1.getSendQuantity());
				}
			}	
		}

	}*/
	
	/*private void fillInQuantity(int month, HongXunAssembleCompilation hongXunAssembleCompilation, int instockQuantity){
		
			if(hongXunAssembleCompilation.getInQuantity1()==null){
				hongXunAssembleCompilation.setInQuantity1(instockQuantity);
			}else{
				hongXunAssembleCompilation.setInQuantity1(hongXunAssembleCompilation.getInQuantity1() + instockQuantity);
			}
		
	}
	
	private void fillOutQuantity(int month, HongXunAssembleCompilation hongXunAssembleCompilation, int outstockQuantity){
		
			if(hongXunAssembleCompilation.getOutQuantity1()==null){
				hongXunAssembleCompilation.setOutQuantity1(outstockQuantity);
			}else{
				hongXunAssembleCompilation.setOutQuantity1(hongXunAssembleCompilation.getOutQuantity1() + outstockQuantity);
			}
	}*/

}
