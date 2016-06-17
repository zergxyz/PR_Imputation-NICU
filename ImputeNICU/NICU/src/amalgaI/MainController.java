package amalgaI;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;


@Controller
@SessionAttributes
public class MainController {

	protected static Logger logger = Logger.getLogger("controller");
	  
	 @Resource(name="InPatientService")
	 private InPatientService ips;
	 public InPatient tempPatient;
	// public List<InPatient> container;
	 
	 
	 
//	 @RequestMapping( value = "/patientsinfo", method = RequestMethod.POST)
//	    public String getPatients(@PathVariable("clinicNumber") String id, BindingResult bindingResult, Model model) {
//	      
//	     logger.debug("Received request to show all persons");
//	      
//	     // Retrieve all persons by delegating the call to PersonService
//	     InPatient InPatient = ips.getTargetPatient(id);
//	     model.addAttribute(InPatient);
//	     
//	     return "patientsinfo";
//	 }
	 
//	 @RequestMapping("/")
//	 public String home() {
//	     return "redirect:/MyJsp";
//	 }


	 
	 @RequestMapping(value = "/welcome", method = RequestMethod.GET)
	 public String mobileLogin(Map<String, Object> model){

	     return "welcome";
	 }
	 
	 @RequestMapping(value = "/t1.jsp", method = RequestMethod.POST)
	 public String mobileMainScreen(@RequestParam("j_username") String usrname, 
			 						@RequestParam("j_password") String pwd, Model model){
		 
		 System.out.println("t1 is displaying");
		 
		 if(usrname.isEmpty()||pwd.isEmpty())
			 return "welcome";
		 else 
		 {
			 try {
				 DirContext dc =Ldap.connect(usrname, pwd);
			 			
			 } catch (NamingException e) {
				 return "welcome";
			 }
		 
		 
			 return "t1";
		 }
	 }
	 
	 @RequestMapping(value = "/t1.jsp", method = RequestMethod.GET)
	 public String mobileMainScreen( Model model){
		 
			 return "t1";
	 }
	 
	 @RequestMapping(value = "/emptyPatientInfo.jsp", method = RequestMethod.GET)
	 public String mobileEmptyNewReport(Map<String, Object> model){

	     return "emptyPatientInfo";
	 }
	 
	 @RequestMapping(value = "/searchFromHistory.jsp", method = RequestMethod.GET)
	 public String mobileScopeHistory(Map<String, Object> model){

	     return "searchFromHistory";
	 }
	 

	 
	 @RequestMapping(value = "/t2.jsp", method = RequestMethod.GET)
	 public String mobileHistoryList(Model model){
		 
	     List<InPatient> his = ips.getAll();
		   //  this.container = his;
		     model.addAttribute("InPatient", new InPatient());
		     model.addAttribute("persons", his);

	     return "t2";
	 }
	 
	 @RequestMapping(value = "/searchDialog.jsp", method = RequestMethod.GET)
	 public String mobileSearchDialog(){

	     return "searchDialog";
	 }
	 
	 @RequestMapping(value = "/tPatientInfo.jsp", method = RequestMethod.POST)
	 public String mobileGetPatient(@RequestParam("clinicNumber") String clnum, Model model) {

		 tempPatient = ips.getTargetPatient(clnum);
		 if(tempPatient==null) 
		 {
			 return "emptyPatientInfo";
			 
		 }
		 else {
			 
			 model.addAttribute("InPatient", tempPatient);
		 
			 return "tPatientInfo"; 
	     }
	 }
	 
// modify this function later	 
	 @RequestMapping( value = "/patientsinfo", method = RequestMethod.POST)
	    public String getPatients(@ModelAttribute("InPatient") InPatient ip, Model model) {
	     
		 tempPatient = ips.getTargetPatient(ip.getClinicNumber());
	     model.addAttribute("inpatient",  tempPatient);
	     
	     return "patientsinfo";
	 }
	 
	 
	 
	 
//	 @RequestMapping( value = "/History2", method = RequestMethod.POST)
//	    public String getTargetH(@ModelAttribute("InPatient") InPatient ip, Model model) {
//	     
//		 tempPatient = ips.getTargetPatientFromHis(ip.getCallCompleteDtm());
//	     model.addAttribute("inpatient",  tempPatient);
//	     
//	     return "History2";
//	 }
	 
