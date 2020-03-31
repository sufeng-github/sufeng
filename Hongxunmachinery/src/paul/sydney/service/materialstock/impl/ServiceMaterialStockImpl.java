package paul.sydney.service.materialstock.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import paul.sydney.commen.result.BaseResult;
import paul.sydney.commen.result.PageResults;
import paul.sydney.dao.IMaterialStockDao;
import paul.sydney.dao.IPurchaseDao;
import paul.sydney.dao.IPurchaseItemDao;
import paul.sydney.dao.StockDao;
import paul.sydney.model.HongXunMaterialStock;
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
		if (hongXunMaterialStock.getStatus() != null) {
			if(hongXunMaterialStock.getStatus().indexOf("*")>-1){
				hql.append(" and status like ?");
				list.add("%" + hongXunMaterialStock.getStatus().replace("*", "") + "%");				
			}else{
				hql.append(" and status =?");
				list.add(hongXunMaterialStock.getStatus());
			}
		}
		
		return iMaterialStockDao.findPageByFetchedHql(hql.toString(), "select count(*) " + hql.toString(), pageNo, pageSize,list.toArray());
	}	

	
	@Override
	public List<Map<String, Object>> getEntity() {
		// TODO Auto-generated method stub
		return iMaterialStockDao.getEntity();
	}
	

	@Override
	public List<Map<String, Object>> autotimp(String str) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		if(str.length()>3){
			StringBuilder hql=new StringBuilder("from HongXunMaterialStock where materialNum like ?");
			List<HongXunMaterialStock> hongXunMaterialStocks = iMaterialStockDao.getListByHQL(hql.toString(),"%" + str + "%");
			for(HongXunMaterialStock item: hongXunMaterialStocks){	
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
	public List<Map<String, Object>> saveRow(HongXunMaterialStock hongXunMaterialStock) {
		// TODO Auto-generated method stub
		if(hongXunMaterialStock.getSafeQuantity()==null){
			hongXunMaterialStock.setSafeQuantity(0);
		}
		if(hongXunMaterialStock.getItemQuantity()==null){
			hongXunMaterialStock.setItemQuantity(0);
		}
		if(hongXunMaterialStock.getInRoadQuantity()==null){
			hongXunMaterialStock.setInRoadQuantity(0);
		}
		if(hongXunMaterialStock.getQuantity()==null){
			hongXunMaterialStock.setQuantity(0);
		}
		iMaterialStockDao.saveEntity(hongXunMaterialStock);
		Map<String,Object> map = new HashMap<String,Object>();
		//mapHongXunMaterialStock(map, hongXunMaterialStock);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.add(map);
		return list;
	}

	@Override
	public List<Map<String, Object>> updateRow(HongXunMaterialStock hongXunMaterialStock) {
		// TODO Auto-generated method stub
		iMaterialStockDao.updateEntity(hongXunMaterialStock);
		Map<String,Object> map = new HashMap<String,Object>();
		//mapHongXunMaterialStock(map, hongXunMaterialStock);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.add(map);
		return list;
	}

	
	@Override
	public List<Map<String, Object>> delAllRows() {
		// TODO Auto-generated method stub
		List<HongXunMaterialStock>  hongXunMaterialStocks = iMaterialStockDao.quary(new HongXunMaterialStock());
		iMaterialStockDao.deleteAll(hongXunMaterialStocks);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		return list;
	}

	@Override
	public BaseResult deleteRow(HongXunMaterialStock hongXunMaterialStock) {
		// TODO Auto-generated method stub
		BaseResult baseResult = new BaseResult();
		try {					
			iMaterialStockDao.delete(hongXunMaterialStock);			
		} catch (Exception e) {
			baseResult.setMessage("删除失败");
			return baseResult;
		}
		return baseResult;		
	}
}
