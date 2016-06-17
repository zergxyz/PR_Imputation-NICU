package amalgaI;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

@Controller
public class CCDashController extends AbstractController {

	@Resource(name = "ICUDataService")
	private ICUDataService icus;
	
	@Resource(name = "ICUDevDataService")
	private ICUDevDataService icds;
	
	@Resource(name = "ORDataService")
	private ORDataService ors;
	
	@Resource(name = "EODataService")
	private EODataService eoDS;
	
	
	private ArrayList<VTStat> models;
	private ArrayList<VTMReport> repoByMode;
	
	
	@RequestMapping(value = "/ccLogin", method = RequestMethod.GET)
	public String ccLogin(Model model) throws ParseException {
		
		
		//List<ICUData> icuData = icus.getICUData();
		List<EOData> eoData= eoDS.getEOData();
		ArrayList<LVCPatient> lvcs = new ArrayList<LVCPatient>();
		
		
		
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
		
//		for (EOData p2 : eoData) {
//			LVCPatient lvc = new LVCPatient();
//			lvc.setClinicNumber(p2.getMcNumber());
//			lvc.setmDT(p2.getMessageDT());
//			lvc.setLevelOfCare(p2.getMessageText());
//			lvcs.add(lvc);
//		}
//
//		icus.storeReportsWithoutICUADmin(lvcs);
		
		

		
		

		return "loginPage";
	}
	
	@RequestMapping(value = "/ccHome", method = RequestMethod.GET)
	public String ccHome(Model model) throws Exception {
		
		
		return "ccDashboard";
	}
	
//	@RequestMapping(value = "/ccReport", method = RequestMethod.GET)
//	public String ccReport(Model model) {
//
//		
//		List<LVCPatient>ps =icus.lvcReport();
//		model.addAttribute("plist", ps);
//		return "ccReport";
//	}
	
	@RequestMapping(value = "/ccReport", method = RequestMethod.GET)
	public String ccReport(@RequestParam("icu") String icu,
			@RequestParam("sDate") String sDate, 
			@RequestParam("eDate") String eDate, Model model) {

		
//		ArrayList<VTModel> pss = ors.getVTICUData(icu, sDate, eDate);
//		
//		String iculist = icu.substring(3);
//		String a1 [] = iculist.split(",");
//		ArrayList<String> icus = new ArrayList<String> ();
//		for(int i=0; i<a1.length; i++) {
//			
//			if(icus.contains(a1[i]))
//				continue;
//			else
//				icus.add(a1[i]);
//		}
//		ArrayList<VTStat>report = ors.VTReport(pss);
//		
//		model.addAttribute("plist", report);
//		model.addAttribute("icus", icus);
		
		ArrayList<ArrayList<VTModel>> modes = ors.getVTICUData(icu, sDate, eDate);
		//System.out.println("size is: "+modes.size());
		ArrayList<VTStat> report = new ArrayList<VTStat>();
		for(ArrayList<VTModel> vtm: modes) {
			
			 report.addAll(ors.VTReport(vtm));
			
		}
		
		ArrayList<VTMReport> vtmr = new ArrayList<VTMReport>();
		vtmr=ors.reportByMode(report);
		System.out.print("Mode size is "+vtmr.size());
		model.addAttribute("plist", report);
		model.addAttribute("vtrepo", vtmr);
		
		this.models= report;
		this.repoByMode = vtmr;
		
		String iculist = icu.substring(3);
		String a1 [] = iculist.split(",");
		ArrayList<String> icus = new ArrayList<String> ();
		for(int i=0; i<a1.length; i++) {
			
			if(icus.contains(a1[i]))
				continue;
			else
				icus.add(a1[i]);
		}
		
		model.addAttribute("icus", icus);
		return "ccReport";
	}

	@RequestMapping(value = "/xmlReport", method = RequestMethod.GET)
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		String output =
			ServletRequestUtils.getStringParameter(request, "output");
 
		//dummy data
			ModelAndView mav = new ModelAndView("ExcelRevenueSummary","re",models);
			mav.addObject("rbm", repoByMode);
			
 
//		if(output ==null || "".equals(output)){
//			//return normal view
//			return new ModelAndView("RevenueSummary","revenueData",revenueData);
// 
//		}else if("EXCEL".equals(output.toUpperCase())){
//			//return excel view
			return mav;
 
//		}else{
//			//return normal view
//			return new ModelAndView("RevenueSummary","revenueData",revenueData);
// 
//		}	
		
	}


}
