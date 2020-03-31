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

public interface IMaterialItemOutStockDao extends IHibernateBaseUtil<HongXunMaterialItemOutStock, Integer>{

	List<HongXunMaterialItemOutStock> quary(HongXunMaterialItemOutStock hongXunMaterialItemOutStock);
/*	void save(HongXunMaterialItemOutStock hongXunMaterialItemOutStock);
	void update(HongXunMaterialItemOutStock hongXunMaterialItemOutStock);
	HongXunMaterialItemOutStock materialItemOutStockFindById(int idc);	*/
}
