package org.jeecg.modules.demo.screw.service;

import java.util.List;

import org.jeecg.modules.demo.screw.entity.CsmScrewframe;
import org.jeecg.modules.demo.util.BaseTree;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: csm_screwframe
 * @Author: jeecg-boot
 * @Date:   2019-11-21
 * @Version: V1.0
 */
public interface ICsmScrewframeService extends IService<CsmScrewframe> {

	List<CsmScrewframe> queryTreeList();

}
