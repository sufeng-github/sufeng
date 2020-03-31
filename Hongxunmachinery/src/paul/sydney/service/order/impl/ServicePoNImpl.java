package paul.sydney.service.order.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import paul.sydney.commen.result.PageResults;
import paul.sydney.dao.IOnlineStockDao;
import paul.sydney.dao.IOrderNumDao;
import paul.sydney.dao.IPonDao;
import paul.sydney.dao.ISpecialMaterialDao;
import paul.sydney.dao.StockDao;
import paul.sydney.model.HongXunOnlineStock;
import paul.sydney.model.HongXunOrderNum;
import paul.sydney.model.HongXunPoN;
import paul.sydney.model.HongXunSpecialMaterialNum;
import paul.sydney.service.order.ServicePoNInf;

@Transactional
@Service("servicePoN")
public class ServicePoNImpl implements ServicePoNInf {

	@Autowired
	StockDao stockDao;

	public void setStockDao(StockDao stockDao) {
		this.stockDao = stockDao;
	}
	@Autowired
	ISpecialMaterialDao iSpecialMaterialDao;
	public void setStockDao(ISpecialMaterialDao iSpecialMaterialDao) {
		this.iSpecialMaterialDao = iSpecialMaterialDao;
	}
	@Autowired
	IOnlineStockDao iOnlineStockDao;
	public void setStockDao(IOnlineStockDao iOnlineStockDao) {
		this.iOnlineStockDao = iOnlineStockDao;
	}
	@Autowired
	IOrderNumDao iOrderNumDao;
	public void setStockDao(IOrderNumDao iOrderNumDao) {
		this.iOrderNumDao = iOrderNumDao;
	}
	@Autowired
	IPonDao iPonDao;
	public void setStockDao(IPonDao iPonDao) {
		this.iPonDao = iPonDao;
	}
	

	@Override
	public List<Map<String, Object>> saveRow(HongXunPoN hongXunPoN) {
		// TODO Auto-generated method stub
		iPonDao.save(hongXunPoN);
		Map<String, Object> map = new HashMap<String, Object>();
		mapHongXunPoN(map, hongXunPoN);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list.add(map);
		return list;
	}

	@Override
	public List<Map<String, Object>> updateRow(HongXunPoN hongXunPoN) {
		// TODO Auto-generated method stub
		iPonDao.update(hongXunPoN);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		mapHongXunPoN(map, hongXunPoN);
		list.add(map);
		return list;
	}
	
	@Override
	public List<Map<String, Object>> deleteRow(int id) {	
		HongXunPoN hongXunPoN = iPonDao.hongXunPoNFindById(id);
		iPonDao.delete(hongXunPoN);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		return list;
	}
	

