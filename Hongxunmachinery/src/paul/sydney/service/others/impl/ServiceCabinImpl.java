package paul.sydney.service.others.impl;


import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import paul.sydney.model.HongXunPurchaseNum;
import paul.sydney.model.HongXunWeiwaiNum;
import paul.sydney.model.HongXunWeldItemInStock;
import paul.sydney.model.HongXunWeldItemOutStock;
import paul.sydney.service.others.ServiceCabinInf;
import paul.sydney.dao.IMaterialItemOutStockDao;
import paul.sydney.dao.IMaterialOutStockItemDao;
import paul.sydney.dao.IMaterialOutStockNumDao;
import paul.sydney.dao.IMaterialStockDao;
import paul.sydney.dao.IOrderNumDao;
import paul.sydney.dao.IPonDao;
import paul.sydney.dao.IProductionDao;
import paul.sydney.dao.IProductionItemInStockDao;
import paul.sydney.dao.IProductionItemOutStockDao;
import paul.sydney.dao.IPurchaseDao;
import paul.sydney.dao.IPurchaseDeItemDao;
import paul.sydney.dao.IPurchaseItemDao;
import paul.sydney.dao.IPurchaseItemInStockDao;
import paul.sydney.dao.IWeiwaiDao;
import paul.sydney.dao.IWeldDao;
import paul.sydney.dao.StockDao;

import paul.sydney.model.HongXunMaterialItemOutStock;
import paul.sydney.model.HongXunMaterialOutStoreItem;
import paul.sydney.model.HongXunMaterialStock;
import paul.sydney.model.HongXunOrderNum;
import paul.sydney.model.HongXunPoN;
import paul.sydney.model.HongXunProductionItemInStock;
import paul.sydney.model.HongXunProductionItemOutStock;
import paul.sydney.model.HongXunProductionStock;
import paul.sydney.model.HongXunProductionWeldStock;
import paul.sydney.model.HongXunPurchaseItem;
import paul.sydney.model.HongXunPurchaseItemInStock;

import org.springframework.transaction.annotation.Transactional;
@Transactional
@Service("serviceCabin")
public class ServiceCabinImpl implements ServiceCabinInf{
	
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
	IPurchaseItemInStockDao iPurchaseItemInStockDao;
	public void setStockDao(IPurchaseItemInStockDao iPurchaseItemInStockDao) {
		this.iPurchaseItemInStockDao = iPurchaseItemInStockDao;
	}
	
