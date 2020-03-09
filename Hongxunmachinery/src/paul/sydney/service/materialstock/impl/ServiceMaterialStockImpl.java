package paul.sydney.service.materialstock.impl;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.tagext.PageData;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import paul.sydney.commen.result.BaseResult;
import paul.sydney.commen.result.PageResults;
import paul.sydney.dao.IMaterialStockDao;
import paul.sydney.dao.IPurchaseDao;
import paul.sydney.dao.IPurchaseItemDao;
import paul.sydney.dao.StockDao;
import paul.sydney.model.HongXunMaterialCompilation;
import paul.sydney.model.HongXunMaterialItemOutStock;
import paul.sydney.model.HongXunMaterialNoLimitInStock;
import paul.sydney.model.HongXunMaterialNoLimitOutStock;
import paul.sydney.model.HongXunMaterialStock;
import paul.sydney.model.HongXunProductionWeldStock;
import paul.sydney.model.HongXunPurchaseDeNum;
import paul.sydney.model.HongXunPurchaseItem;
import paul.sydney.model.HongXunPurchaseItemInStock;
import paul.sydney.model.HongXunPurchaseNum;
import paul.sydney.service.materialstock.ServiceMaterialStockInf;
@Transactional
@Service("serviceMaterialStock")
public class ServiceMaterialStockImpl implements ServiceMaterialStockInf{
	
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
	IPurchaseItemDao iPurchaseItemDao;
	public void setStockDao(IPurchaseItemDao iPurchaseItemDao) {
		this.iPurchaseItemDao = iPurchaseItemDao;
	}
	@Autowired
	IMaterialStockDao iMaterialStockDao;
	public void setStockDao(IMaterialStockDao iMaterialStockDao) {
		this.iMaterialStockDao = iMaterialStockDao;
	}

	
	@Override
	public PageResults<HongXunMaterialStock> getData(HongXunMaterialStock hongXunMaterialStock,Integer pageNo, Integer pageSize) {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<String>();
		StringBuilder hql=new StringBuilder("from HongXunMaterialStock where 1=1");
		//String类型属性
		if (hongXunMaterialStock.getMaterialNum() != null) {
			if(hongXunMaterialStock.getMaterialNum().indexOf("*")>-1){
				hql.append(" and materialNum like ?");
				list.add("%" + hongXunMaterialStock.getMaterialNum().replace("*", "") + "%");				
			}else{
				hql.append(" and materialNum = ?");
				list.add(hongXunMaterialStock.getMaterialNum());
			}
		}
		if (hongXunMaterialStock.getSpecification() != null) {
			if(hongXunMaterialStock.getSpecification().indexOf("*")>-1){
				hql.append(" and specification like ?");
				list.add("%" + hongXunMaterialStock.getSpecification().replace("*", "") + "%");				
			}else{
				hql.append(" and specification =?");
				list.add(hongXunMaterialStock.getSpecification());
			}
		}
		if (hongXunMaterialStock.getAlarm() != null) {
			if(hongXunMaterialStock.getAlarm().indexOf("*")>-1){
				hql.append(" and alarm like ?");
				list.add("%" + hongXunMaterialStock.getAlarm().replace("*", "") + "%");				
			}else{
				hql.append(" and alarm =?");
				list.add(hongXunMaterialStock.getAlarm());
			}
		}
		System.out.println(hql.toString() + "---" + list.toArray());
		
		return iMaterialStockDao.findPageByFetchedHql(hql.toString(), "select count(*) " + hql.toString(), pageNo, pageSize,list.toArray());
	}	

	
	@Override
	public List<Map<String, Object>> getEntity() {
		// TODO Auto-generated method stub
		return iMaterialStockDao.getEntity();
	}
	
