package org.jeecg.modules.demo.util;

import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;

import lombok.Data;

/**
  * 树形基础bean
 */
@Data
public class BaseTree  {
	
	private String id;
	@TableField(exist = false)
	private String parentId;
	@TableField(exist = false)
	private String name;
	@TableField(exist = false)
	private String key;
	@TableField(exist = false)
	private String value;
	@TableField(exist = false)
	private String title;
	@TableField(exist = false)
	private String icon;
	@TableField(exist = false)
	private String label;
	@TableField(exist = false)
	private Boolean isLeaf;
	@TableField(exist = false)
	private List<BaseTree> children;

	
}
