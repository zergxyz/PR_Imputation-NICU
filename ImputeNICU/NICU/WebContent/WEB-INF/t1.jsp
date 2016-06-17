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
		<link href="../resources/landing.css" type="text/css" rel="stylesheet">
	<script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
	<script src="http://code.jquery.com/mobile/1.1.0/jquery.mobile-1.1.0.min.js"></script>
	


<script type="text/javascript">


	$(document).ready(function () {
	$("#wiz").wijwizard();
	});
	
</script>
	
</head> 

<body> 


<div data-role="page" id="one">

	<div data-role="header" >
		<h1>Mayo Clinic Research</h1>
	</div><!-- /header -->

	<div data-role="content">
		<ul data-role="listview"  data-inset="true">
			<li><a href="t2.jsp" data-transition="slide"  >History Records</a></li>
			<li><a href="searchDialog.jsp" data-rel="dialog" data-transition="pop">New Records</a></li>
			<li><a href="welcome" data-transition="fade" data-ajax="false">Logout</a></li>

		</ul>

	</div><!-- /content -->


	<div data-role="footer">

		<h4>Footer content</h4>
	</div><!-- /footer -->
	</div><!-- /page -->



</body>

</html>