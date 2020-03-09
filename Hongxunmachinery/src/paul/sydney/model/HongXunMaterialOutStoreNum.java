package paul.sydney.model;

	public class HongXunMaterialOutStoreNum implements java.io.Serializable{

		private static final long serialVersionUID = 1L;
		private	String 	outStoreNum;
		private	String	workProcedure;
		private	Integer	quantity;		
		private	String	state;
		private	String	remark;
		private int idc;
		public String getOutStoreNum() {
			return outStoreNum;
		}
		public void setOutStoreNum(String outStoreNum) {
			this.outStoreNum = outStoreNum;
		}
		public String getWorkProcedure() {
			return workProcedure;
		}
		public void setWorkProcedure(String workProcedure) {
			this.workProcedure = workProcedure;
		}
		public Integer getQuantity() {
			return quantity;
		}
		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
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
		
	}

