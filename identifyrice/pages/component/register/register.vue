<template>
	<view>
		<view class="myimage">
			<image src="../../../static/user.png" style="width: 100rpx;height: 100rpx;" mode=""></image>
		</view>
		<view class="myform">
		        <uni-forms ref="form" :modelValue="formData" :rules="rules">
		            <uni-forms-item label="用户名" name="name">
		                <input type="text" v-model="formData.name"  placeholder="请输入用户名" />
		            </uni-forms-item>
		            <uni-forms-item label="密码" name="password">
		                <input class="input" v-model="formData.password" type="password" placeholder="请输入密码" />
		            </uni-forms-item>
		        </uni-forms>
				<button @click="register()">注册</button>
		</view>
		<uni-popup ref="popup" type="message">
		    <uni-popup-message type="error" message="用户名已存在" :duration="1800"></uni-popup-message>
		</uni-popup>
	</view>
</template>

<script>
	import UniForms from '../uni-forms/uni-forms.vue'
	import UniFormsItem from "../uni-forms-item/uni-forms-item.vue"
	import UniPopup from "../uni-popup/uni-popup.vue"
	import UniPopupMessage from "../uni-popup-message/uni-popup-message.vue"
	import a from "../../../public/address.js"
	export default {
		components:{
			UniForms,
			UniFormsItem,
			UniPopup,
			UniPopupMessage
		},
		data() {
			return {
				formData: {
				    name: '',
				    email: '',
					password: ''
				},
				rules: {
				    // 对name字段进行必填验证
				    name: {
				        rules: [{
				                required: true,
				                errorMessage: '请输入姓名',
				            },
				            {
				                minLength: 3,
				                maxLength: 11,
				                errorMessage: '姓名长度在 {minLength} 到 {maxLength} 个字符',
				            },
							
				        ]
				    },
				    // 对email字段进行必填验证
				    email: {
				        rules: [{
				            format: 'email',
				            errorMessage: '请输入正确的邮箱地址',
				        }]
				    },
					password: {
					    rules: [{
					        required: true,
					        errorMessage: '请输入密码',
					    },
						{
						    minLength: 7,
						    maxLength: 20,
						    errorMessage: '密码长度在 {minLength} 到 {maxLength} 个字符',
						},
						]
					}
				}
			}
		},
		methods: {
			register() {
			    this.$refs.form.validate().then(res=>{
			        console.log('表单数据信息：', res);
					uni.request({
						url: a.addressIp+'/generator/user/save',
						method:'POST',
						data: {
						        userName: res.name,
								password: res.password
						    },
						success: (res) => {
							console.log(res)
								if(res.data.code === 500){
									this.$refs.popup.open('top')
									return;
								}
						       else{
								this.$store.commit("modifySign",0)
								console.log(this.$store.state.sign,"aaa")
								uni.switchTab({
									url:'/pages/myPage/myPage'
								})
								}
						    }
					})
			    }).catch(err =>{
			        console.log('表单错误信息：', err);
					
			    })
			}
		}
	}
</script>

<style scoped>
uni-forms-item input{
	padding-top: 20rpx;
}
.myform button{
	margin: 0 auto;
	width: 50%;
	margin-top: 8rpx;
	color: rgb(19,184,206);
}

.myimage image{
	display: block;
	margin: 0 auto;
	margin-bottom: 80rpx;
	margin-top: 150rpx;
	
}
.myform{
	
	width: 90%;
	margin: 0 auto;
}

uni-popup{
	position: relative;
	left: 30%;
	}
</style>
