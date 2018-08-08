//主要交互逻辑代码
// seckill.detail.init(params)
var seckill={
	// 封装秒杀相关ajax 地址
	URL:{
		now:"/seckill/time/now",
		path:"/seckill/exposer/",
		killUrl:function(seckillId,md5){
			return "/seckill/execution/"+seckillId+"/"+md5;
		}
	},
	//开始秒杀
	beginSeckill:function(seckillId,box){
		box.hide().html("<button class='btn btn-primary btn-lg' id='killBtn'>开始秒杀</button>");
		//秒杀地址
		var path =seckill.URL.path+seckillId;  //  相当于/miaosha/exposer/1001
		$.post(path,{},function(result){
			if(result && result['success']){
				var exposer = result['data'];
				if(exposer['exposed']){
					//开启秒杀  得到地址
					var md5= exposer['md5'];
					var killUrl = seckill.URL.killUrl(seckillId,md5);
					console.log("killURL="+killUrl);
					//只绑定一次单击
					$("#killBtn").one('click',function(){
						// 1 禁用
						$(this).addClass("disable");  
						// 2 发送秒杀请求
						$.post(killUrl,{},function(result){
							if(result && result['success']){
								var killResult = result["data"];
								var state = killResult["state"];
								var stateInfo = killResult["stateInfo"];
								// 3 显示秒杀结果
								box.html("<span class='label label-success'>"+stateInfo+"</span>");
							}
						});
					});
					box.show();
				}else{
					//未开启秒杀 --客户端时间到了，服务器时间未到
					var now = exposer['now'];
					var start = exposer['start'];
					var end = exposer['end'];
					//重新计时
					seckill.countdown(seckillId,now,start,end);
				}
			}else{
				alert("获取地址失败");
			}
		});
	},
	//验证手机号
	validatePhone:function(phone){
		if(phone && phone.length ==11 && !isNaN(phone)){
			return true;
		}else{
			return false;
		}
	},
	//倒计时函数
	countdown:function(seckillId,nowTime,startTime,endTime){
		var seckillBox= $("#seckill-box");
		//时间的判断
		if(nowTime > endTime){  //秒杀结束了
			seckillBox.html("秒杀结束了");
		}else if(nowTime < startTime){ //秒杀没开始，显示倒计时
			var killTime = new Date(startTime +1000);
			seckillBox.countdown(killTime,function(event){
				var format = event.strftime("秒杀倒计时:%D天 %H时 %M分 %S秒");
				seckillBox.html(format);
			}).on('finish.countdown',function(){
				//时间到了，回调函数 --> 得到秒杀地址
				seckill.beginSeckill(seckillId,seckillBox);
			});
		}else{
			//秒杀进行中 ，可以秒
			seckill.beginSeckill(seckillId,seckillBox);
		}
		
	},
	//详情页秒杀逻辑
	detail:{
		//详情页初始化
		init:function(params){
			//手机验证和登录，计时交互
			var killPhone = $.cookie('killPhone');  //从cookie 中查找手机号
			
			//验证手机号
			if(seckill.validatePhone(killPhone)==false){
				var killPhoneModal=$('#killPhoneModal');
				killPhoneModal.modal({
					show:true,  //显示
					backdrop:'static', //禁止关闭
					keyboard:false    //关闭键盘事件
				});
				//关闭按钮
				$("#killPhoneBtn").click(function(){
					//取出用户输入的手机号
					var userPhone= $("#killPhoneKey").val();
					if(seckill.validatePhone(userPhone)){
						//存cookie
						$.cookie("killPhone",userPhone,{expires:7,path:'/seckill'})
						window.location.reload();//刷新页面
					}else{
						$("#killPhoneMessage").hide().html('<label class="label label-danger">手机号错误！</label>').show(300);					
					}
				});
			}			
			//通过IF 表示已经登录了
			// 取出系统时间，没开始就倒计时， 开始了就秒杀
			var startTime = params["startTime"];
			var endTime = params["endTime"];
			var seckillId = params["seckillId"];
			$.get(seckill.URL.now,{},function(result){
				console.log("result:"+result);
				if(result && result['success']){
					var nowTime = result['data'];
					//倒计时交互
					seckill.countdown(seckillId,nowTime,startTime,endTime);
				}else{
					alert("时间失败");
				}
			});
			
		}
	}
		
		
}