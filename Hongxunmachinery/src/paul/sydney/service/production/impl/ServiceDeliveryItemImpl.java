package paul.sydney.service.production.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import paul.sydney.model.HongXunDeliveryItem;
import paul.sydney.model.HongXunDeliveryNum;
import paul.sydney.model.HongXunProductionStock;
import paul.sydney.service.production.ServiceDeliveryItemInf;
import paul.sydney.commen.result.PageResults;
import paul.sydney.dao.IProductionDao;
import paul.sydney.dao.IProductionDeliveryItemDao;
import paul.sydney.dao.IProductionDeliveryNumDao;
import paul.sydney.dao.StockDao;
@Transactional
@Service("serviceDeliveryItem")
public class ServiceDeliveryItemImpl implements ServiceDeliveryItemInf{
	
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
	IProductionDeliveryItemDao iProductionDeliveryItemDao;
	public void setStockDao(IProductionDeliveryItemDao iProductionDeliveryItemDao) {
		this.iProductionDeliveryItemDao = iProductionDeliveryItemDao;
	}
	@Autowired
	IProductionDeliveryNumDao iProductionDeliveryNumDao;
	public void setStockDao(IProductionDeliveryNumDao iProductionDeliveryNumDao) {
		this.iProductionDeliveryNumDao = iProductionDeliveryNumDao;
	}
	
	@Override
	public List<Map<String, Object>> getEntity() {
		// TODO Auto-generated method stub
        return iProductionDeliveryItemDao.getEntity();
	}
	
	private void mapHongXunDeliveryItem(Map<String, Object> map, HongXunDeliveryItem item) {
		// TODO Auto-generated method stub	
		//System.out.println("test: " + item.getHongXunDataOne().getProductionOrderNum());
		map.put("idc", item.getIdc());
		map.put("pon", item.getPon());
		map.put("line", item.getLine());
		map.put("materialNo", item.getMaterialNo());
		map.put("materialDesc", item.getMaterialDesc());
		map.put("exemption", item.getExemption());
		map.put("code", item.getCode());
		map.put("unit", item.getUnit());
		map.put("projectNum", item.getProjectNum());
		map.put("status", item.getStatus());
		map.put("sendQuantity", item.getSendQuantity());
		//map.put("deliveryNumID", item.getDeliveryNumID());	
		map.put("remark", item.getRemark());
		//HongXunDeliveryNum deliveryNum = iProductionDeliveryNumDao.deliveryNumFindById(item.getDeliveryNumID());
		//map.put("deliveryNum", deliveryNum.getDeliveryNum());
	}

