<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<style type="text/css">
.dialogoverlay{ width:100%;left:0;height:100%;top:0;opacity:.40;background-color:#000;position:fixed;display:none;z-index:100000; }
.loadingImage{margin-left:140px;display:none;}
.uploadimg_clorbx{ position: fixed; left: 50%; top: 50%; width: 100%; height: 100%; z-index: 9999; display:none;}
.large{
height:130px;
width:130px;
}
</style>
<script type="text/javascript">
function toShowLoadingImgoverlay(){ $("#uploadimg_clorbx").show();	 $("#dialogoverlay_img").show(); return;}
function tohideLoadingImgoverlay(){ $("#uploadimg_clorbx").hide();	 $("#dialogoverlay_img").hide(); return;}
</script>
<div id="dialogoverlay_img" class="dialogoverlay" style="z-index: 100000; opacity: 0.2;"></div>
<div id="uploadimg_clorbx" class="uploadimg_clorbx">
  
					<div class="preloader-wrapper large active">
                      <div class="spinner-layer spinner-blue">
                        <div class="circle-clipper left">
                          <div class="circle"></div>
                        </div>
                        <div class="gap-patch">
                          <div class="circle"></div>
                        </div>
                        <div class="circle-clipper right">
                          <div class="circle"></div>
                        </div>
                      </div>

                      <div class="spinner-layer spinner-red">
                        <div class="circle-clipper left">
                          <div class="circle"></div>
                        </div>
                        <div class="gap-patch">
                          <div class="circle"></div>
                        </div>
                        <div class="circle-clipper right">
                          <div class="circle"></div>
                        </div>
                      </div>

                      <div class="spinner-layer spinner-yellow">
                        <div class="circle-clipper left">
                          <div class="circle"></div>
                        </div>
                        <div class="gap-patch">
                          <div class="circle"></div>
                        </div>
                        <div class="circle-clipper right">
                          <div class="circle"></div>
                        </div>
                      </div>

                      <div class="spinner-layer spinner-green">
                        <div class="circle-clipper left">
                          <div class="circle"></div>
                        </div>
                        <div class="gap-patch">
                          <div class="circle"></div>
                        </div>
                        <div class="circle-clipper right">
                          <div class="circle"></div>
                        </div>
                      </div>
                    </div>                               
 		 <p style="color:green; font-size:16px;font-weight:600;">Please Wait...</p>
 
</div>


<!-- Old Loading -->
<!-- 
<style type="text/css">
.dialogoverlay{ width:100%;left:0;height:100%;top:0;opacity:.01;background-color:#FFF;position:fixed;display:none;z-index:100000; }
.uploadimg_clorbx1{ position: fixed; left: 0px; top: 0px; width: 100%; height: 100%; z-index: 9999; display:none; background: url('resources/images/load.gif') 50% 50% no-repeat; }
</style>
<script type="text/javascript">
function toShowLoadingImgoverlay(){ $("#uploadimg_clorbx").show();	 $("#dialogoverlay_img").show(); return;}
function tohideLoadingImgoverlay(){ $("#uploadimg_clorbx").hide();	 $("#dialogoverlay_img").hide(); return;}
</script>
<div id="dialogoverlay_img" class="dialogoverlay" style="z-index: 100000; opacity: 0.2;"></div>
<div id="uploadimg_clorbx" class="uploadimg_clorbx"></div> -->