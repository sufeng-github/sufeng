package paul.sydney.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import paul.sydney.commen.dao.CustomHibernateDaoSupport;
import paul.sydney.commen.dao.impl.HibernateBaseUtil;
import paul.sydney.dao.IOrderNumDao;
import paul.sydney.dao.IPonNumDao;
import paul.sydney.model.HongXunMaterialStock;
import paul.sydney.model.HongXunOrderNum;
import paul.sydney.model.HongXunPoN;
import paul.sydney.model.HongXunPonNum;


@Repository("PonNumDao")
public class PonNumDao extends HibernateBaseUtil<HongXunPonNum, Integer> implements IPonNumDao{
		
	@Override
	public void save(HongXunPonNum hongXunPonNum) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(hongXunPonNum);
	}
	@Override
	public List<HongXunPonNum> quary(HongXunPonNum hongXunPonNum) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().findByExample(hongXunPonNum);
	}
}
