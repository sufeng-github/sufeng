package paul.sydney.dao;

import java.util.List;

import paul.sydney.commen.dao.IHibernateBaseUtil;
import paul.sydney.model.HongXunPoN;

public interface IPonDao extends IHibernateBaseUtil<HongXunPoN, Integer>{

	List<HongXunPoN> quary(HongXunPoN hongXunPoN);
	void save(HongXunPoN hongXunPoN);
	void delete(HongXunPoN hongXunPoN);
	void update(HongXunPoN hongXunPoN);
	HongXunPoN hongXunPoNFindById(int id);
	void deletePoNList(List<HongXunPoN> hongXunPoNs);
}
