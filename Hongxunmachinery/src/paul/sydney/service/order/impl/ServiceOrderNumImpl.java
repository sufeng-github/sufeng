package paul.sydney.service.order.impl;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import paul.sydney.dao.IMaterialOutStockItemDao;
import paul.sydney.dao.IMaterialOutStockNumDao;
import paul.sydney.dao.IMaterialStockDao;
import paul.sydney.dao.IOrderNumDao;
import paul.sydney.dao.IPonDao;
import paul.sydney.dao.IWeiwaiDao;
import paul.sydney.dao.StockDao;
import paul.sydney.model.HongXunMaterialOutStoreItem;
import paul.sydney.model.HongXunMaterialOutStoreNum;
import paul.sydney.model.HongXunMaterialStock;
import paul.sydney.model.HongXunOnlineStock;
import paul.sydney.model.HongXunOrderNum;
import paul.sydney.model.HongXunPoN;
import paul.sydney.model.HongXunWeiwaiItem;
import paul.sydney.model.HongXunWeiwaiNum;
import paul.sydney.service.order.ServiceOrderNumInf;
@Transactional
@Service("orderNum")
public class ServiceOrderNumImpl implements ServiceOrderNumInf {

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
	IWeiwaiDao iWeiwaiDao;
	public void setStockDao(IWeiwaiDao iWeiwaiDao) {
		this.iWeiwaiDao = iWeiwaiDao;
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
	public List<Map<String, Object>> loadData() {
		// TODO Auto-generated method stub
		//System.out.println("serviceTenload");
		List<HongXunOrderNum> hongXunOrderNums = iOrderNumDao.quary(new HongXunOrderNum());
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		//Date date = new Date();
		//SimpleDateFormat dpr = new SimpleDateFormat("yyyy-MM-dd");
		//String today = dpr.format(date);
		for (HongXunOrderNum item : hongXunOrderNums) {
		//	if(item.getPoCreateDate() != null){
		//		String itemDay = dpr.format(item.getPoCreateDate());
		//		if(today.equals(itemDay)){
			Map<String, Object> map = new HashMap<String, Object>();
			mapHongXunOrderNum(map, item);	
			//list.add(map);
			list.add( 0, map);
				//}
		//	}
		}
		return list;
	}
	
	@Override
	public List<Map<String, Object>> changeStatus(int id) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		@SuppressWarnings("unchecked")
		List<HongXunPoN>  hongXunPoNs =  (List<HongXunPoN>) stockDao.quarywithpara("HongXunPoN","orderNumID",id);
		boolean flag = false; boolean flag1 = false; boolean flag2 = false;String status="";
		boolean flag_ = false; boolean flag1_ = false; boolean flag2_ = false; String status_="";BigDecimal sum = null;
		//Map<String,Object> map = new HashMap<String,Object>();
		for(HongXunPoN item: hongXunPoNs){
			if(flag2==false){
				if(item.getStockInQuantity()!=null){
					//System.out.println(item.getStoreOutPlanQuantity()+"::"+item.getStoreOutRealQuantity());
					if(item.getQuantity()>item.getStockInQuantity()){												    		
			    		status = "部分入库";
			    		flag2=true;   		
			    	}else if(item.getQuantity() == item.getStockInQuantity()){
			    		//System.out.println("asf8888");
			    		if(flag1 == true){		    			
			    			status = "部分入库";
			    			flag2=true;   
			    		}else{
			    			//System.out.println("部分入库2");
			    			flag = true;	    			
			    		}
			    	}
		   		}else{
		   			//System.out.println("部分入库3");
			    	if(flag == true){		    		
			    		status = "部分入库";
			    		flag2=true;   
			    	}else{
			    		flag1 = true;
			    	}
			    } 
			}
			if(flag2_==false){
				if(item.getStockOutQuantity()!=null){
					//System.out.println(item.getStoreOutPlanQuantity()+"::"+item.getStoreOutRealQuantity());
					if(item.getQuantity()>item.getStockOutQuantity()){												    		
			    		status_ = "部分出库";
			    		flag2_=true;   		
			    	}else if(item.getQuantity() == item.getStockOutQuantity()){
			    		//System.out.println("asf8888");
			    		if(flag1_ == true){		    			
			    			status_ = "部分出库";
			    			flag2_=true;   
			    		}else{
			    			//System.out.println("部分入库2");
			    			flag_ = true;	    			
			    		}
			    	}
		   		}else{
		   			//System.out.println("部分入库3");
			    	if(flag_ == true){		    		
			    		status_ = "部分出库";
			    		flag2_ = true;   
			    	}else{
			    		flag1_ = true;
			    	}
			    } 
			}
			if(item.getTotalAmount() != null){
    			if(sum==null){
    				sum = item.getTotalAmount();
    			}else{
    				sum = sum.add(item.getTotalAmount());
    				//System.out.println(sum.toString());
    			}
    		}
		}

		HongXunOrderNum hongXunOrderNum = iOrderNumDao.orderNumFindById(id);
		Map<String,Object> map = new HashMap<String,Object>();
		if(status.equals("")){	
			if(flag==false){					
				status = hongXunOrderNum.getStatus();			
			}else{				
				status = "入库完成";						
			}			
		}
		if(status_.equals("")){	
			if(flag_==false){					
				status_ = hongXunOrderNum.getStatus();			
			}else{				
				status_ = "出库完成";						
			}			
		}
		if((status.indexOf("入库")>-1) && (status_.indexOf("出库")>-1)){
			status = status+","+status_;
		//}else if(status.indexOf("入库")>-1) {	
		}else if(status_.indexOf("出库")>-1) {
			status = status_;
		}
		
		map.put("status", status);
		hongXunOrderNum.setStatus(status);
/*		for(HongXunPoN item: hongXunPoNs){
			
		}*/
		//HongXunOrderNum hongXunOrderNum = iOrderNumDao.orderNumFindById(id);
/*		if(status.equals("")){	
			map.put("status", hongXunOrderNum.getStatus());
			//System.out.println("status1:" + hongXunOrderNum.getStatus());
		}else{
			map.put("status", status);
			hongXunOrderNum.setStatus(status);
			//System.out.println("status2:" + status);
		}*/
		
		if(sum != null){	
			hongXunOrderNum.setOrderAmount(sum);	
			map.put("orderAmount", sum);
		}else{
			map.put("orderAmount", "");
		}
		list.add(map);
		
		iOrderNumDao.update(hongXunOrderNum);
		
		return list;
	}
			
	
	
