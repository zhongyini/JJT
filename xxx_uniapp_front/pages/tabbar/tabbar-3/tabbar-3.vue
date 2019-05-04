<template>
	<view class="grace-padding">
		<!-- <view class="grace-h4 grace-center">表单验证</view>
		<view class="grace-text-small grace-center" style="margin-bottom:50upx;">多种模式，快捷方便 ^_^</view> -->
		<view class="grace-form">
			<form @submit="formSubmit">
				<view class="grace-items">
					<view class="grace-label">姓名</view>
					<input type="text" class="input" placeholder="请输入姓名" name="nickname"></input>
				</view>
				<view class="grace-items">
					<view class="grace-label">年龄</view>
					<input type="number" class="input" placeholder="请输入年龄" name="age"></input>
				</view>
				<view class="grace-items">
					<view class="grace-label">性别</view>
					<view class="grace-form-r">
						<picker @change="bindPickerChange" :value="gender[genderIndex]" :range="gender" name="gender">
							<text>{{gender[genderIndex]}}</text>
						</picker>
					</view>
				</view>
				<view class="grace-items">
					<view class="grace-label">走失日期</view>
					<view class="grace-form-r">
						<!-- <picker @change="bindDateChange" :value="dateValue" mode="date" name="bd" start="2019-01-01" end="2016-01-01"> -->
						<picker @change="bindDateChange" :value="dateValue" mode="date" name="bd">
							<text>{{dateValue}}</text>
						</picker>
					</view>
				</view>
				<view class="grace-items">
					<view class="grace-label">家长电话</view>
					<input type="number" class="input" placeholder="请输入电话" name="phone"></input>
				</view>
				<view class="grace-items">
					<view class="grace-label">体貌特征</view>
					<textarea placeholder="请输入体貌特征" name="physicalFeatures" />
					</view>
				<view class="grace-items">
					<view class="grace-label">走失地点</view>
					<textarea placeholder="请输入走失地点" name="lostPlace"/>
				</view>
				<view style="padding:22upx 0;">
					<button formType="submit" type="primary" style="width:100%;">提交</button>
				</view>
			</form>
		</view>
	</view>
</template>
<script>
	var  graceChecker = require("../../../graceUI/graceChecker.js");
	import utilObject from '../../../utils.js';
	
	export default {
	data() {
		return {
			nickname: '',
			genderIndex: 0,
			age : 0,
			gender : ['请选择', '男', '女'],
			dateValue : "请选择",
			phone : "请选择",
			physicalFeatures : "",
			lostPlace : "",
		}
	},
	methods:{ 
		bindPickerChange:function(e){
			this.genderIndex = e.detail.value;
		},
		bindDateChange : function(e){
			this.dateValue = e.detail.value;
		},
		formSubmit : function(e){
			//定义表单规则
			var rule = [
				{name:"nickname", checkType : "string", checkRule:"1,3",  errorMsg:"姓名应为1-3个字符"},
				{name:"age", checkType : "string", checkRule:"1,3", errorMsg:"请输入年龄"},
				{name:"gender", checkType : "in", checkRule:"男,女",  errorMsg:"请选择性别"},
				{name:"phone", checkType : "phoneno",  errorMsg:"请输入正确的电话"},
				{name:"physicalFeatures", checkType : "notnull",  errorMsg:"请输入体貌特征"},
				{name:"lostPlace", checkType : "notnull",  errorMsg:"请输入走失地点"}
			];
			//进行表单检查
			var formData = e.detail.value;
			var checkRes = graceChecker.check(formData, rule);
			if(checkRes){
				const age = e.detail.value.age
				const zsdate = e.detail.value.bd
				const sex = e.detail.value.gender==='男'?1:0
				const site = e.detail.value.lostPlace
				const name = e.detail.value.nickname
				const tel = e.detail.value.phone
				const face = e.detail.value.physicalFeatures
				const uid = uni.getStorageSync('uid')
				
				const data = {
					age,
					zsdate,
					sex,
					site,
					name,
					tel, 
					face,
					uid
				}
				utilObject.request('/stu/info/add', data, 'POST').then(res => {
					if (res.suc) {
						utilObject.tips('发布成功!')
					} else {
						utilObject.tips(res.msg)
					}
				})
			}else{
				uni.showToast({ title: graceChecker.error, icon: "none" });
			}
		}
	}
}
</script>
<style></style>
