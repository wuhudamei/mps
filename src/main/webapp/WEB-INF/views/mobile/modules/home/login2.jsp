<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
	<metaname="screen-orientation"content="portrait">
	<metaname="x5-orientation"content="portrait">
	<!-- <meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="black" name="apple-mobile-web-app-status-bar-style">
	<meta content="telephone=no" name="format-detection">
	<meta content="email=no" name="format-detection">
	<meta content="*" name="Access-Control-Allow-Origin"> --><!--跨域请求  -->
	<title>客户登陆</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/globalUtil.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/login.css"/>
</head>
<body>
	<div class="g-login">
		<div class="logo">
			<img src="${ctxStatic}/mobile/modules/home/images/logo.png" alt="大美装饰管理平台">
		</div>
		<div class="login_form">
			<%-- <form id="loginForm" action="${ctx}/app/manager/login" method="post"> --%>
			<form id="loginForm" action="${ctx}/app/inspector/inspectorIndex" method="post">
			
				<input id="username" type="text" name="username" placeholder="请输入大美装饰管理平台合同预留手机号">
				<!-- <div>
					<input id="yanzheng" type="text" name="yanzheng" placeholder="短信验证码">
					<input id ="get_num" type="button" value="获取验证码" onclick="getCode()"/>  
				</div> -->
				<a id="user_login_btn" href="javascript:void(0)" onclick="login()">登录</a>
			</form>
		</div>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/utils/global.js"></script>
	<script type="text/javascript">
		// globalUtil.fn.alert('请检查输入信息',2.0);
		//获取设备高度（软键盘调出来时高度也会变小，所以在点击事件前获取）

　　　　　　　 var deviceH=document.documentElement.clientHeight+"px";

　　　　　　　//表单获得焦点后动态改变body和背景图片的大小
          $('select,input').on("click",function(){
           $("body").attr("style","background:linear-gradient(#6ab3f4, #0780ec);width:100%;height:"+deviceH+";background-size: 100%"+deviceH);
          });
          /**
  		 * [mql description]
  		 * 解决横竖屏切换问题
  		 * @type {[type]}
  		 */
  		var mql = window.matchMedia("(orientation: portrait)");
  		 console.log(mql);
  		// 如果有匹配，则我们处于垂直视角
  		if(mql.matches) { 
  			// 直立方向
  			console.log("1")
  			$('#g-alertDialog .content').css({'margin-top':'70%'});
  		} else { 
  			//水平方向
  			console.log("2");
  			document.querySelector('html').style.height = 'auto';
  			$('#g-alertDialog .content').css({'margin-top':'30%'});
  			$('.g-login').css({'padding-bottom':'50px'});
  			console.log(document.querySelector('html').style.height);
  		} 
  		// 添加一个媒体查询改变监听者
  		mql.addListener(function(m) {
  			if(m.matches) {
  				// 改变到直立方向
  				console.log("改变到直立方向");
  				document.querySelector('html').style.height = '100%';
  				$('#g-alertDialog .content').css({'margin-top':'70%'});
  				$('.g-login').css({'padding-bottom':'0px'});
  			}
  			else {
  				console.log("改变到水平方向");
  				// 改变到水平方向
  				document.querySelector('html').style.height = 'auto';
  				$('#g-alertDialog .content').css({'margin-top':'30%'});
  				$('.g-login').css({'padding-bottom':'50px'});
  			}
  		});
          
          
          
          
		var wait=60;
   		var interValObj; 
   		//$("#get_num").attr("onclick");
   		function getCode(){
			 var phone = $("#username").val();

          /*  var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
            if(!myreg.test(phone)){
                globalUtil.fn.alert('您输入的手机号有误！',1.0);
            }else{

            }*/
            if(!isNaN(phone) && phone.length==11){
                $("#get_num").attr("disabled", "true");
                $("#get_num").val("倒计时 " + wait + "秒");
				/* $("#get_num").removeAttr("onclick");
				 $("#get_num").text("倒计时 " + wait + "秒");  */
                interValObj = window.setInterval(setRemainTime, 1000);
                $.ajax({
                    url:"${ctx}/app/home/isExist?phone="+phone,
                    type:"get",
                    success:function(isExist){
                        if(isExist==1){
                            $.ajax({
                                url:"${ctx}/app/home/get?phone="+phone,
                                type:"get",
                                success:function(code){
                                    //发送短信
                                }
                            });
                        }else if(isExist==0){


                            globalUtil.fn.alert('您输入的手机号不是签合同时预留手机号，请重新输入。',2.0);
                        }else{

                            globalUtil.fn.alert('您的订单有误',2.0);

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
   	            $("#get_num").val("重新发送验证码");
   	         	wait = 60;
   	        }  
   	    } 
   		
   		function login(){
   			//var orderId = "${orderId}";
   			//var mycode = $("#yanzheng").val();
   			var phone = $("#username").val();
			var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
   			//var mycodereg = /^\d{6}$/;
   			if(!myreg.test(phone)){
   				globalUtil.fn.alert('您输入的手机号格式有误！',1.0);
   			}else{
   				/* if(!mycodereg.test(mycode)){
   	   				globalUtil.fn.alert('您输入的验证码格式有误！',1.0);
   	   			}else{ */
   	   				//$("#loginForm").submit();
   	   				$.ajax({
	   					type:"post",
	   					url:"${ctx}/app/home/login1",
	   					data:{"username":phone},
	   					success:function(data){
	   						/* if(data == "1"){
	   							globalUtil.fn.alert("验证码有误！",1.0);
	   						}
	   						if(data == "2"){
	   							globalUtil.fn.alert("请先获取验证码！",1.0);
	   						} */
	   						if(data == "success"){
	   							window.location.href = "${ctx}/app/home/toMyHome";
	   						}
	   						/* if(data == "4"){
	   							globalUtil.fn.alert("您输入的手机号不是签合同时预留手机号，请重新输入。",1.0);
	   						} */
	   					}
	   				});
   	   			//}
   			}
   		}
	</script>
</body>
</html>