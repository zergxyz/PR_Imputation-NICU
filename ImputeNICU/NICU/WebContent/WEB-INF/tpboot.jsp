<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>


<!doctype html>
<head>
</head>
<body>
	
 <a>Admission Information</a>

               

 				<table class="table table-hover">
				<thead>
                <tr>
                  <th>Patient Name</th>
                  <th>Clinic Number</th>
                  <th>Admission Date</th>
                  <th>Discharge Date</th>
                </tr>
              </thead>
              <tbody>
					<c:forEach items="${patient}" var="person">			
	  	   				<tr>
	  	   				<td>${person.clinicNumber}</td>
 						<td></td>
                     	<td>${person.adminDt}</td>
                     	<td></td>
           				</tr>    
           			</c:forEach> 	 
              </tbody>
            </table>
                  <br>                 
            <form class="form-inline">
			<input type="text" id="dnm" value="choose date">
			<input type="text" id="kkk" value="date"">
			<a id="rep" class="btn btn-primary ">Get Reports</a>
			</form>
</body>


       <script type="text/javascript">

      $( "#dnm" ).datepicker();
  	$( "#kkk" ).datepicker();
  	
  	$("#rep").click(function(){
  	  var sDate= $('#dnm').val();
  	  var eDate= $('#kkk').val();
      var clinic=${patient[0].clinicNumber};
      if(sDate=="choose date") {
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

   </script>
</html>
