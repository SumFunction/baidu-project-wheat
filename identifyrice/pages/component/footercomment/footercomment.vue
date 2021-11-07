<template>
	<view>
		<view class="footercomment" >
			<view class="middle">
				<view class="left">
					<image src="../../../static/write.png" mode=""></image>
				<input :disabled="true" type="text" value="" @click="runout"  placeholder="评论一句,给前排打call..."/>
				</view>
				<view class="right">
					
					<Smallimage v-for=" i in 3" :key="i" :i="i" style="float: left;margin-left: 40rpx;"></Smallimage>
				</view>
			</view>
				<uni-popup ref="popup" type="bottom" class="popup_box">
					<view >
						
					
					<textarea type="text"  v-model="content" placeholder="说点什么..."/>
					<button type="default" class="button" @click.native="sendComment">提交</button>
					</view>
				</uni-popup>
				<uni-popup ref="popup1" type="message">
				    <uni-popup-message  type="error" message="请先去我的页面登入" :duration="1200"></uni-popup-message>
				</uni-popup>
				<uni-popup ref="popup2" type="message">
				    <uni-popup-message  type="success" message="成功提交评论" :duration="1200"></uni-popup-message>
				</uni-popup>
		</view>
	</view>
</template>

<script>
	import Smallimage from "../smallimage/smallimage.vue"
	// import UniPopup from "../uni-popup/uni-popup.vue"
	// import UniPopupMessage from "../uni-popup-message/uni-popup-message.vue"
	// import UniPopupDialog from "../uni-popup-dialog/uni-popup-dialog.vue"
	import a from "../../../public/address.js"
	export default {
		components:{
			Smallimage,
			// UniPopup,
			// UniPopupMessage
			// UniPopupDialog
		},
		data() {
			return {
				content: ''
			}
		},
		methods: {
			runout(){
				if(this.$store.state.userName == ''){
					this.$refs.popup1.open("top")
				}
				else{
					this.$refs.popup.open("bottom")
				}
			},
			sendComment(){
				let that = this
				uni.request({
					url: a.addressIp+"/generator/comment/save",
					method: 'POST',
					data:{
						content: that.content,
						pid: that.$store.state.problemData.id,
						uid: that.$store.state.userId,
						username: that.$store.state.userName
					},
					success(res) {
						that.$refs.popup.close()
						that.$refs.popup2.open("top")
						that.$emit("shuaxin")
					}
				})
			}
		}
	}
</script>

<style scoped>
	
.button{
	width: 115rpx;
	height: 50rpx;
	font-size: 28rpx;
	line-height: 50rpx;
	position: absolute;
	right: 0rpx;
	top: 150rpx;
	z-index: 10;
}	
	
.middle{
	width: 95%;
	height: 80rpx;
	/* background-color: #13B8CE; */
	margin: 0 auto;
}


.middle .left{
	width: 55%;
	height: 60rpx;
	/* background-color: rgb(241,240,245); */
	border-radius: 20rpx;
	float: left;
	margin-top: 14rpx;
}
.middle .left image{
	width: 45rpx;
	height: 45rpx;
	float: left;
	padding-top: 5rpx;
	margin-left: 5rpx;
}
.middle .left input{
	width: 80%;
	height: 90%;
	font-size: 30rpx;
	float: left;
	margin-left: 3rpx;
}

.right{
	width:  40%;
	height: 80rpx;
	/* background-color: palegoldenrod; */
	float: right;
	margin-top: 10rpx;
}
.popup_box view{
	width: 100%;
	height: 200rpx;
	background-color: white;
	position: relative;
	top: -3rpx;
} 
.popup_box view textarea{
	width: 95%;
	margin: 0 auto;
	height: 200rpx;
	background-color: white;
	overflow: auto;
}
</style>
