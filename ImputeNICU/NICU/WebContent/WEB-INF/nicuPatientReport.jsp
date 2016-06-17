<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>NICU</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <link href="../resources/bootstrap.css" rel="stylesheet">
    <link href="../resources/bootstrap-responsive.css" rel="stylesheet">
    <link href="../resources/docs.css" rel="stylesheet">
    <link href="../resources/prettify.css" rel="stylesheet">
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />
    <script src="http://code.jquery.com/jquery-1.8.3.js"></script>
    <script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
 

 
 
  </head>

  <body data-spy="scroll" data-target=".bs-docs-sidebar">


    <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="brand" href="./index.html">Bootstrap</a>
          <div class="nav-collapse collapse">
            <ul class="nav">
              <li >
                <a href="nicuHome">Home</a>
              </li>
              <li class="active"><a href="#">Patient Report</a></li>
            </ul>
         
          </div>

        </div>
      </div>
    </div>




  <div class="container">
  
  <br>
  
  <div class="row">

  <div class="span12">

        <section id="tables">
          <div class="page-header">
            <h3>${patient.patientName}&nbsp&nbsp&nbsp&nbsp&nbsp 
						
			
			
&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp &nbsp&nbsp&nbsp&nbsp&nbsp 
&nbsp&nbsp&nbsp&nbsp&nbsp &nbsp&nbsp&nbsp&nbsp&nbsp
&nbsp&nbsp&nbsp&nbsp&nbsp &nbsp&nbsp&nbsp&nbsp&nbsp 
&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp   
   <input  id="dnm" type="text" class="input-small search-query" value="startDate" />
   <input id= "kkk" type="text" class="input-small search-query" value="endDate" />
 <a id="button" class="btn btn-primary ">Search</a>

			</h3>				
          </div>
		  </section>
	</div>



	
		</div>
		

			
	<div class="row">
	
   <div class="span2"> 
   

   <img src="../resources/images/man.png" width="146px" height="148px" />
   </div>
   <div class="span2">
   <br>
   <a>Clinic Number</a>: ${patient.clinicNumber} <br>
   <a>Birth Date</a>: ${patient.dateOfBirth} <br>
   <a>Gender</a>: ${patient.gender}
   </div>
   <div class = "span8">

<div class="alert alert-info">

				<table class="table table-hover">
				<thead>
               		 <tr>
                  		
                  		<td><a>Admission Date</a></td>
				  	
				  		
				  		<td><a>ABO/rH</a></td>
				  		<td><a>Coombs</a></td>
				  		<td><a>Hematocrit</a></td>
                	</tr>
             	 </thead>
             	 <tbody>   
	  	   					 <c:forEach items="${admins}" var="pp">
	  	   				<tr>
               			<td>${pp.adminDt}</td>
               			<td>${pp.abo}</td>
               			<td></td>
               			<td>${pp.hema}</td>
           				</tr>
	 	 		</c:forEach>         
              	</tbody>
            	</table>
</div>

   </div>
   </div>
  
    <div class="row">

      <div class="span12">

        <section id="tables">

			<style>
					.tb {
						width: 100%;						
    					height: 340px; 
    					overflow: auto;
						padding: 0px 0 0px 0px;
					} 
					
					.alert-info{
						background-color: rgb(255, 255, 255);
					}
					
					/* Let's get this party started */
::-webkit-scrollbar {
    width: 12px;
}
 
/* Track */
::-webkit-scrollbar-track {
    -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.3); 
    -webkit-border-radius: 10px;
    border-radius: 10px;
}
 
/* Handle */
::-webkit-scrollbar-thumb {
    -webkit-border-radius: 10px;
    border-radius: 10px;
    background: rgba(36,160,218,0.8); 
    -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.5); 
}
::-webkit-scrollbar-thumb:window-inactive {
	background: rgba(0,0,180,0.4); 
}

