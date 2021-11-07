<template>
	<view style="height: 1000rpx;width: 750rpx;">
		<view class="title" >
			<view class="">

				{{" "}}&nbsp;&nbsp;&nbsp;{{problemData.name}}
			</view>
		</view>

		<Middleuser ></Middleuser>
		<view class="container">
			<!-- <editor id="editor" :read-only="true" class="ql-container" placeholder="kaishishuru...."
				@ready="onEditorReady"></editor> -->
				<rich-text :nodes="htmlnodes"></rich-text>
		</view>

		<view class="midlle">
		 	<view class="midlleC">讨论区</view>
		 </view>
		 
		

		<qizai9527-commentList :list="commentList" @clickComment="clickComment" @clickUser="clickUser">
		</qizai9527-commentList>
		<view class="bottom" >
			<Footercomment @shuaxin="xianshiComment"></Footercomment>
		</view>
	</view>
</template>

<script>
	import Discuss from "../component/discuss/discuss.vue"
	import Middleuser from "../component/middleuser/middleuser.vue"
	import Footercomment from "../component/footercomment/footercomment.vue"
	import a from "../../public/address.js"
 export default {
		components: {
			Discuss,
			Middleuser,
			Footercomment
		},
		mounted() {
			// this.commentList = this.$store.state.commentList
		},
		unmounted() {
			console.log("组件卸载了")
		},
		onLoad() {
			this.getScreenHeight()
			let test = this.$store.state.problemData.content
	
		
			this.htmlnodes = test.replace(/\<img/g,'<img style="width:100%;margin:0 18rpx;height:auto;display:block;border-radius:30rpx;"')
		},
		onShow(){
			this.xianshiComment();
			
		},
		
		onReady() {
			
			
		},
		data() {
			return {
				htmlnodes:'',
				path: '',
				html:'',
				screenHeight: null,
				editorCtx: null,
				problemData: this.$store.state.problemData,
				imagUrl: 'https://dss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1976832114,2993359804&fm=26&gp=0.jpg',
				commentList: null
			}
		},
		methods: {
			//得到屏幕高度并赋值
			getScreenHeight(){
				let that = this
				uni.getSystemInfo({
					success(res) {
						that.screenHeight = res.screenHeight
						console.log(that.screenHeight,"屏幕高度")
					}
				})
			},
			xianshiComment(){
				this.commentList = [{}]
				let that = this
				
					uni.request({
							url: a.addressIp+"/generator/comment/list",
							method:"POST",
							data:{
								pid: that.$store.state.problemData.id
							},
							success(res) {
								let problemComment = res.data.page
								that.commentList = [{}]
									for( let i = 0; i < problemComment.length; i++){
										that.commentList[i] = {}
										that.commentList[i].id = problemComment[i].id
										that.commentList[i].userId = problemComment[i].uid
										that.commentList[i].avatar = 'https://dss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1976832114,2993359804&fm=26&gp=0.jpg'
										that.commentList[i].name = problemComment[i].username
										that.commentList[i].time = parseInt(problemComment[i].publishTime / 1000) 
										that.commentList[i].content = problemComment[i].content
									}
							}
						})
			},
			onEditorReady() {
				
				// console.log(that.problemData.content,"onEditorReadr时的")
					
					
				uni.createSelectorQuery().select('#editor').context((res) => {
							let that = this
								this.editorCtx = res.context
								this.editorCtx.setContents({
									html: that.$store.state.problemData.content
								
								})
					}).exec()
		
			},
			splitStr(value){
				for(let i = 0; i < value.length; i++){
					value[i].publishTime = value[i].publishTime.split("T")[0]
				}
			},
			// 点击评论事件
			clickComment(e) {
				console.log(e);
			},
			// 点击头像/用户名称事件
			clickUser(e) {
				console.log(e);
			}
		}
	}
</script>

<style scoped>
	.container{
		width: 90%;
		margin: 0 auto;
	}
	#editor{
		overflow: visible !important; 
	}
	.title {
		width: 100%;
		height: 140rpx;
		/* background-color: #4CD964; */
		line-height: 60rpx;
		/* border-bottom: 1rpx solid black; */
		margin: 0 auto;
		margin-top: 10rpx;
		font-weight: bold;
		color: black;
	}

	.title view {
		width: 95%;
		height: 140rpx;
		margin: 0 auto;
	}

	.midlle {
		/* display: flex; */
		width: 750rpx;
		height: 70rpx;
		margin-top: 30rpx;
	}

	.midlle .midlleC {
		width: 30%;
		/* margin-left: 20rpx; */
		padding-left: 35%;
		height: 100%;
		text-align: center;
		font-weight: bold;
		color: black;
		line-height: 70rpx;
	}

	.footer {
		width: 750rpx;
		background-color: rgba(189, 195, 199, 0.15);
		/* height: 1000rpx; */
	}

	.bottom {
		margin: 0rpx;
		padding: 0rpx;
		position: fixed;
		bottom: 0rpx;
		background-color: white;
		width: 100%;
		height: 80rpx;
	}
</style>
