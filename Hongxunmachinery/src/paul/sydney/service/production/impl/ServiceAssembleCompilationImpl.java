package paul.sydney.service.production.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import paul.sydney.model.HongXunAssembleCompilation;
import paul.sydney.commen.result.PageResults;
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
	public List<Map<String, Object>> getEntity() {
		// TODO Auto-generated method stub

		return iProductionCompilationDao.getEntity();
	}
	
	
	@Override
	public void saveRow(List<HongXunAssembleCompilation> list) {
		// TODO Auto-generated method stub
		for(HongXunAssembleCompilation item: list){	
			HongXunProductionStock hongXunProductionStock = iProductionDao.get(item.getIdc());
			
			if (hongXunProductionStock.getHongXunAssembleCompilation() != null) {
				HongXunAssembleCompilation hongXunAssembleCompilation = hongXunProductionStock.getHongXunAssembleCompilation();
/*				try {
					iServiceUtilsInf.reflectionAttr(item,hongXunMaterialCompilation);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				reflectionAttr(item,hongXunAssembleCompilation);
				iProductionCompilationDao.updateEntity(hongXunAssembleCompilation);
			} else {
				hongXunProductionStock.setHongXunAssembleCompilation(item);
				item.setHongXunProductionStock(hongXunProductionStock);
				iProductionCompilationDao.saveEntity(item);
			}
		}
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
	
	public void reflectionAttr(HongXunAssembleCompilation source,HongXunAssembleCompilation target){
		target.setQuantity1(source.getQuantity1());
		target.setQuantity2(source.getQuantity2());
		target.setQuantity3(source.getQuantity3());
		target.setQuantity4(source.getQuantity4());
		target.setQuantity5(source.getQuantity5());
		target.setQuantity6(source.getQuantity6());
		target.setQuantity7(source.getQuantity7());
		target.setQuantity8(source.getQuantity8());
		target.setQuantity9(source.getQuantity9());
		target.setQuantity10(source.getQuantity10());
		target.setQuantity11(source.getQuantity11());
		target.setQuantity12(source.getQuantity12());
		target.setCheckQuantity1(source.getCheckQuantity1());
		target.setCheckQuantity2(source.getCheckQuantity2());
		target.setCheckQuantity3(source.getCheckQuantity3());
		target.setCheckQuantity4(source.getCheckQuantity4());
		target.setCheckQuantity5(source.getCheckQuantity5());
		target.setCheckQuantity6(source.getCheckQuantity6());
		target.setCheckQuantity7(source.getCheckQuantity7());
		target.setCheckQuantity8(source.getCheckQuantity8());
		target.setCheckQuantity9(source.getCheckQuantity9());
		target.setCheckQuantity10(source.getCheckQuantity10());
		target.setCheckQuantity11(source.getCheckQuantity11());
		target.setCheckQuantity12(source.getCheckQuantity12());
		target.setDiffQuantity1(source.getDiffQuantity1());
		target.setDiffQuantity2(source.getDiffQuantity2());
		target.setDiffQuantity3(source.getDiffQuantity3());
		target.setDiffQuantity4(source.getDiffQuantity4());
		target.setDiffQuantity5(source.getDiffQuantity5());
		target.setDiffQuantity6(source.getDiffQuantity6());
		target.setDiffQuantity7(source.getDiffQuantity7());
		target.setDiffQuantity8(source.getDiffQuantity8());
		target.setDiffQuantity9(source.getDiffQuantity9());
		target.setDiffQuantity10(source.getDiffQuantity10());
		target.setDiffQuantity11(source.getDiffQuantity11());
		target.setDiffQuantity12(source.getDiffQuantity12());
		target.setInQuantity1(source.getInQuantity1());
		target.setInQuantity2(source.getInQuantity2());
		target.setInQuantity3(source.getInQuantity3());
		target.setInQuantity4(source.getInQuantity4());
		target.setInQuantity5(source.getInQuantity5());
		target.setInQuantity6(source.getInQuantity6());
		target.setInQuantity7(source.getInQuantity7());
		target.setInQuantity8(source.getInQuantity8());
		target.setInQuantity9(source.getInQuantity9());
		target.setInQuantity10(source.getInQuantity10());
		target.setInQuantity11(source.getInQuantity11());
		target.setInQuantity12(source.getInQuantity12());
		target.setOutQuantity1(source.getOutQuantity1());
		target.setOutQuantity2(source.getOutQuantity2());
		target.setOutQuantity3(source.getOutQuantity3());
		target.setOutQuantity4(source.getOutQuantity4());
		target.setOutQuantity5(source.getOutQuantity5());
		target.setOutQuantity6(source.getOutQuantity6());
		target.setOutQuantity7(source.getOutQuantity7());
		target.setOutQuantity8(source.getOutQuantity8());
		target.setOutQuantity9(source.getOutQuantity9());
		target.setOutQuantity10(source.getOutQuantity10());
		target.setOutQuantity11(source.getOutQuantity11());
		target.setOutQuantity12(source.getOutQuantity12());
		target.setRemark1(source.getRemark1());
		target.setRemark2(source.getRemark2());
		target.setRemark3(source.getRemark3());
		target.setRemark4(source.getRemark4());
		target.setRemark5(source.getRemark5());
		target.setRemark6(source.getRemark6());
		target.setRemark7(source.getRemark7());
		target.setRemark8(source.getRemark8());
		target.setRemark9(source.getRemark9());
		target.setRemark10(source.getRemark10());
		target.setRemark11(source.getRemark11());
		target.setRemark12(source.getRemark12());
	}


}
