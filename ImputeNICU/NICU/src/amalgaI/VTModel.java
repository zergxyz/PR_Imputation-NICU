package amalgaI;

public class VTModel {
	
	private String ventModel;
	private double vtData;
	private String icu;
	private String clinicNo;
	
	
	public void setVtData(double vtData) {
		this.vtData = vtData;
	}
	public double getVtData() {
		return vtData;
	}
	public void setVentModel(String ventModel) {
		this.ventModel = ventModel;
	}
	public String getVentModel() {
		return ventModel;
	}
	public void setIcu(String icu) {
		this.icu = icu;
	}
	public String getIcu() {
		return icu;
	}
	public void setClinicNo(String clinicNo) {
		this.clinicNo = clinicNo;
	}
	public String getClinicNo() {
		return clinicNo;
	}
	
}
