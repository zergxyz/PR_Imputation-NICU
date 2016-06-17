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
         <!-- base css -->
        <link class="links-css" href="../resources/base.css" rel="stylesheet">
		<link class="links-css" href="../resources/datepicker.css" rel="stylesheet">
		<link class="links-css" href="../resources/jquery-ui.css" rel="stylesheet">
		 <link href="../resources/tables.css" rel="stylesheet">
			
         <!-- home page css -->

        <style type="text/css">
            body {
                padding-top: 60px;
                padding-bottom: 40px;
            }
            .sidebar-nav {
                padding: 9px 0;
            }
			
			h1 {
				font-size: 40px;
				line-height: 36px;
				font-family: Times New Roman
			}

        </style>
         <!-- datepicker css -->

         <!-- responsive css -->
        <link href="../resources/bootstrap-responsive.css" rel="stylesheet">
         <!-- media query css -->
        <link href="../resources/media-fluid.css" rel="stylesheet">

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

			<div class="span6 ">
			<img src="../resources/images/mayo.png" width="380px" height="360px" >
		

			</div>
		
			<br>
			<br>
			<br><br>
			<div class="span5 ">
                                        <h1>CC Report</h1>
                                        <p>
                                            Online based report review and generator tool
                                        </p>
										
<form action="ccHome" >
  <fieldset>
    <div id="legend">
      <legend class="">Login</legend>
    </div>
    <div class="control-group">
      <!-- Username -->
      <label class="control-label"  for="username">Username</label>
      <div class="controls">
        <input type="text" id="username" name="username" placeholder="" class="input-xlarge">
      </div>
    </div>
 
    <div class="control-group">
      <!-- Password-->
      <label class="control-label" for="password">Password</label>
      <div class="controls">
        <input type="password" id="password" name="password" placeholder="" class="input-xlarge">
      </div>
    </div>
 
 
    <div class="control-group">
      <!-- Button -->
      <div class="controls">
        <button class="btn btn-success">Login</button>
      </div>
    </div>
  </fieldset>
</form>

													
													
													
								</div>					
													
													
                              
									<br>
							
			
			
				
            </div>
			<div class="row-fluid">
			<br>
			<div class="span4"></div>
				<div class="span4">

</div>


			</div>
			
			
			<br><br>
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


				
			


            });
        </script>
    </body>
</html>
