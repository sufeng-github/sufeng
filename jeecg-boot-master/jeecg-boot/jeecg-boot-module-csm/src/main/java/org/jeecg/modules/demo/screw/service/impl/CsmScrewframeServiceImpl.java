package org.jeecg.modules.demo.screw.service.impl;

import java.util.List;

import org.jeecg.common.constant.CacheConstant;
import org.jeecg.common.constant.CommonConstant;
import org.jeecg.modules.demo.screw.entity.CsmScrewframe;
import org.jeecg.modules.demo.screw.mapper.CsmScrewframeMapper;
import org.jeecg.modules.demo.screw.service.ICsmScrewframeService;
import org.jeecg.modules.demo.util.BaseTree;
import org.jeecg.modules.demo.util.BaseTreeUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: csm_screwframe
 * @Author: jeecg-boot
 * @Date:   2019-11-21
 * @Version: V1.0
 */
@Service
public class CsmScrewframeServiceImpl extends ServiceImpl<CsmScrewframeMapper, CsmScrewframe> implements ICsmScrewframeService {

	@Cacheable(value = CacheConstant.DEPART_INFO_CACHE)
	@Override
	public List<CsmScrewframe> queryTreeList() {
		LambdaQueryWrapper<CsmScrewframe> query = new LambdaQueryWrapper<CsmScrewframe>();
		query.eq(CsmScrewframe::getDelflag, CommonConstant.DEL_FLAG_0.toString());
		//query.orderByAsc(CsmScrewframe::getSort);
		List<CsmScrewframe> list = this.list(query);
		// 调用wrapTreeDataToTreeList方法生成树状数据
		//return BaseTreeUtils.getTreeModelList(list,false);
		return BaseTreeUtils.getScrewframeModelList(list);
		
	}
}
