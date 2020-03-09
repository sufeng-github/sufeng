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
import paul.sydney.model.HongXunWeldNoLimitItemOutStock;
import paul.sydney.service.weld.ServiceWeldNoLimitItemOutStockInf;
import paul.sydney.model.HongXunMaterialNoLimitOutStock;
import paul.sydney.model.HongXunMaterialStock;
import paul.sydney.model.HongXunProductionStock;
import paul.sydney.model.HongXunProductionWeldStock;
import paul.sydney.model.HongXunWeldNoLimitItemInStock;

@Transactional
@Service("serviceWeldNoLimitItemOutStock")
public class ServiceWeldNoLimitItemOutStockImpl implements ServiceWeldNoLimitItemOutStockInf{
	
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
		List<HongXunWeldNoLimitItemOutStock> hongXunWeldNoLimitItemOutStocks = iWeldDao.quary(new HongXunWeldNoLimitItemOutStock());
		for(HongXunWeldNoLimitItemOutStock hongXunWeldNoLimitItemOutStock : hongXunWeldNoLimitItemOutStocks){
        	Map<String,Object> map = new HashMap<String,Object>();
    		mapHongXunWeldNoLimitItemOutStock(map, hongXunWeldNoLimitItemOutStock);
    		list.add(map);
		}		    	
		return list;
	}


	
	private void mapHongXunWeldNoLimitItemOutStock(Map<String, Object> map, HongXunWeldNoLimitItemOutStock item) {
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
				map.put("name", item.getMaterialNum()+ "->" + item.getSpecification());
				map.put("id", item.getIdc());  
				list.add(map);
			}
		}
		return list;
	}
	
	private void savaProductionNoLimitItemOutStock(String materialNum, String specification, Integer quantity, List<Map<String, Object>> list){
		HongXunWeldNoLimitItemOutStock hongXunWeldNoLimitItemOutStock = new HongXunWeldNoLimitItemOutStock();
		hongXunWeldNoLimitItemOutStock.setDate(new Date());
		hongXunWeldNoLimitItemOutStock.setQuantity(quantity);
		hongXunWeldNoLimitItemOutStock.setMaterialNum(materialNum);
		hongXunWeldNoLimitItemOutStock.setSpecification(specification);
		iWeldDao.save(hongXunWeldNoLimitItemOutStock);
		Map<String, Object> map = new HashMap<String, Object>();
		mapHongXunWeldNoLimitItemOutStock(map,hongXunWeldNoLimitItemOutStock);
		list.add(map);
	}
	
	private void outstoreWeldQuantityDec(int quantity, String materialNum, List<Map<String, Object>> list){
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
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("error", "物料号:"+ materialNum +"->装配仓库无记录");
				list.add(map);
				return;
				
	    	}else if(hongXunProductionWeldStocks.size()==1){
	    		if(hongXunProductionWeldStocks.get(0).getQuantity() == null){
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("error", "物料号:"+ materialNum +"->数量为空");
					list.add(map);
					return;
	    		}else{
	    			int result = hongXunProductionWeldStocks.get(0).getQuantity() - quantity;
	    			if(result<0){
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("error", "物料号:"+ materialNum +"->出库数量大于库存数量：" + hongXunProductionWeldStocks.get(0).getQuantity());
						list.add(map);
						return;
	    			}else{
	    				hongXunProductionWeldStocks.get(0).setQuantity(result);
	    				if(hongXunProductionWeldStocks.get(0).getOutQuantity() != null){
	    					hongXunProductionWeldStocks.get(0).setOutQuantity(hongXunProductionWeldStocks.get(0).getOutQuantity() + quantity);
	    				}else{
	    					hongXunProductionWeldStocks.get(0).setOutQuantity(quantity);
	    				}
	    				iWeldDao.update(hongXunProductionWeldStocks.get(0));
	    	    		savaProductionNoLimitItemOutStock(materialNum, hongXunProductionWeldStocks.get(0).getSpecification(), quantity, list);
	    			}
	    		}	    		
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
	public List<Map<String, Object>> outstoreProduction(String materialNum, int quantity) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		outstoreWeldQuantityDec(quantity, materialNum,  list);
    	return list;
		
	}

	@Override
	public List<Map<String, Object>> getEntity() {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String,Object>();
		HongXunWeldNoLimitItemOutStock e = new HongXunWeldNoLimitItemOutStock();
		Class<? extends HongXunWeldNoLimitItemOutStock> cls = e.getClass();  
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
	public Collection<? extends Map<String, Object>> saveRow(HongXunWeldNoLimitItemOutStock item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<? extends Map<String, Object>> updateRow(HongXunWeldNoLimitItemOutStock item) {
		// TODO Auto-generated method stub
		//HongXunMaterialStock hongXunMaterialStock = iWeldDao.hongXunMaterialFindById(item.getMaterialStockID());
		HongXunWeldNoLimitItemOutStock hongXunWeldNoLimitItemOutStock = iWeldDao.weldNoLimitItemOutStockFindById(item.getIdc());	
		long diff = new Date().getTime() - hongXunWeldNoLimitItemOutStock.getDate().getTime();
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
				hongXunProductionWeldStocks.get(0).setQuantity(hongXunProductionWeldStocks.get(0).getQuantity() - (item.getQuantity() - hongXunWeldNoLimitItemOutStock.getQuantity()));
				hongXunProductionWeldStocks.get(0).setOutQuantity(hongXunProductionWeldStocks.get(0).getOutQuantity() + (item.getQuantity() - hongXunWeldNoLimitItemOutStock.getQuantity()));
				hongXunWeldNoLimitItemOutStock.setQuantity(item.getQuantity());
				iWeldDao.update(hongXunProductionWeldStocks.get(0));
				iWeldDao.update(hongXunWeldNoLimitItemOutStock);
				mapHongXunWeldNoLimitItemOutStock(map, item);
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
