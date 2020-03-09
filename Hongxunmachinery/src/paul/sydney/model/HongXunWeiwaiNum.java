package paul.sydney.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class HongXunWeiwaiNum implements java.io.Serializable{

		private static final long serialVersionUID = 1L;
		private String weiwaiNum;
		private Integer weiwaiQuantity;
		private BigDecimal weiwaiMoney;
		private String weiwaiStaff;
		private String workNum;
		private Date weiwaiDate;
		private Date weiwaiDeliveryDate;
		private String supplier;
		private String weiwaiProcedure;
		private String state;
		private String remark;
		private int		idc;
		public String getWeiwaiNum() {
			return weiwaiNum;
		}
		public void setWeiwaiNum(String weiwaiNum) {
			this.weiwaiNum = weiwaiNum;
		}
		public Integer getWeiwaiQuantity() {
			return weiwaiQuantity;
		}
		public void setWeiwaiQuantity(Integer weiwaiQuantity) {
			this.weiwaiQuantity = weiwaiQuantity;
		}
		public BigDecimal getWeiwaiMoney() {
			return weiwaiMoney;
		}
		public void setWeiwaiMoney(BigDecimal weiwaiMoney) {
			this.weiwaiMoney = weiwaiMoney;
		}
		public String getWeiwaiStaff() {
			return weiwaiStaff;
		}
		public void setWeiwaiStaff(String weiwaiStaff) {
			this.weiwaiStaff = weiwaiStaff;
		}
		public Date getWeiwaiDate() {
			return weiwaiDate;
		}
		public void setWeiwaiDate(Date weiwaiDate) {
			this.weiwaiDate = weiwaiDate;
		}
		public Date getWeiwaiDeliveryDate() {
			return weiwaiDeliveryDate;
		}
		public void setWeiwaiDeliveryDate(Date weiwaiDeliveryDate) {
			this.weiwaiDeliveryDate = weiwaiDeliveryDate;
		}
		public String getSupplier() {
			return supplier;
		}
		public void setSupplier(String supplier) {
			this.supplier = supplier;
		}
		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}
		public int getIdc() {
			return idc;
		}
		public void setIdc(int idc) {
			this.idc = idc;
		}
		public String getWorkNum() {
			return workNum;
		}
		public void setWorkNum(String workNum) {
			this.workNum = workNum;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public String getWeiwaiProcedure() {
			return weiwaiProcedure;
		}
		public void setWeiwaiProcedure(String weiwaiProcedure) {
			this.weiwaiProcedure = weiwaiProcedure;
		}



	}

