<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>九间堂新中医</title>
    <link rel="stylesheet" href="${ctx}/static/css/xxx_common.css">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <script src="${ctx}/static/js/jquery-3.3.1.min.js"></script>
    <script src="${ctx}/static/js/qrcode.min.js"></script>
    <script src="${ctx}/static/js/alert.js"></script>
	<style type="text/css">
		#qrcode {
			margin-top: 5%;
			width:100%;
			text-align: center;
		}
		
		.sharediv {
			width: 100&;
			text-align: center;
		}
		.share {
			width: 256px;
			-webkit-appearance : none ;
		    color: #BD9146;
		    background-color: white;
		    border: none;
		    text-align: center;
		    font-size: 1rem;
		    height: 2.8rem;
		    border: none;
		    border-radius:0.4rem;
		}
		.warning {
			text-align: center;
		}
		h3 {
			text-align: center;
			margin-top:25%;
			font-size:1.2rem;
		}
	</style>
</head>
<body>
<!--content-->
<input hidden id="code" value="${code}">
<input hidden id="message" value="${message}">
<h3><nobr>请在体检时将下方二维码展示给工作人员</nobr></h3>
<div id="qrcode"></div>
<div class="sharediv"><button class="share">前往分享画面</button></div>
<div class="foot">
    <p>·九间堂新中医·</p>
</div>
<script>
	if ($("#message").val() != null && $("#message").val()!="") {
		showModal({
            title:"九间堂新中医",
            content:$("#message").val(),
            showCancel:false,
            confirmText:'确定',
            confirmColor:'#ffffff',
        });
	}
	$(".share").click(function(){
		window.location.href="../share/view";
	});
	// TODO 需要根据环境修改路径
	var url = "http://www.ykstudy.cn/wechat-front/card/newWCard";
	var code = $("#code").val();
	// TODO 需要根据环境修改路径
	var redirectUrl =  "http://www.ykstudy.cn/admin-api/adminManager/checkAdmin?url="+url+"&code="+code;
	var qrcode = new QRCode(document.getElementById("qrcode"),{
		render: "table"});  // 设置要生成二维码的链接
	qrcode.makeCode(redirectUrl);
	//qrcode.makeCode(urlTwo );
</script>
</body>
</html>