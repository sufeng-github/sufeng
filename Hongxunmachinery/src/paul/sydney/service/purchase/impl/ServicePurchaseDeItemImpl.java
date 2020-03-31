package paul.sydney.service.purchase.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import paul.sydney.commen.result.PageResults;
import paul.sydney.dao.IMaterialStockDao;
import paul.sydney.dao.IPurchaseDao;
import paul.sydney.dao.IPurchaseDeItemDao;
import paul.sydney.dao.IPurchaseDeNumDao;
import paul.sydney.dao.StockDao;
import paul.sydney.service.purchase.ServicePurchaseDeItemInf;
import paul.sydney.model.HongXunMaterialStock;
import paul.sydney.model.HongXunPurchaseDeItem;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("servicePurchaseDeItem")
public class ServicePurchaseDeItemImpl implements ServicePurchaseDeItemInf{
	
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
	IPurchaseDeNumDao iPurchaseDeNumDao;
	public void setStockDao(IPurchaseDeNumDao iPurchaseDeNumDao) {
		this.iPurchaseDeNumDao = iPurchaseDeNumDao;
	}
	@Autowired
	IPurchaseDeItemDao iPurchaseDeItemDao;
	public void setStockDao(IPurchaseDeItemDao iPurchaseDeItemDao) {
		this.iPurchaseDeItemDao = iPurchaseDeItemDao;
	}
	@Autowired
	IMaterialStockDao iMaterialStockDao;
	public void setStockDao(IMaterialStockDao iMaterialStockDao) {
		this.iMaterialStockDao = iMaterialStockDao;
	}
	
	@Override
	public Boolean deleteRow(HongXunPurchaseDeItem item) {
		// TODO Auto-generated method stub
		HongXunMaterialStock hongXunMaterialStock= iPurchaseDeItemDao.get(item.getIdc()).getHongXunMaterialStock();
		hongXunMaterialStock.setStatus(null);
		iMaterialStockDao.updateEntity(hongXunMaterialStock);
		iPurchaseDeItemDao.deleteSql("delete from HongXunPurchaseDeItem where idc = ?", item.getIdc());
		return true;	
	}
	
	@Override
	public void updateRow(HongXunPurchaseDeItem item) {
		// TODO Auto-generated method stub
		item = iPurchaseDeItemDao.get(item.getIdc());
		iPurchaseDeItemDao.updateEntity(item);
	}

	@Override
	public List<Map<String, Object>> getEntity() {
		// TODO Auto-generated method stub
		return iPurchaseDeItemDao.getEntity();
	}


	@Override
	public PageResults<HongXunPurchaseDeItem> getData(HongXunPurchaseDeItem hongXunPurchaseDeItem, Integer pageNo,
			Integer pageSize) {
		// TODO Auto-generated method stub
		List<Object> list = new ArrayList<Object>();
		StringBuilder hql=new StringBuilder("from HongXunPurchaseDeItem a where 1=1");
		//String类型属性
		if (hongXunPurchaseDeItem.getHongXunPurchaseDeNum().getIdc() != 0) {
			hql.append(" and a.hongXunPurchaseDeNum.idc = ?");
			list.add(hongXunPurchaseDeItem.getHongXunPurchaseDeNum().getIdc());			
		}
		if (hongXunPurchaseDeItem.getHongXunMaterialStock().getMaterialNum() != null) {
			if(hongXunPurchaseDeItem.getHongXunMaterialStock().getMaterialNum().indexOf("*")>-1){
				hql.append(" and a.hongXunMaterialStock.materialNum like ?");
				list.add("%" + hongXunPurchaseDeItem.getHongXunMaterialStock().getMaterialNum().replace("*", "") + "%");				
			}else{
				hql.append(" and a.hongXunMaterialStock.materialNum = ?");
				list.add(hongXunPurchaseDeItem.getHongXunMaterialStock().getMaterialNum());
			}
		}
		if (hongXunPurchaseDeItem.getHongXunMaterialStock().getSpecification() != null) {
			if(hongXunPurchaseDeItem.getHongXunMaterialStock().getSpecification().indexOf("*")>-1){
				hql.append(" and a.hongXunMaterialStock.specification like ?");
				list.add("%" + hongXunPurchaseDeItem.getHongXunMaterialStock().getSpecification().replace("*", "") + "%");				
			}else{
				hql.append(" and a.hongXunMaterialStock.specification =?");
				list.add(hongXunPurchaseDeItem.getHongXunMaterialStock().getSpecification());
			}
		}
		if (hongXunPurchaseDeItem.getSupplier() != null) {
			if(hongXunPurchaseDeItem.getSupplier().indexOf("*")>-1){
				hql.append(" and a.supplier like ?");
				list.add("%" + hongXunPurchaseDeItem.getSupplier().replace("*", "") + "%");				
			}else{
				hql.append(" and a.supplier =?");
				list.add(hongXunPurchaseDeItem.getSupplier());
			}
		}
		return iPurchaseDeItemDao.findPageByFetchedHql(hql.toString(), "select count(*) " + hql.toString(), pageNo, pageSize,list.toArray());	
	}


	@Override
	public List<Map<String, Object>> autotimp(String q, Integer numId) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		try{
			List<Object> listObj = new ArrayList<Object>();
			StringBuilder hql=new StringBuilder("from HongXunPurchaseDeItem a where 1=1");
			//String类型属性	
			if(numId != 0){
				hql.append(" and a.hongXunPurchaseDeNum.idc =?");
				listObj.add(numId);	
			}
			hql.append(" and a.hongXunMaterialStock.materialNum like ?");
			listObj.add("%" + q + "%");				
			List<HongXunPurchaseDeItem> hongXunPurchaseDeItems = iPurchaseDeItemDao.getListByHQL(hql.toString(), listObj.toArray());	
			for(HongXunPurchaseDeItem item: hongXunPurchaseDeItems){
				Map<String,Object> map = new HashMap<String,Object>();	
				map.put("name", item.getHongXunMaterialStock().getMaterialNum());
				map.put("id", item.getIdc());  
				list.add(map);
			}
		} catch (Exception e) {
			throw e;
		}		
		return list;
	}


}
