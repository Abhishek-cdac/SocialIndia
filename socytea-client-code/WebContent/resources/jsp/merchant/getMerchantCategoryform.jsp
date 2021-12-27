<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="merchantCategory==1">
	<!--  <div id="" style="float: left">
		<div class="imgaddplus7" onclick="showSportsDiv('add');" style="">
			 <i class="mdi-content-add-circle tinysmall" style="color: #ff4081; float: left; cursor: pointer;"></i>
			<div class="spacialspace">Sports Detail</div>
		</div>
		<div class="imgaddminus7" onclick="showSportsDiv('sub');"
			style="cursor: pointer; display: none;">
			<i class="mdi-content-remove-circle tinysmall" style="color: #ff4081; float: left; cursor: pointer;"></i>
			<div class="spacialspace">Sports Detail</div>
		</div>
	</div>-->
	<div class="col s12" style="float: left; display: block;" id="addcloseimg1">
		<div style="" id="usernamedivid" class="form-group">
			<div class="left marginleft5px margintop10px"> Add More : </div>
			<div class="left marginleft5px">
			<button type="button" onclick="addMoreSportsDetail();" class="<s:text name="button.color.addfield"/>" id="addButton2" name="submitbtn" value=""><i class="mdi-content-add"></i></button>
			</div>
			<div class="left marginleft5px">
			<button type="button" onclick="removelastSportsDetail();" class="<s:text name="button.color.removefield"/>" id="removeButton2"
												name="submitbtn" value=""><i class="mdi-content-remove"></i></button>
			</div>
		</div>
	</div>
	<div style="clear: both; height: 5px;"></div>
	<div id="sportshidden7" style="display: block; margin: 15px;">
		<div class="row">
			<div class="input-field col s6">
				<div class="form-group" id="divemailId">
					<label for="city" class="control-label"><s:text
							name="Text.merchant.sports" /></label>
					<s:textfield  id="city"
						cssClass="form-control typeahead tt-query typeNameArray"
						autocomplete="off"
						spellcheck="false" />
				</div>
			</div>

			<div class="input-field col s6">
				<div class="form-group" id="divemailId">
					<label for="pin" class="control-label"><s:text
							name="Text.merchant.quantity" /></label>
					<s:textfield name="mrchStockobj.quantity" id="pin"
						cssClass="form-control typeahead tt-query quantityArray"
						autocomplete="off" spellcheck="false" />
				</div>
			</div>
		</div>
		<div id='TextBoxesGroup2'> <div id="TextBoxDiv2"></div>	</div>
	</div>
	
	<s:hidden name="typeNameArray" id="typeNameArrayid"></s:hidden>
	<s:hidden name="quantityArray" id="quantityArrayid"></s:hidden>	