	 @RequestMapping( value = "/History2", method = RequestMethod.POST)
	    public String getTargetH(@RequestParam("date1") String date1, @RequestParam("date2") String date2, Model model) {
	     
		 List<InPatient> his = ips.getAllFromRange(date1, date2);
	     model.addAttribute("persons", his);
	     
	     return "History2";
	 }
	 
	 @RequestMapping(value = "/t3.jsp", method = RequestMethod.POST)
	 public String mobileScopeHistoryList(@RequestParam("comDate1") String date1, @RequestParam("comDate2") String date2, Model model){
		 	
		 SimpleDateFormat sdfDate = new SimpleDateFormat("MM/dd/yyyy");
		 if(date1.isEmpty()||date1.isEmpty())
		 {
			 date1=sdfDate.format(new Date());
			 date2=sdfDate.format(new Date());
		 }
		 
		 List<InPatient> his = ips.getAllFromRange(date1, date2);
	     model.addAttribute("persons", his);

	     return "t3";
	 }
	 
	 
	 // should be modified later 
	 @RequestMapping(value = "/pdf", method = RequestMethod.GET)
	    public ModelAndView handleRequestInternal(HttpServletRequest request,
	    		HttpServletResponse response) throws Exception  
	   {
		// ModelAndView model = new ModelAndView();
//		 Map<String,String> revenueData = new HashMap<String,String>();
//			revenueData.put("SPO2", tempPatient.getSpo2());
//			revenueData.put("Name", tempPatient.getPatientName());

//			InPatient paReport = new InPatient();
//			paReport.setPatientName(tempPatient.getPatientName());
//			paReport.setSpo2(tempPatient.getSpo2());
//			paReport.setExtraChestComp(tempPatient.getExtraChestComp());
		 // multiReport is the View of our application
		 InPatient paReport= tempPatient;

		 ModelAndView mo = new ModelAndView("PDFPage","InPatient", paReport);

	  
		 return mo;
	  
	 }
	 
	 
	 @RequestMapping( value = "/reports", method = RequestMethod.POST)
	    public String getReport(@ModelAttribute("InPatient") InPatient ip, Model model) {
	     
		 ip.setPatientName(tempPatient.getPatientName());
		 ip.setGender(tempPatient.getGender());
		 ip.setSpo2(tempPatient.getSpo2());
		 ip.setClinicNumber(tempPatient.getClinicNumber());
		 ip.setDateOfBirth(tempPatient.getDateOfBirth());
		 ip.setHeartRate(tempPatient.getHeartRate());
		 ip.setAdminDt(tempPatient.getAdminDt());
		 ip.setBloodPressure(tempPatient.getBloodPressure());
		 ip.setResp(tempPatient.getResp());
		 ip.setTemperture(tempPatient.getTemperture());
		 ips.storeReports(ip);
		 tempPatient =ip;
	     model.addAttribute("inpatient",  tempPatient);
	     
	     return "reports";
	 }
	 
