package paul.sydney.model;

import java.util.Date;

public class HongXunWorkItem implements java.io.Serializable{

		private static final long serialVersionUID = 1L;
		private int	idc;
		/*private Date prodInDate;															
		private String prodStaff;*/
		private Integer quantity;
		private String remark;
		private String orderNum; 
		private String lineNum; 
		private String groupNum; 
		private String supplier;
		private String materialNum;
		private String specification;
		private String drawNum;
		private String unit;
		private String attribute;
		private String	bomMaterialCuting;
		private String	bomPlateCuting;
		private String	bomMaterialWeld;
		private String	bomWeiwai;
		private String	bomPainting;
		private String	bomAssemble;
		private String state;
		private Date workDeliveryDate;
		private int workNumID;
		private int orderNumID;
		public int getIdc() {
			return idc;
		}
		public void setIdc(int idc) {
			this.idc = idc;
		}
		public Integer getQuantity() {
			return quantity;
		}
		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}
		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}
		public String getOrderNum() {
			return orderNum;
		}
		public void setOrderNum(String orderNum) {
			this.orderNum = orderNum;
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
		public String getDrawNum() {
			return drawNum;
		}
		public void setDrawNum(String drawNum) {
			this.drawNum = drawNum;
		}	
		public String getLineNum() {
			return lineNum;
		}
		public void setLineNum(String lineNum) {
			this.lineNum = lineNum;
		}
		public String getGroupNum() {
			return groupNum;
		}
		public void setGroupNum(String groupNum) {
			this.groupNum = groupNum;
		}
		public String getUnit() {
			return unit;
		}
		public void setUnit(String unit) {
			this.unit = unit;
		}
		
		public String getSupplier() {
			return supplier;
		}
		public void setSupplier(String supplier) {
			this.supplier = supplier;
		}
		public int getWorkNumID() {
			return workNumID;
		}
		public void setWorkNumID(int workNumID) {
			this.workNumID = workNumID;
		}
		public int getOrderNumID() {
			return orderNumID;
		}
		public void setOrderNumID(int orderNumID) {
			this.orderNumID = orderNumID;
		}
		public String getAttribute() {
			return attribute;
		}
		public void setAttribute(String attribute) {
			this.attribute = attribute;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public String getBomMaterialCuting() {
			return bomMaterialCuting;
		}
		public void setBomMaterialCuting(String bomMaterialCuting) {
			this.bomMaterialCuting = bomMaterialCuting;
		}
		public String getBomPlateCuting() {
			return bomPlateCuting;
		}
		public void setBomPlateCuting(String bomPlateCuting) {
			this.bomPlateCuting = bomPlateCuting;
		}
		public String getBomMaterialWeld() {
			return bomMaterialWeld;
		}
		public void setBomMaterialWeld(String bomMaterialWeld) {
			this.bomMaterialWeld = bomMaterialWeld;
		}
		public String getBomWeiwai() {
			return bomWeiwai;
		}
		public void setBomWeiwai(String bomWeiwai) {
			this.bomWeiwai = bomWeiwai;
		}
		public String getBomPainting() {
			return bomPainting;
		}
		public void setBomPainting(String bomPainting) {
			this.bomPainting = bomPainting;
		}
		public String getBomAssemble() {
			return bomAssemble;
		}
		public void setBomAssemble(String bomAssemble) {
			this.bomAssemble = bomAssemble;
		}
		public Date getWorkDeliveryDate() {
			return workDeliveryDate;
		}
		public void setWorkDeliveryDate(Date workDeliveryDate) {
			this.workDeliveryDate = workDeliveryDate;
		}

}

