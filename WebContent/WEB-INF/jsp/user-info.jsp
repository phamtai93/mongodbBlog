<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 
	<p>user info</p>
	<h4>id: ${user._id}</h4>
	<h4>name: ${user.name}</h4>
 -->
 
                    <div class="col-md-9">
                        <h3>Trang thông tin user</h3>
                        	<c:if test="${messageFromGetUserInfo != 'show user info'}">
                        		<p style="font-size: 16px">${messageFromGetUserInfo} </p>
                        	</c:if>
                        
                        <div class="tabs" id="div_tabs">
                            <ul class="nav nav-pills nav-justified">
                                <li class="active"><a href="#tab2-1" data-toggle="tab">Thông tin liên lạc</a>
                                </li>
                                <li class=""><a href="#tab2-2" data-toggle="tab">Bài viết</a>
                                </li>
                                <li class=""><a href="#tab2-3" data-toggle="tab">Tin nhắn</a>
                                </li>
                                
                            </ul>
                            
                            
                            
                            <div class="tab-content tab-content-inverse">
                                <div class="tab-pane active" id="tab2-1">
                                <c:if test="${messageFromGetUserInfo == 'show user info'}">
                                	<div class="box clearfix">
                            			<div class="heading">
                                			<h3 class="text-uppercase">Thông tin chi tiết</h3>
                            			</div>
										<div class="row">
											<div class="col-xs-4 col-sm-4" style="min-width: 96px">
	                                            <img src="${pageContext.request.contextPath}/${avatar}" alt="" style="min-width: 96px" class="img-thumbnail img-responsive">
	                                            <div class="row">
		                                            <div class="col-xs-4 col-sm-4">
		                                            	<button type="button" class="btn btn-template-main" title="thay đổi ảnh đại diện?" 
		                                            		data-toggle="modal" data-target="#show-images-modal"><i class="fa fa-picture-o"></i></button>
		                                            </div>
		                                            <div class="col-xs-8 col-sm-8" >	                                            	
		                                            	<!-- <input type="text"  value="click one please" style="display:table-cell; width:100%"> -->
		                                            </div>
		                                            
	                                            </div>
	                                            
											</div>
											<div class="col-xs-8 col-sm-8">
												<p>- Tên&nbsp;: &nbsp;<i id="iname"><c:out value="${user.name}"></c:out></i><p>
												<p>- Email&nbsp;: &nbsp;<i id="iemail"><c:out value="${user.email}"></c:out></i></p>
												<p>- Địa chỉ&nbsp;: &nbsp;<i id="iaddress"><c:out value="${user.address}"></c:out></i></p>
												<p>- Nơi làm việc&nbsp;: &nbsp;<i id="iworkplace"><c:out value="${user.workplace}"></c:out></i></p>
												<p>- Ngày sinh&nbsp;: &nbsp;
												<fmt:formatDate value="${user.dob}" var="dob" pattern="dd-MM-yyyy"/>
												<i id="idob"><c:out value="${dob}"></c:out></i>
												</p>
												<p>- Ngày tham gia&nbsp;: &nbsp;
												<fmt:formatDate value="${user.created}" var="dateCreated" pattern="dd-MM-yyyy HH:mm"/>
												<i><c:out value="${dateCreated}"></c:out></i>
												</p>
												<p>- Lần login gần đây&nbsp;: &nbsp;
												<fmt:formatDate value="${user.lastLogin}" var="lastLogin" pattern="dd-MM-yyyy HH:mm"/>
												<i><c:out value="${lastLogin}"></c:out></i>
												</p>
												<p>- Trạng thái&nbsp;: &nbsp;<i><c:out value="${enable}"></c:out>&nbsp;& <c:out value="${status}"></c:out></i></p>
											</div>
										</div>
										<div class="row">
											<div class="col-sm-11">
												<p></p>
												<p id="pdescribe">Giới thiệu&nbsp;: &nbsp;<c:out value="${user.describe}"></c:out></p>
											</div>
										</div>
										<div class="clearfix"></div>
										<hr>
										<c:if test="${changeable eq 'isallowed'}">
										<div class="row">
											<div class="col-sm-12">
												<div class="panel-group accordion" id="accordionThree">
													<div class="panel panel-default">
														<div class="panel-heading">
															<h5 class="">
																<a data-toggle="collapse" data-parent="#accordionThree" href="#collapse3a" id="iwannachangeinfo">
																Thay đổi thông tin? <i id = "change-icon" class="fa fa-pencil-square-o"></i>
																<i id="result-after-change" class="text-lowercase" style="font-size: 12px; color: blue"></i>
																</a>
															</h5>
															
														</div>
														<div id="collapse3a" class="panel-collapse collapse" >
															<div class="panel-body">
																<form onsubmit="return false">
									                                <div class="row">
									                                    <div class="col-sm-6">
									                                        <div class="form-group">
									                                            <label for="address">Địa chỉ<i id="addrerror" style="color: red"></i></label>
									                                            <input type="text" class="form-control" id="address" value="${user.address}">
									                                        </div>
									                                    </div>
									                                    <div class="col-sm-6">
									                                        <div class="form-group">
									                                            <label for="workplace">Nơi làm việc<i id="wkplerror" style="color: red"></i></label>
									                                            <input type="text" class="form-control" id="workplace" value="${user.workplace}">
									                                        </div>
									                                    </div>
									                                </div>
									                                <!-- /.row -->
									
									                                <div class="row">
									                                    <div class="col-sm-6">
									                                        <div class="form-group">
									                                        <fmt:formatDate value="${user.dob}" var="dob" pattern="yyyy-MM-dd"/>
									                                            <label for="dob">Ngày sinh <i id="doberror" style="color: red"></i></label>
									                                            <input type="date" class="form-control" id="dob" value="${dob}">
									                                        </div>
									                                    </div>
									                                    
									                                    <div class="col-sm-6">
									                                        <div class="form-group">
									                                            <label for="email">* Email<i id="emailerror" style="color: red"></i></label>
									                                            <input type="text" class="form-control" id="email" value="${user.email}">
									                                        </div>
									                                    </div>
									                                    <div class="col-sm-12">
									                                		<p><i>* Khi thay đổi email, bạn sẽ bị logout và sẽ phải check mail, kích hoạt lại tài khoản</i></p>
									                                	</div>
									                                </div>
									                                
									                                	
									                                <div class="row">
									                                    <div class="col-sm-12">
									                                        <div class="form-group">
									                                            <label for="describe">Giới thiệu<i id="descerror" style="color: red"></i></label>
									                                            <textarea class="form-control" id="describe" ><c:out value="${user.describe}"></c:out></textarea>
									                                        </div>
									                                    </div>
									                                    <div class="col-sm-12 text-center">
									                                    	<input type="hidden" value="${userName}" id="username_">
									                                    	<input type="hidden" value="${pageContext.request.contextPath}" id="roorUrl">
									                                        <button type="submit" class="btn btn-template-main" id="save_change_bt">
									                                        <i id="save-icon" class="fa fa-save"></i> Lưu thay đổi</button>
									                                    </div>
									                                    
									                                </div>
									                            </form>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
										</c:if>
                        			</div>
                                </c:if>

                        			
                                </div>
                                <!-- /.tab -->
                                <div class="tab-pane" id="tab2-2">
  
                                </div>
                                <!-- /.tab -->
                                <div class="tab-pane active" id="tab2-3">

                                </div>
                                <!-- /.tab -->
                                <div class="tab-pane" id="tab2-4">
                                    <p>
                                        Raw denim you probably haven't heard of them jean shorts Austin. Nesciunt tofu stumptown aliqua, retro synth master cleanse. Mustache cliche tempor, williamsburg carles vegan helvetica. Reprehenderit butcher retro keffiyeh dreamcatcher synth. Cosby sweater
                                        eu banh mi, qui irure terry richardson ex squid. Aliquip placeat salvia cillum iphone. Seitan aliquip quis cardigan american apparel, butcher voluptate nisi qui.
                                    </p>

                                </div>
                                <!-- /.tab -->
                            </div>
                        </div>
                        <!-- /.tabs -->
                    </div>
                    
		<div class="modal fade" id="show-images-modal" tabindex="-1" role="dialog" aria-labelledby="Show-images" aria-hidden="true">
            <div class="modal-dialog"><!-- modal-sm -->

                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="Show-images">Your Images</h4>
                    </div>
                    <div class="modal-body">
                        <form action="customer-orders.html" method="post">


                        </form>

                        <p class="text-center text-muted">Not registered yet?</p>
                        <p class="text-center text-muted"><a href="customer-register.html"><strong>Register now</strong></a>! It is easy and done in 1&nbsp;minute and gives you access to special discounts and much more!</p>

                    </div>
                </div>
            </div>
        </div>
                    
                    
