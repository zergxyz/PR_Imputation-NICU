package amalgaI;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("ICUDataService")
@Transactional
public class ICUDataService {

	private DataSource dataSource;
	private SimpleJdbcTemplate jdbcTemplate;

	@Resource(name = "dataSource-ICUData")
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new SimpleJdbcTemplate(dataSource);
	}
	

	public List<ICUData> getICUData() {

		// Prepare our SQL statement
		//String sql = "select ClinicNo, StartDateTime, stopDateTime from dbo.og_icu_demo where StartDateTime between DateAdd(Day, DateDiff(Day, 7, GetDate()), 0) and DateAdd(Day, DateDiff(Day, 0, GetDate()), 0) order by StartDateTime asc";
		
		//String sql = "select ClinicNo, AdmissionDtTm, DischargeDtTm from dbo.og_icu_demo where AdmissionDtTm between '01/01/2014' and '04/01/2014' order by AdmissionDtTm asc";
		
		
		String sql = "select ClinicNo, StartDateTime, stopDateTime from dbo.og_icu_demo where StartDateTime between '04/01/2014' and '09/01/2014' order by StartDateTime asc";
		
		
		RowMapper<ICUData> mapper = new RowMapper<ICUData>() {
			public ICUData mapRow(ResultSet rs, int rowNum) throws SQLException {
				ICUData icup = new ICUData();

				SimpleDateFormat sf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				icup.setDemoClinicNo(rs.getString("ClinicNo"));
				icup.setDemoStartDT(sf.format(rs.getTimestamp("StartDateTime")));
				if (rs.getTimestamp("stopDateTime") == null)
					icup.setDemoStopDT("still in ICU");
				else
					icup.setDemoStopDT(sf.format(rs.getTimestamp("stopDateTime")));
				return icup;
			}
		};
		return jdbcTemplate.query(sql, mapper);
	}
	
	
	public ArrayList<ICUData> getVTICUData(String icus) {
		String process = icus.substring(3);
		
		String [] list = process.split(",");
		ArrayList<String> iculist =new ArrayList<String>();
		for(String s: list) {
			
			iculist.add(s);
		}
		
		// Prepare our SQL statement
		String sql = "select a.clinicno, a.measurement_datetime, a.measurement_type, b.PatientHeightCM, a.measurement_value, b.PatientGender, b.NurseUnit from dbo.og_icu_vital as a, dbo.og_icu_demo as b where a.measurement_datetime between '01/01/2013' and '01/02/2013' and a.measurement_type IN ( 'Tidal Volume Expired', 'Ventilator Mode') and a.clinicno=b.ClinicNo and b.StartDateTime<=a.measurement_datetime and b.StopDateTime>=a.measurement_datetime and b.NurseUnit in (:iculist) group by a.clinicno, a.measurement_datetime, a.measurement_type, b.PatientHeightCM, a.measurement_value, b.PatientGender, b.NurseUnit";
		
		ArrayList<ICUData> plist = new ArrayList<ICUData>();
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("iculist", iculist);
		
		// Maps a SQL result to a Java object
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, params);
		SimpleDateFormat sdfDate = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		for (Map<String, Object> row : rows) {

			ICUData p = new ICUData();
			p.setDemoClinicNo(row.get("clinicno").toString());
			p.setVitalMeasureDT(sdfDate.format(row.get("measurement_datetime")));
			p.setDemoUnit(row.get("NurseUnit").toString());
			p.setGender(row.get("PatientGender").toString());
			p.setVitalMeasureType(row.get("measurement_type").toString());
			p.setVitalMeasureValue(row.get("measurement_value").toString());
			p.setHeight(row.get("measurement_value").toString());
			BigDecimal b =(BigDecimal)row.get("PatientHeightCM");
			p.setHeight(b.toString());
			
			plist.add(p);
		}

		return plist;
	}
	
	
	
	public List<LVCPatient> lvcReport() {

		// Prepare our SQL statement
		String sql = "select top 6 * from dbo.og_icu_levelofcare where StartDateTime > '05/18/2013' ";
		// Maps a SQL result to a Java object
		RowMapper<LVCPatient> mapper = new RowMapper<LVCPatient>() {
			public LVCPatient mapRow(ResultSet rs, int rowNum) throws SQLException {
				LVCPatient lvp = new LVCPatient();

//				SimpleDateFormat sf = new SimpleDateFormat(
//						"yyyy-MM-dd HH:mm:ss");
				lvp.setClinicNumber(rs.getString("ClinicNo"));
				lvp.setmDT(rs.getString("MessageDateTime"));
				lvp.setLevelOfCare(rs.getString("LevelOfCareMessage"));
				return lvp;
			}
		};
		return jdbcTemplate.query(sql, mapper);
	}
	
	
	public void storeReports(ArrayList<LVCPatient> lvcs) {

		String sql = "If Not Exists (select * from dbo.og_icu_levelofcare where ClinicNo=:clnum and MessageDateTime=:mDT) begin insert into dbo.og_icu_levelofcare (ClinicNo, StartDateTime, MessageDateTime, StopDateTime, LevelOfCareMessage) "
				+ "values (:clnum, :saDT, :mDT, :stDT, :lvc) end";
		for (LVCPatient tp : lvcs) {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("clnum", tp.getClinicNumber());
			parameters.put("saDT", tp.getStartDT());
			parameters.put("mDT", tp.getmDT());
			if (tp.getStopDT().equalsIgnoreCase("still in ICU"))
				parameters.put("stDT", null);
			else
				parameters.put("stDT", tp.getStopDT());
			parameters.put("lvc", tp.getLevelOfCare());
			jdbcTemplate.update(sql, parameters);
		}

	}
	
	public void storeReportsWithoutICUADmin(ArrayList<LVCPatient> lvcs) {

		String sql = "If Not Exists (select * from ICU_DataRep.dbo.og_icu_levelofcare where ClinicNo=:clnum and MessageDateTime=:mDT) begin insert into ICU_DataRep.dbo.og_icu_levelofcare (ClinicNo,  MessageDateTime,  LevelOfCareMessage) "
				+ "values (:clnum, :mDT, :lvc) end";
		for (LVCPatient tp : lvcs) {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("clnum", tp.getClinicNumber());
			parameters.put("mDT", tp.getmDT());
			parameters.put("lvc", tp.getLevelOfCare());
			jdbcTemplate.update(sql, parameters);
		}

	}

	
}
