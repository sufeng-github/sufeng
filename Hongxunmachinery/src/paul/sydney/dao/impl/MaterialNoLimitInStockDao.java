package paul.sydney.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import paul.sydney.commen.dao.CustomHibernateDaoSupport;
import paul.sydney.commen.dao.IHibernateBaseUtil;
import paul.sydney.commen.dao.impl.HibernateBaseUtil;
import paul.sydney.dao.IBomTreeDao;
import paul.sydney.dao.IMaterialNoLimitInStockDao;
import paul.sydney.dao.IMaterialStockDao;
import paul.sydney.model.HongXunMaterialCompilation;
import paul.sydney.model.HongXunMaterialItemOutStock;
import paul.sydney.model.HongXunMaterialNoLimitInStock;
import paul.sydney.model.HongXunMaterialNoLimitOutStock;
import paul.sydney.model.HongXunMaterialOutStoreItem;
import paul.sydney.model.HongXunMaterialOutStoreNum;
import paul.sydney.model.HongXunMaterialStock;

@Repository("MaterialNoLimitInStockDao")
public class MaterialNoLimitInStockDao extends HibernateBaseUtil<HongXunMaterialNoLimitInStock, Integer> implements IMaterialNoLimitInStockDao {
	
	
	@Override
	public List<HongXunMaterialNoLimitInStock> quary(HongXunMaterialNoLimitInStock hongXunMaterialNoLimitInStock) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().findByExample(hongXunMaterialNoLimitInStock);
	}
/*	@Override
	public void save(HongXunMaterialNoLimitInStock hongXunMaterialNoLimitInStock) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(hongXunMaterialNoLimitInStock);
	}
	@Override
	public void update(HongXunMaterialNoLimitInStock item) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(item);
	}
	@Override
	public HongXunMaterialNoLimitInStock materialNoLimitInStockFindById(int idc) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(HongXunMaterialNoLimitInStock.class, idc);
	}*/
	

}
