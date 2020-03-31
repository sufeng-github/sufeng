package paul.sydney.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import paul.sydney.commen.dao.impl.HibernateBaseUtil;
import paul.sydney.dao.IProductionItemOutStockDao;
import paul.sydney.model.HongXunProductionItemOutStock;


@Repository("ProductionItemOutStockDao")
public class ProductionItemOutStockDao extends HibernateBaseUtil<HongXunProductionItemOutStock, Integer> implements IProductionItemOutStockDao{

	@Override
	public void save(HongXunProductionItemOutStock hongXunProductionItemOutStock) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(hongXunProductionItemOutStock);
	}
	@Override
	public List<HongXunProductionItemOutStock> quary(HongXunProductionItemOutStock hongXunProductionItemOutStock) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().findByExample(hongXunProductionItemOutStock);
	}
	
}
