package paul.sydney.service.production.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import paul.sydney.model.HongXunDeliveryItem;
import paul.sydney.model.HongXunDeliveryNum;
import paul.sydney.model.HongXunProductionStock;
import paul.sydney.model.HongXunPurchaseDeNum;
import paul.sydney.service.production.ServiceDeliveryNumInf;
import paul.sydney.service.utils.IServiceUtilsInf;
import paul.sydney.commen.result.PageResults;
import paul.sydney.dao.IProductionDao;
import paul.sydney.dao.IProductionDeliveryItemDao;
import paul.sydney.dao.IProductionDeliveryNumDao;
import paul.sydney.dao.StockDao;
@Transactional
@Service("serviceDeliveryNum")
public class ServiceDeliveryNumImpl implements ServiceDeliveryNumInf{
	
	@Autowired
	StockDao stockDao;
	public void setStockDao(StockDao stockDao) {
		this.stockDao = stockDao;
	}
	@Autowired
	IProductionDao iProductionDao;
	public void setStockDao(IProductionDao iProductionDao) {
		this.iProductionDao = iProductionDao;
	}
	@Autowired
	IProductionDeliveryNumDao iProductionDeliveryNumDao;
	public void setStockDao(IProductionDeliveryNumDao iProductionDeliveryNumDao) {
		this.iProductionDeliveryNumDao = iProductionDeliveryNumDao;
	}
	@Autowired
	IProductionDeliveryItemDao iProductionDeliveryItemDao;
	public void setStockDao(IProductionDeliveryItemDao iProductionDeliveryItemDao) {
		this.iProductionDeliveryItemDao = iProductionDeliveryItemDao;
	}
	@Autowired
	IServiceUtilsInf iServiceUtilsInf;
	public void setStockDao(IServiceUtilsInf iServiceUtilsInf) {
		this.iServiceUtilsInf = iServiceUtilsInf;
	}
	
	@Override
	public List<Map<String, Object>> getEntity() {
		// TODO Auto-generated method stub
		return iProductionDeliveryNumDao.getEntity();
	}

	@Override
	public List<Map<String, Object>> autotimp(String str) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		HongXunDeliveryNum hongXunDeliveryNum = new HongXunDeliveryNum();
		List<HongXunDeliveryNum> hongXunDeliveryNums = iProductionDeliveryNumDao.quary(hongXunDeliveryNum);
		for(HongXunDeliveryNum item: hongXunDeliveryNums){	
			if(item.getDeliveryNum().indexOf(str)>-1){
				Map<String,Object> map = new HashMap<String,Object>();	
				map.put("name", item.getDeliveryNum());
				map.put("id", item.getIdc());  
				list.add(map);
			}
		}
		return list;
	}


	@Override
	public void saveRow(HongXunDeliveryNum hongXunDeliveryNum) {
		// TODO Auto-generated method stub

    	Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//可以方便地修改日期格式
		String purchaseNum = dateFormat.format( now );
		String deliveyDate = purchaseNum.substring(5);
		purchaseNum = deliveyDate.replace("-", "");
		String hql="from HongXunDeliveryNum where idc = (select max(idc) from HongXunDeliveryNum)";
		HongXunDeliveryNum lastNum = iProductionDeliveryNumDao.getByHQL(hql);
		if(lastNum==null){
			hongXunDeliveryNum.setDeliveryNum("DX-" + purchaseNum + "-1");
		}else{
			String index = lastNum.getDeliveryNum().split("-")[2];
			int sn = Integer.valueOf(index)+1;
			hongXunDeliveryNum.setDeliveryNum("DX-" + purchaseNum  + "-" + sn);
		}
		iProductionDeliveryNumDao.save(hongXunDeliveryNum);
	}

	@Override
	public void updateRow(HongXunDeliveryNum item) {
		// TODO Auto-generated method stub
		HongXunDeliveryNum hongXunDeliveryNum = iProductionDeliveryNumDao.get(item.getIdc());
		hongXunDeliveryNum.setCustomer(item.getCustomer());
		hongXunDeliveryNum.setDate(item.getDate());
		hongXunDeliveryNum.setRemark(item.getRemark());
		hongXunDeliveryNum.setSupplierCode(item.getSupplierCode());
		iProductionDeliveryNumDao.update(hongXunDeliveryNum);
	}
	

	@Override
	public void deleteRow(int id) {
		// TODO Auto-generated method stub
		HongXunDeliveryNum hongXunDeliveryNum = iProductionDeliveryNumDao.deliveryNumFindById(id);
		iProductionDeliveryNumDao.delete(hongXunDeliveryNum);

	}


	@Override
	public PageResults<HongXunDeliveryNum> getData(HongXunDeliveryNum hongXunDeliveryNum, Integer pageNo,
			Integer pageSize) {
		// TODO Auto-generated method stub
		List<Object> list = new ArrayList<Object>();
		StringBuilder hql=new StringBuilder("from HongXunDeliveryNum where 1=1");
		//String类型属性
		if (hongXunDeliveryNum.getDeliveryNum() != null) {
			if(hongXunDeliveryNum.getDeliveryNum().indexOf("*")>-1){
				hql.append(" and deliveryNum like ?");
				list.add("%" + hongXunDeliveryNum.getDeliveryNum().replace("*", "") + "%");				
			}else{
				hql.append(" and deliveryNum = ?");
				list.add(hongXunDeliveryNum.getDeliveryNum());
			}
		}
		if (hongXunDeliveryNum.getStartTime() != null) {
			hql.append(" and date >?");
			list.add(hongXunDeliveryNum.getStartTime());
		}
		if (hongXunDeliveryNum.getEndTime() != null) {
			hql.append(" and date <?");
			list.add(hongXunDeliveryNum.getEndTime());
		}
		if(hongXunDeliveryNum.getName() != null){
			hql.append(" and name = ?");
			list.add(hongXunDeliveryNum.getName());
		}
		PageResults<HongXunDeliveryNum> result = iProductionDeliveryNumDao.findPageByFetchedHql(hql.toString(), "select count(*) " + hql.toString(), pageNo, pageSize,list.toArray());
    	 
    	List<HongXunDeliveryNum> hongXunDeliveryNums = result.getRows();
    	for(HongXunDeliveryNum item: hongXunDeliveryNums){
    		Set<HongXunDeliveryItem> hongXunDeliveryItems = item.getHongXunDeliveryItems();
    		int cnt = 0;
    		for(HongXunDeliveryItem item1: hongXunDeliveryItems){
    			if(item1.getStatus().equals("完成")){
    				cnt++;
    			}
    		}
    		item.setStatus(iServiceUtilsInf.print(cnt, hongXunDeliveryItems.size()));
    	}
    	return result;
	}	

}
