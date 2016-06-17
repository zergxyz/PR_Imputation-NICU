package amalgaI;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component("syncWorker")
public class SyncWorker implements Worker {

	protected static Logger logger = Logger.getLogger("service");

	@Resource(name = "ICUDataService")
	private ICUDataService icuDS;
	
	@Resource(name = "EODataService")
	private EODataService eoDS;

	
	public void work() throws ParseException {
		
		
		
		List<EOData> eoData= eoDS.getEOData();
		ArrayList<LVCPatient> lvcs = new ArrayList<LVCPatient>();
		
		for (EOData p2 : eoData) {
			LVCPatient lvc = new LVCPatient();
			lvc.setClinicNumber(p2.getMcNumber());
			lvc.setmDT(p2.getMessageDT());
			lvc.setLevelOfCare(p2.getMessageText());
			lvcs.add(lvc);
		}

		icuDS.storeReportsWithoutICUADmin(lvcs);
		
		
		
	}
	
	public void lvCareJob() {
		
//		this.exDate=new Date(System.currentTimeMillis()).toString();
//        List<ICUPatient> his = ps.getICUPatients(); 
//        ps.d=exDate;
//		System.out.println(his.get(0).getClinicNo());

		
//		List<ICUData> icuData = icuDS.getICUData();
//		List<EOData> eoData= eoDS.getEOData();
//		ArrayList<LVCPatient> lvcs = new ArrayList<LVCPatient>();
//		
//		
//		
//		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		for (ICUData p : icuData) {
//
//			for (EOData p2 : eoData) {
//
//				Date pDateSa = sf.parse(p.getDemoStartDT());
//				Date pDateSt;
//				if (p.getDemoStopDT().equalsIgnoreCase("still in ICU"))
//					pDateSt = new Date();
//				else {
//					pDateSt = sf.parse(p.getDemoStopDT());
//				}
//				Date p2DateM = sf.parse(p2.getMessageDT());
//
//				if (p.getDemoClinicNo().equalsIgnoreCase(p2.getMcNumber())
//					&& pDateSa.before(p2DateM) && pDateSt.after(p2DateM)) {
//					LVCPatient lvc = new LVCPatient();
//					lvc.setClinicNumber(p.getDemoClinicNo());
//					lvc.setmDT(p2.getMessageDT());
//					lvc.setStartDT(p.getDemoStartDT());
//					lvc.setStopDT(p.getDemoStopDT());
//					lvc.setLevelOfCare(p2.getMessageText());
//					lvcs.add(lvc);
//				}
//			}
//		}
//
//		icuDS.storeReports(lvcs);
	}
	
}

