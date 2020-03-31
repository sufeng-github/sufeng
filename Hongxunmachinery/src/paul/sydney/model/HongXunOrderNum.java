package paul.sydney.model;


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class HongXunOrderNum extends StartEndDate implements java.io.Serializable{

		private static final long serialVersionUID = 1L;
		private int		idc;
		private String orderNum;
		private String orderQuantity;
		private Date poCreateDate;
		private BigDecimal orderAmount;
		private String status;
		private String remark;
		
		public int getIdc() {
			return idc;
		}
		public void setIdc(int idc) {
			this.idc = idc;
		}
		public String getOrderNum() {
			return orderNum;
		}
		public void setOrderNum(String orderNum) {
			this.orderNum = orderNum;
		}
		public String getOrderQuantity() {
			return orderQuantity;
		}
		public void setOrderQuantity(String orderQuantity) {
			this.orderQuantity = orderQuantity;
		}
		public BigDecimal getOrderAmount() {
			return orderAmount;
		}
		public void setOrderAmount(BigDecimal orderAmount) {
			this.orderAmount = orderAmount;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}
		public Date getPoCreateDate() {
			return poCreateDate;
		}
		public void setPoCreateDate(Date poCreateDate) {
			this.poCreateDate = poCreateDate;
		}

		
	}

