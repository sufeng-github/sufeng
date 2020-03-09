package paul.sydney.dao;

import java.util.List;

import paul.sydney.commen.dao.IHibernateBaseUtil;
import paul.sydney.commen.dao.impl.HibernateBaseUtil;
import paul.sydney.model.HongXunDeliveryWeldItem;
import paul.sydney.model.HongXunDeliveryWeldNum;
import paul.sydney.model.HongXunMaterialStock;
import paul.sydney.model.HongXunProductionWeldStock;
import paul.sydney.model.HongXunWeldCompilation;
import paul.sydney.model.HongXunWeldItemInStock;
import paul.sydney.model.HongXunWeldItemOutStock;
import paul.sydney.model.HongXunWeldNoLimitItemInStock;
import paul.sydney.model.HongXunWeldNoLimitItemOutStock;

public interface IWeldDao extends IHibernateBaseUtil<HongXunProductionWeldStock, Integer>{
	
	List<HongXunProductionWeldStock> quary(HongXunProductionWeldStock hongXunProductionWeldStock);
	void deleteWeldItemList(List<HongXunProductionWeldStock> hongXunProductionWeldStocks);
	HongXunProductionWeldStock productionWeldFindById(int idc);
	void save(HongXunProductionWeldStock hongXunProductionWeldStock);
	void update(HongXunProductionWeldStock hongXunProductionWeldStock);

	void save(HongXunWeldItemOutStock hongXunWeldItemOutStock);
	List<HongXunWeldItemOutStock> quary(HongXunWeldItemOutStock hongXunWeldItemOutStock);
	
	void save(HongXunWeldItemInStock hongXunWeldItemInStock);
	List<HongXunWeldItemInStock> quary(HongXunWeldItemInStock hongXunWeldItemInStock);

	void delete(HongXunDeliveryWeldItem hongXunDeliveryWeldItem);
	void update(HongXunDeliveryWeldItem hongXunDeliveryWeldItem);
	HongXunDeliveryWeldItem deliveryWeldItemFindById(int deliveryItemID);
	List<HongXunDeliveryWeldItem> quary(HongXunDeliveryWeldItem hongXunDeliveryWeldItem);
	void save(HongXunDeliveryWeldItem hongXunDeliveryWeldItem);
	
	List<HongXunDeliveryWeldNum> quary(HongXunDeliveryWeldNum hongXunDeliveryWeldNum);
	void save(HongXunDeliveryWeldNum hongXunDeliveryWeldNum);
	void delete(HongXunDeliveryWeldNum hongXunDeliveryWeldNum);
	HongXunDeliveryWeldNum deliveryWeldNumFindById(int id);
	void update(HongXunDeliveryWeldNum hongXunDeliveryWeldNum);

	void save(HongXunWeldNoLimitItemInStock hongXunWeldNoLimitItemInStock);
	List<HongXunWeldNoLimitItemInStock> quary(HongXunWeldNoLimitItemInStock hongXunWeldNoLimitItemInStock);
	HongXunWeldNoLimitItemInStock weldNoLimitItemInStockFindById(int idc);
	void update(HongXunWeldNoLimitItemInStock hongXunWeldNoLimitItemInStock);
	
	void save(HongXunWeldNoLimitItemOutStock hongXunWeldNoLimitItemOutStock);
	HongXunWeldNoLimitItemOutStock weldNoLimitItemOutStockFindById(int idc);
	void update(HongXunWeldNoLimitItemOutStock hongXunWeldNoLimitItemOutStock);
	List<HongXunWeldNoLimitItemOutStock> quary(HongXunWeldNoLimitItemOutStock hongXunWeldNoLimitItemOutStock);
	
	void save(HongXunWeldCompilation hongXunWeldCompilation);
	void update(HongXunWeldCompilation hongXunWeldCompilation);
	List<HongXunWeldCompilation> quary(HongXunWeldCompilation hongXunWeldCompilation);
}
