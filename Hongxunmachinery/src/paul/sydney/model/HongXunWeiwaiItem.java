package paul.sydney.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class HongXunWeiwaiItem implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private String	materialNum;
	private String	materialName;							
	private String	specification;	
	private Integer	planQuantity;			
	private Integer	realQuantity;	
	private Date	realDeliveryDate;
	private String	unit;
	private String	supplier;
	private BigDecimal	price;
	private BigDecimal	weiwaiMoney;
	private String	remark;
	private String attribute;
	private int	idc;
	private int	weiwaiNumID;
	private int	orderNumID;
	public String getMaterialNum() {
		return materialNum;
	}
	public void setMaterialNum(String materialNum) {
		this.materialNum = materialNum;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public Integer getPlanQuantity() {
		return planQuantity;
	}
	public void setPlanQuantity(Integer planQuantity) {
		this.planQuantity = planQuantity;
	}
	public Integer getRealQuantity() {
		return realQuantity;
	}
	public void setRealQuantity(Integer realQuantity) {
		this.realQuantity = realQuantity;
	}
	public Date getRealDeliveryDate() {
		return realDeliveryDate;
	}
	public void setRealDeliveryDate(Date realDeliveryDate) {
		this.realDeliveryDate = realDeliveryDate;
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
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getWeiwaiMoney() {
		return weiwaiMoney;
	}
	public void setWeiwaiMoney(BigDecimal weiwaiMoney) {
		this.weiwaiMoney = weiwaiMoney;
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
	public int getWeiwaiNumID() {
		return weiwaiNumID;
	}
	public void setWeiwaiNumID(int weiwaiNumID) {
		this.weiwaiNumID = weiwaiNumID;
	}
	public String getAttribute() {
		return attribute;
	}
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	public int getOrderNumID() {
		return orderNumID;
	}
	public void setOrderNumID(int orderNumID) {
		this.orderNumID = orderNumID;
	}

}

