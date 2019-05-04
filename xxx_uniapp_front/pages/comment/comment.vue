<template>
	<view class="grace-padding">
		<!-- <view class="grace-title" style="margin-top:60upx;"> -->
		<view class="grace-title">
			<view class="grace-h5 grace-blod">评论区</view>
		</view>
		<!-- 评论区 start -->
		<view class="grace-comment">
			<view v-for="(item, index) in list" :key="index" class="grace-comment-list">
				<view class="grace-comment-face">
					<image src="../../static/dog.png" mode="widthFix"></image>
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
			</view>
			<!-- <view class="grace-comment-list">
				<view class="grace-comment-face">
					<image :src="staticUrl + 'logo.png'" mode="widthFix"></image>
				</view>
				<view class="grace-comment-body">
					<view class="grace-comment-top">
						<text>马克一天</text>
						<text class="grace-iconfont icon-zan grace-comment-zan"> 100</text>
					</view>
					<view class="grace-comment-content">除非巍巍群山消逝不见，除非滔滔江水干涸枯竭。除非凛凛寒冬雷声翻滚，除非炎炎酷暑白雪纷飞，除非天地相交聚合连接，直到这样的事情全都发生时，我才敢将对你的情意抛弃决绝！</view>
				</view>
			</view>
			<view class="grace-comment-list">
				<view class="grace-comment-face">
					<image :src="staticUrl + 'logo.png'" mode="widthFix"></image>
				</view>
				<view class="grace-comment-body">
					<view class="grace-comment-top">
						<text>今生缘</text>
						<text class="grace-iconfont icon-zan"> 66</text>
					</view>
					<view class="grace-comment-content">人面不知何处去，桃花依旧笑春风。</view>
					<view class="grace-comment-date">
						<text>08/10 07:55</text>
					</view>
				</view>
			</view>
			<view class="grace-comment-list">
				<view class="grace-comment-face">
					<image :src="staticUrl + 'logo.png'" mode="widthFix"></image>
				</view>
				<view class="grace-comment-body">
					<view class="grace-comment-top">
						<text>小猫咪</text>
						<text class="grace-iconfont icon-zan"> 120</text>
					</view>
					<view class="grace-comment-content">海上生明月，天涯共此时。。</view>
					<view class="grace-comment-date">
						<text>2天前</text>
						<text class="grace-comment-replay-btn">5回复</text>
					</view>
				</view>
			</view> -->
		</view>
		<!-- 评论区 end -->
		<!-- <view class="grace-more-bottom">
			<navigator class="grace-border">查看全部评论 <text class="grace-iconfont icon-arrow-right"></text></navigator>
		</view> -->
		<view style="height:100upx;"></view>
		<!-- 评论输入框 -->
		<view class="grace-footer">
			<view class="grace-input">
				<view class="grace-input-icon grace-iconfont icon-write"></view>
				<input type="text" placeholder="写评论" v-model="evalText"></input>
			</view>
			<view @tap="evaluate" class="grace-items" style="padding:0 20upx;">发布</view>
		</view>
	</view>
</template>
<script>
	import utilObject from '../../utils.js';
	export default {
		data() {
			return {
				name:'',
				list: [],
				id: '',
				evalText: ''
			}
		},
		methods: {
			getEvalList() {
				const id = this.id
				utilObject.request(`/stu/info/detail?id=${id}`).then(res => {
					if (res.suc) {
						this.list = res.res.eval
						this.name = res.res.info.name
					} else {
						utilObject.tips(res.msg)
					}
				})
			},
			evaluate() {
				const uid = uni.getStorageSync('uid')
				const infoid = this.id
				const descp = this.evalText
				const data = {
					uid,
					infoid,
					descp
				}
				utilObject.request(`/stu/usr/addEval`, data, 'POST').then(res => {
					if (res.suc) {
						this.getEvalList()
					} else {
						utilObject.tips(res.msg)
					}
				})
			}
		},
		onLoad(options) {
			this.id = options.id
		},
		onShow() {
			this.getEvalList()
		}
	}
</script>
<style>
	page {
		background: #FFF;
	}

	.grace-comment-top uni-text:last-child {
		color: #0A98D5;
	}
</style>
