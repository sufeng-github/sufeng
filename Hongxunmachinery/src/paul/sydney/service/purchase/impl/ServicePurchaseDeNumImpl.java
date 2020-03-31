package paul.sydney.service.purchase.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import paul.sydney.commen.result.PageResults;
import paul.sydney.dao.IMaterialOutStockItemDao;
import paul.sydney.dao.IMaterialOutStockNumDao;
import paul.sydney.dao.IMaterialStockDao;
import paul.sydney.dao.IPurchaseDao;
import paul.sydney.dao.IPurchaseDeItemDao;
import paul.sydney.dao.IPurchaseDeNumDao;
import paul.sydney.dao.ISuppliersPriceDao;
import paul.sydney.dao.StockDao;
import paul.sydney.model.HongXunPurchaseNum;
import paul.sydney.model.HongXunSuppliersPrice;
import paul.sydney.service.purchase.ServicePurchaseDeNumInf;
import paul.sydney.service.utils.IServiceUtilsInf;
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
	@Autowired
	ISuppliersPriceDao iSuppliersPriceDao;
	public void setStockDao(ISuppliersPriceDao iSuppliersPriceDao) {
		this.iSuppliersPriceDao = iSuppliersPriceDao;
	}
	@Autowired
	IServiceUtilsInf iServiceUtilsInf;
	public void setStockDao(IServiceUtilsInf iServiceUtilsInf) {
		this.iServiceUtilsInf = iServiceUtilsInf;
	}
	
	@Override
	public Boolean deleteRow(HongXunPurchaseDeNum item) {
		// TODO Auto-generated method stub
		item = iPurchaseDeNumDao.get(item.getIdc());
		iPurchaseDeNumDao.delete(item);
		return true;
	}

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
				iPurchaseDeNumDao.updateEntity(item);
			}else{
				iPurchaseDeNumDao.saveEntity(item);
			}
			Map<String, Object> map = new HashMap<String, Object>();
			list.add(map);
		}
		return list;
	}


	@Override
	public void update(HongXunMaterialOutStoreItem hongXunMaterialOutStoreItem) {
		// TODO Auto-generated method stub
		iMaterialOutStockItemDao.updateEntity(hongXunMaterialOutStoreItem);	
	}

	@Override
	public List<Map<String, Object>> createOutStoreSheet(List<HongXunBomTree> hongXunBomTreeList) {
		// TODO Auto-generated method stub
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
    	for(HongXunBomTree item: hongXunBomTreeList){ //生成出库单具体项
    		HongXunMaterialOutStoreItem hongXunMaterialOutStoreItem = new HongXunMaterialOutStoreItem();
    		hongXunMaterialOutStoreItem.setMaterialNum(item.getBomMaterialNum());
    		hongXunMaterialOutStoreItem.setSpecification(item.getBomSpacification());    		
    		hongXunMaterialOutStoreItem.setStoreOutPlanQuantity(item.getBomItemQuantity());
    		if(item.getBomDeliveryDate() ==null){
    		}else{
    			hongXunMaterialOutStoreItem.setStoreOutDate(item.getBomDeliveryDate()); //借用传交期参数
    		}
    		int index = materialNumList.indexOf(item.getBomMaterialNum());
    		if(index>-1){
    			hongXunMaterialOutStoreItemUnite.get(index).setStoreOutPlanQuantity(hongXunMaterialOutStoreItemUnite.get(index).getStoreOutPlanQuantity() + hongXunMaterialOutStoreItem.getStoreOutPlanQuantity());
    		}else{
    			hongXunMaterialOutStoreItemUnite.add(hongXunMaterialOutStoreItem);
	    		materialNumList.add(hongXunMaterialOutStoreItem.getMaterialNum());
    		}
    		hongXunMaterialOutStoreItemList.add(hongXunMaterialOutStoreItem);
    	}
    	
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
    				hongXunMaterialStocks.get(0).setItemQuantity(hongXunMaterialStocks.get(0).getItemQuantity() + item.getStoreOutPlanQuantity());				   			
    			}else{
    				hongXunMaterialStocks.get(0).setItemQuantity(item.getStoreOutPlanQuantity());
    			}
    			if(hongXunMaterialStocks.get(0).getSafeQuantity() == null){
    				hongXunMaterialStocks.get(0).setSafeQuantity(0);
    			}
    			if(hongXunMaterialStocks.get(0).getInRoadQuantity()==null){  				
	    			hongXunMaterialStocks.get(0).setInRoadQuantity(0);
	    		}
	    		if(hongXunMaterialStocks.get(0).getQuantity()-hongXunMaterialStocks.get(0).getItemQuantity() + hongXunMaterialStocks.get(0).getInRoadQuantity() < hongXunMaterialStocks.get(0).getSafeQuantity()){
	    			hongXunMaterialStocks.get(0).setAlarm("报警");
	    			//hongXunMaterialStocks.get(0).setDeliveryDate(item.getStoreOutDate());
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
    			//hongXunMaterialStock.setDeliveryDate(item.getStoreOutDate());
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
    	iMaterialOutStockNumDao.saveEntity(hongXunMaterialOutStoreNum);//保存领料单
    	for(HongXunMaterialOutStoreItem item: hongXunMaterialOutStoreItemList){
    		item.setOutStoreNumID(hongXunMaterialOutStoreNum.getIdc());
    		iMaterialOutStockItemDao.saveEntity(item);
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
    			iMaterialStockDao.updateEntity(item);
    		}else{
    			iMaterialStockDao.saveEntity(item);
    		}
    	}
   		return list;
	}

	@Override
	public List<Map<String, Object>> createNewPurchaseDeNum(List<HongXunMaterialStock> hongXunMaterialStockList) {
		// TODO Auto-generated method stub
		try{
			HongXunPurchaseDeNum hongXunPurchaseDeNum = new HongXunPurchaseDeNum();
	    	hongXunPurchaseDeNum.setPurchaseDeDate(new Date());
	    	Date now = new Date(); 
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//可以方便地修改日期格式
			String purchaseNum = dateFormat.format( now );
			String deliveyDate = purchaseNum.substring(5);
			purchaseNum = purchaseNum.replace("-", "");
			String hql="from HongXunPurchaseDeNum where idc = (select max(idc) from HongXunPurchaseDeNum)";
			HongXunPurchaseDeNum lastNum = iPurchaseDeNumDao.getByHQL(hql);
			if(lastNum==null){
				hongXunPurchaseDeNum.setPurchaseDeNum("HX-" + purchaseNum + "-1");
			}else{
				String index = lastNum.getPurchaseDeNum().split("-")[2];
				int sn = Integer.valueOf(index)+1;
				hongXunPurchaseDeNum.setPurchaseDeNum("HX-" + purchaseNum  + "-" + sn);
			}
			

			hongXunPurchaseDeNum.setHongXunPurchaseDeItems(new HashSet<HongXunPurchaseDeItem>());
			for(HongXunMaterialStock item: hongXunMaterialStockList){
				item.setStatus("已提单(" + deliveyDate +")");
	    		HongXunPurchaseDeItem hongXunPurchaseDeItem = new HongXunPurchaseDeItem();
	    		hongXunPurchaseDeItem.setHongXunPurchaseDeNum(hongXunPurchaseDeNum);
	    		if(item.getPurQuantity()!=null){
	    			hongXunPurchaseDeItem.setQuantity(item.getPurQuantity());
	    		}else{
	    			hongXunPurchaseDeItem.setQuantity(item.getSafeQuantity());
	    		}	   
	    		List<HongXunSuppliersPrice> hongXunSupplierPrices = iSuppliersPriceDao.getListByHQL("from HongXunSuppliersPrice where materialNum =?", item.getMaterialNum());
	       		if(hongXunSupplierPrices.size()==1){
	       			hongXunPurchaseDeItem.setSupplier(hongXunSupplierPrices.get(0).getSupplier());
	       		}
	    		hongXunPurchaseDeItem.setHongXunMaterialStock(item);
	    		iMaterialStockDao.updateEntity(item);
	    		hongXunPurchaseDeNum.getHongXunPurchaseDeItems().add(hongXunPurchaseDeItem);
			}
			iPurchaseDeNumDao.saveEntity(hongXunPurchaseDeNum);
			//iMaterialStockDao.updateEntityAll(hongXunMaterialStockList);
	    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	    	return list;
		} catch (Exception e) {			
			throw e;
		}
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
			
			result = iPurchaseDeNumDao.findPageByFetchedHql(hql.toString(), "select count(*) " + hql.toString(), pageNo,
					pageSize, list.toArray());	
			for(HongXunPurchaseDeNum item1: result.getRows()){
				int cnt = 0;
				for(HongXunPurchaseDeItem item2: item1.getHongXunPurchaseDeItems()){
					if(item2.getStatus()!=null && !item2.getStatus().equals("")){
						cnt++;
					}
				}
				item1.setPurchaseDeStatus(iServiceUtilsInf.print(cnt, item1.getHongXunPurchaseDeItems().size()));
				//System.out.println("cnt:" + cnt + " size:" +  item1.getHongXunPurchaseDeItems().size());
			}
			
		} catch (Exception e) {
			result.setSuccess(false);
		}
		return result;
	}

}
