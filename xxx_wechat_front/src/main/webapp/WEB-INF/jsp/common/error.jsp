<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!-- 正常使用时的用法 -->
<%-- <c:set var="ctx" value="${pageContext.request.contextPath}" /> --%>
<c:set var="ctx" value="https://www.ykstudy.cn" />
<!DOCTYPE html>
<html lang="en">
<head>
<!--[if lt IE 9]>
<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
<title>Waiting</title>
<link rel="shortcut icon" href="${ctx}/static/favicon.ico" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${ctx}/static/js/jquery-1.9.1.min.js"></script>
<script src="${ctx}/static/js/preloader.js"></script> 
<link rel="stylesheet" href="${ctx}/static/css/style.css">
<link href='http://fonts.googleapis.com/css?family=Finger+Paint' rel='stylesheet' type='text/css'>
<script type="text/javascript" src="${ctx}/static/js/css_browser_selector.js"></script>
 <script type="text/javascript" src="${ctx}/static/js/plax.js"></script>
<script type="text/javascript" src="${ctx}/static/js/jquery.spritely-0.6.1.js"></script>
<script type="text/javascript" src="${ctx}/static/js/jquery-animate-css-rotate-scale.js"></script>
<script type="text/javascript" src="${ctx}/static/js/script.js"></script>
</head>
<body >
<div id="indicator"></div>
<div class="wrapper">
	<div class="sky init">
	<div id="clouds" class="clouds init"> </div>
	</div>
	
	<div class="convas init">
		<div id="mountain" class="mountain"></div>
		<div class="ground"></div>
		<div class="holder">
		<div class="rocks"></div>
		<div class="work-sign"></div>
		<div class="text-sign">
			<div class="text font">紧急维护中。。。</div>
		</div>
		<div class="init hole">
			<div class="sweat"></div>
			<div class="worker swing "></div>
			<div class="ground-bottom">
				<div class="cleaner"></div>
			</div>
		</div>
		</div>
	</div>
</div>
</body>
</html>


