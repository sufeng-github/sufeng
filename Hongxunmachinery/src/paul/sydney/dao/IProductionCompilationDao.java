package paul.sydney.dao;

import java.util.List;

import paul.sydney.commen.dao.IHibernateBaseUtil;
import paul.sydney.model.HongXunAssembleCompilation;



public interface IProductionCompilationDao extends IHibernateBaseUtil<HongXunAssembleCompilation, Integer> {
	
	
	void save(HongXunAssembleCompilation hongXunAssembleCompilation);
	void update(HongXunAssembleCompilation hongXunAssembleCompilation);
	List<HongXunAssembleCompilation> quary(HongXunAssembleCompilation hongXunAssembleCompilation);


}
