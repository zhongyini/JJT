<template>
	<view class="grace-padding">
		<view style="margin-top:50px;" class="grace-center">
			<image src='../../static/love.jpg' style='width:68px; height:68px; border-radius:8px;'></image>
		</view>
		<view class="grace-form" style="margin-top:50upx;">
			<form @submit="loginNow">
				<view class="grace-items  item-border">
					<view class="grace-label">
						<picker :value="pnpre" @change="changePre" :range="pnpres" name="pn_pre">
							<text>+{{pnpre}}</text>
						</picker>
					</view>
					<input type="number" name="pn" class="input" v-model="phoneno" placeholder="请输入手机号"></input>
				</view>
				<view class="grace-space-between  item-border" style="margin-top:28upx;">
					<view class="grace-items">
						<view class="grace-label grace-center">密码</view>
						<input type="password" class="input" name="yzm" placeholder="请输入密码"></input>
					</view>
				</view>
				<!-- <view class="grace-space-between  item-border" style="margin-top:28upx;">
					<view class="grace-items" style="width:70%;">
						<view class="grace-label grace-center">验证码</view>
						<input type="number" class="input" name="yzm" placeholder="请输入验证码"></input>
					</view>
					<view style="width:28%;  margin-left:2%; paading:0;">
						<button type="primary" class="login-sendmsg-btn" @tap='getVCode'>{{vcodeBtnName}}</button>
					</view>
				</view> -->
				<button form-type='submit' type='primary' style='background:#00C777; margin-top:30px;'>
					登录 <text class="grace-iconfont icon-arrow-right"></text>
				</button>
			</form>
		</view>
		<view class="grace-center" style="margin-top:20upx; line-height:50upx;" @tap="reg">
			还没有账号？点击注册
		</view>
	</view>
</template>
<script>
	var graceChecker = require("../../graceUI/graceChecker.js");
	import utilObject from '../../utils.js';

	export default {
		data() {
			return {
				pnpre: '86',
				pnpres: ['86', '01', '11', '26', '520'],
				vcodeBtnName: "获取验证码",
				countNum: 120,
				countDownTimer: null,
				phoneno: ''
			}
		},
		methods: {
			loginWithWx: function() {
				uni.showToast({
					title: "请完善登录功能",
					icon: "none"
				})
			},
			changePre: function(e) {
				this.pnpre = this.pnpres[e.detail.value];
			},
			loginNow: function(e) {
				// 表单验证
				var rule = [{
						name: "pn",
						checkType: "phoneno",
						errorMsg: "请填写正确的手机号"
					},
					{
						name: "yzm",
						checkType: "notnull",
						// checkRule: "4,6",
						errorMsg: "请正确填写密码"
					},
				];
				var formData = e.detail.value;
				var checkRes = graceChecker.check(formData, rule);
				// 验证通过
				if (checkRes) {
					const name = e.detail.value.pn
					const pwd = e.detail.value.yzm

					const data = {
						name,
						pwd
					}
					utilObject.request('/stu/usr/login', data, 'POST').then(res => {
						if (res.suc) {
							uni.switchTab({
								url: '../tabbar/tabbar-1/tabbar-1'
							});
							uni.setStorageSync('uid', res.res.user.id)
							uni.setStorageSync('user', res.res.user)
						} else {
							utilObject.tips(res.msg)
						}
					})
				} else {
					uni.showToast({
						title: graceChecker.error,
						icon: "none"
					});
				}
			},
			getVCode: function() {
				var myreg = /^[1][1,2,3,4,5,7,8,9][0-9]{9}$/;
				if (!myreg.test(this.phoneno)) {
					uni.showToast({
						title: '请正确填写手机号码',
						icon: "none"
					});
					return false;
				}
				// 手机号码为 :  this.phoneno
				// vcodeBtnName 可以阻止按钮被多次点击 多次发送 return 会终止函数继续运行
				if (this.vcodeBtnName != '获取验证码' && this.vcodeBtnName != '重新发送') {
					return;
				}
				this.vcodeBtnName = "发送中...";
				// 与后端 api 交互，发送验证码 【自己写的具体业务代码】
				// 假设发送成功，给用户提示
				uni.showToast({
					title: '短信已发送，请注意查收',
					icon: "none"
				});
				// 倒计时
				this.countNum = 120;
				this.countDownTimer = setInterval(function() {
					this.countDown();
				}.bind(this), 1000);
			},
			countDown: function() {
				if (this.countNum < 1) {
					clearInterval(this.countDownTimer);
					this.vcodeBtnName = "重新发送";
					return;
				}
				this.countNum--;
				this.vcodeBtnName = this.countNum + '秒重发';
			},
			reg: function() {
				uni.navigateTo({
					url: '../reg/reg'
				})
			}
		}
	}
</script>
<style>
	.item-border {
		border-bottom: 1px solid #E0E0E0 !important;
	}

	.login-sendmsg-btn {
		border: 1px solid #00C777 !important;
		background: none !important;
		color: #00C777 !important;
		width: 100%;
		height: 35px;
		line-height: 35px;
		margin-top: 6px;
		font-size: 14px !important;
	}

	.grace-login-three {
		display: flex;
		justify-content: center;
		flex-wrap: nowrap;
	}

	.grace-login-three view {
		width: 50px;
		height: 50px;
		line-height: 50px;
		font-size: 46px;
		color: #00C777;
		text-align: center;
		margin: 8px 15px;
	}
</style>
