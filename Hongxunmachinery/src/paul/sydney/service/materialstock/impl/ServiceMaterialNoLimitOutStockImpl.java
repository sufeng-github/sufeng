package paul.sydney.service.materialstock.impl;

import org.springframework.transaction.annotation.Transactional;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import paul.sydney.dao.IMaterialNoLimitInStockDao;
import paul.sydney.dao.IMaterialNoLimitOutStockDao;
import paul.sydney.dao.IMaterialStockDao;
import paul.sydney.dao.StockDao;
import paul.sydney.model.HongXunBomTree;
import paul.sydney.model.HongXunMaterialStock;
import paul.sydney.model.HongXunWorkNum;
import paul.sydney.service.materialstock.ServiceMaterialNoLimitOutStockInf;
import paul.sydney.model.HongXunMaterialNoLimitOutStock;
import paul.sydney.model.HongXunWeiwaiItem;
import paul.sydney.model.HongXunWeiwaiNum;
import paul.sydney.model.HongXunWorkItem;

@Transactional
@Service("serviceNoLimitOutStockImpl")
public class ServiceMaterialNoLimitOutStockImpl implements ServiceMaterialNoLimitOutStockInf{
	
	@Autowired
	StockDao stockDao;
	public void setStockDao(StockDao stockDao) {
		this.stockDao = stockDao;
	}
	@Autowired
	IMaterialStockDao iMaterialStockDao;
	public void setStockDao(IMaterialStockDao iMaterialStockDao) {
		this.iMaterialStockDao = iMaterialStockDao;
	}
	@Autowired
	IMaterialNoLimitOutStockDao iMaterialNoLimitOutStockDao;
	public void setStockDao(IMaterialNoLimitOutStockDao iMaterialNoLimitOutStockDao) {
		this.iMaterialNoLimitOutStockDao = iMaterialNoLimitOutStockDao;
	}
	@Override
	public List<Map<String, Object>> loadData() {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		List<HongXunMaterialNoLimitOutStock>  hongXunPurchaseItemOutStocks = iMaterialNoLimitOutStockDao.quary(new HongXunMaterialNoLimitOutStock());
		for(int i=hongXunPurchaseItemOutStocks.size()-1; i>=0; i--){
			Map<String,Object> map = new HashMap<String,Object>();
			//mapHongXunPurchaseItem(map,item.getHongXunPurchaseItem());
			mapHongXunMaterialItemOutStock(map,hongXunPurchaseItemOutStocks.get(i));
			list.add(map);
		}
		
		return list;
	}
	
/*	@Override
	public List<Map<String, Object>> saveRow(HongXunMaterialNoLimitOutStock hongXunPurchaseItemOutStock) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		HongXunPurchaseItem hongXunPurchaseItem = stockDao.purchaseItemFindById(hongXunPurchaseItemOutStock.getIdc());
		System.out.println("MaterialPlanQuantity:" + hongXunPurchaseItem.getMaterialPlanQuantity());
		@SuppressWarnings("unchecked")
		List<HongXunMaterialStock> hongXunMaterialStocks = (List<HongXunMaterialStock>) stockDao.quarywithpara("HongXunMaterialStock", "materialNum", hongXunPurchaseItem.getMaterialNum()); 
    	if(hongXunMaterialStocks.size()==1){
    		if(hongXunMaterialStocks.get(0).getQuantity() >= hongXunPurchaseItem.getMaterialPlanQuantity()){
    			hongXunMaterialStocks.get(0).setQuantity(hongXunMaterialStocks.get(0).getQuantity() - hongXunPurchaseItem.getMaterialPlanQuantity());
    			stockDao.update(hongXunMaterialStocks.get(0));
    			hongXunPurchaseItemOutStock.setQuantity(hongXunPurchaseItem.getMaterialPlanQuantity());
    			//hongXunPurchaseItemOutStock.setHongXunPurchaseItem(hongXunPurchaseItem);
    			hongXunPurchaseItemOutStock.setDate(new Date());
    			
    			//stockDao.update(hongXunPurchaseItem);
    			stockDao.save(hongXunPurchaseItemOutStock);
    			Map<String,Object> map = new HashMap<String,Object>();
    			//mapHongXunPurchaseItem(map,hongXunPurchaseItem);
    			mapHongXunMaterialItemOutStock(map,hongXunPurchaseItemOutStock);
    			list.add(map);
    		}
    		
    	}else if(hongXunMaterialStocks.size()>1){
    		System.out.println("HongXunMaterialStock 有重复料号出错");
    	}else{
    		System.out.println("无库存记录");
    	}

		return list;
	}*/
	
