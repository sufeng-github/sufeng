package paul.sydney.model;

	public class HongXunOnlineStock implements java.io.Serializable{

		private static final long serialVersionUID = 1L;
		private	String 	materialNum;
		private	String	specification;
		private	Integer	quantity;		
		/*private String  unit;
		private	Integer	safeQuantity;
		private	Integer	calQuantity;
		private String 	alarm;
		private Integer itemQuantity;
		private Integer inRoadQuantity;
		private	String	localCode;*/
		private	String	remark;
		//private	BigDecimal	price;
		//private	BigDecimal	materialMoney;
		//private String  supplier;
		private int idc;
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
		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}		

	}

