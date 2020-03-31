package paul.sydney.service.login.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import paul.sydney.model.HongXunAuthority;
import paul.sydney.service.login.ServiceAuthorityInf;
import paul.sydney.dao.ILoginDao;
import paul.sydney.dao.StockDao;
@Transactional
@Service("serviceAuthority")
public class ServiceAuthorityImpl implements ServiceAuthorityInf {

	@Autowired
	StockDao stockDao;
	public void setStockDao(StockDao stockDao) {
		this.stockDao = stockDao;
	}
	
	@Autowired
	ILoginDao iLoginDao;
	public void setStockDao(ILoginDao iLoginDao) {
		this.iLoginDao = iLoginDao;
	}
	
	@Override
	public boolean login(String userName, String password) {
		// TODO Auto-generated method stub
		List<HongXunAuthority> hongXunAuthoritys = iLoginDao.quary(new HongXunAuthority());
		for(HongXunAuthority item: hongXunAuthoritys){
			if((item.getUserName().equals(userName)) && (item.getUserCode().equals(password))){
				//System.out.println("sadf");
				return true;
			}
		}
		return false;
	}

	@Override
	public List<Map<String, Object>> loadData() {
		// TODO Auto-generated method stub
		//System.out.println("serviceTenload");
		List<HongXunAuthority> hongXunAuthoritys = iLoginDao.quary(new HongXunAuthority());
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		//hongXunAuthoritys.remove(0);
		for (HongXunAuthority item : hongXunAuthoritys) {
			Map<String, Object> map = new HashMap<String, Object>();
			mapHongXunAuthority(map, item);	
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> save(HongXunAuthority hongXunAuthority) {
		// TODO Auto-generated method stub
		// System.out.println("serviceoneloadSaveRow");
		iLoginDao.saveEntity(hongXunAuthority);
		Map<String, Object> map = new HashMap<String, Object>();
		//map.put("idc", hongXunPoN.getIdc());
		mapHongXunAuthority(map, hongXunAuthority);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list.add(map);
		return list;
	}
	
	@Override
	public List<Map<String, Object>> getMessage(int id) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		HongXunAuthority hongXunAuthority = iLoginDao.get(id);
		Map<String, Object> map = new HashMap<String, Object>();
		mapHongXunAuthority(map, hongXunAuthority);
		list.add(map);
		return list;
		
	}
	
	@Override
	public List<Map<String, Object>> update(HongXunAuthority hongXunAuthority) {
		// TODO Auto-generated method stub
		iLoginDao.updateEntity(hongXunAuthority);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		mapHongXunAuthority(map, hongXunAuthority);
		list.add(map);
		return list;
	}
	
	@Override
	public List<Map<String, Object>> deleteRow(int id) {	
		HongXunAuthority hongXunAuthority = iLoginDao.get(id);
		iLoginDao.delete(hongXunAuthority);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		return list;
	}
	
	private void mapHongXunAuthority(Map<String, Object> map, HongXunAuthority item) {
		// TODO Auto-generated method stub
		map.put("idc", item.getIdc());
		map.put("userName", item.getUserName());
		map.put("userCode", item.getUserCode());
		map.put("position", item.getPosition());
		map.put("stateValue", item.getStateValue());
		map.put("stateValue1", item.getStateValue1());
		map.put("stateValue2", item.getStateValue2());
		map.put("AuthorityDsc", item.getAuthorityDsc());
	}

}
