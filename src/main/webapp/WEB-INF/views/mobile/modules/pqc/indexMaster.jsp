<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta content="telephone=no" name="format-detection">
<meta content="email=no" name="format-detection">
<title>首页</title>
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/mobile/modules/pqc/css/reset.css" />
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/mobile/modules/pqc/css/infoMaster.css" />
<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/info.css"/>
<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/manager_info.css"/>
</head>
<body>
	<div class="g_info">
		<div class="top">
			<div class="user_info">
				<img src="${ctxStatic}/mobile/modules/pqc/images/headimg.png"
					alt="headimg"> <span class="user_name">${inspector.realName }</span>
			</div>
		</div>
		<ul class="info_ul">
			<li class="select"><a href="${ctx}/app/pqc/selectCheckList/list">抽检</a></li>
			<li class="recheck"><a
				href="${ctx }/app/pqc/recheck/recheckList">复检</a></li>
			<li class="report"><a href="${ctx}/app/pqc/report/reportList">质检报告</a></li>
			<li class="swiper"><a id="clearId" href="javascript:void(0)">清除缓存</a></li>
		</ul>
		<a class="quit font30 col_0780ec" href="javascript:void(0)" onclick="showTishi()">退出登录</a>
		<div style="padding-top: 300px;"></div>
		<input type="text" hidden="hidden" value="${isevalCount }"
			id="evalCount">

		<div id="tishi" class="alertDialog undis">
			<div class="tip-wrap">
				<p class="tip-tit">提示</p>
				<p class="tip-content">您确定要退出登录吗？</p>
				<a class="tip-btn bor_rgt" href="javascript:;" onclick="cancel()">取消</a>
				<a class="tip-btn" href="javascript:;" onclick="logout()">确定</a>
			</div>
		</div>
		 <script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
		<script type="text/javascript"
			src="${ctxStatic}/mobile/modules/pqc/js/utils/calcRootFontSize.js"></script>
		<script type="text/javascript">
		function showTishi(){
			$("#tishi").show();
		}
		function cancel(){
			$("#tishi").hide();
		}
		function logout(){
			window.location.href="${ctx}/app/pqc/toLogout";
		}   
		
			var u = navigator.userAgent;
			var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1; //android终端
			var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端	
			if (isAndroid) {
				var callbackButton = document.getElementById("clearId");
				callbackButton.onclick = function(e) {
					android.cacheclear();
				}
			}
			if (isiOS) {
				window.onerror = function(err) {
					log('window.onerror: ' + err)
				}
				function setupWebViewJavascriptBridge(callback) {
					if (window.WebViewJavascriptBridge) {
						return callback(WebViewJavascriptBridge);
					}
					if (window.WVJBCallbacks) {
						return window.WVJBCallbacks.push(callback);
					}
					window.WVJBCallbacks = [ callback ];
					var WVJBIframe = document.createElement('iframe');
					WVJBIframe.style.display = 'none';
					WVJBIframe.src = 'https://__bridge_loaded__';
					document.documentElement.appendChild(WVJBIframe);
					setTimeout(function() {
						document.documentElement.removeChild(WVJBIframe)
					}, 0);
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