	@Override
	public List<Map<String, Object>> getWorkItemStorckOuts(String workNum) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    	@SuppressWarnings("unchecked")
		List<HongXunWorkNum> hongXunWorkNums = (List<HongXunWorkNum>) stockDao.quarywithpara("HongXunWorkNum", "workNum", workNum);
		if(hongXunWorkNums.size()==1){
	    	@SuppressWarnings("unchecked")
			List<HongXunWorkItem> hongXunWorkItems = (List<HongXunWorkItem>) stockDao.quarywithpara("HongXunWorkItem", "workNumID", hongXunWorkNums.get(0).getIdc());
	    	for(HongXunWorkItem item: hongXunWorkItems){
				@SuppressWarnings("unchecked")
				List<HongXunBomTree> hongXunbomTrees = (List<HongXunBomTree>) stockDao.quarywithpara("HongXunBomTree", "BomMaterialNum", item.getMaterialNum()); 
				if(hongXunbomTrees.size()>0){
					@SuppressWarnings({"unchecked" })
					List<HongXunBomTree> sunBomList = (List<HongXunBomTree>) stockDao.quarywithpara("HongXunBomTree", "parentID", String.valueOf(hongXunbomTrees.get(0).getIdc()));
					for(HongXunBomTree bomItem: sunBomList){
						Map<String,Object> map = new HashMap<String,Object>();	
						map.put("idc", bomItem.getIdc());
						map.put("materialNum", bomItem.getBomMaterial());
						map.put("materialSpecification", bomItem.getBomSpacification());
						list.add(map);
					}
				}
	    	}
		}else{
			System.out.println("getWorkItemStorckOuts error");
		}
    	return list;
	}


	@Override
	public List<Map<String, Object>> getWeiwaiItemStorckOuts(String weiwaiNum) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    	@SuppressWarnings("unchecked")
		List<HongXunWeiwaiNum> hongXunWeiwaiNums = (List<HongXunWeiwaiNum>) stockDao.quarywithpara("HongXunWeiwaiNum", "weiwaiNum", weiwaiNum);
		if(hongXunWeiwaiNums.size()==1){
	    	@SuppressWarnings("unchecked")
			List<HongXunWeiwaiItem> hongXunWeiwaiItems = (List<HongXunWeiwaiItem>) stockDao.quarywithpara("HongXunWeiwaiItem", "weiwaiNumID", hongXunWeiwaiNums.get(0).getIdc());
	    	for(HongXunWeiwaiItem item: hongXunWeiwaiItems){
				@SuppressWarnings("unchecked")
				List<HongXunBomTree> hongXunbomTrees = (List<HongXunBomTree>) stockDao.quarywithpara("HongXunBomTree", "BomMaterialNum", item.getMaterialNum()); 
				if(hongXunbomTrees.size()>0){
					@SuppressWarnings({"unchecked" })
					List<HongXunBomTree> sunBomList = (List<HongXunBomTree>) stockDao.quarywithpara("HongXunBomTree", "parentID", String.valueOf(hongXunbomTrees.get(0).getIdc()));
					for(HongXunBomTree bomItem: sunBomList){
						Map<String,Object> map = new HashMap<String,Object>();	
						map.put("idc", bomItem.getIdc());
						map.put("materialNum", bomItem.getBomMaterial());
						map.put("materialSpecification", bomItem.getBomSpacification());
						list.add(map);
					}
				}
	    	}
		}else{
			System.out.println("getWeiwaiItemStorckOuts error");
		}
    	return list;
	}
