<%@ page isELIgnored="false" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <%@include file="common/head.jsp" %>
    <title>秒杀详情页</title>  
  </head>
  <body>  	
  	<div class="container">
  	  <h1>&nbsp;</h1>
  	  <div class="panel panel-default">
  	  	 <div class="panel-heading text-center">
  	  	    <div class="pannel-heading"><h1>${seckill.name}</h1></div>
  	  	 </div>
  	  	  <div class="panel-body  text-center">
  	  	     <h2 class="text-danger">
  	  	       <!-- 显示time 图标 -->
  	  	       <span class="glyphicon glyphicon-time"></span>
  	  	       <!-- 显示倒计时 -->
  	  	       <span class="glyphicon" id="seckill-box"></span>
  	  	     </h2>
  	  	 </div>
  	  </div> 	 
  	</div>
  <!-- 登录弹出层  输入手机号 -->
  <div id="killPhoneModal" class="modal fade">
  <div class="modal-dialog" >
    <div class="modal-content">
      <div class="modal-header text-center">       
        <h3 class="modal-title">
			<span class="glyphicon glyphicon-phone"></span>秒杀电话
		</h3>
      </div>
      <div class="modal-body">
        <div class="row">
           <div class="col-xs-8 col-xs-offset-2">
           	 <input class="form-control" type="text" name="killPhone" id="killPhoneKey" placeholder="填写手机号"/>
           </div>
        </div>
      </div>
      <div class="modal-footer">
      	<!-- 验证消息 -->
      	<span id="killPhoneMessage" class="glyphicon"></span>
        <button id="killPhoneBtn" type="button" class="btn btn-success">
        	<span class="glyphicon glyphicon-phone"></span>
        	提交
        </button>       
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
  </body>
  <!-- COOKIE 和倒计时插件 -->
  <script src="/seckill/js/jquery.cookie.min.js"></script>
  <script src="/seckill/js/jquery.countdown.min.js"></script>
  <script src="/seckill/js/seckill.js"></script>
  <script>
    $(function(){
    	// 使用EL 传入参数
    	seckill.detail.init({
    		"seckillId":${seckill.seckillId},
    		"startTime":${seckill.startTime.time},  //毫秒
    		"endTime":${seckill.endTime.time}
    	});
    	
    });
  </script>
</html>