<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>CC Report by Lei</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

        <!-- Le styles -->
 <!-- bootstrap css -->
        <link href="../resources/bootstrap.min.css" rel="stylesheet">
         <link class="links-css" href="../resources/base.css" rel="stylesheet">
		<link class="links-css" href="../resources/datepicker.css" rel="stylesheet">
		<link class="links-css" href="../resources/jquery-ui.css" rel="stylesheet">
		 <link href="../resources/tables.css" rel="stylesheet">
		  <style type="text/css">
            body {
                padding-top: 60px;
                padding-bottom: 40px;
            }
            .sidebar-nav {
                padding: 9px 0;
            }

        </style>
         <!-- datepicker css -->

         <!-- responsive css -->
        <link href="../resources/bootstrap-responsive.css" rel="stylesheet">
         <!-- media query css -->
        <link href="../resources/media-fluid.css" rel="stylesheet">
		 <link href="../resources/select2.css" rel="stylesheet"/>
		
        <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
          <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->

    </head>
    
    <body>

        <div class="navbar navbar-fixed-top">
            <div class="navbar-inner">

                <div class="container-fluid">
                    <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </a>
                    <a class="brand" href="#"><img src="../resources/images/logo-small.png" alt="logo" /></a>
                    <ul class="nav pull-left bar-root">
                        <li class="divider-vertical"></li>
                       
                        <li class="dropdown"><a href="#" data-toggle="dropdown" > <i class="icon-envelope icon-white"></i><span class="label label-important">4</span></a> 
                            <ul class="dropdown-menu">
                                <li><a href="inbox.html">Message : 1 <p class='help-block'><small>From: ab.alhyane@gmail.com</small></p><span class="label">23/09/2012</span></a></li>
                                <li class="divider"></li>
                                <li><a href="inbox.html">Message : 2  <p class='help-block'><small>From: ab.alhyane@gmail.com</small></p><span class="label">21/04/2012</span> </a></li>
                                <li class="divider"></li>
                                <li><a href="inbox.html">Message : 3  <p class='help-block'><small>From: ab.alhyane@gmail.com</small></p><span class="label">20/02/2012</span></a></li>
                                <li class="divider"></li>
                                <li><a href="inbox.html">Message : 4  <p class='help-block'><small>From: ab.alhyane@gmail.com</small></p><span class="label">19/01/2012</span></a></li>
                                <li class="divider"></li>
                                <li class="active"><a href="inbox.html"> Show All </a></li>
                            </ul>
                        </li>
                    </ul>
                    <div class="group-menu nav-collapse"> 
                        <ul class="nav pull-right">
                            <li class="divider-vertical"></li>
                            <li class="dropdown">
                                <a data-toggle="dropdown" href="#">Mayo Clinic<b class="caret"></b></a>
                               <ul class="dropdown-menu">
                                    <li>

                                        <div class="modal-header">

                                            <h3>Critical Care Research</h3>
                                        </div>
                                        <div class="modal-body">
                                            <div class="row">
                                                <div class="span2"><img src="../resources/images/mayo.jpg" alt="avatar" /></div>
                                                <div class="span2 pull-right">
                                                    <h5>User Name</h5>
                                                    <a href="#" class="link-modal" >Account</a>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <a href="#" class="btn btn-info pull-left">log out</a>
                                        </div>

                                    </li>
                                </ul>

                            </li>
                        </ul>

                        <form action="#" class="navbar-search pull-right">
                            <input type="text" placeholder="Search" class="search-query span2" >
                        </form>
                    </div>

                </div>
            </div>
        </div>

        <div class="container-fluid">
            <div class="row-fluid">
                
                <div id='content' class="span9 section-body">
                    <div id="section-body" class="tabbable">
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#tab1" data-toggle="tab">Dashboard</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active" id="tab1">
                                <div class="row-fluid">
                                    <div class="span6">
                                        <div id="block-top" class="">
                                            <ul class="thumbnails">
                                                <li class="">
                                                    <a class="btn btn-maniadmin-6" href="#"  data-original-title="Inbox"><img src="../resources/images/iconset-contact.png"  alt=''/><span class="badge badge-important">Message</span></a>
                                                </li>

                                                <li class="">
                                                    <a class="btn btn-maniadmin-6" href="#myModal" data-toggle="modal" data-original-title="Open modal"><img src="../resources/images/iconset-info.png"  alt=''/>
                                                        <span class="label label-success">New Report</span></a>
                                                </li>

                                                <li class="">
                                                    <a class="btn btn-maniadmin-6" href="#"  data-original-title="first tooltip"><img src="../resources/images/iconset-promo.png" alt='' /><span class="badge badge-warning">Auto Job</span></a>
                                                </li>
                                            </ul>
                                            <div class="modal hide fade" id="myModal" style="display: none;">
                                                <div class="modal-header">
                                                    <button data-dismiss="modal" class="close">?</button>
                                                    <h3>Modal Heading</h3>
                                                </div>
                                                <div class="modal-body">

                                                    <table class="table">
                                                        <thead>
                                                            <tr>
                                                                <th>item</th>
                                                                <th>item</th>
                                                                <th>item</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <tr>
                                                                <td>1</td>
                                                                <td><span class="badge badge-important">6</span></td>
                                                                <td><div class="progress progress-danger progress-striped">
                                                                        <div style="width: 40%" class="bar"></div>
                                                                    </div>
                                                                </td>

                                                            </tr>
                                                            <tr>
                                                                <td>2</td>
                                                                <td><span class="badge badge-info">8</span></td>
                                                                <td>    
                                                                    <div class="progress progress-striped active">
                                                                        <div class="bar" style="width: 70%;"></div>
                                                                    </div>
                                                                </td>

                                                            </tr>
                                                            <tr>
                                                                <td>3</td>
                                                                <td><span class="badge badge-success">2</span></td>
                                                                <td>
                                                                    <div class="progress progress-success progress-striped">
                                                                        <div style="width: 100%" class="bar"></div>
                                                                    </div>
                                                                </td>

                                                            </tr>
                                                        </tbody>
                                                    </table>

                                                </div>
                                                <div class="modal-footer">
                                                    <a data-dismiss="modal" class="btn" href="#">Close</a>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
									
										<div class="span4">
                                    <p>S_Date: <input type="text" id="datepicker" /></p>
									 <p>E_Date: <input type="text" id="datepicker2" /></p>
			</form>
                                    </div>
                                </div>


                                <!--/Row fluid-->		
								

								
								
							<!--<style>
