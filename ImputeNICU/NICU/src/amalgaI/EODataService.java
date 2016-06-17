package amalgaI;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("EODataService")
@Transactional
public class EODataService {

	private DataSource dataSource;
	private SimpleJdbcTemplate jdbcTemplate;

	@Resource(name = "dataSource-EOData")
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new SimpleJdbcTemplate(dataSource);
	}
	
	 public List<EOData> getEOData() {
			
		   
		  // Prepare our SQL statement
		  String sql = "select MCNumber, MessageDateTime, substring(MessageText, charindex('Level of Care:', MessageText), (charindex('^XSERVI||||||||||||', MessageText)-charindex('Level of Care', MessageText))) as metxt from dbo.EnterpriseOrdersMessage where MessageDateTime between DateAdd(Day, DateDiff(Day, 7, GetDate()), 0) and DateAdd(Day, DateDiff(Day, 0, GetDate()), 0) and MessageText like '%Level of Care:%'";
		
		  //String sql = "select MCNumber, MessageDateTime, substring(MessageText, charindex('Level of Care:', MessageText), (charindex('^XSERVI||||||||||||', MessageText)-charindex('Level of Care', MessageText))) as metxt from dbo.EnterpriseOrdersMessage where MessageDateTime between '01/01/2014' and '09/10/2014' and MessageText like '%Level of Care:%'";
			
		  RowMapper<EOData> mapper = new RowMapper<EOData>() {  
		         public EOData mapRow(ResultSet rs, int rowNum) throws SQLException {
		        	 EOData eop = new EOData();
		        	 SimpleDateFormat sf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		        	 eop.setMcNumber(rs.getString("MCNumber"));
		        	 eop.setMessageDT(sf.format(rs.getTimestamp("MessageDateTime")));
		        	 eop.setMessageText(rs.getString("metxt"));
		             return eop;
		         } 
		     };
		   
		  // Retrieve all
		  return jdbcTemplate.query(sql, mapper);
	  }

}
