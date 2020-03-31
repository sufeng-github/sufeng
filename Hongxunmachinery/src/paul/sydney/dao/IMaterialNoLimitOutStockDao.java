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

public interface IMaterialNoLimitOutStockDao extends IHibernateBaseUtil<HongXunMaterialNoLimitOutStock, Integer>{

	//HongXunMaterialNoLimitOutStock materialNoLimitOutStockFindById(int idc);
	//void update(HongXunMaterialNoLimitOutStock hongXunMaterialNoLimitOutStock);
	List<HongXunMaterialNoLimitOutStock> quary(HongXunMaterialNoLimitOutStock hongXunMaterialNoLimitOutStock);
	//void save(HongXunMaterialNoLimitOutStock hongXunMaterialItemOutStock);

}
