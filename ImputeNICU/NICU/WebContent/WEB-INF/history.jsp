<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>



<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Bootstrap, Home</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <link href="../resources/bootstrap.css" rel="stylesheet">
    <style type="text/css">
      body {
        padding-top: 60px;
        padding-bottom: 40px;
      }
    </style>
    <link href="../resources/bootstrap-responsive.css" rel="stylesheet">
	
	
				<style>
				.tcv h1 {
			margin-bottom: 0;
			font-size: 63px;
			line-height: 1;
			letter-spacing: -1px;
			color: inherit;
			}
				
					.tb {
						width: 100%;						
    					height: 270px; 
    					overflow: auto;
						padding: 0px 0 0px 0px;
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

	<link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />
    <script src="http://code.jquery.com/jquery-1.8.3.js"></script>
    <script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
    

  </head>

  <body>

    <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </a>
          <a class="brand" href="#"></a>
          <div class="nav-collapse collapse">
            <ul class="nav">
              <li class="active"><a id="home" href="nicuHome">Home</a></li>
            </ul>
          </div>
        </div>
      </div>
    </div>

    <div class="container">

      <!-- Main hero unit for a primary marketing message or call to action -->
		<br><br>
		<div class="row">
			<div class="span5">
			<div class="tcv">
        <h1>NICU REPORT</h1>
		<br>
		&nbsp&nbsp<span class="label label-success">Latest version: 0.5</span>
		<p>&nbsp&nbspWelcome to use this online report application, you can review all the nicu
		&nbsp&nbsppatients in the current icu and choose the specific date for a chosen 
		&nbsp&nbsppatient to see more details.</p>
		<p>&nbsp&nbsp<input id="sss" type="text" placeholder="Patient Number"></p>
        <p>&nbsp&nbsp<a id="bt1" class="btn btn-primary ">Search</a></p>
		</div>
		</div>
		
		<div class="span7">

		<img src="../resources/images/shield.png" />
		
		</div>
		</div>
		

	<br>
	<div class="row">
		<div class="span3">
		<div class="tb">
			&nbsp&nbsp<a href="#overview"><i class="icon-list"></i> Patients in ICU</a>
        	<br>
			<c:forEach items="${persons}" var="person">			
			&nbsp&nbsp<a href="tpb?clinicNumber=${person.clinicNumber}"><i class="icon-user"></i>&nbsp<c:out value="${person.clinicNumber}" /></a>
			<br>
	 		</c:forEach> 			       
		</div>
		</div>
		
		<div  class="span9">
		<div id="div2" class="tb">
		<a>Admission Information</a>
		 <table class="table table-hover">
              <thead>
                <tr>
                  <th>Patient Name</th>
                  <th>Clinic Number</th>
                  <th>Patient Name</th>
                  <th>Admission Time</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td> </td>
                  <td> </td>
                  <td> </td>
                  <td> </td>
                </tr>
              </tbody>
            </table>
           
			<form class="form-inline">
			 <a>Seach Patients by Date</a><br><br>
			<input type="text" id="dnm" value="choose date">
			<input type="text" id="kkk" value="date"">
			<a class="btn btn-primary ">Get Patients</a>
			</form>
		</div>
		</div>
    </div>


       <script type="text/javascript">
       
       $("#bt1").click(function(){
   	    $("#div2").load("fp?clinicNumber=" +$("#sss").val());
   	  });

      $('a').bind('click', function(e) {
    	 
    	 
    	  var url = $(this).attr('href');
    	  $('#div2').load(url);
    	  e.preventDefault(); // stop the browser from following the link

    	});
      
      $('#home').bind('click', function(e) {
     	 
    	  window.location = "nicuHome";
    	

    	});
      
      $( "#dnm" ).datepicker();
  	$( "#kkk" ).datepicker();
  	
    $("button").click(function(){
  	  var sDate= $('#dnm').val();
  	  var eDate= $('#kkk').val();
        var clinic='${patient.clinicNumber}';
        
  	  window.location = "historical?startDate="+sDate+"&endDate="+eDate;
	  });

   </script>
			</div>
  </body>
</html>
