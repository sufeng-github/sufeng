package paul.sydney.service.materialstock.impl;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import paul.sydney.dao.IMaterialCompilationDao;
import paul.sydney.dao.IMaterialStockDao;
import paul.sydney.dao.StockDao;
import paul.sydney.dao.impl.MaterialStockDao;
import paul.sydney.model.HongXunMaterialCompilation;
import paul.sydney.model.HongXunMaterialStock;
import paul.sydney.service.materialstock.ServiceMaterialCompilationInf;
@Transactional
@Service("serviceMaterialCompliation")
public class ServiceMaterialCompilationImpl implements ServiceMaterialCompilationInf{
	
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
	IMaterialCompilationDao iMaterialCompilationDao;
	public void setStockDao(IMaterialCompilationDao iMaterialCompilationDao) {
		this.iMaterialCompilationDao = iMaterialCompilationDao;
	}
	@Override
	public List<Map<String, Object>> loadData() {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		List<HongXunMaterialCompilation>  hongXunMaterialCompilationList = iMaterialCompilationDao.quary(new HongXunMaterialCompilation());
		//List<HongXunMaterialCompilation>  hongXunMaterialCompilationList = new ArrayList<HongXunMaterialCompilation>();
		//List<HongXunMaterialStock>  hongXunMaterialStocks = iMaterialStockDao.quary(new HongXunMaterialStock());
		
/*		for(HongXunMaterialStock item : hongXunMaterialStocks){
			HongXunMaterialCompilation hongXunMaterialCompilation = new HongXunMaterialCompilation();
			hongXunMaterialCompilation.setMaterialNum(item.getMaterialNum());
			List<HongXunMaterialCompilation> hongXunMaterialCompilations = iMaterialStockDao.quary(hongXunMaterialCompilation);
			if(hongXunMaterialCompilations.size()>0){
				hongXunMaterialCompilationList.add(hongXunMaterialCompilations.get(0));
			}else{
				hongXunMaterialCompilation.setMaterialNum(item.getMaterialNum());
				hongXunMaterialCompilation.setSpecification(item.getSpecification());
				hongXunMaterialCompilationList.add(hongXunMaterialCompilation);
			}
		}*/
		for(HongXunMaterialCompilation item : hongXunMaterialCompilationList){
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
		HongXunMaterialCompilation e = new HongXunMaterialCompilation();
		Class<? extends HongXunMaterialCompilation> cls = e.getClass();  
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
	
	private void mapHongXunMaterialCompilation(Map<String, Object> map, HongXunMaterialCompilation item) {
		// TODO Auto-generated method stub	
		//System.out.println("test: " + item.getHongXunDataOne().getProductionOrderNum());
		map.put("idc", item.getIdc());
		if(item.getStockId() != null){
			HongXunMaterialStock hongXunMaterialStock = iMaterialStockDao.hongXunMaterialFindById(item.getStockId());
			if(hongXunMaterialStock != null){
				map.put("materialNum", hongXunMaterialStock.getMaterialNum());
				map.put("specification", hongXunMaterialStock.getSpecification());
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
		List<HongXunMaterialCompilation> HongXunMaterialCompilations = (List<HongXunMaterialCompilation>) iMaterialStockDao.quaryFuzzyWithpara("HongXunMaterialCompilation", "materialNum", str); 	
		for(HongXunMaterialCompilation item: HongXunMaterialCompilations){	
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
		List<HongXunMaterialCompilation> HongXunMaterialCompilations = (List<HongXunMaterialCompilation>) stockDao.quaryFuzzyWithpara("HongXunMaterialCompilation", "materialNum", materialNum);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (HongXunMaterialCompilations != null) {
			for (HongXunMaterialCompilation item : HongXunMaterialCompilations) {
				Map<String, Object> map = new HashMap<String, Object>();
				mapHongXunMaterialCompilation(map,item);
				list.add(map);
			}
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> saveRow(HongXunMaterialCompilation HongXunMaterialCompilation) {
		// TODO Auto-generated method stub
		iMaterialCompilationDao.save(HongXunMaterialCompilation);
		Map<String,Object> map = new HashMap<String,Object>();
		mapHongXunMaterialCompilation(map, HongXunMaterialCompilation);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.add(map);
		return list;
	}

	@Override
	public List<Map<String, Object>> updateRow(HongXunMaterialCompilation HongXunMaterialCompilation) {
		// TODO Auto-generated method stub
		iMaterialCompilationDao.update(HongXunMaterialCompilation);
		Map<String,Object> map = new HashMap<String,Object>();
		mapHongXunMaterialCompilation(map, HongXunMaterialCompilation);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.add(map);
		return list;
	}

	@Override
	public List<Map<String, Object>> getMaterialAlarms(String alarm) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		@SuppressWarnings("unchecked")
		List<HongXunMaterialCompilation> HongXunMaterialCompilations = (List<HongXunMaterialCompilation>) stockDao.quaryFuzzyWithpara("HongXunMaterialCompilation", "alarm", alarm);
		for(HongXunMaterialCompilation item : HongXunMaterialCompilations){
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
		HongXunMaterialStock hongXunMaterialStock = new HongXunMaterialStock();
		hongXunMaterialStock.setMaterialNum(materialNum);
		if(specification !=null){
			hongXunMaterialStock.setSpecification(specification);
		}
		List<HongXunMaterialStock> hongXunMaterialStocks = iMaterialStockDao.quary(hongXunMaterialStock);
		if(hongXunMaterialStocks.size()==0 || hongXunMaterialStocks.size()==1){
			if(hongXunMaterialStocks.size()==0){			
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("error", "物料号:"+ materialNum +"->原料仓库无记录");
				list.add(map);
				return list;				
	    	}else if(hongXunMaterialStocks.size()==1){
	    		HongXunMaterialCompilation hongXunMaterialCompilation = new HongXunMaterialCompilation();
	    		hongXunMaterialCompilation.setStockId(hongXunMaterialStocks.get(0).getIdc());
	    		List<HongXunMaterialCompilation> hongXunMaterialCompilations = iMaterialCompilationDao.quary(hongXunMaterialCompilation);
	    		Calendar cal = Calendar.getInstance();
	    		cal.setTime(new Date());
	    		int month = cal.get(Calendar.MONTH) + 1;
	    		if(hongXunMaterialCompilations.size()==0){
	    			fillmsg(month, hongXunMaterialCompilation,  hongXunMaterialStock, quantity);	
	    		}else if(hongXunMaterialCompilations.size()==1){
	    			hongXunMaterialCompilation = hongXunMaterialCompilations.get(0);
	    			if(month==1){ 
	    				int checkQuantity = hongXunMaterialCompilation.getCheckQuantity1();
	    				int diffQuantity = checkQuantity - quantity;
	    				hongXunMaterialCompilation.setDiffQuantity1(hongXunMaterialCompilation.getDiffQuantity1()+diffQuantity);
	    				hongXunMaterialCompilation.setCheckQuantity1(quantity);
	    			}else if(month==2){ 
	    				int checkQuantity = hongXunMaterialCompilation.getCheckQuantity2();
	    				int diffQuantity = checkQuantity - quantity;
	    				hongXunMaterialCompilation.setDiffQuantity2(hongXunMaterialCompilation.getDiffQuantity2()+diffQuantity);
	    				hongXunMaterialCompilation.setCheckQuantity2(quantity);
	    			}else if(month==3){ 
	    				int checkQuantity = hongXunMaterialCompilation.getCheckQuantity3();
	    				int diffQuantity = checkQuantity - quantity;
	    				hongXunMaterialCompilation.setDiffQuantity3(hongXunMaterialCompilation.getDiffQuantity3()+diffQuantity);
	    				hongXunMaterialCompilation.setCheckQuantity3(quantity);
	    			}else if(month==4){ 
	    				int checkQuantity = hongXunMaterialCompilation.getCheckQuantity4();
	    				int diffQuantity = checkQuantity - quantity;
	    				hongXunMaterialCompilation.setDiffQuantity4(hongXunMaterialCompilation.getDiffQuantity4()+diffQuantity);
	    				hongXunMaterialCompilation.setCheckQuantity4(quantity);
	    			}else if(month==5){ 
	    				int checkQuantity = hongXunMaterialCompilation.getCheckQuantity5();
	    				int diffQuantity = checkQuantity - quantity;
	    				hongXunMaterialCompilation.setDiffQuantity5(hongXunMaterialCompilation.getDiffQuantity5()+diffQuantity);
	    				hongXunMaterialCompilation.setCheckQuantity5(quantity);
	    			}else if(month==6){ 
	    				int checkQuantity = hongXunMaterialCompilation.getCheckQuantity6();
	    				int diffQuantity = checkQuantity - quantity;
	    				hongXunMaterialCompilation.setDiffQuantity6(hongXunMaterialCompilation.getDiffQuantity6()+diffQuantity);
	    				hongXunMaterialCompilation.setCheckQuantity6(quantity);
	    			}else if(month==7){ 
	    				int checkQuantity = hongXunMaterialCompilation.getCheckQuantity7();
	    				int diffQuantity = checkQuantity - quantity;
	    				hongXunMaterialCompilation.setDiffQuantity7(hongXunMaterialCompilation.getDiffQuantity7()+diffQuantity);
	    				hongXunMaterialCompilation.setCheckQuantity7(quantity);
	    			}else if(month==8){ 
	    				int checkQuantity = hongXunMaterialCompilation.getCheckQuantity8();
	    				int diffQuantity = checkQuantity - quantity;
	    				hongXunMaterialCompilation.setDiffQuantity8(hongXunMaterialCompilation.getDiffQuantity8()+diffQuantity);
	    				hongXunMaterialCompilation.setCheckQuantity8(quantity);
	    			}else if(month==9){ 
	    				int checkQuantity = hongXunMaterialCompilation.getCheckQuantity9();
	    				int diffQuantity = checkQuantity - quantity;
	    				hongXunMaterialCompilation.setDiffQuantity9(hongXunMaterialCompilation.getDiffQuantity9()+diffQuantity);
	    				hongXunMaterialCompilation.setCheckQuantity9(quantity);
	    			}else if(month==10){ 
	    				int checkQuantity = hongXunMaterialCompilation.getCheckQuantity10();
	    				int diffQuantity = checkQuantity - quantity;
	    				hongXunMaterialCompilation.setDiffQuantity10(hongXunMaterialCompilation.getDiffQuantity10()+diffQuantity);
	    				hongXunMaterialCompilation.setCheckQuantity10(quantity);
	    			}else if(month==11){ 
	    				int checkQuantity = hongXunMaterialCompilation.getCheckQuantity11();
	    				int diffQuantity = checkQuantity - quantity;
	    				hongXunMaterialCompilation.setDiffQuantity11(hongXunMaterialCompilation.getDiffQuantity11()+diffQuantity);
	    				hongXunMaterialCompilation.setCheckQuantity11(quantity);
	    			}else if(month==12){ 
	    				int checkQuantity = hongXunMaterialCompilation.getCheckQuantity12();
	    				int diffQuantity = checkQuantity - quantity;
	    				hongXunMaterialCompilation.setDiffQuantity12(hongXunMaterialCompilation.getDiffQuantity12()+diffQuantity);
	    				hongXunMaterialCompilation.setCheckQuantity12(quantity);
	    			}
	    		}else if(hongXunMaterialCompilations.size()>1){
	    			Map<String, Object> map = new HashMap<String, Object>();
	    			map.put("error", "物料号:"+ materialNum +"->指向多个盘点数据,请联系工程师");
	    			list.add(map);
	    			return list;
	    		}
							   		
		    	if(hongXunMaterialCompilation.getIdc() == 0){
		    		iMaterialCompilationDao.save(hongXunMaterialCompilation);	    			
		    	}else{
		    	    iMaterialCompilationDao.update(hongXunMaterialCompilation);	
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
		List<HongXunMaterialCompilation> hongXunMaterialCompilations = iMaterialCompilationDao.quary(new HongXunMaterialCompilation());
		List<HongXunMaterialStock> list = new ArrayList<HongXunMaterialStock>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int month = cal.get(Calendar.MONTH) + 1;
		for(HongXunMaterialCompilation item: hongXunMaterialCompilations){
			HongXunMaterialStock hongXunMaterialStock = iMaterialStockDao.hongXunMaterialFindById(item.getStockId());
    		if(hongXunMaterialStock != null){
			//@SuppressWarnings("unchecked")
    		//List<HongXunMaterialStock> hongXunMaterialStocks = (List<HongXunMaterialStock>) iMaterialStockDao.quarywithpara("HongXunMaterialStock", "materialNum", item.getMaterialNum()); 
    		//if(hongXunMaterialStocks.size()==1){
    			if(month==1){
    				hongXunMaterialStock.setQuantity(item.getCheckQuantity1());
    			}else if(month==2){
    				hongXunMaterialStock.setQuantity(item.getCheckQuantity2());
    			}else if(month==3){
    				hongXunMaterialStock.setQuantity(item.getCheckQuantity3());
    			}else if(month==4){
    				hongXunMaterialStock.setQuantity(item.getCheckQuantity4());
    			}else if(month==5){
    				hongXunMaterialStock.setQuantity(item.getCheckQuantity5());
    			}else if(month==6){
    				hongXunMaterialStock.setQuantity(item.getCheckQuantity6());
    			}else if(month==7){
    				hongXunMaterialStock.setQuantity(item.getCheckQuantity7());
    			}else if(month==8){
    				hongXunMaterialStock.setQuantity(item.getCheckQuantity8());
    			}else if(month==9){
    				hongXunMaterialStock.setQuantity(item.getCheckQuantity9());
    			}else if(month==10){
    				hongXunMaterialStock.setQuantity(item.getCheckQuantity10());
    			}else if(month==11){
    				hongXunMaterialStock.setQuantity(item.getCheckQuantity11());
    			}else if(month==12){
    				hongXunMaterialStock.setQuantity(item.getCheckQuantity12());
    			}
    			hongXunMaterialStock.setInQuantity(0);
    			hongXunMaterialStock.setOutQuantity(0);
    			list.add(hongXunMaterialStock);
    		}/*else if(hongXunMaterialStocks.size()>1){
    			System.out.println("发现物料号有重复");
    		}*/
		}
		for(HongXunMaterialStock item: list){
			iMaterialStockDao.update(item);
		}
		
		return new ArrayList<Map<String,Object>>();
	}
	/*    	}else if(hongXunMaterialCompilations.size()==0){
	hongXunMaterialCompilation = new HongXunMaterialCompilation(); 
	@SuppressWarnings("unchecked")
	List<HongXunMaterialStock> hongXunMaterialStocks = (List<HongXunMaterialStock>) iMaterialStockDao.quarywithpara("HongXunMaterialStock", "materialNum", materialNum); 
	if(hongXunMaterialStocks.size()==1){
		fillmsg(month, hongXunMaterialCompilation, materialNum, hongXunMaterialStocks.get(0), quantity);		
	}else if(hongXunMaterialStocks.size()==0){
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
	
	private void fillmsg(int month, HongXunMaterialCompilation hongXunMaterialCompilation, HongXunMaterialStock hongXunMaterialStock, int quantity){
		if(hongXunMaterialStock.getInQuantity()==null){
			hongXunMaterialStock.setInQuantity(0);
		}
		if(hongXunMaterialStock.getOutQuantity()==null){
			hongXunMaterialStock.setOutQuantity(0);
		}
		if(hongXunMaterialStock.getQuantity() == null){
			hongXunMaterialStock.setQuantity(0);
		}
		int checkquantity = hongXunMaterialStock.getQuantity() + hongXunMaterialStock.getInQuantity() - hongXunMaterialStock.getOutQuantity() - quantity;
		//int checkquantity = 0;
		if(month==1){
			hongXunMaterialCompilation.setInQuantity1(0);
			hongXunMaterialCompilation.setOutQuantity1(0);
			hongXunMaterialCompilation.setQuantity1(hongXunMaterialStock.getQuantity());
			hongXunMaterialCompilation.setCheckQuantity1(quantity);
			hongXunMaterialCompilation.setDiffQuantity1(checkquantity);
		}else if(month==2){
			hongXunMaterialCompilation.setInQuantity2(0);
			hongXunMaterialCompilation.setOutQuantity2(0);
			hongXunMaterialCompilation.setQuantity2(hongXunMaterialStock.getQuantity());
			hongXunMaterialCompilation.setCheckQuantity2(quantity);
			hongXunMaterialCompilation.setDiffQuantity2(checkquantity);
		}else if(month==3){
			hongXunMaterialCompilation.setInQuantity3(0);
			hongXunMaterialCompilation.setOutQuantity3(0);
			hongXunMaterialCompilation.setQuantity3(hongXunMaterialStock.getQuantity());
			hongXunMaterialCompilation.setCheckQuantity3(quantity);
			hongXunMaterialCompilation.setDiffQuantity3(checkquantity);
		}else if(month==4){
			hongXunMaterialCompilation.setInQuantity4(0);
			hongXunMaterialCompilation.setOutQuantity4(0);
			hongXunMaterialCompilation.setQuantity4(hongXunMaterialStock.getQuantity());
			hongXunMaterialCompilation.setCheckQuantity4(quantity);
			hongXunMaterialCompilation.setDiffQuantity4(checkquantity);
		}else if(month==5){
			hongXunMaterialCompilation.setInQuantity5(0);
			hongXunMaterialCompilation.setOutQuantity5(0);
			hongXunMaterialCompilation.setQuantity5(hongXunMaterialStock.getQuantity());
			hongXunMaterialCompilation.setCheckQuantity5(quantity);
			hongXunMaterialCompilation.setDiffQuantity5(checkquantity);
		}else if(month==6){
			hongXunMaterialCompilation.setInQuantity6(0);
			hongXunMaterialCompilation.setOutQuantity6(0);
			hongXunMaterialCompilation.setQuantity6(hongXunMaterialStock.getQuantity());
			hongXunMaterialCompilation.setCheckQuantity6(quantity);
			hongXunMaterialCompilation.setDiffQuantity6(checkquantity);
		}else if(month==7){
			hongXunMaterialCompilation.setInQuantity7(0);
			hongXunMaterialCompilation.setOutQuantity7(0);
			hongXunMaterialCompilation.setQuantity7(hongXunMaterialStock.getQuantity());
			hongXunMaterialCompilation.setCheckQuantity7(quantity);
			hongXunMaterialCompilation.setDiffQuantity7(checkquantity);
		}else if(month==8){
			hongXunMaterialCompilation.setInQuantity8(0);
			hongXunMaterialCompilation.setOutQuantity8(0);
			hongXunMaterialCompilation.setQuantity8(hongXunMaterialStock.getQuantity());
			hongXunMaterialCompilation.setCheckQuantity8(quantity);
			hongXunMaterialCompilation.setDiffQuantity8(checkquantity);
		}else if(month==9){
			hongXunMaterialCompilation.setInQuantity9(0);
			hongXunMaterialCompilation.setOutQuantity9(0);
			hongXunMaterialCompilation.setQuantity9(hongXunMaterialStock.getQuantity());
			hongXunMaterialCompilation.setCheckQuantity9(quantity);
			hongXunMaterialCompilation.setDiffQuantity9(checkquantity);
		}else if(month==10){
			hongXunMaterialCompilation.setInQuantity10(0);
			hongXunMaterialCompilation.setOutQuantity10(0);
			hongXunMaterialCompilation.setQuantity10(hongXunMaterialStock.getQuantity());
			hongXunMaterialCompilation.setCheckQuantity10(quantity);
			hongXunMaterialCompilation.setDiffQuantity10(checkquantity);
		}else if(month==11){
			hongXunMaterialCompilation.setInQuantity11(0);
			hongXunMaterialCompilation.setOutQuantity11(0);
			hongXunMaterialCompilation.setQuantity11(hongXunMaterialStock.getQuantity());
			hongXunMaterialCompilation.setCheckQuantity11(quantity);
			hongXunMaterialCompilation.setDiffQuantity11(checkquantity);
		}else if(month==12){
			hongXunMaterialCompilation.setInQuantity12(0);
			hongXunMaterialCompilation.setOutQuantity12(0);
			hongXunMaterialCompilation.setQuantity12(hongXunMaterialStock.getQuantity());
			hongXunMaterialCompilation.setCheckQuantity12(quantity);
			hongXunMaterialCompilation.setDiffQuantity12(checkquantity);
		}else{
			
		}	
	}
	

	
	@Override
	public List<Map<String, Object>> xlf(List<HongXunMaterialCompilation> hongXunMaterialCompilationList, String date) {
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
		
		System.out.println("month:"+month);
		//System.out.println("hongXunMaterialCompilationList.size(): " + hongXunMaterialCompilationList.size());
		List<HongXunMaterialCompilation> materialCompilations = new ArrayList<HongXunMaterialCompilation>();
		for(HongXunMaterialCompilation item: hongXunMaterialCompilationList){
/*			HongXunMaterialCompilation hongXunMaterialCompilation = new HongXunMaterialCompilation();
			hongXunMaterialCompilation.setMaterialNum(item.getMaterialNum());
			List<HongXunMaterialCompilation> hongXunMaterialCompilations = iMaterialStockDao.quary(hongXunMaterialCompilation);
			if(hongXunMaterialCompilations.size()>0){
				hongXunMaterialCompilation = hongXunMaterialCompilations.get(0);
			}else{
				hongXunMaterialCompilation.setSpecification(item.getSpecification());
			}
			@SuppressWarnings("unchecked")
			List<HongXunMaterialStock> hongXunMaterialStocks = (List<HongXunMaterialStock>) iMaterialStockDao.quarywithpara("HongXunMaterialStock", "materialNum", item.getMaterialNum()); 
			*/
			HongXunMaterialStock hongXunMaterialStock = iMaterialStockDao.hongXunMaterialFindById(item.getIdc());
			//System.out.println("hongXunMaterialStocks.size(): " + hongXunMaterialStocks.size());
			if(hongXunMaterialStock != null){
			//if(hongXunMaterialStocks.size()==1){
				HongXunMaterialCompilation hongXunMaterialCompilation = new HongXunMaterialCompilation();
				hongXunMaterialCompilation.setStockId(hongXunMaterialStock.getIdc());
				List<HongXunMaterialCompilation> hongXunMaterialCompilations = iMaterialCompilationDao.quary(hongXunMaterialCompilation);
				if(hongXunMaterialCompilations.size()==1){
					hongXunMaterialCompilation = hongXunMaterialCompilations.get(0);
				}
				fillmsg(month, hongXunMaterialCompilation,  hongXunMaterialStock,item.getCheckQuantity1());
				materialCompilations.add(hongXunMaterialCompilation);
			}else {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("error", "物料号:"+ item.getMaterialNum() +"->原料仓无入库记录");
				list.add(map);
				return list;
			}/*else{
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("error", "物料号:"+ item.getMaterialNum() +"->原料仓有重复料号，请联系工程师");
				list.add(map);
				return list;
			}*/
			
		}
		for(HongXunMaterialCompilation item: materialCompilations){
			if(item.getIdc()==0){
				iMaterialCompilationDao.save(item);
			}else{
				iMaterialCompilationDao.update(item);
			}
			Map<String,Object> map = new HashMap<String,Object>();
			mapHongXunMaterialCompilation(map,item);
			list.add(map);
		}
		return list;
		

	}
	
	/*@Override
	public List<Map<String, Object>> compilationImport(HSSFWorkbook workbook) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<String> thList = new ArrayList<String>();
		List<HongXunMaterialCompilation> hongXunMaterialCompilations = new ArrayList<HongXunMaterialCompilation>();
		try {
			HSSFSheet sheet = workbook.getSheetAt(0);
			int count = 0;
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			int month = cal.get(Calendar.MONTH) + 1;
			for (Row row : sheet) {
				String specification = "";
				String materialNum = null;
				int quantity = 0;
				//int checkQuantity = 0;
				if (row.getCell(0) == null || row.getCell(0).toString().equals("")) {
					break;
				}
				int end = row.getLastCellNum();				
				for (int i = 0; i < end; i++) {
					Cell cell = row.getCell(i);
					Object obj = getValue(cell);
					if (obj != null) {
						// System.out.print(obj.toString() + "\t");
						if (count < 1) {
							thList.add(obj.toString());
						} else {
							// System.out.println(list.size() + ":" + i);
							String temStr = thList.get(i);
							if (!temStr.equals("")) {
								// System.out.println(temStr);
								if (temStr.equals("物料编码")) {
									materialNum = (obj.toString());
								} else if (temStr.equals("型号/规格")) {
									specification = (obj.toString());
								} else if (temStr.equals("库存量")) {
									if (isNum(obj.toString())) {
										quantity = Double.valueOf(obj.toString()).intValue();				
									}
								} else if (temStr.equals("盘点量")) {								
									if (isNum(obj.toString())) {
										quantity = Double.valueOf(obj.toString()).intValue();					
									}
								}
							}
						}
					}
				}
				if (count < 1) {
					// System.out.println("" + row.getLastCellNum());
					count++;
				} else {
					HongXunMaterialCompilation hongXunMaterialCompilation = new HongXunMaterialCompilation();
					hongXunMaterialCompilation.setMaterialNum(materialNum);
					List<HongXunMaterialCompilation> hongXunMaterialCompilationList = stockDao.quary(hongXunMaterialCompilation);
					if(hongXunMaterialCompilationList.size()>0){
						hongXunMaterialCompilation = hongXunMaterialCompilationList.get(0);
					}else{
						hongXunMaterialCompilation.setSpecification(specification);
					}
					
		    		@SuppressWarnings("unchecked")
		    		List<HongXunMaterialStock> hongXunMaterialStocks = (List<HongXunMaterialStock>) stockDao.quarywithpara("HongXunMaterialStock", "materialNum", materialNum); 
		    		if(hongXunMaterialStocks.size()==1){
		    			fillmsg(month, hongXunMaterialCompilation, materialNum, hongXunMaterialStocks.get(0),quantity);
		    		}else if(hongXunMaterialStocks.size()==0){
		    			Map<String, Object> map = new HashMap<String, Object>();
		    			map.put("error", "物料号:"+ materialNum +"->原料仓无入库记录");
		    			list.add(map);
		    			return list;
		    		}else{
		    			Map<String, Object> map = new HashMap<String, Object>();
		    			map.put("error", "物料号:"+ materialNum +"->原料仓有重复料号，请联系工程师");
		    			list.add(map);
		    			return list;
		    		}
		    		
					hongXunMaterialCompilations.add(hongXunMaterialCompilation);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(HongXunMaterialCompilation item: hongXunMaterialCompilations){
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
	private boolean isNum(String str) {
		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
	}

}
