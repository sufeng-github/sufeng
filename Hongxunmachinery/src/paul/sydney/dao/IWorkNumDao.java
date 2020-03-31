package paul.sydney.dao;

import java.util.List;

import paul.sydney.commen.dao.IHibernateBaseUtil;
import paul.sydney.model.HongXunMaterialStock;
import paul.sydney.model.HongXunWorkItem;
import paul.sydney.model.HongXunWorkNum;

public interface IWorkNumDao extends IHibernateBaseUtil<HongXunWorkNum, Integer>{
	List<HongXunWorkNum> quary(HongXunWorkNum hongXunWorkNum);
	void save(HongXunWorkNum hongXunWorkNum);
	void deletRow(HongXunWorkNum hongXunWorkNum);
	void update(HongXunWorkNum hongXunWorkNum);
	HongXunWorkNum workNumFindById(int id);

	
}
