<template>
	<view class="grace-padding">
		<!-- <view class="grace-title" style="margin-top:60upx;"> -->
		<!-- <view class="grace-title">
			<view class="grace-h5 grace-blod">我的评论</view>
		</view> -->
		<!-- 评论区 start -->
		<view class="grace-comment">
			<view v-for="(item, index) in list" :key="index" class="grace-comment-list" style="position: relative;">
				<view class="grace-comment-face">
					<image src="../../../static/dog.png" mode="widthFix"></image>
				</view>
				<view class="grace-comment-body">
					<view class="grace-comment-top">
						<text>{{item.nickn}}</text>
						<!-- <text class="grace-iconfont icon-zan"></text> -->
					</view>
					<view class="grace-comment-date">
						<text>{{item.cdate}}</text>
						<!-- <text>102</text> -->
					</view>
					<view class="grace-comment-content">{{item.descp}}</view>
				</view>
				<view @tap="deleteComment(item.id)" class="grace-news-tips grace-news-tips-r">删除</view>
			</view>
		</view>
		<!-- 评论区 end -->
		<!-- <view class="grace-more-bottom">
			<navigator class="grace-border">查看全部评论 <text class="grace-iconfont icon-arrow-right"></text></navigator>
		</view> -->
		<view style="height:100upx;"></view>
		<!-- 评论输入框 -->
		<!-- <view class="grace-footer">
			<view class="grace-input">
				<view class="grace-input-icon grace-iconfont icon-write"></view>
				<input type="text" placeholder="写评论"></input>
			</view>
			<view class="grace-items" style="padding:0 20upx;">发布</view>
		</view> -->
	</view>
</template>
<script>
	import utilObject from '../../../utils.js';
	export default {
		data() {
			return {
				list: []
			}
		},
		methods: {
			getEvalList() {
				const uid = uni.getStorageSync('uid')
				utilObject.request(`/stu/usr/evalList?uid=${uid}`).then(res => {
					if (res.suc) {
						this.list = res.res.list
					} else {
						utilObject.tips(res.msg)
					}
				})
			},
			deleteComment(infoid) {
				const data = {
					infoid
				}
				utilObject.request(`/stu/usr/delEval`, data, 'POSt').then(res => {
					if (res.suc) {
						utilObject.tips('已删除')
						this.getEvalList()
					} else {
						utilObject.tips(res.msg)
					}
				})
			}
		},
		onShow() {
			this.getEvalList()
		},
	}
</script>
<style>
	page {
		background: #FFF;
	}

	.grace-comment-top uni-text:last-child {
		color: #0A98D5;
	}

	.grace-news-tips-r {
		bottom: 10px;
		top: auto;
	}
</style>
