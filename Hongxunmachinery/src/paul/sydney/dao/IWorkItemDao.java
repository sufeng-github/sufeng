package paul.sydney.dao;

import java.util.List;

import paul.sydney.commen.dao.IHibernateBaseUtil;
import paul.sydney.model.HongXunMaterialStock;
import paul.sydney.model.HongXunWorkItem;
import paul.sydney.model.HongXunWorkNum;

public interface IWorkItemDao extends IHibernateBaseUtil<HongXunWorkItem, Integer>{

	List<HongXunWorkItem> quary(HongXunWorkItem hongXunDataThreeFather);
	void save(HongXunWorkItem hongXunDataThreeFather);
	void update(HongXunWorkItem hongXunDataThreeFather);
	HongXunWorkItem dataThreeFatherFindById(int idc);
	void deletRow(HongXunWorkItem hongXunDataThreeFather);
	void deleteWorkItemList(List<HongXunWorkItem> hongXunWorkItems);
	
}
