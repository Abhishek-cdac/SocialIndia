<%@ page language="java" import="com.opensymphony.xwork2.ActionSupport"
	import="com.opensymphony.xwork2.ActionContext"
	import="com.letspay.utils.LogOut" errorPage=""%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE>
<script
	src="https://maps.googleapis.com/maps/api/js?libraries=places&key=AIzaSyBZ1vC5XKHRlia0q_Fs77zQmiSg6ZYfj_o"
	type="text/javascript"></script>
<script
	src="<s:text name='Resource.path'/>/js/googlemap/locationpicker.jquery.js"></script>
<style>
.pac-container {
	z-index: 10000;
}

.dialogoverlay_gmap {
	width: 100%;
	left: 0;
	height: 100%;
	top: 0;
	opacity: .01;
	background-color: #000;
	position: fixed;
	display: none;
	z-index: 9999;
}

.mapmodal-dialog {
	width: 600px;
	margin: 50px auto;
}

.mapmodal-content {
	position: relative;
	background-color: #fff;
	-webkit-background-clip: padding-box;
	background-clip: padding-box;
	border: 1px solid #999;
	border: 1px solid rgba(0, 0, 0, .2);
	border-radius: 6px;
	outline: 0;
	-webkit-box-shadow: 0 3px 9px rgba(0, 0, 0, .5);
	box-shadow: 0 3px 9px rgba(0, 0, 0, .5);
}

.close_map {
	color: #fff;
	float: right;
	font-size: 21px;
	font-weight: 700;
	line-height: 1;
	opacity: 1.2;
	text-shadow: 0 1px 0 #fff;
}

button.close_map {
	background: rgba(0, 0, 0, 0) none repeat scroll 0 0;
	border: 0 none;
	cursor: pointer;
	padding: 0;
}

#mapdiv .mapmodal {
	bottom: 0;
	display: none;
	left: 0;
	outline: 0 none;
	overflow: hidden;
	position: fixed;
	right: 0;
	top: 0;
	z-index: 10000;
	background: none;
}

.mapmodal-body {
	padding: 15px;
	position: relative;
	height: 450px;
	overflow: scroll;
}

.fade.in {
	opacity: 1;
}

#mapdiv .mapmodal-header {
	background-color: #009688;
	border-bottom: 1px solid #009688;
	color: #fff;
	min-height: 16.43px;
	padding: 15px;
}
</style>
<div id="dialogoverlaygmapid" class="dialogoverlay_gmap"
	style="z-index: 9999; opacity: 0.5;"></div>
<input type="hidden" id="mapvlaue" est="width: 410px"
	class="form-control">
<div id="mapdiv">
	<div class="mapmodal fade in" id="us6-dialog" style="display: none;"
		aria-hidden="false">
		<div class="mapmodal-dialog">
			<div class="mapmodal-content">
				<div class="mapmodal-header">
					<button aria-label="Close" data-dismiss="mapmodal"
						class="close_map" type="button" id="gmapclose">
						<span aria-hidden="true">×</span>
					</button>
					<div class="mapmodal-title">Find Location</div>
				</div>
				<div class="mapmodal-body">
					<div style="width: 550px" class="form-horizontal">
						<div class="form-group">
							<label class="col-sm-2 control-label">Location:</label>

							<div class="col-sm-10">
								<input type="text" id="us6-address" class="form-control"
									placeholder="Enter a location" autocomplete="off">
							</div>
						</div>
						<div class="form-group" style="display: none">
							<label class="col-sm-2 control-label">Radius:</label>
							<div class="col-sm-5">
								<input type="text" id="us6-radius" class="form-control">
							</div>
						</div>
						<div
							style="width: 100%; height: 400px; position: relative; background-color: rgb(229, 227, 223); overflow: hidden;"
							id="us6"></div>

						<div class="clearfix">&nbsp;</div>
						<div class="m-t-small" style="display: none">
							<label class="p-r-small col-sm-1 control-label">Lat.:</label>

							<div class="col-sm-3">
								<input type="text" id="us6-lat" style="width: 110px"
									class="form-control">
							</div>
							<label class="p-r-small col-sm-2 control-label">Long.:</label>

							<div class="col-sm-3">
								<input type="text" id="us6-lon" style="width: 110px"
									class="form-control">
							</div>
						</div>
						<div class="clearfix"></div>
					</div>
				</div>
				<div class="mapmodal-footer">
					<button data-dismiss="mapmodal"
						style="float: right; margin-right: 10px;" class="btn btn-default"
						type="button" id="gmapcloseBtn">Close</button>
					<button class="btn btn-primary" type="button"
						style="display: none;">Save changes</button>
					<div style="clear: both; height: 10px;"></div>
				</div>
			</div>
			<!-- /.mapmodal-content -->
		</div>
		<!-- /.mapmodal-dialog -->
	</div>

