<template>
	<view class="grace-padding">
		<!-- <view class="grace-h4 grace-center">表单验证</view>
		<view class="grace-text-small grace-center" style="margin-bottom:50upx;">多种模式，快捷方便 ^_^</view> -->
		<view class="grace-form">
			<form @submit="formSubmit">
				<view class="grace-items">
					<view class="grace-label">修改昵称</view>
					<input type="text" class="input" :value="nickn" name="nickn"></input>
				</view>
				<!-- <view class="grace-items">
					<view class="grace-label">修改密码</view>
					<input type="text" class="input" :value="password" name="password"></input>
				</view> -->
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
				nickn: '',
				password: ''
			}
		},
		methods: {
			formSubmit: function(e) {
				//定义表单规则
				var rule = [{
					name: "nickn",
					checkType: "notnull",
					errorMsg: "请输入正确的电话号码"
				}];
				//进行表单检查
				var formData = e.detail.value;
				var checkRes = graceChecker.check(formData, rule);
				if (checkRes) {
					const id = uni.getStorageSync('uid')
					const nickn = e.detail.value.nickn

					const data = {
						id,
						nickn
					}
					utilObject.request('/stu/usr/modNickn', data, 'POST').then(res => {
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
		onLoad() {

		}
	}
</script>
<style></style>
