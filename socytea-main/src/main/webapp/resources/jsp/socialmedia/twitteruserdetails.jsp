<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<s:head />
<sj:head locale="en" jqueryui="true" jquerytheme="pagination"
	customBasepath="resources" />
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form id="smform" role="form" method="post" action="smnewcustomerform">
<input type="hidden"  data-toggle="modal" id="newbtn" data-target=".social" />
<div class="modal fade social" tabindex="-1" role="dialog"  id="fbdiv" aria-labelledby="gridSystemModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" onclick="redirect();" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="gridSystemModalLabel">Modal title</h4>
      </div>
      <div class="modal-body">
      	<div id="maildiv"><label class=""><s:text name="Email Id" /><span class="mandatory">*</span></label></td>
		<div class="form-group"><s:textfield name="custRegObj.emailId" id="emailid" cssClass="form-control emailid " placeholder="Email"  /></div></div>
		<div id="mobilenodiv"><label class=""><s:text name="Mobile No" /><span class="mandatory">*</span></label></td>
		<div class="form-group"><s:textfield name="custRegObj.mobileNo" id="mobileno" cssClass="form-control mobilevalidate" placeholder="Mobile no" /></div></div>
		<s:textfield name="custRegObj.userName" id="firstname"  value="%{screenname}"/>
		<s:textfield name="custRegObj.lastname" id="lastname" />
		<s:textfield name="socInfoObj.userid" id="userid"  value="%{twtid}"/>
		<s:textfield name="socInfoObj.picurl" id="pic_url" value="%{profileimg}" />
		<s:textfield name="socInfoObj.name" id="soc_name"  value="%{name}"/>
		<s:textfield name="socInfoObj.aboutme" id="aboutme" value="%{profiledesc}" />
		<s:textfield name="socInfoObj.viewurl" id="view_url"  value="%{profileurl}"/>
		<s:textfield name="socInfoObj.gender" id="gender"  />
		<s:textfield name="socInfoObj.age" id="age"  />
		<s:textfield name="socInfoObj.permtaddrid" id="permt_addr_id"  />
		<s:textfield name="socInfoObj.permtaddr" id="permt_addr"  />
		<s:textfield name="socInfoObj.permtstate" id="permt_state"  />
		<s:textfield name="socInfoObj.permtcountry" id="permt_country"  />
		<s:textfield name="socInfoObj.permtzip" id="permt_zip"  />
		<s:textfield name="socInfoObj.permtcity" id="permt_city"  />
		
		<s:textfield name="socInfoObj.specialities" id="specialities"  />
		<s:textfield name="socInfoObj.socialtype" id="social_type"  value="2"/>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal" onclick="redirect();">Close</button>
        <button class="btn btn-primary" type="submit">Save</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<s:hidden id="sysruleId" name="sysruleId" value="%{systemrule.condition}"></s:hidden>
</form>
</body>


<!-- CSS -->
	<link type="text/css" href="<s:text name='Resource.path'/>/assets/css/required/bootstrap/bootstrap.min.css" rel="stylesheet">
	<link type="text/css" href="<s:text name='Resource.path'/>/assets/css/optional/bootstrapValidator.min.css" rel="stylesheet" />
	<link type="text/css" href="<s:text name='Resource.path'/>/assets/fonts/metrize-icons/styles-metrize-icons.css" rel="stylesheet">
	<link type="text/css" href="<s:text name='Resource.path'/>/assets/css/styles-core.css" rel="stylesheet" />
	<link type="text/css" href="<s:text name='Resource.path'/>/assets/css/styles-core-responsive.css" rel="stylesheet" />
	<link type="text/css" href="<s:text name='Resource.path'/>/assets/css/demo-files/pages-signin-signup.css" rel="stylesheet" />

	<!-- Javascript -->
	<script type="text/javascript" src="<s:text name='Resource.path'/>/assets/js/required/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="<s:text name='Resource.path'/>/assets/js/required/bootstrap/bootstrap.min.js"></script>
	<script type="text/javascript" src="<s:text name='Resource.path'/>/assets/js/optional/bootstrapValidator.min.js"></script>
	
	<script type="text/javascript" src="<s:text name='Resource.path'/>/js/formValidation.min.js" ></script>
	<script type="text/javascript" src="<s:text name='Resource.path'/>/js/jquery.min.js"></script>
	<script type="text/javascript" src="<s:text name='Resource.path'/>/js/bootstrap.min-1.js"></script>
	
	<script type="text/javascript">
$(document).ready(function(){
	
	$("#newbtn").click();

  	$('#smform')
  	.bootstrapValidator(
  			{
  				message : 'This value is not valid',
  				
  				feedbackIcons : {
  					valid : '<s:text name="Text.feedback.ok" />',
  					invalid : '<s:text name="Text.feedback.remove" />',
  					validating : '<s:text name="Text.feedback.refresh" />'
  				},
  				fields : {
  	emailid : {
		selector : '.emailid',
		validators : {
			notEmpty : {
				message : '<s:text name="Error.customerreg.emailid" />'
			},
			stringLength : {
				min : 8,
				max : 70,
				message : '<s:text name="Error.customerreg.emailid.length" />'
			},
			emailAddress : {
				message : '<s:text name="Error.customerreg.emailid.validate" />'
			}
		}
	},
	mobilevalidate : {
		selector : '.mobilevalidate',
		validators : {
			notEmpty : {
				message : '<s:text name="Error.customerreg.mobileno" />'
			},
			stringLength : {
				min : 5,
				max : 15,
				message : '<s:text name="Error.customerreg.mobileno.length" />'
			},
			regexp : {
				regexp : /^[0-9]+$/,
				message : '<s:text name="Error.customerreg.mobileno.validate" />'
			}

		}
	}
  				}
  			});
  	
	$("#modalredirect").click(function(){
			redirect();
	});
});

function redirect(){
	window.location = "/Dahabshiil/loginprocess";
}

</script>
</html>