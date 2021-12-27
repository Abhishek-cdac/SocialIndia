<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<div class="row">
                  <div class="input-field col m6">
                  		<div class="row">
                  			<div class="input-field col m5"><b><s:text name="text.jvm.details" /> : </b></div>
                  		</div>
                  </div>
             </div>
             <div class="clear height5px"></div>
<div class="row">
              <div class="input-field col m6">
                  <div class="row">
                  <div class="input-field col m5"><s:text name="text.usedmemory" /></div>
                   <div class="input-field col m7">:  <s:property value="UsedMemory"/> MB</div>
                      </div>
                    </div>
                   <div class="input-field col m6">          
                  <div class="row">
                  <div class="input-field col m5"><s:text name="text.freememory" /></div>
                   <div class="input-field col m7">:  <s:property value="FreeMemory"/> MB</div>
                      </div>
                      </div>
							</div>
							<div class="row">
                <div class="input-field col m6">
                  <div class="row">
                  <div class="input-field col m5"><s:text name="text.totalmemory" /> </div>
                   <div class="input-field col m7">:  <s:property value="TotalMemory"/> MB</div>
                      </div>
                    </div>
                   <div class="input-field col m6">
               <div class="row">
                        <div class="input-field col m5"><s:text name="text.maxmemory" /></div>
						  <div class="input-field col m7">:    <s:property value="MaxMemory"/> MB</div>
  						</div>
                     </div>
							</div>
			
			
			<div class="row">
                  <div class="input-field col m6">
                  		<div class="row">
                  			<div class="input-field col m5"><b><s:text name="text.ram.details" /> : </b></div>
                  		</div>
                  </div>
             </div>
             <div class="clear height5px"></div>				
							
			<div class="row">
                  <div class="input-field col m6">
                  		<div class="row">
                  			<div class="input-field col m5"><s:text name="text.ramsize" /> </div>
                   			<div class="input-field col m7">:  <s:property value="ramsize"/> MB</div>
                      	</div>
                   </div>
                   <div class="input-field col m6">
               			<div class="row">
                       		<div class="input-field col m5"><s:text name="text.virtual.ramsize" /> </div>
                   			<div class="input-field col m7">:  <s:property value="virtualramsize"/> MB</div>
  						</div>
                   </div>
			</div>
			
			
			<div class="row">
                  <div class="input-field col m6">
                  		<div class="row">
                  			<div class="input-field col m5"><s:text name="text.free.ramsize" /> </div>
                   			<div class="input-field col m7">:  <s:property value="freeramsize"/> MB</div>
                      	</div>
                   </div>
                   <div class="input-field col m6">
               			<div class="row">
                       		<div class="input-field col m5"><s:text name="text.used.ramsize" /> </div>
                   			<div class="input-field col m7">:  <s:property value="usedramsize"/> MB</div>
  						</div>
                   </div>
			</div>