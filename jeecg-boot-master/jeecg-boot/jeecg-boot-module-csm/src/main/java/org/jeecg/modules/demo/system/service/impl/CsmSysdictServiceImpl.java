package org.jeecg.modules.demo.system.service.impl;

import java.util.List;

import org.jeecg.common.constant.CacheConstant;
import org.jeecg.common.system.vo.DictModel;
import org.jeecg.modules.demo.system.mapper.CsmSysdictMapper;
import org.jeecg.modules.demo.system.service.ICsmSysdictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: systemDict
 * @Author: jeecg-boot
 * @Date:   2019-11-14
 * @Version: V1.0
 */
@Service
public class CsmSysdictServiceImpl implements ICsmSysdictService {


    @Autowired
    private CsmSysdictMapper csmSysdictMapper;

	/**
	 * 通过查询指定code 获取字典
	 * @param code
	 * @return
	 */
	@Override
	@Cacheable(value = CacheConstant.DICT_CACHE,key = "#code")
	public List<DictModel> queryDictItemsByCode(String code) {
		return csmSysdictMapper.queryDictItemsByCode(code);	
	}
	
}
