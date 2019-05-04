<template>
	<view>
		<!-- <graceHeader title="新闻列表" desc="多种模式的项目列表"></graceHeader> -->
		<view class="grace-bg-white grace-common-mt grace-padding grace-common-border">
			<!-- 新闻列表 -->
			<view class="grace-news-list">
				<navigator :url="'../../comment/comment?id=' +item.id" v-for="(item, index) in list" :key="index">
					<view class="">
						发布人:{{item.nickn}}
					</view>
					<view class="grace-news-list-items">
						<!-- <image src="../../static/imgs/imgde.png" class="grace-news-list-img grace-list-imgs-l" mode="widthFix"></image> -->
						<view class="grace-news-list-title">
							<view class="grace-news-list-title-main">{{item.name}}, {{item.sex==1?'男':'女'}}, {{item.age}}岁, {{item.face}}</view>
							<view class="grace-news-list-title-main">家长电话: {{item.tel}}</view>
							<view class="grace-news-list-title-main">走失地点: {{item.site}}</view>
							<view class="grace-news-list-title-main">走失日期: {{item.zsdate}}</view>
							<!-- <view class="grace-news-list-title-main">详情描述: {{item.descp}}</view> -->
						</view>
						<view class="grace-news-tips grace-news-tips-r">评论</view>
					</view>
				</navigator>
				<!-- <navigator url="../../comment/comment">
					<view class="grace-news-list-items">
						<view class="grace-news-list-title">
							<view class="grace-news-list-title-main">阿里员工揭秘：很多程序员离职，在小公司当领导，只动嘴不动手！</view>
							<text class="grace-news-list-title-desc grace-text-overflow">阿里巴巴是中国知名的互联网公司，每个人或多或少的都从淘宝上购买的物品</text>
						</view>
						<image src="../../static/imgs/imgde.png" class="grace-news-list-img grace-list-imgs-r" mode="widthFix"></image>
						<view @click="comment" class="grace-news-tips grace-news-tips-r">评论</view>
					</view>
				</navigator> -->
				<!-- <navigator>
                    <view class="grace-news-list-items">
                        <image src="../../static/imgs/imgde.png" class="grace-news-list-img grace-list-imgs-l" mode="widthFix"></image>
                        <view class="grace-news-list-title">
                            <view class="grace-news-list-title-main">一个项目经理不搞砸几个项目，是无法成长为一名合格的项目经理的</view>
                            <text class="grace-news-list-title-desc grace-text-overflow">搞砸了就是有前途</text>
                        </view>
                    </view>
                    <view class="grace-news-list-info">发布于2天前    xx次浏览<text>其他元素</text></view>
                </navigator>
                <navigator>
                    <view class="grace-news-list-img-news">
                        <view class="grace-news-list-title-main">一个项目经理不搞砸几个项目，是无法成长为一名合格的项目经理的</view>
                        <view class="grace-news-list-imgs">
                            <image src="../../static/imgs/imgde.png" mode="widthFix"></image>
                            <image src="../../static/imgs/imgde.png" mode="widthFix"></image>
                            <image src="../../static/imgs/imgde.png" mode="widthFix"></image>
                        </view>
                    </view>
                    <view class="grace-news-list-info">
                        <view>
                            <text class="grace-iconfont icon-time2"></text>2天前
                            <text class="grace-iconfont icon-eye" style="margin-left:20px;"></text>99次浏览
                        </view>
                    </view>
                </navigator>
                <navigator>
                    <view class="grace-news-list-img-news">
                        <view class="grace-news-list-title-main">一个项目经理不搞砸几个项目，是无法成长为一名合格的项目经理的</view>
                        <view class="grace-news-list-img-big"><image src="../../static/imgs/imgbanner.png" mode="widthFix"></image></view>
                    </view>
                    <view class="grace-news-list-info">发布于2天前    xx次浏览<text>其他元素</text></view>
                </navigator> -->
			</view>
			<!-- 新闻列表 -->
		</view>
	</view>
</template>
<script>
	// import graceHeader from "../../../graceUI/components/graceHeader.vue";
	import utilObject from '../../../utils.js';

	export default {
		data() {
			return {
				list: []
			}
		},
		onShow() {
			this.add()
		},
		methods: {
			add() {
				utilObject.request('/stu/info/list').then(res => {
					if (res.suc) {
						this.list = res.res.list
					} else {
						utilObject.tips(res.msg)
					}
				})
			},
			comment() {
				uni.navigateTo({
					url: '../../comment/comment',
					success: res => {},
					fail: () => {},
					complete: () => {}
				});
			}
		},
		components: {
			// graceHeader
		}
	}
</script>
<style>
	.grace-news-tips-r {
		top: auto;
		bottom: 10px;
	}
</style>
