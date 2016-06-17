<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html> 

<html>



<head>

	<meta charset="utf-8">

	<meta name="viewport" content="width=device-width, initial-scale=1"> 

	

	<title>Single page template</title> 


<link rel="stylesheet" href="http://code.jquery.com/mobile/1.1.0/jquery.mobile-1.1.0.min.css" />
	<script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
<script src="http://code.jquery.com/mobile/1.1.0/jquery.mobile-1.1.0.min.js"></script>
	

<link href="../resources/colorbox.css" rel="stylesheet" type="text/css" />
<script src= "../resources/js/jquery.colorbox.js" type="text/javascript"></script>

<script>

			$(document).ready(function(){

				//Examples of how to assign the ColorBox event to elements

				

				$(".iframe").colorbox({iframe:true, "background-color":"#f00", width:"80%", height:"80%"});

				

				$(".callbacks").colorbox({

					onOpen:function(){ alert('onOpen: colorbox is about to open'); },

					onLoad:function(){ alert('onLoad: colorbox has started to load the targeted content'); },

					onComplete:function(){ alert('onComplete: colorbox has displayed the loaded content'); },

					onCleanup:function(){ alert('onCleanup: colorbox has begun the close process'); },

					onClosed:function(){ alert('onClosed: colorbox has completely closed'); }

				});

				

				//Example of preserving a JavaScript event for inline calls.

				$("#click").click(function(){ 

					$('#click').css({"background-color":"#f00", "color":"#fff", "cursor":"inherit"}).text("Open this window again and this message will still be here.");

					return false;

				});

			});

		</script>

</head> 

<body> 

<div data-role="page" id="two" data-ajax="false">

	<div data-role="header" >

				<h1>History Records</h1>
		<a href="t2.jsp" data-icon="back" >History</a>

		
	</div><!-- /header -->

	<div data-role="content" >	


	 <table id="demo">

                            <tbody>
                            <tr>
                            	<td><img src="../resources/images/Snap7.png" /><b>${InPatient.patientName}</b></td>
                            	<td></td>
                            	<td></td>	
                            </tr>
</table>

<br/>

 <ul data-role=listview data-dividertheme=a data-inset=true>
      <li data-role=list-divider>Demographics</li>
      <li>Clinic Number: ${InPatient.clinicNumber}</li>
      <li>Date of Birth: ${InPatient.dateOfBirth}</li>
      <li>Gender: ${InPatient.gender}</li>
 </ul>
 
<table width=100%>
<td>
	<ul data-role=listview data-dividertheme=a data-inset=true>
      <li data-role=list-divider>Pre-Events Data</li>
      <li> Primary Reason for Activation: ${InPatient.primaryReason}</li>
      <li> Patient's Clinical Status: ${InPatient.paClinicStatus}</li>
      <li> Heart Rate: ${InPatient.heartRate}</li>
      <li> Respiratory Rate : ${InPatient.resp}</li>
      <li> Temperature: ${InPatient.temperture}</li>
  </ul>
</td>
<td></td>

<td>
	 <ul data-role=listview data-dividertheme=a data-inset=true>
	 <li data-role=list-divider>Events Data</li>
      <li> Patient's Status: ${InPatient.patientStatus}</li>
      <li> AVPU Score: ${InPatient.avpuScore}</li>
      <li> Primary Service and Contact Pager: ${InPatient.pscPager}</li>
      <li> Hospital Admission Date: ${InPatient.adminDt}</li>
      <li> Call Completion Time: ${InPatient.callCompleteDtm}</li>
      </ul>
</td>
</table>


      <ul data-role=listview data-dividertheme=a data-inset=true>
      <li data-role=list-divider>Team Interactions</li>
      <li>Oxygen Administration: ${InPatient.oxyAdmin}</li>
      <li>Bag-Mask Ventilation: ${InPatient.bagMaskVen}</li>
      <li>Intubation: ${InPatient.intubation}</li>
      <li>DNR/DNI Oders: ${InPatient.DNR}</li>
      <li>Medication Therapy: ${InPatient.mediThearapy}</li>

    </ul>

<table>
                              <tr>
                            	
                            	<td>Clinic Number: ${InPatient.clinicNumber}</td>
                            	<td>Complete Date: ${InPatient.callCompleteDtm}</td>
                            	<td></td>
                            	
                            </tr>
                            </tbody>
                            </table>
	</div><!-- /content -->

	

	<div data-role="footer">

		<h4>Page Footer</h4>

	</div><!-- /footer -->

</div><!-- /page two -->











</body>

</html>