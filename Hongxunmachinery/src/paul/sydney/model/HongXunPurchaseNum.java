package paul.sydney.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


	public class HongXunPurchaseNum implements java.io.Serializable{

		private static final long serialVersionUID = 1L;
		private	String 	purchaseNum;
		private	String 	productionItemNum;
		private	String 	productionDes;
		private	Date	purchaseDate;	
		private	BigDecimal	purchaseAmount;
		private	Date	purchaseDeliveryDate;
		private	String	purchasingAgent;
		private	String	purchaseStatus;
		private	String	purchaseRemark;
		private	String	purchaseSupplier;
		private	String	purchaseQuantity;
		private	String	purchaseProcedure;
		private	int idc;
		//private Set<HongXunDataOneBranchOne> hongXunDataOneBranchOne = new HashSet<HongXunDataOneBranchOne>();


		public Date getPurchaseDate() {
			return purchaseDate;
		}
		public String getPurchaseNum() {
			return purchaseNum;
		}
		public void setPurchaseNum(String purchaseNum) {
			this.purchaseNum = purchaseNum;
		}
		public String getProductionItemNum() {
			return productionItemNum;
		}
		public void setProductionItemNum(String productionItemNum) {
			this.productionItemNum = productionItemNum;
		}
		public BigDecimal getPurchaseAmount() {
			return purchaseAmount;
		}
		public void setPurchaseAmount(BigDecimal purchaseAmount) {
			this.purchaseAmount = purchaseAmount;
		}
		public void setPurchaseDate(Date purchaseDate) {
			this.purchaseDate = purchaseDate;
		}
		public String getProductionDes() {
			return productionDes;
		}
		public void setProductionDes(String productionDes) {
			this.productionDes = productionDes;
		}
		public Date getPurchaseDeliveryDate() {
			return purchaseDeliveryDate;
		}
		public void setPurchaseDeliveryDate(Date purchaseDeliveryDate) {
			this.purchaseDeliveryDate = purchaseDeliveryDate;
		}
		public String getPurchasingAgent() {
			return purchasingAgent;
		}
		public void setPurchasingAgent(String purchasingAgent) {
			this.purchasingAgent = purchasingAgent;
		}
		public String getPurchaseStatus() {
			return purchaseStatus;
		}
		public void setPurchaseStatus(String purchaseStatus) {
			this.purchaseStatus = purchaseStatus;
		}
		public String getPurchaseRemark() {
			return purchaseRemark;
		}
		public void setPurchaseRemark(String purchaseRemark) {
			this.purchaseRemark = purchaseRemark;
		}
		public int getIdc() {
			return idc;
		}
		public void setIdc(int idc) {
			this.idc = idc;
		}
		public String getPurchaseSupplier() {
			return purchaseSupplier;
		}
		public void setPurchaseSupplier(String purchaseSupplier) {
			this.purchaseSupplier = purchaseSupplier;
		}
		public String getPurchaseQuantity() {
			return purchaseQuantity;
		}
		public void setPurchaseQuantity(String purchaseQuantity) {
			this.purchaseQuantity = purchaseQuantity;
		}
		public String getPurchaseProcedure() {
			return purchaseProcedure;
		}
		public void setPurchaseProcedure(String purchaseProcedure) {
			this.purchaseProcedure = purchaseProcedure;
		}

		
		}

