package paul.sydney.controller.bomtree;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import paul.sydney.model.HongXunBomTree;
import paul.sydney.model.HongXunPoN;
import paul.sydney.service.bomTree.ServiceBomTreeInf;



@Controller 
@RequestMapping("/bomTree.htm") 
public class ControllerBomTree {
    private final ServiceBomTreeInf service;
    @Autowired 
    public ControllerBomTree(ServiceBomTreeInf service){
        this.service = service; 
    }
    
    
    @ResponseBody
   	@RequestMapping("/loadData")
   	private List<Map<String,Object>> loadData(HttpServletRequest request,HttpServletResponse response) {  	
    	String id=request.getParameter("id");
    	String sql = "from HongXunBomTree where parentID=?";
    	List<Map<String,Object>> list;
    	if("".equals(id) || id == null){ 

    		list = service.getList(sql,"99999");//0    		
    		if(list.size()==0){
    			//System.out.println("createRootBom");
    			service.createRootBom(list);
    		}
    	}else{
    		//System.out.println("BomTreeloadData");
    		int nodeid = service.getNodeID(id);
    		list = service.getList(sql,String.valueOf(nodeid));
    		//System.out.println(list.size());
    	}
   		return list;
    	//return service.loadData();
    	
   	}   
    /*
    @ResponseBody
   	@RequestMapping("/getList")
   	private List<Map<String,Object>> getList(@RequestParam(value = "ID", required = true) int ID) {
    	System.out.println("getList");
    	List<Map<String,Object>> list = service.getList(ID); 
    	return list;
   	}  
    */
    @ResponseBody
   	@RequestMapping("/saveRow")
    private List<Map<String,Object>> saveRow(@ModelAttribute(value="bomtree") HongXunBomTree bomtree){
    	    

    	System.out.println("saveRow:bomtree ");
  
    	//HongXunBomTree hongXunBomTree = hongXunBomTreeFillItem(BOMT);
    	if(bomtree.getIdc()==0){
    		//bomtree.setParentID(bomtree.getParentID());
    		return service.saveRow(bomtree);		
    	}else{
    		//hongXunBomTree.setID(bomtree.getID());
    		//hongXunBomTree.setParentID(bomtree.getParentID());
    		return service.updateRow(bomtree);
    	}

   	}
    

/*	@ResponseBody
    @RequestMapping("/save")
    //@RequestMapping(method = RequestMethod.POST)  
    private List<Map<String,Object>> screwDataSave(@ModelAttribute(value="bomTree") HongXunBomTree hongXunBomTree){
    	System.out.println("bomTree");
    	//return null;
    	return service.saveRow(hongXunBomTree);
    }*/
					
    @ResponseBody
   	@RequestMapping("/deleteRow")
   	private List<Map<String,Object>> deleteRow(@ModelAttribute(value="bomtree") HongXunBomTree bomtree){
    	System.out.println("deletebomtree");
    	service.deleteRow(bomtree);
    	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
   		return list;
   	} 

    /*
    @ResponseBody
   	@RequestMapping("/getDataMsg")
    private List<Map<String,Object>> getDataMsg(@RequestParam(value = "materialNum", required = true) String materialNum){
    	System.out.println("materialNum: " + materialNum);
    	HongXunBomTree hongXunBomTree = new HongXunBomTree();
    	hongXunBomTree.setBomMaterial(materialNum);
    	List<Map<String,Object>> list = service.getDataMsg(hongXunBomTree);
		//List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		return list;
    }*/
    @ResponseBody
   	@RequestMapping("/analysis")
    private List<Map<String,Object>> analysis(@RequestParam(value = "orderNumID", required = true) Integer orderNumID){
    	//System.out.println("analysis");
    	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    	if(service.getTablesStatus("bomEnable")==false){
    		Map<String,Object> map = new HashMap<String,Object>();
			map.put("error", "bom已关闭，请联系BOM维护人员开启");
			//System.out.println("订单数量为空");
			list.add(map);
			return list;
    	}else if(service.getTablesStatus("bomModify")){
    		Map<String,Object> map = new HashMap<String,Object>();
			map.put("error", "bom正在维护请稍后操作");
			//System.out.println("订单数量为空");
			list.add(map);
			return list;
    	}else if(service.getTablesStatus("analysis")|| service.getTablesStatus("exportExcel")){
    		Map<String,Object> map = new HashMap<String,Object>();
			map.put("error", "其它定单正在分解请稍后");
			//System.out.println("订单数量为空");
			list.add(map);
			return list;   		
    	}else{	
	    	try{
	    		service.setTablesStatus("analysis",true);
	    		return service.analysis(orderNumID);	
	    	}catch(Exception e){
	    		throw e;	    		
	    	}finally{
	    		service.setTablesStatus("analysis",false);
	    	}
    	}
    }
    
