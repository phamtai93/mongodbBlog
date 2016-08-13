<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <div id="content">
            <div class="container">

                <div class="row">
                    <div class="col-md-6">
                        <div class="box">
                            <h2 class="text-uppercase">FAIL :((</h2>

                            <p class="lead">Không mở được trang thông tin cá nhân của bạn !!</p>
                            <p style="font-size: 16px"><c:out value="${message}"/></p>
                            
                            <hr>

                        </div>
                    </div>


                    <div class="col-md-6">
                        <div class="box">

                            <p class="lead">bạn có muốn đăng nhập lại?</p>
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