package paul.sydney.model;


	public class HongXunPurchaseDeItem implements java.io.Serializable{

		private static final long serialVersionUID = 1L;
		private	Integer quantity;
		//private	Integer	materialId;	
		private Integer deNumId;
		private Integer purchaseId;
		private	String	status;
		private HongXunMaterialStock hongXunMaterialStock;
	//	private HongXunPurchaseDeNum hongXunPurchaseDeNum;
		private	int idc;
		

		public Integer getPurchaseId() {
			return purchaseId;
		}
		public void setPurchaseId(Integer purchaseId) {
			this.purchaseId = purchaseId;
		}
		public Integer getQuantity() {
			return quantity;
		}
		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}
/*		public Integer getMaterialId() {
			return materialId;
		}
		public void setMaterialId(Integer materialId) {
			this.materialId = materialId;
		}*/
		public Integer getDeNumId() {
			return deNumId;
		}
		public void setDeNumId(Integer deNumId) {
			this.deNumId = deNumId;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public int getIdc() {
			return idc;
		}
		public void setIdc(int idc) {
			this.idc = idc;
		}
		public HongXunMaterialStock getHongXunMaterialStock() {
			return hongXunMaterialStock;
		}
		public void setHongXunMaterialStock(HongXunMaterialStock hongXunMaterialStock) {
			this.hongXunMaterialStock = hongXunMaterialStock;
		}
/*		public HongXunPurchaseDeNum getHongXunPurchaseDeNum() {
			return hongXunPurchaseDeNum;
		}
		public void setHongXunPurchaseDeNum(HongXunPurchaseDeNum hongXunPurchaseDeNum) {
			this.hongXunPurchaseDeNum = hongXunPurchaseDeNum;
		}
		*/
		
	}

