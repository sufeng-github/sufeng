package paul.sydney.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class HongXunPurchaseDeItem implements java.io.Serializable{

		private static final long serialVersionUID = 1L;
		private	Integer quantity;
		private	Date	deliveryDate;
		private String	supplier;
		private	String	status;
		private HongXunMaterialStock hongXunMaterialStock;
		private HongXunPurchaseDeNum hongXunPurchaseDeNum;
		private	int idc;
		
		@JsonIgnore
		public HongXunPurchaseDeNum getHongXunPurchaseDeNum() {
			return hongXunPurchaseDeNum;
		}
		public void setHongXunPurchaseDeNum(HongXunPurchaseDeNum hongXunPurchaseDeNum) {
			this.hongXunPurchaseDeNum = hongXunPurchaseDeNum;
		}
		public Integer getQuantity() {
			return quantity;
		}
		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}
		public Date getDeliveryDate() {
			return deliveryDate;
		}
		public void setDeliveryDate(Date deliveryDate) {
			this.deliveryDate = deliveryDate;
		}

		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public int getIdc() {
			return idc;
		}
		public void setIdc(int idc) {
			this.idc = idc;
		}
		public HongXunMaterialStock getHongXunMaterialStock() {
			return hongXunMaterialStock;
		}
		public void setHongXunMaterialStock(HongXunMaterialStock hongXunMaterialStock) {
			this.hongXunMaterialStock = hongXunMaterialStock;
		}

		public String getSupplier() {
			return supplier;
		}
		public void setSupplier(String supplier) {
			this.supplier = supplier;
		}
		
		
	}

