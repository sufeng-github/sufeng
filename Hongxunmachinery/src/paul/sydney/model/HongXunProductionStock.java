package paul.sydney.model;

	public class HongXunProductionStock implements java.io.Serializable{

		private static final long serialVersionUID = 1L;
		private	String 	materialNum;
		private	String	specification;
		private	Integer	quantity;		
		private String  unit;
		private	Integer	safeQuantity;
		private	Integer	calQuantity;
		private String 	alarm;
		private Integer itemQuantity;
		private Integer inRoadQuantity;
		private	String	localCode;
		private	String	name;
		private	String	remark;
		private	Integer inQuantity;
		private	Integer outQuantity;
		private Integer lastInQuantity;
		private Integer thisInQuantity;
		private HongXunAssembleCompilation hongXunAssembleCompilation;
		
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
		public Integer getSafeQuantity() {
			return safeQuantity;
		}
		public void setSafeQuantity(Integer safeQuantity) {
			this.safeQuantity = safeQuantity;
		}
		public String getLocalCode() {
			return localCode;
		}
		public void setLocalCode(String localCode) {
			this.localCode = localCode;
		}
		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}
		public Integer getCalQuantity() {
			return calQuantity;
		}
		public void setCalQuantity(Integer calQuantity) {
			this.calQuantity = calQuantity;
		}
		public Integer getItemQuantity() {
			return itemQuantity;
		}
		public void setItemQuantity(Integer itemQuantity) {
			this.itemQuantity = itemQuantity;
		}
		public Integer getInRoadQuantity() {
			return inRoadQuantity;
		}
		public void setInRoadQuantity(Integer inRoadQuantity) {
			this.inRoadQuantity = inRoadQuantity;
		}
		public String getAlarm() {
			return alarm;
		}
		public void setAlarm(String alarm) {
			this.alarm = alarm;
		}
		public Integer getInQuantity() {
			return inQuantity;
		}
		public void setInQuantity(Integer inQuantity) {
			this.inQuantity = inQuantity;
		}
		public Integer getOutQuantity() {
			return outQuantity;
		}
		public void setOutQuantity(Integer outQuantity) {
			this.outQuantity = outQuantity;
		}
		public HongXunAssembleCompilation getHongXunAssembleCompilation() {
			return hongXunAssembleCompilation;
		}
		public void setHongXunAssembleCompilation(HongXunAssembleCompilation hongXunAssembleCompilation) {
			this.hongXunAssembleCompilation = hongXunAssembleCompilation;
		}
		public Integer getLastInQuantity() {
			return lastInQuantity;
		}
		public void setLastInQuantity(Integer lastInQuantity) {
			this.lastInQuantity = lastInQuantity;
		}
		public Integer getThisInQuantity() {
			return thisInQuantity;
		}
		public void setThisInQuantity(Integer thisInQuantity) {
			this.thisInQuantity = thisInQuantity;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}

	}
