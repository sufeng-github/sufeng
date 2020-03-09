package paul.sydney.model;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class HongXunWorkNum implements java.io.Serializable{

		private static final long serialVersionUID = 1L;
		private int		idc;
		private String orderNum;
		private String componentNum;
		private String componentName;
		private Date workStartDate;
		private Date workEndDate;
		//private	String 	stockoutNum;
		private	String 	workNum;
		private	String	workProcedure;
		private	Integer	workQuantity;
		private	String 	remark;
		private	String	state;
		//private HongXunPoN hongXunPoN;
		//private Set<HongXunWorkItem> hongXunWorkItem = new HashSet<HongXunWorkItem>();
		
		public String getOrderNum() {
			return orderNum;
		}
		public void setOrderNum(String orderNum) {
			this.orderNum = orderNum;
		}
		public String getComponentNum() {
			return componentNum;
		}
		public void setComponentNum(String componentNum) {
			this.componentNum = componentNum;
		}
		public String getComponentName() {
			return componentName;
		}
		public void setComponentName(String componentName) {
			this.componentName = componentName;
		}
		public Date getWorkStartDate() {
			return workStartDate;
		}
		public void setWorkStartDate(Date workStartDate) {
			this.workStartDate = workStartDate;
		}
		public Date getWorkEndDate() {
			return workEndDate;
		}
		public void setWorkEndDate(Date workEndDate) {
			this.workEndDate = workEndDate;
		}
		/*
		public HongXunPoN getHongXunPoN() {
			return hongXunPoN;
		}
		public void setHongXunPoN(HongXunPoN hongXunPoN) {
			this.hongXunPoN = hongXunPoN;
		}*/

		public String getWorkNum() {
			return workNum;
		}
		/*
		public Set<HongXunWorkItem> getHongXunWorkItem() {
			return hongXunWorkItem;
		}
		public void setHongXunWorkItem(Set<HongXunWorkItem> hongXunWorkItem) {
			this.hongXunWorkItem = hongXunWorkItem;
		}*/
		public void setWorkNum(String workNum) {
			this.workNum = workNum;
		}

		public String getWorkProcedure() {
			return workProcedure;
		}
		public void setWorkProcedure(String workProcedure) {
			this.workProcedure = workProcedure;
		}
		public Integer getWorkQuantity() {
			return workQuantity;
		}
		public void setWorkQuantity(Integer workQuantity) {
			this.workQuantity = workQuantity;
		}

		public int getIdc() {
			return idc;
		}
		public void setIdc(int idc) {
			this.idc = idc;
		}
		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}



		
	}

