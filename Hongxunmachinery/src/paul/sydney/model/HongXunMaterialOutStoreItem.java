package paul.sydney.model;

import java.util.Date;

public class HongXunMaterialOutStoreItem implements java.io.Serializable{

		private static final long serialVersionUID = 1L;
		private	String 	materialNum;
		private	String	attribute;
		private	String	specification;
		private	String	materialLotNum;
		private	Integer	storeOutPlanQuantity;	
		private	Integer	storeOutRealQuantity;	
		private String  unit;
		private	String	remark;
		private String  state;
		private String 	staff;
		private Date 	storeOutDate;
		private	int	idc;
		private Integer outStoreNumID;
		
		public String getMaterialNum() {
			return materialNum;
		}
		public void setMaterialNum(String materialNum) {
			this.materialNum = materialNum;
		}
		public String getAttribute() {
			return attribute;
		}
		public void setAttribute(String attribute) {
			this.attribute = attribute;
		}
		public String getSpecification() {
			return specification;
		}
		public void setSpecification(String specification) {
			this.specification = specification;
		}
		public String getMaterialLotNum() {
			return materialLotNum;
		}
		public void setMaterialLotNum(String materialLotNum) {
			this.materialLotNum = materialLotNum;
		}
		public Integer getStoreOutPlanQuantity() {
			return storeOutPlanQuantity;
		}
		public void setStoreOutPlanQuantity(Integer storeOutPlanQuantity) {
			this.storeOutPlanQuantity = storeOutPlanQuantity;
		}
		public Integer getStoreOutRealQuantity() {
			return storeOutRealQuantity;
		}
		public void setStoreOutRealQuantity(Integer storeOutRealQuantity) {
			this.storeOutRealQuantity = storeOutRealQuantity;
		}
		public String getUnit() {
			return unit;
		}
		public void setUnit(String unit) {
			this.unit = unit;
		}
		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public int getIdc() {
			return idc;
		}
		public void setIdc(int idc) {
			this.idc = idc;
		}
		public Integer getOutStoreNumID() {
			return outStoreNumID;
		}
		public void setOutStoreNumID(Integer outStoreNumID) {
			this.outStoreNumID = outStoreNumID;
		}
		public String getStaff() {
			return staff;
		}
		public void setStaff(String staff) {
			this.staff = staff;
		}
		public Date getStoreOutDate() {
			return storeOutDate;
		}
		public void setStoreOutDate(Date storeOutDate) {
			this.storeOutDate = storeOutDate;
		}

	}

