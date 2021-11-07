<template>
	<view>
		<view class="onemiddle">
			<image src="../../../static/circle.png" mode=""></image>
			<view class="nametime" style="float: left;margin-left: 7rpx;">
				<view class="name">
					{{problemData.uname}}
				</view>
				<view class="time">
					{{problemData.publishTime}}
				</view>
			</view>
			<view class="guanzhu" style="float: right;overflow: hidden;">
				<button >关注</button>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				problemData: this.$store.state.problemData
			}
		},
		
		
		computed:{
			// time(){
			// 	return this.problemData.splitStr("-");
			// }
		},
		methods: {
			
		}
	}
</script>

<style scoped>
	
.onemiddle{
	width: 750rpx;
	height: 100rpx;
	/* background-color: #4CD964; */
}
.onemiddle image{
	width: 70rpx;
	height: 70rpx;
	float: left;
	margin-left: 10rpx;
	margin-top: 3rpx;
}

.onemiddle .nametime .name{
	font-size: 28rpx;
	margin-top: 8rpx;
	
}
.onemiddle .nametime .time{
	font-size: 18rpx;
	margin-top: 3rpx;
	color: #555555;
}
.onemiddle .guanzhu button{
	width: 120rpx;
	height: 50rpx;
	font-size: 28rpx;
	line-height: 50rpx;
	letter-spacing: 2rpx;
	/* background-color: white; */
	/* border-color: rgb(221,221,221); */
	border-radius: 20rpx;
	margin-top: 15rpx;
	margin-right: 15rpx;
	float: right;
	overflow: hidden;
	
}
</style>
