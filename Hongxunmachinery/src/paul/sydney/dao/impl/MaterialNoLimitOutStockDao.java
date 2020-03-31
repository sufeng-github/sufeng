package paul.sydney.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import paul.sydney.commen.dao.CustomHibernateDaoSupport;
import paul.sydney.commen.dao.IHibernateBaseUtil;
import paul.sydney.commen.dao.impl.HibernateBaseUtil;
import paul.sydney.dao.IBomTreeDao;
import paul.sydney.dao.IMaterialNoLimitOutStockDao;
import paul.sydney.dao.IMaterialStockDao;
import paul.sydney.model.HongXunMaterialCompilation;
import paul.sydney.model.HongXunMaterialItemOutStock;
import paul.sydney.model.HongXunMaterialNoLimitInStock;
import paul.sydney.model.HongXunMaterialNoLimitOutStock;
import paul.sydney.model.HongXunMaterialOutStoreItem;
import paul.sydney.model.HongXunMaterialOutStoreNum;
import paul.sydney.model.HongXunMaterialStock;

@Repository("MaterialNoLimitOutStockDao")
public class MaterialNoLimitOutStockDao extends HibernateBaseUtil<HongXunMaterialNoLimitOutStock, Integer> implements IMaterialNoLimitOutStockDao {
	
	
/*	@Override
	public void save(HongXunMaterialNoLimitOutStock hongXunMaterialItemOutStock) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(hongXunMaterialItemOutStock);
	}*/
	@Override
	public List<HongXunMaterialNoLimitOutStock> quary(HongXunMaterialNoLimitOutStock hongXunMaterialNoLimitOutStock) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().findByExample(hongXunMaterialNoLimitOutStock);
	}
/*	@Override
	public HongXunMaterialNoLimitOutStock materialNoLimitOutStockFindById(int idc) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(HongXunMaterialNoLimitOutStock.class, idc);
	}
	@Override
	public void update(HongXunMaterialNoLimitOutStock hongXunMaterialNoLimitOutStock) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(hongXunMaterialNoLimitOutStock);
	}*/

}
