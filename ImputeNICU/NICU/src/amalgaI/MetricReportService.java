package amalgaI;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




import au.com.bytecode.opencsv.CSVWriter;

@Service("MetricReportService")
@Transactional
public class MetricReportService {

	private DataSource dataSource;
	private SimpleJdbcTemplate jdbcTemplate;

	@Resource(name = "dataSource-ORData")
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new SimpleJdbcTemplate(dataSource);
	}
	
	public void generateReport() throws Exception {
		
		//create metric report csv file 
		String sql = "select distinct t1.ClinicNumber, t1.PatientAge,  t1.Procedures  from ( select ClinicNumber, EnvStarted, LocationStarted, EnvironmentTypeShortName, LocationDesc, ProcedureType, Procedures, ASAType, PatientAge, PatientAgeUnit from OR_DataRep_Prod.dbo.OR_Demo_Summary where EnvStarted between DateAdd(Day, DateDiff(Day, 8, GetDate()), 0) and DateAdd(Day, DateDiff(Day, 1, GetDate()), 0)  and EnvironmentTypeShortName='Anes' and LocationDesc like 'SMH OR%' and ProcedureType like 'Cardi%' ) as t1, (select clinicNumber, LocationStarted, max(EventDocumentedDatetime) as EventTime from OR_DataRep_Prod.dbo.OR_Events_2014 where EventDocumentedDatetime between DateAdd(Day, DateDiff(Day, 8, GetDate()), 0) and DateAdd(Day, DateDiff(Day, 1, GetDate()), 0) and EventDesc like '%bypass%' group by ClinicNumber, LocationStarted ) as t2 where t1.ClinicNumber=t2.clinicNumber and t1.LocationStarted=t2.LocationStarted and t1.PatientAge >=21";
		RowMapper<MetricReportModel> mapper = new RowMapper<MetricReportModel>() {
			public MetricReportModel mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				MetricReportModel person = new MetricReportModel();

				person.setClinicNum(rs.getString("ClinicNumber"));
				person.setPatientAge(rs.getString("PatientAge"));
				person.setProcedures(rs.getString("Procedures"));
				
				return person;
			}
		};
		
		List<MetricReportModel> mrm = jdbcTemplate.query(sql, mapper);
		
		//StringWriter sw = new StringWriter();
		BufferedWriter out = new BufferedWriter(new FileWriter("metricReport.csv"));
	    CSVWriter writer = new CSVWriter(out);
	    String [] header = { "ClinicNo" , "Age", "Procedure" };
	    writer.writeNext(header);
	    //String [] data;
	    for(MetricReportModel m: mrm) {
	    	writer.writeNext(new String [] {m.getClinicNum(), m.getPatientAge(),
	    			m.getProcedures()});
	    }
	    out.close();
		writer.close();
		
		String subject = "Metric Weekly Report Job Success";
		String content= "report by Lei Fan";
		
		String[] recipients = {"fan.lei@mayo.edu", "Liedl.Lavonne@mayo.edu",
				"Meade.Laurie@mayo.edu"};

		String from = "fan.lei@mayo.edu";

		Mailer.mailWithAttachment(subject, content, recipients, from);
	}
}
