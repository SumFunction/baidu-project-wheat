import Vue from 'vue';
import Vuex from 'vuex';
Vue.use(Vuex);

const store = new Vuex.Store({  
    state: {  
        
		ahtml: '你真棒',
		sign: 100,
		userName: '',
		password: '',
		userId: '',
		problemData: {}
		// commentList:[ {
		// 	id: 1,
		// 	userId: 3,
		// 	avatar: 'https://dss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1976832114,2993359804&fm=26&gp=0.jpg',
		// 	name: '小心',
		// 	time: 1617086756,
		// 	content: '中国外交官这样讽加拿大总理',
		// }]
    },  
    mutations: {  
        modify(state, ahtml) {  
            console.log("你这个菜鸡")
			console.log(state,"????????????")
          // console.log(provider,"!!!!!!!!!!!!")
			state.ahtml = ahtml
        },
		 modifySign(state,sign){
			 state.sign = sign;
		 },
		 addUser(state, modify){
			 state.userName = modify.userName
			 state.password = modify.password
			 state.userId = modify.id
		 },
		
		 modifyProblemData(state,problemData){
			 state.problemData = problemData
		 }
		//  modifyCommentList(state,problemComment){
		// 	 console.log("我进来了",problemComment)
		// // 	 state.commentList = [ {
		// // 	id: 1,
		// // 	userId: 3,
		// // 	avatar: 'https://dss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1976832114,2993359804&fm=26&gp=0.jpg',
		// // 	name: '小心',
		// // 	time: 1617086756,
		// // 	content: '中国外交官这样讽加拿大总理',
		// // }]
		// state.commentList = [{}]
		// 	for( let i = 0; i < problemComment.length; i++){
				
		// 		state.commentList[i] = {}
		// 		state.commentList[i].id = problemComment[i].id
		// 		state.commentList[i].userId = problemComment[i].uid
		// 		state.commentList[i].avatar = 'https://dss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1976832114,2993359804&fm=26&gp=0.jpg'
		// 		state.commentList[i].name = problemComment[i].username
		// 		state.commentList[i].time =  problemComment[i].publishTime
		// 		state.commentList[i].content = problemComment[i].content
		// 	}
		//  }
    }  
})

export default store