</div>
<script>
	var gmapSelId = "0";
	var gmapSelIdlatlangid = "0";
	function toLoadMap() {
		$('#us6')
				.locationpicker(
						{
							location : {
								latitude : 19.0759837,
								longitude : 72.87765590000004
							},
							locationName : "",
							radius : 300,
							zoom : 15,
							scrollwheel : true,
							inputBinding : {
								// latitudeInput: $('#us6-lat'),// longitudeInput: $('#us6-lon'),// radiusInput: $('#us6-radius'),
								locationNameInput : $('#us6-address')
							},
							enableAutocomplete : true,
							onchanged : function(currentLocation, radius,
									isMarkerDropped) {//alert("Location changed. New location (" + currentLocation.latitude + ", " + currentLocation.longitude + ")");
								var addressComponents = $(this).locationpicker(
										'map').location.addressComponents;
								if (gmapSelIdlatlangid != "0") {
									$('#' + gmapSelIdlatlangid)
											.val(
													currentLocation.latitude
															+ ", "
															+ currentLocation.longitude);
								}
								updateControls(addressComponents);
							},
							oninitialized : function(component) {
								$('#us6').locationpicker('autosize');
								var addressComponents = $(component)
										.locationpicker('map').location.addressComponents;
								if (gmapSelIdlatlangid != "0") {
									$('#' + gmapSelIdlatlangid).val(
											"19.0759837, 72.87765590000004");
								}
								updateControls(addressComponents);
							}
						});
		$('#us6-dialog').on('shown.bs.mapmodal', function() {// $('#us6').locationpicker('autosize');
		});
		tohideLoadingImgoverlay();
	}
	$(document).ready(function() {
		$("#gmapclose").click(function() {
			$("#dialogoverlaygmapid").hide();
			$("#us6-dialog").hide();
		});
		$("#gmapcloseBtn").click(function() {
			$("#dialogoverlaygmapid").hide();
			$("#us6-dialog").hide();
		});
	});
	function updateControls(addressComponents) {
		if (gmapSelId != "0") {
			$('#' + gmapSelId).val(
					addressComponents.addressLine1 + ","
							+ addressComponents.city + ","
							+ addressComponents.stateOrProvince + ","
							+ addressComponents.country);
			$('#' + gmapSelId).focus();
			//$('#'+gmapSelId).val($('#us6-address').val());
		} else {

		}

		//$('#tsdsd').val(addressComponents.addressLine1+", "+addressComponents.city+", "+addressComponents.stateOrProvince+", "+addressComponents.country);
		// $('#us5-street1').val(addressComponents.addressLine1);// $('#us5-city').val(addressComponents.city);// $('#us5-state').val(addressComponents.stateOrProvince);//$('#us5-zip').val(addressComponents.postalCode); //$('#us5-country').val(addressComponents.country);	   
		//$('#countryId').val(addressComponents.country);// $('#stateId').val(addressComponents.stateOrProvince);// $('#cityId').val(addressComponents.city);//  $('#postalId').val(addressComponents.postalCode);// $('#addressId').val(addressComponents.addressLine1);	    
	}
	function toShowGmap(txtbxid) {
		toShowLoadingImgoverlay();
		toLoadMap();
		gmapSelId = txtbxid;
		$("#dialogoverlaygmapid").show();
		$("#us6-dialog").show();
	}
	function toShowGmapwithlatlong(txtbxid, latlongtbix) {
		toShowLoadingImgoverlay();
		toLoadMap();
		gmapSelId = txtbxid;
		gmapSelIdlatlangid = latlongtbix;
		$("#dialogoverlaygmapid").show();
		$("#us6-dialog").show();
	}
</script>
