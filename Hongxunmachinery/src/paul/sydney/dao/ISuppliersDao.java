package paul.sydney.dao;

import java.util.List;

import paul.sydney.commen.dao.IHibernateBaseUtil;
import paul.sydney.model.HongXunSupplier;

public interface ISuppliersDao extends IHibernateBaseUtil<HongXunSupplier, Integer>{
	
	void save(HongXunSupplier hongXunSupplier);
	List<HongXunSupplier> quary(HongXunSupplier hongXunSupplier);
	void update(HongXunSupplier hongXunSupplier);
	HongXunSupplier hongXunSupplierFindById(int iD);
	void deletRow(HongXunSupplier hongXunSupplier);
}
