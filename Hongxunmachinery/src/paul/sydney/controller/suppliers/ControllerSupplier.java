package paul.sydney.controller.suppliers;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;


import paul.sydney.model.HongXunSupplier;
import paul.sydney.service.suppliers.ServiceSupplierInf;

@Transactional
@Controller 
@RequestMapping("/supplier.htm") 
public class ControllerSupplier {
    private final ServiceSupplierInf service;
	//private BeansInf beans;
	
    @Autowired 
    public ControllerSupplier(ServiceSupplierInf service){
        this.service = service; 
    	//this.beans = beans;
    }
     
    @ResponseBody
   	@RequestMapping("/loadData")
   	private List<Map<String,Object>> loadData() {
    	//System.out.println("para: " + para);
    	
    	List<Map<String,Object>> list = service.loadData();
    	//System.out.println("branchOneloadData");
   		return list;
   	} 

    
    @ResponseBody
   	@RequestMapping("/saveRow")
    private List<Map<String,Object>> saveRow(@RequestBody List<HongXunSupplier> hongXunSupplierList) {    	
    	// TODO Auto-generated catch block
    	//System.out.println("saveRow: ");
    	List<Map<String,Object>> list = null;
    	for(HongXunSupplier item :hongXunSupplierList){
    		if(item.getIdc()==0){
    			if(list == null){
    				list = service.saveRow(item);
    			}else{
    				list.containsAll(service.saveRow(item));
    			}
    		}else{
    			if(list == null){
    				list = service.updateRow(item);
    			}else{
    				list.containsAll(service.updateRow(item));
    			}	
    		}
    	}
    	return list;
   	}
   
    @ResponseBody
   	@RequestMapping("/deleteRow")
    private List<Map<String,Object>> deleteRow(@RequestBody List<HongXunSupplier> hongXunSupplierList){ 
    	//System.out.println("deleteRow");
    	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    	for(HongXunSupplier item : hongXunSupplierList){
        	int ID = item.getIdc();
        	service.deleteRow(ID);
    	} 	
   		return list;
   	} 
    
    
    @ResponseBody
    @RequestMapping(value="/exlImport",method=RequestMethod.POST)
    public String exlImport(HttpServletRequest request) throws Exception {
    	//System.out.println("exlImport");
        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(request.getSession().getServletContext());
        //检查form中是否有enctype="multipart/form-data"
        if(multipartResolver.isMultipart(request)){
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
           //获取multiRequest 中所有的文件名
            Iterator<String> iter=multiRequest.getFileNames();        
            while(iter.hasNext()){
                MultipartFile file=multiRequest.getFile(iter.next());
                if(file!=null){
                	InputStream is = file.getInputStream(); 
                	if(service.excelImport(is)){
                		return "success";
                	}else{
                		return "false";
                	}     	
                }                
            }
            return "success";
        }else{
        	return "false";
        } 
    }
    

}