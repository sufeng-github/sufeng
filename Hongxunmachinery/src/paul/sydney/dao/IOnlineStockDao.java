package paul.sydney.dao;

import java.util.List;

import paul.sydney.commen.dao.IHibernateBaseUtil;
import paul.sydney.model.HongXunMaterialStock;
import paul.sydney.model.HongXunOnlineStock;
import paul.sydney.model.HongXunWorkItem;
import paul.sydney.model.HongXunWorkNum;

public interface IOnlineStockDao extends IHibernateBaseUtil<HongXunOnlineStock, Integer>{
	void save(HongXunOnlineStock hongXunOnlineStock);

	List<HongXunOnlineStock> quary(HongXunOnlineStock hongXunOnlineStock);

	void deleteOnlineItemList(List<HongXunOnlineStock> hongXunOnlineStocks);

	void update(HongXunOnlineStock hongXunOnlineStock);

	
}
