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

public interface IMaterialOutStockItemDao extends IHibernateBaseUtil<HongXunMaterialOutStoreItem, Integer>{

	//void save(HongXunMaterialOutStoreItem hongXunMaterialOutStoreItem);
	List<HongXunMaterialOutStoreItem> quary(HongXunMaterialOutStoreItem hongXunMaterialOutStoreItem);
	//void update(HongXunMaterialOutStoreItem hongXunMaterialOutStoreItem);
	//void delete(HongXunMaterialOutStoreItem hongXunMaterialOutStoreItem);
	//HongXunMaterialOutStoreItem materialOutStoreFindById(int iD);
	//void deleteOutStoreItemList(List<HongXunMaterialOutStoreItem> hongXunMaterialOutStoreItems);

}