.bs-docs-example::after {
content: "NICU Report";
position: absolute;
top: -1px;
left: -1px;
padding: 3px 7px;
font-size: 12px;
font-weight: bold;
background-color: #f5f5f5;
border: 1px solid #ddd;
color: #9da0a4;
-webkit-border-radius: 4px 0 4px 0;
-moz-border-radius: 4px 0 4px 0;
border-radius: 4px 0 4px 0;
}
Pseudo ::-webkit-scrollbar element
</style>
		
		
          <ul id="myTab" class="nav nav-tabs">
              <li class="active"><a href="#Vitals" data-toggle="tab">Vitals and Location</a></li>
              <li class=""><a href="#ionutri" data-toggle="tab">I & O Nutrition</a></li>
              <li class=""><a href="#Procedures" data-toggle="tab">Procedures</a></li>
              <li class=""><a href="#biliru" data-toggle="tab">Bilirubin</a></li>
			  <li class=""><a href="#Labs" data-toggle="tab">Labs</a></li>
			  <li class=""><a href="#Respiratory" data-toggle="tab">Respiratory</a></li>
			  <li class=""><a href="#dvon" data-toggle="tab">Discharge and VON Data</a></li>
          </ul>
		  <div id="myTabContent" class="tab-content">
              <div class="tab-pane fade active in" id="Vitals">
                <p>
				<div class="tb">
				 <div class="bs-docs-example">			
				<table class="table table-hover">
				<thead>
                <tr>
                  <th>Date</th>
                  <th>Daily Weight</th>
                  <th>Daily Length</th>
                  <th>Head</th>
                  <th>Nurse Unit</th>
                  <th>Bed Location</th>
                </tr>
              </thead>
              <tbody>
              	 <c:forEach items="${persons}" var="person">
	  	   				<tr>
               			<td>${person.reportDate}</td>
                     	<td>${person.weight}</td>
                     	<td>${person.length}</td>
                     	<td>${person.head}</td>
                     	<td>${person.nurseUnit}</td>
                     	<td>${person.bedLocation}</td>
           				</tr>
	 	 		</c:forEach>         
              </tbody>
            </table>
	
          </div>
        
		  </div>
				  </p>
              </div>
              <div class="tab-pane fade" id="ionutri">
                 <p>
				<div class="tb">
				 <div class="bs-docs-example">			
				<table class="table table-hover">
				<thead>
                <tr>
                  <th>Date</th>
                  <th>Breast Fed</th>
                  <th>Breast Milk</th>
                  <th>Donor Breast Milk</th>
				  <th>Lipids</th>
                  <th>TPN</th>
                  <th>Infant Formula</th>
                  <th>Dextrose IV Fluids</th>
                </tr>
              </thead>
              <tbody>
               <c:forEach items="${persons}" var="person">
	  	   				<tr>
               			<td>${person.reportDate}</td>
                     	<td>${person.breastFed}</td>
                     	<td>${person.breastMilk}</td>
                     	<td>${person.breatDonor}</td>
                     	<td>${person.lipids}</td>
                     	<td>${person.tpn}</td>
                     	<td>${person.infantFormula}</td>
                     	<td>${person.dextrose}</td>
           				</tr>
	 	 		</c:forEach>
              </tbody>
            </table> 
	
          </div>
		  </div>
				</p>
				</div>
             <div class="tab-pane fade" id="Procedures">
			  <p>
				<div class="tb">
				 <div class="bs-docs-example">			
				<table class="table table-hover">
				<thead>
                <tr>
                  <th>Date</th>
                  <th>Red Blood Cells</th>
                  <th>Platelets</th>
                  <th>Cryoprecipitate</th>
				  <th>Photo Therapy</th>
                  <th>Fresh Plasma</th>
                  <th>Drains 1-6</th>
                  <th>Central Line</th>
				  <th>Ostomy</th>
                  <th>Urinary Catheter</th>
				  <th>Intubation</th>
                </tr>
              </thead>
              <tbody>
              <c:forEach items="${persons}" var="person">
	  	   				<tr>
               			<td>${person.reportDate}</td>
                     	<td>${person.redCell}</td>
                     	<td>${person.platelets}</td>
                     	<td>${person.cryoprecipitate}</td>
                     	<td></td>
                     	<td>${person.freshFrozenPlasma}</td>
                     	<td></td>
                     	<td></td>
                     	<td></td>
                     	<td></td>
                     	<td></td>
           				</tr>
	 	 		</c:forEach>
              </tbody>
            </table>
	
          </div>
		  </div>
				</p>
			</div>
			
			
			<div class="tab-pane fade" id="biliru">
                <p>
				<div class="tb">
				 <div class="bs-docs-example">			
				<table class="table table-hover">
				<thead>
                <tr>
                  <th>Date</th>
                  <th>Daily Total Bilirubin</th>
                  <th>Daily Direct Bilirubin</th>
                  <th>Highest Total Bilirubin(until now)</th>
                  <th>Highest Direct Bilirubin(until now)</th>
                </tr>
              </thead>
              <tbody>
              	 <c:forEach items="${persons}" var="person">
	  	   				<tr>
               			<td>${person.reportDate}</td>
                     	<td>${person.dailybilitotal}</td>
                     	<td>${person.dailybilidirect }</td>
                        <td>${person.totalbili}</td>
                     	<td>${person.totalbilidir}</td>
           				</tr>
	 	 		</c:forEach>         
              </tbody>
            </table>
	
          </div>
        
		  </div>
				  </p>
              </div>
			
			<div class="tab-pane fade" id="Labs">
			  <p>
				<div class="tb">
				 <div class="bs-docs-example">			
				<table class="table table-hover">
				<thead>
                <tr>
                  <th>Date</th>
                  <th>Amphetamine</th>
				  <th>Cocaine</th>
				  <th>Mathaphetamin</th>
				  <th>Opiate</th>
				  <th>Phencyclidine</th>
				  <th>Tetrahydrocannabinol</th>
				  <th>Minn Newborn Screen</th>
                </tr>
              </thead>
              <tbody>
                <c:forEach items="${persons}" var="person">
	  	   				<tr>
               			<td>${person.reportDate}</td>
                     	<td>${person.amphetamine}</td>
                     	<td>${person.cocaine}</td>
                     	<td>${person.methamphetamin}</td>
                     	<td>${person.opiate}</td>
                     	<td>${person.phe}</td>
                     	<td>${person.tetra}</td>
                     	<td></td>
           				</tr>
	 	 		</c:forEach>
          
              </tbody>
            </table>
	
          </div>
		  </div>
				</p>
			</div>
			
			
			<div class="tab-pane fade" id="Respiratory">
			  <p>
				<div class="tb">
				 <div class="bs-docs-example">			
				<table class="table table-hover">
				<thead>
                <tr>
                  		<th>Date</th>
                       	<th>Respiratory Device</th>
                       	<th>Mode of Ventilation</th>
                       	<th>Higest FiO2</th>
                       	<th>Nitric Oxide</th> 
                       	<th>SpO2</th>
                       	<th>Highest O2</th>                      
                </tr>
              </thead>
              <tbody>
                <c:forEach items="${persons}" var="person">
	  	   				<tr>
               			<td>${person.reportDate}</td>
                     	<td>${person.oxy}</td>
                     	<td>${person.ventil}</td>
                     	<td>${person.fio2}</td>
                     	<td>${person.niox}</td>
                     	<td>${person.avespo2}</td>
                     	<td>${person.o2}</td>                    
           				</tr>
	 	 		</c:forEach>         
             	 </tbody>
            	</table>
	
          		</div>
		  		</div>
			</p>
			</div>
			
			
			
			<div class="tab-pane fade" id="dvon">
			  <p>
				<div class="tb">
				 <div class="bs-docs-example">			
				<table class="table table-hover">
				<thead>
                <tr>
                  		<th>Date</th>
                       	<th>Discharge Time:</th>
                       	<th>Tempererature at Admission</th>     
                </tr>
              </thead>
              <tbody>
                <c:forEach items="${persons}" var="person">
	  	   				<tr>
               			<td>${person.reportDate}</td>
                     	<td></td>
                     	<td>${person.temperature}</td>                             
           				</tr>
	 	 		</c:forEach>         
             	 </tbody>
            	</table>
	
          		</div>
		  		</div>
			</p>
			</div>

           </div>
		  
		  <script>
		  $('#myTab a').click(function (e) {
  e.preventDefault();
  $(this).tab('show');
})
		  </script>

        </section>

      </div>
    </div>
    
    

  </div>



    <!-- Footer
    ================================================== -->
    <footer class="footer">
      <div class="container">
        <p>Used for Mayo Clinic NICU Report</p>
      </div>
    </footer>



    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script type="text/javascript" src="http://platform.twitter.com/widgets.js"></script>
    <script src="../resources/bootstrap-transition.js"></script> 
    <script src="../resources/bootstrap-tab.js"></script>
    <script>
    

       
    	 $( "#dnm" ).datepicker();
    	 $( "#kkk" ).datepicker();
    	        
    	 $('#button').bind('click', function(e) 
    	 {
    		  var sDate= $('#dnm').val();
    	  	  var eDate= $('#kkk').val();
    	      var clinic='${patient.clinicNumber}';
    	      if(sDate=="startDate") {
    	    	  sDate=new Date();
    	    	  var d  = sDate.getDate();
    	    	  var day = (d < 10) ? '0' + d : d;
    	    	  var m = sDate.getMonth() + 1;
    	    	  var month = (m < 10) ? '0' + m : m;
    	    	  var yy = sDate.getYear();
    	    	  var year = (yy < 1000) ? yy + 1900 : yy;
    	    	  sDate=month+"/"+day+"/"+year;
    	      }
    	  	  window.location = "bootpd?clinicNumber="+clinic+"&startDate="+sDate+"&endDate="+eDate;
    		});

    	// $('a').click(function(){
    		// $('#changable').html(" Baby's ABO/rH: ${patient.abo}<br> Baby's Coombs: ${patient.coomb}<br>Hematocrit on Birth: ${patient.hema}");
    		//});       	

    	        


    </script>
    
  </body>
</html>