</s:if>
<s:elseif test="merchantCategory==2">	
	<!-- <div id=""> <div class="imgaddplus8" onclick="showPharmacyDiv('add');" style="">
		 <i class="mdi-content-add-circle tinysmall " style="color: #ff4081; float: left; cursor: pointer;"></i>
			<div class="spacialspace" >Pharmacy Detail</div>
		</div>
		<div class="imgaddminus8" onclick="showPharmacyDiv('sub');"
			style="cursor: pointer; display: none;">
			<i class="mdi-content-remove-circle tinysmall" style="color: #ff4081; float: left; cursor: pointer;"></i>
			<div class="spacialspace">Pharmacy Detail</div>
		</div> </div>-->
	
	<div class="col s12" style="float: left; display: block;" id="addcloseimg2">
		<div style="" id="usernamedivid" class="form-group">
		<div class="left marginleft5px margintop10px"> Add More : </div>
		<div class="left marginleft5px">
			<button type="button" onclick="addMorePharmacyDetail();" class="<s:text name="button.color.addfield"/>" id="addButton1" name="submitbtn" value=""><i class="mdi-content-add"></i></button>
			</div>
			<div class="left marginleft5px">
			<button type="button" onclick="removelastPharmacyDetail();" class="<s:text name="button.color.removefield"/>" id="removeButton1" name="submitbtn" value=""><i class="mdi-content-remove"></i></button>
			</div>
		</div>
	</div>

	<div style="clear: both; height: 5px;"></div>
	<div id="pharmacyhidden8" style="display: block; margin: 15px;">
	<div  class="borderradius">
		<div class="row">

			<div class="input-field col s6">
				<div class="form-group" id="divemailId">
					<label for="typeName" class="control-label">Medicine</label>
					<s:textfield name="mrchStockobj.typeName" id="typeName"
						cssClass="form-control typeahead tt-query typeNameArray"
						autocomplete="off"
						spellcheck="false" />
				</div>
			</div>
			<div class="input-field col s6">
				<div class="form-group" id="divemailId">
					<label for="power" class="control-label">Power</label>
					<s:textfield name="mrchStockobj.power" id="power"
						cssClass="form-control typeahead tt-query powerArray"
						autocomplete="off"
						spellcheck="false" />
				</div>
			</div>
		</div>
		<div class="row">

			<div class="input-field col s6">
				<div class="form-group" id="divemailId">
					<label for="company" class="control-label">Company</label>
					<s:textfield name="mrchStockobj.companyName" id="company"
						cssClass="form-control typeahead tt-query companyNameArray"
						autocomplete="off"
						spellcheck="false" />
				</div>
			</div>

			<div class="input-field col s6">
				<div class="form-group" id="divemailId">
					<label for="quantity" class="control-label">Quantity</label>
					<s:textfield name="mrchStockobj.quantity" id="quantity"
						cssClass="form-control typeahead tt-query quantityArray"
						autocomplete="off" spellcheck="false" />
				</div>
			</div>
		</div>
		</div>
		<!-- 		<div style=" border: 1px solid rgb(210, 210, 210); height: auto; "></div>  -->
		<div class="clear" style="height: 10px;"></div>
		<div id='TextBoxesGroup'>
			<div id="TextBoxDiv"></div>
		</div>
	</div>
	<s:hidden name="typeNameArray" id="typeNameArrayid"></s:hidden>
	<s:hidden name="powerArray" id="powerArrayid"></s:hidden>
	<s:hidden name="companyNameArray" id="companyNameArrayid"></s:hidden>
	<s:hidden name="quantityArray" id="quantityArrayid"></s:hidden>
</s:elseif>
<s:elseif test="merchantCategory==3">

	<!-- <div id=""> <div class="imgaddplus9" onclick="showjwelleryDiv('add');" style="">
			 <i class="mdi-content-add-circle tinysmall "
									style="color: #ff4081; float: left; cursor: pointer;"></i>
			<div class="spacialspace" style="font-weight: bold;">Jewellery
				Detail</div>
		</div>
		<div class="imgaddminus9" onclick="showjwelleryDiv('sub');"
			style="cursor: pointer; display: none;">
			<i class="mdi-content-remove-circle tinysmall"
									style="color: #ff4081; float: left; cursor: pointer;"></i>
			<div class="spacialspace" style="font-weight: bold;">Jewellery
				Detail</div>
		</div> </div> -->
	<div class="col s12" style="float: left; display: block;" id="addcloseimg3">
		<div style="" id="usernamedivid" class="form-group">
		<div class="left marginleft5px margintop10px"> Add More : </div>
			<div class="left marginleft5px">
			<button type="button" onclick="addMoreJewellaryDetail();" class="<s:text name="button.color.addfield"/>" id="addButton"
												name="submitbtn" value=""><i class="mdi-content-add"></i></button>
			</div>
			<div class="left marginleft5px">
			<button type="button" onclick="removelastjwellaryDetail();" class="<s:text name="button.color.removefield"/>" id="removeButton"
			name="submitbtn" value=""><i class="mdi-content-remove"></i></button>
			</div>
		</div>
	</div>
	<div style="clear: both; height: 5px;"></div>
	<div id="jewelleryhidden9" style="display: block; margin: 15px;">
		<div class="row">
			<div class="input-field col s6">
				<div class="form-group" id="divemailId">
					<label for="jwelltype" class="control-label"><s:text
							name="Text.merchant.jewellery" /></label>
					<s:textfield name="mrchStockobj.typeName" id="jwelltype"
						cssClass="form-control typeahead tt-query typeNameArray" value=""
						autocomplete="off"
						spellcheck="false" />
				</div>
			</div>

			<div class="input-field col s6">
				<div class="form-group" id="divemailId">
					<label for="jwellquantity" class="control-label"><s:text
							name="Text.merchant.quantity" /></label>
					<s:textfield name="mrchStockobj.quantity" id="jwellquantity"
						cssClass="form-control typeahead tt-query quantityArray" value=""
						autocomplete="off" spellcheck="false" />
				</div>
			</div>
		</div>
		<div id='TextBoxesGroup1'>
			<div id="TextBoxDiv1"></div>
		</div>
	</div>
