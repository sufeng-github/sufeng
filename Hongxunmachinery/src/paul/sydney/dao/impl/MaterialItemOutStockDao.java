package paul.sydney.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import paul.sydney.commen.dao.CustomHibernateDaoSupport;
import paul.sydney.commen.dao.IHibernateBaseUtil;
import paul.sydney.commen.dao.impl.HibernateBaseUtil;
import paul.sydney.dao.IBomTreeDao;
import paul.sydney.dao.IMaterialItemOutStockDao;
import paul.sydney.dao.IMaterialStockDao;
import paul.sydney.model.HongXunMaterialCompilation;
import paul.sydney.model.HongXunMaterialItemOutStock;
import paul.sydney.model.HongXunMaterialNoLimitInStock;
import paul.sydney.model.HongXunMaterialNoLimitOutStock;
import paul.sydney.model.HongXunMaterialOutStoreItem;
import paul.sydney.model.HongXunMaterialOutStoreNum;
import paul.sydney.model.HongXunMaterialStock;

@Repository("MaterialItemOutStockDao")
public class MaterialItemOutStockDao extends HibernateBaseUtil<HongXunMaterialItemOutStock, Integer> implements IMaterialItemOutStockDao {
	
	
/*	@Override
	public void update(HongXunMaterialItemOutStock hongXunMaterialItemOutStock) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(hongXunMaterialItemOutStock);
	}
	@Override
	public void save(HongXunMaterialItemOutStock hongXunDataTwoChildren) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(hongXunDataTwoChildren);
	}*/
	@Override
	public List<HongXunMaterialItemOutStock> quary(HongXunMaterialItemOutStock hongXunDataTwoChildren) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().findByExample(hongXunDataTwoChildren);
	}
/*	@Override
	public HongXunMaterialItemOutStock materialItemOutStockFindById(int idc) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(HongXunMaterialItemOutStock.class, idc);
	}*/
	

}
