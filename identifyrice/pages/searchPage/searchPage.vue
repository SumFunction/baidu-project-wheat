<template>
	<view>
		<image  src="../../static/bg.png" style="width: 100%; height: 268.333px;" mode="widthFix" class="png">
			<view class="search">
			<input confirm-type="search" @confirm="twicegetData()" v-model="title" class="inputTro" placeholder="请输入内容" />
			<image  src="../../static/search.png" @click="twicegetData"></image>
			</view>
			<view class="midlle">
				<view class="midlleC">问题列表</view>
			</view>
			<view class="footer">
				
				<SearchList v-show="changeData == 0" v-for="(item,i) in problemData" :key="item.id" :i="i" :problemData="item" @click.native="redirect(item)"></SearchList>
				<SearchList v-show="changeData == 1" v-for="(item,i) in searchData" :key="item.id" :i="i" :problemData="item" @click.native="redirect(item)"></SearchList>
				
			</view>
		</image>
		<!-- :iconSize="30" -->
		<view v-show="isLoadMore">  
		                <!-- <uni-load-more :status="loadStatus" ></uni-load-more> -->
						<uni-load-more  status="loading" :contentText="{
							contentrefresh: '加载中'
						}"></uni-load-more>
		</view>
		<view style="text-align: center;" v-if="isflag">----我是有底线的----</view>
	</view>
</template>

<script>
	import SearchList from '../component/searchlist/searchlist.vue'
	import a from "../../public/address.js"
	export default {
		components:{
			SearchList
		},
		data() {
			return {
				problemData: [],
				searchData:[],
				title: '',
				page: 0,
				limit: 5,
				isflag: false,
				changeData: 0,
				isLoadMore: false
				
			}
		},
		onShow() {
			this.page = 1;
			this.changeData = 0;
			this.problemData = [];
			this.title = '';
			this.isflag = false;
			this.getProblemData();
			// if(!this.isflag && this.changeData == 0){
			// 	this.page++
			// 	this.getProblemData()
			// }
		},
		onReachBottom() {
			console.log("我到底部了")
			if(!this.isflag &&  !this.isLoadMore){
				this.isLoadMore = true
				setTimeout(() => {
					this.page++
					this.getProblemData()
				},500)
			}
		},
		methods: {
			twicegetData(){
				if(this.title == ''){
					this.changeData = 0
					return;
				}
				let that = this
				uni.request({
					url: a.addressIp+"/generator/problem/moulist?title=" + that.title,
					method: "GET",
					success(res) {
						console.log(res)
						that.changeData = 1;
						that.searchData = res.data.page
						that.splitStr(that.searchData)
					}
				})
			},
			getProblemData(){
				let that = this
				uni.request({
					url: a.addressIp+"/generator/problem/pagequery?page=" + that.page + "&limit=" + that.limit,
					method: "GET",
					success(res) {
						// console.log(res)
						that.isLoadMore = false
						if(res.data.page.length < that.limit)
						{
							that.isflag = true
							}
						that.problemData = [...that.problemData,...res.data.page]
						that.splitStr(that.problemData)
					}
				})
			},
			redirect(item){
				console.log(item,"item")
				this.$store.commit("modifyProblemData",item)
				
				uni.navigateTo({
						url:`/pages/problem/problem`
					})
			
			},
			splitStr(value){
				for(let i = 0; i < value.length; i++){
					value[i].publishTime = value[i].publishTime.split("T")[0]
				}
			}
		}
	}
</script>

<style scoped>

.plcolor{
color: #bbb;
}
.search{
	width: 420rpx;
	height: 50rpx;
	display: flex;
	position: absolute;
	top:  348rpx;
	background-color: white;
	border-radius: 40rpx;
	left: 165rpx;
}
.search input{
	width: 350rpx;
	background-color: white;
	border-radius: 40rpx;
	padding-left: 17rpx;
	font-size: 30rpx;
	height: 40rpx;
	line-height: 0rpx;
	margin-top: -3rpx;
}
.search image{
	width: 32rpx;
	height: 32rpx;
	padding-top: 10rpx;
	
}

.midlle {
	/* display: flex; */
	width: 750rpx;
	height: 50rpx;
}
.midlle .midlleC{
	width: 30%;
	/* margin-left: 20rpx; */
	padding-left: 35%;
	text-align: center;
	font-weight: bold;
	color: black;
}

.footer{
	width: 750rpx;
	background-color: rgba(189,195,199,0.15);
	/* height: 1000rpx; */
}


</style>
