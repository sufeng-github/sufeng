package paul.sydney.controller.others;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import paul.sydney.service.others.ServiceCabinInf;

@Controller 
@RequestMapping("/cabin.htm")
public class ControllerCabin {
    private final ServiceCabinInf service;
    @Autowired 
    public ControllerCabin(ServiceCabinInf service){
        this.service = service; 
    }
   
    @ResponseBody
   	@RequestMapping("/leftTopOne")
   	private List<Map<String,Object>> leftTopOne() {
    	List<Map<String,Object>> list = service.leftTopOne();
    	//System.out.println("loadData");
   		return list;
   	}    
    @ResponseBody
   	@RequestMapping("/leftTopTwo")
   	private List<Map<String,Object>> leftTopTwo() {
    	List<Map<String,Object>> list = service.leftTopTwo();
    	//System.out.println("loadData");
   		return list;
   	}    
    
    @ResponseBody
   	@RequestMapping("/leftTopThree")
   	private List<Map<String,Object>> leftTopThree() {
    	List<Map<String,Object>> list = service.leftTopThree();
    	//System.out.println("loadData");
   		return list;
   	} 
    
    @ResponseBody
   	@RequestMapping("/leftTopFour")
   	private List<Map<String,Object>> leftTopFour() {
    	List<Map<String,Object>> list = service.leftTopFour();
    	//System.out.println("loadData");
   		return list;
   	} 
    
    @ResponseBody
   	@RequestMapping("/rightPartOne")
   	private List<Map<String,Object>> rightPartOne() {
    	List<Map<String,Object>> list = service.rightPartOne();
    	//System.out.println("loadData");
   		return list;
   	} 
    
    @ResponseBody
   	@RequestMapping("/rightPartTwo")
   	private List<Map<String,Object>> rightPartTwo() {
    	List<Map<String,Object>> list = service.rightPartTwo();
    	//System.out.println("loadData");
   		return list;
   	} 
    
    @ResponseBody
   	@RequestMapping("/rightPartThree")
   	private List<Map<String,Object>> rightPartThree() {
    	List<Map<String,Object>> list = service.rightPartThree();
    	//System.out.println("loadData");
   		return list;
   	} 
    
    
    @ResponseBody
   	@RequestMapping("/rightPartFour")
   	private List<Map<String,Object>> rightPartFour() {
    	List<Map<String,Object>> list = service.rightPartFour();
    	//System.out.println("loadData");
   		return list;
   	} 
    
    @ResponseBody
   	@RequestMapping("/rightPartFive")
   	private List<Map<String,Object>> rightPartFive() {
    	List<Map<String,Object>> list = service.rightPartFive();
    	//System.out.println("loadData");
   		return list;
   	}
    
    @ResponseBody
   	@RequestMapping("/leftBottomOne")
   	private List<Map<String,Object>> leftBottomOne() {
    	List<Map<String,Object>> list = service.leftBottomOne();
    	//System.out.println("loadData");
   		return list;
   	} 
    
    @ResponseBody
   	@RequestMapping("/leftBottomTwo")
   	private List<Map<String,Object>> leftBottomTwo() {
    	List<Map<String,Object>> list = service.leftBottomTwo();
    	//System.out.println("loadData");
   		return list;
   	} 
    
    @ResponseBody
   	@RequestMapping("/leftBottomThree")
   	private List<Map<String,Object>> leftBottomThree() {
    	List<Map<String,Object>> list = service.leftBottomThree();
    	//System.out.println("loadData");
   		return list;
   	} 
}