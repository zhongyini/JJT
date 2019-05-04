<template>
	<view>
		<!-- <page-head :title="title"></page-head> -->
		<view class="uni-common-mt">
			<view>
				<!-- <map id="myMap" @tap="tip()" @controltap="tipc" :latitude="latitude" :longitude="longitude" :controls="controls"></map> -->
				<map id="myMap" :show-location="true" @controltap="tipc" @tap="tip" :circles="circles" :controls="controls" :scale='14' :latitude="latitude" :longitude="longitude"></map>
			</view>
		</view>
	</view>
</template>
<script>
	export default {
		data() {
			return {
				user: {},
				title: 'map',
				latitude: 31.12400167201743,
				longitude: 121.38997961495207,
				covers: [{
					latitude: 0,
					longitude: 0,
					// #ifdef APP-PLUS
					iconPath: '../../../static/img/location@3x.png',
					// #endif
					// #ifndef APP-PLUS
					iconPath: '../../../static/location.png',
					// #endif
				}],
				circles: [{
					latitude: 31.12400167201743,
					longitude: 121.38997961495207,
					fillColor: "#FFFFFF",
					color: '#FF0000',
					radius: 100,
					strokeWidth: 1
				}],
				controls: [{ //在地图上显示控件，控件不随着地图移动
					id: 1, //控件id
					iconPath: '../../../static/circle.png', //显示的图标	
					position: { //控件在地图的位置
						left: 50,
						top: 100,
						width: 250,
						height: 250
					},
					clickable: true
				}],
				// 				markers: [{
				// 					id: 0,
				// 					iconPath: '../../../static/logo.png',
				// 					latitude: 39.909,
				// 					longitude: 116.39742,
				// 					width: 300,
				// 					height: 300,
				// 					title: "当前位置",
				// 					callout: {
				// 						padding: 10,
				// 						content: "当前位置",
				// 						bgColor: "#DC143C",
				// 						color: "#FFFF00",
				// 						display: "ALWAYS"
				// 					},
				// 					label: {
				// 						content: "标题"
				// 					},
				// 					anchor: {}
				// 				}]
			}
		},
		onLoad() {
			this.user = uni.getStorageSync('user');
			console.log(this.user);
			this.circles.radius = this.user.distance * 100;
			console.log(this.circles.radius);
			// 初始化地图，设置中心点坐标和地图级别
			/* uni.getLocation({
				type: 'gcj02',
				altitude: false,
				success: function(res) {
					if(res) {
						console.log('当前位置的经度：' + res.longitude);
						console.log('当前位置的纬度：' + res.latitude);
					}
				},
				fail: function(err) {
					console.log(err);
				},
				complete: function(res) {
					console.log(res);
				}
			}); */
		},
		onReady() {
			var t = this;
			this.mapCtx = uni.createMapContext('myMap', this)
			console.log(this.mapCtx);
			/* this.mapCtx.getCenterLocation({
				success: function(res) {
					console.log(res);
					t.longitude = res.longitude
					t.latitude = res.latitude
					console.log(t.longitude);
					console.log(t.latitude);
// 					this.covers[0].longitude = res.longitude
// 					this.covers[0].latitude = res.latitude
				}
			}); */
		},
		methods: {
			tip() {
				uni.showToast({
					title: '超出安全范围',
					icon: 'none'
				});
			},
			tipc() {
				uni.showToast({
					title: '在安全范围内',
					icon: 'none'
				});
			}
		}
	}
</script>
<style>
	map {
		width: 100%;
		height: 1200upx;
	}
</style>
