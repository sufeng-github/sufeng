package paul.sydney.service.production.impl;

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
import paul.sydney.model.HongXunAssembleCompilation;
import paul.sydney.dao.IProductionCompilationDao;
import paul.sydney.dao.IProductionDao;
import paul.sydney.dao.StockDao;
import paul.sydney.model.HongXunProductionStock;
import paul.sydney.service.production.ServiceAssembleCompilationInf;
@Transactional
@Service("serviceAssembleCompliation")
public class ServiceAssembleCompilationImpl implements ServiceAssembleCompilationInf{
	
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
	IProductionCompilationDao iProductionCompilationDao;
	public void setStockDao(IProductionCompilationDao iProductionCompilationDao) {
		this.iProductionCompilationDao = iProductionCompilationDao;
	}
	@Override
	public List<Map<String, Object>> loadData() {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		List<HongXunAssembleCompilation>  HongXunMaterialCompilations = iProductionCompilationDao.quary(new HongXunAssembleCompilation());
		for(HongXunAssembleCompilation item : HongXunMaterialCompilations){
		
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
		HongXunAssembleCompilation e = new HongXunAssembleCompilation();
		Class<? extends HongXunAssembleCompilation> cls = e.getClass();  
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
	
	private void mapHongXunMaterialCompilation(Map<String, Object> map, HongXunAssembleCompilation item) {
		// TODO Auto-generated method stub	
		//System.out.println("test: " + item.getHongXunDataOne().getProductionOrderNum());
		map.put("idc", item.getIdc());
		if(item.getStockId() != null){
			HongXunProductionStock hongXunProductionStock = iProductionDao.productionFindById(item.getStockId());
			if(hongXunProductionStock != null){
				map.put("materialNum", hongXunProductionStock.getMaterialNum());
				map.put("specification", hongXunProductionStock.getSpecification());
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
		List<HongXunAssembleCompilation> HongXunMaterialCompilations = (List<HongXunAssembleCompilation>) iProductionDao.quaryFuzzyWithpara("HongXunAssembleCompilation", "materialNum", str); 	
		for(HongXunAssembleCompilation item: HongXunMaterialCompilations){	
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
		List<HongXunAssembleCompilation> HongXunMaterialCompilations = (List<HongXunAssembleCompilation>) stockDao.quaryFuzzyWithpara("HongXunAssembleCompilation", "materialNum", materialNum);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (HongXunMaterialCompilations != null) {
			for (HongXunAssembleCompilation item : HongXunMaterialCompilations) {
				Map<String, Object> map = new HashMap<String, Object>();
				mapHongXunMaterialCompilation(map,item);
				list.add(map);
			}
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> saveRow(HongXunAssembleCompilation hongXunAssembleCompilation) {
		// TODO Auto-generated method stub
		iProductionCompilationDao.save(hongXunAssembleCompilation);
		Map<String,Object> map = new HashMap<String,Object>();
		mapHongXunMaterialCompilation(map, hongXunAssembleCompilation);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.add(map);
		return list;
	}

	@Override
	public List<Map<String, Object>> updateRow(HongXunAssembleCompilation hongXunAssembleCompilation) {
		// TODO Auto-generated method stub
		iProductionCompilationDao.update(hongXunAssembleCompilation);
		Map<String,Object> map = new HashMap<String,Object>();
		mapHongXunMaterialCompilation(map, hongXunAssembleCompilation);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.add(map);
		return list;
	}

	@Override
	public List<Map<String, Object>> getMaterialAlarms(String alarm) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		@SuppressWarnings("unchecked")
		List<HongXunAssembleCompilation> HongXunMaterialCompilations = (List<HongXunAssembleCompilation>) stockDao.quaryFuzzyWithpara("HongXunAssembleCompilation", "alarm", alarm);
		for(HongXunAssembleCompilation item : HongXunMaterialCompilations){
			Map<String,Object> map = new HashMap<String,Object>();
			mapHongXunMaterialCompilation(map,item);
			list.add(map);
		}
		
		return list;
	}

	@Override
	public List<Map<String, Object>> scanMaterialCompilation(String materialNum, int quantity) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
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
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("error", "物料号:"+ materialNum +"->装配仓库无记录");
				list.add(map);
				return list;				
	    	}else if(hongXunProductionStocks.size()==1){
	    		HongXunAssembleCompilation hongXunAssembleCompilation = new HongXunAssembleCompilation();
	    		hongXunAssembleCompilation.setStockId(hongXunProductionStocks.get(0).getIdc());
	    		List<HongXunAssembleCompilation> hongXunAssembleCompilations = iProductionCompilationDao.quary(hongXunAssembleCompilation);
	    		Calendar cal = Calendar.getInstance();
	    		cal.setTime(new Date());
	    		int month = cal.get(Calendar.MONTH) + 1;
	    		if(hongXunAssembleCompilations.size()==0){
	    			fillmsg(month, hongXunAssembleCompilation, materialNum, hongXunProductionStock, quantity);	
	    		}else if(hongXunAssembleCompilations.size()==1){
	    			hongXunAssembleCompilation = hongXunAssembleCompilations.get(0);
	    			if(month==1){ 
	    				int checkQuantity = hongXunAssembleCompilation.getCheckQuantity1();
	    				int diffQuantity = checkQuantity - quantity;
	    				hongXunAssembleCompilation.setDiffQuantity1(hongXunAssembleCompilation.getDiffQuantity1()+diffQuantity);
	    				hongXunAssembleCompilation.setCheckQuantity1(quantity);
	    			}else if(month==2){ 
	    				int checkQuantity = hongXunAssembleCompilation.getCheckQuantity2();
	    				int diffQuantity = checkQuantity - quantity;
	    				hongXunAssembleCompilation.setDiffQuantity2(hongXunAssembleCompilation.getDiffQuantity2()+diffQuantity);
	    				hongXunAssembleCompilation.setCheckQuantity2(quantity);
	    			}else if(month==3){ 
	    				int checkQuantity = hongXunAssembleCompilation.getCheckQuantity3();
	    				int diffQuantity = checkQuantity - quantity;
	    				hongXunAssembleCompilation.setDiffQuantity3(hongXunAssembleCompilation.getDiffQuantity3()+diffQuantity);
	    				hongXunAssembleCompilation.setCheckQuantity3(quantity);
	    			}else if(month==4){ 
	    				int checkQuantity = hongXunAssembleCompilation.getCheckQuantity4();
	    				int diffQuantity = checkQuantity - quantity;
	    				hongXunAssembleCompilation.setDiffQuantity4(hongXunAssembleCompilation.getDiffQuantity4()+diffQuantity);
	    				hongXunAssembleCompilation.setCheckQuantity4(quantity);
	    			}else if(month==5){ 
	    				int checkQuantity = hongXunAssembleCompilation.getCheckQuantity5();
	    				int diffQuantity = checkQuantity - quantity;
	    				hongXunAssembleCompilation.setDiffQuantity5(hongXunAssembleCompilation.getDiffQuantity5()+diffQuantity);
	    				hongXunAssembleCompilation.setCheckQuantity5(quantity);
	    			}else if(month==6){ 
	    				int checkQuantity = hongXunAssembleCompilation.getCheckQuantity6();
	    				int diffQuantity = checkQuantity - quantity;
	    				hongXunAssembleCompilation.setDiffQuantity6(hongXunAssembleCompilation.getDiffQuantity6()+diffQuantity);
	    				hongXunAssembleCompilation.setCheckQuantity6(quantity);
	    			}else if(month==7){ 
	    				int checkQuantity = hongXunAssembleCompilation.getCheckQuantity7();
	    				int diffQuantity = checkQuantity - quantity;
	    				hongXunAssembleCompilation.setDiffQuantity7(hongXunAssembleCompilation.getDiffQuantity7()+diffQuantity);
	    				hongXunAssembleCompilation.setCheckQuantity7(quantity);
	    			}else if(month==8){ 
	    				int checkQuantity = hongXunAssembleCompilation.getCheckQuantity8();
	    				int diffQuantity = checkQuantity - quantity;
	    				hongXunAssembleCompilation.setDiffQuantity8(hongXunAssembleCompilation.getDiffQuantity8()+diffQuantity);
	    				hongXunAssembleCompilation.setCheckQuantity8(quantity);
	    			}else if(month==9){ 
	    				int checkQuantity = hongXunAssembleCompilation.getCheckQuantity9();
	    				int diffQuantity = checkQuantity - quantity;
	    				hongXunAssembleCompilation.setDiffQuantity9(hongXunAssembleCompilation.getDiffQuantity9()+diffQuantity);
	    				hongXunAssembleCompilation.setCheckQuantity9(quantity);
	    			}else if(month==10){ 
	    				int checkQuantity = hongXunAssembleCompilation.getCheckQuantity10();
	    				int diffQuantity = checkQuantity - quantity;
	    				hongXunAssembleCompilation.setDiffQuantity10(hongXunAssembleCompilation.getDiffQuantity10()+diffQuantity);
	    				hongXunAssembleCompilation.setCheckQuantity10(quantity);
	    			}else if(month==11){ 
	    				int checkQuantity = hongXunAssembleCompilation.getCheckQuantity11();
	    				int diffQuantity = checkQuantity - quantity;
	    				hongXunAssembleCompilation.setDiffQuantity11(hongXunAssembleCompilation.getDiffQuantity11()+diffQuantity);
	    				hongXunAssembleCompilation.setCheckQuantity11(quantity);
	    			}else if(month==12){ 
	    				int checkQuantity = hongXunAssembleCompilation.getCheckQuantity12();
	    				int diffQuantity = checkQuantity - quantity;
	    				hongXunAssembleCompilation.setDiffQuantity12(hongXunAssembleCompilation.getDiffQuantity12()+diffQuantity);
	    				hongXunAssembleCompilation.setCheckQuantity12(quantity);
	    			}
	    		}else if(hongXunAssembleCompilations.size()>1){
	    			Map<String, Object> map = new HashMap<String, Object>();
	    			map.put("error", "物料号:"+ materialNum +"->指向多个盘点数据,请联系工程师");
	    			list.add(map);
	    			return list;
	    		}
							   		
		    	if(hongXunAssembleCompilation.getIdc() == 0){
		    		iProductionCompilationDao.save(hongXunAssembleCompilation);	    			
		    	}else{
		    	    iProductionCompilationDao.update(hongXunAssembleCompilation);	
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
		List<HongXunAssembleCompilation> hongXunAssembleCompilations = iProductionCompilationDao.quary(new HongXunAssembleCompilation());
		List<HongXunProductionStock> list = new ArrayList<HongXunProductionStock>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int month = cal.get(Calendar.MONTH) + 1;
		for(HongXunAssembleCompilation item: hongXunAssembleCompilations){
/*    		HongXunProductionStock hongXunProductionStock = new HongXunProductionStock();
    		hongXunProductionStock.setMaterialNum(item.getMaterialNum());
    		hongXunProductionStock.setSpecification(item.getSpecification());
    		List<HongXunProductionStock> hongXunProductionStocks = stockDao.quary(hongXunProductionStock);*/
			HongXunProductionStock hongXunProductionStock = iProductionDao.productionFindById(item.getStockId());
    		if(hongXunProductionStock != null){
    			if(month==1){
    				hongXunProductionStock.setQuantity(item.getCheckQuantity1());
    			}else if(month==2){
    				hongXunProductionStock.setQuantity(item.getCheckQuantity2());
    			}else if(month==3){
    				hongXunProductionStock.setQuantity(item.getCheckQuantity3());
    			}else if(month==4){
    				hongXunProductionStock.setQuantity(item.getCheckQuantity4());
    			}else if(month==5){
    				hongXunProductionStock.setQuantity(item.getCheckQuantity5());
    			}else if(month==6){
    				hongXunProductionStock.setQuantity(item.getCheckQuantity6());
    			}else if(month==7){
    				hongXunProductionStock.setQuantity(item.getCheckQuantity7());
    			}else if(month==8){
    				hongXunProductionStock.setQuantity(item.getCheckQuantity8());
    			}else if(month==9){
    				hongXunProductionStock.setQuantity(item.getCheckQuantity9());
    			}else if(month==10){
    				hongXunProductionStock.setQuantity(item.getCheckQuantity10());
    			}else if(month==11){
    				hongXunProductionStock.setQuantity(item.getCheckQuantity11());
    			}else if(month==12){
    				hongXunProductionStock.setQuantity(item.getCheckQuantity12());
    			}
    			hongXunProductionStock.setInQuantity(0);
    			hongXunProductionStock.setOutQuantity(0);
    			list.add(hongXunProductionStock);
    		}
		}
		for(HongXunProductionStock item: list){
			iProductionDao.update(item);
		}
		
		return new ArrayList<Map<String,Object>>();
	}
	/*    	}else if(hongXunAssembleCompilations.size()==0){
	hongXunAssembleCompilation = new HongXunAssembleCompilation(); 
	@SuppressWarnings("unchecked")
	List<HongXunMaterialStock> hongXunProductionStocks = (List<HongXunMaterialStock>) stockDao.quarywithpara("HongXunMaterialStock", "materialNum", materialNum); 
	if(hongXunProductionStocks.size()==1){
		fillmsg(month, hongXunAssembleCompilation, materialNum, hongXunProductionStocks.get(0), quantity);		
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
	private void fillmsg(int month, HongXunAssembleCompilation hongXunAssembleCompilation, String materialNum, HongXunProductionStock hongXunProductionStock, int quantity){
		if(hongXunProductionStock.getInQuantity()==null){
			hongXunProductionStock.setInQuantity(0);
		}
		if(hongXunProductionStock.getOutQuantity()==null){
			hongXunProductionStock.setOutQuantity(0);
		}
		if(hongXunProductionStock.getQuantity() == null){
			hongXunProductionStock.setQuantity(0);
		}
		int checkquantity = hongXunProductionStock.getQuantity() + hongXunProductionStock.getInQuantity() - hongXunProductionStock.getOutQuantity() - quantity;
		//int checkquantity = 0;
		if(month==1){
			
			hongXunAssembleCompilation.setInQuantity1(hongXunProductionStock.getInQuantity());
			hongXunAssembleCompilation.setOutQuantity1(hongXunProductionStock.getOutQuantity());
			hongXunAssembleCompilation.setQuantity1(hongXunProductionStock.getQuantity());
			hongXunAssembleCompilation.setCheckQuantity1(quantity);
			hongXunAssembleCompilation.setDiffQuantity1(checkquantity);
		}else if(month==2){
			hongXunAssembleCompilation.setInQuantity2(hongXunProductionStock.getInQuantity());
			hongXunAssembleCompilation.setOutQuantity2(hongXunProductionStock.getOutQuantity());
			hongXunAssembleCompilation.setQuantity2(hongXunProductionStock.getQuantity());
			hongXunAssembleCompilation.setCheckQuantity2(quantity);
			hongXunAssembleCompilation.setDiffQuantity2(checkquantity);
		}else if(month==3){
			hongXunAssembleCompilation.setInQuantity3(hongXunProductionStock.getInQuantity());
			hongXunAssembleCompilation.setOutQuantity3(hongXunProductionStock.getOutQuantity());
			hongXunAssembleCompilation.setQuantity3(hongXunProductionStock.getQuantity());
			hongXunAssembleCompilation.setCheckQuantity3(quantity);
			hongXunAssembleCompilation.setDiffQuantity3(checkquantity);
		}else if(month==4){
			hongXunAssembleCompilation.setInQuantity4(hongXunProductionStock.getInQuantity());
			hongXunAssembleCompilation.setOutQuantity4(hongXunProductionStock.getOutQuantity());
			hongXunAssembleCompilation.setQuantity4(hongXunProductionStock.getQuantity());
			hongXunAssembleCompilation.setCheckQuantity4(quantity);
			hongXunAssembleCompilation.setDiffQuantity4(checkquantity);
		}else if(month==5){
			hongXunAssembleCompilation.setInQuantity5(hongXunProductionStock.getInQuantity());
			hongXunAssembleCompilation.setOutQuantity5(hongXunProductionStock.getOutQuantity());
			hongXunAssembleCompilation.setQuantity5(hongXunProductionStock.getQuantity());
			hongXunAssembleCompilation.setCheckQuantity5(quantity);
			hongXunAssembleCompilation.setDiffQuantity5(checkquantity);
		}else if(month==6){
			hongXunAssembleCompilation.setInQuantity6(hongXunProductionStock.getInQuantity());
			hongXunAssembleCompilation.setOutQuantity6(hongXunProductionStock.getOutQuantity());
			hongXunAssembleCompilation.setQuantity6(hongXunProductionStock.getQuantity());
			hongXunAssembleCompilation.setCheckQuantity6(quantity);
			hongXunAssembleCompilation.setDiffQuantity6(checkquantity);
		}else if(month==7){
			hongXunAssembleCompilation.setInQuantity7(hongXunProductionStock.getInQuantity());
			hongXunAssembleCompilation.setOutQuantity7(hongXunProductionStock.getOutQuantity());
			hongXunAssembleCompilation.setQuantity7(hongXunProductionStock.getQuantity());
			hongXunAssembleCompilation.setCheckQuantity7(quantity);
			hongXunAssembleCompilation.setDiffQuantity7(checkquantity);
		}else if(month==8){
			hongXunAssembleCompilation.setInQuantity8(hongXunProductionStock.getInQuantity());
			hongXunAssembleCompilation.setOutQuantity8(hongXunProductionStock.getOutQuantity());
			hongXunAssembleCompilation.setQuantity8(hongXunProductionStock.getQuantity());
			hongXunAssembleCompilation.setCheckQuantity8(quantity);
			hongXunAssembleCompilation.setDiffQuantity8(checkquantity);
		}else if(month==9){
			hongXunAssembleCompilation.setInQuantity9(hongXunProductionStock.getInQuantity());
			hongXunAssembleCompilation.setOutQuantity9(hongXunProductionStock.getOutQuantity());
			hongXunAssembleCompilation.setQuantity9(hongXunProductionStock.getQuantity());
			hongXunAssembleCompilation.setCheckQuantity9(quantity);
			hongXunAssembleCompilation.setDiffQuantity9(checkquantity);
		}else if(month==10){
			hongXunAssembleCompilation.setInQuantity10(hongXunProductionStock.getInQuantity());
			hongXunAssembleCompilation.setOutQuantity10(hongXunProductionStock.getOutQuantity());
			hongXunAssembleCompilation.setQuantity10(hongXunProductionStock.getQuantity());
			hongXunAssembleCompilation.setCheckQuantity10(quantity);
			hongXunAssembleCompilation.setDiffQuantity10(checkquantity);
		}else if(month==11){
			hongXunAssembleCompilation.setInQuantity11(hongXunProductionStock.getInQuantity());
			hongXunAssembleCompilation.setOutQuantity11(hongXunProductionStock.getOutQuantity());
			hongXunAssembleCompilation.setQuantity11(hongXunProductionStock.getQuantity());
			hongXunAssembleCompilation.setCheckQuantity11(quantity);
			hongXunAssembleCompilation.setDiffQuantity11(checkquantity);
		}else if(month==12){
			hongXunAssembleCompilation.setInQuantity12(hongXunProductionStock.getInQuantity());
			hongXunAssembleCompilation.setOutQuantity12(hongXunProductionStock.getOutQuantity());
			hongXunAssembleCompilation.setQuantity12(hongXunProductionStock.getQuantity());
			hongXunAssembleCompilation.setCheckQuantity12(quantity);
			hongXunAssembleCompilation.setDiffQuantity12(checkquantity);
		}else{
			
		}
		/*//一对多时用SQL查找会出问题 select instoreDate 
		String sql = "from HongXunProductionItemInStock where date_format(date,'%Y-%m')=date_format(now(),'%Y-%m')";
		@SuppressWarnings("unchecked")
		List<HongXunProductionItemInStock> hongXunProductionItemInStocks = (List<HongXunProductionItemInStock>) stockDao.quarywithpara(sql);
		for(HongXunProductionItemInStock item: hongXunProductionItemInStocks){ 
			HongXunPoN hongXunPoN = stockDao.hongXunPoNFindById(item.getPoNID());
			//System.out.println(hongXunPurchaseItem.getMaterialNum());
			if(hongXunPoN.getMaterialNo().equals(materialNum)){
				fillInQuantity(month, hongXunAssembleCompilation, item.getQuantity());
			}
		}
		
		//一对多时用SQL查找会出问题 select instoreDate 
		sql = "from HongXunProductionNoLimitItemInStock where date_format(date,'%Y-%m')=date_format(now(),'%Y-%m')";
		@SuppressWarnings("unchecked")
		List<HongXunProductionNoLimitItemInStock> hongXunProductionNoLimitItemInStocks = (List<HongXunProductionNoLimitItemInStock>) stockDao.quarywithpara(sql);
		for(HongXunProductionNoLimitItemInStock item: hongXunProductionNoLimitItemInStocks){ 	
			//System.out.println(hongXunPurchaseItem.getMaterialNum());
			if(item.getMaterialNum().equals(materialNum)){
				fillInQuantity(month, hongXunAssembleCompilation, item.getQuantity());
			}
		}
		
		sql = "from HongXunProductionItemOutStock where date_format(date,'%Y-%m')=date_format(now(),'%Y-%m')";
		@SuppressWarnings("unchecked")
		List<HongXunProductionItemOutStock> hongXunMaterialItemOutStocks = (List<HongXunProductionItemOutStock>) stockDao.quarywithpara(sql);
		//System.out.println("hongXunMaterialItemOutStocks.size(): " + hongXunMaterialItemOutStocks.size());
		for(HongXunProductionItemOutStock item: hongXunMaterialItemOutStocks){ 		
			HongXunPoN hongXunPoN = stockDao.hongXunPoNFindById(item.getPoNID());
			//System.out.println("hongXunProductionStock.getMaterialNum(): " + hongXunProductionStock.getMaterialNum());
			if(hongXunPoN.getMaterialNo().equals(materialNum)){
				//System.out.println("materialNum: " + materialNum);
				fillOutQuantity(month, hongXunAssembleCompilation, item.getQuantity());
			}	
		}
		
		sql = "from HongXunProductionNoLimitItemOutStock where date_format(date,'%Y-%m')=date_format(now(),'%Y-%m')";
		@SuppressWarnings("unchecked")
		List<HongXunProductionNoLimitItemOutStock> hongXunProductionNoLimitItemOutStocks = (List<HongXunProductionNoLimitItemOutStock>) stockDao.quarywithpara(sql);
		for(HongXunProductionNoLimitItemOutStock item: hongXunProductionNoLimitItemOutStocks){ 	
			//System.out.println(hongXunPurchaseItem.getMaterialNum());
			if(item.getMaterialNum().equals(materialNum)){
				fillOutQuantity(month, hongXunAssembleCompilation, item.getQuantity());
			}
		}
	
		sql = "from HongXunDeliveryNum where date_format(date,'%Y-%m')=date_format(now(),'%Y-%m')";
		@SuppressWarnings("unchecked")
		List<HongXunDeliveryNum> hongXunDeliveryNums = (List<HongXunDeliveryNum>) stockDao.quarywithpara(sql);
		for(HongXunDeliveryNum item: hongXunDeliveryNums){ 	
			//System.out.println(hongXunPurchaseItem.getMaterialNum());
			HongXunDeliveryItem hongXunDeliveryItem = new HongXunDeliveryItem();
			hongXunDeliveryItem.setDeliveryNumID(item.getIdc());
			List<HongXunDeliveryItem> hongXunDeliveryItems = stockDao.quary(hongXunDeliveryItem);
			for(HongXunDeliveryItem item1: hongXunDeliveryItems){ 	
				if(item1.getMaterialNo().equals(materialNum)){			
					fillOutQuantity(month, hongXunAssembleCompilation, item1.getSendQuantity());
				}
			}	
		}*/
		
		//fillOthers(month, hongXunAssembleCompilation,hongXunProductionStock, quantity);
	}
	
/*	private void fillInQuantity(int month, HongXunAssembleCompilation hongXunAssembleCompilation, int instockQuantity){
		if(month==1){
			if(hongXunAssembleCompilation.getInQuantity1()==null){
				hongXunAssembleCompilation.setInQuantity1(instockQuantity);
			}else{
				hongXunAssembleCompilation.setInQuantity1(hongXunAssembleCompilation.getInQuantity1() + instockQuantity);
			}
		}else if(month==2){
			if(hongXunAssembleCompilation.getInQuantity2()==null){
				hongXunAssembleCompilation.setInQuantity2(instockQuantity);
			}else{
				hongXunAssembleCompilation.setInQuantity2(hongXunAssembleCompilation.getInQuantity2() + instockQuantity);
			}
		}else if(month==3){
			if(hongXunAssembleCompilation.getInQuantity3()==null){
				hongXunAssembleCompilation.setInQuantity3(instockQuantity);
			}else{
				hongXunAssembleCompilation.setInQuantity3(hongXunAssembleCompilation.getInQuantity3() + instockQuantity);
			}
		}else if(month==4){
			if(hongXunAssembleCompilation.getInQuantity4()==null){
				hongXunAssembleCompilation.setInQuantity4(instockQuantity);
			}else{
				hongXunAssembleCompilation.setInQuantity4(hongXunAssembleCompilation.getInQuantity4() + instockQuantity);
			}
		}else if(month==5){
			if(hongXunAssembleCompilation.getInQuantity5()==null){
				hongXunAssembleCompilation.setInQuantity5(instockQuantity);
			}else{
				hongXunAssembleCompilation.setInQuantity5(hongXunAssembleCompilation.getInQuantity5() + instockQuantity);
			}
		}else if(month==6){
			if(hongXunAssembleCompilation.getInQuantity6()==null){
				hongXunAssembleCompilation.setInQuantity6(instockQuantity);
			}else{
				hongXunAssembleCompilation.setInQuantity6(hongXunAssembleCompilation.getInQuantity6() + instockQuantity);
			}
		}else if(month==7){
			if(hongXunAssembleCompilation.getInQuantity7()==null){
				hongXunAssembleCompilation.setInQuantity7(instockQuantity);
			}else{
				hongXunAssembleCompilation.setInQuantity7(hongXunAssembleCompilation.getInQuantity7() + instockQuantity);
			}
		}else if(month==8){
			if(hongXunAssembleCompilation.getInQuantity8()==null){
				hongXunAssembleCompilation.setInQuantity8(instockQuantity);
			}else{
				hongXunAssembleCompilation.setInQuantity8(hongXunAssembleCompilation.getInQuantity8() + instockQuantity);
			}
		}else if(month==9){
			if(hongXunAssembleCompilation.getInQuantity9()==null){
				hongXunAssembleCompilation.setInQuantity9(instockQuantity);
			}else{
				hongXunAssembleCompilation.setInQuantity9(hongXunAssembleCompilation.getInQuantity9() + instockQuantity);
			}
		}else if(month==10){
			if(hongXunAssembleCompilation.getInQuantity10()==null){
				hongXunAssembleCompilation.setInQuantity10(instockQuantity);
			}else{
				hongXunAssembleCompilation.setInQuantity10(hongXunAssembleCompilation.getInQuantity10() + instockQuantity);
			}
		}else if(month==11){
			if(hongXunAssembleCompilation.getInQuantity11()==null){
				hongXunAssembleCompilation.setInQuantity11(instockQuantity);
			}else{
				hongXunAssembleCompilation.setInQuantity11(hongXunAssembleCompilation.getInQuantity11() + instockQuantity);
			}
		}else if(month==12){
			if(hongXunAssembleCompilation.getInQuantity12()==null){
				hongXunAssembleCompilation.setInQuantity12(instockQuantity);
			}else{
				hongXunAssembleCompilation.setInQuantity12(hongXunAssembleCompilation.getInQuantity12() + instockQuantity);
			}
		}else{
			
		}
	}
	
	private void fillOutQuantity(int month, HongXunAssembleCompilation hongXunAssembleCompilation, int outstockQuantity){
		if(month==1){
			if(hongXunAssembleCompilation.getOutQuantity1()==null){
				hongXunAssembleCompilation.setOutQuantity1(outstockQuantity);
			}else{
				hongXunAssembleCompilation.setOutQuantity1(hongXunAssembleCompilation.getOutQuantity1() + outstockQuantity);
			}
		}else if(month==2){
			if(hongXunAssembleCompilation.getOutQuantity2()==null){
				hongXunAssembleCompilation.setOutQuantity2(outstockQuantity);
			}else{
				hongXunAssembleCompilation.setOutQuantity2(hongXunAssembleCompilation.getOutQuantity2() + outstockQuantity);
			}
		}else if(month==3){
			if(hongXunAssembleCompilation.getOutQuantity3()==null){
				hongXunAssembleCompilation.setOutQuantity3(outstockQuantity);
			}else{
				hongXunAssembleCompilation.setOutQuantity3(hongXunAssembleCompilation.getOutQuantity3() + outstockQuantity);
			}
		}else if(month==4){
			if(hongXunAssembleCompilation.getOutQuantity4()==null){
				hongXunAssembleCompilation.setOutQuantity4(outstockQuantity);
			}else{
				hongXunAssembleCompilation.setOutQuantity4(hongXunAssembleCompilation.getOutQuantity4() + outstockQuantity);
			}
		}else if(month==5){
			if(hongXunAssembleCompilation.getOutQuantity5()==null){
				hongXunAssembleCompilation.setOutQuantity5(outstockQuantity);
			}else{
				hongXunAssembleCompilation.setOutQuantity5(hongXunAssembleCompilation.getOutQuantity5() + outstockQuantity);
			}
		}else if(month==6){
			if(hongXunAssembleCompilation.getOutQuantity6()==null){
				hongXunAssembleCompilation.setOutQuantity6(outstockQuantity);
			}else{
				hongXunAssembleCompilation.setOutQuantity6(hongXunAssembleCompilation.getOutQuantity6() + outstockQuantity);
			}
		}else if(month==7){
			if(hongXunAssembleCompilation.getOutQuantity7()==null){
				hongXunAssembleCompilation.setOutQuantity7(outstockQuantity);
			}else{
				hongXunAssembleCompilation.setOutQuantity7(hongXunAssembleCompilation.getOutQuantity7() + outstockQuantity);			
			}
			
		}else if(month==8){
			if(hongXunAssembleCompilation.getOutQuantity8()==null){
				hongXunAssembleCompilation.setOutQuantity8(outstockQuantity);
			}else{
				hongXunAssembleCompilation.setOutQuantity8(hongXunAssembleCompilation.getOutQuantity8() + outstockQuantity);
			}
		}else if(month==9){
			if(hongXunAssembleCompilation.getOutQuantity9()==null){
				hongXunAssembleCompilation.setOutQuantity9(outstockQuantity);
			}else{
				hongXunAssembleCompilation.setOutQuantity9(hongXunAssembleCompilation.getOutQuantity9() + outstockQuantity);
			}
		}else if(month==10){
			if(hongXunAssembleCompilation.getOutQuantity10()==null){
				hongXunAssembleCompilation.setOutQuantity10(outstockQuantity);
			}else{
				hongXunAssembleCompilation.setOutQuantity10(hongXunAssembleCompilation.getOutQuantity10() + outstockQuantity);
			}
		}else if(month==11){
			if(hongXunAssembleCompilation.getOutQuantity11()==null){
				hongXunAssembleCompilation.setOutQuantity11(outstockQuantity);
			}else{
				hongXunAssembleCompilation.setOutQuantity11(hongXunAssembleCompilation.getOutQuantity11() + outstockQuantity);
			}
		}else if(month==12){
			if(hongXunAssembleCompilation.getOutQuantity12()==null){
				hongXunAssembleCompilation.setOutQuantity12(outstockQuantity);
			}else{
				hongXunAssembleCompilation.setOutQuantity12(hongXunAssembleCompilation.getOutQuantity12() + outstockQuantity);
			}
		}else{
			
		}
	}
	
	private void fillOthers(int month, HongXunAssembleCompilation hongXunAssembleCompilation,HongXunProductionStock hongXunProductionStock, int quantity){
		hongXunAssembleCompilation.setMaterialNum(hongXunProductionStock.getMaterialNum());
		hongXunAssembleCompilation.setSpecification(hongXunProductionStock.getSpecification());
		if(month==1){
			hongXunAssembleCompilation.setQuantity1(hongXunProductionStock.getQuantity());
			hongXunAssembleCompilation.setCheckQuantity1(quantity);
			hongXunAssembleCompilation.setDiffQuantity1(hongXunAssembleCompilation.getQuantity1()-hongXunAssembleCompilation.getCheckQuantity1());
		}else if(month==2){
			hongXunAssembleCompilation.setQuantity2(hongXunProductionStock.getQuantity());
			hongXunAssembleCompilation.setCheckQuantity2(quantity);
			hongXunAssembleCompilation.setDiffQuantity2(hongXunAssembleCompilation.getQuantity2()-hongXunAssembleCompilation.getCheckQuantity2());
		}else if(month==3){
			hongXunAssembleCompilation.setQuantity3(hongXunProductionStock.getQuantity());
			hongXunAssembleCompilation.setCheckQuantity3(quantity);
			hongXunAssembleCompilation.setDiffQuantity3(hongXunAssembleCompilation.getQuantity3()-hongXunAssembleCompilation.getCheckQuantity3());
		}else if(month==4){
			hongXunAssembleCompilation.setQuantity4(hongXunProductionStock.getQuantity());
			hongXunAssembleCompilation.setCheckQuantity4(quantity);
			hongXunAssembleCompilation.setDiffQuantity4(hongXunAssembleCompilation.getQuantity4()-hongXunAssembleCompilation.getCheckQuantity4());
		}else if(month==5){
			hongXunAssembleCompilation.setQuantity5(hongXunProductionStock.getQuantity());
			hongXunAssembleCompilation.setCheckQuantity5(quantity);
			hongXunAssembleCompilation.setDiffQuantity5(hongXunAssembleCompilation.getQuantity5()-hongXunAssembleCompilation.getCheckQuantity5());
		}else if(month==6){
			hongXunAssembleCompilation.setQuantity6(hongXunProductionStock.getQuantity());
			hongXunAssembleCompilation.setCheckQuantity6(quantity);
			hongXunAssembleCompilation.setDiffQuantity6(hongXunAssembleCompilation.getQuantity6()-hongXunAssembleCompilation.getCheckQuantity6());
		}else if(month==7){
			hongXunAssembleCompilation.setQuantity7(hongXunProductionStock.getQuantity());
			hongXunAssembleCompilation.setCheckQuantity7(quantity);
			hongXunAssembleCompilation.setDiffQuantity7(hongXunAssembleCompilation.getQuantity7()-hongXunAssembleCompilation.getCheckQuantity7());
		}else if(month==8){
			hongXunAssembleCompilation.setQuantity8(hongXunProductionStock.getQuantity());
			hongXunAssembleCompilation.setCheckQuantity8(quantity);
			hongXunAssembleCompilation.setDiffQuantity8(hongXunAssembleCompilation.getQuantity8()-hongXunAssembleCompilation.getCheckQuantity8());
		}else if(month==9){
			hongXunAssembleCompilation.setQuantity9(hongXunProductionStock.getQuantity());
			hongXunAssembleCompilation.setCheckQuantity9(quantity);
			hongXunAssembleCompilation.setDiffQuantity9(hongXunAssembleCompilation.getQuantity9()-hongXunAssembleCompilation.getCheckQuantity9());
		}else if(month==10){
			hongXunAssembleCompilation.setQuantity10(hongXunProductionStock.getQuantity());
			hongXunAssembleCompilation.setCheckQuantity10(quantity);
			hongXunAssembleCompilation.setDiffQuantity10(hongXunAssembleCompilation.getQuantity10()-hongXunAssembleCompilation.getCheckQuantity10());
		}else if(month==11){
			hongXunAssembleCompilation.setQuantity11(hongXunProductionStock.getQuantity());
			hongXunAssembleCompilation.setCheckQuantity11(quantity);
			hongXunAssembleCompilation.setDiffQuantity11(hongXunAssembleCompilation.getQuantity11()-hongXunAssembleCompilation.getCheckQuantity11());
		}else if(month==12){
			hongXunAssembleCompilation.setQuantity12(hongXunProductionStock.getQuantity());
			hongXunAssembleCompilation.setCheckQuantity12(quantity);
			hongXunAssembleCompilation.setDiffQuantity12(hongXunAssembleCompilation.getQuantity12()-hongXunAssembleCompilation.getCheckQuantity12());
		}
	}
*/
	@Override
	public List<Map<String, Object>> xlf(List<HongXunAssembleCompilation> hongXunAssembleCompilationList,String date) {
		// TODO Auto-generated method stub
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
		List<HongXunAssembleCompilation> assembleCompilations = new ArrayList<HongXunAssembleCompilation>();
		//导入的盘点表，先在系统的盘点表中查一遍
		for(HongXunAssembleCompilation item: hongXunAssembleCompilationList){
/*			HongXunAssembleCompilation hongXunAssembleCompilation = new HongXunAssembleCompilation();
			hongXunAssembleCompilation.setMaterialNum(item.getMaterialNum());
			hongXunAssembleCompilation.setSpecification(item.getSpecification());
			List<HongXunAssembleCompilation> hongXunAssembleCompilations = stockDao.quary(hongXunAssembleCompilation);
			//System.out.println("hongXunAssembleCompilations.size(): " + hongXunAssembleCompilations.size());
			if(hongXunAssembleCompilations.size()==1){
				hongXunAssembleCompilation = hongXunAssembleCompilations.get(0);
			}else if(hongXunAssembleCompilations.size()>1){	
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("error", "物料号:"+ item.getMaterialNum() + " 规格:" + item.getSpecification() +"->盘点表有重复记录");
				list.add(map);
				return list;
			}*/
	
	
			HongXunProductionStock hongXunProductionStock = iProductionDao.productionFindById(item.getIdc());
			//System.out.println("hongXunProductionStocks.size(): " + hongXunProductionStocks.size());
			if(hongXunProductionStock != null){
				HongXunAssembleCompilation hongXunAssembleCompilation = new HongXunAssembleCompilation();
				hongXunAssembleCompilation.setStockId(hongXunProductionStock.getIdc());
				List<HongXunAssembleCompilation> hongXunAssembleCompilations = iProductionCompilationDao.quary(hongXunAssembleCompilation);
				if(hongXunAssembleCompilations.size()==1){
					hongXunAssembleCompilation = hongXunAssembleCompilations.get(0);
				}
				fillmsg(month, hongXunAssembleCompilation, item.getMaterialNum(), hongXunProductionStock, item.getCheckQuantity1());	
				assembleCompilations.add(hongXunAssembleCompilation);
			}else {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("error", "物料号:"+ item.getMaterialNum() + " 规格:" + item.getSpecification() + "->装配仓无入库记录");
				list.add(map);
				return list;
			}/*else{
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("error", "物料号:"+ item.getMaterialNum() + " 规格:" + item.getSpecification() + "->装配仓有重复料号规格，请联系工程师");
				list.add(map);
				return list;
			}*/
			
		}
		//System.out.println("assembleCompilations.size(): " + assembleCompilations.size());
		for(HongXunAssembleCompilation item: assembleCompilations){
			if(item.getIdc()==0){
				iProductionCompilationDao.save(item);
			}else{
				iProductionCompilationDao.update(item);
			}
			Map<String,Object> map = new HashMap<String,Object>();
			mapHongXunMaterialCompilation(map,item);
			list.add(map);
		}
		return list;
	}


}
