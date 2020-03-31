package paul.sydney.service.production.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import paul.sydney.commen.result.PageResults;
import paul.sydney.dao.IProductionDao;
import paul.sydney.dao.IProductionDeliveryItemDao;
import paul.sydney.dao.IProductionItemInStockDao;
import paul.sydney.dao.StockDao;
import paul.sydney.model.HongXunDeliveryItem;
import paul.sydney.model.HongXunProductionItemInStock;
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

	@Autowired
	IProductionItemInStockDao iProductionItemInStockDao;
	public void setStockDao(IProductionItemInStockDao iProductionItemInStockDao) {
		this.iProductionItemInStockDao = iProductionItemInStockDao;
	}
	
	@Autowired
	IProductionDeliveryItemDao iProductionDeliveryItemDao;
	public void setStockDao(IProductionDeliveryItemDao iProductionDeliveryItemDao) {
		this.iProductionDeliveryItemDao = iProductionDeliveryItemDao;
	}
	
	@Override
	public List<Map<String, Object>> getEntity() {
        return iProductionDao.getEntity();
	}
	
	@Override
	public List<Map<String, Object>> autotimp(String str, String name) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		List<String> listObj = new ArrayList<String>();
		StringBuilder hql=new StringBuilder("from HongXunProductionStock where 1=1");
		hql.append(" and materialNum like ?");
		listObj.add("%" + str + "%");
		hql.append(" and name = ?");
		listObj.add(name);
		List<HongXunProductionStock> hongXunProductionStocks = iProductionDao.getListByHQL(hql.toString(), listObj.toArray());
		for(HongXunProductionStock item: hongXunProductionStocks){	
			Map<String,Object> map = new HashMap<String,Object>();
			//map.put("materialName", item.getMaterialName());	
			map.put("name", item.getMaterialNum());
			map.put("id", item.getIdc());  
			list.add(map);
		}
		return list;
	}


	private void saveItemInStock(Integer thisInQuanatity, HongXunProductionStock hongXunProductionStock, String remark){
		if(thisInQuanatity != null){
			HongXunProductionItemInStock hongXunProductionItemInStock = new HongXunProductionItemInStock();
			hongXunProductionItemInStock.setHongXunProductionStock(hongXunProductionStock);
			hongXunProductionItemInStock.setDate(new Date());
			hongXunProductionItemInStock.setRemark(remark);
			hongXunProductionItemInStock.setQuantity(thisInQuanatity);
			iProductionItemInStockDao.save(hongXunProductionItemInStock);
		}	
	}
	
	@Override
	public PageResults<HongXunProductionStock> outStock(int proIdc, int itemOutIdc, int sendQuantity,
			PageResults<HongXunProductionStock> result) {
		// TODO Auto-generated method stub
		HongXunProductionStock hongXunProductionStock = iProductionDao.get(proIdc);
		hongXunProductionStock.setQuantity(hongXunProductionStock.getQuantity()-sendQuantity);
		HongXunDeliveryItem hongXunDeliveryItem = iProductionDeliveryItemDao.get(itemOutIdc);
		hongXunDeliveryItem.setStatus("完成");
		iProductionDeliveryItemDao.updateEntity(hongXunDeliveryItem);
		return result;
	}
	
	@Override
	public PageResults<HongXunProductionStock> scanInStock(HongXunProductionStock item, PageResults<HongXunProductionStock> result) {
		// TODO Auto-generated method stub	
		Integer thisInQuantity = item.getThisInQuantity();
		String name = item.getName();
		if(item.getIdc()!=0){
			item = iProductionDao.get(item.getIdc());
			item.setName(name);
		}
		if(item.getInQuantity()==null){
			item.setInQuantity(0);
		}
		if(item.getQuantity()==null){
			item.setQuantity(0);
		}
		item.setInQuantity(item.getInQuantity()+thisInQuantity);
		item.setQuantity(item.getQuantity()+thisInQuantity);
		item.setLastInQuantity(thisInQuantity);
		
		iProductionDao.updateEntity(item);
		saveItemInStock(thisInQuantity, item,"扫码入库");
		return result;
	}	
	
	@Override
	public void saveRow(HongXunProductionStock hongXunProductionStock) {
		// TODO Auto-generated method stub
		Integer thisInQuanatity = hongXunProductionStock.getThisInQuantity();
		iProductionDao.save(hongXunProductionStock);
		saveItemInStock(thisInQuanatity, hongXunProductionStock,"手动入库");
	}
	
	@Override
	public void updateRow(HongXunProductionStock hongXunProductionStock) {
		// TODO Auto-generated method stub	
		Integer thisInQuanatity = hongXunProductionStock.getThisInQuantity();
		iProductionDao.update(hongXunProductionStock);
		saveItemInStock(thisInQuanatity, hongXunProductionStock,"手动入库");
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
	
		List<String> list = new ArrayList<String>();
		StringBuilder hql=new StringBuilder("from HongXunProductionStock where 1=1");
		//String类型属性
		if (hongXunProductionStock.getMaterialNum() != null) {
			if(hongXunProductionStock.getMaterialNum().indexOf("*")>-1){
				hql.append(" and materialNum like ?");
				list.add("%" + hongXunProductionStock.getMaterialNum().replace("*", "") + "%");				
			}else{
				hql.append(" and materialNum = ?");
				list.add(hongXunProductionStock.getMaterialNum());
			}
		}
		if (hongXunProductionStock.getSpecification() != null) {
			if(hongXunProductionStock.getSpecification().indexOf("*")>-1){
				hql.append(" and specification like ?");
				list.add("%" + hongXunProductionStock.getSpecification().replace("*", "") + "%");				
			}else{
				hql.append(" and specification =?");
				list.add(hongXunProductionStock.getSpecification());
			}
		}
		if (hongXunProductionStock.getName() != null) {
			hql.append(" and name =?");
			list.add(hongXunProductionStock.getName());
		}
		return iProductionDao.findPageByFetchedHql(hql.toString(), "select count(*) " + hql.toString(), pageNo, pageSize,list.toArray());
	}	

	@Override
	public List<Map<String, Object>> deleteRow(HongXunProductionStock item) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();	
		iProductionDao.delete(item);	
		return list;
	}





}