    @ResponseBody
    @RequestMapping(value="/exlImport",method=RequestMethod.POST)
    public List<Map<String,Object>> exlImport(HttpServletRequest request){
    	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    	Map<String,Object> map = new HashMap<String,Object>();
    	if(service.getTablesStatus("analysis")){		
			map.put("error", "计划正在分解");			
			list.add(map);
			return list;
    		//return "plan is doing";
    	}else{
    		HSSFWorkbook workbook = null;
    		POIFSFileSystem fs = null;
    		try{
    			service.setTablesStatus("bomModify",true);
		    	String nodeID = request.getParameter("nodeID");
		    	if(nodeID == null || nodeID.equals("")){
		    		
					map.put("error", "请选中一个节点");			
					list.add(map);
					return list;
		    		//return "node is empty";
		    	}else{
			    	System.out.println("exlImport :" + nodeID);
			        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(request.getSession().getServletContext());
			        //检查form中是否有enctype="multipart/form-data"
			        if(multipartResolver.isMultipart(request)){
			            //将request变成多部分request
			            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
			           //获取multiRequest 中所有的文件名
			            Iterator<String> iter=multiRequest.getFileNames();        
			            while(iter.hasNext()){
			                MultipartFile file=multiRequest.getFile(iter.next());			       
			                if(file!=null){
			                	//InputStream is = file.getInputStream(); 
			                	//return service.excelImport(is,nodeID);
			                	fs = new POIFSFileSystem(file.getInputStream());
			                	workbook = new HSSFWorkbook(fs);
			                	return service.excelImport(workbook ,nodeID);
			                }                
			            }
			            map.put("error", "导入文件为空");			
		    			list.add(map);
		    			return list;
			        }else{
			        	map.put("error", "无效文件");			
		    			list.add(map);
		    			return list;
			        } 
		    	}
    		}catch(Exception e){
    			//return "bom import error";  				
    			e.printStackTrace();
    		}finally{
    			
    			try {
    				if(workbook != null){
    					System.out.println("workbook.close()");
    					workbook.close();
    				}
    				if(fs != null){
    					System.out.println("fs.close()");
    					fs.close();
    				}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}   				   			
    			service.setTablesStatus("bomModify",false);
    		}
    	}
    	map.put("error", "导入文件失败");			
		list.add(map);
		return list;
	  	
    }
    
    
    @ResponseBody
    @RequestMapping(value="/qrcodeImport",method=RequestMethod.POST)
    public List<Map<String,Object>> qrcodeImport(HttpServletRequest request) throws Exception {

	        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(request.getSession().getServletContext());
	        //检查form中是否有enctype="multipart/form-data"
	        if(multipartResolver.isMultipart(request)){
	            //将request变成多部分request
	            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
	           //获取multiRequest 中所有的文件名
	            Iterator<String> iter=multiRequest.getFileNames();        
	            while(iter.hasNext()){
	                MultipartFile file=multiRequest.getFile(iter.next());
	                if(file!=null){
	                	InputStream is = file.getInputStream(); 
	                	return service.qrcodeImport(is);	                	
	                }                
	            }           
	        }
	        return null;
    }
    
    @ResponseBody
    @RequestMapping(value="/barcodeImport",method=RequestMethod.POST)
    public List<Map<String,Object>> barcodeImport(HttpServletRequest request) throws Exception {

	        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(request.getSession().getServletContext());
	        //检查form中是否有enctype="multipart/form-data"
	        if(multipartResolver.isMultipart(request)){
	            //将request变成多部分request
	            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
	  
	           //获取multiRequest 中所有的文件名
	            Iterator<String> iter=multiRequest.getFileNames();        
	            while(iter.hasNext()){
	                MultipartFile file=multiRequest.getFile(iter.next());
	                String name = file.getOriginalFilename();
	                name=name.replace("工单信息", "");
	                name=name.replace(".xls", "");
	                if(file!=null){
	                	InputStream is = file.getInputStream();	                	
	                	//System.out.println("fileName :" + fileName + "-->" + file.getName());
	                	return service.barcodeImport(is, name);	                	
	                }                
	            }           
	        }
	        
/*	        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
	        if(multipartResolver.isMultipart(request)){  
	            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;  
	            Iterator<String> iter = multiRequest.getFileNames(); 
	            
	            while(iter.hasNext()){  
	                MultipartFile file = multiRequest.getFile(iter.next());  
	    
	                String name = file.getOriginalFilename();
	                name=name.replace("工单信息", "");
	                name=name.replace(".xls", "");
	                //System.out.println(name);
	                if(file!=null){
	                	InputStream is = file.getInputStream();	                	
	                	//System.out.println("fileName :" + fileName + "-->" + file.getName());
	                	return service.barcodeImport(is, name);	                	
	                }          
	            }  
	        } */ 
	        
	        return null;
    }
    
