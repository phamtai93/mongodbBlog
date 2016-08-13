<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <div id="content">
            <div class="container">

                <div class="row">
                    <div class="col-md-6">
                        <div class="box">
                            <h2 class="text-uppercase">ACTIVATING SUCCESS :))</h2>

                            <p class="lead">Kích hoạt tài khoản thành công !!</p>
                            <p style="font-size: 16px"><c:out value="${name}"/>,&nbsp;<c:out value="${message}"></c:out>
                            <hr>

                        </div>
                    </div>


                    <div class="col-md-6">
                        <div class="box">
					<c:choose>
						<c:when test="${not empty userName}">
							<p class="lead">bạn đã đăng nhập với username là : <a href="user-info.html?n=${userName}"><c:out value="${userName}"></c:out></a> </p>						
                            <hr>         
						</c:when>
						<c:otherwise>
							<p class="lead">bạn có muốn đăng nhập ngay không?</p>
                            <p class="text-muted">Click vào <a href="login.html">đây</a> hoặc nút bên dưới để đến <a href="login.html">trang đăng nhập</a>...</p>							
                            <hr>         
                            <div class="text-center">
                                <a href="login.html" class="btn btn-template-main"><i class="fa fa-sign-in"></i>Sign in</a>
                            </div>
						</c:otherwise>
					</c:choose>
     
                        </div>
                    </div>

                </div>
                <!-- /.row -->

            </div>
            <!-- /.container -->
        </div>
        <!-- /#content -->