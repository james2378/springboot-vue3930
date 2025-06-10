<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','movie_title') || $check_field('add','movie_title') || $check_field('set','movie_title')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="电影名称" prop="movie_title">
					<el-input id="movie_title" v-model="form['movie_title']" placeholder="请输入电影名称"
							  v-if="user_group === '管理员' || (form['the_latest_movie_id'] && $check_field('set','movie_title')) || (!form['the_latest_movie_id'] && $check_field('add','movie_title'))" :disabled="disabledObj['movie_title_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','movie_title')">{{form['movie_title']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','movie_number') || $check_field('add','movie_number') || $check_field('set','movie_number')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="电影编号" prop="movie_number">
					<el-input id="movie_number" v-model="form['movie_number']" placeholder="请输入电影编号"
							  v-if="user_group === '管理员' || (form['the_latest_movie_id'] && $check_field('set','movie_number')) || (!form['the_latest_movie_id'] && $check_field('add','movie_number'))" :disabled="disabledObj['movie_number_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','movie_number')">{{form['movie_number']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','film_classification') || $check_field('add','film_classification') || $check_field('set','film_classification')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="电影分类" prop="film_classification">
					<el-input id="film_classification" v-model="form['film_classification']" placeholder="请输入电影分类"
							  v-if="user_group === '管理员' || (form['the_latest_movie_id'] && $check_field('set','film_classification')) || (!form['the_latest_movie_id'] && $check_field('add','film_classification'))" :disabled="disabledObj['film_classification_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','film_classification')">{{form['film_classification']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','number_of_sessions') || $check_field('add','number_of_sessions') || $check_field('set','number_of_sessions')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="场次" prop="number_of_sessions">
					<el-input id="number_of_sessions" v-model="form['number_of_sessions']" placeholder="请输入场次"
							  v-if="user_group === '管理员' || (form['the_latest_movie_id'] && $check_field('set','number_of_sessions')) || (!form['the_latest_movie_id'] && $check_field('add','number_of_sessions'))" :disabled="disabledObj['number_of_sessions_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','number_of_sessions')">{{form['number_of_sessions']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','cover') || $check_field('add','cover') || $check_field('set','cover')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="封面" prop="cover">
					<el-upload :disabled="disabledObj['cover_isDisabled']" id="cover" class="avatar-uploader" drag
						accept="image/gif, image/jpeg, image/png, image/jpg" action="" :http-request="upload_cover"
						:show-file-list="false" v-if="user_group === '管理员' || (form['the_latest_movie_id'] && $check_field('set','cover')) || (!form['the_latest_movie_id'] && $check_field('add','cover'))">
						<img v-if="form['cover']" :src="$fullUrl(form['cover'])" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
					<el-image v-else-if="$check_field('get','cover')" style="width: 100px; height: 100px"
						:src="$fullUrl(form['cover'])" :preview-src-list="[$fullUrl(form['cover'])]">
						<div slot="error" class="image-slot">
							<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
						</div>
					</el-image>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','price') || $check_field('add','price') || $check_field('set','price')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="价格" prop="price">
					<el-input id="price" v-model="form['price']" placeholder="请输入价格"
							  v-if="user_group === '管理员' || (form['the_latest_movie_id'] && $check_field('set','price')) || (!form['the_latest_movie_id'] && $check_field('add','price'))" :disabled="disabledObj['price_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','price')">{{form['price']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','movie_details') || $check_field('add','movie_details') || $check_field('set','movie_details')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="电影详情" prop="movie_details">
					<el-input id="movie_details" v-model="form['movie_details']" placeholder="请输入电影详情"
							  v-if="user_group === '管理员' || (form['the_latest_movie_id'] && $check_field('set','movie_details')) || (!form['the_latest_movie_id'] && $check_field('add','movie_details'))" :disabled="disabledObj['movie_details_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','movie_details')">{{form['movie_details']}}</div>
				</el-form-item>
			</el-col>
			<el-col :xs="24" :sm="12" :lg="8">
				<el-form-item>
					<el-button type="primary" @click="submit()">提交</el-button>
					<el-button @click="cancel()">取消</el-button>
				</el-form-item>
			</el-col>

		</el-form>
	</el-main>
</template>

<script>
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		data() {
			return {
				field: "the_latest_movie_id",
				url_add: "~/api/the_latest_movie/add?",
				url_set: "~/api/the_latest_movie/set?",
				url_get_obj: "~/api/the_latest_movie/get_obj?",
				url_upload: "~/api/the_latest_movie/upload?",

				query: {
					"the_latest_movie_id": 0,
				},

				form: {
					"movie_title":'', // 电影名称
					"movie_number":'', // 电影编号
					"film_classification":'', // 电影分类
					"number_of_sessions":'', // 场次
					"cover":'', // 封面
					"price":'', // 价格
					"movie_details":'', // 电影详情
					"the_latest_movie_id": 0, // ID

				},
				disabledObj:{
					"movie_title_isDisabled": false,
					"movie_number_isDisabled": false,
					"film_classification_isDisabled": false,
					"number_of_sessions_isDisabled": false,
					"cover_isDisabled": false,
					"price_isDisabled": false,
					"movie_details_isDisabled": false,
				},

			}
		},
		methods: {
			/**
			 * 上传封面
			 * @param {Object} param图片参数
			 */
			upload_cover(param){
				this.uploadFile(param.file, "cover");
			},

			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
				// 获取缓存数据附加
				form = $.db.get("form");
				$.push(this.form ,form);
				if(this.form && form){
					Object.keys(this.form).forEach(key => {
						Object.keys(form).forEach(dbKey => {
							// if(dbKey === "charging_standard"){
							// 	this.form['charging_rules'] = form[dbKey];
							// 	this.disabledObj['charging_rules_isDisabled'] = true;
							// };
							if(key === dbKey){
								this.disabledObj[key+'_isDisabled'] = true;
							}
						})
					})
				}
				$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){

			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/the_latest_movie/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/the_latest_movie/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/the_latest_movie/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/the_latest_movie/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/the_latest_movie/view','get');
					console.log(bl ? "你有视图查询权限视作有查询权限" : "你没有视图查询权限");
				}

				console.log(bl ? "具有当前页面的查看权，请注意这不代表你有字段的查看权" : "无权查看当前页，请注意即便有字段查询权限没有页面查询权限也不行");

				return bl;
			},
			/**
			 * 上传文件
			 * @param {Object} param
			 */
			uploadimg(param) {
				this.uploadFile(param.file, "avatar");
			},

		},
		created() {
		}
	}
</script>

<style>
	.avatar-uploader .el-upload {
		border: 1px dashed #d9d9d9;
		border-radius: 6px;
		cursor: pointer;
		position: relative;
		overflow: hidden;
	}

	.avatar-uploader .el-upload:hover {
		border-color: #409EFF;
	}

	.avatar-uploader-icon {
		font-size: 28px;
		color: #8c939d;
		width: 178px;
		height: 178px;
		line-height: 178px;
		text-align: center;
	}

	.avatar {
		width: 178px;
		height: 178px;
		display: block;
	}
</style>
