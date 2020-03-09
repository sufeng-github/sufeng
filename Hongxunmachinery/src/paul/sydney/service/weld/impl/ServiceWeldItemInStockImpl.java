package paul.sydney.service.weld.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import paul.sydney.dao.IBomTreeDao;
import paul.sydney.dao.IOrderNumDao;
import paul.sydney.dao.IPonDao;
import paul.sydney.dao.IWeldDao;
import paul.sydney.dao.StockDao;
import paul.sydney.model.HongXunPoN;
import paul.sydney.model.HongXunWeldItemInStock;
import paul.sydney.service.weld.ServiceWeldItemInStockInf;
import paul.sydney.model.HongXunProductionWeldStock;
import paul.sydney.model.HongXunBomTree;
@Transactional
@Service("serviceWeldItemInStock")
public class ServiceWeldItemInStockImpl implements ServiceWeldItemInStockInf{
	
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
/*
	@Override
	public List<Map<String, Object>> loadData() {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		List<HongXunWeldItemInStock>  hongXunPurchaseItemInStocks = stockDao.quary(new HongXunWeldItemInStock());
		for(HongXunWeldItemInStock item : hongXunPurchaseItemInStocks){
			Map<String,Object> map = new HashMap<String,Object>();
		
			mapHongXunProductionItem(map,item.getHongXunPurchaseItem());
			mapHongXunWeldItemInStock(map,item);
			list.add(map);
		}
		
		return list;
	}*/
	
	@Override
	public List<Map<String, Object>> getWeldItemsInStore(int orderNumID) {
		// TODO Auto-generated method stub
    	@SuppressWarnings("unchecked")
		List<HongXunPoN> hongXunPoNs = (List<HongXunPoN>) stockDao.quarywithpara("HongXunPoN", "orderNumID", orderNumID); 
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		//System.out.println("list.size:" + list.size());
    	for(HongXunPoN item : hongXunPoNs){
        	@SuppressWarnings("unchecked")
    		List<HongXunWeldItemInStock> hongXunWeldItemInStocks = (List<HongXunWeldItemInStock>) stockDao.quarywithpara("HongXunWeldItemInStock", "poNID", item.getIdc()); 
        	for(HongXunWeldItemInStock hongXunWeldItemInStock : hongXunWeldItemInStocks){
        		Map<String,Object> map = new HashMap<String,Object>();
    			mapHongXunWeldItemInStock(map, hongXunWeldItemInStock);
    			map.put("materialNum", item.getMaterialNo());
    			map.put("specification", item.getMaterialDesc());
    			list.add(map);
        	}		
    	}
		return list;
	}

