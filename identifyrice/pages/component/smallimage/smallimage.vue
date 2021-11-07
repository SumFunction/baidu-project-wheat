<template>
	<view>
		<view class="first">
		<image :src="imagUrl[i].url" mode=""></image>
		<text>{{imagUrl[i].name}}</text>
		</view>
	</view>
</template>

<script>
	export default {
		props:["i"],
		data() {
			return {
				imagUrl: [{ url:'../../../static/thumbup.png',
							name: '点赞'},
							{
								url: '../../../static/collection.png',
								name: '收藏'},
							{
								url: '../../../static/share.png',
								name: '分享'
								}
							
							
							]
			}
		},
		methods: {
			
		}
	}
</script>

<style>
.first{
	width: 50rpx;
	height: 80rpx;
	/* background-color: pink; */
}
.first image{
	width: 45rpx;
	height: 45rpx;
	float: left;
}
.first text{
	font-size: 18rpx;
	float: left;
	letter-spacing: 1rpx;
	margin-left: 6rpx;
}
</style>
