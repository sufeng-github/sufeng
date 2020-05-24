package paul.sydney.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import paul.sydney.commen.dao.IHibernateBaseUtil;
import paul.sydney.commen.dao.impl.HibernateBaseUtil;
import paul.sydney.dao.IWeldDeliveryNumDao;
import paul.sydney.model.HongXunDeliveryWeldItem;
import paul.sydney.model.HongXunDeliveryWeldNum;
import paul.sydney.model.HongXunMaterialStock;
import paul.sydney.model.HongXunProductionWeldStock;
import paul.sydney.model.HongXunWeldCompilation;
import paul.sydney.model.HongXunWeldItemInStock;
import paul.sydney.model.HongXunWeldItemOutStock;
import paul.sydney.model.HongXunWeldNoLimitItemInStock;
import paul.sydney.model.HongXunWeldNoLimitItemOutStock;
@Repository("WeldDeliveryNumDao")
public class WeldDeliveryNumDao extends HibernateBaseUtil<HongXunDeliveryWeldNum, Integer> implements IWeldDeliveryNumDao{
	
	
	@Override
	public List<HongXunDeliveryWeldNum> quary(HongXunDeliveryWeldNum hongXunDeliveryWeldNum) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().findByExample(hongXunDeliveryWeldNum);
	}
	@Override
	public void save(HongXunDeliveryWeldNum hongXunDeliveryWeldNum) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(hongXunDeliveryWeldNum);
	}
	@Override
	public void delete(HongXunDeliveryWeldNum hongXunDeliveryWeldNum) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(hongXunDeliveryWeldNum);
	}
	@Override
	public HongXunDeliveryWeldNum deliveryWeldNumFindById(int id) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(HongXunDeliveryWeldNum.class, id);
	}
	@Override
	public void update(HongXunDeliveryWeldNum hongXunDeliveryWeldNum) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(hongXunDeliveryWeldNum);
	}

}
