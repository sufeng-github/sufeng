package paul.sydney.service.production.impl;

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
import org.springframework.transaction.annotation.Transactional;
import paul.sydney.dao.IProductionDao;
import paul.sydney.dao.IProductionNoLimitInStockDao;
import paul.sydney.dao.StockDao;
import paul.sydney.model.HongXunProductionNoLimitItemInStock;
import paul.sydney.model.HongXunProductionStock;
import paul.sydney.service.production.ServiceProductionNoLimitItemInStockInf;
import paul.sydney.model.HongXunBomTree;

@Transactional
@Service("serviceProductionNoLimitItemInStock")
public class ServiceProductionNoLimitItemInStockImpl implements ServiceProductionNoLimitItemInStockInf{
	
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
	IProductionNoLimitInStockDao iProductionNoLimitInStockDao;
	public void setStockDao(IProductionNoLimitInStockDao iProductionNoLimitInStockDao) {
		this.iProductionNoLimitInStockDao = iProductionNoLimitInStockDao;
	}
	@Override
	public List<Map<String, Object>> loadData() {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		//System.out.println("list.size:" + list.size());
		List<HongXunProductionNoLimitItemInStock> hongXunProductionNoLimitItemInStocks = iProductionNoLimitInStockDao.quary(new HongXunProductionNoLimitItemInStock());
		for(HongXunProductionNoLimitItemInStock hongXunProductionNoLimitItemInStock : hongXunProductionNoLimitItemInStocks){
        	Map<String,Object> map = new HashMap<String,Object>();
    		mapHongXunProductionNoLimitItemInStock(map, hongXunProductionNoLimitItemInStock);
    		list.add(map);
		}		    	
		return list;
	}


	
	private void mapHongXunProductionNoLimitItemInStock(Map<String, Object> map, HongXunProductionNoLimitItemInStock item) {
		// TODO Auto-generated method stub	

		map.put("materialNum", item.getMaterialNum());
		map.put("specification", item.getSpecification());
		if(item.getDate() != null){
			map.put("date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(item.getDate()));
		}	
		//map.put("instoreQuantity", item.getInstoreQuantity());
		map.put("quantity", item.getQuantity());
		map.put("lotNum", item.getLotNum());
		map.put("remark", item.getRemark());
		map.put("idc", item.getIdc());
	}
	
	
	@Override
	public List<Map<String, Object>> autotimp(String str) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
	
		List<HongXunProductionStock> hongXunProductionStockList = iProductionDao.quary(new HongXunProductionStock());
		for(HongXunProductionStock item: hongXunProductionStockList){	
			if(item.getMaterialNum().indexOf(str)>-1){
				Map<String,Object> map = new HashMap<String,Object>();
				//map.put("materialName", item.getMaterialName());	
				map.put("name", item.getMaterialNum() + "->" + item.getSpecification());
				map.put("id", item.getIdc());  
				list.add(map);
			}
		}
		return list;
	}
	
	private void savaProductionNoLimitItemInStock(String materialNum, String specification, Integer quantity, List<Map<String, Object>> list){
		HongXunProductionNoLimitItemInStock hongXunProductionNoLimitItemInStock = new HongXunProductionNoLimitItemInStock();
		hongXunProductionNoLimitItemInStock.setDate(new Date());
		hongXunProductionNoLimitItemInStock.setQuantity(quantity);
		hongXunProductionNoLimitItemInStock.setMaterialNum(materialNum);
		hongXunProductionNoLimitItemInStock.setSpecification(specification);
		iProductionNoLimitInStockDao.save(hongXunProductionNoLimitItemInStock);
		Map<String, Object> map = new HashMap<String, Object>();
		mapHongXunProductionNoLimitItemInStock(map,hongXunProductionNoLimitItemInStock);
		list.add(map);
	}
	
