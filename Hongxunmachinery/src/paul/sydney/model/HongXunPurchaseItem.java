package paul.sydney.model;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



	public class HongXunPurchaseItem implements java.io.Serializable{

		private static final long serialVersionUID = 1L;

		private	Date	deliveryDate;
		private	Integer	materialRealQuantity;	
		private Integer materialPurchaseQuantity;
		private	BigDecimal	price;
		private	BigDecimal	materialMoney;
		private String  supplier;
		private	String	materialRemark;
		private String  materialState;
		private Integer arrivalQuantity;
		private HongXunPurchaseDeItem hongXunPurchaseDeItem;
		
		private HongXunPurchaseNum hongXunPurchaseNum;
		
		private	int	idc;
		//private int numId;

		public HongXunPurchaseDeItem getHongXunPurchaseDeItem() {
			return hongXunPurchaseDeItem;
		}

		@JsonIgnore
		public HongXunPurchaseNum getHongXunPurchaseNum() {
			return hongXunPurchaseNum;
		}
		public void setHongXunPurchaseNum(HongXunPurchaseNum hongXunPurchaseNum) {
			this.hongXunPurchaseNum = hongXunPurchaseNum;
		}
		public void setHongXunPurchaseDeItem(HongXunPurchaseDeItem hongXunPurchaseDeItem) {
			this.hongXunPurchaseDeItem = hongXunPurchaseDeItem;
		}
		public Date getDeliveryDate() {
			return deliveryDate;
		}
		public void setDeliveryDate(Date deliveryDate) {
			this.deliveryDate = deliveryDate;
		}
		public Integer getArrivalQuantity() {
			return arrivalQuantity;
		}
		public void setArrivalQuantity(Integer arrivalQuantity) {
			this.arrivalQuantity = arrivalQuantity;
		}
		public Integer getMaterialRealQuantity() {
			return materialRealQuantity;
		}
		public void setMaterialRealQuantity(Integer materialRealQuantity) {
			this.materialRealQuantity = materialRealQuantity;
		}
		
		public String getMaterialRemark() {
			return materialRemark;
		}
		public void setMaterialRemark(String materialRemark) {
			this.materialRemark = materialRemark;
		}
		public BigDecimal getMaterialMoney() {
			return materialMoney;
		}
		public void setMaterialMoney(BigDecimal materialMoney) {
			this.materialMoney = materialMoney;
		}

		public String getSupplier() {
			return supplier;
		}
		public void setSupplier(String supplier) {
			this.supplier = supplier;
		}
		public String getMaterialState() {
			return materialState;
		}
		public void setMaterialState(String materialState) {
			this.materialState = materialState;
		}
		
		public BigDecimal getPrice() {
			return price;
		}
		public void setPrice(BigDecimal price) {
			this.price = price;
		}
		public int getIdc() {
			return idc;
		}
		public void setIdc(int idc) {
			this.idc = idc;
		}

		public Integer getMaterialPurchaseQuantity() {
			return materialPurchaseQuantity;
		}
		public void setMaterialPurchaseQuantity(Integer materialPurchaseQuantity) {
			this.materialPurchaseQuantity = materialPurchaseQuantity;
		}

/*		public Integer getPurchaseNumID() {
			return purchaseNumID;
		}
		public void setPurchaseNumID(Integer purchaseNumID) {
			this.purchaseNumID = purchaseNumID;
		}*/

	}

