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

public interface IMaterialNoLimitInStockDao extends IHibernateBaseUtil<HongXunMaterialNoLimitInStock, Integer>{


	List<HongXunMaterialNoLimitInStock> quary(HongXunMaterialNoLimitInStock hongXunMaterialNoLimitInStock);
	void save(HongXunMaterialNoLimitInStock hongXunMaterialNoLimitInStock);
	void update(HongXunMaterialNoLimitInStock item);
	HongXunMaterialNoLimitInStock materialNoLimitInStockFindById(int idc);
}
