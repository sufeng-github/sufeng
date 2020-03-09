package paul.sydney.dao;

import java.util.List;

import paul.sydney.model.HongXunSupplier;
import paul.sydney.model.HongXunSuppliersPrice;

public interface ISuppliersDao {
	void save(HongXunSuppliersPrice hongXunSuppliersPrice);
	void update(HongXunSuppliersPrice hongXunSuppliersPrice);
	List<HongXunSuppliersPrice> quary(HongXunSuppliersPrice hongXunSuppliersPrice);
	void deletePriceList(List<HongXunSuppliersPrice> hongXunSuppliersPriceList);
	
	void save(HongXunSupplier hongXunSupplier);
	List<HongXunSupplier> quary(HongXunSupplier hongXunSupplier);
	void update(HongXunSupplier hongXunSupplier);
	HongXunSupplier hongXunSupplierFindById(int iD);
	void deletRow(HongXunSupplier hongXunSupplier);
}
