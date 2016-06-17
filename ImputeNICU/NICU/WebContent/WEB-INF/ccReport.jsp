<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Report Page</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

        <!-- Le styles -->
 <!-- bootstrap css -->
        <link href="../resources/bootstrap.min.css" rel="stylesheet">
         <!-- base css -->
        <link class="links-css" href="../resources/base.css" rel="stylesheet">
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
        <link href="../resources/datepicker.css" rel="stylesheet"/>
         <!-- responsive css -->
        <link href="../resources/bootstrap-responsive.css" rel="stylesheet">
         <!-- media query css -->
        <link href="../resources/media-fluid.css" rel="stylesheet">
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>

        <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
          <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
	
				<script type="text/javascript">
$(function () {
		var str1 = $('#vm').text();
		var str2 = $('#pos').text();
		var str3 = $('#neg').text();
		var vm = str1.split(",");
		var pos = str2.split(",");
		for(var j=0; j<pos.length; j++) { pos[j] = +pos[j]; } 
		var neg = str3.split(",");
		for(var i=0; i<neg.length; i++) { neg[i] = +neg[i]; } 
		
        $('#container').highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: 'Vent Data'
            },
            subtitle: {
                text: 'Mayo Clinic'
            },
            xAxis: {
                categories: vm
            },
            yAxis: {
                min: 0,
                title: {
                    text: 'VT (ml/kg)'
                }
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                    '<td style="padding:0"><b>{point.y:.1f} mm</b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                useHTML: true
            },
            plotOptions: {
                column: {
                    pointPadding: 0.2,
                    borderWidth: 0
                }
            },
            series: [{
                name: 'VT>8',
                data: pos
    
            }, {
                name: 'VT<8',
                data: neg
    
            }]
        });
    });
    

		</script>
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
                    <a class="brand" href="ccHome"><img src="../resources/images/logo-small.png" alt="logo" /></a>

                    <div class="group-menu nav-collapse"> 
                        <ul class="nav pull-right">
                            <li class="divider-vertical"></li>
                            <li class="dropdown">
                                <a data-toggle="dropdown" href="#">Mayo Clinic<b class="caret"></b></a>
                               <ul class="dropdown-menu">
                                    <li>

                                        <div class="modal-header">

                                            <h3>Lei Fan</h3>
                                        </div>
                                        <div class="modal-body">
                                            <div class="row">
                                                <div class="span1"><img src="../resources/images/photo.png" alt="avatar" /></div>
                                                <div class="span3 pull-right">
                                                    <h5>mail@gmail.com</h5>
                                                    <a href="#" class="link-modal" >Account</a>  <a href="#" class="link-modal" >Settings-Privacy</a>

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

                <div id='content' class="span12 section-body">
                    <div id="section-body" class="tabbable">
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#tab1" data-toggle="tab">Report by Patient</a></li>
                            <li ><a href="#tab2" data-toggle="tab">Report by Mode</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active" id="tab1">
								                                <div class="row-fluid">
                                    <div class="span12">
                                        <div id="block-top" class="">
                                            <ul class="thumbnails">


                                                <li class="">
                                                    <a class="btn btn-maniadmin-6" href="pdfReport"  data-original-title="first tooltip"><img src="../resources/images/iconset-info.png" alt='' /><span class="label label-important">PDF</span></a>
                                                </li>

                                                <li class="">
                                                    <a class="btn btn-maniadmin-6" href="xmlReport" ><img src="../resources/images/iconset-info.png"  alt=''/>
                                                        <span class="label label-success">Excel</span></a>
                                                </li>

                                              
                                            </ul>

                                        </div>
                                    </div>
                                </div>
                                <div class="row-fluid">
                                    <!--Tabs1-->
                                    <div class="span12">

	<div id="accordion1" class="accordion">
                                            <div class="accordion-group">
                                                <div class="accordion-heading">
                                                    <a class="accordion-toggle" data-toggle="collapse" href="#notification" data-original-title="">
                                                        <i class="icon-th icon-white"></i> <span class="divider-vertical"></span> 
                                                        
                                                           <c:forEach items="${icus}" var="icu">
                                                        		${icu},
                                                               </c:forEach>  
                
                                                        <i class="icon-chevron-down icon-white pull-right"></i>
                                                    </a>
                                                </div>
                                                <div id="notification" class="accordion-body in collapse" style="height: auto;">
                                                    <div class="accordion-inner paddind">
<table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered" id="example">
	<thead>
		<tr>
			<th>Clinic Number</th>
			<th>Vent Model</th>
			<th>VT>8ml/kg</th>
			<th>VT<8ml/kg</th>
		</tr>
	</thead>
			

														
                                                           
                                                        <tbody>
                                                        
                                                        <c:forEach items="${plist}" var="pp">
                                                        		<tr class="odd gradeX">
                                                                    <td class="  sorting_1">${pp.clinicNO}</td>
                                                                    <td>${pp.ventMode}</td>
																	<td>${pp.pos} (${pp.posPercent})</td>
																	<td>${pp.neg} (${pp.negPercent})</td>
                                                                </tr>
                                                                
                                                               </c:forEach>  
														</tbody></table>
														
														
                                                    </div>
                                                </div>
                                            </div>

                                        </div>
										
	
	
	
                                    </div>
                                    <!--/Tabs1-->
                                </div>
                                <!--/Row fluid-->



								
								
								


                            </div>
                            
                            
                            	<div class="tab-pane" id="tab2">
									
									
									<table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered" id="example">
	<thead>
		<tr>
			<th>Vent Mode</th>
			<th>VT>8ml/kg</th>
			<th>VT<8ml/kg</th>
		</tr>
	</thead>
			

														
                                                           
                                                        <tbody>
                                                        
                                                        <c:forEach items="${vtrepo}" var="repo">
                                                        		<tr class="odd gradeX">
                                                                    <td class="  sorting_1">${repo.mode}</td>
																	<td>${repo.pos}</td>
																	<td>${repo.neg}</td>
                                                                </tr>
                                                                
                                                               </c:forEach>  
														</tbody></table>
									
									
									
                            </div>
                            
                        </div>
                    </div>
                </div>
				<br><br><br>
  
            </div>
            <footer>
                <p><strong>&copy; Maniadmin 2012</strong></p>
            </footer>
            <div class="theme">
                <h4>Style</h4>
                    <a class="darkblue style" href="darkblue.css"></a>
                    <a class="darkred style" href="darkred.css"></a>
                    <a class="default style" href="base.css"></a>
                     <a class="switcher" href="#"><i class="icon-circle-arrow-right"></i></a>
            </div>
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
		<script src="../resources/js/jquery.dataTables.js"></script>
     	<script src="../resources/js/DT_bootstrap.js"></script>
      
        <script src="../resources/js/jquery-ui.min.js"></script>
        <script src="../resources/js/select2.js"></script>
 		<script src="../resources/highcharts.js"></script>
		<script src="../resources/exporting.js"></script>
	
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
                $("#datepicker").datepicker();
// switch style 
                $('a.style').click(function(){
                    var style = $(this).attr('href');
                    $('.links-css').attr('href','css/' + style);
                    return false;
                });
               


           

            });
        </script>
    </body>
</html>
