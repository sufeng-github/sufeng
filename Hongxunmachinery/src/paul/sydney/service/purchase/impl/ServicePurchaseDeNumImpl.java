package paul.sydney.service.purchase.impl;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import paul.sydney.commen.result.PageResults;
import paul.sydney.dao.IMaterialOutStockItemDao;
import paul.sydney.dao.IMaterialOutStockNumDao;
import paul.sydney.dao.IMaterialStockDao;
import paul.sydney.dao.IPurchaseDao;
import paul.sydney.dao.IPurchaseDeItemDao;
import paul.sydney.dao.IPurchaseDeNumDao;
import paul.sydney.dao.StockDao;
import paul.sydney.model.HongXunPurchaseNum;
import paul.sydney.service.purchase.ServicePurchaseDeNumInf;
import paul.sydney.model.HongXunBomTree;
import paul.sydney.model.HongXunMaterialOutStoreItem;
import paul.sydney.model.HongXunMaterialOutStoreNum;
import paul.sydney.model.HongXunMaterialStock;
import paul.sydney.model.HongXunPurchaseDeItem;
import paul.sydney.model.HongXunPurchaseDeNum;
import paul.sydney.model.HongXunPurchaseItem;
import org.springframework.transaction.annotation.Transactional;
@Transactional
@Service("servicePurchaseDeNum")
public class ServicePurchaseDeNumImpl implements ServicePurchaseDeNumInf{
	
	@Autowired
	StockDao stockDao;
	public void setStockDao(StockDao stockDao) {
		this.stockDao = stockDao;
	}
	@Autowired
	IPurchaseDao iPurchaseDao;
	public void setStockDao(IPurchaseDao iPurchaseDao) {
		this.iPurchaseDao = iPurchaseDao;
	}
	@Autowired
	IPurchaseDeNumDao iPurchaseDeNumDao;
	public void setStockDao(IPurchaseDeNumDao iPurchaseDeNumDao) {
		this.iPurchaseDeNumDao = iPurchaseDeNumDao;
	}
	@Autowired
	IPurchaseDeItemDao iPurchaseDeItemDao;
	public void setStockDao(IPurchaseDeItemDao iPurchaseDeItemDao) {
		this.iPurchaseDeItemDao = iPurchaseDeItemDao;
	}
	@Autowired
	IMaterialStockDao iMaterialStockDao;
	public void setStockDao(IMaterialStockDao iMaterialStockDao) {
		this.iMaterialStockDao = iMaterialStockDao;
	}
	@Autowired
	IMaterialOutStockNumDao iMaterialOutStockNumDao;
	public void setStockDao(IMaterialOutStockNumDao iMaterialOutStockNumDao) {
		this.iMaterialOutStockNumDao = iMaterialOutStockNumDao;
	}
	@Autowired
	IMaterialOutStockItemDao iMaterialOutStockItemDao;
	public void setStockDao(IMaterialOutStockItemDao iMaterialOutStockItemDao) {
		this.iMaterialOutStockItemDao = iMaterialOutStockItemDao;
	}

