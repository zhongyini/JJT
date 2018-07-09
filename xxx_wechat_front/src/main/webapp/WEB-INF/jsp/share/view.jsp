<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>九间堂新中医</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <link rel="stylesheet" href="${ctx}/static/css/xxx_common.css">
    <link rel="stylesheet" href="${ctx}/static/css/lightbox.css">
    <link rel="stylesheet" href="${ctx}/static/css/zoomify.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/loading.css">
    <script src="${ctx}/static/js/jquery-3.3.1.min.js"></script>
    <script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
    <script src="${ctx}/static/js/jssdk-config.js"></script>
    <script src="${ctx}/static/js/zoomify.min.js"></script>
    <script src="${ctx}/static/js/lightbox.js"></script>
    <script src="${ctx}/static/js/qrcode.min.js?a=1"></script>
    <script src="${ctx}/static/js/alert.js"></script>

    <style type="text/css">
        .red_pull_cash {
            margin-top: 0.3rem;
            margin-bottom: 0.3rem;
            font-size: 0;
        }
        .red_pull_cash p {
            margin-top: 0.3rem;
            font-size: 0.9rem;
            color: #999999;
        }
        .red_recode {
            margin-left: 5%;
            padding-bottom: 0.3rem;
        }
        .red_success_count {
            width: 50%;
            float: left;
            border-right: 1px solid #eeeeee;
            overflow: hidden;
            padding-right: -1px;
            margin-right: -1px;
        }
        .red_cash_count {
            overflow: hidden;
            margin-left: -1px;
            width: 50%;
            float: right;
            border-left: 1px solid #eeeeee;
        }

        .content_input_div_white {
            color: #BD9146;
            background-color: white;
            border: none;
            text-align: center;
            font-size: 1rem;
            border-radius: 0.4rem;
        }
        .red_table_td1,
        .red_table_td2,
        .red_table_td3 {
            text-align: left;
            font-size: 0.8rem;
            color: #aaaaaa;
        }
        .red_table_td1 {
            min-width: 3rem;
            width: 3rem;
        }
        .red_table_td2 {
            width: 100%;
        }
        .red_table_td3 {
            min-width: 4rem;
            width: 4rem;
        }
        .red_table_img {
            width: 2rem;
            margin-left: 0.5rem;
        }
        .red_button_more {
            font-size: 0.8rem;
            margin-top: 0.5rem;
            margin-bottom: 0.7rem;
            color: #aaaaaa;
        }
        .red_count_p{
            margin-top: 0.6rem;
            margin-bottom: 0.3rem;
            font-size: 1.1rem;
            color: #aaaaaa;
        }
        .red_count_cnt{
            color: #aaaaaa;
            margin-bottom: 0.6rem;
        }
        .share_ico {
            width: 30rem;
        }
        .share_table{
            margin-left: 5%;
        }
        .share_img{
            width: 4rem;
        }
        .red_count_no{
            font-size: 1.3rem;
            color: #9A2422;
        }
        .red_content_list{
            height: 3rem;
        }
        .red_content_list_table {
            border-collapse: collapse;
            margin-left: 1rem;
            margin-right: 1rem;
            margin-bottom: -1px;
            overflow: hidden;
        }
        .red_content_list_table,
        .red_content_list,
        .red_content_list td {
            border-bottom: 1px solid #eeeeee;
        }
        .red_table_div{
            margin-bottom: -1px;
            overflow: hidden;
            border-top: 1px dashed #eeeeee;
        }
        .cash_button_1{
            margin-left: 3%;
            float: left;
            width: 45%;
        }
        .cash_button_2{
            margin-right: 3%;
            float: right;
            width: 45%;
        }
        #qrcode_img > canvas {
            display: none;
        }
		.red_div_title {
			color: #888888;
			border-collapse: collapse;
            margin-bottom: -1px;
            overflow: hidden;
		}
        .more-box {
            display: none;
            width: 100%;
            height: 100%;
            position: fixed;
            top: 0;
            left: 0;
            z-index: 999;
            overflow: hidden;
	   		background-color: rgba(0, 0, 0, .5);
        }
        .more-box .more {
            margin: 20% auto;
            width: 80%;
            text-align: center;
            background-image: url("../static/img/bg_3.jpg");
            border-radius: .25rem;
            /*overflow: hidden;*/
        }
        .more-box .more h2 {
            display: flex;	align-items: center;
            justify-content: center;
            background-color: rgba(255, 255, 255, .8);
            position: relative;
            height: 1.4rem;
            color: #F7B52C;
            font-size: 24px;
            border-top-left-radius: .25rem;
            border-top-right-radius: .25rem;
            font-weight: 600
        }
        [data-dpr="2"] .more-box .more h2 {
            font-size: 48px
        }
        [data-dpr="3"] .more-box .more h2 {
            font-size: 72px
        }
        .more-box .more h2 img {
            width: 1rem;
            position: absolute;
            right: .35rem;
            top: 40%;
            margin-top: -.3125rem
        }
        .more-box .more .more-con {
            height: auto;
            overflow-y: scroll;
            font-size: 0.7rem;
            /*line-height: .4rem;*/
            font-weight: 600;
            text-align: center;
        }
        .more-box .more img {
            width: 95%;
        }
        [data-dpr="2"] .more-box .more .more-con {
            font-size: 2rem;
        }[data-dpr="3"] .more-box .more .more-con {
            font-size: 3rem;
        }
        .more-box .more .more-con ul li {
            color: #000000
        }
        .detail{
            width: 90%;
            margin-left: 5%;
            height: 3.5rem;
            background-image: url("../static/img/btn.png");
            background-size:100% 100% ;
            text-align: right;
            vertical-align: bottom;
            margin-top: 7%;
        }
        .detail_btn{
            margin-top: 0.5rem;
            width: 2.5rem;
            margin-right: 1rem;
        }
    </style>

