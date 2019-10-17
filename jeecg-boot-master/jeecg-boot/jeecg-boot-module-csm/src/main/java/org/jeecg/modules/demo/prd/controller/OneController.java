package org.jeecg.modules.demo.prd.controller;

import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo.prd.entity.Mul;
import org.jeecg.modules.demo.prd.entity.One;
import org.jeecg.modules.demo.prd.vo.OnePage;
import org.jeecg.modules.demo.prd.service.IOneService;
import org.jeecg.modules.demo.prd.service.IMulService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSON;

 /**
 * @Description: 一对多测试
 * @Author: jeecg-boot
 * @Date:   2019-10-16
 * @Version: V1.0
 */
@RestController
@RequestMapping("/prd/one")
@Slf4j
public class OneController {
	@Autowired
	private IOneService oneService;
	@Autowired
	private IMulService mulService;
	
	/**
	  * 分页列表查询
	 * @param one
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<IPage<One>> queryPageList(One one,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<One>> result = new Result<IPage<One>>();
		QueryWrapper<One> queryWrapper = QueryGenerator.initQueryWrapper(one, req.getParameterMap());
		Page<One> page = new Page<One>(pageNo, pageSize);
		IPage<One> pageList = oneService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param onePage
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<One> add(@RequestBody OnePage onePage) {
		Result<One> result = new Result<One>();
		try {
			One one = new One();
			BeanUtils.copyProperties(onePage, one);
			
			oneService.saveMain(one, onePage.getMulList());
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param onePage
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<One> edit(@RequestBody OnePage onePage) {
		Result<One> result = new Result<One>();
		One one = new One();
		BeanUtils.copyProperties(onePage, one);
		One oneEntity = oneService.getById(one.getId());
		if(oneEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = oneService.updateById(one);
			oneService.updateMain(one, onePage.getMulList());
			result.success("修改成功!");
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
			oneService.delMain(id);
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
	public Result<One> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<One> result = new Result<One>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.oneService.delBatchMain(Arrays.asList(ids.split(",")));
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
	public Result<One> queryById(@RequestParam(name="id",required=true) String id) {
		Result<One> result = new Result<One>();
		One one = oneService.getById(id);
		if(one==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(one);
			result.setSuccess(true);
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/queryMulByMainId")
	public Result<List<Mul>> queryMulListByMainId(@RequestParam(name="id",required=true) String id) {
		Result<List<Mul>> result = new Result<List<Mul>>();
		List<Mul> mulList = mulService.selectByMainId(id);
		result.setResult(mulList);
		result.setSuccess(true);
		return result;
	}

  /**
      * 导出excel
   *
   * @param request
   * @param response
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, One one) {
      // Step.1 组装查询条件查询数据
      QueryWrapper<One> queryWrapper = QueryGenerator.initQueryWrapper(one, request.getParameterMap());
      List<One> queryList = oneService.list(queryWrapper);
      // 过滤选中数据
      String selections = request.getParameter("selections");
      List<One> oneList = new ArrayList<One>();
      if(oConvertUtils.isEmpty(selections)) {
    	  oneList = queryList;
      }else {
    	  List<String> selectionList = Arrays.asList(selections.split(","));
    	  oneList = queryList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
      }
	  // Step.2 组装pageList
      List<OnePage> pageList = new ArrayList<OnePage>();
      for (One main : oneList) {
          OnePage vo = new OnePage();
          BeanUtils.copyProperties(main, vo);
          List<Mul> mulList = mulService.selectByMainId(main.getId());
          vo.setMulList(mulList);
          pageList.add(vo);
      }
      // Step.3 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      mv.addObject(NormalExcelConstants.FILE_NAME, "一对多测试列表");
      mv.addObject(NormalExcelConstants.CLASS, OnePage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("一对多测试列表数据", "导出人:Jeecg", "导出信息"));
      mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
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
              List<OnePage> list = ExcelImportUtil.importExcel(file.getInputStream(), OnePage.class, params);
              for (OnePage page : list) {
                  One po = new One();
                  BeanUtils.copyProperties(page, po);
                  oneService.saveMain(po, page.getMulList());
              }
              return Result.ok("文件导入成功！数据行数:" + list.size());
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
