<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
	
  <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
  <script src="https://apis.google.com/js/api:client.js"></script>
  <script>
  var googleUser = {};
  var startApp = function() {
    gapi.load('auth2', function(){
      // Retrieve the singleton for the GoogleAuth library and set up the client.
      auth2 = gapi.auth2.init({
        client_id: '378061940201-ner3qldkjnhvdfkmbs5gifhr4gtv84gc.apps.googleusercontent.com',
        cookiepolicy: 'single_host_origin',
        // Request scopes in addition to 'profile' and 'email'
        //scope: 'additional_scope'
      });
      attachSignin(document.getElementById('customBtn'));
    });
  };

  function attachSignin(element) {
    console.log(element.id);
    auth2.attachClickHandler(element, {},
        function(googleUser) {
          //document.getElementById('name').innerText = "Signed in: " +
              googleUser.getBasicProfile().getName();
          ///alert("Basic BasicProfile==="+googleUser.getBasicProfile())
          var profile = googleUser.getBasicProfile();
    	  //var id_token = googleUser.getAuthResponse().id_token;
    	 // alert("==="+profile.getId()+"==="+profile.getName()+"==="+profile.getImageUrl()+"==="+profile.getEmail()+"==="+profile.gender+"==="+profile.family_name+"==="+profile.locale+"==="+profile.given_name);
    	  console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
    	  console.log('Name: ' + profile.getName());
    	  console.log('Image URL: ' + profile.getImageUrl());
    	  console.log('Email: ' + profile.getEmail());
         var email = profile.getEmail();
         var first_name = profile.getName();
         var last_name = profile.getName();
         var uniqid = profile.getId();
         var imgurl  = profile.getImageUrl();
         
         
         if(uniqid != null && uniqid != ""){
       	  $.ajax({
           		type : 'post',
           		datatype : 'html',
           		url : 'checksocialuserIdexist',
           		data : 'googleuserId='+uniqid+'&soctype='+2,
           		success : function(data) {
           			var count = $.trim(data);
           			var dataval = $.trim(data);
        			//alert(dataval);
        		
        			if(dataval.length < 0){
        				//alert("success");
        				var values = dataval.split("!_!");
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
           		      			$("#maildiv").hide();
           		      			$("#userdata").hide();
           		      			$("#formcont").html("");
           		      			$("#formcont").html(data);
           		      			$("#firstname").val(first_name);
           		            	  $("#lastname").val(last_name);
           		            	  $("#userid").val(uniqid);
           		            	  $("#pic_url").val(imgurl);
           		            	  $("#social_type").val("2");
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
           		              	//formvaldation();
           		              	
           		              	$("#newbtn").click();
           		                }else{
           		                	$("#smform").attr("action","smnewcustomerform");
           		                	$("#passdiv").hide();
           		                	$("#userdata").hide();
           		                	 $("#fname").val(first_name);
           		                 	  $("#lname").val(last_name);
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
    	  
        }, function(error) {
          alert(JSON.stringify(error, undefined, 2));
        });
  }
  </script>
  <style type="text/css">
    #customBtn {
      display: inline-block;
      background: #4285f4;
      color: white;
      width: 190px;
      border-radius: 5px;
      white-space: nowrap;
      display: none;
    }
    #customBtn:hover {
      cursor: pointer;
    }
    span.label {
      font-weight: bold;
    }
    span.icon {
      background: url('/identity/sign-in/g-normal.png') transparent 5px 50% no-repeat;
      display: inline-block;
      vertical-align: middle;
      width: 42px;
      height: 42px;
      border-right: #2265d4 1px solid;
    }
    span.buttonText {
      display: inline-block;
      vertical-align: middle;
      padding-left: 42px;
      padding-right: 42px;
      font-size: 14px;
      font-weight: bold;
      /* Use the Roboto font that is loaded in the <head> */
      font-family: 'Roboto', sans-serif;
    }
  </style>
  </head>
  <body>
  <!-- In the callback, you would hide the gSignInWrapper element on a
  successful sign in -->
 
  <script>startApp();</script>
</body>
</html>