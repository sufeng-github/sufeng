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


public interface IProductionDao extends IHibernateBaseUtil<HongXunProductionStock, Integer> {
	void save(HongXunProductionStock hongXunProductionStock);
	void update(HongXunProductionStock hongXunProductionStock);
	HongXunProductionStock productionFindById(Integer stockId);
	List<HongXunProductionStock> quary(HongXunProductionStock hongXunProductionStock);
	void deleteProductionItemList(List<HongXunProductionStock> hongXunProductionStocks);
	
	


}
