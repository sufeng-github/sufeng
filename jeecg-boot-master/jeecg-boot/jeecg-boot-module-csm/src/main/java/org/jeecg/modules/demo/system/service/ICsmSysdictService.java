package org.jeecg.modules.demo.system.service;

import java.util.List;

import org.apache.poi.ss.formula.functions.T;
import org.jeecg.common.system.vo.DictModel;
import org.jeecg.modules.demo.bom.entity.CsmBom;


/**
 * @Description: systemDict
 * @Author: jeecg-boot
 * @Date:   2019-11-14
 * @Version: V1.0
 */
public interface ICsmSysdictService {

	public List<DictModel> queryDictItemsByCode(String code);

	public List<String> queryForeignKeys(String dB, String fk);

	public List<CsmBom> findTListByIds(String string, List<String> ids);

}