<c:if test="${changeable eq 'isallowed'}">                 
	<script type="text/javascript">

		$(document).ready(function(){
			
			var readyForClick = true;
			function validate(){
	      		var flag = true;
	      		var email = $.trim($('#email').val());
	
	      		var dob = $.trim($('#dob').val());
	      		var address = $.trim($('#address').val());
	      		var workplace = $.trim($('#workplace').val());
	      		var describe = $.trim($('#describe').val());
	
	      		var regexEmail = /\b[A-Z0-9._%+-]+@(?:[A-Z0-9-]+\.)+[A-Z]{2,6}\b/i;
	
	      		var regexDate=/\b([0-9]{4}[-|/](0[1-9]|1[0-2])[-|/]([0-2]{1}[0-9]{1}|3[0-1]{1})\b|\b([0-2]{1}[0-9]{1}|3[0-1]{1})[-|/](0[1-9]|1[0-2])[-|/][0-9]{4})\b/;
	      		
	      		
	      		if(address.length>60){
	      			flag = false;
	  				document.getElementById("addrerror").innerHTML='&nbsp;&nbsp;&nbsp; địa chỉ quá dài, tối đa khoảng 60 ký tự';
	      		}
	      		else{document.getElementById("addrerror").innerHTML='';}
	      		
	      		if(workplace.length>60){
	      			flag = false;
	  				document.getElementById("wkplerror").innerHTML='&nbsp;&nbsp;&nbsp; nơi làm việc quá dài, tối đa khoảng 60 ký tự';
	      		}
	      		else{document.getElementById("wkplerror").innerHTML='';}
	      		
	      		if(email === ""|| email == null){
					// cho phép để trống;
	      		}
	      		else{
	      			
	      			if(email.length > 254){
	      				document.getElementById("emailerror").innerHTML='&nbsp;&nbsp;&nbsp; email quá dài..!';
	      			}
	      			else{
	      				if(!email.match(regexEmail)){
	              			flag = false;
	              			document.getElementById("emailerror").innerHTML='&nbsp;&nbsp;&nbsp; email có vẻ như không hợp lệ!?';
	              		}else{
	              			document.getElementById("emailerror").innerHTML='';
	              		}
	      			}
	      		}
	      		
	      		if(describe.length>500){
	      			flag = false;
	      			document.getElementById("descerror").innerHTML='&nbsp;&nbsp;&nbsp; giới thiệu tối đa khoảng 500 kí tự';
	      		}
	      		else{document.getElementById("descerror").innerHTML='';}
	
	      		
	      		if(dob != null && dob !=="" ){
	      			if(!dob.match(regexDate)){
	          			flag = false;
	          			document.getElementById("doberror").innerHTML='&nbsp;&nbsp;&nbsp; ngày sinh không đúng định dạng?!';
	          		}
	          		else{
	          			var age = _calculateAge(new Date(dob));
	          			if(age < 12){
	          				flag = false;
	          				document.getElementById("doberror").innerHTML='&nbsp;&nbsp;&nbsp; bạn phải từ 12 tuổi trở lên';
	          			}
	          			else{document.getElementById("doberror").innerHTML='';}
	          		}
	      		}
	      		
	      		
	      		return flag;
	      	}
	      	
	      	function _calculateAge(birthday) { // birthday is a date
	      	    var ageDifMs = Date.now() - birthday.getTime();
	      	    var ageDate = new Date(ageDifMs); // miliseconds from epoch
	      	    return Math.abs(ageDate.getUTCFullYear() - 1970);
	      	}
			
	      	
			var token = $("meta[name='_csrf']").attr("content");
	    	var header = $("meta[name='_csrf_header']").attr("content");
			var name = $("#username_").val();
			var rootUrl = $("#rootUrl").val();
			lastInfo = [$('#address').val(),$('#workplace').val(), $('#dob').val(), $('#email').val(), $('#describe').val()];;
			
			
			$("#save_change_bt").click(function(){
				if(readyForClick){
					$(this).prop("disable",true);
					readyForClick = false;
					$("#save-icon").removeClass("fa-save");
					$("#save-icon").addClass("fa-spinner fa-spin");
					
					$("#change-icon").removeClass("fa-pencil-square-o");
					$("#change-icon").addClass("fa-spinner fa-spin fa-lg");
					
					var valid = validate();
					if(valid == true){
						
						var flag_2 = false; 
						//alert("lastinfo: " + lastInfo.toString() );
						jsonObj = {};
						var s;
						
						if( (s =$('#address').val()) !== lastInfo[0]){
							jsonObj.address = s; flag_2 = true; 
						} 
						
						if( (s =$('#workplace').val()) !== lastInfo[1]){
							jsonObj.workplace = s; flag_2 = true; 
						}
						
						if( (s =$('#dob').val()) !== lastInfo[2]){
							jsonObj.dob = s; flag_2 = true; 
						}
						
						if( (s =$('#email').val()) !== lastInfo[3]){
							jsonObj.email = s; flag_2 = true;
						}
						
						if( (s =$('#describe').val()) !== lastInfo[4]){
							jsonObj.describe = s; flag_2 = true;
						}
						
						jsonObj.name = name;
						jsonString = JSON.stringify(jsonObj);
		
						if(name===""){
							alert("Bạn không được phép, có thể bạn chưa đăng nhập hoặc tải trang bị lỗi... Bạn hãy tải lại trang hoặc nhập lại");
							$(this).prop("disable",true);
						}
						else
							if(flag_2){
							$.ajax({
								    type:"POST",
								    url: "${pageContext.request.contextPath}/authUser/save-change-user-info.web",
								    data: JSON.stringify(jsonObj),
								    beforeSend: function(xhr) { 
								    	xhr.setRequestHeader(header, token);
							            xhr.setRequestHeader("Accept", "application/json");  
							            xhr.setRequestHeader("Content-Type", "application/json; charset=UTF-8");  
							        },
							        success:function(response){
							        	if(response.end === "success"){
							        		document.getElementById("result-after-change").innerHTML= response.message;
						        			for (var key in jsonObj) {
								        		  if (jsonObj.hasOwnProperty(key)) {
								        			  switch(key) {
								        			  case "address":
								        				  document.getElementById("iaddress").innerHTML = jsonObj.address; // cập nhật lại thông tin hiển thị trong <p></P> 
								        				  lastInfo[0]=jsonObj.address; // cập nhật lại address trong mảng lastInfo
								        				  break;
								        			  case "workplace":
								        				  document.getElementById("iworkplace").innerHTML = jsonObj.workplace;
								        				  lastInfo[1]=jsonObj.workplace;
								        				  break;
								        			  case "dob":
								        				  document.getElementById("idob").innerHTML = formatDateTime(jsonObj.dob, false);
								        				  lastInfo[2]=jsonObj.dob;
								        				  break;
								        			  case "email":
								        				  lastInfo[3]=jsonObj.email;
								        				  document.getElementById("iemail").innerHTML = jsonObj.email;
								        				  setTimeout(function(){window.location.href = rootUrl + "/logout.html";}, 6000);
								        				  break;
								        			  case "describe":
								        				  lastInfo[4]=jsonObj.describe;
								        				  document.getElementById("pdescribe").innerHTML = "Giới thiệu&nbsp;:&nbsp;&nbsp;" + jsonObj.describe;
								        				  break;
								        			  }
								        		    
								        		  }
								        	}
						        			$('#collapse3a').collapse("toggle");
							        	}
							        	else
								        	if(response.end === "fail"){
								        		document.getElementById("result-after-change").innerHTML= "" + response.message; 
								        	}
								        	else
								        		if(response.end === "inputwrong"){
								        			document.getElementById("result-after-change").innerHTML="có ít nhất 1 trường không hợp lệ !";
									        		for (var key in response) {
										        		  if (response.hasOwnProperty(key)) {
										        			  switch(key) {
										        			  case "address": 
										        				  if(response.address==='ok'){
										        					  document.getElementById("iaddress").innerHTML = jsonObj.address; // cập nhật lại thông tin hiển thị trong <p></P> 
											        				  lastInfo[0]=jsonObj.address; // cập nhật lại address trong mảng lastInfo
											        				  break;
										        				  }else{
										        					  document.getElementById("addrerror").innerHTML = response.address;
											        				  break;
										        				  }
										        				  
										        			  case "workplace": 
										        				  if(response.workplace==='ok'){
										        					  document.getElementById("iworkplace").innerHTML = jsonObj.workplace; 
											        				  lastInfo[1]=jsonObj.workplace; 
											        				  break;
										        				  }else{
										        					  document.getElementById("wkplerror").innerHTML = response.workplace;
											        				  break;
										        				  }
										        				  
										        			  case "dob": 
										        				  if(response.dob==='ok'){
										        					  document.getElementById("iaddress").innerHTML = jsonObj.dob;
											        				  lastInfo[2]=jsonObj.dob;
											        				  break;
										        				  }else{
										        					  document.getElementById("doberror").innerHTML = response.dob;
											        				  break;
										        				  }
										        				  
										        			  case "email": 
										        				  if(response.email==='ok'){
										        					  document.getElementById("iaddress").innerHTML = jsonObj.email; 
											        				  lastInfo[3]=jsonObj.email;
											        				  break;
										        				  }else{
										        					  document.getElementById("emailerror").innerHTML = response.email;
											        				  break;
										        				  }
										        				  
										        			  case "describe": 
										        				  if(response.describe==='ok'){
										        					  document.getElementById("iaddress").innerHTML = jsonObj.describe; 
											        				  lastInfo[4]=jsonObj.describe; 
											        				  break;
										        				  }else{
										        					  document.getElementById("descerror").innerHTML = response.describe;
											        				  break;
										        				  }
										        				  
										        			  }
										        		    
										        		  }
										        	}
								        		}
							        	
						        		$('html, body').animate({
					                        scrollTop: $("#div_tabs").offset().top}, 500);
						        		$("#save-icon").removeClass("fa-spinner fa-spin");
										$("#save-icon").addClass("fa-save");
										$("#change-icon").removeClass("fa-spinner fa-spin fa-lg");
										$("#change-icon").addClass("fa-pencil-square-o");
							        	setTimeout(function() { document.getElementById("result-after-change").innerHTML=""; }, 3000);
							        	$(this).prop("disable",false);
							        	readyForClick = true;
	
							        },
							        error:function(jqXhr, textStatus, errorThrown){
							        	alert(textStatus);
							        	$('#collapse3a').collapse("toggle");
							        	$('html, body').animate({
					                        scrollTop: $("#div_tabs").offset().top}, 500);
							        	$("#save-icon").removeClass("fa-spinner fa-spin");
										$("#save-icon").addClass("fa-save");
										$("#change-icon").removeClass("fa-spinner fa-spin fa-lg");
										$("#change-icon").addClass("fa-pencil-square-o");
							        	cancel_(lastInfo[0],lastInfo[1],lastInfo[2],lastInfo[3],lastInfo[4]);
							        	$(this).prop("disable",false);
							        	readyForClick = true;
							        }
								});
							}
							else{
								document.getElementById("result-after-change").innerHTML="không có trường nào được thay đổi !";
								$('#collapse3a').collapse("toggle");
								$('html, body').animate({
			                        scrollTop: $("#div_tabs").offset().top}, 500);
								$("#save-icon").removeClass("fa-spinner fa-spin");
								$("#save-icon").addClass("fa-save");
								$("#change-icon").removeClass("fa-spinner fa-spin fa-lg");
								$("#change-icon").addClass("fa-pencil-square-o");
								setTimeout(function() { document.getElementById("result-after-change").innerHTML=""; }, 3000);
								$(this).prop("disable",false);
								readyForClick = true;
	
							}
						
					}
				}
			});
			
			$("#iwannachangeinfo").click(function(){
		    });
			
			function cancel_(x0, x1, x2, x3, x4){
				$('#address').val(x0); 
				$('#workplace').val(x1); 
		        $('#dob').val(x2);
		        $('#email').val(x3);
		        $('#describe').val(x4);        
			};
			
			
			function formatDateTime(stringDate, haveTime) {
				var dateTime = new Date(stringDate );
				var date = dateTime.getDate();
				var month = dateTime.getMonth();
				var result ="";
				if(date < 10){result="0" + date} else{result = "" + date}
				if(month < 10){result=result + "-0" + (month+1)} else{result = result + "-" + (month+1)}
				result = result + "-" + dateTime.getFullYear();
				if(haveTime){
					var hour = dateTime.getHours();
					var minute = dateTime.getMinutes();
					var second = dateTime.getSeconds();
					if(hour < 10){result = result + " 0" + hour} else{result = result + " " + hour}
					if(minute < 10){result = result + ":0" + minute} else{result = result + ":" + minute}
					if(second < 10){result = result + ":0" + second} else{result = result + ":" + second}
				}
				return result;
		    }; 
			
		});

	</script>
	
</c:if>  