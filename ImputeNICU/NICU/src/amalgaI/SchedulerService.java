package amalgaI;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Scheduler for handling jobs
 */
@Service
public class SchedulerService {

	protected static Logger logger = Logger.getLogger("service");

	@Autowired
	@Qualifier("syncWorker")
	private SyncWorker worker;

	@Resource(name="MetricReportService")
	private MetricReportService mrs;
	
//	@Resource(name = "mailer")
//	private Mailer mm;

	// @Scheduled(cron="*/50 * * * * ?")
	// @Scheduled(fixedRate=30000)
	// public void doSchedule() {
	// //System.out.println("Starting data retrieval at " + new
	// Date(System.currentTimeMillis()));
	// worker.lvCareJob();
	// }

	@Scheduled(cron = "0 15 09 ? * MON")
	public void seSchedule() throws ParseException, Exception, IOException {
		// System.out.println("Starting data retrieval at " + new
		// Date(System.currentTimeMillis()));
		worker.work();
		
		String subject = "Level of care Job Sucess";
		String content= "ICU datamart level of care job is done!";
		String[] recipients = {"fan.lei@mayo.edu"};
		String[] cc = {};
		String from = "fan.lei@mayo.edu";

		Mailer.postMail(subject, content, recipients, cc, from);
	}
	
//	@Scheduled(cron = "0 20 09 ? * MON")
//	public void weeklyMetricReport() throws ParseException, Exception, IOException {
//
//		//weekly metric report 
//		mrs.generateReport();
//
//	}

}
