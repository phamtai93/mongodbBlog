<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
      <div id="content">
          <div class="container">

              <div class="row">
			<div class="col-md-2">
			</div>
                  <div class="col-md-8">
                      <div class="box">
                          <h2 class="text-uppercase">Login</h2>
                          <c:if test="${not empty param.authfailed}">
                          	<h5 style="color: red">Error: email hoặc password không đúng !</h5>
                          </c:if>
                          <hr>

                          <form action="j_spring_security_check" method="post" onsubmit="return validate()">
                              <div class="form-group">
                                  <label for="email">Email <i id="emailerror" style="color: red"></i></label>
                                  <input type="text" class="form-control" id="email" name="j_username">
                              </div>
                              <div class="form-group">
                                  <label for="password">Password <i id="pwderror" style="color: red"></i></label>
                                  <input type="password" class="form-control" id="password" name="j_password">
                              </div>
                              <div class="form-group ">
                              	<label class="" for="rememberMe"  >
									<input id="rememberMe"  type="checkbox" value="yes" name="_spring_security_remember_me" title=""/>
									Remember me </label>
								<a>&nbsp&nbsp&nbsp&nbsp</a>
								<a> quên mật khẩu ?</a>
                              </div>
                              <div class="text-center">
                                  <button type="submit" class="btn btn-template-main" ><i class="fa fa-sign-in"></i> Log in</button>
                              </div>
                              <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                          </form>
                      </div>
                  </div>
			

              </div>
              <!-- /.row -->

          </div>
          <!-- /.container -->
      </div>
      <!-- /#content -->
      
      <script type="text/javascript">
      	function validate(){
      		var flag = true;
      		var email = $.trim($('#email').val());
      		var pwd = $('#password').val();
      		var regexEmail = /\b[A-Z0-9._%+-]+@(?:[A-Z0-9-]+\.)+[A-Z]{2,6}\b/i;
      		if(email === ""|| email == null){
      			flag = false;
      			document.getElementById("emailerror").innerHTML='&nbsp&nbsp&nbsp email không được để trống';
      		}
      		else{
      			if(!email.match(regexEmail)){
          			flag = false;
          			document.getElementById("emailerror").innerHTML='&nbsp&nbsp&nbsp email có vẻ như không hợp lệ!?';
          		}else{
          			document.getElementById("emailerror").innerHTML='';
          		}
      		}
      		
      		if(pwd === ""|| pwd == null){
      			flag = false;
      			document.getElementById("pwderror").innerHTML='&nbsp&nbsp&nbsp password không được để trống';
      		}else{
      			document.getElementById("pwderror").innerHTML='';
      		}
      		
      		return flag;
      	}
      
      	
      </script>