	 // do the same function like reports.jsp
	 @RequestMapping(value = "/formTesting.jsp", method = RequestMethod.POST)
	 public String mobileStoreReprot(@RequestParam("pager") String pager, 
			 						 @RequestParam("pRequest") String pres, 
			 						 @RequestParam("comDate") String comDate,
			 						 @RequestParam("comTime") String comTime,
			 						 @RequestParam("cliStat") String cliStat,
			 						 @RequestParam("diagnostic") String diagnostic,
			 						 @RequestParam("pReason") String pReason,
			 						 @RequestParam("chestInfo") String chestInfo,
			 						 @RequestParam("avpu") String avpu,
			 						 @RequestParam("canp") String canp,
			 						 @RequestParam("bagVen") String bagVen,
			 						 @RequestParam("intubation") String intu,
			 						 @RequestParam("oxy") String oxy,
			 						 @RequestParam("actions") String act,
			 						 @RequestParam("edStat") String edStat,
			 						 @RequestParam("mtherapy") String mtherapy,
			 						 @RequestParam("dnr") String dnr,
			 						 Model model) {

		 InPatient nip= new InPatient();
		 nip.setPscPager(pager);
		 nip.setPersonalRequest(pres);
		 
		 //set the correct format for the database to store this time stamp
		
		 String comDTime=comDate+" "+comTime;
		 SimpleDateFormat sdfDate = new SimpleDateFormat("MM/dd/yyyy HH:mm a");
		 if(comDate.isEmpty()||comDate.contains("1900"))
			 comDTime=sdfDate.format(new Date());
		 
		 nip.setCallCompleteDtm(comDTime);
		 
		 nip.setExtraChestComp(chestInfo);
		 nip.setPaClinicStatus(cliStat);
		 nip.setDiagnostic(diagnostic);
		 nip.setPrimaryReason(pReason);
		 nip.setAvpuScore(avpu);
		 nip.setCpapNIPPY(canp);
		 nip.setBagMaskVen(bagVen);
		 nip.setIntubation(intu);
		 if(act.contains("Cardioversion"))
		 	nip.setCardioversion("Yes");
		 else
			 nip.setCardioversion("No");
		 
		 if(act.contains("Defibrillation"))
			 	nip.setDefibrillation("Yes");
		 else
				nip.setDefibrillation("No");
		 
		 if(act.contains("Bolus Fluid Administration"))
			 	nip.setBolusFluid("Yes");
		 else
				nip.setBolusFluid("No");
		 nip.setDNR(dnr);
		 nip.setMediThearapy(mtherapy);
		 nip.setPatientStatus(edStat);
		 
		 nip.setPatientName(tempPatient.getPatientName());
		 nip.setGender(tempPatient.getGender());
		 nip.setSpo2(tempPatient.getSpo2());
		 nip.setClinicNumber(tempPatient.getClinicNumber());
		 nip.setDateOfBirth(tempPatient.getDateOfBirth());
		 nip.setHeartRate(tempPatient.getHeartRate());
		 nip.setAdminDt(tempPatient.getAdminDt());
		 nip.setBloodPressure(tempPatient.getBloodPressure());
		 nip.setResp(tempPatient.getResp());
		 nip.setTemperture(tempPatient.getTemperture());
		 ips.storeReports(nip);

		 
		 
		// model.addAttribute("InPatient", nip);
	     return "formTesting";
	 }
	 
