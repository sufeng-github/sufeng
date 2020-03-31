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
}
