<template>
	<view class="all">
		<view class="title">
			{{item.title}}
		</view>
		<view class="article">
			{{"  "}}&nbsp;&nbsp;&nbsp;&nbsp;
			{{item.content}}
		</view>
	</view>
</template>

<script>
	export default {
		props:["item"],
		data() {
			return {
				
			}
		},
		methods: {
			
		}
	}
</script>

<style scoped>
.all{
	width: 95%;
	/* margin: 0 auto; */
	background-color: white;
	color: rgb(99,96,99);
	/* position: relative; */

	/* margin-top: 40rpx; */
	/* float: left; */
	margin: 0rpx auto;
	margin-top: 43rpx;
	border: 1rpx solid white;
	
}
.title{
	width: 90%;
	height: 110rpx;
	line-height: 110rpx;
	font-size: 40rpx;
	font-weight: bold;
	margin: 0 auto;
	
}
.article{
	width: 90%;
	margin: 0 auto;
	line-height: 42rpx;
	padding-bottom: 35rpx;
}
</style>
