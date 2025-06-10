<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','venue_name') || $check_field('add','venue_name') || $check_field('set','venue_name')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="场次名称" prop="venue_name">
					<el-input id="venue_name" v-model="form['venue_name']" placeholder="请输入场次名称"
							  v-if="user_group === '管理员' || (form['session_management_id'] && $check_field('set','venue_name')) || (!form['session_management_id'] && $check_field('add','venue_name'))" :disabled="disabledObj['venue_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','venue_name')">{{form['venue_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','session_number') || $check_field('add','session_number') || $check_field('set','session_number')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="场次编号" prop="session_number">
					<el-input id="session_number" v-model="form['session_number']" placeholder="请输入场次编号"
							  v-if="user_group === '管理员' || (form['session_management_id'] && $check_field('set','session_number')) || (!form['session_management_id'] && $check_field('add','session_number'))" :disabled="disabledObj['session_number_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','session_number')">{{form['session_number']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','session_details') || $check_field('add','session_details') || $check_field('set','session_details')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="场次详情" prop="session_details">
					<el-input type="textarea" id="session_details" v-model="form['session_details']" placeholder="请输入场次详情"
						v-if="user_group === '管理员' || (form['session_management_id'] && $check_field('set','session_details')) || (!form['session_management_id'] && $check_field('add','session_details'))" :disabled="disabledObj['session_details_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','session_details')">{{form['session_details']}}</div>
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
				field: "session_management_id",
				url_add: "~/api/session_management/add?",
				url_set: "~/api/session_management/set?",
				url_get_obj: "~/api/session_management/get_obj?",
				url_upload: "~/api/session_management/upload?",

				query: {
					"session_management_id": 0,
				},

				form: {
					"venue_name":'', // 场次名称
					"session_number":'', // 场次编号
					"session_details":'', // 场次详情
					"session_management_id": 0, // ID

				},
				disabledObj:{
					"venue_name_isDisabled": false,
					"session_number_isDisabled": false,
					"session_details_isDisabled": false,
				},

			}
		},
		methods: {

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
					bl = this.$check_action('/session_management/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/session_management/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/session_management/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/session_management/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/session_management/view','get');
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
