<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="amalgaI.InPatient" %>



<!DOCTYPE html> 

<html>



<head>

	<meta charset="utf-8">

	<meta name="viewport" content="width=device-width, initial-scale=1"> 

	

	<title>Single page template</title> 

	<link rel="stylesheet" href="http://code.jquery.com/mobile/1.1.0/jquery.mobile-1.1.0.min.css" />
	<link href="../resources//landing.css" type="text/css" rel="stylesheet">
	<link type="text/css" href="http://dev.jtsage.com/cdn/datebox/latest/jqm-datebox.min.css" rel="stylesheet" /> 
	<link type="text/css" href="http://dev.jtsage.com/cdn/simpledialog/latest/jquery.mobile.simpledialog.min.css" rel="stylesheet" /> 	

	<script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="http://jzaefferer.github.com/jquery-validation/jquery.validate.js"></script>
	<script src="http://code.jquery.com/mobile/1.1.0/jquery.mobile-1.1.0.min.js"></script>
	
	<script type="text/javascript" src="http://dev.jtsage.com/jquery.mousewheel.min.js"></script>
	<script type="text/javascript" src="http://dev.jtsage.com/cdn/datebox/latest/jqm-datebox.core.min.js"></script>
	<script type="text/javascript" src="http://dev.jtsage.com/cdn/datebox/latest/jqm-datebox.mode.calbox.min.js"></script>
	<script type="text/javascript" src="http://dev.jtsage.com/cdn/datebox/latest/jqm-datebox.mode.datebox.min.js"></script>
	<script type="text/javascript" src="http://dev.jtsage.com/cdn/datebox/latest/jqm-datebox.mode.flipbox.min.js"></script>
	<script type="text/javascript" src="http://dev.jtsage.com/cdn/datebox/latest/jqm-datebox.mode.durationbox.min.js"></script>
	<script type="text/javascript" src="http://dev.jtsage.com/cdn/datebox/latest/jqm-datebox.mode.slidebox.min.js"></script>
	<script type="text/javascript" src="http://dev.jtsage.com/cdn/datebox/i18n/jquery.mobile.datebox.i18n.en_US.utf8.js"></script>
	
	<script type="text/javascript" src="http://dev.jtsage.com/cdn/simpledialog/latest/jquery.mobile.simpledialog.min.js"></script>
	<script type="text/javascript" src="demos/extras.js"></script>
	<script type="text/javascript" src="http://dev.jtsage.com/gpretty/prettify.js"></script>
	<link type="text/css" href="http://dev.jtsage.com/gpretty/prettify.css" rel="stylesheet" />
	
</head> 

<body> 

<div data-role="page" id="two" data-ajax="false">

	<div data-role="header">
		
		<a href="t1.jsp" data-icon="home">Home</a>
		<h1>History Records</h1>

	</div><!-- /header -->

	<div data-role="content" >	

 <ul data-role=listview data-inset="true" data-filter=true data-dividertheme=a data-ajax="false">

 <li data-role=list-divider>Patients within this week</li>
		
	   <c:forEach items="${persons}" var="person">
	    
	  	 <li>
      
        <a href="recordContents?callCompleteDtm=${person.callCompleteDtm}" data-transition="slide" >  <img src= "../resources/images/p-logo.png" class=ui-li-icon /> <c:out value="${person.patientName}" /> <c:out value="${person.gender}" /> <c:out value="${person.clinicNumber}" /> <c:out value="${person.callCompleteDtm}" /></a>
      	</li>
      </c:forEach>       
         



 <li data-role=list-divider>More Patients from previous time</li>

 <li>

       <a href="searchFromHistory.jsp" data-transition="flip"> <img src="../resources/images/p-logo.png" class=ui-li-icon /> Check Details</a>

      </li>



</ul>

		

		

	</div><!-- /content -->

	

	<div data-role="footer">

		<h4>Page Footer</h4>

	</div><!-- /footer -->

</div><!-- /page two -->











</body>

</html>