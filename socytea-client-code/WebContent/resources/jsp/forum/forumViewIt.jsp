<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
				 			<input id="scrollfocus" type="hidden" style="width: 0px; border: none;" />
							<div class="col m12" id="customdivid"> <b>Customer Reviews:</b> </div>						
							<s:iterator value="forumdiscussList" status="autocount">
							<div class="card teal lighten-5">
								<div class="input-fields">
									<div id="heightt" class="row">
										<div class="col s12 m4 l3">
										<div class="col col s4 m4 l4" style="min-height: 110px; top: 22px; position: relative; width: 40%;">
											<s:if test="imageName!=null && imageName!=''">
												<img class="hoverable circle responsive-img valign profile-image pointer"
													src="/templogo/profile/mobile/<s:property value="userId"/>/<s:property value="imageName"/>"
													onclick="toViewlargesizeimgwithsrc(this,'/templogo/profile/web/<s:property value="userId"/>/<s:property value="imageName"/>');"
													id="memberprofimg_<s:property value="userId"/>">
											</s:if>
											<s:else>
											<img class="hoverable circle responsive-img valign profile-image" alt="" src="<s:text name='Resource.path'/>/images/social/profile-default-male.png" id="memberprofimg_<s:property value="userId"/>" onclick="toViewlargesizeimg(this)">
												<%-- <img class="hoverable circle responsive-img valign profile-image pointer"
													src="resources/images/social/defaultimg.png"
													onclick="toViewlargesizeimgwithsrc(this,'resources/images/social/defaultimg.png');"
													id="memberprofimg_<s:property value="userId"/>"> --%>
											</s:else>
										</div>
										<div style="width: auto;">
										<p></p>
										<p></p>
											<div>
												Name :&nbsp;
													<s:if test="firstName!=null && firstName.length()>0 ">
														<s:property value="firstName" />
													</s:if> <s:elseif test="lastName!=null && lastName.length()>0 ">
														<s:property value="lastName" />
													</s:elseif> <s:else>
														<s:property value="mobileNo" />
													</s:else></div>
													<p></p>
										<p></p>
												<div>Date&nbsp;&nbsp;:&nbsp;<s:property
														value="splDate" /></div>
														<p></p>
														<p></p>
														<div>Time&nbsp;:&nbsp;<s:property value="splTime" />
														<p></p>
											</div>
										</div>
										</div>
										<s:set var="discussionDesc" value="discussionDesc" />
										<s:set var="commentsMin" value="commentsMin" />
										<div class="col s12 m8 l9">
										<div class="row">
										<div class="col s12">
										<s:if test="commentLen>150">
											<div style="width: 81%;"
												id="commentmin<s:property value="#autocount.Index+1"/>"
												class="left m">
												<p>
													<%
														String commentmin = request.getAttribute("commentsMin")
																		.toString();
																out.println(commentmin);
													%><span id="" style="cursor: pointer; color: lightseagreen"
														onclick="morefunc1('commentmin<s:property value="#autocount.Index+1"/>','commentsless<s:property value="#autocount.Index+1"/>');">...More</span>
												</p>
											</div>
											<div style="width: 81%; display: none;"
												id="commentsless<s:property value="#autocount.Index+1"/>"
												class="left m">
												<p>
													<%  String last = request.getAttribute("discussionDesc").toString();
														out.println(last);%>
													<span id="" style="cursor: pointer; color: lightseagreen;"
														onclick="morefunc1('commentsless<s:property value="#autocount.Index+1"/>','commentmin<s:property value="#autocount.Index+1"/>');">...Less</span>
												</p>
											</div>
										</s:if>
										<s:else>
											<div style="width: 81%;" id="fullcomment" class="left m">
												<p>
													<%  String last = request.getAttribute("discussionDesc").toString();
														out.println(last);%>
												</p>
											</div>
										</s:else>
										</div>
										</div>
										</div>
									</div>

								</div>
								</div>								
							</s:iterator>
							<s:hidden value="%{topicName}" id="topicname"></s:hidden>
							<s:hidden value="%{topicsDesc}" id="desc"></s:hidden>
							<s:hidden value="%{firstName}" id="firstname"></s:hidden>
							<s:hidden value="%{lastName}" id="lastname"></s:hidden>
							<s:hidden value="%{userMobileNo}" id="userMobileNo"></s:hidden>
							<s:hidden value="%{topicsDescMin}" id="topicsDescMin"></s:hidden>
							<s:hidden value="%{topicsDescLen}" id="topicsDescLen"></s:hidden>					
	<script type="text/javascript">
		function morefunc1(hidediv, showdiv) {

			$("#" + hidediv).hide();
			$("#" + showdiv).show();
		}
	</script>
