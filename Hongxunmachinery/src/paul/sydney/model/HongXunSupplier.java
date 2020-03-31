package paul.sydney.model;

	public class HongXunSupplier implements java.io.Serializable{

		private static final long serialVersionUID = 1L;
		private	String 	supplierCode;
		private	String 	supplier;
		private	String	category;
		private	String	attribute;
		private	String	purCondition;
		private	String	period;
		private	String	address;
		private	String	people;
		private	String	phone;
		private String	remark;
		private	int idc;
		public String getSupplierCode() {
			return supplierCode;
		}
		public void setSupplierCode(String supplierCode) {
			this.supplierCode = supplierCode;
		}
		public String getSupplier() {
			return supplier;
		}
		public void setSupplier(String supplier) {
			this.supplier = supplier;
		}
		public String getCategory() {
			return category;
		}
		public void setCategory(String category) {
			this.category = category;
		}
		public String getAttribute() {
			return attribute;
		}
		public void setAttribute(String attribute) {
			this.attribute = attribute;
		}

		public String getPurCondition() {
			return purCondition;
		}
		public void setPurCondition(String purCondition) {
			this.purCondition = purCondition;
		}
		public String getPeriod() {
			return period;
		}
		public void setPeriod(String period) {
			this.period = period;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getPeople() {
			return people;
		}
		public void setPeople(String people) {
			this.people = people;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
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
		
	}

