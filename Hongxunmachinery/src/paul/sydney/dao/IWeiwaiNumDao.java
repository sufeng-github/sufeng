package paul.sydney.dao;

import java.util.List;

import paul.sydney.commen.dao.IHibernateBaseUtil;
import paul.sydney.model.HongXunWeiwaiNum;

public interface IWeiwaiNumDao extends IHibernateBaseUtil<HongXunWeiwaiNum, Integer>{
	void save(HongXunWeiwaiNum hongXunDataFour);
	void update(HongXunWeiwaiNum hongXunDataFour);
	List<HongXunWeiwaiNum> quary(HongXunWeiwaiNum hongXunDataFour);
	HongXunWeiwaiNum weiwaiNumFindById(int iD);
	void delete(HongXunWeiwaiNum hongXunWeiwaiNum);
	void deleteWeiwaiNumList(List<HongXunWeiwaiNum> hongXunWeiwaiNumList);
	
	
}
