<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
<style type="text/css">
td{ font-family:Roboto;}
</style>
</head>
 <body style="margin: 0;background-color:#FFFFFF; font-size: 15px;">
 <div class="logo">			
						<h2 style="margin-bottom: 8px; margin-top: 8px; margin-left: 8px;">
						<a href="posthomepage">						
						<table class="logoTable" style="background-color:#009688;">
							<tr>
								<s:if test="((ivrScietyid!=null && ivrScietyid !='' && ivrScietyid!='nul' && ivrScietyid!='-1') && (ivrScietyimgname!=null && ivrScietyimgname!='' && ivrScietyimgname!='null') )">
									<td style="width: 50px;height:50px"> 
										<img src="/templogo/society/web/<s:property value="ivrScietyid"/>/<s:property value="ivrScietyimgname"/>" width="100%;" style="width: 60px;height:60px"/>
									</td>									
								</s:if>
								
								<s:elseif test="(ivrScietyid!=null && ivrScietyid !='' && ivrScietyid !='null' && ivrScietyid !='-1') && (ivrTwnshipimgname!=null && ivrTwnshipimgname!=''&& ivrTwnshipimgname!='null')">
									<td style="width: 50px;height:50px"> 
										<img src="/templogo/township/web/<s:property value="ivrScietyid"/>/<s:property value="ivrTwnshipimgname"/>" width="100%;" style="width: 60px;height:60px"/>
									</td>									
								</s:elseif>
								
								<s:else>
									<td style="width: 50px;height:50px">
										<img  src="<s:text name='Resource.path'/>/images/social/social-india-final-logo.pn	g" width="100%;" style="width: 60px;height:60px"/>
									</td>									
						   		</s:else>
						   		
						   		 <s:if test="societyName!=null && societyName !='' && societyName !='null' ">
								    <td>										
										<div style="background-color: #fcfcfc;width: 900px;margin: 0 auto;">
                							<div style="background-color: #009688;box-shadow: 0 0 5px rgba(86, 96, 117, 0.15);left: 0;max-height: 60px;min-height: 70px;top: 0;z-index: 1999;">
                    						<div style="background-color: #009688;height: 70px;width: 280px;float:left;">
                        						<a class="logo_img" href="#" style="font-family:Roboto;text-decoration: none; color:#ffffff; font-size: 40px; margin: 5px 15px; float:left;"><s:property value="societyName"/></a>
                    						</div>
                						</div>
                						</div>
									</td>
								</s:if>
						   		
							    <s:elseif test="townshipName!=null && townshipName !='' && townshipName !='null' ">
								    <td>
										<div style="background-color: #fcfcfc;width: 900px;margin: 0 auto;">
                							<div style="background-color: #009688;box-shadow: 0 0 5px rgba(86, 96, 117, 0.15);left: 0;max-height: 60px;min-height: 70px;top: 0;z-index: 1999;">
                    						<div style="background-color: #009688;height: 70px;width: 280px;float:left;">
                        						<a class="logo_img" href="#" style="font-family:Roboto;text-decoration: none; color:#ffffff; font-size: 40px; margin: 5px 15px; float:left;"><s:property value="townshipName"/></a>
                    						</div>
                						</div>
                						</div>
									</td>
								</s:elseif>
								<s:else>
									<td>
										 <div style="background-color: #fcfcfc;width: 900px;margin: 0 auto;">
                							<div style="background-color: #009688;box-shadow: 0 0 5px rgba(86, 96, 117, 0.15);left: 0;max-height: 60px;min-height: 70px;top: 0;z-index: 1999;">
                    						<div style="background-color: #009688;height: 70px;width: 280px;float:left;">
                        						<a class="logo_img" href="#" style="font-family:Roboto;text-decoration: none; color:#ffffff; font-size: 40px; margin: 5px 15px; float:left;">Socytea</a>
                    						</div>
                						</div>
                						</div>
						    		</td>
						    	</s:else>
								</tr>
						</table>						
							</a>
						</h2>					
				</div>
       <!--  <div style="width: 100%;">
            <div style=" background-color: #fcfcfc;width: 700px;margin: 0 auto;">
                <div style="background-color: #009688;box-shadow: 0 0 5px rgba(86, 96, 117, 0.15);left: 0;max-height: 60px;min-height: 70px;top: 0;z-index: 1999;">
                    <div style="background-color: #009688;height: 70px;width: 280px;float:left;">
                        <a class="logo_img" href="#" style="font-family:Roboto;text-decoration: none;color:#ffffff;font-size: 40px;margin: 5px 15px;float:left;">Social India</a>
                    </div>
                </div>
                </div></div> -->
                
                
