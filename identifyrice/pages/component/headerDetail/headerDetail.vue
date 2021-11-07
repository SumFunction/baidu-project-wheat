<template>
	<view class="all">
		<view class="one">{{headerData.chiName}}</view>
		<view class="two" style="font-weight: bold;">{{headerData.engName}}</view>
		<view class="three">可信度:{{" "}}&nbsp;{{acc}}%</view>
		<view class="four">
			<image class="fourOne" :src="headerData.url1" mode=""></image>
			<image class="fourTwo" :src="headerData.url2" mode=""></image>
		</view>
		<view class="five">
			<view style="display: inline;font-weight: bold;">{{headerData.time == null ? "" : "发生时期  "}}</view>
			<view style="display: inline;">{{headerData.time == null ? "" : headerData.time + ' '}}</view>
			
		</view>
	</view>
</template>

<script>
	export default {
		props:['headerData',"acc"],
		data() {
			return {
				
			}
		},
		methods: {
			
		}
	}
</script >

<style scoped>

.all{
	width: 95%;
	background-color:white;
	border: 1rpx solid white;
	margin: 0 auto;
	margin-top: 40rpx;
	color: rgb(99,96,99);
}
.one{
	width: 90%;
	height: 110rpx;
	line-height: 110rpx;
	font-size: 40rpx;
	font-weight: bold;
	margin: 0 auto;
	
}
.two,.three,.five{
	width: 90%;
	height: 60rpx;
	line-height: 60rpx;
	margin: 0 auto;
}
.five{
	padding-top: 10rpx;
	padding-bottom: 10rpx;
	// width: 150px;
	white-space: nowrap;
	text-overflow: ellipsis;
	overflow: hidden;

}
.three{
	padding-bottom: 20rpx
}
.two{
	font-style: italic;
}
.four{
	width:90%;
	margin: 0 auto;
}
.four image{
	display: inline-block;
	height: 300rpx;
	width: 300rpx;
}

.fourTwo{
	margin-left: 20rpx
}
</style>
