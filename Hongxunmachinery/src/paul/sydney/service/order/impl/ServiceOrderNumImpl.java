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

import paul.sydney.commen.result.PageResults;
import paul.sydney.dao.IMaterialOutStockItemDao;
import paul.sydney.dao.IMaterialOutStockNumDao;
import paul.sydney.dao.IMaterialStockDao;
import paul.sydney.dao.IOnlineStockDao;
import paul.sydney.dao.IOrderNumDao;
import paul.sydney.dao.IPonDao;
import paul.sydney.dao.IWeiwaiItemDao;
import paul.sydney.dao.IWeiwaiNumDao;
import paul.sydney.dao.StockDao;
import paul.sydney.model.HongXunMaterialOutStoreItem;
import paul.sydney.model.HongXunMaterialOutStoreNum;
import paul.sydney.model.HongXunMaterialStock;
import paul.sydney.model.HongXunOnlineStock;
import paul.sydney.model.HongXunOrderNum;
import paul.sydney.model.HongXunPoN;
import paul.sydney.model.HongXunPurchaseNum;
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
	IOnlineStockDao iOnlineStockDao;
	public void setStockDao(IOnlineStockDao iOnlineStockDao) {
		this.iOnlineStockDao = iOnlineStockDao;
	}
	
	@Autowired
	IMaterialStockDao iMaterialStockDao;
	public void setStockDao(IMaterialStockDao iMaterialStockDao) {
		this.iMaterialStockDao = iMaterialStockDao;
	}
	@Autowired
	IWeiwaiNumDao iWeiwaiNumDao;
	public void setStockDao(IWeiwaiNumDao iWeiwaiNumDao) {
		this.iWeiwaiNumDao = iWeiwaiNumDao;
	}
	@Autowired
	IWeiwaiItemDao iWeiwaiItemDao;
	public void setStockDao(IWeiwaiItemDao iWeiwaiItemDao) {
		this.iWeiwaiItemDao = iWeiwaiItemDao;
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
			iOnlineStockDao.update(hongXunOnlineStock);
		}
		iPonDao.deletePoNList(hongXunPoNs);
		iOrderNumDao.delete(hongXunOrderNum);
		for(HongXunMaterialStock hongXunMaterialStock: hongXunMaterialStockList){
			iMaterialStockDao.updateEntity(hongXunMaterialStock);
		}
		iMaterialOutStockItemDao.deleteAll(hongXunMaterialOutStoreItemList);
		iMaterialOutStockNumDao.deleteAll(hongXunMaterialOutStoreNumList);
		iWeiwaiItemDao.deleteWeiwaiItemList(hongXunWeiwaiItemList);
		iWeiwaiNumDao.deleteWeiwaiNumList(hongXunWeiwaiNumList);
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
		hongXunOrderNum.setStatus("新订单");
		hongXunOrderNum.setPoCreateDate(new Date());
		// System.out.println("orderNumImport");
		iOrderNumDao.save(hongXunOrderNum);
		List<HongXunPoN> hongXunPoNs = new ArrayList<HongXunPoN>();
		try {
			HSSFSheet sheet = workbook.getSheetAt(0);
			int count = 0;
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
					count++;
				} else {
					hongXunPoN.setOrderNumID(hongXunOrderNum.getIdc());
					hongXunPoNs.add(hongXunPoN);
					cnt++;
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
		return iOrderNumDao.getEntity();
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
	public PageResults<HongXunOrderNum> getData(HongXunOrderNum hongXunOrderNum, Integer pageNo, Integer pageSize) {
		// TODO Auto-generated method stub
		PageResults<HongXunOrderNum> result = new PageResults<HongXunOrderNum>();
		try {
			List<Object> list = new ArrayList<Object>();
			StringBuilder hql = new StringBuilder("from HongXunOrderNum where 1=1");
			if (hongXunOrderNum.getOrderNum() != null) {
				if (hongXunOrderNum.getOrderNum().indexOf("*") > -1) {
					hql.append(" and orderNum like ?");
					list.add("%" + hongXunOrderNum.getOrderNum().replace("*", "") + "%");
				} else {
					hql.append(" and orderNum = ?");
					list.add(hongXunOrderNum.getOrderNum());
				}
			}
			if (hongXunOrderNum.getStatus() != null) {
				if (hongXunOrderNum.getStatus().indexOf("*") > -1) {
					hql.append(" and status like ?");
					list.add("%" + hongXunOrderNum.getStatus().replace("*", "") + "%");
				} else {
					hql.append(" and status =?");
					list.add(hongXunOrderNum.getStatus());
				}
			}

			if (hongXunOrderNum.getStartTime() != null) {
				hql.append(" and poCreateDate >?");
				list.add(hongXunOrderNum.getStartTime());
			}
			if (hongXunOrderNum.getEndTime() != null) {
				hql.append(" and poCreateDate <?");
				list.add(hongXunOrderNum.getEndTime());
			}
			
			result = iOrderNumDao.findPageByFetchedHql(hql.toString(), "select count(*) " + hql.toString(), pageNo,
					pageSize, list.toArray());
		} catch (Exception e) {
			result.setSuccess(false);
		}
		return result;
	}
}

