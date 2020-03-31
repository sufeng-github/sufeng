package paul.sydney.model;

import java.util.Date;
import java.util.Set;

	public class HongXunPurchaseDeNum extends StartEndDate implements java.io.Serializable{

		private static final long serialVersionUID = 1L;
		private	String 	purchaseDeNum;
		private	Date	purchaseDeDate;	
		private	String	purchaseDeStatus;
		private	String	purchaseDeRemark;
		private Set<HongXunPurchaseDeItem> hongXunPurchaseDeItems;//图书Set集合  
		private	int idc;

		public String getPurchaseDeNum() {
			return purchaseDeNum;
		}
		public void setPurchaseDeNum(String purchaseDeNum) {
			this.purchaseDeNum = purchaseDeNum;
		}
		public Date getPurchaseDeDate() {
			return purchaseDeDate;
		}
		public void setPurchaseDeDate(Date purchaseDeDate) {
			this.purchaseDeDate = purchaseDeDate;
		}
		public String getPurchaseDeStatus() {
			return purchaseDeStatus;
		}
		public void setPurchaseDeStatus(String purchaseDeStatus) {
			this.purchaseDeStatus = purchaseDeStatus;
		}
		public String getPurchaseDeRemark() {
			return purchaseDeRemark;
		}
		public void setPurchaseDeRemark(String purchaseDeRemark) {
			this.purchaseDeRemark = purchaseDeRemark;
		}
		public int getIdc() {
			return idc;
		}
		public void setIdc(int idc) {
			this.idc = idc;
		}
		public Set<HongXunPurchaseDeItem> getHongXunPurchaseDeItems() {
			return hongXunPurchaseDeItems;
		}
		public void setHongXunPurchaseDeItems(Set<HongXunPurchaseDeItem> hongXunPurchaseDeItems) {
			this.hongXunPurchaseDeItems = hongXunPurchaseDeItems;
		}
		
	}

