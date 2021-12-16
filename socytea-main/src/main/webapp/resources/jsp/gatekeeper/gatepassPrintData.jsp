<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
<style type="text/css">
td{ font-family:Roboto;}
.vistortyclass{
font-size: 40px;
color:#009688;
}
.hrclass{
background-color:#6B6B6B;
}
.mobileclass{
float: right;
    margin-right: -98px;
/*   padding: 57px 0 13px 84px; */
margin-top: 50px;
}
.skillclass{
float: right;
    margin-right: -109px;
/*     padding: 104px 0 13px 84px; */
margin-top: 100px;
}
.issueclass{
    font-size: 25px;
    margin-left: -138px;
    padding: 142px 0 0 153px;
}
.issuehrclass{
background-color:#6B6B6B;
margin-right: 20px;
}
.expiryclass{
float: right;
    margin-right: -192px;
    padding: 110px 0 13px 84px;
}
label{
float: right;
    margin-right: 36px;
    margin-top: -58px;
}
.spanclass{
float: right;
    font-size: 16px;
    margin-right: 112px;
    margin-top: -58px;
}
.hrclass2{
margin-top: -15px;
background-color:#6B6B6B;
margin-right: 20px;
}
.breakword{word-break: break-all;word-wrap: break-word;}
</style>
</head>
 <body style="margin: 0;background-color:#FFFFFF; font-size: 15px;">
		<div>
		<div style="background-color: #fcfcfc;width: 430px;margin: 0 auto;border-radius:15px;background-color: #009688;box-shadow: 0 0 5px rgba(86, 96, 117, 0.15);left: 0;max-height: 60px;min-height: 115px;top: 0;z-index: 1999;">
                							<div style="float:left;">
												<div>
												<a class="logo_img" href="#" style="font-family:Roboto;text-decoration: none; color:#ffffff; font-size: 40px; margin: 5px 15px; ">GATEPASS</a>

												</div>
												<div style="float:left"><img  src="<s:text name='Resource.path'/>/images/barcode.png" style="margin-left:50px;width:113px;height:67px;"/></div>


                        						</div >

                						<div style="color:#ffffff;width: 100px;float:right;margin-top:10px"><img  src="<s:text name='Resource.path'/>/images/social/socialindia_roundlogo.png" width="67px;"/>

											<b>Generated on</b><br>
											<span style="float:right;margin-right:20px"><b><s:property value="gatepassObj.EntryDatetime" /></b></span>
                    						</div>

             </div>
             <div style="height:1px; clear:both;"></div>	
<div style="background-color: #fcfcfc;width: 430px;margin: 0 auto;border-radius:15px;background-color:#FFFFFF;box-shadow: 0 0 5px rgba(86, 96, 117, 0.15);left: 0;min-height:470px;max-height:500px;border:3px solid #009688;">

	<div style="">
    	<div style="float:left;">
    	<s:if test="#session.visitorimg!=null && #session.visitorimg!=''">
    		 <img  src="/templogo/gatepass/mobile/<s:property value="#session.GATEPASSID"/>/<s:property value="#session.visitorimg"/>" style="margin-left:16px;height:84px;width:90px;margin-top:20px;float:left; border-radius:50px;"/>
    		<!-- <img  src="<s:text name='Resource.path'/>/images/avatar.jpg" style="margin-left:16px;height:84px;width:90px;margin-top:20px;float:left; border-radius:50px;"/> -->
    	</s:if>
		<s:else>
			<img  src="<s:text name='Resource.path'/>/images/avatar.jpg" style="margin-left:16px;height:84px;width:90px;margin-top:20px;float:left; border-radius:50px;"/>
		</s:else>
    	</div>
    	<div style="float:left;margin-top:20px">
    		<div><b style="font-size:30px;margin-left: 15px;"><b><s:property value="gatepassObj.gatepassNo" /></b></b></div>
			<div style="height:2px; clear:both;"></div>	
			<div><span style="font-size:25px;margin-left: 15px;"><b><s:property value="gatepassObj.visitorName" /></b></span></div>
    	</div>   
	</div>
	
	<div style="height:5px; clear:both;"></div>
	<hr class="hrclass">
	<div class="vistortyclass" style="margin:0 0 0 15px;">
		
		<s:if test="passType.equalsIgnoreCase('1')">
			<span class="<s:text name="view.fontvalue.color" />">Visitor</span>
		</s:if>
		<s:else>
			<span class="<s:text name="view.fontvalue.color" />">Skilled Help</span>
		</s:else>
	 	
	 </div>
	<hr class="hrclass">
	<div class="row col s12">
		<div class="input-field col s6" style="float:left;font-size:20px;margin-left:15px; width: 50%;">
		<b><s:if test="!Block_Name.equalsIgnoreCase('') ">
			BLOCK - <s:property value="Block_Name" />,
			<s:if test="!Flat_Name.equalsIgnoreCase('') ">
			DOOR
			</s:if>
			</s:if>
			<s:else></s:else></b>
		</div>
		
		<div class="input-field col s6" style="float:right;width: 40%">
				<div style="float:right;margin-right:15px">DATE OF BIRTH</div>	
				<div style="height:5px; clear:both;"></div>			
				<span style="float:right;margin-right:15px" class="breakword"><b><s:property value="gatepassObj.dateOfBirth" /></b></span>
		</div>
	</div>
	
	<div style="height:5px; clear:both;"></div>
	
	<div class="row col s12">
		<div class="input-field col s6" style="float:left;font-size:100px;margin-left: 10px;width: 40%;">
			<span> <s:property value="Flat_Name" /></span>
		</div>
		<div  style="float:right;width: 50%;">
			<div class="input-field col s6">
				<div style="height:15px; clear:both;"></div>
				<div style="float:right;margin-right:15px">MOBILE No.</div>
				<div style="height:5px; clear:both;"></div>
				<span style="float:right;margin-right:15px" class="breakword"><b><s:property value="gatepassObj.mobileNo" /></b></span>		
			</div>
			<div style="height:15px; clear:both;"></div>
			<div class="input-field col s6">
				<div style="float:right;margin-right:15px">SKILL</div>		
				<div style="height:5px; clear:both;"></div>
				<span style="float:right;margin-right:15px;" class="breakword"><b><s:property value="skillName" /></b></span>
			</div>
		</div>
	</div>
	
	<div style="height:5px; clear:both;"></div>
	<hr class="hrclass">
	<div class="row col s12">
		<div class="input-field col s6" style="float:left;margin-left: 15px;width: 40%;">
			
				<div style="float:left;">ISSUE DATE</div>
				<div style="height:5px; clear:both;"></div>
				<div style="float:left;"><span><b><s:property value="Visited_date" /></b></span></div>
			
	 	</div>
		<div class="input-field col s6" style="float:right;width: 50%;">
				<div style="float:right;margin-right:15px;">EXPIRY DATE</div>
				<div style="height:5px; clear:both;"></div>
				<div><span style="float:right;margin-right:15px;"><b><s:property value="Visited_exprydateonly" /></b></span></div>
		</div>			 		
	 </div>	 
	<div style="height:5px; clear:both;"></div>
	<hr class="hrclass">
	<div style="float:left;margin-left:15px">
	 	<div><span style="float:right;margin-right:15px;"><b><s:property value="gatepassObj.Description" /></b></span></div>
	 </div>
</div>
</div>				
</body>
</html>!_!<s:property value="flag"/>!_!