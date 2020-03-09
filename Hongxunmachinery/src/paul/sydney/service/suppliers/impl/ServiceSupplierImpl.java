package paul.sydney.service.suppliers.impl;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import paul.sydney.dao.ISuppliersDao;
import paul.sydney.dao.StockDao;
import paul.sydney.model.HongXunSupplier;
import paul.sydney.service.suppliers.ServiceSupplierInf;
import paul.sydney.model.HongXunPurchaseNum;

@Transactional
@Service("serviceSupplier")
public class ServiceSupplierImpl implements ServiceSupplierInf{
	
/*	@Autowired
	StockDao stockDao;
	public void setStockDao(StockDao stockDao) {
		this.stockDao = stockDao;
	}*/
	@Autowired
	ISuppliersDao iSuppliersDao;
	public void setStockDao(ISuppliersDao iSuppliersDao) {
		this.iSuppliersDao = iSuppliersDao;
	}
	
	@Override
	public List<Map<String, Object>> loadData() {
		// TODO Auto-generated method stub
		//System.out.println("serviceBranchoneload");
		HongXunSupplier hongXunSupplier = new HongXunSupplier();
		List<HongXunSupplier> hongXunSuppliers = iSuppliersDao.quary(hongXunSupplier);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		for(HongXunSupplier item : hongXunSuppliers){
			Map<String,Object> map = new HashMap<String,Object>();
			//System.out.println(itemSun.getIdc());
			mapHongXunSupplier(map, item);
			list.add(map);
		}		
		return list;	
		
	}
	
	
	@Override
	public List<Map<String, Object>> saveRow(HongXunSupplier hongXunSupplier) {
		// TODO Auto-generated method stub
		//System.out.println("serviceoneloadSaveRow");	
		iSuppliersDao.save(hongXunSupplier);
		Map<String,Object> map = new HashMap<String,Object>();
		mapHongXunSupplier(map,hongXunSupplier);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.add(map);
		return list;
	}
	
	@Override
	public List<Map<String, Object>> updateRow(HongXunSupplier hongXunSupplier) {

		iSuppliersDao.update(hongXunSupplier);
		Map<String,Object> map = new HashMap<String,Object>();
		mapHongXunSupplier(map,hongXunSupplier);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.add(map);
		return list;
	}
	
	@Override
	public List<Map<String, Object>> deleteRow(int ID) {
		// TODO Auto-generated method stub
		
		HongXunSupplier hongXunSupplier = iSuppliersDao.hongXunSupplierFindById(ID);
		iSuppliersDao.deletRow(hongXunSupplier);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("idc",ID);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.add(map);
		return list;
	}


/*
	@Override
	public HongXunPurchaseNum quarywithpara(String para) {
		// TODO Auto-generated method stub
		return iSuppliersDao.quarywithpara(para);
	}*/


	
	private void mapHongXunSupplier(Map<String, Object> map, HongXunSupplier item) {
		// TODO Auto-generated method stub	
		//System.out.println("test: " + item.getHongXunDataOne().getProductionOrderNum());
		map.put("idc", item.getIdc());
		map.put("address", item.getAddress());
		map.put("attribute", item.getAttribute());
		map.put("category", item.getCategory());
		map.put("purCondition", item.getPurCondition());
		map.put("people", item.getPeople());
		map.put("period", item.getPeriod());
		map.put("phone", item.getPhone());	  	
		map.put("supplier", item.getSupplier());
		map.put("supplierCode", item.getSupplierCode());
		map.put("remark", item.getRemark());
	}

	@Override
	public boolean excelImport(InputStream is) {
		// TODO Auto-generated method stub
		try {
			Workbook workbook = WorkbookFactory.create(is);
			int sheets = workbook.getNumberOfSheets();
			for(int j=0; j<sheets; j++){

				Sheet sheet = workbook.getSheetAt(j);	
				HongXunSupplier node = new HongXunSupplier();	
				/*node.setBomMaterialNum(sheet.getSheetName());
				node.setParentID("1");
				iSuppliersDao.save(node);
				int nodeID = node.getIdc();*/
				List<String> list = new ArrayList<String>();
				int end = sheet.getRow(0).getLastCellNum();
					int count = 0;						
					//String fatherId1 =""; String fatherId2 =""; String fatherId3 =""; String fatherId4 =""; String fatherId5 ="";
					for (Row row : sheet) {
						if (row.getCell(0).toString().equals("")) {
							return false;
						}
						HongXunSupplier hongXunSupplier = new HongXunSupplier();	
						//int css = 1; 
						for (int i = 0; i < end; i++) {				
							Cell cell = row.getCell(i);	
							String obj = getValue(cell);
							if(obj!=null){	
								if (count < 1) {
									list.add(obj);
								} else {
									String temStr = list.get(i);								
									if (temStr.equals("供应商代码")) {							
										hongXunSupplier.setSupplierCode(obj);
									} else if (temStr.equals("供应商")) {							
										hongXunSupplier.setSupplier(obj);
									} else if (temStr.equals("物料类别")) {
										hongXunSupplier.setCategory(obj);
									} else if (temStr.equals("采购属性")) {
										hongXunSupplier.setAttribute(obj);
									} else if (temStr.equals("采购要求")) {			
										hongXunSupplier.setPurCondition(obj);
									} else if (temStr.equals("货期")) {
										hongXunSupplier.setPeriod(obj);
									} else if (temStr.equals("地址")) {
										hongXunSupplier.setAddress(obj);
									} else if (temStr.equals("联系人")) {
										hongXunSupplier.setPeople(obj);
									} else if (temStr.equals("联系电话")) {		
										hongXunSupplier.setPhone(obj);
									}
								}
							}
						}
						if (count < 1) {
							count++;
						} else {	
							iSuppliersDao.save(hongXunSupplier);	
						}
					}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
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

}
