<!-- //////////////////////////////////////////////////////////////////////////// -->
 <div class="fixed-action-btn click-to-toggle" style="bottom: 50px; right: 19px;">
                <a class="btn-floating btn-large">
                  <i class="mdi-action-stars"></i>
                </a>
                <ul>
                  <li><a href="Homeform" class="btn-floating teal"><i class="large mdi-action-dashboard"></i></a></li>
                  <li><a href="postnotificationpage" class="btn-floating pink lighten-1"><i class="large mdi-social-notifications"></i></a></li>
                  <li><a href="townshipmgmt?rd=000003_000001_" class="btn-floating purple darken-3"><i class="large mdi-social-group"></i></a></li>
                  <!--   <li><a href="app-widget.html" class="btn-floating yellow darken-1"><i class="large mdi-device-now-widgets"></i></a></li> -->
                  <li><a href="complaintsmgmt?rd=000006_000001_" class="btn-floating red accent-4"><i class="large mdi-action-settings-applications"></i></a></li>
                </ul>
            </div>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
  <!-- START FOOTER --> 
   <s:if test="((#session.sSoctyIdStr!=null && #session.sSoctyIdStr !='' && #session.sSoctyIdStr!=null && #session.sSoctyIdStr!='-1') && (#session.sSoctyColorcode!=null && #session.sSoctyColorcode!='' && #session.sSoctyColorcode!='null'))">
			   	<footer class="page-footer" style="background-color:<s:property value="#session.sSoctyColorcode"/>">
		</s:if>	
		<s:elseif test="((#session.townshipIdStr!=null && #session.townshipIdStr!='null' && #session.townshipIdStr!='-1' && #session.townshipIdStr!='') && (#session.townshipcolourcode!=null && #session.townshipcolourcode!=''))">
			  	<footer class="page-footer" style="background-color:<s:property value="#session.townshipcolourcode"/>">
		</s:elseif>
		<s:else>
				<footer class="page-footer <s:text name="full.theme.bgolor"/>">
		</s:else>
  
  
   	 <div class="footer-copyright">
      <div class="container">
      <span> <a href="#modal3" class="modal-trigger grey-text text-lighten-4">Terms of Use</a>&nbsp;&nbsp; | &nbsp;&nbsp;</span>
		<span> <a href="#modal2" class="modal-trigger grey-text text-lighten-4">Privacy Policy </a>&nbsp;&nbsp; | &nbsp;&nbsp;</span>
		<span> <a href="#modal4" class="modal-trigger grey-text text-lighten-4"> Copyright &copy; 2021 Social India</a> All rights reserved.</span>
 		<span class="right"> Design and Developed by <a class="grey-text text-lighten-4" href="#">Nectar Infotel Solutions Pvt Ltd</a></span>
 		
      <%--  <span>Copyright Â© 2016<a class="waves-effect waves-light modal-trigger" href="#modal4"> Social India All rights reserved.</a>
            </span>
             <span style="align:ce"> <a class="grey-text text-lighten-4" href="#">Privacy Policy</a></span>
           <span class="right"> Design and Developed by <a class="grey-text text-lighten-4" href="#">Peninlog Technologies</a></span> --%>
           
      <%--   <span>Copyright Â© 2016 <a class="waves-effect waves-light btn modal-trigger  green" href="#modal4" >Social India</a> All rights reserved.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label class="button.color.box.create">Privacy Policy</label></span>
        <span class="right"> Design and Developed by <a class="grey-text text-lighten-4" href="#">Peninlog Technologies</a></span> --%>
        
        </div>
    </div>
     
    <jsp:include page="footercontent.jsp"></jsp:include>
                       
              
  </footer>
  <!-- END FOOTER -->
