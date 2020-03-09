package paul.sydney.model;

public class HongXunTablesStatus implements java.io.Serializable{
		
		private static final long serialVersionUID = 1L;
	
		private	int	idc;	
		private boolean bomModify;	
		private boolean analysis;
		private boolean onekeyExport;
		private boolean bomEnable;
		public int getIdc() {
			return idc;
		}
		public void setIdc(int idc) {
			this.idc = idc;
		}
		public boolean isBomModify() {
			return bomModify;
		}
		public void setBomModify(boolean bomModify) {
			this.bomModify = bomModify;
		}
		public boolean isAnalysis() {
			return analysis;
		}
		public void setAnalysis(boolean analysis) {
			this.analysis = analysis;
		}
		public boolean isOnekeyExport() {
			return onekeyExport;
		}
		public void setOnekeyExport(boolean onekeyExport) {
			this.onekeyExport = onekeyExport;
		}
		public boolean isBomEnable() {
			return bomEnable;
		}
		public void setBomEnable(boolean bomEnable) {
			this.bomEnable = bomEnable;
		}

		
	}

