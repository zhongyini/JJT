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
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="format-detection" content="telepgone=yes">
<link rel="stylesheet" href="${ctx}/static/css/xxx_common.css">
<script src="${ctx}/static/js/jquery-3.3.1.min.js"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<script src="${ctx}/static/js/jssdk-config.js"></script>
<script src="${ctx}/static/js/alert.js"></script>
<script src="${ctx}/static/js/countDown.js"></script>
<style type="text/css">
.content_input_title {
	font-size: 1.2rem;
}

.content_input_prompt {
	font-size: 0.8rem;
	color: #726E6B;
	margin-top: 0.3rem;
	margin-bottom: 0.8rem;
}

.content_input_use_title {
	text-align: left;
	margin-left: 5%;
	margin-top: 5%;
	margin-bottom: 5%;
	color: #A8A8A8;
}

.content_input_volumen_div {
	overflow:hidden;
	display:inline-block;
	float:left;
	height: 5rem;
	text-align: center;
}

.content_input_volumen_div p {
	margin-top:1rem;
	font-size:0.8rem;
	text-align: left;
	color: #A8A8A8;
	margin-left: 5%;
}

.content_input_volumen_div_vertical {
	width: 1rem;
	height: 1rem;
	vertical-align: middle;
	margin: auto;
	left: 50%;
	/*top: 50%;*/
	margin-top: 0.4rem;
	/*margin-left: 0.5rem;*/
}

.content_input_volumen_contain {
	overflow: hidden;
	text-align: left;
	height: 5rem;
	margin-left: 5%;
	font-size:0;
}

.content_input_copy_img {
	width: 1rem;
	margin-left: 0.5rem;
	overflow: hidden;
}

.content_input_receive_img {
	float: right;
	width: 4rem;
	margin-top: -0.8rem;
	margin-right: -0.6rem;
}

.red_content_list {
	height: 3rem;
}

.red_content_list_table {
	border-collapse: collapse;
	margin-left: 1rem;
	margin-right: 1rem;
	margin-bottom: -1px;
	overflow: hidden;
}

.red_content_list_table, .red_content_list, .red_content_list td {
	color: #A8A8A8;
	border-bottom: 1px solid #eeeeee;
}

.red_table_div {
	margin-bottom: -1px;
	overflow: hidden;
	border-top: 1px dashed #eeeeee;
}

.red_div_content {
	border-radius: 0.4rem;
	width: 90%;
	background-color: white;
	margin-left: 5%;
	margin-right: 5%;
	padding-bottom: 0.3rem;
}

.red_table_img {
	width: 2rem;
	margin-left: 0.5rem;
}

.red_table_td1 {
	min-width: 3.5rem;
	width: 3rem;
	text-align: left;
}

.red_table_td2 {
	width: 100%;
	text-align: left;
}

.red_table_td3 {
	min-width: 4rem;
	width: 4rem;
	text-align: right;
}

.red_button_more {
	font-size: 0.8rem;
	margin-top: 0.5rem;
	margin-bottom: 0.7rem;
	color: #aaaaaa;
}

.red_div_title {
	color: #888888;
}

.page-div {
	width: 90%;
	margin: auto;
	margin-top: 1px;
}

.page-prev {
	width: 30%;
	float: left;
}

.page-value {
	width: 10%;
	border: none;
	outline: none;
	float: center;
}

.page-next {
	width: 30%;
	float: right;
}

</style>

</head>
<body>
<!--head-->
<div class="head">
	<img class="title_img" src="${ctx}/static/img/title.png">
</div>