	 @RequestMapping(value = "/formTesting2.jsp", method = RequestMethod.POST)
	 public String mobileStoreEmptyReprot( @RequestParam("pager") String pager, 
			 						 @RequestParam("pRequest") String pres, 
			 						 @RequestParam("comDate") String comDate,
			 						 @RequestParam("comTime") String comTime,
			 						 @RequestParam("cliStat") String cliStat,
			 						 @RequestParam("diagnostic") String diagnostic,
			 						 @RequestParam("pReason") String pReason,
			 						 @RequestParam("chestInfo") String chestInfo,
			 						 @RequestParam("avpu") String avpu,
			 						 @RequestParam("canp") String canp,
			 						 @RequestParam("bagVen") String bagVen,
			 						 @RequestParam("intubation") String intu,
			 						 @RequestParam("oxy") String oxy,
			 						 @RequestParam("actions") String act,
			 						 @RequestParam("edStat") String edStat,
			 						 @RequestParam("mtherapy") String mtherapy,
			 						 @RequestParam("dnr") String dnr,
			 						 @RequestParam("pName") String pName,
			 						 @RequestParam("cliNum") String cliNum,
			 						 @RequestParam("gender") String gender,
			 						 @RequestParam("birthDate") String birthDate,
			 						 @RequestParam("temperature") String temperature,
			 						 @RequestParam("bp") String bp,
			 						 @RequestParam("heartRate") String heartRate,
			 						 @RequestParam("spo") String spo,
			 						 @RequestParam("adDate") String adDate,
			 						 @RequestParam("adTime") String adTime,
			 						 @RequestParam("resp") String resp,
			 						 Model model) {

		 InPatient nip= new InPatient();
		 nip.setPscPager(pager);
		 nip.setPersonalRequest(pres);
		 
		 //set the correct format for the database to store this time stamp
		 String comDTime=comDate+" "+comTime;
		 SimpleDateFormat sdfDate = new SimpleDateFormat("MM/dd/yyyy HH:mm a");
		 if(comDate.isEmpty()||comDate.contains("1900"))
			 comDTime=sdfDate.format(new Date());
		 nip.setCallCompleteDtm(comDTime);
		 
		 String adDTime=adDate+" "+adTime;
		 if(adDate.isEmpty()||adDate.contains("1900"))
			 adDTime=sdfDate.format(new Date());
		 
		 nip.setAdminDt(adDTime);
		 
		 
		 nip.setExtraChestComp(chestInfo);
		 nip.setPaClinicStatus(cliStat);
		 nip.setDiagnostic(diagnostic);
		 nip.setPrimaryReason(pReason);
		 nip.setAvpuScore(avpu);
		 nip.setCpapNIPPY(canp);
		 nip.setBagMaskVen(bagVen);
		 nip.setIntubation(intu);
		 if(act.contains("Cardioversion"))
		 	nip.setCardioversion("Yes");
		 else
			 nip.setCardioversion("No");
		 
		 if(act.contains("Defibrillation"))
			 	nip.setDefibrillation("Yes");
		 else
				nip.setDefibrillation("No");
		 
		 if(act.contains("Bolus Fluid Administration"))
			 	nip.setBolusFluid("Yes");
		 else
				nip.setBolusFluid("No");
		 nip.setDNR(dnr);
		 nip.setMediThearapy(mtherapy);
		 nip.setPatientStatus(edStat);
		 
		 nip.setPatientName(pName);
		 nip.setGender(gender);
		 nip.setSpo2(spo);
		 nip.setClinicNumber(cliNum);
		 nip.setDateOfBirth(birthDate);
		 nip.setHeartRate(heartRate);
		 nip.setBloodPressure(bp);
		 nip.setResp(resp);
		 nip.setTemperture(temperature);
		 ips.storeReports(nip);

		 
		 
		// model.addAttribute("InPatient", nip);
	     return "formTesting2";
	 }
	 
	 
	 @RequestMapping( value = "/history", method = RequestMethod.GET)
	    public String retrieveHistory(Model model) {
	     
	     List<InPatient> his = ips.getAll();
	   //  this.container = his;
	     model.addAttribute("InPatient", new InPatient());
	     model.addAttribute("persons", his);
	     return "history";
	 }
	 
	 
//	 @RequestMapping( value = "/newpdf", method = RequestMethod.GET)
//	    public ModelAndView checkReport( @RequestParam("callCompleteDtm")String cdate, Model model) {
//	     
//		 InPatient ip = ips.getTargetPatientFromHis( cdate);
//		 
//		ModelAndView mo = new ModelAndView("PDFPage","InPatient", ip);
//		 
//		// model.addAttribute("inpatient", ip);
//	     
//	     return mo;
//	 }
	 
	 // this one has a problem, right now the table in the database can only handle 
	 // different completedatetime patients. A newer table should be set up based on their
	 //report id and make it work for same date patient.
	 @RequestMapping( value = "/recordContents", method = RequestMethod.GET)
	 	public String mobileGetRecordFromList (@RequestParam("callCompleteDtm")String cdate, Model model) {
		 
		 InPatient ip = ips.getTargetPatientFromHis( cdate);
		 model.addAttribute("InPatient", ip);
		 
		 return "recordContents";
	 }
	 
//	 @RequestMapping( value = "/newpdf1", method = RequestMethod.GET)
//	    public ModelAndView checkReport1( @RequestParam("clinicno")String num, @RequestParam("callCompleteDtm")String cdate, Model model) {
//	     
//		 InPatient ip = ips.getTargetPatientFromHis1(num, cdate);
//		 
//		ModelAndView mo = new ModelAndView("PDFPage","InPatient", ip);
//		 
//		// model.addAttribute("inpatient", ip);
//	     
//	     return mo;
//	 }
	 
	 
	 }
	 
	 
	 
	
