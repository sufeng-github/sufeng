package org.jeecg.modules.demo.prd.mapper;

import java.util.List;
import org.jeecg.modules.demo.prd.entity.Mul;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 多
 * @Author: jeecg-boot
 * @Date:   2019-10-16
 * @Version: V1.0
 */
public interface MulMapper extends BaseMapper<Mul> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<Mul> selectByMainId(@Param("mainId") String mainId);
}