	@Override
	public Boolean deleteRow(HongXunPurchaseDeNum item) {
		// TODO Auto-generated method stub
		iPurchaseDeNumDao.deleteById(item.getIdc());
/*		HongXunPurchaseDeNum hongXunPurchaseDeNum = iPurchaseDeNumDao.purchaseDeNumFindById(item.getIdc());
		HongXunPurchaseDeItem hongXunPurchaseDeItem = new HongXunPurchaseDeItem();
		hongXunPurchaseDeItem.setDeNumId(ID);
		List<HongXunPurchaseDeItem> hongXunPurchaseDeItems = iPurchaseDeItemDao.quary(hongXunPurchaseDeItem);
		//System.out.println("hongXunMaterialStocks.size(): " + hongXunMaterialStocks.size());
		iPurchaseDeItemDao.deletePurchaseDeItemList(hongXunPurchaseDeItems);
		iPurchaseDeNumDao.delete(hongXunPurchaseDeNum);*/
		return true;
	}



/*	private void mapHongXunPurchaseDeNum(Map<String, Object> map, HongXunPurchaseDeNum hongXunPurchaseDeNum) {
		// TODO Auto-generated method stub			
		map.put("idc", hongXunPurchaseDeNum.getIdc());
		map.put("purchaseDeNum", hongXunPurchaseDeNum.getPurchaseDeNum());
		if(hongXunPurchaseDeNum.getPurchaseDeDate() != null){
			map.put("purchaseDeDate", new SimpleDateFormat("yyyy-MM-dd").format(hongXunPurchaseDeNum.getPurchaseDeDate()));
		}
		
		map.put("purchaseDeRemark", hongXunPurchaseDeNum.getPurchaseDeRemark());	
		
		HongXunPurchaseDeItem hongXunPurchaseDeItem = new HongXunPurchaseDeItem();
		hongXunPurchaseDeItem.setDeNumId(hongXunPurchaseDeNum.getIdc());		
		int total = iPurchaseDeItemDao.quary(hongXunPurchaseDeItem).size();
		hongXunPurchaseDeItem.setStatus("已提单");
		int decnt = iPurchaseDeItemDao.quary(hongXunPurchaseDeItem).size();
		if(total>0){
			//float u = ((float)total - (float)decnt)/(float)total;
			map.put("purchaseDeStatus", print(decnt, total) + "已下单" );
			
		}
	}*/

/*	 private String print(double num1, double num2)
	  {		
		 double ratio = num1 / num2;
		 // 创建一个数值格式化对象  
		 NumberFormat format = NumberFormat.getPercentInstance();
		 //设置保留几位小数
		 format.setMaximumFractionDigits(2);
		 String result=format.format(ratio);
		 //System.out.println("百分比为1："+result);
		 //double ratioBB=1-ratio;
		 //String resultBB=format.format(ratioBB);
		 //System.out.println("百分比为："+resultBB);
		 return result;
		 
      }*/


	@Override
	public List<Map<String, Object>> autotimp(String str) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		List<String> listName = new ArrayList<String>();
		@SuppressWarnings("unchecked")
		List<HongXunPurchaseDeNum> hongXunPurchaseDeNums = (List<HongXunPurchaseDeNum>) stockDao.quaryFuzzyWithpara("HongXunPurchaseDeNum", "purchaseDeNum", str); 
		
