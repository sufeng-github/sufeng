package org.jeecg.modules.demo.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecg.common.system.vo.DictModel;

/**
 * @Description: systemDict
 * @Author: jeecg-boot
 * @Date:   2019-11-14
 * @Version: V1.0
 */
public interface CsmSysdictMapper{

	public List<DictModel> queryDictItemsByCode(@Param("code") String code);
}
