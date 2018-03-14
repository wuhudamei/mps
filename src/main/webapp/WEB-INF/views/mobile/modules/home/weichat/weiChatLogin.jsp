<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" href="${ctxStatic}/mobile/modules/home/css/base.css">
    <link rel="stylesheet" href="${ctxStatic}/mobile/modules/home/css/ljxLogin.css">
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/globalUtil.css"/>
    <title>登录</title>
</head>

<body>
    <div class="wrap">
        <div class="logo">
            <img src="${ctxStatic}/mobile/modules/home/images/logo.png" alt="">
        </div>
        <div class="tab">
            <p>
                <span class="name"></span><input type="text" id="username" placeholder="输入美得你预留手机号">
            </p>
            <p>
                <span class="psd"></span><input type="text" id="yanzheng" placeholder="短信验证码">
                <i class="info" id="info" onclick="getCode()">获取验证码</i>
            </p>
            <p class="btn" id="btn" onclick="login()">登录</p>
           <!--  <p class="worry">
                <span></span>您输入的手机号不存在
            </p> -->
        </div>
    </div>
    <script src="${ctxStatic}/mobile/modules/home/js/lib/jquery-2.1.1.min.js"></script>
    <script src="${ctxStatic}/mobile/modules/home/js/utils/calcRootFontSize.js"></script>
    <script src="${ctxStatic}/mobile/modules/home/js/utils/ljxLogin.js"></script>
    <script src="${ctxStatic}/mobile/modules/home/js/utils/base.js"></script>
    <script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/global.js"></script>
    <script type="text/javascript">
	    var wait = 60;
		var interValObj; 
		function getCode(){
			var phone = $("#username").val();
			var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1})|(14[0-9]{1}))+\d{8})$/;
			if(!myreg.test(phone)){
				 globalUtil.fn.alert('您输入的手机号有误！',2.0);
			}else{ 
				 $("#info").attr("onclick", "null"); 
			     $("#info").text("倒计时 " + wait + "秒"); 
		         interValObj = window.setInterval(setRemainTime, 1000);
		         $.ajax({
		        	 url:"${ctx}/app/wx/home/sendCode",
		        	 type:"post",
		        	 data :{
		        		 "mobilePhone":phone,
		        		 "appType":'4'
		        	 },
		        	 success:function(data){
		        		 if(data=="error"){
		        			 globalUtil.fn.alert("手机号未注册！",2.0);
		        		 }else if(data == "messagefail"){
		        			 globalUtil.fn.alert("短信发送失败",2.0);
		        		 }else{
		        			 globalUtil.fn.alert("短信发送成功",2.0);
		        		 }
		        	 }
		         });
			 }
		}
		function setRemainTime() {  
   		 	wait--; 
   	        if (wait > 0) { 
   	         	$("#info").text("倒计时 " + wait + "秒");
   	        } else {  
   	        	window.clearInterval(interValObj);
   	        	$("#info").attr("onclick", "getCode()"); 
   	        	//$("#info").removeAttr("disabled");//启用按钮  
   	            $("#info").text("获取验证码");
   	         	wait = 60;
   	        }  
   	    } 
		function login(){
			//alert("aaa");
   			$("#info").text("获取验证码");
   			$("#info").attr("onclick", "getCode()"); 
   			//$("#info").removeAttr("disabled");//启用按钮
   			clearInterval(interValObj);//清楚定时器
   			var mycode = $("#yanzheng").val();
   			//alert(mycode);
   			var phone = $("#username").val();
			var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1})|(14[0-9]{1}))+\d{8})$/;
   			var mycodereg = /^\d{6}$/;
   			if(!myreg.test(phone)){
   				globalUtil.fn.alert('您输入的手机号格式有误！',2.0);
   			}else{
   				if(mycode == null){
   					globalUtil.fn.alert('请输入验证码',2.0);
   				}else if(!mycodereg.test(mycode)){
   	   				globalUtil.fn.alert('请输入6位有效验证码',2.0);
   	   			}else{
   	   				$.ajax({
	   					type:"post",
	   					url:"${ctx}/app/wx/home/login",
	   					data:{
	   						"code":mycode,
	   						"mobilePhone":phone,
	   						"appType":'4'
	   					},
	   					success:function(data){
	   						if(data == "codeerror"){
	   							globalUtil.fn.alert("验证码有误！",2.0);
	   						}
	   						if(data == "loginsuccess"){
	   							window.location.href = "${ctx}/app/home/isLogin";
	   						}
	   					}
	   				});
   	   			}
   			}
   		}
    </script>
</body>
</html>