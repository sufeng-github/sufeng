package paul.sydney.dao;

import java.util.List;

import paul.sydney.commen.dao.IHibernateBaseUtil;
import paul.sydney.model.HongXunAuthority;


public interface ILoginDao extends IHibernateBaseUtil<HongXunAuthority, Integer>{

	List<HongXunAuthority> quary(HongXunAuthority hongXunAuthority);
/*	void save(HongXunAuthority hongXunAuthority);
	void update(HongXunAuthority hongXunAuthority);
	HongXunAuthority authorityFindById(int id);
	void delete(HongXunAuthority hongXunAuthority);*/
}
