package paul.sydney.dao;

import java.util.List;

import paul.sydney.commen.dao.IHibernateBaseUtil;
import paul.sydney.model.HongXunWeiwaiItem;

public interface IWeiwaiItemDao extends IHibernateBaseUtil<HongXunWeiwaiItem, Integer>{
	
	List<HongXunWeiwaiItem> quary(HongXunWeiwaiItem hongXunDataFive);
	void save(HongXunWeiwaiItem hongXunDataFive);
	HongXunWeiwaiItem dataFiveFindById(int iD);
	void deletRow(HongXunWeiwaiItem hongXunDataFive);
	void deleteWeiwaiItemList(List<HongXunWeiwaiItem> hongXunWeiwaiItems);
	void update(HongXunWeiwaiItem hongXunDataFive);
	
}
