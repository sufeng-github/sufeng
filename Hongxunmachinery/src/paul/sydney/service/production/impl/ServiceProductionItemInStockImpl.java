package paul.sydney.service.production.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import paul.sydney.commen.result.PageResults;
import paul.sydney.dao.IBomTreeDao;
import paul.sydney.dao.IOrderNumDao;
import paul.sydney.dao.IPonDao;
import paul.sydney.dao.IProductionDao;
import paul.sydney.dao.IProductionItemInStockDao;
import paul.sydney.dao.StockDao;
import paul.sydney.model.HongXunPoN;
import paul.sydney.model.HongXunProductionItemInStock;
import paul.sydney.model.HongXunProductionStock;
import paul.sydney.model.HongXunPurchaseItemInStock;
import paul.sydney.service.production.ServiceProductionItemInStockInf;
@Transactional
@Service("serviceProductionItemInStock")
public class ServiceProductionItemInStockImpl implements ServiceProductionItemInStockInf{
	
	@Autowired
	StockDao stockDao;
	public void setStockDao(StockDao stockDao) {
		this.stockDao = stockDao;
	}
	@Autowired
	IBomTreeDao iBomTreeDao;
	public void setStockDao(IBomTreeDao iBomTreeDao) {
		this.iBomTreeDao = iBomTreeDao;
	}
	@Autowired
	IOrderNumDao iOrderDao;
	public void setStockDao(IOrderNumDao iOrderDao) {
		this.iOrderDao = iOrderDao;
	}
	@Autowired
	IPonDao iPonDao;
	public void setStockDao(IPonDao iPonDao) {
		this.iPonDao = iPonDao;
	}
	@Autowired
	IProductionDao iProductionDao;
	public void setStockDao(IProductionDao iProductionDao) {
		this.iProductionDao = iProductionDao;
	}
	
	@Autowired
	IProductionItemInStockDao iProductionItemInStockDao;
	public void setStockDao(IProductionItemInStockDao iProductionItemInStockDao) {
		this.iProductionItemInStockDao = iProductionItemInStockDao;
	}


	@Override
	public List<Map<String, Object>> autotimp(String str, String name) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		List<String> listObj = new ArrayList<String>();
		StringBuilder hql=new StringBuilder("from HongXunProductionItemInStock a where 1=1");
		hql.append(" and a.hongXunProductionStock.materialNum like ?");
		listObj.add("%" + str + "%");	
		hql.append(" and name = ?");
		listObj.add(name);
		List<HongXunProductionItemInStock> hongXunProductionItemInStocks = iProductionItemInStockDao.getListByHQL(hql.toString(), listObj.toArray());
		for(HongXunProductionItemInStock item: hongXunProductionItemInStocks){	
			Map<String,Object> map = new HashMap<String,Object>();
			//map.put("materialName", item.getMaterialName());	
			map.put("name", item.getHongXunProductionStock().getMaterialNum());
			map.put("id", item.getIdc());  
			list.add(map);
		}
		return list;
	}

	@Override
	public PageResults<HongXunProductionItemInStock> getData(HongXunProductionItemInStock hongXunProductionItemInStock,
			Integer pageNo, Integer pageSize) {
		// TODO Auto-generated method stub
		PageResults<HongXunProductionItemInStock> result = new PageResults<HongXunProductionItemInStock>();
		try{
			List<Object> list = new ArrayList<Object>();
			StringBuilder hql=new StringBuilder("from HongXunProductionItemInStock a where 1=1" );
	
	    	if(hongXunProductionItemInStock.getHongXunProductionStock().getMaterialNum()!=null){
	    		String materialNum = hongXunProductionItemInStock.getHongXunProductionStock().getMaterialNum();
	    		if(materialNum.indexOf("*")>-1){
					hql.append(" and a.hongXunProductionStock.materialNum like ?");
					list.add("%" + materialNum.replace("*", "") + "%");				
				}else{
					hql.append(" and a.hongXunProductionStock.materialNum=?");
					list.add(materialNum);
				}
	    	}   
	    	if(hongXunProductionItemInStock.getHongXunProductionStock().getSpecification()!=null){
	    		String specification = hongXunProductionItemInStock.getHongXunProductionStock().getSpecification();
	    		if(specification.indexOf("*")>-1){
					hql.append(" and a.hongXunProductionStock.specification like ?");
					list.add("%" + specification.replace("*", "") + "%");				
				}else{
					hql.append(" and a.hongXunProductionStock.specification=?");
					list.add(specification);
				}
	    	} 
	    	if (hongXunProductionItemInStock.getStartTime() != null) {
				hql.append(" and a.date >?");
				list.add(hongXunProductionItemInStock.getStartTime());
			}
			if (hongXunProductionItemInStock.getEndTime() != null) {
				hql.append(" and a.date <?");
				list.add(hongXunProductionItemInStock.getEndTime());
			}
			if (hongXunProductionItemInStock.getHongXunProductionStock().getName() != null) {
				hql.append(" and a.hongXunProductionStock.name=?");
				list.add(hongXunProductionItemInStock.getHongXunProductionStock().getName());
			}
			result = iProductionItemInStockDao.findPageByFetchedHql(hql.toString(), "select count(*) " + hql.toString(), pageNo, pageSize,list.toArray());	

		} catch (Exception e) {
			result.setSuccess(false);
			
		}
		return result;
	}

}
