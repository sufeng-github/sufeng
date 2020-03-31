package paul.sydney.service.bomTree.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import com.itextpdf.barcodes.BarcodeQRCode;
import com.itextpdf.barcodes.qrcode.EncodeHintType;
import com.itextpdf.barcodes.qrcode.ErrorCorrectionLevel;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;

import paul.sydney.model.HongXunBomTree;
import paul.sydney.model.HongXunPoN;
import paul.sydney.model.HongXunPonNum;
import paul.sydney.model.HongXunSpecialMaterialNum;
import paul.sydney.model.HongXunTablesStatus;
import paul.sydney.service.bomTree.ExcelPara;
import paul.sydney.service.bomTree.ServiceBomTreeInf;
import paul.sydney.dao.IBomTreeDao;
import paul.sydney.dao.IOrderNumDao;
import paul.sydney.dao.IPonDao;
import paul.sydney.dao.IPonNumDao;
import paul.sydney.dao.ISpecialMaterialDao;
import paul.sydney.dao.ITablesStatusDao;
import paul.sydney.dao.StockDao;

@Transactional
@Service("serviceBomTree")
public class ServiceBomTreeImpl implements ServiceBomTreeInf {

	@Autowired
	StockDao stockDao;
	public void setStockDao(StockDao stockDao) {
		this.stockDao = stockDao;
	}
	@Autowired
	ISpecialMaterialDao iSpecialMaterialDao;
	public void setStockDao(ISpecialMaterialDao iSpecialMaterialDao) {
		this.iSpecialMaterialDao = iSpecialMaterialDao;
	}
	@Autowired
	ITablesStatusDao iTablesStatusDao;
	public void setStockDao(ITablesStatusDao iTablesStatusDao) {
		this.iTablesStatusDao = iTablesStatusDao;
	}
	
	@Autowired
	IBomTreeDao iBomTreeDao;
	public void setStockDao(IBomTreeDao iBomTreeDao) {
		this.iBomTreeDao = iBomTreeDao;
	}
	@Autowired
	IOrderNumDao iOrderNumDao;
	public void setStockDao(IOrderNumDao iOrderNumDao) {
		this.iOrderNumDao = iOrderNumDao;
	}
	@Autowired
	IPonNumDao iPonNumDao;
	public void setStockDao(IPonNumDao iPonNumDao) {
		this.iPonNumDao = iPonNumDao;
	}
	@Autowired
	IPonDao iPonDao;
	public void setStockDao(IPonDao iPonDao) {
		this.iPonDao = iPonDao;
	}
	
	/*
	 * @Override public List<Map<String, Object>> loadData() { // TODO
	 * Auto-generated method stub System.out.println("serviceBomload");
	 * HongXunBomTree hongXunBomTree = new HongXunBomTree();
	 * List<HongXunBomTree> hongXunBomTrees = stockDao.quary(hongXunBomTree);
	 * List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
	 * for(HongXunBomTree item : hongXunBomTrees){ Map<String,Object> map = new
	 * HashMap<String,Object>(); mapHongXunBomTree(map, item); list.add(map); }
	 * return list; }
	 */

	/*
	 * @Override public List<Map<String, Object>> getList(int ID) { // TODO
	 * Auto-generated method stub List<Map<String,Object>> list = new
	 * ArrayList<Map<String,Object>>(); List<HongXunBomTree> hongXunBomTrees =
	 * getChildren(ID); for(HongXunBomTree item : hongXunBomTrees){
	 * System.out.println(item.getIdc()); Map<String,Object> map = new
	 * HashMap<String,Object>(); mapHongXunBomTree(map, item); list.add(map); }
	 * return list; }
	 */

	@Override
	public void createRootBom(List<Map<String, Object>> list) {
		// TODO Auto-generated method stub
		HongXunBomTree hongXunBomTree = new HongXunBomTree();
		hongXunBomTree.setBomMaterialNum("BOM");
		hongXunBomTree.setParentID("99999");
		hongXunBomTree.setNodeID(0);
		iBomTreeDao.saveEntity(hongXunBomTree);
		Map<String, Object> map = new HashMap<String, Object>();
		mapHongXunBomTree(map, hongXunBomTree);
		list.add(map);
	}

	@Override
	public int getNodeID(String id) {
		// TODO Auto-generated method stub
		HongXunBomTree hongXunBomTree = iBomTreeDao.get(Integer.valueOf(id));
		return hongXunBomTree.getNodeID();
	}

