<template>
	<view class="all" style="width: 750rpx;background-color: rgb(239,236,240);">
		<view class="top" style="width: 95%;height: 850rpx;background-color: white;margin: 0 auto;">
			<view class="title">
				请上传单张小麦照片
			</view>
			<image src="../../static/cha.png" class="deleteImg" @click="changeImage"
				:style="imgUrl == null ? 'display: none;' : 'display:block;'" mode=""></image>
			<view class="middlePic">
				<img @click="redirect" :src="imgUrl" alt="">
			</view>
			<view class="foot">
				<button type="default" @click="redirectPage">上传识别</button>
			</view>
		</view>
		<view class="footer" style="width: 95%;margin:0rpx auto;">
			<view class="contain" style="width: 100%;height: 100%;">
				<view class="one">
					当前系统能识别的病害种类
				</view>

				<view class="two" style="font-size: 28rpx;">
					健康小麦{{" "}}&nbsp;&nbsp;&nbsp;小麦锈病{{" "}}&nbsp;&nbsp;&nbsp;小麦白粉病{{" "}}&nbsp;&nbsp;&nbsp;小麦黑橞病
				</view>
				<view class="three" style="font-size: 28rpx;">
					小麦根腐病{{" "}}&nbsp;&nbsp;&nbsp;小麦赤霉病{{" "}}&nbsp;&nbsp;&nbsp;小麦叶枯病
				</view>

			</view>
			<view class="load" :style="isflag == 0 ? 'display: none;' : 'display:block;'">

				<view class="" style="height: 60rpx;">

				</view>
				<view class="iconImag" style="padding-left: 20rpx;">

					<uni-load-more :iconSize="30" status="loading" :contentText="{
				contentrefresh: ' '
			}"></uni-load-more>
				</view>
				<view class="title" style="color: white;">
					识别中...
				</view>
			</view>
		</view>

		<uni-popup ref="popup" type="message">
			<uni-popup-message type="error" message="请先上传一张图片" :duration="1200"></uni-popup-message>
		</uni-popup>
	</view>
</template>

<script>
	import a from "../../public/address.js"
	export default {

		data() {
			return {
				imgUrl: null,
				isflag: 0,
				index: null,
				acc: null
			}
		},

		methods: {
			changeImage() {
				this.imgUrl = null;
				this.isflag = 0;
			},
			redirectPage() {

				if (this.imgUrl == null) {
					this.$refs.popup.open("top")

				} else {
					this.distinguish()
				}

			},
			//图片识别
			distinguish() {
				let those = this;
				this.isflag= 1;
				uni.uploadFile({
					url: a.distinguishIp+'/uploadResnet',
					filePath: those.imgUrl,
					name: 'file',
					formData: {
						'user': 'test'
					},
					success: (uploadFileRes) => {
						// those.isflag = 0
						those.isflag = 0
						let res = JSON.parse(uploadFileRes.data)
						those.index = res.index
						those.acc = parseInt(res.acc * 10000)
						those.acc = parseFloat(those.acc) / 100
						
						uni.navigateTo({
							url: `/pages/questionDetails/questionDetails?index=${those.index}&acc=${those.acc}`
						})
					},
					fail() {
						those.isflag = 0;
					}

				})
			},
			redirect() {
				let that = this
				uni.chooseImage({
					count: 1,
					success(res) {
						const tempFilePaths = res.tempFilePaths[0];
						that.imgUrl = tempFilePaths;

					}
				})

			}
		}
	}
</script>

<style scoped>
	.all {
		height: 1500rpx;
	}

	.top {
		position: relative;
		top: 30rpx;

	}

	.top .title {
		width: 100%;
		height: 100rpx;
		/* background-color: #13B8CE; */
		line-height: 100rpx;
		text-align: center;
		color: rgb(62, 70, 76);
	}

	.top .deleteImg {
		position: absolute;
		width: 35rpx;
		height: 35rpx;
		right: 20rpx;
		top: 86rpx;
	}

	.top .middlePic {
		width: 600rpx;
		height: 600rpx;
		/* background-color: #007AFF; */
		border: 1rpx solid rgb(221, 221, 221);
		background-image: url(../../static/onePlus.png);
		background-repeat: no-repeat;
		background-size: 100% 100%;
		-moz-background-size: 100% 100%;
		margin: 0 auto;
	}

	.top .middlePic image {
		width: 100%;
		height: 100%;
	}

	.foot button {
		background-color: rgb(59, 190, 69);
		color: white;
		width: 350rpx;
		height: 70rpx;
		line-height: 70rpx;
		border-radius: 30rpx;
		font-size: 32rpx;
		margin-top: 30rpx;

	}

	.footer {
		position: relative;
		background-color: white;
		height: 210rpx;
		top: 50rpx;
	}

	.footer .contain view {
		color: rgb(40, 44, 52);
		margin-top: 17rpx;
		margin-left: 10rpx;
		float: left;
	}

	.load {
		position: relative;
		top: -820rpx;
		left: 230rpx;
		width: 250rpx;
		height: 250rpx;
		background-color:
			rgba(0, 0, 0);
		opacity: 0.6;
		border-radius: 10rpx;
	}

	.load .iconImg {}

	.load .title {
		margin-left: 68rpx;
		margin-top: 25rpx;
	}
</style>
