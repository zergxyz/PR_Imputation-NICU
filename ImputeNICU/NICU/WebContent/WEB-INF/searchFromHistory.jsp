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
	
	<link type="text/css" href="http://dev.jtsage.com/cdn/datebox/latest/jqm-datebox.min.css" rel="stylesheet" /> 
	<link type="text/css" href="http://dev.jtsage.com/cdn/simpledialog/latest/jquery.mobile.simpledialog.min.css" rel="stylesheet" /> 



	<script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
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

</head> 

<body> 

	<div data-role="dialog" >
	
			<div data-role="header" data-theme="d">
			<h1>Select Time Scope</h1>
	</div>

		<div data-role="content" data-theme="c">
			<h1>History Searching</h1>
			
			 <form  method="post" action="t3.jsp" data-ajax="false" >
				<div data-role="collapsible" data-collapsed="false">
            
							<label for="comDate1">Call Completion Date</label>
							<input name="comDate1" id="comDate1" type="text" data-role="datebox" data-options='{"mode":"calbox", "useNewStyle":true}' />			
							<label for="comDate2">Call Completion Date</label>
							<input name="comDate2" id="comDate2" type="text" data-role="datebox" data-options='{"mode":"calbox", "useNewStyle":true}' />									
							<table>
								<td><button data-ajax="false" data-icon=search >Search</button> </td>
								<td><a href="t2.jsp" data-role="button" data-icon=back  data-theme="a" data-inline=true>Cancel</a></td>
							</table>
				</div>
			      
			
			</form>    
		</div>
		
		
		<div data-role="footer">
		<h4>Footer content</h4>
	</div><!-- /footer -->
			
	</div>


</body>

</html>