<!--content-->
<div class="content">
	<input id="openid" hidden value="${result.openid}">
	<input id="cardId" hidden value="${result.cardId}">
	<input id="code" hidden value="${result.code}">
	<input id="cardList" hidden value="${result.cardList}">
	<input id="total" hidden value="${result.total}">
	<input id="brotherList" hidden value="${result.brotherList}">
	<div class="content_div">
        <div>
            <div class="content_div_logol_1"></div>
            <div class="content_logo" >
                <img src="${ctx}/static/img/logo.png">
            </div>
            <div class="content_div_logol_2"></div>
        </div>
		<!--每个页面的不同点 start-->
		<div class="content_input">
			<p class="content_input_title">
				<strong><nobr>凭券免费体检一次</nobr></strong>
			</p>
			<div class="content_input_volumen_contain">
				<div class="content_input_button input_10 content_input_volumen_div">
					<div class="content_input_volumen_div_vertical">体验券</div>
				</div>
				<div class="content_input_button_white input_80 content_input_volumen_div">
					<img class="content_input_receive_img" src="${ctx}/static/img/ico_1.png">
					<p style="margin-top: 1rem">生命能量评估体检</p>
					<p style="margin-top: 1rem;" ><nobr>体验券已收入账户：<span>${result.phone}</span></nobr></p>
				</div>
			</div><br>
			<div>
				<input type="button" class="content_input_button_white input_90" onclick="openCard();" value="立即使用" /><br /><br>
				<input type="button" class="content_input_button input_90" onclick="goShare();" value="邀请好友，领取现金红包" />
			</div>
			<c:choose>
				<c:when test="${result.total != '0'}">
					<p class="red_div_title" style="margin-top:0.5rem;">Ta们也领了这张体验券哦</p>
					<div class="red_div_content"><div class="red_table_div">
						<table class="red_content_list_table">
							<c:forEach var="item" items="${result.brotherList}">
								<tr class="red_content_list">
									<td class="red_table_td1"><img src="${item.headimgurl}" class="red_table_img"></td>
									<td class="red_table_td2"><span style="color:#444444;">${item.nickname}</span><br>${fn:substring(item.createDate,0,19)}</td>
									<td class="red_table_td3">${item.money}</td>
								</tr>
							</c:forEach>
						</table>
					</div>
					<p class="red_button_more look_more">查看更多</p>
				</c:when>
				<c:otherwise>
					
				</c:otherwise>
			</c:choose>	
		</div>

		<!--每个页面的不同点 end-->
		<div class="content_foot">
			<div>
				<hr class="content_foot_title_left" />
				<hr class="content_foot_title_right" />
				<p class="content_foot_title">活动规则</p>
			</div>
			<br />
			<div class="content_foot_content">
                <p>1、若好友通过您分享的链接领取体验券，完成体验后，您可获得10元现金红包</p>
				<p>2、若同一人有2个手机号，只能使用1张体验券</p>
				<p>3、使用请提前两天预约，预约微信号zhengdaxxx，或拨打预约电话<a class="callPhone" href="tel:400-697-0790">400-697-0790</a>，我们会尽快联系您</p>
				<p>4、此券不能与其他优惠同时使用，不找零、不折现</p>
				<p>5、活动有效期：2018.06.20-2018.07.31；逾期参与则无法继续使用体验券</p>
				<p>6、领券参与体验产生的差旅费请自行解决</p>
				<p>7、如有其他疑问，请电话<a class="callPhone" href="tel:400-697-0790">400-697-0790</a>或者添加客服微信号zhengdaxxx 联系我们</p>
				<p>8、在法律法规允许的范围内，上海九间堂中医门诊部有限公司对本活动拥有最终解释权</p>
                <br/>
            </div>
		</div>
	</div>
</div>
<div class="foot">
	<p>·九间堂新中医·</p>
</div>
<script>
document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
    // 通过下面这个API隐藏底部导航栏
    WeixinJSBridge.call('hideToolbar');
});
	var pageNum = 1;
	var pageSize = 5;
	if ($("#total").val() < pageSize) {
		$(".look_more").addClass("hidden");
	}
	var limit = $(".page-value").val();
	$(".page-prev").click(function() {
		if ($(".page-value").val() == 1) {
			return;
		}

	});
	$(".look_more").click(function() {
		pageNum += 1;
		newPage(pageNum,pageSize);
	});

	function newPage(pageNum,pageSize) {
		$.ajax({
			url : "../card/page?openid="+$("#openid").val()+"&pageNum="+pageNum+"&pageSize="+pageSize,
			type : "get",
			async : true,
			dataType : "json",
			success : function(result) {
				if (!result) {
					return;
				}
				if (result.result.list) {
					if (result.result.list.length < pageSize) {
						$(".look_more").addClass("hidden");
					}
					for (var index in result.result.list) {
						var addAppend = "<tr class='red_content_list'><td class='red_table_td1'><img src='"+result.result.list[index].headimgurl+"' class='red_table_img'></td><td class='red_table_td2'><span style='color:#444444;'>"+result.result.list[index].nickname+"</span><br>"+result.result.list[index].createDate.substring(0,19)+"</td><td class='red_table_td3'>"+result.result.list[index].money+"</td></tr>";
						$(".red_content_list_table").append(addAppend);
					}
				}
			},
			error : function(res) {
				console.log(res);
			}
		});
	}
	function goShare() {
		window.location.href = "../share/view";
	};
</script>
</body>
</html>