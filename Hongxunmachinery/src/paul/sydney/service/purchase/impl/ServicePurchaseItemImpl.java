package paul.sydney.service.purchase.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import paul.sydney.commen.result.PageResults;
import paul.sydney.dao.IMaterialStockDao;
import paul.sydney.dao.IPurchaseDao;
import paul.sydney.dao.IPurchaseDeItemDao;
import paul.sydney.dao.IPurchaseItemDao;
import paul.sydney.dao.IPurchaseItemInStockDao;
import paul.sydney.dao.StockDao;
import paul.sydney.model.HongXunPurchaseNum;
import paul.sydney.service.purchase.ServicePurchaseItemInf;
import paul.sydney.model.HongXunPurchaseItem;
import paul.sydney.model.HongXunMaterialStock;
import paul.sydney.model.HongXunPurchaseDeItem;
import paul.sydney.model.HongXunPurchaseItemInStock;

@Transactional
@Service("serviceNumTwo")
public class ServicePurchaseItemImpl implements ServicePurchaseItemInf{
	
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
	IPurchaseItemInStockDao iPurchaseItemInStockDao;
	public void setStockDao(IPurchaseItemInStockDao iPurchaseItemInStockDao) {
		this.iPurchaseItemInStockDao = iPurchaseItemInStockDao;
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
	public PageResults<HongXunPurchaseItem> getData(HongXunPurchaseItem hongXunPurchaseItem, Integer pageNo,
			Integer pageSize) {
		// TODO Auto-generated method stub
		PageResults<HongXunPurchaseItem> result = new PageResults<HongXunPurchaseItem>();
		try{
			List<Object> list = new ArrayList<Object>();
			StringBuilder hql=new StringBuilder("from HongXunPurchaseItem a where 1=1");
			//String类型属性
			if (hongXunPurchaseItem!=null && hongXunPurchaseItem.getHongXunPurchaseNum().getIdc() != 0) {
				hql.append(" and a.hongXunPurchaseNum.idc = ?");
				list.add(hongXunPurchaseItem.getHongXunPurchaseNum().getIdc());			
			}
			if (hongXunPurchaseItem.getHongXunPurchaseDeItem().getHongXunMaterialStock().getMaterialNum() != null) {
				if(hongXunPurchaseItem.getHongXunPurchaseDeItem().getHongXunMaterialStock().getMaterialNum().indexOf("*")>-1){
					hql.append(" and a.hongXunPurchaseDeItem.hongXunMaterialStock.materialNum like ?");
					list.add("%" + hongXunPurchaseItem.getHongXunPurchaseDeItem().getHongXunMaterialStock().getMaterialNum().replace("*", "") + "%");				
				}else{
					hql.append(" and a.hongXunPurchaseDeItem.hongXunMaterialStock.materialNum = ?");
					list.add(hongXunPurchaseItem.getHongXunPurchaseDeItem().getHongXunMaterialStock().getMaterialNum());
				}
			}
			if (hongXunPurchaseItem.getHongXunPurchaseDeItem().getHongXunMaterialStock().getSpecification() != null) {
				if(hongXunPurchaseItem.getHongXunPurchaseDeItem().getHongXunMaterialStock().getSpecification().indexOf("*")>-1){
					hql.append(" and a.hongXunPurchaseDeItem.hongXunMaterialStock.specification like ?");
					list.add("%" + hongXunPurchaseItem.getHongXunPurchaseDeItem().getHongXunMaterialStock().getSpecification().replace("*", "") + "%");				
				}else{
					hql.append(" and a.hongXunPurchaseDeItem.hongXunMaterialStock.specification =?");
					list.add(hongXunPurchaseItem.getHongXunPurchaseDeItem().getHongXunMaterialStock().getSpecification());
				}
			}
			if (hongXunPurchaseItem.getSupplier() != null) {
				if(hongXunPurchaseItem.getSupplier().indexOf("*")>-1){
					hql.append(" and a.supplier like ?");
					list.add("%" + hongXunPurchaseItem.getSupplier().replace("*", "") + "%");				
				}else{
					hql.append(" and a.supplier =?");
					list.add(hongXunPurchaseItem.getSupplier());
				}
			}
			result = iPurchaseItemDao.findPageByFetchedHql(hql.toString(), "select count(*) " + hql.toString(), pageNo, pageSize,list.toArray());	
		} catch (Exception e) {	
			result.setSuccess(false);
			throw e;
		}
		return result;
	}	

	
/*	@Override
	public List<Map<String, Object>> saveMoney(int id) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    	@SuppressWarnings("unchecked")
		List<HongXunPurchaseItem> hongXunPurchaseItems = (List<HongXunPurchaseItem>) stockDao.quarywithpara("HongXunPurchaseItem", "purchaseNumID", id); 
    	BigDecimal sum = null; //String state="";
    	for(int i=0; i<hongXunPurchaseItems.size(); i++){
    		if(hongXunPurchaseItems.get(i).getMaterialMoney() != null){
    			if(sum==null){
    				sum = hongXunPurchaseItems.get(i).getMaterialMoney();
    			}else{
    				sum = sum.add(hongXunPurchaseItems.get(i).getMaterialMoney());
    			}
    		}

    	}
    	
		if(sum != null){
			HongXunPurchaseNum hongXunDataOne = iPurchaseDao.purchaseNumFindById(id);
			hongXunDataOne.setPurchaseAmount(sum);
			iPurchaseDao.update(hongXunDataOne);	
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("purchaseOrderAmount", hongXunDataOne.getPurchaseAmount());
			list.add(map);	
		}else{
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("purchaseOrderAmount", "");
			list.add(map);	
		}
			
		return list;
	}*/
	
	@Override
	public List<Map<String, Object>> updateSotockState(int id) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    	@SuppressWarnings("unchecked")
		List<HongXunPurchaseItem> hongXunPurchaseItems = (List<HongXunPurchaseItem>) stockDao.quarywithpara("HongXunPurchaseItem", "purchaseNumID", id); 
    	int flag = -1; //int flag1 = 0;
    	for(HongXunPurchaseItem item : hongXunPurchaseItems){
    		if(item.getMaterialRealQuantity() != null){
    			if(item.getMaterialPurchaseQuantity() > item.getMaterialRealQuantity()){
    				flag = 0;
    				break;
    			}else{
    				flag = 1;
    			}
    		}
    	}
    	if(flag != -1){
			HongXunPurchaseNum hongXunPurchaseNum = iPurchaseDao.purchaseNumFindById(id);
			if(flag == 0){
				hongXunPurchaseNum.setPurchaseStatus("部分入库");
			}else if(flag == 1){
				hongXunPurchaseNum.setPurchaseStatus("入库完成");
			}	
			iPurchaseDao.update(hongXunPurchaseNum);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("purchaseStatus", hongXunPurchaseNum.getPurchaseStatus());
			list.add(map);		
    	}			
		return list;
	}
	
	
	@Override
	public List<Map<String, Object>> deleteRow(HongXunPurchaseItem item) {
		// TODO Auto-generated method stub
		try {
			HongXunPurchaseDeItem hongXunPurchaseDeItem = iPurchaseItemDao.get(item.getIdc()).getHongXunPurchaseDeItem();
			hongXunPurchaseDeItem.setStatus(null);
			hongXunPurchaseDeItem.getHongXunMaterialStock().setStatus(hongXunPurchaseDeItem.getHongXunMaterialStock().getStatus().replace("已下单", "已提单"));
			iPurchaseDeItemDao.updateEntity(hongXunPurchaseDeItem);		
			iPurchaseItemDao.deleteSql("delete from HongXunPurchaseItem where idc = ?", item.getIdc());
		} catch (Exception e) {				
			e.printStackTrace();
		}
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		return list;
	}
	
