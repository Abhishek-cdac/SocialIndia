<style type="text/css">
#myImg {border-radius: 5px;cursor: pointer;transition: 1.0;}
.imghover:hover {opacity: 0.7;cursor: pointer;}
#myImg:hover {opacity: 0.7;}
.modal_cbx {
	display: none; /* Hidden by default */
	position: fixed; /* Stay in place */
	z-index: 100000; /* Sit on top */
	padding-top: 100px; /* Location of the box */
	left: 0;
	top: 0;
	width: 100%; /* Full width */
	height: 100%; /* Full height */
	overflow: none; /* Enable scroll if needed */
	background-color: rgb(0, 0, 0); /* Fallback color */
	background-color: rgba(0, 0, 0, 0.9); /* Black w/ opacity */
	alignment: cenetr;
}
/* Caption of Modal Image */
#caption {margin: auto;display: block;width: 40%;max-width: 400px;text-align: center;color: #ccc;padding: 10px 0;height: 120px;}
/* Add Animation */
.modal-content, #caption {-webkit-animation-name: zoom;-webkit-animation-duration: 0.6s;animation-name: zoom;animation-duration: 0.6s;}
.clorboxtable{height: auto;max-height: 480px;max-width: 50%;min-height: 30%;min-width: 30%;text-align: center;width: auto;}
.clorboxtablevideo{height: auto;max-height: 480px;max-width: 60%;min-height: 45%;min-width: 60%;text-align: center;width: auto;}
/* The Close Button */
.close_imgclbx {position: absolute;top: 15px;right: 35px;color: #FFFFFF;font-size: 25px;font-weight: bold;transition: 0.3s;}
.close_imgclbx:hover, .close_imgclbx:focus {color: red;text-decoration: none;cursor: pointer;}
@ -webkit-keyframes zoom {from {-webkit-transform: scale(0) }to { -webkit-transform: scale(1) }}
@keyframes zoom {from {transform: scale(0)}to {transform: scale(1)}}
/* 100% Image Width on Smaller Screens */
@media only screen and (max-width: 500px) {.modal-content {width: 100%;}}
</style>
<div id="myModal" class="modal_cbx">
	<span class="close_imgclbx">X</span>
	<div id="bloackdiv" style="">
		<table id="tbliidd_img" class="clorboxtable" align="center">
			<tr>
				<td style="background-color: #fff; width: 15px; height: 20px; padding: 15px; align: center;" id="clbxtdid">
					<div class="card"><div class="card-image"><img class="model-content" id="img01" style=" min-height: 350px; max-height: 400px; height: auto; min-width: 380px; max-width: 450px; width: auto;">
					</div></div>
				</td>
			</tr>
		</table>
	</div>
	<div id="caption"></div>
</div>
<div id="myModalvideo" class="modal_cbx">
	<span class="close_imgclbx">X</span>
	<div id="bloackdiv" style="">
		<table id="tbliidd_vido" class="clorboxtablevideo" align="center">
			<tr>
				<td style="background-color: #fff; width: 15px; height: 400px; padding: 15px; align: center;" id="clbxtdid">
					<div class="card"><div class="card-image" style="min-height: 350px; max-height: 400px; height: auto; min-width: 100%; max-width:100%; width: auto;"	>
						<embed autostart="false" allowfullscreen="true" class="model-content" id="vido01"  style="min-height: 350px; max-height: 400px; height: auto; min-width: 100%; max-width: 100%; width: auto;">					
						<!-- <video controls style="min-height: 350px; max-height: 400px; height: auto; min-width: 100%; max-width: 100%; width: auto;">
						<source id="vido01"></source>
						</video> -->
					
					</div></div>
				</td>
			</tr>
		</table>
	</div>
	<div id="caption"></div>
</div>
<script type="text/javascript">
$(document).ready(function() {
$("#myModal").click(function() {$("#myModal").css("display", "none");});
$('#tbliidd_img').click(function(event) {event.stopPropagation();});
$("#myModalvideo").click(function() {$("#myModalvideo").css("display", "none");});
$('#tbliidd_vido').click(function(event) {event.stopPropagation();});
});
	var modal = document.getElementById('myModal');
	var img = document.getElementById('myImg');
	var modalImg = document.getElementById("img01");
	var captionText = document.getElementById("caption");
	if(img!='null' && img!='' && (typeof img) !=undefined && img!=null){
		img.onclick = function() {
			modal.style.display = "block";
			modalImg.src = this.src;
			modalImg.alt = this.alt;
			captionText.innerHTML = this.alt;
		}
	}
	// Get the <span> element that closes the modal
	var span = document.getElementsByClassName("close_imgclbx")[0];
	// When the user clicks on <span> (x), close the modal
	span.onclick = function() {modal.style.display = "none";}	
	//Image Zoom method
	function toViewlargesizeimg(thisobj){
		$("#myModal").css("display","none");
		$("#img01").attr("src","");
		$("#img01").attr("alt","");
		$("#img01").attr("src",thisobj.src);
		$("#img01").attr("alt",thisobj.alt);
		$("#caption").text(thisobj.alt);
		$("#myModal").css("display","block");
	}
	
	function toViewlargesizeimgwithsrc(thisobj,srcpath){// /templogo/images/labor//web/12/11.jpg	
		$("#myModal").css("display","none");
		$("#img01").attr("src","");
		$("#img01").attr("alt","");	
		$("#img01").attr("src",srcpath);
		$("#img01").attr("alt",thisobj.alt);
		$("#caption").text(thisobj.alt);
		$("#myModal").css("display","block");
	}	

	function toViewlargesizeVideowithsrc(thisobj,srcpath){// /templogo/images/labor//web/12/11.jpg	
		//srcpath = "https://www.youtube.com/embed/XGSy3_Czz8k?autoplay=1";
		$("#myModal").css("display","none");
		$("#myModalvideo").css("display","none");
		$("#vido01").attr("src","");
		$("#vido01").attr("alt","");	
		$("#vido01").attr("src",srcpath);
		$("#vido01").attr("alt",thisobj.alt);
		$("#caption").text(thisobj.alt);
		$("#myModalvideo").css("display","block");
	}	

	function toviewmap(mpurl){		
		$("#myModal").css("display","none");		
		var lvrAtag ="<div style='min-height: 350px; max-height: 400px; height: auto; min-width: 380px; max-width: 450px; width: auto;'><iframe id='right_side' style='min-height: 350px; max-height: 400px; height: auto; min-width: 380px; max-width: 450px; width: auto;' name='right_side' src='"+mpurl+"' width='100%'  height='100%' frameBorder='0' allowfullscreen></iframe></div>";
			$("#clbxtdid").html(lvrAtag);
			/*<a href='"+mpurl+"' target='right_side' id='mapidcbx'></a>
			$("#mapidcbx").show();
			$("#mapidcbx").click(function(){});
			$("#mapidcbx").hide();*/
			$("#myModal").css("display","block");
			 $('#dataProcessingIFrame').ready
			    (function(){
			    	toShowLoadingImgoverlay();
			    		$("#uploadimg_clorbx").css("z-index","200000");
				    });
					    
			$('#right_side').load(function() { 
				tohideLoadingImgoverlay();
			});
		}
	function Imgzoom(a) {
		var img = document.getElementById(a);
		var modalImg = document.getElementById("img01");
		var captionText = document.getElementById("caption");
		img.onclick = function() {
			modal.style.display = "block";
			modalImg.src = this.src;
			modalImg.alt = this.alt;
			captionText.innerHTML = this.alt;
		}
		// Get the <span> element that closes the modal
		var span = document.getElementsByClassName("close_imgclbx")[0];
		// When the user clicks on <span> (x), close the modal
		span.onclick = function() {modal.style.display = "none";}
	}	
</script>