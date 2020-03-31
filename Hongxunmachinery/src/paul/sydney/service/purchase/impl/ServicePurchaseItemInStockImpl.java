package paul.sydney.service.purchase.impl;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import paul.sydney.commen.result.PageResults;
import paul.sydney.dao.IMaterialStockDao;
import paul.sydney.dao.IPurchaseDao;
import paul.sydney.dao.IPurchaseDeItemDao;
import paul.sydney.dao.IPurchaseItemDao;
import paul.sydney.dao.IPurchaseItemInStockDao;
import paul.sydney.dao.StockDao;
import paul.sydney.model.HongXunMaterialStock;
import paul.sydney.model.HongXunPurchaseDeItem;
import paul.sydney.model.HongXunPurchaseItem;
import paul.sydney.model.HongXunPurchaseItemInStock;
import paul.sydney.service.purchase.ServicePurchaseItemInStockInf;
@Transactional
@Service("servicePurchaseItemInStock")
public class ServicePurchaseItemInStockImpl implements ServicePurchaseItemInStockInf{
	
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
	IMaterialStockDao iMaterialStockDao;
	public void setStockDao(IMaterialStockDao iMaterialStockDao) {
		this.iMaterialStockDao = iMaterialStockDao;
	}
	@Autowired
	IPurchaseItemDao iPurchaseItemDao;
	public void setStockDao(IPurchaseItemDao iPurchaseItemDao) {
		this.iPurchaseItemDao = iPurchaseItemDao;
	}
	@Autowired
	IPurchaseDeItemDao iPurchaseDeItemDao;
	public void setStockDao(IPurchaseDeItemDao iPurchaseDeItemDao) {
		this.iPurchaseDeItemDao = iPurchaseDeItemDao;
	}
	@Autowired
	IPurchaseItemInStockDao iPurchaseItemInStockDao;
	public void setStockDao(IPurchaseItemInStockDao iPurchaseItemInStockDao) {
		this.iPurchaseItemInStockDao = iPurchaseItemInStockDao;
	}

	
	private void mapHongXunPurchaseItemInStock(Map<String, Object> map, HongXunPurchaseItemInStock item) {
		// TODO Auto-generated method stub	
		//map.put("mysidc", item.getIdc());
		if(item.getInstoreDate() != null){
			map.put("instoreDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(item.getInstoreDate()));
		}	
		//map.put("instoreQuantity", item.getInstoreQuantity());
		map.put("realQuantity", item.getRealQuantity());
		map.put("lotNum", item.getLotNum());
		map.put("remark", item.getRemark());
		map.put("idc", item.getIdc());
		map.put("purchaseItemID", item.getPurchaseItemID());
	}

	@Override
	public List<Map<String, Object>> instoreMaterialNum(String materialNum, int purchaseNumID, int quantity) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		HongXunPurchaseItemInStock hongXunPurchaseItemInStock = new HongXunPurchaseItemInStock();
/*		HongXunPurchaseItem hongXunPurchaseItem = new HongXunPurchaseItem();
		hongXunPurchaseItem.setPurchaseNumID(purchaseNumID);		
		List<HongXunPurchaseItem> hongXunPurchaseItems = iPurchaseItemDao.quary(hongXunPurchaseItem);*/
		List<HongXunPurchaseItem> hongXunPurchaseItems = iPurchaseItemDao.getListByHQL("from HongXunPurchaseItem where purchaseNumID =?" , purchaseNumID);
		for(HongXunPurchaseItem item: hongXunPurchaseItems){	
			if(item.getHongXunPurchaseDeItem().getHongXunMaterialStock().getMaterialNum().equals(materialNum)){			
/*					if(item.getMaterialRealQuantity() == null){
						if(quantity > item.getHongXunPurchaseDeItem().getQuantity()){
							//System.out.println("入库数量大于采购数量");
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("error", "物料号:"+ materialNum +"->入库数量大于采购数量");
							list.add(map);
							return list;
						}
					}else{
						if(quantity > item.getHongXunPurchaseDeItem().getQuantity()-item.getMaterialRealQuantity()){
							//System.out.println("入库数量大于采购数量...");
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("error", "物料号:"+ materialNum +"->入库数量大于采购数量...");
							list.add(map);
							return list;
						}
					}*/
				
				hongXunPurchaseItemInStock.setPurchaseItemID(item.getIdc()); //填充入库信息
				hongXunPurchaseItemInStock.setInstoreDate(new Date());
				hongXunPurchaseItemInStock.setRealQuantity(quantity);
				iPurchaseItemInStockDao.save(hongXunPurchaseItemInStock);
				if(item.getMaterialRealQuantity() == null){		//修改采购入库数量
					item.setMaterialRealQuantity(hongXunPurchaseItemInStock.getRealQuantity());
				}else{
					item.setMaterialRealQuantity(item.getMaterialRealQuantity() + hongXunPurchaseItemInStock.getRealQuantity());
				}
				iPurchaseItemDao.update(item);

				
				HongXunMaterialStock hongXunMaterialStock = item.getHongXunPurchaseDeItem().getHongXunMaterialStock(); 
			    		if(hongXunMaterialStock.getQuantity() != null){
			    			hongXunMaterialStock.setQuantity(hongXunMaterialStock.getQuantity() + hongXunPurchaseItemInStock.getRealQuantity());		    			
			    		}else{
			    			hongXunMaterialStock.setQuantity(hongXunPurchaseItemInStock.getRealQuantity());
			    		}
			    		if(hongXunMaterialStock.getInQuantity() != null){
			    			hongXunMaterialStock.setInQuantity(hongXunMaterialStock.getInQuantity() + hongXunPurchaseItemInStock.getRealQuantity());		    			
			    		}else{
			    			hongXunMaterialStock.setInQuantity(hongXunPurchaseItemInStock.getRealQuantity());
			    		}
			    		
			    		if(hongXunMaterialStock.getInRoadQuantity() != null){
			    			hongXunMaterialStock.setInRoadQuantity(hongXunMaterialStock.getInRoadQuantity()-hongXunPurchaseItemInStock.getRealQuantity());
			    		}
			    		
			    		iMaterialStockDao.updateEntity(hongXunMaterialStock);
				
					break;
			}	
		}
		Map<String, Object> map = new HashMap<String, Object>();
		mapHongXunPurchaseItemInStock(map,hongXunPurchaseItemInStock);		
		list.add(map);
		return list;
	}

