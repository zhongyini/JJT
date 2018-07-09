<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html lang="zh-CN">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="renderer" content="webkit">
<meta name="author" content="Comma.Team" />
<meta name="copyright" content="Comma.Team" />
<meta name="keywords" content="咖墨 团队" />
<meta name="description" content="Comma.Team" />
<meta
	content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no"
	name="viewport">
<title></title>
<script type="text/javascript">
function myurl() {
	window.location.href=document.getElementById("url").value+"?openId="+document.getElementById("openId").value;
}
</script>
</head>
<body onload="myurl()">
	<div>
		<input id="url" hidden="hidden" value="${url}"> <input id="openId" hidden="hidden"
			value="${openId}">
	</div>


</body>

</html>
