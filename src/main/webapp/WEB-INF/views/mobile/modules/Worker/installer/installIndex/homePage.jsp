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
	<title>首页</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/new1/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/new1/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/new1/minePage.css"/>
</head>
<body>
	<div class="font0">
		<div class="top">
			<div class="user_info clearfix">
				<div class="headimg"><img src="${ctxStatic}/mobile/modules/Worker/img/userImg.png" alt=""></div>
				<div class="rgt">
					<span class="user_name">${worker.realname }<span class="eng_name"> / ${worker.loginname }</span></span>
				</div>
				<div class="total">
					<span class="font24 col_7f7f7f"><span class="pr16 font40 col_ff9800">${unfinishedCount}</span>未完工</span>
					<span class="font40 col_b2 pl13 pr13">/</span>
					<span class="font24 col_7f7f7f"><span class="pr16 font40 col_ff9800">${finishedCount}</span>已完工</span>
					<span class="font40 col_b2 pl13 pr13">/</span>
					<span class="font24 col_7f7f7f"><span class="pr16 font40 col_ff9800">${allCount}</span>合计</span>
				</div>
			</div>
		</div>
		<ul class="mt114 bg_f shadow clearfix">
			<a class="list-line bg_f itemList clearfix" href="${ctx}/app/worker/install/installItemList/list"><li class="list-text">安装项列表</li></a>
			<a class="list-line bg_f signList clearfix" href="${ctx}/app/worker/install/installSign/list"><li class="list-text">我要签到</li></a>
			<a class="list-line bg_f quesList clearfix" href="${ctx}/app/worker/install/installProblem/list"><li class="list-text">上报安装问题</li></a>
			<a class="list-line bg_f doneList clearfix" href="${ctx}/app/worker/install/installApplyForCompletion/list">
				<li class="list-text">申请完工<p class="font24 col_b30808 pr70">安装完成并且项目经理线下验收后，才能点申请完工哟</p></li>
			</a>
		</ul>
	</div>
	<div style="padding-top:300px;"></div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript">
		var u = navigator.userAgent;
		var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1; //android终端
		var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端	
		if(isAndroid){
			var callbackButton = document.getElementById("clearId");
			callbackButton.onclick = function(e){
				android.cacheclear();
			}
		}
		if(isiOS){
			window.onerror = function(err) {
	     		log('window.onerror: ' + err)
	     	}
	        function setupWebViewJavascriptBridge(callback) {
	             if (window.WebViewJavascriptBridge) { return callback(WebViewJavascriptBridge); }
	             if (window.WVJBCallbacks) { return window.WVJBCallbacks.push(callback); }
	             window.WVJBCallbacks = [callback];
	             var WVJBIframe = document.createElement('iframe');
	             WVJBIframe.style.display = 'none';
	             WVJBIframe.src = 'https://__bridge_loaded__';
	             document.documentElement.appendChild(WVJBIframe);
	             setTimeout(function() { document.documentElement.removeChild(WVJBIframe) }, 0);
	        }
	        setupWebViewJavascriptBridge(function(bridge) {
	     		//var callbackButton = document.getElementById('buttons').appendChild(document.createElement('button'))
	     		var callbackButton = document.getElementById("clearId");
	     		//callbackButton.innerHTML = 'Fire testObjcCallback';
	     		callbackButton.onclick = function(e) {
	     			e.preventDefault()
	     			bridge.callHandler('cacheclear', function(response) {
	     			});
	     		}
	     	})
		}
	</script>
</body>
</html>