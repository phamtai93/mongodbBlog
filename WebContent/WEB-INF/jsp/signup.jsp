<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

        <div id="content">
            <div class="container">

                <div class="row">
                    <div class="col-md-6">
                        <div class="box">
                            <h2 class="text-uppercase">Đăng ký</h2>

                            <p class="lead">Bạn chưa có tài khoản?</p>
                            <p>Bằng cách điền vào form bên dưới và click vào đăng ký để có ngay một tài khoản...</p>
                            
                            <hr>

                            <form:form action="register-processing.html" modelAttribute="user" method="post" onsubmit="return validate()">
                                <div class="form-group">
                                    <label for="name">Tên <i id="nameerror" style="color: red"></i>
                                    <form:errors path="name" cssStyle="color: red;" element="i"></form:errors>
                                    </label>
                                    <input type="text" class="form-control" id="name" name="name">
                                </div>
                                <div class="form-group">
                                    <label for="email">Email <i id="emailerror" style="color: red"></i></label>
                                    <form:errors path="email" cssStyle="color: red;" element="i"></form:errors>
                                    <input type="text" class="form-control" id="email" name="email">
                                </div>
                                <div class="form-group">
                                    <label for="password">Password <i id="pwderror" style="color: red"></i></label>
                                    <form:errors path="password" cssStyle="color: red;" element="i"></form:errors>
                                    <input type="password" class="form-control" id="password" name="password">
                                </div>
                                <div class="form-group">
                                    <label for="passwordconfirm">Nhập lại Password <i id="pwdcferror" style="color: red"></i></label>
                                    <input type="password" class="form-control" id="passwordconfirm" name="passwordconfirm">
                                </div>
                                <div class="form-group">
                                    <label for="dob">Ngày sinh <i id="doberror" style="color: red"></i></label>
                                    <form:errors path="dob" cssStyle="color: red;" element="i"></form:errors>
                                    <input type="date" class="form-control" id="dob" name="dob">
                                </div>
                                <div class="form-group">
                                    <label for="address">Địa chỉ <i id="addrerror" style="color: red"></i></label>
                                    <form:errors path="address" cssStyle="color: red;" element="i"></form:errors>
                                    <input type="text" class="form-control" id="address" name="address">
                                </div>
                                <div class="form-group">
                                    <label for="workplace">Nơi làm việc <i id="wkplerror" style="color: red"></i></label>
                                    <form:errors path="workplace" cssStyle="color: red;" element="i"></form:errors>
                                    <input type="text" class="form-control" id="workplace" name="workplace">
                                </div>
                                <div class="form-group">
                                    <label for="describe">Giới thiệu <i id="descerror" style="color: red"></i></label>
                                    <form:errors path="describe" cssStyle="color: red;" element="i"></form:errors>
                                    <textarea id="describe" rows="4" class="form-control" name="describe"></textarea>
                                </div>
                                
                                <div class="text-center">
                                    <button type="submit" class="btn btn-template-main"><i class="fa fa-user-md"></i> Register</button>
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                </div>
                            </form:form>
                        </div>
                    </div>
                    
                    
       <script type="text/javascript">
      	function validate(){
      		var flag = true;
      		var email = $.trim($('#email').val());
      		var name = $.trim($('#name').val());
      		var dob = $.trim($('#dob').val());
      		var address = $.trim($('#address').val());
      		var workplace = $.trim($('#workplace').val());
      		var describe = $.trim($('#describe').val());
      		var pwd = $('#password').val();
      		var pwdcf = $('#passwordconfirm').val();
      		var regexEmail = /\b[A-Z0-9._%+-]+@(?:[A-Z0-9-]+\.)+[A-Z]{2,6}\b/i;
      		var regexName = /[a-zA-Z0-9.-_@]/
      		var regexDate=/\b([0-9]{4}[-|/](0[1-9]|1[0-2])[-|/]([0-2]{1}[0-9]{1}|3[0-1]{1})\b|\b([0-2]{1}[0-9]{1}|3[0-1]{1})[-|/](0[1-9]|1[0-2])[-|/][0-9]{4})\b/;
      		
      		if(name === ""|| name == null){
      			flag = false;
      			document.getElementById("nameerror").innerHTML='&nbsp;&nbsp;&nbsp; tên không được để trống';
      		}
      		else{
      			if(name.length<6 || name.length>30){
      				flag = false;
      				document.getElementById("nameerror").innerHTML='&nbsp;&nbsp;&nbsp; tên quá ngắn, hoặc quá dài (độ dài từ 6 đến 30 kí tự)';
      			}else{
      				if(!name.match(regexName)){
          				flag = false;
          				document.getElementById("nameerror").innerHTML='&nbsp;&nbsp;&nbsp; tên chỉ chứa ký tự chữ cái, số, dấu . - _ @';
          			}
      				else{
      					document.getElementById("nameerror").innerHTML='';
      				}
      			}
      		}
      		
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
      			flag = false;
      			document.getElementById("emailerror").innerHTML='&nbsp;&nbsp;&nbsp; email không được để trống';
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
      			document.getElementById("descerror").innerHTML='&nbsp;&nbsp;&nbsp; giới thiệu quá dài, tối đa khoảng 500 kí tự';
      		}
      		else{document.getElementById("descerror").innerHTML='';}
      		
      		if(pwd === ""|| pwd == null){
      			flag = false;
      			document.getElementById("pwderror").innerHTML='&nbsp;&nbsp;&nbsp; password không được để trống';
      		}else{
      			document.getElementById("pwderror").innerHTML='';
      		}
      		
      		if(pwdcf === "" || pwdcf== null){
      			flag = false;
      			document.getElementById("pwdcferror").innerHTML='&nbsp;&nbsp;&nbsp; bạn chưa nhập lại password';
      		}else{
      			if(pwdcf !== pwd){
      				flag = false;
      				document.getElementById("pwdcferror").innerHTML='&nbsp;&nbsp;&nbsp; Nhập lại password không khớp';
      			}
      			else{document.getElementById("pwdcferror").innerHTML='';}
      		}
      		
      		if(dob != null && dob !=="" ){
      			if(!dob.match(regexDate)){
          			flag = false;
          			document.getElementById("doberror").innerHTML='&nbsp;&nbsp;&nbsp; có vẻ như bạn đã làm gì đó khiến ngày sinh không đúng định dạng?!';
          		}
          		else{
          			var age = _calculateAge(new Date(dob));
          			if(age < 12){
          				flag = false;
          				document.getElementById("doberror").innerHTML='&nbsp;&nbsp;&nbsp; bạn phải từ 12 tuổi trở lên mới được đăng ký';
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

      </script>

                    <div class="col-md-6">
                        <div class="box">

                            <p class="lead">Bạn đã có một tài khoản?</p>
                            <p class="text-muted">Click vào <a href="login.html">đây</a> hoặc nút bên dưới để đến <a href="login.html">trang đăng nhập</a>...</p>
							
                            <hr>
                                
                            <div class="text-center">
                                <a href="login.html" class="btn btn-template-main"><i class="fa fa-sign-in"></i>Sign in</a>
                            </div>
                           
                        </div>
                    </div>

                </div>
                <!-- /.row -->

            </div>
            <!-- /.container -->
        </div>
        <!-- /#content -->