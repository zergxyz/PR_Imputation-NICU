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

@Service("ICUDevDataService")
@Transactional
public class ICUDevDataService {
	
	private DataSource dataSource;
	private SimpleJdbcTemplate jdbcTemplate;

	@Resource(name = "dataSource")
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new SimpleJdbcTemplate(dataSource);
	}
	
	public void storeReports(ArrayList<LVCPatient> lvcs) {

		String sql = "If Not Exists (select * from ICU_DataRep.dbo.og_icu_levelofcare where ClinicNo=:clnum and MessageDateTime=:mDT) begin insert into ICU_DataRep.dbo.og_icu_levelofcare (ClinicNo, StartDateTime, MessageDateTime, StopDateTime, LevelOfCareMessage) "
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
