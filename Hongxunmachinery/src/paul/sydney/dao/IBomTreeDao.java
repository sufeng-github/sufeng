package paul.sydney.dao;

import java.util.List;

import paul.sydney.commen.dao.IHibernateBaseUtil;
import paul.sydney.model.HongXunBomTree;


public interface IBomTreeDao extends IHibernateBaseUtil<HongXunBomTree, Integer>{
	List<HongXunBomTree> quary(HongXunBomTree hongXunBomTree);
	//void save(HongXunBomTree hongXunBomTree);
	//void update(HongXunBomTree hongXunBomTree);
	//void deletRow(HongXunBomTree hongXunBomTree);
	//HongXunBomTree hongXunBomTreeFindById(Integer id);
	//void deleteBomTree(List<HongXunBomTree> hongXunBomTrees);	
/*	void save(HongXunBomPlane hongXunBomPlane);
	List<HongXunBomPlane> quary(HongXunBomPlane hongXunBomPlane);
	void update(HongXunBomPlane hongXunBomPlane);
	void delete(HongXunBomPlane hongXunBomPlane);*/
}
