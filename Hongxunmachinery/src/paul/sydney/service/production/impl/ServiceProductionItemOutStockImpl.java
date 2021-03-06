package paul.sydney.service.production.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import paul.sydney.dao.IBomTreeDao;
import paul.sydney.dao.IOrderNumDao;
import paul.sydney.dao.IPonDao;
import paul.sydney.dao.IProductionDao;
import paul.sydney.dao.IProductionItemOutStockDao;
import paul.sydney.dao.StockDao;
import paul.sydney.model.HongXunMaterialStock;
import paul.sydney.model.HongXunPoN;
import paul.sydney.model.HongXunProductionItemOutStock;
import paul.sydney.model.HongXunProductionStock;
import paul.sydney.model.HongXunPurchaseItem;
import paul.sydney.service.production.ServiceProductionItemOutStockInf;
import paul.sydney.model.HongXunBomTree;
import paul.sydney.model.HongXunMaterialItemOutStock;
import paul.sydney.model.HongXunProductionItemOutStock;
@Transactional
@Service("serviceProductionItemOutStock")
public class ServiceProductionItemOutStockImpl implements ServiceProductionItemOutStockInf{
	
	@Autowired
	StockDao stockDao;
	public void setStockDao(StockDao stockDao) {
		this.stockDao = stockDao;
	}
	@Autowired
	IBomTreeDao iBomTreeDao;
	public void setStockDao(IBomTreeDao iBomTreeDao) {
		this.iBomTreeDao = iBomTreeDao;
	}
	@Autowired
	IProductionDao iProductionDao;
	public void setStockDao(IProductionDao iProductionDao) {
		this.iProductionDao = iProductionDao;
	}
	@Autowired
	IProductionItemOutStockDao iProductionItemOutStockDao;
	public void setStockDao(IProductionItemOutStockDao iProductionItemOutStockDao) {
		this.iProductionItemOutStockDao = iProductionItemOutStockDao;
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
/*
	@Override
	public List<Map<String, Object>> loadData() {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		List<HongXunProductionItemOutStock>  hongXunPurchaseItemInStocks = stockDao.quary(new HongXunProductionItemOutStock());
		for(HongXunProductionItemOutStock item : hongXunPurchaseItemInStocks){
			Map<String,Object> map = new HashMap<String,Object>();
		
			mapHongXunProductionItem(map,item.getHongXunPurchaseItem());
			mapHongXunProductionItemInStock(map,item);
			list.add(map);
		}
		
		return list;
	}*/
	
	@Override
	public List<Map<String, Object>> getProductionItemsOutStore(int orderNumID) {
		// TODO Auto-generated method stub
    	@SuppressWarnings("unchecked")
		List<HongXunPoN> hongXunPoNs = (List<HongXunPoN>) stockDao.quarywithpara("HongXunPoN", "orderNumID", orderNumID); 
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		//System.out.println("list.size:" + list.size());
    	for(HongXunPoN item : hongXunPoNs){
        	@SuppressWarnings("unchecked")
    		List<HongXunProductionItemOutStock> hongXunProductionItemOutStocks = (List<HongXunProductionItemOutStock>) stockDao.quarywithpara("HongXunProductionItemOutStock", "poNID", item.getIdc()); 
        	for(HongXunProductionItemOutStock hongXunProductionItemOutStock : hongXunProductionItemOutStocks){
        		Map<String,Object> map = new HashMap<String,Object>();
    			mapHongXunProductionItemOutStock(map, hongXunProductionItemOutStock);
    			map.put("materialNum", item.getMaterialNo());
    			map.put("specification", item.getMaterialDesc());
    			list.add(map);
        	}		
    	}
		return list;
	}

	/*
	int mainID = hongXunPurchaseItemInStockList.get(0).getIdc();
	HongXunProductionItemOutStock hongXunPurchaseItemInStock = new HongXunProductionItemOutStock();
	
	hongXunPurchaseItemInStock.setInstoreQuantity(hongXunPurchaseItemInStockList.get(0).getInstoreQuantity());
	hongXunPurchaseItemInStock.setRealQuantity(hongXunPurchaseItemInStockList.get(0).getInstoreQuantity());
	hongXunPurchaseItemInStock.setLotNum(hongXunPurchaseItemInStockList.get(0).getLotNum());
	//hongXunPurchaseItemInStock.setRemark(hongXunPurchaseItemInStockList.get(0).getRemark());
	hongXunPurchaseItemInStock.setMainID(mainID);*/
/*	@Override
	public List<Map<String, Object>> saveRow(HongXunProductionItemOutStock hongXunPurchaseItemInStock) {
		// TODO Auto-generated method stub
		//System.out.println("serviceoneloadSaveRow");	
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		HongXunPurchaseItem hongXunPurchaseItem = stockDao.dataTwoFindById(hongXunPurchaseItemInStock.getIdc());
		hongXunPurchaseItemInStock.setHongXunPurchaseItem(hongXunPurchaseItem);
		hongXunPurchaseItemInStock.setInstoreDate(new Date());
		stockDao.update(hongXunPurchaseItem);
		stockDao.save(hongXunPurchaseItemInStock);
		
    	@SuppressWarnings("unchecked")
		List<HongXunMaterialStock> hongXunMaterialStocks = (List<HongXunMaterialStock>) stockDao.quarywithpara("HongXunMaterialStock", "materialNum", hongXunPurchaseItem.getMaterialNum()); 
    	if(hongXunMaterialStocks.size()==1){
    		if(hongXunMaterialStocks.get(0).getQuantity() != null){
    			//hongXunMaterialStocks.get(0).setQuantity(hongXunMaterialStocks.get(0).getQuantity() + hongXunPurchaseItemInStock.getInstoreQuantity());
    		}else{
    			//hongXunMaterialStocks.get(0).setQuantity(hongXunPurchaseItemInStock.getInstoreQuantity());
    		}
    		stockDao.update(hongXunMaterialStocks.get(0));
    	}else if(hongXunMaterialStocks.size()>1){
    		System.out.println("HongXunMaterialStock 有重复料号出错");
    	}else{
    		HongXunMaterialStock hongXunMaterialStock = new HongXunMaterialStock();
    		hongXunMaterialStock.setMaterialNum(hongXunPurchaseItem.getMaterialNum());
    		//hongXunMaterialStock.setQuantity(hongXunPurchaseItemInStock.getInstoreQuantity());
    		hongXunMaterialStock.setSpecification(hongXunPurchaseItem.getSpecification());
    		hongXunMaterialStock.setUnit(hongXunPurchaseItem.getUnit());
    		stockDao.save(hongXunMaterialStock);
    	}
		Map<String,Object> map = new HashMap<String,Object>();
		mapHongXunProductionItem(map,hongXunPurchaseItem);
		mapHongXunProductionItemInStock(map,hongXunPurchaseItemInStock);
	//	map.put("materialRealQuantity", hongXunPurchaseItem.getMaterialRealQuantity());	
		//map.put("mysidc", mapHongXunDataTwoFather.getIdc());
		//map.put("instoreDate", mapHongXunDataTwoFather.getInstoreDate());
		list.add(map);
		return list;
	}*/
	

	
	private void mapHongXunProductionItemOutStock(Map<String, Object> map, HongXunProductionItemOutStock item) {
		// TODO Auto-generated method stub	
		//map.put("mysidc", item.getIdc());
		if(item.getDate() != null){
			map.put("date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(item.getDate()));
		}	
		//map.put("instoreQuantity", item.getInstoreQuantity());
		map.put("quantity", item.getQuantity());
		map.put("lotNum", item.getLotNum());
		map.put("remark", item.getRemark());
	}
	
/*	public void mapHongXunDataTwoChildren(Map<String, Object> map, HongXunDataTwoChildren item) {
		map.put("idc", item.getIdc());
		if(item.getOutStoreDate() != null){
			map.put("outStoreDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(item.getOutStoreDate()));
		}
		if(item.getOutStoreQuantity() != null){
			map.put("outStoreQuantity", item.getOutStoreQuantity());
		}
		//map.put("lotNum", item.getLotNum());
		//map.put("remark", item.getRemark());
		
	}*/
	
/*	@Override
	public List<Map<String, Object>> autotimp(String str, int purchaseNumID) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    	@SuppressWarnings("unchecked")
		List<HongXunPurchaseItem> hongXunPurchaseItems = (List<HongXunPurchaseItem>) stockDao.quarywithpara("HongXunPurchaseItem", "purchaseNumID", purchaseNumID); 
    	
		//List<HongXunProductionItemOutStock> hongXunPurchaseItemInStocks = stockDao.quary(new HongXunProductionItemOutStock());	
		for(HongXunPurchaseItem item: hongXunPurchaseItems){	
			if(item.getMaterialNum().indexOf(str)>-1){
				Map<String,Object> map = new HashMap<String,Object>();
				//map.put("materialName", item.getMaterialName());	
				map.put("name", item.getMaterialNum());
				map.put("id", item.getIdc());  
				list.add(map);
			}
		}
		return list;
	}*/
	@Override
	public List<Map<String, Object>> autotimp(String str, int orderNumID) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		HongXunPoN hongXunPoN = new HongXunPoN();
		hongXunPoN.setOrderNumID(orderNumID);
		List<HongXunPoN> hongXunPoNList = iPonDao.quary(hongXunPoN);		
    	//@SuppressWarnings("unchecked")
		//List<HongXunPurchaseItem> hongXunPurchaseItems = (List<HongXunPurchaseItem>) stockDao.quarywithpara("HongXunPurchaseItem", "purchaseNumID", purchaseNumID);   	
		//List<HongXunProductionItemInStock> hongXunPurchaseItemInStocks = stockDao.quary(new HongXunProductionItemInStock());	
		for(HongXunPoN item: hongXunPoNList){	
			if(item.getMaterialNo().indexOf(str)>-1){
				Map<String,Object> map = new HashMap<String,Object>();
				//map.put("materialName", item.getMaterialName());	
				map.put("name", item.getMaterialNo());
				map.put("id", item.getIdc());  
				list.add(map);
			}
		}
		return list;
	}

	List<Map<String, Object>> outstoreProductionCheck(HongXunPoN item, int quantity, String materialNum, List<Map<String, Object>> list){
		if(item.getQuantity() != null){
			if(item.getStockOutQuantity() == null){
				if(quantity > item.getQuantity()){
					//System.out.println("出库数量大于订单数量");
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("error", "物料号:" + materialNum +"->出库数量大于订单数量...");
					list.add(map);
					return list;
				}
			}else{
				if(quantity > item.getQuantity()-item.getStockOutQuantity()){
					//System.out.println("出库数量大于订单未出库数量...");
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("error", "物料号:" + materialNum +"->出库数量大于订单未出库数量...");
					list.add(map);
					return list;
				}
			}
			HongXunBomTree hongXunBomTree = new HongXunBomTree();
			hongXunBomTree.setSn(1);
			hongXunBomTree.setBomMaterialNum(materialNum);
			List<HongXunBomTree> hongXunBomTrees = iBomTreeDao.quary(hongXunBomTree);
			if(hongXunBomTrees.size()==1){
				if(!(hongXunBomTrees.get(0).getBomGroup().equals("B00装配清单") || hongXunBomTrees.get(0).getBomGroup().equals("732"))){
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("error", "物料号:"+ materialNum +"->不是装配件");
					list.add(map);
					return list;
				}
			}else if(hongXunBomTrees.size()==0){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("error", "物料号:"+ materialNum +"->Bom无信息记录");
				list.add(map);
				return list;
			}else{
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("error", "物料号:"+ materialNum +"->Bom中发现重复");
				list.add(map);
				return list;
			}
		}else{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", "物料号:"+ materialNum +"->生产数量为空");
			list.add(map);
			return list;
		}
		return list;
	}
	
	private void outstoreProductionQuantityAdd(HongXunPoN item, int quantity, String materialNum, List<Map<String, Object>> list){
		@SuppressWarnings("unchecked")
		List<HongXunProductionStock> hongXunProductionStocks = (List<HongXunProductionStock>) stockDao.quarywithpara("HongXunProductionStock", "materialNum", materialNum); 
    	if(hongXunProductionStocks.size()==0){
    		//System.out.println("库存无此产品");
    		Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", "物料号:" + materialNum +"->库存无此产品");
			list.add(map);
			//break;
    	}else if(hongXunProductionStocks.size()==1){
    		if(hongXunProductionStocks.get(0).getQuantity() != null){
    			int outQuantity = hongXunProductionStocks.get(0).getQuantity()-quantity;
    			if(outQuantity>-1){
    				HongXunProductionItemOutStock hongXunProductionItemOutStock = new HongXunProductionItemOutStock();
    				hongXunProductionItemOutStock.setPoNID(item.getIdc());
    				hongXunProductionItemOutStock.setDate(new Date());
    				hongXunProductionItemOutStock.setQuantity(quantity);
    				iProductionItemOutStockDao.save(hongXunProductionItemOutStock);

    				hongXunProductionStocks.get(0).setQuantity(outQuantity);
    				iProductionDao.update(hongXunProductionStocks.get(0));
    				Map<String, Object> map = new HashMap<String, Object>();
    				mapHongXunProductionItemOutStock(map,hongXunProductionItemOutStock);
	    			map.put("materialNum", item.getMaterialNo());
	    			map.put("specification", item.getMaterialDesc());
    				list.add(map);
    				//break;
    			}else{
    				Map<String, Object> map = new HashMap<String, Object>();
					map.put("error", "物料号:" + materialNum +"->库存不足,库存数量：" + hongXunProductionStocks.get(0).getQuantity());
					list.add(map);
					//break;
    			}
    		}else{
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("error", "物料号:" + materialNum +"->库存数量为NULL,请联系软件工程师。");
				list.add(map);
				//break;
    		}			    		
    	}else{
    		Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", "物料号:" + materialNum +"->库存有重复部件号,请联系软件工程师。");
			list.add(map);
			//break;
    	}
	}
	
	@Override
	public List<Map<String, Object>> outstoreProduction(String materialNum, int orderNumID, int quantity) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if(iOrderDao.orderNumFindById(orderNumID).getStatus().equals("新定单")){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", "定单未分解，禁止出入库");
			list.add(map);
			return list;
		}
    	HongXunPoN hongXunPonEntity = new HongXunPoN();
    	hongXunPonEntity.setOrderNumID(orderNumID);
    	hongXunPonEntity.setMaterialNo(materialNum);
    	List<HongXunPoN> hongXunPoNs = iPonDao.quary(hongXunPonEntity);
    	if(hongXunPoNs==null || hongXunPoNs.size()==0){
    		Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", "物料号:"+ materialNum +"->订单中无信息");
			list.add(map);
    	}else{
    		if(hongXunPoNs.size()==1){
        		outstoreProductionCheck(hongXunPoNs.get(0),  quantity,  materialNum,  list);
        		if(list.size()>0){
        			return list;
        		}
    			outstoreProductionQuantityAdd(hongXunPoNs.get(0), quantity, materialNum, list);
    			/*    				if(item.getStockOutQuantity() == null){
				item.setStockOutQuantity(hongXunProductionItemOutStock.getQuantity());
			}else{
				item.setStockOutQuantity(item.getStockOutQuantity() + hongXunProductionItemOutStock.getQuantity());
			}
			stockDao.update(item);*/
    			if(hongXunPoNs.get(0).getStockOutQuantity() == null){				
    				hongXunPoNs.get(0).setStockOutQuantity(quantity);
    			}else{
    				hongXunPoNs.get(0).setStockOutQuantity(hongXunPoNs.get(0).getStockOutQuantity() + quantity);
    			}
    			//System.out.println("hongXunPoNs.get(0) " + hongXunPoNs.get(0).getIdc());
    			iPonDao.update(hongXunPoNs.get(0));
    		}else{
    			int totalQuantity = 0;
    			int totalOutStoreQuantiy=0;
    			for(HongXunPoN item: hongXunPoNs){	
    				 totalQuantity =  totalQuantity + item.getQuantity();
    				 if(item.getStockOutQuantity() != null){
    					 totalOutStoreQuantiy = totalOutStoreQuantiy + item.getStockOutQuantity(); 
    				 }
    			}
    			HongXunPoN hongXunPoN = hongXunPoNs.get(0).clone();
    			hongXunPoN.setQuantity(totalQuantity);
    			hongXunPoN.setStockOutQuantity(totalOutStoreQuantiy);
        		outstoreProductionCheck(hongXunPoN,  quantity,  materialNum,  list);
        		if(list.size()>0){
        			return list;
        		}
        		outstoreProductionQuantityAdd(hongXunPoNs.get(0), quantity, materialNum, list);
        		int quantityClone = quantity;  
        		for(HongXunPoN item: hongXunPoNs){	
        			if(item.getStockOutQuantity() == null){
        				if(quantityClone>=item.getQuantity()){
        					item.setStockOutQuantity(item.getQuantity());
        					quantityClone = quantityClone - item.getQuantity();
        				}else{
        					if(quantityClone != 0){
        						item.setStockOutQuantity(quantityClone); 
        						quantityClone=0;
        					}
        					
        				}
        				
        			}else{
        				int dfQuantity = item.getQuantity()- item.getStockOutQuantity();
        				if(dfQuantity != 0){
	        				if(quantityClone>=dfQuantity){
	        					item.setStockOutQuantity(item.getQuantity());
	        					quantityClone = quantityClone - dfQuantity;
	        				}else{
	        					if(quantityClone != 0){
	        						item.setStockOutQuantity(item.getStockOutQuantity() + quantityClone);
	        						quantityClone=0;
	        					}        					
	        				}
        				}
        			}
        			iPonDao.update(item);
        			if(quantityClone==0){
        				break;
        			}
        		}
    		}
    	}
    	//@SuppressWarnings("unchecked")
		//List<HongXunPoN> hongXunPoNs = (List<HongXunPoN>) stockDao.quarywithpara("HongXunPoN", "orderNumID", orderNumID); 
    
    	//	for(HongXunPoN item: hongXunPoNs){	
		//		if(item.getMaterialNo().equals(materialNum)){
					/*if(item.getQuantity() != null){
						if(item.getStockOutQuantity() == null){
							if(quantity > item.getQuantity()){
								System.out.println("出库数量大于订单数量");
								Map<String, Object> map = new HashMap<String, Object>();
								map.put("error", "物料号:" + materialNum +"->出库数量大于订单数量...");
								list.add(map);
								break;
							}
						}else{
							if(quantity > item.getQuantity()-item.getStockOutQuantity()){
								System.out.println("出库数量大于订单未出库数量...");
								Map<String, Object> map = new HashMap<String, Object>();
								map.put("error", "物料号:" + materialNum +"->出库数量大于订单未出库数量...");
								list.add(map);
								break;
							}
						}
						HongXunBomTree hongXunBomTree = new HongXunBomTree();
						hongXunBomTree.setSn(1);
						hongXunBomTree.setBomMaterialNum(materialNum);
						List<HongXunBomTree> hongXunBomTrees = stockDao.quary(hongXunBomTree);
						if(hongXunBomTrees.size()==1){
							if(!(hongXunBomTrees.get(0).getBomGroup().equals("B00装配清单") || hongXunBomTrees.get(0).getBomGroup().equals("732"))){
								Map<String, Object> map = new HashMap<String, Object>();
								map.put("error", "物料号:"+ materialNum +"->不是装配件");
								list.add(map);
								return list;
							}
						}else if(hongXunBomTrees.size()==0){
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("error", "物料号:"+ materialNum +"->Bom无信息记录");
							list.add(map);
							return list;
						}else{
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("error", "物料号:"+ materialNum +"->Bom中发现重复");
							list.add(map);
							return list;
						}
					}else{
						
					}*/
	
					/*@SuppressWarnings("unchecked")
					List<HongXunProductionStock> hongXunProductionStocks = (List<HongXunProductionStock>) stockDao.quarywithpara("HongXunProductionStock", "materialNum", materialNum); 
			    	if(hongXunProductionStocks.size()==0){
			    		System.out.println("库存无此产品");
			    		Map<String, Object> map = new HashMap<String, Object>();
						map.put("error", "物料号:" + materialNum +"->库存无此产品");
						list.add(map);
						break;
			    	}else if(hongXunProductionStocks.size()==1){
			    		if(hongXunProductionStocks.get(0).getQuantity() != null){
			    			int outQuantity = hongXunProductionStocks.get(0).getQuantity()-quantity;
			    			if(outQuantity>-1){
			    				HongXunProductionItemOutStock hongXunProductionItemOutStock = new HongXunProductionItemOutStock();
			    				hongXunProductionItemOutStock.setPoNID(item.getIdc());
			    				hongXunProductionItemOutStock.setDate(new Date());
			    				hongXunProductionItemOutStock.setQuantity(quantity);
			    				stockDao.save(hongXunProductionItemOutStock);
			    				if(item.getStockOutQuantity() == null){
			    					item.setStockOutQuantity(hongXunProductionItemOutStock.getQuantity());
			    				}else{
			    					item.setStockOutQuantity(item.getStockOutQuantity() + hongXunProductionItemOutStock.getQuantity());
			    				}
			    				stockDao.update(item);
			    				hongXunProductionStocks.get(0).setQuantity(outQuantity);
			    				stockDao.update(hongXunProductionStocks.get(0));
			    				Map<String, Object> map = new HashMap<String, Object>();
			    				mapHongXunProductionItemOutStock(map,hongXunProductionItemOutStock);
				    			map.put("materialNum", item.getMaterialNo());
				    			map.put("specification", item.getMaterialDesc());
			    				list.add(map);
			    				break;
			    			}else{
			    				Map<String, Object> map = new HashMap<String, Object>();
								map.put("error", "物料号:" + materialNum +"->库存不足,库存数量：" + hongXunProductionStocks.get(0).getQuantity());
								list.add(map);
								break;
			    			}
			    		}else{
		    				Map<String, Object> map = new HashMap<String, Object>();
							map.put("error", "物料号:" + materialNum +"->库存数量为NULL,请联系软件工程师。");
							list.add(map);
							break;
			    		}			    		
			    	}else{
			    		Map<String, Object> map = new HashMap<String, Object>();
						map.put("error", "物料号:" + materialNum +"->库存有重复部件号,请联系软件工程师。");
						list.add(map);
						break;
			    	}*/
			//	}
			//}
/*    		if(list.size()==0){
	    
	    		Map<String, Object> map = new HashMap<String, Object>();
				map.put("error", "物料号:"+ materialNum +"->订单无信息");
				list.add(map);
    		}*/
			return list;
    	}
	
	

}
