package paul.sydney.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import paul.sydney.commen.dao.CustomHibernateDaoSupport;
import paul.sydney.commen.dao.IHibernateBaseUtil;
import paul.sydney.commen.dao.impl.HibernateBaseUtil;
import paul.sydney.dao.IBomTreeDao;
import paul.sydney.dao.IMaterialCompilationDao;
import paul.sydney.dao.IMaterialStockDao;
import paul.sydney.model.HongXunMaterialCompilation;
import paul.sydney.model.HongXunMaterialItemOutStock;
import paul.sydney.model.HongXunMaterialNoLimitInStock;
import paul.sydney.model.HongXunMaterialNoLimitOutStock;
import paul.sydney.model.HongXunMaterialOutStoreItem;
import paul.sydney.model.HongXunMaterialOutStoreNum;
import paul.sydney.model.HongXunMaterialStock;

@Repository("MaterialCompilationDao")
public class MaterialCompilationDao extends HibernateBaseUtil<HongXunMaterialCompilation, Integer> implements IMaterialCompilationDao {

	
	@Override
	public List<HongXunMaterialCompilation> quary(HongXunMaterialCompilation hongXunMaterialCompilation) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().findByExample(hongXunMaterialCompilation);
	}
/*	@Override
	public void save(HongXunMaterialCompilation hongXunMaterialCompilation) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(hongXunMaterialCompilation);
	}
	@Override
	public void update(HongXunMaterialCompilation hongXunMaterialCompilation) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(hongXunMaterialCompilation);
	}*/


}
