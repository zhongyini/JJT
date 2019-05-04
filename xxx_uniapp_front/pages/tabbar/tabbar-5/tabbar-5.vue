<template>
	<view>
		<!-- <graceHeader title="用户昵称" desc="其他信息..."></graceHeader> -->
		<graceHeader :title="nickn"></graceHeader>
		<view class="grace-list grace-bg-white grace-common-mt">
			<view class='items'>
				<view class="title">联系电话<text>{{phone}}</text></view>
				<!-- <view class="arrow-right"></view> -->
			</view>
			<view @tap="modifyNickn" class='items'>
				<view class="title">昵称<text>{{nickn}}</text></view>
				<view class="arrow-right"></view>
			</view>
			<view @tap="modifyPassword" class='items'>
				<view class="title">密码<text>{{password}}</text></view>
				<view class="arrow-right"></view>
			</view>
			<view @tap="modifyDistance" class='items'>
				<view class="title">安全距离<text>{{distance}}km</text></view>
				<view class="arrow-right"></view>
			</view>
		</view>

		<view style="width:100%; margin-top:18px;">
			<button @tap="quit" style="width:100%; background:#FFFFFF; border:0;">退出登录</button>
		</view>
		<view style="width:100%; height:30px;"></view>
	</view>
</template>

<script>
	import graceHeader from "../../../graceUI/components/graceHeader.vue";
	import utilObject from '../../../utils.js';

	export default {
		data() {
			return {
				nickn: '',
				phone: '',
				password: '',
				distance: ''
			}
		},
		components: {
			graceHeader
		},
		onShow() {
			const id = uni.getStorageSync('uid')
			const data = {
				id
			}
			utilObject.request('/stu/usr/info', data).then(res => {
				if (res.suc) {
					this.nickn = res.res.data.nickn
					this.phone = res.res.data.name
					this.password = res.res.data.pwd
					this.distance = res.res.data.distance
				} else {
					utilObject.tips(res.msg)
				}
			})
		},
		methods: {
			modifyNickn() {
				uni.navigateTo({
					url: '../../modifyNickn/modifyNickn'
				});
			},
			modifyDistance() {
				uni.navigateTo({
					url: '../../modifyDistance/modifyDistance?distance=' + this.distance
				});
			},
			modifyPassword() {
				uni.navigateTo({
					url: `../../modifyPassword/modifyPassword?password=${this.password}`
				});
			},
			quit() {
				uni.navigateTo({
					url: '../../login/login'
				});
			}
		}
	}
</script>

<style>

</style>
