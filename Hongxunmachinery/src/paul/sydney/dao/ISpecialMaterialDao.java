package paul.sydney.dao;

import java.util.List;

import paul.sydney.commen.dao.IHibernateBaseUtil;
import paul.sydney.model.HongXunMaterialStock;
import paul.sydney.model.HongXunSpecialMaterialNum;
import paul.sydney.model.HongXunWorkItem;
import paul.sydney.model.HongXunWorkNum;

public interface ISpecialMaterialDao extends IHibernateBaseUtil<HongXunSpecialMaterialNum, Integer>{
	List<HongXunSpecialMaterialNum> quary(HongXunSpecialMaterialNum hongXunSpecialMaterialNum);

	void save(HongXunSpecialMaterialNum hongXunSpecialMaterialNum);

	void update(HongXunSpecialMaterialNum hongXunSpecialMaterialNum);

	HongXunSpecialMaterialNum hongXunSpecialFindById(int iD);

	void deletRow(HongXunSpecialMaterialNum hongXunSpecialMaterialNum);

	
}
