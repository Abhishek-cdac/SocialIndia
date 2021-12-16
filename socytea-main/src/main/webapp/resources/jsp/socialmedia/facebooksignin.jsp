<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html>
<html>
<head>
<title>Facebook Login JavaScript Example</title>
<meta charset="UTF-8">
</head>
<body>
<script>
  // This function is called when someone finishes with the Login
  // Button.  See the onlogin handler attached to it in the sample
  // code below.
  function checkLoginState() {
	  FB.login(function(response) {
     	 //alert("Successfully Posted.=="+response.status+"==="+response.authResponse);
         if (response.authResponse) {
             accessToken1 =   FB.getAuthResponse()['accessToken'];
            // alert(accessToken1);
             getLoggedUserDetail();
           //  postToFeed(accessToken1);
         }
         }, {perms:'email,user_about_me,user_birthday,user_education_history,user_hometown,user_likes,user_location,user_work_history'});
  }
  var retu="";
  var logDet = "";
  var logDetIndex="";
  var logUsr="";
  var allwrkhis="";
  var fuledu="";
  var profileupmrg="";// for profile merge
  function getLoggedUserDetail() {   
      var fullname="";
      FB.api('/me?fields=id,name,first_name,last_name,birthday,bio,quotes,gender,hometown,location,languages,work,education,email,likes,groups,updated_time', function(response) {            
          var prnt = response.id;
          logUsr= prnt;               
          var uname = response.name;           
          var first_name = response.first_name;
          var last_name = response.last_name;
          var birthday = response.birthday;
          var bio = response.bio;
          var quotes = response.quotes;
          var gender = response.gender;
          var email = response.email;
          var hometown = response.hometown;
          var hometownid = response.hometown.id;
          var hometownname = response.hometown.name;
          var locationid = response.location.id;
          var locationname =  response.location.name;
          var location = response.location;
          var lstuptime=response.updated_time; 
          var work = response.work;
         // var education = response.education;
          
         //  var data1=prnt+"---"+uname+"---"+first_name+"---"+last_name+"---"+birthday+"--"+gender+"---"+email+"---"+lstuptime+"---"+hometownid+"---"+hometownname+"---"+locationid+"---"+locationname;
      var data1=logUsr+"---"+first_name+"---"+birthday+"---"+bio+"---"+quotes+"---"+gender+"---"+email+"---"+hometown+"---"+hometownid+"---"+hometownname+"---"+locationid+"---"+locationname+"---"+location+"---"+lstuptime;
        // alert(data1);
           if(response.education!=undefined){
              var d3 = getEducation(response.education,prnt,"-");
          }
          
          if(response.languages!=undefined){
              var d1 = getLanguages(response.languages,prnt,"-");
          }
                  
          if(response.work!=undefined){
              var d2 = getWorks(response.work,prnt,"-");
          } 
          if(prnt != null && prnt != ""){ 
        	  $.ajax({
          		type : 'post',
          		datatype : 'html',
          		url : 'checksocialuserIdexist',
          		data : 'fbuserId='+prnt+'&soctype='+1,
          		success : function(data) {
          			var dataval = $.trim(data);
          			//alert(dataval);
          			
          			if(dataval.length > 0){
          				//alert("success");
          				var values = dataval.split("!_!");
          				alert("-------------"+values);
          				//alert(values[0]);
          				//alert(values[1]);
          				$("#fbuname").val(values[0]);
          				$("#fbpswd").val(values[1]);
          				$("#fbexistuserform").attr("action","existsocialusersignin");
          				$("#fbexistuserform").submit();
          			}else{         				
            				$.ajax({
            		      		type : 'post',
            		      		datatype : 'html',
            		      		url : 'socialregform',
            		      		data : '',
            		      		success : function(data) {
            		      			$("#formcont").html("");
            		      			$("#formcont").html(data);
            		      			
            		      			 $("#maildiv").hide();
            		             	  $("#firstname").val(first_name);
            		             	  $("#lastname").val(last_name);
            		             	 $("#userid").val(prnt);
            		             	 $("#soc_name").val(uname);
            		             	 $("#gender").val(gender);
            		             	 $("#permt_addr_id").val(locationid);
            		             	 $("#permt_city").val(locationname);
            		             	 $("#social_type").val("1");
            		      			if(email != null && email != ""){
            		      				$("#smform").attr("action","socialmediaform");
            		              	  $("#emailid").val(email);
            		              	             		              	  
            		              	$('#smform')
            		              	.bootstrapValidator(
            		              			{
            		              				
            		              				message : 'This value is not valid',
            		              				
            		              				feedbackIcons : {
            		              					valid : '<s:text name="Text.feedback.ok" />',
            		              					invalid : '<s:text name="Text.feedback.remove" />',
            		              					validating : '<s:text name="Text.feedback.refresh" />'
            		              				},
            		              				fields : {
            		              					societyKey : {
            		              		selector : '.societyKey',
            		              		validators : {
            		              			notEmpty : {
            		              				message : '<s:text name="Error.customerreg.societykey" />'
            		              			},
            		              			stringLength : {
                								min : 3,
                								max : 20,
                								message : '<s:text name="Error.customerreg.societykey.length" />'
                							},
            		              		}
            		              	},
            		              	emailid : {
                						selector : '.emailid',
                						validators : {
                							notEmpty : {
                								message : '<s:text name="Error.customerreg.emailid" />'
                							},
                							stringLength : {
                								min : 8,
                								max : 70,
                								message : '<s:text name="Error.customerreg.emailid.length" />'
                							},
                							emailAddress : {
                								message : '<s:text name="Error.customerreg.emailid.validate" />'
                							}
                						}
                					},
            		              	mobilevalidate : {
										selector : '.mobilevalidate',
										validators : {
											notEmpty : {
												message : '<s:text name="Error.customerreg.mobileno" />'
											},
											stringLength : {
												min : 5,
												max : 15,
												message : '<s:text name="Error.customerreg.mobileno.length" />'
											},
											regexp : {
												regexp : /^[0-9]+$/,
												message : '<s:text name="Error.customerreg.mobileno.validate" />'
											}

										}
									}
            		              				}
            		              			});
            		              	$("#newbtn").click();
            		                }else{
            		                	$("#smform").attr("action","smnewcustomerform");
            		                	$("#passdiv").hide();
            		                	$("#mobilenodiv").hide();
            		                	$("#emaildiv").hide();
            		                 	  $("#newbtn").click();
            		                 	 $('#smform')
            		                   	.bootstrapValidator(
            		                   			{
            		                   				message : 'This value is not valid',
            		                   				
            		                   				feedbackIcons : {
            		                   					valid : '<s:text name="Text.feedback.ok" />',
            		                   					invalid : '<s:text name="Text.feedback.remove" />',
            		                   					validating : '<s:text name="Text.feedback.refresh" />'
            		                   				},
            		                   				fields : {
            		                   					emailid : {
            		                						selector : '.emailid',
            		                						validators : {
            		                							notEmpty : {
            		                								message : '<s:text name="Error.customerreg.emailid" />'
            		                							},
            		                							stringLength : {
            		                								min : 8,
            		                								max : 70,
            		                								message : '<s:text name="Error.customerreg.emailid.length" />'
            		                							},
            		                							emailAddress : {
            		                								message : '<s:text name="Error.customerreg.emailid.validate" />'
            		                							}
            		                						}
            		                					},
            		                					mobilevalidate : {
        													selector : '.mobilevalidate',
        													validators : {
        														notEmpty : {
        															message : '<s:text name="Error.customerreg.mobileno" />'
        														},
        														stringLength : {
        															min : 5,
        															max : 15,
        															message : '<s:text name="Error.customerreg.mobileno.length" />'
        														},
        														regexp : {
        															regexp : /^[0-9]+$/,
        															message : '<s:text name="Error.customerreg.mobileno.validate" />'
        														}

        													}
        												}
            		                   				}
            		                   			});
            		                 	  
            		                }
            		      		}
            		          });
            				
            			}
            		}
            		});
          }
          
          
      });
  }
  window.fbAsyncInit = function() {
  FB.init({
    appId      : '805508096241894',
    cookie     : true,  // enable cookies to allow the server to access 
                        // the session
    xfbml      : true,  // parse social plugins on this page
    version    : 'v2.5' // use version 2.2
  });
  };
  // Now that we've initialized the JavaScript SDK, we call 
  // FB.getLoginStatus().  This function gets the state of the
  // person visiting this page and can return one of three states to
  // the callback you provide.  They can be:
  //
  // 1. Logged into your app ('connected')
  // 2. Logged into Facebook, but not your app ('not_authorized')
  // 3. Not logged into Facebook and can't tell if they are logged into
  //    your app or not.
  //
  // These three cases are handled in the callback function.
  
  // Load the SDK asynchronously
  (function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = "//connect.facebook.net/en_GB/sdk.js#xfbml=1&version=v2.5";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));
  
  
  function udf(ob){
      if(ob!=undefined){
          return true;
      }
      return false;
  }
  
  function getEducation(ar,v1,v2){
      var d = "";
      for(var i = 0;i<ar.length;i++){
          if(udf(ar[i].type)){
              d += v1+"<s_p>"+v2+"<s_p>education-type<s_p>"+ar[i].type+"<s_p>1<spk>";
             
          }                  
          if(udf(ar[i].school.name)){
              //                        d +="education-school-id<s_p>"+ar[i].school.id+"<spk>";
              d += v1+"<s_p>"+v2+"<s_p>education-school-name<s_p>"+ar[i].school.name+"<s_p>1<spk>";
             
          }                           
      }                
      return d;
  }
 
   
  function getLanguages(ar,v1,v2){
      var d = "";
      for(var i = 0;i<ar.length;i++){                   
          d+="languages-name<s_p>"+ar[i].name+"<s_p>1<spk>";
      }
      return d;
  }
  
          
  function getWorks(ar,v1,v2){
      var d = "";
      for(var i = 0;i<ar.length;i++){
          var empid="-";
          if(ar[i].employer!=undefined){
              empid=ar[i].employer.id;
              //  alert(empid);
              d += "work-employer-id<s_p>-<s_p>"+empid +"<s_p>1<spk>";
              d += "work-employer-name<s_p>"+empid+"<s_p>"+ar[i].employer.name+"<s_p>1<spk>";
             
          }
          if(ar[i].location!=undefined){
              //                        d +="work-location-id<s_p>"+ar[i].location.id+"<spk>";
              d += "work-location-name<s_p>"+empid+"<s_p>"+ar[i].location.name+"<s_p>1<spk>";
             
          }
          if(ar[i].position!=undefined){
              //                        d +="work-position-id<s_p>"+ar[i].position.id+"<spk>";
              d += "work-position-name<s_p>"+empid+"<s_p>"+ar[i].position.name+"<s_p>1<spk>";
             
          }
                  
          if(ar[i].description!=undefined){
              d += "work-description<s_p>"+empid+"<s_p>"+ar[i].description+"<s_p>1<spk>";
              
          }
          if(ar[i].start_date!=undefined){
              d += "work-start_date<s_p>"+empid+"<s_p>"+ar[i].start_date+"<s_p>1<spk>";
             
          }
          if(ar[i].end_date!=undefined){
              d += "work-end_date<s_p>"+empid+"<s_p>"+ar[i].end_date+"<s_p>1<spk>";
              
          }
                  
          if(ar[i].projects!=undefined){
              for(var j = 0;j<ar[i].projects.length;j++){
                  var prjid=ar[i].projects[j].id;
                  if(ar[i].projects[j].name!=undefined){
                      d += "work-projects-name<s_p>"+prjid+"<s_p>"+ar[i].projects[j].name+"<s_p>1<spk>";
                    
                  }
                  if(ar[i].projects[j].description!=undefined){
                      d += "work-projects-description<s_p>"+prjid+"<s_p>"+ar[i].projects[j].description+"<s_p>1<spk>";
                      
                          
                  }
                  if(ar[i].projects[j].start_date!=undefined){
                      d += "work-projects-start_date<s_p>"+prjid+"<s_p>"+ar[i].projects[j].start_date+"<s_p>1<spk>";
                      
                  }
                  if(ar[i].projects[j].end_date!=undefined){
                      d += "work-projects-end_date<s_p>"+prjid+"<s_p>"+ar[i].projects[j].end_date+"<s_p>1<spk>";
                      
                  }
                          
              }
          }
      }
      return d;
  }
  

</script>

<!--
  Below we include the Login Button social plugin. This button uses
  the JavaScript SDK to present a graphical Login button that triggers
  the FB.login() function when clicked.
-->
<!--<div id="fb-root"></div>
 <div class="fb-login-button" data-max-rows="1" data-size="large" data-show-faces="false" data-auto-logout-link="false" onclick="checkLoginState();"></div>-->
  <%-- <input type="button" name="fbpage" id="fbpage" value="Employer Page" class="btnblue left hrjobbtn"   onclick="checkLoginState();"/> 
 
 <button type="button" class="btn btn-primary" data-toggle="modal" id="newbtn" data-target=".modal">Large modal</button>
<form id="smform" role="form" method="post" action="">
<s:hidden id="sysruleId" name="sysruleId" value="%{systemrule.condition}"></s:hidden>
</form> --%>

</body>

</html>