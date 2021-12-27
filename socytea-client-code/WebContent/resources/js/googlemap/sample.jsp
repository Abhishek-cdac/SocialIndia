<%@ page language="java" import="java.util.Locale" import="java.util.Date" import="java.util.TimeZone" 
import="java.text.SimpleDateFormat" 
import="java.util.Map" import="com.opensymphony.xwork2.ActionSupport" 
import="com.opensymphony.xwork2.ActionContext" 
import="com.letspay.utils.LogOut" errorPage="" %>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<!-- Favicon and touch icons -->
<link rel="shortcut icon" href="assets/ico/favicon.png">
<link rel="apple-touch-icon-precomposed" sizes="144x144" href="assets/ico/apple-touch-icon-144-precomposed.png"/>
<link rel="apple-touch-icon-precomposed" sizes="114x114" href="assets/ico/apple-touch-icon-114-precomposed.png"/>
<link rel="apple-touch-icon-precomposed" sizes="72x72" href="assets/ico/apple-touch-icon-72-precomposed.png"/>
<link rel="apple-touch-icon-precomposed" href="assets/ico/apple-touch-icon-57-precomposed.png"/>
<link rel="shortcut icon" href="<s:text name='Resource.path'/>/images/social/48.ico">
<style type="text/css">
.logosize{
width: 10%;
}.logoword{
width: 30%;
}
</style>
<link rel="stylesheet" type="text/css" href="<s:text name='Resource.path'/>/css/bootstrap/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="<s:text name='Resource.path'/>/ccc/dist/css/bootstrapValidator.css"/>
<link rel="stylesheet" type="text/css" href="<s:text name='Resource.path'/>/css/jquery.alerts.css" />
<link rel="stylesheet" type="text/css" href="<s:text name='Resource.path'/>/css/bootstrap/styles-core.css"/>
<link rel="stylesheet" type="text/css" href="<s:text name='Resource.path'/>/css/bootstrap/style.css"/>


<%--
<link rel="stylesheet" type="text/css" href="<s:text name='Resource.path'/>/css/bootstrap/bootstrap.min.css"/>
--%>
	<script type="text/javascript" src="<s:text name='Resource.path'/>/js/jquery-1.8.2.min.js"></script>	
	<script type="text/javascript" src="<s:text name='Resource.path'/>/js/bootstrap/bootstrap.min.js"></script>
	<script type="text/javascript" src="<s:text name='Resource.path'/>/js/bootstrap.min-1.js"></script>
	<script type="text/javascript" src="<s:text name='Resource.path'/>/js/formValidation.min.js"></script>
    <script  src="http://maps.google.com/maps/api/js?sensor=false&libraries=places&key=AIzaSyBlea6shX4OsMTSd4kA6k-9RhbQ4hUwbEU" type="text/javascript"></script>
	<script src="<s:text name='Resource.path'/>/js/googlemap/locationpicker.jquery.js"></script>
</head>
<body>
<button data-toggle="modal" data-target="#us6-dialog">Click here to open dialog</button>
<div class="row">
<div class="form-group">
<label class="col-sm-2 control-label">Full Address :</label>
<div class="col-sm-4"><textarea row="6" cols="20" id="tsdsd" class="form-control"> </textarea>
<!--  <div class="col-sm-3"><input type="text" id="tsdsd" est="width: 410px" class="form-control"></div><input type="text" id="addressId" class="form-control" placeholder="Country Name" autocomplete="off"></div>-->
</div></div></div>
<div class="row">
<div class="form-group">
<label class="col-sm-2 control-label">Address :</label>
<div class="col-sm-4"><input type="text" id="addressId" class="form-control" placeholder="Country Name" autocomplete="off"></div>
</div>
</div>
<div class="row">
<div class="form-group">
<label class="col-sm-2 control-label">Country :</label>
<div class="col-sm-4"><input type="text" id="countryId" class="form-control" placeholder="Country Name" autocomplete="off"></div>
</div></div>
<div class="row">
<div class="form-group">
<label class="col-sm-2 control-label">State :</label>
<div class="col-sm-4"><input type="text" id="stateId" class="form-control" placeholder="Country Name" autocomplete="off"></div>
</div></div>
<div class="row">
<div class="form-group">
<label class="col-sm-2 control-label">City :</label>
<div class="col-sm-4"><input type="text" id="cityId" class="form-control" placeholder="Country Name" autocomplete="off"></div>
</div></div>
<div class="row">
<div class="form-group">
<label class="col-sm-2 control-label">Postal :</label>
<div class="col-sm-4"><input type="text" id="postalId" class="form-control" placeholder="Country Name" autocomplete="off"></div>
</div>
</div>

