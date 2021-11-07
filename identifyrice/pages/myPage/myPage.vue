<template>
	<view>
		
		<Login v-if="isflag == 0" v-on:changeVisible="changeVisible" :sign="sign"></Login>
		<Member v-if="isflag != 0"  @changeVisible="changeVisible" :problemData="problemData" @logout="logout"></Member>
		<uni-popup ref="popup" type="message">
		    <uni-popup-message  type="success" message="注册成功" :duration="1200"></uni-popup-message>
		</uni-popup>
		<uni-popup ref="popup1" type="message">
		    <uni-popup-message  type="success" message="问题添加成功" :duration="1200"></uni-popup-message>
		</uni-popup>
	</view>
</template>

<script>
	import Login from '../component/login/login.vue'
	import Member from '../component/member/member.vue'
	import UniPopup from '../component/uni-popup/uni-popup.vue'
	import UniPopupMessage from '../component/uni-popup-message/uni-popup-message.vue'
	import a from "../../public/address.js"
	export default {
		components:{
			Login,
			Member,
			UniPopup,
			UniPopupMessage
		},
		onLoad() {
			
		},
		watch:{
			isflag(newName,oldName){
					if(newName != 0){
						let that = this
						uni.request({
							url: a.addressIp+'/generator/problem/list',
							method: "POST",
							data:{
								uid: this.$store.state.userId
							},
							success(res) {
								console.log(res,"我的res")
								that.problemData = res.data.page
								that.splitStr(that.problemData)
								console.log(that.problemData,"收到的problemData")
							}
						})
						
					}
				
			}
		},
		onShow() {
			console.log(this.$store.state.sign)
			
			if(this.$store.state.sign == 0){
				this.$refs.popup.open("top")
				this.$store.commit("modifySign",100)
			}
			if(this.$store.state.sign == 1){
				this.$refs.popup1.open("top")
				this.$store.commit("modifySign",100)
			}
			if(this.isflag != 0){
				let that = this
				uni.request({
					url: a.addressIp+'/generator/problem/list',
					method: "POST",
					data:{
						uid: this.$store.state.userId
					},
					success(res) {
						console.log(res,"我的res")
						that.problemData = res.data.page
						that.splitStr(that.problemData)
						console.log(that.problemData,"收到的problemData")
					}
				})
			}
			
		},
		data() {
			return {
				isflag: 0,//选择展示哪个页面
				sign: 2,
				problemData:[]
			}
		},
		methods: {
			changeVisible(){
				this.isflag++;
				// console.log("我没出现？")
			},
			logout(){
				this.isflag = 0;
			},
			splitStr(value){
				console.log(value,"我是value")
				for(let i = 0; i < value.length; i++){
					value[i].publishTime = value[i].publishTime.split("T")[0]
				}
			}
		}
	}
</script>

<style scoped>

</style>
