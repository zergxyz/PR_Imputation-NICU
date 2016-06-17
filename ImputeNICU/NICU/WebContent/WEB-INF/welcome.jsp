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




	<script type="text/javascript">
		$('div').live('pagecreate', function() {
			prettyPrint();
		});
	</script>
	
<script type="text/javascript">
	$( "#register" ).validate({
    submitHandler: function( form ) {
        alert( "Call Login Action" );
    }
});
	
</script>
	

</head> 

<body> 
<div data-role="page" id="one">

	<div data-role="header" >
		<h1>Mayo Clinic Research</h1>
	</div><!-- /header -->


	<img src="../resources/images/rrtLogo.png" />



	<div data-role="content">

		<div id=billboard>	
		
		<form id="register" class="validate" action="t1.jsp" method="post" data-transition="slide" >
		<p>
			<label for="j_username">Username:</label> 
			<input id="j_username" type="text" name="j_username" maxlength="75">		
			<label for="j_password">Password:</label> <input type="password" name="j_password" id="j_password">
		</p>	           
		<p> 
			<input type="submit" class="submit" value="Login"/>
		</p>
		</form>

		</div>

	
	</div><!-- /content -->

	<div data-role="footer" >

		<h4>METRIC</h4>

	</div><!-- /footer -->

</div><!-- /page -->


</body>

</html>