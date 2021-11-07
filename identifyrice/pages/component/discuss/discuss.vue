<template>
	<view>
		<view class="chat">
			<view class="username">英雄从哪里来:</view>
			<view class="timer">2021-10-13</view>
			<view class="chatcontent">{{"     "}}&nbsp;&nbsp;&nbsp;你写的真不错啊啊啊啊啊啊啊啊啊啊</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				
			}
		},
		methods: {
			
		}
	}
</script>

<style scoped>

.chat{
	width: 95%;
	margin: 0 auto;
	height: 130rpx;
	/* background-color: #4CD964; */
	border-radius: 6rpx;
	background-color: white;
	margin-bottom: 5rpx;
}
.username, .timer{
	font-size: 30rpx;
	color: #13B8CE;
	margin-top: 4rpx;
}
.username{
	float: left;
	margin-left: 6rpx;
}
.timer{
	float: right;
	margin-right: 6rpx;
}
.chatcontent{
	float: left;
	width: 90%;
	height: 80rpx;
	font-size: 33rpx;
	color: #13B8CE;
	margin-top: 10rpx;
}
</style>
