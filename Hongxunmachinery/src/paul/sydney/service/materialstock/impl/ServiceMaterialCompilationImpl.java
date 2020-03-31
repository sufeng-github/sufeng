package paul.sydney.service.materialstock.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import paul.sydney.commen.result.PageResults;
import paul.sydney.dao.IMaterialCompilationDao;
import paul.sydney.dao.IMaterialStockDao;
import paul.sydney.dao.StockDao;
import paul.sydney.model.HongXunMaterialCompilation;
import paul.sydney.model.HongXunMaterialStock;
import paul.sydney.service.materialstock.ServiceMaterialCompilationInf;
import paul.sydney.service.utils.IServiceUtilsInf;
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
	@Autowired
	IServiceUtilsInf iServiceUtilsInf;
	public void setStockService(IServiceUtilsInf iServiceUtilsInf) {
		this.iServiceUtilsInf = iServiceUtilsInf;
	}

	@Override
	public PageResults<HongXunMaterialCompilation> getData(HongXunMaterialCompilation hongXunMaterialCompilation,
			Integer pageNo, Integer pageSize) {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<String>();
		StringBuilder hql=new StringBuilder("from HongXunMaterialCompilation where 1=1");
		//String类型属性
		if (hongXunMaterialCompilation.getMaterialNum() != null) {
			if(hongXunMaterialCompilation.getMaterialNum().indexOf("*")>-1){
				hql.append(" and materialNum like ?");
				list.add("%" + hongXunMaterialCompilation.getMaterialNum().replace("*", "") + "%");				
			}else{
				hql.append(" and materialNum = ?");
				list.add(hongXunMaterialCompilation.getMaterialNum());
			}
		}
		if (hongXunMaterialCompilation.getSpecification() != null) {
			if(hongXunMaterialCompilation.getSpecification().indexOf("*")>-1){
				hql.append(" and specification like ?");
				list.add("%" + hongXunMaterialCompilation.getSpecification().replace("*", "") + "%");				
			}else{
				hql.append(" and specification =?");
				list.add(hongXunMaterialCompilation.getSpecification());
			}
		}
		return iMaterialCompilationDao.findPageByFetchedHql(hql.toString(), "select count(*) " + hql.toString(), pageNo, pageSize,list.toArray());
	}	
	
	@Override
	public List<Map<String, Object>> getEntity() {
		// TODO Auto-generated method stub
		return iMaterialCompilationDao.getEntity();
	}

	@Override
	public void synchronization() {
		// TODO Auto-generated method stub
		StringBuilder hql = new StringBuilder("from HongXunMaterialCompilation where 1=1");
		List<HongXunMaterialCompilation> hongXunMaterialCompilations = iMaterialCompilationDao
				.getListByHQL(hql.toString());
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int month = cal.get(Calendar.MONTH) + 1;
		for (HongXunMaterialCompilation item : hongXunMaterialCompilations) {
			HongXunMaterialStock hongXunMaterialStock = iMaterialStockDao.get(item.getIdc());
			if (month == 1) {
				if (item.getCheckQuantity1() != null) {
					hongXunMaterialStock.setQuantity(item.getCheckQuantity1());
				}
			} else if (month == 2) {
				if (item.getCheckQuantity1() != null) {
					hongXunMaterialStock.setQuantity(item.getCheckQuantity2());
				}
			} else if (month == 3) {
				if (item.getCheckQuantity1() != null) {
					hongXunMaterialStock.setQuantity(item.getCheckQuantity3());
				}
			} else if (month == 4) {
				if (item.getCheckQuantity1() != null) {
					hongXunMaterialStock.setQuantity(item.getCheckQuantity4());
				}
			} else if (month == 5) {
				if (item.getCheckQuantity1() != null) {
					hongXunMaterialStock.setQuantity(item.getCheckQuantity5());
				}
			} else if (month == 6) {
				if (item.getCheckQuantity1() != null) {
					hongXunMaterialStock.setQuantity(item.getCheckQuantity6());
				}
			} else if (month == 7) {
				if (item.getCheckQuantity1() != null) {
					hongXunMaterialStock.setQuantity(item.getCheckQuantity7());
				}
			} else if (month == 8) {
				if (item.getCheckQuantity1() != null) {
					hongXunMaterialStock.setQuantity(item.getCheckQuantity8());
				}
			} else if (month == 9) {
				if (item.getCheckQuantity1() != null) {
					hongXunMaterialStock.setQuantity(item.getCheckQuantity9());
				}
			} else if (month == 10) {
				if (item.getCheckQuantity1() != null) {
					hongXunMaterialStock.setQuantity(item.getCheckQuantity10());
				}
			} else if (month == 11) {
				if (item.getCheckQuantity1() != null) {
					hongXunMaterialStock.setQuantity(item.getCheckQuantity11());
				}
			} else if (month == 12) {
				if (item.getCheckQuantity1() != null) {
					hongXunMaterialStock.setQuantity(item.getCheckQuantity12());
				}
			}
			hongXunMaterialStock.setInQuantity(0);
			hongXunMaterialStock.setOutQuantity(0);
			iMaterialStockDao.updateEntity(hongXunMaterialStock);
		}
	}
	

	@Override
	public void saveRow(List<HongXunMaterialCompilation> list) {
		// TODO Auto-generated method stub		
		for(HongXunMaterialCompilation item: list){	
			HongXunMaterialStock hongXunMaterialStock = iMaterialStockDao.get(item.getIdc());
			if (hongXunMaterialStock.getHongXunMaterialCompilation() != null) {
				HongXunMaterialCompilation hongXunMaterialCompilation = hongXunMaterialStock.getHongXunMaterialCompilation();
				reflectionAttr(item,hongXunMaterialCompilation);
				iMaterialCompilationDao.updateEntity(hongXunMaterialCompilation);
			} else {
				hongXunMaterialStock.setHongXunMaterialCompilation(item);
				item.setHongXunMaterialStock(hongXunMaterialStock);
				iMaterialCompilationDao.saveEntity(item);
			}
		}
	}

	public void reflectionAttr(HongXunMaterialCompilation source,HongXunMaterialCompilation target){
		
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
