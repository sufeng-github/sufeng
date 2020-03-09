package paul.sydney.service.others.impl;

import java.io.InputStream;
import java.lang.reflect.Field;
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
import paul.sydney.model.HongXunSuppliersPrice;
import paul.sydney.service.others.ServiceSpecialMaterialNumInf;
import paul.sydney.model.HongXunPurchaseNum;
import paul.sydney.model.HongXunSpecialMaterialNum;

@Transactional
@Service("serviceSpecialMaterialNum")
public class ServiceSpecialMaterialNumImpl implements ServiceSpecialMaterialNumInf{
	
	@Autowired
	StockDao stockDao;
	public void setStockDao(StockDao stockDao) {
		this.stockDao = stockDao;
	}
	@Autowired
	ISuppliersDao iSuppliersDao;
	public void setStockDao(ISuppliersDao iSuppliersDao) {
		this.iSuppliersDao = iSuppliersDao;
	}
	
	
	@Override
	public List<Map<String, Object>> loadData() {
		// TODO Auto-generated method stub
		//System.out.println("serviceBranchoneload");
		HongXunSpecialMaterialNum hongXunSpecialMaterialNum = new HongXunSpecialMaterialNum();
		List<HongXunSpecialMaterialNum> hongXunSpecialMaterialNums = stockDao.quary(hongXunSpecialMaterialNum);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		for(HongXunSpecialMaterialNum item : hongXunSpecialMaterialNums){
			Map<String,Object> map = new HashMap<String,Object>();
			//System.out.println(itemSun.getIdc());
			mapHongXunSpecialMaterialNum(map, item);
			list.add(map);
		}		
		return list;	
		
	}
	
	
	@Override
	public List<Map<String, Object>> saveRow(HongXunSpecialMaterialNum hongXunSpecialMaterialNum) {
		// TODO Auto-generated method stub
		//System.out.println("serviceoneloadSaveRow");	
		stockDao.save(hongXunSpecialMaterialNum);
		Map<String,Object> map = new HashMap<String,Object>();
		mapHongXunSpecialMaterialNum(map,hongXunSpecialMaterialNum);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.add(map);
		return list;
	}
	
	@Override
	public List<Map<String, Object>> updateRow(HongXunSpecialMaterialNum hongXunSpecialMaterialNum) {

		stockDao.update(hongXunSpecialMaterialNum);
		Map<String,Object> map = new HashMap<String,Object>();
		mapHongXunSpecialMaterialNum(map,hongXunSpecialMaterialNum);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.add(map);
		return list;
	}
	
	@Override
	public List<Map<String, Object>> deleteRow(int ID) {
		// TODO Auto-generated method stub
		
		HongXunSpecialMaterialNum hongXunSpecialMaterialNum = stockDao.hongXunSpecialFindById(ID);
		stockDao.deletRow(hongXunSpecialMaterialNum);
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
		return stockDao.quarywithpara(para);
	}*/


	
	private void mapHongXunSpecialMaterialNum(Map<String, Object> map, HongXunSpecialMaterialNum item) {
		// TODO Auto-generated method stub	
		//System.out.println("test: " + item.getHongXunDataOne().getProductionOrderNum());
		map.put("idc", item.getIdc());	
		map.put("attribute", item.getAttribute());
		map.put("materialNum", item.getMaterialNum());
		map.put("specification", item.getSpecification());
		map.put("localtion", item.getLocaltion());
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
				stockDao.save(node);
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

	@Override
	public List<Map<String, Object>> getEntity() {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String,Object>();
		HongXunSpecialMaterialNum e = new HongXunSpecialMaterialNum();
		Class<? extends HongXunSpecialMaterialNum> cls = e.getClass();  
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

}
