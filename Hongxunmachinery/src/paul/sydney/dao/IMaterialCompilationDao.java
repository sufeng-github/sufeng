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

public interface IMaterialCompilationDao extends IHibernateBaseUtil<HongXunMaterialCompilation, Integer>{


	List<HongXunMaterialCompilation> quary(HongXunMaterialCompilation hongXunMaterialCompilation);
	void save(HongXunMaterialCompilation hongXunMaterialCompilation);
	void update(HongXunMaterialCompilation hongXunMaterialCompilation);
	
	
}
