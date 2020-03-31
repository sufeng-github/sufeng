package paul.sydney.model;

import java.util.Date;


	public class HongXunProductionItemOutStock implements java.io.Serializable{

		private static final long serialVersionUID = 2L;
		private	String	lotNum;
		private	Date	date;	
		private	String	remark;	
		private	Integer quantity;
		private	Integer poNID;
		private	int	idc;
		public String getLotNum() {
			return lotNum;
		}
		public void setLotNum(String lotNum) {
			this.lotNum = lotNum;
		}
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}
		public Integer getQuantity() {
			return quantity;
		}
		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}

		public Integer getPoNID() {
			return poNID;
		}
		public void setPoNID(Integer poNID) {
			this.poNID = poNID;
		}
		public int getIdc() {
			return idc;
		}
		public void setIdc(int idc) {
			this.idc = idc;
		}

	}

