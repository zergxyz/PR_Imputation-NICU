<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html> 

<html>


<head>

	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1"> 

	<title>Search Patient</title> 

	<link rel="stylesheet" href="http://code.jquery.com/mobile/1.1.0/jquery.mobile-1.1.0.min.css" />
	<script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
	<script src="http://code.jquery.com/mobile/1.1.0/jquery.mobile-1.1.0.min.js"></script>

</head> 

<body> 

	<div data-role="dialog" >
	
			<div data-role="header" data-theme="d">
			<h1>Dialog</h1>
	</div>

		<div data-role="content" data-theme="c">
			<h1>Patients Searching</h1>
			
			 <form  method="post" action="tPatientInfo.jsp" data-ajax="false" >
			<p>
				<input type="search" name="clinicNumber" id="search-basic" value=""  placeholder="patient's clinic number"/>
			</p>
			<table>
				<td><button data-ajax="false" data-icon=search >Search</button> </td>
				<td><a href="t1.jsp" data-role="button" data-icon=back  data-theme="a" data-inline=true>Cancel</a></td>
			</table>
			      
			
			</form>    
		</div>
		
		
		<div data-role="footer">
		<h4>Footer content</h4>
	</div><!-- /footer -->
			
	</div>


</body>

</html>