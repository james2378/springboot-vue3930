<template>
	<div class="diy_edit page_order_center" id="order_center_edit">
		<div class='warp'>
			<div class='container'>
				<div class='row diy_edit_content_box'>
					<div v-if="$check_field('set','order_number') || $check_field('add','order_number') || $check_field('get','order_number')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>订单编号:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_order_number" v-model="form['order_number']" placeholder="请输入订单编号" v-if="(form['order_number'] && $check_field('set','order_number')) || (!form['order_number'] && $check_field('add','order_number'))" :disabled="true"/>
							<span v-else-if="$check_field('get','order_number')">{{ form[''] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','number_of_sessions') || $check_field('add','number_of_sessions') || $check_field('get','number_of_sessions')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>场次:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_number_of_sessions" v-model="form['number_of_sessions']" placeholder="请输入场次" v-if="(form['number_of_sessions'] && $check_field('set','number_of_sessions')) || (!form['number_of_sessions'] && $check_field('add','number_of_sessions'))"  :disabled="disabledObj['number_of_sessions_isDisabled']"/>
							<span v-else-if="$check_field('get','number_of_sessions')">{{ form[''] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','price') || $check_field('add','price') || $check_field('get','price')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>价格:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_price" v-model="form['price']" placeholder="请输入价格" v-if="(form['price'] && $check_field('set','price')) || (!form['price'] && $check_field('add','price'))"  :disabled="disabledObj['price_isDisabled']"/>
							<span v-else-if="$check_field('get','price')">{{ form[''] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','movie_number') || $check_field('add','movie_number') || $check_field('get','movie_number')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>电影编号:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_movie_number" v-model="form['movie_number']" placeholder="请输入电影编号" v-if="(form['movie_number'] && $check_field('set','movie_number')) || (!form['movie_number'] && $check_field('add','movie_number'))"  :disabled="disabledObj['movie_number_isDisabled']"/>
							<span v-else-if="$check_field('get','movie_number')">{{ form[''] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','movie_title') || $check_field('add','movie_title') || $check_field('get','movie_title')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>电影名称:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_movie_title" v-model="form['movie_title']" placeholder="请输入电影名称" v-if="(form['movie_title'] && $check_field('set','movie_title')) || (!form['movie_title'] && $check_field('add','movie_title'))"  :disabled="disabledObj['movie_title_isDisabled']"/>
							<span v-else-if="$check_field('get','movie_title')">{{ form[''] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','number_of_purchases') || $check_field('add','number_of_purchases') || $check_field('get','number_of_purchases')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>购买数:
							</span>
						</div>
						<!-- 数字 -->
						<div class="diy_field diy_number">
							<input type="number" id="form_number_of_purchases" v-model.number="form['number_of_purchases']" placeholder="请输入购买数" v-if="(form['number_of_purchases'] && $check_field('set','number_of_purchases')) || (!form['number_of_purchases'] && $check_field('add','number_of_purchases'))" :disabled="disabledObj['number_of_purchases_isDisabled']" />
							<span v-else-if="$check_field('get','number_of_purchases')">{{ form[''] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','total') || $check_field('add','total') || $check_field('get','total')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>合计:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_total" v-model="form['total']" placeholder="请输入合计" v-if="(form['total'] && $check_field('set','total')) || (!form['total'] && $check_field('add','total'))"  @focus="set_total()" :disabled="disabledObj['total_isDisabled']"/>
							<span v-else-if="$check_field('get','total')">{{ form[''] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','purchaser') || $check_field('add','purchaser') || $check_field('get','purchaser')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>购买人:
							</span>
						</div>
						<div class="diy_field diy_down">
							<select id="form_purchaser" :disabled="disabledObj['purchaser_isDisabled']" v-model="form['purchaser']" v-if="(form['purchaser'] && $check_field('set','purchaser')) || (!form['purchaser'] && $check_field('add','purchaser'))" >
								<option v-for="o in list_user_purchaser" :value="o['user_id']">
									{{o['nickname'] + '-' + o['username']}}
								</option>
							</select>
							<span v-else-if="$check_field('get','purchaser')">{{ form['purchaser'] }}</span>
						</div>
					</div>
					<!-- 座位 -->
					<div class="form-item selected_seat_box col-12 col-md-6">
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
							<div v-for="row in seatRow" :key="row">
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
					</div>
				</div>
				<div class="diy_edit_submit_box row">
					<div class="col-12">
						<div class="btn_box">
							<button class="btn_submit" @click="submit()">提交</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	import mixin from "@/mixins/page.js";
	export default {
		mixins: [mixin],
		components: {},
		data() {
			return {
				url_get_obj: "~/api/order_center/get_obj?",
				url_add: "~/api/order_center/add?",
				url_set: "~/api/order_center/set?",

				// 登录权限
				oauth: {
					"signIn": true,
					"user_group": []
				},

				// 查询条件
				query: {
					"order_number": "",
					"number_of_sessions": "",
					"price": "",
					"movie_number": "",
					"movie_title": "",
					"number_of_purchases": 0,
					"total": "",
					"purchaser": 0,
					"order_center_id": 0,
					"seat": "", // 座位
				},

				obj: {
					"order_number":this.$get_stamp(), // 订单编号
					"number_of_sessions":'', // 场次
					"price":'', // 价格
					"movie_number":'', // 电影编号
					"movie_title":'', // 电影名称
					"number_of_purchases":0, // 购买数
					"total":'', // 合计
					"purchaser": 0, // 购买人
					"order_center_id": 0,
					"seat": "", // 座位
				},

				// 表单字段
				form: {
					"order_number":this.$get_stamp(), // 订单编号
					"number_of_sessions":'', // 场次
					"price":'', // 价格
					"movie_number":'', // 电影编号
					"movie_title":'', // 电影名称
					"number_of_purchases":0, // 购买数
					"total":'', // 合计
					"purchaser": 0, // 购买人
					"order_center_id": 0,
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

				// ID字段
				field: "order_center_id",
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
			async get_user_session_purchaser(){
				var _this = this;
				var json = await this.$get("~/api/user_group/get_obj?name=用户注册");
				if(json.result && json.result.obj){
					var source_table = json.result.obj.source_table;
					var user_id = _this.$store.state.user.user_id;
					if (user_id){
						var url = "~/api/"+source_table+"/get_obj?"
						this.$get(url, {"user_id":_this.$store.state.user.user_id}, function(res) {
							if (res.result && res.result.obj) {
								var arr = []
								for (let key in res.result.obj) {
									arr.push(key)
								}
								var arrForm = []
								for (let key in _this.form) {
									arrForm.push(key)
								}
								_this.form["purchaser"] = user_id
								_this.disabledObj['purchaser' + '_isDisabled'] = true
								for (var i=0;i<arr.length;i++){
									for (var j=0;j<arrForm.length;j++){
										if (arr[i]===arrForm[j]){
											if (arr[i]!=="purchaser") {
												_this.form[arrForm[j]] = res.result.obj[arr[i]]
												_this.disabledObj[arrForm[j] + '_isDisabled'] = true
												break;
											}
										}
									}
								}
							}
						});
					}
				}
				else if(json.error){
					console.error(json.error);
				}
			},

			/**
			 * 修改文件
			 * @param { Object } files 上传文件对象
			 * @param { String } str 表单的属性名
			 */
			change_file(files, str) {
				var form = new FormData();
				form.append("file", files[0]);
				this.$post("~/api/order_center/upload?", form, (res) => {
					if (res.result) {
						this.form[str] = res.result.url;
					} else if (res.error) {
						this.$toast(res.error.message);
					}
				});
			},

			/**
			 * 获取对象后获取缓存表单
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_before(param){
				var form = $.db.get("form");
				if (form) {
					this.obj = $.push(this.obj ,form);
					this.form = $.push(this.form ,form);
				}
				var arr = []
				for (let key in form) {
					arr.push(key)
				}
				for (var i=0;i<arr.length;i++){
					this.disabledObj[arr[i] + '_isDisabled'] = true
				}
				return param;
			},

			/**
			 * 获取对象后获取缓存表单
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json ,func){
				var form = $.db.get("form");
				var obj = Object.assign({} ,form ,this.obj);
				if (form) {
					this.obj = $.push(this.obj ,obj);
				}
				if (form) {
					this.form = $.push(this.form ,form);
				}
				if(func){
					func(json);
				}
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
			  let seat = this.obj.seat;
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
			 * 获取所有坐座位信息
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
			this.get_user_session_purchaser();
			this.get_list_user_purchaser();
			this.get_list();
		}
	}
</script>

<style>

	.seat-wrapper {
	  margin-top: 20px;
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
