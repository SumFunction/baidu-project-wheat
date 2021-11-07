<template>
	<view class="mainbody">
		
			<HeaderDetail :headerData="headerData" :acc="acc"></HeaderDetail>
			<DetailMouble v-for="(item) in detailData" :item="item" v-if="item.content != null" :key="item.title"></DetailMouble>
		
	</view>
</template>

<script>
	import DetailMouble from"../component/detailMoudle/detailMoudle.vue"
	import HeaderDetail from"../component/headerDetail/headerDetail.vue"
	import allData from "../../public/data.js"
	export default {
		components:{
			DetailMouble,
			HeaderDetail
		},
		onLoad(options) {
			console.log(options,"看下我得到了吗load")
			this.index = options.index
			this.acc = options.acc;
			this.dataList = allData.dataList
			this.headerData = this.dataList[this.index]
			this.detailData[0].content = this.dataList[this.index].introduce
			this.detailData[1].content = this.dataList[this.index].feature
			this.detailData[2].content = this.dataList[this.index].measure
			this.detailData[3].content = this.dataList[this.index].regular
						// console.log(this.dataList);
		},
	
		data() {
			return {
				index: null,
				acc: null,
				dataList: null,
				headerData:{
					chiName: ' ',
					engName: ' ',
					time: ' ',
					acc: ' ',
					url1: null,
					url2: null
				},
				detailData:[{
					title: '简要介绍',
					content: null
				},
				{
					title: '表现特征',
					content: null
				},{
					title: '防治措施',
					content: null
				},{
					title: '流行规律',
					content: null
				}
				]
			}
		},
		methods: {
			
		}
	}
</script>

<style>
.mainbody{
	width: 750rpx;
	/* height: 1200rpx; */
	background-color: rgb(239,236,240);
	/* display: flex; */
	/* flex-direction: row; */
	border: 1rpx solid rgb(239,236,240);
}

</style>
