package paul.sydney.service.order.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import paul.sydney.dao.IOrderNumDao;
import paul.sydney.dao.IPonDao;
import paul.sydney.dao.StockDao;
import paul.sydney.model.HongXunOnlineStock;
import paul.sydney.model.HongXunOrderNum;
import paul.sydney.model.HongXunPoN;
import paul.sydney.model.HongXunProductionStock;
import paul.sydney.model.HongXunProductionWeldStock;
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
	public List<Map<String, Object>> loadData() {
		// TODO Auto-generated method stub
		//System.out.println("serviceTenload");
		List<HongXunPoN> hongXunPoNs = iPonDao.quary(new HongXunPoN());
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		//Date date = new Date();
		//SimpleDateFormat dpr = new SimpleDateFormat("yyyy-MM-dd");
		//String today = dpr.format(date);
		for (HongXunPoN item : hongXunPoNs) {
		//	if(item.getPoCreateDate() != null){
		//		String itemDay = dpr.format(item.getPoCreateDate());
		//		if(today.equals(itemDay)){
					Map<String, Object> map = new HashMap<String, Object>();
					mapHongXunPoN(map, item);	
					list.add(map);
				//}
		//	}
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> saveRow(HongXunPoN hongXunPoN) {
		// TODO Auto-generated method stub
		// System.out.println("serviceoneloadSaveRow");
		iPonDao.save(hongXunPoN);
		Map<String, Object> map = new HashMap<String, Object>();
		//map.put("idc", hongXunPoN.getIdc());
		mapHongXunPoN(map, hongXunPoN);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list.add(map);
		return list;
	}

	@Override
	public List<Map<String, Object>> updateRow(HongXunPoN hongXunPoN) {
		// TODO Auto-generated method stub
		//System.out.println("HongXunPoN updateRow");
		//System.out.println(hongXunPoN.getWorkDeliveryDate());
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
/*		//第一步获得定单号	
		String orderNum = iOrderNumDao.orderNumFindById(hongXunPoNList.get(0).getOrderNumID()).getOrderNum();
		System.out.println("orderNum:" + orderNum);
		String attribute = null;*/
		/*for(HongXunPoN item: hongXunPoNList){
			@SuppressWarnings("unchecked")
			List<HongXunBomTree>  hongXunBomTrees =  (List<HongXunBomTree>) iOrderNumDao.quarywithpara("HongXunBomTree","bomMaterialNum",item.getMaterialNo());
			for(HongXunBomTree hongXunBomTree: hongXunBomTrees){
				if(hongXunBomTree.getSn()==1){
					attribute = hongXunBomTree.getAttribute();
					@SuppressWarnings("unchecked")
					List<HongXunWorkNum>  hongXunWorkNums =  (List<HongXunWorkNum>) iOrderNumDao.quarywithpara("HongXunWorkNum","orderNum",orderNum);
					for(HongXunWorkNum hongXunWorkNum: hongXunWorkNums){
						if(hongXunWorkNum.getWorkProcedure().equals(attribute)){
							
						}
					}
					
					break;
				}
			}
			if(item.getIdc()!=0){
				iOrderNumDao.delete(item);	
			}
		}*/
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		return list;
	}
	
	/*@Override
	public List<Map<String, Object>> deleteRow(HongXunPoN hongXunPoN) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<HongXunPurchaseItem>  hongXunPurchaseItems =  (List<HongXunPurchaseItem>) iOrderNumDao.quarywithpara("HongXunPurchaseItem","orderNumID",hongXunPoN.getIdc());
		for(HongXunPurchaseItem item: hongXunPurchaseItems){
			if(item.getMaterialRemark() == null){
				item.setMaterialRemark("定单中已删除");
			}else{
				item.setMaterialRemark(item.getMaterialRemark() + ",定单中已删除");
			}
			HongXunPurchaseNum hongXunPurchaseNum = iOrderNumDao.purchaseNumFindById(item.getPurchaseNumID());
			if(hongXunPurchaseNum.getPurchaseRemark() == null){
				hongXunPurchaseNum.setPurchaseRemark("采购单异常");
				iOrderNumDao.update(hongXunPurchaseNum);
			}else{
				if(hongXunPurchaseNum.getPurchaseRemark().indexOf("异常")==-1){
					hongXunPurchaseNum.setPurchaseRemark(hongXunPurchaseNum.getPurchaseRemark() + ",采购单异常");
					stockDao.update(hongXunPurchaseNum);
				}
			}
			stockDao.update(item);
			
		}
		@SuppressWarnings("unchecked")
		List<HongXunWorkItem>  hongXunWorkItems =  (List<HongXunWorkItem>) stockDao.quarywithpara("HongXunWorkItem","orderNumID",hongXunPoN.getIdc());
		for(HongXunWorkItem item: hongXunWorkItems){
			if(item.getRemark() == null){
				item.setRemark("定单中已删除");
			}else{
				item.setRemark(item.getRemark() + ",定单中已删除");
			}
			HongXunWorkNum hongXunWorkNum = stockDao.workNumFindById(item.getWorkNumID());
			//System.out.println("1");
			if(hongXunWorkNum.getRemark() == null){
				//System.out.println("2");
				hongXunWorkNum.setRemark("工单异常");
				stockDao.update(hongXunWorkNum);
			}else{
				if(hongXunWorkNum.getRemark().indexOf("异常")==-1){
					hongXunWorkNum.setRemark(hongXunWorkNum.getRemark() + ",工单异常");
					stockDao.update(hongXunWorkNum);
				}
			}
			stockDao.update(item);
		}
		@SuppressWarnings("unchecked")
		List<HongXunWeiwaiItem>  hongXunWeiwaiItems =  (List<HongXunWeiwaiItem>) stockDao.quarywithpara("HongXunWeiwaiItem","orderNumID",hongXunPoN.getIdc());
		for(HongXunWeiwaiItem item: hongXunWeiwaiItems){
			if(item.getRemark() == null){
				item.setRemark("定单中已删除");
			}else{
				item.setRemark(item.getRemark() + ",定单中已删除");
			}
			HongXunWeiwaiNum hongXunWeiwaiNum = stockDao.weiwaiNumFindById(item.getWeiwaiNumID());
			if(hongXunWeiwaiNum.getRemark() == null){
				hongXunWeiwaiNum.setRemark("委外单异常");
				stockDao.update(hongXunWeiwaiNum);
			}else{
				if(hongXunWeiwaiNum.getRemark().indexOf("异常")==-1){
					hongXunWeiwaiNum.setRemark(hongXunWeiwaiNum.getRemark() + ",委外单异常");
					stockDao.update(hongXunWeiwaiNum);
				}
			}
			stockDao.update(item);
		}
		stockDao.delete(hongXunPoN);		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		return list;
	}
*/
/*
	@Override
	public List<Map<String, Object>> betweenDateFind(String date1, String date2) throws ParseException {
		// TODO Auto-generated method stub
		Date beginDate;
		Date endDate;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		if (df.parse(date1).before(df.parse(date2))) {
			// System.out.println("0");
			beginDate = df.parse(date1);
			endDate = df.parse(date2);
		} else {
			// System.out.println("1");
			beginDate = df.parse(date2);
			endDate = df.parse(date1);
		}
		Calendar c = Calendar.getInstance();
		c.setTime(endDate);
		c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
		endDate = c.getTime();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<HongXunPoN> hongXunPoNs = stockDao.quary(new HongXunPoN());
		for (HongXunPoN item : hongXunPoNs) {
			if (beginDate.before(item.getOrderStartDate()) && endDate.after(item.getOrderStartDate())) {
				System.out.println("ID: " + item.getID());
				Map<String, Object> map = new HashMap<String, Object>();
				mapHongXunPoN(map, item);
				list.add(map);
			}
		}
		return list;
	}
*/
	

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
		//System.out.println("pon :" +pon);
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
	

/*	@Override
	public List<Map<String, Object>> orderNumImport(InputStream is) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		List<String> list = new ArrayList<String>();
		System.out.println("asdfsad2f3");
		try {
			Workbook workbook = WorkbookFactory.create(is);
			Sheet sheet = workbook.getSheetAt(0);
			int count = 0;
			for (Row row : sheet) {
				if (row.getCell(0).toString().equals("")) {
					return null;
				}
				int end = row.getLastCellNum();
				HongXunPoN hongXunPoN = new HongXunPoN();
				for (int i = 0; i < end; i++) {
					Cell cell = row.getCell(i);
					Object obj = getValue(cell);
					if(obj!=null){	
					// System.out.print(obj.toString() + "\t");
						if (count < 1) {
							System.out.println(obj.toString());
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
								} else if (temStr.equals("生产交期")) {
									SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
									Date d = sdf.parse(obj.toString());
									hongXunPoN.setWorkDeliveryDate(d);
								} else if (temStr.equals("PO创建日期")) {
									SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
									Date d = sdf.parse(obj.toString());
									hongXunPoN.setPoCreateDate(d);
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
					@SuppressWarnings("unchecked")
					List<HongXunBomTree>  hongXunBomTrees =  (List<HongXunBomTree>) stockDao.quarywithpara("HongXunBomTree","bomMaterialNum",hongXunPoN.getMaterialNo());
					for(HongXunBomTree item: hongXunBomTrees){
						if(item.getSn()==1){
							hongXunPoN.setAttribute(hongXunBomTrees.get(0).getBomGroup());
						}
					}
					stockDao.save(hongXunPoN);
					//System.out.println(hongXunPoN.getMaterialNo());
					Map<String, Object> map = new HashMap<String, Object>();
					mapHongXunPoN(map, hongXunPoN);
					listmap.add(map);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listmap;
	}*/

/*	private String getValue(Cell cell) {
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
	}*/
	
/*	private String replaceBlank(String str) {
		String dest = "";
		if (str!=null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}*/
/*	private boolean isNum(String str) {
		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
	}*/


	@Override
	public List<Map<String, Object>> betweenDateFind(String date1, String date2) throws ParseException {
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
         c.add(Calendar.SECOND, -1);
         endDate = c.getTime();
    	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    	List<HongXunPoN> hongXunPoNs = iPonDao.quary(new HongXunPoN());
    	for(HongXunPoN item: hongXunPoNs){
    		//System.out.println("date: " + item.getPoCreateDate());
			if(beginDate.getTime()<= item.getPoCreateDate().getTime() && endDate.getTime() >= item.getPoCreateDate().getTime()){
				//System.out.println("date: ");
       			Map<String,Object> map = new HashMap<String,Object>();
       			mapHongXunPoN(map, item);
       	   		list.add(map);	
			}
    	}
    	return list;
	}
	

	@Override
	public List<Map<String, Object>> autotimp(Integer orderNumID, String str) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		//List<String> listName = new ArrayList<String>();
		@SuppressWarnings("unchecked")
		List<HongXunPoN> hongXunPoNs = (List<HongXunPoN>) stockDao.quarywithpara("HongXunPoN", "orderNumID", orderNumID); 
		
		/*
		@SuppressWarnings("unchecked")
		List<HongXunPoN> hongXunPoNs = (List<HongXunPoN>) stockDao.quaryFuzzyWithpara("HongXunPoN", "pon", str); 
		*/
		for(HongXunPoN item : hongXunPoNs){
			if(item.getMaterialNo().indexOf(str)>-1){
				//if(!listName.contains(item.getMaterialNo())){
					Map<String,Object> map = new HashMap<String,Object>();
					map.put("id", item.getIdc());
					//map.put("name", item.getMaterialNo());
					map.put("name", item.getMaterialNo() + "->" + item.getMaterialDesc());
					//listName.add(item.getMaterialNo());
					list.add(map);
				//}
			}	
		};
		return list;
	}
	
	@Override
	public List<Map<String, Object>> searchNum(Integer orderNumID, String materialNum) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		@SuppressWarnings("unchecked")
		List<HongXunPoN> hongXunPoNs = (List<HongXunPoN>) stockDao.quarywithpara("HongXunPoN", "orderNumID", orderNumID); 

		for (HongXunPoN item : hongXunPoNs) {
			if(item.getMaterialNo().equals(materialNum)){
				Map<String, Object> map = new HashMap<String, Object>();
				mapHongXunPoN(map, item);
				list.add(map);
			}
		}
		
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
		List<HongXunSpecialMaterialNum> hongXunSpecialMaterialNums = stockDao.quary(hongXunSpecialMaterialNum);
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
					stockDao.update(hongXunOnlineStock);
				}
			}
		}	
		return list;
	}
	
	private HongXunOnlineStock getOnlineStock(String materialNum){
		HongXunOnlineStock hongXunOnlineStock = new HongXunOnlineStock();
		hongXunOnlineStock.setMaterialNum(materialNum);
		List<HongXunOnlineStock> hongXunOnlineStocks = stockDao.quary(hongXunOnlineStock);
		if(hongXunOnlineStocks.size()>0){
			return hongXunOnlineStocks.get(0);
		}else{
			return null;
		}
	}
