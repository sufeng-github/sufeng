package paul.sydney.model;

import java.util.Date;


	public class HongXunProductionNoLimitItemInStock implements java.io.Serializable{

		private static final long serialVersionUID = 1L;
		private	String	lotNum;
		private	Date	date;	
		private	String	remark;	
		private	Integer quantity;
		private String materialNum;
		private String specification;
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
		public int getIdc() {
			return idc;
		}
		public void setIdc(int idc) {
			this.idc = idc;
		}
		public String getMaterialNum() {
			return materialNum;
		}
		public void setMaterialNum(String materialNum) {
			this.materialNum = materialNum;
		}
		public String getSpecification() {
			return specification;
		}
		public void setSpecification(String specification) {
			this.specification = specification;
		}

	}