	@Override
	public List<Map<String, Object>> saveRow(HongXunOrderNum hongXunOrderNum) {
		// TODO Auto-generated method stub
		// System.out.println("serviceoneloadSaveRow");
		iOrderNumDao.save(hongXunOrderNum);
		Map<String, Object> map = new HashMap<String, Object>();
		//map.put("idc", hongXunOrderNum.getIdc());
		mapHongXunOrderNum(map, hongXunOrderNum);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list.add(map);
		return list;
	}

	@Override
	public List<Map<String, Object>> updateRow(HongXunOrderNum hongXunOrderNum) {
		// TODO Auto-generated method stub
		System.out.println("HongXunOrderNum updateRow");
		iOrderNumDao.update(hongXunOrderNum);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		mapHongXunOrderNum(map, hongXunOrderNum);
		list.add(map);
		return list;
	}
	@Override
	public List<Map<String, Object>> deleteRow(HongXunOrderNum hongXunOrderNum) {
		// TODO Auto-generated method stub
		List<HongXunOnlineStock> hongXunOnlineStockList = new ArrayList<HongXunOnlineStock>();
		@SuppressWarnings("unchecked")
		List<HongXunPoN>  hongXunPoNs =  (List<HongXunPoN>) stockDao.quarywithpara("HongXunPoN","orderNumID",hongXunOrderNum.getIdc());
		//String orderNum = hongXunOrderNum.getOrderNum();
		//删除时成品仓库，在产与发货去除相应的数量。
		for(HongXunPoN item: hongXunPoNs){
			int result = item.getQuantity()-item.getWorkQuantity();
			if(result > 0){
				if(item.getExemption()==null || (!item.getExemption().equals("未扣"))){
					@SuppressWarnings("unchecked")
					List<HongXunOnlineStock>  hongXunOnlineStocks =  (List<HongXunOnlineStock>) stockDao.quarywithpara("HongXunOnlineStock","materialNum",item.getMaterialNo());
					System.out.println("hongXunOnlineStocks.size(): " +hongXunOnlineStocks.size());
					if(hongXunOnlineStocks.size()>=1){
						//hongXunProductionStocks.get(0).setInRoadQuantity(hongXunProductionStocks.get(0).getInRoadQuantity()-item.getWorkQuantity());
						//hongXunProductionStocks.get(0).setItemQuantity(hongXunProductionStocks.get(0).getItemQuantity()-item.getQuantity());
						System.out.println("item.getQuantity() :" + item.getQuantity());
						System.out.println("item.getWorkQuantity() :" + item.getWorkQuantity());			
						hongXunOnlineStocks.get(0).setQuantity(hongXunOnlineStocks.get(0).getQuantity() + result);
						hongXunOnlineStockList.add(hongXunOnlineStocks.get(0));
						//stockDao.update(hongXunProductionStock);
					}
				}
			}
		}
		
		List<HongXunMaterialStock> hongXunMaterialStockList = new ArrayList<HongXunMaterialStock>();
		List<HongXunMaterialOutStoreItem> hongXunMaterialOutStoreItemList = new ArrayList<HongXunMaterialOutStoreItem>();
		List<HongXunMaterialOutStoreNum> hongXunMaterialOutStoreNumList = new ArrayList<HongXunMaterialOutStoreNum>();
		@SuppressWarnings("unchecked")
		List<HongXunMaterialOutStoreNum>  hongXunMaterialOutStoreNums =  (List<HongXunMaterialOutStoreNum>) stockDao.quarywithpara("HongXunMaterialOutStoreNum","outStoreNum",hongXunOrderNum.getOrderNum());	
		List<String> listStr = new ArrayList<String>();
		List<HongXunMaterialOutStoreItem> hongXunMaterialOutStoreItemUnite = new ArrayList<HongXunMaterialOutStoreItem>();
		int index=0;
		for(HongXunMaterialOutStoreNum item: hongXunMaterialOutStoreNums){
			@SuppressWarnings("unchecked")
			List<HongXunMaterialOutStoreItem>  hongXunMaterialOutStoreItems =  (List<HongXunMaterialOutStoreItem>) stockDao.quarywithpara("HongXunMaterialOutStoreItem","outStoreNumID",item.getIdc());
			/*for(HongXunMaterialOutStoreItem hongXunMaterialOutStoreItem: hongXunMaterialOutStoreItems){   
				String str = hongXunMaterialOutStoreItem.getMaterialNum();
				index = listStr.indexOf(str);
				if(index > -1){
					hongXunMaterialOutStoreItemUnite.get(index).setStoreOutPlanQuantity(hongXunMaterialOutStoreItemUnite.get(index).getStoreOutPlanQuantity() + hongXunMaterialOutStoreItem.getStoreOutPlanQuantity());
				}else{
					listStr.add(str);
					hongXunMaterialOutStoreItemUnite.add(hongXunMaterialOutStoreItem);	
				}
	    	}*/
			hongXunMaterialOutStoreItemList.addAll(hongXunMaterialOutStoreItems);
			hongXunMaterialOutStoreNumList.add(item);
		}
		
		for(HongXunMaterialOutStoreItem item: hongXunMaterialOutStoreItemUnite){  
    		@SuppressWarnings("unchecked")
    		List<HongXunMaterialStock> hongXunMaterialStocks = (List<HongXunMaterialStock>) stockDao.quarywithpara("HongXunMaterialStock", "materialNum", item.getMaterialNum()); 
    		if(hongXunMaterialStocks.size()==1){   			
    			//System.out.println("终于进来了");
    			if(hongXunMaterialStocks.get(0).getItemQuantity() !=null){  	
    				//System.out.println(hongXunMaterialStocks.get(0).getItemQuantity() +"-" + item.getStoreOutPlanQuantity()); 
    				hongXunMaterialStocks.get(0).setItemQuantity(hongXunMaterialStocks.get(0).getItemQuantity() - item.getStoreOutPlanQuantity());						
    				//System.out.println(hongXunMaterialStocks.get(0).getItemQuantity());
    			}
		    	if(hongXunMaterialStocks.get(0).getQuantity()-hongXunMaterialStocks.get(0).getItemQuantity()+hongXunMaterialStocks.get(0).getInRoadQuantity() >= hongXunMaterialStocks.get(0).getSafeQuantity()){
		    		hongXunMaterialStocks.get(0).setAlarm(null);
		    	}
		    	//hongXunMaterialStockList.add(hongXunMaterialStocks.get(0));
		    	
		    	String str = hongXunMaterialStocks.get(0).getMaterialNum();
				index = listStr.indexOf(str);
				if(index > -1){
					hongXunMaterialStockList.get(index).setItemQuantity(hongXunMaterialStocks.get(0).getItemQuantity());
				}else{
					listStr.add(str);
					hongXunMaterialStockList.add(hongXunMaterialStocks.get(0));	
				}
    			//stockDao.update(hongXunMaterialStocks.get(0));
    		}else if(hongXunMaterialStocks.size()>1){
    			System.out.println("实时库存，物料号：" + item.getMaterialNum() + "有重复");
    		}else{
    			System.out.println("实时库存，物料号：" + item.getMaterialNum() + "无记录");
    		}
		}

		
		List<HongXunWeiwaiItem> hongXunWeiwaiItemList = new ArrayList<HongXunWeiwaiItem>();  
		List<HongXunWeiwaiNum>  hongXunWeiwaiNumList = new ArrayList<HongXunWeiwaiNum>(); 
		@SuppressWarnings("unchecked")
		List<HongXunWeiwaiNum>  hongXunWeiwaiNums =  (List<HongXunWeiwaiNum>) stockDao.quarywithpara("HongXunWeiwaiNum", "weiwaiNum", hongXunOrderNum.getOrderNum());	
		for(HongXunWeiwaiNum item: hongXunWeiwaiNums){
			@SuppressWarnings("unchecked")
			List<HongXunWeiwaiItem>  hongXunWeiwaiItems =  (List<HongXunWeiwaiItem>) stockDao.quarywithpara("HongXunWeiwaiItem","weiwaiNumID",item.getIdc());
			hongXunWeiwaiItemList.addAll(hongXunWeiwaiItems);
			hongXunWeiwaiNumList.add(item);
		}

		
		for(HongXunOnlineStock hongXunOnlineStock: hongXunOnlineStockList){
			stockDao.update(hongXunOnlineStock);
		}
		iPonDao.deletePoNList(hongXunPoNs);
		iOrderNumDao.delete(hongXunOrderNum);
		for(HongXunMaterialStock hongXunMaterialStock: hongXunMaterialStockList){
			iMaterialStockDao.update(hongXunMaterialStock);
		}
		iMaterialOutStockItemDao.deleteOutStoreItemList(hongXunMaterialOutStoreItemList);
		iMaterialOutStockNumDao.deleteOutStoreNumList(hongXunMaterialOutStoreNumList);
		iWeiwaiDao.deleteWeiwaiItemList(hongXunWeiwaiItemList);
		iWeiwaiDao.deleteWeiwaiNumList(hongXunWeiwaiNumList);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		return list;
	}

	
	@Override
	public List<Map<String, Object>> orderNumImport(HSSFWorkbook workbook) {
		// TODO Auto-generated method stub
		// HSSFWorkbook workbook = null;

		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		List<String> list = new ArrayList<String>();
		int cnt = 0;
		HongXunOrderNum hongXunOrderNum = new HongXunOrderNum();
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 可以方便地修改日期格式
		String orderNum = dateFormat.format(now);
		orderNum = orderNum.replace("-", "");
		orderNum = orderNum.replace(":", "");
		orderNum = orderNum.replace(" ", "");
		hongXunOrderNum.setOrderNum(orderNum);
		hongXunOrderNum.setStatus("新工单");
		hongXunOrderNum.setPoCreateDate(new Date());
		// System.out.println("orderNumImport");
		iOrderNumDao.save(hongXunOrderNum);
		List<HongXunPoN> hongXunPoNs = new ArrayList<HongXunPoN>();
		// List<HongXunOnlineStock> hongXunProductionStockList = new
		// ArrayList<HongXunOnlineStock>();

		try {
			// Workbook workbook = WorkbookFactory.create(is);
			// workbook = new HSSFWorkbook(fs);
			HSSFSheet sheet = workbook.getSheetAt(0);
			int count = 0;
			// HongXunProductionStock hongXunProductionStock = new
			// HongXunProductionStock();
			// HongXunProductionWeldStock hongXunProductionWeldStock = new
			// HongXunProductionWeldStock();
			for (Row row : sheet) {
				if (row.getCell(0) == null || row.getCell(0).toString().equals("")) {
					break;
				}
				int end = row.getLastCellNum();
				HongXunPoN hongXunPoN = new HongXunPoN();

				for (int i = 0; i < end; i++) {
					Cell cell = row.getCell(i);
					Object obj = getValue(cell);
					if (obj != null) {
						// System.out.print(obj.toString() + "\t");
						if (count < 1) {
							list.add(obj.toString());
						} else {
							// System.out.println(list.size() + ":" + i);

							String temStr = list.get(i);
							if (!temStr.equals("")) {
								// System.out.println(temStr);
								if (temStr.equals("Supplier") || temStr.equals("供应商")) {
									hongXunPoN.setSupplier(obj.toString());
								} else if (temStr.equals("PO#") || temStr.equals("PO号")) {
									hongXunPoN.setPon(obj.toString());
								} else if (temStr.equals("Line") || temStr.equals("行号")) {
									hongXunPoN.setLine(obj.toString());
								} else if (temStr.equals("Group") || temStr.equals("采购组")) {
									hongXunPoN.setGroupd(obj.toString());
								} else if (temStr.equals("Material No.") || temStr.equals("物料号")) {
									hongXunPoN.setMaterialNo(obj.toString());
									/*
									 * int quantity =
									 * getStockQuantity(hongXunProductionStock,
									 * hongXunProductionWeldStock,hongXunPoN.
									 * getMaterialNo());
									 * hongXunPoN.setStockQuantity(quantity);
									 */
								} else if (temStr.equals("Material Desc.") || temStr.equals("物料描述")) {
									hongXunPoN.setMaterialDesc(obj.toString());
								} else if (temStr.equals("Exemption") || temStr.equals("免检")) {
									hongXunPoN.setExemption(obj.toString());
								} else if (temStr.equals("Unit") || temStr.equals("单位")) {
									hongXunPoN.setUnit(obj.toString());
								} else if (temStr.equals("Quantity") || temStr.equals("数量")) {
									String str = obj.toString();
									// System.out.println("quantity:" + str);
									if (isNum(str)) {
										hongXunPoN.setQuantity(Double.valueOf(str).intValue());
										hongXunPoN.setWorkQuantity(hongXunPoN.getQuantity());
									}
								} else if (temStr.equals("Price") || temStr.equals("单价")) {
									String str = obj.toString();
									str = str.replace(" /PC", "");
									// System.out.println("" + str)
									if (isNum(str)) {
										hongXunPoN.setPrice(new BigDecimal(str));
									}
								} else if (temStr.equals("Total Amount") || temStr.equals("价格")) {
									String str = obj.toString();
									if (isNum(str)) {
										hongXunPoN.setTotalAmount(new BigDecimal(str));
									}

								} else if (temStr.equals("Delivered Quantity") || temStr.equals("已交数量")) {
									String str = obj.toString();
									if (isNum(str)) {
										hongXunPoN.setDeliveredQuantity(Double.valueOf(str).intValue());
									}
								} else if (temStr.equals("Returned Quantity") || temStr.equals("退货数量")) {
									String str = obj.toString();
									if (isNum(str)) {
										hongXunPoN.setReturnedQuantity(Double.valueOf(str).intValue());
									}
								} else if (temStr.equals("Non-delivery") || temStr.equals("未交数量")) {
									String str = obj.toString();
									if (isNum(str)) {
										hongXunPoN.setNonDelivery(Double.valueOf(str).intValue());
									}
								} else if (temStr.equals("备货数量")) {
									String str = obj.toString();
									if (isNum(str)) {
										hongXunPoN.setReadyQuantity(Double.valueOf(str).intValue());
									}
								} else if (temStr.equals("在途数量")) {
									String str = obj.toString();
									if (isNum(str)) {
										hongXunPoN.setInroadQuantity(Double.valueOf(str).intValue());
									}
								} else if (temStr.equals("PO交期")) {
									SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
									Date d = sdf.parse(obj.toString());
									hongXunPoN.setPoDeliveryDate(d);

								} else if (temStr.equals("PO创建日期")) {
									SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
									Date d = sdf.parse(obj.toString());
									hongXunPoN.setPoCreateDate(d);
									
									if(cnt == 0){
										//hongXunOrderNum.setPoCreateDate(d); 
										hongXunOrderNum.setOrderNum(sdf.format(d));
									}
									 
								} else if (temStr.equals("生产交期")) {
									SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
									Date d = sdf.parse(obj.toString());
									hongXunPoN.setWorkDeliveryDate(d);
								} else if (temStr.equals("Others") || temStr.equals("其他")) {
									hongXunPoN.setOthers(obj.toString());
								}
							}
						}
					}
				}
				if (count < 1) {
					// System.out.println("" + row.getLastCellNum());
					count++;
				} else {
					// 给PO单在导入时加属性 A01,A02等，暂时无用
					/*
					 * @SuppressWarnings("unchecked") List<HongXunBomTree>
					 * hongXunBomTrees = (List<HongXunBomTree>)
					 * stockDao.quarywithpara("HongXunBomTree","bomMaterialNum",
					 * hongXunPoN.getMaterialNo()); for(HongXunBomTree item:
					 * hongXunBomTrees){ if(item.getSn()==1){
					 * hongXunPoN.setAttribute(hongXunBomTrees.get(0).
					 * getBomGroup()); break; } }
					 */
					// 依据成品仓库做生产量的计算，成品仓库的在产与发货量也随着改变，初期不用。
					/*
					 * @SuppressWarnings("unchecked")
					 * List<HongXunProductionStock> hongXunProductionStocks =
					 * (List<HongXunProductionStock>)
					 * stockDao.quarywithpara("HongXunProductionStock",
					 * "materialNum",hongXunPoN.getMaterialNo());
					 * if(hongXunProductionStocks.size()>=1){
					 * if(hongXunProductionStocks.size()>1){
					 * System.out.println("成品仓库有重复料号");
					 * //System.out.println(hongXunProductionStocks.get(0).
					 * getSpecification()+ " : " +
					 * hongXunProductionStocks.get(1).getSpecification()); } int
					 * safeQuantity = 0; int inRoadQuantity = 0; int
					 * itemQuantity = 0;
					 * if(hongXunProductionStocks.get(0).getQuantity()!=null){
					 * if(hongXunProductionStocks.get(0).getSafeQuantity()!=null
					 * ){ safeQuantity =
					 * hongXunProductionStocks.get(0).getSafeQuantity(); }
					 * if(hongXunProductionStocks.get(0).getInRoadQuantity()!=
					 * null){ inRoadQuantity =
					 * hongXunProductionStocks.get(0).getInRoadQuantity(); }
					 * if(hongXunProductionStocks.get(0).getItemQuantity()!=null
					 * ){ itemQuantity =
					 * hongXunProductionStocks.get(0).getItemQuantity(); } int
					 * result = hongXunProductionStocks.get(0).getQuantity() +
					 * inRoadQuantity - safeQuantity - itemQuantity; if(result
					 * >= hongXunPoN.getQuantity()){
					 * hongXunPoN.setWorkQuantity(0); }else{
					 * hongXunPoN.setWorkQuantity(hongXunPoN.getQuantity()-
					 * result); } }else{
					 * hongXunPoN.setWorkQuantity(hongXunPoN.getQuantity()); }
					 * hongXunProductionStocks.get(0).setInRoadQuantity(
					 * inRoadQuantity + hongXunPoN.getWorkQuantity());
					 * hongXunProductionStocks.get(0).setItemQuantity(
					 * itemQuantity + hongXunPoN.getQuantity());
					 * //stockDao.update(hongXunProductionStocks.get(0));
					 * hongXunProductionStockList.add(hongXunProductionStocks.
					 * get(0)); }else{
					 * hongXunPoN.setWorkQuantity(hongXunPoN.getQuantity()); }
					 */

					hongXunPoN.setOrderNumID(hongXunOrderNum.getIdc());

					// stockDao.save(hongXunPoN);
					hongXunPoNs.add(hongXunPoN);

					cnt++;
					// System.out.println(hongXunPoN.getMaterialNo());
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// stockDao.saveOrUpDate(hongXunPoNs);
		Session session = null;
		boolean flag = false;
		try {
			if (session == null) {
				session = stockDao.session();
				session.beginTransaction(); // 开启事物
			}
			// int i = 0;
			for (HongXunPoN hongXunPoN : hongXunPoNs) {
				// System.out.println(i++);
				session.save(hongXunPoN);
			}
			session.getTransaction().commit(); // 提交事物
			flag = true;
		} catch (Exception e) {
			e.printStackTrace(); // 打印错误信息
			session.getTransaction().rollback(); // 出错将回滚事物
			iOrderNumDao.delete(hongXunOrderNum);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", "导入文件失败");
			listmap.add(map);

			return listmap;
		} finally {
			// session.flush();//清理缓存，执行批量更新20条记录的SQL update语句
			// session.clear();
			session.close();
		}
		if (flag == true) {
			/*
			 * for(HongXunProductionStock hongXunProductionStock:
			 * hongXunProductionStockList){
			 * stockDao.update(hongXunProductionStock); }
			 */
			hongXunOrderNum.setOrderQuantity(String.valueOf(cnt));
			iOrderNumDao.update(hongXunOrderNum);
		}

		Map<String, Object> map = new HashMap<String, Object>();
		mapHongXunOrderNum(map, hongXunOrderNum);
		listmap.add(map);
		return listmap;
	}


	private String getValue(Cell cell) {
		if(cell == null){
			return null;
		}
		String obj = null;
		switch (cell.getCellTypeEnum()) {
		case BOOLEAN:
			obj = String.valueOf(cell.getBooleanCellValue());
			break;
		case ERROR:
			obj = String.valueOf(cell.getErrorCellValue());
			break;
		case NUMERIC:		
			String str = String.valueOf(cell.getNumericCellValue());  
			//System.out.println("num:" + cell.getNumericCellValue());
			BigDecimal bd = new BigDecimal(replaceBlank(str));	
			obj = bd.stripTrailingZeros().toPlainString();
			break;
		case STRING:
			String str1 = cell.getStringCellValue();
			if(str1.equals("")){
				obj=null;
			}else{
				obj = replaceBlank(str1);
			}
			break;
		default:
			//System.out.println("read getvalue default");
			obj = null;
			break;
		}
		return obj;
	}

	
	private String replaceBlank(String str) {
		String dest = "";
		if (str!=null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}
	private boolean isNum(String str) {
		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
	}

	
	private void mapHongXunOrderNum(Map<String, Object> map, HongXunOrderNum item) {
		// TODO Auto-generated method stub
		map.put("idc", item.getIdc());
		map.put("orderAmount", item.getOrderAmount());
		if(item.getPoCreateDate() != null){
			map.put("poCreateDate", new SimpleDateFormat("yyyy-MM-dd").format(item.getPoCreateDate()));
		}
		map.put("orderNum", item.getOrderNum());
		map.put("orderQuantity", item.getOrderQuantity());
		map.put("remark", item.getRemark());
		map.put("status", item.getStatus());
	}

	@Override
	public List<Map<String, Object>> getEntity() {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String,Object>();
		HongXunOrderNum e = new HongXunOrderNum();
		Class<? extends HongXunOrderNum> cls = e.getClass();  
        Field[] fields = cls.getDeclaredFields();  
        for(int i=0; i<fields.length; i++){  
            Field f = fields[i];  
            f.setAccessible(true); 
            map.put(f.getName(), "");
            //System.out.println("属性名:" + f.getName()/* + " 属性值:" + f.get(e)*/);  
        } 
        list.add(map);
        return list;
	}

	
	@Override
	public List<Map<String, Object>> betweenDateFind(String date1, String date2) throws ParseException{
		// TODO Auto-generated method stub
		Date beginDate;
    	Date endDate;
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    	if(df.parse(date1).before(df.parse(date2))){
    		//System.out.println("0");
        	beginDate= df.parse(date1);
        	endDate = df.parse(date2);
    	}else{
    		//System.out.println("1");
        	beginDate= df.parse(date2); 
        	endDate = df.parse(date1);
    	}
    	 Calendar c = Calendar.getInstance();
         c.setTime(endDate);
         c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
         endDate = c.getTime();
    	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    	List<HongXunOrderNum> hongXunOrderNums = iOrderNumDao.quary(new HongXunOrderNum());
    	for(HongXunOrderNum item: hongXunOrderNums){
    		//System.out.println("date: " + item.getPoCreateDate());
			if(beginDate.getTime()<= item.getPoCreateDate().getTime() && endDate.getTime() >= item.getPoCreateDate().getTime()){
				//System.out.println("date: ");
       			Map<String,Object> map = new HashMap<String,Object>();
       			mapHongXunOrderNum(map, item);
       	   		list.add(map);	
			}
    	}
    	return list;
	}

	@Override
	public List<Map<String, Object>> autotimp(String str) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		List<String> listName = new ArrayList<String>();
		@SuppressWarnings("unchecked")
		List<HongXunOrderNum> hongXunOrderNums = (List<HongXunOrderNum>) stockDao.quaryFuzzyWithpara("HongXunOrderNum", "orderNum", str); 	
		for(HongXunOrderNum item : hongXunOrderNums){
			if(!listName.contains(item.getOrderNum())){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("id", item.getIdc());
				map.put("name", item.getOrderNum());
				listName.add(item.getOrderNum());
				list.add(map);
			}
		};
		return list;
	}

	@Override
	public List<Map<String, Object>> searchNum(String orderNum) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<HongXunOrderNum> hongXunOrderNums = (List<HongXunOrderNum>) stockDao.quarywithpara("HongXunOrderNum", "orderNum", orderNum);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		//if (hongXunOrderNums != null) {
			for (HongXunOrderNum item : hongXunOrderNums) {
				Map<String, Object> map = new HashMap<String, Object>();
				mapHongXunOrderNum(map, item);
				list.add(map);
			}
		//}
		return list;
	}

}




	/*private List<HongXunBomTree> getBomList(String materialNum){
		@SuppressWarnings("unchecked")
		List<HongXunBomTree> hongXunBomTrees = (List<HongXunBomTree>)stockDao.quarywithpara("HongXunBomTree", "BomMaterialNum", materialNum);
		if(hongXunBomTrees.size()==1){
			hongXunBomTrees
		}else{
			
		}
		return hongXunBomTrees;
	}*/
	
/*			int proInstoreQuantity = 0;
			@SuppressWarnings("unchecked")
			List<HongXunProductionItemInStock> hongXunProductionItemInStocks = (List<HongXunProductionItemInStock>) stockDao.quarywithpara("HongXunProductionItemInStock", "poNID", item.getIdc()); 
	    	for(HongXunProductionItemInStock hongXunProductionItemInStock: hongXunProductionItemInStocks){
	    		proInstoreQuantity = proInstoreQuantity + hongXunProductionItemInStock.getQuantity();
	    	}
	    	if(proInstoreQuantity>0 && proInstoreQuantity<item.getQuantity()){		
	    		//map.put("status", "部分入库");
	    		//list.add(map);
	    		System.out.println("部分入库1");
	    		status = "部分入库";
	    		break;	    		
	    	}else if((proInstoreQuantity != 0) && (proInstoreQuantity == item.getQuantity())){
	    		System.out.println(item.getIdc());
	    		if(flag1 == true){
	    			//map.put("status", "部分入库");
	    			//list.add(map);
	    			System.out.println("部分入库2");
	    			status = "部分入库";
	    			break;
	    		}else{
	    			flag = true;	    			
	    		}
	    	}else{
	    		if(flag == true){
	    			//map.put("status", "部分入库");
	    			//list.add(map);
	    			System.out.println("部分入库3");
	    			status = "部分入库";
	    			break;
	    		}else{
	    			flag1 = true;
	    		}
	    	}    
	    	boolean flag2 = false; boolean flag3 = false;
		for(HongXunPoN item: hongXunPoNs){

			int proOutstoreQuantity = 0;
			@SuppressWarnings("unchecked")
			List<HongXunProductionItemOutStock> hongXunProductionItemOutStocks = (List<HongXunProductionItemOutStock>) stockDao.quarywithpara("HongXunProductionItemOutStock", "poNID", item.getIdc()); 
	    	for(HongXunProductionItemOutStock hongXunProductionItemOutStock: hongXunProductionItemOutStocks){
	    		proOutstoreQuantity = proOutstoreQuantity + hongXunProductionItemOutStock.getQuantity();
	    	}
	    	if(proOutstoreQuantity>0 && proOutstoreQuantity<item.getQuantity()){		
	    		//map.put("status", "部分入库");
	    		//list.add(map);    		
	    		status = status + "部分出库";
	    		break;	    		
	    	}else if((proOutstoreQuantity != 0) && (proOutstoreQuantity == item.getQuantity())){
	    		if(flag3 == true){
	    			//map.put("status", "部分入库");
	    			//list.add(map);
	    			status = status + "部分出库";
	    			break;
	    		}else{
	    			flag2 = true;	    			
	    		}
	    	}else{
	    		if(flag2 == true){
	    			//map.put("status", "部分入库");
	    			//list.add(map);
	    			status = status + "部分出库";
	    			break;
	    		}else{
	    			flag3 = true;
	    		}
	    	}    	 	
		}
	    	*/
	
/*@Override
public List<Map<String, Object>> orderNumImport(InputStream is) {
	// TODO Auto-generated method stub
	List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
	List<String> list = new ArrayList<String>();
	int cnt=0;
	HongXunOrderNum hongXunOrderNum = new HongXunOrderNum();
	Date now = new Date(); 
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
	String orderNum = dateFormat.format( now ); 
	orderNum = orderNum.replace("-", "");
	orderNum = orderNum.replace(":", "");
	orderNum = orderNum.replace(" ", "");
	hongXunOrderNum.setOrderNum(orderNum);
	hongXunOrderNum.setStatus("新工单");
	//System.out.println("orderNumImport");
	stockDao.save(hongXunOrderNum);
	List<HongXunPoN> hongXunPoNs = new ArrayList<HongXunPoN>();
	List<HongXunProductionStock>  hongXunProductionStockList = new ArrayList<HongXunProductionStock>();
	
	try {
		Workbook workbook = WorkbookFactory.create(is);
		Sheet sheet = workbook.getSheetAt(0);
		int count = 0; 
		
		for (Row row : sheet) {
			if (row.getCell(0)==null || row.getCell(0).toString().equals("")) {
				break;
			}
			int end = row.getLastCellNum();
			HongXunPoN hongXunPoN = new HongXunPoN();				
			for (int i = 0; i < end; i++) {
				Cell cell = row.getCell(i);
				Object obj = getValue(cell);
				if(obj!=null){	
				// System.out.print(obj.toString() + "\t");
					if (count < 1) {
						list.add(obj.toString());
					} else {
						// System.out.println(list.size() + ":" + i);						

						String temStr = list.get(i);
						if (!temStr.equals("")) {
							// System.out.println(temStr);
							if (temStr.equals("Supplier") || temStr.equals("供应商")) {
								hongXunPoN.setSupplier(obj.toString());
							} else if (temStr.equals("PO#") || temStr.equals("PO号")) {
								hongXunPoN.setPon(obj.toString());
							} else if (temStr.equals("Line") || temStr.equals("行号")) {
								hongXunPoN.setLine(obj.toString());
							} else if (temStr.equals("Group") || temStr.equals("采购组")) {
								hongXunPoN.setGroupd(obj.toString());
							} else if (temStr.equals("Material No.") || temStr.equals("物料号")) {
								hongXunPoN.setMaterialNo(obj.toString());
							} else if (temStr.equals("Material Desc.") || temStr.equals("物料描述")) {
								hongXunPoN.setMaterialDesc(obj.toString());
							} else if (temStr.equals("Exemption") || temStr.equals("免检")) {
								hongXunPoN.setExemption(obj.toString());
							} else if (temStr.equals("Unit") || temStr.equals("单位")) {
								hongXunPoN.setUnit(obj.toString());
							} else if (temStr.equals("Quantity") || temStr.equals("数量")) {
								String str = obj.toString();
								//System.out.println("quantity:" + str);
								if (isNum(str)) {								
									hongXunPoN.setQuantity(Double.valueOf(str).intValue());										
								}
							} else if (temStr.equals("Price") || temStr.equals("单价")) {
								String str = obj.toString();
								str = str.replace(" /PC", "");
								//System.out.println("" + str)
								if (isNum(str)) {
									hongXunPoN.setPrice(new BigDecimal(str));
								}
							} else if (temStr.equals("Total Amount") || temStr.equals("价格")) {
								String str = obj.toString();
								if (isNum(str)) {
									hongXunPoN.setTotalAmount(new BigDecimal(str));
								}

							} else if (temStr.equals("Delivered Quantity") || temStr.equals("已交数量")) {
								String str = obj.toString();
								if (isNum(str)) {
									hongXunPoN.setDeliveredQuantity(Double.valueOf(str).intValue());
								}
							} else if (temStr.equals("Returned Quantity") || temStr.equals("退货数量")) {
								String str = obj.toString();
								if (isNum(str)) {
									hongXunPoN.setReturnedQuantity(Double.valueOf(str).intValue());
								}
							} else if (temStr.equals("Non-delivery") || temStr.equals("未交数量")) {
								String str = obj.toString();
								if (isNum(str)) {
									hongXunPoN.setNonDelivery(Double.valueOf(str).intValue());
								}
							} else if (temStr.equals("备货数量")) {
								String str = obj.toString();
								if (isNum(str)) {
									hongXunPoN.setReadyQuantity(Double.valueOf(str).intValue());
								}
							} else if (temStr.equals("在途数量")) {
								String str = obj.toString();
								if (isNum(str)) {
									hongXunPoN.setInroadQuantity(Double.valueOf(str).intValue());
								}
							} else if (temStr.equals("PO交期")) {
								SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
								Date d = sdf.parse(obj.toString());
								hongXunPoN.setPoDeliveryDate(d);

							} else if (temStr.equals("PO创建日期")) {
								SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
								Date d = sdf.parse(obj.toString());
								hongXunPoN.setPoCreateDate(d);
								if(cnt == 0){
									hongXunOrderNum.setPoCreateDate(d);
								}
							} else if (temStr.equals("生产交期")) {
								SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
								Date d = sdf.parse(obj.toString());
								hongXunPoN.setWorkDeliveryDate(d);	
							} else if (temStr.equals("Others") || temStr.equals("其他")) {
								hongXunPoN.setOthers(obj.toString());
							}
						}
					}
				}	
			}
			if (count < 1) {
				// System.out.println("" + row.getLastCellNum());
				count++;
			} else {
				
				
				hongXunPoN.setOrderNumID(hongXunOrderNum.getIdc());
				
				//stockDao.save(hongXunPoN);
				hongXunPoNs.add(hongXunPoN);
				cnt++;
				//System.out.println(hongXunPoN.getMaterialNo());
			}

		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	//stockDao.saveOrUpDate(hongXunPoNs);
	Session session = null;
	boolean flag = false;
	try { 			
		if(session == null){
			session = stockDao.session();
			session.beginTransaction(); // 开启事物
		}
		
		for(HongXunPoN hongXunPoN: hongXunPoNs){
			session.save(hongXunPoN);
		}
		session.getTransaction().commit(); // 提交事物
		flag = true;
	} catch (Exception e) {
		e.printStackTrace(); // 打印错误信息
	 	session.getTransaction().rollback(); // 出错将回滚事物
	 	stockDao.delete(hongXunOrderNum);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error", "导入文件失败");
		listmap.add(map);
		return listmap;
	} finally {
		//session.flush();//清理缓存，执行批量更新20条记录的SQL update语句
		//session.clear();
		session.close();
	}
	if(flag == true){
		for(HongXunProductionStock hongXunProductionStock: hongXunProductionStockList){
			stockDao.update(hongXunProductionStock);
		}
		hongXunOrderNum.setOrderQuantity(String.valueOf(cnt));
		stockDao.update(hongXunOrderNum);
	}

	Map<String, Object> map = new HashMap<String, Object>();
	mapHongXunOrderNum(map, hongXunOrderNum);
	listmap.add(map);
	return listmap;
}*/
