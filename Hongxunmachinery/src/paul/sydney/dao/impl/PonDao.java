package paul.sydney.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import paul.sydney.commen.dao.CustomHibernateDaoSupport;
import paul.sydney.commen.dao.impl.HibernateBaseUtil;
import paul.sydney.dao.IOrderNumDao;
import paul.sydney.dao.IPonDao;
import paul.sydney.model.HongXunMaterialStock;
import paul.sydney.model.HongXunOrderNum;
import paul.sydney.model.HongXunPoN;
import paul.sydney.model.HongXunPonNum;


@Repository("PonDao")
public class PonDao extends HibernateBaseUtil<HongXunPoN, Integer> implements IPonDao{
	
	
	
	@Override
	public List<HongXunPoN> quary(HongXunPoN hongXunPoN) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().findByExample(hongXunPoN);
	}
	@Override
	public void save(HongXunPoN hongXunPoN) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(hongXunPoN);
	}
	@Override
	public void delete(HongXunPoN hongXunPoN) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(hongXunPoN);
	}
	@Override
	public void update(HongXunPoN hongXunPoN) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(hongXunPoN);
	}
	@Override
	public HongXunPoN hongXunPoNFindById(int id) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(HongXunPoN.class, id);
	}
	@Override
	public void deletePoNList(List<HongXunPoN> hongXunPoNs) {
		// TODO Auto-generated method stub
		getHibernateTemplate().deleteAll(hongXunPoNs);
	}
}
