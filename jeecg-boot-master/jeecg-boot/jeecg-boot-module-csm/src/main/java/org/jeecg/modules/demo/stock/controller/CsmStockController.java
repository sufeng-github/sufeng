package org.jeecg.modules.demo.stock.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo.stock.entity.CsmStock;
import org.jeecg.modules.demo.stock.service.ICsmStockService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;

 /**
 * @Description: csm_stock
 * @Author: jeecg-boot
 * @Date:   2019-11-26
 * @Version: V1.0
 */
@RestController
@RequestMapping("/stock/csmStock")
@Slf4j
public class CsmStockController {
	@Autowired
	private ICsmStockService csmStockService;
	
	/**
	  * 分页列表查询
	 * @param csmStock
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<IPage<CsmStock>> queryPageList(CsmStock csmStock,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<CsmStock>> result = new Result<IPage<CsmStock>>();
		QueryWrapper<CsmStock> queryWrapper = QueryGenerator.initQueryWrapper(csmStock, req.getParameterMap());
		Page<CsmStock> page = new Page<CsmStock>(pageNo, pageSize);
		IPage<CsmStock> pageList = csmStockService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param csmStock
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<CsmStock> add(@RequestBody CsmStock csmStock) {
		Result<CsmStock> result = new Result<CsmStock>();
		try {
			csmStockService.save(csmStock);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param csmStock
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<CsmStock> edit(@RequestBody CsmStock csmStock) {
		Result<CsmStock> result = new Result<CsmStock>();
		CsmStock csmStockEntity = csmStockService.getById(csmStock.getId());
		if(csmStockEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = csmStockService.updateById(csmStock);
			//TODO 返回false说明什么？
			if(ok) {
				result.success("修改成功!");
			}
		}
		
		return result;
	}
	
	/**
	  *   通过id删除
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			csmStockService.removeById(id);
		} catch (Exception e) {
			log.error("删除失败",e.getMessage());
			return Result.error("删除失败!");
		}
		return Result.ok("删除成功!");
	}
	
	/**
	  *  批量删除
	 * @param ids
	 * @return
	 */
	@DeleteMapping(value = "/deleteBatch")
	public Result<CsmStock> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<CsmStock> result = new Result<CsmStock>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.csmStockService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/queryById")
	public Result<CsmStock> queryById(@RequestParam(name="id",required=true) String id) {
		Result<CsmStock> result = new Result<CsmStock>();
		CsmStock csmStock = csmStockService.getById(id);
		if(csmStock==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(csmStock);
			result.setSuccess(true);
		}
		return result;
	}

  /**
      * 导出excel
   *
   * @param request
   * @param response
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, CsmStock csmStock) {
      // Step.1 组装查询条件查询数据
      QueryWrapper<CsmStock> queryWrapper = QueryGenerator.initQueryWrapper(csmStock, request.getParameterMap());
      List<CsmStock> pageList = csmStockService.list(queryWrapper);
      // Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      // 过滤选中数据
      String selections = request.getParameter("selections");
      if(oConvertUtils.isEmpty(selections)) {
    	  mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
      }else {
    	  List<String> selectionList = Arrays.asList(selections.split(","));
    	  List<CsmStock> exportList = pageList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
    	  mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
      }
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "csm_stock列表");
      mv.addObject(NormalExcelConstants.CLASS, CsmStock.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("csm_stock列表数据", "导出人:Jeecg", "导出信息"));
      return mv;
  }

  /**
      * 通过excel导入数据
   *
   * @param request
   * @param response
   * @return
   */
  @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
  public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
      Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
      for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
          MultipartFile file = entity.getValue();// 获取上传文件对象
          ImportParams params = new ImportParams();
          params.setTitleRows(2);
          params.setHeadRows(1);
          params.setNeedSave(true);
          try {
              List<CsmStock> listCsmStocks = ExcelImportUtil.importExcel(file.getInputStream(), CsmStock.class, params);
              csmStockService.saveBatch(listCsmStocks);
              return Result.ok("文件导入成功！数据行数:" + listCsmStocks.size());
          } catch (Exception e) {
              log.error(e.getMessage(),e);
              return Result.error("文件导入失败:"+e.getMessage());
          } finally {
              try {
                  file.getInputStream().close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      }
      return Result.ok("文件导入失败！");
  }

}
