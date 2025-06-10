<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','classification_name') || $check_field('add','classification_name') || $check_field('set','classification_name')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="分类名称" prop="classification_name">
					<el-input id="classification_name" v-model="form['classification_name']" placeholder="请输入分类名称"
							  v-if="user_group === '管理员' || (form['film_classification_id'] && $check_field('set','classification_name')) || (!form['film_classification_id'] && $check_field('add','classification_name'))" :disabled="disabledObj['classification_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','classification_name')">{{form['classification_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','classification_number') || $check_field('add','classification_number') || $check_field('set','classification_number')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="分类编号" prop="classification_number">
					<el-input id="classification_number" v-model="form['classification_number']" placeholder="请输入分类编号"
							  v-if="user_group === '管理员' || (form['film_classification_id'] && $check_field('set','classification_number')) || (!form['film_classification_id'] && $check_field('add','classification_number'))" :disabled="disabledObj['classification_number_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','classification_number')">{{form['classification_number']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','classified_picture') || $check_field('add','classified_picture') || $check_field('set','classified_picture')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="分类图片" prop="classified_picture">
					<el-upload :disabled="disabledObj['classified_picture_isDisabled']" id="classified_picture" class="avatar-uploader" drag
						accept="image/gif, image/jpeg, image/png, image/jpg" action="" :http-request="upload_classified_picture"
						:show-file-list="false" v-if="user_group === '管理员' || (form['film_classification_id'] && $check_field('set','classified_picture')) || (!form['film_classification_id'] && $check_field('add','classified_picture'))">
						<img v-if="form['classified_picture']" :src="$fullUrl(form['classified_picture'])" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
					<el-image v-else-if="$check_field('get','classified_picture')" style="width: 100px; height: 100px"
						:src="$fullUrl(form['classified_picture'])" :preview-src-list="[$fullUrl(form['classified_picture'])]">
						<div slot="error" class="image-slot">
							<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
						</div>
					</el-image>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','classification_description') || $check_field('add','classification_description') || $check_field('set','classification_description')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="分类描述" prop="classification_description">
					<el-input type="textarea" id="classification_description" v-model="form['classification_description']" placeholder="请输入分类描述"
						v-if="user_group === '管理员' || (form['film_classification_id'] && $check_field('set','classification_description')) || (!form['film_classification_id'] && $check_field('add','classification_description'))" :disabled="disabledObj['classification_description_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','classification_description')">{{form['classification_description']}}</div>
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
				field: "film_classification_id",
				url_add: "~/api/film_classification/add?",
				url_set: "~/api/film_classification/set?",
				url_get_obj: "~/api/film_classification/get_obj?",
				url_upload: "~/api/film_classification/upload?",

				query: {
					"film_classification_id": 0,
				},

				form: {
					"classification_name":'', // 分类名称
					"classification_number":'', // 分类编号
					"classified_picture":'', // 分类图片
					"classification_description":'', // 分类描述
					"film_classification_id": 0, // ID

				},
				disabledObj:{
					"classification_name_isDisabled": false,
					"classification_number_isDisabled": false,
					"classified_picture_isDisabled": false,
					"classification_description_isDisabled": false,
				},

			}
		},
		methods: {
			/**
			 * 上传分类图片
			 * @param {Object} param图片参数
			 */
			upload_classified_picture(param){
				this.uploadFile(param.file, "classified_picture");
			},

			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
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
					bl = this.$check_action('/film_classification/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/film_classification/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/film_classification/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/film_classification/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/film_classification/view','get');
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
