package paul.sydney.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import paul.sydney.commen.dao.CustomHibernateDaoSupport;
import paul.sydney.commen.dao.IHibernateBaseUtil;
import paul.sydney.commen.dao.impl.HibernateBaseUtil;
import paul.sydney.dao.IBomTreeDao;
import paul.sydney.dao.IMaterialStockDao;
import paul.sydney.model.HongXunMaterialCompilation;
import paul.sydney.model.HongXunMaterialItemOutStock;
import paul.sydney.model.HongXunMaterialNoLimitInStock;
import paul.sydney.model.HongXunMaterialNoLimitOutStock;
import paul.sydney.model.HongXunMaterialOutStoreItem;
import paul.sydney.model.HongXunMaterialOutStoreNum;
import paul.sydney.model.HongXunMaterialStock;

@Repository("MaterialStockDao")
public class MaterialStockDao extends HibernateBaseUtil<HongXunMaterialStock, Integer> implements IMaterialStockDao {
	@Override
	public void delete(HongXunMaterialStock hongXunMaterialStock) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(hongXunMaterialStock);
	}
	/*@Override
	public void update(HongXunMaterialStock hongXunMaterialStock) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(hongXunMaterialStock);
	}
	@Override
	public HongXunMaterialStock hongXunMaterialFindById(int id) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(HongXunMaterialStock.class, id);
	}
	@Override
	public void deleteMaterialItemList(List<HongXunMaterialStock> hongXunMaterialStocks) {
		// TODO Auto-generated method stub
		getHibernateTemplate().deleteAll(hongXunMaterialStocks);
	}
	@Override
	public void save(HongXunMaterialStock hongXunMaterialStock) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(hongXunMaterialStock);
	}*/
	@Override
	public List<HongXunMaterialStock> quary(HongXunMaterialStock hongXunMaterialStock) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().findByExample(hongXunMaterialStock);
	}
	

}
