<template>
	<view class="grace-padding">
		<!-- <view class="grace-h4 grace-center">表单验证</view>
		<view class="grace-text-small grace-center" style="margin-bottom:50upx;">多种模式，快捷方便 ^_^</view> -->
		<view class="grace-form">
			<form @submit="formSubmit">
				<view class="grace-items">
					<view class="grace-label">旧密码</view>
					<view class="input">{{password}}</view>
				</view>
				<view class="grace-items">
					<view class="grace-label">新密码</view>
					<input type="text" class="input" :value="newPassword" name="newPassword"></input>
				</view>
				<view style="padding:22upx 0;">
					<button formType="submit" type="primary" style="width:100%;">提交</button>
				</view>
			</form>
		</view>
	</view>
</template>
<script>
	var graceChecker = require("../../graceUI/graceChecker.js");
	import utilObject from '../../utils.js';

	export default {
		data() {
			return {
				phone: '',
				password: '',
				newPassword: ''
			}
		},
		methods: {
			formSubmit: function(e) {
				//定义表单规则
				var rule = [
					{
						name: "newPassword",
						checkType: "notnull",
						errorMsg: "请输入密码"
					}
				];
				//进行表单检查
				var formData = e.detail.value;
				var checkRes = graceChecker.check(formData, rule);
				if (checkRes) {
					const id = uni.getStorageSync('uid')
					const newp = e.detail.value.newPassword

					const data = {
						id,
						newp
					}
					utilObject.request('/stu/usr/modPwd', data, 'POST').then(res => {
						if (res.suc) {
							uni.showToast({
								title: "修改成功!",
								icon: "none"
							});
							uni.navigateBack({
								delta: 1
							});
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
			}
		},
		onLoad(options) {
			this.password = options.password
		}
	}
</script>
<style></style>
