package paul.sydney.controller.login;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import paul.sydney.service.login.ServiceLoginInf;

@Controller 	
//@RequestMapping("/login.htm") 
public class ControllerLogin {
    private final ServiceLoginInf service;
    @Autowired 
    public ControllerLogin(ServiceLoginInf service){
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
					//if(userName.equals("admin")){	
						List<Integer> list = service.getAuthority(userName,password);
						System.out.println("a :" + list.get(0) + "   a1 :" + list.get(1));
						for(int i=0; i<3; i++){
							if(list.get(i)==null){
								list.set(i,0);
							}
						}
						int authority = list.get(0) | list.get(1);
						int authority1 = (list.get(0) ^ list.get(1)) & list.get(1);//(~ (~(list.get(0) ^ list.get(1))) & list.get(1)  )&list.get(1);
						int authority2 = list.get(2);
						System.out.println("authority :" + authority + "   authority1 :" + authority1);
						System.out.println("web.root:"+ System.getProperty("hongxunmachinery.root"));
						request.setAttribute("authority", authority);						
						request.setAttribute("authority1", authority1);
						request.setAttribute("authority2", authority2);
						request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);				
					/*}else{
						response.sendRedirect("index.jsp");
					}*/ 
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


    
/*    @RequestMapping(value="/saveFile",method=RequestMethod.POST)
    public String screworderimport(HttpServletRequest request) throws Exception {
    	System.out.println("asdfsaf");
        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(
        		request.getSession().getServletContext());
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
                	
                }                
            }
            return "success";
        }else{
        	return "error";
        } 
    }*/
}