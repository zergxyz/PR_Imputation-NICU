package amalgaI;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("NICUService")
@Transactional
public class NICUService {

	private DataSource dataSource;
	private SimpleJdbcTemplate jdbcTemplate;

	@Resource(name = "dataSource-ICUData")
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new SimpleJdbcTemplate(dataSource);
	}

	public List<NICUPatient> getAllPatients() {

		// Prepare our SQL statement
		String sql = "select * from og_icu_demo where NurseUnit = 'MB3B' and DischargeDtTm is null and (BedLocation like '510%' or BedLocation like '508%' or BedLocation like '512%' or BedLocation like '513%')";

		// Maps a SQL result to a Java object
		RowMapper<NICUPatient> mapper = new RowMapper<NICUPatient>() {
			public NICUPatient mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				NICUPatient person = new NICUPatient();

				person.setClinicNumber(rs.getString("ClinicNo"));
				person.setPatientName(rs.getString("PatientName"));

				SimpleDateFormat sf = new SimpleDateFormat("MMM dd yyyy");
				person.setAdminDt(sf.format(rs.getTimestamp("StartDateTime")));

				return person;
			}
		};

		// Retrieve all
		return jdbcTemplate.query(sql, mapper);
	}


	// for target patient view to see specific records of this patient
	public ArrayList<NICUPatient> adminInfo(String clnum) {

		String sql = "select * from og_icu_demo where ClinicNo= :clnumber";

		ArrayList<NICUPatient> plist = new ArrayList<NICUPatient>();
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("clnumber", clnum);

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, params);
		SimpleDateFormat sf = new SimpleDateFormat("MMM dd yyyy");
		String adminDate = "";
		for (Map<String, Object> row : rows) {

			NICUPatient p = new NICUPatient();
			p.setClinicNumber(row.get("ClinicNo").toString());
			p.setPatientName((String) row.get("PatientName"));
			adminDate = sf.format(row.get("StartDateTime"));
			p.setAdminDt(adminDate);
			plist.add(p);
		}

		return plist;
	}

	public ArrayList<NICUPatient> getHistoricalPatients(String sDate,
			String eDate) {

		ArrayList<NICUPatient> ps = new ArrayList<NICUPatient>();
		// Prepare our SQL statement
		String sql = "select * from og_icu_demo where NurseUnit = 'MB3B' and AdmissionDtTm between :sDate and :eDate";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("sDate", sDate);
		params.addValue("eDate", eDate);
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, params);
		for (Map<String, Object> row : rows) {
			NICUPatient p = new NICUPatient();
			p.setClinicNumber(row.get("ClinicNo").toString());
			// p.setAdminDt((String)row.get("StartDateTime"));
			ps.add(p);
		}
		return ps;
	}

	public Hashtable<String, String> getLabs(String clnum, String targetDate)
			throws ParseException {

		Hashtable<String, String> labs = new Hashtable<String, String>();
		SimpleDateFormat sdfDate = new SimpleDateFormat("MM/dd/yyyy");
		Date nextDay1 = sdfDate.parse(targetDate);
		String nextDate = sdfDate.format(nextDay1.getTime() + 1000 * 60 * 60
				* 24);

		String sql = "select * from og_icu_lab  where ClinicNo= :clnumber and LabCollectionDtTm between :tarDate and :neDate";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("clnumber", clnum);
		params.addValue("tarDate", targetDate);
		params.addValue("neDate", nextDate);
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, params);
		String desc = "";
		String res = "";
		for (Map<String, Object> row : rows) {

			desc = (String) row.get("LabTestDesc");
			res = (String) row.get("LabResultText");
			labs.put(desc, res);
		}

		return labs;
	}

	// it seems like there are some cases that muliple records will be recorded
	// within one day, do we need to consider all of them or the most updated
	// one?

	public Hashtable<String, String> getVitals(String clnum, String targetDate)
			throws ParseException {

		Hashtable<String, String> vitals = new Hashtable<String, String>();

		SimpleDateFormat sdfDate = new SimpleDateFormat("MM/dd/yyyy");
		Date nextDay1 = sdfDate.parse(targetDate);
		String nextDate = sdfDate.format(nextDay1.getTime() + 1000 * 60 * 60
				* 24);

		String sql = "select * from og_icu_vital where clinicno= :clnumber and measurement_datetime between :tarDate and :neDate";

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("clnumber", clnum);
		params.addValue("tarDate", targetDate);
		params.addValue("neDate", nextDate);

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, params);
		String desc = "";
		String res = "";
		for (Map<String, Object> row : rows) {

			desc = (String) row.get("measurement_type");
			res = (String) row.get("measurement_value");
			vitals.put(desc, res);
		}

		// Enumeration<String> enumKey = vitals.keys();
		// while(enumKey.hasMoreElements()) {
		// String key = enumKey.nextElement();
		// String val = vitals.get(key);
		// System.out.print(" "+val);
		// }

		// System.out.println(h.get("Erythrocytes"));

		return vitals;
	}

	public Hashtable<String, String> getFluids(String clnum, String targetDate)
			throws ParseException {

		Hashtable<String, String> fluids = new Hashtable<String, String>();

		SimpleDateFormat sdfDate = new SimpleDateFormat("MM/dd/yyyy");
		Date nextDay1 = sdfDate.parse(targetDate);
		String nextDate = sdfDate.format(nextDay1.getTime() + 1000 * 60 * 60
				* 24);

		String sql = "select * from og_icu_fluids where clinicno= :clnumber and donedate between :tarDate and :neDate";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("clnumber", clnum);
		params.addValue("tarDate", targetDate);
		params.addValue("neDate", nextDate);

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, params);
		// fluids data part should be double checked for more data fields

		String desc = "";
		String res = "";
		for (Map<String, Object> row : rows) {

			desc = (String) row.get("genericname");
			res = (String) row.get("dosec");
			fluids.put(desc, res);
		}

		// Enumeration<String> enumKey = fluids.keys();
		// while(enumKey.hasMoreElements()) {
		// String key = enumKey.nextElement();
		// String val = fluids.get(key);
		// System.out.print(" "+key);
		// }

		return fluids;
	}

	// this part has problem of getting target data, more details needed -------
	public Hashtable<String, String> getFlowSheets(String clnum) {

		Hashtable<String, String> flowSheets = new Hashtable<String, String>();
		String sql = "select * from og_flowsheet where clinicno= ? and flowdatetime >=DateAdd(Day, DateDiff(Day, 0, GetDate()), 0)";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, clnum);
		String desc = "";
		String res = "";
		for (Map<String, Object> row : rows) {

			desc = (String) row.get("COMPONENT_NAME");
			res = (String) row.get("FLOW_TEXT");
			flowSheets.put(desc, res);
		}

		// System.out.println(h.get("Erythrocytes"));

		return flowSheets;
	}

	public Hashtable<String, String> globalDataSets(String clnum,
			String targetDate) throws ParseException {

		Hashtable<String, String> global = new Hashtable<String, String>();

		SimpleDateFormat sdfDate = new SimpleDateFormat("MM/dd/yyyy");
		Date nextDay1 = sdfDate.parse(targetDate);
		String nextDate = sdfDate.format(nextDay1.getTime() + 1000 * 60 * 60
				* 24);
		
		//System.out.println("getting labs ABO............");
		String sql = "select * from og_icu_lab where ClinicNo = ? and  (labtestDesc like 'ABO+Rh%' or labtestDesc like 'ABO/Rh%') ";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, clnum);
		String desc = "";
		String res = "";
		for (Map<String, Object> row : rows) {

			desc = (String) row.get("LabTestDesc");
			res = (String) row.get("LabResultText");
			global.put(desc, res);
		}

		
		//System.out.println("getting labs DAT ............");
		String sql2 = "select * from og_icu_lab where ClinicNo = ? and labtestDesc = 'DAT'";
		List<Map<String, Object>> rows2 = jdbcTemplate
				.queryForList(sql2, clnum);
		String desc2 = "";
		String res2 = "";
		for (Map<String, Object> row2 : rows2) {

			desc2 = (String) row2.get("LabTestDesc");
			res2 = (String) row2.get("LabResultText");
			global.put(desc2, res2);
		}

		LinkedList<Double> ll = new LinkedList<Double>();
		
		//System.out.println("getting vitals FIO2 ............");
		String sql3 = "select * from og_icu_vital where measurement_type = 'FIO2/O2%' and clinicno = :clnumber and measurement_datetime between :tarDate and :neDate and measurement_value is not null";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("clnumber", clnum);
		params.addValue("tarDate", targetDate);
		params.addValue("neDate", nextDate);

		List<Map<String, Object>> rows3 = jdbcTemplate.queryForList(sql3,
				params);
		String desc3 = "";
		double res3 = 0.0;

		for (Map<String, Object> row3 : rows3) {

			desc3 = (String) row3.get("measurement_value");
			res3 = Double.parseDouble(desc3.substring(0, 2));
			System.out.println(res3);
			ll.add(res3);
		}

		Collections.sort(ll);
		if (ll.isEmpty())
			desc3 = " ";
		else
			desc3 = ll.getLast() + "";

		global.put("highestFio2", desc3);
		// System.out.print(global.get("DAT")); Nitric Oxide

		LinkedList<Double> ll2 = new LinkedList<Double>();
		
		//System.out.println("getting vitals Nitric Oxide ............");
		String sql4 = "select * from og_icu_vital where measurement_type = 'Nitric Oxide' and clinicno = :clnumber and measurement_datetime between :tarDate and :neDate";
		List<Map<String, Object>> rows4 = jdbcTemplate.queryForList(sql4,
				params);
		String desc4 = "";
		double res4 = 0;

		for (Map<String, Object> row4 : rows4) {
			desc4 = (String) row4.get("measurement_value");
			res4 = Double.parseDouble(desc4);
			// System.out.println(res3);
			ll2.add(res4);
		}
		Collections.sort(ll2);
		if (ll2.isEmpty())
			desc4 = " ";
		else
			desc4 = ll2.getLast() + "";

		global.put("highestniox", desc4);

		LinkedList<Double> ll3 = new LinkedList<Double>();
		
		//System.out.println("getting vitals SpO2 ............");
		String sql5 = "select * from og_icu_vital where measurement_type = 'SpO2' and clinicno = :clnumber and measurement_datetime between :tarDate and :neDate and measurement_value is not null";
		List<Map<String, Object>> rows5 = jdbcTemplate.queryForList(sql5,
				params);
		double sum = 0.0;
		double average = 0.0;
		String desc5 = "";
		for (Map<String, Object> row5 : rows5) {
			desc5 = (String) row5.get("measurement_value");
			sum = Double.parseDouble(desc5);
			// System.out.println(sum);
			ll3.add(sum);
		}
		if (ll3.isEmpty()) {
		} else {
			ll3.removeLast();
		}

		// for(double d: ll3) {
		//			
		// System.out.println("after deleting: "+d);
		// }

		Collections.sort(ll3);
		sum = 0.0;
		if (ll3.isEmpty()) {
			desc5 = " ";
			global.put("avespo2", desc5);
		} else {
			// System.out.println("debuging");
			for (int i = 0; i < ll3.size(); i++) {
				sum += ll3.get(i);
			}
			average = sum / ll3.size();

			DecimalFormat df = new DecimalFormat("#.##");
			desc5 = df.format(average);
			global.put("avespo2", desc5);
		}

		LinkedList<Double> ll4 = new LinkedList<Double>();
		
		//System.out.println("getting vital Oxygen ............");
		String sql6 = "select * from og_icu_vital where measurement_type = 'Oxygen % / LPM #1' and clinicno = :clnumber and measurement_datetime between :tarDate and :neDate and measurement_value is not null";
		List<Map<String, Object>> rows6 = jdbcTemplate.queryForList(sql6,
				params);
		String desc6 = "";
		double res6 = 0;

		for (Map<String, Object> row6 : rows6) {
			desc6 = (String) row6.get("measurement_value");
			res6 = Double.parseDouble(desc6);
			// System.out.println(res3);
			ll4.add(res6);
		}
		Collections.sort(ll4);
		if (ll4.isEmpty())
			desc6 = " ";
		else
			desc6 = ll4.getLast() + "";

		global.put("highesto2", desc6);

		LinkedList<Double> ll5 = new LinkedList<Double>();
		
		//System.out.println("getting labs Bilirubin............");
		String sql7 = "select * from dbo.og_icu_lab where ClinicNo= ? and LabTestDesc ='Bilirubin,Total'";
		List<Map<String, Object>> rows7 = jdbcTemplate
				.queryForList(sql7, clnum);
		String desc7 = "";
		double res7 = 0;

		for (Map<String, Object> row7 : rows7) {
			// desc7=(String)row7.get("LabResultText");
			if (row7.get("LabTestResult") == null)
				continue;
			res7 = ((BigDecimal) row7.get("LabTestResult")).doubleValue();
			ll5.add(res7);
		}
		Collections.sort(ll5);
		if (ll5.isEmpty())
			desc7 = " ";
		else
			desc7 = ll5.getLast() + "";
		global.put("totalbili", desc7);

		LinkedList<Double> ll6 = new LinkedList<Double>();
		
		//System.out.println("getting labs Direct ............");
		String sql8 = "select * from dbo.og_icu_lab where ClinicNo= ? and LabTestDesc ='Bilirubin,Direct'";
		List<Map<String, Object>> rows8 = jdbcTemplate
				.queryForList(sql8, clnum);
		String desc8 = "";
		double res8 = 0;

		for (Map<String, Object> row8 : rows8) {
			// desc7=(String)row7.get("LabResultText");
			if (row8.get("LabTestResult") == null)
				continue;
			res8 = ((BigDecimal) row8.get("LabTestResult")).doubleValue();
			ll6.add(res8);
		}
		Collections.sort(ll6);
		if (ll6.isEmpty())
			desc8 = " ";
		else
			desc8 = ll6.getLast() + "";
		global.put("totalbilidir", desc8);

		//System.out.println("getting temperatures ............");
		String sql9 = "select * from dbo.og_icu_vital where ClinicNo = ? and measurement_type = 'Temperature-Manual' and measurement_value is not null order by measurement_datetime ";
		List<Map<String, Object>> rows9 = jdbcTemplate
				.queryForList(sql9, clnum);

		String res9 = "";
		for (Map<String, Object> row9 : rows9) {

			res9 = (String) row9.get("measurement_value");
			break;
		}
		global.put("tempermanul", res9);

		return global;
	}

	public ArrayList<NICUPatient> detailAdminData(String clnum, String sDate,
			String eDate) throws ParseException {

		String sql = "select * from og_icu_demo where ClinicNo= :clnumber ";

		ArrayList<NICUPatient> plist = new ArrayList<NICUPatient>();
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("clnumber", clnum);

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, params);
		SimpleDateFormat sf = new SimpleDateFormat("MMM dd yyyy");
		String adminDate = "";
		for (Map<String, Object> row : rows) {

			NICUPatient p = new NICUPatient();
			p.setClinicNumber(row.get("ClinicNo").toString());
			p.setPatientName((String) row.get("PatientName"));
			adminDate = sf.format(row.get("StartDateTime"));
			p.setAdminDt(adminDate);
			plist.add(p);
		}

		SimpleDateFormat sdfDate = new SimpleDateFormat("MM/dd/yyyy");

		for (NICUPatient p : plist) {
			Date d1 = new SimpleDateFormat("MMM dd yyyy").parse(p.getAdminDt());
			Hashtable<String, String> global = this.globalDataSets(clnum,
					sdfDate.format(d1));

			int flag = 0;
			if (global.containsKey("ABO/RH Blood Type")) {
				p.setAbo(global.get("ABO/RH Blood Type"));
				flag++;
			}

			if ((flag == 0) && global.containsKey("ABO+Rh Typing"))
				p.setAbo(global.get("ABO+Rh Typing"));

			Date adDate = new SimpleDateFormat("MMM dd yyyy").parse(p
					.getAdminDt());
			SimpleDateFormat sdfDate1 = new SimpleDateFormat("MM/dd/yyyy");
			String nextDate = sdfDate1.format(adDate.getTime() + 1000 * 60 * 60
					* 24);
			String hemoDate = sdfDate1.format(adDate);

			String sqlHemo = "select * from og_icu_lab  where ClinicNo= :clnumber and LabCollectionDtTm between :d1 and :d2 and LabTestDesc like '%Hematocrit%'";
			MapSqlParameterSource params2 = new MapSqlParameterSource();
			params2.addValue("clnumber", clnum);
			params2.addValue("d1", hemoDate);
			params2.addValue("d2", nextDate);
			// System.out.println(hemoDate+"  "+nextDate);
			List<Map<String, Object>> rows2 = jdbcTemplate.queryForList(
					sqlHemo, params2);
			String hema = "";
			for (Map<String, Object> row2 : rows2) {

				hema += (String) row2.get("LabResultText") + "; ";
			}
			// System.out.println(hema);
			p.setHema(hema);

		}
		return plist;
	}

	// this part has problem of getting target data, more details needed -------
	public Hashtable<String, String> getBedLocation(String clnum) {

		Hashtable<String, String> beds = new Hashtable<String, String>();
		String sql = "select  * from og_icu_bedlocation where clinicno = ?";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, clnum);
		String sDate = "";
		String sDate1, sDate2;
		String bedLocation = "";
		SimpleDateFormat sdfDate = new SimpleDateFormat("MM/dd/yyyy");
		for (Map<String, Object> row : rows) {

			sDate1 = sdfDate.format(row.get("RoomStartDateTime"));
			if (row.get("RoomStopDateTime") == null)
				continue;
			else {
				sDate2 = sdfDate.format(row.get("RoomStopDateTime"));
				sDate = sDate1 + "," + sDate2;
				bedLocation = (String) row.get("HospitalRoomBed");
				beds.put(sDate, bedLocation);
			}

		}

		// System.out.println(h.get("Erythrocytes"));

		return beds;
	}
	
	public Hashtable<String, String> getNurseUnit(String clnum) {

		Hashtable<String, String> nurse = new Hashtable<String, String>();
		String sql = "select  * from og_icu_bedlocation where clinicno = ?";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, clnum);
		String sDate = "";
		String sDate1, sDate2;
		String nurseUnit = "";
		SimpleDateFormat sdfDate = new SimpleDateFormat("MM/dd/yyyy");
		for (Map<String, Object> row : rows) {

			sDate1 = sdfDate.format(row.get("RoomStartDateTime"));
			if (row.get("RoomStopDateTime") == null)
				continue;
			else {
				sDate2 = sdfDate.format(row.get("RoomStopDateTime"));
				sDate = sDate1 + "," + sDate2;
				nurseUnit = (String) row.get("NurseUnit");
				nurse.put(sDate, nurseUnit);
			}

		}

		// System.out.println(h.get("Erythrocytes"));

		return nurse;
	}

	public NICUPatient modelPatient(String clnum, String targetDate)
			throws ParseException {

		String sql = "select top 1 ClinicNo, AdmissionDtTm, PatientName, PatientDOB, PatientGender, NurseUnit, BedLocation from og_icu_demo where ClinicNo= ? order by StartDateTime desc";

		RowMapper<NICUPatient> mapper = new RowMapper<NICUPatient>() {
			public NICUPatient mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				NICUPatient person = new NICUPatient();
				person.setClinicNumber(rs.getString("ClinicNo"));
				person.setPatientName(rs.getString("PatientName"));
				SimpleDateFormat sf = new SimpleDateFormat("MMM dd yyyy");

				person.setDateOfBirth(sf.format(rs.getTimestamp("PatientDOB")));
				person.setAdminDt(sf.format(rs.getTimestamp("AdmissionDtTm")));
				String nurseUnit = rs.getString("NurseUnit");
				String bed = rs.getString("BedLocation");
//				person.setUnit(nurseUnit + bed);
				person.setGender(rs.getString("PatientGender"));
				person.setNurseUnit(rs.getString("NurseUnit"));
				person.setBedLocation(rs.getString("BedLocation"));
				return person;
			}
		};

		NICUPatient p = (NICUPatient) this.jdbcTemplate.queryForObject(sql,
				mapper, clnum);

		SimpleDateFormat sdfDate = new SimpleDateFormat("MM/dd/yyyy");
		Date d1 = sdfDate.parse(targetDate);
		Date d2 = new SimpleDateFormat("MMM dd yyyy").parse(p.getAdminDt());
		if (d1.getTime() < d2.getTime()) {
			p.setPatientName("invalidDate");
			return p;
		}

		//System.out.println("processing lab data");
		Hashtable<String, String> labs = this.getLabs(clnum, targetDate);
		
		//System.out.println("processing global data");
		Hashtable<String, String> global = this.globalDataSets(clnum,
				targetDate);
		
		//System.out.println("processing vitals data");
		Hashtable<String, String> vitals = this.getVitals(clnum, targetDate);
		
		//System.out.println("processing fluids data");
		Hashtable<String, String> fluids = this.getFluids(clnum, targetDate);
		// Hashtable<String, String> flowsheets= this.getFlowSheets(clnum);
		Hashtable<String, String> beds = this.getBedLocation(clnum);
		Hashtable<String, String> nurse = this.getNurseUnit((clnum));
		
		if (beds.size() != 1) {

			Enumeration<String> enumKey = beds.keys();
			while (enumKey.hasMoreElements()) {
				String key = enumKey.nextElement();
				String co[] = key.split(",");
				String val = beds.get(key);
				Date d3 = sdfDate.parse(co[0]);
				Date d4 = sdfDate.parse(co[1]);

				if (d3.before(d1) && d4.after(d1)) {
					p.setBedLocation(val);
				}
			}

		}
		
		if (nurse.size() != 1) {

			Enumeration<String> enumKey = nurse.keys();
			while (enumKey.hasMoreElements()) {
				String key = enumKey.nextElement();
				String co[] = key.split(",");
				String val = nurse.get(key);
				Date d3 = sdfDate.parse(co[0]);
				Date d4 = sdfDate.parse(co[1]);

				if (d3.before(d1) && d4.after(d1)) {
					p.setNurseUnit((val));
				}
			}

		}

		int flag = 0;
		if (global.containsKey("ABO/RH Blood Type")) {
			p.setAbo(global.get("ABO/RH Blood Type"));
			flag++;
		}

		if ((flag == 0) && global.containsKey("ABO+Rh Typing"))
			p.setAbo(global.get("ABO+Rh Typing"));

		if (global.containsKey("DAT"))
			p.setCoomb(global.get("DAT"));

		if (vitals.containsKey("Weight(kg)"))
			p.setWeight(vitals.get("Weight(kg)"));
		if (vitals.containsKey("Length"))
			p.setLength(vitals.get("Length"));
		if (vitals.containsKey("Head Circumference"))
			p.setHead(vitals.get("Head Circumference"));

		if (fluids.containsKey("Breast Fed"))
			p.setBreastFed("Yes");
		else
			p.setBreastFed("No");

		if (fluids.containsKey("Breast Milk")
				|| fluids.containsKey("Breast Milk & Bovine HMF 24 cal/oz"))
			p.setBreastMilk("Yes");
		else
			p.setBreastMilk("No");

		if (fluids.containsKey("Breast Milk (Donor)"))
			p.setBreatDonor("Yes");
		else
			p.setBreatDonor("No");

		if (fluids.containsKey("CPN/PPN"))
			p.setTpn("Yes");
		else
			p.setTpn("No");

		if (fluids.containsKey("Lipids"))
			p.setLipids("Yes");
		else
			p.setLipids("No");

		if (fluids.containsKey("RBC Leuko-Reduced")
				|| fluids.containsKey("RBC Leuko-Reduced_")
				|| fluids.containsKey("RBC_") || fluids.containsKey("RBC"))
			p.setRedCell("Yes");
		else
			p.setRedCell("No");

		if (fluids.containsKey("Fresh Frozen Plasma"))
			p.setFreshFrozenPlasma("Yes");
		else
			p.setFreshFrozenPlasma("No");

		if (fluids.containsKey("Cryoprecipitate"))
			p.setCryoprecipitate("Yes");
		else
			p.setCryoprecipitate("No");

		if (fluids.containsKey("Platelets"))
			p.setPlatelets("Yes");
		else
			p.setPlatelets("No");

		Enumeration<String> enumKey = fluids.keys();
		String dex = "";
		String infantfla = "";
		while (enumKey.hasMoreElements()) {
			String key = enumKey.nextElement();
			// System.out.println(key);
			if (key.startsWith("D")) {
				dex += key + ";";
			}
			if (key.contains("Infant Formula")) {
				infantfla += key;
			}
		}
		p.setDextrose(dex);
		p.setInfantFormula(infantfla);

		// the flowsheet table is very slow, need to double check this table
		// String draintype="";
		// if(flowsheets.containsKey("Drain #2 Type"))
		// draintype+="Drain #2 Type";
		// if(flowsheets.containsKey("Drain #1 Type"))
		// draintype+="Drain #1 Type";
		// p.setDraintype(draintype);
		String oxy = "";
		String temp = "";
		String ven = "";
		Enumeration<String> e = vitals.keys();
		while (e.hasMoreElements()) {
			temp = e.nextElement();
			if (temp.contains("Oxygen Device"))
				oxy += temp + "; ";
			else if (temp.contains("Ventilat"))
				ven += vitals.get(temp) + "; ";
		}

		p.setOxy(oxy);
		p.setVentil(ven);
		p.setFio2(global.get("highestFio2"));
		p.setNiox(global.get("highestniox"));
		p.setAvespo2(global.get("avespo2"));
		p.setO2(global.get("highesto2"));
		p.setTotalbili(global.get("totalbili"));
		p.setTotalbilidir(global.get("totalbilidir"));
		p.setAmphetamine(labs.get("Amphetamine"));
		p.setCocaine(labs.get("Cocaine"));
		p.setMethamphetamin(labs.get("Methamphetamine"));
		p.setOpiate(labs.get("Opiate"));
		p.setPhe(labs.get("Phencyclidine"));
		p.setTetra(labs.get("Tetrahydrocannabinol"));
		// p.setHema(labs.get("Hematocrit"));

		Enumeration<String> labKey = labs.keys();
		String lab = "";
		while (labKey.hasMoreElements()) {
			lab = labKey.nextElement();
			// System.out.println(lab);
			if (lab.equalsIgnoreCase("Methamphetamine")) {
				System.out.println(labs.get(lab));
			}
		}

		Date adDate = new SimpleDateFormat("MMM dd yyyy").parse(p.getAdminDt());
		SimpleDateFormat sdfDate1 = new SimpleDateFormat("MM/dd/yyyy");
		String nextDate = sdfDate1.format(adDate.getTime() + 1000 * 60 * 60
				* 24);
		String hemoDate = sdfDate1.format(adDate);

		String sqlHemo = "select * from og_icu_lab  where ClinicNo= :clnumber and LabCollectionDtTm between :d1 and :d2 and LabTestDesc like '%Hematocrit%'";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("clnumber", clnum);
		params.addValue("d1", hemoDate);
		params.addValue("d2", nextDate);
		// System.out.println(hemoDate+"  "+nextDate);
		List<Map<String, Object>> rows2 = jdbcTemplate.queryForList(sqlHemo,
				params);
		String hema = "";
		for (Map<String, Object> row2 : rows2) {

			hema += (String) row2.get("LabResultText") + "; ";
		}
		// System.out.println(hema);
		p.setHema(hema);

		// bilirubin calculations here:
		String biliNextDate = sdfDate1.format(sdfDate1.parse(targetDate)
				.getTime()
				+ 1000 * 60 * 60 * 24);
		LinkedList<Double> ll5 = new LinkedList<Double>();
		String sql7 = "select * from dbo.og_icu_lab where ClinicNo= :clnumber and LabTestDesc ='Bilirubin,Total' and LabCollectionDtTm between :d1 and :d2";
		MapSqlParameterSource paramsBili = new MapSqlParameterSource();
		paramsBili.addValue("clnumber", clnum);
		paramsBili.addValue("d1", targetDate);
		paramsBili.addValue("d2", biliNextDate);
		List<Map<String, Object>> rows7 = jdbcTemplate.queryForList(sql7,
				paramsBili);
		String desc7 = "";
		double res7 = 0.0;

		for (Map<String, Object> row7 : rows7) {

			if (row7.get("LabTestResult") == null)
				continue;
			res7 = ((BigDecimal) row7.get("LabTestResult")).doubleValue();
			ll5.add(res7);
		}
		Collections.sort(ll5);
		if (ll5.isEmpty())
			desc7 = " ";
		else
			desc7 = ll5.getLast() + "";
		p.setDailybilitotal(desc7);

		LinkedList<Double> ll6 = new LinkedList<Double>();
		String sql8 = "select * from dbo.og_icu_lab where ClinicNo= :clnumber and LabTestDesc ='Bilirubin,Direct' and LabCollectionDtTm between :d1 and :d2";
		List<Map<String, Object>> rows8 = jdbcTemplate.queryForList(sql8,
				paramsBili);
		String desc8 = "";
		double res8 = 0.0;

		for (Map<String, Object> row8 : rows8) {
			// desc7=(String)row7.get("LabResultText");
			if (row8.get("LabTestResult") == null)
				continue;
			res8 = ((BigDecimal) row8.get("LabTestResult")).doubleValue();
			ll6.add(res8);
		}
		Collections.sort(ll6);
		if (ll6.isEmpty())
			desc8 = " ";
		else
			desc8 = ll6.getLast() + "";
		p.setDailybilidirect(desc8);

		// System.out.print(labs.get("Hematocrit"));
		p.setMnn(labs.get("Minn Newborn Screen"));
		p.setTemperature(global.get("tempermanul"));
		p.setReportDate(targetDate);

		return p;
	}

	public ArrayList<NICUPatient> modelPatients(String clnum, String sDate,
			String eDate) throws ParseException {

		ArrayList<NICUPatient> ps = new ArrayList<NICUPatient>();
		SimpleDateFormat sdfDate = new SimpleDateFormat("MM/dd/yyyy");
		Date d0 = sdfDate.parse(sDate);
		Date dn = sdfDate.parse(eDate);
		while (d0.before(dn)) {
			ps.add(modelPatient(clnum, sdfDate.format(d0)));
			d0 = new Date(d0.getTime() + 1000 * 60 * 60 * 24);
		}
		return ps;
	}

}
