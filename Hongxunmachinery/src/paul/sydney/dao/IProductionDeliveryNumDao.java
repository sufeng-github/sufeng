package paul.sydney.dao;

import java.util.List;

import paul.sydney.commen.dao.IHibernateBaseUtil;
import paul.sydney.model.HongXunAssembleCompilation;
import paul.sydney.model.HongXunDeliveryItem;
import paul.sydney.model.HongXunDeliveryNum;
import paul.sydney.model.HongXunProductionItemInStock;
import paul.sydney.model.HongXunProductionItemOutStock;
import paul.sydney.model.HongXunProductionNoLimitItemInStock;
import paul.sydney.model.HongXunProductionNoLimitItemOutStock;
import paul.sydney.model.HongXunProductionStock;


public interface IProductionDeliveryNumDao extends IHibernateBaseUtil<HongXunDeliveryNum, Integer> {

	List<HongXunDeliveryNum> quary(HongXunDeliveryNum hongXunDeliveryNum);
	void save(HongXunDeliveryNum hongXunDeliveryNum);
	void update(HongXunDeliveryNum hongXunDeliveryNum);
	HongXunDeliveryNum deliveryNumFindById(int id);


}
