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
	
	<link type="text/css" href="http://dev.jtsage.com/cdn/datebox/latest/jqm-datebox.min.css" rel="stylesheet" /> 
	<link type="text/css" href="http://dev.jtsage.com/cdn/simpledialog/latest/jquery.mobile.simpledialog.min.css" rel="stylesheet" /> 



	<script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
	<script src="http://code.jquery.com/mobile/1.1.0/jquery.mobile-1.1.0.min.js"></script>
	<script src="../resources/main.js"></script>
	
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
	
	<!-- Step One -->

<div data-role="page" id="step1">



    <div data-role="header">

      

        <h1>Create a new Patient Report</h1>

    </div>

    <div data-role="content">    

	
	
	

        <form method="post" class="msform" data-ajax="false"  >

            <input type="hidden" name="nextStep" value="step2">
			 
			 <div data-role="collapsible" data-collapsed="false">

			 	
			 
			 			<div class="ui-grid-a">
							<div class="ui-block-a">				
								<div data-role="fieldcontain"> 
             					<label >Patient Name:</label> 
             					<input type="text" name="pName" id="pName" value="" required /> 
            					</div>
            				</div>
							<div class="ui-block-b">
								<div data-role="fieldcontain"> 
								<label >Clinic Number:&nbsp</label> 
             					<input type="text" name="cliNum" id="cliNum" value="" required /> 
            					</div>
            			    </div>
            			 </div>
            			 
            			    	   		 			
	   		 		<div data-role="fieldcontain"> 
             		<fieldset data-role="controlgroup">
					<legend>Gender:</legend>
     				<input type="radio" name="gender" id="gender1" value="M" checked="checked" />
     				<label for="gender1">Male</label>
     				<input type="radio" name="gender" id="gender2" value="F"  />
     				<label for="gender2">Female</label>
     				<input type="radio" name="gender" id="gender3" value="U"  />
     				<label for="gender3">Unknown</label>
					</fieldset>
            		</div>
            		
            		 <label for="birthDate">Date of Birth:</label>
			 <input name="birthDate" id="birthDate" type="text" data-role="datebox" data-options='{"mode":"calbox", "useNewStyle":true}' required />							 
			 
			 			 	<div class="ui-grid-a">
							<div class="ui-block-a">				
								<div data-role="fieldcontain"> 
             					<label >Temperature:&nbsp&nbsp&nbsp&nbsp</label> 
             					<input type="text" name="temperature" id="temperature" value="" /> 
            					</div>
            				</div>
							<div class="ui-block-b">
								<div data-role="fieldcontain"> 
								<label >Blood Pressure:</label> 
             					<input type="text" name="bp" id="bp" value="" /> 
            					</div>
            			    </div>
            			    <div class="ui-block-a">				
								<div data-role="fieldcontain"> 
             					<label >Heart Rate:&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</label> 
             					<input type="text" name="heartRate" id="heartRate" value="" /> 
            					</div>
            				</div>
							<div class="ui-block-b">
								<div data-role="fieldcontain"> 
								<label >Spo-2:&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</label> 
             					<input type="text" name="spo" id="spo" value="" /> 
            					</div>
            			    </div>
            			    <div class="ui-block-a">				
								<div data-role="fieldcontain"> 
             					<label >Respiratory: &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</label> 
             					<input type="text" name="resp" id="resp" value="" /> 
            					</div>
            				</div>
            			    <div class="ui-block-b">				
								<div data-role="fieldcontain"> 
            					<label>Pager: &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</label> 
            					<input type="text" name="pager" id="pager" value="" /> 
            					</div>
            				</div>
            			    <div class="ui-block-a">				
								<div data-role="fieldcontain"> 
             					<label for="select-choice-1" class="select">Requesting:&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</label>
						<select name="pRequest" id="pRequest" data-native-menu="false">
						<option value="Patient">Patient</option>
						<option value="RN">RN</option>
						<option value="Patient's family">Patient's family</option>
						<option value="Other">MD</option>
						</select>
            					</div>
            				</div>
            			 </div>
            			 
            			 	<label for="adDate">Admission Date</label>
							<input name="adDate" id="adDate" type="text" data-role="datebox" data-options='{"mode":"calbox", "useNewStyle":true}' required />			
							<label for="adTime">Admission Time</label>
							<input name="adTime" id="adTime" type="text" data-role="datebox" data-options='{"mode":"timebox", "useNewStyle":true}' required /> 	   		 			
			 </div>
			
            			 <br />
            			             
            <div class="ui-body ui-body-b">
		<fieldset class="ui-grid-a">
				<div class="ui-block-a"><button type="submit" data-theme="d">Cancel</button></div>
				<div class="ui-block-b"><button type="submit" data-theme="a">Continue</button></div>
	    </fieldset>
		</div>
        </form>
    </div>
    <div data-role="footer">
        <h4>Page Footer</h4>
    </div>
