package paul.sydney.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import paul.sydney.commen.dao.impl.HibernateBaseUtil;
import paul.sydney.dao.ITablesStatusDao;
import paul.sydney.model.HongXunTablesStatus;

@Repository("TablesStatusDao")
public class TablesStatusDao extends HibernateBaseUtil<HongXunTablesStatus, Integer> implements ITablesStatusDao{
	@Override
	public HongXunTablesStatus tablesStatusFindById(int id) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(HongXunTablesStatus.class, id);
	}


	@Override
	public void update(HongXunTablesStatus hongXunTablesStatus) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(hongXunTablesStatus);
	}


	@Override
	public List<HongXunTablesStatus> quary(HongXunTablesStatus hongXunTablesStatus) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().findByExample(hongXunTablesStatus);
	}


	@Override
	public void save(HongXunTablesStatus hongXunTablesStatus) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(hongXunTablesStatus);
	}

}