/*
	@Override
	public List<Map<String, Object>> getWorkItemStorckOuts(List<HongXunWorkItem> hongXunWorkItemList) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> list1 = new ArrayList<Map<String,Object>>();	
		Boolean flag = false;
		for(HongXunWorkItem item: hongXunWorkItemList){
	    	@SuppressWarnings("unchecked")
			List<HongXunPurchaseItemOutStock> hongXunPurchaseItemOutStocks = (List<HongXunPurchaseItemOutStock>) stockDao.quarywithpara("HongXunPurchaseItemOutStock", "workItemID", item.getIdc()); 
			if(hongXunPurchaseItemOutStocks.size()>0){
				flag = true;
				break;
			}
		}
		
		for(HongXunWorkItem item: hongXunWorkItemList){
	    	@SuppressWarnings("unchecked")
			List<HongXunPurchaseItemOutStock> hongXunPurchaseItemOutStocks = (List<HongXunPurchaseItemOutStock>) stockDao.quarywithpara("HongXunPurchaseItemOutStock", "workItemID", item.getIdc()); 
			if(hongXunPurchaseItemOutStocks.size()>0){
				flag = true;
				
		    	for(HongXunPurchaseItemOutStock outStockItem: hongXunPurchaseItemOutStocks){
					Map<String,Object> map = new HashMap<String,Object>();
					mapHongXunMaterialItemOutStock(map, outStockItem);
					list.add(map);
				}
			}else{
				if(flag == false){
					@SuppressWarnings("unchecked")
					List<HongXunBomTree> hongXunbomTrees = (List<HongXunBomTree>) stockDao.quarywithpara("HongXunBomTree", "BomMaterialNum", item.getMaterialNum()); 					
					if(hongXunbomTrees.size()==1){
						@SuppressWarnings({"unchecked" })
						List<HongXunBomTree> sunBomList = (List<HongXunBomTree>) stockDao.quarywithpara("HongXunBomTree", "parentID", String.valueOf(hongXunbomTrees.get(0).getIdc()));
						for(HongXunBomTree bomItem: sunBomList){
							HongXunPurchaseItemOutStock hongXunPurchaseItemOutStock = new HongXunPurchaseItemOutStock();
							hongXunPurchaseItemOutStock.setOutStoreDate(new Date());
							if(hongXunbomTrees.get(0).getBomQuantity()!=null && item.getQuantity()!=null){
								hongXunPurchaseItemOutStock.setQuantity(bomItem.getBomQuantity() * item.getQuantity());
							}
							hongXunPurchaseItemOutStock.setWorkItemID(item.getIdc());		
							Map<String,Object> map = new HashMap<String,Object>();	
							mapHongXunMaterialItemOutStock(map, hongXunPurchaseItemOutStock);
							@SuppressWarnings("unchecked")
							List<HongXunMaterialStock> hongXunMaterialStocks = (List<HongXunMaterialStock>) stockDao.quarywithpara("HongXunMaterialStock", "BomMaterialNum", item.getMaterialNum()); 					
							if(hongXunMaterialStocks.size()>0){
								map.put("materialNum", hongXunMaterialStocks.get(0).getMaterialNum());
								map.put("specification", hongXunMaterialStocks.get(0).getSpecification());  	
								map.put("unit",hongXunMaterialStocks.get(0).getUnit());	
							}
							list1.add(map);
						}
					}else{
						System.out.println("出错Bom没有或多个部件号：" + item.getMaterialNum());
					}
				}
			}
		}
		if(flag == true){
			return list;
		}else{
			return list1;
		}*/
			
/*		hongXunDataThree.getWorkQuantity();
		HongXunBomTree hongXunBomTree = new HongXunBomTree();
		hongXunBomTree.setBomMaterialNum(hongXunDataThree.getComponentNum());
		List<HongXunBomTree> hongXunBomTrees = stockDao.quary(hongXunBomTree);
		if(hongXunBomTrees.size()==1){
			@SuppressWarnings("unchecked")
			List<HongXunBomTree> hongXunbomTrees = (List<HongXunBomTree>) stockDao.quarywithpara("HongXunBomTree", "parentID", hongXunBomTrees.get(0).getIdc()); 		
		}else{
			return null;
		}*/		
		/*HongXunPurchaseItemOutStock hongXunDataTwoChildren = new HongXunPurchaseItemOutStock();
    	//hongXunDataTwoChildren.setChildUncleID(hongXunDataThree.getIdc());
    	List<HongXunPurchaseItemOutStock> hongXunDataTwoChildrens = stockDao.quary(hongXunDataTwoChildren); 	
    	for(HongXunPurchaseItemOutStock item : hongXunDataTwoChildrens){
			Map<String,Object> map = new HashMap<String,Object>();
			mapHongXunMaterialItemOutStock(map, item);
			list.add(map);
    	}	
		return list;
	}*/
	
	/*
	@Override
	public List<Map<String, Object>> outstore(HongXunPurchaseItemOutStock hongXunDataTwoChildren) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		if(hongXunDataTwoChildren.getIdc()==0){
		hongXunPurchaseItemOutStock hongXunDataTwoSun = stockDao.dataTwoFatherFindById(hongXunDataTwoChildren.getMainID()); 
		hongXunDataTwoSun.setRealQuantity(hongXunDataTwoSun.getRealQuantity()-hongXunDataTwoChildren.getQuantity());
		stockDao.update(hongXunDataTwoSun);
		hongXunDataTwoChildren.setHongXunPurchaseItemInStock(hongXunDataTwoSun);
		hongXunDataTwoChildren.setOutStoreDate(new Date());
		stockDao.save(hongXunDataTwoChildren);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("idc", hongXunDataTwoChildren.getIdc());
		map.put("outStoreDate", hongXunDataTwoChildren.getOutStoreDate());
		list.add(map);
		}else{
			stockDao.update(hongXunDataTwoChildren);
		}
		return list;
	}*/
