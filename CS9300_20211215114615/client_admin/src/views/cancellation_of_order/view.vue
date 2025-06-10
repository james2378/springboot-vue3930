<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','order_number') || $check_field('add','order_number') || $check_field('set','order_number')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="订单编号" prop="order_number">
					<el-input id="order_number" v-model="form['order_number']" placeholder="请输入订单编号"
							  v-if="user_group === '管理员' || (form['cancellation_of_order_id'] && $check_field('set','order_number')) || (!form['cancellation_of_order_id'] && $check_field('add','order_number'))" :disabled="disabledObj['order_number_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','order_number')">{{form['order_number']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','movie_title') || $check_field('add','movie_title') || $check_field('set','movie_title')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="电影名称" prop="movie_title">
					<el-input id="movie_title" v-model="form['movie_title']" placeholder="请输入电影名称"
							  v-if="user_group === '管理员' || (form['cancellation_of_order_id'] && $check_field('set','movie_title')) || (!form['cancellation_of_order_id'] && $check_field('add','movie_title'))" :disabled="disabledObj['movie_title_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','movie_title')">{{form['movie_title']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','number_of_sessions') || $check_field('add','number_of_sessions') || $check_field('set','number_of_sessions')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="场次" prop="number_of_sessions">
					<el-input id="number_of_sessions" v-model="form['number_of_sessions']" placeholder="请输入场次"
							  v-if="user_group === '管理员' || (form['cancellation_of_order_id'] && $check_field('set','number_of_sessions')) || (!form['cancellation_of_order_id'] && $check_field('add','number_of_sessions'))" :disabled="disabledObj['number_of_sessions_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','number_of_sessions')">{{form['number_of_sessions']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','number_of_purchases') || $check_field('add','number_of_purchases') || $check_field('set','number_of_purchases')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="购买数" prop="number_of_purchases">
					<el-input id="number_of_purchases" v-model="form['number_of_purchases']" placeholder="请输入购买数"
							  v-if="user_group === '管理员' || (form['cancellation_of_order_id'] && $check_field('set','number_of_purchases')) || (!form['cancellation_of_order_id'] && $check_field('add','number_of_purchases'))" :disabled="disabledObj['number_of_purchases_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','number_of_purchases')">{{form['number_of_purchases']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','total') || $check_field('add','total') || $check_field('set','total')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="合计" prop="total">
					<el-input id="total" v-model="form['total']" placeholder="请输入合计"
							  v-if="user_group === '管理员' || (form['cancellation_of_order_id'] && $check_field('set','total')) || (!form['cancellation_of_order_id'] && $check_field('add','total'))" :disabled="disabledObj['total_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','total')">{{form['total']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','purchaser') || $check_field('add','purchaser') || $check_field('set','purchaser')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="购买人" prop="purchaser">
						<el-select v-if="user_group === '管理员' || (form['cancellation_of_order_id'] && $check_field('set','purchaser')) || (!form['cancellation_of_order_id'] && $check_field('add','purchaser'))" id="purchaser" v-model="form['purchaser']" :disabled="disabledObj['purchaser_isDisabled']">
							<el-option v-for="o in list_user_purchaser" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
						<el-select v-else-if="$check_field('get','purchaser')" id="purchaser" v-model="form['purchaser']" :disabled="true">
							<el-option v-for="o in list_user_purchaser" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','payment_status') || $check_field('add','payment_status') || $check_field('set','payment_status')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="支付情况" prop="payment_status">
					<el-select id="payment_status" v-model="form['payment_status']"
						v-if="user_group === '管理员' || (form['cancellation_of_order_id'] && $check_field('set','payment_status')) || (!form['cancellation_of_order_id'] && $check_field('add','payment_status'))">
						<el-option v-for="o in list_payment_status" :key="o" :label="o" :value="o">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','payment_status')">{{form['payment_status']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','contact_number') || $check_field('add','contact_number') || $check_field('set','contact_number')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="联系电话" prop="contact_number">
					<el-input id="contact_number" v-model="form['contact_number']" placeholder="请输入联系电话"
							  v-if="user_group === '管理员' || (form['cancellation_of_order_id'] && $check_field('set','contact_number')) || (!form['cancellation_of_order_id'] && $check_field('add','contact_number'))" :disabled="disabledObj['contact_number_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','contact_number')">{{form['contact_number']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','cancellation_reason') || $check_field('add','cancellation_reason') || $check_field('set','cancellation_reason')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="取消原因" prop="cancellation_reason">
					<el-input type="textarea" id="cancellation_reason" v-model="form['cancellation_reason']" placeholder="请输入取消原因"
						v-if="user_group === '管理员' || (form['cancellation_of_order_id'] && $check_field('set','cancellation_reason')) || (!form['cancellation_of_order_id'] && $check_field('add','cancellation_reason'))" :disabled="disabledObj['cancellation_reason_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','cancellation_reason')">{{form['cancellation_reason']}}</div>
				</el-form-item>
			</el-col>
			<el-col :xs="24" :sm="12" :lg="8">
				<el-form-item label="审核状态" prop="examine_state">
					<el-select id="examine_state" v-model="form['examine_state']"
						v-if="user_group === '管理员' || (form['examine_state'] && $check_examine()) || (!form['examine_state'] && $check_examine())">
						<el-option key="未审核" label="未审核" value="未审核"></el-option>
						<el-option key="已通过" label="已通过" value="已通过"></el-option>
						<el-option key="未通过" label="未通过" value="未通过"></el-option>
					</el-select>
					<div v-else-if="$check_field('get','examine_state')">{{form["examine_state"]}}</div>
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
				field: "cancellation_of_order_id",
				url_add: "~/api/cancellation_of_order/add?",
				url_set: "~/api/cancellation_of_order/set?",
				url_get_obj: "~/api/cancellation_of_order/get_obj?",
				url_upload: "~/api/cancellation_of_order/upload?",

				query: {
					"cancellation_of_order_id": 0,
				},

				form: {
					"order_number":'', // 订单编号
					"movie_title":'', // 电影名称
					"number_of_sessions":'', // 场次
					"number_of_purchases":'', // 购买数
					"total":'', // 合计
					"purchaser": 0, // 购买人
					"payment_status":'', // 支付情况
					"contact_number":'', // 联系电话
					"cancellation_reason":'', // 取消原因
					"examine_state": "未审核",
					"cancellation_of_order_id": 0, // ID

				},
				disabledObj:{
					"order_number_isDisabled": false,
					"movie_title_isDisabled": false,
					"number_of_sessions_isDisabled": false,
					"number_of_purchases_isDisabled": false,
					"total_isDisabled": false,
					"purchaser_isDisabled": false,
					"payment_status_isDisabled": false,
					"contact_number_isDisabled": false,
					"cancellation_reason_isDisabled": false,
				},
				// 用户列表
				list_user_purchaser: [],
				//支付情况选项列表
				list_payment_status: ['已支付','未支付'],

			}
		},
		methods: {
			/**
			 * 获取用户注册用户列表
			 */
			async get_list_user_purchaser() {
                // if(this.user_group !== "管理员" && this.form["purchaser"] === 0) {
                //     this.form["purchaser"] = this.user.user_id;
                // }
                var json = await this.$get("~/api/user/get_list?user_group=用户注册");
                if(json.result && json.result.list){
                    this.list_user_purchaser = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
			get_user_purchaser(id){
				var obj = this.list_user_purchaser.getObj({"user_id":id});
				var ret = "";
				if(obj){
					if(obj.nickname){
						ret = obj.nickname;}
					else{
						ret = obj.username;
					}
				}
				return ret;
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
				/**
				* 请求列表前
				* @param {Object} param
				*/
				var user_group = this.user.user_group;
				if (user_group !== "管理员") {
					switch (user_group) {
						case "购买人":
							if(param["purchaser"] > 0){
								param["purchaser"] = this.user.user_id;
							}
							break;
					}
				}
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
					bl = this.$check_action('/cancellation_of_order/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/cancellation_of_order/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/cancellation_of_order/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/cancellation_of_order/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/cancellation_of_order/view','get');
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
			this.get_list_user_purchaser();
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