</head>
<body class="bg">
<!--head-->
<div class="head">
    <img class="title_img" src="${ctx}/static/img/red_title.png">
</div>

<!--content-->
<div class="content">
    <input id="openid" hidden value="${result.openid}">
    <input id="ctx" hidden value="${ctx}">
    <input id="total" hidden value="${result.total}">
    <div class="content_div" style="border: 0">

        <%-- <div class="content_logo">
            <img src="${ctx}/static/img/logo_bg.png">
        </div> --%>

        <!--每个页面的不同点 start-->
        <div class="content_input">
            <!--分享按钮-->
            <div class="share_table input_90">
                <table>
                    <tr>
                        <td class="share_ico"><img class="wechat_share share_img" src="${ctx}/static/img/red_ico1.png"><br>微信</td>
                        <td class="share_ico"><img class="wechat_share share_img" src="${ctx}/static/img/red_ico2.png"><br>朋友圈</td>
                        <td class="share_ico" id="qrcode_img">
                        	<%-- <a class="lightbox-group share_img qrcode_img" href="..${result.qrcodeUrl}" data-lightbox="example-set"> --%>
                            <%-- <img class="share_img qrcode_img" id="qrcode_img" src="..${result.qrcodeUrl}"> --%>
                            <!-- </a> -->
                        </td>
                    </tr>
                </table>
            </div>
            <!--红包记录-->
            <div class="content_input_div_white input_90 red_recode">
            	<div>
                    <div class="red_success_count">
                        <p class="red_count_p">成功邀请</p>
                        <p class="red_count_cnt"><span class="red_count_no">${result.total}</span>人</p>
                    </div>
                    <div class="red_cash_count">
                        <p class="red_count_p">赚取红包</p>
                        <p class="red_count_cnt"><span class="red_count_no">${result.amountCash}</span>元</p>
                    </div>
                </div>
                <!--红包提现按钮-->
                <div class="red_pull_cash">
                    <div></div>
                    <button class="content_input_button_brown cash_button_1" id="cashList">领取历史</button>
                    <button class="content_input_button cash_button_2" id="cash">红包提现</button>
                </div>
                <c:choose>
                	<c:when test="${result.total != '0'}">
                		<div class="red_div_title"  style="width:100%;margin-left:0px"><p style="margin-top:0.5rem;font-size:14px;">我邀请的好友</p></div>
                	</c:when>
                </c:choose>
                <div class="red_table_div">
	                <c:choose>
	                	<c:when test="${result.total != '0'}">
                			<table class="red_content_list_table">
	                            <c:forEach var="item" items="${result.recommendList}">
	                                <tr class="red_content_list">
	                                    <td class="red_table_td1"><img src="${item.headimgurl}" class="red_table_img"></td>
	                                    <td class="red_table_td2"><span style="color:#444444;">${item.nickname}</span><br>${fn:substring(item.createDate,0,19)}
	                                    </td>
	                                    <td class="red_table_td3">${item.money}</td>
	                                </tr>
	                            </c:forEach>
	                        </table>
	                    </c:when>
	                    <c:otherwise>
	                    </c:otherwise>
	                </c:choose>
                </div>
	        	<p class="red_button_more look_more">查看更多</p>
            </div>
        </div>
        <!--每个页面的不同点 end-->
		<div class="detail">
            <img id="more-open" class="detail_btn" src="${ctx}/static/img/btn_check.png">
        </div>
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
<div class="more-box">
    <div class="more">
        <h2><img id="more-close" src="${ctx}/static/img/more-close.png" alt=""></h2>
        <div class="more-con">
            <ul>
            	<li></li>
                <li>企事业高管的健康管理首选</li>
                <li>品质体检项目，邀您体验</li>
                <li>无需抽血</li>
                <li>比传统影像学检测更超前、更深入</li>
                <li>先进生物物理学技术，检测身体能量值</li>
                <li>了解身体细胞、器官、系统的健康状态</li>
                <li>评估发展趋势及根源</li>
                <li>早发现，早规避，远离重大疾病</li>
                <li></li>
            </ul>
        </div>
        <img src="${ctx}/static/img/receive_detail.jpg">
    </div>