	@Override
	public List<Map<String, Object>> orderNumGetList(int orderNumID) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<HongXunPoN> hongXunPoNs = (List<HongXunPoN>) stockDao.quarywithpara("HongXunPoN", "orderNumID", orderNumID);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
		if (hongXunPoNs != null) {
			for (HongXunPoN item : hongXunPoNs) {
				Map<String, Object> map = new HashMap<String, Object>();
				mapHongXunPoN(map, item);
				if(item.getExemption()!= null && item.getExemption().equals("未扣")){
					list.add(map);
				}else if(item.getExemption()!= null && item.getExemption().equals("已扣")){
					list1.add(map);
				}else{
					list2.add(map);
				}
			}
			list.addAll(list1);
			list.addAll(list2);
		}
		return list;
	}
	
	@Override
	public List<Map<String, Object>> ponNumList(String pon) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		HongXunPoN hongXunPoN = new HongXunPoN();
		hongXunPoN.setPon(pon);
		List<HongXunPoN> hongXunPoNs = iPonDao.quary(hongXunPoN);
		for (HongXunPoN item : hongXunPoNs) {
			Map<String, Object> map = new HashMap<String, Object>();
			mapHongXunPoN(map, item);
			list.add(map);
		}
		return list;
	}
	
	@Override
	public List<Map<String, Object>> updateState(int orderNumID, String state) {
		// TODO Auto-generated method stub
		HongXunOrderNum hongXunOrderNum = iOrderNumDao.orderNumFindById(orderNumID);
		hongXunOrderNum.setStatus(state);
		iOrderNumDao.update(hongXunOrderNum);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		return list;
	}

	@Override
	public List<Map<String, Object>> autotimp(Integer orderNumID, String str) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		//List<String> listName = new ArrayList<String>();
		@SuppressWarnings("unchecked")
		List<HongXunPoN> hongXunPoNs = (List<HongXunPoN>) stockDao.quarywithpara("HongXunPoN", "orderNumID", orderNumID); 

		for(HongXunPoN item : hongXunPoNs){
			if(item.getMaterialNo().indexOf(str)>-1){
				//if(!listName.contains(item.getMaterialNo())){
					Map<String,Object> map = new HashMap<String,Object>();
					map.put("id", item.getIdc());
					//map.put("name", item.getMaterialNo());
					map.put("name", item.getMaterialNo());
					//listName.add(item.getMaterialNo());
					list.add(map);
				//}
			}	
		};
		return list;
	}
	
	@Override
	public List<Map<String, Object>> decrease(int id) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		HongXunPoN hongXunPoN = new HongXunPoN();
		hongXunPoN.setOrderNumID(id);
		List<HongXunPoN>  hongXunPoNs =  iPonDao.quary(hongXunPoN);
		HongXunSpecialMaterialNum hongXunSpecialMaterialNum = new HongXunSpecialMaterialNum();
		hongXunSpecialMaterialNum.setLocaltion("顶层");
		List<HongXunSpecialMaterialNum> hongXunSpecialMaterialNums = iSpecialMaterialDao.quary(hongXunSpecialMaterialNum);
		List<String> set = new ArrayList<String>();
		for(HongXunSpecialMaterialNum item: hongXunSpecialMaterialNums){
			//System.out.println("num:" + item.getMaterialNum());
			set.add(item.getMaterialNum());
		}
		for(HongXunPoN item: hongXunPoNs){	
		//	int stockQuantity =0;
			HongXunOnlineStock hongXunOnlineStock = getOnlineStock(item.getMaterialNo());
			if(set.contains(item.getMaterialNo())){
				item.setExemption("未扣");
				iPonDao.update(item);
			}else if(hongXunOnlineStock != null){
				if(hongXunOnlineStock.getQuantity() != null && hongXunOnlineStock.getQuantity() != 0){
					if(item.getWorkQuantity()> hongXunOnlineStock.getQuantity()){
						item.setWorkQuantity(item.getWorkQuantity()-hongXunOnlineStock.getQuantity());
						hongXunOnlineStock.setQuantity(0);
					}else{		
						hongXunOnlineStock.setQuantity(hongXunOnlineStock.getQuantity() - item.getWorkQuantity());
						item.setWorkQuantity(0);
					}
					item.setExemption("已扣");
					iPonDao.update(item);
					iOnlineStockDao.update(hongXunOnlineStock);
				}
			}
		}	
		return list;
	}
	
	private HongXunOnlineStock getOnlineStock(String materialNum){
		HongXunOnlineStock hongXunOnlineStock = new HongXunOnlineStock();
		hongXunOnlineStock.setMaterialNum(materialNum);
		List<HongXunOnlineStock> hongXunOnlineStocks = iOnlineStockDao.quary(hongXunOnlineStock);
		if(hongXunOnlineStocks.size()>0){
			return hongXunOnlineStocks.get(0);
		}else{
			return null;
		}
	}

	
	private void mapHongXunPoN(Map<String, Object> map, HongXunPoN hongXunPoN) {
		// TODO Auto-generated method stub
		map.put("idc", hongXunPoN.getIdc());
		map.put("supplier", hongXunPoN.getSupplier());
		map.put("pon", hongXunPoN.getPon());
		map.put("line", hongXunPoN.getLine());
		map.put("groupd", hongXunPoN.getGroupd());
		map.put("materialNo", hongXunPoN.getMaterialNo());
		map.put("materialDesc", hongXunPoN.getMaterialDesc());
		map.put("exemption", hongXunPoN.getExemption());
		map.put("unit", hongXunPoN.getUnit());
		if(hongXunPoN.getQuantity()!=null){
			map.put("quantity", hongXunPoN.getQuantity());
		}

		if(hongXunPoN.getPrice()!=null){
			map.put("price", hongXunPoN.getPrice().doubleValue());
		}
		if(hongXunPoN.getTotalAmount()!=null){
			map.put("totalAmount", hongXunPoN.getTotalAmount().doubleValue());
		}		
		if(hongXunPoN.getDeliveredQuantity()!=null){
			map.put("deliveredQuantity", hongXunPoN.getDeliveredQuantity());
		}
		if(hongXunPoN.getReturnedQuantity()!=null){
			map.put("returnedQuantity", hongXunPoN.getReturnedQuantity());
		}
		if(hongXunPoN.getNonDelivery()!=null){
			map.put("nonDelivery", hongXunPoN.getNonDelivery());
		}
		if(hongXunPoN.getReadyQuantity()!=null){
			map.put("readyQuantity", hongXunPoN.getReadyQuantity());
		}
		if(hongXunPoN.getInroadQuantity()!=null){
			map.put("inroadQuantity", hongXunPoN.getInroadQuantity());
		}
		if(hongXunPoN.getWorkQuantity()!=null){
			map.put("workQuantity", hongXunPoN.getWorkQuantity());
		}
		if (hongXunPoN.getPoDeliveryDate() != null) {
			map.put("poDeliveryDate", new SimpleDateFormat("yyyy-MM-dd").format(hongXunPoN.getPoDeliveryDate()));
		}
		
		if (hongXunPoN.getPoCreateDate() != null) {
			map.put("poCreateDate", new SimpleDateFormat("yyyy-MM-dd").format(hongXunPoN.getPoCreateDate()));
		}
/*		if (hongXunPoN.getWorkDeliveryDate() != null) {
			map.put("workDeliveryDate", new SimpleDateFormat("yyyy-MM-dd").format(hongXunPoN.getWorkDeliveryDate()));
		}*/
		
		map.put("orderNumID", hongXunPoN.getOrderNumID());
		map.put("others", hongXunPoN.getOthers());
		map.put("attribute", hongXunPoN.getAttribute());
		map.put("status", hongXunPoN.getStatus());
		map.put("stockInQuantity", hongXunPoN.getStockInQuantity());
		map.put("stockOutQuantity", hongXunPoN.getStockOutQuantity());
		map.put("stockQuantity",getStockQuantity(hongXunPoN.getMaterialNo()));
		
	}
	
	private int getStockQuantity(String materialNum){
		HongXunOnlineStock hongXunOnlineStock = new HongXunOnlineStock();
		hongXunOnlineStock.setMaterialNum(materialNum);
		List<HongXunOnlineStock> hongXunOnlineStocks = iOnlineStockDao.quary(hongXunOnlineStock);
		if((hongXunOnlineStocks !=null) && (hongXunOnlineStocks.size()>0)){
			return hongXunOnlineStocks.get(0).getQuantity();
		}
		return 0;
		
	}

	@Override
	public PageResults<HongXunPoN> getData(HongXunPoN hongXunPoN, Integer pageNo, Integer pageSize) {
		// TODO Auto-generated method stub
		PageResults<HongXunPoN> result = new PageResults<HongXunPoN>();
		try {
			List<Object> list = new ArrayList<Object>();
			StringBuilder hql = new StringBuilder("from HongXunPoN where 1=1");
			if (hongXunPoN.getOrderNumID() != null) {
				hql.append(" and orderNumID = ?");
				list.add(hongXunPoN.getOrderNumID());				
			}
			if (hongXunPoN.getMaterialNo() != null) {
				if (hongXunPoN.getMaterialNo().indexOf("*") > -1) {
					hql.append(" and materialNo like ?");
					list.add("%" + hongXunPoN.getMaterialNo().replace("*", "") + "%");
				} else {
					hql.append(" and materialNo = ?");
					list.add(hongXunPoN.getMaterialNo());
				}
			}
			if (hongXunPoN.getMaterialDesc() != null) {
				if (hongXunPoN.getMaterialDesc().indexOf("*") > -1) {
					hql.append(" and materialDesc like ?");
					list.add("%" + hongXunPoN.getMaterialDesc().replace("*", "") + "%");
				} else {
					hql.append(" and materialDesc = ?");
					list.add(hongXunPoN.getMaterialDesc());
				}
			}
			if (hongXunPoN.getPon() != null) {
				if (hongXunPoN.getPon().indexOf("*") > -1) {
					hql.append(" and pon like ?");
					list.add("%" + hongXunPoN.getPon().replace("*", "") + "%");
				} else {
					hql.append(" and pon = ?");
					list.add(hongXunPoN.getPon());
				}
			}
			if (hongXunPoN.getLine() != null) {
				if (hongXunPoN.getLine().indexOf("*") > -1) {
					hql.append(" and line like ?");
					list.add("%" + hongXunPoN.getLine().replace("*", "") + "%");
				} else {
					hql.append(" and line = ?");
					list.add(hongXunPoN.getLine());
				}
			}
			result = iPonDao.findPageByFetchedHql(hql.toString(), "select count(*) " + hql.toString(), pageNo,
					pageSize, list.toArray());

		} catch (Exception e) {
			result.setSuccess(false);
		}
		return result;
	}

}
