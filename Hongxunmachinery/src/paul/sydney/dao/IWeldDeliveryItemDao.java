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

public interface IWeldDeliveryItemDao extends IHibernateBaseUtil<HongXunDeliveryWeldItem, Integer>{
	
	void delete(HongXunDeliveryWeldItem hongXunDeliveryWeldItem);
	void update(HongXunDeliveryWeldItem hongXunDeliveryWeldItem);
	HongXunDeliveryWeldItem deliveryWeldItemFindById(int deliveryItemID);
	List<HongXunDeliveryWeldItem> quary(HongXunDeliveryWeldItem hongXunDeliveryWeldItem);
	void save(HongXunDeliveryWeldItem hongXunDeliveryWeldItem);
	
}