		for(HongXunPurchaseDeNum item : hongXunPurchaseDeNums){
			if(!listName.contains(item.getPurchaseDeNum())){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("id", item.getIdc());
				map.put("name", item.getPurchaseDeNum());
				listName.add(item.getPurchaseDeNum());
				list.add(map);
			}
		};
		return list;
	}


	@Override
	public List<Map<String, Object>> saveRow(List<HongXunPurchaseDeNum> hongXunPurchaseDeNumList) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		for(HongXunPurchaseDeNum item: hongXunPurchaseDeNumList){
			if(item.getIdc() != 0){
				iPurchaseDeNumDao.update(item);
			}else{
				if(item.getPurchaseDeNum() == null && item.getPurchaseDeDate() == null){
					item.setPurchaseDeDate(new Date());
					Date now = new Date(); 
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
					String purchaseNum = dateFormat.format( now ); 
					purchaseNum = purchaseNum.replace("-", "");
					purchaseNum = purchaseNum.replace(":", "");
					purchaseNum = purchaseNum.replace(" ", "");
			    	item.setPurchaseDeNum(purchaseNum);
			    	item.setPurchaseDeStatus("已提单");
				}
				iPurchaseDeNumDao.save(item);
			}
			Map<String, Object> map = new HashMap<String, Object>();
			//mapHongXunPurchaseNum(map, item);
			list.add(map);
		}
		return list;
	}


	@Override
	public void update(HongXunMaterialOutStoreItem hongXunMaterialOutStoreItem) {
		// TODO Auto-generated method stub
		iMaterialOutStockItemDao.update(hongXunMaterialOutStoreItem);	
	}

	@Override
	public List<Map<String, Object>> createOutStoreSheet(List<HongXunBomTree> hongXunBomTreeList) {
		// TODO Auto-generated method stub
		//SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	HongXunMaterialOutStoreNum hongXunMaterialOutStoreNum = new HongXunMaterialOutStoreNum();
    	hongXunMaterialOutStoreNum.setOutStoreNum(hongXunBomTreeList.get(0).getStatus());   	
    	hongXunMaterialOutStoreNum.setWorkProcedure(hongXunBomTreeList.get(0).getRemark());
    	hongXunMaterialOutStoreNum.setState("新增领料单");
    	//service.saveRow(hongXunMaterialOutStoreNum);
    	
    	hongXunBomTreeList = hongXunBomTreeList.subList(1, hongXunBomTreeList.size());
    	List<HongXunMaterialOutStoreItem> hongXunMaterialOutStoreItemList = new ArrayList<HongXunMaterialOutStoreItem>();
    	List<HongXunMaterialOutStoreItem> hongXunMaterialOutStoreItemUnite = new ArrayList<HongXunMaterialOutStoreItem>();
    	List<String> materialNumList = new ArrayList<String>();
    	int cnt = 0;

/*    	for(HongXunBomTree item: hongXunBomTreeList){
    		materialNumList.add(item.getBomMaterialNum());
    	}
    	System.out.println(materialNumList.size());
    	materialNumList = materialNumList.stream().distinct().collect(Collectors.toList());
    	System.out.println(materialNumList.size());
    	for(String str: materialNumList){
    		for(HongXunBomTree item: hongXunBomTreeList){
    			
    		}
    	}*/
    	for(HongXunBomTree item: hongXunBomTreeList){ //生成出库单具体项
    		HongXunMaterialOutStoreItem hongXunMaterialOutStoreItem = new HongXunMaterialOutStoreItem();
    		hongXunMaterialOutStoreItem.setMaterialNum(item.getBomMaterialNum());
    		hongXunMaterialOutStoreItem.setSpecification(item.getBomSpacification());    		
    		hongXunMaterialOutStoreItem.setStoreOutPlanQuantity(item.getBomItemQuantity());
    		if(item.getBomDeliveryDate() ==null){
    			System.out.println(item.getBomMaterialNum() + "交货日期为空");
    		}else{
    			//System.out.println("交货日期：" + item.getBomDeliveryDate());
    			hongXunMaterialOutStoreItem.setStoreOutDate(item.getBomDeliveryDate()); //借用传交期参数
    		}
    		//service.save(hongXunMaterialOutStoreItem);
    			
    		//boolean flag = false;
    		
    		int index = materialNumList.indexOf(item.getBomMaterialNum());
    		if(index>-1){
    			hongXunMaterialOutStoreItemUnite.get(index).setStoreOutPlanQuantity(hongXunMaterialOutStoreItemUnite.get(index).getStoreOutPlanQuantity() + hongXunMaterialOutStoreItem.getStoreOutPlanQuantity());
    			/*if(hongXunMaterialOutStoreItemList.get(index).getStoreOutDate().after(item.getBomDeliveryDate())){ //合并选择最早日期
    				hongXunMaterialOutStoreItemList.get(index).setStoreOutDate(item.getBomDeliveryDate());
    			}*/

    			//stockDao.update(hongXunMaterialOutStoreItemList.get(index));	
    		}else{
    			//stockDao.save(hongXunMaterialOutStoreItem);
    			hongXunMaterialOutStoreItemUnite.add(hongXunMaterialOutStoreItem);
	    		materialNumList.add(hongXunMaterialOutStoreItem.getMaterialNum());
    		}
    		hongXunMaterialOutStoreItemList.add(hongXunMaterialOutStoreItem);
    	}
    	
    	//System.out.println("我等到花了也谢了" + hongXunMaterialOutStoreItemUnite.size());
    	List<HongXunMaterialStock> hongXunMaterialStockList = new ArrayList<HongXunMaterialStock>();
    	for(HongXunMaterialOutStoreItem item: hongXunMaterialOutStoreItemUnite){ //设置项目数，比较安全库存是否报警
    		//stockDao.save(item);
    		HongXunMaterialStock hongXunMaterialStockEntity = new HongXunMaterialStock();
    		hongXunMaterialStockEntity.setMaterialNum(item.getMaterialNum());
    		//@SuppressWarnings("unchecked")
    		List<HongXunMaterialStock> hongXunMaterialStocks = iMaterialStockDao.quary(hongXunMaterialStockEntity);//(List<HongXunMaterialStock>) stockDao.quarywithpara("HongXunMaterialStock", "materialNum", item.getMaterialNum()); 
    		if(hongXunMaterialStocks.size()==1){
    			//System.out.println("终于进来了");
    			if(hongXunMaterialStocks.get(0).getItemQuantity() !=null){  
    				//hongXunMaterialStocks.get(0).setSafeQuantity(hongXunMaterialStocks.get(0).getItemQuantity());
    				hongXunMaterialStocks.get(0).setItemQuantity(hongXunMaterialStocks.get(0).getItemQuantity() + item.getStoreOutPlanQuantity());				   			
    				//hongXunMaterialStocks.get(0).setInRoadQuantity(item.getStoreOutPlanQuantity());
    			}else{
    				//System.out.println("2");
    				//hongXunMaterialStocks.get(0).setSafeQuantity(0);
    				hongXunMaterialStocks.get(0).setItemQuantity(item.getStoreOutPlanQuantity());
    				//hongXunMaterialStocks.get(0).setInRoadQuantity(item.getStoreOutPlanQuantity());
    			}
    			if(hongXunMaterialStocks.get(0).getSafeQuantity() == null){
    				hongXunMaterialStocks.get(0).setSafeQuantity(0);
    			}
    			if(hongXunMaterialStocks.get(0).getInRoadQuantity()==null){  				
	    			hongXunMaterialStocks.get(0).setInRoadQuantity(0);
	    		}
	    		if(hongXunMaterialStocks.get(0).getQuantity()-hongXunMaterialStocks.get(0).getItemQuantity() + hongXunMaterialStocks.get(0).getInRoadQuantity() < hongXunMaterialStocks.get(0).getSafeQuantity()){
	    			hongXunMaterialStocks.get(0).setAlarm("报警");
	    			hongXunMaterialStocks.get(0).setDeliveryDate(item.getStoreOutDate());
	    		}
	    		hongXunMaterialStockList.add(hongXunMaterialStocks.get(0));
    			//stockDao.update(hongXunMaterialStocks.get(0));
    		}else if(hongXunMaterialStocks.size()>1){
    			System.out.println(" 实时库存有重复料号:" + item.getMaterialNum());
    		}else{
    			System.out.println("新物料：" + item.getMaterialNum());
    			HongXunMaterialStock hongXunMaterialStock = new HongXunMaterialStock();
    			hongXunMaterialStock.setMaterialNum(item.getMaterialNum());
    			hongXunMaterialStock.setSpecification(item.getSpecification());
    			hongXunMaterialStock.setDeliveryDate(item.getStoreOutDate());
    			hongXunMaterialStock.setQuantity(0);
    			hongXunMaterialStock.setSafeQuantity(0);
    			hongXunMaterialStock.setInRoadQuantity(0);
    			hongXunMaterialStock.setItemQuantity(item.getStoreOutPlanQuantity());
    			hongXunMaterialStock.setAlarm("报警");
    			hongXunMaterialStock.setRemark("新物料");
    			hongXunMaterialStockList.add(hongXunMaterialStock);
    		}	
    	}
    	
    	hongXunMaterialOutStoreNum.setQuantity(cnt);
    	iMaterialOutStockNumDao.save(hongXunMaterialOutStoreNum);//保存领料单
    	for(HongXunMaterialOutStoreItem item: hongXunMaterialOutStoreItemList){
    		item.setOutStoreNumID(hongXunMaterialOutStoreNum.getIdc());
    		iMaterialOutStockItemDao.save(item);
    	}
		List<String> listStr = new ArrayList<String>();
		List<HongXunMaterialStock> listhongXunMaterialStock = new ArrayList<HongXunMaterialStock>();
		int index=0;
		for(HongXunMaterialStock item: hongXunMaterialStockList){
			String str = item.getMaterialNum();
			index = listStr.indexOf(str);
			if(index > -1){
				listhongXunMaterialStock.get(index).setItemQuantity(listhongXunMaterialStock.get(index).getItemQuantity() + item.getItemQuantity());
			}else{
				listStr.add(str);
				listhongXunMaterialStock.add(item);	
			}
		}
    	
    	for(HongXunMaterialStock item: listhongXunMaterialStock){
    		if(item.getIdc()!=0){
    			iMaterialStockDao.update(item);
    		}else{
    			iMaterialStockDao.save(item);
    		}
    	}
   		return list;
	}

	@Override
	public List<Map<String, Object>> createNewPurchaseDeNum(List<HongXunMaterialStock> hongXunMaterialStockList) {
		// TODO Auto-generated method stub
		HongXunPurchaseDeNum hongXunPurchaseDeNum = new HongXunPurchaseDeNum();
		List<HongXunPurchaseDeNum> hongXunPurchaseDeNums = iPurchaseDeNumDao.quary(hongXunPurchaseDeNum);
		
    	hongXunPurchaseDeNum.setPurchaseDeDate(new Date());
    	Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//可以方便地修改日期格式
		String purchaseNum = dateFormat.format( now ); 
		purchaseNum = purchaseNum.replace("-", "");
		if(hongXunPurchaseDeNums.size()==0){
			hongXunPurchaseDeNum.setPurchaseDeNum("HX-" + purchaseNum + "-1");
		}else{
			String index = hongXunPurchaseDeNums.get(hongXunPurchaseDeNums.size()-1).getPurchaseDeNum().split("-")[2];
			int sn = Integer.valueOf(index)+1;
			hongXunPurchaseDeNum.setPurchaseDeNum("HX-" + purchaseNum  + "-" + sn);
		}
	//	Set<HongXunPurchaseDeItem> hongXunPurchaseDeItems = (Set<HongXunPurchaseDeItem>) new ArrayList<HongXunPurchaseDeItem>();
		//hongXunPurchaseDeNum.setHongXunPurchaseDeItems(new HashSet<HongXunPurchaseDeItem>());
		iPurchaseDeNumDao.saveEntity(hongXunPurchaseDeNum);
		for(HongXunMaterialStock item: hongXunMaterialStockList){
/*    		if(item.getPurQuantity()==null){
    			item.setPurQuantity(item.getSafeQuantity());
    		}*/
    		//
    		HongXunPurchaseDeItem hongXunPurchaseDeItem = new HongXunPurchaseDeItem();
    		hongXunPurchaseDeItem.setStatus("已提单");
    		if(item.getPurQuantity()!=null){
    			hongXunPurchaseDeItem.setQuantity(item.getPurQuantity());
    		}else{
    			hongXunPurchaseDeItem.setQuantity(item.getSafeQuantity());
    		}
    		//hongXunPurchaseDeItem.setMaterialId(item.getIdc());
    		hongXunPurchaseDeItem.setDeNumId(hongXunPurchaseDeNum.getIdc());
    		hongXunPurchaseDeItem.setHongXunMaterialStock(item);
    		//hongXunPurchaseDeNum.getHongXunPurchaseDeItems().add(hongXunPurchaseDeItem);
    		iPurchaseDeItemDao.saveEntity(hongXunPurchaseDeItem);
    		//item.setPurchaseDeId(hongXunPurchaseDeNum.getIdc());
    		//stockDao.update(item);
		}
    	
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	return list;
	}

	@Override
	public List<Map<String, Object>> getEntity() {
		// TODO Auto-generated method stub
		return iPurchaseDeNumDao.getEntity();

	}

	@Override
	public List<Map<String, Object>> updateSotockState(int purchaseNumID) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    	@SuppressWarnings("unchecked")
		List<HongXunPurchaseItem> hongXunPurchaseItems = (List<HongXunPurchaseItem>) stockDao.quarywithpara("HongXunPurchaseItem", "purchaseNumID", purchaseNumID); 
		boolean flag = false; boolean flag1 = false; String status="";
    	for(HongXunPurchaseItem item : hongXunPurchaseItems){
	   		if(item.getMaterialRealQuantity() != null){
	    		if(item.getMaterialPurchaseQuantity() > item.getMaterialRealQuantity()){		
		    		//System.out.println("部分入库1");
		    		status = "部分入库";
		    		break;	    		
		    	}else if(item.getMaterialPurchaseQuantity() == item.getMaterialRealQuantity()){
		    		if(flag1 == true){
		    			//System.out.println("部分入库2");
		    			status = "部分入库";
		    			break;
		    		}else{
		    			flag = true;	    			
		    		}
		    	}
	   		}else{
		    	if(flag == true){
		    		//System.out.println("部分入库3");
		    		status = "部分入库";
		    		break;
		    	}else{
		    		flag1 = true;
		    	}
		    }   		
    	}

    	Map<String,Object> map = new HashMap<String,Object>();
    	HongXunPurchaseNum hongXunPurchaseNum = iPurchaseDao.purchaseNumFindById(purchaseNumID);
		if(status.equals("")){	
			if(flag==false){
				status = hongXunPurchaseNum.getPurchaseStatus();				
			}else{
				status = "入库完成";								
			}
			
			//System.out.println("status1:" + hongXunOrderNum.getStatus());
		}
		map.put("purchaseStatus", status);
	
		hongXunPurchaseNum.setPurchaseStatus(status);
		iPurchaseDao.update(hongXunPurchaseNum);
		list.add(map);	
		return list;
	}

	@Override
	public PageResults<HongXunPurchaseDeNum> getData(HongXunPurchaseDeNum hongXunPurchaseDeNum, Integer pageNo,
			Integer pageSize) {
		// TODO Auto-generated method stub
		//HongXunPurchaseDeNum HongXunPurchaseDeNum = new HongXunPurchaseDeNum();
		
		//System.out.println(HongXunPurchaseDeNum + "---" +iPurchaseDeNumDao.quary(HongXunPurchaseDeNum).size());
		PageResults<HongXunPurchaseDeNum> result = new PageResults<HongXunPurchaseDeNum>();
		try {
			List<Object> list = new ArrayList<Object>();
			StringBuilder hql = new StringBuilder("from HongXunPurchaseDeNum where 1=1");
			// String类型属性
			if (hongXunPurchaseDeNum.getPurchaseDeNum() != null) {
				if (hongXunPurchaseDeNum.getPurchaseDeNum().indexOf("*") > -1) {
					hql.append(" and purchaseDeNum like ?");
					list.add("%" + hongXunPurchaseDeNum.getPurchaseDeNum().replace("*", "") + "%");
				} else {
					hql.append(" and purchaseDeNum = ?");
					list.add(hongXunPurchaseDeNum.getPurchaseDeNum());
				}
			}
			if (hongXunPurchaseDeNum.getPurchaseDeStatus() != null) {
				if (hongXunPurchaseDeNum.getPurchaseDeStatus().indexOf("*") > -1) {
					hql.append(" and purchaseDeStatus like ?");
					list.add("%" + hongXunPurchaseDeNum.getPurchaseDeStatus().replace("*", "") + "%");
				} else {
					hql.append(" and purchaseDeStatus =?");
					list.add(hongXunPurchaseDeNum.getPurchaseDeStatus());
				}
			}

			if (hongXunPurchaseDeNum.getStartTime() != null) {
				hql.append(" and purchaseDeDate >?");
				list.add(hongXunPurchaseDeNum.getStartTime());
			}
			if (hongXunPurchaseDeNum.getEndTime() != null) {
				hql.append(" and purchaseDeDate <?");
				list.add(hongXunPurchaseDeNum.getEndTime());
			}
			//System.out.println(hql.toString());
			result = iPurchaseDeNumDao.findPageByFetchedHql(hql.toString(), "select count(*) " + hql.toString(), pageNo,
					pageSize, list.toArray());

		} catch (Exception e) {
			result.setSuccess(false);
		}
		return result;
	}

}
