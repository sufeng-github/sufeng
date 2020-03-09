package paul.sydney.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import paul.sydney.commen.dao.CustomHibernateDaoSupport;
import paul.sydney.commen.dao.impl.HibernateBaseUtil;
import paul.sydney.dao.IOrderNumDao;
import paul.sydney.model.HongXunMaterialStock;
import paul.sydney.model.HongXunOrderNum;
import paul.sydney.model.HongXunPoN;
import paul.sydney.model.HongXunPonNum;


@Repository("OrderNumDao")
public class OrderNumDao extends HibernateBaseUtil<HongXunOrderNum, Integer> implements IOrderNumDao{
	
	@Override
	public void save(HongXunOrderNum hongXunOrderNum) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(hongXunOrderNum);
	}
	@Override
	public void update(HongXunOrderNum hongXunOrderNum) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(hongXunOrderNum);
	}
	@Override
	public List<HongXunOrderNum> quary(HongXunOrderNum hongXunOrderNum) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().findByExample(hongXunOrderNum);
	}
	@Override
	public void delete(HongXunOrderNum hongXunOrderNum) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(hongXunOrderNum);
	}
	@Override
	public HongXunOrderNum orderNumFindById(int ID) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(HongXunOrderNum.class, ID);
	}
	
	
}