	@Override
	public List<Map<String, Object>> autotimp(String q, int deliveryNumID) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		List<Object> listObj = new ArrayList<Object>();
		StringBuilder hql=new StringBuilder("from HongXunDeliveryItem a where 1=1");
		hql.append(" and materialNo like ?");
		listObj.add("%" + q + "%");
		hql.append(" and a.hongXunDeliveryNum.idc = ?");
		listObj.add(deliveryNumID);	
		List<HongXunDeliveryItem> hongXunDeliveryItems = iProductionDeliveryItemDao.getListByHQL(hql.toString(), listObj.toArray());		
		for(HongXunDeliveryItem item: hongXunDeliveryItems){				
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("name", item.getMaterialNo());
			map.put("id", item.getIdc()); 
			list.add(map);			
		}
		return list;
	}

	
	@Override
	public List<Map<String, Object>> outstock(String materialNum, int deliveryItemID) {
		// TODO Auto-generated method stub
		String specification = null;
		if(materialNum.indexOf("->") > 0){
			specification = materialNum.split("->")[1];
			materialNum = materialNum.split("->")[0];
		}
/*		HongXunDeliveryItem hongXunDeliveryItem = new HongXunDeliveryItem();
		hongXunDeliveryItem.setMaterialNo(materialNum);	
		hongXunDeliveryItem.setDeliveryNumID(deliveryNumID);
		hongXunDeliveryItem.setRemark("库存无记录");
		List<HongXunDeliveryItem> hongXunDeliveryItems = iProductionDeliveryItemDao.quary(hongXunDeliveryItem);*/
		HongXunDeliveryItem hongXunDeliveryItem = iProductionDeliveryItemDao.deliveryItemFindById(deliveryItemID);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		//if (hongXunDeliveryItems.size()>0) {
		if (hongXunDeliveryItem != null && hongXunDeliveryItem.getRemark().equals("库存无记录") && 
				hongXunDeliveryItem.getMaterialNo().equals(materialNum)) {
			HongXunProductionStock hongXunProductionStock = new HongXunProductionStock();
			hongXunProductionStock.setMaterialNum(materialNum);
			if(specification !=null){
				hongXunProductionStock.setSpecification(specification);
			}
			List<HongXunProductionStock> hongXunProductionStocks = iProductionDao.quary(hongXunProductionStock);
			if(hongXunProductionStocks.size()==0){				
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("error", "物料号:"+ materialNum +"->焊接仓库无记录");
				list.add(map);
				return list;	
		    }else if(hongXunProductionStocks.size()==1){
		    	int result = hongXunProductionStocks.get(0).getQuantity() - hongXunDeliveryItem.getSendQuantity();
		    	if(result<0){
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("error", "物料号:"+ materialNum +"->出库数量大于库存数量：" + hongXunProductionStocks.get(0).getQuantity());
					list.add(map);
					return list;
		    	}else{
		    		hongXunProductionStocks.get(0).setQuantity(result);
		    		if(hongXunProductionStocks.get(0).getOutQuantity() != null){
		    			hongXunProductionStocks.get(0).setOutQuantity(hongXunProductionStocks.get(0).getOutQuantity() + hongXunDeliveryItem.getSendQuantity());
		    		}else{
		    			hongXunProductionStocks.get(0).setOutQuantity(hongXunDeliveryItem.getSendQuantity());
		    		}
		    		iProductionDao.update(hongXunProductionStocks.get(0));
		    		hongXunDeliveryItem.setRemark("");
		    		iProductionDeliveryItemDao.update(hongXunDeliveryItem);
		    		Map<String, Object> map = new HashMap<String, Object>();
		    		mapHongXunDeliveryItem(map, hongXunDeliveryItem);
					list.add(map);
		    		return list;
		    	}		    	
	    	}else{
	    		//System.out.println("HongXunMaterialStock 有重复料号出错");
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("error", "物料号:"+ materialNum +"->有重复料号,请联系工程师");
				list.add(map);
				return list;
	    	}
		}else{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", "送货单无:"+ materialNum +"料号或已出库");
			list.add(map);
			return list;
		}		
	}
	
	@Override
	public void saveRow(HongXunDeliveryItem item) {
		// TODO Auto-generated method stub
		iProductionDeliveryItemDao.save(item);
	}

	@Override
	public void updateRow(HongXunDeliveryItem item) {
		// TODO Auto-generated method stub
		HongXunDeliveryItem hongXunDeliveryItem = iProductionDeliveryItemDao.get(item.getIdc());
		hongXunDeliveryItem.setMaterialNo(item.getMaterialNo());
		hongXunDeliveryItem.setMaterialNo(item.getMaterialNo());
		hongXunDeliveryItem.setSendQuantity(item.getSendQuantity());
		hongXunDeliveryItem.setRemark(item.getRemark());
		hongXunDeliveryItem.setLine(item.getLine());
		hongXunDeliveryItem.setUnit(item.getUnit());
		hongXunDeliveryItem.setPon(item.getPon());
		hongXunDeliveryItem.setExemption(item.getExemption());
		hongXunDeliveryItem.setCode(item.getCode());
		hongXunDeliveryItem.setProjectNum(item.getProjectNum());
		iProductionDeliveryItemDao.update(hongXunDeliveryItem);
	}

	@Override
	public void deleteRow(HongXunDeliveryItem item) {
		// TODO Auto-generated method stub
		iProductionDeliveryItemDao.delete(item);
	}	


	@Override
	public List<Map<String, Object>> importData(List<HongXunDeliveryItem> hongXunDeliveryItemList) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();		
		HongXunProductionStock  hongXunProductionStock = new HongXunProductionStock();
		HongXunDeliveryNum hongXunDeliveryNum = null;
		for(HongXunDeliveryItem item: hongXunDeliveryItemList){
			if(hongXunDeliveryNum == null){
				hongXunDeliveryNum = iProductionDeliveryNumDao.get(item.getDeliveryNumID());		
				hongXunDeliveryNum.setHongXunDeliveryItems(new HashSet<HongXunDeliveryItem>());
			}
			hongXunProductionStock.setMaterialNum(item.getMaterialNo());
			List<HongXunProductionStock>  hongXunProductionStocks = iProductionDao.quary(hongXunProductionStock);	
			if(hongXunProductionStocks.size()==1 ){
				int quantity = hongXunProductionStocks.get(0).getQuantity() - item.getSendQuantity();
				if(quantity>-1){
					if(hongXunProductionStocks.get(0).getOutQuantity() == null){
						hongXunProductionStocks.get(0).setOutQuantity(0);
					}
					hongXunProductionStocks.get(0).setQuantity(quantity);
					hongXunProductionStocks.get(0).setOutQuantity(hongXunProductionStocks.get(0).getOutQuantity() + item.getSendQuantity());
					iProductionDao.update(hongXunProductionStocks.get(0));
					item.setStatus("已出库");
				}else{
					item.setStatus("库存不足");
				}
			}else if(hongXunProductionStocks.size()>1) {
				item.setStatus("多种规格");
			}else{
				item.setStatus("无库存记录");
			}
			item.setHongXunDeliveryNum(hongXunDeliveryNum);
			hongXunDeliveryNum.getHongXunDeliveryItems().add(item);
		}
		if(hongXunDeliveryNum != null){
			iProductionDeliveryNumDao.updateEntity(hongXunDeliveryNum);
		}
		return list;
	}

	@Override
	public PageResults<HongXunDeliveryItem> getData(HongXunDeliveryItem hongXunDeliveryItem, Integer pageNo,
			Integer pageSize) {
		List<Object> list = new ArrayList<Object>();
		StringBuilder hql=new StringBuilder("from HongXunDeliveryItem a where 1=1");
		//String类型属性
		if (hongXunDeliveryItem.getMaterialNo()!= null) {
			if(hongXunDeliveryItem.getMaterialNo().indexOf("*")>-1){
				hql.append(" and a.materialNo like ?");
				list.add("%" + hongXunDeliveryItem.getMaterialNo().replace("*", "") + "%");				
			}else{
				hql.append(" and a.materialNo = ?");
				list.add(hongXunDeliveryItem.getMaterialNo());
			}
		}
		if (hongXunDeliveryItem.getMaterialDesc()!= null) {
			if(hongXunDeliveryItem.getMaterialDesc().indexOf("*")>-1){
				hql.append(" and a.materialDesc like ?");
				list.add("%" + hongXunDeliveryItem.getMaterialDesc().replace("*", "") + "%");				
			}else{
				hql.append(" and a.materialDesc = ?");
				list.add(hongXunDeliveryItem.getMaterialDesc());
			}
		}
		if (hongXunDeliveryItem.getHongXunDeliveryNum().getIdc()!= 0) {
			hql.append(" and a.hongXunDeliveryNum.idc = ?");
			list.add(hongXunDeliveryItem.getHongXunDeliveryNum().getIdc());
		}
		return iProductionDeliveryItemDao.findPageByFetchedHql(hql.toString(), "select count(*) " + hql.toString(), pageNo, pageSize,list.toArray());
	}


}