/*	@Override
	public List<Map<String, Object>> loadData() {
		// TODO Auto-generated method stub
		//System.out.println("serviceBranchoneload");rrrRR
		HongXunDataOneBranchLeaf hongXunDataOneBranchLeaf = new HongXunDataOneBranchLeaf();
		List<HongXunDataOneBranchLeaf> hongXunDataOneBranchLeafs = stockDao.quary(hongXunDataOneBranchLeaf);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		for(HongXunDataOneBranchLeaf item : hongXunDataOneBranchLeafs){
			Map<String,Object> map = new HashMap<String,Object>();
			//System.out.println(itemSun.getIdc());
			mapHongXunDataOneBranchLeaf(map, item);
			list.add(map);
		}	
		return list;		
	}*/

	/*private void mapHongXunPurchaseItem(Map<String, Object> map, HongXunPurchaseItem item) {
		// TODO Auto-generated method stub	
		//System.out.println("test: " + item.getHongXunDataOne().getProductionOrderNum());
		map.put("idc", item.getIdc());
		map.put("materialNum", item.getMaterialNum());
		map.put("materialName", item.getMaterialName());
		map.put("specification", item.getSpecification());
		map.put("materialLotNum", item.getMaterialLotNum());
		int value = item.getMaterialPlanQuantity();
		if(value == 0){
			map.put("materialPlanQuantity", "");
		}else{
			map.put("materialPlanQuantity", item.getMaterialPlanQuantity());
		}
		
		if(item.getMaterialRealQuantity()!=null){
			map.put("materialRealQuantity", item.getMaterialRealQuantity());
		}

		map.put("materialUnit", item.getUnit());
	  	if(item.getPrice() != null){
	  		map.put("materialPrice", item.getPrice().doubleValue());
	  	}
	  	if(item.getMaterialMoney() != null){
	  		map.put("materialMoney", item.getMaterialMoney().doubleValue());
	  	}
		map.put("materialsupplier", item.getSupplier());	  	
		map.put("materialRemark", item.getMaterialRemark());
		map.put("uncleID", item.getPurchaseNumID());
		@SuppressWarnings("unchecked")
		List<HongXunMaterialStock> hongXunMaterialStocks = (List<HongXunMaterialStock>) stockDao.quarywithpara("HongXunMaterialStock", "materialNum", item.getMaterialNum()); 
    	if(hongXunMaterialStocks.size()==1){
    		map.put("storeQuantity", hongXunMaterialStocks.get(0).getQuantity());
    	}else if(hongXunMaterialStocks.size()>1){
    		System.out.println("HongXunMaterialStock 有重复料号出错");
    	}
		
	}	*/
	
	public void mapHongXunMaterialItemOutStock(Map<String, Object> map, HongXunMaterialNoLimitOutStock item) {
		// TODO Auto-generated method stub			
		map.put("idc", item.getIdc());
		map.put("quantity", item.getQuantity());
		if(item.getDate() != null){
			map.put("date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(item.getDate()));
		}
		map.put("remark", item.getRemark());
		map.put("staff", item.getStaff());
		map.put("lotNum", item.getLotNum());
		if(item.getMaterialStockID() != null){
			HongXunMaterialStock hongXunMaterialStock =iMaterialStockDao.hongXunMaterialFindById(item.getMaterialStockID());	
			map.put("materialStockID", item.getMaterialStockID());
			map.put("materialNum", hongXunMaterialStock.getMaterialNum());
			map.put("specification", hongXunMaterialStock.getSpecification());
			map.put("stockQuantity", hongXunMaterialStock.getQuantity());
		}

	}

	@Override
	public List<Map<String, Object>> autotimp(String str) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		//List<String> listName = new ArrayList<String>();
		List<HongXunMaterialStock> hongXunMaterialStocks = iMaterialStockDao.quary(new HongXunMaterialStock());	
		for(HongXunMaterialStock item: hongXunMaterialStocks){	
			if(item.getMaterialNum().indexOf(str)>-1){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("name", item.getMaterialNum() + "->" + item.getSpecification());
				map.put("id", item.getIdc());  
				list.add(map);
			}
		}
		return list;
	}


	@Override
	public List<Map<String, Object>> searchMaterialNum(String materialNum, int quantity) {
		// TODO Auto-generated method stub
		String specification = null;
		if(materialNum.indexOf("->") > 0){
			specification = materialNum.split("->")[1];
			materialNum = materialNum.split("->")[0];
		}		
		HongXunMaterialStock hongXunMaterialStock = new HongXunMaterialStock();
		hongXunMaterialStock.setMaterialNum(materialNum);
		if(specification !=null){
			hongXunMaterialStock.setSpecification(specification);
		}
		
		HongXunMaterialNoLimitOutStock hongXunMaterialItemOutStock = null;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		//HongXunMaterialStock hongXunMaterialStockEntity = new HongXunMaterialStock();
		//hongXunMaterialStockEntity.setMaterialNum(materialNum);
		//@SuppressWarnings("unchecked")
		List<HongXunMaterialStock> hongXunMaterialStocks = iMaterialStockDao.quary(hongXunMaterialStock);//(List<HongXunMaterialStock>) stockDao.quarywithpara("HongXunMaterialStock", "materialNum", materialNum); 
    	if(hongXunMaterialStocks.size()==1){
    		if(hongXunMaterialStocks.get(0).getQuantity() != null){
    			int storeQuantity = hongXunMaterialStocks.get(0).getQuantity() - quantity;
    			if(storeQuantity>-1){
    				hongXunMaterialStocks.get(0).setQuantity(storeQuantity);
    				if(hongXunMaterialStocks.get(0).getItemQuantity()==null){
    					//hongXunMaterialStocks.get(0).setItemQuantity(0-quantity);
    					Map<String, Object> map = new HashMap<String, Object>();
    					map.put("error", "物料号:"+ materialNum + " 规格:" + specification + "->原料仓库项目数为空");
    					list.add(map);
    					return list;
    				}else{	  		
    					hongXunMaterialStocks.get(0).setItemQuantity(hongXunMaterialStocks.get(0).getItemQuantity()-quantity);
    				}
    				hongXunMaterialStock = hongXunMaterialStocks.get(0); 
    				//System.out.println("hongXunMaterialStock: " + hongXunMaterialStock.getMaterialNum());
    				hongXunMaterialItemOutStock = new HongXunMaterialNoLimitOutStock();
	    	    	hongXunMaterialItemOutStock.setDate(new Date());    		
	    			hongXunMaterialItemOutStock.setQuantity(quantity);
	    				    			
    			}else{
    				Map<String, Object> map = new HashMap<String, Object>();
					map.put("error", "物料号:"+ materialNum + " 规格:" + specification + "->库存不足,库存数量：" + hongXunMaterialStocks.get(0).getQuantity());
					list.add(map);
    				System.out.println("库存不足");
    				return list;
    			}
    		}
    	}else if(hongXunMaterialStocks.size()==0){
    		Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", "物料号:"+ materialNum + " 规格:" + specification + "->无库存记录");
			list.add(map);
			return list;
    	}else{
    		//System.out.println("HongXunMaterialStock 有重复料号出错");
    		Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", "物料号:"+ materialNum + " 规格:" + specification + "->有重复料号，请联系工程师");
			list.add(map);
			return list;
    	}
    	if ((hongXunMaterialStock == null) || (hongXunMaterialItemOutStock == null)){
    		Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", "物料号:"+ materialNum + " 规格:" + specification + "->出库单中无记录");
			list.add(map);
			return list;
    	}
    	
    	if(hongXunMaterialStock != null){
    		iMaterialStockDao.update(hongXunMaterialStock);	   		
    		if(hongXunMaterialItemOutStock != null){
    			hongXunMaterialItemOutStock.setMaterialStockID(hongXunMaterialStock.getIdc());
    			iMaterialNoLimitOutStockDao.save(hongXunMaterialItemOutStock);	
    	    	Map<String, Object> map = new HashMap<String, Object>();
    			mapHongXunMaterialItemOutStock(map,hongXunMaterialItemOutStock);
    			list.add(map);
    	    }  	
    	}

		return list;
	}

	@Override
	public List<Map<String, Object>> getEntity() {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String,Object>();
		HongXunMaterialNoLimitOutStock e = new HongXunMaterialNoLimitOutStock();
		Class<? extends HongXunMaterialNoLimitOutStock> cls = e.getClass();  
        Field[] fields = cls.getDeclaredFields();  
        for(int i=0; i<fields.length; i++){  
            Field f = fields[i];  
            f.setAccessible(true); 
            map.put(f.getName(), "");
            //System.out.println("属性名:" + f.getName()/* + " 属性值:" + f.get(e)*/);  
        } 
        list.add(map);
        return list;
	}
