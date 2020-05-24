package paul.sydney.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import paul.sydney.commen.dao.impl.HibernateBaseUtil;
import paul.sydney.dao.ISpecialMaterialDao;
import paul.sydney.model.HongXunSpecialMaterialNum;
@Repository("SpecialMaterialDao")
public class SpecialMaterialDao extends HibernateBaseUtil<HongXunSpecialMaterialNum, Integer> implements ISpecialMaterialDao {
	

	@Override
	public List<HongXunSpecialMaterialNum> quary(HongXunSpecialMaterialNum hongXunSpecialMaterialNum) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().findByExample(hongXunSpecialMaterialNum);
	}


	@Override
	public void save(HongXunSpecialMaterialNum hongXunSpecialMaterialNum) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(hongXunSpecialMaterialNum);
	}


	@Override
	public void update(HongXunSpecialMaterialNum hongXunSpecialMaterialNum) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(hongXunSpecialMaterialNum);
	}


	@Override
	public HongXunSpecialMaterialNum hongXunSpecialFindById(int id) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(HongXunSpecialMaterialNum.class, id);
	}


	@Override
	public void deletRow(HongXunSpecialMaterialNum hongXunSpecialMaterialNum) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(hongXunSpecialMaterialNum);
	}
}