</div>



<!-- Step Two -->

<div data-role="page" id="step2">



    <div data-role="header">

        <a href="t1.jsp" data-icon="home" data-ajax="false">Home</a>

        <h1>Events Data</h1>

    </div>

    <div data-role="content">    

        <form method="post" class="msform" data-ajax="false">
               
            <input type="hidden" name="nextStep" value="step3">

            <div data-role="collapsible" data-collapsed="false">
            
							<label for="comDate">Call Completion Date</label>
							<input name="comDate" id="comDate" type="text" data-role="datebox" data-options='{"mode":"calbox", "useNewStyle":true}' required />			
							<label for="comTime">Call Completion Time</label>
							<input name="comTime" id="comTime" type="text" data-role="datebox" data-options='{"mode":"timebox", "useNewStyle":true}' required />

						
            	 	 <div class="ui-grid-a">
							<div class="ui-block-a">				
								<div data-role="fieldcontain"> 
             					<label >Clinical Status:</label> 
             					<input type="text" name="cliStat" id="cliStat" value="" /> 
            					</div>
            				</div>
							<div class="ui-block-b">
								<div data-role="fieldcontain"> 
								<label >Diagnostic Case:</label> 
             					<input type="text" name="diagnostic" id="diagnostic" value="" /> 
            					</div>
            				</div>
	   		 			</div>
	   		 			
	   		 			 <div class="ui-grid-a">
							<div class="ui-block-a">				
								<div data-role="fieldcontain"> 
             				<label for="select-choice-1" class="select">Prime Reason:</label>
							<select name="pReason" id="pReason" data-native-menu="false">
							<option value="Physiological criteria">Physiological criteria</option>
							<option value="Staff provider worried">Staff provider worried</option>
							</select>
            			</div> 
            				</div>
							<div class="ui-block-b">
				
            				</div>
	   		 			</div>
	   		 			
            	 	 <div data-role="fieldcontain">
             <fieldset data-role="controlgroup">
        
                	<legend>Extra Chest Exam:</legend>
     				<input type="radio" name="chestInfo" id="chestInfo1" value="Initiated" checked="checked" />
     				<label for="chestInfo1">Initiated</label>
     				
     				<input type="radio" name="chestInfo" id="chestInfo2" value="Not Initiated"  />
     				<label for="chestInfo2">Not Initiated</label>
        
             </fieldset>
            </div>           			
					<div data-role="fieldcontain"> 
             			<label for="select-choice-1" class="select">AVPU Score: &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</label>
						<select name="avpu" id="avpu" data-native-menu="false">
						<option value="Responds to verbal stimuli">Responds to verbal stimuli</option>
						<option value="Alert">Alert</option>
						<option value="Responds to painful stimuli">Responds to painful stimuli</option>
						<option value="Unresponsive">Unresponsive</option>
						</select>
            		</div> 
            	 		<div class="ui-body ui-body-b">
						<fieldset class="ui-grid-a">
						<div class="ui-block-a"><button type="submit" data-theme="d">Cancel</button></div>
						<div class="ui-block-b"><button type="submit" data-theme="a">Continue</button></div>
	    				</fieldset>
            			</div>

         </div>    
        </form>

        

    </div>



    <div data-role="footer">

        <h4>Page Footer</h4>

    </div>



</div>



<!-- Step Three -->

