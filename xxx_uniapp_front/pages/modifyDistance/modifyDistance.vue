<template>
	<view class="grace-padding">
		<!-- <view class="grace-h4 grace-center">表单验证</view>
		<view class="grace-text-small grace-center" style="margin-bottom:50upx;">多种模式，快捷方便 ^_^</view> -->
		<view class="grace-form">
			<form @submit="formSubmit">
				<view class="grace-items">
					<view class="grace-label">安全距离</view>
					<input type="text" class="input" :value="distance" name="distance"></input>
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
				distance: '',
			}
		},
		methods: {
			formSubmit: function(e) {
				//定义表单规则
				var rule = [{
					name: "distance",
					checkType: "notnull",
					errorMsg: "请重新输入"
				}];
				//进行表单检查
				var formData = e.detail.value;
				var checkRes = graceChecker.check(formData, rule);
				if (checkRes) {
					const id = uni.getStorageSync('uid')
					const distance = e.detail.value.distance

					const data = {
						id,
						distance
					}
					utilObject.request('/stu/usr/modDistance', data, 'POST').then(res => {
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
		onLoad(prop) {
			console.log(prop);
			this.distance = prop.distance;
		}
	}
</script>
<style></style>
