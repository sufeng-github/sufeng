package paul.sydney.service.weld.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import paul.sydney.commen.result.PageResults;
import paul.sydney.dao.IOrderNumDao;
import paul.sydney.dao.IPonDao;
import paul.sydney.dao.IWeldDao;
import paul.sydney.dao.StockDao;
import paul.sydney.model.HongXunDeliveryWeldItem;
import paul.sydney.model.HongXunDeliveryWeldNum;
import paul.sydney.model.HongXunPoN;
import paul.sydney.model.HongXunProductionStock;
import paul.sydney.model.HongXunProductionWeldStock;
import paul.sydney.model.HongXunWeldCompilation;
import paul.sydney.model.HongXunWeldItemInStock;
import paul.sydney.model.HongXunWeldItemOutStock;
import paul.sydney.model.HongXunWeldNoLimitItemInStock;
import paul.sydney.model.HongXunWeldNoLimitItemOutStock;
import paul.sydney.service.weld.ServiceProductionWeldStockInf;
@Transactional
@Service("serviceProductionWeldStock")
public class ServiceProductionWeldStockImpl implements ServiceProductionWeldStockInf{
	
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
	@Autowired
	IOrderNumDao iOrderDao;
	public void setStockDao(IOrderNumDao iOrderDao) {
		this.iOrderDao = iOrderDao;
	}
	@Autowired
	IPonDao iPonDao;
	public void setStockDao(IPonDao iPonDao) {
		this.iPonDao = iPonDao;
	}
	@Override
	public PageResults<HongXunProductionWeldStock> getData(HongXunProductionWeldStock hongXunProductionWeldStock, Integer pageNo,
			Integer pageSize) {
		// TODO Auto-generated method stub
		return iWeldDao.findPageByFetchedHql("from HongXunProductionWeldStock order by id asc", "select count(*) from HongXunProductionWeldStock", pageNo, pageSize);
	}
	@Override
	public List<Map<String, Object>> getEntity() {
		// TODO Auto-generated method stub
		return iWeldDao.getEntity();
	}
	
	private void mapHongXunProductionStock(Map<String, Object> map, HongXunProductionWeldStock item) {
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
/*		HongXunWeldCompilation hongXunWeldCompilation = new HongXunWeldCompilation();
		fillmsg(0, hongXunWeldCompilation, item.getMaterialNum());
		map.put("inQuantity", hongXunWeldCompilation.getInQuantity1());
		map.put("outQuantity", hongXunWeldCompilation.getOutQuantity1());*/
	}

