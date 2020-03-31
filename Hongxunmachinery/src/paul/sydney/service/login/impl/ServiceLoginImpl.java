package paul.sydney.service.login.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import paul.sydney.dao.ILoginDao;
import paul.sydney.dao.StockDao;
import paul.sydney.model.HongXunAuthority;
import paul.sydney.service.login.ServiceLoginInf;
@Transactional
@Service("serviceLogin")
public class ServiceLoginImpl implements ServiceLoginInf{
	
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
/*	@Override
	public boolean login(String userName, String password) {
		// TODO Auto-generated method stub
		List<Login> logins = stockDao.quary(new Login());
		for(Login item: logins){
			if((item.getName().equals(userName)) && (item.getCode().equals(password))){
				//System.out.println("sadf");
				return true;
			}
		}
		return false;
	}*/
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
	public List<Integer> getAuthority(String userName, String password) {
		// TODO Auto-generated method stub
		List<HongXunAuthority> hongXunAuthoritys = iLoginDao.quary(new HongXunAuthority());
		List<Integer> list = new ArrayList<Integer>();
		for(HongXunAuthority item: hongXunAuthoritys){
			if((item.getUserName().equals(userName)) && (item.getUserCode().equals(password))){
				//System.out.println("sadf");
				list.add(item.getStateValue());
				list.add(item.getStateValue1());
				list.add(item.getStateValue2());
				return list;
			}
		}
		return list;
	}
      
}
