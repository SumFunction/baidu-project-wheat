<template>
	<view class="all">
		<view class="circle" 
		>
			{{i+1}}
		</view>
		<view class="title" >
			<text decode="true">
			 <!-- {{"     "}}&nbsp;&nbsp;&nbsp; -->
			 {{problemData.name}}
			</text>
		</view>
		<view class="message">
			<view class="author">
				{{problemData.uname}}
			</view>
			<view class="timer">
				{{problemData.publishTime}}
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		props:["problemData","i"],
		data() {
			return {
				userName: this.$store.state.userName,
				myStyle: ["background-color: red;","background-color: yellow;","background-color: blue;","background-color: rgb(240,240,240);"]
			}
		},
		methods: {
			
		}
	}
</script>

<style scoped>

.all{
	margin: 0 auto;
	/* height: ; */
	height: 170rpx;
	width: 90%;
	background-color: white;
	margin-top: 10rpx;
	border-radius: 16rpx;
}

.title{
	position: relative;
	font-weight: bold;
	font-size: 26rpx;
	margin: 0 auto;
	width: 90%;
	z-index: 20;
	margin-top: 10rpx;
	letter-spacing: 1rpx;
	color: black;
}

.circle{
	position: relative;
	height: 30rpx;
	background-color: rgb(29,197,208);
	width: 27rpx;
	border-radius: 4rpx;
	font-size: 20rpx;
	text-align: center;
	line-height: 30rpx;
	color: black;
	/* margin: 0 auto; */
	z-index: 9;
}
.circle:after{
	height: 27rpx;
	content: "";
	width: 27rpx;
	position: absolute;
	border-radius: 27rpx;
	top: 27rpx;
	left: 0rpx;
	z-index: 10;
	background-color: white;
}
.message{
	margin: 0 auto;
	width: 90%;
	font-size: 20rpx;
	margin-top: 15rpx;
	/* color: rgba(114,126,137,1); */
	color: black;
}
.message .author{
	float: left;
}
.message .timer{
	float: right;
}
</style>
