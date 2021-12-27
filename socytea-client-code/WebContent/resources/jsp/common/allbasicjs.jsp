<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!-- ================================================
    Scripts
    ================================================ -->
	<!--angularjs-->
	<script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins/angular.min.js"></script>
	<script type="text/javascript" src="<s:text name='Resource.path'/>/js/materialize.js"></script>
	<!--prism-->
	<script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins/prism/prism.js"></script>
	<!--scrollbar-->
	<script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins/perfect-scrollbar/perfect-scrollbar.min.js"></script>
	<script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins/formatter/jquery.formatter.min.js"></script>
	<!-- data-tables -->
	<script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins/data-tables/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins/data-tables/data-tables-script.js"></script>		
	<!-- validation -->
	<script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins/jquery-validation/jquery.validate.min.js"></script>
	<script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins/jquery-validation/additional-methods.min.js"></script>
	<!--plugins.js - Some Specific JS codes for Plugin Settings-->
	<script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins.js"></script>
	<!--custom-script.js - Add your own theme custom JS-->
	<script type="text/javascript" src="<s:text name='Resource.path'/>/js/custom-script.js"></script>
	<script type="text/javascript" src="<s:text name='Resource.path'/>/js/required/bootstrap/bootstraptypehead.min.js?yvvctty"></script>
	  <!--sweetalert -->
    <script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins/sweetalert/dist/sweetalert.min.js"></script> 
    <script type="text/javascript" src="<s:text name='Resource.path'/>/js/custom-script.js"></script>
    <script type="text/javascript">
    		function toValiEmail(fildid){		
			if($("#"+fildid).val().length >= 1){					
			var rr = validateEmail($("#"+fildid).val());	
			if(!rr){					 				
				swal("<s:text name="emailfrmt.err.title"/>", "<s:text name="email.frmt.err.msg"/>", "error");
				$("#"+fildid).val("");
				$("#"+fildid).focus();
				return false;
			}else{
				return true;
			}}else{return true;}
			}
    		function toValiMobno(fildid){		
    			if($("#"+fildid).val().length >= 1){					    			
    				if($('#'+fildid).val() < 0.1 ){			 				
    				swal("<s:text name="mobile.err.title"/>", "<s:text name="mobile.frmt.err.msg"/>", "error");
    				$("#"+fildid).val("");
    				$("#"+fildid).focus();
    					return false;
    				}else{
    					return true;
    			}}else{return true;}
    			}    				
			</script>