/*	private HongXunProductionWeldStock getProductionWeldStock(String materialNum){
		HongXunProductionWeldStock hongXunProductionWeldStock = new HongXunProductionWeldStock();
		hongXunProductionWeldStock.setMaterialNum(materialNum);
		List<HongXunProductionWeldStock> hongXunProductionWeldStocks = stockDao.quary(hongXunProductionWeldStock);
		if(hongXunProductionWeldStocks.size()>0){
			return hongXunProductionWeldStocks.get(0);
		}else{
			return null;
		}
	}*/
	
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
		/*
		int proInstoreQuantity = 0;
		HongXunProductionItemInStock hongXunProductionItemInStock = new HongXunProductionItemInStock();
		hongXunProductionItemInStock.setPoNID(hongXunPoN.getIdc());
		List<HongXunProductionItemInStock> hongXunProductionItemInStocks = stockDao.quary(hongXunProductionItemInStock);
		//@SuppressWarnings("unchecked")
		//List<HongXunProductionItemInStock> hongXunProductionItemInStocks = (List<HongXunProductionItemInStock>) stockDao.quarywithpara("HongXunProductionItemInStock", "poNID", item.getIdc()); 
    	for(HongXunProductionItemInStock item: hongXunProductionItemInStocks){
    		proInstoreQuantity = proInstoreQuantity + item.getQuantity();
    	}
    	if(proInstoreQuantity>0){
    		map.put("stockInQuantity", proInstoreQuantity);
    	}
    	proInstoreQuantity = 0;
    	HongXunWeldItemInStock hongXunWeldItemInStock = new HongXunWeldItemInStock();
    	hongXunWeldItemInStock.setPoNID(hongXunPoN.getIdc());
    	List<HongXunWeldItemInStock> hongXunWeldItemInStocks = stockDao.quary(hongXunWeldItemInStock);
    	for(HongXunWeldItemInStock item: hongXunWeldItemInStocks){
    		proInstoreQuantity = proInstoreQuantity + item.getQuantity();
    	}
    	if(proInstoreQuantity>0){
    		//System.out.println("stockInQuantity :" + proInstoreQuantity);
    		map.put("stockInQuantity", proInstoreQuantity);
    	}
    	
		int proOutstoreQuantity = 0;
		HongXunProductionItemOutStock hongXunProductionItemOutStock = new HongXunProductionItemOutStock();
		hongXunProductionItemOutStock.setPoNID(hongXunPoN.getIdc());
		List<HongXunProductionItemOutStock> hongXunProductionItemOutStocks = stockDao.quary(hongXunProductionItemOutStock);
		//@SuppressWarnings("unchecked")
		//List<HongXunProductionItemOutStock> hongXunProductionItemOutStocks = (List<HongXunProductionItemOutStock>) stockDao.quarywithpara("HongXunProductionItemOutStock", "poNID", item.getIdc()); 
    	for(HongXunProductionItemOutStock item: hongXunProductionItemOutStocks){
    		proOutstoreQuantity = proOutstoreQuantity + item.getQuantity();
    	}
    	if(proOutstoreQuantity>0){
    		map.put("stockOutQuantity", proOutstoreQuantity);
    	}
    	
    	proOutstoreQuantity = 0;
    	HongXunWeldItemOutStock hongXunWeldItemOutStock = new HongXunWeldItemOutStock();
    	hongXunWeldItemOutStock.setPoNID(hongXunPoN.getIdc());
    	List<HongXunWeldItemOutStock> hongXunWeldItemOutStocks = stockDao.quary(hongXunWeldItemOutStock);
    	for(HongXunWeldItemOutStock item: hongXunWeldItemOutStocks){
    		proOutstoreQuantity = proOutstoreQuantity + item.getQuantity();
    	}
    	if(proInstoreQuantity>0){
    		map.put("stockOutQuantity", proOutstoreQuantity);
    	}*/
	}

	
	private int getStockQuantity(String materialNum){
		HongXunOnlineStock hongXunOnlineStock = new HongXunOnlineStock();
		hongXunOnlineStock.setMaterialNum(materialNum);
		List<HongXunOnlineStock> hongXunOnlineStocks = stockDao.quary(hongXunOnlineStock);
		if((hongXunOnlineStocks !=null) && (hongXunOnlineStocks.size()>0)){
			return hongXunOnlineStocks.get(0).getQuantity();
		}
/*		HongXunProductionWeldStock hongXunProductionWeldStock = new HongXunProductionWeldStock();
		hongXunProductionWeldStock.setMaterialNum(materialNum);
		List<HongXunProductionWeldStock> hongXunProductionWeldStocks = stockDao.quary(hongXunProductionWeldStock);
		if((hongXunProductionWeldStocks!=null) && (hongXunProductionWeldStocks.size()>0)){
			return hongXunProductionWeldStocks.get(0).getQuantity();
		}*/
		return 0;
		
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
}
