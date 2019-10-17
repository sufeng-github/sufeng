package org.jeecg.modules.demo.prd.service;

import org.jeecg.modules.demo.prd.entity.Mul;
import org.jeecg.modules.demo.prd.entity.One;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 一对多测试
 * @Author: jeecg-boot
 * @Date:   2019-10-16
 * @Version: V1.0
 */
public interface IOneService extends IService<One> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(One one,List<Mul> mulList) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(One one,List<Mul> mulList);
	
	/**
	 * 删除一对多
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);
	
}