	@Override
	public List<Map<String, Object>> autotimp(String q, int purchaseNumID) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		try{
			List<Object> listObj = new ArrayList<Object>();
			StringBuilder hql=new StringBuilder("from HongXunPurchaseItemInStock a where 1=1");
			//String类型属性			
			hql.append(" and a.hongXunPurchaseItem.hongXunPurchaseNum.idc =?");
			listObj.add(purchaseNumID);			
			hql.append(" and a.hongXunPurchaseItem.hongXunPurchaseDeItem.hongXunMaterialStock.materialNum like ?");
			listObj.add("%" + q + "%");				
			List<HongXunPurchaseItemInStock> hongXunPurchaseItemInStocks = iPurchaseItemInStockDao.getListByHQL(hql.toString(), listObj.toArray());	
			for(HongXunPurchaseItemInStock item: hongXunPurchaseItemInStocks){
				Map<String,Object> map = new HashMap<String,Object>();	
				map.put("name", item.getHongXunPurchaseItem().getHongXunPurchaseDeItem().getHongXunMaterialStock().getMaterialNum());
				map.put("id", item.getIdc());  
				list.add(map);
			}
		} catch (Exception e) {
			throw e;
		}		
		return list;
	}

	@Override
	public Collection<? extends Map<String, Object>> saveRow(HongXunPurchaseItemInStock item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateRow(HongXunPurchaseItemInStock item) {
		// TODO Auto-generated method stub

		HongXunPurchaseItemInStock hongXunPurchaseItemInStock = iPurchaseItemInStockDao.purchaseItemInStockFindById(item.getIdc());	
		hongXunPurchaseItemInStock.setLotNum(item.getLotNum());	
		hongXunPurchaseItemInStock.setRemark(item.getRemark());
		iPurchaseItemInStockDao.updateEntity(hongXunPurchaseItemInStock);	
	}
	@Override
	public void deleteRow(HongXunPurchaseItemInStock item) {
		// TODO Auto-generated method stub
		//删除还原来料仓库数量，入库数量
		item = iPurchaseItemInStockDao.get(item.getIdc());
		int quantity = item.getHongXunPurchaseItem().getHongXunPurchaseDeItem().getHongXunMaterialStock().getQuantity();
		item.getHongXunPurchaseItem().getHongXunPurchaseDeItem().getHongXunMaterialStock().setQuantity(quantity-item.getRealQuantity());		
		int intQuantity = item.getHongXunPurchaseItem().getHongXunPurchaseDeItem().getHongXunMaterialStock().getInQuantity();
		item.getHongXunPurchaseItem().getHongXunPurchaseDeItem().getHongXunMaterialStock().setInQuantity(intQuantity-item.getRealQuantity());
		//还原采购到货数量
		item.getHongXunPurchaseItem().setMaterialRealQuantity(item.getHongXunPurchaseItem().getMaterialRealQuantity()-item.getRealQuantity());
		item.getHongXunPurchaseItem().setArrivalQuantity(0);
		
		iMaterialStockDao.updateEntity(item.getHongXunPurchaseItem().getHongXunPurchaseDeItem().getHongXunMaterialStock());
		iPurchaseItemDao.updateEntity(item.getHongXunPurchaseItem());
		iPurchaseItemInStockDao.delete(item);		
	}	
	
	@Override
	public List<Map<String, Object>> getEntity() {
		// TODO Auto-generated method stub
		return iPurchaseItemInStockDao.getEntity();
	}
	@Override
	public PageResults<HongXunPurchaseItemInStock> getData(HongXunPurchaseItemInStock hongXunPurchaseItemInStock,
			Integer pageNo, Integer pageSize) {
		// TODO Auto-generated method stub
		PageResults<HongXunPurchaseItemInStock> result = new PageResults<HongXunPurchaseItemInStock>();
		try{
			List<Object> list = new ArrayList<Object>();

			StringBuilder hql=new StringBuilder("from HongXunPurchaseItemInStock a where 1=1" );
			//StringBuilder hql=new StringBuilder("from HongXunPurchaseItemInStock where 1=1" );
			if (hongXunPurchaseItemInStock.getHongXunPurchaseItem().getHongXunPurchaseNum().getIdc()!=0) {	
				hql.append(" and a.hongXunPurchaseItem.hongXunPurchaseNum.idc = ?");
				list.add(hongXunPurchaseItemInStock.getHongXunPurchaseItem().getHongXunPurchaseNum().getIdc());	
			}
			
	    	if(hongXunPurchaseItemInStock.getHongXunPurchaseItem().getHongXunPurchaseDeItem().getHongXunMaterialStock().getMaterialNum()!=null){
	    		String materialNum = hongXunPurchaseItemInStock.getHongXunPurchaseItem().getHongXunPurchaseDeItem().getHongXunMaterialStock().getMaterialNum();
	    		if(materialNum.indexOf("*")>-1){
					hql.append(" and a.hongXunPurchaseItem.hongXunPurchaseDeItem.hongXunMaterialStock.materialNum like ?");
					list.add("%" + materialNum.replace("*", "") + "%");				
				}else{
					hql.append(" and a.hongXunPurchaseItem.hongXunPurchaseDeItem.hongXunMaterialStock.materialNum=?");
					list.add(materialNum);
				}
	    	}   	
	    	if(hongXunPurchaseItemInStock.getHongXunPurchaseItem().getHongXunPurchaseDeItem().getHongXunMaterialStock().getSpecification()!=null){
	    		String spec = hongXunPurchaseItemInStock.getHongXunPurchaseItem().getHongXunPurchaseDeItem().getHongXunMaterialStock().getSpecification();
	    		if(spec.indexOf("*")>-1){
					hql.append(" and a.hongXunPurchaseItem.hongXunPurchaseDeItem.hongXunMaterialStock.specification like ?");
					list.add("%" + spec.replace("*", "") + "%");				
				}else{
					hql.append(" and a.hongXunPurchaseItem.hongXunPurchaseDeItem.hongXunMaterialStock.specification = ?");
					list.add(spec);
				}
	    	}
			result = iPurchaseItemInStockDao.findPageByFetchedHql(hql.toString(), "select count(*) " + hql.toString(), pageNo, pageSize,list.toArray());	
		//	System.out.println("-----sadf :" + result.getRows().size());
		} catch (Exception e) {
			result.setSuccess(false);
			
		}
		return result;
	}


}
