package paul.sydney.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;

import paul.sydney.commen.dao.CustomHibernateDaoSupport;
import paul.sydney.commen.dao.impl.HibernateBaseUtil;
import paul.sydney.dao.IBomTreeDao;
import paul.sydney.model.HongXunBomPlane;
import paul.sydney.model.HongXunBomTree;
import paul.sydney.model.HongXunPurchaseNum;


@Repository("BomTreeDao")

public class BomTreeDao extends HibernateBaseUtil<HongXunBomTree, Integer> implements IBomTreeDao{
	@Override
	public List<HongXunBomTree> quary(HongXunBomTree hongXunBomTree) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().findByExample(hongXunBomTree);
	}
/*	@Override
	public void deleteBomTree(List<HongXunBomTree> hongXunBomTrees) {
		// TODO Auto-generated method stub
		getHibernateTemplate().deleteAll(hongXunBomTrees);
	}
	@Override
	public HongXunBomTree hongXunBomTreeFindById(Integer id) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(HongXunBomTree.class, id);
	}
	@Override
	public void save(HongXunBomTree hongXunBomTree) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(hongXunBomTree);
	}

	@Override
	public void update(HongXunBomTree hongXunBomTree) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(hongXunBomTree);
	}

	@Override
	public void deletRow(HongXunBomTree hongXunBomTree) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(hongXunBomTree);
	}*/
/*
	@Override
	public void save(HongXunBomPlane hongXunBomPlane) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(hongXunBomPlane);
	}

	@Override
	public List<HongXunBomPlane> quary(HongXunBomPlane hongXunBomPlane) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().findByExample(hongXunBomPlane);
	}

	@Override
	public void update(HongXunBomPlane hongXunBomPlane) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(hongXunBomPlane);
	}

	@Override
	public void delete(HongXunBomPlane hongXunBomPlane) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(hongXunBomPlane);
	}*/
}
