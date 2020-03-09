package paul.sydney.model;


public class HongXunDeliveryItem implements java.io.Serializable,Cloneable{
		
		private static final long serialVersionUID = 1L;	
		private	String	pon;
		private	String	line;
		private String  materialNo;
		private String  materialDesc;
		private String  exemption;	
		private	String 	code;
		private String  unit;
		private	String	projectNum;
		private String	status;
		private String	remark;
		private Integer	sendQuantity;
		private Integer	choutQuantity;
		private Integer	rebackQuantity;
		private Integer deliveryNumID;
		private	int	idc;	

		@Override  
	    public HongXunDeliveryItem clone() {  
			HongXunDeliveryItem hongXunBomTree = null;  
	        try{  
	        	hongXunBomTree = (HongXunDeliveryItem)super.clone();  
	        }catch(CloneNotSupportedException e) {  
	            e.printStackTrace();  
	        }  
	        return hongXunBomTree;  
	    }


		public String getPon() {
			return pon;
		}

		public void setPon(String pon) {
			this.pon = pon;
		}

		public String getLine() {
			return line;
		}

		public void setLine(String line) {
			this.line = line;
		}

		public String getMaterialNo() {
			return materialNo;
		}

		public void setMaterialNo(String materialNo) {
			this.materialNo = materialNo;
		}

		public String getMaterialDesc() {
			return materialDesc;
		}

		public void setMaterialDesc(String materialDesc) {
			this.materialDesc = materialDesc;
		}

		public String getExemption() {
			return exemption;
		}

		public void setExemption(String exemption) {
			this.exemption = exemption;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getUnit() {
			return unit;
		}

		public void setUnit(String unit) {
			this.unit = unit;
		}

		public String getProjectNum() {
			return projectNum;
		}

		public void setProjectNum(String projectNum) {
			this.projectNum = projectNum;
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

		public Integer getSendQuantity() {
			return sendQuantity;
		}

		public void setSendQuantity(Integer sendQuantity) {
			this.sendQuantity = sendQuantity;
		}

		public Integer getChoutQuantity() {
			return choutQuantity;
		}

		public void setChoutQuantity(Integer choutQuantity) {
			this.choutQuantity = choutQuantity;
		}

		public Integer getRebackQuantity() {
			return rebackQuantity;
		}

		public void setRebackQuantity(Integer rebackQuantity) {
			this.rebackQuantity = rebackQuantity;
		}

		public Integer getDeliveryNumID() {
			return deliveryNumID;
		}

		public void setDeliveryNumID(Integer deliveryNumID) {
			this.deliveryNumID = deliveryNumID;
		}

		public int getIdc() {
			return idc;
		}

		public void setIdc(int idc) {
			this.idc = idc;
		}  
		
	}

