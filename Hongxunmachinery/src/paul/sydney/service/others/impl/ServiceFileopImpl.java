package paul.sydney.service.others.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.LobHelper;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import paul.sydney.model.HongXunPurchaseNum;
import paul.sydney.service.others.ServiceFileopInf;
import paul.sydney.dao.StockDao;

import paul.sydney.model.HongXunPurchaseItem;
import paul.sydney.model.HongXunBomTree;
import paul.sydney.model.HongXunFile;


@Transactional
@Service("serviceFileOP")
public class ServiceFileopImpl implements ServiceFileopInf{
	
	@Autowired
	StockDao stockDao;
	public void setStockDao(StockDao stockDao) {
		this.stockDao = stockDao;
	}
/*	
	@Override
	public List<Map<String, Object>> saveFile(String filePath) throws IOException {
		// TODO Auto-generated method stub
        //得到session
		System.out.println("saveFile");
	
		Session session = stockDao.getSession();

        //得到LobHelper
        LobHelper lobHelper = session.getLobHelper();
        //得到图片的blob
        InputStream in = new FileInputStream(filePath);
        Blob blob = lobHelper.createBlob(in, in.available());
    
        //得到简介的clob
        Clob clob = lobHelper.createClob("这是一本书");
     
        HongXunFile hongXunFile = new HongXunFile();
        hongXunFile.setMyfile(blob);
        hongXunFile.setDescription(clob);
        //存进数据库
        session.beginTransaction();
        session.save(hongXunFile);
        session.getTransaction().commit();
        session.close();
		return null;

	}*/
	/*
	@Override
	public HongXunFile readFile(){
		// TODO Auto-generated method stub

    	Session session = stockDao.getSession(); 
    	//从数据库里取值
    	session.beginTransaction();
    	Query query = session.createQuery("from HongXunFile ");

    	@SuppressWarnings("unchecked")
		List<HongXunFile> result = query.list();
    	System.out.println(result.size());
    	HongXunFile hongXunFile = result.get(0);
    	//HongXunFile hongXunFile = (HongXunFile)session.get(HongXunFile.class,22);
    	session.getTransaction().commit();
    	session.close();
		return hongXunFile;
	}
*/
	
	@Override
	public int getFileId(String name) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<HongXunFile> hongXunFiles = (List<HongXunFile>) stockDao.quarywithpara("HongXunFile", "name", name);

    	if(hongXunFiles.size()>0){	
    		return hongXunFiles.get(0).getIdc();	
    	}else{
    		return 0;
    	}
	}
