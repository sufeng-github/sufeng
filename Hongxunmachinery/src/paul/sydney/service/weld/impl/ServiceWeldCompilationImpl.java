package paul.sydney.service.weld.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import paul.sydney.dao.IWeldDao;
import paul.sydney.dao.StockDao;
import paul.sydney.model.HongXunWeldCompilation;
import paul.sydney.service.weld.ServiceWeldCompilationInf;
import paul.sydney.model.HongXunProductionWeldStock;
@Transactional
@Service("serviceWeldCompliation")
public class ServiceWeldCompilationImpl implements ServiceWeldCompilationInf{
	
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
		List<HongXunWeldCompilation>  hongXunWeldCompilations = iWeldDao.quary(new HongXunWeldCompilation());
		for(HongXunWeldCompilation item : hongXunWeldCompilations){		
			Map<String,Object> map = new HashMap<String,Object>();
			mapHongXunMaterialCompilation(map,item);
			list.add(map);
		}
		
		return list;
	}
	
	@Override
	public List<Map<String, Object>> getEntity() {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String,Object>();
		HongXunWeldCompilation e = new HongXunWeldCompilation();
		Class<? extends HongXunWeldCompilation> cls = e.getClass();  
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
	
	private void mapHongXunMaterialCompilation(Map<String, Object> map, HongXunWeldCompilation item) {
		// TODO Auto-generated method stub	
		//System.out.println("test: " + item.getHongXunDataOne().getProductionOrderNum());
		map.put("idc", item.getIdc());
		if(item.getStockId() != null){
			HongXunProductionWeldStock hongXunProductionWeldStock = iWeldDao.productionWeldFindById(item.getStockId());
			if(hongXunProductionWeldStock != null){
				map.put("materialNum", hongXunProductionWeldStock.getMaterialNum());
				map.put("specification", hongXunProductionWeldStock.getSpecification());
			}
		}
		map.put("quantity1", item.getQuantity1());
		map.put("inQuantity1", item.getInQuantity1());
		map.put("outQuantity1", item.getOutQuantity1());
		map.put("checkQuantity1", item.getCheckQuantity1());
		map.put("diffQuantity1", item.getDiffQuantity1());
		map.put("remark1", item.getRemark1());

		map.put("quantity2", item.getQuantity2());
		map.put("inQuantity2", item.getInQuantity2());
		map.put("outQuantity2", item.getOutQuantity2());
		map.put("checkQuantity2", item.getCheckQuantity2());
		map.put("diffQuantity2", item.getDiffQuantity2());
		map.put("remark2", item.getRemark2());
		
		map.put("quantity3", item.getQuantity3());
		map.put("inQuantity3", item.getInQuantity3());
		map.put("outQuantity3", item.getOutQuantity3());
		map.put("checkQuantity3", item.getCheckQuantity3());
		map.put("diffQuantity3", item.getDiffQuantity3());
		map.put("remark3", item.getRemark3());
		
		map.put("quantity4", item.getQuantity4());
		map.put("inQuantity4", item.getInQuantity4());
		map.put("outQuantity4", item.getOutQuantity4());
		map.put("checkQuantity4", item.getCheckQuantity4());
		map.put("diffQuantity4", item.getDiffQuantity4());
		map.put("remark4", item.getRemark4());
		
		map.put("quantity5", item.getQuantity5());
		map.put("inQuantity5", item.getInQuantity5());
		map.put("outQuantity5", item.getOutQuantity5());
		map.put("checkQuantity5", item.getCheckQuantity5());
		map.put("diffQuantity5", item.getDiffQuantity5());
		map.put("remark5", item.getRemark5());
		
		map.put("quantity6", item.getQuantity6());
		map.put("inQuantity6", item.getInQuantity6());
		map.put("outQuantity6", item.getOutQuantity6());
		map.put("checkQuantity6", item.getCheckQuantity6());
		map.put("diffQuantity6", item.getDiffQuantity6());
		map.put("remark6", item.getRemark6());
		
		map.put("quantity7", item.getQuantity7());
		map.put("inQuantity7", item.getInQuantity7());
		map.put("outQuantity7", item.getOutQuantity7());
		map.put("checkQuantity7", item.getCheckQuantity7());
		map.put("diffQuantity7", item.getDiffQuantity7());
		map.put("remark7", item.getRemark7());
		
		map.put("quantity8", item.getQuantity8());
		map.put("inQuantity8", item.getInQuantity8());
		map.put("outQuantity8", item.getOutQuantity8());
		map.put("checkQuantity8", item.getCheckQuantity8());
		map.put("diffQuantity8", item.getDiffQuantity8());
		map.put("remark8", item.getRemark8());
		
		map.put("quantity9", item.getQuantity9());
		map.put("inQuantity9", item.getInQuantity9());
		map.put("outQuantity9", item.getOutQuantity9());
		map.put("checkQuantity9", item.getCheckQuantity9());
		map.put("diffQuantity9", item.getDiffQuantity9());
		map.put("remark9", item.getRemark9());
		
		map.put("quantity10", item.getQuantity10());
		map.put("inQuantity10", item.getInQuantity10());
		map.put("outQuantity10", item.getOutQuantity10());
		map.put("checkQuantity10", item.getCheckQuantity10());
		map.put("diffQuantity10", item.getDiffQuantity10());
		map.put("remark10", item.getRemark10());
		
		map.put("quantity11", item.getQuantity11());
		map.put("inQuantity11", item.getInQuantity11());
		map.put("outQuantity11", item.getOutQuantity11());
		map.put("checkQuantity11", item.getCheckQuantity11());
		map.put("diffQuantity11", item.getDiffQuantity11());
		map.put("remark11", item.getRemark11());
		
		map.put("quantity12", item.getQuantity12());
		map.put("inQuantity12", item.getInQuantity12());
		map.put("outQuantity12", item.getOutQuantity12());
		map.put("checkQuantity12", item.getCheckQuantity12());
		map.put("diffQuantity12", item.getDiffQuantity12());
		map.put("remark12", item.getRemark12());
	}

/*	@Override
	public List<Map<String, Object>> autotimp(String str) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		//List<String> listName = new ArrayList<String>();
		@SuppressWarnings("unchecked")
		List<HongXunWeldCompilation> hongXunWeldCompilations = (List<HongXunWeldCompilation>) iWeldDao.quaryFuzzyWithpara("HongXunWeldCompilation", "materialNum", str); 	
		for(HongXunWeldCompilation item: hongXunWeldCompilations){	
			Map<String,Object> map = new HashMap<String,Object>();
			//map.put("materialName", item.getMaterialName());	
			map.put("name", item.getMaterialNum());
			map.put("id", item.getIdc());  
			list.add(map);
		}
		return list;
	}*/


	@Override
	public List<Map<String, Object>> searchMaterialNum(String materialNum) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<HongXunWeldCompilation> hongXunWeldCompilations = (List<HongXunWeldCompilation>) stockDao.quaryFuzzyWithpara("HongXunWeldCompilation", "materialNum", materialNum);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (hongXunWeldCompilations != null) {
			for (HongXunWeldCompilation item : hongXunWeldCompilations) {
				Map<String, Object> map = new HashMap<String, Object>();
				mapHongXunMaterialCompilation(map,item);
				list.add(map);
			}
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> saveRow(HongXunWeldCompilation hongXunWeldCompilation) {
		// TODO Auto-generated method stub
		iWeldDao.save(hongXunWeldCompilation);
		Map<String,Object> map = new HashMap<String,Object>();
		mapHongXunMaterialCompilation(map, hongXunWeldCompilation);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.add(map);
		return list;
	}

	@Override
	public List<Map<String, Object>> updateRow(HongXunWeldCompilation hongXunWeldCompilation) {
		// TODO Auto-generated method stub
		iWeldDao.update(hongXunWeldCompilation);
		Map<String,Object> map = new HashMap<String,Object>();
		mapHongXunMaterialCompilation(map, hongXunWeldCompilation);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.add(map);
		return list;
	}

	@Override
	public List<Map<String, Object>> getMaterialAlarms(String alarm) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		@SuppressWarnings("unchecked")
		List<HongXunWeldCompilation> hongXunWeldCompilations = (List<HongXunWeldCompilation>) stockDao.quaryFuzzyWithpara("HongXunWeldCompilation", "alarm", alarm);
		for(HongXunWeldCompilation item : hongXunWeldCompilations){
			Map<String,Object> map = new HashMap<String,Object>();
			mapHongXunMaterialCompilation(map,item);
			list.add(map);
		}
		
		return list;
	}

	@Override
	public List<Map<String, Object>> scanMaterialCompilation(String materialNum, int quantity) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
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
						map.put("error", "物料号:"+ materialNum +"->焊接仓库无记录");
						list.add(map);
						return list;				
			    	}else if(hongXunProductionWeldStocks.size()==1){
			    		HongXunWeldCompilation hongXunWeldCompilation = new HongXunWeldCompilation();
			    		hongXunWeldCompilation.setStockId(hongXunProductionWeldStocks.get(0).getIdc());
			    		List<HongXunWeldCompilation> hongXunWeldCompilations = iWeldDao.quary(hongXunWeldCompilation);
			    		Calendar cal = Calendar.getInstance();
			    		cal.setTime(new Date());
			    		int month = cal.get(Calendar.MONTH) + 1;
			    		if(hongXunWeldCompilations.size()==0){
			    			fillmsg(month, hongXunWeldCompilation, materialNum, hongXunProductionWeldStock, quantity);	
			    		}else if(hongXunWeldCompilations.size()==1){
			    			hongXunWeldCompilation = hongXunWeldCompilations.get(0);
			    			if(month==1){ 
			    				int checkQuantity = hongXunWeldCompilation.getCheckQuantity1();
			    				int diffQuantity = checkQuantity - quantity;
			    				hongXunWeldCompilation.setDiffQuantity1(hongXunWeldCompilation.getDiffQuantity1()+diffQuantity);
			    				hongXunWeldCompilation.setCheckQuantity1(quantity);
			    			}else if(month==2){ 
			    				int checkQuantity = hongXunWeldCompilation.getCheckQuantity2();
			    				int diffQuantity = checkQuantity - quantity;
			    				hongXunWeldCompilation.setDiffQuantity2(hongXunWeldCompilation.getDiffQuantity2()+diffQuantity);
			    				hongXunWeldCompilation.setCheckQuantity2(quantity);
			    			}else if(month==3){ 
			    				int checkQuantity = hongXunWeldCompilation.getCheckQuantity3();
			    				int diffQuantity = checkQuantity - quantity;
			    				hongXunWeldCompilation.setDiffQuantity3(hongXunWeldCompilation.getDiffQuantity3()+diffQuantity);
			    				hongXunWeldCompilation.setCheckQuantity3(quantity);
			    			}else if(month==4){ 
			    				int checkQuantity = hongXunWeldCompilation.getCheckQuantity4();
			    				int diffQuantity = checkQuantity - quantity;
			    				hongXunWeldCompilation.setDiffQuantity4(hongXunWeldCompilation.getDiffQuantity4()+diffQuantity);
			    				hongXunWeldCompilation.setCheckQuantity4(quantity);
			    			}else if(month==5){ 
			    				int checkQuantity = hongXunWeldCompilation.getCheckQuantity5();
			    				int diffQuantity = checkQuantity - quantity;
			    				hongXunWeldCompilation.setDiffQuantity5(hongXunWeldCompilation.getDiffQuantity5()+diffQuantity);
			    				hongXunWeldCompilation.setCheckQuantity5(quantity);
			    			}else if(month==6){ 
			    				int checkQuantity = hongXunWeldCompilation.getCheckQuantity6();
			    				int diffQuantity = checkQuantity - quantity;
			    				hongXunWeldCompilation.setDiffQuantity6(hongXunWeldCompilation.getDiffQuantity6()+diffQuantity);
			    				hongXunWeldCompilation.setCheckQuantity6(quantity);
			    			}else if(month==7){ 
			    				int checkQuantity = hongXunWeldCompilation.getCheckQuantity7();
			    				int diffQuantity = checkQuantity - quantity;
			    				hongXunWeldCompilation.setDiffQuantity7(hongXunWeldCompilation.getDiffQuantity7()+diffQuantity);
			    				hongXunWeldCompilation.setCheckQuantity7(quantity);
			    			}else if(month==8){ 
			    				int checkQuantity = hongXunWeldCompilation.getCheckQuantity8();
			    				int diffQuantity = checkQuantity - quantity;
			    				hongXunWeldCompilation.setDiffQuantity8(hongXunWeldCompilation.getDiffQuantity8()+diffQuantity);
			    				hongXunWeldCompilation.setCheckQuantity8(quantity);
			    			}else if(month==9){ 
			    				int checkQuantity = hongXunWeldCompilation.getCheckQuantity9();
			    				int diffQuantity = checkQuantity - quantity;
			    				hongXunWeldCompilation.setDiffQuantity9(hongXunWeldCompilation.getDiffQuantity9()+diffQuantity);
			    				hongXunWeldCompilation.setCheckQuantity9(quantity);
			    			}else if(month==10){ 
			    				int checkQuantity = hongXunWeldCompilation.getCheckQuantity10();
			    				int diffQuantity = checkQuantity - quantity;
			    				hongXunWeldCompilation.setDiffQuantity10(hongXunWeldCompilation.getDiffQuantity10()+diffQuantity);
			    				hongXunWeldCompilation.setCheckQuantity10(quantity);
			    			}else if(month==11){ 
			    				int checkQuantity = hongXunWeldCompilation.getCheckQuantity11();
			    				int diffQuantity = checkQuantity - quantity;
			    				hongXunWeldCompilation.setDiffQuantity11(hongXunWeldCompilation.getDiffQuantity11()+diffQuantity);
			    				hongXunWeldCompilation.setCheckQuantity11(quantity);
			    			}else if(month==12){ 
			    				int checkQuantity = hongXunWeldCompilation.getCheckQuantity12();
			    				int diffQuantity = checkQuantity - quantity;
			    				hongXunWeldCompilation.setDiffQuantity12(hongXunWeldCompilation.getDiffQuantity12()+diffQuantity);
			    				hongXunWeldCompilation.setCheckQuantity12(quantity);
			    			}
			    		}else if(hongXunWeldCompilations.size()>1){
			    			Map<String, Object> map = new HashMap<String, Object>();
			    			map.put("error", "物料号:"+ materialNum +"->指向多个盘点数据,请联系工程师");
			    			list.add(map);
			    			return list;
			    		}
									   		
				    	if(hongXunWeldCompilation.getIdc() == 0){
				    		iWeldDao.save(hongXunWeldCompilation);	    			
				    	}else{
				    	    iWeldDao.update(hongXunWeldCompilation);	
				    		return list;   	    	
				    	}
			    	}  		
		    	}else{
		    		//System.out.println("HongXunMaterialStock 有重复料号出错");
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("error", "物料号:"+ materialNum +"->有重复料号,请联系工程师");
					list.add(map);
					return list;
		    	}
				return list;
	}
	

	@Override
	public List<Map<String, Object>> synchronization() {
		// TODO Auto-generated method stub
		List<HongXunWeldCompilation> hongXunWeldCompilations = iWeldDao.quary(new HongXunWeldCompilation());
		List<HongXunProductionWeldStock> list = new ArrayList<HongXunProductionWeldStock>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int month = cal.get(Calendar.MONTH) + 1;
		for(HongXunWeldCompilation item: hongXunWeldCompilations){
/*    		HongXunProductionWeldStock hongXunProductionWeldStock = new HongXunProductionWeldStock();
    		hongXunProductionWeldStock.setMaterialNum(item.getMaterialNum());
    		hongXunProductionWeldStock.setSpecification(item.getSpecification());
    		List<HongXunProductionWeldStock> hongXunProductionWeldStocks = iWeldDao.quary(hongXunProductionWeldStock);
    		*/
			HongXunProductionWeldStock hongXunProductionWeldStock = iWeldDao.productionWeldFindById(item.getStockId());
    		if(hongXunProductionWeldStock != null){
    			if(month==1){
    				hongXunProductionWeldStock.setQuantity(item.getCheckQuantity1());
    			}else if(month==2){
    				hongXunProductionWeldStock.setQuantity(item.getCheckQuantity2());
    			}else if(month==3){
    				hongXunProductionWeldStock.setQuantity(item.getCheckQuantity3());
    			}else if(month==4){
    				hongXunProductionWeldStock.setQuantity(item.getCheckQuantity4());
    			}else if(month==5){
    				hongXunProductionWeldStock.setQuantity(item.getCheckQuantity5());
    			}else if(month==6){
    				hongXunProductionWeldStock.setQuantity(item.getCheckQuantity6());
    			}else if(month==7){
    				hongXunProductionWeldStock.setQuantity(item.getCheckQuantity7());
    			}else if(month==8){
    				hongXunProductionWeldStock.setQuantity(item.getCheckQuantity8());
    			}else if(month==9){
    				hongXunProductionWeldStock.setQuantity(item.getCheckQuantity9());
    			}else if(month==10){
    				hongXunProductionWeldStock.setQuantity(item.getCheckQuantity10());
    			}else if(month==11){
    				hongXunProductionWeldStock.setQuantity(item.getCheckQuantity11());
    			}else if(month==12){
    				hongXunProductionWeldStock.setQuantity(item.getCheckQuantity12());
    			}
    			hongXunProductionWeldStock.setInQuantity(0);
    			hongXunProductionWeldStock.setOutQuantity(0);
    			list.add(hongXunProductionWeldStock);
    		}
		}
		for(HongXunProductionWeldStock item: list){
			iWeldDao.update(item);
		}
		
		return new ArrayList<Map<String,Object>>();
	}
	/*    	}else if(hongXunWeldCompilations.size()==0){
	hongXunWeldCompilation = new HongXunWeldCompilation(); 
	@SuppressWarnings("unchecked")
	List<HongXunMaterialStock> hongXunProductionStocks = (List<HongXunMaterialStock>) stockDao.quarywithpara("HongXunMaterialStock", "materialNum", materialNum); 
	if(hongXunProductionStocks.size()==1){
		fillmsg(month, hongXunWeldCompilation, materialNum, hongXunProductionStocks.get(0), quantity);		
	}else if(hongXunProductionStocks.size()==0){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error", "物料号:" + materialNum + "->原料仓无入库记录");
		list.add(map);
		return list;
	}else{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error", "物料号:" + materialNum + "->原料仓有重复料号，请联系工程师");
		list.add(map);
		return list;
	}*/
	private void fillmsg(int month, HongXunWeldCompilation hongXunWeldCompilation, String materialNum, HongXunProductionWeldStock hongXunProductionWeldStock, int quantity){
		if(hongXunProductionWeldStock.getInQuantity()==null){
			hongXunProductionWeldStock.setInQuantity(0);
		}
		if(hongXunProductionWeldStock.getOutQuantity()==null){
			hongXunProductionWeldStock.setOutQuantity(0);
		}
		if(hongXunProductionWeldStock.getQuantity() == null){
			hongXunProductionWeldStock.setQuantity(0);
		}
		int checkquantity = hongXunProductionWeldStock.getQuantity() + hongXunProductionWeldStock.getInQuantity() - hongXunProductionWeldStock.getOutQuantity() - hongXunProductionWeldStock.getInQuantity() - quantity;
		
		if(month==1){

			hongXunWeldCompilation.setInQuantity1(hongXunProductionWeldStock.getInQuantity());
			hongXunWeldCompilation.setOutQuantity1(hongXunProductionWeldStock.getOutQuantity());
			hongXunWeldCompilation.setQuantity1(hongXunProductionWeldStock.getQuantity());
			hongXunWeldCompilation.setCheckQuantity1(quantity);
			hongXunWeldCompilation.setDiffQuantity1(checkquantity);
		}else if(month==2){
			hongXunWeldCompilation.setInQuantity2(hongXunProductionWeldStock.getInQuantity());
			hongXunWeldCompilation.setOutQuantity2(hongXunProductionWeldStock.getOutQuantity());
			hongXunWeldCompilation.setQuantity2(hongXunProductionWeldStock.getQuantity());
			hongXunWeldCompilation.setCheckQuantity2(quantity);
			hongXunWeldCompilation.setDiffQuantity2(checkquantity);
		}else if(month==3){
			hongXunWeldCompilation.setInQuantity3(hongXunProductionWeldStock.getInQuantity());
			hongXunWeldCompilation.setOutQuantity3(hongXunProductionWeldStock.getOutQuantity());
			hongXunWeldCompilation.setQuantity3(hongXunProductionWeldStock.getQuantity());
			hongXunWeldCompilation.setCheckQuantity3(quantity);
			hongXunWeldCompilation.setDiffQuantity3(checkquantity);
		}else if(month==4){
			hongXunWeldCompilation.setInQuantity4(hongXunProductionWeldStock.getInQuantity());
			hongXunWeldCompilation.setOutQuantity4(hongXunProductionWeldStock.getOutQuantity());
			hongXunWeldCompilation.setQuantity4(hongXunProductionWeldStock.getQuantity());
			hongXunWeldCompilation.setCheckQuantity4(quantity);
			hongXunWeldCompilation.setDiffQuantity4(checkquantity);
		}else if(month==5){
			hongXunWeldCompilation.setInQuantity5(hongXunProductionWeldStock.getInQuantity());
			hongXunWeldCompilation.setOutQuantity5(hongXunProductionWeldStock.getOutQuantity());
			hongXunWeldCompilation.setQuantity5(hongXunProductionWeldStock.getQuantity());
			hongXunWeldCompilation.setCheckQuantity5(quantity);
			hongXunWeldCompilation.setDiffQuantity5(checkquantity);
		}else if(month==6){
			hongXunWeldCompilation.setInQuantity6(hongXunProductionWeldStock.getInQuantity());
			hongXunWeldCompilation.setOutQuantity6(hongXunProductionWeldStock.getOutQuantity());
			hongXunWeldCompilation.setQuantity6(hongXunProductionWeldStock.getQuantity());
			hongXunWeldCompilation.setCheckQuantity6(quantity);
			hongXunWeldCompilation.setDiffQuantity6(checkquantity);
		}else if(month==7){
			hongXunWeldCompilation.setInQuantity7(hongXunProductionWeldStock.getInQuantity());
			hongXunWeldCompilation.setOutQuantity7(hongXunProductionWeldStock.getOutQuantity());
			hongXunWeldCompilation.setQuantity7(hongXunProductionWeldStock.getQuantity());
			hongXunWeldCompilation.setCheckQuantity7(quantity);
			hongXunWeldCompilation.setDiffQuantity7(checkquantity);
		}else if(month==8){
			hongXunWeldCompilation.setInQuantity8(hongXunProductionWeldStock.getInQuantity());
			hongXunWeldCompilation.setOutQuantity8(hongXunProductionWeldStock.getOutQuantity());
			hongXunWeldCompilation.setQuantity8(hongXunProductionWeldStock.getQuantity());
			hongXunWeldCompilation.setCheckQuantity8(quantity);
			hongXunWeldCompilation.setDiffQuantity8(checkquantity);
		}else if(month==9){
			hongXunWeldCompilation.setInQuantity9(hongXunProductionWeldStock.getInQuantity());
			hongXunWeldCompilation.setOutQuantity9(hongXunProductionWeldStock.getOutQuantity());
			hongXunWeldCompilation.setQuantity9(hongXunProductionWeldStock.getQuantity());
			hongXunWeldCompilation.setCheckQuantity9(quantity);
			hongXunWeldCompilation.setDiffQuantity9(checkquantity);
		}else if(month==10){
			hongXunWeldCompilation.setInQuantity10(hongXunProductionWeldStock.getInQuantity());
			hongXunWeldCompilation.setOutQuantity10(hongXunProductionWeldStock.getOutQuantity());
			hongXunWeldCompilation.setQuantity10(hongXunProductionWeldStock.getQuantity());
			hongXunWeldCompilation.setCheckQuantity10(quantity);
			hongXunWeldCompilation.setDiffQuantity10(checkquantity);
		}else if(month==11){
			hongXunWeldCompilation.setInQuantity11(hongXunProductionWeldStock.getInQuantity());
			hongXunWeldCompilation.setOutQuantity11(hongXunProductionWeldStock.getOutQuantity());
			hongXunWeldCompilation.setQuantity11(hongXunProductionWeldStock.getQuantity());
			hongXunWeldCompilation.setCheckQuantity11(quantity);
			hongXunWeldCompilation.setDiffQuantity11(checkquantity);
		}else if(month==12){
			hongXunWeldCompilation.setInQuantity12(hongXunProductionWeldStock.getInQuantity());
			hongXunWeldCompilation.setOutQuantity12(hongXunProductionWeldStock.getOutQuantity());
			hongXunWeldCompilation.setQuantity12(hongXunProductionWeldStock.getQuantity());
			hongXunWeldCompilation.setCheckQuantity12(quantity);
			hongXunWeldCompilation.setDiffQuantity12(checkquantity);
		}else{
			
		}
	/*	//一对多时用SQL查找会出问题 select instoreDate 
		String sql = "from HongXunWeldItemInStock where date_format(date,'%Y-%m')=date_format(now(),'%Y-%m')";
		@SuppressWarnings("unchecked")
		List<HongXunWeldItemInStock> hongXunWeldItemInStocks = (List<HongXunWeldItemInStock>) stockDao.quarywithpara(sql);
		for(HongXunWeldItemInStock item: hongXunWeldItemInStocks){ 
			HongXunPoN hongXunPoN = stockDao.hongXunPoNFindById(item.getPoNID());
			//System.out.println(hongXunPurchaseItem.getMaterialNum());
			if(hongXunPoN.getMaterialNo().equals(materialNum)){
				fillInQuantity(month, hongXunWeldCompilation, item.getQuantity());
			}
		}
		//一对多时用SQL查找会出问题 select instoreDate 
		sql = "from HongXunWeldNoLimitItemInStock where date_format(date,'%Y-%m')=date_format(now(),'%Y-%m')";
		@SuppressWarnings("unchecked")
		List<HongXunWeldNoLimitItemInStock> hongXunWeldNoLimitItemInStocks = (List<HongXunWeldNoLimitItemInStock>) stockDao.quarywithpara(sql);
		for(HongXunWeldNoLimitItemInStock item: hongXunWeldNoLimitItemInStocks){ 	
			//System.out.println(hongXunPurchaseItem.getMaterialNum());
			if(item.getMaterialNum().equals(materialNum)){
				fillInQuantity(month, hongXunWeldCompilation, item.getQuantity());
			}
		}
		
		sql = "from HongXunWeldItemOutStock where date_format(date,'%Y-%m')=date_format(now(),'%Y-%m')";
		@SuppressWarnings("unchecked")
		List<HongXunWeldItemOutStock> hongXunWeldItemOutStocks = (List<HongXunWeldItemOutStock>) stockDao.quarywithpara(sql);
		//System.out.println("hongXunMaterialItemOutStocks.size(): " + hongXunMaterialItemOutStocks.size());
		for(HongXunWeldItemOutStock item: hongXunWeldItemOutStocks){ 		
			HongXunPoN hongXunPoN = stockDao.hongXunPoNFindById(item.getPoNID());
			//System.out.println("hongXunProductionStock.getMaterialNum(): " + hongXunProductionStock.getMaterialNum());
			if(hongXunPoN.getMaterialNo().equals(materialNum)){
				//System.out.println("materialNum: " + materialNum);
				fillOutQuantity(month, hongXunWeldCompilation, item.getQuantity());
			}	
		}
		
		
		sql = "from HongXunWeldNoLimitItemOutStock where date_format(date,'%Y-%m')=date_format(now(),'%Y-%m')";
		@SuppressWarnings("unchecked")
		List<HongXunWeldNoLimitItemOutStock> hongXunWeldNoLimitItemOutStocks = (List<HongXunWeldNoLimitItemOutStock>) stockDao.quarywithpara(sql);
		for(HongXunWeldNoLimitItemOutStock item: hongXunWeldNoLimitItemOutStocks){ 	
			//System.out.println(hongXunPurchaseItem.getMaterialNum());
			if(item.getMaterialNum().equals(materialNum)){
				fillOutQuantity(month, hongXunWeldCompilation, item.getQuantity());
			}
		}
	
		sql = "from HongXunDeliveryWeldNum where date_format(date,'%Y-%m')=date_format(now(),'%Y-%m')";
		@SuppressWarnings("unchecked")
		List<HongXunDeliveryWeldNum> hongXunDeliveryWeldNums = (List<HongXunDeliveryWeldNum>) stockDao.quarywithpara(sql);
		for(HongXunDeliveryWeldNum item: hongXunDeliveryWeldNums){ 	
			//System.out.println(hongXunPurchaseItem.getMaterialNum());
			HongXunDeliveryWeldItem hongXunDeliveryWeldItem = new HongXunDeliveryWeldItem();
			hongXunDeliveryWeldItem.setDeliveryNumID(item.getIdc());
			List<HongXunDeliveryWeldItem> hongXunDeliveryWeldItems = stockDao.quary(hongXunDeliveryWeldItem);
			for(HongXunDeliveryWeldItem item1: hongXunDeliveryWeldItems){ 	
				if(item1.getMaterialNo().equals(materialNum)){			
					fillOutQuantity(month, hongXunWeldCompilation, item1.getSendQuantity());
				}
			}	
		}
		fillOthers(month, hongXunWeldCompilation,hongXunProductionWeldStock, quantity);*/
	}
	
	/*private void fillInQuantity(int month, HongXunWeldCompilation hongXunWeldCompilation, int instockQuantity){
		if(month==1){
			if(hongXunWeldCompilation.getInQuantity1()==null){
				hongXunWeldCompilation.setInQuantity1(instockQuantity);
			}else{
				hongXunWeldCompilation.setInQuantity1(hongXunWeldCompilation.getInQuantity1() + instockQuantity);
			}
		}else if(month==2){
			if(hongXunWeldCompilation.getInQuantity2()==null){
				hongXunWeldCompilation.setInQuantity2(instockQuantity);
			}else{
				hongXunWeldCompilation.setInQuantity2(hongXunWeldCompilation.getInQuantity2() + instockQuantity);
			}
		}else if(month==3){
			if(hongXunWeldCompilation.getInQuantity3()==null){
				hongXunWeldCompilation.setInQuantity3(instockQuantity);
			}else{
				hongXunWeldCompilation.setInQuantity3(hongXunWeldCompilation.getInQuantity3() + instockQuantity);
			}
		}else if(month==4){
			if(hongXunWeldCompilation.getInQuantity4()==null){
				hongXunWeldCompilation.setInQuantity4(instockQuantity);
			}else{
				hongXunWeldCompilation.setInQuantity4(hongXunWeldCompilation.getInQuantity4() + instockQuantity);
			}
		}else if(month==5){
			if(hongXunWeldCompilation.getInQuantity5()==null){
				hongXunWeldCompilation.setInQuantity5(instockQuantity);
			}else{
				hongXunWeldCompilation.setInQuantity5(hongXunWeldCompilation.getInQuantity5() + instockQuantity);
			}
		}else if(month==6){
			if(hongXunWeldCompilation.getInQuantity6()==null){
				hongXunWeldCompilation.setInQuantity6(instockQuantity);
			}else{
				hongXunWeldCompilation.setInQuantity6(hongXunWeldCompilation.getInQuantity6() + instockQuantity);
			}
		}else if(month==7){
			if(hongXunWeldCompilation.getInQuantity7()==null){
				hongXunWeldCompilation.setInQuantity7(instockQuantity);
			}else{
				hongXunWeldCompilation.setInQuantity7(hongXunWeldCompilation.getInQuantity7() + instockQuantity);
			}
		}else if(month==8){
			if(hongXunWeldCompilation.getInQuantity8()==null){
				hongXunWeldCompilation.setInQuantity8(instockQuantity);
			}else{
				hongXunWeldCompilation.setInQuantity8(hongXunWeldCompilation.getInQuantity8() + instockQuantity);
			}
		}else if(month==9){
			if(hongXunWeldCompilation.getInQuantity9()==null){
				hongXunWeldCompilation.setInQuantity9(instockQuantity);
			}else{
				hongXunWeldCompilation.setInQuantity9(hongXunWeldCompilation.getInQuantity9() + instockQuantity);
			}
		}else if(month==10){
			if(hongXunWeldCompilation.getInQuantity10()==null){
				hongXunWeldCompilation.setInQuantity10(instockQuantity);
			}else{
				hongXunWeldCompilation.setInQuantity10(hongXunWeldCompilation.getInQuantity10() + instockQuantity);
			}
		}else if(month==11){
			if(hongXunWeldCompilation.getInQuantity11()==null){
				hongXunWeldCompilation.setInQuantity11(instockQuantity);
			}else{
				hongXunWeldCompilation.setInQuantity11(hongXunWeldCompilation.getInQuantity11() + instockQuantity);
			}
		}else if(month==12){
			if(hongXunWeldCompilation.getInQuantity12()==null){
				hongXunWeldCompilation.setInQuantity12(instockQuantity);
			}else{
				hongXunWeldCompilation.setInQuantity12(hongXunWeldCompilation.getInQuantity12() + instockQuantity);
			}
		}else{
			
		}
	}
	
	private void fillOutQuantity(int month, HongXunWeldCompilation hongXunWeldCompilation, int outstockQuantity){
		if(month==1){
			if(hongXunWeldCompilation.getOutQuantity1()==null){
				hongXunWeldCompilation.setOutQuantity1(outstockQuantity);
			}else{
				hongXunWeldCompilation.setOutQuantity1(hongXunWeldCompilation.getOutQuantity1() + outstockQuantity);
			}
		}else if(month==2){
			if(hongXunWeldCompilation.getOutQuantity2()==null){
				hongXunWeldCompilation.setOutQuantity2(outstockQuantity);
			}else{
				hongXunWeldCompilation.setOutQuantity2(hongXunWeldCompilation.getOutQuantity2() + outstockQuantity);
			}
		}else if(month==3){
			if(hongXunWeldCompilation.getOutQuantity3()==null){
				hongXunWeldCompilation.setOutQuantity3(outstockQuantity);
			}else{
				hongXunWeldCompilation.setOutQuantity3(hongXunWeldCompilation.getOutQuantity3() + outstockQuantity);
			}
		}else if(month==4){
			if(hongXunWeldCompilation.getOutQuantity4()==null){
				hongXunWeldCompilation.setOutQuantity4(outstockQuantity);
			}else{
				hongXunWeldCompilation.setOutQuantity4(hongXunWeldCompilation.getOutQuantity4() + outstockQuantity);
			}
		}else if(month==5){
			if(hongXunWeldCompilation.getOutQuantity5()==null){
				hongXunWeldCompilation.setOutQuantity5(outstockQuantity);
			}else{
				hongXunWeldCompilation.setOutQuantity5(hongXunWeldCompilation.getOutQuantity5() + outstockQuantity);
			}
		}else if(month==6){
			if(hongXunWeldCompilation.getOutQuantity6()==null){
				hongXunWeldCompilation.setOutQuantity6(outstockQuantity);
			}else{
				hongXunWeldCompilation.setOutQuantity6(hongXunWeldCompilation.getOutQuantity6() + outstockQuantity);
			}
		}else if(month==7){
			if(hongXunWeldCompilation.getOutQuantity7()==null){
				hongXunWeldCompilation.setOutQuantity7(outstockQuantity);
				//System.out.println("getOutQuantity7: " + hongXunWeldCompilation.getOutQuantity7());
			}else{
				hongXunWeldCompilation.setOutQuantity7(hongXunWeldCompilation.getOutQuantity7() + outstockQuantity);
				//System.out.println("getOutQuantity7(): " + hongXunWeldCompilation.getOutQuantity7());
			}
			
		}else if(month==8){
			if(hongXunWeldCompilation.getOutQuantity8()==null){
				hongXunWeldCompilation.setOutQuantity8(outstockQuantity);
			}else{
				hongXunWeldCompilation.setOutQuantity8(hongXunWeldCompilation.getOutQuantity8() + outstockQuantity);
			}
		}else if(month==9){
			if(hongXunWeldCompilation.getOutQuantity9()==null){
				hongXunWeldCompilation.setOutQuantity9(outstockQuantity);
			}else{
				hongXunWeldCompilation.setOutQuantity9(hongXunWeldCompilation.getOutQuantity9() + outstockQuantity);
			}
		}else if(month==10){
			if(hongXunWeldCompilation.getOutQuantity10()==null){
				hongXunWeldCompilation.setOutQuantity10(outstockQuantity);
			}else{
				hongXunWeldCompilation.setOutQuantity10(hongXunWeldCompilation.getOutQuantity10() + outstockQuantity);
			}
		}else if(month==11){
			if(hongXunWeldCompilation.getOutQuantity11()==null){
				hongXunWeldCompilation.setOutQuantity11(outstockQuantity);
			}else{
				hongXunWeldCompilation.setOutQuantity11(hongXunWeldCompilation.getOutQuantity11() + outstockQuantity);
			}
		}else if(month==12){
			if(hongXunWeldCompilation.getOutQuantity12()==null){
				hongXunWeldCompilation.setOutQuantity12(outstockQuantity);
			}else{
				hongXunWeldCompilation.setOutQuantity12(hongXunWeldCompilation.getOutQuantity12() + outstockQuantity);
			}
		}else{
			
		}
	}
	
	private void fillOthers(int month, HongXunWeldCompilation hongXunWeldCompilation,HongXunProductionWeldStock hongXunProductionWeldStock, int quantity){
		hongXunWeldCompilation.setMaterialNum(hongXunProductionWeldStock.getMaterialNum());
		hongXunWeldCompilation.setSpecification(hongXunProductionWeldStock.getSpecification());
		if(month==1){
			hongXunWeldCompilation.setQuantity1(hongXunProductionWeldStock.getQuantity());
			hongXunWeldCompilation.setCheckQuantity1(quantity);
			hongXunWeldCompilation.setDiffQuantity1(hongXunWeldCompilation.getQuantity1()-hongXunWeldCompilation.getCheckQuantity1());
		}else if(month==2){
			hongXunWeldCompilation.setQuantity2(hongXunProductionWeldStock.getQuantity());
			hongXunWeldCompilation.setCheckQuantity2(quantity);
			hongXunWeldCompilation.setDiffQuantity2(hongXunWeldCompilation.getQuantity2()-hongXunWeldCompilation.getCheckQuantity2());
		}else if(month==3){
			hongXunWeldCompilation.setQuantity3(hongXunProductionWeldStock.getQuantity());
			hongXunWeldCompilation.setCheckQuantity3(quantity);
			hongXunWeldCompilation.setDiffQuantity3(hongXunWeldCompilation.getQuantity3()-hongXunWeldCompilation.getCheckQuantity3());
		}else if(month==4){
			hongXunWeldCompilation.setQuantity4(hongXunProductionWeldStock.getQuantity());
			hongXunWeldCompilation.setCheckQuantity4(quantity);
			hongXunWeldCompilation.setDiffQuantity4(hongXunWeldCompilation.getQuantity4()-hongXunWeldCompilation.getCheckQuantity4());
		}else if(month==5){
			hongXunWeldCompilation.setQuantity5(hongXunProductionWeldStock.getQuantity());
			hongXunWeldCompilation.setCheckQuantity5(quantity);
			hongXunWeldCompilation.setDiffQuantity5(hongXunWeldCompilation.getQuantity5()-hongXunWeldCompilation.getCheckQuantity5());
		}else if(month==6){
			hongXunWeldCompilation.setQuantity6(hongXunProductionWeldStock.getQuantity());
			hongXunWeldCompilation.setCheckQuantity6(quantity);
			hongXunWeldCompilation.setDiffQuantity6(hongXunWeldCompilation.getQuantity6()-hongXunWeldCompilation.getCheckQuantity6());
		}else if(month==7){
			hongXunWeldCompilation.setQuantity7(hongXunProductionWeldStock.getQuantity());
			hongXunWeldCompilation.setCheckQuantity7(quantity);
			hongXunWeldCompilation.setDiffQuantity7(hongXunWeldCompilation.getQuantity7()-hongXunWeldCompilation.getCheckQuantity7());
		}else if(month==8){
			hongXunWeldCompilation.setQuantity8(hongXunProductionWeldStock.getQuantity());
			hongXunWeldCompilation.setCheckQuantity8(quantity);
			hongXunWeldCompilation.setDiffQuantity8(hongXunWeldCompilation.getQuantity8()-hongXunWeldCompilation.getCheckQuantity8());
		}else if(month==9){
			hongXunWeldCompilation.setQuantity9(hongXunProductionWeldStock.getQuantity());
			hongXunWeldCompilation.setCheckQuantity9(quantity);
			hongXunWeldCompilation.setDiffQuantity9(hongXunWeldCompilation.getQuantity9()-hongXunWeldCompilation.getCheckQuantity9());
		}else if(month==10){
			hongXunWeldCompilation.setQuantity10(hongXunProductionWeldStock.getQuantity());
			hongXunWeldCompilation.setCheckQuantity10(quantity);
			hongXunWeldCompilation.setDiffQuantity10(hongXunWeldCompilation.getQuantity10()-hongXunWeldCompilation.getCheckQuantity10());
		}else if(month==11){
			hongXunWeldCompilation.setQuantity11(hongXunProductionWeldStock.getQuantity());
			hongXunWeldCompilation.setCheckQuantity11(quantity);
			hongXunWeldCompilation.setDiffQuantity11(hongXunWeldCompilation.getQuantity11()-hongXunWeldCompilation.getCheckQuantity11());
		}else if(month==12){
			hongXunWeldCompilation.setQuantity12(hongXunProductionWeldStock.getQuantity());
			hongXunWeldCompilation.setCheckQuantity12(quantity);
			hongXunWeldCompilation.setDiffQuantity12(hongXunWeldCompilation.getQuantity12()-hongXunWeldCompilation.getCheckQuantity12());
		}
	}
*/
	
	/*@Override
	public List<Map<String, Object>> exlImport(HSSFWorkbook workbook) {
		// TODO Auto-generated method stub
		
		List<String> list = new ArrayList<String>();
		List<HongXunProductionWeldStock> hongXunProductionWeldStocks = new ArrayList<HongXunProductionWeldStock>();
		try {
			HSSFSheet sheet = workbook.getSheetAt(0);
			int count = 0;
			for (Row row : sheet) {
				if (row.getCell(0) == null || row.getCell(0).toString().equals("")) {
					break;
				}
				int end = row.getLastCellNum();
				HongXunProductionWeldStock hongXunProductionWeldStock = new HongXunProductionWeldStock();
				for (int i = 0; i < end; i++) {
					Cell cell = row.getCell(i);
					Object obj = getValue(cell);
					if (obj != null) {
						if (count < 1) {
							list.add(obj.toString());
						} else {
							String temStr = list.get(i);
							if (!temStr.equals("")) {
								if (temStr.equals("出库量")) {
									String str = obj.toString();
									if (isNum(str)) {
										hongXunProductionWeldStock.setOutQuantity(Double.valueOf(str).intValue());										
									}									
								} else if ( temStr.equals("入库量")) {
									String str = obj.toString();
									if (isNum(str)) {
										hongXunProductionWeldStock.setInQuantity(Double.valueOf(str).intValue());										
									}
								} else if (temStr.equals("库存量")) {
									String str = obj.toString();
									if (isNum(str)) {
										hongXunProductionWeldStock.setQuantity(Double.valueOf(str).intValue());										
									}
								} else if (temStr.equals("型号/规格")) {
									hongXunProductionWeldStock.setSpecification(obj.toString());
								} else if (temStr.equals("物料编码")) {
									hongXunProductionWeldStock.setMaterialNum(obj.toString());			
								} else if (temStr.equals("盘点量")) {
									String str = obj.toString();
									if (isNum(str)) {
										hongXunProductionWeldStock.setSafeQuantity(Double.valueOf(str).intValue());										
									}
								} 								
							}
						}
					}
				}
				if (count < 1) {
					count++;
				} else {									
					hongXunProductionWeldStocks.add(hongXunProductionWeldStock);				
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return saveWeldCompilation(hongXunProductionWeldStocks);
	}
	private boolean isNum(String str) {
		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
	}
	private String getValue(Cell cell) {
		if(cell == null){
			return null;
		}
		String obj = null;
		switch (cell.getCellTypeEnum()) {
		case BOOLEAN:
			obj = String.valueOf(cell.getBooleanCellValue());
			break;
		case ERROR:
			obj = String.valueOf(cell.getErrorCellValue());
			break;
		case NUMERIC:		
			String str = String.valueOf(cell.getNumericCellValue());  
			//System.out.println("num:" + cell.getNumericCellValue());
			BigDecimal bd = new BigDecimal(replaceBlank(str));	
			obj = bd.stripTrailingZeros().toPlainString();
			break;
		case STRING:
			String str1 = cell.getStringCellValue();
			if(str1.equals("")){
				obj=null;
			}else{
				obj = replaceBlank(str1);
			}
			break;
		default:
			//System.out.println("read getvalue default");
			obj = null;
			break;
		}
		return obj;
	}
	private String replaceBlank(String str) {
		String dest = "";
		if (str!=null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}
	
	private List<Map<String, Object>> saveWeldCompilation (List<HongXunProductionWeldStock> hongXunProductionWeldStockList) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int month = cal.get(Calendar.MONTH) + 1;	
		List<HongXunWeldCompilation> weldCompilations = new ArrayList<HongXunWeldCompilation>();
		for(HongXunProductionWeldStock item: hongXunProductionWeldStockList){
			//在盘点表中查找是否存在项
			HongXunWeldCompilation hongXunWeldCompilation = new HongXunWeldCompilation();
			hongXunWeldCompilation.setMaterialNum(item.getMaterialNum());
			hongXunWeldCompilation.setSpecification(item.getSpecification());
			List<HongXunWeldCompilation> hongXunWeldCompilations = stockDao.quary(hongXunWeldCompilation);
			//System.out.println("hongXunAssembleCompilations.size(): " + hongXunAssembleCompilations.size());
			if(hongXunWeldCompilations.size()==1){
				hongXunWeldCompilation = hongXunWeldCompilations.get(0);
			}else if(hongXunWeldCompilations.size()>1){	
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("error", "物料号:"+ item.getMaterialNum() + " 规格:" + item.getSpecification() +"->盘点表有重复记录");
				list.add(map);
				return list;
			}
			//在库存中查找是否存在项
			HongXunProductionWeldStock hongXunProductionWeldStock = new HongXunProductionWeldStock();
			hongXunProductionWeldStock.setMaterialNum(item.getMaterialNum());
			hongXunProductionWeldStock.setSpecification(item.getSpecification());
			List<HongXunProductionWeldStock> hongXunProductionWeldStocks = stockDao.quary(hongXunProductionWeldStock);
			if(hongXunProductionWeldStocks.size()==1){
				//System.out.println(item.getMaterialNum() + "->item.getSpecification() :" + item.getSpecification().replaceAll("\r|\n|\\s*", "") + "hongXunProductionWeldStocks.get(0) :" + hongXunProductionWeldStocks.get(0).getSpecification());
				fillmsg(month, hongXunWeldCompilation, item.getMaterialNum(), hongXunProductionWeldStocks.get(0),item.getSafeQuantity());
			}else if(hongXunProductionWeldStocks.size()==0){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("error", "物料号:"+ item.getMaterialNum() + " 规格:" + item.getSpecification() + "->焊接仓无入库记录");
				list.add(map);
				return list;
			}else{
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("error", "物料号:"+ item.getMaterialNum() + " 规格:" + item.getSpecification() + "->焊接仓有重复料号规格，请联系工程师");
				list.add(map);
				return list;
			}
			weldCompilations.add(hongXunWeldCompilation);
			
		}	
		for(HongXunWeldCompilation item: weldCompilations){
			if(item.getIdc()==0){
				stockDao.save(item);
			}else{
				stockDao.update(item);
			}
			Map<String,Object> map = new HashMap<String,Object>();
			mapHongXunMaterialCompilation(map,item);
			list.add(map);
		}
		return list;
		
	}*/
	
	@Override
	public List<Map<String, Object>> xlf(List<HongXunWeldCompilation> hongXunWeldCompilationList, String date) {
		// TODO Auto-generated method stub
/*		List<HongXunProductionWeldStock> s = stockDao.quary(new HongXunProductionWeldStock());
		for(HongXunProductionWeldStock item: s){
			item.setSpecification(item.getSpecification().replaceAll("\\s*\r|\n|\t", ""));
			stockDao.update(item);
		}*/
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int month = cal.get(Calendar.MONTH) + 1;			
		if(date.indexOf("-")>0){
			String[] arrStr = date.split("-");
			if(arrStr.length==3){
				month=Integer.valueOf(arrStr[1]);
			}
		}
		List<HongXunWeldCompilation> weldCompilations = new ArrayList<HongXunWeldCompilation>();
		for(HongXunWeldCompilation item: hongXunWeldCompilationList){
/*			HongXunWeldCompilation hongXunWeldCompilation = new HongXunWeldCompilation();
			hongXunWeldCompilation.setMaterialNum(item.getMaterialNum());
			hongXunWeldCompilation.setSpecification(item.getSpecification());
			List<HongXunWeldCompilation> hongXunWeldCompilations = stockDao.quary(hongXunWeldCompilation);
			//System.out.println("hongXunAssembleCompilations.size(): " + hongXunAssembleCompilations.size());
			if(hongXunWeldCompilations.size()==1){
				hongXunWeldCompilation = hongXunWeldCompilations.get(0);
			}else if(hongXunWeldCompilations.size()>1){	
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("error", "物料号:"+ item.getMaterialNum() + " 规格:" + item.getSpecification() +"->盘点表有重复记录");
				list.add(map);
				return list;
			}*/
			
			HongXunProductionWeldStock hongXunProductionWeldStock = iWeldDao.productionWeldFindById(item.getIdc());
			if(hongXunProductionWeldStock != null){
				HongXunWeldCompilation hongXunWeldCompilation = new HongXunWeldCompilation();
				hongXunWeldCompilation.setStockId(hongXunProductionWeldStock.getIdc());
				List<HongXunWeldCompilation> hongXunWeldCompilations = iWeldDao.quary(hongXunWeldCompilation);
				if(hongXunWeldCompilations.size()==1){
					hongXunWeldCompilation = hongXunWeldCompilations.get(0);
				}
				if(item.getCheckQuantity1()==null){
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("error", "物料号:"+ item.getMaterialNum() + "->盘点量为空");
					list.add(map);
					return list;
				}
				fillmsg(month, hongXunWeldCompilation, item.getMaterialNum(), hongXunProductionWeldStock, item.getCheckQuantity1());	
				weldCompilations.add(hongXunWeldCompilation);
			}else {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("error", "物料号:"+ item.getMaterialNum() + " 规格:" + item.getSpecification() + "->焊接仓无入库记录");
				list.add(map);
				return list;
			}
			//@SuppressWarnings("unchecked")
			//List<HongXunProductionWeldStock> hongXunProductionWeldStocks = (List<HongXunProductionWeldStock>) stockDao.quarywithpara("HongXunProductionWeldStock", "materialNum", item.getMaterialNum()); 
			//System.out.println("hongXunProductionStocks.size(): " + hongXunProductionStocks.size());
	/*		HongXunProductionWeldStock hongXunProductionWeldStock = new HongXunProductionWeldStock();
			hongXunProductionWeldStock.setMaterialNum(item.getMaterialNum());
			hongXunProductionWeldStock.setSpecification(item.getSpecification());
			List<HongXunProductionWeldStock> hongXunProductionWeldStocks = stockDao.quary(hongXunProductionWeldStock);
			if(hongXunProductionWeldStocks.size()==1){
				//System.out.println(item.getMaterialNum() + "->item.getSpecification() :" + item.getSpecification().replaceAll("\r|\n|\\s*", "") + "hongXunProductionWeldStocks.get(0) :" + hongXunProductionWeldStocks.get(0).getSpecification());
				fillmsg(month, hongXunWeldCompilation, item.getMaterialNum(), hongXunProductionWeldStocks.get(0),item.getCheckQuantity1());
			}else if(hongXunProductionWeldStocks.size()==0){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("error", "物料号:"+ item.getMaterialNum() + " 规格:" + item.getSpecification() + "->焊接仓无入库记录");
				list.add(map);
				return list;
			}else{
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("error", "物料号:"+ item.getMaterialNum() + " 规格:" + item.getSpecification() + "->焊接仓有重复料号规格，请联系工程师");
				list.add(map);
				return list;
			}
			weldCompilations.add(hongXunWeldCompilation);*/
		}
		//System.out.println("assembleCompilations.size(): " + assembleCompilations.size());
		for(HongXunWeldCompilation item: weldCompilations){
			if(item.getIdc()==0){
				iWeldDao.save(item);
			}else{
				iWeldDao.update(item);
			}
			Map<String,Object> map = new HashMap<String,Object>();
			mapHongXunMaterialCompilation(map,item);
			list.add(map);
		}
		return list;
		
	}

}