	/*
	int mainID = hongXunPurchaseItemInStockList.get(0).getIdc();
	HongXunWeldItemInStock hongXunPurchaseItemInStock = new HongXunWeldItemInStock();
	
	hongXunPurchaseItemInStock.setInstoreQuantity(hongXunPurchaseItemInStockList.get(0).getInstoreQuantity());
	hongXunPurchaseItemInStock.setRealQuantity(hongXunPurchaseItemInStockList.get(0).getInstoreQuantity());
	hongXunPurchaseItemInStock.setLotNum(hongXunPurchaseItemInStockList.get(0).getLotNum());
	//hongXunPurchaseItemInStock.setRemark(hongXunPurchaseItemInStockList.get(0).getRemark());
	hongXunPurchaseItemInStock.setMainID(mainID);*/
/*	@Override
	public List<Map<String, Object>> saveRow(HongXunWeldItemInStock hongXunPurchaseItemInStock) {
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
		mapHongXunWeldItemInStock(map,hongXunPurchaseItemInStock);
	//	map.put("materialRealQuantity", hongXunPurchaseItem.getMaterialRealQuantity());	
		//map.put("mysidc", mapHongXunDataTwoFather.getIdc());
		//map.put("instoreDate", mapHongXunDataTwoFather.getInstoreDate());
		list.add(map);
		return list;
	}*/
	

	
	private void mapHongXunWeldItemInStock(Map<String, Object> map, HongXunWeldItemInStock item) {
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

	private  List<Map<String, Object>> instoreProductionCheck(HongXunPoN item, int quantity, String materialNum, List<Map<String, Object>> list){
		if(item.getQuantity() != null){
			if(item.getStockInQuantity() == null){
				if(quantity > item.getQuantity()){
					System.out.println("入库数量大于订单生产数量");
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("error", "物料号:"+ materialNum +"->入库数量大于订单生产数量");
					list.add(map);
					return list;
				}
			}else{
				if(quantity > item.getQuantity()-item.getStockInQuantity()){
					System.out.println("入库数量大于订单生产未入库数量...");
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("error", "物料号:"+ materialNum +"->入库数量大于订单生产未入库数量...");
					list.add(map);
					return list;
				}
			}
			HongXunBomTree hongXunBomTree = new HongXunBomTree();
			hongXunBomTree.setSn(1);
			hongXunBomTree.setBomMaterialNum(materialNum);
			List<HongXunBomTree> hongXunBomTrees = iBomTreeDao.quary(hongXunBomTree);
			if(hongXunBomTrees.size()==1){
				if(hongXunBomTrees.get(0).getBomGroup().indexOf("焊接") == -1){
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("error", "物料号:"+ materialNum +"->不是焊接件");
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
	
	private void instoreProductionQuantityAdd(HongXunPoN item, int quantity, String materialNum, List<Map<String, Object>> list){

		@SuppressWarnings("unchecked")
		List<HongXunProductionWeldStock> hongXunProductionWeldStocks = (List<HongXunProductionWeldStock>) stockDao.quarywithpara("HongXunProductionWeldStock", "materialNum", materialNum); 
    	if(hongXunProductionWeldStocks.size()==0 || hongXunProductionWeldStocks.size()==1){
			if(hongXunProductionWeldStocks.size()==0){
	    		HongXunProductionWeldStock hongXunProductionWeldStock = new HongXunProductionWeldStock();
	    		hongXunProductionWeldStock.setMaterialNum(item.getMaterialNo());
	    		hongXunProductionWeldStock.setQuantity(quantity);
	    		hongXunProductionWeldStock.setInQuantity(quantity);
	    		hongXunProductionWeldStock.setSpecification(item.getMaterialDesc());
	    		hongXunProductionWeldStock.setUnit(item.getUnit());
	    		iWeldDao.save(hongXunProductionWeldStock);	    		
	    	}else if(hongXunProductionWeldStocks.size()==1){
	    		if(hongXunProductionWeldStocks.get(0).getQuantity() != null){
	    			hongXunProductionWeldStocks.get(0).setQuantity(hongXunProductionWeldStocks.get(0).getQuantity() + quantity);
	    		}else{
	    			hongXunProductionWeldStocks.get(0).setQuantity(quantity);
	    		}
	    		if(hongXunProductionWeldStocks.get(0).getInQuantity() != null){
	    			hongXunProductionWeldStocks.get(0).setInQuantity(hongXunProductionWeldStocks.get(0).getInQuantity() + quantity);
	    		}else{
	    			hongXunProductionWeldStocks.get(0).setInQuantity(quantity);
	    		}
	    		iWeldDao.update(hongXunProductionWeldStocks.get(0));
	    	}
			HongXunWeldItemInStock hongXunWeldItemInStock = new HongXunWeldItemInStock();
			hongXunWeldItemInStock.setPoNID(item.getIdc());
			hongXunWeldItemInStock.setDate(new Date());
			hongXunWeldItemInStock.setQuantity(quantity);
			iWeldDao.save(hongXunWeldItemInStock);
			
			
			Map<String, Object> map = new HashMap<String, Object>();
			mapHongXunWeldItemInStock(map,hongXunWeldItemInStock);			
			map.put("materialNum", item.getMaterialNo());
			map.put("specification", item.getMaterialDesc());
			list.add(map);
			//break;
    	}else{
    		System.out.println("HongXunMaterialStock 有重复料号出错");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", "物料号:"+ materialNum +"->有重复料号,请联系工程师");
			list.add(map);
			//break;
    	}
	}
	
	@Override
	public List<Map<String, Object>> instoreProductionWeld(String materialNum, int orderNumID, int quantity) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if(iOrderDao.orderNumFindById(orderNumID).getStatus().equals("新定单")){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", "定单未分解，禁止出入库");
			list.add(map);
			return list;
		}
    	//@SuppressWarnings("unchecked")
		//List<HongXunPoN> hongXunPoNs = (List<HongXunPoN>) stockDao.quarywithpara("HongXunPoN", "orderNumID", orderNumID); 
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
        		instoreProductionCheck(hongXunPoNs.get(0),  quantity,  materialNum,  list);
        		if(list.size()>0){
        			return list;
        		}
    			instoreProductionQuantityAdd(hongXunPoNs.get(0), quantity, materialNum, list);
    			if(hongXunPoNs.get(0).getStockInQuantity() == null){				
    				hongXunPoNs.get(0).setStockInQuantity(quantity);
    			}else{
    				hongXunPoNs.get(0).setStockInQuantity(hongXunPoNs.get(0).getStockInQuantity() + quantity);
    			}
    			//System.out.println("hongXunPoNs.get(0) " + hongXunPoNs.get(0).getIdc());
    			iPonDao.update(hongXunPoNs.get(0));
    		}else{
    			int totalQuantity = 0;
    			int totalInStoreQuantiy=0;
    			for(HongXunPoN item: hongXunPoNs){	
    				 totalQuantity =  totalQuantity + item.getQuantity();
    				 if(item.getStockInQuantity() != null){
    					 totalInStoreQuantiy = totalInStoreQuantiy + item.getStockInQuantity(); 
    				 }
    			}
    			//System.out.println("totalQuantity: " + totalQuantity);
    			//System.out.println("totalInStoreQuantiy: " + totalInStoreQuantiy);
    			HongXunPoN hongXunPoN = hongXunPoNs.get(0).clone();
    			hongXunPoN.setQuantity(totalQuantity);
    			hongXunPoN.setStockInQuantity(totalInStoreQuantiy);
    			
        		instoreProductionCheck(hongXunPoN,  quantity,  materialNum,  list);
        		if(list.size()>0){
        			return list;
        		}
        		instoreProductionQuantityAdd(hongXunPoNs.get(0), quantity, materialNum, list);
        		int quantityClone = quantity;  
        		for(HongXunPoN item: hongXunPoNs){	
        			if(item.getStockInQuantity() == null){
        				if(quantityClone>=item.getQuantity()){
        					item.setStockInQuantity(item.getQuantity());
        					quantityClone = quantityClone - item.getQuantity();
        				}else{
        					if(quantityClone != 0){
        						item.setStockInQuantity(quantityClone); 
        						quantityClone=0;
        					}
        					
        				}
        				
        			}else{
        				int dfQuantity = item.getQuantity()- item.getStockInQuantity();
        				if(dfQuantity != 0){
	        				if(quantityClone>=dfQuantity){
	        					item.setStockInQuantity(item.getQuantity());
	        					quantityClone = quantityClone - dfQuantity;
	        				}else{
	        					if(quantityClone != 0){
	        						item.setStockInQuantity(item.getStockInQuantity() + quantityClone);
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
    	return list;
	}	
/*
	@Override
	public List<Map<String, Object>> instoreProductionWeld(String materialNum, int orderNumID, int quantity) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	@SuppressWarnings("unchecked")
		List<HongXunPoN> hongXunPoNs = (List<HongXunPoN>) stockDao.quarywithpara("HongXunPoN", "orderNumID", orderNumID); 
		for(HongXunPoN item: hongXunPoNs){	
			if(item.getMaterialNo().equals(materialNum)){
				if(item.getQuantity() != null){
					if(item.getStockInQuantity() == null){
						if(quantity > item.getQuantity()){
							System.out.println("入库数量大于订单生产数量");
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("error", "物料号:"+ materialNum +"->入库数量大于订单生产数量");
							list.add(map);
							return list;
						}
					}else{
						if(quantity > item.getQuantity()-item.getStockInQuantity()){
							System.out.println("入库数量大于订单生产未入库数量...");
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("error", "物料号:"+ materialNum +"->入库数量大于订单生产未入库数量...");
							list.add(map);
							return list;
						}
					}
					HongXunBomTree hongXunBomTree = new HongXunBomTree();
					hongXunBomTree.setSn(1);
					hongXunBomTree.setBomMaterialNum(materialNum);
					List<HongXunBomTree> hongXunBomTrees = stockDao.quary(hongXunBomTree);
					if(hongXunBomTrees.size()==1){
						if(hongXunBomTrees.get(0).getBomGroup().indexOf("焊接") == -1){
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("error", "物料号:"+ materialNum +"->不是焊接件");
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
					
				}

				@SuppressWarnings("unchecked")
				List<HongXunProductionWeldStock> hongXunProductionWeldStocks = (List<HongXunProductionWeldStock>) stockDao.quarywithpara("HongXunProductionWeldStock", "materialNum", materialNum); 
		    	if(hongXunProductionWeldStocks.size()==0 || hongXunProductionWeldStocks.size()==1){
					if(hongXunProductionWeldStocks.size()==0){
			    		HongXunProductionWeldStock hongXunProductionWeldStock = new HongXunProductionWeldStock();
			    		hongXunProductionWeldStock.setMaterialNum(item.getMaterialNo());
			    		hongXunProductionWeldStock.setQuantity(quantity);
			    		hongXunProductionWeldStock.setSpecification(item.getMaterialDesc());
			    		hongXunProductionWeldStock.setUnit(item.getUnit());
			    		stockDao.save(hongXunProductionWeldStock);	    		
			    	}else if(hongXunProductionWeldStocks.size()==1){
			    		if(hongXunProductionWeldStocks.get(0).getQuantity() != null){
			    			hongXunProductionWeldStocks.get(0).setQuantity(hongXunProductionWeldStocks.get(0).getQuantity() + quantity);
			    		}else{
			    			hongXunProductionWeldStocks.get(0).setQuantity(quantity);
			    		}
			    		stockDao.update(hongXunProductionWeldStocks.get(0));
			    	}
					HongXunWeldItemInStock hongXunWeldItemInStock = new HongXunWeldItemInStock();
					hongXunWeldItemInStock.setPoNID(item.getIdc());
					hongXunWeldItemInStock.setDate(new Date());
					hongXunWeldItemInStock.setQuantity(quantity);
					stockDao.save(hongXunWeldItemInStock);
					if(item.getStockInQuantity() == null){
						item.setStockInQuantity(hongXunWeldItemInStock.getQuantity());
					}else{
						item.setStockInQuantity(item.getStockInQuantity() + hongXunWeldItemInStock.getQuantity());
					}
					stockDao.update(item);
					Map<String, Object> map = new HashMap<String, Object>();
					mapHongXunWeldItemInStock(map,hongXunWeldItemInStock);
	    			map.put("materialNum", item.getMaterialNo());
	    			map.put("specification", item.getMaterialDesc());
					list.add(map);
					break;
		    	}else{
		    		System.out.println("HongXunMaterialStock 有重复料号出错");
					Map<String, Object> map = new HashMap<String, Object>();
	    			map.put("error", "物料号:"+ materialNum +"->有重复料号,请联系工程师");
					list.add(map);
					break;
		    	}
			}
		}
		if(list.size()==0){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", "物料号:"+ materialNum +"->订单中无信息");
			list.add(map);
		}
		return list;
	}
*/
}
