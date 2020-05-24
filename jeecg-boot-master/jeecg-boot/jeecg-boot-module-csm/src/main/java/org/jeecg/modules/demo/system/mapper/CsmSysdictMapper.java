package org.jeecg.modules.demo.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.formula.functions.T;
import org.jeecg.common.system.vo.DictModel;
import org.jeecg.modules.demo.bom.entity.CsmBom;

/**
 * @Description: systemDict
 * @Author: jeecg-boot
 * @Date:   2019-11-14
 * @Version: V1.0
 */
public interface CsmSysdictMapper{

	public List<DictModel> queryDictItemsByCode(@Param("code") String code);

	public List<String> queryForeignKeys(@Param("DB")String DB, @Param("fk")String fk);

	public List<CsmBom> findTListByIds(@Param("DB")String DB, @Param("ids") List<String> ids);
	
}
