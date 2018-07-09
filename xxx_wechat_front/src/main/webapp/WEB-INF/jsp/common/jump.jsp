<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html lang="zh-CN">

<head>
<meta charset="utf-8">
<meta
	content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no"
	name="viewport">
<title></title>
<script type="text/javascript">
function myurl() {
	window.location.href=document.getElementById("url").value;
}
</script>
</head>
<body onload="myurl()">
	<div>
		<input id="url" hidden="hidden" value="${url}">
		<input id="openid" hidden="hidden" value="${openid}">
	</div>
</body>
</html>
