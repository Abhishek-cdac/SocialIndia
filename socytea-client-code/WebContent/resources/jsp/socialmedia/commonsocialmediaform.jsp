<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>

 		<%-- <div id="maildiv"><label class=""><s:text name="Email Id" /><span class="mandatory">*</span></label></td>
		<div class="form-group"><s:textfield name="custRegObj.emailId" id="emailid" cssClass="form-control emailid " placeholder="Email" /></div></div> --%> 
        
        <div id="passdiv"><label class=""><s:text name="Society Key" /><span class="mandatory">*</span></label></td>
		<div class="form-group"><s:textfield name="societyKey" id="societyKey" cssClass="form-control societyKey " placeholder="Society Key" /></div></div>
		<div id="mobilenodiv"><label class=""><s:text name="Mobile No" /><span class="mandatory">*</span></label></td>
		<div class="form-group"><s:textfield name="custRegObj.mobileNo" id="mobileno" cssClass="form-control mobilevalidate" placeholder="Mobile No" /></div></div>
 		<%-- <div id="emaildiv"><label class=""><s:text name="Email Id" /><span class="mandatory">*</span></label></td>
		<div class="form-group"><s:textfield name="custRegObj.emailId" id="emailId" cssClass="form-control emailid" placeholder="Email" /></div></div> --%> 
		<div id="userdata" style="display: none">
		<s:textfield name="custRegObj.userName" id="firstname"  />
		<s:textfield name="custRegObj.lastName" id="lastname"  />
		<s:textfield name="socInfoObj.userid" id="userid" />
		<s:textfield name="socInfoObj.name" id="soc_name"  />
		
			<s:textfield name="custRegObj.emailId" readonly="true" id="emailid"  />
		<s:textfield name="socInfoObj.gender" id="gender"  />
		<s:textfield name="socInfoObj.socialtype" id="social_type"  />
		<s:textfield name="socInfoObj.picurl" id="pic_url"  />
		<s:textfield name="socInfoObj.permtaddrid" id="permt_addr_id"  />
		<s:textfield name="socInfoObj.permtcity" id="permt_city"  />
				
	    <s:textfield name="socInfoObj.permtaddr" id="permt_addr"  />
		<s:textfield name="socInfoObj.permtstate" id="permt_state"  />
		<s:textfield name="socInfoObj.permtcountry" id="permt_country"  />
		<s:textfield name="socInfoObj.permtzip" id="permt_zip"  />
		
		<s:textfield name="socInfoObj.viewurl" id="view_url"  />
		<s:textfield name="socInfoObj.pdfurl" id="pdf_url"  />
		<s:textfield name="socInfoObj.skilllinkedin" id="skill_linkedin"  />
		<s:textfield name="socInfoObj.skillname" id="skill_name"  />
		<s:textfield name="socInfoObj.certificationid" id="certification_id"  />
		<s:textfield name="socInfoObj.certificationname" id="certification_name"  />
		<s:textfield name="socInfoObj.specialities" id="specialities"  />
				
		<s:textfield name="socInfoObj.aboutme" id="aboutme"  />
		<s:textfield name="socInfoObj.age" id="age"  /> 
		</div>
      