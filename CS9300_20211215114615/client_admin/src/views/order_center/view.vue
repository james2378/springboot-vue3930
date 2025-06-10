<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','order_number') || $check_field('add','order_number') || $check_field('set','order_number')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="订单编号" prop="order_number">
					<el-input id="order_number" v-model="form['order_number']" placeholder="请输入订单编号"
							  v-if="user_group === '管理员' || (form['order_center_id'] && $check_field('set','order_number')) || (!form['order_center_id'] && $check_field('add','order_number'))" :disabled="true"></el-input>
					<div v-else-if="$check_field('get','order_number')">{{form['order_number']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','number_of_sessions') || $check_field('add','number_of_sessions') || $check_field('set','number_of_sessions')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="场次" prop="number_of_sessions">
					<el-input id="number_of_sessions" v-model="form['number_of_sessions']" placeholder="请输入场次"
							  v-if="user_group === '管理员' || (form['order_center_id'] && $check_field('set','number_of_sessions')) || (!form['order_center_id'] && $check_field('add','number_of_sessions'))" :disabled="disabledObj['number_of_sessions_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','number_of_sessions')">{{form['number_of_sessions']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','price') || $check_field('add','price') || $check_field('set','price')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="价格" prop="price">
					<el-input id="price" v-model="form['price']" placeholder="请输入价格"
							  v-if="user_group === '管理员' || (form['order_center_id'] && $check_field('set','price')) || (!form['order_center_id'] && $check_field('add','price'))" :disabled="disabledObj['price_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','price')">{{form['price']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','movie_number') || $check_field('add','movie_number') || $check_field('set','movie_number')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="电影编号" prop="movie_number">
					<el-input id="movie_number" v-model="form['movie_number']" placeholder="请输入电影编号"
							  v-if="user_group === '管理员' || (form['order_center_id'] && $check_field('set','movie_number')) || (!form['order_center_id'] && $check_field('add','movie_number'))" :disabled="disabledObj['movie_number_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','movie_number')">{{form['movie_number']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','movie_title') || $check_field('add','movie_title') || $check_field('set','movie_title')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="电影名称" prop="movie_title">
					<el-input id="movie_title" v-model="form['movie_title']" placeholder="请输入电影名称"
							  v-if="user_group === '管理员' || (form['order_center_id'] && $check_field('set','movie_title')) || (!form['order_center_id'] && $check_field('add','movie_title'))" :disabled="disabledObj['movie_title_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','movie_title')">{{form['movie_title']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','number_of_purchases') || $check_field('add','number_of_purchases') || $check_field('set','number_of_purchases')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="购买数" prop="number_of_purchases">
					<el-input-number id="number_of_purchases" v-model.number="form['number_of_purchases']"
						v-if="user_group === '管理员' || (form['order_center_id'] && $check_field('set','number_of_purchases')) || (!form['order_center_id'] && $check_field('add','number_of_purchases'))"></el-input-number>
					<div v-else-if="$check_field('get','number_of_purchases')">{{form['number_of_purchases']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','total') || $check_field('add','total') || $check_field('set','total')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="合计" prop="total">
					<el-input id="total" v-model="form['total']" placeholder="请输入合计"
							  v-if="user_group === '管理员' || (form['order_center_id'] && $check_field('set','total')) || (!form['order_center_id'] && $check_field('add','total'))"  @focus="set_total()" :disabled="disabledObj['total_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','total')">{{form['total']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','purchaser') || $check_field('add','purchaser') || $check_field('set','purchaser')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="购买人" prop="purchaser">
						<div v-if="user_group !== '管理员'">
							{{ get_user_session_purchaser(form['purchaser']) }}
							<!--<el-input id="business_name" v-model="form['purchaser']" placeholder="请输入购买人"-->
							<!--v-if="user_group === '管理员' || (form['order_center_id'] && $check_field('set','purchaser')) || (!form['order_center_id'] && $check_field('add','purchaser'))" :disabled="disabledObj['purchaser_isDisabled']"></el-input>-->
							<!--<div v-else-if="$check_field('get','purchaser')">{{form['purchaser']}}</div>-->
							<el-select v-if="user_group === '管理员' || (form['order_center_id'] && $check_field('set','purchaser')) || (!form['order_center_id'] && $check_field('add','purchaser'))" id="purchaser" v-model="form['purchaser']" :disabled="disabledObj['purchaser_isDisabled']">
								<el-option v-for="o in list_user_purchaser" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
							<el-select v-else-if="$check_field('get','purchaser')" id="purchaser" v-model="form['purchaser']" :disabled="true">
								<el-option v-for="o in list_user_purchaser" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
						</div>
						<el-select v-else id="purchaser" v-model="form['purchaser']" :disabled="disabledObj['purchaser_isDisabled']">
							<el-option v-for="o in list_user_purchaser" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
				</el-form-item>
			</el-col>
			<el-col :xs="24" :sm="24" :lg="24">
				<el-form-item label="选座" prop="seat">
				  <div class="seat-wrapper">
					<div class="illustration">
					  <div class="illustration-img-wrapper unselected-seat"></div>
					  <span class="illustration-text">可选</span>
					  <div class="illustration-img-wrapper selected-seat"></div>
					  <span class="illustration-text">已选</span>
					  <div class="illustration-img-wrapper bought-seat"></div>
					  <span class="illustration-text">不可选</span>
					  <div class="btn-buy" @click="buySeat">选定座位</div>
					</div>
					<div class="inner-seat-wrapper" ref="innerSeatWrapper">
					  <div v-for="row in seatRow">
						<!--这里的v-if很重要，如果没有则会导致报错，因为seatArray初始状态为空-->
						<div
						  v-for="col in seatCol"
						  v-if="seatArray.length > 0"
						  class="seat"
						  :style="{ width: seatSize + 'px', height: seatSize + 'px' }"
						>
						  <div
							class="inner-seat"
							@click="handleChooseSeat(row - 1, col - 1)"
							v-if="seatArray[row - 1][col - 1] !== -1"
							:class="
							  seatArray[row - 1][col - 1] === 2
								? 'bought-seat'
								: seatArray[row - 1][col - 1] === 1
								? 'selected-seat'
								: 'unselected-seat'
							"
						  ></div>
						</div>
					  </div>
					</div>
				  </div>
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
				field: "order_center_id",
				url_add: "~/api/order_center/add?",
				url_set: "~/api/order_center/set?",
				url_get_obj: "~/api/order_center/get_obj?",
				url_upload: "~/api/order_center/upload?",

				query: {
					"order_center_id": 0,
				},

				form: {
					"order_number":this.$get_stamp(), // 订单编号
					"number_of_sessions":'', // 场次
					"price":'', // 价格
					"movie_number":'', // 电影编号
					"movie_title":'', // 电影名称
					"number_of_purchases":0, // 购买数
					"total":'', // 合计
					"purchaser": 0, // 购买人
					"order_center_id": 0, // ID
					"seat": "", // 座位

				},
				disabledObj:{
					"order_number_isDisabled": false,
					"number_of_sessions_isDisabled": false,
					"price_isDisabled": false,
					"movie_number_isDisabled": false,
					"movie_title_isDisabled": false,
					"total_isDisabled": false,
					"purchaser_isDisabled": false,
				},
				// 用户列表
				list_user_purchaser: [],
				// 用户组
				group_user_purchaser: "",
				//影院座位的二维数组,-1为非座位，0为未购座位，1为已选座位(绿色),2为已购座位(红色)
				seatArray: [],
				//影院座位行数
				seatRow: 5,
				//影院座位列数
				seatCol: 8,
				//座位尺寸
				seatSize: "",
				seatArr: [],
				list_: [],
				seatList: "",

			}
		},
		methods: {
			set_total(){
				this.form.total =this.form.price * this.form.number_of_purchases
			},
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
			/**
			 * 获取用户注册用户组
			 */
			async get_group_user_purchaser() {
				this.form["purchaser"] = this.user.user_id;
				var json = await this.$get("~/api/user_group/get_obj?name=用户注册");
				if(json.result && json.result.obj){
					this.group_user_purchaser = json.result.obj;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_session_purchaser(id){
				var _this = this;
				var user_id = {"user_id":id}
				var url = "~/api/"+_this.group_user_purchaser.source_table+"/get_obj?"
				this.$get(url, user_id, function(res) {
					if (res.result && res.result.obj) {
						var arr = []
						for (let key in res.result.obj) {
							arr.push(key)
						}
						var arrForm = []
						for (let key in _this.form) {
							arrForm.push(key)
						}
						for (var i=0;i<arr.length;i++){
							for (var j=0;j<arrForm.length;j++){
								if (arr[i]===arrForm[j]){
									if (arr[i]!=="purchaser") {
										_this.form[arrForm[j]] = res.result.obj[arr[i]]
										_this.disabledObj[arrForm[j] + '_isDisabled'] = true
										break;
									}else {
										_this.disabledObj[arrForm[j] + '_isDisabled'] = true
									}
								}
							}
						}
					}
				});
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
					bl = this.$check_action('/order_center/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/order_center/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/order_center/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/order_center/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/order_center/view','get');
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
			// 选座
			//选定且购买座位
			buySeat: function () {
			  //遍历seatArray，将值为1的座位变为2
			  let oldArray = this.seatArray.slice();
			  for (let i = 0; i < this.seatRow; i++) {
				for (let j = 0; j < this.seatCol; j++) {
				  if (oldArray[i][j] === 1) {
					oldArray[i][j] = 2;
				  }
				}
			  }
			  this.seatArray = oldArray;
			  let string = "";
			  for (let x = 0; x < oldArray.length; x++) {
				let array = oldArray[x];
				for (let j = 0; j < array.length; j++) {
				  if (oldArray[x][j] === 2) {
					if (string == "") {
					  string = "" + (x * 8 + j);
					} else {
					  string = string + "," + (x * 8 + j);
					}
				  }
				}
			  }
			  // 对比判断哪些是新增的座位
			  let seat = this.form.seat;
			  let seatB = "";
			  if (string != "" && string != null) {
				let stringList = string.split(",");
				let seatList = this.seatList;
				if (seatList.length != 0) {
				  let seatListArr = seatList.split(",");
				  for (let v = 0; v < stringList.length; v++) {
					if (
					  seatListArr.indexOf(stringList[v]) == -1 &&
					  seat.indexOf(stringList[v]) == -1
					) {
					  if (seat == "" || seat == null) {
						seat = stringList[v] + "";
						seatB = seat;
					  } else {
						if (seat.indexOf(stringList[v]) == -1) {
						  seat = seat + "," + stringList[v];
						  if (seatB == "" || seatB == null) {
							seatB = stringList[v];
						  } else {
							seatB = seatB + "," + stringList[v];
						  }
						}
					  }
					}
				  }
				} else {
				  seat = string;
				}
			  }

			  if (this.form.seat == "") {
				this.form.seat = seat;
			  } else {
				if (seatB != "") {
				  this.form.seat = this.form.seat + "," + seatB;
				}
			  }
			  this.seatList = string;
			},
			//处理座位选择逻辑
			handleChooseSeat: function (row, col) {
			  let seatValue = this.seatArray[row][col];
			  let newArray = this.seatArray;
			  //如果是已购座位，直接返回
			  if (seatValue === 2) return;
			  //如果是已选座位点击后变未选
			  if (seatValue === 1) {
				newArray[row][col] = 0;
			  } else if (seatValue === 0) {
				newArray[row][col] = 1;
			  }
			  //必须整体更新二维数组，Vue无法检测到数组某一项更新,必须slice复制一个数组才行
			  this.seatArray = newArray.slice();
			},
			/**
			 * 获取所有座位
			 */
			async get_list() {
			  var json = await this.$get("~/api/order_center/get_list");
			  if (json.result && json.result.list) {
				this.list_ = json.result.list;
			  } else if (json.error) {
				console.error(json.error);
			  }
			  console.log(json);
			  let list = json.result.list;
			  if (list != null && list.length != 0) {
				let seatArr = "";
				for (let j = 0; j < list.length; j++) {
				  let seat = list[j].seat;
				  if (seat != null && seat != "") {
					if (seatArr == "") {
					  seatArr = seat + "";
					} else {
					  seatArr = seatArr + "," + seat;
					}
				  }
				}
				this.seatList = seatArr;
			  }
			  this.initSeatArray(5, 8);
			},

			//初始座位数组
			initSeatArray: function () {
			  let seatList = this.seatList;
			  let seatArr = seatList.split(",");
			  if (seatList == "" || seatList == null) {
				seatArr = [];
			  }
			  this.seatArr = seatArr;

			  let seatArray = Array(this.seatRow)
				.fill(0)
				.map(() => Array(this.seatCol).fill(0));
			  this.seatArray = seatArray;
			  for (let j = 0; j < seatArr.length; j++) {
				let i = Math.floor(seatArr[j] / 8);
				let x = seatArr[j] % 8;
				seatArray[i][x] = 2;
			  }
			  this.seatSize = this.$refs.innerSeatWrapper
				? parseInt(
					parseInt(
					  window.getComputedStyle(this.$refs.innerSeatWrapper).width,
					  10
					) / this.seatCol,
					10
				  )
				: 0;
			},

		},
		created() {
			this.get_list_user_purchaser();
			this.get_group_user_purchaser();
			this.get_list();
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
	.seat-wrapper {
	height: 370px;
	width: 500px;
	border: 1px dotted #c5c5c5;
	/* margin: 0 auto; */
	position: relative;
	overflow: hidden;
	}

	.inner-seat-wrapper {
	position: absolute;
	bottom: 0;
	width: 100%;
	box-sizing: border-box;
	}
	.seat {
	float: left;
	display: flex;
	justify-content: center;
	align-items: center;
	}
	.inner-seat {
	width: 80%;
	height: 80%;
	cursor: pointer;
	}

	.btn-wrapper {
	margin: 20px auto;
	width: 1000px;
	height: 30px;
	text-align: center;
	}

	.btn-buy {
	height: 100%;
	line-height: 35px;
	font-size: 15px;
	border-radius: 5px;
	padding: 0 5px;
	background-color: #ffa349;
	color: #ffffff;
	display: inline-block;
	cursor: pointer;
	margin: 5px;
	}

	.illustration {
	position: absolute;
	left: 10px;
	top: 20px;
	height: 35px;
	width: 300px;
	}

	.illustration-img-wrapper {
	width: 25px;
	height: 25px;
	position: relative;
	top: 50%;
	display: inline-block;
	transform: translateY(-50%);
	margin-left: 10px;
	}

	.illustration-text {
	display: inline-block;
	height: 100%;
	line-height: 35px;
	font-size: 14px;
	position: relative;
	top: -2px;
	}
    .selected-seat {
	background: url("/img/selected.png") center center no-repeat;
	background-size: 100% 100%;
	}

	.unselected-seat {
	background: url("/img/unselected.png") center center no-repeat;
	background-size: 100% 100%;
	}

	.bought-seat {
	background: url("/img/bought.png") center center no-repeat;
	background-size: 100% 100%;
	}
</style>
