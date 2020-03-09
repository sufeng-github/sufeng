package paul.sydney.dao.impl;

import java.util.List;

import paul.sydney.commen.dao.IHibernateBaseUtil;
import paul.sydney.commen.dao.impl.HibernateBaseUtil;
import paul.sydney.dao.IProductionCompilationDao;
import paul.sydney.model.HongXunAssembleCompilation;
import paul.sydney.model.HongXunDeliveryItem;
import paul.sydney.model.HongXunDeliveryNum;
import paul.sydney.model.HongXunProductionItemInStock;
import paul.sydney.model.HongXunProductionItemOutStock;
import paul.sydney.model.HongXunProductionNoLimitItemInStock;
import paul.sydney.model.HongXunProductionNoLimitItemOutStock;
import paul.sydney.model.HongXunProductionStock;


public class ProductionCompilationDao extends HibernateBaseUtil<HongXunAssembleCompilation, Integer> implements IProductionCompilationDao{
	
	@Override
	public void save(HongXunAssembleCompilation hongXunAssembleCompilation) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(hongXunAssembleCompilation);
	}


	@Override
	public void update(HongXunAssembleCompilation hongXunAssembleCompilation) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(hongXunAssembleCompilation);
	}


	@Override
	public List<HongXunAssembleCompilation> quary(HongXunAssembleCompilation hongXunAssembleCompilation) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().findByExample(hongXunAssembleCompilation);
	}
}
