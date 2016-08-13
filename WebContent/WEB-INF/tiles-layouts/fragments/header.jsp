<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
        <header>

            <!-- *** TOP ***
_________________________________________________________ -->
            <div id="top">
                <div class="container">
                    <div class="row">
                        <div class="col-xs-5 contact">
                            <p class="hidden-sm hidden-xs">Contact via phamvantaiqn93@gmail.com</p>
                            <p class="hidden-md hidden-lg"><a href="#" data-animate-hover="pulse"><i class="fa fa-phone"></i></a>  <a href="#" data-animate-hover="pulse"><i class="fa fa-envelope"></i></a>
                            </p>
                        </div>
                        <div class="col-xs-7">
                            <div class="social">
                                <a href="#" class="external facebook" data-animate-hover="pulse"><i class="fa fa-facebook"></i></a>
                                <a href="#" class="external gplus" data-animate-hover="pulse"><i class="fa fa-google-plus"></i></a>
                                <a href="#" class="external twitter" data-animate-hover="pulse"><i class="fa fa-twitter"></i></a>
                                <a href="#" class="email" data-animate-hover="pulse"><i class="fa fa-envelope"></i></a>
                            </div>
                <sec:authorize access="isAuthenticated()" var="loggedIn"></sec:authorize>  
				<c:choose>
					<c:when test="${(not empty userName) && (loggedIn)}">
							<div class="login">
								<a href="${pageContext.request.contextPath}/authUser/user-info.html"><i>hi!&nbsp</i>${userName}</a>
								<a href="${pageContext.request.contextPath}/logout.html"><i class="fa fa-user"></i> <span class="hidden-xs text-uppercase">Log out</span></a>
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
							</div>
					</c:when>
					<c:otherwise>
							<div class="login">
	                            <a href="${pageContext.request.contextPath}/login.html" ><!--  data-toggle="modal" data-target="#login-modal"--><i class="fa fa-sign-in"></i> <span class="hidden-xs text-uppercase">Sign in</span></a>
	                            <a href="${pageContext.request.contextPath}/signup.html"><i class="fa fa-user"></i> <span class="hidden-xs text-uppercase">Sign up</span></a>
                            </div>
					</c:otherwise>
				</c:choose>
                          

                        </div>
                    </div>
                </div>
            </div>

            <!-- *** TOP END *** -->

            <!-- *** NAVBAR ***
    _________________________________________________________ -->

            <div class="navbar-affixed-top" data-spy="affix" data-offset-top="200">

                <div class="navbar navbar-default yamm" role="navigation" id="navbar">

                    <div class="container">
                        <div class="navbar-header">

                            <a class="navbar-brand home" href="${pageContext.request.contextPath}/index.html">
                                <img src="<c:url value="/resources/front/img/logo.png"/> " alt="Universal logo" class="hidden-xs hidden-sm">
                                <img src="<c:url value="/resources/front/img/logo-small.png"/>" alt="Universal logo" class="visible-xs visible-sm"><span class="sr-only">Universal - go to homepage</span>
                            </a>
                            <div class="navbar-buttons">
                                <button type="button" class="navbar-toggle btn-template-main" data-toggle="collapse" data-target="#navigation">
                                    <span class="sr-only">Toggle navigation</span>
                                    <i class="fa fa-align-justify"></i>
                                </button>
                            </div>
                        </div>
                        <!--/.navbar-header -->

                        <div class="navbar-collapse collapse" id="navigation">

                            <ul class="nav navbar-nav navbar-right">
                                <li class=""><!-- dropdown active -->
                                    <a href="${pageContext.request.contextPath}/home.html" class="">Home </a>
                                    <!-- <ul class="dropdown-menu">
                                        <li><a href="index.html">Option 1: Default Page</a>
                                        </li>
                                        <li><a href="index2.html">Option 2: Application</a>
                                        </li>
                                        <li><a href="index3.html">Option 3: Startup</a>
                                        </li>
                                        <li><a href="index4.html">Option 4: Agency</a>
                                        </li>
                                        <li><a href="index5.html">Option 5: Portfolio</a>
                                        </li>
                                    </ul> -->
                                </li>
                                <li class="dropdown">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Chủ đề<b class="caret"></b></a>
                                    <ul class="dropdown-menu">
                                    <c:if test="${not empty topic}">
                                    <c:forEach items="${topic}" var="topicDetail">
                                        <li><a href="/list-of-posts.html?topic=${topicDetail.urlName}" >${topicDetail.name}</a>
                                        </li>
                                    </c:forEach>
                                    </c:if>
                                    </ul>
                                    
                                </li>
                            <c:choose>
                            	<c:when test="${(not empty userName) && (loggedIn)}">
                            	<li class="dropdown use-yamm  ">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Tài Khoản <b class="caret"></b></a>
                                    <ul class="dropdown-menu  col-sm-5">
                                        <li>
                                            <div class="yamm-content">
                                                <div class="row">
                                                    <div class="col-sm-5">
                                                        <img src="<c:url value="/${avatar}" /> " class="img-responsive hidden-xs" alt="">
                                                    </div>
                                                    
                                                    <div class="col-sm-7">
                                                        <ul>
                                                            <li><a href="${pageContext.request.contextPath}/logout.html">Đăng xuất</a>
                                                            </li>
                                                            <li><a href="${pageContext.request.contextPath}/authUser/user-info.html">Thông tin cá nhân</a>
                                                            </li>
                                                            <li><a href="${pageContext.request.contextPath}/authUser/notification.html">Thông báo</a>
                                                            </li>
                                                        </ul>
                                                        
                                                    </div>
                                                </div>
                                            </div>
                                        </li>
                                    </ul>
                                </li>	
                            	</c:when>
                            	<c:otherwise>
                            	<li class="">
                                    <a href="${pageContext.request.contextPath}/login.html" class="" >Tài Khoản </a>
                                </li>
                            	</c:otherwise>
                            </c:choose>

                                <li class="">
                                    <a href="#" class="" >Liên Hệ</a>
                                </li>
                                <li class="">
                                    <a href="${pageContext.request.contextPath}/home.html" class="">About Us </a>
                                </li>
                            </ul>

                        </div>
                        <!--/.nav-collapse -->



                        <div class="collapse clearfix" id="search">

                            <form class="navbar-form" role="search">
                                <div class="input-group">
                                    <input type="text" class="form-control" placeholder="Search">
                                    <span class="input-group-btn">

                    					<button type="submit" class="btn btn-template-main"><i class="fa fa-search"></i></button>

                					</span>
                                </div>
                            </form>

                        </div>
                        <!--/.nav-collapse -->

                    </div>


                </div>
                <!-- /#navbar -->

            </div>

            <!-- *** NAVBAR END *** -->

        </header>
        
        
        <!-- *** LOGIN MODAL *** --> <!-- *** LOGIN MODAL END *** -->

        <div id="heading-breadcrumbs">
            <div class="container">
                <div class="row">
                    <div class="col-md-6">
                        <h1>Blog - coding blog</h1>
                    </div>
                    <div class="col-md-6">
                        <ul class="breadcrumb">
                            <li><a href="${pageContext.request.contextPath}/home.html">Home</a> </li>
                            <c:if test="${not empty breadcrumb}">
                            <c:forEach items="${breadcrumb}" var="item">
                            	<li><a href="${item.value}"><c:out value="${item.key}"></c:out> </a></li>
                            </c:forEach>
                            </c:if>
                        </ul>

                    </div>
                </div>
            </div>
        </div>
        