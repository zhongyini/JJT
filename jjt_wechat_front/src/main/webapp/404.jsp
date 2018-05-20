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
<title>出错啦</title>
<!-- External CSS -->
<link rel="stylesheet" href="${ctx}/static/css/app.css">
<!-- In-document CSS -->
<style>
/* ... */
</style>
<!-- JavaScript -->
<script src="${ctx}/static/js/comma.js"></script>
<script src="${ctx}/static/js/app.js"></script>
</head>
<body ontouchstart="">
	<div class="page">
		<div class="content-padded">
			<div class="panel panel-qiaohu panel-qiaohu-y panel-qiaohu-error">
				<div class="panel-content">
					<div class="panel-title ">
						<h2>出错啦</h2>
					</div>
					<div class="panel-body">
						<div class="error-fork">
							<img src="${ctx}/static/img/error_fork.png" alt="" />
						</div>
						<h3>出错啦，请再试一次吧。</h3>
						<a href="javascript:gotoLogin()"
							class="weui-btn weui-btn_warn error-btn">点击再次尝试</a>
						<div class="error-x">
							<img src="${ctx}/static/img/youhuiquan-n.png" alt="" />
						</div>
					</div>
				</div>
			</div>
			<!--panel-qiaohu end-->
			<div class="footer-logo">
				<img src="${ctx}/static/img/logo.png" width="107" height="41" alt="">
			</div>

		</div>
	</div>

	<!--content page end-->
	<script>
		//如果视觉稿尺寸是640px*1008px，页面样式是以视觉稿尺寸除以2来计算，那么输入页面的宽度为320px和高度为504px
		window.onload = window.onresize = function() {
			pageResponse({
				selectors : '.page', //模块选择器，使用querySelectorAll的方法
				mode : 'cover', // auto || contain || cover 
				width : '320', //输入页面的宽度，只支持输入数值，默认宽度为320px
				height : '514' //输入页面的高度，只支持输入数值，默认高度为504px
			})
			myScrollLoaded();
			document.addEventListener('touchmove', function(e) {
				//e.preventDefault();
			}, isPassive() ? {
				capture : false,
				passive : false
			} : false);
		}
		function gotoLogin() {
			window.location = "http://" + window.location.host
					+ "/user/oauthLogin";
		}
	</script>

</body>

</html>
