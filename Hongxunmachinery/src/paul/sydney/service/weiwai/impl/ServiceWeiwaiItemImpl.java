package paul.sydney.service.weiwai.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import paul.sydney.dao.IWeiwaiDao;
import paul.sydney.dao.StockDao;
import paul.sydney.model.HongXunBomTree;
import paul.sydney.model.HongXunPurchaseNum;
import paul.sydney.model.HongXunWeiwaiItem;
import paul.sydney.model.HongXunWeiwaiNum;
import paul.sydney.service.weiwai.ServiceWeiwaiItemInf;

@Transactional
@Service("serviceNumFive")
public class ServiceWeiwaiItemImpl implements ServiceWeiwaiItemInf{
	
	@Autowired
	StockDao stockDao;
	public void setStockDao(StockDao stockDao) {
		this.stockDao = stockDao;
	}
	@Autowired
	IWeiwaiDao iWeiwaiDao;
	public void setStockDao(IWeiwaiDao iWeiwaiDao) {
		this.iWeiwaiDao = iWeiwaiDao;
	}
	
	@Override
	public List<Map<String, Object>> loadData() {
		// TODO Auto-generated method stub
		//System.out.println("serviceBranchoneload");
		HongXunWeiwaiItem hongXunDataFive = new HongXunWeiwaiItem();
		List<HongXunWeiwaiItem> hongXunDataFives = iWeiwaiDao.quary(hongXunDataFive);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		for(HongXunWeiwaiItem item : hongXunDataFives){
			Map<String,Object> map = new HashMap<String,Object>();
			//System.out.println(itemSun.getIdc());
			mapHongXunDataFive(map, item);
			list.add(map);
		}
		
		return list;		
	}
	

	@Override
	public List<Map<String, Object>> saveMoney(int id, BigDecimal value) {
		// TODO Auto-generated method stub
		//HongXunDataOne hongXunDataOne = iWeiwaiDao.dataOneBranchOneFindById(id).getHongXunDataOne();
		HongXunWeiwaiNum hongXunDataFour = iWeiwaiDao.weiwaiNumFindById(id);
		hongXunDataFour.setWeiwaiMoney(value);
		iWeiwaiDao.update(hongXunDataFour);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("weiwaiMoney", hongXunDataFour.getWeiwaiMoney().floatValue());
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.add(map);
		return list;
	}
	
	@Override
	public List<Map<String, Object>> saveRow(HongXunWeiwaiItem hongXunDataFive) {
		// TODO Auto-generated method stub
		//System.out.println("serviceoneloadSaveRow");	
		iWeiwaiDao.save(hongXunDataFive);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("idc", hongXunDataFive.getIdc());
		map.put("weiwaiMoney", hongXunDataFive.getWeiwaiMoney());	
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.add(map);
		return list;
	}
	
	@Override
	public List<Map<String, Object>> deleteRow(int ID) {
		// TODO Auto-generated method stub
		HongXunWeiwaiItem hongXunDataFive = iWeiwaiDao.dataFiveFindById(ID);
		iWeiwaiDao.deletRow(hongXunDataFive);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		return list;
	}
	
	@Override
	public List<Map<String, Object>> updateRow(HongXunWeiwaiItem hongXunDataFive) {
		//HongXunDataOne hongXunDataOne = iWeiwaiDao.dataOneFindById(hongXunDataFive.getMainID());
		//hongXunDataFive.setHongXunDataOne(hongXunDataOne);
		iWeiwaiDao.update(hongXunDataFive);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("weiwaiMoney", hongXunDataFive.getWeiwaiMoney());
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.add(map);
		return list;
	}