	@Override
	public List<Map<String, Object>> autotimp(String str) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		//List<String> listName = new ArrayList<String>();
		@SuppressWarnings("unchecked")
		List<HongXunProductionWeldStock> hongXunProductionStocks = (List<HongXunProductionWeldStock>) stockDao.quaryFuzzyWithpara("HongXunProductionWeldStock", "materialNum", str); 	
		for(HongXunProductionWeldStock item: hongXunProductionStocks){	
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
		List<HongXunProductionWeldStock> hongXunProductionStocks = (List<HongXunProductionWeldStock>) stockDao.quaryFuzzyWithpara("HongXunProductionWeldStock", "materialNum", materialNum);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (hongXunProductionStocks != null) {
			for (HongXunProductionWeldStock item : hongXunProductionStocks) {
				Map<String, Object> map = new HashMap<String, Object>();
				mapHongXunProductionStock(map,item);
				list.add(map);
			}
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> saveRow(HongXunProductionWeldStock hongXunProductionWeldStock) {
		// TODO Auto-generated method stub
		iWeldDao.save(hongXunProductionWeldStock);
		Map<String,Object> map = new HashMap<String,Object>();
		mapHongXunProductionStock(map, hongXunProductionWeldStock);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.add(map);
		return list;
	}

	@Override
	public List<Map<String, Object>> updateRow(HongXunProductionWeldStock hongXunProductionWeldStock) {
		// TODO Auto-generated method stub
		iWeldDao.update(hongXunProductionWeldStock);
		Map<String,Object> map = new HashMap<String,Object>();
		mapHongXunProductionStock(map, hongXunProductionWeldStock);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.add(map);
		return list;
	}
	
	@Override
	public List<Map<String, Object>> deleteRow(HongXunProductionWeldStock item) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();	
		iWeldDao.delete(item);	
		return list;
	}
	
	@Override
	public List<Map<String, Object>> delAllRows() {
		// TODO Auto-generated method stub
		List<HongXunProductionWeldStock>  hongXunProductionWeldStocks = iWeldDao.quary(new HongXunProductionWeldStock());
		iWeldDao.deleteWeldItemList(hongXunProductionWeldStocks);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		return list;
	}

	private void fillmsg(int month, HongXunWeldCompilation hongXunWeldCompilation, String materialNum){
		
		//一对多时用SQL查找会出问题 select instoreDate 
		String sql = "from HongXunWeldItemInStock where date_format(date,'%Y-%m')=date_format(now(),'%Y-%m')";
		@SuppressWarnings("unchecked")
		List<HongXunWeldItemInStock> hongXunWeldItemInStocks = (List<HongXunWeldItemInStock>) stockDao.quarywithpara(sql);
		for(HongXunWeldItemInStock item: hongXunWeldItemInStocks){ 
			HongXunPoN hongXunPoN = iPonDao.hongXunPoNFindById(item.getPoNID());
			//System.out.println(hongXunPurchaseItem.getMaterialNum());
			if(hongXunPoN.getMaterialNo().equals(materialNum)){
				fillInQuantity(month, hongXunWeldCompilation, item.getQuantity());
			}
		}
		//一对多时用SQL查找会出问题 select instoreDate 
		sql = "from HongXunWeldNoLimitItemInStock where date_format(date,'%Y-%m')=date_format(now(),'%Y-%m')";
		@SuppressWarnings("unchecked")
		List<HongXunWeldNoLimitItemInStock> hongXunWeldNoLimitItemInStocks = (List<HongXunWeldNoLimitItemInStock>) stockDao.quarywithpara(sql);
		for(HongXunWeldNoLimitItemInStock item: hongXunWeldNoLimitItemInStocks){ 	
			//System.out.println(hongXunPurchaseItem.getMaterialNum());
			if(item.getMaterialNum().equals(materialNum)){
				fillInQuantity(month, hongXunWeldCompilation, item.getQuantity());
			}
		}
		
		sql = "from HongXunWeldItemOutStock where date_format(date,'%Y-%m')=date_format(now(),'%Y-%m')";
		@SuppressWarnings("unchecked")
		List<HongXunWeldItemOutStock> hongXunWeldItemOutStocks = (List<HongXunWeldItemOutStock>) stockDao.quarywithpara(sql);
		//System.out.println("hongXunMaterialItemOutStocks.size(): " + hongXunMaterialItemOutStocks.size());
		for(HongXunWeldItemOutStock item: hongXunWeldItemOutStocks){ 		
			HongXunPoN hongXunPoN = iPonDao.hongXunPoNFindById(item.getPoNID());
			//System.out.println("hongXunProductionStock.getMaterialNum(): " + hongXunProductionStock.getMaterialNum());
			if(hongXunPoN.getMaterialNo().equals(materialNum)){
				//System.out.println("materialNum: " + materialNum);
				fillOutQuantity(month, hongXunWeldCompilation, item.getQuantity());
			}	
		}
		
		
		sql = "from HongXunWeldNoLimitItemOutStock where date_format(date,'%Y-%m')=date_format(now(),'%Y-%m')";
		@SuppressWarnings("unchecked")
		List<HongXunWeldNoLimitItemOutStock> hongXunWeldNoLimitItemOutStocks = (List<HongXunWeldNoLimitItemOutStock>) stockDao.quarywithpara(sql);
		for(HongXunWeldNoLimitItemOutStock item: hongXunWeldNoLimitItemOutStocks){ 	
			//System.out.println(hongXunPurchaseItem.getMaterialNum());
			if(item.getMaterialNum().equals(materialNum)){
				fillOutQuantity(month, hongXunWeldCompilation, item.getQuantity());
			}
		}
	
		sql = "from HongXunDeliveryWeldNum where date_format(date,'%Y-%m')=date_format(now(),'%Y-%m')";
		@SuppressWarnings("unchecked")
		List<HongXunDeliveryWeldNum> hongXunDeliveryWeldNums = (List<HongXunDeliveryWeldNum>) stockDao.quarywithpara(sql);
		for(HongXunDeliveryWeldNum item: hongXunDeliveryWeldNums){ 	
			//System.out.println(hongXunPurchaseItem.getMaterialNum());
			HongXunDeliveryWeldItem hongXunDeliveryWeldItem = new HongXunDeliveryWeldItem();
			hongXunDeliveryWeldItem.setDeliveryNumID(item.getIdc());
			List<HongXunDeliveryWeldItem> hongXunDeliveryWeldItems = iWeldDao.quary(hongXunDeliveryWeldItem);
			for(HongXunDeliveryWeldItem item1: hongXunDeliveryWeldItems){ 	
				if(item1.getMaterialNo().equals(materialNum)){			
					fillOutQuantity(month, hongXunWeldCompilation, item1.getSendQuantity());
				}
			}	
		}
		
	}
	
	private void fillInQuantity(int month, HongXunWeldCompilation hongXunWeldCompilation, int instockQuantity){	
			if(hongXunWeldCompilation.getInQuantity1()==null){
				hongXunWeldCompilation.setInQuantity1(instockQuantity);
			}else{
				hongXunWeldCompilation.setInQuantity1(hongXunWeldCompilation.getInQuantity1() + instockQuantity);
			}		
	}
	
	private void fillOutQuantity(int month, HongXunWeldCompilation hongXunWeldCompilation, int outstockQuantity){
	
			if(hongXunWeldCompilation.getOutQuantity1()==null){
				hongXunWeldCompilation.setOutQuantity1(outstockQuantity);
			}else{
				hongXunWeldCompilation.setOutQuantity1(hongXunWeldCompilation.getOutQuantity1() + outstockQuantity);
			}	
	}

	
}
