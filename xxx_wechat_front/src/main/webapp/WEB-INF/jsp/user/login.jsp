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
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

    <link rel="stylesheet" href="${ctx}/static/css/xxx_common.css">
	<script src="${ctx}/static/js/jquery-3.3.1.min.js"></script>
	<script src="${ctx}/static/js/alert.js"></script>
	<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
	<script src="${ctx}/static/js/jssdk-config.js"></script>
	<script src="${ctx}/static/js/countDown.js"></script>
	
    <style type="text/css">
        .content_input_title{
            font-size: 1.2rem;
        }
        .content_input_prompt{
            font-size: 0.8rem;
            color: #726E6B;
            margin-top: 0.3rem;
        }
        .content_code_btn1{
            border-bottom-right-radius: 0;
            border-top-right-radius: 0;
            float: left;
        }
        .content_code_btn2{
            border-bottom-left-radius: 0;
            border-top-left-radius: 0;
            font-size: 0.8rem;
            color: #70B6E5;
            border-left: 1px solid #eeeeee;
            float: left;
        }
        .receive_content_img{
            width: 100%;
        }
        .content_input_div{
            height: 2.8rem;
            margin-left: 5%;
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

    <div class="content_div">
		<div>
            <div class="content_div_logol_1"></div>
	            <div class="content_logo" >
	                <img src="${ctx}/static/img/logo.png">
	            </div>
            <div class="content_div_logol_2"></div>
        </div>
		<input hidden id="openid" value="${result.openid}">
		<input hidden id="recOpenid" value="${result.recOpenid}">
		<input hidden id="cardId" value="${result.cardId}">
		<input hidden id="oldPhoneNumber" value="${result.phone}">
		<input hidden id="oldVerifyCode" value="${result.verifyCode}">
        <!--每个页面的不同点 start-->
        <div class="content_input">
            <p class="content_input_title"><nobr><strong>凭券免费体检一次</strong></nobr></p>
            <p class="content_input_prompt">已有${result.codeNum + 200}人领取</p>
            <br>
            <input type="number" id="phoneNumber" class="content_input_text input_90" value="${result.phone}" placeholder="请输入手机号码"/>
            <br><br/>
            <div>

            </div>
            <div id="verifyCodeDiv" class="content_input_div">
                <input type="number" id="verifyCode" class="content_input_text input_60 content_code_btn1" placeholder="请输入验证码">
                <input type="button" id="verifyButton" class="content_input_button_white input_30 content_code_btn2" value="获取短信校验码">

            </div>
            <br>
			<div>
            <button type="button" id="addCard" class="content_input_button input_90" value="领取免费体验券">领取免费体验券</button>
            </div>
        </div>
        <div>
            <img class="receive_content_img" src="${ctx}/static/img/receive_1.png">
            <img class="receive_content_img" src="${ctx}/static/img/receive_2.jpg">
            <img class="receive_content_img" src="${ctx}/static/img/receive_3.jpg">
            <img class="receive_content_img" src="${ctx}/static/img/receive_4.jpg">
        </div>
        <!--每个页面的不同点 end-->

        <div class="content_foot">
            <div>
                <hr class="content_foot_title_left"/>
                <hr class="content_foot_title_right"/>
                <p class="content_foot_title">活动规则</p>
            </div>
            <br/>
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
    <p>· 九间堂新中医 ·</p>
</div>
<script type="text/javascript">
	document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
	    // 通过下面这个API隐藏底部导航栏
	    WeixinJSBridge.call('hideToolbar');
	});
	// 进入画面时判断是否有手机号
	var oldPhoneNumber = $("#oldPhoneNumber").val();
	if (oldPhoneNumber != "" && oldPhoneNumber.length==11) {
		$("#verifyCodeDiv").addClass("hidden");
	}
	//设置倒计时
	var countdown = 120;
	function settime(obj) {
	   if (countdown == 0) {
		obj.disabled=false; 
	   	obj.value = "获取短信校验码";
	    countdown = 120;
	    count = 0;
	    return;
	   } else {
		obj.disabled=true;
		//alert(obj.disabled);
		obj.value = "" + countdown + "秒后再获取";
	    countdown--;
	   }
	   setTimeout(function () {
	       settime(obj)
	   }, 1000);
	}
	
	// 验证手机号
	function verifyPhone(phoneNumber) {
		//手机号码格式验证
		var partten=/^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/;
		if(!partten.test(phoneNumber) || phoneNumber.length != 11){
			// 弹出框式样
			showToast({
			   title:"<br>请输入11位手机号",
			   icon:'none'
			});
			return null;
		}
		return "verify ok";
	}
	// 随机验证码
	var randomNum = "";
	// 获取短信校验码按钮点击事件
	$("#verifyButton").click(function(){
		// 验证手机号
		if (!verifyPhone($("#phoneNumber").val())){
			return;
		}
		// 随机数
		var random="";
		for(var i=0;i<4;i++){
			random+=Math.floor(Math.random()*10);
		}
		// 随机数 -> 随机验证码
		randomNum = random;
		$.ajax({
			url : "../user/verifyCode",
			type : "post",
			data : {"openid":$("#openid").val(),"phoneNumber":$("#phoneNumber").val(),"randomNum":randomNum},
			success : function(result) {
				// 获取成功
				if (result.status == 0) {
					randomNum = result.result;
					// 倒计时
					settime(document.getElementById("verifyButton"));
					// 弹出提示框
					showToast({
					   title:"短信发送成功",
					   icon:'success',
					   duration:1000
					});
				} else if (result.message == "触发天级流控") {
					// 获取失败，弹出提示框
					showModal({
                           title:"九间堂新中医",
                           content:"短信发送已超限额，请稍后再试。",
                           showCancel:false,
                           confirmText:'确定',
                           confirmColor:'#ffffff',
                           success:function (res) {
                              
                           }
                       });
				} else {
					// 获取失败，弹出提示框
					showModal({
                        title:"九间堂新中医",
                        content:result.message,
                        showCancel:false,
                        confirmText:'确定',
                    });
				}
			},
			error : function(res) {
				console.log(res);
				// 获取失败，弹出提示框
				showToast({
				   title:"<br>失败，请稍后再试",
				   icon:'none',
				   duration:1000
				});
			}
		});
	});
	
	// 监听手机号改变事件
	$("#phoneNumber").bind('input propertychange', function() {
		// 手机号没改变
		if ($("#oldPhoneNumber").val() != ""
			&& $("#phoneNumber").val() != "" 
			&& $("#oldPhoneNumber").val() == $("#phoneNumber").val()) {
			// 隐藏验证码行
			$("#verifyCodeDiv").addClass("hidden");
		} else {
			// 显示验证码行
			$("#verifyCodeDiv").removeClass("hidden");
		}
	});
	// 添加卡券
	$("#addCard").click(function(){
		// 用户openid不存在
		if (!$("#openid").val()) {
			// 弹出提示框
			showToast({
			   title:"<br>请刷新重试",
			   icon:'none'
			});
			return;
		}
		
		// 是否验证
		var ifVerify = "yes";
		// 手机号没改变
		if ($("#verifyCodeDiv").hasClass("hidden")) {
			ifVerify = "no";
		} else {
			ifVerify = "yes";
			// 验证手机号
			if (!verifyPhone($("#phoneNumber").val())) {
				return;
			}
			// 验证验证码
			if (!$("#verifyCode").val()) {
				showToast({
				   title:"<br>验证码有误",
				   icon:'none'
				});
				return;
			}
		}
		/* showLoading({
		   title:"Waiting",
		   mask:true
		}); */
		$.ajax({
        	url: "../user/doLogin",
        	type: "post",
        	data: {"openid":$("#openid").val(),"phoneNumber":$("#phoneNumber").val(),"verifyCode":$("#verifyCode").val(),"randomNum":randomNum,"ifVerify":ifVerify},
        	async: false,
        	success : function(result) {
        		// 用户信息更新成功
        		if (result.status==0) {
        			// 重置数据
            		$("#verifyCode").val("");
        			randomNum = "";
            		addCard();
        		} else {
        			// 重置数据
            		$("#verifyCode").val("");
        			randomNum = "";
        			showToast({
				   		title:result.message,
				   		icon:'none',
				   		duration:1000
					});
        		}
        	},
			error : function(res) {
				// 重置数据
        		$("#verifyCode").val("");
    			randomNum = "";
				showToast({
				   title:"Error",
				   icon:'none'
				});
			}
        });
		
	});

</script>
</body>
</html>