	private void mapHongXunMaterialStock(Map<String, Object> map, HongXunMaterialStock item) {
		// TODO Auto-generated method stub	
		//System.out.println("test: " + item.getHongXunDataOne().getProductionOrderNum());
		map.put("idc", item.getIdc());
		map.put("materialNum", item.getMaterialNum());
		map.put("specification", item.getSpecification());
		map.put("quantity", item.getQuantity());
		map.put("unit", item.getUnit());
		map.put("safeQuantity", item.getSafeQuantity());
		//map.put("calQuantity", item.getCalQuantity());
		map.put("alarm", item.getAlarm());
		if(item.getDeliveryDate()!=null){
			map.put("deliveryDate", new SimpleDateFormat("yyyy-MM-dd").format(item.getDeliveryDate()));
		}
		map.put("itemQuantity", item.getItemQuantity());
		map.put("inRoadQuantity", item.getInRoadQuantity());
		map.put("purQuantity", item.getPurQuantity());
		map.put("localCode", item.getLocalCode());
		map.put("remark", item.getRemark());
		map.put("inQuantity", item.getInQuantity());
		map.put("outQuantity", item.getOutQuantity());
		//map.put("purchaseDeId", item.getPurchaseDeId());
		
/*		HongXunMaterialCompilation hongXunMaterialCompilation = new HongXunMaterialCompilation();
		fillmsg(0, hongXunMaterialCompilation, item.getMaterialNum());
		map.put("inQuantity", hongXunMaterialCompilation.getInQuantity1());
		map.put("outQuantity", hongXunMaterialCompilation.getOutQuantity1());*/
	}

	@Override
	public List<Map<String, Object>> autotimp(String str) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		//List<String> listName = new ArrayList<String>();
		@SuppressWarnings("unchecked")
		List<HongXunMaterialStock> hongXunMaterialStocks = (List<HongXunMaterialStock>) stockDao.quaryFuzzyWithpara("HongXunMaterialStock", "materialNum", str); 	
		for(HongXunMaterialStock item: hongXunMaterialStocks){	
			Map<String,Object> map = new HashMap<String,Object>();
			//map.put("materialName", item.getMaterialName());	
			map.put("name", item.getMaterialNum());
			map.put("id", item.getIdc());  
			list.add(map);
		}
		return list;
	}


	@Override
	public List<Map<String, Object>> saveRow(HongXunMaterialStock hongXunMaterialStock) {
		// TODO Auto-generated method stub
		iMaterialStockDao.save(hongXunMaterialStock);
		Map<String,Object> map = new HashMap<String,Object>();
		mapHongXunMaterialStock(map, hongXunMaterialStock);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.add(map);
		return list;
	}

	@Override
	public List<Map<String, Object>> updateRow(HongXunMaterialStock hongXunMaterialStock) {
		// TODO Auto-generated method stub
		iMaterialStockDao.update(hongXunMaterialStock);
		Map<String,Object> map = new HashMap<String,Object>();
		mapHongXunMaterialStock(map, hongXunMaterialStock);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.add(map);
		return list;
	}


/*	@Override
	public List<Map<String, Object>> getMaterialDelivery(int purchaseDeId) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		HongXunMaterialStock hongXunMaterialStock = new HongXunMaterialStock();
		hongXunMaterialStock.setPurchaseDeId(purchaseDeId);
		List<HongXunMaterialStock> hongXunMaterialStocks = stockDao.quary(hongXunMaterialStock);
		for(HongXunMaterialStock item : hongXunMaterialStocks){
			Map<String,Object> map = new HashMap<String,Object>();
			if(item.getAlarm()==null){
				item.setAlarm("已下单");
				if(item.getPurchaseId()!=null){
					HongXunPurchaseNum hongXunPurchaseNum = stockDao.purchaseNumFindById(item.getPurchaseId());
					if(hongXunPurchaseNum != null && hongXunPurchaseNum.getPurchaseDate() != null){
						String date = new SimpleDateFormat("yyyy-MM-dd").format(hongXunPurchaseNum.getPurchaseDate());
						map.put("date", date);
					}
				}
			}
			if(item.getPurQuantity()==null){
				item.setPurQuantity(item.getSafeQuantity());
			}
			mapHongXunMaterialStock(map,item);
			list.add(map);
		}
		
		return list;
	}*/
	
