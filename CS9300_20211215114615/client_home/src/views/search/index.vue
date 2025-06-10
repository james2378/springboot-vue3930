<template>
  <div class="page_search">
	<div class="warp">
	  <div class="container">
		<div class="row">
		  <div class="col-12">
			<div class="card_result_search">
			  <div class="title">搜索结果</div>
				<!-- 文章搜索结果 -->
			  <list_result_search
				:list="result_article"
				title="电影资讯"
				source_table="article"
			  ></list_result_search>
				<!-- 论坛搜索结果 -->
			  <list_result_search
				:list="result_forum"
				title="评价中心"
				source_table="forum"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/the_latest_movie/list', 'get')"
				:list="result_the_latest_movie_movie_title"
				title="最新电影电影名称"
				source_table="the_latest_movie"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/the_latest_movie/list', 'get')"
				:list="result_the_latest_movie_film_classification"
				title="最新电影电影分类"
				source_table="the_latest_movie"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/the_latest_movie/list', 'get')"
				:list="result_the_latest_movie_number_of_sessions"
				title="最新电影场次"
				source_table="the_latest_movie"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/user_registration/list', 'get')"
				:list="result_user_registration_gender"
				title="用户注册性别"
				source_table="user_registration"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/film_classification/list', 'get')"
				:list="result_film_classification_classification_name"
				title="电影分类分类名称"
				source_table="film_classification"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/film_classification/list', 'get')"
				:list="result_film_classification_classification_number"
				title="电影分类分类编号"
				source_table="film_classification"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/session_management/list', 'get')"
				:list="result_session_management_venue_name"
				title="场次管理场次名称"
				source_table="session_management"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/session_management/list', 'get')"
				:list="result_session_management_session_number"
				title="场次管理场次编号"
				source_table="session_management"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/film/list', 'get')"
				:list="result_film_movie_title"
				title="电影电影名称"
				source_table="film"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/film/list', 'get')"
				:list="result_film_film_classification"
				title="电影电影分类"
				source_table="film"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/film/list', 'get')"
				:list="result_film_number_of_sessions"
				title="电影场次"
				source_table="film"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/order_center/list', 'get')"
				:list="result_order_center_order_number"
				title="订单中心订单编号"
				source_table="order_center"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/order_center/list', 'get')"
				:list="result_order_center_number_of_sessions"
				title="订单中心场次"
				source_table="order_center"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/order_center/list', 'get')"
				:list="result_order_center_movie_title"
				title="订单中心电影名称"
				source_table="order_center"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/cancellation_of_order/list', 'get')"
				:list="result_cancellation_of_order_movie_title"
				title="取消订单电影名称"
				source_table="cancellation_of_order"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/cancellation_of_order/list', 'get')"
				:list="result_cancellation_of_order_payment_status"
				title="取消订单支付情况"
				source_table="cancellation_of_order"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/movie_attendance/list', 'get')"
				:list="result_movie_attendance_movie_title"
				title="电影上座率电影名称"
				source_table="movie_attendance"
			  ></list_result_search>
			</div>
		  </div>
		</div>
	  </div>
	</div>
  </div>
</template>

<script>
import mixin from "../../mixins/page.js";
import list_result_search from "../../components/diy/list_result_search.vue";