<div style="margin-left: 25px;"> 
<div style="font-size: 17px;font-weight:600;font-family:Roboto;">Society Admin Detail</div>
<div style="clear:both;height:10px;"></div>
<table>
<tr>
<td >Township Name </td> <td>:</td>
<td><b><s:property value="townshipName"/></b></td>
</tr>
<tr>
<td>Society Name </td> <td>:</td>
<td><b><s:property value="societyName"/></b></td>
</tr>
<tr></tr>
<tr><td colspan="2"></td></tr>
<tr><td colspan="3" style="font-size: 17px;font-weight:600;font-family:Roboto;">Login Detail</td></tr>
<tr></tr>
<tr>
<td>Mobile No </td>
<td>:</td>
<td><b><s:property value="mobileNo"/></b></td>
</tr>
<tr>
<td>Email </td>
<td>:</td>
<td><b><s:property value="emailId"/></b></td>
</tr>
<tr>
<td>Password </td>
<td>:</td>
<td><b><s:property value="password"/></b></td>
</tr>
<tr>
<td>Activation Key </td>
<td>:</td>
<td><b><s:property value="activationkey"/></b></td>
</tr>
</table>
</div>
<div class="clear" style="height:10px;"></div>
<div style="font-size: 17px;font-weight:600;font-family:Roboto; margin-left:25px;"><b>Terms & Conditions</b></div>
<div class="clear" style="height:5px;"></div>
<div style="margin-left:25px;"><b>1. Introduction</b>
 <p style="margin-left:30px;"> All Content created and published on the digital platforms under the url http://www.business-standard.com the mobile browser site, applications, Business Standard E-paper belong to Business Standard Private Limited and its licensors who own all intellectual property rights</p>
</div>
<div style="margin-left:25px;"><b>2.Registration Access and Use</b>
 <p style="margin-left:30px;"> We welcome users to register on our digital platforms. We offer the below mentioned registration services which may be subject to change in the future. All changes will be appended in the terms and conditions page and communicated to existing users by email.</p>
</div>
<div style="margin-left:25px;"><b>3.Privacy Policy and Registration</b>
 <p style="margin-left:30px;"> All information received by us from your registration on business-standard.com or other digital products of Business Standard will be used by Business Standard in accordance with our Privacy Policy. Kindly read the below mentioned details.</p>
</div>
<div style="margin-left:25px;"><b>4.Personal Subscription Services </b>
 <p style="margin-left:30px;"> Personal subscription services include Business Standard premium access to behind the pay wall content. When you subscribe to Business Standard Premium access, you gain access to opinion pieces, comment and exclusive features specially chosen for you by the Business Standard editors.</p>
</div>
<div style="margin-left:25px;"><b>5.Subscription Period, Renewal and Cancellation of Personal Subscriptions</b>
 <p style="margin-left:30px;"> If you chose to pay monthly, your subscription will continue until you tell us that you no longer wish to receive it, in which case you will stop paying the monthly fees. We will notify you at least 7 days in advance of any changes to the price in your subscription that will apply upon next monthly renewal.</p>
</div>
<div style="margin-left:25px;"><b>6.Who Your Personal Subscription Contract is with</b>
 <p style="margin-left:30px;"> All Content created and published on the digital platforms under the url http://www.business-standard.com the mobile browser site, applications, Business Standard E-paper belong to Business Standard Private Limited and its licensors who own all intellectual property rights</p>
</div>
<div style="margin-left:25px;"><b>7.User Generated Content</b>
 <p style="margin-left:30px;"> "BS" and "Business Standard" are registered trade marks of Business Standard Private Limited and you may not use them without prior written permission from Business Standard. You are permitted to use the content on this platform only as set out in our Copyright Policy.</p>
</div>
<div style="margin-left:25px;"><b>8.Changes to Terms and Conditions and Validity</b>
 <p style="margin-left:30px;"> 
 These terms and conditions were published on 1st June 2016 and replace with immediate effect the terms and conditions previously publishers.
</p>
</div>
<div class="clear" style="height:30px;"></div>
</body>
 <footer class="page-footer teal">
    <div style="background-color: #fcfcfc;width: 900px;margin: 0 auto;">
      <div style="background-color: #009688;color:#d1d1d1;box-shadow: 0 0 5px rgba(86, 96, 117, 0.15);left: 0;max-height: 50px;min-height: 50px; line-height:50px;top: 0;z-index: 1999;">
        <span>Copyright &copy; 2016 <a href="#" class="color:#fff;">Social India</a> All rights reserved.</span>
        <span style="float:right;"> Design and Developed by <a href="#" class="grey-text text-lighten-4">Peninlog Technologies</a></span>
        </div>
    </div>
  </footer>
</html>!_!<s:property value="flag"/>!_!