</div>
<div class="foot">
    <p>· 九间堂新中医 ·</p>
</div>
<div class="loading">
    <img class="shareing" src="../static/img/share.png"></img>
</div>
<script>
document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
    // 通过下面这个API隐藏底部导航栏
    WeixinJSBridge.call('hideToolbar');
});
$('.more-box').hide();
/*点击体检详情*/
$('#more-open').click(function(){
    /*显示体检详情*/
    $('.more-box').show();
    $("body").css("overflow","hidden");
    $("body").on("touchmove",function(event){
    	event.preventDefault;
    }, false);
});/*点击活动规则画面关闭按钮*/
$('#more-close').click(function(){
    /*隐藏体检详情*/
    $('.more-box').hide();
    $("body").css("overflow","scroll");
    $("body").off("touchmove");
});
    var qrcode = new QRCode(document.getElementById("qrcode_img"), {render: "table"});  // 设置要生成二维码的链接
    qrcode.makeCode(window.location.protocol + "//" + window.location.host + "/wechat-front/user/login?recommend_openid=" + $("#openid").val());
    $("#qrcode_img").children("img").addClass("share_img zoomify");
    $("#qrcode_img").append("<br>面对面邀请");

	var pageNum = 1;
	var pageSize = 5;
	if ($("#total").val() < pageSize) {
		$(".look_more").addClass("hidden");
	}
	
	$(".look_more").click(function() {
		pageNum += 1;
		newPage(pageNum,pageSize);
	});

	function newPage(pageNum,pageSize) {
        $.ajax({
            url: "../share/page?openid="+$("#openid").val()+"&pageNum="+pageNum+"&pageSize="+pageSize,
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

    $(".zoomify").click(function () {
        $(this).zoomify();
    });
    $(".wechat_share").click(function () {
        $(".loading").show();
    });
    $(".loading").click(function () {
        $(".loading").hide();
    });
    $("#cash").click(function () {
    	showModal({
            title:"九间堂新中医",
            content:"是否领取红包",
            showCancel:true,
            cancelText:'否',
            cancelColor:"#ffffff",
            confirmText:'是',
            confirmColor:'#ffffff',
            success:function (res) {
                if (res.confirm) {
                	$.post('${ctx}/payment', {"openId": $("#openid").val()}, function (result) {
                        if (result.code === 0 && null != result.data) {
                            window.location.href = result.data;
                            return false;
                        }

                        if (result.code === 0 && null == result.data) {
                        	showModal({
                                title:"九间堂新中医",
                                content:"网络繁忙，请稍后重试。",
                                showCancel:false,
                                confirmText:'确定',
                                confirmColor:'#ffffff',
                                success:function (res) {
                                   
                                }
                            });
                        }

                        if (result.code !== 0) {
                            if (result.code === 100001) {
                            	showModal({
                                    title:"九间堂新中医",
                                    content:"账户不存在",
                                    showCancel:false,
                                    confirmText:'确定',
                                    confirmColor:'#ffffff',
                                    success:function (res) {
                                       
                                    }
                                });
                            } else if (result.code === 100002) {
                            	showModal({
                                    title:"九间堂新中医",
                                    content:"该账号没有红包可领取",
                                    showCancel:false,
                                    confirmText:'确定',
                                    confirmColor:'#ffffff',
                                    success:function (res) {
                                       
                                    }
                                });
                            } else {
                            	showModal({
                                    title:"九间堂新中医",
                                    content:"网络繁忙,请稍候重试！",
                                    showCancel:false,
                                    confirmText:'确定',
                                    confirmColor:'#ffffff',
                                    success:function (res) {
                                       
                                    }
                                });
                            }
                        }
                    });
                } else {
                	return;
                }
            }
        });
    });
    $("#cashList").click(function () {
        window.location.href = "../payment/listPayment?openId=" + $("#openid").val();
    });
</script>
</body>
</html>