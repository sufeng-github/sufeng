package paul.sydney.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import paul.sydney.commen.dao.impl.HibernateBaseUtil;
import paul.sydney.dao.IWeldCompilationDao;
import paul.sydney.model.HongXunWeldCompilation;

@Repository("WeldCompilationDao")
public class WeldCompilationDao extends HibernateBaseUtil<HongXunWeldCompilation, Integer> implements IWeldCompilationDao{
	@Override
	public void save(HongXunWeldCompilation hongXunWeldCompilation) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(hongXunWeldCompilation);
	}
	@Override
	public void update(HongXunWeldCompilation hongXunWeldCompilation) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(hongXunWeldCompilation);
	}
	@Override
	public List<HongXunWeldCompilation> quary(HongXunWeldCompilation hongXunWeldCompilation) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().findByExample(hongXunWeldCompilation);
	}
	
}
