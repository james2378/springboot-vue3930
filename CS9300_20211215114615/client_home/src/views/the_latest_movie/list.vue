<template>
	<div class="diy_list page_the_latest_movie" id="the_latest_movie_list">
		<div class="warp">
			<div class="container diy_list_container">
				<div class="diy_list_title">
					<div class="col">
						<span class="title">最新电影列表</span>
					</div>
				</div>
				<div class="row diy_list_search">
					<div class="col">
						<!-- 搜索栏 -->
						<div class="view">							
							<span class="diy_list_search_title">关键字搜索：</span>
							<b-form-input size="sm" class="mr-sm-2" placeholder="电影名称搜索" v-model="query['movie_title']" />
							<b-form-input size="sm" class="mr-sm-2" placeholder="电影分类搜索" v-model="query['film_classification']" />
							<b-form-input size="sm" class="mr-sm-2" placeholder="场次搜索" v-model="query['number_of_sessions']" />
							<b-button size="sm" @click="search()" >
								<b-icon icon="search"/>
							</b-button>
						</div>
						<!-- /搜索栏 -->
					</div>
				</div>
				<div class="diy_list_select_box">
					<span class="diy_list_select_title">下拉搜索：</span>
						<div class="diy_list_dropdown_box">
						<div class="col">
							<!-- 筛选 -->
							<div class="view">
							</div>
							<!-- /筛选 -->
						</div>
					</div>
					<div class="diy_list_sort_box">
						<div class="col">
							<!-- 排序 -->
							<div class="view">
								<b-dropdown text="排序" variant="outline-dark" left>
										<b-dropdown-item v-for="(o, i) in list_sort" :key="i" @click="set_sort(o)" >
												{{ o.name }}
										</b-dropdown-item>
								</b-dropdown>
							</div>
							<!--/排序 -->
						</div>
					</div>
				</div>
				<div class="row diy_list_box">
					<div class="col">
						<!-- 列表 -->
						<list_the_latest_movie :list="list" />
						<!-- /列表 -->
					</div>
				</div>
				<div class="row diy_list_page_box">
					<div class="col overflow-auto flex_cc">
						<!-- 分页器 -->
						<diy_pager v-model="query['page']" :size="query['size']" :count="count" v-on:toPage="toPage" v-on:toSize="toSize" ></diy_pager>
						<!-- /分页器 -->
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	import list_the_latest_movie from "@/components/diy/list_the_latest_movie.vue";
	import diy_pager from "@/components/diy/diy_pager";
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		components: {
			diy_pager,
			list_the_latest_movie
		},
		data() {
			return {
				url_get_list: "~/api/the_latest_movie/get_list?like=0",

				// 查询条件
				query: {
					keyword: "",
					page: 1,
					size: 10,
					"movie_title": "", // 电影名称
					"film_classification": "", // 电影分类
					"number_of_sessions": "", // 场次
				},

				// 排序内容
				list_sort: [{
						name: "创建时间从高到低",
						value: "`create_time` desc",
					},
					{
						name: "创建时间从低到高",
						value: "`create_time` asc",
					},
					{
						name: "更新时间从高到低",
						value: "`update_time` desc",
					},
					{
						name: "更新时间从低到高",
						value: "`update_time` asc",
					},
					{
						name: "电影名称正序",
						value: "`movie_title` asc",
					},
					{
						name: "电影名称倒序",
						value: "`movie_title` desc",
					},
					{
						name: "电影分类正序",
						value: "`film_classification` asc",
					},
					{
						name: "电影分类倒序",
						value: "`film_classification` desc",
					},
					{
						name: "场次正序",
						value: "`number_of_sessions` asc",
					},
					{
						name: "场次倒序",
						value: "`number_of_sessions` desc",
					},
				],
			}
		},
		methods: {
			/**
			 * 筛选选择
			 */
			filter_set(o,key) {
			    if (o == "全部") {
			        this.query[key] = "";
			    } else {
			        this.query[key] = o;
			    }
			    this.search();
			},

			/**
			 * 排序
			 */
			set_sort(o) {
			    this.query.orderby = o.value;
			    this.search();
			},

			/**
			 * 筛选
			 */

			/**
			 * 重置
			 */
			reset() {
				this.query.movie_title = ""
				this.query.film_classification = ""
				this.query.number_of_sessions = ""
				this.search();
			},

			// 返回条数
			toSize(i){
				this.query.size = i;
				this.first();
			},

			// 返回页数
			toPage(i){
				this.query.page = i;
				this.first();
			},
		},
		computed: {
		},
		created() {
		}
	}
</script>

<style>
</style>
