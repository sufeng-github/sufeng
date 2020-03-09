package paul.sydney.dao;

import java.util.List;

import paul.sydney.commen.dao.IHibernateBaseUtil;
import paul.sydney.model.HongXunOrderNum;


public interface IOrderNumDao extends IHibernateBaseUtil<HongXunOrderNum, Integer>{
	void save(HongXunOrderNum hongXunOrderNum);
	void update(HongXunOrderNum hongXunOrderNum);
	List<HongXunOrderNum> quary(HongXunOrderNum hongXunOrderNum);
	void delete(HongXunOrderNum hongXunOrderNum);
	HongXunOrderNum orderNumFindById(int orderNumID);
	
}
