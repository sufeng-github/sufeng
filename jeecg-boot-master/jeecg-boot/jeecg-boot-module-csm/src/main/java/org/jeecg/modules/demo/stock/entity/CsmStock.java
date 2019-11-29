package org.jeecg.modules.demo.stock.entity;

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
 * @Description: csm_stock
 * @Author: jeecg-boot
 * @Date:   2019-11-26
 * @Version: V1.0
 */
@Data
@TableName("csm_stock")
public class CsmStock implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**id*/
	@TableId(type = IdType.ID_WORKER_STR)
	private java.lang.String id;
	/**序列号*/
	@Excel(name = "序列号", width = 15)
	private java.lang.String serialnum;
	/**日期*/
	@Excel(name = "日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date date;
	/**数量*/
	@Excel(name = "数量", width = 15)
	private java.lang.Integer quantity;
	/**批号*/
	@Excel(name = "批号", width = 15)
	private java.lang.String lotnum;
	/**有效日期*/
	@Excel(name = "有效日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date valuedate;
	/**生产日期*/
	@Excel(name = "生产日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date pd;
	/**灭菌号*/
	@Excel(name = "灭菌号", width = 15)
	private java.lang.String mjn;
	/**安全库存*/
	@Excel(name = "安全库存", width = 15)
	private java.lang.Integer safequantity;
	/**采购详细id*/
	@Excel(name = "采购详细id", width = 15)
	private java.lang.String purchaseItemId;
	/**bomId*/
	@Excel(name = "bomId", width = 15)
	private java.lang.String bomId;
	/**销售id*/
	@Excel(name = "销售id", width = 15)
	private java.lang.String saleId;
	/**送货id*/
	@Excel(name = "送货id", width = 15)
	private java.lang.String deliveryId;
	/**备货id*/
	@Excel(name = "备货id", width = 15)
	private java.lang.String hospitalstockId;
	/**退换调库id*/
	@Excel(name = "退换调库id", width = 15)
	private java.lang.String beaId;
	/**调出库id*/
	@Excel(name = "调出库id", width = 15)
	private java.lang.String transferId;
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
