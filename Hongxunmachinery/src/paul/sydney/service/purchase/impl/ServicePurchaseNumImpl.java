package paul.sydney.service.purchase.impl;


import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import paul.sydney.dao.IPurchaseItemDao;
import paul.sydney.dao.ISuppliersDao;
import paul.sydney.dao.ISuppliersPriceDao;
import paul.sydney.dao.StockDao;
import paul.sydney.model.HongXunPurchaseNum;
import paul.sydney.model.HongXunSuppliersPrice;
import paul.sydney.service.purchase.ServicePurchaseNumInf;
import paul.sydney.service.utils.IServiceUtilsInf;
import paul.sydney.model.HongXunBomTree;
import paul.sydney.model.HongXunMaterialOutStoreItem;
import paul.sydney.model.HongXunMaterialOutStoreNum;
import paul.sydney.model.HongXunMaterialStock;
import paul.sydney.model.HongXunPurchaseDeItem;
import paul.sydney.model.HongXunPurchaseItem;
import org.springframework.transaction.annotation.Transactional;
@Transactional
@Service("servicePurchaseNum")
public class ServicePurchaseNumImpl implements ServicePurchaseNumInf{
	
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
	IPurchaseItemDao iPurchaseItemDao;
	public void setStockDao(IPurchaseItemDao iPurchaseItemDao) {
		this.iPurchaseItemDao = iPurchaseItemDao;
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
	ISuppliersDao iSuppliersDao;
	public void setStockDao(ISuppliersDao iSuppliersDao) {
		this.iSuppliersDao = iSuppliersDao;
	}
	@Autowired
	ISuppliersPriceDao iSuppliersPriceDao;
	public void setStockDao(ISuppliersPriceDao iSuppliersPriceDao) {
		this.iSuppliersPriceDao = iSuppliersPriceDao;
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
	IServiceUtilsInf iServiceUtilsInf;
	public void setStockDao(IServiceUtilsInf iServiceUtilsInf) {
		this.iServiceUtilsInf = iServiceUtilsInf;
	}
/*	@Override
	public List<Map<String, Object>> loadData() {
		// TODO Auto-generated method stub
		//System.out.println("serviceoneload");
		List<HongXunPurchaseNum> hongXunDataOnes = iPurchaseDao.quary(new HongXunPurchaseNum());
   		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
   		List<Map<String,Object>> listCaptial = new ArrayList<Map<String,Object>>();
   		for(int i=hongXunDataOnes.size()-1; i>=0; i--){
   			Map<String,Object> map = new HashMap<String,Object>();
			mapHongXunPurchaseNum(map, hongXunDataOnes.get(i));
   			if(hongXunDataOnes.get(i).getPurchaseStatus() == null || hongXunDataOnes.get(i).getPurchaseStatus().indexOf("部分")>-1){
   				listCaptial.add(map);
   			}else{
		   	   	list.add(map);	
   			}
   		}
   		listCaptial.addAll(list);
   		return listCaptial;
	}
	*/
	@Override
	public PageResults<HongXunPurchaseNum> getData(HongXunPurchaseNum hongXunPurchaseNum, Integer pageNo,
			Integer pageSize) {
		// TODO Auto-generated method stub
		PageResults<HongXunPurchaseNum> result = new PageResults<HongXunPurchaseNum>();
		try {
			List<Object> list = new ArrayList<Object>();
			StringBuilder hql = new StringBuilder("from HongXunPurchaseNum where 1=1");
			// String类型属性
			if (hongXunPurchaseNum.getPurchaseNum() != null) {
				if (hongXunPurchaseNum.getPurchaseNum().indexOf("*") > -1) {
					hql.append(" and purchaseNum like ?");
					list.add("%" + hongXunPurchaseNum.getPurchaseNum().replace("*", "") + "%");
				} else {
					hql.append(" and purchaseNum = ?");
					list.add(hongXunPurchaseNum.getPurchaseNum());
				}
			}
			if (hongXunPurchaseNum.getPurchaseStatus() != null) {
				if (hongXunPurchaseNum.getPurchaseStatus().indexOf("*") > -1) {
					hql.append(" and purchaseStatus like ?");
					list.add("%" + hongXunPurchaseNum.getPurchaseStatus().replace("*", "") + "%");
				} else {
					hql.append(" and purchaseStatus =?");
					list.add(hongXunPurchaseNum.getPurchaseStatus());
				}
			}

			if (hongXunPurchaseNum.getStartTime() != null) {
				hql.append(" and purchaseDate >?");
				list.add(hongXunPurchaseNum.getStartTime());
			}
			if (hongXunPurchaseNum.getEndTime() != null) {
				hql.append(" and purchaseDate <?");
				list.add(hongXunPurchaseNum.getEndTime());
			}		
			result = iPurchaseDao.findPageByFetchedHql(hql.toString(), "select count(*) " + hql.toString(), pageNo,
					pageSize, list.toArray());
			//System.out.println("result.getRows().size():" + result.getRows().size());
			for(HongXunPurchaseNum item1: result.getRows()){
				int arrivedQuantity = 0;
				int purchaseQuantity = 0;
				BigDecimal money = new BigDecimal(0);
				for(HongXunPurchaseItem item2: item1.getHongXunPurchaseItems()){
					if(item2.getHongXunPurchaseDeItem().getQuantity() != null){
						purchaseQuantity = item2.getHongXunPurchaseDeItem().getQuantity() + purchaseQuantity;
					}
					if(item2.getMaterialRealQuantity() != null){
						arrivedQuantity = item2.getMaterialRealQuantity() + arrivedQuantity;
					}
					if(item2.getMaterialMoney()!=null){						
						money = money.add(item2.getMaterialMoney());						
					}
				}
				item1.setPurchaseStatus(iServiceUtilsInf.print(arrivedQuantity, purchaseQuantity));
				item1.setPurchaseAmount(money);

			}
		} catch (Exception e) {
			result.setSuccess(false);
		}
		return result;
	}
	
	@Override
	public Boolean deleteRow(HongXunPurchaseNum hongXunPurchaseNum) {
		// TODO Auto-generated method stub
		try {
			hongXunPurchaseNum = iPurchaseDao.get(hongXunPurchaseNum.getIdc());		
			Set<HongXunPurchaseItem> hongXunPurchaseItems = hongXunPurchaseNum.getHongXunPurchaseItems();
			for(HongXunPurchaseItem item : hongXunPurchaseItems){			
				HongXunPurchaseDeItem hongXunPurchaseDeItem = item.getHongXunPurchaseDeItem();
				HongXunMaterialStock hongXunMaterialStock = hongXunPurchaseDeItem.getHongXunMaterialStock();
				hongXunMaterialStock.setInRoadQuantity(hongXunMaterialStock.getInRoadQuantity() - item.getHongXunPurchaseDeItem().getQuantity());			
				iMaterialStockDao.updateEntity(hongXunMaterialStock);
				hongXunPurchaseDeItem.setStatus(null);
				iPurchaseDeItemDao.update(hongXunPurchaseDeItem);
				//iPurchaseItemDao.delete(item);
			}
			iPurchaseDao.deletRow(hongXunPurchaseNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;		
	}


	private void mapHongXunPurchaseNum(Map<String, Object> map, HongXunPurchaseNum item) {
		// TODO Auto-generated method stub			
		map.put("idc", item.getIdc());
		map.put("purchaseNum", item.getPurchaseNum());
		map.put("productionOrderNum", item.getProductionItemNum());
		
		map.put("productionDes", item.getProductionDes());
		if(item.getPurchaseDate() != null){
			map.put("purchaseDate", new SimpleDateFormat("yyyy-MM-dd").format(item.getPurchaseDate()));
		}
		if(item.getPurchaseDeliveryDate() != null){
			map.put("purchaseDeliveryDate", new SimpleDateFormat("yyyy-MM-dd").format(item.getPurchaseDeliveryDate()));
		}
		map.put("purchasingAgent", item.getPurchasingAgent());
		map.put("purchaseSupplier", item.getPurchaseSupplier());
		if(item.getPurchaseAmount() != null){
			map.put("purchaseOrderAmount",item.getPurchaseAmount().doubleValue());
		}
		map.put("purchaseStatus", item.getPurchaseStatus());
		map.put("purchaseRemark", item.getPurchaseRemark());	
		map.put("purchaseProcedure", item.getPurchaseProcedure());	
		map.put("purchaseQuantity", item.getPurchaseQuantity());	
		
	}

	

	@Override
	public List<Map<String, Object>> autotimp(String str) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		List<String> listName = new ArrayList<String>();
		@SuppressWarnings("unchecked")
		List<HongXunPurchaseNum> hongXunPurchaseNums = (List<HongXunPurchaseNum>) stockDao.quaryFuzzyWithpara("HongXunPurchaseNum", "purchaseNum", str); 
		
		for(HongXunPurchaseNum item : hongXunPurchaseNums){
			if(!listName.contains(item.getPurchaseNum())){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("id", item.getIdc());
				map.put("name", item.getPurchaseNum());
				listName.add(item.getPurchaseNum());
				list.add(map);
			}
		};
		return list;
	}


	@Override
	public List<Map<String, Object>> saveRow(List<HongXunPurchaseNum> hongXunPurchaseNumList) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		for(HongXunPurchaseNum item: hongXunPurchaseNumList){
			if(item.getIdc() != 0){				
				iPurchaseDao.update(item);
			}else{
				if(item.getPurchaseNum() == null && item.getPurchaseDate() == null){
					item.setPurchaseDate(new Date());
					Date now = new Date(); 
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
					String purchaseNum = dateFormat.format( now ); 
					purchaseNum = purchaseNum.replace("-", "");
					purchaseNum = purchaseNum.replace(":", "");
					purchaseNum = purchaseNum.replace(" ", "");
			    	item.setPurchaseNum(purchaseNum);
			    	item.setPurchaseStatus("新增外购");
				}
				iPurchaseDao.save(item);
			}
			Map<String, Object> map = new HashMap<String, Object>();
			mapHongXunPurchaseNum(map, item);
			list.add(map);
		}
		return list;
	}

	@Override
	public void update(HongXunMaterialOutStoreItem hongXunMaterialOutStoreItem) {
		// TODO Auto-generated method stub
		iMaterialOutStockItemDao.updateEntity(hongXunMaterialOutStoreItem);	
	}
/*	
	@Override
	public void calPurchaseQuantity(int purchaseNumID) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<HongXunPurchaseItem> hongXunPurchaseItems = (List<HongXunPurchaseItem>) stockDao.quarywithpara("HongXunPurchaseItem", "purchaseNumID", purchaseNumID);
		for(HongXunPurchaseItem item: hongXunPurchaseItems){		
			@SuppressWarnings("unchecked")
			List<HongXunMaterialStock> hongXunMaterialStocks = (List<HongXunMaterialStock>) stockDao.quarywithpara("HongXunMaterialStock", "materialNum", item.getMaterialNum());
			if(hongXunMaterialStocks.size()==1){
				Integer safeQuantity = hongXunMaterialStocks.get(0).getSafeQuantity();
				Integer calQuantity = hongXunMaterialStocks.get(0).getCalQuantity();
				Integer realQuantity = hongXunMaterialStocks.get(0).getQuantity();
				int purchaseQuantity;
				if(calQuantity== null){
					calQuantity = realQuantity;
				}
					calQuantity = calQuantity - item.getMaterialPlanQuantity();
					if(calQuantity>=0){
						if(safeQuantity == null){
							purchaseQuantity = 0;							
						}else{		
							if(calQuantity>=safeQuantity){
								purchaseQuantity = 0;
							}else{
								purchaseQuantity = safeQuantity;
								calQuantity = calQuantity + safeQuantity;
							}
						}
					}else{
						if(safeQuantity == null){
							purchaseQuantity = 0- calQuantity;
							calQuantity = 0;
						}else{
							purchaseQuantity=(0 - calQuantity) +  safeQuantity;
							calQuantity =  safeQuantity;
						}
					}				
				
				hongXunMaterialStocks.get(0).setCalQuantity(calQuantity);
				stockDao.update(hongXunMaterialStocks.get(0));	
				item.setMaterialPurchaseQuantity(purchaseQuantity);
				stockDao.update(item);				
			}else if(hongXunMaterialStocks.size()==0){
				item.setMaterialPurchaseQuantity(item.getMaterialPlanQuantity());
				stockDao.update(item);	
			}else{
				System.out.println("有重复料号，请联系工程师");
			}
		}
	}*/

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
	public List<Map<String, Object>> createNewPurchaseNum(List<HongXunPurchaseDeItem> hongXunPurchaseDeItemList) {
		// TODO Auto-generated method stub
		HongXunPurchaseNum hongXunPurchaseNum = new HongXunPurchaseNum();
		Date now = new Date(); 
    	hongXunPurchaseNum.setPurchaseDate(now);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//可以方便地修改日期格式
		String purchaseNum = dateFormat.format( now );
		String deliveyDate = purchaseNum.substring(5);
		purchaseNum = purchaseNum.replace("-", "");
		purchaseNum = purchaseNum.substring(4);
		String hql="from HongXunPurchaseNum where idc = (select max(idc) from HongXunPurchaseNum)";
		HongXunPurchaseNum lastNum = iPurchaseDao.getByHQL(hql);
		if(lastNum==null){
			hongXunPurchaseNum.setPurchaseNum("PX-" + purchaseNum + "-1");
		}else{
			String index = lastNum.getPurchaseNum().split("-")[2];
			int sn = Integer.valueOf(index)+1;
			hongXunPurchaseNum.setPurchaseNum("PX-" + purchaseNum  + "-" + sn);
		}
		hongXunPurchaseNum.setHongXunPurchaseItems(new HashSet<HongXunPurchaseItem>());
    	HongXunSuppliersPrice hongXunSupplierPrice = new HongXunSuppliersPrice();
    	for(HongXunPurchaseDeItem item: hongXunPurchaseDeItemList){
    		item = iPurchaseDeItemDao.get(item.getIdc());
    		HongXunPurchaseItem hongXunPurchaseItem = new HongXunPurchaseItem();
    		hongXunPurchaseItem.setHongXunPurchaseNum(hongXunPurchaseNum);
    		hongXunPurchaseItem.setHongXunPurchaseDeItem(item);
       		hongXunSupplierPrice.setMaterialNum(item.getHongXunMaterialStock().getMaterialNum());
       		List<HongXunSuppliersPrice> hongXunSupplierPrices = iSuppliersPriceDao.quary(hongXunSupplierPrice);
       		if(hongXunSupplierPrices.size()==1){
       			hongXunPurchaseItem.setPrice(hongXunSupplierPrices.get(0).getPrice());
       			double materialMoney = hongXunPurchaseItem.getPrice().doubleValue() * hongXunPurchaseItem.getHongXunPurchaseDeItem().getQuantity();
       			hongXunPurchaseItem.setMaterialMoney(new BigDecimal(Double.toString(materialMoney)));
       			hongXunPurchaseItem.setSupplier(hongXunSupplierPrices.get(0).getSupplier());
       		}
       		hongXunPurchaseNum.getHongXunPurchaseItems().add(hongXunPurchaseItem); 
       		
			if (item.getHongXunMaterialStock().getInRoadQuantity() == null) {
				item.getHongXunMaterialStock().setInRoadQuantity(0);	
			} 
			if (item.getHongXunMaterialStock().getPurQuantity() == null) {
				item.getHongXunMaterialStock().setInRoadQuantity(0);
			} 	
			item.getHongXunMaterialStock().setInRoadQuantity(item.getHongXunMaterialStock().getInRoadQuantity() + item.getQuantity());	
			item.getHongXunMaterialStock().setStatus("已下单(" + deliveyDate + ")");
			//iMaterialStockDao.updateEntity(item.getHongXunMaterialStock());
    		item.setStatus("已下单(" + deliveyDate + ")");
    		iPurchaseDeItemDao.updateEntity(item);
		}
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	iPurchaseDao.saveEntity(hongXunPurchaseNum);


    	return list;
	}

	@Override
	public List<Map<String, Object>> getEntity() {
		// TODO Auto-generated method stub
        return iPurchaseDao.getEntity();
	}



}
