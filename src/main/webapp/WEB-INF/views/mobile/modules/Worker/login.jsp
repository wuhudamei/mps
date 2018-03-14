<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
	<meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="black" name="apple-mobile-web-app-status-bar-style">
	<meta content="telephone=no" name="format-detection">
	<meta content="email=no" name="format-detection">
	<title>工人登陆</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/globalUtil.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/login.css"/>
	<style type="text/css">
		
	</style>
		<script type="text/javascript">
		var wait=60;
   		var interValObj; 
   		//$("#get_num").attr("onclick");
   		function getCode(obj){
			 var phone = $("#username").val();
          /*  var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1})|(14[0-9]{1}))+\d{8})$/;
            if(!myreg.test(phone)){
                globalUtil.fn.alert('您输入的手机号有误！',1.0);
            }else{

            }*/
            if(!isNaN(phone) && phone.length==11){
                $("#get_num").attr("disabled", "true");
                $("#get_num").val("倒计时 " + wait + "秒");
                interValObj = window.setInterval(setRemainTime, 1000);
                $.ajax({
                    url:"${ctx}/app/worker/isExist",
                    type:"post",
                    dataType:"json",
                    contentType: "application/json; charset=utf-8",
                    data:phone,
                    success:function(isExist){
                        if(isExist==1){
                            $.ajax({
                                url:"${ctx}/app/worker/get",
                                type:"post",
                                dataType:"json",
                                contentType: "application/json; charset=utf-8",
                                data:phone,
                                success:function(code){
                                    //发送成功
                                }
                            });
                        }else{
                            globalUtil.fn.alert('您输入的用户名不存在！',1.0);
                        }
                    }
                });
            }else{
                globalUtil.fn.alert('您输入的手机号有误！',1.0);
            }



		}

   		function setRemainTime() {  
   			wait--; 
   	        if (wait > 0) { 
   	        	//$("#get_num").text("倒计时 " + wait + "秒");
   	         	$("#get_num").val("倒计时 " + wait + "秒");
   	        } else {  
   	        	window.clearInterval(interValObj);
   	           /*  $("#get_num").attr("onclick");
   	            $("#get_num").text("重新发送验证码");  */
   	            $("#get_num").removeAttr("disabled");//启用按钮  
   	            $("#get_num").val("重新获取验证码");
   	         	wait = 60;
   	        }  
   	    } 
   		
   		function login(){
   			$("#get_num").val("获取验证码");
   			$("#get_num").removeAttr("disabled");//启用按钮
   			clearInterval(interValObj);
   			var mycode = $("#yanzheng").val();
   			var phone = $("#username").val();
			/*var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1})|(14[0-9]{1}))+\d{8})$/;*/
   			var mycodereg = /^\d{6}$/;
            if(!isNaN(phone) && phone.length==11){

                if(mycode == null|| mycode == ''){
                    globalUtil.fn.alert('请输入验证码',1.0);
                }else if(!mycodereg.test(mycode)){
                    globalUtil.fn.alert('您输入的验证码格式有误！',1.0);
                }else{
                    //$("#loginForm").submit();
                    $.ajax({
                        type:"post",
                        url:"${ctx}/app/worker/login",
                        data:"username="+phone+"&yanzheng="+mycode,
                        success:function(data){
                            if(data == "1"){
                                globalUtil.fn.alert("验证码有误！",1.0);
                            }
                            if(data == "2"){
                                window.location.href = "${ctx}/app/worker/toindex";
                            }
                            if(data == "3"){
                                window.location.href = "${ctx}/app/worker/toindex1";
                            }
                            if(data == "4"){
                                globalUtil.fn.alert("用户名不存在！",1.0);
                            }
                            if(data == "5"){
                                globalUtil.fn.alert("请先获取验证码！",1.0);
                            }
                            if(data == "6"){
                                window.location.href = "${ctx}/app/worker/install/installIndex/toindex";
                            }
                        }
                    });
                }



   			}else{
                globalUtil.fn.alert('您输入的手机号格式有误！',1.0);
   			}
   		}
	</script>
</head>
<body>
	<div class="g-login">
		<div class="logo">
			<img src="${ctxStatic}/mobile/modules/Worker/images/logo_worker.png" alt="美得你">
		</div>
		<!-- <h3 class="logo_text">美得你，是你最好的选择</h3> -->
		<div class="login_form">
			<%-- <form id="loginForm" action="${ctx}/app/worker/login" method="post"> --%>
			<form id="loginForm">
				<input id="username" type="text" name="username" value="" placeholder="请输入美得你预留手机号">
				<div>
					<input id="yanzheng" type="text" name="yanzheng" value="" placeholder="短信验证码">
					<!-- <a id="get_num" href="#" onclick="getCode(this)">获取验证码</a> -->
					<input id ="get_num" type="button" value="获取验证码" onclick="getCode()"/>
				</div>
				<a id="user_login_btn" href="#" onclick="login()">登录</a>
			</form>	
			<div>${message}</div>
		</div>
	</div>
	<!-- <div id="alertDialog" class="">
		<div class="content">
			<span>用户不是项目经理，无法登录</span>
			<span>帐号或密码错误</span>
		</div>
	</div> -->
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/global.js"></script>
	<script type="text/javascript">
		// globalUtil.fn.alert('请检查输入信息',2.0);
		//获取设备高度（软键盘调出来时高度也会变小，所以在点击事件前获取）
		var deviceH=document.documentElement.clientHeight+"px";
		//表单获得焦点后动态改变body和背景图片的大小
	      $('select,input').on("click",function(){
	      	 $("body").attr("style","background:linear-gradient(#6ab3f4, #0780ec);width:100%;height:"+deviceH+";background-size: 100%"+deviceH);
	      });
	</script>
</body>
</html>