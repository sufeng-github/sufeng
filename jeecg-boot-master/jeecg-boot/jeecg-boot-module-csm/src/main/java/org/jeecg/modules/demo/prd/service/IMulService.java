package org.jeecg.modules.demo.prd.service;

import org.jeecg.modules.demo.prd.entity.Mul;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 多
 * @Author: jeecg-boot
 * @Date:   2019-10-16
 * @Version: V1.0
 */
public interface IMulService extends IService<Mul> {

	public List<Mul> selectByMainId(String mainId);
}
