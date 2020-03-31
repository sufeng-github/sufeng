package paul.sydney.service.utils.impl;

import java.lang.reflect.Field;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import paul.sydney.model.HongXunPurchaseItem;
import paul.sydney.service.utils.IServiceUtilsInf;




@Transactional
@Service("ServiceUtilsImpl")
public class ServiceUtilsImpl implements IServiceUtilsInf{

	

    /** 
     * @param class1 用于赋值的实体类  
     * @param class2 需要待赋值的实体类
     * 描述：反射实体类赋值 
     */ 
	@Override
	public void reflectionAttr(Object class1,Object class2) throws Exception{  
    	 Class<? extends Object> clazz1 = class1.getClass();
         Class<? extends Object> clazz2 = class2.getClass();
		//  获取两个实体类的所有属性  
        Field[] fields1 = clazz1.getDeclaredFields();
        Field[] fields2 = clazz2.getDeclaredFields();  
		// 遍历class1Bean，获取逐个属性值，然后遍历class2Bean查找是否有相同的属性，如有相同则赋值  
        for (java.lang.reflect.Field f1 : fields1) {  
            if(f1.getName().equals("id")|| f1.getName().equals("hongXunMaterialStock"))  
                continue;
            //设置访问权限
            f1.setAccessible(true);
            Object value = f1.get(class1);	    
            for (Field f2 : fields2) {  
                if(f1.getName().equals(f2.getName())){ 
               		//设置访问权限
                    f2.setAccessible(true);
                    f2.set(class2,value);	         
                }  
            }  
        }    
    }  
	@Override
	public String print(double num1, double num2)
	{	
		 if(num2==0){
			 return "0";
		 }
		 double ratio = num1 / num2;
		 // 创建一个数值格式化对象  
		 NumberFormat format = NumberFormat.getPercentInstance();
		 //设置保留几位小数
		 format.setMaximumFractionDigits(2);
		 String result=format.format(ratio);
		 return result;		 
    }
	
      
}
