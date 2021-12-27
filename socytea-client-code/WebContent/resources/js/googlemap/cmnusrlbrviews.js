/**
 * 
 */

function usrfeedbackview(id){	
	var res = id.split(",");
	if(res[1]=='Labor'){
		$("#deletelaborid").val(res[0]);	
		$("#deletelaborserv_id").val(res[2]);	
		/*toShowLoadingImgoverlay();
		$("#delgroupform").attr("action", "viewlbrfeedbck");
		$("#delgroupform").submit();*/
	}else{
		
	}
	
}
function usrview(id){
	$("#deleteresidentid").val(id);		
	/*toShowLoadingImgoverlay();
	$("#delgroupform").attr("action", "viewresidentdetails");
	$("#delgroupform").submit();*/	
}