	@Autowired
	StockDao stockDao;
	public void setStockDao(StockDao stockDao) {
		this.stockDao = stockDao;
	}
	@Autowired
	IMaterialStockDao iMaterialStockDao;
	public void setStockDao(IMaterialStockDao iMaterialStockDao) {
		this.iMaterialStockDao = iMaterialStockDao;
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
	@Autowired
	IProductionItemOutStockDao iProductionItemOutStockDao;
	public void setStockDao(IProductionItemOutStockDao iProductionItemOutStockDao) {
		this.iProductionItemOutStockDao = iProductionItemOutStockDao;
	}
	@Autowired
	IWeldDao iWeldDao;
	public void setStockDao(IWeldDao iWeldDao) {
		this.iWeldDao = iWeldDao;
	}
	@Autowired
	IWeiwaiDao iWeiwaiDao;
	public void setStockDao(IWeiwaiDao iWeiwaiDao) {
		this.iWeiwaiDao = iWeiwaiDao;
	}
	@Autowired
	IPonDao iPonDao;
	public void setStockDao(IPonDao iPonDao) {
		this.iPonDao = iPonDao;
	}
	@Autowired
	IOrderNumDao iOrderDao;
	public void setStockDao(IOrderNumDao iOrderDao) {
		this.iOrderDao = iOrderDao;
	}
	@Autowired
	IMaterialOutStockItemDao iMaterialOutStockItemDao;
	public void setStockDao(IMaterialOutStockItemDao iMaterialOutStockItemDao) {
		this.iMaterialOutStockItemDao = iMaterialOutStockItemDao;
	}
	@Autowired
	IMaterialItemOutStockDao iMaterialItemOutStockDao;
	public void setStockDao(IMaterialItemOutStockDao iMaterialItemOutStockDao) {
		this.iMaterialItemOutStockDao = iMaterialItemOutStockDao;
	}
	@Override
	public List<Map<String, Object>> leftTopOne() {
		// TODO Auto-generated method stub		
   		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
   		String year = getSysYear(); 
   		//System.out.println("year:" + year);
   		List<HongXunPurchaseNum> hongXunPurchaseNums = iPurchaseDao.quary(new HongXunPurchaseNum());
   		double purchaseMoney[] = new double[12]; 
   		for(int i=hongXunPurchaseNums.size()-1; i>=0; i--){
   			if(hongXunPurchaseNums.get(i).getPurchaseDate() !=null){
   				String purchaseDate = new SimpleDateFormat("yyyy-MM-dd").format(hongXunPurchaseNums.get(i).getPurchaseDate());
   				//System.out.println("purchase" + purchaseDate.substring(0, 4));
   				if(purchaseDate.substring(0, 4).equals(year)){
   					if(hongXunPurchaseNums.get(i).getPurchaseAmount() != null){
	   					String month = purchaseDate.substring(5, 7);
	   					//System.out.println("month:" + month);
	   					fillParameter(purchaseMoney, month, hongXunPurchaseNums.get(i).getPurchaseAmount().doubleValue());	
   					}
   				}else{
   					break;
   				}
   			}  						
   		}
   		
   		List<HongXunWeiwaiNum> hongXunWeiwaiNums = iWeiwaiDao.quary(new HongXunWeiwaiNum());
   		double weiwaiMoney[] = new double[12]; 
   		for(int i=hongXunWeiwaiNums.size()-1; i>=0; i--){
   			if(hongXunWeiwaiNums.get(i).getWeiwaiMoney() !=null){
   				String weiwaiDate = new SimpleDateFormat("yyyy-MM-dd").format(hongXunWeiwaiNums.get(i).getWeiwaiDate());
   				if(weiwaiDate.substring(0, 4).equals(year)){
   					if(hongXunWeiwaiNums.get(i).getWeiwaiMoney() != null){
   						String month = weiwaiDate.substring(5, 7);
   						fillParameter(weiwaiMoney, month, hongXunWeiwaiNums.get(i).getWeiwaiMoney().doubleValue());	
   					}
   				}else{
   					break;
   				}
   			}  						
   		}
   		
   		List<HongXunOrderNum> hongXunOrderNums = iOrderDao.quary(new HongXunOrderNum());
   		double orderMoney[] = new double[12]; 
   		for(int i=hongXunOrderNums.size()-1; i>=0; i--){
   			if(hongXunOrderNums.get(i).getOrderAmount() !=null){
   				String orderDate = new SimpleDateFormat("yyyy-MM-dd").format(hongXunOrderNums.get(i).getPoCreateDate());
   				if(orderDate.substring(0, 4).equals(year)){
   					if(hongXunOrderNums.get(i).getOrderAmount() != null){
   						String month = orderDate.substring(5, 7);
   						fillParameter(orderMoney, month, hongXunOrderNums.get(i).getOrderAmount().doubleValue());	
   					}
   				}else{
   					break;
   				}
   			}  						
   		}

   		Map<String,Object> map = new HashMap<String,Object>();
   		map.put("purchaseMoney", purchaseMoney);
   		map.put("weiwaiMoney", weiwaiMoney);
   		map.put("orderMoney", orderMoney);
   		list.add(map);
   		return list;
	}
	
	@Override
	public List<Map<String, Object>> leftTopTwo() {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		List<HongXunMaterialStock> hongXunMaterialStocks = iMaterialStockDao.quary(new HongXunMaterialStock());
		String materialNum[] = new String[hongXunMaterialStocks.size()];
		int stockQuantity[] = new int[hongXunMaterialStocks.size()];
		int stockItemQuantity[] = new int[hongXunMaterialStocks.size()];
		int stockInRoadQuantity[] = new int[hongXunMaterialStocks.size()];
		int i = 0;		
		//HongXunPurchaseItem hongXunPurchaseItem = new HongXunPurchaseItem();
		//HongXunPurchaseItemInStock hongXunPurchaseItemInStock = new HongXunPurchaseItemInStock();
		//HongXunMaterialOutStoreItem hongXunMaterialOutStoreItem= new HongXunMaterialOutStoreItem();
		//String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date()).substring(0, 7);
		for(HongXunMaterialStock item: hongXunMaterialStocks){
			materialNum[i] = item.getMaterialNum();
			//System.out.println("materialNum: " + item.getMaterialNum());
			if(item.getQuantity()!=null){
				stockQuantity[i] = item.getQuantity();
			}else{
				stockQuantity[i] = 0;
			}
			if(item.getItemQuantity() != null){
				stockItemQuantity[i] = 0-item.getItemQuantity();
			}
			if(item.getInRoadQuantity() != null){
				stockInRoadQuantity[i] = item.getInRoadQuantity();
			}
			/*hongXunPurchaseItem.setMaterialNum(item.getMaterialNum());
			List<HongXunPurchaseItem> hongXunPurchaseItems = stockDao.quary(hongXunPurchaseItem);
			for(HongXunPurchaseItem item1: hongXunPurchaseItems){
				hongXunPurchaseItemInStock.setPurchaseItemID(item1.getIdc());
				List<HongXunPurchaseItemInStock> hongXunPurchaseItemInStocks = stockDao.quary(hongXunPurchaseItemInStock);
				for(HongXunPurchaseItemInStock item2: hongXunPurchaseItemInStocks){
					String inStockdate = new SimpleDateFormat("yyyy-MM-dd").format(item2.getInstoreDate()).substring(0, 7);
					if(date.equals(inStockdate)){
						stockInQuantity[i] = stockInQuantity[i] + item2.getRealQuantity();
					}
				}
			}*/
			i++;
			//hongXunMaterialOutStoreItem.setMaterialNum(item.getMaterialNum());
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
   		map.put("materialNum", materialNum);
   		map.put("stockQuantity", stockQuantity);
   		map.put("stockItemQuantity", stockItemQuantity);
   		map.put("stockInRoadQuantity", stockInRoadQuantity);
   		list.add(map);
   		return list;	
	}
	
	
	@Override
	public List<Map<String, Object>> leftTopThree() {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		List<HongXunProductionStock> hongXunProductionStocks = iProductionDao.quary(new HongXunProductionStock());
		String materialNum[] = new String[hongXunProductionStocks.size()];
		int stockQuantity[] = new int[hongXunProductionStocks.size()];
		int stockInQuantity[] = new int[hongXunProductionStocks.size()];
		int stockOutQuantity[] = new int[hongXunProductionStocks.size()];
		int i = 0;		
/*		HongXunPurchaseItem hongXunPurchaseItem = new HongXunPurchaseItem();
		HongXunPurchaseItemInStock hongXunPurchaseItemInStock = new HongXunPurchaseItemInStock();
		//HongXunMaterialOutStoreItem hongXunMaterialOutStoreItem= new HongXunMaterialOutStoreItem();
		String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date()).substring(0, 7);*/
		for(HongXunProductionStock item: hongXunProductionStocks){
			materialNum[i] = item.getMaterialNum();
			stockQuantity[i] = item.getQuantity();
			if(item.getItemQuantity()!=null){
				stockInQuantity[i] = item.getItemQuantity();
			}
			if(item.getInRoadQuantity() != null){
				stockOutQuantity[i] = item.getInRoadQuantity();
			}
			i++;
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
   		map.put("materialNum", materialNum);
   		map.put("stockQuantity", stockQuantity);
   		map.put("stockInQuantity", stockInQuantity);
   		map.put("stockOutQuantity", stockOutQuantity);
   		list.add(map);
   		return list;	
	}
	
	@Override
	public List<Map<String, Object>> leftTopFour() {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		List<HongXunProductionWeldStock> hongXunProductionWeldStocks = iWeldDao.quary(new HongXunProductionWeldStock());
		String materialNum[] = new String[hongXunProductionWeldStocks.size()];
		int stockQuantity[] = new int[hongXunProductionWeldStocks.size()];
		int stockInQuantity[] = new int[hongXunProductionWeldStocks.size()];
		int stockOutQuantity[] = new int[hongXunProductionWeldStocks.size()];
		int i = 0;		
/*		HongXunPurchaseItem hongXunPurchaseItem = new HongXunPurchaseItem();
		HongXunPurchaseItemInStock hongXunPurchaseItemInStock = new HongXunPurchaseItemInStock();
		//HongXunMaterialOutStoreItem hongXunMaterialOutStoreItem= new HongXunMaterialOutStoreItem();
		String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date()).substring(0, 7);*/
		for(HongXunProductionWeldStock item: hongXunProductionWeldStocks){
			materialNum[i] = item.getMaterialNum();
			if(item.getQuantity() !=null){
				stockQuantity[i] = item.getQuantity();
			}
			if(item.getItemQuantity()!=null){
				stockInQuantity[i] = item.getItemQuantity();
			}
			if(item.getInRoadQuantity() != null){
				stockOutQuantity[i] = item.getInRoadQuantity();
			}
			i++;
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
   		map.put("materialNum", materialNum);
   		map.put("stockQuantity", stockQuantity);
   		map.put("stockInQuantity", stockInQuantity);
   		map.put("stockOutQuantity", stockOutQuantity);
   		list.add(map);
   		return list;	
	}
	

	@Override
	public List<Map<String, Object>> leftBottomOne() {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();	
		List<HongXunMaterialItemOutStock> hongXunMaterialItemOutStocks = iMaterialItemOutStockDao.quary(new HongXunMaterialItemOutStock());
		List<HongXunPurchaseItemInStock>  hongXunPurchaseItemInStocks = iPurchaseItemInStockDao.quary(new HongXunPurchaseItemInStock());
		//System.out.println("hongXunPurchaseItemInStocks.size() :" + hongXunPurchaseItemInStocks.size());
		//	HongXunPurchaseItem hongXunPurchaseItem = new HongXunPurchaseItem();
		List<String> listNum = new ArrayList<String>();
		List<Integer> listInQuantity = new ArrayList<Integer>();
		List<Integer> listOutQuantity = new ArrayList<Integer>();
		String today =new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		for(HongXunPurchaseItemInStock item: hongXunPurchaseItemInStocks){	
			String date = new SimpleDateFormat("yyyy-MM-dd").format(item.getInstoreDate());	
			if(date.equals(today)){
				HongXunPurchaseItem hongXunPurchaseItem = iPurchaseItemDao.purchaseItemFindById(item.getPurchaseItemID());
				listNum.add(hongXunPurchaseItem.getMaterialNum());
				if(item.getRealQuantity()==null){
					listInQuantity.add(0);
				}else{
					listInQuantity.add(item.getRealQuantity());
				}
				listOutQuantity.add(0);
			}

		}
		
		for(HongXunMaterialItemOutStock item: hongXunMaterialItemOutStocks){
			String date = new SimpleDateFormat("yyyy-MM-dd").format(item.getDate());	
			if(date.equals(today)){
				HongXunMaterialOutStoreItem hongXunMaterialOutStoreItem = iMaterialOutStockItemDao.materialOutStoreFindById(item.getOutStoreItemID()); 			
				int index = listNum.indexOf(hongXunMaterialOutStoreItem.getMaterialNum());
				if(index > -1){
					listOutQuantity.set(index, 0-item.getQuantity());
				}else{
					listNum.add(hongXunMaterialOutStoreItem.getMaterialNum());
					listInQuantity.add(0);			
					listOutQuantity.add(0-item.getQuantity());
				}
			}
		}
		//System.out.println("listNum.size() :" + listNum.size());
		String materialNum[] = new String[listNum.size()];
		int stockQuantity[] = new int[listNum.size()];
		int stockInQuantity[] = new int[listNum.size()];
		int stockOutQuantity[] = new int[listNum.size()];
		HongXunMaterialStock hongXunMaterialStock = new HongXunMaterialStock();
		for(int i=0; i<listNum.size(); i++){
			materialNum[i] 		= listNum.get(i);
			hongXunMaterialStock.setMaterialNum(listNum.get(i));
			List<HongXunMaterialStock> hongXunMaterialStocks = iMaterialStockDao.quary(hongXunMaterialStock);
			if(hongXunMaterialStocks.size()>0){
				stockQuantity[i]=hongXunMaterialStocks.get(0).getQuantity();
			}else{
				System.out.println("原材料库存有重复料号");
			}			
			stockInQuantity[i] 	= listInQuantity.get(i);
			stockOutQuantity[i] = listOutQuantity.get(i);
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
   		map.put("materialNum", materialNum);
   		map.put("stockQuantity", stockQuantity);
   		map.put("stockInQuantity", stockInQuantity);
   		map.put("stockOutQuantity", stockOutQuantity);
   		list.add(map);
   		return list;			
	}
	


	@Override
	public List<Map<String, Object>> leftBottomTwo() {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();	
		List<HongXunProductionItemOutStock> hongXunProductionItemOutStocks = iProductionItemOutStockDao.quary(new HongXunProductionItemOutStock());
		List<HongXunProductionItemInStock>  hongXunProductionItemInStocks = iProductionItemInStockDao.quary(new HongXunProductionItemInStock());
		//System.out.println("hongXunPurchaseItemInStocks.size() :" + hongXunPurchaseItemInStocks.size());
		//	HongXunPurchaseItem hongXunPurchaseItem = new HongXunPurchaseItem();
		List<String> listNum = new ArrayList<String>();
		List<Integer> listInQuantity = new ArrayList<Integer>();
		List<Integer> listOutQuantity = new ArrayList<Integer>();
		String today =new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		for(HongXunProductionItemInStock item: hongXunProductionItemInStocks){	
			String date = new SimpleDateFormat("yyyy-MM-dd").format(item.getDate());	
			if(date.equals(today)){
				HongXunPoN hongXunPoN = iPonDao.hongXunPoNFindById(item.getPoNID());
				listNum.add(hongXunPoN.getMaterialNo());
				if(item.getQuantity()==null){
					listInQuantity.add(0);
				}else{
					listInQuantity.add(item.getQuantity());
				}
				listOutQuantity.add(0);
			}

		}
		
		for(HongXunProductionItemOutStock item: hongXunProductionItemOutStocks){
			String date = new SimpleDateFormat("yyyy-MM-dd").format(item.getDate());	
			if(date.equals(today)){
				HongXunPoN hongXunPoN = iPonDao.hongXunPoNFindById(item.getPoNID());
				int index = listNum.indexOf(hongXunPoN.getMaterialNo());
				if(index > -1){
					listOutQuantity.set(index, 0-item.getQuantity());
				}else{
					listNum.add(hongXunPoN.getMaterialNo());
					listInQuantity.add(0);			
					listOutQuantity.add(0-item.getQuantity());
				}
			}
		}
		//System.out.println("listNum.size() :" + listNum.size());
		String materialNum[] = new String[listNum.size()];
		int stockQuantity[] = new int[listNum.size()];
		int stockInQuantity[] = new int[listNum.size()];
		int stockOutQuantity[] = new int[listNum.size()];
		HongXunProductionStock hongXunProductionStock = new HongXunProductionStock();
		for(int i=0; i<listNum.size(); i++){
			materialNum[i] 		= listNum.get(i);
			hongXunProductionStock.setMaterialNum(listNum.get(i));
			List<HongXunProductionStock> hongXunProductionStocks = iProductionDao.quary(hongXunProductionStock);
			if(hongXunProductionStocks.size()>0){
				stockQuantity[i]=hongXunProductionStocks.get(0).getQuantity();
			}else{
				System.out.println("装配件库存有重复料号");
			}			
			stockInQuantity[i] 	= listInQuantity.get(i);
			stockOutQuantity[i] = listOutQuantity.get(i);
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
   		map.put("materialNum", materialNum);
   		map.put("stockQuantity", stockQuantity);
   		map.put("stockInQuantity", stockInQuantity);
   		map.put("stockOutQuantity", stockOutQuantity);
   		list.add(map);
   		return list;	
	}
	

	@Override
	public List<Map<String, Object>> leftBottomThree() {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();	
		List<HongXunWeldItemOutStock> hongXunWeldItemOutStocks = iWeldDao.quary(new HongXunWeldItemOutStock());
		List<HongXunWeldItemInStock>  hongXunWeldItemInStocks = iWeldDao.quary(new HongXunWeldItemInStock());
		//System.out.println("hongXunPurchaseItemInStocks.size() :" + hongXunPurchaseItemInStocks.size());
		//	HongXunPurchaseItem hongXunPurchaseItem = new HongXunPurchaseItem();
		List<String> listNum = new ArrayList<String>();
		List<Integer> listInQuantity = new ArrayList<Integer>();
		List<Integer> listOutQuantity = new ArrayList<Integer>();
		String today =new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		for(HongXunWeldItemInStock item: hongXunWeldItemInStocks){	
			String date = new SimpleDateFormat("yyyy-MM-dd").format(item.getDate());	
			if(date.equals(today)){
				HongXunPoN hongXunPoN = iPonDao.hongXunPoNFindById(item.getPoNID());
				listNum.add(hongXunPoN.getMaterialNo());
				if(item.getQuantity()==null){
					listInQuantity.add(0);
				}else{
					listInQuantity.add(item.getQuantity());
				}
				listOutQuantity.add(0);
			}

		}
		
		for(HongXunWeldItemOutStock item: hongXunWeldItemOutStocks){
			String date = new SimpleDateFormat("yyyy-MM-dd").format(item.getDate());	
			if(date.equals(today)){
				HongXunPoN hongXunPoN = iPonDao.hongXunPoNFindById(item.getPoNID());
				int index = listNum.indexOf(hongXunPoN.getMaterialNo());
				if(index > -1){
					listOutQuantity.set(index, 0-item.getQuantity());
				}else{
					listNum.add(hongXunPoN.getMaterialNo());
					listInQuantity.add(0);			
					listOutQuantity.add(0-item.getQuantity());
				}
			}
		}
		//System.out.println("listNum.size() :" + listNum.size());
		String materialNum[] = new String[listNum.size()];
		int stockQuantity[] = new int[listNum.size()];
		int stockInQuantity[] = new int[listNum.size()];
		int stockOutQuantity[] = new int[listNum.size()];
		HongXunProductionWeldStock hongXunProductionWeldStock = new HongXunProductionWeldStock();
		for(int i=0; i<listNum.size(); i++){
			materialNum[i] 		= listNum.get(i);
			hongXunProductionWeldStock.setMaterialNum(listNum.get(i));
			List<HongXunProductionWeldStock> hongXunProductionWeldStocks = iWeldDao.quary(hongXunProductionWeldStock);
			if(hongXunProductionWeldStocks.size()>0){
				stockQuantity[i]=hongXunProductionWeldStocks.get(0).getQuantity();
			}else{
				System.out.println("装配件库存有重复料号");
			}			
			stockInQuantity[i] 	= listInQuantity.get(i);
			stockOutQuantity[i] = listOutQuantity.get(i);
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
   		map.put("materialNum", materialNum);
   		map.put("stockQuantity", stockQuantity);
   		map.put("stockInQuantity", stockInQuantity);
   		map.put("stockOutQuantity", stockOutQuantity);
   		list.add(map);
   		return list;	
	}
	
	@Override
	public List<Map<String, Object>> rightPartOne() {
		// TODO Auto-generated method stub
  		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
   		String year = getSysYear(); 
   		//System.out.println("year:" + year);
   		List<HongXunPurchaseNum> hongXunPurchaseNums = iPurchaseDao.quary(new HongXunPurchaseNum());
   		double purchaseMoney = 0; 
   		for(int i=hongXunPurchaseNums.size()-1; i>=0; i--){
   			if(hongXunPurchaseNums.get(i).getPurchaseDate() !=null){
   				String purchaseDate = new SimpleDateFormat("yyyy-MM-dd").format(hongXunPurchaseNums.get(i).getPurchaseDate());
   				//System.out.println("purchase" + purchaseDate.substring(0, 4));
   				if(purchaseDate.substring(0, 4).equals(year)){
   					if(hongXunPurchaseNums.get(i).getPurchaseAmount() != null){
   						purchaseMoney = purchaseMoney + hongXunPurchaseNums.get(i).getPurchaseAmount().doubleValue();
   					}
   				}else{
   					break;
   				}
   			}  						
   		}
   		
   		List<HongXunWeiwaiNum> hongXunWeiwaiNums = iWeiwaiDao.quary(new HongXunWeiwaiNum());
   		double weiwaiMoney = 0; 
   		for(int i=hongXunWeiwaiNums.size()-1; i>=0; i--){
   			if(hongXunWeiwaiNums.get(i).getWeiwaiMoney() !=null){
   				String weiwaiDate = new SimpleDateFormat("yyyy-MM-dd").format(hongXunWeiwaiNums.get(i).getWeiwaiDate());
   				if(weiwaiDate.substring(0, 4).equals(year)){
   					if(hongXunWeiwaiNums.get(i).getWeiwaiMoney() != null){
   						weiwaiMoney = weiwaiMoney + hongXunWeiwaiNums.get(i).getWeiwaiMoney().doubleValue();
   					}
   				}else{
   					break;
   				}
   			}  						
   		}
   		
   		List<HongXunOrderNum> hongXunOrderNums = iOrderDao.quary(new HongXunOrderNum());
   		double orderMoney = 0; 
   		for(int i=hongXunOrderNums.size()-1; i>=0; i--){
   			if(hongXunOrderNums.get(i).getOrderAmount() !=null){
   				String orderDate = new SimpleDateFormat("yyyy-MM-dd").format(hongXunOrderNums.get(i).getPoCreateDate());
   				if(orderDate.substring(0, 4).equals(year)){
   					if(hongXunOrderNums.get(i).getOrderAmount() != null){
   						orderMoney = orderMoney + hongXunOrderNums.get(i).getOrderAmount().doubleValue();
   					}
   				}else{
   					break;
   				}
   			}  						
   		}

   		Map<String,Object> map = new HashMap<String,Object>();
   		map.put("purchaseMoney", purchaseMoney);
   		map.put("weiwaiMoney", weiwaiMoney);
   		map.put("orderMoney", orderMoney);
   		list.add(map);
   		return list;
	}
	
	@Override
	public List<Map<String, Object>> rightPartTwo() {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
   		HongXunPurchaseNum hongXunPurchaseNum = new HongXunPurchaseNum();
   		hongXunPurchaseNum.setPurchaseStatus("部分入库");
   		List<HongXunPurchaseNum> hongXunPurchaseNums = iPurchaseDao.quary(hongXunPurchaseNum);  
		String purchaseNum[] = new String[hongXunPurchaseNums.size()];
		int stockQuantity[] = new int[hongXunPurchaseNums.size()];
		int stockInRoadQuantity[] = new int[hongXunPurchaseNums.size()];
		int stockInQuantity[] = new int[hongXunPurchaseNums.size()];
		HongXunPurchaseItem hongXunPurchaseItem = new HongXunPurchaseItem();
   		for(int i=0;i<hongXunPurchaseNums.size();i++){
   			hongXunPurchaseItem.setPurchaseNumID(hongXunPurchaseNums.get(i).getIdc());
   			List<HongXunPurchaseItem> hongXunPurchaseItems = iPurchaseItemDao.quary(hongXunPurchaseItem);
   			int quantity = 0; int inRoadQuantity = 0; int inQuantity = 0;
   			for(HongXunPurchaseItem item: hongXunPurchaseItems){
   				quantity = quantity + item.getMaterialPurchaseQuantity();
   				if(item.getMaterialRealQuantity() != null){
   					inQuantity = inQuantity + item.getMaterialRealQuantity();
   					inRoadQuantity = inRoadQuantity + (item.getMaterialPurchaseQuantity() - item.getMaterialRealQuantity());
   				}else{
   					inRoadQuantity = inRoadQuantity + item.getMaterialPurchaseQuantity();
   				}
   			}
   			purchaseNum[i] = hongXunPurchaseNums.get(i).getPurchaseNum();
   			stockQuantity[i] = quantity;
   			stockInRoadQuantity[i] = inRoadQuantity;
   			stockInQuantity[i] = inQuantity;		 						
   		}
   		Map<String,Object> map = new HashMap<String,Object>();
   		map.put("purchaseNum", purchaseNum);
   		map.put("stockQuantity", stockQuantity);
   		map.put("stockInRoadQuantity", stockInRoadQuantity);
   		map.put("stockInQuantity", stockInQuantity);
   		list.add(map);
		return list;
	}
	
	@Override
	public List<Map<String, Object>> rightPartThree() {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		List<HongXunPurchaseNum> hongXunPurchaseNums = new ArrayList<HongXunPurchaseNum>();
		HongXunPurchaseNum hongXunPurchaseNum = new HongXunPurchaseNum();
   		String[] strArray={"新增外购","部分入库"};
   		for(int i=0; i<strArray.length; i++){
   			hongXunPurchaseNum.setPurchaseStatus(strArray[i]);
   	   		List<HongXunPurchaseNum> items = iPurchaseDao.quary(hongXunPurchaseNum);
   	   		if((items != null) && (items.size()>0)){
   	   			hongXunPurchaseNums.addAll(items);
   	   		}
   		}
   		List<String> listMaterialNum = new ArrayList<String>();
   		List<Long>	listDeliveryDate = new ArrayList<Long>();
   		List<Long>	listDelayDate = new ArrayList<Long>();
   		//List<Date>	listActualDate = new ArrayList<Date>();		
   		HongXunPurchaseItem hongXunPurchaseItem = new HongXunPurchaseItem();
   		Date today = new Date();
   		for(int i=0;i<hongXunPurchaseNums.size();i++){
   			hongXunPurchaseItem.setPurchaseNumID(hongXunPurchaseNums.get(i).getIdc());
   			List<HongXunPurchaseItem> hongXunPurchaseItems = iPurchaseItemDao.quary(hongXunPurchaseItem);		
   			for(HongXunPurchaseItem item: hongXunPurchaseItems){
   				if(item.getMaterialRealQuantity() != item.getMaterialPurchaseQuantity()){  					
	   				if(item.getDeliveryDate() != null){
	   					long timeStamp = today.getTime() - item.getDeliveryDate().getTime();
	   					if(timeStamp > 0){
	   						//System.out.println(item.getDeliveryDate()); 
	   						listMaterialNum.add(item.getMaterialNum());  
	   						listDeliveryDate.add(timeStamp / (1000 * 24 * 60 * 60));
	   						timeStamp = today.getTime() - hongXunPurchaseNums.get(i).getPurchaseDate().getTime();
	   						listDelayDate.add(timeStamp / (1000 * 24 * 60 * 60));			
	   					}
	   				}
   				}
   			}
   		}
   		
   		String materialNum[] = new String[listMaterialNum.size()];
		Long[] deliveryDate = new Long[listMaterialNum.size()];
		Long[] delayDate = new Long[listMaterialNum.size()];
   		for(int i=0;i<listMaterialNum.size();i++){
   			materialNum[i] = listMaterialNum.get(i);
   			deliveryDate[i] = listDeliveryDate.get(i);
   			delayDate[i] = listDelayDate.get(i);
   		}
   		
   		Map<String,Object> map = new HashMap<String,Object>();
   		map.put("materialNum", materialNum);
   		map.put("deliveryDate", deliveryDate);
   		map.put("delayDate", delayDate);
   		list.add(map);
		return list;
	}
	
	@Override
	public List<Map<String, Object>> rightPartFour() {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		List<HongXunOrderNum> hongXunOrderNums = new ArrayList<HongXunOrderNum>();
   		HongXunOrderNum hongXunOrderNum = new HongXunOrderNum();
   		String[] strArray={"定单已分配","部分入库","部分出库","部分入库,部分出库"};
   		for(int i=0; i<strArray.length; i++){
   			hongXunOrderNum.setStatus(strArray[i]);
   	   		List<HongXunOrderNum> items = iOrderDao.quary(hongXunOrderNum);
   	   		if((items != null) && (items.size()>0)){
   	   			hongXunOrderNums.addAll(items);
   	   		}
   		}
   		String orderNum[] = new String[hongXunOrderNums.size()];
		int analysis[] = new int[hongXunOrderNums.size()];
		int analysis1[] = new int[hongXunOrderNums.size()];
		//int stockQuantity[] = new int[hongXunOrderNums.size()];
		float stockInQuantity[] = new float[hongXunOrderNums.size()];
		float stockInQuantity1[] = new float[hongXunOrderNums.size()];
		float stockOutQuantity[] = new float[hongXunOrderNums.size()];
		float stockOutQuantity1[] = new float[hongXunOrderNums.size()];
		HongXunPoN hongXunPoN = new HongXunPoN();
		for(int i=0;i<hongXunOrderNums.size();i++){
			hongXunPoN.setOrderNumID(hongXunOrderNums.get(i).getIdc());
   			List<HongXunPoN> hongXunPoNs = iPonDao.quary(hongXunPoN);
   			int quantity = 0; int outQuantity = 0; int inQuantity = 0;
   			for(HongXunPoN item: hongXunPoNs){
   				quantity = quantity + item.getQuantity();
   				if(item.getStockInQuantity() != null){
   					inQuantity = inQuantity + item.getStockInQuantity();	
   				}
   				if(item.getStockOutQuantity() != null){
   					outQuantity = outQuantity + item.getStockOutQuantity();
   				}
   			}
   			orderNum[i] = hongXunOrderNums.get(i).getOrderNum();
   			//(float)(Math.round(totalPrice*100)/100);
   			//stockInQuantity[i] = (float)inQuantity / (float)quantity * 10000;
   			stockInQuantity[i]  =   new  BigDecimal((float)inQuantity / (float)quantity * 100).setScale(2,  BigDecimal.ROUND_HALF_UP).floatValue();  
   			//stockInQuantity[i]    =  b.setScale(2,  BigDecimal.ROUND_HALF_UP).floatValue();  
   			stockInQuantity1[i]    =  100 - stockInQuantity[i];
   			
   			stockOutQuantity[i] = new  BigDecimal((float)outQuantity / (float)quantity * 100).setScale(2,  BigDecimal.ROUND_HALF_UP).floatValue();  
   			stockOutQuantity1[i]    =  100 - stockOutQuantity[i];
   			analysis[i] = 100;
   			analysis1[i] = 0;
		}
		Map<String,Object> map = new HashMap<String,Object>();
   		map.put("orderNum", orderNum);
   		map.put("stockInQuantity", stockInQuantity);
   		map.put("stockInQuantity1", stockInQuantity1);
   		map.put("stockOutQuantity", stockOutQuantity);
   		map.put("stockOutQuantity1", stockOutQuantity1);
   		map.put("analysis", analysis);
   		map.put("analysis1", analysis1);
   		list.add(map);
		return list;
   		
	}
	

	@Override
	public List<Map<String, Object>> rightPartFive() {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		List<HongXunOrderNum> hongXunOrderNums = new ArrayList<HongXunOrderNum>();
		HongXunOrderNum hongXunOrderNum = new HongXunOrderNum();
   		String[] strArray={"新订单","部分入库","部分入库,部分出库","部分出库,部分入库"};
   		for(int i=0; i<strArray.length; i++){
   			hongXunOrderNum.setStatus(strArray[i]);
   	   		List<HongXunOrderNum> items = iOrderDao.quary(hongXunOrderNum);
   	   		if((items != null) && (items.size()>0)){
   	   			hongXunOrderNums.addAll(items);
   	   		}
   		}
   		List<String> listMaterialNum = new ArrayList<String>();
   		List<Long>	listDeliveryDate = new ArrayList<Long>();
   		List<Long>	listDelayDate = new ArrayList<Long>();
   		//List<Date>	listActualDate = new ArrayList<Date>();		
   		HongXunPoN hongXunPoN = new HongXunPoN();
   		Date today = new Date();
   		for(int i=0;i<hongXunOrderNums.size();i++){
   			hongXunPoN.setOrderNumID(hongXunOrderNums.get(i).getIdc());
   			List<HongXunPoN> hongXunPoNs = iPonDao.quary(hongXunPoN);		
   			for(HongXunPoN item: hongXunPoNs){
   				if(item.getInroadQuantity() != item.getQuantity()){  					
	   				if(item.getPoDeliveryDate() != null){
	   					long timeStamp = today.getTime() - item.getPoDeliveryDate().getTime();
	   					if(timeStamp > 0){
	   						//System.out.println(item.getDeliveryDate()); 
	   						listMaterialNum.add(item.getMaterialNo());  
	   						listDeliveryDate.add(timeStamp / (1000 * 24 * 60 * 60));
	   						timeStamp = today.getTime() - hongXunOrderNums.get(i).getPoCreateDate().getTime();
	   						listDelayDate.add(timeStamp / (1000 * 24 * 60 * 60));			
	   					}
	   				}
   				}
   			}
   		}
   		
   		String materialNum[] = new String[listMaterialNum.size()];
		Long[] deliveryDate = new Long[listMaterialNum.size()];
		Long[] delayDate = new Long[listMaterialNum.size()];
   		for(int i=0;i<listMaterialNum.size();i++){
   			materialNum[i] = listMaterialNum.get(i);
   			deliveryDate[i] = listDeliveryDate.get(i);
   			delayDate[i] = listDelayDate.get(i);
   		}
   		
   		Map<String,Object> map = new HashMap<String,Object>();
   		map.put("materialNum", materialNum);
   		map.put("deliveryDate", deliveryDate);
   		map.put("delayDate", delayDate);
   		list.add(map);
		return list;
	}
	
	private void fillParameter(double[] purchaseMoney, String month, Double value){
			//System.out.println("month:" + month + "  value:" + value);
			if(month.equals("01")){
				purchaseMoney[0] = purchaseMoney[0] + value;
			}else if(month.equals("02")){
				purchaseMoney[1] = purchaseMoney[1] + value;
			}else if(month.equals("03")){
				purchaseMoney[2] = purchaseMoney[2] + value;
			}else if(month.equals("04")){
				purchaseMoney[3] = purchaseMoney[3] + value;
			}else if(month.equals("05")){
				purchaseMoney[4] = purchaseMoney[4] + value;
			}else if(month.equals("06")){
				purchaseMoney[5] = purchaseMoney[5] + value;
			}else if(month.equals("07")){
				purchaseMoney[6] = purchaseMoney[6] + value;
			}else if(month.equals("08")){
				purchaseMoney[7] = purchaseMoney[7] + value;
			}else if(month.equals("09")){
				purchaseMoney[8] = purchaseMoney[8] + value;
			}else if(month.equals("10")){
				purchaseMoney[9] = purchaseMoney[9] + value;
			}else if(month.equals("11")){
				purchaseMoney[10] = purchaseMoney[10] + value;
			}else if(month.equals("12")){
				purchaseMoney[11] = purchaseMoney[11] + value;
			}   		
	}
	
	private  String getSysYear() {
		Calendar date = Calendar.getInstance();
		String year = String.valueOf(date.get(Calendar.YEAR));
		return year;
	}

	public long getDatePoor(Date endDate, Date nowDate) {
		 
	    long nd = 1000 * 24 * 60 * 60;
	    //long nh = 1000 * 60 * 60;
	    //long nm = 1000 * 60;
	    // long ns = 1000;
	    // 获得两个时间的毫秒时间差异
	    long diff = endDate.getTime() - nowDate.getTime();
	    // 计算差多少天
	    long day = diff / nd;
	    // 计算差多少小时
	    //long hour = diff % nd / nh;
	    // 计算差多少分钟
	    //long min = diff % nd % nh / nm;
	    // 计算差多少秒//输出结果
	    // long sec = diff % nd % nh % nm / ns;
	    //return day + "天" + hour + "小时" + min + "分钟";
	    return day;
	}


}
