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
	<title>工长登陆</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/globalUtil.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/login.css"/>
	<style type="text/css">
		
	</style>
</head>
<body>
	<div class="g-login">
		<div class="logo">
			<img src="${ctxStatic}/mobile/modules/Manager/images/logo.png" alt="大美装饰管理平台">
		</div>
		<h3 class="logo_text">大美装饰管理平台，是你最好的选择</h3>
		<div class="login_form">
			<form id="loginForm" action="${ctx}/app/manager/login" method="post">
				<input type="hidden" name="ReturnUrl" value="${param.ReturnUrl }"/>
				<input id="phone" type="text" name="phone" value="" placeholder="请输入大美装饰管理平台预留手机号">
				<input id="password" type="password" name="password" value="" placeholder="请输入密码">
				<!-- <a id="user_login_btn" href="javascript:;" onclick ="login()">登录</a> -->
				<input id="user_login_btn" type="submit" value="登 录"/>
			</form>
		</div>
	</div>
	<!-- <div id="alertDialog" class="">
		<div class="content">
			<span>用户不是项目经理，无法登录</span>
			<span>帐号或密码错误</span>
		</div>
	</div> -->
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/global.js"></script>
	<!-- <script type="text/javascript">
		globalUtil.fn.alert('message',1.0);
	</script> -->
</body>
</html>