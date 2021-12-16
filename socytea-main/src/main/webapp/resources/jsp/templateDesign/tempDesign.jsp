<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="<s:text name="meta.contentType"/>">
<meta name="viewport" content="<s:text name="meta.viewport"/>">
<meta http-equiv="X-UA-Compatible" content="<s:text name="meta.X-UA-Compatible"/>">
<meta name="msapplication-tap-highlight" content="<s:text name="meta.msapplication-tap-highlight"/>">
<meta name="description" content="<s:text name="meta.description"/>">
<meta name="keywords" content="<s:text name="meta.keywords"/>">
<jsp:include page="../common/icodisplyfile.jsp"></jsp:include>
<title><s:text name="Text.Title" /></title>
<jsp:include page="../common/basiccss.jsp"></jsp:include>
<link href="<s:text name='Resource.path'/>/js/plugins/chartist-js/chartist.min.css" type="text/css" rel="stylesheet" media="screen,projection">
<link href="<s:text name='Resource.path'/>/css/jquery-te-1.4.0.css" type="text/css" rel="stylesheet" media="screen,projection">
 <style>
            .ui-autocomplete {
                max-height: 200px;
                overflow-y: auto;
                overflow-x: hidden; /* prevent horizontal scrollbar */
            }
            /* IE 6 doesn't support max-height we use height instead, but this forces the menu to always be this tall */
            * html .ui-autocomplete {
                height: 200px;
            }
            .ui-combobox {
                position: relative;
                display: inline-block;
            }
            .newne{
                float: right;
                margin-right: -11px;
                margin-top: -372px;
                width: 17%;
            }
            .cmnbtn{
                height:30px;
                width: 100px;
            }
            .cmnbuttn{
                background-color: #EEEEEE;
                border: 1px solid #B1B1B1;
                border-radius: 3px;
                color: #45494C;
                cursor: pointer;
                font-size: 12px;
                font-weight: bold;
                margin-top:  5px;
            }
            .dataTables_wrapper {
                padding: 0 5px 10px;
            }
        </style>