	private void instoreProductionQuantityAdd(int quantity, String materialNum, List<Map<String, Object>> list){
		String specification = null;
		if(materialNum.indexOf("->") > 0){
			specification = materialNum.split("->")[1];
			materialNum = materialNum.split("->")[0];
		}
		
		HongXunProductionStock hongXunProductionStock = new HongXunProductionStock();
		hongXunProductionStock.setMaterialNum(materialNum);
		if(specification !=null){
			hongXunProductionStock.setSpecification(specification);
		}
		List<HongXunProductionStock> hongXunProductionStocks = iProductionDao.quary(hongXunProductionStock);
		if(hongXunProductionStocks.size()==0 || hongXunProductionStocks.size()==1){
			if(hongXunProductionStocks.size()==0){
				@SuppressWarnings("unchecked")
				List<HongXunBomTree> hongXunBomTrees = (List<HongXunBomTree>) stockDao.quarywithpara("HongXunBomTree", "bomMaterialNum", materialNum); 
				HongXunBomTree hongXunBomTree = null;
				for(HongXunBomTree item: hongXunBomTrees){
		    		if(item.getSn()==1){
		    			hongXunBomTree = item;
		    			break;
		    		}
		    	}
				if(hongXunBomTree != null){
		    		//HongXunProductionStock hongXunProductionStock = new HongXunProductionStock();
		    		hongXunProductionStock.setMaterialNum(hongXunBomTree.getBomMaterialNum());
		    		hongXunProductionStock.setQuantity(quantity);		    	
		    		hongXunProductionStock.setInQuantity(quantity);
		    		hongXunProductionStock.setSpecification(hongXunBomTree.getBomSpacification());
		    		//hongXunProductionStock.setUnit(item.getUnit());
		    		iProductionDao.save(hongXunProductionStock);	
		    		savaProductionNoLimitItemInStock(materialNum, hongXunProductionStock.getSpecification(), quantity, list);
				}else{
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("error", "物料号:"+ materialNum +"->Bom一级物料中无记录,请联系BOM维护员");
					list.add(map);
					return;
				}
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
	    		savaProductionNoLimitItemInStock(materialNum, hongXunProductionStocks.get(0).getSpecification(), quantity, list);
	    	}  		
    	}else{
    		System.out.println("HongXunMaterialStock 有重复料号出错");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", "物料号:"+ materialNum +"->有重复料号,请联系工程师");
			list.add(map);
			//break;
    	}
	}
	
	@Override
	public List<Map<String, Object>> instoreProduction(String materialNum, int quantity) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		instoreProductionQuantityAdd(quantity, materialNum,  list);
    	return list;
		
	}

	@Override
	public List<Map<String, Object>> getEntity() {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String,Object>();
		HongXunProductionNoLimitItemInStock e = new HongXunProductionNoLimitItemInStock();
		Class<? extends HongXunProductionNoLimitItemInStock> cls = e.getClass();  
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

	@Override
	public Collection<? extends Map<String, Object>> saveRow(HongXunProductionNoLimitItemInStock item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<? extends Map<String, Object>> updateRow(HongXunProductionNoLimitItemInStock item) {
		// TODO Auto-generated method stub
		HongXunProductionNoLimitItemInStock hongXunProductionNoLimitItemInStock = iProductionNoLimitInStockDao.productionNoLimitItemInStockFindById(item.getIdc());	
		long diff = new Date().getTime() - hongXunProductionNoLimitItemInStock.getDate().getTime();
		// 计算差多少天
		long nd = 1000 * 24 * 60 * 60;
		long nh = 1000 * 60 * 60;
		long nm = 1000 * 60;
		//long day = diff / nd;
		// 计算差多少小时
		long hour = diff % nd / nh;
		// 计算差多少分钟
		//long min = diff % nd % nh / nm;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		//System.out.println("min:" + min);
		if(hour<30){			
			HongXunProductionStock hongXunProductionStock = new HongXunProductionStock();
			hongXunProductionStock.setMaterialNum(item.getMaterialNum());
			List<HongXunProductionStock> hongXunProductionStocks = iProductionDao.quary(hongXunProductionStock);
			if(hongXunProductionStocks==null || hongXunProductionStocks.size() == 0){
				map.put("error", "料号:" + item.getMaterialNum() +"焊接仓无记录");
				list.add(map);
			}else if(hongXunProductionStocks.size() == 1){
				hongXunProductionStocks.get(0).setQuantity(hongXunProductionStocks.get(0).getQuantity() + (item.getQuantity() - hongXunProductionNoLimitItemInStock.getQuantity()));
				hongXunProductionStocks.get(0).setInQuantity(hongXunProductionStocks.get(0).getInQuantity() + (item.getQuantity() - hongXunProductionNoLimitItemInStock.getQuantity()));
				hongXunProductionNoLimitItemInStock.setQuantity(item.getQuantity());
				iProductionDao.update(hongXunProductionStocks.get(0));
				iProductionNoLimitInStockDao.update(hongXunProductionNoLimitItemInStock);
				mapHongXunProductionNoLimitItemInStock(map, item);
				list.add(map);
			}else{
				map.put("error", "料号:" + item.getMaterialNum() +"焊接仓有多个记录");
				list.add(map);
			}

			return list;
		}else{
			map.put("error","出库时间" + hour +"分已超过30小时，禁止修改");
			list.add(map);
			return list;
		}
	}

}
