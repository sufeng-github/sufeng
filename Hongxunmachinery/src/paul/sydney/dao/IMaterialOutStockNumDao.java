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

public interface IMaterialOutStockNumDao extends IHibernateBaseUtil<HongXunMaterialOutStoreNum, Integer>{

	void save(HongXunMaterialOutStoreNum hongXunMaterialOutStoreNum);
	void update(HongXunMaterialOutStoreNum hongXunMaterialOutStoreNum);
	void delete(HongXunMaterialOutStoreNum hongXunMaterialOutStoreNum);
	void deleteOutStoreNumList(List<HongXunMaterialOutStoreNum> hongXunMaterialOutStoreNumList);
	HongXunMaterialOutStoreNum outStoreNumFindById(int iD);
	List<HongXunMaterialOutStoreNum> quary(HongXunMaterialOutStoreNum hongXunMaterialOutStoreNum);

}