	@Override
	public List<Map<String, Object>> getWeiwaiChildrens(int weiwaiNumID) {
		// TODO Auto-generated method stub
    	@SuppressWarnings("unchecked")
		List<HongXunWeiwaiItem> hongXunDataFives = (List<HongXunWeiwaiItem>) stockDao.quarywithpara("HongXunWeiwaiItem", "weiwaiNumID", weiwaiNumID); 
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    	for(HongXunWeiwaiItem item : hongXunDataFives){
			Map<String,Object> map = new HashMap<String,Object>();
			//System.out.println(item.getID());

			mapHongXunDataFive(map, item);
			list.add(map);
    	}
		return list;
	}
	
/*
	@Override
	public List<HongXunWeiwaiItem> quary(HongXunWeiwaiItem hongXunDataFive) {
		// TODO Auto-generated method stub
		List<HongXunWeiwaiItem> list = stockDao.quary(hongXunDataFive);
		return list;
	}*/

/*

	@Override
	public HongXunDataOne quarywithpara(String para) {
		// TODO Auto-generated method stub
		return stockDao.quarywithpara(para);
	}


	@Override
	public List<Map<String, Object>> getStockinChildrens(int uncleID) {
		// TODO Auto-generated method stub
    	@SuppressWarnings("unchecked")
		List<HongXunWeiwaiItem> hongXunDataFives = (List<HongXunWeiwaiItem>) stockDao.quarywithpara("HongXunWeiwaiItem", "uncleID", uncleID); 
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
	
    	for(HongXunWeiwaiItem item : hongXunDataFives){
			//item.getParentID();
			System.out.println("getStockinChildrens");
			@SuppressWarnings("unchecked")
			List<HongXunDataFiveFather> hongXunDataFiveSuns = (List<HongXunDataFiveFather>) stockDao.quarywithpara("HongXunDataFiveFather", "grandfID", item.getIdc()); 
			System.out.println("size : " + hongXunDataFiveSuns.size());
			Map<String,Object> map = new HashMap<String,Object>();
			//System.out.println(item.getID());
			mapHongXunDataFive(map, item);
			list.add(map);
    	}
    	
		return list;
	}

	@Override
	public List<Map<String, Object>> stockinLoadData() {
		// TODO Auto-generated method stub
		
		@SuppressWarnings("unchecked")
		List<HongXunWeiwaiItem> hongXunDataFives = (List<HongXunWeiwaiItem>) stockDao.quary("from HongXunWeiwaiItem");
		//System.out.println(hongXunDataFives.size());
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		for(HongXunWeiwaiItem item : hongXunDataFives){
			Map<String,Object> map = new HashMap<String,Object>();
			mapHongXunDataFive(map, item);
			list.add(map);
		}
		return list;
	}*/

	
	private void mapHongXunDataFive(Map<String, Object> map, HongXunWeiwaiItem item) {
		// TODO Auto-generated method stub	
		//System.out.println("test: " + item.getHongXunDataOne().getProductionOrderNum());
		map.put("idc", item.getIdc());
		map.put("materialNum", item.getMaterialNum());
		map.put("materialName", item.getMaterialName());
		map.put("specification", item.getSpecification());
		//map.put("materialModel", item.getMaterialModel());

		if(item.getPlanQuantity()!=null){
			map.put("planQuantity", item.getPlanQuantity());
		}
		
		if(item.getRealQuantity()!=null){
			map.put("realQuantity", item.getRealQuantity());
		}
		if(item.getRealDeliveryDate() != null){
			map.put("realDeliveryDate", new SimpleDateFormat("yyyy-MM-dd").format(item.getRealDeliveryDate()));
		}
		map.put("unit", item.getUnit());
		if(item.getPrice()!=null){
	  		map.put("price", item.getPrice().doubleValue());
	  	}
		if(item.getWeiwaiMoney()!=null){
	  		map.put("weiwaiMoney", item.getWeiwaiMoney().doubleValue());
	  	}
		if(item.getSupplier()!=null){
	  		map.put("supplier", item.getSupplier());
	  	}  	
		map.put("remark", item.getRemark());
		/*if(item.getHongXunDataOne()!=null){
			map.put("mainID", item.getHongXunDataOne().getIdc());
		}*/
		map.put("weiwaiNumID", item.getWeiwaiNumID());
	}

	@Override
	public List<Map<String, Object>> autotimp(String str) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		//List<String> listName = new ArrayList<String>();
		@SuppressWarnings("unchecked")
		List<HongXunBomTree> hongXunBomTrees = (List<HongXunBomTree>) stockDao.quaryFuzzyWithpara("HongXunBomTree", "BomMaterialNum", str); 	
		for(HongXunBomTree item: hongXunBomTrees){	
			Map<String,Object> map = new HashMap<String,Object>();
			//map.put("materialName", item.getMaterialName());	
			map.put("name", item.getBomMaterialNum());
			map.put("id", item.getIdc());  
			list.add(map);
		}
		return list;
	}

	
	@Override
	public List<Map<String, Object>> searchNum(String materialNum) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<HongXunBomTree> hongXunBomTrees = (List<HongXunBomTree>) stockDao.quarywithpara("HongXunBomTree", "BomMaterialNum", materialNum);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (hongXunBomTrees != null) {
			for (HongXunBomTree item : hongXunBomTrees) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("materialNum", item.getBomMaterialNum());
				//map.put("materialsupplier", item.getMaterialsupplier());  
			
				map.put("attribute", item.getAttribute());
			
				list.add(map);
			}
		}
		return list;
	}

}
