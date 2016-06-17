package amalgaI;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

public class CCExcelReport extends AbstractExcelView {

	@Override
	protected void buildExcelDocument(Map model, HSSFWorkbook workbook,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		ArrayList<VTStat> report = (ArrayList<VTStat>) model.get("re");
		ArrayList<VTMReport> rbm = (ArrayList<VTMReport>) model.get("rbm");
		
		
		//create a wordsheet
		HSSFSheet sheet = workbook.createSheet("Vent report by patient");
		
		HSSFRow header = sheet.createRow(0);
		header.createCell(0).setCellValue("Clinic Number");
		header.createCell(1).setCellValue("Vent Mode");
		header.createCell(2).setCellValue("VT > 8ml/kg");
		header.createCell(3).setCellValue("VT > 8ml/kg Percent");
		header.createCell(4).setCellValue("VT < 8ml/kg");
		header.createCell(5).setCellValue("VT > 8ml/kg Percent");
		
		int rowNum = 1;
		for (VTStat vt: report) {
			//create the row data
			HSSFRow row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(vt.getClinicNO());
			row.createCell(1).setCellValue(vt.getVentMode());
			row.createCell(2).setCellValue(vt.getPos());
			row.createCell(3).setCellValue(vt.getPosPercent());
			row.createCell(4).setCellValue(vt.getNeg());
			row.createCell(5).setCellValue(vt.getNegPercent());
        }
		
		
		
		
		//create the second sheet
		HSSFSheet sheet2 = workbook.createSheet("Vent report by mode");
		
		HSSFRow header2 = sheet2.createRow(0);

		header2.createCell(0).setCellValue("Vent Mode");
		header2.createCell(1).setCellValue("VT > 8ml/kg");
		header2.createCell(2).setCellValue("VT < 8ml/kg");
		
		
		int rowNum2 = 1;
		for (VTMReport vtm: rbm) {
			//create the row data
			HSSFRow row = sheet2.createRow(rowNum2++);
			row.createCell(0).setCellValue(vtm.getMode());
			row.createCell(1).setCellValue(vtm.getPos());
			row.createCell(2).setCellValue(vtm.getNeg());
        }
		
	}

}
