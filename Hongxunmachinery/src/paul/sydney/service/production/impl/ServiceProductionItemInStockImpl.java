package paul.sydney.service.production.impl;

import java.text.SimpleDateFormat;
import java.util.AbstractList;
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
import paul.sydney.dao.IProductionItemInStockDao;
import paul.sydney.dao.StockDao;
import paul.sydney.model.HongXunMaterialStock;
import paul.sydney.model.HongXunPoN;
import paul.sydney.model.HongXunProductionItemInStock;
import paul.sydney.model.HongXunProductionStock;
import paul.sydney.model.HongXunPurchaseItem;
import paul.sydney.model.HongXunPurchaseItemInStock;
import paul.sydney.model.HongXunPurchaseNum;
import paul.sydney.service.production.ServiceProductionItemInStockInf;
import paul.sydney.model.HongXunBomTree;
import paul.sydney.model.HongXunMaterialItemOutStock;
import paul.sydney.model.HongXunProductionItemInStock;
@Transactional
@Service("serviceProductionItemInStock")
public class ServiceProductionItemInStockImpl implements ServiceProductionItemInStockInf{
	
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
	IOrderNumDao iOrderDao;
	public void setStockDao(IOrderNumDao iOrderDao) {
		this.iOrderDao = iOrderDao;
	}
	@Autowired
	IPonDao iPonDao;
	public void setStockDao(IPonDao iPonDao) {
		this.iPonDao = iPonDao;
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
/*
	@Override
	public List<Map<String, Object>> loadData() {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		List<HongXunProductionItemInStock>  hongXunPurchaseItemInStocks = stockDao.quary(new HongXunProductionItemInStock());
		for(HongXunProductionItemInStock item : hongXunPurchaseItemInStocks){
			Map<String,Object> map = new HashMap<String,Object>();		
			mapHongXunProductionItem(map,item.getHongXunPurchaseItem());
			mapHongXunProductionItemInStock(map,item);
			list.add(map);
		}
		
		return list;
	}*/
	
	@Override
	public List<Map<String, Object>> getProductionItemsInStore(int orderNumID) {
		// TODO Auto-generated method stub
    	@SuppressWarnings("unchecked")
		List<HongXunPoN> hongXunPoNs = (List<HongXunPoN>) stockDao.quarywithpara("HongXunPoN", "orderNumID", orderNumID); 
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		//System.out.println("list.size:" + list.size());
    	for(HongXunPoN item : hongXunPoNs){
        	@SuppressWarnings("unchecked")
    		List<HongXunProductionItemInStock> hongXunProductionItemInStocks = (List<HongXunProductionItemInStock>) stockDao.quarywithpara("HongXunProductionItemInStock", "poNID", item.getIdc()); 
        	for(HongXunProductionItemInStock hongXunProductionItemInStock : hongXunProductionItemInStocks){
        		Map<String,Object> map = new HashMap<String,Object>();
    			mapHongXunProductionItemInStock(map, hongXunProductionItemInStock);
    			map.put("materialNum", item.getMaterialNo());
    			map.put("specification", item.getMaterialDesc());
    			list.add(map);
        	}		
    	}
		return list;
	}

	/*
	int mainID = hongXunPurchaseItemInStockList.get(0).getIdc();
	HongXunProductionItemInStock hongXunPurchaseItemInStock = new HongXunProductionItemInStock();
	
	hongXunPurchaseItemInStock.setInstoreQuantity(hongXunPurchaseItemInStockList.get(0).getInstoreQuantity());
	hongXunPurchaseItemInStock.setRealQuantity(hongXunPurchaseItemInStockList.get(0).getInstoreQuantity());
	hongXunPurchaseItemInStock.setLotNum(hongXunPurchaseItemInStockList.get(0).getLotNum());
	//hongXunPurchaseItemInStock.setRemark(hongXunPurchaseItemInStockList.get(0).getRemark());
	hongXunPurchaseItemInStock.setMainID(mainID);*/
/*	@Override
	public List<Map<String, Object>> saveRow(HongXunProductionItemInStock hongXunPurchaseItemInStock) {
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
	

	
	private void mapHongXunProductionItemInStock(Map<String, Object> map, HongXunProductionItemInStock item) {
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

	private  List<Map<String, Object>> instoreProductionCheck(HongXunPoN hongXunPoN, int quantity,  List<Map<String, Object>> list){
		if(hongXunPoN.getWorkQuantity() != null){
			if(hongXunPoN.getStockInQuantity() == null){
				if(quantity > hongXunPoN.getWorkQuantity()){
					System.out.println("入库数量大于订单生产数量");
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("error", "物料号:" + hongXunPoN.getMaterialNo() + "->" + hongXunPoN.getMaterialDesc() +"->入库数量大于订单生产数量");
					list.add(map);
					return list;
				}
			}else{
				if(quantity > hongXunPoN.getWorkQuantity()-hongXunPoN.getStockInQuantity()){
					System.out.println("入库数量大于订单生产未入库数量...");
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("error", "物料号:"+ hongXunPoN.getMaterialNo() + "->" + hongXunPoN.getMaterialDesc() +"->入库数量大于订单生产未入库数量...");
					list.add(map);
					return list;
				}
			}
			HongXunBomTree hongXunBomTree = new HongXunBomTree();
			hongXunBomTree.setSn(1);
			hongXunBomTree.setBomMaterialNum(hongXunPoN.getMaterialNo());
			List<HongXunBomTree> hongXunBomTrees = iBomTreeDao.quary(hongXunBomTree);
			if(hongXunBomTrees.size()==1){
				if(!(hongXunBomTrees.get(0).getBomGroup().equals("B00装配清单") || hongXunBomTrees.get(0).getBomGroup().equals("732"))){
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("error", "物料号:"+ hongXunPoN.getMaterialNo() + "->" + hongXunPoN.getMaterialDesc() +"->不是装配件");
					list.add(map);
					return list;
				}
			}else if(hongXunBomTrees.size()==0){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("error", "物料号:"+ hongXunPoN.getMaterialNo() + "->" + hongXunPoN.getMaterialDesc() +"->Bom无信息记录");
				list.add(map);
				return list;
			}else{
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("error", "物料号:"+ hongXunPoN.getMaterialNo() + "->" + hongXunPoN.getMaterialDesc() +"->Bom中发现重复");
				list.add(map);
				return list;
			}
			
		}else{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", "物料号:"+ hongXunPoN.getMaterialNo() + "->" + hongXunPoN.getMaterialDesc() +"->生产数量为空");
			list.add(map);
			return list;
		}
		return list;
	}
	
	private void instoreProductionQuantityAdd(HongXunPoN hongXunPoN, int quantity, List<Map<String, Object>> list){
		@SuppressWarnings("unchecked")
		List<HongXunProductionStock> hongXunProductionStocks = (List<HongXunProductionStock>) stockDao.quarywithpara("HongXunProductionStock", "materialNum", hongXunPoN.getMaterialNo()); 
    	//if(hongXunProductionStocks.size()==0 || hongXunProductionStocks.size()==1){
			if(hongXunProductionStocks.size()==0){
	    		HongXunProductionStock hongXunProductionStock = new HongXunProductionStock();
	    		hongXunProductionStock.setMaterialNum(hongXunPoN.getMaterialNo());
	    		hongXunProductionStock.setQuantity(quantity);
	    		hongXunProductionStock.setInQuantity(quantity);
	    		hongXunProductionStock.setSpecification(hongXunPoN.getMaterialDesc());
	    		hongXunProductionStock.setUnit(hongXunPoN.getUnit());
	    		iProductionDao.save(hongXunProductionStock);	    		
	    	}else if(hongXunProductionStocks.size()==1){
	    		if(hongXunProductionStocks.get(0).getQuantity() != null){
	    			hongXunProductionStocks.get(0).setQuantity(hongXunProductionStocks.get(0).getQuantity() + quantity);
	    		}else{
	    			hongXunProductionStocks.get(0).setQuantity(quantity);
	    		}
	    		if(hongXunProductionStocks.get(0).getInQuantity() != null){
	    			hongXunProductionStocks.get(0).setInQuantity(hongXunProductionStocks.get(0).getInQuantity() + quantity);
	    		}else{
	    			hongXunProductionStocks.get(0).setInQuantity(quantity);
	    		}
	    		iProductionDao.update(hongXunProductionStocks.get(0));
	    	}else{
	    		String desc = null;
				int indexs = hongXunPoN.getMaterialDesc().indexOf("长度L=:");
				if(indexs > -1){
					String str = hongXunPoN.getMaterialDesc().substring(indexs+5);
					desc = str.substring(0, str.indexOf("."));				
				}
	    		for(HongXunProductionStock item: hongXunProductionStocks){
	    			if(desc == null){
	    				System.out.println("请查看这个问题：" + hongXunPoN.getMaterialNo() + "->" + hongXunPoN.getMaterialDesc());
	    			}else{
	    				if(item.getSpecification().indexOf(desc)>-1){
	    					if(item.getQuantity() != null){
	    						item.setQuantity(item.getQuantity() + quantity);
	    		    		}else{
	    		    			item.setQuantity(quantity);
	    		    		}
	    		    		if(item.getInQuantity() != null){
	    		    			item.setInQuantity(item.getInQuantity() + quantity);
	    		    		}else{
	    		    			item.setInQuantity(quantity);
	    		    		}
	    		    		iProductionDao.update(item);
	    				}
	    			}
	    		}
	    	}
			HongXunProductionItemInStock hongXunProductionItemInStock = new HongXunProductionItemInStock();
			hongXunProductionItemInStock.setPoNID(hongXunPoN.getIdc());
			hongXunProductionItemInStock.setDate(new Date());
			hongXunProductionItemInStock.setQuantity(quantity);
			iProductionItemInStockDao.save(hongXunProductionItemInStock);

			Map<String, Object> map = new HashMap<String, Object>();
			mapHongXunProductionItemInStock(map,hongXunProductionItemInStock);
			map.put("materialNum", hongXunPoN.getMaterialNo());
			map.put("specification", hongXunPoN.getMaterialDesc());
			list.add(map);
			//break;
    	/*}else{
    		System.out.println("HongXunMaterialStock 有重复料号出错");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", "物料号:"+ materialNum +"->有重复料号,请联系工程师");
			list.add(map);
			//break;
    	}*/
	}
	
	@Override
	public List<Map<String, Object>> instoreProduction(String materialNum, int orderItemID, int orderNumID, int quantity) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if(iOrderDao.orderNumFindById(orderNumID).getStatus().equals("新订单")){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", "订单未分解，禁止出入库");
			list.add(map);
			return list;
		}
		HongXunPoN hongXunPoN = iPonDao.hongXunPoNFindById(orderItemID);
		instoreProductionCheck(hongXunPoN,  quantity,  list);
		if(list.size()>0){
			return list;
		}
		if(hongXunPoN == null){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", "物料号:"+ materialNum +"->订单中无信息");
			list.add(map);
			return list;
		}
		instoreProductionQuantityAdd(hongXunPoN, quantity, list);
    	return list;	
	}

}
