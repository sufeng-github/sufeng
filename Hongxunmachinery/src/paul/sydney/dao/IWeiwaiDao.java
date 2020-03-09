package paul.sydney.dao;

import java.util.List;
import paul.sydney.model.HongXunWeiwaiItem;
import paul.sydney.model.HongXunWeiwaiNum;

public interface IWeiwaiDao {
	void save(HongXunWeiwaiNum hongXunDataFour);
	void update(HongXunWeiwaiNum hongXunDataFour);
	List<HongXunWeiwaiNum> quary(HongXunWeiwaiNum hongXunDataFour);
	HongXunWeiwaiNum weiwaiNumFindById(int iD);
	void delete(HongXunWeiwaiNum hongXunWeiwaiNum);
	List<HongXunWeiwaiItem> quary(HongXunWeiwaiItem hongXunDataFive);
	void save(HongXunWeiwaiItem hongXunDataFive);
	HongXunWeiwaiItem dataFiveFindById(int iD);
	void deletRow(HongXunWeiwaiItem hongXunDataFive);
	void deleteWeiwaiItemList(List<HongXunWeiwaiItem> hongXunWeiwaiItems);
	void deleteWeiwaiNumList(List<HongXunWeiwaiNum> hongXunWeiwaiNumList);
	void update(HongXunWeiwaiItem hongXunDataFive);
	
}
