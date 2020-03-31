package paul.sydney.model;

import java.util.Date;

public class HongXunDeliveryWeldNum implements java.io.Serializable,Cloneable{
		
		private static final long serialVersionUID = 1L;	
		private String 	deliveryNum;
		private	String	customer;
		private	String	supplierCode;
		private Date  	date;		
		private	int	idc;
		private String	status;
		private String  remark;
		
		public String getDeliveryNum() {
			return deliveryNum;
		}
		public void setDeliveryNum(String deliveryNum) {
			this.deliveryNum = deliveryNum;
		}
		public String getCustomer() {
			return customer;
		}
		public void setCustomer(String customer) {
			this.customer = customer;
		}
		public String getSupplierCode() {
			return supplierCode;
		}
		public void setSupplierCode(String supplierCode) {
			this.supplierCode = supplierCode;
		}
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
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
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}	

		
	}

