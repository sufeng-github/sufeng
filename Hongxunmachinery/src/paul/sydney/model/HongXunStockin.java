package paul.sydney.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


	public class HongXunStockin implements java.io.Serializable{

		private static final long serialVersionUID = 1L;
		private Integer stockinQuantity;
		private Date stockinDate;
		private Integer stockinThisQuantity;
		private String stockinWorker;
		private String stockinLotNum;
		private String stockinRemark;
		private	int	idc;
		
		public Integer getStockinQuantity() {
			return stockinQuantity;
		}
		public void setStockinQuantity(Integer stockinQuantity) {
			this.stockinQuantity = stockinQuantity;
		}
		public Date getStockinDate() {
			return stockinDate;
		}
		public void setStockinDate(Date stockinDate) {
			this.stockinDate = stockinDate;
		}
		public Integer getStockinThisQuantity() {
			return stockinThisQuantity;
		}
		public void setStockinThisQuantity(Integer stockinThisQuantity) {
			this.stockinThisQuantity = stockinThisQuantity;
		}
		public String getStockinWorker() {
			return stockinWorker;
		}
		public void setStockinWorker(String stockinWorker) {
			this.stockinWorker = stockinWorker;
		}
		public String getStockinLotNum() {
			return stockinLotNum;
		}
		public void setStockinLotNum(String stockinLotNum) {
			this.stockinLotNum = stockinLotNum;
		}
		public String getStockinRemark() {
			return stockinRemark;
		}
		public void setStockinRemark(String stockinRemark) {
			this.stockinRemark = stockinRemark;
		}
		public int getIdc() {
			return idc;
		}
		public void setIdc(int idc) {
			this.idc = idc;
		}
	
	}

