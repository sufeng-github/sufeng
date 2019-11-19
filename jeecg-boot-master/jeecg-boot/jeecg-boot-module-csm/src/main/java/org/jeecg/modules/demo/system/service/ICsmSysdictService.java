package org.jeecg.modules.demo.system.service;

import java.util.List;
import org.jeecg.common.system.vo.DictModel;

/**
 * @Description: systemDict
 * @Author: jeecg-boot
 * @Date:   2019-11-14
 * @Version: V1.0
 */
public interface ICsmSysdictService {

	public List<DictModel> queryDictItemsByCode(String code);

}
