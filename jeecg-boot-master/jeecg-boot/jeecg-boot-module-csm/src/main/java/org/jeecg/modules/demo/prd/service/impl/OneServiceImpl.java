package org.jeecg.modules.demo.prd.service.impl;

import org.jeecg.modules.demo.prd.entity.One;
import org.jeecg.modules.demo.prd.entity.Mul;
import org.jeecg.modules.demo.prd.mapper.MulMapper;
import org.jeecg.modules.demo.prd.mapper.OneMapper;
import org.jeecg.modules.demo.prd.service.IOneService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 一对多测试
 * @Author: jeecg-boot
 * @Date:   2019-10-16
 * @Version: V1.0
 */
@Service
public class OneServiceImpl extends ServiceImpl<OneMapper, One> implements IOneService {

	@Autowired
	private OneMapper oneMapper;
	@Autowired
	private MulMapper mulMapper;
	
	@Override
	@Transactional
	public void saveMain(One one, List<Mul> mulList) {
		oneMapper.insert(one);
		if(mulList!=null && mulList.size()>0) {
			for(Mul entity:mulList) {
				//外键设置
				entity.setOneId(one.getId());
				mulMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void updateMain(One one,List<Mul> mulList) {
		oneMapper.updateById(one);
		
		//1.先删除子表数据
		mulMapper.deleteByMainId(one.getId());
		
		//2.子表数据重新插入
		if(mulList!=null && mulList.size()>0) {
			for(Mul entity:mulList) {
				//外键设置
				entity.setOneId(one.getId());
				mulMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		mulMapper.deleteByMainId(id);
		oneMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			mulMapper.deleteByMainId(id.toString());
			oneMapper.deleteById(id);
		}
	}
	
}