	@Override
	public void updateRow(HongXunPurchaseItem hongXunPurchaseItem) {
		iPurchaseItemDao.updateEntity(hongXunPurchaseItem);
	}

	@Override
	public List<HongXunPurchaseItem> quary(HongXunPurchaseItem hongXunPurchaseItem) {
		// TODO Auto-generated method stub
		List<HongXunPurchaseItem> list = iPurchaseItemDao.quary(hongXunPurchaseItem);
		return list;
	}

	@Override
	public List<Map<String, Object>> autotimp(String q, int purchaseNumID) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		try{
			List<Object> listObj = new ArrayList<Object>();
			StringBuilder hql=new StringBuilder("from HongXunPurchaseItem a where 1=1");
			//String类型属性			
			hql.append(" and a.hongXunPurchaseNum.idc = ?");
			listObj.add(purchaseNumID);			
			hql.append(" and a.hongXunPurchaseDeItem.hongXunMaterialStock.materialNum like ?");
			listObj.add("%" + q + "%");				
			List<HongXunPurchaseItem> hongXunPurchaseItems = iPurchaseItemDao.getListByHQL(hql.toString(), listObj.toArray());	
			for(HongXunPurchaseItem item: hongXunPurchaseItems){
				Map<String,Object> map = new HashMap<String,Object>();	
				map.put("name", item.getHongXunPurchaseDeItem().getHongXunMaterialStock().getMaterialNum());
				map.put("id", item.getIdc());  
				list.add(map);
			}
		} catch (Exception e) {
			throw e;
		}
		return list;
	}

	
	@Override
	public PageResults<HongXunPurchaseItem> instock(HongXunPurchaseItem item) {
		// TODO Auto-generated method stub
		PageResults<HongXunPurchaseItem> result = new PageResults<HongXunPurchaseItem>();
		//入库记录保存
		HongXunPurchaseItemInStock hongXunPurchaseItemInStock = new HongXunPurchaseItemInStock();
		HongXunPurchaseItem hongXunPurchaseItem = iPurchaseItemDao.purchaseItemFindById(item.getIdc());
		//hongXunPurchaseItemInStock.setPurchaseItemID(hongXunPurchaseItem.getIdc()); //填充入库信息
		hongXunPurchaseItemInStock.setInstoreDate(new Date());
		hongXunPurchaseItemInStock.setRealQuantity(item.getArrivalQuantity());
		hongXunPurchaseItemInStock.setHongXunPurchaseItem(hongXunPurchaseItem);
		iPurchaseItemInStockDao.save(hongXunPurchaseItemInStock);
		//采购到货更新
		if(hongXunPurchaseItem.getMaterialRealQuantity() == null){		//修改采购入库数量
			hongXunPurchaseItem.setMaterialRealQuantity(hongXunPurchaseItemInStock.getRealQuantity());
		}else{
			hongXunPurchaseItem.setMaterialRealQuantity(hongXunPurchaseItem.getMaterialRealQuantity() + hongXunPurchaseItemInStock.getRealQuantity());
		}
		hongXunPurchaseItem.setArrivalQuantity(item.getArrivalQuantity());
		iPurchaseItemDao.update(hongXunPurchaseItem);
		//库存数量，入库量，在途更新
		HongXunMaterialStock hongXunMaterialStock = hongXunPurchaseItem.getHongXunPurchaseDeItem().getHongXunMaterialStock();
    	if(hongXunMaterialStock.getQuantity() != null){
    		hongXunMaterialStock.setQuantity(hongXunMaterialStock.getQuantity() + item.getArrivalQuantity());		    			
    	}else{
    		hongXunMaterialStock.setQuantity(item.getArrivalQuantity());
    	}
    	if(hongXunMaterialStock.getInRoadQuantity() != null){
    		hongXunMaterialStock.setInRoadQuantity(hongXunMaterialStock.getInRoadQuantity()-item.getArrivalQuantity());
    	}
    	if(hongXunMaterialStock.getInQuantity() != null){
    		hongXunMaterialStock.setInQuantity(hongXunMaterialStock.getInQuantity()+item.getArrivalQuantity());
    	}else{
    		hongXunMaterialStock.setInQuantity(item.getArrivalQuantity());
    	}
    	iMaterialStockDao.updateEntity(hongXunMaterialStock);
    	return result;	
	}
	
	@Override
	public List<Map<String, Object>> outstore(String materialNum, int quantity, int purchaseNumID) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		//int tmpQuantity = quantity;
		@SuppressWarnings("unchecked")
		List<HongXunPurchaseItem> hongXunDataTwos = (List<HongXunPurchaseItem>) stockDao.quarywithpara("HongXunPurchaseItem", "materialNum", materialNum); 
		return list;

	}

	@Override
	public List<Map<String, Object>> getEntity() {
		// TODO Auto-generated method stub
        return iPurchaseItemDao.getEntity();
	}

}
