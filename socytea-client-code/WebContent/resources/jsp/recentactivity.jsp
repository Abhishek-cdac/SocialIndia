<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<s:iterator value="RecentActList">

                        <div class="recent-activity-list chat-out-list row">
                            <div class="col s3 recent-activity-list-icon"><i class="mdi-social-poll small"></i>
                            </div>
                            <div class="col s9 recent-activity-list-text">
                                <a href="#"><s:property value="entryDatetimeformated"/></a>
                                <p><s:property value="operations"/></p>
                            </div>
                        </div>
                        
                   
                    </s:iterator>