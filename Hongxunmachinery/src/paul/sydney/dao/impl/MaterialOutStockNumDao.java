package paul.sydney.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import paul.sydney.commen.dao.impl.HibernateBaseUtil;
import paul.sydney.dao.IMaterialOutStockNumDao;
import paul.sydney.model.HongXunMaterialOutStoreNum;


@Repository("MaterialOutStockNumDao")
public class MaterialOutStockNumDao extends HibernateBaseUtil<HongXunMaterialOutStoreNum, Integer> implements IMaterialOutStockNumDao {

	
/*	@Override
	public void deleteOutStoreNumList(List<HongXunMaterialOutStoreNum> hongXunMaterialOutStoreNumList) {
		// TODO Auto-generated method stub
		getHibernateTemplate().deleteAll(hongXunMaterialOutStoreNumList);
	}
	@Override
	public void save(HongXunMaterialOutStoreNum hongXunMaterialOutStoreNum) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(hongXunMaterialOutStoreNum);
	}
	@Override
	public void update(HongXunMaterialOutStoreNum hongXunMaterialOutStoreNum) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(hongXunMaterialOutStoreNum);
	}*/
	@Override
	public void delete(HongXunMaterialOutStoreNum hongXunMaterialOutStoreNum) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(hongXunMaterialOutStoreNum);
	}
/*	@Override
	public HongXunMaterialOutStoreNum outStoreNumFindById(int id) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(HongXunMaterialOutStoreNum.class, id);
	}*/
	@Override
	public List<HongXunMaterialOutStoreNum> quary(HongXunMaterialOutStoreNum hongXunMaterialOutStoreNum) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().findByExample(hongXunMaterialOutStoreNum);
	}


}
