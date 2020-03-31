package paul.sydney.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


	public class HongXunPurchaseItemInStock implements java.io.Serializable{

		private static final long serialVersionUID = 1L;

		private	String	lotNum;
		private	Date	instoreDate;	
		private	String	remark;	
		private	Integer realQuantity;
		private HongXunPurchaseItem hongXunPurchaseItem; 
		private	int	idc;
		private Integer purchaseItemID;


		public HongXunPurchaseItem getHongXunPurchaseItem() {
			return hongXunPurchaseItem;
		}
		public void setHongXunPurchaseItem(HongXunPurchaseItem hongXunPurchaseItem) {
			this.hongXunPurchaseItem = hongXunPurchaseItem;
		}
		public Date getInstoreDate() {
			return instoreDate;
		}
		public String getLotNum() {
			return lotNum;
		}
		public void setLotNum(String lotNum) {
			this.lotNum = lotNum;
		}
		public void setInstoreDate(Date instoreDate) {
			this.instoreDate = instoreDate;
		}
		public Integer getRealQuantity() {
			return realQuantity;
		}
		public void setRealQuantity(Integer realQuantity) {
			this.realQuantity = realQuantity;
		}
		public int getIdc() {
			return idc;
		}
		public void setIdc(int idc) {
			this.idc = idc;
		}
		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}
		public Integer getPurchaseItemID() {
			return purchaseItemID;
		}
		public void setPurchaseItemID(Integer purchaseItemID) {
			this.purchaseItemID = purchaseItemID;
		}

	}

