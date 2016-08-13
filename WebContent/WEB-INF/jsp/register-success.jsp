<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <div id="content">
            <div class="container">

                <div class="row">
                    <div class="col-md-6">
                        <div class="box">
                            <h2 class="text-uppercase">SUCCESSFUL :))</h2>

                            <p class="lead">Đăng ký thành công !!</p>
                            <p><c:out value="${object.name}"/>,&nbsp;bạn đã đăng ký thành công. xin cảm ơn bạn đã đăng ký thanh viên của Blog.
                            &nbsp; Bạn hãy check email mà bạn đã sử dụng đã đăng ký &nbsp;<c:out value="${object.email}"/>&nbsp;và làm theo hướng dẫn để kích hoạt tài khoản.
                            <hr>

                        </div>
                    </div>


                    <div class="col-md-6">
                        <div class="box">

                            <p class="lead">bạn có muốn đăng nhập ngay không?</p>
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