</head>
<body>
 <div id="loader-wrapper">
      <div id="loader"></div>        
      <div class="loader-section section-left"></div>
      <div class="loader-section section-right"></div>
 </div>
  <jsp:include page="../common/header.jsp"></jsp:include>
 <div id="main">
    <div class="wrapper">
     <jsp:include page="../common/menuBar.jsp"></jsp:include>
      <section id="content">
        <div id="breadcrumbs-wrapper">
          <div class="container">
            <div class="row">
              <div class="col s12 m12 l12">
                <h5 class="breadcrumbs-title"><s:text name="Mail Template"/></h5>
                <ol class="breadcrumbs left">
                   <li><a href="posthomepage"><s:text name="Breadcrumb.dashboardhome" /></a></li>
					<li><a href="#"><s:text name="config.mgmt" /></a></li>
                    <li class="active"><s:text name="Mail Template" /></li>
                </ol>                              						
              </div>              
            </div>            
          </div>  
          <jsp:include page="../common/Alert.jsp" flush="true" />        
        </div>
      	<div class="container">
       	<div class="card-panel">  
                        <form method="post" name="form_template" id="form_template">
                            <div class="row">
							<div class="input-field col m6">
							<label for="stateId" class="active">Mail Template</label>
							<div id="selectid"></div>
                  			</div></div>
                  			 <div class="row">
							<div class="input-field col m6">
							<label id="subject11" for="subject">Subject</label>
							<s:textfield name="emailsub" id="subject" cssClass="form-control"/>						
                  			</div></div>
                  				 <div class="row">
                  				 <div class="input-field col m8">
                                <label class="active">Body of Content</label>
                                <textarea id="wysiwyg" name="emailcontent" rows="10" class="form-control" cols="80" ></textarea>
                            </div>
                             <div class="input-field col m3">
                            <div id='TextBoxesGroup'>
							<div id="TextBoxDiv"></div>	</div>
							</div>	
                            </div>
                        <%--      <button type="button" id="userCancelButtonId" class="<s:text name="button.color.preview"/>" onclick="cancelFunction();">
						<s:text name="Text.button.Preview" /><i class="<s:text name="button.icon.preview"/>"></i></button> --%>
						<button type="button" id="userCreateButtonId" class="<s:text name="button.color.submit"/>" onclick="updateFunction();">
						<s:text name="Text.button.update" /><i class="<s:text name="button.icon.submitcard"/>"></i></button>
						<button type="button" id="userCancelButtonId" class="<s:text name="button.color.cancel"/>" onclick="cancelFunction();">
						<s:text name="Text.button.cancel" /><i class="<s:text name="button.icon.replycard"/>"></i></button>
                        </form>
       				</div>
					<s:form method="post" id="userCancelForm"></s:form>
				</div>				
			</section>
		</div>
	</div>
	<jsp:include page="../common/footer.jsp"></jsp:include>
	<jsp:include page="../common/allbasicjs.jsp"></jsp:include>	
	<script type="text/javascript" src="<s:text name='Resource.path'/>/js/jquery-te-1.4.0.min.js"></script>
    <script type="text/javascript">
         $(document).ready(function() { 
        	 
             $('#wysiwyg').jqte({outdent: false,indent: false,link:false,unlink:false,source: false,rule: false,strike:false,remove: false});
            	   $('[contenteditable]').on('paste',function(e) {
                      e.preventDefault();
                      var text = (e.originalEvent || e).clipboardData.getData('text/plain') || prompt('Paste something..');                   
                      insertAtCursor(text);
                  }); 
            	
            	 $.ajax({
     				type : 'post',
     				datatype : 'json',
     				url : 'getemailtemplatesub',
     				data : '',
     				success : function(data) {
     					var arr=data.split("!_!");	
     					$('#selectid').html(data);
     					 $('select').material_select(); 
     					 $('#subject').val('');
     					$(".jqte_editor").html('');
     					$("#wysiwyg").text('');
     					$("#subject11").removeClass("active");
     				}
     			}); 
            });
            function loadSubject() {
            	var selval=$('#stateId').val();              	
            	if(selval!='' && selval!=null){
            	$.ajax({
     				type : 'post',
     				datatype : 'json',
     				url : 'templatesubject',
     				data : 'templateid='+selval,
     				success : function(data) {
     					if(data.length>0){
     						var arr=data.split("!_!");	
     						document.getElementById('subject11').className = 'active';
         					$("#subject").val(arr[0]);
         					var aa= arr[1].replace(/&lt;/g,"<");
         					var aa1= aa.replace(/&gt;/g,">");
         					var aa2= aa1.replace(/&quot;/g,"'");
         					var aa3= aa2.replace(/&amp;nbsp;/g," ");
         					$(".jqte_editor").html(aa3.trim());
         					$("#wysiwyg").text(aa3.trim());
         					var str_array = arr[2].split(',');
         					var counter = 0;
         					$("#TextBoxesGroup").html("<div id='TextBoxDiv'></div>");
         					for(var i = 0; i < str_array.length; i++) {
         						var ress=str_array[i].trim();
         						if(ress!=''){
         						var newTextBoxDiv = $(document.createElement('div')).attr("id", 'TextBoxDiv' + counter);	
         						var ssss="<button type=\"button\" style=\"width:255px;margin-top:30px;\" class=\"form-control <s:text name="button.color.template"/>\" value='"+ress+"' onmousedown=\"insertAtCursor('"+ress+"');\">"+ress+" <i class=\"<s:text name="button.icon.replycard"/>\"></i></button><br/>";
         					    newTextBoxDiv.html(ssss);	            
         					    newTextBoxDiv.appendTo("#TextBoxesGroup"); 	
         						counter++; 
         						}else{
         							
         						}
         					}
     					}
     				}
     			}); 
            	}else{
            		$('#subject').val('');
            		document.getElementById('subject11').className = '';
            		$(".jqte_editor").html('');
 					$("#wysiwyg").text('');
            	}
            }
            function insertAtCursor(text) {             	 
                if($(document.activeElement).attr("class")==='jqte_editor'){
                    var sel, range, html;
                    if (window.getSelection) {
                        sel = window.getSelection();
                        if (sel.getRangeAt && sel.rangeCount) {
                            range = sel.getRangeAt(0);
                            range.deleteContents();
                            range.insertNode( document.createTextNode(text) );
                        }
                    } else if (document.selection && document.selection.createRange) {
                        document.selection.createRange().text = text;
                    }
                }
                else{
                	 swal("Please place your cursor inside the editor window");	  
                   // jAlert("Please place your cursor inside the editor window");
                }
            }
            function cancelFunction(){
            	$("#userCancelForm").attr("action", "Homeform");
            	$("#userCancelForm").submit();
            	$('#userCancelButtonId').addClass('<s:text name="cancel.button.animated.rollout"/>');		
            	toShowLoadingImgoverlay();
            	$(".card-panel").addClass('<s:text name="cardpanel.button.animated.flipoutx"/>');
            }
            function updateFunction(){
            	var sub=$('#subject').val();
            	if(sub!=''){
            	swal({ title: "Are you sure want to save template",
                    text: "You will save the email template!",   
                    type: "warning",   
                    showCancelButton: true,   
                    confirmButtonColor: "#DD6B55",   
                    confirmButtonText: "Yes",   
                    closeOnConfirm: false }, 
                    function(){ 
            		$("#form_template").attr("action", "tempUpdate");
                	$("#form_template").submit(); 
                	toShowLoadingImgoverlay();	          	
                    });
            }else{
            	 swal("Please select mail template");	  
            	
            }
            	}
        </script>
</body>
</html>