/*	@Override
	public HongXunFile readFile(String name) {
		// TODO Auto-generated method stub
		HongXunFile hongXunFile = null;
		@SuppressWarnings("unchecked")
		List<HongXunFile> hongXunFiles = (List<HongXunFile>) stockDao.quarywithpara("HongXunFile", "name", name);

    	if(hongXunFiles.size()>0){	
    		hongXunFile = hongXunFiles.get(0);	
    	}
		return hongXunFile;
	}*/
	
	@Override
	public HongXunFile readFile(int idc) {
		// TODO Auto-generated method stub
		HongXunFile hongXunFile = stockDao.fileFindById(idc);
		return hongXunFile;
	}
	
	@Override
	public Map<String, Object> saveFile(String name, byte[] bs, int parentId) {
		// TODO Auto-generated method stub
        //得到session
		//System.out.println("saveFile");
	
		Session session = stockDao.session();
        //System.out.println("1");
        //得到LobHelper
        LobHelper lobHelper = session.getLobHelper();
        //得到图片的blob
        //InputStream in = new FileInputStream(filePath);
        Blob blob = lobHelper.createBlob(bs);
    
        //得到简介的clob
        Clob clob = lobHelper.createClob("这是一本书");
     
        HongXunFile hongXunFile = new HongXunFile();
        hongXunFile.setMyfile(blob);
        hongXunFile.setDescription(clob);
        hongXunFile.setName(name);
        hongXunFile.setParentId(parentId);
        //存进数据库
        session.beginTransaction();
        session.save(hongXunFile);
        session.getTransaction().commit();
        session.close();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("id", hongXunFile.getIdc());
		map.put("parentId", hongXunFile.getParentId());
		map.put("name", hongXunFile.getName());
		return map;
	}

	@Override
	public List<Map<String, Object>> loadData() {
		// TODO Auto-generated method stub
		HongXunFile hongXunFile = new HongXunFile();
		List<HongXunFile> hongXunFiles = stockDao.quary(hongXunFile);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		//System.out.println("123");
		if(hongXunFiles.size()==0){
		//	System.out.println("456");
			hongXunFile.setName("工程图纸");
			stockDao.save(hongXunFile);
			Map<String,Object> map = new HashMap<String,Object>();
			mapHongXunFile(map, hongXunFile);
	   	   	list.add(map);
		}else{
			for(HongXunFile item : hongXunFiles){
				Map<String,Object> map = new HashMap<String,Object>();
				mapHongXunFile(map, item);
				//map.put("state","closed");
		   	   	list.add(map);	
			}
		}
		return list;
	}

	@Override
	public Boolean update(int id, String name) {
		// TODO Auto-generated method stub
		HongXunFile hongXunFile = stockDao.fileFindById(id);
		hongXunFile.setName(name);
		stockDao.update(hongXunFile);
		return true;
	}

	private void mapHongXunFile(Map<String, Object> map, HongXunFile item) {
		// TODO Auto-generated method stub			
		map.put("id", item.getIdc());
		map.put("parentId", item.getParentId());
		map.put("name", item.getName());
		

	}

	@Override
	public Boolean delete(int id) {
		// TODO Auto-generated method stub
		HongXunFile hongXunFile = stockDao.fileFindById(id);
		@SuppressWarnings("unchecked")
		List<HongXunFile> hongXunFiles = (List<HongXunFile>) stockDao.quarywithpara("HongXunFile", "parentId", id);
		for(HongXunFile item: hongXunFiles){
			stockDao.delete(item);
		}
		stockDao.delete(hongXunFile);
		return true;
		/*String hql = "id=" + id;
		for(HongXunFile item: hongXunFiles){
			hql =hql + " or id=" + item.getIdc();			
		}	
		Session session = stockDao.getSession();
		Query q = session.createQuery("delete from HongXunFile where "+hql);
		q.executeUpdate();
		session.close();
		*/
		
/*
		public void deleteByIds(final List<String> ids) {
//			获取类名--对应表单
			String pojoName = entityClass.getSimpleName();
//			hql语句
			final String hql = "DELETE "+pojoName+" WHERE UUID IN (:ids)";
			实现
			this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
//				实现HibernateCallback接口获得Hibernate Session的引用
				public Object doInHibernate(Session session) throws HibernateException {
					Query query = session.createSQLQuery(hql);
					query.setParameterList("ids", ids);
					return query.executeUpdate();
				}
			});
		}*/

		

/*		public void del(int[] selectFlag) {
			    //数组中封装的是ID的集合;
			        String hql = "";
			        for(int i=0;i<selectFlag.length;i++) {
			            if(i==0) {
			                hql = "id="+selectFlag[i];
			            } else {
			                hql =hql + " or id="+selectFlag[i];
			            }
			        }   
			        Session session= this.getSession();
			        Query q= session.createQuery("delete from User where "+hql);
			        q.executeUpdate();
			    }
*/
	}

	@Override
	public List<Map<String, Object>> add(int parentId) {
		// TODO Auto-generated method stub
		HongXunFile hongXunFile = new HongXunFile();
		hongXunFile.setName("undefined");
		hongXunFile.setParentId(parentId);
		stockDao.save(hongXunFile);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", hongXunFile.getIdc());
		map.put("parentId", hongXunFile.getParentId());
		map.put("name", "undefined");
		list.add(map);
		return list;
	}

	@Override
	public List<Map<String, Object>> wirteFile(MultipartFile[] files, int parentId) throws IOException {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		for(int i=0;i<files.length;i++){		
			//@SuppressWarnings("unchecked")
			//List<HongXunFile> hongXunFiles = (List<HongXunFile>) stockDao.quarywithpara("HongXunFile", "name", files[i].getOriginalFilename()); 
			//if(hongXunFiles.size()==0){
				if (!files[i].isEmpty()) {
					//System.out.println(files[i].getOriginalFilename());
		            byte[] bs= files[i].getBytes() ;
		            list.add(saveFile(files[i].getOriginalFilename(), bs, parentId));
		            //realPath="E:"+File.separator+uniqueName;//文件上传的路径这里为E盘
		            //files[i].transferTo(new File(realPath));   // 转存文件
		           
				}else{   				
					break;
				}
			//}	
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> autotimp(String str) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		@SuppressWarnings("unchecked")
		List<HongXunFile> hongXunFiles = (List<HongXunFile>) stockDao.quaryFuzzyWithpara("HongXunFile", "name", str); 
		for(HongXunFile item : hongXunFiles){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("id", item.getIdc());
			map.put("name", item.getName());
			list.add(map);
		};
		return list;
	}	

}
