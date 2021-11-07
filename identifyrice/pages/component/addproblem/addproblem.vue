<template>
	<view>
		<view class="midlle">
			<view class="midlleC">添加问题</view>
		</view>
		<view class="myform">
			<uni-forms ref="form" :modelValue="formData" :rules="rules">
				<uni-forms-item label="问题名称" name="title">
					<input type="text" v-model="formData.title" placeholder="请输入问题名称" />
				</uni-forms-item>
				<uni-forms-item label="问题内容" name="title">
				</uni-forms-item>
				<jinEdit placeholder="请输入内容" @editOk="editOk" :uploadFileUrl="imgUrl"></jinEdit>
			</uni-forms>
			<!-- <button @click="submit()">提交</button> -->
		</view>
		<uni-popup ref="popup" type="message">
		    <uni-popup-message type="error" message="问题名已存在" :duration="1800"></uni-popup-message>
		</uni-popup>
	</view>
</template>

<script>
	import {
		mapState,
		mapMutations
	} from 'vuex';
	import UniForms from '../uni-forms/uni-forms.vue'
	import UniFormsItem from "../uni-forms-item/uni-forms-item.vue"
	import UniPopup from "../uni-popup/uni-popup.vue"
	import UniPopupMessage from "../uni-popup-message/uni-popup-message.vue"
	import jinEdit from "../../../components/jin-edit/jin-edit.vue"
	import a from "../../../public/address.js"
	export default {
		components: {
			UniForms,
			UniFormsItem,
			UniPopup,
			UniPopupMessage,
			jinEdit
		},
		computed: {
			//将需要数据的名字以参数的形式放在数组参数中，state中的东西，只能放到computed，不能让放到data对象中  
		},
		onLoad() {

		},
		onReady() {

		},
		data() {
			return {
				imgUrl: a.addressIp+'/upload',
				html: '',
				placeholder: '开始输入...',
				formData: {
					title: '',
					content: '',
				},
				rules: {
					// 对name字段进行必填验证
					title: {
						rules: [{
							required: true,
							errorMessage: '请输入问题名称',
						}]
					},
				}

			}
		},
		methods: {

			editOk(res) {
				this.html = res.html;
				this.submit();
			},
			submit() {
				this.$refs.form.validate().then(res => {
					console.log('表单数据信息：', res);
					let that = this
					uni.request({
						url: a.addressIp+'/generator/problem/save',
						method: 'POST',
						data:{
							name: res.title,
							content: that.html,
							uid: that.$store.state.userId,
							uname: that.$store.state.userName
						},
						success(res) {
							console.log(res,"我成功了吗")
							if(res.data.code == 500){
								that.$refs.popup.open("top")
								
							}else
							{
								that.$store.commit("modifySign",1)
								uni.switchTab({
									url:'/pages/myPage/myPage'
								})
							}
							
						}
					})
				}).catch(err => {
					console.log('表单错误信息：', err);
				})
				// let a =
				
				// this.$store.commit('modify', this.html)
				
			}
		}
	}
</script>

<style scoped>
	.midlle {
		/* display: flex; */
		width: 750rpx;
		height: 80rpx;
		border-top: 1rpx solid #13B8CE;
	}

	.midlle .midlleC {
		width: 30%;
		/* margin-left: 20rpx; */
		padding-left: 35%;
		text-align: center;
		font-weight: bold;
		color: #13B8CE;
		line-height: 80rpx;
		/* margin-top: 100rpx; */
	}

	uni-forms-item input {
		padding-top: 15rpx;
	}

	.myform button {
		margin: 0 auto;
		width: 20%;
		margin-top: 8rpx;
		font-size: 30rpx;
		color: rgb(19, 184, 206);
	}

	.myimage image {
		display: block;
		margin: 0 auto;
		margin-bottom: 80rpx;
		margin-top: 150rpx;

	}

	.myform {

		width: 90%;
		margin: 0 auto;
	}

	uni-popup {
		position: relative;
		left: 30%;
	}
</style>
