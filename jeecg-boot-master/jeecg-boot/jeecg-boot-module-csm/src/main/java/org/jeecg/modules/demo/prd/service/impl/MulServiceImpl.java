package org.jeecg.modules.demo.prd.service.impl;

import org.jeecg.modules.demo.prd.entity.Mul;
import org.jeecg.modules.demo.prd.mapper.MulMapper;
import org.jeecg.modules.demo.prd.service.IMulService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 多
 * @Author: jeecg-boot
 * @Date:   2019-10-16
 * @Version: V1.0
 */
@Service
public class MulServiceImpl extends ServiceImpl<MulMapper, Mul> implements IMulService {
	
	@Autowired
	private MulMapper mulMapper;
	
	@Override
	public List<Mul> selectByMainId(String mainId) {
		return mulMapper.selectByMainId(mainId);
	}
}
