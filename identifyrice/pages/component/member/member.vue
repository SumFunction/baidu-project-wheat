<template>
	<view>
		<view class="header">
			<image src="../../../static/mymember.png"  mode=""></image>
			<view>{{userName}}</view>
			<button @click="logout">注销登入</button>
		</view>
		<view class="midlle">
			<view class="midlleC">我的问题</view>
			<image src="../../../static/plus.png" @click="addRedirect"></image>
		</view>
		<view class="footer">
			<view class="" v-for="(item,i) in problemData" :key="item.id">
				<SearchList  :problemData="item" :i="i" @click.native="redirect(item)"></SearchList>
				<image @click.native="centerDialog(item.id)" src="../../../static/minus.png" mode=""></image>
			</view>
		</view>
		<uni-popup ref="popup" type="dialog">
		    <!-- <uni-popup-message type="error" message="用户名或密码错误" :duration="1200"></uni-popup-message> -->
			<uni-popup-dialog mode="base" title="警告" content="你确定要删除这条问题吗？" :duration="2000" :before-close="true" @close="closeCenterDialog" @confirm="okDelete"></uni-popup-dialog>
		</uni-popup>
		
	</view>
</template>

<script>
	import SearchList from '../searchlist/searchlist.vue'
	import a from "../../../public/address.js"
	export default {
		components:{
			SearchList
		},
		props:['problemData'],
		data() {
			return {
				userName: this.$store.state.userName,
				id: null
			}
		},
		methods: {
			//跳转到问题展示界面
			redirect(item){
				
				this.$store.commit("modifyProblemData",item)
				
				uni.navigateTo({
						url:`/pages/problem/problem`
					})
			
			},
			// 跳转到添加问题页面
			addRedirect(){
				uni.navigateTo({
					url:"/pages/component/addproblem/addproblem"
				})
			},
			//账号登出
			logout(){
				this.$emit("logout"),
				this.$store.commit("addUser",{
					userName: '',
					password: '',
					userId: ''
				})
			},
			//弹出中间删除问题警示框
			centerDialog(id){
				this.id = id
				this.$refs.popup.open('center')
			},
			//取消中间删除问题警示框
			closeCenterDialog(){
				this.$refs.popup.close()
			},
			//确定删除这个问题
			okDelete(){
				this.deleteProblemData(this.id)
				this.$refs.popup.close()
			},
			//删除问题所调用的方法，它是okDelete方法所调用
			deleteProblemData(id){
				
				let that = this
				uni.request({
					url: a.addressIp+'/generator/problem/delete',
					method: 'POST',
					data:{
						id
					},
					success(res) {
						that.$emit("changeVisible")
					}
				})
			}
		}
	}
</script>

<style scoped>
	.footer{
		width: 750rpx;
		background-color: rgba(189,195,199,0.15);
		height: 1000rpx;
		border: 1rpx solid rgba(0,0,0,0);
	}
	.footer view image{
		width: 33rpx;
		height: 33rpx;
		float: right;
		margin-right: 5rpx;
		margin-top: -100rpx;
		z-index: 5;
	}
	.header{
		width: 750rpx;
		height: 200rpx;
	}
	.header image{
		width: 130rpx;
		height: 130rpx;
		float: left;
		margin-top: 35rpx;
		margin-left: 5rpx;
	}
	.header view{
		font-size: 40rpx;
		color: black;
		float: left;
		margin-top: 70rpx;
		margin-left: 5rpx;
		font-weight: bold;
	}
	.header button{
		width: 145rpx;
		height: 40rpx;
		font-size: 20rpx;
		line-height: 40rpx;
		color: rgb(0,0,0);
		float: right;
		margin-top: 120rpx;
	}
	.midlle {
		/* display: flex; */
		width: 750rpx;
		height: 80rpx;
		border-top: 1rpx solid rgb(0,0,0);
	}
	.midlle .midlleC{
		width: 30%;
		/* margin-left: 20rpx; */
		padding-left: 35%;
		text-align: center;
		font-weight: bold;
		color: rgb(0,0,0);
		line-height: 80rpx;
	}
	.midlle image{
		width: 43rpx;
		height: 43rpx;
		float: right;
		margin-top: -58rpx;
		margin-right: 5rpx;
	}
</style>
