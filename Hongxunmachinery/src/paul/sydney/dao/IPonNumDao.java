package paul.sydney.dao;

import java.util.List;

import paul.sydney.commen.dao.IHibernateBaseUtil;

import paul.sydney.model.HongXunPonNum;

public interface IPonNumDao extends IHibernateBaseUtil<HongXunPonNum, Integer>{

	void save(HongXunPonNum hongXunPonNum);
	List<HongXunPonNum> quary(HongXunPonNum hongXunPonNum);
	

}
