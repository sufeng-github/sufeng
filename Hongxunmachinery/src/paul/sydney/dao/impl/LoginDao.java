package paul.sydney.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import paul.sydney.commen.dao.impl.HibernateBaseUtil;
import paul.sydney.dao.ILoginDao;
import paul.sydney.model.HongXunAuthority;


@Repository("LoginDao")
public class LoginDao extends HibernateBaseUtil<HongXunAuthority, Integer> implements ILoginDao{
	@Override
	public List<HongXunAuthority> quary(HongXunAuthority hongXunAuthority) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().findByExample(hongXunAuthority);
	}

	/*@Override
	public void save(HongXunAuthority hongXunAuthority) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(hongXunAuthority);
	}

	@Override
	public void update(HongXunAuthority hongXunAuthority) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(hongXunAuthority);
	}

	@Override
	public HongXunAuthority authorityFindById(int id) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(HongXunAuthority.class, id);
	}

	@Override
	public void delete(HongXunAuthority hongXunAuthority) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(hongXunAuthority);
	}*/

}
