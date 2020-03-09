package paul.sydney.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


	public class HongXunPurchaseItem implements java.io.Serializable{

		private static final long serialVersionUID = 1L;
		private	String 	materialNum;
		private	String	materialName;
		private	String	attribute;
		private	String	specification;
		private	String	materialLotNum;
		//private	Integer	materialPlanQuantity;	
		private	Date	deliveryDate;
		private	Integer	materialRealQuantity;	
		private Integer materialPurchaseQuantity;
		private String  unit;
		private	BigDecimal	price;
		private	BigDecimal	materialMoney;
		private String  supplier;
		private	String	materialRemark;
		private String  materialState;
		//private	Date	instoreDate;	
		//private	Date	backstoreDate;	
		//private	Integer 	instoreQuantity;
		//private HongXunDataOne hongXunDataOne; 
		private	int	idc;
		private Integer purchaseDeItemID;
		//private int brotherID;
		private Integer purchaseNumID;
		//private int orderNumID;
		//private Set<HongXunPurchaseItemInStock> hongXunPurchaseItemInStock = new HashSet<HongXunPurchaseItemInStock>();
	
		public String getMaterialNum() {
			return materialNum;
		}
		public void setMaterialNum(String materialNum) {
			this.materialNum = materialNum;
		}

		public Date getDeliveryDate() {
			return deliveryDate;
		}
		public void setDeliveryDate(Date deliveryDate) {
			this.deliveryDate = deliveryDate;
		}
		/*		public Integer getMaterialPlanQuantity() {
			return materialPlanQuantity;
		}
		public void setMaterialPlanQuantity(Integer materialPlanQuantity) {
			this.materialPlanQuantity = materialPlanQuantity;
		}*/
		public Integer getMaterialRealQuantity() {
			return materialRealQuantity;
		}
		public void setMaterialRealQuantity(Integer materialRealQuantity) {
			this.materialRealQuantity = materialRealQuantity;
		}
		public String getMaterialName() {
			return materialName;
		}
		public void setMaterialName(String materialName) {
			this.materialName = materialName;
		}
	
		public String getAttribute() {
			return attribute;
		}
		public void setAttribute(String attribute) {
			this.attribute = attribute;
		}
		public String getMaterialLotNum() {
			return materialLotNum;
		}
		public void setMaterialLotNum(String materialLotNum) {
			this.materialLotNum = materialLotNum;
		}	
		public String getMaterialRemark() {
			return materialRemark;
		}
		public void setMaterialRemark(String materialRemark) {
			this.materialRemark = materialRemark;
		}
		public BigDecimal getMaterialMoney() {
			return materialMoney;
		}
		public void setMaterialMoney(BigDecimal materialMoney) {
			this.materialMoney = materialMoney;
		}

		public String getSupplier() {
			return supplier;
		}
		public void setSupplier(String supplier) {
			this.supplier = supplier;
		}
		public String getMaterialState() {
			return materialState;
		}
		public void setMaterialState(String materialState) {
			this.materialState = materialState;
		}
		public String getSpecification() {
			return specification;
		}
		public void setSpecification(String specification) {
			this.specification = specification;
		}
		public String getUnit() {
			return unit;
		}
		public void setUnit(String unit) {
			this.unit = unit;
		}
		public BigDecimal getPrice() {
			return price;
		}
		public void setPrice(BigDecimal price) {
			this.price = price;
		}
		public int getIdc() {
			return idc;
		}
		public void setIdc(int idc) {
			this.idc = idc;
		}
/*		public int getBrotherID() {
			return brotherID;
		}
		public void setBrotherID(int brotherID) {
			this.brotherID = brotherID;
		}

		public int getOrderNumID() {
			return orderNumID;
		}
		public void setOrderNumID(int orderNumID) {
			this.orderNumID = orderNumID;
		}*/

/*		public Set<HongXunPurchaseItemInStock> getHongXunPurchaseItemInStock() {
			return hongXunPurchaseItemInStock;
		}
		public void setHongXunPurchaseItemInStock(Set<HongXunPurchaseItemInStock> hongXunPurchaseItemInStock) {
			this.hongXunPurchaseItemInStock = hongXunPurchaseItemInStock;
		}*/
		public Integer getMaterialPurchaseQuantity() {
			return materialPurchaseQuantity;
		}
		public void setMaterialPurchaseQuantity(Integer materialPurchaseQuantity) {
			this.materialPurchaseQuantity = materialPurchaseQuantity;
		}


		public Integer getPurchaseDeItemID() {
			return purchaseDeItemID;
		}
		public void setPurchaseDeItemID(Integer purchaseDeItemID) {
			this.purchaseDeItemID = purchaseDeItemID;
		}
		public Integer getPurchaseNumID() {
			return purchaseNumID;
		}
		public void setPurchaseNumID(Integer purchaseNumID) {
			this.purchaseNumID = purchaseNumID;
		}



	}

