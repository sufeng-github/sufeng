package paul.sydney.model;

public class HongXunAuthority implements java.io.Serializable{

		private static final long serialVersionUID = 1L;
		private int		idc;
		private String userName;
		private String position;
		private String authorityDsc;
		private Integer stateValue;
		private Integer stateValue1;
		private Integer stateValue2;
		private String userCode;
		public int getIdc() {
			return idc;
		}
		public void setIdc(int idc) {
			this.idc = idc;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getPosition() {
			return position;
		}
		public void setPosition(String position) {
			this.position = position;
		}
		public String getAuthorityDsc() {
			return authorityDsc;
		}
		public void setAuthorityDsc(String authorityDsc) {
			this.authorityDsc = authorityDsc;
		}

		public Integer getStateValue() {
			return stateValue;
		}
		public void setStateValue(Integer stateValue) {
			this.stateValue = stateValue;
		}
		
		public Integer getStateValue1() {
			return stateValue1;
		}
		public void setStateValue1(Integer stateValue1) {
			this.stateValue1 = stateValue1;
		}		
		public Integer getStateValue2() {
			return stateValue2;
		}
		public void setStateValue2(Integer stateValue2) {
			this.stateValue2 = stateValue2;
		}
		public String getUserCode() {
			return userCode;
		}
		public void setUserCode(String userCode) {
			this.userCode = userCode;
		}
		
	}

