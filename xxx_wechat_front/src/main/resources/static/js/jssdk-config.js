$(function(){
	var protocol = window.location.protocol;
	var host = window.location.host;
	var pathname = "/wechat-front/user/login?recommend_openid="+$("#openid").val();
	var shareUrl = protocol+"//"+host+pathname;
	console.log(shareUrl);
	var thisUrl = location.href.split("#")[0];
	var title = "至臻体检，邀您体验";
	var desc = "众多企事业高管的健康管理首选，品质体检项目，邀您体验，您获健康，我来买单。";
	var link = shareUrl;
	var imgUrl = protocol+"//"+host+"/wechat-front/static/img/share_img.jpg";
	//var imgUrl = "https://mmbiz.qpic.cn/mmbiz_png/Qj3vUwRqqkWeBicjmbb0ax8MKuUoLU7nf7CeVnTlNBQyhNyt1GX6lLjBKsaeia0LnGSNmMNCXqcPcTBKXZia9qnbQ/0?wx_fmt=png";
	$.ajax({
		url: "../config/jsapiTicket",
		data:{"url":thisUrl},
		success : function(result) {
			if (!result) {
				return;
			}
			var appId = result.appid;//服务端设置的id,用于下面拼接生成需要分享的link
		    var timestamp = result.timestamp;//因为服务端是String类型，此处转化成数值类型
		    var nonceStr = result.nonceStr;
		    var signature = result.signature;
		    wx.config({
			    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
			    appId: appId, // 必填，公众号的唯一标识
			    timestamp: timestamp, // 必填，生成签名的时间戳
			    nonceStr: nonceStr, // 必填，生成签名的随机串
			    signature: signature,// 必填，签名
			    jsApiList: ['onMenuShareTimeline', 'onMenuShareAppMessage', 'onMenuShareQQ', 'onMenuShareWeibo', 'onMenuShareQZone', 'addCard', 'openCard'] // 必填，需要使用的JS接口列表
			});
		    wx.ready(function(){
		    	wx.onMenuShareTimeline({
		    	    title: title, // 分享标题
		    	    link: link, // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
		    	    imgUrl: imgUrl, // 分享图标
		    	    success: function () {
		    	    // 用户确认分享后执行的回调函数
		    	    addShareNumber();
		    	},
		    	cancel: function () {
		    	    // 用户取消分享后执行的回调函数
		    	    }
		    	});
		    	
		    	wx.onMenuShareAppMessage({
		    		title: title, // 分享标题
		    		desc: desc, // 分享描述
		    		link: link, // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
		    		imgUrl: imgUrl, // 分享图标
		    		type: '', // 分享类型,music、video或link，不填默认为link
		    		dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
		    		success: function () {
		    		// 用户确认分享后执行的回调函数
		    		addShareNumber();
		    		},
		    		cancel: function () {
		    		// 用户取消分享后执行的回调函数
		    		}
		    	});
	
		    	wx.onMenuShareQQ({
		    		title: title, // 分享标题
		    		desc: desc, // 分享描述
		    		link: link, // 分享链接
		    		imgUrl: imgUrl, // 分享图标
		    		success: function () {
		    		// 用户确认分享后执行的回调函数
		    		addShareNumber();
		    		},
		    		cancel: function () {
		    		// 用户取消分享后执行的回调函数
		    		}
		    	});
		    	
		    	wx.onMenuShareWeibo({
		    		title: title, // 分享标题
		    		desc: desc, // 分享描述
		    		link: link, // 分享链接
		    		imgUrl: imgUrl, // 分享图标
		    		success: function () {
		    		// 用户确认分享后执行的回调函数
		    		addShareNumber();
		    		},
		    		cancel: function () {
		    		// 用户取消分享后执行的回调函数
		    		}
		    	});
		    	
		    	wx.onMenuShareQZone({
		    		title: title, // 分享标题
		    		desc: desc, // 分享描述
		    		link: link, // 分享链接
		    		imgUrl: imgUrl, // 分享图标
		    		success: function () {
		    		// 用户确认分享后执行的回调函数
		    		addShareNumber();
		    		},
		    		cancel: function () {
		    		// 用户取消分享后执行的回调函数
		    		}
		    	});
		    });
		    
		    wx.error(function(res){
		        // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
		        console.log(res);
		    });
		}
	});
});

// 添加卡券
var addCard = function() {
	$.ajax({
		url: "../config/apiTicket?cardId="+$("#cardId").val(),
		type: "post",
		async: false,
		dataType: "json",
		success : function(result) {
			console.log(result);
			var cardExtData = '{"timestamp":"'+result.timestamp+'","nonce_str":"'+result.nonce+'","signature":"'+result.signature+'"}';
			wx.addCard({
		        cardList: [{
	                cardId: result.cardId,
	                cardExt: cardExtData
	            }],
		        success: function (res) {
		            var result = JSON.stringify(res);
		            $.ajax({
		            	url: "../card/insert",
		            	type: "post",
		            	data: {"res":result,"openid":$("#openid").val(),"recommend_openid":$("#recOpenid").val()},
		            	async: false,
		            	success : function(result) {
		            		//hideLoading();
				    		window.location.href = "../card/view";
		            	}
		            });
		         },
		        cancel: function (res) {
		        	console.log(JSON.stringify(res));
            		//hideLoading();
		        }
		    });
		},
		error : function(result) {
			alert("卡券获取失败，请稍后再试");
		}
	});
};

var openCard = function() {
	wx.openCard({
		cardList: [{
			cardId: $("#cardId").val(),
			code: $("#code").val()
		}]// 需要打开的卡券列表
	});
};

var addShareNumber = function() {
	$.ajax({
		url: "../user/addShareNumber?openid="+$("#openid").val(),
		type: "get",
		async: false,
		success : function(result) {
			//alert("成功");
		},
		error : function (res) {
	       // alert("失败");
	    }
	});
};