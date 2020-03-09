package paul.sydney.model;


public class HongXunBomPlane implements java.io.Serializable{
		
		private static final long serialVersionUID = 1L;
		private Integer sn;
		private	Integer	BomQuantity;
		private	String	BomSpacification;
		private	String	BomLength;
		private	String	BomDrawingNum;
		private	String	BomPosion;
		private	String	BomMaterial;
		private	String 	BomMaterialNum;						
		private String	attribute;
		private String	BomMaterialCuting;
		private String	BomPlateCuting;
		private String	BomMaterialWeld;
		private String	BomWeiwai;
		private String	BomPainting;
		private String	BomAssemble;
		private String	remark;
		private String	BomLocation;
		private String	BomGroup;
		private	int	idc;	
		private Integer parentID;
		public int getIdc() {
			return idc;
		}
		public void setIdc(int idc) {
			this.idc = idc;
		}
		public String getBomMaterialNum() {
			return BomMaterialNum;
		}
		public void setBomMaterialNum(String bomMaterialNum) {
			BomMaterialNum = bomMaterialNum;
		}

		public Integer getBomQuantity() {
			return BomQuantity;
		}
		public void setBomQuantity(Integer bomQuantity) {
			BomQuantity = bomQuantity;
		}
		public String getBomSpacification() {
			return BomSpacification;
		}
		public void setBomSpacification(String bomSpacification) {
			BomSpacification = bomSpacification;
		}
		public String getBomLength() {
			return BomLength;
		}
		public void setBomLength(String bomLength) {
			BomLength = bomLength;
		}
		public String getBomDrawingNum() {
			return BomDrawingNum;
		}
		public void setBomDrawingNum(String bomDrawingNum) {
			BomDrawingNum = bomDrawingNum;
		}
		public String getBomPosion() {
			return BomPosion;
		}
		public void setBomPosion(String bomPosion) {
			BomPosion = bomPosion;
		}
		public String getBomMaterial() {
			return BomMaterial;
		}
		public void setBomMaterial(String bomMaterial) {
			BomMaterial = bomMaterial;
		}

		public String getAttribute() {
			return attribute;
		}
		public void setAttribute(String attribute) {
			this.attribute = attribute;
		}

		public Integer getSn() {
			return sn;
		}
		public void setSn(Integer sn) {
			this.sn = sn;
		}

		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}
		public String getBomMaterialCuting() {
			return BomMaterialCuting;
		}
		public void setBomMaterialCuting(String bomMaterialCuting) {
			BomMaterialCuting = bomMaterialCuting;
		}
		public String getBomPlateCuting() {
			return BomPlateCuting;
		}
		public void setBomPlateCuting(String bomPlateCuting) {
			BomPlateCuting = bomPlateCuting;
		}
		public String getBomMaterialWeld() {
			return BomMaterialWeld;
		}
		public void setBomMaterialWeld(String bomMaterialWeld) {
			BomMaterialWeld = bomMaterialWeld;
		}
		public String getBomWeiwai() {
			return BomWeiwai;
		}
		public void setBomWeiwai(String bomWeiwai) {
			BomWeiwai = bomWeiwai;
		}
		public String getBomPainting() {
			return BomPainting;
		}
		public void setBomPainting(String bomPainting) {
			BomPainting = bomPainting;
		}
		public String getBomAssemble() {
			return BomAssemble;
		}
		public void setBomAssemble(String bomAssemble) {
			BomAssemble = bomAssemble;
		}
		public String getBomLocation() {
			return BomLocation;
		}
		public void setBomLocation(String bomLocation) {
			BomLocation = bomLocation;
		}

		public Integer getParentID() {
			return parentID;
		}
		public void setParentID(Integer parentID) {
			this.parentID = parentID;
		}
		public String getBomGroup() {
			return BomGroup;
		}
		public void setBomGroup(String bomGroup) {
			BomGroup = bomGroup;
		}
		
	}