    @ResponseBody
    @RequestMapping(value="/sendBarcodeImport",method=RequestMethod.POST)
    public List<Map<String,Object>> sendBarcodeImport(HttpServletRequest request) throws Exception {

	        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(request.getSession().getServletContext());
	        //检查form中是否有enctype="multipart/form-data"
	        if(multipartResolver.isMultipart(request)){
	            //将request变成多部分request
	            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
	  
	           //获取multiRequest 中所有的文件名
	            Iterator<String> iter=multiRequest.getFileNames();        
	            while(iter.hasNext()){
	                MultipartFile file=multiRequest.getFile(iter.next());
	                String name = file.getOriginalFilename();
	                name=name.replace("工单信息", "");
	                name=name.replace(".xls", "");
	                if(file!=null){
	                	InputStream is = file.getInputStream();	                	
	                	//System.out.println("fileName :" + fileName + "-->" + file.getName());
	                	return service.sendbarcodeImport(is, name);	                	
	                }                
	            }           
	        }
	        
	        return null;
    }
    
    @ResponseBody
    @RequestMapping(value="/download",method=RequestMethod.GET)
    public void download(//@RequestParam(value = "fileName", required = true) String fileName,
    					HttpServletRequest request, HttpServletResponse response) {
    	//String fileName=request.getParameter("fileName");
    	//System.out.println("downLoad...." + fileName);
    	service.download(request, response);
    }
    
    @ResponseBody
   	@RequestMapping("/searchNum")
    private List<Map<String,Object>> searchBomNum(@RequestParam(value = "num", required = true) String num) {
    	System.out.println(num);
    	List<Map<String,Object>> list = service.searchBomNum(num);
    	return list;
   	} 
    
    @ResponseBody
   	@RequestMapping("/findParent")
    private List<Map<String,Object>> findParent(@RequestParam(value = "parentID", required = true) String parentID) {
    	System.out.println(parentID);
    	List<Map<String,Object>> list = service.findParent(parentID);
    	return list;
   	} 
    
/*    @ResponseBody
   	@RequestMapping("/searchAttribute")
    private List<Map<String,Object>> searchBomAttribute(@RequestParam(value = "attribute", required = true) String attribute) {
    	System.out.println(attribute);
    	List<Map<String,Object>> list = service.searchBomAttribute(attribute);
    	return list;
   	} */
    
    @ResponseBody
   	@RequestMapping("/autotimp")
   	public List<Map<String,Object>> autotimp(HttpServletRequest request) {
    	String q=request.getParameter("q");
    	//System.out.println("autotimp:" + q); 	
   		return service.autotimp(q);  
   	} 
    
    @ResponseBody
   	@RequestMapping("/onselect")
    private List<Map<String,Object>> searchNum(@RequestParam(value = "name", required = true) String materialNum){
    	System.out.println(materialNum);
    	List<Map<String,Object>> list = service.onselect(materialNum);
		return list;	
    }
    
    @ResponseBody
   	@RequestMapping("/searchPurchaseItem")
    private List<Map<String,Object>> searchPurchaseItem(@RequestParam(value = "name", required = true) String purchaseItem){
    	System.out.println(purchaseItem);
    	List<Map<String,Object>> list = service.searchPurchaseItem(purchaseItem);
		return list;
    	
    }
    
    
    @ResponseBody
   	@RequestMapping("/getBomItems")
   	private List<Map<String,Object>> getBomItems(@RequestBody List<HongXunBomTree> hongXunBomTreeList) {
    	List<Map<String, Object>> list = null;
    	for(HongXunBomTree item: hongXunBomTreeList){
        	String sql = "from HongXunBomTree where parentID=?";
        	if(list==null){
        		list = service.getList(sql,String.valueOf(item.getIdc()));
        	}else{
        		list.addAll(service.getList(sql,String.valueOf(item.getIdc())));
        	}
    	}
		return list;
    	
    }

    
	@ResponseBody
   	@RequestMapping("/analysisItem")
    private List<Map<String,Object>> analysisItem(@RequestParam(value = "name", required = true) String listItem){
    	return service.getAnalysisItem(listItem);
    	//System.out.println("analysisList");
		//return analysisList;
 
    }
    
	@ResponseBody
   	@RequestMapping("/bomEnable")
    private List<Map<String,Object>> bomEnable(@RequestParam(value = "bomEnable", required = true) int bomEnable){
		if(bomEnable == 0){
			service.setTablesStatus("bomEnable",false);
		}else{
			service.setTablesStatus("bomEnable",true);
		}
    	//System.out.println("analysisList");
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		return list;
    }

	@ResponseBody
   	@RequestMapping("/getBomEnable")
    private List<Map<String,Object>> getBomEnable(){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		if(service.getTablesStatus("bomEnable")){
			map.put("bomEnable", 1);
		}else{
			map.put("bomEnable", 0);
		}
		list.add(map);
    	//System.out.println("analysisList");
    	
		return list;
    }
	
	
	@RequestMapping("/oneKeyExportExcel")
    public void oneKeyExportExcel(HttpServletRequest request, HttpServletResponse response) {
		try{ 
			service.setTablesStatus("exportExcel",true);
			service.oneKeyExportExcel(request, response); 
    	}catch(Exception e){
    		throw e;
    	}finally{
    		service.setTablesStatus("exportExcel",false);
    	}
    	  
        return;
    }
    
	
    
}