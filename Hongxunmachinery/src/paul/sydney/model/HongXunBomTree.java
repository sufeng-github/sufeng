package paul.sydney.model;

import java.util.Date;

public class HongXunBomTree implements java.io.Serializable,Cloneable{
		
		private static final long serialVersionUID = 1L;
		private Integer sn;
		private	Integer	bomQuantity;
		private	Integer	bomItemQuantity;
		private	String	bomSpacification;
		private	String	bomLength;
		private	String	bomDrawingNum;
		private	String	bomPosion;
		private	String	bomMaterial;
		private	String 	bomMaterialNum;						
		private String	attribute;
		private String	bomMaterialCuting;
		private String	bomPlateCuting;
		private String	bomMaterialWeld;
		private String	bomWeiwai;
		private String	bomPainting;
		private String	bomAssemble;
		private String	remark;
		private String	bomLocation;
		private String	bomGroup;
		private	int	idc;	
		private String parentID;
		private Integer	nodeID;
		private Integer topID;	
		private String	bomLine;
		private Date	bomDeliveryDate;
		private String	bomOhters;
		private String	bomTopMaterialNum;
		private String	bomOrderNum;
		private String 	status;
		@Override  
	    public HongXunBomTree clone() {  
			HongXunBomTree hongXunBomTree = null;  
	        try{  
	        	hongXunBomTree = (HongXunBomTree)super.clone();  
	        }catch(CloneNotSupportedException e) {  
	            e.printStackTrace();  
	        }  
	        return hongXunBomTree;  
	    }  

		
		public Integer getSn() {
			return sn;
		}
		public void setSn(Integer sn) {
			this.sn = sn;
		}
		public Integer getBomQuantity() {
			return bomQuantity;
		}
		public void setBomQuantity(Integer bomQuantity) {
			this.bomQuantity = bomQuantity;
		}
		public String getBomSpacification() {
			return bomSpacification;
		}
		public void setBomSpacification(String bomSpacification) {
			this.bomSpacification = bomSpacification;
		}
		public String getBomLength() {
			return bomLength;
		}
		public void setBomLength(String bomLength) {
			this.bomLength = bomLength;
		}
		public String getBomDrawingNum() {
			return bomDrawingNum;
		}
		public void setBomDrawingNum(String bomDrawingNum) {
			this.bomDrawingNum = bomDrawingNum;
		}
		public String getBomPosion() {
			return bomPosion;
		}
		public void setBomPosion(String bomPosion) {
			this.bomPosion = bomPosion;
		}
		public String getBomMaterial() {
			return bomMaterial;
		}
		public void setBomMaterial(String bomMaterial) {
			this.bomMaterial = bomMaterial;
		}
		public String getBomMaterialNum() {
			return bomMaterialNum;
		}
		public void setBomMaterialNum(String bomMaterialNum) {
			this.bomMaterialNum = bomMaterialNum;
		}
		public String getAttribute() {
			return attribute;
		}
		public void setAttribute(String attribute) {
			this.attribute = attribute;
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
		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}
		public String getBomLocation() {
			return bomLocation;
		}
		public void setBomLocation(String bomLocation) {
			this.bomLocation = bomLocation;
		}
		public String getBomGroup() {
			return bomGroup;
		}
		public void setBomGroup(String bomGroup) {
			this.bomGroup = bomGroup;
		}
		public int getIdc() {
			return idc;
		}
		public void setIdc(int idc) {
			this.idc = idc;
		}
		public String getParentID() {
			return parentID;
		}
		public void setParentID(String parentID) {
			this.parentID = parentID;
		}
		public Integer getTopID() {
			return topID;
		}
		public void setTopID(Integer topID) {
			this.topID = topID;
		}
		public String getBomLine() {
			return bomLine;
		}
		public void setBomLine(String bomLine) {
			this.bomLine = bomLine;
		}
		public Date getBomDeliveryDate() {
			return bomDeliveryDate;
		}
		public void setBomDeliveryDate(Date bomDeliveryDate) {
			this.bomDeliveryDate = bomDeliveryDate;
		}
		public String getBomOhters() {
			return bomOhters;
		}
		public void setBomOhters(String bomOhters) {
			this.bomOhters = bomOhters;
		}
		public String getBomTopMaterialNum() {
			return bomTopMaterialNum;
		}
		public void setBomTopMaterialNum(String bomTopMaterialNum) {
			this.bomTopMaterialNum = bomTopMaterialNum;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public Integer getBomItemQuantity() {
			return bomItemQuantity;
		}
		public void setBomItemQuantity(Integer bomItemQuantity) {
			this.bomItemQuantity = bomItemQuantity;
		}
		public String getBomOrderNum() {
			return bomOrderNum;
		}
		public void setBomOrderNum(String bomOrderNum) {
			this.bomOrderNum = bomOrderNum;
		}
		public Integer getNodeID() {
			return nodeID;
		}
		public void setNodeID(Integer nodeID) {
			this.nodeID = nodeID;
		}
		
		
	}

