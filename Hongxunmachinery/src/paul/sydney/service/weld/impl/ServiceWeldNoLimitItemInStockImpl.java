package paul.sydney.service.weld.impl;

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

import paul.sydney.dao.IWeldDao;
import paul.sydney.dao.StockDao;
import paul.sydney.model.HongXunWeldNoLimitItemInStock;
import paul.sydney.model.HongXunWeldNoLimitItemOutStock;
import paul.sydney.service.weld.ServiceWeldNoLimitItemInStockInf;
import paul.sydney.model.HongXunProductionWeldStock;
import paul.sydney.model.HongXunBomTree;
import paul.sydney.model.HongXunMaterialNoLimitOutStock;
import paul.sydney.model.HongXunMaterialStock;

@Transactional
@Service("serviceWeldNoLimitItemInStock")
public class ServiceWeldNoLimitItemInStockImpl implements ServiceWeldNoLimitItemInStockInf{
	
	@Autowired
	StockDao stockDao;
	public void setStockDao(StockDao stockDao) {
		this.stockDao = stockDao;
	}
	@Autowired
	IWeldDao iWeldDao;
	public void setStockDao(IWeldDao iWeldDao) {
		this.iWeldDao = iWeldDao;
	}
	@Override
	public List<Map<String, Object>> loadData() {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		//System.out.println("list.size:" + list.size());
		List<HongXunWeldNoLimitItemInStock> hongXunWeldNoLimitItemInStocks = iWeldDao.quary(new HongXunWeldNoLimitItemInStock());
		for(HongXunWeldNoLimitItemInStock hongXunWeldNoLimitItemInStock : hongXunWeldNoLimitItemInStocks){
        	Map<String,Object> map = new HashMap<String,Object>();
    		mapHongXunWeldNoLimitItemInStock(map, hongXunWeldNoLimitItemInStock);
    		list.add(map);
		}		    	
		return list;
	}


	
	private void mapHongXunWeldNoLimitItemInStock(Map<String, Object> map, HongXunWeldNoLimitItemInStock item) {
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
	
		List<HongXunProductionWeldStock> hongXunProductionWeldStockList = iWeldDao.quary(new HongXunProductionWeldStock());
		for(HongXunProductionWeldStock item: hongXunProductionWeldStockList){	
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
		HongXunWeldNoLimitItemInStock hongXunWeldNoLimitItemInStock = new HongXunWeldNoLimitItemInStock();
		hongXunWeldNoLimitItemInStock.setDate(new Date());
		hongXunWeldNoLimitItemInStock.setQuantity(quantity);
		hongXunWeldNoLimitItemInStock.setMaterialNum(materialNum);
		hongXunWeldNoLimitItemInStock.setSpecification(specification);
		iWeldDao.save(hongXunWeldNoLimitItemInStock);
		Map<String, Object> map = new HashMap<String, Object>();
		mapHongXunWeldNoLimitItemInStock(map,hongXunWeldNoLimitItemInStock);
		list.add(map);
	}
	
	
	private void instoreWeldQuantityAdd(int quantity, String materialNum, List<Map<String, Object>> list){
		String specification = null;
		if(materialNum.indexOf("->") > 0){
			specification = materialNum.split("->")[1];
			materialNum = materialNum.split("->")[0];
		}
		
		HongXunProductionWeldStock hongXunProductionWeldStock = new HongXunProductionWeldStock();
		hongXunProductionWeldStock.setMaterialNum(materialNum);
		if(specification !=null){
			hongXunProductionWeldStock.setSpecification(specification);
		}
		List<HongXunProductionWeldStock> hongXunProductionWeldStocks = iWeldDao.quary(hongXunProductionWeldStock);
		if(hongXunProductionWeldStocks.size()==0 || hongXunProductionWeldStocks.size()==1){
			if(hongXunProductionWeldStocks.size()==0){
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
		    		//HongXunProductionWeldStock hongXunProductionWeldStock = new HongXunProductionWeldStock();
		    		hongXunProductionWeldStock.setMaterialNum(hongXunBomTree.getBomMaterialNum());
		    		hongXunProductionWeldStock.setQuantity(quantity);
		    		hongXunProductionWeldStock.setInQuantity(quantity);
		    		hongXunProductionWeldStock.setSpecification(hongXunBomTree.getBomSpacification());
		    		//hongXunProductionWeldStock.setUnit(item.getUnit());
		    		iWeldDao.save(hongXunProductionWeldStock);	
		    		savaProductionNoLimitItemInStock(materialNum, hongXunProductionWeldStock.getSpecification(), quantity, list);
				}else{
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("error", "物料号:"+ materialNum +"->Bom一级物料中无记录,请联系BOM维护员");
					list.add(map);
					return;
				}
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
	    		savaProductionNoLimitItemInStock(materialNum, hongXunProductionWeldStocks.get(0).getSpecification(), quantity, list);
	    	}  		
    	}else{
    		//System.out.println("HongXunMaterialStock 有重复料号出错");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", "物料号:"+ materialNum +"->有重复料号规格,请联系工程师");
			list.add(map);
			//break;
    	}
	}	
	
	
	@Override
	public List<Map<String, Object>> instoreProduction(String materialNum, int quantity) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		instoreWeldQuantityAdd(quantity, materialNum,  list);
    	return list;
		
	}

	@Override
	public List<Map<String, Object>> getEntity() {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String,Object>();
		HongXunWeldNoLimitItemInStock e = new HongXunWeldNoLimitItemInStock();
		Class<? extends HongXunWeldNoLimitItemInStock> cls = e.getClass();  
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
	public Collection<? extends Map<String, Object>> saveRow(HongXunWeldNoLimitItemInStock item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<? extends Map<String, Object>> updateRow(HongXunWeldNoLimitItemInStock item) {
		// TODO Auto-generated method stub
		//HongXunMaterialStock hongXunMaterialStock = iWeldDao.hongXunMaterialFindById(item.getMaterialStockID());
		HongXunWeldNoLimitItemInStock hongXunWeldNoLimitItemInStock = iWeldDao.weldNoLimitItemInStockFindById(item.getIdc());	
		long diff = new Date().getTime() - hongXunWeldNoLimitItemInStock.getDate().getTime();
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
			HongXunProductionWeldStock hongXunProductionWeldStock = new HongXunProductionWeldStock();
			hongXunProductionWeldStock.setMaterialNum(item.getMaterialNum());
			List<HongXunProductionWeldStock> hongXunProductionWeldStocks = iWeldDao.quary(hongXunProductionWeldStock);
			if(hongXunProductionWeldStocks==null || hongXunProductionWeldStocks.size() == 0){
				map.put("error", "料号:" + item.getMaterialNum() +"焊接仓无记录");
				list.add(map);
			}else if(hongXunProductionWeldStocks.size() == 1){
				hongXunProductionWeldStocks.get(0).setQuantity(hongXunProductionWeldStocks.get(0).getQuantity() + (item.getQuantity() - hongXunWeldNoLimitItemInStock.getQuantity()));
				hongXunProductionWeldStocks.get(0).setInQuantity(hongXunProductionWeldStocks.get(0).getInQuantity() + (item.getQuantity() - hongXunWeldNoLimitItemInStock.getQuantity()));			
				hongXunWeldNoLimitItemInStock.setQuantity(item.getQuantity());
				iWeldDao.update(hongXunProductionWeldStocks.get(0));
				iWeldDao.update(hongXunWeldNoLimitItemInStock);
				mapHongXunWeldNoLimitItemInStock(map, item);
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
