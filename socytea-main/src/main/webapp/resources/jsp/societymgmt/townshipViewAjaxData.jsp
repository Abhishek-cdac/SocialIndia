<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
	<div class="row">
                <div class="input-field col m6">
                  <div class="row">
                  <div class="input-field col m7"><b>Address Information :
                   <a target="_black" href="https://www.google.co.in/maps/place/<s:property value="townShipMst.cityName"/>" style="margin-top:5px;" class="tooltipped" data-toggle="modal" data-target="#addnewaccount" data-position="bottom" data-delay="<s:text name="material.tooltip.delay"/>" data-tooltip="Location"><i class="mdi-communication-location-on tinysmall green-text text-darken-1"></i></a></b></div>
                  </div>
                 </div>
    </div>
    <div class="row">
                <div class="input-field col m6">
                  <div class="row">
                  <div class="input-field col m5">Address</div>
                   <div class="input-field col m7"> : <span class="<s:text name="view.fontvalue.color" />">  <s:property value="townShipMst.address"/></span></div>
                      </div>
                    </div>
     </div>
	<div class="row">
                <div class="input-field col m6">
                  <div class="row">
                  <div class="input-field col m5"><s:text name="Country"></s:text></div>
                   <div class="input-field col m7"> : <span class="<s:text name="view.fontvalue.color" />">  <s:property value="townShipMst.countryName"/></span></div>
                      </div>
                </div>
     </div> 									
	<div class="row">
                <div class="input-field col m6">
                  <div class="row">
                  <div class="input-field col m5"><s:text name="State"></s:text></div>
                   <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />">  <s:property value="townShipMst.stateName"/></span></div>
                      </div>
                    </div>
     </div>	
     <div class="row">
                <div class="input-field col m6">
                  <div class="row">
                  <div class="input-field col m5"><s:text name="City"></s:text></div>
                   <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />">  <s:property value="townShipMst.cityName"/></span></div>
                      </div>
                    </div>
      </div>	
      <div class="row">
                <div class="input-field col m6">
                  <div class="row">
                  <div class="input-field col m5"><s:text name="Pin Code"></s:text></div>
                   <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />">  <s:property value="townShipMst.pinCode"/></span></div>
                      </div>
                      <div style="clear: both; height:10px;"></div>
         </div></div>							