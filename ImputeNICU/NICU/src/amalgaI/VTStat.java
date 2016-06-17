package amalgaI;

public class VTStat {

	private String ventMode;
	private int pos;
	private int neg;
	private String posPercent;
	private String negPercent;
	private String clinicNO;
	
	
	
	public String getPosPercent() {
		return posPercent;
	}
	public void setPosPercent(String posPercent) {
		this.posPercent = posPercent;
	}
	public String getNegPercent() {
		return negPercent;
	}
	public void setNegPercent(String negPercent) {
		this.negPercent = negPercent;
	}
	public String getVentMode() {
		return ventMode;
	}
	public void setVentMode(String ventMode) {
		this.ventMode = ventMode;
	}
	public int getPos() {
		return pos;
	}
	public void setPos(int pos) {
		this.pos = pos;
	}
	public int getNeg() {
		return neg;
	}
	public void setNeg(int neg) {
		this.neg = neg;
	}
	public void setClinicNO(String clinicNO) {
		this.clinicNO = clinicNO;
	}
	public String getClinicNO() {
		return clinicNO;
	}

}