/*
	@Override
	public List<Map<String, Object>> getMaterialItemOutStock(int outStoreNumID) {
		// TODO Auto-generated method stub
		System.out.println("asdfasdfs");
		@SuppressWarnings("unchecked")
		List<HongXunMaterialOutStoreItem> hongXunMaterialOutStoreItems = (List<HongXunMaterialOutStoreItem>) stockDao.quarywithpara("HongXunMaterialOutStoreItem", "outStoreNumID", outStoreNumID); 
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		System.out.println("list.size:" + hongXunMaterialOutStoreItems.size());
    	for(HongXunMaterialOutStoreItem item : hongXunMaterialOutStoreItems){
    		@SuppressWarnings("unchecked")
    		List<HongXunMaterialNoLimitOutStock> hongXunMaterialItemOutStocks = (List<HongXunMaterialNoLimitOutStock>) stockDao.quarywithpara("HongXunMaterialNoLimitOutStock", "outStoreItemID", item.getIdc()); 
    		for(HongXunMaterialNoLimitOutStock hongXunMaterialItemOutStock: hongXunMaterialItemOutStocks){
    			Map<String,Object> map = new HashMap<String,Object>();
    			mapHongXunMaterialItemOutStock(map,hongXunMaterialItemOutStock);
    			map.put("materialNum", item.getMaterialNum());
    			map.put("specification", item.getSpecification());
    			list.add(map);
    		}	
    	}
		return list;
	}

	@Override
	public List<Map<String, Object>> outstoreMaterialNum(String materialNum, int outStoreNumID, int quantity) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		HongXunMaterialStock hongXunMaterialStock = null;
		HongXunMaterialNoLimitOutStock hongXunMaterialItemOutStock = null;
		HongXunMaterialOutStoreItem hongXunMaterialOutStoreItem = null;
		HongXunMaterialOutStoreItem hongXunMaterialOutStoreItemEntity = new HongXunMaterialOutStoreItem();
		hongXunMaterialOutStoreItemEntity.setOutStoreNumID(outStoreNumID);
    	//@SuppressWarnings("unchecked")
    	List<HongXunMaterialOutStoreItem> hongXunMaterialOutStoreItems = stockDao.quary(hongXunMaterialOutStoreItemEntity);//(List<HongXunMaterialOutStoreItem>) stockDao.quarywithpara("HongXunMaterialOutStoreItem", "outStoreNumID", outStoreNumID); 
    	for(HongXunMaterialOutStoreItem item : hongXunMaterialOutStoreItems){ //查看工单具体项
			if(item.getMaterialNum().equals(materialNum)){	
				if(item.getStoreOutRealQuantity()==null){
					item.setStoreOutRealQuantity(0);
				}

					HongXunMaterialStock hongXunMaterialStockEntity = new HongXunMaterialStock();
					hongXunMaterialStockEntity.setMaterialNum(materialNum);
					//@SuppressWarnings("unchecked")
					List<HongXunMaterialStock> hongXunMaterialStocks = stockDao.quary(hongXunMaterialStockEntity);//(List<HongXunMaterialStock>) stockDao.quarywithpara("HongXunMaterialStock", "materialNum", materialNum); 
			    	if(hongXunMaterialStocks.size()==1){
			    		if(hongXunMaterialStocks.get(0).getQuantity() != null){
			    			int storeQuantity = hongXunMaterialStocks.get(0).getQuantity() - quantity;
			    			if(storeQuantity>-1){
			    				hongXunMaterialStocks.get(0).setQuantity(storeQuantity);
			    				if(hongXunMaterialStocks.get(0).getItemQuantity()==null){
			    					hongXunMaterialStocks.get(0).setItemQuantity(0);
			    	
			    				}
			    				hongXunMaterialStocks.get(0).setItemQuantity(hongXunMaterialStocks.get(0).getItemQuantity()-quantity);
			    				//stockDao.update(hongXunMaterialStocks.get(0));  	
			    				hongXunMaterialStock=hongXunMaterialStocks.get(0);
			    				hongXunMaterialItemOutStock = new HongXunMaterialNoLimitOutStock();

				    	    		hongXunMaterialItemOutStock.setDate(new Date());
				    			
				    					hongXunMaterialItemOutStock.setQuantity(quantity);
				       				
				    				hongXunMaterialItemOutStock.setOutStoreItemID(item.getIdc());
				    				hongXunMaterialItemOutStock.setMaterialStockID(hongXunMaterialStock.getIdc());
				
			    				item.setStoreOutDate(new Date());			    	
			    				item.setStoreOutRealQuantity(item.getStoreOutRealQuantity()+quantity);
			    				//stockDao.update(item);
			    				hongXunMaterialOutStoreItem = item;
			    				break;
			    			}else{
			    				Map<String, Object> map = new HashMap<String, Object>();
								map.put("error", "物料号:"+ materialNum +"->库存不足,库存数量：" + hongXunMaterialStocks.get(0).getQuantity());
								list.add(map);
			    				System.out.println("库存不足");
			    				return list;
			    			}
			    		}
			    	}else if(hongXunMaterialStocks.size()==0){
			    		Map<String, Object> map = new HashMap<String, Object>();
						map.put("error", "物料号:"+ materialNum +"->无库存记录");
						list.add(map);
						return list;
			    	}else{
			    		//System.out.println("HongXunMaterialStock 有重复料号出错");
			    		Map<String, Object> map = new HashMap<String, Object>();
						map.put("error", "物料号:"+ materialNum +"->有重复料号，请联系工程师");
						list.add(map);
						return list;
			    	}
				//}	
			}
		}
    	if(hongXunMaterialStock != null && hongXunMaterialItemOutStock != null && hongXunMaterialOutStoreItem != null ){
    		stockDao.update(hongXunMaterialStock); 
    		stockDao.save(hongXunMaterialItemOutStock);
    		stockDao.update(hongXunMaterialOutStoreItem);
			Map<String, Object> map = new HashMap<String, Object>();
			mapHongXunMaterialItemOutStock(map,hongXunMaterialItemOutStock);
			list.add(map);
    	}else{
    		Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", "物料号:"+ materialNum +"->出库单中无记录");
			list.add(map);		
    	}
		return list;
	}
*/
	@Override
	public List<Map<String, Object>> scanOutMaterialNum(String materialNum, int quantity) {
		// TODO Auto-generated method stub
		HongXunMaterialStock hongXunMaterialStock = null;
		HongXunMaterialNoLimitOutStock hongXunMaterialItemOutStock = null;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		HongXunMaterialStock hongXunMaterialStockEntity = new HongXunMaterialStock();
		hongXunMaterialStockEntity.setMaterialNum(materialNum);
		//@SuppressWarnings("unchecked")
		List<HongXunMaterialStock> hongXunMaterialStocks = iMaterialStockDao.quary(hongXunMaterialStockEntity);//(List<HongXunMaterialStock>) stockDao.quarywithpara("HongXunMaterialStock", "materialNum", materialNum); 
    	if(hongXunMaterialStocks.size()==1){
    		if(hongXunMaterialStocks.get(0).getQuantity() != null){
    			int storeQuantity = hongXunMaterialStocks.get(0).getQuantity() - quantity;
    			if(storeQuantity>-1){
    				hongXunMaterialStocks.get(0).setQuantity(storeQuantity);
    				if(hongXunMaterialStocks.get(0).getOutQuantity()!=null){
    					hongXunMaterialStocks.get(0).setOutQuantity(hongXunMaterialStocks.get(0).getOutQuantity() + quantity);
    				}else{
    					hongXunMaterialStocks.get(0).setOutQuantity(quantity);
    				}
    				if(hongXunMaterialStocks.get(0).getItemQuantity()==null){
    					//hongXunMaterialStocks.get(0).setItemQuantity(0-quantity);
    					Map<String, Object> map = new HashMap<String, Object>();
    					map.put("error", "物料号:"+ materialNum +"->原料仓库项目数为空");
    					list.add(map);
    					System.out.println("报错：扫描出库，项目数为空");
    					return list;
    				}else{	  		
    					hongXunMaterialStocks.get(0).setItemQuantity(hongXunMaterialStocks.get(0).getItemQuantity()-quantity);
    				}
    				hongXunMaterialStock = hongXunMaterialStocks.get(0); 
    				//System.out.println("hongXunMaterialStock: " + hongXunMaterialStock.getMaterialNum());
    				hongXunMaterialItemOutStock = new HongXunMaterialNoLimitOutStock();
	    	    	hongXunMaterialItemOutStock.setDate(new Date());    		
	    			hongXunMaterialItemOutStock.setQuantity(quantity);
	    				    			
    			}else{
    				Map<String, Object> map = new HashMap<String, Object>();
					map.put("error", "物料号:"+ materialNum +"->库存不足,库存数量：" + hongXunMaterialStocks.get(0).getQuantity());
					list.add(map);
    				System.out.println("库存不足");
    				return list;
    			}
    		}
    	}else if(hongXunMaterialStocks.size()==0){
    		Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", "物料号:"+ materialNum +"->无库存记录");
			list.add(map);
			return list;
    	}else{
    		//System.out.println("HongXunMaterialStock 有重复料号出错");
    		Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", "物料号:"+ materialNum +"->有重复料号，请联系工程师");
			list.add(map);
			return list;
    	}
    	if ((hongXunMaterialStock == null) || (hongXunMaterialItemOutStock == null)){
    		Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", "物料号:"+ materialNum +"->出库单中无记录");
			list.add(map);
			return list;
    	}
    	
    	if(hongXunMaterialStock != null){
    		iMaterialStockDao.update(hongXunMaterialStock);	
    		if(hongXunMaterialItemOutStock != null){
    			hongXunMaterialItemOutStock.setMaterialStockID(hongXunMaterialStock.getIdc());
    			iMaterialNoLimitOutStockDao.save(hongXunMaterialItemOutStock);	
    	    	Map<String, Object> map = new HashMap<String, Object>();
    			mapHongXunMaterialItemOutStock(map,hongXunMaterialItemOutStock);
    			list.add(map);
    	    }  	
    	}

		return list;
	}

	@Override
	public Collection<? extends Map<String, Object>> saveRow(HongXunMaterialNoLimitOutStock item) {
		// TODO Auto-generated method stub
		//stockDao.save(item); 
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
/*		Map<String, Object> map = new HashMap<String, Object>();
		mapHongXunMaterialItemInStock(map, item);
		list.add(map);*/
		return list;
	}

	@Override
	public Collection<? extends Map<String, Object>> updateRow(HongXunMaterialNoLimitOutStock item) {
		// TODO Auto-generated method stub
		HongXunMaterialStock hongXunMaterialStock = iMaterialStockDao.hongXunMaterialFindById(item.getMaterialStockID());
		HongXunMaterialNoLimitOutStock hongXunMaterialNoLimitOutStock = iMaterialNoLimitOutStockDao.materialNoLimitOutStockFindById(item.getIdc());	
		long diff = new Date().getTime() - hongXunMaterialNoLimitOutStock.getDate().getTime();
		// 计算差多少天
		long nd = 1000 * 24 * 60 * 60;
		long nh = 1000 * 60 * 60;
		long nm = 1000 * 60;
		//long day = diff / nd;
		// 计算差多少小时
		//long hour = diff % nd / nh;
		// 计算差多少分钟
		long min = diff % nd % nh / nm;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		//System.out.println("min:" + min);
		if(min<30){			
			hongXunMaterialStock.setQuantity(hongXunMaterialStock.getQuantity() - (item.getQuantity() - hongXunMaterialNoLimitOutStock.getQuantity()));
			hongXunMaterialStock.setOutQuantity(hongXunMaterialStock.getOutQuantity() + (item.getQuantity() - hongXunMaterialNoLimitOutStock.getQuantity()));
			hongXunMaterialNoLimitOutStock.setQuantity(item.getQuantity());		
			iMaterialStockDao.update(hongXunMaterialStock);
			iMaterialNoLimitOutStockDao.update(hongXunMaterialNoLimitOutStock);
			mapHongXunMaterialItemOutStock(map, item);
			list.add(map);
			return list;
		}else{
			map.put("error","出库时间" + min +"分已超过半小时，禁止修改");
			list.add(map);
			return list;
		}
	}


	
/*	private boolean compareTwoTime(Date time1, Date time2, long threshold) {
		boolean flag = false;
		try {
			long millisecond = time1.getTime() - time2.getTime();
			System.out.println("millisecond: " + millisecond);
			if (millisecond <= threshold) {
				flag = true;
			} else{
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}*/


	
}
