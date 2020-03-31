package paul.sydney.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import paul.sydney.commen.dao.impl.HibernateBaseUtil;
import paul.sydney.dao.IProductionItemInStockDao;
import paul.sydney.model.HongXunProductionItemInStock;


@Repository("ProductionItemInStockDao")
public class ProductionItemInStockDao extends HibernateBaseUtil<HongXunProductionItemInStock, Integer> implements IProductionItemInStockDao {

	@Override
	public void save(HongXunProductionItemInStock hongXunProductionItemInStock) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(hongXunProductionItemInStock);
	}

	@Override
	public List<HongXunProductionItemInStock> quary(HongXunProductionItemInStock hongXunProductionItemInStock) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().findByExample(hongXunProductionItemInStock);
	}

}