export default {
  mixins: [mixin],
  data() {
	return {
	  "query": {
		word: "",
	  },
	  "result_article": [],
	  "result_forum": [],
			"result_the_latest_movie_movie_title":[],
			"result_the_latest_movie_film_classification":[],
			"result_the_latest_movie_number_of_sessions":[],
			"result_user_registration_gender":[],
			"result_film_classification_classification_name":[],
			"result_film_classification_classification_number":[],
			"result_session_management_venue_name":[],
			"result_session_management_session_number":[],
			"result_film_movie_title":[],
			"result_film_film_classification":[],
			"result_film_number_of_sessions":[],
			"result_order_center_order_number":[],
			"result_order_center_number_of_sessions":[],
			"result_order_center_movie_title":[],
			"result_cancellation_of_order_movie_title":[],
			"result_cancellation_of_order_payment_status":[],
			"result_movie_attendance_movie_title":[],
	};
  },
  methods: {
	/**
	 * 获取文章
	 */
	get_article() {
	  this.$get("~/api/article/get_list?like=0", { page: 1, size: 10, title: this.query.word }, (json) => {
		if (json.result) {
		  this.result_article = json.result.list;
		}
	  });
	},
	/**
	 * 获取评价中心
	 */
	get_forum() {
	  this.$get("~/api/forum/get_list?like=0", { page: 1, size: 10, title: this.query.word }, (json) => {
		if (json.result) {
		  this.result_forum = json.result.list;
		}
	  });
	},
	/**
	 * 获取movie_title
	 */
	get_the_latest_movie_movie_title(){
		this.$get("~/api/the_latest_movie/get_list?like=0", { page: 1, size: 10, "movie_title": this.query.word }, (json) => {
		  if (json.result) {
			var result_the_latest_movie_movie_title = json.result.list;
			result_the_latest_movie_movie_title.map(o => o.title = o['movie_title'])
	  			this.result_the_latest_movie_movie_title = result_the_latest_movie_movie_title
		 	}
		});
	},
	/**
	 * 获取film_classification
	 */
	get_the_latest_movie_film_classification(){
		this.$get("~/api/the_latest_movie/get_list?like=0", { page: 1, size: 10, "film_classification": this.query.word }, (json) => {
		  if (json.result) {
			var result_the_latest_movie_film_classification = json.result.list;
			result_the_latest_movie_film_classification.map(o => o.title = o['film_classification'])
	  			this.result_the_latest_movie_film_classification = result_the_latest_movie_film_classification
		 	}
		});
	},
	/**
	 * 获取number_of_sessions
	 */
	get_the_latest_movie_number_of_sessions(){
		this.$get("~/api/the_latest_movie/get_list?like=0", { page: 1, size: 10, "number_of_sessions": this.query.word }, (json) => {
		  if (json.result) {
			var result_the_latest_movie_number_of_sessions = json.result.list;
			result_the_latest_movie_number_of_sessions.map(o => o.title = o['number_of_sessions'])
	  			this.result_the_latest_movie_number_of_sessions = result_the_latest_movie_number_of_sessions
		 	}
		});
	},
	/**
	 * 获取gender
	 */
	get_user_registration_gender(){
		this.$get("~/api/user_registration/get_list?like=0", { page: 1, size: 10, "gender": this.query.word }, (json) => {
		  if (json.result) {
			var result_user_registration_gender = json.result.list;
			result_user_registration_gender.map(o => o.title = o['gender'])
	  			this.result_user_registration_gender = result_user_registration_gender
		 	}
		});
	},
	/**
	 * 获取classification_name
	 */
	get_film_classification_classification_name(){
		this.$get("~/api/film_classification/get_list?like=0", { page: 1, size: 10, "classification_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_film_classification_classification_name = json.result.list;
			result_film_classification_classification_name.map(o => o.title = o['classification_name'])
	  			this.result_film_classification_classification_name = result_film_classification_classification_name
		 	}
		});
	},
	/**
	 * 获取classification_number
	 */
	get_film_classification_classification_number(){
		this.$get("~/api/film_classification/get_list?like=0", { page: 1, size: 10, "classification_number": this.query.word }, (json) => {
		  if (json.result) {
			var result_film_classification_classification_number = json.result.list;
			result_film_classification_classification_number.map(o => o.title = o['classification_number'])
	  			this.result_film_classification_classification_number = result_film_classification_classification_number
		 	}
		});
	},
	/**
	 * 获取venue_name
	 */
	get_session_management_venue_name(){
		this.$get("~/api/session_management/get_list?like=0", { page: 1, size: 10, "venue_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_session_management_venue_name = json.result.list;
			result_session_management_venue_name.map(o => o.title = o['venue_name'])
	  			this.result_session_management_venue_name = result_session_management_venue_name
		 	}
		});
	},
	/**
	 * 获取session_number
	 */
	get_session_management_session_number(){
		this.$get("~/api/session_management/get_list?like=0", { page: 1, size: 10, "session_number": this.query.word }, (json) => {
		  if (json.result) {
			var result_session_management_session_number = json.result.list;
			result_session_management_session_number.map(o => o.title = o['session_number'])
	  			this.result_session_management_session_number = result_session_management_session_number
		 	}
		});
	},
	/**
	 * 获取movie_title
	 */
	get_film_movie_title(){
		this.$get("~/api/film/get_list?like=0", { page: 1, size: 10, "movie_title": this.query.word }, (json) => {
		  if (json.result) {
			var result_film_movie_title = json.result.list;
			result_film_movie_title.map(o => o.title = o['movie_title'])
	  			this.result_film_movie_title = result_film_movie_title
		 	}
		});
	},
	/**
	 * 获取film_classification
	 */
	get_film_film_classification(){
		this.$get("~/api/film/get_list?like=0", { page: 1, size: 10, "film_classification": this.query.word }, (json) => {
		  if (json.result) {
			var result_film_film_classification = json.result.list;
			result_film_film_classification.map(o => o.title = o['film_classification'])
	  			this.result_film_film_classification = result_film_film_classification
		 	}
		});
	},
	/**
	 * 获取number_of_sessions
	 */
	get_film_number_of_sessions(){
		this.$get("~/api/film/get_list?like=0", { page: 1, size: 10, "number_of_sessions": this.query.word }, (json) => {
		  if (json.result) {
			var result_film_number_of_sessions = json.result.list;
			result_film_number_of_sessions.map(o => o.title = o['number_of_sessions'])
	  			this.result_film_number_of_sessions = result_film_number_of_sessions
		 	}
		});
	},
	/**
	 * 获取order_number
	 */
	get_order_center_order_number(){
		this.$get("~/api/order_center/get_list?like=0", { page: 1, size: 10, "order_number": this.query.word }, (json) => {
		  if (json.result) {
			var result_order_center_order_number = json.result.list;
			result_order_center_order_number.map(o => o.title = o['order_number'])
	  			this.result_order_center_order_number = result_order_center_order_number
		 	}
		});
	},
	/**
	 * 获取number_of_sessions
	 */
	get_order_center_number_of_sessions(){
		this.$get("~/api/order_center/get_list?like=0", { page: 1, size: 10, "number_of_sessions": this.query.word }, (json) => {
		  if (json.result) {
			var result_order_center_number_of_sessions = json.result.list;
			result_order_center_number_of_sessions.map(o => o.title = o['number_of_sessions'])
	  			this.result_order_center_number_of_sessions = result_order_center_number_of_sessions
		 	}
		});
	},
	/**
	 * 获取movie_title
	 */
	get_order_center_movie_title(){
		this.$get("~/api/order_center/get_list?like=0", { page: 1, size: 10, "movie_title": this.query.word }, (json) => {
		  if (json.result) {
			var result_order_center_movie_title = json.result.list;
			result_order_center_movie_title.map(o => o.title = o['movie_title'])
	  			this.result_order_center_movie_title = result_order_center_movie_title
		 	}
		});
	},
	/**
	 * 获取movie_title
	 */
	get_cancellation_of_order_movie_title(){
		this.$get("~/api/cancellation_of_order/get_list?like=0", { page: 1, size: 10, "movie_title": this.query.word }, (json) => {
		  if (json.result) {
			var result_cancellation_of_order_movie_title = json.result.list;
			result_cancellation_of_order_movie_title.map(o => o.title = o['movie_title'])
	  			this.result_cancellation_of_order_movie_title = result_cancellation_of_order_movie_title
		 	}
		});
	},
	/**
	 * 获取payment_status
	 */
	get_cancellation_of_order_payment_status(){
		this.$get("~/api/cancellation_of_order/get_list?like=0", { page: 1, size: 10, "payment_status": this.query.word }, (json) => {
		  if (json.result) {
			var result_cancellation_of_order_payment_status = json.result.list;
			result_cancellation_of_order_payment_status.map(o => o.title = o['payment_status'])
	  			this.result_cancellation_of_order_payment_status = result_cancellation_of_order_payment_status
		 	}
		});
	},
	/**
	 * 获取movie_title
	 */
	get_movie_attendance_movie_title(){
		this.$get("~/api/movie_attendance/get_list?like=0", { page: 1, size: 10, "movie_title": this.query.word }, (json) => {
		  if (json.result) {
			var result_movie_attendance_movie_title = json.result.list;
			result_movie_attendance_movie_title.map(o => o.title = o['movie_title'])
	  			this.result_movie_attendance_movie_title = result_movie_attendance_movie_title
		 	}
		});
	},

  },
  components: { list_result_search },
	created(){
    this.query.word = this.$route.query.word || "";
  },
  mounted() {
	this.get_article();
	this.get_forum();
		this.get_the_latest_movie_movie_title();
		this.get_the_latest_movie_film_classification();
		this.get_the_latest_movie_number_of_sessions();
		this.get_user_registration_gender();
		this.get_film_classification_classification_name();
		this.get_film_classification_classification_number();
		this.get_session_management_venue_name();
		this.get_session_management_session_number();
		this.get_film_movie_title();
		this.get_film_film_classification();
		this.get_film_number_of_sessions();
		this.get_order_center_order_number();
		this.get_order_center_number_of_sessions();
		this.get_order_center_movie_title();
		this.get_cancellation_of_order_movie_title();
		this.get_cancellation_of_order_payment_status();
		this.get_movie_attendance_movie_title();
  },
  watch: {
	$route() {
	  $.push(this.query, this.$route.query);
	  this.get_article();
	  this.get_forum();
	  this.get_the_latest_movie_movie_title();
	  this.get_the_latest_movie_film_classification();
	  this.get_the_latest_movie_number_of_sessions();
	  this.get_user_registration_gender();
	  this.get_film_classification_classification_name();
	  this.get_film_classification_classification_number();
	  this.get_session_management_venue_name();
	  this.get_session_management_session_number();
	  this.get_film_movie_title();
	  this.get_film_film_classification();
	  this.get_film_number_of_sessions();
	  this.get_order_center_order_number();
	  this.get_order_center_number_of_sessions();
	  this.get_order_center_movie_title();
	  this.get_cancellation_of_order_movie_title();
	  this.get_cancellation_of_order_payment_status();
	  this.get_movie_attendance_movie_title();
	},
  },
};
</script>

<style scoped>
.card_search {
  text-align: center;
}
.card_result_search>.title {
  text-align: center;
  padding: 10px 0;
}
</style>
