package paul.sydney.model;

import java.math.BigDecimal;
import java.util.Date;


	public class HongXunPoN implements java.io.Serializable,Cloneable{

		private static final long serialVersionUID = 1L;
		
		private	String 	supplier;
		private	String 	pon;
		private	String 	line;
		private	String 	groupd;
		private String  materialNo;
		private String  materialDesc;
		private String  exemption;
		private String  unit;
		private Integer  quantity;
		//private Integer  orderQuantity;
		private	BigDecimal	price;
		private	BigDecimal	totalAmount;
		private Integer  deliveredQuantity;
		private Integer  returnedQuantity;
		private Integer  nonDelivery;
		private Integer  readyQuantity;
		private Integer  inroadQuantity;
		private	Date	poDeliveryDate;
		private	Date	poCreateDate;	
		private	Date	workDeliveryDate;
		private	String	others;
		private String	attribute;
		private String	status;
		private Integer	stockQuantity;
		//private Integer	stockRqQuantity;
		private Integer	workQuantity;
		private Integer	stockInQuantity;
		private Integer	stockOutQuantity;
		private Integer orderNumID;
		private	int idc;
		@Override  
	    public HongXunPoN clone() {  
			HongXunPoN hongXunPoN = null;  
	        try{  
	        	hongXunPoN = (HongXunPoN)super.clone();  
	        }catch(CloneNotSupportedException e) {  
	            e.printStackTrace();  
	        }  
	        return hongXunPoN;  
	    }  

		public Integer getOrderNumID() {
			return orderNumID;
		}

		public void setOrderNumID(Integer orderNumID) {
			this.orderNumID = orderNumID;
		}

		public String getSupplier() {
			return supplier;
		}
		public void setSupplier(String supplier) {
			this.supplier = supplier;
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
		public String getGroupd() {
			return groupd;
		}
		public void setGroupd(String groupd) {
			this.groupd = groupd;
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
		public String getUnit() {
			return unit;
		}
		public void setUnit(String unit) {
			this.unit = unit;
		}	
/*		public Integer getOrderQuantity() {
			return orderQuantity;
		}
		public void setOrderQuantity(Integer orderQuantity) {
			this.orderQuantity = orderQuantity;
		}*/
		public Integer getQuantity() {
			return quantity;
		}
		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}
		public BigDecimal getPrice() {
			return price;
		}
		public void setPrice(BigDecimal price) {
			this.price = price;
		}
		public BigDecimal getTotalAmount() {
			return totalAmount;
		}
		public void setTotalAmount(BigDecimal totalAmount) {
			this.totalAmount = totalAmount;
		}
		public Integer getDeliveredQuantity() {
			return deliveredQuantity;
		}
		public void setDeliveredQuantity(Integer deliveredQuantity) {
			this.deliveredQuantity = deliveredQuantity;
		}
		public Integer getReturnedQuantity() {
			return returnedQuantity;
		}
		public void setReturnedQuantity(Integer returnedQuantity) {
			this.returnedQuantity = returnedQuantity;
		}
		public Integer getNonDelivery() {
			return nonDelivery;
		}
		public void setNonDelivery(Integer nonDelivery) {
			this.nonDelivery = nonDelivery;
		}
		public Integer getReadyQuantity() {
			return readyQuantity;
		}
		public void setReadyQuantity(Integer readyQuantity) {
			this.readyQuantity = readyQuantity;
		}
		public Integer getInroadQuantity() {
			return inroadQuantity;
		}
		public void setInroadQuantity(Integer inroadQuantity) {
			this.inroadQuantity = inroadQuantity;
		}
		public Date getPoDeliveryDate() {
			return poDeliveryDate;
		}
		public void setPoDeliveryDate(Date poDeliveryDate) {
			this.poDeliveryDate = poDeliveryDate;
		}
		public Date getPoCreateDate() {
			return poCreateDate;
		}
		public void setPoCreateDate(Date poCreateDate) {
			this.poCreateDate = poCreateDate;
		}
		public String getOthers() {
			return others;
		}
		public void setOthers(String others) {
			this.others = others;
		}
		
		public String getAttribute() {
			return attribute;
		}
		public void setAttribute(String attribute) {
			this.attribute = attribute;
		}
		public int getIdc() {
			return idc;
		}
		public void setIdc(int idc) {
			this.idc = idc;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public Integer getStockQuantity() {
			return stockQuantity;
		}
		public void setStockQuantity(Integer stockQuantity) {
			this.stockQuantity = stockQuantity;
		}
/*		public Integer getStockRqQuantity() {
			return stockRqQuantity;
		}
		public void setStockRqQuantity(Integer stockRqQuantity) {
			this.stockRqQuantity = stockRqQuantity;
		}*/
		public Integer getWorkQuantity() {
			return workQuantity;
		}
		public void setWorkQuantity(Integer workQuantity) {
			this.workQuantity = workQuantity;
		}
		public Integer getStockInQuantity() {
			return stockInQuantity;
		}
		public void setStockInQuantity(Integer stockInQuantity) {
			this.stockInQuantity = stockInQuantity;
		}
		public Integer getStockOutQuantity() {
			return stockOutQuantity;
		}
		public void setStockOutQuantity(Integer stockOutQuantity) {
			this.stockOutQuantity = stockOutQuantity;
		}
		public Date getWorkDeliveryDate() {
			return workDeliveryDate;
		}
		public void setWorkDeliveryDate(Date workDeliveryDate) {
			this.workDeliveryDate = workDeliveryDate;
		}	
		
	}