/*	@Override
	public List<Map<String, Object>> getMaterialAllDelivery() {
		// TODO Auto-generated method stub
		List<HongXunPurchaseDeNum> hongXunPurchaseDeNums = stockDao.quary(new HongXunPurchaseDeNum());
   		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
   		for(int i=hongXunPurchaseDeNums.size()-1; i>=0; i--){
   			HongXunMaterialStock hongXunMaterialStock = new HongXunMaterialStock();
   			hongXunMaterialStock.setPurchaseDeId(hongXunPurchaseDeNums.get(i).getIdc());
   			String date = new SimpleDateFormat("yyyy-MM-dd").format(hongXunPurchaseDeNums.get(i).getPurchaseDeDate());
   			List<HongXunMaterialStock> hongXunMaterialStocks = stockDao.quary(hongXunMaterialStock);
   			for(HongXunMaterialStock item : hongXunMaterialStocks){
   				if(item.getAlarm() !=null && item.getAlarm().equals("已提单")){
   					Map<String,Object> map = new HashMap<String,Object>();
   					if(item.getPurQuantity()==null){
   						item.setPurQuantity(item.getSafeQuantity());
   					}
   	   				mapHongXunMaterialStock(map,item);
   	   				map.put("date", date);
   	   				list.add(map);
   				}   				
   			}
   		}		
		return list;
	}*/
	
	@Override
	public List<Map<String, Object>> delAllRows() {
		// TODO Auto-generated method stub
		List<HongXunMaterialStock>  hongXunMaterialStocks = iMaterialStockDao.quary(new HongXunMaterialStock());
		//System.out.println("hongXunMaterialStocks :" +  hongXunMaterialStocks.size());
		iMaterialStockDao.deleteMaterialItemList(hongXunMaterialStocks);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		return list;
	}

	private void fillmsg(int month, HongXunMaterialCompilation hongXunMaterialCompilation, String materialNum){
		//一对多时用SQL查找会出问题 select instoreDate 
		
		String sql = "from HongXunPurchaseItemInStock where date_format(instoreDate,'%Y-%m')=date_format(now(),'%Y-%m')";
		@SuppressWarnings("unchecked")
		List<HongXunPurchaseItemInStock> hongXunPurchaseItemInStocks = (List<HongXunPurchaseItemInStock>) stockDao.quarywithpara(sql);
		for(HongXunPurchaseItemInStock item: hongXunPurchaseItemInStocks){ 
			HongXunPurchaseItem hongXunPurchaseItem = iPurchaseItemDao.purchaseItemFindById(item.getPurchaseItemID());
			//System.out.println(hongXunPurchaseItem.getMaterialNum());
			if(hongXunPurchaseItem.getMaterialNum().equals(materialNum)){
				fillInQuantity(month, hongXunMaterialCompilation, item.getRealQuantity());
			}
		}
		sql = "from HongXunMaterialNoLimitInStock where date_format(date,'%Y-%m')=date_format(now(),'%Y-%m')";
		@SuppressWarnings("unchecked")
		List<HongXunMaterialNoLimitInStock> hongXunMaterialNoLimitInStocks = (List<HongXunMaterialNoLimitInStock>) stockDao.quarywithpara(sql);
		for(HongXunMaterialNoLimitInStock item: hongXunMaterialNoLimitInStocks){ 
			 HongXunMaterialStock materialStock = iMaterialStockDao.hongXunMaterialFindById(item.getMaterialStockID());
			//System.out.println(hongXunPurchaseItem.getMaterialNum());
			if(materialStock.getMaterialNum().equals(materialNum)){
				fillInQuantity(month, hongXunMaterialCompilation, item.getQuantity());
			}
		}
		
		sql = "from HongXunMaterialItemOutStock where date_format(date,'%Y-%m')=date_format(now(),'%Y-%m')";
		@SuppressWarnings("unchecked")
		List<HongXunMaterialItemOutStock> hongXunMaterialItemOutStocks = (List<HongXunMaterialItemOutStock>) stockDao.quarywithpara(sql);
		//System.out.println("hongXunMaterialItemOutStocks.size(): " + hongXunMaterialItemOutStocks.size());
		for(HongXunMaterialItemOutStock item: hongXunMaterialItemOutStocks){ 
			HongXunMaterialStock hxMaterialStock = iMaterialStockDao.hongXunMaterialFindById(item.getMaterialStockID());
			//System.out.println("hongXunMaterialStock.getMaterialNum(): " + hongXunMaterialStock.getMaterialNum());
			if(hxMaterialStock.getMaterialNum().equals(materialNum)){
				//System.out.println("materialNum: " + materialNum);
				fillOutQuantity(month, hongXunMaterialCompilation, item.getQuantity());
			}	
		}		
		
		sql = "from HongXunMaterialNoLimitOutStock where date_format(date,'%Y-%m')=date_format(now(),'%Y-%m')";
		@SuppressWarnings("unchecked")
		List<HongXunMaterialNoLimitOutStock> hongXunMaterialNoLimitOutStocks = (List<HongXunMaterialNoLimitOutStock>) stockDao.quarywithpara(sql);
		//System.out.println("hongXunMaterialItemOutStocks.size(): " + hongXunMaterialItemOutStocks.size());
		for(HongXunMaterialNoLimitOutStock item: hongXunMaterialNoLimitOutStocks){ 
			HongXunMaterialStock hxMaterialStock = iMaterialStockDao.hongXunMaterialFindById(item.getMaterialStockID());
			//System.out.println("hongXunMaterialStock.getMaterialNum(): " + hongXunMaterialStock.getMaterialNum());
			if(hxMaterialStock.getMaterialNum().equals(materialNum)){
				System.out.println("materialNum: " + materialNum);
				fillOutQuantity(month, hongXunMaterialCompilation, item.getQuantity());
			}	
		}		
	}
	
	private void fillInQuantity(int month, HongXunMaterialCompilation hongXunMaterialCompilation, int instockQuantity){
		
			if(hongXunMaterialCompilation.getInQuantity1()==null){
				hongXunMaterialCompilation.setInQuantity1(instockQuantity);
			}else{
				hongXunMaterialCompilation.setInQuantity1(hongXunMaterialCompilation.getInQuantity1() + instockQuantity);
			}		
	}
	
	private void fillOutQuantity(int month, HongXunMaterialCompilation hongXunMaterialCompilation, int outstockQuantity){
			if(hongXunMaterialCompilation.getOutQuantity1()==null){
				hongXunMaterialCompilation.setOutQuantity1(outstockQuantity);
			}else{
				hongXunMaterialCompilation.setOutQuantity1(hongXunMaterialCompilation.getOutQuantity1() + outstockQuantity);
			}
	}

	@Override
	public List<Map<String, Object>> deleteRow(HongXunMaterialStock hongXunMaterialStock) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		if(hongXunMaterialStock.getOutQuantity()==null || hongXunMaterialStock.getOutQuantity()==0){
			iMaterialStockDao.delete(hongXunMaterialStock);
		}
		return list;
	}
/*
	@Override
	public List<Map<String, Object>> moveRow(HongXunMaterialStock item) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		HongXunMaterialStock hongXunMaterialStock = stockDao.hongXunMaterialFindById(item.getIdc());
		hongXunMaterialStock.setAlarm("报警");
		hongXunMaterialStock.setPurchaseDeId(null);
		stockDao.update(hongXunMaterialStock);
		return list;
	}*/
	@Override
	public BaseResult deleteRow(int id) {
		// TODO Auto-generated method stub
		BaseResult baseResult = new BaseResult();
		iMaterialStockDao.deleteById(id);
		return baseResult;
	}


}
