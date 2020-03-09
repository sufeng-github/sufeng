package paul.sydney.controller.login;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import paul.sydney.model.HongXunAuthority;
import paul.sydney.service.login.ServiceAuthorityInf;

@Transactional
@Controller 
@RequestMapping("/authority.htm") 
public class ControllerAuthority {
    private final ServiceAuthorityInf service;
    @Autowired 
    public ControllerAuthority(ServiceAuthorityInf service){
        this.service = service; 
    }   
    
    @RequestMapping(value="login.do", method = RequestMethod.POST)
	@ResponseBody
	public void doLogin(HttpServletRequest request,HttpServletResponse response){
    	System.out.println("login");
		try {
			String userName=request.getParameter("userName");
			String password=request.getParameter("password");
			if(userName==null||userName.equals("")||password==null||password.equals("")){
				response.sendRedirect("index.jsp");
			}else{
				if(service.login(userName,password)){
					//request.setAttribute("user", userName);
					if(userName.equals("admin")){
						//request.getRequestDispatcher("/WEB-INF/jsp/admin.jsp").forward(request, response);
						request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);				
					}else{
						response.sendRedirect("index.jsp");
					} 
				}else{
					response.sendRedirect("index.jsp");
				}
			}		
		}
		catch (ServletException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    @ResponseBody
   	@RequestMapping("/loadData")
   	private List<Map<String,Object>> loadData() {
    	List<Map<String,Object>> list = service.loadData();
    	System.out.println("tenloadData");
   		return list;
   	}  
    
    @ResponseBody
   	@RequestMapping("/getMessage")
   	private List<Map<String,Object>> getMessage(@RequestParam(value = "idc", required = true) int id){
    	
		return service.getMessage(id);
    	
    }
    @ResponseBody
   	@RequestMapping("/saveRow")
   	private List<Map<String,Object>> saveRow(@RequestParam(value = "userName", required = true) String userName,
								   			@RequestParam(value = "userCode", required = true) String userCode,
								   			@RequestParam(value = "position", required = true) String position,
								   			@RequestParam(value = "stateValue", required = true) int stateValue,
								   			@RequestParam(value = "stateValue1", required = true) int stateValue1,
								   			@RequestParam(value = "stateValue2", required = true) int stateValue2,
								   			@RequestParam(value = "idc", required = true) int id) throws ParseException {
    	
    	HongXunAuthority hongXunAuthority = new HongXunAuthority();
    	hongXunAuthority.setIdc(id);
    	hongXunAuthority.setPosition(position);
    	hongXunAuthority.setStateValue(stateValue);
    	hongXunAuthority.setStateValue1(stateValue1);
    	hongXunAuthority.setStateValue2(stateValue2);
    	hongXunAuthority.setUserName(userName);
    	hongXunAuthority.setUserCode(userCode);
    	//hongXunAuthority.setAuthorityDsc("");
    	if(id==0){
    		service.save(hongXunAuthority);
    	}else{
    		service.update(hongXunAuthority);
    	}
    	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    	return list;
   	}
  
    @ResponseBody
   	@RequestMapping("/deleteRow")
    private List<Map<String,Object>> deleteRow(@RequestParam(value = "idc", required = true) int id){
   		System.out.println("deleteRow");
   		return service.deleteRow(id);
   	}
    
}