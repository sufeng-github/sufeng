package paul.sydney.dao;

import java.util.List;
import org.hibernate.Session;
import paul.sydney.model.HongXunSpecialMaterialNum;
import paul.sydney.model.HongXunTablesStatus;
import paul.sydney.model.HongXunFile;
import paul.sydney.model.HongXunOnlineStock;


public interface StockDao {

//	Stock quaryFindById(int id);
	Session session();
	Session getSession();

	
	List<?> quarywithpara(String para);
	List<?> quarywithpara(String string, String string2, String string3);
	List<?> quarywithpara(String string, String string2, int value);
	List<?> quarywithpara(String string, String string2, String string3, int value);

	List<?> getList(String sql, String str);


	List<HongXunFile> quary(HongXunFile hongXunFile);
	HongXunFile fileFindById(int id);
	void save(HongXunFile hongXunFile);
	void delete(HongXunFile hongXunFile);
	void update(HongXunFile hongXunFile);
	List<?> quary(String sql);

	List<?> quaryFuzzyWithpara(String string, String string2, String attribute);

	HongXunTablesStatus tablesStatusFindById(int i);

	void update(HongXunTablesStatus hongXunTablesStatus);

	List<HongXunTablesStatus> quary(HongXunTablesStatus hongXunTablesStatus);

	void save(HongXunTablesStatus hongXunTablesStatus);

	List<HongXunSpecialMaterialNum> quary(HongXunSpecialMaterialNum hongXunSpecialMaterialNum);

	void save(HongXunSpecialMaterialNum hongXunSpecialMaterialNum);

	void update(HongXunSpecialMaterialNum hongXunSpecialMaterialNum);

	HongXunSpecialMaterialNum hongXunSpecialFindById(int iD);

	void deletRow(HongXunSpecialMaterialNum hongXunSpecialMaterialNum);



	void save(HongXunOnlineStock hongXunOnlineStock);

	List<HongXunOnlineStock> quary(HongXunOnlineStock hongXunOnlineStock);

	void deleteOnlineItemList(List<HongXunOnlineStock> hongXunOnlineStocks);

	void update(HongXunOnlineStock hongXunOnlineStock);













}