.ilarge {
  width: 100px;
  height: 16px !important;
  margin-bottom: 10px !important;
  line-height: 30px !important;
  padding: 11px 19px !important;
  font-size: 16px  !important;
  -webkit-border-radius: 6px;
     -moz-border-radius: 6px;
          border-radius: 6px;
}

.ilarge[class=input-large] {
    width: 600px;
}
</style>	-->
								
								   <div class="row-fluid">
          
<span class="label  label-success">Select your Report</span><br><br>
								  <!-- <form class="navbar-form pull-left">
								   
  <input type="text" class="input-large">
  <button type="submit" class="btn-large">Submit</button>
</form>-->
<select id="e1" style="width: 270px" >
        <optgroup label="A">
                   <option value="V">Vent Report</option>
                  
               </optgroup>
               <optgroup label="W">
                   <option value="CA">Work Load Per Day of the Month</option>
               </optgroup>
               <optgroup label="H">
                
               </optgroup>
               <optgroup label="L">
                   <option value="AL">Level of Care</option>
                  
               </optgroup>
               <optgroup label="N">
                   <option value="CT">Non Patient Activity</option>
               </optgroup>
               <optgroup label="P">
                   <option value="CT">Procedure Report</option>
               </optgroup>
    </select>

								   
									</div>
									<br>
					<a id="gr" class="btn " data-original-title="">Generate Report</a>				
									
									
									
									
									
									

                            </div>
                        </div>
                    </div>
                </div>
                <div class="span3" id="widget">
                    <div id="accordion4" class="accordion">

                        <div class="accordion-group">
                            <div class="accordion-heading">
                                <a href="#elements" data-toggle="collapse" class="accordion-toggle">
                                    <i class="icon-star-empty icon-white"></i> <span class="divider-vertical"></span> ICUs Location <i class="icon-chevron-down icon-white pull-right"></i>
                                </a>
                            </div>
                            <div class="accordion-body collapse in" id="elements">
                                <div class="accordion-inner paddind">
                                    
 <table> 
	<td> <label class="checkbox">
      <input type="checkbox" value="MB5B"> <span class="label">MB5B</span>
    </label> </td>
	<td>&nbsp &nbsp &nbsp </td>
	<td>&nbsp &nbsp &nbsp </td>
	<td>&nbsp &nbsp &nbsp </td>
	<td> <label class="checkbox">
      <input type="checkbox" value="E104"> <span class="label label-warning">E104</span>
    </label> </td>
	
	<tr>
	<td> <label class="checkbox">
      <input type="checkbox" value="MB6B"> <span class="label">MB6B</span>
    </label> </td>
	<td>&nbsp &nbsp &nbsp </td>
	<td>&nbsp &nbsp &nbsp </td>
	<td>&nbsp &nbsp &nbsp </td>
	<td> <label class="checkbox">
      <input type="checkbox" value="MV5D"> <span class="label">MB5D</span>
    </label> </td>
	</tr>
	
	<tr>
	<td> <label class="checkbox">
      <input type="checkbox" value="MB7G"> <span class="label">MB7G</span>
    </label> </td>
	<td>&nbsp &nbsp &nbsp </td>
	<td>&nbsp &nbsp &nbsp </td>
	<td>&nbsp &nbsp &nbsp </td>
	<td> <label class="checkbox">
      <input type="checkbox" value="MB4E"> <span class="label">MB4E</span>
    </label> </td>
	</tr>
	
	<tr>
	<td> <label class="checkbox">
      <input type="checkbox" value="MB3G"> <span class="label">MB3G</span>
    </label> </td>
	<td>&nbsp &nbsp &nbsp </td>
	<td>&nbsp &nbsp &nbsp </td>
	<td>&nbsp &nbsp &nbsp </td>
	<td> <label class="checkbox">
      <input type="checkbox" value="MB5G"> <span class="label">MB5G</span>
    </label> </td>
	</tr>
	
	<tr>
	<td> <label class="checkbox">
      <input type="checkbox" value="MB8D"> <span class="label">MB8D</span>
    </label> </td>
	<td>&nbsp &nbsp &nbsp </td>
	<td>&nbsp &nbsp &nbsp </td>
	<td>&nbsp &nbsp &nbsp </td>
	<td> <label class="checkbox">
      <input type="checkbox" value="MB7D"> <span class="label">MB7D</span>
    </label> </td>
	</tr>
	
		<tr>
	<td> <label class="checkbox">
      <input type="checkbox" value="MB3B"> <span class="label">MB3B</span>
    </label> </td>
	<td>&nbsp &nbsp &nbsp </td>
	<td>&nbsp &nbsp &nbsp </td>
	<td>&nbsp &nbsp &nbsp </td>
	<td> <label class="checkbox">
      <input type="checkbox" value="MB8E"> <span class="label">MB8E</span>
    </label> </td>
	</tr>
	
	<tr>
	<td> <label class="checkbox">
      <input type="checkbox" value="MB5E"> <span class="label">MB5E</span>
    </label> </td>
	<td>&nbsp &nbsp &nbsp </td>
	<td>&nbsp &nbsp &nbsp </td>
	<td>&nbsp &nbsp &nbsp </td>
	<td> <label class="checkbox">
      <input type="checkbox" value="MB6G"> <span class="label">MB6G</span>
    </label> </td>
	</tr>
	
	<tr>
	<td> <label class="checkbox">
      <input type="checkbox" value="MB5C"> <span class="label">MB5C</span>
    </label> </td>
	<td>&nbsp &nbsp &nbsp </td>
	<td>&nbsp &nbsp &nbsp </td>
	<td>&nbsp &nbsp &nbsp </td>
	<td> <label class="checkbox">
      <input type="checkbox" value="MB4D"> <span class="label">MB4D</span>
    </label> </td>
	</tr>
	
	<tr>
	<td> <label class="checkbox">
      <input type="checkbox" value="MB6E"> <span class="label">MB6E</span>
    </label> </td>
	<td>&nbsp &nbsp &nbsp </td>
	<td>&nbsp &nbsp &nbsp </td>
	<td>&nbsp &nbsp &nbsp </td>
	<td> <label class="checkbox">
      <input type="checkbox" value="MB6F"> <span class="label">MB6F</span>
    </label> </td>
	</tr>
	
	<tr>
	<td> <label class="checkbox">
      <input type="checkbox" value="MB7E"> <span class="label">MB7E</span>
    </label> </td>
	<td>&nbsp &nbsp &nbsp </td>
	<td>&nbsp &nbsp &nbsp </td>
	<td>&nbsp &nbsp &nbsp </td>
	<td> <label class="checkbox">
      <input type="checkbox" value="E103"> <span class="label lable-warning">E103</span>
    </label> </td>
	</tr>
	
	<tr>
	<td> <label class="checkbox">
      <input type="checkbox" value="MB7B"> <span class="label">MB7B</span>
    </label> </td>

	</tr>
									
									</table>


  
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <footer>
                <p><strong>Lei Fan 2013</strong></p>
            </footer>

        </div><!--/.fluid-container-->



        <!-- Le javascript
        ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="../resources/js/jquery.min.js"></script>
        <script src="../resources/js/bootstrap.min.js"></script>

        
        <script src="../resources/js/jquery.flot.js"></script>
        <script src="../resources/js/jquery.flot.resize.js"></script>
        <script src="../resources/js/jquery.flot.pie.js"></script>
        <script src="../resources/js/customcharts.js"></script>

      
        <script src="../resources/js/jquery-ui.min.js"></script>
 		 <script src="../resources/js/select2.js"></script>
 		
         <script type="text/javascript">
            $(document).ready(function(){
                $('.togglemenuleft').click(function(){
                    $('#menu-left').toggleClass('span1');
                    $('#menu-left').toggleClass('icons-only');
                    $('#menu-left').toggleClass('span3');
                    
                    $('#content').toggleClass('span6');
                    $('#content').toggleClass('span8');
                    
                    $(this).find('i').toggleClass('icon-circle-arrow-right');
                    $(this).find('i').toggleClass('icon-circle-arrow-left');
                    $('#menu-left').find('span').toggle();
                    $('#menu-left').find('.dropdown').toggle();
                });

                $('#menu-left a').click(function(){
                    $('#menu-left').find('a').removeClass('active');
                    $(this).addClass('active');
                });
        // tool tip
                $('a').tooltip('hide');

        //datePciker
               
				
				var sDate = $('#datepicker').datepicker( );
				var eDate = $("#datepicker2").datepicker( );
				
				
				var rc = "icu";
				

				$("#e1").select2();
			
				
				
				
				$("#gr").bind('click', function(e){
    	
						var sDate= $('#datepicker').val();
						var eDate= $('#datepicker2').val();
						//window.location.href ="hist?startDate="+sDate+"&endDate="+eDate; 
						//window.location.href ="ccReport"; 
						//alert($(rc).select2('data').text);
						
						$('input:checkbox:checked').each(function () {
						
							rc+=$(this).val()+","+$(this).val()+","; 
						
						
						});
						
						if(rc!="icu") { 
						
							//alert(rc);
							//send to controller 
							window.location.href ="ccReport?icu="+rc+"&sDate="+sDate+"&eDate="+eDate;
							//window.location.href ="ccReport?icu="+rc+"&sDate="+sDate+"&eDate="+eDate;
							//$.post("ccReport", { icu: rc, sDate: sDate, eDare: eDate } );
						}
				});

				
				
            });
        </script>
    </body>
</html>
    
    
		 
        