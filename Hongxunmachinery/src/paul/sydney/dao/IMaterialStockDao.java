package paul.sydney.dao;

import java.util.List;

import paul.sydney.commen.dao.IHibernateBaseUtil;
import paul.sydney.model.HongXunMaterialCompilation;
import paul.sydney.model.HongXunMaterialItemOutStock;
import paul.sydney.model.HongXunMaterialNoLimitInStock;
import paul.sydney.model.HongXunMaterialNoLimitOutStock;
import paul.sydney.model.HongXunMaterialOutStoreItem;
import paul.sydney.model.HongXunMaterialOutStoreNum;
import paul.sydney.model.HongXunMaterialStock;

public interface IMaterialStockDao extends IHibernateBaseUtil<HongXunMaterialStock, Integer>{
	void update(HongXunMaterialStock hongXunMaterialStock);
	void save(HongXunMaterialStock hongXunMaterialStock);
	void delete(HongXunMaterialStock hongXunMaterialStock);
	void deleteMaterialItemList(List<HongXunMaterialStock> hongXunMaterialStocks);
	List<HongXunMaterialStock> quary(HongXunMaterialStock hongXunMaterialStock);
	HongXunMaterialStock hongXunMaterialFindById(int idc);
	

}
