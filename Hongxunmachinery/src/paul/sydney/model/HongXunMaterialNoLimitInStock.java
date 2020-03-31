package paul.sydney.model;

import java.util.Date;


	public class HongXunMaterialNoLimitInStock implements java.io.Serializable{

		private static final long serialVersionUID = 1L;
		private	Date			date;		
		private	String			remark;		
		private	String			staff;	
		private	String			lotNum;	
		private	String			unit;	
		private	Integer 		quantity;
		private	int	idc;
		private Integer materialStockID;


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
		public String getStaff() {
			return staff;
		}
		public void setStaff(String staff) {
			this.staff = staff;
		}

		public Integer getQuantity() {
			return quantity;
		}
		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}

		public String getLotNum() {
			return lotNum;
		}
		public void setLotNum(String lotNum) {
			this.lotNum = lotNum;
		}
		public String getUnit() {
			return unit;
		}
		public void setUnit(String unit) {
			this.unit = unit;
		}
		public int getIdc() {
			return idc;
		}
		public void setIdc(int idc) {
			this.idc = idc;
		}
		public Integer getMaterialStockID() {
			return materialStockID;
		}
		public void setMaterialStockID(Integer materialStockID) {
			this.materialStockID = materialStockID;
		}



	
	}

