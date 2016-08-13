<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta charset="UTF-8">
    <meta name="robots" content="all,follow">
    <meta name="googlebot" content="index,follow,snippet,archive">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="keywords" content="">
	<meta name="_csrf" content="${_csrf.token}"/>
	<meta name="_csrf_header" content="${_csrf.headerName}"/>
    <link href='http://fonts.googleapis.com/css?family=Roboto:400,100,100italic,300,300italic,500,700,800' rel='stylesheet' type='text/css'>
    <!-- Bootstrap and Font Awesome css -->
    <link rel="stylesheet" href="<c:url value="/resources/front/css/font-awesome.min.css"/>" >
    <link rel="stylesheet" href="<c:url value="/resources/front/css/bootstrap.min.css"/>" >
    <!-- Css animations  -->
    <link rel="stylesheet" href="<c:url value="/resources/front/css/animate.css"/>"  >
    <!-- Theme stylesheet, if possible do not edit this stylesheet -->
    <link rel="stylesheet" id="theme-stylesheet" href="<c:url value="/resources/front/css/style.default.css" />" >
    <!-- Custom stylesheet - for your changes -->
    <link rel="stylesheet" href="<c:url value="/resources/front/css/custom.css"/> " >

    <!-- Favicon and apple touch icons-->
    <link rel="shortcut icon" href="<c:url value="/resources/front/img/favicon.ico"/>" type="image/x-icon"  />
    <link rel="apple-touch-icon" href="<c:url value="/resources/front/img/apple-touch-icon.png" />" />
    <link rel="apple-touch-icon" sizes="57x57" href="<c:url value="/resources/front/img/apple-touch-icon-57x57.png" /> " />
    <link rel="apple-touch-icon" sizes="72x72" href="<c:url value="/resources/front/img/apple-touch-icon-72x72.png" /> " />
    <link rel="apple-touch-icon" sizes="76x76" href="<c:url value="/resources/front/img/apple-touch-icon-76x76.png" /> " />
    <link rel="apple-touch-icon" sizes="114x114" href="<c:url value="/resources/front/img/apple-touch-icon-114x114.png" /> " />
    <link rel="apple-touch-icon" sizes="120x120" href="<c:url value="/resources/front/img/apple-touch-icon-120x120.png" /> " />
    <link rel="apple-touch-icon" sizes="144x144" href="<c:url value="/resources/front/img/apple-touch-icon-144x144.png" /> " />
    <link rel="apple-touch-icon" sizes="152x152" href="<c:url value="/resources/front/img/apple-touch-icon-152x152.png" /> " />
	<title><tiles:getAsString name="title" /></title>
	
	<tiles:importAttribute name="javascript"/>
	<c:forEach var="script" items="${javascript}">
		<script type="text/javascript" src="<c:url value="${script}" />" ></script>
	</c:forEach>
	<script>
		window.jQuery || document.write('<script src="<c:url value="/resources/front/js/jquery-1.11.0.min.js"/>"><\/script>')
	</script>
</head>
 
<body>
<div id="all">
	<tiles:insertAttribute name="header" />
	<tiles:insertAttribute name="menu" />
	        <div id="content">
            <div class="container">
                <div class="row">
	<tiles:insertAttribute name="body" />
	<tiles:insertAttribute name="side-bar" />
	                </div><!-- /.row -->
            </div><!-- /.container -->
        </div><!-- /#content -->
	<tiles:insertAttribute name="pre-footer"/>
	<tiles:insertAttribute name="footer" />
</div>

</body>
</html>