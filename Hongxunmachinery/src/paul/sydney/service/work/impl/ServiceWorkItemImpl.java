package paul.sydney.service.work.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import paul.sydney.dao.IWorkDao;
import paul.sydney.dao.StockDao;
import paul.sydney.model.HongXunWorkNum;
import paul.sydney.service.work.ServiceWorkItemInf;
import paul.sydney.model.HongXunWorkItem;

@Transactional
@Service("serviceWorkItem")
public class ServiceWorkItemImpl implements ServiceWorkItemInf{
	
	@Autowired
	StockDao stockDao;
	public void setStockDao(StockDao stockDao) {
		this.stockDao = stockDao;
	}
	@Autowired
	IWorkDao iWorkDao;
	public void setStockDao(IWorkDao iWorkDao) {
		this.iWorkDao = iWorkDao;
	}
	@Override
	public List<Map<String, Object>> saveRow(HongXunWorkItem hongXunWorkItem) {
		// TODO Auto-generated method stub
		//System.out.println("serviceoneloadSaveRow");
		iWorkDao.save(hongXunWorkItem);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("idc", hongXunWorkItem.getIdc());
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.add(map);
		return list;
	}
	
	@Override
	public List<Map<String, Object>> deleteRow(HongXunWorkItem hongXunWorkItem) {
		// TODO Auto-generated method stub
		iWorkDao.deletRow(hongXunWorkItem);
		return null;
	}

	@Override
	public List<Map<String, Object>> updateRow(HongXunWorkItem hongXunWorkItem) {
		// TODO Auto-generated method stub
		/*HongXunDataThree tempHongXunDataThree = iWorkDao.dataThreeFindById(hongXunDataThree.getID());	
		Set<HongXunDataThreeBranchOne> hongXunDataThreeBranchOne = tempHongXunDataThree.getHongXunDataThreeBranchOne();
		hongXunDataThree.setHongXunDataThreeBranchOne(hongXunDataThreeBranchOne);*/
		iWorkDao.update(hongXunWorkItem);
		Map<String,Object> map = new HashMap<String,Object>();
		mapHongXunWorkItem(map,hongXunWorkItem);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.add(map);
		return list;
	}

	@Override
	public List<HongXunWorkNum> quary(HongXunWorkNum hongXunDataThree) {
		// TODO Auto-generated method stub
		List<HongXunWorkNum> list = iWorkDao.quary(hongXunDataThree);
		return list;
	}

	@Override
	public List<Map<String, Object>> getWorkNumItems(int id) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		
		@SuppressWarnings("unchecked")
		List<HongXunWorkItem>  hongXunWorkItems =  (List<HongXunWorkItem>) stockDao.quarywithpara("HongXunWorkItem","workNumID",id);
		for(HongXunWorkItem item: hongXunWorkItems){
	   		Map<String,Object> map = new HashMap<String,Object>();
	    	mapHongXunWorkItem(map, item);    
	    	list.add(map);
		}
		/*if(list.size()>0){
			List<Map<String,Object>> listroot = new ArrayList<Map<String,Object>>();
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("remark", "工单号:123456");
			listroot
		}*/
		return list;
	}
	
	private void mapHongXunWorkItem(Map<String, Object> map, HongXunWorkItem item) {
		// TODO Auto-generated method stub
		map.put("idc", item.getIdc());
		map.put("specification", item.getSpecification());
		map.put("orderNum", item.getOrderNum());
		map.put("drawNum", item.getDrawNum());
		map.put("lineNum", item.getLineNum());
		map.put("groupNum", item.getGroupNum());
		map.put("supplier", item.getSupplier());
		map.put("materialNum", item.getMaterialNum());
		map.put("quantity", item.getQuantity());
		map.put("unit", item.getUnit());
		map.put("remark", item.getRemark());
		map.put("attribute", item.getAttribute());
		map.put("state", item.getState());
		map.put("workNumID", item.getWorkNumID());
		map.put("bomMaterialCuting", item.getBomMaterialCuting());
		map.put("bomPlateCuting", item.getBomPlateCuting());
		map.put("bomMaterialWeld", item.getBomMaterialWeld());
		map.put("bomAssemble", item.getBomAssemble());
		map.put("bomPainting", item.getBomPainting());
		map.put("bomWeiwai", item.getBomWeiwai());
		if(item.getWorkDeliveryDate() != null){
			map.put("workDeliveryDate", new SimpleDateFormat("yyyy-MM-dd").format(item.getWorkDeliveryDate()));
		}
	}

}