<div data-role="page" id="step3">



    <div data-role="header">

        <a href="t1.jsp" data-icon="home" data-ajax="false">Home</a>

        <h1>Team Intervention Data</h1>

    </div>



    <div data-role="content">    

    

        <form method="post" class="msform" data-ajax="false">

        <input type="hidden" name="nextStep" value="formTesting2.jsp">
		
			 <div data-role="collapsible" data-collapsed="false"> 

            
     <div class="ui-grid-a"> 
	
	<div class="ui-block-a">				
							<div data-role="fieldcontain"> 
             				<fieldset data-role="controlgroup">
					<legend>CP/NIP:</legend>
     				<input type="radio" name="canp" id="canp1" value="CPAP" checked="checked" />
     				<label for="canp1">CPAP</label>
     				<input type="radio" name="canp" id="canp2" value="NIPPY"  />
     				<label for="canp2">NIPPY</label>
					</fieldset>
            			</div> 
            				</div>
							<div class="ui-block-b">
								
								<div data-role="fieldcontain"> 
             		<fieldset data-role="controlgroup">
					<legend>Bag-Mask Ventilation:</legend>
     				<input type="radio" name="bagVen" id="bagVen1" value="Performed" checked="checked" />
     				<label for="bagVen1">Performed</label>
     				<input type="radio" name="bagVen" id="bagVen2" value="Requested"  />
     				<label for="bagVen2">Requested</label>
					</fieldset>
					</div>
            				</div>
            				
            		<div class="ui-block-a">
			 	
			 		<div data-role="fieldcontain"> 
             		<fieldset data-role="controlgroup">
					<legend>Intubation:</legend>
     				<input type="radio" name="intubation" id="intubation1" value="Intubated" checked="checked" />
     				<label for="intubation1">Intubated</label>
     				<input type="radio" name="intubation" id="intubation2" value="None"  />
     				<label for="intubation2">None</label>
					</fieldset>
            			</div> 
				
                </div>			    
             	<div class="ui-block-b"> 
             	
             		<div data-role="fieldcontain"> 
             		<fieldset data-role="controlgroup">
					<legend>Oxygen Admin:</legend>
     				<input type="radio" name="oxy" id="oxy1" value="Supplementary" checked="checked" />
     				<label for="oxy1">Supplementary</label>
     				<input type="radio" name="oxy" id="oxy2" value="Increased"  />
     				<label for="oxy2">Increased</label>
					</fieldset>
					</div>		
				</div>
				         
</div>




	 <div data-role="fieldcontain">
             <fieldset data-role="controlgroup">
        
                 <legend>Actions taken:</legend>
        
                 <input type="checkbox" name="actions" id="checkbox-1" value="Defibrillation" checked="checked" />
                 <label for="checkbox-1">Defibrillation</label>
        
                 <input type="checkbox" name="actions" id="checkbox-2" value="Cardioversion" />
                 <label for="checkbox-2">Cardioversion</label>
        
                 <input type="checkbox" name="actions" id="checkbox-3" value="Bolus Fluid Administration" />
                 <label for="checkbox-3">Bolus Fluid Administration</label>
                 
        
             </fieldset>
            </div>
            
            <div class="ui-grid-a">
            <div class="ui-block-a">
		
		<div data-role="fieldcontain">
		<label >Ending Status:</label> 
        <input type="text" name="edStat" id="edStat" value="" /> 
        </div>	
			
		</div>

	<div class="ui-block-b">

		<div data-role="fieldcontain">
			
			<label for="flip-a">Medical Therapy</label>
			<select name="mtherapy" id="flip-a" data-role="slider">
			<option value="Yes">Yes</option>
			<option value="No">No</option>
			</select>
		</div>
	</div>
            </div>
            
            
           <div data-role="fieldcontain">
		<label >DNR/NI Oders:</label> 
        <input type="text" name="dnr" id="dnr" value="" /> 
        </div>	

        
        <div class="ui-body ui-body-b">
						<fieldset class="ui-grid-a">
						<div class="ui-block-a"><button type="submit" data-theme="d">Cancel</button></div>
						<div class="ui-block-b"><button type="submit" data-theme="a">Continue</button></div>
	    				</fieldset>
            			</div>
			 </div>    
  

    

    

        </form>

        

    </div>



    <div data-role="footer">

        <h4>Page Footer</h4>

    </div>



</div>



<script>

$("#step1").live("pageinit", function() {

    $("form.msform").live("submit", handleMSForm);    

});

</script>
	

</body>

</html>