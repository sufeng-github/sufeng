package org.jeecg.modules.demo.system.controller;

import java.util.List;

import org.apache.poi.ss.formula.functions.T;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.vo.DictModel;
import org.jeecg.modules.demo.bom.entity.CsmBom;
import org.jeecg.modules.demo.stock.entity.CsmStock;
import org.jeecg.modules.demo.system.mapper.CsmSysdictMapper;
import org.jeecg.modules.demo.system.service.ICsmSysdictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

 /**
 * @Description: systemDict
 * @Author: jeecg-boot
 * @Date:   2019-11-14
 * @Version: V1.0
 */
@RestController
@RequestMapping("/system/csmSysdict")
@Slf4j
public class CsmSysdictController {
	@Autowired
	private ICsmSysdictService csmSysdictService;

  /**
	 * 获取字典数据
	 * @param dictCode 字典code
	 * @param dictCode 表名,文本字段,code字段  | 举例：sys_user,realname,id
	 * @return
	 */
	@RequestMapping(value = "/getDictItems/{dictCode}", method = RequestMethod.GET)
	public Result<List<DictModel>> getDictItems(@PathVariable String dictCode) {
		log.info(" dictCode : "+ dictCode);
		Result<List<DictModel>> result = new Result<List<DictModel>>();
		List<DictModel> ls = null;
		try {
			//if(dictCode.equals("saleCompany")) {
				ls = csmSysdictService.queryDictItemsByCode(dictCode);				
			//}
			result.setSuccess(true);
			result.setResult(ls);
			log.info(result.toString());
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
			return result;
		}

		return result;
	}
	  /**
		 * 获取字典数据
		 * @param dictCode 字典code
		 * @param dictCode 表名,文本字段,code字段  | 举例：sys_user,realname,id
		 * @return
		 */
		@RequestMapping(value = "/getForeignKeys/{DB}", method = RequestMethod.GET)
		public Result<List<CsmBom>> getForeignKeys(@PathVariable String DB,
											@RequestParam(name="fk",required=true) String fk) {
			//log.info(" DB : "+ DB);
			Result<List<CsmBom>> result = new Result<List<CsmBom>>();
			try {
				
				List<String> ids = csmSysdictService.queryForeignKeys(DB,"bom_id");
				//System.out.println("999999999:" + ids.get(0));
				List<CsmBom> list = csmSysdictService.findTListByIds("csm_bom",ids);
				//System.out.println("9999999998:" + list.get(0));
				result.setSuccess(true);
				result.setResult(list);
				log.info(result.toString());
			} catch (Exception e) {
				log.error(e.getMessage(),e);
				result.error500("操作失败");
				return result;
			}

			return result;
		}
	
}