<s:hidden name="typeNameArray" id="typeNameArrayid"></s:hidden>
	<s:hidden name="quantityArray" id="quantityArrayid"></s:hidden>
	
</s:elseif>
<s:elseif test="merchantCategory==4">
	<!-- <div id="">
		<div class="imgaddplus9" onclick="showRestaurantDiv('add');" style="">
			 <i class="mdi-content-add-circle tinysmall "
									style="color: #ff4081; float: left; cursor: pointer;"></i>
			<div class="spacialspace" style="font-weight: bold;">Restaurant
				Detail</div>
		</div>
		<div class="imgaddminus9" onclick="showRestaurantDiv('sub');"
			style="cursor: pointer; display: none;">
			<i class="mdi-content-remove-circle tinysmall"
									style="color: #ff4081; float: left; cursor: pointer;"></i>
			<div class="spacialspace" style="font-weight: bold;">Restaurant
				Detail</div>
		</div>
	</div> -->
	<div style="clear: both; height: 5px;"></div>
	<div id="restauranthidden9" style="display: block; margin: 15px;">
		<div class="row">
			<div class="input-field col s6">
				<div class="form-group" id="divemailId">
					<label for="Cuisines" class="control-label">Cuisines</label>
					<s:textfield name="mrchStockobj.cuisines" id="Cuisines"
						cssClass="form-control typeahead tt-query" 
						autocomplete="off" spellcheck="false" />
				</div>
			</div>

			<div class="input-field col s6">
				<div class="form-group">
					<label for="tblid" class="active">Features</label>
					
					<table id="tblid" >
						<tr style="height: 50px;">
							<td style="width: 30%;"><div>
									<input id="brakfast" class="restaurentCheckbox" type="checkbox" name="restaruntFeatures[]"
										value="breakFast"> <label for="brakfast">Break Fast</label>
								</div></td>
							<td style="width: 40%;"><div >
									<input id="lunch" class="restaurentCheckbox" type="checkbox" name="restaruntFeatures[]"
										value="lunch"> <label for="lunch">Lunch</label>
								</div></td>
							<td  style="width: 30%;"><div>
									<input id="dinner" class="restaurentCheckbox" type="checkbox" name="restaruntFeatures[]"
										value="dinner"> <label for="dinner">Dinner</label>
								</div></td>
							
						</tr>
						<tr>
						<td><div >
									<input id="indore" class="restaurentCheckbox" type="checkbox" name="restaruntFeatures[]"
										value="indoor"> <label for="indore">Indoor</label>
								</div></td>
							<td style="height: 50px;"><input id="delivery" class="myCheckbox"
								type="checkbox" name="restaruntFeatures[]" value="delivery"> <label
								for="delivery">Delivery</label></td>
							<td><input id="takeaway" class="restaurentCheckbox" type="checkbox" name="restaruntFeatures[]"
								value="takeAway"> <label for="takeaway">Take-Away</label></td>
							
						</tr>
						<tr>
						<td><input id="cafe" class="restaurentCheckbox" type="checkbox" name="restaruntFeatures[]"
								value="cafe"> <label for="cafe">Cafe</label></td>
							<td><input id="luxuryDining" class="restaurentCheckbox" type="checkbox" name="restaruntFeatures[]"
								value="luxuryDining"> <label for="luxuryDining">Luxury Dining</label></td>
							<td style="height: 50px;"><input id="nightlife" class="restaurentCheckbox"
								type="checkbox" name="restaruntFeatures[]" value="nightlife"> <label
								for="nightlife">Nightlife</label></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
		<div id='TextBoxesGroup1'>
			<div id="TextBoxDiv1"></div>
		</div>
	</div>
<s:hidden name="restaruntFeatureDetail" id="restaruntFeatureDetail"></s:hidden>

</s:elseif>
<s:else>
</s:else>

<%-- <s:hidden name="merchantobj.merchantCategoryId" value="%{merchantCategory}"></s:hidden> --%>