package paul.sydney.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import paul.sydney.commen.dao.CustomHibernateDaoSupport;
import paul.sydney.model.HongXunAssembleCompilation;
import paul.sydney.model.HongXunAuthority;
import paul.sydney.model.HongXunBomPlane;
import paul.sydney.model.HongXunBomTree;
import paul.sydney.model.HongXunDeliveryItem;
import paul.sydney.model.HongXunDeliveryNum;
import paul.sydney.model.HongXunDeliveryWeldItem;
import paul.sydney.model.HongXunDeliveryWeldNum;
import paul.sydney.model.HongXunWeiwaiItem;
import paul.sydney.model.HongXunWeiwaiNum;
import paul.sydney.model.HongXunWeldCompilation;
import paul.sydney.model.HongXunWeldItemInStock;
import paul.sydney.model.HongXunWeldItemOutStock;
import paul.sydney.model.HongXunWeldNoLimitItemInStock;
import paul.sydney.model.HongXunWeldNoLimitItemOutStock;
import paul.sydney.model.HongXunPurchaseNum;
import paul.sydney.model.HongXunSpecialMaterialNum;
import paul.sydney.model.HongXunPurchaseItem;
import paul.sydney.model.HongXunMaterialItemOutStock;
import paul.sydney.model.HongXunMaterialNoLimitInStock;
import paul.sydney.model.HongXunMaterialNoLimitOutStock;
import paul.sydney.model.HongXunPurchaseItemInStock;
import paul.sydney.model.HongXunPoN;
import paul.sydney.model.HongXunPonNum;
import paul.sydney.model.HongXunProductionItemInStock;
import paul.sydney.model.HongXunProductionItemOutStock;
import paul.sydney.model.HongXunProductionNoLimitItemInStock;
import paul.sydney.model.HongXunProductionNoLimitItemOutStock;
import paul.sydney.model.HongXunProductionStock;
import paul.sydney.model.HongXunProductionWeldStock;
import paul.sydney.model.HongXunPurchaseDeItem;
import paul.sydney.model.HongXunPurchaseDeNum;
import paul.sydney.model.HongXunSupplier;
import paul.sydney.model.HongXunSuppliersPrice;
import paul.sydney.model.HongXunTablesStatus;
import paul.sydney.model.HongXunWorkNum;
import paul.sydney.model.HongXunWorkItem;
import paul.sydney.model.HongXunFile;
import paul.sydney.model.HongXunMaterialCompilation;
import paul.sydney.model.HongXunMaterialOutStoreItem;
import paul.sydney.model.HongXunMaterialOutStoreNum;
import paul.sydney.model.HongXunMaterialStock;
import paul.sydney.model.HongXunOnlineStock;
import paul.sydney.model.HongXunOrderNum;

@Repository("stockDao")
public class StockDaoImpl extends CustomHibernateDaoSupport implements StockDao{

    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }
	
	@Override
	public Session session() {
		// TODO Auto-generated method stub
		return getHibernateTemplate().getSessionFactory().openSession();
		//return this.currentSession();	
	}


	@Override
	public List<?> quarywithpara(String para) {
		// TODO Auto-generated method stub
		List<?> list = getHibernateTemplate().find(para);
		return list;
	}


	@Override
	public List<?> quarywithpara(String database, String filed, String name) {
		// TODO Auto-generated method stub
		List<?> list = getHibernateTemplate().find("from " + database +" where " + filed + "=?",name);
		return list;
	}

	@Override
	public List<?> quarywithpara(String database, String filed, String symbol, int value) {
		// TODO Auto-generated method stub
		List<?> list = getHibernateTemplate().find("from " + database +" where " + filed + symbol,value);
		return list;
	}
	
	@Override
	public List<?> quarywithpara(String database, String filed, int value) {
		// TODO Auto-generated method stub
		List<?> list = getHibernateTemplate().find("from " + database +" where " + filed + "=?",value);
		return list;
	}


	@Override
	public List<?> getList(String sql, String str) {
		// TODO Auto-generated method stub
		List<?> list =  getHibernateTemplate().find(sql,str);
		return list;
	}

	@Override
	public List<HongXunFile> quary(HongXunFile hongXunFile) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().findByExample(hongXunFile);
	}


	@Override
	public HongXunFile fileFindById(int id) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(HongXunFile.class, id);
	}


	@Override
	public void save(HongXunFile hongXunFile) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(hongXunFile);
	}


	@Override
	public void delete(HongXunFile hongXunFile) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(hongXunFile);
	}


	@Override
	public void update(HongXunFile hongXunFile) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(hongXunFile);
	}


	@Override
	public List<?> quary(String sql) {
		// TODO Auto-generated method stub
		List<?> list = getHibernateTemplate().find(sql);
		return list;
	}



	@Override
	public List<?> quaryFuzzyWithpara(String database, String filed, String name) {
		// TODO Auto-generated method stub
		List<?> list = getHibernateTemplate().find("from " + database +" where " + filed + " like ?" ,  "%" + name + "%");
		//List<?> list = getHibernateTemplate().find("from HongXunBomTree where attribute like" + "'%" + name + "%'");
		return list;
	}

	@Override
	public HongXunTablesStatus tablesStatusFindById(int id) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(HongXunTablesStatus.class, id);
	}


	@Override
	public void update(HongXunTablesStatus hongXunTablesStatus) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(hongXunTablesStatus);
	}


	@Override
	public List<HongXunTablesStatus> quary(HongXunTablesStatus hongXunTablesStatus) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().findByExample(hongXunTablesStatus);
	}


	@Override
	public void save(HongXunTablesStatus hongXunTablesStatus) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(hongXunTablesStatus);
	}

	@Override
	public List<HongXunSpecialMaterialNum> quary(HongXunSpecialMaterialNum hongXunSpecialMaterialNum) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().findByExample(hongXunSpecialMaterialNum);
	}


	@Override
	public void save(HongXunSpecialMaterialNum hongXunSpecialMaterialNum) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(hongXunSpecialMaterialNum);
	}


	@Override
	public void update(HongXunSpecialMaterialNum hongXunSpecialMaterialNum) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(hongXunSpecialMaterialNum);
	}


	@Override
	public HongXunSpecialMaterialNum hongXunSpecialFindById(int id) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(HongXunSpecialMaterialNum.class, id);
	}


	@Override
	public void deletRow(HongXunSpecialMaterialNum hongXunSpecialMaterialNum) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(hongXunSpecialMaterialNum);
	}

	@Override
	public void save(HongXunOnlineStock hongXunOnlineStock) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(hongXunOnlineStock);
	}


	@Override
	public List<HongXunOnlineStock> quary(HongXunOnlineStock hongXunOnlineStock) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().findByExample(hongXunOnlineStock);
	}


	@Override
	public void deleteOnlineItemList(List<HongXunOnlineStock> hongXunOnlineStocks) {
		// TODO Auto-generated method stub
		getHibernateTemplate().deleteAll(hongXunOnlineStocks);
	}


	@Override
	public void update(HongXunOnlineStock hongXunOnlineStock) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(hongXunOnlineStock);
	}


}