package org.jeecg.modules.demo.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo.screw.entity.CsmScrewframe;

import cn.hutool.core.util.ObjectUtil;

public class BaseTreeUtils {
	
	
	/**
	 * 将从数据库查询的dataList转化成前端的树形结构treeList
	 * *如isListShow为true，节点列表父节点值设置为空
	 * @param dataList
	 * @return List<BaseTree>
	 */
	public static List<BaseTree> getTreeModelList(List<? extends BaseTree> dataList,boolean isListShow){
		
		
		dataList = Collections.synchronizedList(dataList);
		if(isListShow){
			for(BaseTree data : dataList){
				data.setParentId(null);
			}
		}
		List<BaseTree> treeList = new ArrayList<BaseTree>();
		getTreeModelList(treeList, dataList, null);
		return treeList;
	}
	
	/**
	 * 将从数据库查询的dataList通过递归算法转化成前端的树形结构treeList
	 * 
	 * @param treeList
	 * @param dataList
	 * @param temp
	 */
	public static void getTreeModelList(List<BaseTree> treeList,List<? extends BaseTree> dataList,BaseTree temp) {
		if(temp!=null){
			temp.setIsLeaf(true);
		}
		BaseTree data = null;
		for(int i = 0 ; i < dataList.size(); i++){
			data = dataList.get(i);
			String tempPid = data.getParentId();
			if(temp==null && oConvertUtils.isEmpty(tempPid)) {
				if(ObjectUtil.isEmpty(data.getKey())){
					data.setKey(data.getId().toString());
				}
				if(ObjectUtil.isEmpty(data.getValue())){
					data.setValue(data.getId().toString());
				}
				if(ObjectUtil.isEmpty(data.getTitle())){
					data.setTitle(data.getName());
				}
				
				treeList.add(data);
				dataList.remove(data);
				i--;
				getTreeModelList(treeList, dataList, data);
			}else if(temp!=null && tempPid!=null && tempPid.equals(temp.getId())){
				if(ObjectUtil.isEmpty(data.getKey())){
					data.setKey(data.getId().toString());
				}
				if(ObjectUtil.isEmpty(data.getValue())){
					data.setValue(data.getId().toString());
				}
				if(ObjectUtil.isEmpty(data.getTitle())){
					data.setTitle(data.getName());
				}
				if(temp.getChildren()==null){
					temp.setChildren(new ArrayList<BaseTree>());
					temp.setIsLeaf(false);
				}
				temp.getChildren().add(data);
				dataList.remove(data);
				i--;
				getTreeModelList(treeList, dataList, data);
			}
			
		}
	}
	
	public static List<CsmScrewframe> getScrewframeModelList(List<CsmScrewframe> dataList){
		List<CsmScrewframe> treeList = new ArrayList<CsmScrewframe>();
		for(CsmScrewframe item: dataList) {
			item.setKey(item.getId().toString());
			item.setValue(item.getId().toString());
			item.setTitle(item.getName());
			treeList.add(item);
		}
		return treeList;
	}
	
	

}
