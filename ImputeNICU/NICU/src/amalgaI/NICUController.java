package amalgaI;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
public class NICUController {

	@Resource(name = "NICUService")
	private NICUService nicus;
	
	@Resource(name="MetricReportService")
	private MetricReportService mrs;

	// default view will show all current patients in NICU
	@RequestMapping(value = "/nicuHome", method = RequestMethod.GET)
	public String nicuReportHome(Model model) throws Exception {

		List<NICUPatient> his = nicus.getAllPatients();
		// ps.getLabs();

		model.addAttribute("Patient", new NICUPatient());
		model.addAttribute("persons", his);

		return "nicuHome";
	}

	// when you select the specific patient, you will see his/her admission
	// information records here
	@RequestMapping(value = "/tpb", method = RequestMethod.GET)
	public String tpb(@RequestParam("clinicNumber") String clnum, Model model)
			throws ParseException {

		ArrayList<NICUPatient> pss = nicus.adminInfo(clnum);
		model.addAttribute("patient", pss);
		return "tpboot";
	}

	@RequestMapping(value = "/hist", method = RequestMethod.GET)
	public String history(@RequestParam("startDate") String sdate,
			@RequestParam("endDate") String edate, Model model) throws ParseException {

		ArrayList<NICUPatient> his =nicus.getHistoricalPatients(sdate, edate);
		model.addAttribute("persons", his);
		
		return "history";
	}
	
	
	// the details information for individual patient
	@RequestMapping(value = "/bootpd", method = RequestMethod.GET)
	public String bootPatientDetails(
			@RequestParam("clinicNumber") String clnum,
			@RequestParam("startDate") String sdate,
			@RequestParam("endDate") String edate, Model model)
			throws ParseException {

		NICUPatient p = nicus.modelPatient(clnum, sdate);
		model.addAttribute("patient", p);

		if (edate.equalsIgnoreCase("date")) {
			ArrayList<NICUPatient> his = new ArrayList<NICUPatient>();
			his.add(p);
			model.addAttribute("persons", his);
			return "nicuPatientReport";

		} else {

			List<NICUPatient> his = nicus.modelPatients(clnum, sdate, edate);
			model.addAttribute("persons", his);
			List<NICUPatient> adminInfo = nicus.detailAdminData(clnum, sdate,
					edate);
			model.addAttribute("admins", adminInfo);
			return "nicuPatientReport";
		}
	}

}
