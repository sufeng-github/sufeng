package org.jeecg.modules.demo.screw.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

import org.jeecg.modules.demo.util.BaseTree;

/**
 * @Description: csm_screwframe
 * @Author: jeecg-boot
 * @Date:   2019-11-21
 * @Version: V1.0
 */
@Data
@EqualsAndHashCode(callSuper=true)
@TableName("csm_screwframe")
public class CsmScrewframe extends BaseTree implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**id*/
	@TableId(type = IdType.ID_WORKER_STR)
	private java.lang.String id;
	/**名称*/
	@Excel(name = "名称", width = 15)
	private java.lang.String name;
	/**编码*/
	@Excel(name = "编码", width = 15)
	private java.lang.String code;
	/**状态*/
	@Excel(name = "状态", width = 15)
	private java.lang.String status;
	/**去向*/
	@Excel(name = "去向", width = 15)
	private java.lang.String located;
	/**顺序*/
	@Excel(name = "顺序", width = 15)
	private java.lang.String screworder;
	/**删除标致*/
	@Excel(name = "删除标致", width = 15)
	private java.lang.String delflag;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
	private java.lang.String createBy;
	/**创建日期*/
	@Excel(name = "创建日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date createTime;
	/**更新人*/
	@Excel(name = "更新人", width = 15)
	private java.lang.String updateBy;
	/**更新日期*/
	@Excel(name = "更新日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date updateTime;
	/**所属部门*/
	@Excel(name = "所属部门", width = 15)
	private java.lang.String sysOrgCode;
}
