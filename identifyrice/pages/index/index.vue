<template>
	
	<view style="background-color: #f0f0f0;">
	<image src="../../static/bg.png" style="width: 100%; height: 268.333px;" mode="widthFix" class="png"></image>
	<view class="padding">
	  <view class="padding">
	   
	    <navigator @click="redirect" class="padding-xl radius shadow bg-gradual-green margin-top" url="../wheatClassify/wheatClassify" style="color: white;background-image: linear-gradient(45deg,rgb(8,148,236), rgb(37,216,197));">
	      <view class="cuIcon-cu-image" style="position:absolute" id="test2">
	          <image class="sm" src="../../static/1.png" ></image>
	      </view>
	      <view class="flex justify-center" >
	        <text class="text-bold text-sl">病害识别</text>
	      </view>
	    </navigator>
	</view>
	</view>
	
	
	
	<view class="bg-gray solid-bottom margin-top padding">
	<view class="action bg-white shadow" style="background-color: white;color: rgb(0,0,0)">
	  <text class="padding-bottom" style="display:block"></text>
	  <text class="cuIcon-title text-blue padding-left" style="color: #0081ff;"></text>
	  <text style="font-size: 33rpx;font-weight: bold;">系统说明：</text>
	  <text style="display:block" class="padding-left padding-bottom padding-top">本系统为小麦病害识别系统</text>
	  <text style="display:block" class="padding-left padding-bottom">主要功能有：小麦病害的识别、提供问题交流区平台。
	  您只需上传相应的图片即可。</text>
	</view>
	</view>
	
	<view class="box margin-top">
	<view class="cu-bar bg-white" style="background-color: white;">
	<view class="action sub-title">
	<text class="text-xl text-bold text-green" style="color: #39b54a;">关于我们</text>
	<text class="bg-green" style="background-color: #39b54a;;" ></text>
	</view>
	</view>
	</view>
	</view>
	<!-- <view>
	<image src="../../static/bg.png" style="width: 100%; height: 268.333px;" mode="widthFix" class="png"></image>
	
		<view class="middle">
			<button @click="redirect">病害识别</button>
		</view>
	
	
	</view> -->
</template>

<script>
	export default {
		data() {
			return {
				title: 'Hello'
			}
		},
		onLoad() {

		},
		methods: {
			redirect(){
				
				console.log("我点中了")
				uni.navigateTo({
					url: '/pages/plantIdentify/plantIdentify'
				})
			}
		}
	}
</script>

<style scoped>
 @import url("../../public/colorui.css");
 @import url("../../public/icon.css");
	
	#test1{
	  width:25px;
	  height:25px;
	}
	#test2{
	  width:20px;
	  height:20px;
	}
	image{
	  width:100%;
	  height:100%
	}
	
/* .middle{
	width: 750rpx;
	margin-top: 200rpx;
}
.middle button{
	width: 30%;
	height: 100rpx;
	background-color: rgb(11,154,233);
	color: white;
	line-height: 100rpx;
	z-index: 10;
} */
</style>