	@Override
	public List<Map<String, Object>> getList(String sql, String id) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		@SuppressWarnings("unchecked")
		List<HongXunBomTree> hongXunBomTrees = (List<HongXunBomTree>) stockDao.getList(sql, id);
		for (HongXunBomTree item : hongXunBomTrees) {
			// System.out.println("idc :" + item.getIdc());
			Map<String, Object> map = new HashMap<String, Object>();
			mapHongXunBomTree(map, item);
			if (getChildren(item.getNodeID()).size() > 0) {
				map.put("state", "closed");
			} else {
				map.put("iconCls", "");
			}
			list.add(map);
		}
		return list;
	}

	private List<HongXunBomTree> getChildren(int ID) {
		// TODO Auto-generated method stub
		HongXunBomTree hongXunBomTree = new HongXunBomTree();
		hongXunBomTree.setParentID(String.valueOf(ID));
		List<HongXunBomTree> hongXunBomTrees = iBomTreeDao.quary(hongXunBomTree);
		return hongXunBomTrees;
	}

	/*
	 * @SuppressWarnings("unchecked") private List<HongXunBomTree>
	 * getAllChildrens(String ID,List<HongXunBomTree> hongXunBomTrees) { // TODO
	 * Auto-generated method stub
	 * 
	 * //List<HongXunBomTree> hongXunBomTrees = (List<HongXunBomTree>)stockDao.
	 * getList("from HongXunBomTree where parentID=?",ID); for(HongXunBomTree
	 * item : (List<HongXunBomTree>)stockDao.
	 * getList("from HongXunBomTree where parentID=?",ID)){
	 * System.out.println("ID:" + item.getNodeID()); hongXunBomTrees.add(item);
	 * getAllChildrens(String.valueOf(item.getNodeID()),hongXunBomTrees); }
	 * return hongXunBomTrees; }
	 */

	@Override
	public List<HongXunBomTree> quary(HongXunBomTree hongXunBomTree) {
		// TODO Auto-generated method stub
		List<HongXunBomTree> list = iBomTreeDao.quary(hongXunBomTree);
		return list;
	}

	private void mapHongXunBomTree(Map<String, Object> map, HongXunBomTree item) {
		// TODO Auto-generated method stub

		map.put("idc", item.getIdc());
		map.put("parentID", item.getParentID());
		map.put("nodeID", item.getNodeID());
		map.put("topID", item.getTopID());
		map.put("sn", item.getSn());
		map.put("bomQuantity", item.getBomQuantity());
		map.put("bomItemQuantity", item.getBomItemQuantity());
		map.put("bomSpacification", item.getBomSpacification());
		map.put("bomLength", item.getBomLength());
		map.put("bomDrawingNum", item.getBomDrawingNum());
		map.put("bomPosion", item.getBomPosion());
		map.put("bomMaterial", item.getBomMaterial());
		map.put("bomMaterialNum", item.getBomMaterialNum());
		map.put("attribute", item.getAttribute());
		map.put("bomMaterialCuting", item.getBomMaterialCuting());
		map.put("bomPlateCuting", item.getBomPlateCuting());
		map.put("bomMaterialWeld", item.getBomMaterialWeld());
		map.put("bomWeiwai", item.getBomWeiwai());
		map.put("bomPainting", item.getBomPainting());
		map.put("bomAssemble", item.getBomAssemble());
		map.put("remark", item.getRemark());
		map.put("bomLocation", item.getBomLocation());
		map.put("bomGroup", item.getBomGroup());
		map.put("bomOrderNum", item.getBomOrderNum());
		map.put("bomLine", item.getBomLine());

		if (item.getBomDeliveryDate() != null) {
			map.put("bomDeliveryDate", new SimpleDateFormat("yyyy-MM-dd").format(item.getBomDeliveryDate()));
		}

	}

	@Override
	public List<Map<String, Object>> saveRow(HongXunBomTree hongXunBomTree) {
		// TODO Auto-generated method stub
		iBomTreeDao.saveEntity(hongXunBomTree);
		Map<String, Object> map = new HashMap<String, Object>();
		mapHongXunBomTree(map, hongXunBomTree);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list.add(map);
		return list;
	}

	@Override
	public List<Map<String, Object>> updateRow(HongXunBomTree hongXunBomTree) {
		// TODO Auto-generated method stub
		iBomTreeDao.updateEntity(hongXunBomTree);
		Map<String, Object> map = new HashMap<String, Object>();
		mapHongXunBomTree(map, hongXunBomTree);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list.add(map);
		return list;
	}

	@Override
	public void deleteRow(HongXunBomTree hongXunBomTree) {
		// TODO Auto-generated method stub
		if (hongXunBomTree.getParentID().equals("99999")) {
			// System.out.println("99999");
			List<HongXunBomTree> hongXunBomTrees = iBomTreeDao.quary(new HongXunBomTree());
			System.out.println(hongXunBomTrees.size());
			iBomTreeDao.deleteAll(hongXunBomTrees);
		} else {
			iBomTreeDao.delete(hongXunBomTree);
			String parentID = String.valueOf(hongXunBomTree.getNodeID());
			@SuppressWarnings("unchecked")
			List<HongXunBomTree> hongXunBomTrees = (List<HongXunBomTree>) stockDao
					.getList("from HongXunBomTree where parentID=?", parentID);
			for (HongXunBomTree item : hongXunBomTrees) {
				// System.out.println("ID:" + item.getIdc());
				deleteRow(item);
			}
		}
	}

	/*
	 * @Override public List<Map<String, Object>> getDataMsg(HongXunBomTree
	 * hongXunBomTree) { // TODO Auto-generated method stub
	 * List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
	 * 
	 * @SuppressWarnings("unchecked") List<HongXunBomTree> hongXunBomTrees =
	 * (List<HongXunBomTree>) stockDao.quarywithpara("HongXunBomTree",
	 * "bomMaterialNum", hongXunBomTree.getBomMaterial()); for(HongXunBomTree
	 * item: hongXunBomTrees){ Map<String,Object> map = new
	 * HashMap<String,Object>(); map.put("materialName",
	 * item.getMaterialName()); map.put("materialsupplier",
	 * item.getMaterialsupplier()); map.put("unit", item.getUnit());
	 * map.put("attribute", item.getAttribute());
	 * map.put("materialSpecification", item.getSpecification());
	 * map.put("materialPrice", item.getMaterialPrice()); list.add(map); break;
	 * } return list; }
	 */
	@Override
	public List<Map<String, Object>> qrcodeImport(InputStream is) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			Workbook workbook = WorkbookFactory.create(is);
			Sheet sheet = workbook.getSheetAt(0);
			int rowNum = sheet.getLastRowNum();
			for (int j = 0; j <= rowNum; j++) {
				Cell cell = sheet.getRow(j).getCell(0);
				String obj = getValue(cell);
				// System.out.println("obj: " + obj);
				if (obj != null) {
					Map<String, Object> map = new HashMap<String, Object>();
					obj = obj + "||" + sheet.getRow(j).getCell(1);
					map.put(String.valueOf(j), obj);
					list.add(map);
				} else {
					break;
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> barcodeImport(InputStream is, String fileName) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		try {
			Workbook workbook = WorkbookFactory.create(is);
			int sheetNum = workbook.getNumberOfSheets();
			List<String> itemList = new ArrayList<String>();
			List<String> listTital = new ArrayList<String>();
			for (int i = 0; i < sheetNum; i++) {
				// List<String> sheetList = new ArrayList<String>();
				Sheet sheet = workbook.getSheetAt(i);

				int rowNum = sheet.getLastRowNum();
				rowNum = rowNum - 5;
				System.out.println("rowNum: " + rowNum);
				// if(rowNum>7){
				// String [] arry = new String[rowNum-7];
				// }
				int end = 0;
				for (int j = 1; j <= rowNum; j++) {
					if (j == 1) {
						end = sheet.getRow(j).getLastCellNum();
						for (int k = 0; k < end; k++) {
							Cell cell = sheet.getRow(j).getCell(k);
							String obj = null;
							obj = getValue(cell);
							if (obj != null) {
								listTital.add(obj);
								// System.out.println("name:" + obj);
							}
						}
					} else {
						if (sheet.getRow(j) == null) {
							break;

						} else if (sheet.getRow(j).getFirstCellNum() != 0 && sheet.getRow(j).getLastCellNum() < 21) {
							break;

						}
						String materialNum = "";
						int quantity = 0;
						String species = "";
						String drawNum = "";
						int sn = 0;

						for (String item : listTital) {

							Cell cell = null;
							if (item.equals("部件号")) {
								cell = sheet.getRow(j).getCell(listTital.indexOf("部件号"));
								if (cell != null) {
									materialNum = getValue(cell);
								}
							} else if (item.equals("图号")) {
								cell = sheet.getRow(j).getCell(listTital.indexOf("图号"));
								if (cell != null) {
									drawNum = (getValue(cell));
								}
							} else if (item.equals("项目数")) {
								cell = sheet.getRow(j).getCell(listTital.indexOf("项目数"));
								if (cell != null) {
									quantity = Integer.valueOf(getValue(cell));
								}
							} else if (item.equals("技术规格")) {
								cell = sheet.getRow(j).getCell(listTital.indexOf("技术规格"));
								if (cell != null) {
									species = getValue(cell);
								}
							} else if (item.equals("序号")) {
								cell = sheet.getRow(j).getCell(listTital.indexOf("序号"));
								if (cell != null) {
									sn = Integer.valueOf(getValue(cell));
									if (sn != 1) {
										continue;
									}
								}
							}
						}

						// arry[j-1] = (materialNum + "||" + quantity + "||" +
						// species);
						// Map<String, Object> mapinner = new HashMap<String,
						// Object>();
						// mapinner.put(String.valueOf(j), materialNum + "||" +
						// quantity + "||" + species);
						// System.out.println(materialNum + "||" + quantity +
						// "||" + species);
						// if (sn == 1) {
						// sheetList.add(materialNum + "||" + quantity + "||" +
						// species);
						itemList.add(materialNum + "||" + quantity + "||" + species + "||" + sheet.getSheetName() + "||"
								+ drawNum);
						// }
					}
				}
				// System.out.println("sheetList.size() :" + sheetList.size());
				// if (sheetList.size() > 0) {
				/*
				 * Map<String, Object> map = new HashMap<String, Object>();
				 * map.put(sheet.getSheetName() + fileName,sheetList);
				 * list.add(map);
				 */
				// }

			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("fileName", fileName);
			list.add(map);
			System.out.println(itemList.size());
			itextPdf("D:/" + fileName + ".pdf", itemList, fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> sendbarcodeImport(InputStream is, String fileName) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		try {
			Workbook workbook = WorkbookFactory.create(is);
			int sheetNum = workbook.getNumberOfSheets();
			List<String> itemList = new ArrayList<String>();
			List<String> listTital = new ArrayList<String>();
			for (int i = 0; i < sheetNum; i++) {

				Sheet sheet = workbook.getSheetAt(i);
				int rowNum = sheet.getLastRowNum();
				// System.out.println("rowNum: " + rowNum);
				int end = 0;
				for (int j = 0; j < rowNum; j++) {
					if (j == 0) {
						end = sheet.getRow(j).getLastCellNum();
						for (int k = 0; k < end; k++) {
							Cell cell = sheet.getRow(j).getCell(k);
							String obj = null;
							obj = getValue(cell);
							if (obj != null) {
								listTital.add(obj);
								// System.out.println("name:" + obj);
							}
						}
					} else {
						String code = "";
						String orderNum = "";
						String materialNum = "";
						String itemNum = "";
						String name = "";
						String date = "";
						String lotNum = "";
						String quantity = "";
						String ln = "";
						if (sheet.getRow(j) == null) {

							break;

						} else if (sheet.getRow(j).getFirstCellNum() != 0 && sheet.getRow(j).getLastCellNum() < 13) {

							break;

						}
						for (String item : listTital) {
							Cell cell = null;
							if (item.equals("代码")) {
								cell = sheet.getRow(j).getCell(listTital.indexOf("代码"));
								if (cell != null) {
									code = getValue(cell);
									// System.out.println("code:" + code);
								}
							} else if (item.equals("订单号")) {
								cell = sheet.getRow(j).getCell(listTital.indexOf("订单号"));
								if (cell != null) {
									orderNum = (getValue(cell));
									// System.out.println("orderNum:" +
									// orderNum);
								}
							} else if (item.equals("物料号")) {
								cell = sheet.getRow(j).getCell(listTital.indexOf("物料号"));
								if (cell != null) {
									materialNum = getValue(cell);
								}
							} else if (item.equals("项目号")) {
								cell = sheet.getRow(j).getCell(listTital.indexOf("项目号"));
								if (cell != null) {
									itemNum = getValue(cell);
									if (itemNum == null) {
										itemNum = "";
									}
								}
							} else if (item.equals("物料名称")) {
								cell = sheet.getRow(j).getCell(listTital.indexOf("物料名称"));
								if (cell != null) {
									name = getValue(cell);
								}
							} else if (item.equals("生产日期")) {
								cell = sheet.getRow(j).getCell(listTital.indexOf("生产日期"));
								if (cell != null) {
									date = getValue(cell);
								}
							} else if (item.equals("炉批号")) {
								cell = sheet.getRow(j).getCell(listTital.indexOf("炉批号"));
								if (cell != null) {
									lotNum = getValue(cell);
									if (lotNum == null) {
										lotNum = "";
									}
								}
							} else if (item.equals("数量")) {
								cell = sheet.getRow(j).getCell(listTital.indexOf("数量"));
								if (cell != null) {
									quantity = (getValue(cell));
									// System.out.println("quantity:" +
									// quantity);
								}
							} else if (item.equals("行号")) {
								cell = sheet.getRow(j).getCell(listTital.indexOf("行号"));
								if (cell != null) {
									ln = (getValue(cell));
									// System.out.println("quantity:" +
									// quantity);
								}
							}
						}
						itemList.add(code + "||" + orderNum + "||" + materialNum + "||" + itemNum + "||" + name + "||"
								+ date + "||" + lotNum + "||" + quantity + "||" + ln);
					}
				}

			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("fileName", fileName);
			list.add(map);
			// System.out.println("9999999999999999: " + itemList.size());
			itextPdf("D:/" + fileName + ".pdf", itemList, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void download(HttpServletRequest request, HttpServletResponse response) {
		// String id=(String)params.get("id");
		String fileName = request.getParameter("fileName");
		File file = new File("D:/" + fileName + ".pdf");
		try (InputStream inputStream = new FileInputStream(file);
				OutputStream outputStream = response.getOutputStream();) {
			response.setContentType("application/x-download");
			response.addHeader("Content-Disposition", "attachment;filename=" + fileName + ".pdf");
			IOUtils.copy(inputStream, outputStream);
			outputStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void itextPdf(String filePath, List<String> list, String fileName) throws Exception {

		File file = new File(filePath);
		file.getParentFile().mkdirs();
		filePath.replace("", "");
		if (fileName == null) {
			manipulatePdf(filePath, list);
		} else {
			manipulatePdf(filePath, list, fileName);
		}
	}

	private void manipulatePdf(String dest, List<String> list, String fileName) throws Exception {
		PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
		// Document doc = new Document(pdfDoc);
		/*
		 * PdfFont font =
		 * PdfFontFactory.createFont("C:/Windows/Fonts/simhei.ttf",
		 * PdfEncodings.IDENTITY_H,true);
		 */
		URL urls = ResourceUtils.getURL("classpath:");
		String path = urls.getPath();
		// System.out.println("path: " + path);
		PdfFont font = PdfFontFactory.createFont(path + "source/simhei.ttf", PdfEncodings.IDENTITY_H, true);
		// PdfFont font = PdfFontFactory.createFont("STSong-Light",
		// "UniGB-UCS2-H", true);
		Document doc = new Document(pdfDoc, new PageSize(255, 156));
		doc.setMargins(0, 0, 0, 0);
		float[] columnWidths = { (float) 1, 1, 1, 1, 1 };
		Table table = new Table(UnitValue.createPercentArray(columnWidths));
		int i = 0;
		for (String item : list) {
			com.itextpdf.layout.element.Cell cell = new com.itextpdf.layout.element.Cell()
					.add(new Paragraph("工单号").setFont(font).setFontSize(13));
			cell.setPaddingBottom(0);
			cell.setPaddingTop(0);
			cell.setHeight(19);
			// cell.setTextAlignment(TextAlignment.LEFT).add(new
			// Paragraph("工单号:").setFont(font));
			cell.setBorder(null);
			table.addCell(cell);
			cell = new com.itextpdf.layout.element.Cell(1, 2)
					.add(new Paragraph(fileName).setFont(font).setFontSize(13));
			cell.setPaddingBottom(0);
			cell.setPaddingTop(0);
			cell.setHeight(19);
			cell.setBorder(null);
			table.addCell(cell);
			String[] msg = item.split("\\|\\|");
			String specs = null;
			int index = msg[2].indexOf("PC=");
			if (index > -1) {
				String substr = msg[2].substring(index + 3);
				int inndex = substr.indexOf("MM");
				if (inndex > -1) {
					specs = substr.substring(0, inndex);
					// System.out.println("specs:" + specs);
				}
			}
			msg[2] = replaceBlank(msg[2]);
			/*
			 * if(msg[2].length()>30){ msg[2]= msg[2].substring(0, 30); }
			 */
			if (specs == null) {
				table.addCell(createBarcode(msg[0] + "#" + msg[1] + "#" + fileName, pdfDoc));
			} else {
				table.addCell(createBarcode(msg[0] + "#" + msg[1] + "#" + fileName + "#" + specs, pdfDoc));
			}
			cell = new com.itextpdf.layout.element.Cell().add(new Paragraph("部件号").setFont(font).setFontSize(13));
			cell.setHeight(19);
			cell.setPaddingBottom(0);
			cell.setPaddingTop(0);
			cell.setBorder(null);
			table.addCell(cell);
			cell = new com.itextpdf.layout.element.Cell(1, 2).add(new Paragraph(msg[0]).setFont(font).setFontSize(13));
			cell.setHeight(19);
			cell.setPaddingBottom(0);
			cell.setPaddingTop(0);
			cell.setBorder(null);
			table.addCell(cell);
			cell = new com.itextpdf.layout.element.Cell().add(new Paragraph("图号").setFont(font).setFontSize(13));
			cell.setHeight(19);
			cell.setPaddingBottom(0);
			cell.setPaddingTop(0);
			cell.setBorder(null);
			table.addCell(cell);
			cell = new com.itextpdf.layout.element.Cell(1, 2).add(new Paragraph(msg[4]).setFont(font).setFontSize(13));
			cell.setHeight(19);
			cell.setPaddingBottom(0);
			cell.setPaddingTop(0);
			cell.setBorder(null);
			table.addCell(cell);
			cell = new com.itextpdf.layout.element.Cell().add(new Paragraph("工艺").setFont(font).setFontSize(13));
			cell.setMinHeight(19);
			cell.setPaddingBottom(0);
			cell.setPaddingTop(0);
			cell.setBorder(null);
			table.addCell(cell);
			cell = new com.itextpdf.layout.element.Cell(1, 2).add(new Paragraph(msg[3]).setFont(font).setFontSize(13));
			cell.setMinHeight(19);
			cell.setPaddingBottom(0);
			cell.setPaddingTop(0);
			cell.setBorder(null);
			table.addCell(cell);
			cell = new com.itextpdf.layout.element.Cell().add(new Paragraph("数量").setFont(font).setFontSize(13));
			cell.setHeight(19);
			cell.setPaddingBottom(0);
			cell.setPaddingTop(0);
			cell.setBorder(null);
			table.addCell(cell);
			cell = new com.itextpdf.layout.element.Cell(1, 2).add(new Paragraph(msg[1]).setFont(font).setFontSize(13));
			cell.setHeight(19);
			cell.setPaddingBottom(0);
			cell.setPaddingTop(0);
			cell.setBorder(null);
			table.addCell(cell);

			cell = new com.itextpdf.layout.element.Cell().add(new Paragraph("规格").setFont(font).setFontSize(13));
			cell.setHeight(48);
			cell.setPaddingBottom(0);
			cell.setPaddingTop(0);
			cell.setBorder(null);
			table.addCell(cell);
			cell = new com.itextpdf.layout.element.Cell(1, 4).add(new Paragraph(msg[2]).setFont(font).setFontSize(10));
			cell.setHeight(48);
			cell.setPaddingBottom(0);
			cell.setPaddingTop(0);
			cell.setBorder(null);
			table.addCell(cell);
			cell = new com.itextpdf.layout.element.Cell(0, 5)
					.add(new Paragraph(String.valueOf(++i)).setFont(font).setFontSize(6));
			cell.setHeight(9);
			cell.setPaddingBottom(0);
			cell.setPaddingTop(0);
			cell.setBorder(null);
			cell.setTextAlignment(TextAlignment.CENTER);
			table.addCell(cell);
			// break;
		}
		doc.add(table);
		/*
		 * int n = pdfDoc.getNumberOfPages(); System.out.println("n:" + n); for
		 * (int i = 1; i <= n; i++) { doc.showTextAligned(new
		 * Paragraph(String.format("page %s of %s", i, n)), 10, 10,
		 * TextAlignment.RIGHT);
		 * 
		 * }
		 */
		doc.close();
	}

	private void manipulatePdf(String dest, List<String> list) throws Exception {
		PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
		//PdfFont font = PdfFontFactory.createFont("STSong-Light", "UniGB-UCS2-H", true);
		
		URL urls = ResourceUtils.getURL("classpath:");
		String path = urls.getPath();
		PdfFont font = PdfFontFactory.createFont(path + "source/simhei.ttf", PdfEncodings.IDENTITY_H, true);
		
		Document doc = new Document(pdfDoc, new PageSize(255, 156));
		doc.setMargins(0, 0, 0, 0);
		float[] columnWidths = { (float) 1.3, 1, 1, (float) 1, 1, 1 };
		Table table = new Table(UnitValue.createPercentArray(columnWidths));
		// Table table = new
		// Table(UnitValue.createPercentArray(6)).useAllAvailableWidth();

		for (String item : list) {
			String[] msg = item.split("\\|\\|");
			com.itextpdf.layout.element.Cell cell = new com.itextpdf.layout.element.Cell()
					.add(new Paragraph("代码\rCode").setFont(font).setFontSize(8));
			cell.setBorder(null);
			cell.setHeight(26);
			table.addCell(cell);
			cell = new com.itextpdf.layout.element.Cell(1, 2).add(new Paragraph(msg[0]).setFont(font).setFontSize(14));
			cell.setBorder(null);
			table.addCell(cell);
			cell = new com.itextpdf.layout.element.Cell()
					.add(new Paragraph("订单号\rOrder No.").setFont(font).setFontSize(8));
			cell.setBorder(null);
			cell.setHeight(26);
			table.addCell(cell);
			cell = new com.itextpdf.layout.element.Cell(1, 2).add(new Paragraph(msg[1]).setFont(font).setFontSize(13));
			cell.setBorder(null);
			cell.setPadding(0);
			table.addCell(cell);
			cell = new com.itextpdf.layout.element.Cell()
					.add(new Paragraph("物料号\rMaterial No.").setFont(font).setFontSize(8));
			cell.setBorder(null);
			cell.setHeight(26);
			table.addCell(cell);
			cell = new com.itextpdf.layout.element.Cell(1, 2).add(new Paragraph(msg[2]).setFont(font).setFontSize(13));
			cell.setBorder(null);

			table.addCell(cell);
			cell = new com.itextpdf.layout.element.Cell()
					.add(new Paragraph("项目号\r Item No.").setFont(font).setFontSize(8));
			cell.setHeight(26);
			cell.setBorder(null);
			table.addCell(cell);
			cell = new com.itextpdf.layout.element.Cell(1, 2).add(new Paragraph(msg[3]).setFont(font).setFontSize(9));
			cell.setBorder(null);
			// cell.setTextAlignment(TextAlignment.CENTER);
			// cell.setPaddingTop(5);
			//cell.setHeight(26);
			table.addCell(cell);
			cell = new com.itextpdf.layout.element.Cell()
					.add(new Paragraph("物料名称\rMaterial Na").setFont(font).setFontSize(8));
			cell.setBorder(null);

			table.addCell(cell);
			cell = new com.itextpdf.layout.element.Cell(1, 2).add(new Paragraph(msg[4]).setFont(font).setFontSize(8));
			cell.setBorder(null);
			cell.setHeight(28);
			table.addCell(cell);
			cell = new com.itextpdf.layout.element.Cell().add(new Paragraph("生产日期\rDate").setFont(font).setFontSize(8));
			cell.setBorder(null);
			cell.setPadding(0);
			cell.setHeight(28);
			table.addCell(cell);
			cell = new com.itextpdf.layout.element.Cell(1, 2).add(new Paragraph(msg[5]).setFont(font).setFontSize(13));
			cell.setBorder(null);
			cell.setHeight(26);
			table.addCell(cell);
			cell = new com.itextpdf.layout.element.Cell()
					.add(new Paragraph("炉批号\rHeat No.").setFont(font).setFontSize(8));
			cell.setBorder(null);
			table.addCell(cell);
			cell = new com.itextpdf.layout.element.Cell(1, 2).add(new Paragraph(msg[6]).setFont(font).setFontSize(14));
			cell.setBorder(null);
			table.addCell(cell);
			cell = new com.itextpdf.layout.element.Cell().add(new Paragraph("数量\rNumber").setFont(font).setFontSize(8));
			cell.setBorder(null);
			cell.setPadding(0);
			table.addCell(cell);
			cell = new com.itextpdf.layout.element.Cell(1, 2).add(new Paragraph(msg[7]).setFont(font).setFontSize(16));
			cell.setBorder(null);
			table.addCell(cell);
			cell = new com.itextpdf.layout.element.Cell().add(new Paragraph("供\r(检)").setFont(font).setFontSize(8));
			cell.setHeight(26);
			cell.setTextAlignment(TextAlignment.CENTER);
			cell.setBorder(null);
			cell.setPaddingBottom(0);
			cell.setPaddingTop(0);
			table.addCell(cell);

			Table table2 = new Table(UnitValue.createPercentArray(8)).useAllAvailableWidth();
			com.itextpdf.layout.element.Cell cell2 = new com.itextpdf.layout.element.Cell().add(new Paragraph(""));
			cell2.setBorder(null);
			table2.addCell(cell2);
			cell2 = new com.itextpdf.layout.element.Cell(1, 6).add(new Paragraph(msg[8]).setFont(font).setFontSize(8));
			table2.addCell(cell2);
			cell2 = new com.itextpdf.layout.element.Cell().add(new Paragraph(""));
			cell2.setBorder(null);
			table2.addCell(cell2);
			cell = new com.itextpdf.layout.element.Cell(1, 4).add(table2);
		
			cell.setTextAlignment(TextAlignment.CENTER);
			cell.setBorder(null);
			table.addCell(cell);
			cell = new com.itextpdf.layout.element.Cell().add(new Paragraph("需\r(检)").setFont(font).setFontSize(8));
			cell.setHeight(26);
			cell.setTextAlignment(TextAlignment.CENTER);
			cell.setBorder(null);
			cell.setPaddingBottom(0);
			cell.setPaddingTop(0);
			table.addCell(cell);
		}
		doc.add(table);

		doc.close();

	}

	private com.itextpdf.layout.element.Cell createBarcode(String code, PdfDocument pdfDoc) {
		Map<EncodeHintType, Object> hints = new HashMap<>();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
		BarcodeQRCode barcodeQRCode = new BarcodeQRCode(null, hints);
		// BarcodeQRCode barcodeQRCode = new BarcodeQRCode();
		barcodeQRCode.setCode(code);
		PdfFormXObject pdfFormXObject = barcodeQRCode.createFormXObject(pdfDoc);
		com.itextpdf.layout.element.Table table2 = new com.itextpdf.layout.element.Table(
				UnitValue.createPercentArray(1)).useAllAvailableWidth();
		table2.setHorizontalAlignment(com.itextpdf.layout.property.HorizontalAlignment.CENTER);
		com.itextpdf.layout.element.Cell cell2 = new com.itextpdf.layout.element.Cell().setBorder(null);
		// cell2.setHeight(146);
		// cell2.setWidth(100);
		cell2.setPadding(0);
		cell2.add(new Image(pdfFormXObject).setHeight(95).setWidth(95));

		table2.addCell(cell2);
		com.itextpdf.layout.element.Cell cell = new com.itextpdf.layout.element.Cell(5, 2).add(table2);
		cell.setBorder(null);
		cell.setPadding(0);
		return cell;
	}

	private List<Map<String, Object>> checkRootNode(int rootID, HSSFSheet sheet, List<String> listMaterialNum,
			List<Map<String, Object>> listMsg, ExcelPara excelPara, List<HongXunBomTree> hongXunBomTreeList) {
		if (iBomTreeDao.get(rootID).getParentID().equals("99999")) {
			boolean flag = false;
			List<HongXunBomTree> childrens = getChildren(Integer.valueOf(rootID)); // 查看ROOT
																					// 节点有几个子节点
			for (HongXunBomTree item : childrens) {
				if (item.getBomMaterialNum().equals(sheet.getSheetName())) { // 如果节点与sheet
																				// name
																				// 相同
																				// ，获取nodeid
					flag = true;
					excelPara.setNodeID(item.getNodeID());
					// System.out.println("nodeID: " + nodeID);
					HongXunBomTree bomTreeEntity = new HongXunBomTree();
					bomTreeEntity.setParentID(String.valueOf(excelPara.getNodeID()));
					List<HongXunBomTree> hongXunBomTrees = iBomTreeDao.quary(bomTreeEntity); // 获取到sheet
																							// 的
																							// 下层节点
					excelPara.setBomLength(hongXunBomTrees.size());
					// System.out.println("bomLength:" + bomLength);
					HongXunBomTree tempHongXunBomTree = new HongXunBomTree();
					for (HongXunBomTree hongXunBomTree : hongXunBomTrees) { // 查看是否有一级重复料号，计sheet子节点含子下层等所有节点数量
						if (!listMaterialNum.contains(hongXunBomTree.getBomMaterialNum())) {
							listMaterialNum.add(hongXunBomTree.getBomMaterialNum());
						} else {
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("error",
									sheet.getSheetName() + " 一级物料号:" + hongXunBomTree.getBomMaterialNum() + "有重复");
							listMsg.add(map);
							return listMsg;
						}
						tempHongXunBomTree.setTopID(hongXunBomTree.getNodeID());
						hongXunBomTrees = iBomTreeDao.quary(tempHongXunBomTree);
						if (hongXunBomTrees != null) {
							excelPara.setBomLength(excelPara.getBomLength() + hongXunBomTrees.size());
						}
					}
					break;
				}
			}
			// System.out.println("bomLength: " + bomLength);
			if (flag == false) { // root 下无节，创建新节点
				HongXunBomTree node = new HongXunBomTree();
				node.setBomMaterialNum(sheet.getSheetName());
				node.setParentID("0");
				excelPara.setNodeid(excelPara.getNodeid() + 1);
				node.setNodeID(excelPara.getNodeid());
				excelPara.setNodeID(node.getNodeID());
				hongXunBomTreeList.add(node);
			}
		} else {
			// j=sheets;
			// nodeID = Integer.valueOf(rootID);
		}
		return listMsg;
	}

	private HongXunBomTree readOneRows(int end, HSSFSheet sheet, int k, int count, List<String> list) {
		HongXunBomTree hongXunBomTree = new HongXunBomTree();
		for (int i = 0; i < end; i++) {
			Cell cell = sheet.getRow(k).getCell(i);
			String obj = null;

			obj = getValue(cell);
			// System.out.println(i + " obj:" + obj);
			if (obj != null) {
				if (count < 1) {
					list.add(obj);
				} else {
					String temStr = list.get(i);
					if (temStr.equals("序号")) {
						hongXunBomTree.setSn(Double.valueOf(obj).intValue());
						if (hongXunBomTree.getSn() == 1) {
							hongXunBomTree.setBomGroup(sheet.getSheetName());
						}
					} else if (temStr.equals("数量")) {
						// hongXunBomTree.setSe(Double.valueOf(obj).intValue());
						// System.out.println("数量："+Double.valueOf(obj).intValue());
						if (isNumericZidai(obj)) {
							hongXunBomTree.setBomQuantity(Integer.valueOf(obj));
						} else {
							hongXunBomTree.setBomQuantity(0);
						}

					} else if (temStr.equals("技术规格")) {
						hongXunBomTree.setBomSpacification(obj);
					} else if (temStr.equals("长度")) {
						hongXunBomTree.setBomLength(obj);
					} else if (temStr.equals("图号")) {
						hongXunBomTree.setBomDrawingNum(obj);
					} else if (temStr.equals("位置")) {

						hongXunBomTree.setBomPosion(obj);
					} else if (temStr.equals("材料")) {
						hongXunBomTree.setBomMaterial(obj);
					} else if (temStr.equals("部件号")) {
						hongXunBomTree.setBomMaterialNum(obj);

					} else if (temStr.equals("属性")) {
						hongXunBomTree.setAttribute(obj);
					} else if (temStr.equals("库位编码")) {
						hongXunBomTree.setBomLocation(obj);
					} else if (temStr.equals("备注")) {
						hongXunBomTree.setRemark(obj);
					} else if (temStr.equals("下料")) {
						hongXunBomTree.setBomMaterialCuting("下料");
					} else if (temStr.equals("钣金")) {
						hongXunBomTree.setBomPlateCuting("钣金");
					} else if (temStr.equals("焊接")) {
						if (obj.equals("B.1") || obj.equals("B1")) {
							hongXunBomTree.setBomMaterialWeld("焊接.1");
						} else {
							hongXunBomTree.setBomMaterialWeld("焊接");
						}
					} else if (temStr.equals("外协")) {
						hongXunBomTree.setBomWeiwai(obj);
					} else if (temStr.equals("喷涂")) {
						hongXunBomTree.setBomPainting(obj);
					} else if (temStr.equals("装配")) {
						hongXunBomTree.setBomAssemble("装配");
					}
				}
			}
		}
		return hongXunBomTree;
	}

	private boolean isNumericZidai(String str) {
		for (int i = 0; i < str.length(); i++) {
			// System.out.println(str.charAt(i));
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	@Override
	public List<Map<String, Object>> excelImport(HSSFWorkbook workbook, String rootID) {
		// TODO Auto-generated method stub
		// System.out.println("parentID:
		// "+stockDao.hongXunBomTreeFindById(Integer.valueOf(rootID)).getParentID());
		// boolean result = false;
		List<Map<String, Object>> listMsg = new ArrayList<Map<String, Object>>();
		List<HongXunBomTree> hongXunBomTreeList = new ArrayList<HongXunBomTree>();
		// boolean flaga = true;
		// int sumRows = 0;
		// int nodeid = 0;
		ExcelPara excelPara = new ExcelPara();
		excelPara.setFlaga(true);
		excelPara.setSumRows(0);
		excelPara.setNodeid(0);
		try {
			// Workbook workbook = HSSFWorkbookFactory.create(is);
			// HSSFWorkbook workbook = new HSSFWorkbook(fs);
			// evaluator=workbook.getCreationHelper().createFormulaEvaluator();
			int sheets = workbook.getNumberOfSheets();
			if (sheets > 5) {
				sheets = 5;
			}
			List<String> listMaterialNum = new ArrayList<String>();
			HongXunBomTree topMaterialNum = null;
			for (int j = 0; j < sheets; j++) {
				// int nodeID = 0; int bomLength=0;

				excelPara.setNodeID(0);
				excelPara.setBomLength(0);
				HSSFSheet sheet = workbook.getSheetAt(j);
				List<Map<String, Object>> errMsg = checkRootNode(Integer.valueOf(rootID), sheet, listMaterialNum,
						listMsg, excelPara, hongXunBomTreeList);
				hongXunBomTreeList.get(hongXunBomTreeList.size() - 1).getNodeID();
				if (errMsg.size() > 0) {
					// workbook.close();
					// fs.close()();
					return errMsg;
				}
				/*
				 * if(stockDao.hongXunBomTreeFindById(Integer.valueOf(rootID)).
				 * getParentID().equals("99999")){ List<HongXunBomTree>
				 * childrens = getChildren(Integer.valueOf(rootID)); // 0
				 * for(HongXunBomTree item: childrens){
				 * if(item.getBomMaterialNum().equals(sheet.getSheetName())){
				 * flag = true; if(flaga==false){ nodeID = item.getIdc(); }else{
				 * nodeID = item.getNodeID(); } //System.out.println("nodeID: "
				 * + nodeID); //List<HongXunBomTree> hongXunBomTrees =
				 * (List<HongXunBomTree>)stockDao.quarywithpara(
				 * "HongXunBomTree", "parentID", String.valueOf(nodeID));
				 * HongXunBomTree bomTreeEntity = new HongXunBomTree();
				 * bomTreeEntity.setParentID(String.valueOf(nodeID));
				 * List<HongXunBomTree> hongXunBomTrees =
				 * stockDao.quary(bomTreeEntity); //获取到sheet 的 下层节点 bomLength =
				 * hongXunBomTrees.size(); //System.out.println("bomLength:" +
				 * bomLength); HongXunBomTree tempHongXunBomTree = new
				 * HongXunBomTree(); for(HongXunBomTree hongXunBomTree:
				 * hongXunBomTrees){
				 * if(!listMaterialNum.contains(hongXunBomTree.getBomMaterialNum
				 * ())){
				 * listMaterialNum.add(hongXunBomTree.getBomMaterialNum());
				 * }else{ //System.out.println("一级物料号:" +
				 * hongXunBomTree.getBomMaterialNum() + "有重复");
				 * Map<String,Object> map = new HashMap<String,Object>();
				 * map.put("error", "一级物料号:" +
				 * hongXunBomTree.getBomMaterialNum() + "有重复");
				 * listMsg.add(map); //fs.close()(); //workbook.close()();
				 * return listMsg; } if(flaga==false){
				 * tempHongXunBomTree.setTopID(hongXunBomTree.getIdc()); }else{
				 * tempHongXunBomTree.setTopID(hongXunBomTree.getNodeID()); }
				 * 
				 * hongXunBomTrees = stockDao.quary(tempHongXunBomTree);
				 * if(hongXunBomTrees != null){ bomLength = bomLength +
				 * hongXunBomTrees.size(); } }
				 * 
				 * break; } }
				 * 
				 * System.out.println("bomLength:  " + bomLength); if(flag ==
				 * false){ HongXunBomTree node = new HongXunBomTree();
				 * node.setBomMaterialNum(sheet.getSheetName()); //0
				 * if(flaga==false){ node.setParentID(rootID);
				 * stockDao.save(node); nodeID = node.getIdc(); }else{
				 * node.setParentID("0"); node.setNodeID(++nodeid); nodeID =
				 * node.getNodeID(); hongXunBomTreeList.add(node); }
				 * 
				 * } }else{ j=sheets; nodeID = Integer.valueOf(rootID); }
				 */
				List<String> list = new ArrayList<String>();
				int end = sheet.getRow(0).getLastCellNum();
				int count = 0;
				int topID = 0;
				int length = 0;
				int css = 0;
				String fatherId1 = "";
				String fatherId2 = "";
				String fatherId3 = "";
				String fatherId8 = "";
				String fatherId4 = "";
				String fatherId5 = "";
				String fatherId6 = "";
				String fatherId7 = "";
				// int sf=0;
				int rowNum = sheet.getLastRowNum();
				System.out.println("rowNum :" + rowNum);
				// sumRows = sumRows + rowNum;
				// System.out.println(sumRows);
				for (int k = 0; k <= rowNum; k++) {
					// System.out.println("k: " + k);
					/*
					 * System.out.println("- " +
					 * sheet.getRow(k).getFirstCellNum() + ": " +
					 * sheet.getRow(k).getLastCellNum());
					 */
					if (sheet.getRow(k) == null) {
						// sumRows = sumRows -1;
						excelPara.setSumRows(excelPara.getSumRows() + k - 1);
						System.out.println("1->" + excelPara.getSumRows());
						break;

					} else if (sheet.getRow(k).getFirstCellNum() != 0 || sheet.getRow(k).getLastCellNum() < 20) {

						excelPara.setSumRows(excelPara.getSumRows() + k - 1);
						System.out.println("2->" + excelPara.getSumRows());
						break;

					} else if (k == rowNum) {
						excelPara.setSumRows(excelPara.getSumRows() + k);
						// System.out.println("2->" + sumRows);
					}
					HongXunBomTree hongXunBomTree = readOneRows(end, sheet, k, count, list);
					/*
					 * if (hongXunBomTree.getBomQuantity() == 0) { Map<String,
					 * Object> map = new HashMap<String, Object>();
					 * map.put("error", sheet.getSheetName() + "料号:" +
					 * hongXunBomTree.getBomMaterialNum() + "检查这一行的数量");
					 * listMsg.add(map); return listMsg; }
					 */
					/*
					 * HongXunBomTree hongXunBomTree = new HongXunBomTree(); for
					 * (int i = 0; i < end; i++) { Cell cell =
					 * sheet.getRow(k).getCell(i); String obj = null;
					 * 
					 * obj = getValue(cell);
					 * 
					 * 
					 * if(obj!=null){ if (count < 1) { list.add(obj); } else {
					 * String temStr = list.get(i); if (temStr.equals("序号")) {
					 * hongXunBomTree.setSn(Double.valueOf(obj).intValue());
					 * if(hongXunBomTree.getSn()==1){
					 * hongXunBomTree.setBomGroup(sheet.getSheetName()); } }
					 * else if (temStr.equals("数量")) {
					 * //hongXunBomTree.setSe(Double.valueOf(obj).intValue());
					 * hongXunBomTree.setBomQuantity(Integer.valueOf(obj)); }
					 * else if (temStr.equals("技术规格")) {
					 * hongXunBomTree.setBomSpacification(obj); } else if
					 * (temStr.equals("长度")) { hongXunBomTree.setBomLength(obj);
					 * } else if (temStr.equals("图号")) {
					 * hongXunBomTree.setBomDrawingNum(obj); } else if
					 * (temStr.equals("位置")) { hongXunBomTree.setBomPosion(obj);
					 * } else if (temStr.equals("材料")) {
					 * hongXunBomTree.setBomMaterial(obj); } else if
					 * (temStr.equals("部件号")) {
					 * hongXunBomTree.setBomMaterialNum(obj);
					 * 
					 * } else if (temStr.equals("属性")) {
					 * hongXunBomTree.setAttribute(obj); } else if
					 * (temStr.equals("库位编码")) {
					 * hongXunBomTree.setBomLocation(obj); }else if
					 * (temStr.equals("备注")) { hongXunBomTree.setRemark(obj); }
					 * else if (temStr.equals("下料")) {
					 * hongXunBomTree.setBomMaterialCuting("下料"); } else if
					 * (temStr.equals("钣金")) {
					 * hongXunBomTree.setBomPlateCuting("钣金"); } else if
					 * (temStr.equals("焊接")) { if(obj.equals("B.1") ||
					 * obj.equals("B1")){
					 * hongXunBomTree.setBomMaterialWeld("焊接.1"); }else{
					 * hongXunBomTree.setBomMaterialWeld("焊接"); } } else if
					 * (temStr.equals("外协")) { hongXunBomTree.setBomWeiwai(obj);
					 * } else if (temStr.equals("喷涂")) {
					 * hongXunBomTree.setBomPainting(obj); } else if
					 * (temStr.equals("装配")) {
					 * hongXunBomTree.setBomAssemble("装配"); } } } }
					 */
					if (count < 1) {
						count++;
					} else {
						if (hongXunBomTree.getSn() == null) {
							System.out.println("k:" + k + " -->" + sheet.getRow(k).getLastCellNum());

							Map<String, Object> map = new HashMap<String, Object>();
							map.put("error",
									sheet.getSheetName() + "一级物料号:" + hongXunBomTree.getBomMaterialNum() + "序号为空");
							listMsg.add(map);
							// workbook.close()();
							// fs.close()();
							return listMsg;
						}
						if (hongXunBomTree.getSn() != 1) {
							if (hongXunBomTree.getBomPosion() != null) {
								if (hongXunBomTree.getBomPosion().equals("")) {
									Map<String, Object> map = new HashMap<String, Object>();
									map.put("error",
											sheet.getSheetName() + "位置号:" + hongXunBomTree.getBomPosion() + "为空");
									listMsg.add(map);
									return listMsg;
								} else if (hongXunBomTree.getBomPosion().equals("0")
										|| hongXunBomTree.getBomPosion().equals("00")
										|| hongXunBomTree.getBomPosion().equals("000")
										|| hongXunBomTree.getBomPosion().equals("0000")
										|| hongXunBomTree.getBomPosion().equals("00000")
										|| hongXunBomTree.getBomPosion().equals("000000")) {
									Map<String, Object> map = new HashMap<String, Object>();
									map.put("error",
											sheet.getSheetName() + "位置号:" + hongXunBomTree.getBomPosion() + "全为0");
									listMsg.add(map);
									return listMsg;
								}
							} else {
								Map<String, Object> map = new HashMap<String, Object>();
								map.put("error", sheet.getSheetName() + "位置号:" + hongXunBomTree.getBomPosion() + "为空");
								listMsg.add(map);
								return listMsg;
							}
						}

						if (hongXunBomTree.getSn() == 1) {
							// System.out.println("getBomMaterialNum: " +
							// hongXunBomTree.getBomMaterialNum());
							topMaterialNum = hongXunBomTree;
							fatherId1 = "";
							fatherId2 = "";
							fatherId3 = "";
							fatherId4 = "";
							fatherId5 = "";
							fatherId6 = "";
							fatherId7 = "";
							if (!listMaterialNum.contains(hongXunBomTree.getBomMaterialNum())) {
								listMaterialNum.add(hongXunBomTree.getBomMaterialNum());
							} else {
								Map<String, Object> map = new HashMap<String, Object>();
								map.put("error",
										sheet.getSheetName() + "一级物料号:" + hongXunBomTree.getBomMaterialNum() + "有重复");
								listMsg.add(map);
								// workbook.close()();
								// fs.close()();
								return listMsg;
							}

							hongXunBomTree.setParentID(String.valueOf(excelPara.getNodeID()));
							if (excelPara.isFlaga() == false) {
								iBomTreeDao.saveEntity(hongXunBomTree);
								fatherId1 = String.valueOf(hongXunBomTree.getIdc());
								// System.out.println("fatherId1: " +
								// fatherId1);
								topID = hongXunBomTree.getIdc();
							} else {
								excelPara.setNodeid(excelPara.getNodeid() + 1);
								hongXunBomTree.setNodeID(excelPara.getNodeid());
								hongXunBomTreeList.add(hongXunBomTree);
								fatherId1 = String.valueOf(hongXunBomTree.getNodeID());
								// System.out.println("fatherId1: " +
								// fatherId1);
								topID = hongXunBomTree.getNodeID();
							}

							length = -1;
							css = 1;

							// System.out.println("css: " + css);
						} else if (css == 1) {
							for (int i = 0; i < hongXunBomTree.getBomPosion().length(); i++) {
								if (!hongXunBomTree.getBomPosion().subSequence(i, i + 1).equals("0")) {
									hongXunBomTree.setTopID(topID);
									if (length == -1) {
										// System.out.println("-1 fatherId1: " +
										// fatherId1);
										hongXunBomTree.setParentID(fatherId1);
										if (excelPara.isFlaga() == false) {
											iBomTreeDao.saveEntity(hongXunBomTree);
											fatherId2 = String.valueOf(hongXunBomTree.getIdc());
										} else {
											excelPara.setNodeid(excelPara.getNodeid() + 1);
											hongXunBomTree.setNodeID(excelPara.getNodeid());
											hongXunBomTreeList.add(hongXunBomTree);
											fatherId2 = String.valueOf(hongXunBomTree.getNodeID());
										}
										// System.out.println("css: " + css);
									} else {
										if (length == i) {
											hongXunBomTree.setParentID(fatherId1);
											// System.out.println("css: " + css
											// + " fatherId1:" + fatherId1);
											if (excelPara.isFlaga() == false) {
												iBomTreeDao.saveEntity(hongXunBomTree);
												fatherId2 = String.valueOf(hongXunBomTree.getIdc());
											} else {
												excelPara.setNodeid(excelPara.getNodeid() + 1);
												hongXunBomTree.setNodeID(excelPara.getNodeid());
												hongXunBomTreeList.add(hongXunBomTree);
												fatherId2 = String.valueOf(hongXunBomTree.getNodeID());
											}
										} else {
											if (i > length) {
												hongXunBomTree.setParentID(fatherId2);

												if (excelPara.isFlaga() == false) {
													iBomTreeDao.saveEntity(hongXunBomTree);
													fatherId3 = String.valueOf(hongXunBomTree.getIdc());
												} else {
													excelPara.setNodeid(excelPara.getNodeid() + 1);
													hongXunBomTree.setNodeID(excelPara.getNodeid());
													hongXunBomTreeList.add(hongXunBomTree);
													fatherId3 = String.valueOf(hongXunBomTree.getNodeID());
												}
												css = 2;
												// System.out.println("css: " +
												// css + " fatherId2:" +
												// fatherId2);
											} else {
												Map<String, Object> map = new HashMap<String, Object>();
												map.put("error",
														sheet.getSheetName() + " 一级料号:"
																+ topMaterialNum.getBomMaterialNum() + "下的 " + "二级物料号:"
																+ hongXunBomTree.getBomMaterialNum() + "位置号错误");
												listMsg.add(map);
												// workbook.close()();
												// fs.close()();
												return listMsg;
											}
										}
									}
									length = i;
									// System.out.println("i: " + i);
									break;
								}
							}
						} else if (css == 2) {
							for (int i = 0; i < hongXunBomTree.getBomPosion().length(); i++) {
								if (hongXunBomTree.getBomPosion() == null) {
									Map<String, Object> map = new HashMap<String, Object>();
									map.put("error",
											sheet.getSheetName() + " 一级料号:" + topMaterialNum.getBomMaterialNum() + "下的 "
													+ "三级物料号:" + hongXunBomTree.getBomMaterialNum() + "位置号错误");
									listMsg.add(map);
									// workbook.close()();
									// fs.close()();
									return listMsg;
								}
								if (!hongXunBomTree.getBomPosion().subSequence(i, i + 1).equals("0")) {
									hongXunBomTree.setTopID(topID);
									if (length == i) {
										hongXunBomTree.setParentID(fatherId2);
										// System.out.println("css: " + css + "
										// fatherId2:" + fatherId2);
										if (excelPara.isFlaga() == false) {
											iBomTreeDao.saveEntity(hongXunBomTree);
											fatherId3 = String.valueOf(hongXunBomTree.getIdc());
										} else {
											excelPara.setNodeid(excelPara.getNodeid() + 1);
											hongXunBomTree.setNodeID(excelPara.getNodeid());
											hongXunBomTreeList.add(hongXunBomTree);
											fatherId3 = String.valueOf(hongXunBomTree.getNodeID());
										}
									} else {
										if (i > length) {
											if (i - length > 1) {
												Map<String, Object> map = new HashMap<String, Object>();
												map.put("error",
														sheet.getSheetName() + " 一级料号:"
																+ topMaterialNum.getBomMaterialNum() + "下的 " + "三级物料号:"
																+ hongXunBomTree.getBomMaterialNum() + "位置号错误");
												listMsg.add(map);
												// workbook.close()();
												//// fs.close()();
												return listMsg;
											}
											hongXunBomTree.setParentID(fatherId3);

											if (excelPara.isFlaga() == false) {
												iBomTreeDao.saveEntity(hongXunBomTree);
												fatherId4 = String.valueOf(hongXunBomTree.getIdc());
											} else {
												excelPara.setNodeid(excelPara.getNodeid() + 1);
												hongXunBomTree.setNodeID(excelPara.getNodeid());
												hongXunBomTreeList.add(hongXunBomTree);
												fatherId4 = String.valueOf(hongXunBomTree.getNodeID());
											}
											css = 3;
											// System.out.println("css: " + css
											// + " fatherId3:" + fatherId3);
										} else {
											if (length - i == 1) {
												hongXunBomTree.setParentID(fatherId1);

												if (excelPara.isFlaga() == false) {
													iBomTreeDao.saveEntity(hongXunBomTree);
													fatherId2 = String.valueOf(hongXunBomTree.getIdc());
												} else {
													excelPara.setNodeid(excelPara.getNodeid() + 1);
													hongXunBomTree.setNodeID(excelPara.getNodeid());
													hongXunBomTreeList.add(hongXunBomTree);
													fatherId2 = String.valueOf(hongXunBomTree.getNodeID());
												}
												css = 1;
											} else {
												Map<String, Object> map = new HashMap<String, Object>();
												map.put("error",
														sheet.getSheetName() + " 一级料号:"
																+ topMaterialNum.getBomMaterialNum() + "下的 " + "三级物料号:"
																+ hongXunBomTree.getBomMaterialNum() + "位置号错误");
												listMsg.add(map);
												// workbook.close()();
												//// fs.close()()();
												return listMsg;
											}
											// System.out.println("css: " + css
											// + " fatherId1:" + fatherId1);
										}
									}

									length = i;
									// System.out.println("i: " + i);
									break;
								} /*
									 * else{ Map<String,Object> map = new
									 * HashMap<String,Object>();
									 * map.put("error", sheet.getSheetName() +
									 * " 一级料号:" +
									 * topMaterialNum.getBomMaterialNum() +
									 * "下的 " + "三级物料号:" +
									 * hongXunBomTree.getBomMaterialNum() +
									 * "位置号错误"); listMsg.add(map); return
									 * listMsg; }
									 */
							}
						} else if (css == 3) {
							for (int i = 0; i < hongXunBomTree.getBomPosion().length(); i++) {
								if (hongXunBomTree.getBomPosion() == null) {
									Map<String, Object> map = new HashMap<String, Object>();
									map.put("error",
											sheet.getSheetName() + " 一级料号:" + topMaterialNum.getBomMaterialNum() + "下的 "
													+ "四级物料号:" + hongXunBomTree.getBomMaterialNum() + "位置号错误");
									listMsg.add(map);
									// workbook.close()();
									//// fs.close()()();
									return listMsg;
								}
								if (!hongXunBomTree.getBomPosion().subSequence(i, i + 1).equals("0")) {
									hongXunBomTree.setTopID(topID);

									if (length == i) {
										hongXunBomTree.setParentID(fatherId3);
										// stockDao.saveEntity(hongXunBomTree);
										// fatherId4 =
										// String.valueOf(hongXunBomTree.getIdc());
										if (excelPara.isFlaga() == false) {
											iBomTreeDao.saveEntity(hongXunBomTree);
											fatherId4 = String.valueOf(hongXunBomTree.getIdc());
										} else {
											excelPara.setNodeid(excelPara.getNodeid() + 1);
											hongXunBomTree.setNodeID(excelPara.getNodeid());
											hongXunBomTreeList.add(hongXunBomTree);
											fatherId4 = String.valueOf(hongXunBomTree.getNodeID());
										}
										// System.out.println("css: " + css + "
										// fatherId3:" + fatherId3);
									} else {
										if (i > length) {
											if (i - length > 1) {
												Map<String, Object> map = new HashMap<String, Object>();
												map.put("error",
														sheet.getSheetName() + " 一级料号:"
																+ topMaterialNum.getBomMaterialNum() + "下的 " + "四级物料号:"
																+ hongXunBomTree.getBomMaterialNum() + "位置号错误");
												listMsg.add(map);
												// workbook.close()();
												//// fs.close()()();
												return listMsg;
											}
											hongXunBomTree.setParentID(fatherId4);
											// stockDao.saveEntity(hongXunBomTree);
											// fatherId5 =
											// String.valueOf(hongXunBomTree.getIdc());
											if (excelPara.isFlaga() == false) {
												iBomTreeDao.saveEntity(hongXunBomTree);
												fatherId5 = String.valueOf(hongXunBomTree.getIdc());
											} else {
												excelPara.setNodeid(excelPara.getNodeid() + 1);
												hongXunBomTree.setNodeID(excelPara.getNodeid());
												hongXunBomTreeList.add(hongXunBomTree);
												fatherId5 = String.valueOf(hongXunBomTree.getNodeID());
											}
											css = 4;
											// System.out.println("css: " + css
											// + " fatherId4:" + fatherId4);
										} else {
											if (length - i == 1) {
												hongXunBomTree.setParentID(fatherId2);
												// stockDao.saveEntity(hongXunBomTree);
												// fatherId3 =
												// String.valueOf(hongXunBomTree.getIdc());
												if (excelPara.isFlaga() == false) {
													iBomTreeDao.saveEntity(hongXunBomTree);
													fatherId3 = String.valueOf(hongXunBomTree.getIdc());
												} else {
													excelPara.setNodeid(excelPara.getNodeid() + 1);
													hongXunBomTree.setNodeID(excelPara.getNodeid());
													hongXunBomTreeList.add(hongXunBomTree);
													fatherId3 = String.valueOf(hongXunBomTree.getNodeID());
												}
												css = 2;
												// System.out.println("css: " +
												// css + " fatherId1:" +
												// fatherId2);
											} else if (length - i == 2) {
												hongXunBomTree.setParentID(fatherId1);
												// stockDao.saveEntity(hongXunBomTree);
												// fatherId2 =
												// String.valueOf(hongXunBomTree.getIdc());
												if (excelPara.isFlaga() == false) {
													iBomTreeDao.saveEntity(hongXunBomTree);
													fatherId2 = String.valueOf(hongXunBomTree.getIdc());
												} else {
													excelPara.setNodeid(excelPara.getNodeid() + 1);
													hongXunBomTree.setNodeID(excelPara.getNodeid());
													hongXunBomTreeList.add(hongXunBomTree);
													fatherId2 = String.valueOf(hongXunBomTree.getNodeID());
												}
												css = 1;
												// System.out.println("css: " +
												// css + " fatherId2:" +
												// fatherId1);
											} else {
												Map<String, Object> map = new HashMap<String, Object>();
												map.put("error",
														sheet.getSheetName() + " 一级料号:"
																+ topMaterialNum.getBomMaterialNum() + "下的 " + "四级物料号:"
																+ hongXunBomTree.getBomMaterialNum() + "位置号错误");
												listMsg.add(map);
												// workbook.close()();
												//// fs.close()()();
												return listMsg;
											}
										}
									}

									length = i;
									// System.out.println("i: " + i);
									break;
								} /*
									 * else{ Map<String,Object> map = new
									 * HashMap<String,Object>();
									 * map.put("error", sheet.getSheetName() +
									 * " 一级料号:" +
									 * topMaterialNum.getBomMaterialNum() +
									 * "下的 " + "四级物料号:" +
									 * hongXunBomTree.getBomMaterialNum() +
									 * "位置号错误"); listMsg.add(map); return
									 * listMsg; }
									 */
							}
						} else if (css == 4) {
							for (int i = 0; i < hongXunBomTree.getBomPosion().length(); i++) {
								if (hongXunBomTree.getBomPosion() == null) {
									Map<String, Object> map = new HashMap<String, Object>();
									map.put("error",
											sheet.getSheetName() + " 一级料号:" + topMaterialNum.getBomMaterialNum() + "下的 "
													+ "五级物料号:" + hongXunBomTree.getBomMaterialNum() + "位置号错误");
									listMsg.add(map);
									// workbook.close()();
									//// fs.close()()();
									return listMsg;
								}
								if (!hongXunBomTree.getBomPosion().subSequence(i, i + 1).equals("0")) {
									hongXunBomTree.setTopID(topID);

									if (length == i) {
										hongXunBomTree.setParentID(fatherId4);
										// stockDao.saveEntity(hongXunBomTree);
										// fatherId5 =
										// String.valueOf(hongXunBomTree.getIdc());
										if (excelPara.isFlaga() == false) {
											iBomTreeDao.saveEntity(hongXunBomTree);
											fatherId5 = String.valueOf(hongXunBomTree.getIdc());
										} else {
											excelPara.setNodeid(excelPara.getNodeid() + 1);
											hongXunBomTree.setNodeID(excelPara.getNodeid());
											hongXunBomTreeList.add(hongXunBomTree);
											fatherId5 = String.valueOf(hongXunBomTree.getNodeID());
										}
										// System.out.println("css: " + css + "
										// fatherId4:" + fatherId4);
									} else {
										if (i > length) {
											if (i - length > 1) {
												Map<String, Object> map = new HashMap<String, Object>();
												map.put("error",
														sheet.getSheetName() + " 一级料号:"
																+ topMaterialNum.getBomMaterialNum() + "下的 " + "五级物料号:"
																+ hongXunBomTree.getBomMaterialNum() + "位置号错误");
												listMsg.add(map);
												// workbook.close()();
												//// fs.close()()();
												return listMsg;
											}
											hongXunBomTree.setParentID(fatherId5);
											// stockDao.saveEntity(hongXunBomTree);
											// fatherId6 =
											// String.valueOf(hongXunBomTree.getIdc());
											if (excelPara.isFlaga() == false) {
												iBomTreeDao.saveEntity(hongXunBomTree);
												fatherId6 = String.valueOf(hongXunBomTree.getIdc());
											} else {
												excelPara.setNodeid(excelPara.getNodeid() + 1);
												hongXunBomTree.setNodeID(excelPara.getNodeid());
												hongXunBomTreeList.add(hongXunBomTree);
												fatherId6 = String.valueOf(hongXunBomTree.getNodeID());
											}
											css = 5;
											// System.out.println("css: " + css
											// + " fatherId5:" + fatherId5);
										} else {
											if (length - i == 1) {
												hongXunBomTree.setParentID(fatherId3);
												// stockDao.saveEntity(hongXunBomTree);
												// fatherId4 =
												// String.valueOf(hongXunBomTree.getIdc());
												if (excelPara.isFlaga() == false) {
													iBomTreeDao.saveEntity(hongXunBomTree);
													fatherId4 = String.valueOf(hongXunBomTree.getIdc());
												} else {
													excelPara.setNodeid(excelPara.getNodeid() + 1);
													hongXunBomTree.setNodeID(excelPara.getNodeid());
													hongXunBomTreeList.add(hongXunBomTree);
													fatherId4 = String.valueOf(hongXunBomTree.getNodeID());
												}
												css = 3;
												// System.out.println("css: " +
												// css + " fatherId1:" +
												// fatherId1);
											} else if (length - i == 2) {
												hongXunBomTree.setParentID(fatherId2);
												// stockDao.saveEntity(hongXunBomTree);
												// fatherId3 =
												// String.valueOf(hongXunBomTree.getIdc());
												if (excelPara.isFlaga() == false) {
													iBomTreeDao.saveEntity(hongXunBomTree);
													fatherId3 = String.valueOf(hongXunBomTree.getIdc());
												} else {
													excelPara.setNodeid(excelPara.getNodeid() + 1);
													hongXunBomTree.setNodeID(excelPara.getNodeid());
													hongXunBomTreeList.add(hongXunBomTree);
													fatherId3 = String.valueOf(hongXunBomTree.getNodeID());
												}
												css = 2;
												// System.out.println("css: " +
												// css + " fatherId2:" +
												// fatherId2);
											} else if (length - i == 3) {
												hongXunBomTree.setParentID(fatherId1);
												// stockDao.saveEntity(hongXunBomTree);
												// fatherId2 =
												// String.valueOf(hongXunBomTree.getIdc());
												if (excelPara.isFlaga() == false) {
													iBomTreeDao.saveEntity(hongXunBomTree);
													fatherId2 = String.valueOf(hongXunBomTree.getIdc());
												} else {
													excelPara.setNodeid(excelPara.getNodeid() + 1);
													hongXunBomTree.setNodeID(excelPara.getNodeid());
													hongXunBomTreeList.add(hongXunBomTree);
													fatherId2 = String.valueOf(hongXunBomTree.getNodeID());
												}
												css = 1;
												// System.out.println("css: " +
												// css + " fatherId3:" +
												// fatherId3);
											} else {
												Map<String, Object> map = new HashMap<String, Object>();
												map.put("error",
														sheet.getSheetName() + " 一级料号:"
																+ topMaterialNum.getBomMaterialNum() + "下的 " + "五级物料号:"
																+ hongXunBomTree.getBomMaterialNum() + "位置号错误");
												listMsg.add(map);
												// workbook.close()();
												// fs.close()();
												return listMsg;
											}
										}
									}

									length = i;
									// System.out.println("i: " + i);
									break;
								} /*
									 * else{ Map<String,Object> map = new
									 * HashMap<String,Object>();
									 * map.put("error", sheet.getSheetName() +
									 * " 一级料号:" +
									 * topMaterialNum.getBomMaterialNum() +
									 * "下的 " + "五级物料号:" +
									 * hongXunBomTree.getBomMaterialNum() +
									 * "位置号错误"); listMsg.add(map); return
									 * listMsg; }
									 */
							}
						} else if (css == 5) {
							for (int i = 0; i < hongXunBomTree.getBomPosion().length(); i++) {
								if (hongXunBomTree.getBomPosion() == null) {
									Map<String, Object> map = new HashMap<String, Object>();
									map.put("error",
											sheet.getSheetName() + " 一级料号:" + topMaterialNum.getBomMaterialNum() + "下的 "
													+ "六级物料号:" + hongXunBomTree.getBomMaterialNum() + "位置号错误");
									listMsg.add(map);
									// workbook.close()();
									// fs.close()();
									return listMsg;
								}
								if (!hongXunBomTree.getBomPosion().subSequence(i, i + 1).equals("0")) {
									hongXunBomTree.setTopID(topID);

									if (length == i) {
										hongXunBomTree.setParentID(fatherId5);
										// stockDao.saveEntity(hongXunBomTree);
										// fatherId6 =
										// String.valueOf(hongXunBomTree.getIdc());
										// System.out.println("css: " + css);
										if (excelPara.isFlaga() == false) {
											iBomTreeDao.saveEntity(hongXunBomTree);
											fatherId6 = String.valueOf(hongXunBomTree.getIdc());
										} else {
											excelPara.setNodeid(excelPara.getNodeid() + 1);
											hongXunBomTree.setNodeID(excelPara.getNodeid());
											hongXunBomTreeList.add(hongXunBomTree);
											fatherId6 = String.valueOf(hongXunBomTree.getNodeID());
										}
									} else {
										if (i > length) {
											if (i - length == 1) {
												Map<String, Object> map = new HashMap<String, Object>();
												map.put("error",
														sheet.getSheetName() + " 一级料号:"
																+ topMaterialNum.getBomMaterialNum() + "下的 " + "六级物料号:"
																+ hongXunBomTree.getBomMaterialNum() + "位置号错误");
												listMsg.add(map);
												// workbook.close()();
												// fs.close()();
												return listMsg;
											}
											hongXunBomTree.setParentID(fatherId6);
											// stockDao.saveEntity(hongXunBomTree);
											// fatherId7 =
											// String.valueOf(hongXunBomTree.getIdc());
											if (excelPara.isFlaga() == false) {
												iBomTreeDao.saveEntity(hongXunBomTree);
												fatherId7 = String.valueOf(hongXunBomTree.getIdc());
											} else {
												excelPara.setNodeid(excelPara.getNodeid() + 1);
												hongXunBomTree.setNodeID(excelPara.getNodeid());
												hongXunBomTreeList.add(hongXunBomTree);
												fatherId7 = String.valueOf(hongXunBomTree.getNodeID());
											}
											css = 6;
											// System.out.println("css: " +
											// css);
										} else {
											if (length - i == 1) {
												hongXunBomTree.setParentID(fatherId4);
												// stockDao.saveEntity(hongXunBomTree);
												// fatherId5 =
												// String.valueOf(hongXunBomTree.getIdc());
												if (excelPara.isFlaga() == false) {
													iBomTreeDao.saveEntity(hongXunBomTree);
													fatherId5 = String.valueOf(hongXunBomTree.getIdc());
												} else {
													excelPara.setNodeid(excelPara.getNodeid() + 1);
													hongXunBomTree.setNodeID(excelPara.getNodeid());
													hongXunBomTreeList.add(hongXunBomTree);
													fatherId5 = String.valueOf(hongXunBomTree.getNodeID());
												}
												css = 4;
												// System.out.println("css: " +
												// css);
											} else if (length - i == 2) {
												hongXunBomTree.setParentID(fatherId3);
												// stockDao.saveEntity(hongXunBomTree);
												// fatherId4 =
												// String.valueOf(hongXunBomTree.getIdc());
												if (excelPara.isFlaga() == false) {
													iBomTreeDao.saveEntity(hongXunBomTree);
													fatherId4 = String.valueOf(hongXunBomTree.getIdc());
												} else {
													excelPara.setNodeid(excelPara.getNodeid() + 1);
													hongXunBomTree.setNodeID(excelPara.getNodeid());
													hongXunBomTreeList.add(hongXunBomTree);
													fatherId4 = String.valueOf(hongXunBomTree.getNodeID());
												}
												css = 3;
												// System.out.println("css: " +
												// css);
											} else if (length - i == 3) {
												hongXunBomTree.setParentID(fatherId2);
												// stockDao.saveEntity(hongXunBomTree);
												// fatherId3 =
												// String.valueOf(hongXunBomTree.getIdc());
												if (excelPara.isFlaga() == false) {
													iBomTreeDao.saveEntity(hongXunBomTree);
													fatherId3 = String.valueOf(hongXunBomTree.getIdc());
												} else {
													excelPara.setNodeid(excelPara.getNodeid() + 1);
													hongXunBomTree.setNodeID(excelPara.getNodeid());
													hongXunBomTreeList.add(hongXunBomTree);
													fatherId3 = String.valueOf(hongXunBomTree.getNodeID());
												}
												css = 2;
												// System.out.println("css: " +
												// css);
											} else if (length - i == 4) {
												hongXunBomTree.setParentID(fatherId1);
												// stockDao.saveEntity(hongXunBomTree);
												// fatherId2 =
												// String.valueOf(hongXunBomTree.getIdc());
												if (excelPara.isFlaga() == false) {
													iBomTreeDao.saveEntity(hongXunBomTree);
													fatherId2 = String.valueOf(hongXunBomTree.getIdc());
												} else {
													excelPara.setNodeid(excelPara.getNodeid() + 1);
													hongXunBomTree.setNodeID(excelPara.getNodeid());
													hongXunBomTreeList.add(hongXunBomTree);
													fatherId2 = String.valueOf(hongXunBomTree.getNodeID());
												}
												css = 1;
												// System.out.println("css: " +
												// css);
											} else {
												Map<String, Object> map = new HashMap<String, Object>();
												map.put("error",
														sheet.getSheetName() + " 一级料号:"
																+ topMaterialNum.getBomMaterialNum() + "下的 " + "六级物料号:"
																+ hongXunBomTree.getBomMaterialNum() + "位置号错误");
												listMsg.add(map);
												// workbook.close()();
												// fs.close()();
												return listMsg;
											}
										}
									}

									length = i;
									// System.out.println("i: " + i);
									break;
								} /*
									 * else{ Map<String,Object> map = new
									 * HashMap<String,Object>();
									 * map.put("error", sheet.getSheetName() +
									 * " 一级料号:" +
									 * topMaterialNum.getBomMaterialNum() +
									 * "下的 " + "六级物料号:" +
									 * hongXunBomTree.getBomMaterialNum() +
									 * "位置号错误"); listMsg.add(map); return
									 * listMsg; }
									 */
							}
						} else if (css == 6) {
							for (int i = 0; i < hongXunBomTree.getBomPosion().length(); i++) {
								if (hongXunBomTree.getBomPosion() == null) {
									Map<String, Object> map = new HashMap<String, Object>();
									map.put("error",
											sheet.getSheetName() + " 一级料号:" + topMaterialNum.getBomMaterialNum() + "下的 "
													+ "七级物料号:" + hongXunBomTree.getBomMaterialNum() + "位置号错误");
									listMsg.add(map);
									// workbook.close()();
									// fs.close()();
									return listMsg;
								}
								if (!hongXunBomTree.getBomPosion().subSequence(i, i + 1).equals("0")) {
									hongXunBomTree.setTopID(topID);

									if (length == i) {
										hongXunBomTree.setParentID(fatherId6);
										// stockDao.saveEntity(hongXunBomTree);
										// fatherId7 =
										// String.valueOf(hongXunBomTree.getIdc());
										if (excelPara.isFlaga() == false) {
											iBomTreeDao.saveEntity(hongXunBomTree);
											fatherId7 = String.valueOf(hongXunBomTree.getIdc());
										} else {
											excelPara.setNodeid(excelPara.getNodeid() + 1);
											hongXunBomTree.setNodeID(excelPara.getNodeid());
											hongXunBomTreeList.add(hongXunBomTree);
											fatherId7 = String.valueOf(hongXunBomTree.getNodeID());
										}
									} else {
										if (i > length) {
											if (i - length == 1) {
												Map<String, Object> map = new HashMap<String, Object>();
												map.put("error",
														sheet.getSheetName() + " 一级料号:"
																+ topMaterialNum.getBomMaterialNum() + "下的 " + "七级物料号:"
																+ hongXunBomTree.getBomMaterialNum() + "位置号错误");
												listMsg.add(map);
												// workbook.close()();
												// fs.close()();
												return listMsg;
											}
											hongXunBomTree.setParentID(fatherId7);
											// stockDao.saveEntity(hongXunBomTree);
											// fatherId8 =
											// String.valueOf(hongXunBomTree.getIdc());
											if (excelPara.isFlaga() == false) {
												iBomTreeDao.saveEntity(hongXunBomTree);
												fatherId8 = String.valueOf(hongXunBomTree.getIdc());
											} else {
												excelPara.setNodeid(excelPara.getNodeid() + 1);
												hongXunBomTree.setNodeID(excelPara.getNodeid());
												hongXunBomTreeList.add(hongXunBomTree);
												fatherId8 = String.valueOf(hongXunBomTree.getNodeID());
											}
											css = 7;
										} else {
											if (length - i == 1) {
												hongXunBomTree.setParentID(fatherId5);
												// stockDao.saveEntity(hongXunBomTree);
												// fatherId6 =
												// String.valueOf(hongXunBomTree.getIdc());
												if (excelPara.isFlaga() == false) {
													iBomTreeDao.saveEntity(hongXunBomTree);
													fatherId6 = String.valueOf(hongXunBomTree.getIdc());
												} else {
													hongXunBomTree.setNodeID(excelPara.getNodeid());
													hongXunBomTreeList.add(hongXunBomTree);
													fatherId6 = String.valueOf(hongXunBomTree.getNodeID());
												}
												css = 5;
											} else if (length - i == 2) {
												hongXunBomTree.setParentID(fatherId4);
												// stockDao.saveEntity(hongXunBomTree);
												// fatherId5 =
												// String.valueOf(hongXunBomTree.getIdc());
												if (excelPara.isFlaga() == false) {
													iBomTreeDao.saveEntity(hongXunBomTree);
													fatherId5 = String.valueOf(hongXunBomTree.getIdc());
												} else {
													excelPara.setNodeid(excelPara.getNodeid() + 1);
													hongXunBomTree.setNodeID(excelPara.getNodeid());
													hongXunBomTreeList.add(hongXunBomTree);
													fatherId5 = String.valueOf(hongXunBomTree.getNodeID());
												}
												css = 4;
											} else if (length - i == 3) {
												hongXunBomTree.setParentID(fatherId3);
												// iBomTreeDao.saveEntity(hongXunBomTree);
												// fatherId4 =
												// String.valueOf(hongXunBomTree.getIdc());
												if (excelPara.isFlaga() == false) {
													iBomTreeDao.saveEntity(hongXunBomTree);
													fatherId4 = String.valueOf(hongXunBomTree.getIdc());
												} else {
													excelPara.setNodeid(excelPara.getNodeid() + 1);
													hongXunBomTree.setNodeID(excelPara.getNodeid());
													hongXunBomTreeList.add(hongXunBomTree);
													fatherId4 = String.valueOf(hongXunBomTree.getNodeID());
												}
												css = 3;
											} else if (length - i == 4) {
												hongXunBomTree.setParentID(fatherId2);
												// iBomTreeDao.saveEntity(hongXunBomTree);
												// fatherId3 =
												// String.valueOf(hongXunBomTree.getIdc());
												if (excelPara.isFlaga() == false) {
													iBomTreeDao.saveEntity(hongXunBomTree);
													fatherId3 = String.valueOf(hongXunBomTree.getIdc());
												} else {
													excelPara.setNodeid(excelPara.getNodeid() + 1);
													hongXunBomTree.setNodeID(excelPara.getNodeid());
													hongXunBomTreeList.add(hongXunBomTree);
													fatherId3 = String.valueOf(hongXunBomTree.getNodeID());
												}
												css = 2;
											} else if (length - i == 5) {
												hongXunBomTree.setParentID(fatherId1);
												// iBomTreeDao.saveEntity(hongXunBomTree);
												// fatherId2 =
												// String.valueOf(hongXunBomTree.getIdc());
												if (excelPara.isFlaga() == false) {
													iBomTreeDao.saveEntity(hongXunBomTree);
													fatherId2 = String.valueOf(hongXunBomTree.getIdc());
												} else {
													excelPara.setNodeid(excelPara.getNodeid() + 1);
													hongXunBomTree.setNodeID(excelPara.getNodeid());
													hongXunBomTreeList.add(hongXunBomTree);
													fatherId2 = String.valueOf(hongXunBomTree.getNodeID());
												}
												css = 1;
											} else {
												Map<String, Object> map = new HashMap<String, Object>();
												map.put("error",
														sheet.getSheetName() + " 一级料号:"
																+ topMaterialNum.getBomMaterialNum() + "下的 " + "七级物料号:"
																+ hongXunBomTree.getBomMaterialNum() + "位置号错误");
												listMsg.add(map);
												// workbook.close()();
												// fs.close()();
												return listMsg;
											}
										}
									}

									length = i;
									// System.out.println("i: " + i);
									break;
								} /*
									 * else{ Map<String,Object> map = new
									 * HashMap<String,Object>();
									 * map.put("error", sheet.getSheetName() +
									 * " 一级料号:" +
									 * topMaterialNum.getBomMaterialNum() +
									 * "下的 " + "七级物料号:" +
									 * hongXunBomTree.getBomMaterialNum() +
									 * "位置号错误"); listMsg.add(map); return
									 * listMsg; }
									 */
							}
						} else if (css == 7) {
							for (int i = 0; i < hongXunBomTree.getBomPosion().length(); i++) {
								if (hongXunBomTree.getBomPosion() == null) {
									Map<String, Object> map = new HashMap<String, Object>();
									map.put("error",
											sheet.getSheetName() + " 一级料号:" + topMaterialNum.getBomMaterialNum() + "下的 "
													+ "八级物料号:" + hongXunBomTree.getBomMaterialNum() + "位置号错误");
									listMsg.add(map);
									// workbook.close()();
									// fs.close()();
									return listMsg;
								}
								if (!hongXunBomTree.getBomPosion().subSequence(i, i + 1).equals("0")) {
									hongXunBomTree.setTopID(topID);

									if (length == i) {
										hongXunBomTree.setParentID(fatherId7);
										iBomTreeDao.saveEntity(hongXunBomTree);
										fatherId8 = String.valueOf(hongXunBomTree.getIdc());
									} else {
										if (i > length) {
											if (i - length == 1) {
												Map<String, Object> map = new HashMap<String, Object>();
												map.put("error",
														sheet.getSheetName() + " 一级料号:"
																+ topMaterialNum.getBomMaterialNum() + "下的 " + "八级物料号:"
																+ hongXunBomTree.getBomMaterialNum() + "位置号错误");
												listMsg.add(map);
												// workbook.close()();
												// fs.close()();
												return listMsg;
											}
											hongXunBomTree.setParentID(fatherId8);
											// iBomTreeDao.saveEntity(hongXunBomTree);
											if (excelPara.isFlaga() == false) {
												iBomTreeDao.saveEntity(hongXunBomTree);
											} else {
												excelPara.setNodeid(excelPara.getNodeid() + 1);
												hongXunBomTree.setNodeID(excelPara.getNodeid());
												hongXunBomTreeList.add(hongXunBomTree);
											}
											// fatherId9 =
											// String.valueOf(hongXunBomTree.getIdc());
											css = 8;
										} else {
											if (length - i == 1) {
												hongXunBomTree.setParentID(fatherId6);
												// iBomTreeDao.saveEntity(hongXunBomTree);
												// fatherId7 =
												// String.valueOf(hongXunBomTree.getIdc());
												if (excelPara.isFlaga() == false) {
													iBomTreeDao.saveEntity(hongXunBomTree);
													fatherId7 = String.valueOf(hongXunBomTree.getIdc());
												} else {
													excelPara.setNodeid(excelPara.getNodeid() + 1);
													hongXunBomTree.setNodeID(excelPara.getNodeid());
													hongXunBomTreeList.add(hongXunBomTree);
													fatherId7 = String.valueOf(hongXunBomTree.getNodeID());
												}
												css = 6;
											} else if (length - i == 2) {
												hongXunBomTree.setParentID(fatherId5);
												// iBomTreeDao.saveEntity(hongXunBomTree);
												// fatherId6 =
												// String.valueOf(hongXunBomTree.getIdc());
												if (excelPara.isFlaga() == false) {
													iBomTreeDao.saveEntity(hongXunBomTree);
													fatherId6 = String.valueOf(hongXunBomTree.getIdc());
												} else {
													excelPara.setNodeid(excelPara.getNodeid() + 1);
													hongXunBomTree.setNodeID(excelPara.getNodeid());
													hongXunBomTreeList.add(hongXunBomTree);
													fatherId6 = String.valueOf(hongXunBomTree.getNodeID());
												}
												css = 5;
											} else if (length - i == 3) {
												hongXunBomTree.setParentID(fatherId4);
												// iBomTreeDao.saveEntity(hongXunBomTree);
												// fatherId5 =
												// String.valueOf(hongXunBomTree.getIdc());
												if (excelPara.isFlaga() == false) {
													iBomTreeDao.saveEntity(hongXunBomTree);
													fatherId5 = String.valueOf(hongXunBomTree.getIdc());
												} else {
													excelPara.setNodeid(excelPara.getNodeid() + 1);
													hongXunBomTree.setNodeID(excelPara.getNodeid());
													hongXunBomTreeList.add(hongXunBomTree);
													fatherId5 = String.valueOf(hongXunBomTree.getNodeID());
												}
												css = 4;
											} else if (length - i == 4) {
												hongXunBomTree.setParentID(fatherId3);
												// iBomTreeDao.saveEntity(hongXunBomTree);
												// fatherId4 =
												// String.valueOf(hongXunBomTree.getIdc());
												if (excelPara.isFlaga() == false) {
													iBomTreeDao.saveEntity(hongXunBomTree);
													fatherId4 = String.valueOf(hongXunBomTree.getIdc());
												} else {
													excelPara.setNodeid(excelPara.getNodeid() + 1);
													hongXunBomTree.setNodeID(excelPara.getNodeid());
													hongXunBomTreeList.add(hongXunBomTree);
													fatherId4 = String.valueOf(hongXunBomTree.getNodeID());
												}
												css = 3;
											} else if (length - i == 5) {
												hongXunBomTree.setParentID(fatherId2);
												// iBomTreeDao.saveEntity(hongXunBomTree);
												// fatherId3 =
												// String.valueOf(hongXunBomTree.getIdc());
												if (excelPara.isFlaga() == false) {
													iBomTreeDao.saveEntity(hongXunBomTree);
													fatherId3 = String.valueOf(hongXunBomTree.getIdc());
												} else {
													excelPara.setNodeid(excelPara.getNodeid() + 1);
													hongXunBomTree.setNodeID(excelPara.getNodeid());
													hongXunBomTreeList.add(hongXunBomTree);
													fatherId3 = String.valueOf(hongXunBomTree.getNodeID());
												}
												css = 2;
											} else if (length - i == 6) {
												hongXunBomTree.setParentID(fatherId1);
												// iBomTreeDao.saveEntity(hongXunBomTree);
												// fatherId2 =
												// String.valueOf(hongXunBomTree.getIdc());
												if (excelPara.isFlaga() == false) {
													iBomTreeDao.saveEntity(hongXunBomTree);
													fatherId2 = String.valueOf(hongXunBomTree.getIdc());
												} else {
													excelPara.setNodeid(excelPara.getNodeid() + 1);
													hongXunBomTree.setNodeID(excelPara.getNodeid());
													hongXunBomTreeList.add(hongXunBomTree);
													fatherId2 = String.valueOf(hongXunBomTree.getNodeID());
												}
												css = 1;
											} else {
												Map<String, Object> map = new HashMap<String, Object>();
												map.put("error",
														sheet.getSheetName() + " 一级料号:"
																+ topMaterialNum.getBomMaterialNum() + "下的 " + "八级物料号:"
																+ hongXunBomTree.getBomMaterialNum() + "位置号错误");
												listMsg.add(map);
												// workbook.close()();
												// fs.close()();
												return listMsg;
											}
										}
									}

									length = i;
									// System.out.println("i: " + i);
									break;
								} /*
									 * else{ Map<String,Object> map = new
									 * HashMap<String,Object>();
									 * map.put("error", sheet.getSheetName() +
									 * " 一级料号:" +
									 * topMaterialNum.getBomMaterialNum() +
									 * "下的 " + "八级物料号:" +
									 * hongXunBomTree.getBomMaterialNum() +
									 * "位置号错误"); listMsg.add(map); return
									 * listMsg; }
									 */
							}
						} else {
							System.out.println("css8");
						}
					}
					if (k == 0) {
						k = excelPara.getBomLength();
					}
				}

			}
			// workbook.close()();
			// fs.close()();
			/*
			 * HongXunBomTree hongXunBomTree = new HongXunBomTree();
			 * List<HongXunBomTree> hongXunBomTrees =
			 * stockDao.quary(hongXunBomTree); for(HongXunBomTree item:
			 * hongXunBomTrees){
			 * 
			 * }
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (excelPara.isFlaga() == true) {
			iBomTreeDao.saveAll(hongXunBomTreeList);
			/*Session session = null;
			int i = 0;
			try {
				if (session == null) {
					session = stockDao.session();
					session.beginTransaction(); // 开启事物
				}
				for (HongXunBomTree hongXunBomTree : hongXunBomTreeList) {
					if (hongXunBomTree.getBomQuantity() == null) {
						hongXunBomTree.setBomQuantity(0);
					}
					session.save(hongXunBomTree);
					i++;
					// System.out.println(i);
					if (i % 1000 == 0) { // 每一千条刷新并写入数据库
						session.flush();
						session.clear();
					}
				}
				session.getTransaction().commit(); // 提交事物
			} catch (Exception e) {
				e.printStackTrace(); // 打印错误信息
				session.getTransaction().rollback(); // 出错将回滚事物
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("success", "导入失败");
				listMsg.add(map);
				return listMsg;
			} finally {
				// session.flush();//清理缓存，执行批量更新20条记录的SQL update语句
				// session.clear();
				session.close();
			}*/
		}
		// System.out.println("123");
		int bomQuantity = excelPara.getNodeid() - 5;// stockDao.quary(new
													// HongXunBomTree()).size()-6;

		if (excelPara.getSumRows() == bomQuantity) {
			// System.out.println("789");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("success", "导入成功");
			listMsg.add(map);
			return listMsg;
		} else {
			// System.out.println("1566");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("success", "excel总数:" + excelPara.getSumRows() + ", bom导入数量:" + bomQuantity);
			listMsg.add(map);
			return listMsg;
		}

	}

	/*
	 * private boolean isRowEmpty(Row row){ for (int i = row.getFirstCellNum();
	 * i < row.getLastCellNum(); i++) { Cell cell = row.getCell(i); if (cell !=
	 * null && cell.getCellType() != Cell.CELL_TYPE_BLANK){ return false; } }
	 * return true; }
	 */

	@SuppressWarnings({ "unchecked", "resource" })
	@Override
	public List<Map<String, Object>> excelImport(InputStream is, String rootID) {
		// TODO Auto-generated method stub
		// System.out.println("parentID:
		// "+stockDao.hongXunBomTreeFindById(Integer.valueOf(rootID)).getParentID());
		// boolean result = false;
		List<Map<String, Object>> listMsg = new ArrayList<Map<String, Object>>();
		int sumRows = 0;
		List<HongXunBomTree> hongXunBomTreeList = new ArrayList<HongXunBomTree>();
		boolean flaga = true;
		int nodeid = 0;
		try {
			// Workbook workbook = HSSFWorkbookFactory.create(is);
			Workbook workbook = WorkbookFactory.create(is);
			// evaluator=workbook.getCreationHelper().createFormulaEvaluator();
			int sheets = workbook.getNumberOfSheets();
			if (sheets > 5) {
				sheets = 5;
			}
			List<String> listMaterialNum = new ArrayList<String>();

			for (int j = 0; j < sheets; j++) {
				int nodeID = 0;
				boolean flag = false;
				int bomLength = 0;
				Sheet sheet = workbook.getSheetAt(j);
				if (iBomTreeDao.get(Integer.valueOf(rootID)).getParentID().equals("99999")) {
					List<HongXunBomTree> childrens = getChildren(Integer.valueOf(rootID)); // 0
					for (HongXunBomTree item : childrens) {
						if (item.getBomMaterialNum().equals(sheet.getSheetName())) {
							flag = true;
							if (flaga == false) {
								nodeID = item.getIdc();
							} else {
								nodeID = item.getNodeID();
							}
							// System.out.println("nodeID: " + nodeID);
							List<HongXunBomTree> hongXunBomTrees = (List<HongXunBomTree>) stockDao
									.quarywithpara("HongXunBomTree", "parentID", String.valueOf(nodeID));
							bomLength = hongXunBomTrees.size();
							// System.out.println("bomLength:" + bomLength);
							HongXunBomTree tempHongXunBomTree = new HongXunBomTree();
							for (HongXunBomTree hongXunBomTree : hongXunBomTrees) {
								if (!listMaterialNum.contains(hongXunBomTree.getBomMaterialNum())) {
									listMaterialNum.add(hongXunBomTree.getBomMaterialNum());
								} else {
									// System.out.println("一级物料号:" +
									// hongXunBomTree.getBomMaterialNum() +
									// "有重复");
									Map<String, Object> map = new HashMap<String, Object>();
									map.put("error", "一级物料号:" + hongXunBomTree.getBomMaterialNum() + "有重复");
									listMsg.add(map);
									return listMsg;
								}
								if (flaga == false) {
									tempHongXunBomTree.setTopID(hongXunBomTree.getIdc());
								} else {
									tempHongXunBomTree.setTopID(hongXunBomTree.getNodeID());
								}

								hongXunBomTrees = iBomTreeDao.quary(tempHongXunBomTree);
								if (hongXunBomTrees != null) {
									bomLength = bomLength + hongXunBomTrees.size();
								}
							}
							// List<HongXunBomTree> nodes = getChildren(nodeID);
							// List<HongXunBomTree> hongXunBomTrees = new
							// ArrayList<HongXunBomTree>();
							// hongXunBomTrees =
							// getAllChildrens(String.valueOf(nodeID),hongXunBomTrees);
							// System.out.println("getAllChildrens" +
							// hongXunBomTrees.size());
							/*
							 * int topID = nodes.get(nodes.size()-1).getIdc();
							 * 
							 * @SuppressWarnings("unchecked")
							 * List<HongXunBomTree> hongXunBomTrees =
							 * (List<HongXunBomTree>)stockDao.quarywithpara(
							 * "HongXunBomTree", "topID", topID);
							 * if(hongXunBomTrees.size() >0){ lastMaterialNum =
							 * hongXunBomTrees.get(hongXunBomTrees.size()-1).
							 * getBomMaterialNum();
							 * 
							 * System.out.println("materialNum: " +
							 * lastMaterialNum); }else{ lastMaterialNum =
							 * nodes.get(nodes.size()-1).getBomMaterialNum(); }
							 * break;
							 */
						}
					}
					System.out.println("bomLength:  " + bomLength);
					if (flag == false) {
						HongXunBomTree node = new HongXunBomTree();
						node.setBomMaterialNum(sheet.getSheetName());
						// 0
						if (flaga == false) {
							node.setParentID(rootID);
							iBomTreeDao.saveEntity(node);
							nodeID = node.getIdc();
						} else {
							node.setParentID("0");
							node.setNodeID(++nodeid);
							nodeID = node.getNodeID();
							hongXunBomTreeList.add(node);
						}

					}
				} else {
					j = sheets;
					nodeID = Integer.valueOf(rootID);
				}
				List<String> list = new ArrayList<String>();
				int end = sheet.getRow(0).getLastCellNum();
				int count = 0;
				int topID = 0;
				int length = 0;
				int css = 0;
				String fatherId1 = "";
				String fatherId2 = "";
				String fatherId3 = "";
				String fatherId8 = "";
				String fatherId4 = "";
				String fatherId5 = "";
				String fatherId6 = "";
				String fatherId7 = "";
				// int sf=0;
				int rowNum = sheet.getLastRowNum();
				// System.out.println(rowNum);
				// sumRows = sumRows + rowNum;
				// System.out.println(sumRows);
				for (int k = 0; k <= rowNum; k++) {
					// System.out.println("k: " + k);

					// System.out.println(sf++);

					// if (sheet.getRow(k).getCell(0)==null ||
					// sheet.getRow(k).getCell(0).toString().equals("")) {
					if (sheet.getRow(k).getFirstCellNum() == sheet.getRow(k).getLastCellNum()) {
						// sumRows = sumRows -1;
						sumRows = sumRows + k - 1;
						// System.out.println("1->" + sumRows);
						break;
					} else if (k == rowNum) {
						sumRows = sumRows + k;
						// System.out.println("2->" + sumRows);
					}

					HongXunBomTree hongXunBomTree = new HongXunBomTree();
					for (int i = 0; i < end; i++) {
						Cell cell = sheet.getRow(k).getCell(i);
						String obj = null;
						obj = getValue(cell);
						if (obj != null) {
							if (count < 1) {
								list.add(obj);
							} else {
								String temStr = list.get(i);
								if (temStr.equals("序号")) {
									hongXunBomTree.setSn(Double.valueOf(obj).intValue());
									if (hongXunBomTree.getSn() == 1) {
										hongXunBomTree.setBomGroup(sheet.getSheetName());
									}
								} else if (temStr.equals("数量")) {
									// hongXunBomTree.setSe(Double.valueOf(obj).intValue());
									hongXunBomTree.setBomQuantity(Integer.valueOf(obj));
								} else if (temStr.equals("技术规格")) {
									hongXunBomTree.setBomSpacification(obj);
								} else if (temStr.equals("长度")) {
									hongXunBomTree.setBomLength(obj);
								} else if (temStr.equals("图号")) {
									hongXunBomTree.setBomDrawingNum(obj);
								} else if (temStr.equals("位置")) {
									hongXunBomTree.setBomPosion(obj);
								} else if (temStr.equals("材料")) {
									hongXunBomTree.setBomMaterial(obj);
								} else if (temStr.equals("部件号")) {
									hongXunBomTree.setBomMaterialNum(obj);

								} else if (temStr.equals("属性")) {
									hongXunBomTree.setAttribute(obj);
								} else if (temStr.equals("库位编码")) {
									hongXunBomTree.setBomLocation(obj);
								} else if (temStr.equals("备注")) {
									hongXunBomTree.setRemark(obj);
								} else if (temStr.equals("下料")) {
									hongXunBomTree.setBomMaterialCuting("下料");
								} else if (temStr.equals("钣金")) {
									hongXunBomTree.setBomPlateCuting("钣金");
								} else if (temStr.equals("焊接")) {
									if (obj.equals("B.1") || obj.equals("B1")) {
										hongXunBomTree.setBomMaterialWeld("焊接.1");
									} else {
										hongXunBomTree.setBomMaterialWeld("焊接");
									}
								} else if (temStr.equals("外协")) {
									hongXunBomTree.setBomWeiwai(obj);
								} else if (temStr.equals("喷涂")) {
									hongXunBomTree.setBomPainting(obj);
								} else if (temStr.equals("装配")) {
									hongXunBomTree.setBomAssemble("装配");
								}
							}
						}
					}
					if (count < 1) {
						count++;
					} else {
						if (hongXunBomTree.getSn() == null) {
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("error", "一级物料号:" + hongXunBomTree.getBomMaterialNum() + "序号为空");
							listMsg.add(map);
							return listMsg;
						}
						if (hongXunBomTree.getSn() == 1) {
							// System.out.println("getBomMaterialNum: " +
							// hongXunBomTree.getBomMaterialNum());
							fatherId1 = "";
							fatherId2 = "";
							fatherId3 = "";
							fatherId4 = "";
							fatherId5 = "";
							fatherId6 = "";
							fatherId7 = "";
							if (!listMaterialNum.contains(hongXunBomTree.getBomMaterialNum())) {
								listMaterialNum.add(hongXunBomTree.getBomMaterialNum());
							} else {
								Map<String, Object> map = new HashMap<String, Object>();
								map.put("error", "一级物料号:" + hongXunBomTree.getBomMaterialNum() + "有重复");
								listMsg.add(map);
								return listMsg;
							}

							hongXunBomTree.setParentID(String.valueOf(nodeID));
							if (flaga == false) {
								iBomTreeDao.saveEntity(hongXunBomTree);
								fatherId1 = String.valueOf(hongXunBomTree.getIdc());
								// System.out.println("fatherId1: " +
								// fatherId1);
								topID = hongXunBomTree.getIdc();
							} else {
								hongXunBomTree.setNodeID(++nodeid);
								hongXunBomTreeList.add(hongXunBomTree);
								fatherId1 = String.valueOf(hongXunBomTree.getNodeID());
								// System.out.println("fatherId1: " +
								// fatherId1);
								topID = hongXunBomTree.getNodeID();
							}

							length = -1;
							css = 1;

							// System.out.println("css: " + css);
						} else if (css == 1) {
							for (int i = 0; i < hongXunBomTree.getBomPosion().length(); i++) {
								if (!hongXunBomTree.getBomPosion().subSequence(i, i + 1).equals("0")) {
									hongXunBomTree.setTopID(topID);
									if (length == -1) {
										// System.out.println("-1 fatherId1: " +
										// fatherId1);
										hongXunBomTree.setParentID(fatherId1);
										if (flaga == false) {
											iBomTreeDao.saveEntity(hongXunBomTree);
											fatherId2 = String.valueOf(hongXunBomTree.getIdc());
										} else {
											hongXunBomTree.setNodeID(++nodeid);
											hongXunBomTreeList.add(hongXunBomTree);
											fatherId2 = String.valueOf(hongXunBomTree.getNodeID());
										}
										// System.out.println("css: " + css);
									} else {
										if (length == i) {
											hongXunBomTree.setParentID(fatherId1);
											// System.out.println("css: " + css
											// + " fatherId1:" + fatherId1);
											if (flaga == false) {
												iBomTreeDao.saveEntity(hongXunBomTree);
												fatherId2 = String.valueOf(hongXunBomTree.getIdc());
											} else {
												hongXunBomTree.setNodeID(++nodeid);
												hongXunBomTreeList.add(hongXunBomTree);
												fatherId2 = String.valueOf(hongXunBomTree.getNodeID());
											}
										} else {
											if (i > length) {
												hongXunBomTree.setParentID(fatherId2);

												if (flaga == false) {
													iBomTreeDao.saveEntity(hongXunBomTree);
													fatherId3 = String.valueOf(hongXunBomTree.getIdc());
												} else {
													hongXunBomTree.setNodeID(++nodeid);
													hongXunBomTreeList.add(hongXunBomTree);
													fatherId3 = String.valueOf(hongXunBomTree.getNodeID());
												}
												css = 2;
												// System.out.println("css: " +
												// css + " fatherId2:" +
												// fatherId2);
											} else {
												Map<String, Object> map = new HashMap<String, Object>();
												map.put("error",
														"二级物料号:" + hongXunBomTree.getBomMaterialNum() + "位置号错误");
												listMsg.add(map);
												return listMsg;
											}
										}
									}
									length = i;
									// System.out.println("i: " + i);
									break;
								}
							}
						} else if (css == 2) {
							for (int i = 0; i < hongXunBomTree.getBomPosion().length(); i++) {
								if (hongXunBomTree.getBomPosion() == null) {
									Map<String, Object> map = new HashMap<String, Object>();
									map.put("error", "三级物料号:" + hongXunBomTree.getBomMaterialNum() + "位置号错误");
									listMsg.add(map);
									return listMsg;
								}
								if (!hongXunBomTree.getBomPosion().subSequence(i, i + 1).equals("0")) {
									hongXunBomTree.setTopID(topID);
									if (length == i) {
										hongXunBomTree.setParentID(fatherId2);
										// System.out.println("css: " + css + "
										// fatherId2:" + fatherId2);
										if (flaga == false) {
											iBomTreeDao.saveEntity(hongXunBomTree);
											fatherId3 = String.valueOf(hongXunBomTree.getIdc());
										} else {
											hongXunBomTree.setNodeID(++nodeid);
											hongXunBomTreeList.add(hongXunBomTree);
											fatherId3 = String.valueOf(hongXunBomTree.getNodeID());
										}
									} else {
										if (i > length) {
											if (i - length > 1) {
												Map<String, Object> map = new HashMap<String, Object>();
												map.put("error",
														"三级物料号:" + hongXunBomTree.getBomMaterialNum() + "位置号错误");
												listMsg.add(map);
												return listMsg;
											}
											hongXunBomTree.setParentID(fatherId3);

											if (flaga == false) {
												iBomTreeDao.saveEntity(hongXunBomTree);
												fatherId4 = String.valueOf(hongXunBomTree.getIdc());
											} else {
												hongXunBomTree.setNodeID(++nodeid);
												hongXunBomTreeList.add(hongXunBomTree);
												fatherId4 = String.valueOf(hongXunBomTree.getNodeID());
											}
											css = 3;
											// System.out.println("css: " + css
											// + " fatherId3:" + fatherId3);
										} else {
											if (length - i == 1) {
												hongXunBomTree.setParentID(fatherId1);

												if (flaga == false) {
													iBomTreeDao.saveEntity(hongXunBomTree);
													fatherId2 = String.valueOf(hongXunBomTree.getIdc());
												} else {
													hongXunBomTree.setNodeID(++nodeid);
													hongXunBomTreeList.add(hongXunBomTree);
													fatherId2 = String.valueOf(hongXunBomTree.getNodeID());
												}
												css = 1;
											} else {
												Map<String, Object> map = new HashMap<String, Object>();
												map.put("error",
														"三级物料号:" + hongXunBomTree.getBomMaterialNum() + "位置号错误");
												listMsg.add(map);
												return listMsg;
											}
											// System.out.println("css: " + css
											// + " fatherId1:" + fatherId1);
										}
									}

									length = i;
									// System.out.println("i: " + i);
									break;
								} /*
									 * else{ Map<String,Object> map = new
									 * HashMap<String,Object>();
									 * map.put("error", "三级物料号:" +
									 * hongXunBomTree.getBomMaterialNum() +
									 * "位置号错误"); listMsg.add(map); return
									 * listMsg; }
									 */
							}
						} else if (css == 3) {
							for (int i = 0; i < hongXunBomTree.getBomPosion().length(); i++) {
								if (hongXunBomTree.getBomPosion() == null) {
									Map<String, Object> map = new HashMap<String, Object>();
									map.put("error", "四级物料号:" + hongXunBomTree.getBomMaterialNum() + "位置号错误");
									listMsg.add(map);
									return listMsg;
								}
								if (!hongXunBomTree.getBomPosion().subSequence(i, i + 1).equals("0")) {
									hongXunBomTree.setTopID(topID);

									if (length == i) {
										hongXunBomTree.setParentID(fatherId3);
										// iBomTreeDao.saveEntity(hongXunBomTree);
										// fatherId4 =
										// String.valueOf(hongXunBomTree.getIdc());
										if (flaga == false) {
											iBomTreeDao.saveEntity(hongXunBomTree);
											fatherId4 = String.valueOf(hongXunBomTree.getIdc());
										} else {
											hongXunBomTree.setNodeID(++nodeid);
											hongXunBomTreeList.add(hongXunBomTree);
											fatherId4 = String.valueOf(hongXunBomTree.getNodeID());
										}
										// System.out.println("css: " + css + "
										// fatherId3:" + fatherId3);
									} else {
										if (i > length) {
											if (i - length > 1) {
												Map<String, Object> map = new HashMap<String, Object>();
												map.put("error",
														"四级物料号:" + hongXunBomTree.getBomMaterialNum() + "位置号错误");
												listMsg.add(map);
												return listMsg;
											}
											hongXunBomTree.setParentID(fatherId4);
											// iBomTreeDao.saveEntity(hongXunBomTree);
											// fatherId5 =
											// String.valueOf(hongXunBomTree.getIdc());
											if (flaga == false) {
												iBomTreeDao.saveEntity(hongXunBomTree);
												fatherId5 = String.valueOf(hongXunBomTree.getIdc());
											} else {
												hongXunBomTree.setNodeID(++nodeid);
												hongXunBomTreeList.add(hongXunBomTree);
												fatherId5 = String.valueOf(hongXunBomTree.getNodeID());
											}
											css = 4;
											// System.out.println("css: " + css
											// + " fatherId4:" + fatherId4);
										} else {
											if (length - i == 1) {
												hongXunBomTree.setParentID(fatherId2);
												// iBomTreeDao.saveEntity(hongXunBomTree);
												// fatherId3 =
												// String.valueOf(hongXunBomTree.getIdc());
												if (flaga == false) {
													iBomTreeDao.saveEntity(hongXunBomTree);
													fatherId3 = String.valueOf(hongXunBomTree.getIdc());
												} else {
													hongXunBomTree.setNodeID(++nodeid);
													hongXunBomTreeList.add(hongXunBomTree);
													fatherId3 = String.valueOf(hongXunBomTree.getNodeID());
												}
												css = 2;
												// System.out.println("css: " +
												// css + " fatherId1:" +
												// fatherId2);
											} else if (length - i == 2) {
												hongXunBomTree.setParentID(fatherId1);
												// iBomTreeDao.saveEntity(hongXunBomTree);
												// fatherId2 =
												// String.valueOf(hongXunBomTree.getIdc());
												if (flaga == false) {
													iBomTreeDao.saveEntity(hongXunBomTree);
													fatherId2 = String.valueOf(hongXunBomTree.getIdc());
												} else {
													hongXunBomTree.setNodeID(++nodeid);
													hongXunBomTreeList.add(hongXunBomTree);
													fatherId2 = String.valueOf(hongXunBomTree.getNodeID());
												}
												css = 1;
												// System.out.println("css: " +
												// css + " fatherId2:" +
												// fatherId1);
											} else {
												Map<String, Object> map = new HashMap<String, Object>();
												map.put("error",
														"四级物料号:" + hongXunBomTree.getBomMaterialNum() + "位置号错误");
												listMsg.add(map);
												return listMsg;
											}
										}
									}

									length = i;
									// System.out.println("i: " + i);
									break;
								} /*
									 * else{ Map<String,Object> map = new
									 * HashMap<String,Object>();
									 * map.put("error", "四级物料号:" +
									 * hongXunBomTree.getBomMaterialNum() +
									 * "位置号错误"); listMsg.add(map); return
									 * listMsg; }
									 */
							}
						} else if (css == 4) {
							for (int i = 0; i < hongXunBomTree.getBomPosion().length(); i++) {
								if (hongXunBomTree.getBomPosion() == null) {
									Map<String, Object> map = new HashMap<String, Object>();
									map.put("error", "五级物料号:" + hongXunBomTree.getBomMaterialNum() + "位置号错误");
									listMsg.add(map);
									return listMsg;
								}
								if (!hongXunBomTree.getBomPosion().subSequence(i, i + 1).equals("0")) {
									hongXunBomTree.setTopID(topID);

									if (length == i) {
										hongXunBomTree.setParentID(fatherId4);
										// iBomTreeDao.saveEntity(hongXunBomTree);
										// fatherId5 =
										// String.valueOf(hongXunBomTree.getIdc());
										if (flaga == false) {
											iBomTreeDao.saveEntity(hongXunBomTree);
											fatherId5 = String.valueOf(hongXunBomTree.getIdc());
										} else {
											hongXunBomTree.setNodeID(++nodeid);
											hongXunBomTreeList.add(hongXunBomTree);
											fatherId5 = String.valueOf(hongXunBomTree.getNodeID());
										}
										// System.out.println("css: " + css + "
										// fatherId4:" + fatherId4);
									} else {
										if (i > length) {
											if (i - length > 1) {
												Map<String, Object> map = new HashMap<String, Object>();
												map.put("error",
														"五级物料号:" + hongXunBomTree.getBomMaterialNum() + "位置号错误");
												listMsg.add(map);
												return listMsg;
											}
											hongXunBomTree.setParentID(fatherId5);
											// iBomTreeDao.saveEntity(hongXunBomTree);
											// fatherId6 =
											// String.valueOf(hongXunBomTree.getIdc());
											if (flaga == false) {
												iBomTreeDao.saveEntity(hongXunBomTree);
												fatherId6 = String.valueOf(hongXunBomTree.getIdc());
											} else {
												hongXunBomTree.setNodeID(++nodeid);
												hongXunBomTreeList.add(hongXunBomTree);
												fatherId6 = String.valueOf(hongXunBomTree.getNodeID());
											}
											css = 5;
											// System.out.println("css: " + css
											// + " fatherId5:" + fatherId5);
										} else {
											if (length - i == 1) {
												hongXunBomTree.setParentID(fatherId3);
												// iBomTreeDao.saveEntity(hongXunBomTree);
												// fatherId4 =
												// String.valueOf(hongXunBomTree.getIdc());
												if (flaga == false) {
													iBomTreeDao.saveEntity(hongXunBomTree);
													fatherId4 = String.valueOf(hongXunBomTree.getIdc());
												} else {
													hongXunBomTree.setNodeID(++nodeid);
													hongXunBomTreeList.add(hongXunBomTree);
													fatherId4 = String.valueOf(hongXunBomTree.getNodeID());
												}
												css = 3;
												// System.out.println("css: " +
												// css + " fatherId1:" +
												// fatherId1);
											} else if (length - i == 2) {
												hongXunBomTree.setParentID(fatherId2);
												// iBomTreeDao.saveEntity(hongXunBomTree);
												// fatherId3 =
												// String.valueOf(hongXunBomTree.getIdc());
												if (flaga == false) {
													iBomTreeDao.saveEntity(hongXunBomTree);
													fatherId3 = String.valueOf(hongXunBomTree.getIdc());
												} else {
													hongXunBomTree.setNodeID(++nodeid);
													hongXunBomTreeList.add(hongXunBomTree);
													fatherId3 = String.valueOf(hongXunBomTree.getNodeID());
												}
												css = 2;
												// System.out.println("css: " +
												// css + " fatherId2:" +
												// fatherId2);
											} else if (length - i == 3) {
												hongXunBomTree.setParentID(fatherId1);
												// iBomTreeDao.saveEntity(hongXunBomTree);
												// fatherId2 =
												// String.valueOf(hongXunBomTree.getIdc());
												if (flaga == false) {
													iBomTreeDao.saveEntity(hongXunBomTree);
													fatherId2 = String.valueOf(hongXunBomTree.getIdc());
												} else {
													hongXunBomTree.setNodeID(++nodeid);
													hongXunBomTreeList.add(hongXunBomTree);
													fatherId2 = String.valueOf(hongXunBomTree.getNodeID());
												}
												css = 1;
												// System.out.println("css: " +
												// css + " fatherId3:" +
												// fatherId3);
											} else {
												Map<String, Object> map = new HashMap<String, Object>();
												map.put("error",
														"五级物料号:" + hongXunBomTree.getBomMaterialNum() + "位置号错误");
												listMsg.add(map);
												return listMsg;
											}
										}
									}

									length = i;
									// System.out.println("i: " + i);
									break;
								} /*
									 * else{ Map<String,Object> map = new
									 * HashMap<String,Object>();
									 * map.put("error", "五级物料号:" +
									 * hongXunBomTree.getBomMaterialNum() +
									 * "位置号错误"); listMsg.add(map); return
									 * listMsg; }
									 */
							}
						} else if (css == 5) {
							for (int i = 0; i < hongXunBomTree.getBomPosion().length(); i++) {
								if (hongXunBomTree.getBomPosion() == null) {
									Map<String, Object> map = new HashMap<String, Object>();
									map.put("error", "六级物料号:" + hongXunBomTree.getBomMaterialNum() + "位置号错误");
									listMsg.add(map);
									return listMsg;
								}
								if (!hongXunBomTree.getBomPosion().subSequence(i, i + 1).equals("0")) {
									hongXunBomTree.setTopID(topID);

									if (length == i) {
										hongXunBomTree.setParentID(fatherId5);
										// iBomTreeDao.saveEntity(hongXunBomTree);
										// fatherId6 =
										// String.valueOf(hongXunBomTree.getIdc());
										// System.out.println("css: " + css);
										if (flaga == false) {
											iBomTreeDao.saveEntity(hongXunBomTree);
											fatherId6 = String.valueOf(hongXunBomTree.getIdc());
										} else {
											hongXunBomTree.setNodeID(++nodeid);
											hongXunBomTreeList.add(hongXunBomTree);
											fatherId6 = String.valueOf(hongXunBomTree.getNodeID());
										}
									} else {
										if (i > length) {
											if (i - length == 1) {
												Map<String, Object> map = new HashMap<String, Object>();
												map.put("error",
														"六级物料号:" + hongXunBomTree.getBomMaterialNum() + "位置号错误");
												listMsg.add(map);
												return listMsg;
											}
											hongXunBomTree.setParentID(fatherId6);
											// iBomTreeDao.saveEntity(hongXunBomTree);
											// fatherId7 =
											// String.valueOf(hongXunBomTree.getIdc());
											if (flaga == false) {
												iBomTreeDao.saveEntity(hongXunBomTree);
												fatherId7 = String.valueOf(hongXunBomTree.getIdc());
											} else {
												hongXunBomTree.setNodeID(++nodeid);
												hongXunBomTreeList.add(hongXunBomTree);
												fatherId7 = String.valueOf(hongXunBomTree.getNodeID());
											}
											css = 6;
											// System.out.println("css: " +
											// css);
										} else {
											if (length - i == 1) {
												hongXunBomTree.setParentID(fatherId4);
												// iBomTreeDao.saveEntity(hongXunBomTree);
												// fatherId5 =
												// String.valueOf(hongXunBomTree.getIdc());
												if (flaga == false) {
													iBomTreeDao.saveEntity(hongXunBomTree);
													fatherId5 = String.valueOf(hongXunBomTree.getIdc());
												} else {
													hongXunBomTree.setNodeID(++nodeid);
													hongXunBomTreeList.add(hongXunBomTree);
													fatherId5 = String.valueOf(hongXunBomTree.getNodeID());
												}
												css = 4;
												// System.out.println("css: " +
												// css);
											} else if (length - i == 2) {
												hongXunBomTree.setParentID(fatherId3);
												// iBomTreeDao.saveEntity(hongXunBomTree);
												// fatherId4 =
												// String.valueOf(hongXunBomTree.getIdc());
												if (flaga == false) {
													iBomTreeDao.saveEntity(hongXunBomTree);
													fatherId4 = String.valueOf(hongXunBomTree.getIdc());
												} else {
													hongXunBomTree.setNodeID(++nodeid);
													hongXunBomTreeList.add(hongXunBomTree);
													fatherId4 = String.valueOf(hongXunBomTree.getNodeID());
												}
												css = 3;
												// System.out.println("css: " +
												// css);
											} else if (length - i == 3) {
												hongXunBomTree.setParentID(fatherId2);
												// iBomTreeDao.saveEntity(hongXunBomTree);
												// fatherId3 =
												// String.valueOf(hongXunBomTree.getIdc());
												if (flaga == false) {
													iBomTreeDao.saveEntity(hongXunBomTree);
													fatherId3 = String.valueOf(hongXunBomTree.getIdc());
												} else {
													hongXunBomTree.setNodeID(++nodeid);
													hongXunBomTreeList.add(hongXunBomTree);
													fatherId3 = String.valueOf(hongXunBomTree.getNodeID());
												}
												css = 2;
												// System.out.println("css: " +
												// css);
											} else if (length - i == 4) {
												hongXunBomTree.setParentID(fatherId1);
												// iBomTreeDao.saveEntity(hongXunBomTree);
												// fatherId2 =
												// String.valueOf(hongXunBomTree.getIdc());
												if (flaga == false) {
													iBomTreeDao.saveEntity(hongXunBomTree);
													fatherId2 = String.valueOf(hongXunBomTree.getIdc());
												} else {
													hongXunBomTree.setNodeID(++nodeid);
													hongXunBomTreeList.add(hongXunBomTree);
													fatherId2 = String.valueOf(hongXunBomTree.getNodeID());
												}
												css = 1;
												// System.out.println("css: " +
												// css);
											} else {
												Map<String, Object> map = new HashMap<String, Object>();
												map.put("error",
														"六级物料号:" + hongXunBomTree.getBomMaterialNum() + "位置号错误");
												listMsg.add(map);
												return listMsg;
											}
										}
									}

									length = i;
									// System.out.println("i: " + i);
									break;
								} /*
									 * else{ Map<String,Object> map = new
									 * HashMap<String,Object>();
									 * map.put("error", "六级物料号:" +
									 * hongXunBomTree.getBomMaterialNum() +
									 * "位置号错误"); listMsg.add(map); return
									 * listMsg; }
									 */
							}
						} else if (css == 6) {
							for (int i = 0; i < hongXunBomTree.getBomPosion().length(); i++) {
								if (hongXunBomTree.getBomPosion() == null) {
									Map<String, Object> map = new HashMap<String, Object>();
									map.put("error", "七级物料号:" + hongXunBomTree.getBomMaterialNum() + "位置号错误");
									listMsg.add(map);
									return listMsg;
								}
								if (!hongXunBomTree.getBomPosion().subSequence(i, i + 1).equals("0")) {
									hongXunBomTree.setTopID(topID);

									if (length == i) {
										hongXunBomTree.setParentID(fatherId6);
										// iBomTreeDao.saveEntity(hongXunBomTree);
										// fatherId7 =
										// String.valueOf(hongXunBomTree.getIdc());
										if (flaga == false) {
											iBomTreeDao.saveEntity(hongXunBomTree);
											fatherId7 = String.valueOf(hongXunBomTree.getIdc());
										} else {
											hongXunBomTree.setNodeID(++nodeid);
											hongXunBomTreeList.add(hongXunBomTree);
											fatherId7 = String.valueOf(hongXunBomTree.getNodeID());
										}
									} else {
										if (i > length) {
											if (i - length == 1) {
												Map<String, Object> map = new HashMap<String, Object>();
												map.put("error",
														"七级物料号:" + hongXunBomTree.getBomMaterialNum() + "位置号错误");
												listMsg.add(map);
												return listMsg;
											}
											hongXunBomTree.setParentID(fatherId7);
											// iBomTreeDao.saveEntity(hongXunBomTree);
											// fatherId8 =
											// String.valueOf(hongXunBomTree.getIdc());
											if (flaga == false) {
												iBomTreeDao.saveEntity(hongXunBomTree);
												fatherId8 = String.valueOf(hongXunBomTree.getIdc());
											} else {
												hongXunBomTree.setNodeID(++nodeid);
												hongXunBomTreeList.add(hongXunBomTree);
												fatherId8 = String.valueOf(hongXunBomTree.getNodeID());
											}
											css = 7;
										} else {
											if (length - i == 1) {
												hongXunBomTree.setParentID(fatherId5);
												// iBomTreeDao.saveEntity(hongXunBomTree);
												// fatherId6 =
												// String.valueOf(hongXunBomTree.getIdc());
												if (flaga == false) {
													iBomTreeDao.saveEntity(hongXunBomTree);
													fatherId6 = String.valueOf(hongXunBomTree.getIdc());
												} else {
													hongXunBomTree.setNodeID(++nodeid);
													hongXunBomTreeList.add(hongXunBomTree);
													fatherId6 = String.valueOf(hongXunBomTree.getNodeID());
												}
												css = 5;
											} else if (length - i == 2) {
												hongXunBomTree.setParentID(fatherId4);
												// iBomTreeDao.saveEntity(hongXunBomTree);
												// fatherId5 =
												// String.valueOf(hongXunBomTree.getIdc());
												if (flaga == false) {
													iBomTreeDao.saveEntity(hongXunBomTree);
													fatherId5 = String.valueOf(hongXunBomTree.getIdc());
												} else {
													hongXunBomTree.setNodeID(++nodeid);
													hongXunBomTreeList.add(hongXunBomTree);
													fatherId5 = String.valueOf(hongXunBomTree.getNodeID());
												}
												css = 4;
											} else if (length - i == 3) {
												hongXunBomTree.setParentID(fatherId3);
												// iBomTreeDao.saveEntity(hongXunBomTree);
												// fatherId4 =
												// String.valueOf(hongXunBomTree.getIdc());
												if (flaga == false) {
													iBomTreeDao.saveEntity(hongXunBomTree);
													fatherId4 = String.valueOf(hongXunBomTree.getIdc());
												} else {
													hongXunBomTree.setNodeID(++nodeid);
													hongXunBomTreeList.add(hongXunBomTree);
													fatherId4 = String.valueOf(hongXunBomTree.getNodeID());
												}
												css = 3;
											} else if (length - i == 4) {
												hongXunBomTree.setParentID(fatherId2);
												// iBomTreeDao.saveEntity(hongXunBomTree);
												// fatherId3 =
												// String.valueOf(hongXunBomTree.getIdc());
												if (flaga == false) {
													iBomTreeDao.saveEntity(hongXunBomTree);
													fatherId3 = String.valueOf(hongXunBomTree.getIdc());
												} else {
													hongXunBomTree.setNodeID(++nodeid);
													hongXunBomTreeList.add(hongXunBomTree);
													fatherId3 = String.valueOf(hongXunBomTree.getNodeID());
												}
												css = 2;
											} else if (length - i == 5) {
												hongXunBomTree.setParentID(fatherId1);
												// iBomTreeDao.saveEntity(hongXunBomTree);
												// fatherId2 =
												// String.valueOf(hongXunBomTree.getIdc());
												if (flaga == false) {
													iBomTreeDao.saveEntity(hongXunBomTree);
													fatherId2 = String.valueOf(hongXunBomTree.getIdc());
												} else {
													hongXunBomTree.setNodeID(++nodeid);
													hongXunBomTreeList.add(hongXunBomTree);
													fatherId2 = String.valueOf(hongXunBomTree.getNodeID());
												}
												css = 1;
											} else {
												Map<String, Object> map = new HashMap<String, Object>();
												map.put("error",
														"七级物料号:" + hongXunBomTree.getBomMaterialNum() + "位置号错误");
												listMsg.add(map);
												return listMsg;
											}
										}
									}

									length = i;
									// System.out.println("i: " + i);
									break;
								} /*
									 * else{ Map<String,Object> map = new
									 * HashMap<String,Object>();
									 * map.put("error", "七级物料号:" +
									 * hongXunBomTree.getBomMaterialNum() +
									 * "位置号错误"); listMsg.add(map); return
									 * listMsg; }
									 */
							}
						} else if (css == 7) {
							for (int i = 0; i < hongXunBomTree.getBomPosion().length(); i++) {
								if (hongXunBomTree.getBomPosion() == null) {
									Map<String, Object> map = new HashMap<String, Object>();
									map.put("error", "八级物料号:" + hongXunBomTree.getBomMaterialNum() + "位置号错误");
									listMsg.add(map);
									return listMsg;
								}
								if (!hongXunBomTree.getBomPosion().subSequence(i, i + 1).equals("0")) {
									hongXunBomTree.setTopID(topID);

									if (length == i) {
										hongXunBomTree.setParentID(fatherId7);
										iBomTreeDao.saveEntity(hongXunBomTree);
										fatherId8 = String.valueOf(hongXunBomTree.getIdc());
									} else {
										if (i > length) {
											if (i - length == 1) {
												Map<String, Object> map = new HashMap<String, Object>();
												map.put("error",
														"八级物料号:" + hongXunBomTree.getBomMaterialNum() + "位置号错误");
												listMsg.add(map);
												return listMsg;
											}
											hongXunBomTree.setParentID(fatherId8);
											// iBomTreeDao.saveEntity(hongXunBomTree);
											if (flaga == false) {
												iBomTreeDao.saveEntity(hongXunBomTree);
											} else {
												hongXunBomTree.setNodeID(++nodeid);
												hongXunBomTreeList.add(hongXunBomTree);
											}
											// fatherId9 =
											// String.valueOf(hongXunBomTree.getIdc());
											css = 8;
										} else {
											if (length - i == 1) {
												hongXunBomTree.setParentID(fatherId6);
												// iBomTreeDao.saveEntity(hongXunBomTree);
												// fatherId7 =
												// String.valueOf(hongXunBomTree.getIdc());
												if (flaga == false) {
													iBomTreeDao.saveEntity(hongXunBomTree);
													fatherId7 = String.valueOf(hongXunBomTree.getIdc());
												} else {
													hongXunBomTree.setNodeID(++nodeid);
													hongXunBomTreeList.add(hongXunBomTree);
													fatherId7 = String.valueOf(hongXunBomTree.getNodeID());
												}
												css = 6;
											} else if (length - i == 2) {
												hongXunBomTree.setParentID(fatherId5);
												// iBomTreeDao.saveEntity(hongXunBomTree);
												// fatherId6 =
												// String.valueOf(hongXunBomTree.getIdc());
												if (flaga == false) {
													iBomTreeDao.saveEntity(hongXunBomTree);
													fatherId6 = String.valueOf(hongXunBomTree.getIdc());
												} else {
													hongXunBomTree.setNodeID(++nodeid);
													hongXunBomTreeList.add(hongXunBomTree);
													fatherId6 = String.valueOf(hongXunBomTree.getNodeID());
												}
												css = 5;
											} else if (length - i == 3) {
												hongXunBomTree.setParentID(fatherId4);
												// iBomTreeDao.saveEntity(hongXunBomTree);
												// fatherId5 =
												// String.valueOf(hongXunBomTree.getIdc());
												if (flaga == false) {
													iBomTreeDao.saveEntity(hongXunBomTree);
													fatherId5 = String.valueOf(hongXunBomTree.getIdc());
												} else {
													hongXunBomTree.setNodeID(++nodeid);
													hongXunBomTreeList.add(hongXunBomTree);
													fatherId5 = String.valueOf(hongXunBomTree.getNodeID());
												}
												css = 4;
											} else if (length - i == 4) {
												hongXunBomTree.setParentID(fatherId3);
												// iBomTreeDao.saveEntity(hongXunBomTree);
												// fatherId4 =
												// String.valueOf(hongXunBomTree.getIdc());
												if (flaga == false) {
													iBomTreeDao.saveEntity(hongXunBomTree);
													fatherId4 = String.valueOf(hongXunBomTree.getIdc());
												} else {
													hongXunBomTree.setNodeID(++nodeid);
													hongXunBomTreeList.add(hongXunBomTree);
													fatherId4 = String.valueOf(hongXunBomTree.getNodeID());
												}
												css = 3;
											} else if (length - i == 5) {
												hongXunBomTree.setParentID(fatherId2);
												// iBomTreeDao.saveEntity(hongXunBomTree);
												// fatherId3 =
												// String.valueOf(hongXunBomTree.getIdc());
												if (flaga == false) {
													iBomTreeDao.saveEntity(hongXunBomTree);
													fatherId3 = String.valueOf(hongXunBomTree.getIdc());
												} else {
													hongXunBomTree.setNodeID(++nodeid);
													hongXunBomTreeList.add(hongXunBomTree);
													fatherId3 = String.valueOf(hongXunBomTree.getNodeID());
												}
												css = 2;
											} else if (length - i == 6) {
												hongXunBomTree.setParentID(fatherId1);
												// iBomTreeDao.saveEntity(hongXunBomTree);
												// fatherId2 =
												// String.valueOf(hongXunBomTree.getIdc());
												if (flaga == false) {
													iBomTreeDao.saveEntity(hongXunBomTree);
													fatherId2 = String.valueOf(hongXunBomTree.getIdc());
												} else {
													hongXunBomTree.setNodeID(++nodeid);
													hongXunBomTreeList.add(hongXunBomTree);
													fatherId2 = String.valueOf(hongXunBomTree.getNodeID());
												}
												css = 1;
											} else {
												Map<String, Object> map = new HashMap<String, Object>();
												map.put("error",
														"八级物料号:" + hongXunBomTree.getBomMaterialNum() + "位置号错误");
												listMsg.add(map);
												return listMsg;
											}
										}
									}

									length = i;
									// System.out.println("i: " + i);
									break;
								} /*
									 * else{ Map<String,Object> map = new
									 * HashMap<String,Object>();
									 * map.put("error", "八级物料号:" +
									 * hongXunBomTree.getBomMaterialNum() +
									 * "位置号错误"); listMsg.add(map); return
									 * listMsg; }
									 */
							}
						} else {
							System.out.println("css8");
						}
					}
					if (k == 0) {
						k = bomLength;
					}

				}

			}

			/*
			 * HongXunBomTree hongXunBomTree = new HongXunBomTree();
			 * List<HongXunBomTree> hongXunBomTrees =
			 * iBomTreeDao.quary(hongXunBomTree); for(HongXunBomTree item:
			 * hongXunBomTrees){
			 * 
			 * }
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (flaga == true) {
			iBomTreeDao.saveAll(hongXunBomTreeList);
			/*Session session = null;
			try {
				if (session == null) {
					session = stockDao.session();
					session.beginTransaction(); // 开启事物
				}
				for (HongXunBomTree hongXunBomTree : hongXunBomTreeList) {
					if (hongXunBomTree.getBomQuantity() == null) {
						hongXunBomTree.setBomQuantity(0);
					}
					session.save(hongXunBomTree);
				}
				session.getTransaction().commit(); // 提交事物
			} catch (Exception e) {
				e.printStackTrace(); // 打印错误信息
				session.getTransaction().rollback(); // 出错将回滚事物
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("success", "导入失败");
				listMsg.add(map);
				return listMsg;
			} finally {
				// session.flush();//清理缓存，执行批量更新20条记录的SQL update语句
				// session.clear();
				session.close();
			}*/
		}

		int bomQuantity = iBomTreeDao.quary(new HongXunBomTree()).size() - 6;
		if (sumRows == bomQuantity) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("success", "导入成功");
			listMsg.add(map);
			return listMsg;
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("success", "excel总数:" + sumRows + ", bom导入数量:" + bomQuantity);
			listMsg.add(map);
			return listMsg;
		}

	}

	// private static FormulaEvaluator evaluator;
	private String getValue(Cell cell) {
		if (cell == null) {
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
			// System.out.println("num:" + cell.getNumericCellValue());
			BigDecimal bd = new BigDecimal(replaceBlank(str));
			obj = bd.stripTrailingZeros().toPlainString();
			break;
		case STRING:
			String str1 = cell.getStringCellValue();
			// System.out.println("str1: " + str1);
			if (str1.equals("")) {
				obj = null;
			} else {
				obj = replaceBlank(str1);
			}
			break;
		case FORMULA:
			// System.out.print("FORMULA:");
			// obj=getCellValue(evaluator.evaluate(cell));
			try {
				obj = String.valueOf(cell.getNumericCellValue());
			} catch (IllegalStateException e) {
				obj = String.valueOf(cell.getRichStringCellValue());
			}
			break;
		default:
			// System.out.println("read getvalue default");
			obj = null;
			break;
		}
		return obj;
	}

	// 六角螺母 M4 8级 GB/T6170-2000 DIN934 A2P
	private String replaceBlank(String str) {
		String dest = "";
		if (str != null) {
			/*
			 * Pattern p = Pattern.compile("\\s*|\t|\r|\n"); Matcher m =
			 * p.matcher(str); dest = m.replaceAll("");
			 */
			// dest=str.replace(" ", "");
			dest = str.trim();
		}
		return dest;
	}

	@Override
	public List<Map<String, Object>> searchBomNum(String num) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		@SuppressWarnings("unchecked")
		List<HongXunBomTree> hongXunBomTrees = (List<HongXunBomTree>) stockDao.quarywithpara("HongXunBomTree",
				"bomMaterialNum", num);
		if (hongXunBomTrees.size() == 0) {
			return null;
		} else if (hongXunBomTrees.size() > 0) {
			for (HongXunBomTree item : hongXunBomTrees) {
				Map<String, Object> map = new HashMap<String, Object>();
				mapHongXunBomTree(map, item);
				if (getChildren(item.getNodeID()).size() > 0) {
					map.put("state", "closed");
				}
				list.add(map);
			}
		} else {
			System.out.println("部件号不唯一");
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> findParent(String parentID) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		// HongXunBomTree hongXunBomTree =
		// stockDao.hongXunBomTreeFindById(Integer.valueOf(parentID));
		HongXunBomTree hongXunBomTree = null;
		@SuppressWarnings("unchecked")
		List<HongXunBomTree> hongXunBomTrees = (List<HongXunBomTree>) stockDao.quarywithpara("HongXunBomTree", "nodeID",
				Integer.valueOf(parentID));
		if (hongXunBomTrees.size() == 1) {
			hongXunBomTree = hongXunBomTrees.get(0);
		}
		if (hongXunBomTree != null) {

			Map<String, Object> map = new HashMap<String, Object>();
			mapHongXunBomTree(map, hongXunBomTree);
			if (getChildren(hongXunBomTree.getNodeID()).size() > 0) {
				map.put("state", "closed");
			}
			list.add(map);

		}
		return list;
	}

	/*
	 * @Override public List<Map<String, Object>> searchBomAttribute(String
	 * attribute) { // TODO Auto-generated method stub List<Map<String,Object>>
	 * list = new ArrayList<Map<String,Object>>();
	 * 
	 * @SuppressWarnings("unchecked") List<HongXunBomTree> hongXunBomTrees =
	 * (List<HongXunBomTree>) stockDao.quaryFuzzyWithpara("HongXunBomTree",
	 * "attribute", attribute); //@SuppressWarnings("unchecked")
	 * //List<HongXunBomTree> hongXunBomTrees = (List<HongXunBomTree>)
	 * stockDao.quary("from StudentInfo s where s.sname like'%"+attribute+"%'");
	 * for(HongXunBomTree item: hongXunBomTrees){ Map<String,Object> map = new
	 * HashMap<String,Object>(); mapHongXunBomTree(map, item);
	 * if(getChildren(item.getIdc()).size()>0){ map.put("state", "closed"); }
	 * list.add(map); } return list; }
	 */

	@Override
	public List<Map<String, Object>> autotimp(String str) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		// List<String> listName = new ArrayList<String>();
		@SuppressWarnings("unchecked")
		List<HongXunBomTree> hongXunBomTrees = (List<HongXunBomTree>) stockDao.quaryFuzzyWithpara("HongXunBomTree",
				"bomMaterialNum", str);
		for (HongXunBomTree item : hongXunBomTrees) {
			Map<String, Object> map = new HashMap<String, Object>();
			// map.put("materialName", item.getMaterialName());
			map.put("name", item.getBomMaterialNum());
			map.put("id", item.getIdc());
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> onselect(String materialNum) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<HongXunBomTree> hongXunBomTrees = (List<HongXunBomTree>) stockDao.quarywithpara("HongXunBomTree",
				"bomMaterialNum", materialNum);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (hongXunBomTrees != null) {
			for (HongXunBomTree item : hongXunBomTrees) {

				// HongXunBomTree hongXunBomTree =
				// stockDao.hongXunBomTreeFindById(Integer.valueOf(item.getParentID()));

				Map<String, Object> map = new HashMap<String, Object>();
				// map.put("bomMaterialNum", item.getBomMaterialNum());
				// map.put("attribute", item.getAttribute());
				// map.put("bomSpecification", item.getBomSpacification());
				// map.put("weiwaiPrice", item.getMaterialPrice());
				mapHongXunBomTree(map, item);
				if (getChildren(item.getNodeID()).size() > 0) {
					map.put("state", "closed");
				}
				list.add(map);
			}
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> searchPurchaseItem(String materialNum) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<HongXunBomTree> hongXunBomTrees = (List<HongXunBomTree>) stockDao.quarywithpara("HongXunBomTree",
				"bomMaterialNum", materialNum);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (hongXunBomTrees != null) {
			for (HongXunBomTree item : hongXunBomTrees) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("materialNum", item.getBomMaterialNum());
				map.put("attribute", item.getAttribute());
				map.put("materialSpecification", item.getBomSpacification());
				list.add(map);
			}
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> analysis(Integer orderNumID) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> list3 = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> list4 = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> list5 = new ArrayList<Map<String, Object>>();
		List<HongXunBomTree> listPurchase = new ArrayList<HongXunBomTree>();
		List<HongXunBomTree> listWeld = new ArrayList<HongXunBomTree>();
		List<HongXunBomTree> listAssemble = new ArrayList<HongXunBomTree>();
		List<HongXunBomTree> list732 = new ArrayList<HongXunBomTree>();
		List<HongXunPoN> listErr = null;
		List<HongXunBomTree> listError = new ArrayList<HongXunBomTree>();
		List<HongXunPonNum> hongXunPonNumList = new ArrayList<HongXunPonNum>();
		List<Object> parameter = new ArrayList<Object>();
		
		StringBuilder hql = new StringBuilder("from HongXunPoN where 1=1");
		if (orderNumID != null) {
			hql.append(" and orderNumID = ?");
			parameter.add(orderNumID);				
		}
		List<HongXunPoN> hongXunPoNList = iPonDao.getListByHQL(hql.toString(), parameter.toArray());
		
		List<HongXunPoN> hongXunNewPoNList = analysisOne(hongXunPoNList, hongXunPonNumList);// 合并工单相同项
		analysisTwo(hongXunNewPoNList, listErr, list, list1, list2, listWeld, listAssemble, list732);// 按工序分类并检查错误
		if (list.size() != 0) {
			return list;
		}
		a03Analysis(listWeld, list3, listError, listPurchase);
		b00Assamble(listAssemble, list4, listError, listPurchase);
		assamble732(list732, list5, listError, listPurchase);

		Map<String, Object> map = new HashMap<String, Object>();
		if (list1 != null) {
			map.put("list1", list1);
		}
		if (list2 != null) {
			map.put("list2", list2);
		}
		if (list3.size() > 0) {
			map.put("list3", list3);
		}
		if (list4.size() > 0) {
			map.put("list4", list4);
		}
		if (list5.size() > 0) {
			map.put("list5", list5);
		}

		List<Map<String, Object>> list6 = invertMap(listPurchase);
		if (list6.size() > 0) {
			// System.out.println("list6");
			map.put("list6", list6);
		}

		if (listError.size() > 0) {
			map.put("listError", listError);
		}

		list.add(map);
		analysisList = list;
		for (HongXunPonNum hongXunPonNum : hongXunPonNumList) {
			iPonNumDao.save(hongXunPonNum);
		}
		System.out.println("分解完成");
		return list;
	}

	private List<Map<String, Object>> analysisList = null;

	@SuppressWarnings("unchecked")
	@Override
	public void oneKeyExportExcel(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		List<List<Map<String, Object>>> list = new ArrayList<List<Map<String, Object>>>();
		System.out.println("onkey");
		if (analysisList != null) {
			// System.out.println("onkey1");
			/*
			 * if(analysisList.get(0).get("listErrs") !=null){ List<Map<String,
			 * Object>> listErrs = new ArrayList<Map<String, Object>>();
			 * listErrs.addAll((List<Map<String, Object>>)
			 * analysisList.get(0).get("listErrs"));
			 * addNewItem(listErrs,"BOM不包含信息"); list.add(listErrs); }
			 * if(analysisList.get(0).get("listError") !=null){ List<Map<String,
			 * Object>> listError = new ArrayList<Map<String, Object>>();
			 * listError.addAll((List<Map<String, Object>>)
			 * analysisList.get(0).get("listError"));
			 * addNewItem(listError,"BOM属性为空的信息"); list.add(listError); }
			 */
			if (analysisList.get(0).get("list1") != null) {
				List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
				list1.addAll((List<Map<String, Object>>) analysisList.get(0).get("list1"));
				addNewItem(list1, "A01焊接下料");
				list.add(list1);
			}
			if (analysisList.get(0).get("list2") != null) {
				List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
				list2.addAll((List<Map<String, Object>>) analysisList.get(0).get("list2"));
				addNewItem(list2, "A02焊接钣金");
				list.add(list2);
			}
			if (analysisList.get(0).get("list3") != null) {
				List<Map<String, Object>> list3 = (List<Map<String, Object>>) analysisList.get(0).get("list3");
				if (list3.get(0).get("list3_0") != null) {
					List<Map<String, Object>> list3_0 = new ArrayList<Map<String, Object>>();
					list3_0.addAll((List<Map<String, Object>>) list3.get(0).get("list3_0"));
					addNewItem(list3_0, "A03焊接");
					list.add(list3_0);
				}
				if (list3.get(0).get("list3_1") != null) {
					// System.out.println("list3_1: " + "exit");
					List<Map<String, Object>> list3_1 = new ArrayList<Map<String, Object>>();
					list3_1.addAll((List<Map<String, Object>>) list3.get(0).get("list3_1"));
					addNewItem(list3_1, "A03焊接下料");
					list.add(list3_1);
				} else {
					System.out.println("list3_1: " + "null");
				}
				if (list3.get(0).get("list3_2") != null) {
					List<Map<String, Object>> list3_2 = new ArrayList<Map<String, Object>>();
					list3_2.addAll((List<Map<String, Object>>) list3.get(0).get("list3_2"));
					addNewItem(list3_2, "A03焊接钣金");
					list.add(list3_2);
				}
				if (list3.get(0).get("list3_3") != null) {
					List<Map<String, Object>> list3_3 = new ArrayList<Map<String, Object>>();
					list3_3.addAll((List<Map<String, Object>>) list3.get(0).get("list3_3"));
					addNewItem(list3_3, "A03焊接件");
					list.add(list3_3);
				}
				if (list3.get(0).get("list3_4") != null) {
					List<Map<String, Object>> list3_4 = new ArrayList<Map<String, Object>>();
					list3_4.addAll((List<Map<String, Object>>) list3.get(0).get("list3_4"));
					addNewItem(list3_4, "A03外购");
					list.add(list3_4);
				}
			}
			if (analysisList.get(0).get("list4") != null) {
				List<Map<String, Object>> list4 = (List<Map<String, Object>>) analysisList.get(0).get("list4");
				if (list4.get(0).get("list4_4") != null) {
					List<Map<String, Object>> list4_4 = new ArrayList<Map<String, Object>>();
					list4_4.addAll((List<Map<String, Object>>) list4.get(0).get("list4_4"));
					addNewItem(list4_4, "装配");
					list.add(list4_4);
				}
				if (list4.get(0).get("list4_1") != null) {
					List<Map<String, Object>> list4_1 = new ArrayList<Map<String, Object>>();
					list4_1.addAll((List<Map<String, Object>>) list4.get(0).get("list4_1"));
					addNewItem(list4_1, "装配下料");
					list.add(list4_1);
				}
				if (list4.get(0).get("list4_2") != null) {
					List<Map<String, Object>> list4_2 = new ArrayList<Map<String, Object>>();
					list4_2.addAll((List<Map<String, Object>>) list4.get(0).get("list4_2"));
					addNewItem(list4_2, "装配钣金");
					list.add(list4_2);
				}
				if (list4.get(0).get("list4_3") != null) {
					List<Map<String, Object>> list4_3 = new ArrayList<Map<String, Object>>();
					list4_3.addAll((List<Map<String, Object>>) list4.get(0).get("list4_3"));
					addNewItem(list4_3, "装配焊接");
					list.add(list4_3);
				}
				if (list4.get(0).get("list4_5") != null) {
					List<Map<String, Object>> list4_5 = new ArrayList<Map<String, Object>>();
					list4_5.addAll((List<Map<String, Object>>) list4.get(0).get("list4_5"));
					addNewItem(list4_5, "装配外购");
					list.add(list4_5);
				}
				if (list4.get(0).get("list4_6") != null) {
					List<Map<String, Object>> list4_6 = new ArrayList<Map<String, Object>>();
					list4_6.addAll((List<Map<String, Object>>) list4.get(0).get("list4_6"));
					addNewItem(list4_6, "装配外协");
					list.add(list4_6);
				}
				if (list4.get(0).get("list4_7") != null) {
					List<Map<String, Object>> list4_7 = new ArrayList<Map<String, Object>>();
					list4_7.addAll((List<Map<String, Object>>) list4.get(0).get("list4_7"));
					addNewItem(list4_7, "装配喷涂");
					list.add(list4_7);
				}
			}
			if (analysisList.get(0).get("list5") != null) {
				List<Map<String, Object>> list5 = (List<Map<String, Object>>) analysisList.get(0).get("list5");
				if (list5.get(0).get("list5_4") != null) {
					List<Map<String, Object>> list5_4 = new ArrayList<Map<String, Object>>();
					list5_4.addAll((List<Map<String, Object>>) list5.get(0).get("list5_4"));
					addNewItem(list5_4, "732");
					list.add(list5_4);
				}
				if (list5.get(0).get("list5_1") != null) {

					List<Map<String, Object>> list5_1 = new ArrayList<Map<String, Object>>();
					list5_1.addAll((List<Map<String, Object>>) list5.get(0).get("list5_1"));
					addNewItem(list5_1, "732下料");
					list.add(list5_1);
				}
				if (list5.get(0).get("list5_2") != null) {
					List<Map<String, Object>> list5_2 = new ArrayList<Map<String, Object>>();
					list5_2.addAll((List<Map<String, Object>>) list5.get(0).get("list5_2"));
					addNewItem(list5_2, "732钣金");
					list.add(list5_2);
				}
				if (list5.get(0).get("list5_3") != null) {
					List<Map<String, Object>> list5_3 = new ArrayList<Map<String, Object>>();
					list5_3.addAll((List<Map<String, Object>>) list5.get(0).get("list5_3"));
					addNewItem(list5_3, "732焊接");
					list.add(list5_3);
				}

				if (list5.get(0).get("list5_5") != null) {
					List<Map<String, Object>> list5_5 = new ArrayList<Map<String, Object>>();
					list5_5.addAll((List<Map<String, Object>>) list5.get(0).get("list5_5"));
					addNewItem(list5_5, "732外购");
					list.add(list5_5);
				}
				if (list5.get(0).get("list5_6") != null) {
					List<Map<String, Object>> list5_6 = new ArrayList<Map<String, Object>>();
					list5_6.addAll((List<Map<String, Object>>) list5.get(0).get("list5_6"));
					addNewItem(list5_6, "732外协");
					list.add(list5_6);
				}
				if (list5.get(0).get("list5_7") != null) {
					List<Map<String, Object>> list5_7 = new ArrayList<Map<String, Object>>();
					list5_7.addAll((List<Map<String, Object>>) list5.get(0).get("list5_7"));
					addNewItem(list5_7, "732喷涂");
					list.add(list5_7);
				}
				if (analysisList.get(0).get("list6") != null) {
					List<Map<String, Object>> list6 = new ArrayList<Map<String, Object>>();
					list6.addAll((List<Map<String, Object>>) analysisList.get(0).get("list6"));
					addNewItem(list6, "外购件");
					list.add(list6);
				}

			}
			try {
				exportExcelBook(request, response, list);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void exportExcelBook(HttpServletRequest request, HttpServletResponse response,
			List<List<Map<String, Object>>> listSheet) throws IOException {

		// 文件名称，客户端传来的参数，防止中文文件名乱码参数编码因此这里需要解码
		String fileName = URLDecoder.decode(request.getParameter("fileName"), "UTF-8");
		String orderNum = request.getParameter("orderNum");
		fileName = fileName + orderNum;
		// System.out.println("orderNum: " + orderNum);
		HSSFWorkbook wb = new HSSFWorkbook();

		for (List<Map<String, Object>> list : listSheet) {

			Sheet sheet = wb.createSheet(list.get(0).get("name").toString());
			/*
			 * sheet.autoSizeColumn((short)2); //调整第三列宽度
			 * sheet.autoSizeColumn((short)3); //调整第三列宽度
			 * sheet.autoSizeColumn((short)4); //调整第三列宽度
			 */
			HSSFCellStyle cellStyle = wb.createCellStyle();
			Font cellFont = wb.createFont();
			String title = "";
			String sheetName = list.get(0).get("name").toString();
			if (sheetName.equals("A01焊接下料") || sheetName.equals("A02焊接钣金") || sheetName.equals("A03焊接")) {
				title = sheetName + "--表面处理/入库                      单号：" + orderNum;
			} else if (sheetName.equals("装配") || sheetName.equals("732")) {
				title = sheetName + "--入库                      单号：" + orderNum;
			} else if (sheetName.equals("A03焊接下料") || sheetName.equals("A03焊接钣金") || sheetName.equals("装配下料")
					|| sheetName.equals("装配钣金") || sheetName.equals("732下料") || sheetName.equals("732钣金")
					|| sheetName.equals("732焊接")) {
				title = sheetName + "--焊接                     单号：" + orderNum;
			} else if (sheetName.equals("装配焊接")) {
				title = sheetName + "--装配                     单号：" + orderNum;
			} else {
				title = sheetName + "           单号：" + orderNum;
			}

			String[] tableHead = { "序号", "单梯", "定单号", "行号", "技术规格", "图号", "长度", "材料", "位置", "属性", "部件号", "项目数", "下料",
					"钣金", "焊接", "外协", "装配", "喷涂", " PO交期", "生产交期", "备注" };
			String[] columName = { "sn", "bomQuantity", "bomOrderNum", "bomLine", "bomSpacification", "bomDrawingNum",
					"bomLength", "bomMaterial", "bomPosion", "attribute", "bomMaterialNum", "bomItemQuantity",
					"bomMaterialCuting", "bomPlateCuting", "bomMaterialWeld", "bomWeiwai", "bomAssemble", "bomPainting",
					"bomDeliveryDate", "生产交期", "remark" };
			int[] columWidths = { 512, 512, 3584, 1152, 8192, 3840, 1536, 1792, 1536, 2048, 3840, 1536, 1024, 1024,
					1024, 1024, 1024, 2048, 3584, 3584, 6144 };

			setTitles(sheet, wb, title, tableHead);
			Row row = null;
			Cell cell = null;
			String[] values = null;
			int sum = 0;
			for (int i = 1; i < list.size(); i++) {
				values = sortMap(list.get(i), columName);
				row = sheet.createRow(1 + i);
				// row.setHeightInPoints(25);
				for (int j = 0; j < values.length; j++) {
					cell = row.createCell(j);
					cell.setCellValue(values[j]);
					// System.out.println(j + ":" + values[j]);
					if (j == 11) {
						sum = sum + Integer.valueOf(values[j]);
					}
					cell.setCellStyle(setCellStyle(cellStyle, cellFont));
				}
			}
			setBootStyle(sheet, list, wb, sum);
			for (int i = 0; i < columWidths.length; i++) {
				// System.out.println(i + ": " +columWidths.get(i));
				sheet.setColumnWidth(i, columWidths[i]);
			}
			PrintSetup printSetup = sheet.getPrintSetup();
			printSetup.setPaperSize(HSSFPrintSetup.A4_PAPERSIZE); // 纸张
			sheet.setDisplayGridlines(false);
			sheet.setPrintGridlines(false);
			sheet.setMargin(HSSFSheet.TopMargin, (double) 0.2); // 上边距
			sheet.setMargin(HSSFSheet.BottomMargin, (double) 0.2); // 下边距
			sheet.setMargin(HSSFSheet.LeftMargin, (double) 0.2); // 左边距
			sheet.setMargin(HSSFSheet.RightMargin, (double) 0.2); // 右边距

			/*
			 * row = sheet.createRow(3+list.size()); cell = row.createCell(0);
			 * sheet.addMergedRegion(new CellRangeAddress(3+list.size(),
			 * 3+list.size(), 0, 3)); cell.setCellValue("????????/??????:");
			 * cell.setCellStyle(setTitleStyle2(wb)); cell = row.createCell(4);
			 * sheet.addMergedRegion(new CellRangeAddress(3+list.size(),
			 * 3+list.size(), 4, 13));
			 * cell.setCellValue("??????????/?????????:");
			 * cell.setCellStyle(setTitleStyle2(wb));
			 * 
			 * row = sheet.createRow(3+list.size()+1); cell = row.createCell(0);
			 * sheet.addMergedRegion(new CellRangeAddress(3+list.size()+1,
			 * 3+list.size()+1, 0, 10)); cell.setCellValue("??????:");
			 * cell.setCellStyle(setTitleStyle2(wb));
			 * 
			 * row = sheet.createRow(3+list.size()+2); cell = row.createCell(0);
			 * sheet.addMergedRegion(new CellRangeAddress(3+list.size()+2,
			 * 3+list.size()+2, 0, 1)); cell.setCellValue("???:");
			 * cell.setCellStyle(setTitleStyle2(wb)); cell = row.createCell(2);
			 * sheet.addMergedRegion(new CellRangeAddress(3+list.size()+2,
			 * 3+list.size()+2, 2, 3)); cell.setCellValue("????:");
			 * cell.setCellStyle(setTitleStyle2(wb)); cell = row.createCell(4);
			 * sheet.addMergedRegion(new CellRangeAddress(3+list.size()+2,
			 * 3+list.size()+2, 4, 5)); cell.setCellValue("????:");
			 * cell.setCellStyle(setTitleStyle2(wb)); cell = row.createCell(6);
			 * sheet.addMergedRegion(new CellRangeAddress(3+list.size()+2,
			 * 3+list.size()+2, 6, 9)); cell.setCellValue("?????:");
			 * cell.setCellStyle(setTitleStyle2(wb)); cell = row.createCell(10);
			 * sheet.addMergedRegion(new CellRangeAddress(3+list.size()+2,
			 * 3+list.size()+2, 10, 13)); cell.setCellValue("???:");
			 * cell.setCellStyle(setTitleStyle2(wb));
			 */
		}

		// 获取输出流
		OutputStream os = response.getOutputStream();
		// 重置输出流
		response.reset();
		// 设定输出文件头
		response.setHeader("Content-disposition",
				"attachment; filename=" + new String(fileName.getBytes("GB2312"), "8859_1") + ".xls");
		// 定义输出类型
		response.setContentType("application/msexcel");
		wb.write(os);
		os.close();
		return;
	}

	private HSSFCellStyle setTitleStyle(HSSFWorkbook wb) {
		HSSFCellStyle titleStyle = wb.createCellStyle(); // ???????
		titleStyle.setAlignment(HorizontalAlignment.CENTER);
		titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		Font ztFont = wb.createFont();
		ztFont.setItalic(false); // ?????????б????
		ztFont.setColor(Font.COLOR_NORMAL); // ??????????????????
		ztFont.setFontHeightInPoints((short) 16); // ???????С?????18px
		ztFont.setFontName("????"); // ???????塱??????????????????
		// ztFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); //???
		// ztFont.setUnderline(Font.U_DOUBLE); //
		// ????Font.U_SINGLE?????????/Font.U_DOUBLE?????????
		// ztFont.setStrikeout(true); // ???????????
		titleStyle.setFont(ztFont);
		return titleStyle;
	}

	private void setBootStyle(Sheet sheet, List<Map<String, Object>> list, HSSFWorkbook wb, int sum) {
		// System.out.println("sum: " + sum);
		Row row = sheet.createRow(1 + list.size() + 1);
		Cell cell = row.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(1 + list.size() + 1, 1 + list.size() + 1, 0, 10));
		cell.setCellValue("数量:");
		cell.setCellStyle(setTitleStyle2(wb));
		cell = row.createCell(11);
		// sheet.addMergedRegion(new CellRangeAddress(1+list.size()+1,
		// 1+list.size()+1, 11, 12));
		cell.setCellValue(sum);
		cell.setCellStyle(setTitleStyle2(wb));

		row = sheet.createRow(1 + list.size() + 4);
		cell = row.createCell(4);
		sheet.addMergedRegion(new CellRangeAddress(1 + list.size() + 4, 1 + list.size() + 4, 4, 7));
		cell.setCellValue("计划:");
		cell.setCellStyle(setTitleStyle2(wb));
		cell = row.createCell(8);
		sheet.addMergedRegion(new CellRangeAddress(1 + list.size() + 4, 1 + list.size() + 4, 8, 14));
		// sheet.addMergedRegion(new CellRangeAddress(1+list.size()+1,
		// 1+list.size()+1, 11, 12));
		cell.setCellValue("制造:");
		cell.setCellStyle(setTitleStyle2(wb));

		cell = row.createCell(15);
		sheet.addMergedRegion(new CellRangeAddress(1 + list.size() + 4, 1 + list.size() + 4, 15, 20));
		// sheet.addMergedRegion(new CellRangeAddress(1+list.size()+1,
		// 1+list.size()+1, 11, 12));
		cell.setCellValue("交接:");
		cell.setCellStyle(setTitleStyle2(wb));
	}

	private HSSFCellStyle setTitleStyle2(HSSFWorkbook wb) {
		HSSFCellStyle titleStyle2 = wb.createCellStyle(); // ??????
		titleStyle2.setAlignment(HorizontalAlignment.LEFT);
		titleStyle2.setVerticalAlignment(VerticalAlignment.CENTER);
		Font ztFont2 = wb.createFont();
		ztFont2.setItalic(false); // ?????????б????
		ztFont2.setColor(Font.COLOR_NORMAL); // ??????????????????
		ztFont2.setFontHeightInPoints((short) 11); // ???????С?????18px
		ztFont2.setFontName("????"); // ??????????????????
		// ztFont2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); //???
		// ztFont.setUnderline(Font.U_DOUBLE); //
		// ????Font.U_SINGLE?????????/Font.U_DOUBLE?????????
		// ztFont.setStrikeout(true); // ???????????
		titleStyle2.setFont(ztFont2);
		return titleStyle2;
	}

	private HSSFCellStyle setCellStyle(HSSFCellStyle cellStyle, Font cellFont) {
		// ----------------????????----------------------------------
		// ??????
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		cellStyle.setBorderBottom(BorderStyle.THIN); // ?±??
		cellStyle.setBorderLeft(BorderStyle.THIN);// ????
		cellStyle.setBorderTop(BorderStyle.THIN);// ????
		cellStyle.setBorderRight(BorderStyle.THIN);// ????

		cellFont.setItalic(false); // ?????????б????
		cellFont.setColor(Font.COLOR_NORMAL);
		cellFont.setFontHeightInPoints((short) 12); // 设置字体大小
		cellFont.setFontName("宋体"); // 设置字体
		// cellFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		cellStyle.setFont(cellFont);
		cellStyle.setWrapText(true);// ???????????
		return cellStyle;
	}

	private void setTitles(Sheet sheet, HSSFWorkbook wb, String title, String[] tableHead) {

		// List<Integer> list = new ArrayList<Integer>();
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, tableHead.length - 1));
		Row row = sheet.createRow(0);
		Cell cell = row.createCell(0);
		cell.setCellValue(title);
		cell.setCellStyle(setTitleStyle(wb));

		HSSFCellStyle cellStyle = wb.createCellStyle();
		Font cellFont = wb.createFont();
		row = sheet.createRow(1);
		cell = row.createCell(1);
		for (int i = 0; i < tableHead.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(tableHead[i]);
			cell.setCellStyle(setCellStyle(cellStyle, cellFont));
			// list.add(titles[i].getBytes().length);
			/*
			 * if(titles[i].equals("定单号") || titles[i].equals("部件号") ||
			 * titles[i].equals("交货日期") || titles[i].equals("图号")){
			 * sheet.setColumnWidth(i, 3000); }else if(titles[i].equals("技术规格")
			 * || titles[i].equals("备注")){ sheet.setColumnWidth(4, 8000); }
			 */
		}
		// return list;
	}

	private String[] sortMap(Map<String, Object> map, String[] columName) {

		List<String> list = new ArrayList<String>();
		for (int i = 0; i < columName.length; i++) {
			list.add(columName[i]);
		}
		String[] values = new String[list.size()];
		Iterator<String> iterator = map.keySet().iterator();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			int index = list.indexOf(key);
			if (index > -1) {
				if (map.get(key) != null) {
					values[index] = String.valueOf(map.get(key));
				} else {
					values[index] = "";
				}
			}
		}
		return values;
	}

	private void addNewItem(List<Map<String, Object>> list, String name) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		list.add(0, map);
	}

	@Override
	public boolean getTablesStatus(String str) {
		// TODO Auto-generated method stub

		HongXunTablesStatus hongXunTablesStatus = iTablesStatusDao.tablesStatusFindById(1);
		if (hongXunTablesStatus == null) {
			// System.out.println("size == 0");
			hongXunTablesStatus = new HongXunTablesStatus();
			hongXunTablesStatus.setAnalysis(false);
			hongXunTablesStatus.setBomModify(false);
			hongXunTablesStatus.setOnekeyExport(false);
			hongXunTablesStatus.setBomEnable(false);
			iTablesStatusDao.save(hongXunTablesStatus);
		}
		if (str.equals("analysis")) {
			System.out.println("analyis:" + hongXunTablesStatus.isAnalysis());
			return hongXunTablesStatus.isAnalysis();
		} else if (str.equals("bomModify")) {
			// System.out.println("analyis:" +
			// hongXunTablesStatus.isBomModify());
			return hongXunTablesStatus.isBomModify();
		} else if (str.equals("bomEnable")) {
			// System.out.println("analyis:" +
			// hongXunTablesStatus.isBomModify());
			return hongXunTablesStatus.isBomEnable();
		} else {
			System.out.println("error:getTablesStatus");
			return false;
		}
	}

	@Override
	public void setTablesStatus(String str, boolean flag) {
		// TODO Auto-generated method stub
		HongXunTablesStatus hongXunTablesStatus = iTablesStatusDao.tablesStatusFindById(1);
		if (str.equals("bomModify")) {
			hongXunTablesStatus.setBomModify(flag);
		} else if (str.equals("analysis")) {
			hongXunTablesStatus.setAnalysis(flag);
		} else if (str.equals("exportExcel")) {
			hongXunTablesStatus.setOnekeyExport(flag);
		} else if (str.equals("bomEnable")) {
			hongXunTablesStatus.setBomEnable(flag);
		}
		iTablesStatusDao.update(hongXunTablesStatus);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getAnalysisItem(String listItem) {
		// TODO Auto-generated method stub
		if (analysisList == null) {
			return null;
		} else if (listItem.equals("listErrs")) {
			return (List<Map<String, Object>>) analysisList.get(0).get("listErrs");
		} else if (listItem.equals("listError")) {
			return (List<Map<String, Object>>) analysisList.get(0).get("listError");
		} else if (listItem.equals("list1")) {
			List<Map<String, Object>> list = (List<Map<String, Object>>) analysisList.get(0).get("list1");
			return list;// .subList(1, list.size()-1);
		} else if (listItem.equals("list2")) {
			List<Map<String, Object>> list = (List<Map<String, Object>>) analysisList.get(0).get("list2");
			return list;// .subList(1, list.size()-1);
		} else if (listItem.equals("list3_0")) {
			List<Map<String, Object>> list = (List<Map<String, Object>>) analysisList.get(0).get("list3");
			list = (List<Map<String, Object>>) list.get(0).get("list3_0");
			return list;// .subList(1, list.size()-1);
		} else if (listItem.equals("list3_1")) {
			List<Map<String, Object>> list = (List<Map<String, Object>>) analysisList.get(0).get("list3");
			list = (List<Map<String, Object>>) list.get(0).get("list3_1");
			return list;// .subList(1, list.size()-1);
		} else if (listItem.equals("list3_2")) {
			List<Map<String, Object>> list = (List<Map<String, Object>>) analysisList.get(0).get("list3");
			list = (List<Map<String, Object>>) list.get(0).get("list3_2");
			return list;// .subList(1, list.size()-1);
		} else if (listItem.equals("list3_3")) {
			List<Map<String, Object>> list = (List<Map<String, Object>>) analysisList.get(0).get("list3");
			list = (List<Map<String, Object>>) list.get(0).get("list3_3");
			return list;// .subList(1, list.size()-1);
		} else if (listItem.equals("list3_4")) {
			List<Map<String, Object>> list = (List<Map<String, Object>>) analysisList.get(0).get("list3");
			list = (List<Map<String, Object>>) list.get(0).get("list3_4");
			return list;// .subList(1, list.size()-1);
		} else if (listItem.equals("list4_1")) {
			List<Map<String, Object>> list = (List<Map<String, Object>>) analysisList.get(0).get("list4");
			list = (List<Map<String, Object>>) list.get(0).get("list4_1");
			return list;// .subList(1, list.size()-1);
		} else if (listItem.equals("list4_2")) {
			List<Map<String, Object>> list = (List<Map<String, Object>>) analysisList.get(0).get("list4");
			list = (List<Map<String, Object>>) list.get(0).get("list4_2");
			return list;// .subList(1, list.size()-1);
		} else if (listItem.equals("list4_3")) {
			List<Map<String, Object>> list = (List<Map<String, Object>>) analysisList.get(0).get("list4");
			list = (List<Map<String, Object>>) list.get(0).get("list4_3");
			return list;// .subList(1, list.size()-1);
		} else if (listItem.equals("list4_4")) {
			List<Map<String, Object>> list = (List<Map<String, Object>>) analysisList.get(0).get("list4");
			list = (List<Map<String, Object>>) list.get(0).get("list4_4");
			return list;// .subList(1, list.size()-1);
		} else if (listItem.equals("list4_5")) {
			List<Map<String, Object>> list = (List<Map<String, Object>>) analysisList.get(0).get("list4");
			list = (List<Map<String, Object>>) list.get(0).get("list4_5");
			return list;// .subList(1, list.size()-1);
		} else if (listItem.equals("list4_6")) {
			List<Map<String, Object>> list = (List<Map<String, Object>>) analysisList.get(0).get("list4");
			list = (List<Map<String, Object>>) list.get(0).get("list4_6");
			return list;// .subList(1, list.size()-1);
		} else if (listItem.equals("list4_7")) {
			List<Map<String, Object>> list = (List<Map<String, Object>>) analysisList.get(0).get("list4");
			list = (List<Map<String, Object>>) list.get(0).get("list4_7");
			return list;// .subList(1, list.size()-1);
		} else if (listItem.equals("list5_1")) {
			List<Map<String, Object>> list = (List<Map<String, Object>>) analysisList.get(0).get("list5");
			list = (List<Map<String, Object>>) list.get(0).get("list5_1");
			return list;// .subList(1, list.size()-1);
		} else if (listItem.equals("list5_2")) {
			List<Map<String, Object>> list = (List<Map<String, Object>>) analysisList.get(0).get("list5");
			list = (List<Map<String, Object>>) list.get(0).get("list5_2");
			return list;// .subList(1, list.size()-1);
		} else if (listItem.equals("list5_3")) {
			List<Map<String, Object>> list = (List<Map<String, Object>>) analysisList.get(0).get("list5");
			list = (List<Map<String, Object>>) list.get(0).get("list5_3");
			return list;// .subList(1, list.size()-1);
		} else if (listItem.equals("list5_4")) {
			List<Map<String, Object>> list = (List<Map<String, Object>>) analysisList.get(0).get("list5");
			list = (List<Map<String, Object>>) list.get(0).get("list5_4");
			return list;// .subList(1, list.size()-1);
		} else if (listItem.equals("list5_5")) {
			List<Map<String, Object>> list = (List<Map<String, Object>>) analysisList.get(0).get("list5");
			list = (List<Map<String, Object>>) list.get(0).get("list5_5");
			return list;// .subList(1, list.size()-1);
		} else if (listItem.equals("list5_6")) {
			List<Map<String, Object>> list = (List<Map<String, Object>>) analysisList.get(0).get("list5");
			list = (List<Map<String, Object>>) list.get(0).get("list5_6");
			return list;// .subList(1, list.size()-1);
		} else if (listItem.equals("list5_7")) {
			List<Map<String, Object>> list = (List<Map<String, Object>>) analysisList.get(0).get("list5");
			list = (List<Map<String, Object>>) list.get(0).get("list5_7");
			return list;// .subList(1, list.size()-1);
		} else if (listItem.equals("list6")) {
			List<Map<String, Object>> list = (List<Map<String, Object>>) analysisList.get(0).get("list6");
			return list;// .subList(1, list.size()-1);

		} else if (listItem.equals("all")) {
			return analysisList;
		} else {
			System.out.println("list.error");
		}
		return null;
	}

	private List<HongXunPoN> analysisOne(List<HongXunPoN> hongXunPoNList, List<HongXunPonNum> hongXunPonNumList) {
		List<HongXunPoN> hongXunNewPoNList = new ArrayList<HongXunPoN>();
		List<String> materialNumList = new ArrayList<String>();
		List<String> ponList = new ArrayList<String>();

		HongXunSpecialMaterialNum hongXunSpecialMaterialNum = new HongXunSpecialMaterialNum();
		hongXunSpecialMaterialNum.setLocaltion("顶层");
		List<HongXunSpecialMaterialNum> set = iSpecialMaterialDao.quary(hongXunSpecialMaterialNum);
		for (HongXunPoN item : hongXunPoNList) {
			int index1 = ponList.indexOf(item.getPon());
			if (index1 == -1) {

				ponList.add(item.getPon());
				HongXunPonNum hongXunPonNum = new HongXunPonNum();
				hongXunPonNum.setPoCreateDate(item.getPoCreateDate());
				hongXunPonNum.setPon(item.getPon());
				hongXunPonNum.setSupplier(item.getSupplier());
				hongXunPonNum.setQuantity(1);
				hongXunPonNum.setTotalAmount(item.getTotalAmount());
				hongXunPonNumList.add(hongXunPonNum);
			} else {
				hongXunPonNumList.get(index1).setQuantity(hongXunPonNumList.get(index1).getQuantity() + 1);
				if (item.getTotalAmount() != null && hongXunPonNumList.get(index1).getTotalAmount() != null) {
					double totalAmount = hongXunPonNumList.get(index1).getTotalAmount().doubleValue()
							+ item.getTotalAmount().doubleValue();
					hongXunPonNumList.get(index1).setTotalAmount(BigDecimal.valueOf(totalAmount));
				}
			}

			int index = materialNumList.indexOf(item.getMaterialNo());
			if (index > -1) {
				if (set.contains(item.getMaterialNo())) {
					index = -1;
				}
			}
			if ((index > -1) && (hongXunNewPoNList.get(index).getMaterialDesc().equals(item.getMaterialDesc()))) {
				// if(hongXunNewPoNList.get(index).getMaterialDesc().equals(item.getMaterialDesc())){
				// hongXunNewPoNList.get(index).setQuantity(hongXunNewPoNList.get(index).getQuantity()
				// + item.getQuantity());
				hongXunNewPoNList.get(index)
						.setWorkQuantity(hongXunNewPoNList.get(index).getWorkQuantity() + item.getWorkQuantity());

				/*
				 * if(hongXunNewPoNList.get(index).getMaterialNo().equals(
				 * "1717995301")){ System.out.println("----" +
				 * hongXunNewPoNList.get(index).getQuantity()); }
				 */
			} else {
				/*
				 * if(item.getMaterialNo().equals("1717995301")){
				 * System.out.println("---" + item.getQuantity()); }
				 */
				hongXunNewPoNList.add(item);
				materialNumList.add(item.getMaterialNo());
			}
		}

		return hongXunNewPoNList;
	}

	private List<Map<String, Object>> analysisTwo(List<HongXunPoN> hongXunNewPoNList, List<HongXunPoN> listErr,
			List<Map<String, Object>> list, List<Map<String, Object>> list1, List<Map<String, Object>> list2,
			List<HongXunBomTree> listWeld, List<HongXunBomTree> listAssemble, List<HongXunBomTree> list732) {
		HongXunBomTree hongXunBomTree = new HongXunBomTree();
		hongXunBomTree.setSn(1);
		for (HongXunPoN item : hongXunNewPoNList) {
			hongXunBomTree.setBomMaterialNum(item.getMaterialNo());
			Map<String, Object> map = new HashMap<String, Object>();
			List<HongXunBomTree> hongXunBomTrees = (List<HongXunBomTree>) iBomTreeDao.quary(hongXunBomTree);
			
			if (hongXunBomTrees.size() == 0) {
				if (listErr == null) {
					listErr = new ArrayList<HongXunPoN>();
				}
				// System.out.println(item.getMaterialNo());
				listErr.add(item);
			} else if (hongXunBomTrees.size() == 1) {
				// for(HongXunBomTree hongXunBomTree: hongXunBomTrees){

				if (hongXunBomTrees.get(0).getSn() == 1) {
					hongXunBomTrees.get(0).setBomDeliveryDate(item.getPoDeliveryDate());
					if (item.getWorkQuantity() != null) {
						hongXunBomTrees.get(0).setBomItemQuantity(item.getWorkQuantity());
					} else {
						map.put("error", "订单数量为空");			
						list.add(map);
					}
					hongXunBomTrees.get(0).setBomOrderNum(item.getPon());
					hongXunBomTrees.get(0).setBomSpacification(item.getMaterialDesc());
					hongXunBomTrees.get(0).setRemark(item.getOthers());
					hongXunBomTrees.get(0).setBomLine(item.getLine());

					if (item.getWorkQuantity() != 0) {
						if (hongXunBomTrees.get(0).getBomGroup().equals("A01焊接下料")) {
							if (list1 == null) {
								list1 = new ArrayList<Map<String, Object>>();
							}
							mapHongXunBomTree(map, hongXunBomTrees.get(0));
							list1.add(map);
						} else if (hongXunBomTrees.get(0).getBomGroup().equals("A02焊接钣金")) {
							if (list2 == null) {
								list2 = new ArrayList<Map<String, Object>>();
							}
							mapHongXunBomTree(map, hongXunBomTrees.get(0));

							list2.add(map);
						} else if (hongXunBomTrees.get(0).getBomGroup().equals("A03焊接")) {
							if (listWeld == null) {
								listWeld = new ArrayList<HongXunBomTree>();
							}
							listWeld.add(hongXunBomTrees.get(0));
						} else if (hongXunBomTrees.get(0).getBomGroup().equals("B00装配清单")) {
				
							listAssemble.add(hongXunBomTrees.get(0));
						} else if (hongXunBomTrees.get(0).getBomGroup().equals("732")) {
			
							list732.add(hongXunBomTrees.get(0));
						} else {
						

							map.put("error", "物料号：" + hongXunBomTrees.get(0).getBomMaterialNum() + "不能分解");
							// System.out.println("不能分解");
							list.add(map);
							// return list;
						}
					}
				}
				// }
			} else {
				map.put("error", "物料号：" + hongXunBomTrees.get(0).getBomMaterialNum() + "一层料号重复");
				// System.out.println("不能分解");
				list.add(map);
				// return list;
			}
		}
		if (listErr != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("listErrs", listErr);
			System.out.println("listErrs");
			list.add(map);
			analysisList = list;
			return list;
		}
		System.out.println("分解第一步完成");
		return null;

	}

	private void a03Analysis(List<HongXunBomTree> listWeld, List<Map<String, Object>> list3,
			List<HongXunBomTree> listError, List<HongXunBomTree> listPurchase) {
		List<Map<String, Object>> listWeldComponent = null;
		List<HongXunBomTree> listWeldMaterialCuting = new ArrayList<HongXunBomTree>();
		List<HongXunBomTree> listWeldPlateCuting = new ArrayList<HongXunBomTree>();
		List<HongXunBomTree> listWeldWeldComponent = new ArrayList<HongXunBomTree>();
		List<HongXunBomTree> listWeldPurchase = new ArrayList<HongXunBomTree>();

		if (listWeld != null) {
			for (HongXunBomTree item : listWeld) {
				// if(item.getBomGroup().equals("A03焊接汇总")){
				// item.getBomItemQuantity();
				// if(item.getBomMaterialWeld() !=null &&
				// item.getBomMaterialWeld().equals("焊接")){
				if (listWeldComponent == null) {
					listWeldComponent = new ArrayList<Map<String, Object>>();
				}
				Map<String, Object> mapTop = new HashMap<String, Object>();
				mapHongXunBomTree(mapTop, item);
				listWeldComponent.add(mapTop);
				if (item.getBomMaterialCuting() != null && item.getBomMaterialCuting().equals("下料")) {
					listWeldMaterialCuting.add(item.clone());
				}
				if (item.getBomPlateCuting() != null && item.getBomPlateCuting().equals("钣金")) {
					listWeldPlateCuting.add(item.clone());
				}
				if (item.getAttribute() != null && item.getAttribute().equals("外购件")) {
					// listWeldPurchase.add(item.clone());
					listPurchase.add(item.clone());
				}
				@SuppressWarnings("unchecked")
				List<HongXunBomTree> hongXunBomTrees = (List<HongXunBomTree>) stockDao.quarywithpara("HongXunBomTree",
						"topID", item.getNodeID());
				// System.out.println("A03ndoeID: " + item.getNodeID() + "
				// size(): " + hongXunBomTrees.size());

				for (HongXunBomTree hongXunBomTree : hongXunBomTrees) {
					hongXunBomTree.setBomDeliveryDate(item.getBomDeliveryDate());
					hongXunBomTree.setBomItemQuantity(hongXunBomTree.getBomQuantity() * item.getBomItemQuantity());

					Map<String, Object> map0 = new HashMap<String, Object>();
					mapHongXunBomTree(map0, hongXunBomTree);
					listWeldComponent.add(map0);
					if (hongXunBomTree.getBomMaterialCuting() != null
							&& hongXunBomTree.getBomMaterialCuting().equals("下料")) {
						listWeldMaterialCuting.add(hongXunBomTree.clone());
					}
					if (hongXunBomTree.getBomPlateCuting() != null && hongXunBomTree.getBomPlateCuting().equals("钣金")) {
						listWeldPlateCuting.add(hongXunBomTree.clone());
					}

					if (hongXunBomTree.getAttribute() != null && hongXunBomTree.getAttribute().equals("外购件")) {
						// System.out.println("发现外购件");
						// listWeldPurchase.add(hongXunBomTree.clone()); //分步外购件
						listPurchase.add(hongXunBomTree.clone());
					}
					if (!((hongXunBomTree.getBomMaterialCuting() != null
							&& hongXunBomTree.getBomMaterialCuting().equals("下料"))
							|| (hongXunBomTree.getBomPlateCuting() != null
									&& hongXunBomTree.getBomPlateCuting().equals("钣金"))
							|| (hongXunBomTree.getAttribute() != null && hongXunBomTree.getAttribute().equals("外购件"))
							|| (hongXunBomTree.getBomMaterialWeld() != null
									&& hongXunBomTree.getBomMaterialWeld().equals("焊接.1")))) {
						// System.out.println("A03焊接分解时出现问题" + item.getIdc() +
						// ":" + item.getBomSpacification() + ":" +
						// hongXunBomTree.getIdc() + "--" +
						// hongXunBomTree.getBomMaterial() + ":" +
						// hongXunBomTree.getBomLocation());
						if (listError == null) {
							listError = new ArrayList<HongXunBomTree>();
						}
						hongXunBomTree.setAttribute("A03焊接");
						listError.add(hongXunBomTree);
						// Map<String,Object> map = new
						// HashMap<String,Object>();
						// map.put("error", "A03焊接分解时出现问题");
						// list.add(map);
						// return list;
					}

				}
				// }

			}
			if (listWeldMaterialCuting != null || listWeldPlateCuting != null || listWeldComponent != null
					|| listWeldWeldComponent != null) {
				// list3 = new ArrayList<Map<String,Object>>();
				Map<String, Object> map = new HashMap<String, Object>();
				if (listWeldComponent != null) {
					map.put("list3_0", listWeldComponent);
				}
				if (listWeldMaterialCuting != null) {
					/*
					 * ArrayList<Map<String, Object>> listMap = new
					 * ArrayList<Map<String,Object>>(); for(HongXunBomTree
					 * hongXunBomTree: listWeldMaterialCuting){
					 * Map<String,Object> map1 = new HashMap<String,Object>();
					 * mapHongXunBomTree(map1,hongXunBomTree);
					 * listMap.add(map1); }
					 */
					List<Map<String, Object>> list = invertMap(listWeldMaterialCuting);
					if (list.size() > 0) {
						map.put("list3_1", list);
					}
				}
				if (listWeldPlateCuting != null) {
					List<Map<String, Object>> list = invertMap(listWeldPlateCuting);
					if (list.size() > 0) {
						map.put("list3_2", list);
					}
				}
				/*
				 * if(listWeldWeldComponent != null){ map.put("list3_3",
				 * invertMap(listWeldWeldComponent)); }
				 */
				if (listWeldPurchase != null) {
					List<Map<String, Object>> list = invertMap(listWeldPurchase);
					if (list.size() > 0) {
						map.put("list3_4", list);
					}
				}
				list3.add(map);
			}
		}
	}

	private List<Map<String, Object>> invertMap(List<HongXunBomTree> listHongXunBomTree) {
		List<String> listStr = new ArrayList<String>();
		List<HongXunBomTree> list = new ArrayList<HongXunBomTree>();
		int index = 0;
		HongXunSpecialMaterialNum hongXunSpecialMaterialNum = new HongXunSpecialMaterialNum();
		// hongXunSpecialMaterialNum.setLocaltion("顶层");
		List<HongXunSpecialMaterialNum> hongXunSpecialMaterialNums = iSpecialMaterialDao.quary(hongXunSpecialMaterialNum);
		List<String> set = new ArrayList<String>();
		for (HongXunSpecialMaterialNum item : hongXunSpecialMaterialNums) {
			// System.out.println("num:" + item.getMaterialNum());
			set.add(item.getMaterialNum());
		}
		// System.out.println("set:" + set.size());
		for (HongXunBomTree hongXunBomTree : listHongXunBomTree) {
			String str = hongXunBomTree.getBomMaterialNum()
			/*
			 * +hongXunBomTree.getBomSpacification()
			 */;
			index = listStr.indexOf(str);
			if (index > -1) {
				if (set.contains(hongXunBomTree.getBomMaterialNum())) {
					// System.out.println("bomtree" +
					// hongXunBomTree.getBomMaterialNum());
					listStr.add(str);
					list.add(hongXunBomTree);
				} else {
					list.get(index).setBomItemQuantity(
							list.get(index).getBomItemQuantity() + hongXunBomTree.getBomItemQuantity());
				}
			} else {
				listStr.add(str);
				list.add(hongXunBomTree);
			}
		}
		ArrayList<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (HongXunBomTree hongXunBomTree : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			mapHongXunBomTree(map, hongXunBomTree);
			listMap.add(map);
		}
		return listMap;
	}

	private void b00Assamble(List<HongXunBomTree> listAssemble, List<Map<String, Object>> list4,
			List<HongXunBomTree> listError, List<HongXunBomTree> listPurchase) {
		List<Map<String, Object>> listAssAssemble = null;
		List<HongXunBomTree> listAssMaterialCuting = new ArrayList<HongXunBomTree>();
		List<HongXunBomTree> listAssPlateCuting = new ArrayList<HongXunBomTree>();
		List<HongXunBomTree> listAssComponent = new ArrayList<HongXunBomTree>();
		List<HongXunBomTree> listAssPurchase = new ArrayList<HongXunBomTree>();
		List<HongXunBomTree> listAssWeiwai = new ArrayList<HongXunBomTree>();
		List<HongXunBomTree> listAssPainting = new ArrayList<HongXunBomTree>();
		if (listAssemble != null) {
			for (HongXunBomTree item : listAssemble) {
				// if(item.getBomGroup().equals("B00装配清单汇总")){

				if (listAssAssemble == null) {
					listAssAssemble = new ArrayList<Map<String, Object>>();
				}
				Map<String, Object> mapTop = new HashMap<String, Object>();
				// hongXunBomTree.setBomQuantity(hongXunBomTree.getBomQuantity()
				// * item.getBomQuantity());
				mapHongXunBomTree(mapTop, item);
				listAssAssemble.add(mapTop);
				if (item.getBomMaterialCuting() != null && item.getBomMaterialCuting().equals("下料")) {
					listAssMaterialCuting.add(item.clone());
				}
				if (item.getBomPlateCuting() != null && item.getBomPlateCuting().equals("钣金")) {
					listAssPlateCuting.add(item.clone());
				}
				if (item.getBomMaterialWeld() != null
						&& (item.getBomMaterialWeld().equals("焊接") || item.getBomMaterialWeld().equals("焊接.1"))) {
					listAssComponent.add(item.clone());
				}
				if (item.getAttribute() != null && item.getAttribute().equals("外购件")) {
					// listAssPurchase.add(item.clone());
					listPurchase.add(item.clone());
				}
				if (item.getBomWeiwai() != null) {
					listAssWeiwai.add(item.clone());
				}
				if (item.getBomPainting() != null) {
					listAssPainting.add(item.clone());
				}
				@SuppressWarnings("unchecked")
				List<HongXunBomTree> hongXunBomTrees = (List<HongXunBomTree>) stockDao.quarywithpara("HongXunBomTree",
						"topID", item.getNodeID());
				// System.out.println("装配ndoeID: " + item.getNodeID() + "
				// size(): " + hongXunBomTrees.size());

				for (HongXunBomTree hongXunBomTree : hongXunBomTrees) {

					hongXunBomTree.setBomDeliveryDate(item.getBomDeliveryDate());
					hongXunBomTree.setBomItemQuantity(hongXunBomTree.getBomQuantity() * item.getBomItemQuantity());
					Map<String, Object> map0 = new HashMap<String, Object>();
					// hongXunBomTree.setBomQuantity(hongXunBomTree.getBomQuantity()
					// * item.getBomQuantity());
					mapHongXunBomTree(map0, hongXunBomTree);
					listAssAssemble.add(map0);
					if (hongXunBomTree.getBomMaterialCuting() != null
							&& hongXunBomTree.getBomMaterialCuting().equals("下料")) {
						listAssMaterialCuting.add(hongXunBomTree.clone());
					}
					if (hongXunBomTree.getBomPlateCuting() != null && hongXunBomTree.getBomPlateCuting().equals("钣金")) {
						listAssPlateCuting.add(hongXunBomTree.clone());

					}
					if (hongXunBomTree.getBomMaterialWeld() != null && (hongXunBomTree.getBomMaterialWeld().equals("焊接")
							|| hongXunBomTree.getBomMaterialWeld().equals("焊接.1"))) {
						listAssComponent.add(hongXunBomTree.clone());
					}

					/*
					 * if(hongXunBomTree.getBomAssemble()!=null &&
					 * hongXunBomTree.getBomAssemble().equals("装配.1")){
					 * if(listAssAssemble == null){ listAssAssemble = new
					 * ArrayList<Map<String,Object>>(); } Map<String,Object> map
					 * = new HashMap<String,Object>();
					 * //hongXunBomTree.setBomQuantity(hongXunBomTree.
					 * getBomQuantity() * item.getBomQuantity());
					 * mapHongXunBomTree(map,hongXunBomTree);
					 * listAssAssemble.add(map);
					 * 
					 * String sql = "from HongXunBomTree where parentID=?";
					 * 
					 * @SuppressWarnings("unchecked") List<HongXunBomTree>
					 * tmpHongXunBomTrees =
					 * (List<HongXunBomTree>)stockDao.getList(sql,String.valueOf
					 * (hongXunBomTree.getIdc())); for(HongXunBomTree
					 * tmpHongXunBomTree: tmpHongXunBomTrees){
					 * Map<String,Object> tmpMap = new HashMap<String,Object>();
					 * tmpHongXunBomTree.setBomItemQuantity(tmpHongXunBomTree.
					 * getBomQuantity() * hongXunBomTree.getBomItemQuantity());
					 * mapHongXunBomTree(tmpMap,tmpHongXunBomTree);
					 * listAssAssemble.add(tmpMap); } }
					 */
					if (hongXunBomTree.getAttribute() != null && hongXunBomTree.getAttribute().equals("外购件")) {
						// listAssPurchase.add(hongXunBomTree.clone());
						listPurchase.add(hongXunBomTree.clone());
					}
					if (hongXunBomTree.getBomWeiwai() != null) {
						listAssWeiwai.add(hongXunBomTree.clone());
					}
					if (hongXunBomTree.getBomPainting() != null) {
						listAssPainting.add(hongXunBomTree.clone());
					}
					if (!((hongXunBomTree.getBomMaterialCuting() != null
							&& hongXunBomTree.getBomMaterialCuting().equals("下料"))
							|| (hongXunBomTree.getBomPlateCuting() != null
									&& hongXunBomTree.getBomPlateCuting().equals("钣金"))
							|| (hongXunBomTree.getBomMaterialWeld() != null
									&& (hongXunBomTree.getBomMaterialWeld().equals("焊接")
											|| hongXunBomTree.getBomMaterialWeld().equals("焊接.1")))
							|| (hongXunBomTree.getAttribute() != null && hongXunBomTree.getAttribute().equals("外购件"))
							|| (hongXunBomTree.getBomWeiwai() != null) || (hongXunBomTree.getBomPainting() != null)
							|| (hongXunBomTree.getBomAssemble() != null
									&& hongXunBomTree.getBomAssemble().equals("装配.1"))
							|| (hongXunBomTree.getBomAssemble() != null
									&& hongXunBomTree.getBomAssemble().equals("装配")))
							|| (hongXunBomTree.getRemark() != null
									&& hongXunBomTree.getRemark().indexOf("整体外购") > -1)) {
						if (listError == null) {
							listError = new ArrayList<HongXunBomTree>();
						}
						hongXunBomTree.setAttribute("装配");
						listError.add(hongXunBomTree);
						// System.out.println("装配分解时出现问题" + item.getIdc() + ":"
						// + item.getBomSpacification() + ":" +
						// hongXunBomTree.getIdc() + "--" +
						// hongXunBomTree.getBomMaterial() + ":" +
						// hongXunBomTree.getBomLocation());
						// Map<String,Object> map = new
						// HashMap<String,Object>();
						// map.put("error", "装配分解时出现问题");
						// list.add(map);
						// return list;
					}

				}
				// }
			}
			if (listAssAssemble != null || listAssMaterialCuting != null || listAssPlateCuting != null
					|| listAssComponent != null || listAssPainting != null) {
				// list4 = new ArrayList<Map<String,Object>>();
				Map<String, Object> map = new HashMap<String, Object>();
				/*
				 * if(listAssAll != null){ map.put("list4_0", listAssAll); }
				 */

				if (listAssMaterialCuting != null) {
					List<Map<String, Object>> list = invertMap(listAssMaterialCuting);
					if (list.size() > 0) {
						map.put("list4_1", list);
					}
				}
				if (listAssPlateCuting != null) {
					List<Map<String, Object>> list = invertMap(listAssPlateCuting);
					if (list.size() > 0) {
						map.put("list4_2", list);
					}
				}
				if (listAssComponent != null) {
					List<Map<String, Object>> list = invertMap(listAssComponent);
					if (list.size() > 0) {
						map.put("list4_3", list);
					}
				}
				if (listAssAssemble != null) {
					map.put("list4_4", listAssAssemble);
				}
				if (listAssPurchase != null) {
					List<Map<String, Object>> list = invertMap(listAssPurchase);
					if (list.size() > 0) {
						map.put("list4_5", list);
					}
				}
				if (listAssWeiwai != null) {
					List<Map<String, Object>> list = invertMap(listAssWeiwai);
					if (list.size() > 0) {
						map.put("list4_6", list);
					}
				}
				if (listAssPainting != null) {
					List<Map<String, Object>> list = invertMap(listAssPainting);
					if (list.size() > 0) {
						map.put("list4_7", list);
					}
				}
				list4.add(map);
			}
		}
	}

	private void assamble732(List<HongXunBomTree> list732, List<Map<String, Object>> list5,
			List<HongXunBomTree> listError, List<HongXunBomTree> listPurchase) {
		List<Map<String, Object>> list732Assemble = null;
		List<HongXunBomTree> list732MaterialCuting = new ArrayList<HongXunBomTree>();
		List<HongXunBomTree> list732PlateCuting = new ArrayList<HongXunBomTree>();
		List<HongXunBomTree> list732Component = new ArrayList<HongXunBomTree>();
		List<HongXunBomTree> list732Purchase = new ArrayList<HongXunBomTree>();
		List<HongXunBomTree> list732Weiwai = new ArrayList<HongXunBomTree>();
		List<HongXunBomTree> list732Painting = new ArrayList<HongXunBomTree>();

		if (list732 != null) {
			for (HongXunBomTree item : list732) {
				if (list732Assemble == null) {
					list732Assemble = new ArrayList<Map<String, Object>>();
				}
				Map<String, Object> mapTop = new HashMap<String, Object>();
				mapHongXunBomTree(mapTop, item);
				list732Assemble.add(mapTop);

				if (item.getBomMaterialCuting() != null && item.getBomMaterialCuting().equals("下料")) {
					list732MaterialCuting.add(item.clone());
				}
				if (item.getBomPlateCuting() != null && item.getBomPlateCuting().equals("钣金")) {
					list732PlateCuting.add(item.clone());
				}
				if (item.getBomMaterialWeld() != null
						&& (item.getBomMaterialWeld().equals("焊接") || item.getBomMaterialWeld().equals("焊接.1"))) {
					list732Component.add(item.clone());
				}
				if (item.getAttribute() != null && item.getAttribute().equals("外购件")) {
					// list732Purchase.add(item.clone());
					listPurchase.add(item.clone());
				}
				if (item.getBomWeiwai() != null) {
					list732Weiwai.add(item.clone());
				}
				if (item.getBomPainting() != null) {
					list732Painting.add(item.clone());
				}

				@SuppressWarnings("unchecked")
				List<HongXunBomTree> hongXunBomTrees = (List<HongXunBomTree>) stockDao.quarywithpara("HongXunBomTree",
						"topID", item.getNodeID());
				// System.out.println("732ndoeID: " + item.getNodeID() + "
				// size(): " + hongXunBomTrees.size());
				for (HongXunBomTree hongXunBomTree : hongXunBomTrees) {

					hongXunBomTree.setBomDeliveryDate(item.getBomDeliveryDate());
					hongXunBomTree.setBomItemQuantity(hongXunBomTree.getBomQuantity() * item.getBomItemQuantity());
					Map<String, Object> map0 = new HashMap<String, Object>();
					// hongXunBomTree.setBomQuantity(hongXunBomTree.getBomQuantity()
					// * item.getBomQuantity());
					mapHongXunBomTree(map0, hongXunBomTree);
					list732Assemble.add(map0);
					if (hongXunBomTree.getBomMaterialCuting() != null
							&& hongXunBomTree.getBomMaterialCuting().equals("下料")) {
						list732MaterialCuting.add(hongXunBomTree.clone());
					}
					if (hongXunBomTree.getBomPlateCuting() != null && hongXunBomTree.getBomPlateCuting().equals("钣金")) {
						list732PlateCuting.add(hongXunBomTree.clone());
					}
					if (hongXunBomTree.getBomMaterialWeld() != null && (hongXunBomTree.getBomMaterialWeld().equals("焊接")
							|| hongXunBomTree.getBomMaterialWeld().equals("焊接.1"))) {
						list732Component.add(hongXunBomTree.clone());
					}
					/*
					 * if(hongXunBomTree.getBomAssemble()!=null &&
					 * hongXunBomTree.getBomAssemble().equals("装配.1")){
					 * if(list732Assemble == null){ list732Assemble = new
					 * ArrayList<Map<String,Object>>(); } Map<String,Object> map
					 * = new HashMap<String,Object>();
					 * //hongXunBomTree.setBomQuantity(hongXunBomTree.
					 * getBomQuantity() * item.getBomQuantity());
					 * mapHongXunBomTree(map,hongXunBomTree);
					 * list732Assemble.add(map); String sql =
					 * "from HongXunBomTree where parentID=?";
					 * 
					 * @SuppressWarnings("unchecked") List<HongXunBomTree>
					 * tmpHongXunBomTrees =
					 * (List<HongXunBomTree>)stockDao.getList(sql,String.valueOf
					 * (hongXunBomTree.getIdc())); for(HongXunBomTree
					 * tmpHongXunBomTree: tmpHongXunBomTrees){
					 * Map<String,Object> tmpMap = new HashMap<String,Object>();
					 * tmpHongXunBomTree.setBomItemQuantity(tmpHongXunBomTree.
					 * getBomQuantity() * hongXunBomTree.getBomItemQuantity());
					 * mapHongXunBomTree(tmpMap,tmpHongXunBomTree);
					 * list732Assemble.add(tmpMap); } }
					 */
					if (hongXunBomTree.getAttribute() != null && hongXunBomTree.getAttribute().equals("外购件")) {
						// list732Component.add(hongXunBomTree.clone());
						listPurchase.add(hongXunBomTree.clone());
					}
					if (hongXunBomTree.getBomWeiwai() != null) {
						list732Weiwai.add(hongXunBomTree.clone());
					}
					if (hongXunBomTree.getBomPainting() != null) {
						list732Painting.add(hongXunBomTree.clone());
					}
					// System.out.println("732ndoeID: " + item.getNodeID());
					if (!((hongXunBomTree.getBomMaterialCuting() != null
							&& hongXunBomTree.getBomMaterialCuting().equals("下料"))
							|| (hongXunBomTree.getBomPlateCuting() != null
									&& hongXunBomTree.getBomPlateCuting().equals("钣金"))
							|| (hongXunBomTree.getBomMaterialWeld() != null
									&& (hongXunBomTree.getBomMaterialWeld().equals("焊接")
											|| hongXunBomTree.getBomMaterialWeld().equals("焊接.1")))
							|| (hongXunBomTree.getAttribute() != null && hongXunBomTree.getAttribute().equals("外购件"))
							|| (hongXunBomTree.getBomWeiwai() != null) || (hongXunBomTree.getBomPainting() != null)
							|| (hongXunBomTree.getBomAssemble() != null
									&& hongXunBomTree.getBomAssemble().equals("装配.1"))
							|| (hongXunBomTree.getBomAssemble() != null && hongXunBomTree.getBomAssemble().equals("装配"))
							|| (hongXunBomTree.getRemark() != null
									&& hongXunBomTree.getRemark().indexOf("整体外购") > -1))) {
						if (listError == null) {
							// System.out.println("asdfasdfd12354");
							listError = new ArrayList<HongXunBomTree>();
						}
						hongXunBomTree.setAttribute("732");
						listError.add(hongXunBomTree);
						// System.out.println("732分解时出现问题" + item.getIdc() + ":"
						// + item.getBomSpacification() + ":" +
						// hongXunBomTree.getIdc() + "--" +
						// hongXunBomTree.getBomMaterial() + ":" +
						// hongXunBomTree.getBomLocation());
						// Map<String,Object> map = new
						// HashMap<String,Object>();
						// map.put("error", "732分解时出现问题");
						// list.add(map);
						// return list;
					}
				}
				// }
			}
			// System.out.println("732分解完成1");
			if (list732MaterialCuting != null || list732PlateCuting != null || list732Component != null
					|| list732Assemble != null || list732Painting != null) {
				// list5 = new ArrayList<Map<String,Object>>();
				Map<String, Object> map = new HashMap<String, Object>();
				if (list732MaterialCuting != null) {
					List<Map<String, Object>> list = invertMap(list732MaterialCuting);
					if (list.size() > 0) {
						map.put("list5_1", list);
					}
				}
				if (list732PlateCuting != null) {
					List<Map<String, Object>> list = invertMap(list732PlateCuting);
					if (list.size() > 0) {
						map.put("list5_2", list);
					}
				}
				if (list732Component != null) {
					List<Map<String, Object>> list = invertMap(list732Component);
					if (list.size() > 0) {
						map.put("list5_3", list);
					}
				}
				if (list732Assemble != null) {
					map.put("list5_4", list732Assemble);
				}
				if (list732Purchase != null) {
					List<Map<String, Object>> list = invertMap(list732Purchase);
					if (list.size() > 0) {
						map.put("list5_5", list);
					}
				}
				if (list732Weiwai != null) {
					List<Map<String, Object>> list = invertMap(list732Weiwai);
					if (list.size() > 0) {
						map.put("list5_6", list);
					}
				}
				if (list732Painting != null) {
					List<Map<String, Object>> list = invertMap(list732Painting);
					if (list.size() > 0) {
						map.put("list5_7", list);
					}
				}
				list5.add(map);
			}
		}
		// System.out.println("732分解完成");
	}

}
