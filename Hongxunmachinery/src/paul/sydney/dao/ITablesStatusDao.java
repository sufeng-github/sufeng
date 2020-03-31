package paul.sydney.dao;

import java.util.List;

import paul.sydney.commen.dao.IHibernateBaseUtil;
import paul.sydney.model.HongXunMaterialStock;
import paul.sydney.model.HongXunTablesStatus;
import paul.sydney.model.HongXunWorkItem;
import paul.sydney.model.HongXunWorkNum;

public interface ITablesStatusDao extends IHibernateBaseUtil<HongXunTablesStatus, Integer>{
	HongXunTablesStatus tablesStatusFindById(int i);

	void update(HongXunTablesStatus hongXunTablesStatus);

	List<HongXunTablesStatus> quary(HongXunTablesStatus hongXunTablesStatus);

	void save(HongXunTablesStatus hongXunTablesStatus);

	
}
