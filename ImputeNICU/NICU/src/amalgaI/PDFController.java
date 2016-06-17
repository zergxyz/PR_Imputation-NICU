package amalgaI;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

@Controller
public class PDFController extends AbstractController {

	
	@Resource(name = "ICUDataService")
	private ICUDataService icus;
	
	@RequestMapping(value = "/pdfReport", method = RequestMethod.GET)
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
		HttpServletResponse response) throws Exception {
 
		String output =
			ServletRequestUtils.getStringParameter(request, "output");
		
		List<LVCPatient>ps =icus.lvcReport();

		Map<String,String> revenueData = new HashMap<String,String>();
		for(LVCPatient ll: ps) {
			
			revenueData.put(ll.getClinicNumber(), ll.getLevelOfCare());
		}
		
 
//		if(output ==null || "".equals(output)){
//		    //return normal view
//		    return new ModelAndView("RevenueSummary","revenueData",revenueData);
// 
//		}else if("PDF".equals(output.toUpperCase())){
//		    //return excel view
		    return new ModelAndView("PDFRevenueSummary","revenueData",revenueData);
 
//		}else{
//		    //return normal view
//		    return new ModelAndView("RevenueSummary","revenueData",revenueData);
// 
//		}	
	}

}
