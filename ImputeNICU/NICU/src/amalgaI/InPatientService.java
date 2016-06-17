package amalgaI;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
import javax.annotation.Resource;
import javax.sql.DataSource;
 
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



//SELECT * FROM dbo.og_icu_rrt where CallCompleteDtTm>=GETDATE()-70
@Service("InPatientService")
@Transactional
public class InPatientService {

	 
	 protected static Logger logger = Logger.getLogger("service");
	  
	 private DataSource dataSource;
	 private SimpleJdbcTemplate jdbcTemplate;
	  
	 @Resource(name="dataSource")
	 public void setDataSource(DataSource dataSource) {
	     this.dataSource = dataSource;
	     this.jdbcTemplate = new SimpleJdbcTemplate(dataSource);
	 }
	 

	 public InPatient getTargetPatient(String clinicNo) {
	  logger.debug("Retrieving target patient");
	   
	  
// String sql = "SELECT clinicNumber, firstName, middleName, lastName, birthDate, gender FROM ICU_DataRep.dbo.EDPastCensus where clinicNumber = ? ";
	  
//	  RowMapper<InPatient> mapper = new RowMapper<InPatient>() {  
//	         public InPatient mapRow(ResultSet rs, int rowNum) throws SQLException {
//	          InPatient p = new InPatient();
//	          p.setPatientName(rs.getString("firstName")+","
//	          +rs.getString("middleName")+","+ rs.getString("lastName"));
//	          SimpleDateFormat sdfDate = new SimpleDateFormat("MM/dd/yyyy");
//	          p.setDateOfBirth(sdfDate.format(rs.getTimestamp("birthDate")));
//	          p.setGender(rs.getString("gender"));
//	          p.setClinicNumber(rs.getString("clinicNumber"));
//	          return p;
//	         }
//	     };
	  
	  //String sql = "SELECT top 1 measurement_value=v1.measurement_value, measurement_value=va.measurement_value, PatientName, PatientGender, PatientDOB, ClinicNo=va.clinicno FROM dbo.og_icu_vital va join dbo.og_icu_demo de on va.clinicno= de.ClinicNo join dbo.og_icu_vital v1 on v1.clinicno= de.ClinicNo where va.measurement_type = 'Heart Rate' and va.clinicno = ? and v1.measurement_type = 'Respiratory Rate' order by va.measurement_datetime desc, v1.measurement_datetime DESC";
	  String sql = "SELECT top 1 measurement_value=v1.measurement_value,  measurement_value=va.measurement_value, PatientName, PatientGender, PatientDOB, ClinicNo=va.clinicno, AdmissionDtTm FROM dbo.og_icu_vital va join dbo.og_icu_demo de on va.clinicno= de.ClinicNo left join dbo.og_icu_vital v1 on v1.clinicno= de.ClinicNo and v1.measurement_type = 'SpO2' where va.measurement_type = 'Heart Rate' and va.clinicno = ? order by va.measurement_datetime desc, v1.measurement_datetime DESC";
	  RowMapper<InPatient> mapper = new RowMapper<InPatient>() {  
	         public InPatient mapRow(ResultSet rs, int rowNum)  {
	          InPatient p = new InPatient();
	          try {
				  p.setPatientName(rs.getString("PatientName"));
				  p.setGender(rs.getString("PatientGender"));
		          p.setClinicNumber(rs.getString("ClinicNo"));
		          SimpleDateFormat sdfDate = new SimpleDateFormat("MM/dd/yyyy");
		          p.setDateOfBirth(sdfDate.format(rs.getTimestamp("PatientDOB")));
		          p.setAdminDt(sdfDate.format(rs.getTimestamp("AdmissionDtTm")));
		          p.setSpo2(rs.getString(1));
		          p.setHeartRate(rs.getString(2));
			} catch (SQLException e) {
				
				
			}
	          
	          return p;
	         }
	     };
	     
	     InPatient ip;

	     try{ 
	    	 
	    	 ip = (InPatient) this.jdbcTemplate.queryForObject(sql, mapper, clinicNo);
		     String sql1 = "select top 1 measurement_value=v0.measurement_value, measurement_value=v1.measurement_value, clinicno=v0.clinicno from dbo.og_icu_vital v0 left join dbo.og_icu_vital v1 on v1.clinicno= v0.clinicno and v1.measurement_type = 'Systolic BP-Standing' where v0.clinicno= ? and v0.measurement_type = 'Respiratory Rate' order by v0.measurement_datetime desc, v1.measurement_datetime desc";
		     
		     RowMapper<InPatient> mapper2 = new RowMapper<InPatient>() {  
		         public InPatient mapRow(ResultSet rs, int rowNum) throws SQLException {
		          InPatient p = new InPatient();
		          p.setResp(rs.getString(1));
		          p.setBloodPressure(rs.getString(2));
		          return p;
		         }
		     };
		     
		     InPatient ip2 = (InPatient) this.jdbcTemplate.queryForObject(sql1, mapper2, clinicNo);
		     ip.setResp(ip2.getResp());
		     ip.setBloodPressure(ip2.getBloodPressure());
		     
		     
		     String sql2= "SELECT top 1 measurement_value from dbo.og_icu_vital where measurement_type ='Temperature-Bladder' and clinicno = ? order by measurement_datetime desc";
		     RowMapper<InPatient> mapper3 = new RowMapper<InPatient>() {  
		         public InPatient mapRow(ResultSet rs, int rowNum) throws SQLException {
		          InPatient p = new InPatient();
		          p.setTemperture((rs.getString("measurement_value")));
		          return p;
		         }
		     };
		     InPatient ip3 = (InPatient) this.jdbcTemplate.queryForObject(sql2, mapper3, clinicNo);
		     ip.setTemperture(ip3.getTemperture());
	     }
	     catch(Exception e)
	     
	     {
	    	 System.out.print("I got it");
				return null;
	     }
	    

	     

	     
	  return ip;
	 }
	 
	 
	 public void storeReports(InPatient tp) {
		 
		 String sql="insert into dbo.og_icu_rrt (ClinicNo, PatientName, Spo2, BloodPressure, Temperature, Resp, HeartRate, PersonalRequesting, PscPager, " +
		 		"AVPUScore, PrimaryReason, PatientStatus, DNR, PaClinicStatus, OxygenAdmin, BagMaskVen, CPAPNIPPY, Intubation, ExtraChestComp, Defibrillation, Cardioversion, Diagnostic, BolusFluid, BloodProductsGiven, MedicationThearapy, AdmissionDtTm,  PatientDOB, CallCompleteDtTm, PatientGender ) " +
		 		"values (:clnum, :pName, :spo, :bp, :temperature, :resp, :heartRate, :personRequest, :pscPager, :avpu, :primaryReason, :pStatus, :dnr, :pEndStatus, :oxy, :bag, :cpap, :intuba, :extraChest, :defibri, :cardi, :diag, :bolusF, :bpGiven, :medication, :admiDT, :pDOB, :cDT, :gender) ";
		
		  Map<String, Object> parameters = new HashMap<String, Object>();
		  parameters.put("clnum", tp.getClinicNumber());
		  parameters.put("pName", tp.getPatientName());
		  parameters.put("spo", tp.getSpo2());
		  parameters.put("bp", tp.getBloodPressure());
		  parameters.put("temperature", tp.getTemperture());
		  parameters.put("resp", tp.getResp());
		  parameters.put("heartRate", tp.getHeartRate());
		  
		  parameters.put("personRequest", tp.getPersonalRequest());
		  parameters.put("pscPager", tp.getPscPager());
		  parameters.put("avpu", tp.getAvpuScore());
		  parameters.put("primaryReason", tp.getPrimaryReason());
		  parameters.put("pStatus", tp.getPaClinicStatus());
		  parameters.put("dnr", tp.getDNR());

		  
		  parameters.put("pEndStatus", tp.getPatientStatus());
		  parameters.put("oxy", tp.getOxyAdmin());
		  parameters.put("bag", tp.getBagMaskVen());
		  parameters.put("cpap", tp.getCpapNIPPY());
		  parameters.put("intuba", tp.getIntubation());
		  parameters.put("extraChest", tp.getExtraChestComp());
		  parameters.put("defibri", tp.getDefibrillation());
		  parameters.put("cardi", tp.getCardioversion());
		  parameters.put("diag", tp.getDiagnostic());
		  parameters.put("bolusF", tp.getBolusFluid());
		  
		  parameters.put("bpGiven", tp.getBloodProductGiven());
		  parameters.put("medication", tp.getMediThearapy());
		  parameters.put("admiDT", tp.getAdminDt());
		  parameters.put("pDOB", tp.getDateOfBirth());
		  parameters.put("cDT", tp.getCallCompleteDtm());
		  parameters.put("gender", tp.getGender());		  
		  
		  // Save
		  jdbcTemplate.update(sql, parameters);
	 }
	 
	 
		 
	 
	 public List<InPatient> getAll() {
		  logger.debug("Retrieving all persons");
		   //SELECT * FROM dbo.og_icu_rrt where CallCompleteDtTm>=GETDATE()-70
		  // Prepare our SQL statement
		  String sql = "select ClinicNo, PatientName, CallCompleteDtTm, PatientGender from ICU_DataRep.dbo.og_icu_rrt where CallCompleteDtTm>=GETDATE()-7";
		   
		  // Maps a SQL result to a Java object
		  RowMapper<InPatient> mapper = new RowMapper<InPatient>() {  
		         public InPatient mapRow(ResultSet rs, int rowNum) throws SQLException {
		          InPatient person = new InPatient();
		        
		          person.setClinicNumber(rs.getString("ClinicNo"));
		          person.setPatientName(rs.getString("PatientName"));
		          person.setGender(rs.getString("PatientGender"));
		          
		          SimpleDateFormat sdfDate = new SimpleDateFormat("MM/dd/yyyy HH:mm a");
		          person.setCallCompleteDtm(sdfDate.format(rs.getTimestamp("CallCompleteDtTm")));
		         
		             return person;
		         }
		     };
		   
		  // Retrieve all
		  return jdbcTemplate.query(sql, mapper);
		 }
	 
	 
	 public List<InPatient> getAllFromRange(String date1, String date2) {
		  logger.debug("Retrieving all persons");
		   
		  // Prepare our SQL statement
		  String sql = "select ClinicNo, PatientName, CallCompleteDtTm from ICU_DataRep.dbo.og_icu_rrt where CallCompleteDtTm between ? and ?";
		   
		  // Maps a SQL result to a Java object
		  RowMapper<InPatient> mapper = new RowMapper<InPatient>() {  
		         public InPatient mapRow(ResultSet rs, int rowNum) throws SQLException {
		          InPatient person = new InPatient();
		        
		          person.setClinicNumber(rs.getString("ClinicNo"));
		          person.setPatientName(rs.getString("PatientName"));
		          SimpleDateFormat sdfDate = new SimpleDateFormat("MM/dd/yyyy HH:mm a");
		          person.setCallCompleteDtm(sdfDate.format(rs.getTimestamp("CallCompleteDtTm")));
		         
		             return person;
		         }
		     };
		   
		  // Retrieve all
		  return jdbcTemplate.query(sql, mapper, date1, date2);
		 }
	 
	 
	 public InPatient getTargetPatientFromHis(String cdate) {
		 
		
		 String sql ="select * from ICU_DataRep.dbo.og_icu_rrt where CallCompleteDtTm = ? ";
		 
		  RowMapper<InPatient> mapper = new RowMapper<InPatient>() {  
		         public InPatient mapRow(ResultSet rs, int rowNum) throws SQLException {
		          InPatient person = new InPatient();
		          person.setClinicNumber(rs.getString("ClinicNo"));
		          person.setPatientName(rs.getString("PatientName"));
		          person.setSpo2(rs.getString("Spo2"));
		          person.setBloodPressure(rs.getString("BloodPressure"));
		          person.setTemperture(rs.getString("Temperature"));
		          person.setResp(rs.getString("Resp"));
		          person.setHeartRate(rs.getString("HeartRate"));
		          person.setPersonalRequest(rs.getString("PersonalRequesting"));
		          person.setPscPager(rs.getString("PscPager"));
		          person.setAvpuScore(rs.getString("AVPUScore"));
		          person.setPrimaryReason(rs.getString("PrimaryReason"));
		          person.setPatientStatus(rs.getString("PatientStatus"));
		          person.setDNR(rs.getString("DNR"));
		          SimpleDateFormat sdfDate = new SimpleDateFormat("MM/dd/yyyy HH:mm a");
		          person.setCallCompleteDtm(sdfDate.format(rs.getTimestamp("CallCompleteDtTm")));
		          person.setDateOfBirth(sdfDate.format(rs.getTimestamp("PatientDOB")));
		          person.setGender(rs.getString("PatientGender"));
		          person.setOxyAdmin(rs.getString("OxygenAdmin"));
		          person.setBagMaskVen(rs.getString("BagMaskVen"));
		          person.setIntubation(rs.getString("Intubation"));
		          person.setMediThearapy(rs.getString("MedicationThearapy"));
		          
		             return person;
		         }
		     };
		     
		     InPatient ip = (InPatient) this.jdbcTemplate.queryForObject(sql, mapper, cdate);
		     
		 
		 return ip;
	 }
	 
	 
//	 public InPatient getTargetPatientFromHis1( String cdate) {
//		 
//			
//		
//		 
//		  RowMapper<InPatient> mapper = new RowMapper<InPatient>() {  
//		         public InPatient mapRow(ResultSet rs, int rowNum) throws SQLException {
//		          InPatient person = new InPatient();
//		          person.setClinicNumber(rs.getString("ClinicNo"));
//		          person.setPatientName(rs.getString("PatientName"));
//		          person.setSpo2(rs.getString("Spo2"));
//		          person.setBloodPressure(rs.getString("BloodPressure"));
//		          person.setTemperture(rs.getString("Temperature"));
//		          person.setResp(rs.getString("Resp"));
//		          person.setHeartRate(rs.getString("HeartRate"));
//		          person.setPersonalRequest(rs.getString("PersonalRequesting"));
//		          person.setPscPager(rs.getString("PscPager"));
//		          person.setAvpuScore(rs.getString("AVPUScore"));
//		          person.setPrimaryReason(rs.getString("PrimaryReason"));
//		          person.setPatientStatus(rs.getString("PatientStatus"));
//		          person.setDNR(rs.getString("DNR"));
//		          person.setResponsibleTeam(rs.getString("ResponsibleTeam"));
//		          SimpleDateFormat sdfDate = new SimpleDateFormat("MM/dd/yyyy");
//		          person.setCallCompleteDtm(sdfDate.format(rs.getTimestamp("CallCompleteDtTm")));
//		         
//		             return person;
//		         }
//		     };
//		     
//		     InPatient ip = (InPatient) this.jdbcTemplate.queryForObject(sql, mapper, no, cdate);
//		     
//		 
//		 return ip;
//	 }
	 

	  
}