<div class="modal fade in" id="us6-dialog" style="display: none;" aria-hidden="false">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button aria-label="Close" data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span></button>
                        <h4 class="modal-title">Modal title</h4>
                    </div>
                    <div class="modal-body">
                        <div style="width: 550px" class="form-horizontal">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">Location:</label>

                                <div class="col-sm-10"><input type="text" id="us6-address" class="form-control" placeholder="Enter a location" autocomplete="off"></div>
                            </div>
                            <div class="form-group" style="display : none">
                                <label class="col-sm-2 control-label">Radius:</label>

                                <div class="col-sm-5"><input type="text" id="us6-radius" class="form-control"></div>
                            </div>
                            <div style="width: 100%; height: 400px; position: relative; background-color: rgb(229, 227, 223); overflow: hidden;" id="us6">
                            </div>
                           
                            <div class="clearfix">&nbsp;</div>
                            <div class="m-t-small" style="display : none">
                                <label class="p-r-small col-sm-1 control-label">Lat.:</label>

                                <div class="col-sm-3"><input type="text" id="us6-lat" style="width: 110px" class="form-control"></div>
                                <label class="p-r-small col-sm-2 control-label">Long.:</label>

                                <div class="col-sm-3"><input type="text" id="us6-lon" style="width: 110px" class="form-control"></div>
                            </div>
                            <div class="clearfix"></div>                          
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button data-dismiss="modal" class="btn btn-default" type="button">Close</button>
                        <button class="btn btn-primary" type="button">Save changes</button>
                    </div>
                </div>
                <!-- /.modal-content -->
            </div>
            <!-- /.modal-dialog -->
        </div>
<script>
	$('#us6').locationpicker({
		location: {latitude: 19.0759837, longitude: 72.87765590000004},
		locationName: "",
	    radius: 500,
	    zoom: 15,
	    scrollwheel: true,
	    inputBinding: {
	       // latitudeInput: $('#us6-lat'),
	       // longitudeInput: $('#us6-lon'),
	       // radiusInput: $('#us6-radius'),
	        locationNameInput: $('#us6-address')
	    },
	    enableAutocomplete: true,
	    onchanged: function(currentLocation, radius, isMarkerDropped) {
	    	//alert("Location changed. New location (" + currentLocation.latitude + ", " + currentLocation.longitude + ")");
	    	var addressComponents = $(this).locationpicker('map').location.addressComponents;
	    	//$(this).locationpicker('map').map.setZoom(18);
	        updateControls(addressComponents);	       
	    },
	    oninitialized: function(component) {		    
	    	$('#us6').locationpicker('autosize');
	        var addressComponents = $(component).locationpicker('map').location.addressComponents;
	       // $(component).locationpicker('map').map.setZoom(18);
	        updateControls(addressComponents);
	       
	    }			
	});
	$('#us6-dialog').on('shown.bs.modal', function () {
	   // $('#us6').locationpicker('autosize');
	});

	function updateControls(addressComponents) {
	   // $('#us5-street1').val(addressComponents.addressLine1);
	   // $('#us5-city').val(addressComponents.city);
	   // $('#us5-state').val(addressComponents.stateOrProvince);
	    //$('#us5-zip').val(addressComponents.postalCode);
	    //$('#us5-country').val(addressComponents.country);
	    $('#tsdsd').val(addressComponents.addressLine1+","+addressComponents.city+","+addressComponents.stateOrProvince+","+addressComponents.country);
	    $('#countryId').val(addressComponents.country);
	    $('#stateId').val(addressComponents.stateOrProvince);
	    $('#cityId').val(addressComponents.city);
	    $('#postalId').val(addressComponents.postalCode);
	    $('#addressId').val(addressComponents.addressLine1);
	    
	}
</script>
</body>
<style type="text/css">
.pac-container{
	z-index :10000;
	}
</style>
</html>