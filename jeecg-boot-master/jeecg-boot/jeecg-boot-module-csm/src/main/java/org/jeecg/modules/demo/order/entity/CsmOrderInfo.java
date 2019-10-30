package org.jeecg.modules.demo.order.entity;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;

/**
 * @Description: csm_order_info
 * @Author: jeecg-boot
 * @Date:   2019-10-30
 * @Version: V1.0
 */
@Data
@TableName("csm_order_info")
public class CsmOrderInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**id*/
	@TableId(type = IdType.ID_WORKER_STR)
	private java.lang.String id;
	/**手术类型*/
	@Excel(name = "手术类型", width = 15)
	private java.lang.String opeId;
	/**日期*/
	@Excel(name = "日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date date;
	/**工具箱*/
	@Excel(name = "工具箱", width = 15)
	private java.lang.String toolId;
	/**螺钉架*/
	@Excel(name = "螺钉架", width = 15)
	private java.lang.String scrId;
	/**送货员*/
	@Excel(name = "送货员", width = 15)
	private java.lang.String deliverymanId;
	/**跟台员*/
	@Excel(name = "跟台员", width = 15)
	private java.lang.String followerId;
	/**销售员*/
	@Excel(name = "销售员", width = 15)
	private java.lang.String salerId;
	/**医院*/
	@Excel(name = "医院", width = 15)
	private java.lang.String hospitalId;
	/**患者*/
	@Excel(name = "患者", width = 15)
	private java.lang.String patient;
	/**编号*/
	@Excel(name = "编号", width = 15)
	private java.lang.String code;
	/**病床号*/
	@Excel(name = "病床号", width = 15)
	private java.lang.String bednum;
	/**销售公司*/
	@Excel(name = "销售公司", width = 15)
	private java.lang.String companyId;
	/**取货员*/
	@Excel(name = "取货员", width = 15)
	private java.lang.String fetcherId;
	/**客户*/
	@Excel(name = "客户", width = 15)
	private java.lang.String customerId;
	/**状态*/
	@Excel(name = "状态", width = 15)
	private java.lang.String status;
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
