package amalgaI;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.commons.lang.NumberUtils;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("ORDataService")
@Transactional
public class ORDataService {

	private DataSource dataSource;
	private SimpleJdbcTemplate jdbcTemplate;

	@Resource(name = "dataSource-ORData")
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new SimpleJdbcTemplate(dataSource);
	}

	public ArrayList<ArrayList<VTModel>>  getVTICUData(String icus, String sDate,
			String eDate) {

		Hashtable<String, String> dic = new Hashtable<String, String>();
		dic.put("E104", "Ei 10-4 ICU");
		dic.put("MB5B", "Mb 5B ICU");
		dic.put("MB5G", "Mb 5G ICU");
		dic.put("MB4D", "Mb 4D ICU");
		dic.put("MB4E", "Mb 4E ICU");
		dic.put("MB6B", "Mb 6B ICU");
		dic.put("MB6G", "Mb 6G ICU");
		dic.put("MB8D", "Mb 8D ICU");
		dic.put("MB8E", "Mb 8E ICU");
		dic.put("MB3G", "Mb 3G ICU");

		dic.put("MB7D", "Mb 7D ICU");
		dic.put("MB7E", "Mb 7E ICU");
		dic.put("MB7B", "Mb 7B ICU");
		dic.put("MB7G", "Mb 7G ICU");
		dic.put("MB3B", "Mb 3B NICU");

		ArrayList<ORData> olist = new ArrayList<ORData>();

		String process = icus.substring(3);

		String[] list = process.split(",");
		ArrayList<String> iculist = new ArrayList<String>();
		for (String s : list) {

			iculist.add(dic.get(s));
		}

		// Prepare our SQL statement
		String sql = "select distinct a.ClinicNumber, a.MeasurementTime, "
				+ "a.VitalCodeDescription, a.VitalSignValue, b.Height, b.DeptDesc, "
				+ "c.GenderCode from OR_DataRep_Prod.dbo.OR_Vitals_ICU_2014 as a, OR_DataRep_Prod.dbo.OR_Demo_2014 as b, "
				+ "OR_DataRep_Prod.dbo.OR_Demographics_extended as c where a.VitalCodeDescription "
				+ "in ('Tidal Volume Expired', "
				+ "'Ventilator Mode') and (a.MeasurementTime between :sDate "
				+ "and :eDate ) and a.ClinicNumber = b.ClinicNumber and "
				+ "(a.MeasurementTime between b.EnvStarted and b.EnvEnded) "
				+ "and b.DeptDesc in (:iculist) and a.ClinicNumber=c.ClinicNumber";

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("iculist", iculist);
		params.addValue("sDate", sDate);
		params.addValue("eDate", eDate);

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, params);
		SimpleDateFormat sdfDate = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		for (Map<String, Object> row : rows) {

			ORData p = new ORData();
			p.setVitalClinicNo(row.get("ClinicNumber").toString());
			p.setVitalMeasuerDT(sdfDate.format(row.get("MeasurementTime")));
			p.setVitalCodeDesc(row.get("VitalCodeDescription").toString());
			p.setVitalValue(row.get("VitalSignValue").toString());
			BigDecimal heiy = (BigDecimal) row.get("Height");
			double hei = heiy.doubleValue();
			double ibw = 0.0;
			if (NumberUtils.isNumber(p.getVitalValue())) {

				if (row.get("GenderCode").toString().equalsIgnoreCase("M")) {
					hei -= 154;
					hei *= 0.9;
					hei += 50;
					ibw = hei;
					double v = Double.parseDouble(p.getVitalValue()) / ibw;
					// System.out.println(v);
					// String str = new DecimalFormat("#").format(v);
					// p.setVitalCodeDesc(v+"");
					p.setVtData(v);
				}
				else {
					
					hei -= 154;
					hei *= 0.9;
					hei += 45.5;
					ibw = hei;
					double v = Double.parseDouble(p.getVitalValue()) / ibw;
					// System.out.println(v);
					// String str = new DecimalFormat("#").format(v);
					// p.setVitalCodeDesc(v+"");
					p.setVtData(v);
					
				}
			}
			p.setDemoDeptDesc(row.get("DeptDesc").toString());

			olist.add(p);
		}

		//ArrayList<VTModel> vtm = new ArrayList<VTModel>();
		ArrayList<String> cliMap = new ArrayList<String>();
		ArrayList<ArrayList<VTModel>> modes = new ArrayList<ArrayList<VTModel>>();
		
		for (ORData or : olist) {

			if (or.getVitalCodeDesc().equalsIgnoreCase("Tidal Volume Expired")) {
				for (ORData or2 : olist) {

					if (or2.getVitalMeasuerDT().equalsIgnoreCase(
							or.getVitalMeasuerDT())&&
							or2.getVitalClinicNo().equalsIgnoreCase(
									or.getVitalClinicNo())
							&& or2.getVitalCodeDesc().equalsIgnoreCase(
									"Ventilator Mode")) {
						VTModel m = new VTModel();
						m.setIcu(or.getDemoDeptDesc());
						m.setVentModel(or2.getVitalValue());
						m.setVtData(or.getVtData());
						m.setClinicNo(or.getVitalClinicNo());
						if(!cliMap.contains(m.getClinicNo())) {
							cliMap.add(m.getClinicNo());
							ArrayList<VTModel> li = new ArrayList<VTModel>();
							li.add(m);
							modes.add(li);
							System.out.println("adding number"+ m.getClinicNo());
						}
						else {
							for (ArrayList<VTModel> ll: modes) {
								if(ll.get(0).getClinicNo().equalsIgnoreCase(m.getClinicNo())) {
									
									ll.add(m);
								}
							}
						}
						//vtm.add(m);
					}

				}

			}

		}

		return modes;
	}
	
	
	//public  ArrayList<ArrayList<VTModel>>
	
	public ArrayList<VTStat> VTReport(ArrayList<VTModel> vtm) {
		
		
		int acP = 0, acN = 0, arpvP=0, arpvN=0, 
		avapsP=0, avapsN=0, blievP=0, blievN=0,
		cmvP=0, cmvN=0,cpapP=0, cpapN=0, imvpsP=0, imvpsN=0,
		pcP=0, pcN=0, pcvP=0, pcvN=0, stP=0, stN=0, simvP=0, simvN=0,
		spontP=0, spontN=0, stdbyP=0, stdbyN=0;
		

		
		for(VTModel v: vtm) {
		
			if(v.getVentModel().equalsIgnoreCase("A/C")) {
				if(v.getVtData()>8.0)
					acP++;
				else
					acN++;
			}
			else if(v.getVentModel().equalsIgnoreCase("APRV")) {
				if(v.getVtData()>8.0)
					arpvP++;
				else
					arpvN++;
			}
			else if(v.getVentModel().equalsIgnoreCase("AVAPS")) {
				if(v.getVtData()>8.0)
					avapsP++;
				else
					avapsN++;
			}
			else if(v.getVentModel().equalsIgnoreCase("BILEV")) {
				if(v.getVtData()>8.0)
					blievP++;
				else
					blievN++;
			}
			else if(v.getVentModel().equalsIgnoreCase("CMV")) {
				if(v.getVtData()>8.0)
					cmvP++;
				else
					cmvN++;
			}
			else if(v.getVentModel().equalsIgnoreCase("CPAP")) {
				if(v.getVtData()>8.0)
					cpapP++;
				else
					cpapN++;
			}
			else if(v.getVentModel().equalsIgnoreCase("IMVPS")) {
				if(v.getVtData()>8.0)
					imvpsP++;
				else
					imvpsN++;
			}
			else if(v.getVentModel().equalsIgnoreCase("PC")) {
				if(v.getVtData()>8.0)
					pcP++;
				else
					pcN++;
			}
			else if(v.getVentModel().equalsIgnoreCase("PCV")) {
				if(v.getVtData()>8.0)
					pcvP++;
				else
					pcvN++;
			}
			else if(v.getVentModel().equalsIgnoreCase("S/T")) {
				if(v.getVtData()>8.0)
					stP++;
				else
					stN++;
			}
			else if(v.getVentModel().equalsIgnoreCase("SIMV")) {
				if(v.getVtData()>8.0)
					simvP++;
				else
					simvN++;
			}
			else if(v.getVentModel().equalsIgnoreCase("SPONT")) {
				if(v.getVtData()>8.0)
					spontP++;
				else
					spontN++;
			}
			else if(v.getVentModel().equalsIgnoreCase("STDBY")) {
				if(v.getVtData()>8.0)
					stdbyP++;
				else
					stdbyN++;
			}
		}
		
		ArrayList<VTStat> vts = new ArrayList<VTStat>();
		
		if(acP!=0||acN!=0) {
			VTStat vs = new VTStat(); 
			vs.setVentMode("A/C");
			vs.setPos(acP);
			double p1 =(double)acP/(acP+acN)*100.0;
			double p2 = (double)acN/(acP+acN)*100.0;
			vs.setPosPercent(String.format("%1$,.2f", p1)+"%");
			vs.setNegPercent(String.format("%1$,.2f", p2)+"%");
			vs.setNeg(acN);
			vs.setClinicNO(vtm.get(0).getClinicNo());
			vts.add(vs);
		}
		
		if(arpvP!=0 || arpvN!=0) {
			VTStat vs = new VTStat(); 
			vs.setVentMode("APRV");
			vs.setPos(arpvP);
			vs.setNeg(arpvN);
			double p1= (double)arpvP/(arpvP+arpvN)*100.0;
			double p2= (double)arpvN/(arpvP+arpvN)*100.0;
			vs.setPosPercent(String.format("%1$,.2f", p1)+"%");
			vs.setNegPercent(String.format("%1$,.2f", p2)+"%");
			vs.setClinicNO(vtm.get(0).getClinicNo());
			vts.add(vs);
		}
		
		if(avapsP!=0 || avapsN!=0) {
			VTStat vs = new VTStat(); 
			vs.setVentMode("AVAPS");
			vs.setPos(avapsP);
			vs.setNeg(avapsN);
			
			double p1= (double)avapsP/(avapsP+avapsN)*100.0;
			double p2= (double)avapsN/(avapsP+avapsN)*100.0;
			vs.setPosPercent(String.format("%1$,.2f", p1)+"%");
			vs.setNegPercent(String.format("%1$,.2f", p2)+"%");
			vs.setClinicNO(vtm.get(0).getClinicNo());
			vts.add(vs);
		}
		
		if(blievP!=0 || blievN!=0) {
			VTStat vs = new VTStat(); 
			vs.setVentMode("BILEV");
			vs.setPos(blievP);
			vs.setNeg(blievN);
			
			double p1= (double)blievP/(blievP+blievN)*100.0;
			double p2= (double)blievN/(blievP+blievN)*100.0;
			vs.setPosPercent(String.format("%1$,.2f", p1)+"%");
			vs.setNegPercent(String.format("%1$,.2f", p2)+"%");
			vs.setClinicNO(vtm.get(0).getClinicNo());
			vts.add(vs);
		}
		
		if(cmvP!=0 || cmvN!=0) {
			VTStat vs = new VTStat(); 
			vs.setVentMode("CMV");
			vs.setPos(cmvP);
			vs.setNeg(cmvN);
			
			
			
			double p1= (double)cmvP/(cmvP+cmvN)*100.0;
			double p2= (double)cmvN/(cmvP+cmvN)*100.0;
			vs.setPosPercent(String.format("%1$,.2f", p1)+"%");
			vs.setNegPercent(String.format("%1$,.2f", p2)+"%");
			
			vs.setClinicNO(vtm.get(0).getClinicNo());
			vts.add(vs);
		}
		
		if(cpapP!=0 || cpapN!=0) {
			VTStat vs = new VTStat(); 
			vs.setVentMode("CPAP");
			vs.setPos(cpapP);
			vs.setNeg(cpapN);
			
			double p1= (double)cpapP/(cpapP+cpapN)*100.0;
			double p2= (double)cpapN/(cpapP+cpapN)*100.0;
			vs.setPosPercent(String.format("%1$,.2f", p1)+"%");
			vs.setNegPercent(String.format("%1$,.2f", p2)+"%");
			
			vs.setClinicNO(vtm.get(0).getClinicNo());
			vts.add(vs);
		}
		
		if(imvpsP!=0 || imvpsN!=0) {
			VTStat vs = new VTStat(); 
			vs.setVentMode("IMVPS");
			vs.setPos(imvpsP);
			vs.setNeg(imvpsN);
			
			double p1= (double)imvpsP/(imvpsP+imvpsN)*100.0;
			double p2= (double)imvpsN/(imvpsP+imvpsN)*100.0;
			vs.setPosPercent(String.format("%1$,.2f", p1)+"%");
			vs.setNegPercent(String.format("%1$,.2f", p2)+"%");
			vs.setClinicNO(vtm.get(0).getClinicNo());
			vts.add(vs);
		}
		
		if(pcP!=0 || pcN!=0) {
			VTStat vs = new VTStat(); 
			vs.setVentMode("PC");
			vs.setPos(pcP);
			vs.setNeg(pcN);
			
			double p1= (double)pcP/(pcP+pcN)*100.0;
			double p2= (double)pcN/(pcP+pcN)*100.0;
			vs.setPosPercent(String.format("%1$,.2f", p1)+"%");
			vs.setNegPercent(String.format("%1$,.2f", p2)+"%");
			vs.setClinicNO(vtm.get(0).getClinicNo());
			vts.add(vs);
		}
		
		if(pcvP!=0 || pcvN!=0) {
			VTStat vs = new VTStat(); 
			vs.setVentMode("PCV");
			vs.setPos(pcvP);
			vs.setNeg(pcvN);
			
			double p1= (double)pcvP/(pcvP+pcvN)*100.0;
			double p2= (double)pcvN/(pcvP+pcvN)*100.0;
			vs.setPosPercent(String.format("%1$,.2f", p1)+"%");
			vs.setNegPercent(String.format("%1$,.2f", p2)+"%");
			vs.setClinicNO(vtm.get(0).getClinicNo());
			vts.add(vs);
		}
		
		if(stP!=0 || stN!=0) {
			VTStat vs = new VTStat(); 
			vs.setVentMode("S/T");
			vs.setPos(stP);
			vs.setNeg(stN);
			
			
			double p1= (double)stP/(stP+stN)*100.0;
			double p2= (double)stN/(stP+stN)*100.0;
			vs.setPosPercent(String.format("%1$,.2f", p1)+"%");
			vs.setNegPercent(String.format("%1$,.2f", p2)+"%");
			vs.setClinicNO(vtm.get(0).getClinicNo());
			vts.add(vs);
		}
		
		if(simvP!=0 || simvN!=0) {
			VTStat vs = new VTStat(); 
			vs.setVentMode("SIMV");
			vs.setPos(simvP);
			vs.setNeg(simvN);
			
			double p1= (double)simvP/(simvP+simvN)*100.0;
			double p2= (double)simvN/(simvP+simvN)*100.0;
			vs.setPosPercent(String.format("%1$,.2f", p1)+"%");
			vs.setNegPercent(String.format("%1$,.2f", p2)+"%");
			vs.setClinicNO(vtm.get(0).getClinicNo());
			vts.add(vs);
		}
		
		if(spontP!=0 || spontN!=0) {
			VTStat vs = new VTStat(); 
			vs.setVentMode("SPONT");
			vs.setPos(spontP);
			vs.setNeg(spontN);
			
			double sum = spontP+spontN;
			
			double p1= ((double)spontP/sum)*100;
			double p2= ((double)spontN/sum)*100;

			vs.setPosPercent(String.format("%1$,.2f", p1)+"%");
			vs.setNegPercent(String.format("%1$,.2f", p2)+"%");
			
			vs.setClinicNO(vtm.get(0).getClinicNo());
			vts.add(vs);
		}
		
		if(stdbyP!=0 || stdbyN!=0) {
			VTStat vs = new VTStat(); 
			vs.setVentMode("STDBY");
			vs.setPos(stdbyP);
			vs.setNeg(stdbyN);
			
			double p1= (double)stdbyP/(stdbyP+stdbyN)*100.0;
			double p2= (double)stdbyN/(stdbyP+stdbyN)*100.0;
			vs.setPosPercent(String.format("%1$,.2f", p1)+"%");
			vs.setNegPercent(String.format("%1$,.2f", p2)+"%");
			vs.setClinicNO(vtm.get(0).getClinicNo());
			vts.add(vs);
		}
		
		
//		Hashtable<String, String> res;
//				//add to the list
//				res.put("A/C", "VT>8:"+acP+";   VT<8:"+acN);
//		res.put("APRV", "VT>8:"+arpvP+";   VT<8:"+arpvN);
//		res.put("AVAPS", "VT>8:"+avapsP+";   VT<8:"+avapsN);
//		res.put("BILEV", "VT>8:"+blievP+";   VT<8:"+blievN);
//		res.put("CMV", "VT>8:"+cmvP+";   VT<8:"+cmvN);
//		res.put("CPAP", "VT>8:"+cpapP+";   VT<8:"+cpapN);
//		res.put("IMVPS", "VT>8:"+imvpsP+";   VT<8:"+imvpsN);
//		res.put("PC", "VT>8:"+pcP+";   VT<8:"+pcN);
//		res.put("PCV", "VT>8:"+pcvP+";   VT<8:"+pcvN);
//		res.put("S/T", "VT>8:"+stP+";   VT<8:"+stN);
//		res.put("SIMV", "VT>8:"+simvP+";   VT<8:"+simvN);
//		res.put("SPONT", "VT>8:"+spontP+";   VT<8:"+spontN);
//		res.put("STDBY", "VT>8:"+stdbyP+";   VT<8:"+stdbyN);
		
		
		return vts;
	}
	
	public ArrayList<VTMReport> reportByMode (ArrayList<VTStat> vts) {
		
		ArrayList<VTMReport> modeRepo = new ArrayList<VTMReport>();
		int acP = 0, acN = 0, arpvP=0, arpvN=0, 
		avapsP=0, avapsN=0, blievP=0, blievN=0,
		cmvP=0, cmvN=0,cpapP=0, cpapN=0, imvpsP=0, imvpsN=0,
		pcP=0, pcN=0, pcvP=0, pcvN=0, stP=0, stN=0, simvP=0, simvN=0,
		spontP=0, spontN=0, stdbyP=0, stdbyN=0;
		
		for(VTStat v: vts) {
			if(v.getVentMode().equalsIgnoreCase("A/C")) {
				acP+=v.getPos();
				acN+=v.getNeg();
			}
			if(v.getVentMode().equalsIgnoreCase("APRV")) {
				arpvP+=v.getPos();
				arpvN+=v.getNeg();
			}
			if(v.getVentMode().equalsIgnoreCase("AVAPS")) {
				avapsP+=v.getPos();
				avapsN+=v.getNeg();
			}
			if(v.getVentMode().equalsIgnoreCase("BILEV")) {
				blievP+=v.getPos();
				blievN+=v.getNeg();
			}
			if(v.getVentMode().equalsIgnoreCase("CMV")) {
				cmvP+=v.getPos();
				cmvN+=v.getNeg();
			}
			if(v.getVentMode().equalsIgnoreCase("CPAP")) {
				cpapP+=v.getPos();
				cpapN+=v.getNeg();
			}
			if(v.getVentMode().equalsIgnoreCase("IMVPS")) {
				imvpsP+=v.getPos();
				imvpsN+=v.getNeg();
			}
			if(v.getVentMode().equalsIgnoreCase("PC")) {
				pcP+=v.getPos();
				pcN+=v.getNeg();
			}
			if(v.getVentMode().equalsIgnoreCase("PCV")) {
				pcvP+=v.getPos();
				pcvN+=v.getNeg();
			}
			if(v.getVentMode().equalsIgnoreCase("S/T")) {
				stP+=v.getPos();
				stN+=v.getNeg();
			}
			if(v.getVentMode().equalsIgnoreCase("SIMV")) {
				simvP+=v.getPos();
				simvN+=v.getNeg();
			}
			if(v.getVentMode().equalsIgnoreCase("SPONT")) {
				spontP+=v.getPos();
				spontN+=v.getNeg();
			}
			if(v.getVentMode().equalsIgnoreCase("STDBY")) {
				stdbyP+=v.getPos();
				stdbyN+=v.getNeg();
			}
		}
		

		if(acP!=0||acN!=0) {
			VTMReport vm = new VTMReport(); 
			vm.setMode("A/C");
			vm.setNeg(acN);
			vm.setPos(acP);
			modeRepo.add(vm);
		}
		
		if(arpvP!=0||arpvN!=0) {
			VTMReport vm = new VTMReport(); 
			vm.setMode("APRV");
			vm.setNeg(arpvN);
			vm.setPos(arpvP);
			modeRepo.add(vm);
		}
		
		if(avapsP!=0||avapsN!=0) {
			VTMReport vm = new VTMReport(); 
			vm.setMode("AVAPS");
			vm.setNeg(avapsN);
			vm.setPos(avapsP);
			modeRepo.add(vm);
		}
		
		if(blievP!=0||blievN!=0) {
			VTMReport vm = new VTMReport(); 
			vm.setMode("BILEV");
			vm.setNeg(blievN);
			vm.setPos(blievP);
			modeRepo.add(vm);
		}
		
		if(cmvP!=0||cmvN!=0) {
			VTMReport vm = new VTMReport(); 
			vm.setMode("CMV");
			vm.setNeg(cmvN);
			vm.setPos(cmvP);
			modeRepo.add(vm);
		}
		
		if(cpapP!=0||cpapN!=0) {
			VTMReport vm = new VTMReport(); 
			vm.setMode("CPAP");
			vm.setNeg(cpapN);
			vm.setPos(cpapP);
			modeRepo.add(vm);
		}
		
		if(imvpsP!=0||imvpsN!=0) {
			VTMReport vm = new VTMReport(); 
			vm.setMode("IMVPS");
			vm.setNeg(imvpsN);
			vm.setPos(imvpsP);
			modeRepo.add(vm);
		}
		
		if(pcP!=0||pcN!=0) {
			VTMReport vm = new VTMReport(); 
			vm.setMode("PC");
			vm.setNeg(pcN);
			vm.setPos(pcP);
			modeRepo.add(vm);
		}
		
		if(pcvP!=0||pcvN!=0) {
			VTMReport vm = new VTMReport(); 
			vm.setMode("PCV");
			vm.setNeg(pcvN);
			vm.setPos(pcvP);
			modeRepo.add(vm);
		}
		
		if(stP!=0||stN!=0) {
			VTMReport vm = new VTMReport(); 
			vm.setMode("S/T");
			vm.setNeg(stN);
			vm.setPos(stP);
			modeRepo.add(vm);
		}
		
		if(simvP!=0||simvN!=0) {
			VTMReport vm = new VTMReport(); 
			vm.setMode("SIMV");
			vm.setNeg(simvN);
			vm.setPos(simvP);
			modeRepo.add(vm);
		}
		
		if(spontP!=0||spontN!=0) {
			VTMReport vm = new VTMReport(); 
			vm.setMode("SPONT");
			vm.setNeg(spontN);
			vm.setPos(spontP);
			modeRepo.add(vm);
		}
		
		if(stdbyP!=0||stdbyN!=0) {
			VTMReport vm = new VTMReport(); 
			vm.setMode("STDBY");
			vm.setNeg(stdbyN);
			vm.setPos(stdbyP);
			modeRepo.add(vm);
		}
		
		return modeRepo;
		
	}
}
