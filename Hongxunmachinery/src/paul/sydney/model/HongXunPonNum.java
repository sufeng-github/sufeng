package paul.sydney.model;

import java.math.BigDecimal;
import java.util.Date;


	public class HongXunPonNum implements java.io.Serializable,Cloneable{

		private static final long serialVersionUID = 1L;		
		private	String 	supplier;
		private	String 	pon;
		private Integer  quantity;
		private	BigDecimal	totalAmount;
		private	Date	poCreateDate;			
		private String	status;
		private	int idc;
		@Override  
	    public HongXunPonNum clone() {  
			HongXunPonNum hongXunPoN = null;  
	        try{  
	        	hongXunPoN = (HongXunPonNum)super.clone();  
	        }catch(CloneNotSupportedException e) {  
	            e.printStackTrace();  
	        }  
	        return hongXunPoN;  
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
		public Integer getQuantity() {
			return quantity;
		}
		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}		
		public BigDecimal getTotalAmount() {
			return totalAmount;
		}
		public void setTotalAmount(BigDecimal totalAmount) {
			this.totalAmount = totalAmount;
		}	
		public Date getPoCreateDate() {
			return poCreateDate;
		}
		public void setPoCreateDate(Date poCreateDate) {
			this.poCreateDate = poCreateDate;
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

	}

